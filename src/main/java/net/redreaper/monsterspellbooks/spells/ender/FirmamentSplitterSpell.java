package net.redreaper.monsterspellbooks.spells.ender;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.CameraShakeData;
import io.redspace.ironsspellbooks.api.util.CameraShakeManager;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.init.ModSounds;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class FirmamentSplitterSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "firmament_splitter");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.damage", new Object[]{this.getDamageText(spellLevel, caster)}), Component.translatable("ui.irons_spellbooks.distance", new Object[]{Utils.stringTruncation((double)this.getRange(spellLevel, caster), 1)}));
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.COMMON)
            .setSchoolResource(SchoolRegistry.ENDER_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(15)
            .build();

    public FirmamentSplitterSpell() {
        this.manaCostPerLevel = 15;
        this.baseSpellPower = 5;
        this.spellPowerPerLevel = 2;
        this.castTime = 10;
        this.baseManaCost = 30;
    }

    @Override
    public Optional<SoundEvent> getCastStartSound() {return Optional.of(ModSounds.BLASTLING_AMBIENT.get());}

    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of(SoundRegistry.ECHOING_STRIKE.get());
    }

    @Override
    public CastType getCastType() {
        return CastType.LONG;
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public ResourceLocation getSpellResource() {
        return spellId;
    }

    public boolean canBeInterrupted(@Nullable Player player) {
        return false;
    }

    @Override
    public int getEffectiveCastTime(int spellLevel, @Nullable LivingEntity entity) {return getCastTime(spellLevel);}

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        float radius = 0.5F;
        Vec3 forward = entity.getForward().multiply((double)1.0F, (double)0.0F, (double)1.0F).normalize();
        Vec3 start = entity.getEyePosition().subtract((double)0.0F, (double)0.5F, (double)0.0F).add(forward.scale((double)1.5F));
        float count = this.getRange(spellLevel, entity);
        CameraShakeManager.addCameraShake(new CameraShakeData(level, 10, entity.position(), 10.0F));

        for(int i = 0; (float)i < count; ++i) {
            Vec3 strikeLocation = start.add(forward.scale((double)i));
            Vec3 particleLocation = level.clip(new ClipContext(strikeLocation, strikeLocation.add((double)0.0F, (double)-2.0F, (double)0.0F), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, CollisionContext.empty())).getLocation().add((double)0.0F, 0.1, (double)0.0F);
            MagicManager.spawnParticles(level, ModParticleHelper.SPACE_SHARD, particleLocation.x, particleLocation.y, particleLocation.z, 25, (double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F, false);
            MagicManager.spawnParticles(level, ParticleHelper.UNSTABLE_ENDER, particleLocation.x, particleLocation.y, particleLocation.z, 25, (double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F, false);
            List<Entity> entities = level.getEntities(entity, AABB.ofSize(strikeLocation, (double)radius, (double)(radius * 3.0F), (double)radius));
            SpellDamageSource damageSource = this.getDamageSource(entity);

            for(Entity targetEntity : entities) {
                if (targetEntity.isAlive() && targetEntity.isPickable() && Utils.hasLineOfSight(level, strikeLocation.add((double)0.0F, (double)1.0F, (double)0.0F), targetEntity.getBoundingBox().getCenter(), true) && DamageSources.applyDamage(targetEntity, this.getDamage(spellLevel, entity), damageSource)) {
                    EnchantmentHelper.doPostAttackEffects((ServerLevel)level, targetEntity, damageSource);
                }
            }
        }

        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    private float getRange(int spellLevel, LivingEntity entity) {
        return (float)(9 + spellLevel * 2);
    }

    private float getDamage(int spellLevel, LivingEntity entity) {
        return this.getSpellPower(spellLevel, entity) + this.getAdditionalDamage(entity);
    }

    private float getAdditionalDamage(LivingEntity entity) {
        if (entity == null) {
            return 0.0F;
        } else {
            float weaponDamage = Utils.getWeaponDamage(entity);
            return weaponDamage;
        }
    }

    private String getDamageText(int spellLevel, LivingEntity entity) {
        if (entity != null) {
            float weaponDamage = this.getAdditionalDamage(entity);
            String plus = "";
            if (weaponDamage > 0.0F) {
                plus = String.format(" (+%s)", Utils.stringTruncation((double)weaponDamage, 1));
            }

            String damage = Utils.stringTruncation((double)this.getDamage(spellLevel, entity), 1);
            return damage + plus;
        } else {
            float var10000 = this.getSpellPower(spellLevel, entity);
            return "" + var10000;
        }
    }

    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.ONE_HANDED_VERTICAL_UPSWING_ANIMATION;
    }

    public AnimationHolder getCastFinishAnimation() {
        return AnimationHolder.pass();
    }
}
