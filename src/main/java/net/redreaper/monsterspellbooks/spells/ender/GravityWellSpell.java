package net.redreaper.monsterspellbooks.spells.ender;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.acetheeldritchking.aces_spell_utils.spells.ASSpellAnimations;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.init.ModMobEffects;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class GravityWellSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "gravity_well");

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(this.getDamage(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.radius", Utils.stringTruncation(this.getRadius(spellLevel, caster), 2)));
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.LEGENDARY)
            .setSchoolResource(SchoolRegistry.ENDER_RESOURCE)
            .setMaxLevel(3)
            .setCooldownSeconds(120)
            .build();

    public GravityWellSpell() {
        this.manaCostPerLevel = 25;
        this.baseSpellPower = 6;
        this.spellPowerPerLevel = 3;
        this.castTime = 25;
        this.baseManaCost = 90;
    }

    public DefaultConfig getDefaultConfig() {
        return this.defaultConfig;
    }

    public CastType getCastType() {
        return CastType.LONG;
    }

    public int getEffectiveCastTime(int spellLevel, @Nullable LivingEntity entity) {
        return this.getCastTime(spellLevel);
    }

    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of(SoundEvents.ANVIL_PLACE);
    }

    public ResourceLocation getSpellResource() {
        return this.spellId;
    }


    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        float radius = 5.0F;
        float range = 1.7F;
        MagicManager.spawnParticles(level, new BlastwaveParticleOptions(SchoolRegistry.ENDER.get().getTargetingColor(), radius), entity.getX(), entity.getY() + .165f, entity.getZ(), 1, 0, 0, 0, 0, true);
        Vec3 smiteLocation = Utils.raycastForBlock(entity.level(), entity.getEyePosition(), entity.getEyePosition().add(entity.getForward().multiply(range, 0.0F, range)), ClipContext.Fluid.NONE).getLocation();
        List<Entity> entities = entity.level().getEntities(entity, AABB.ofSize(smiteLocation, radius * 2.0F, radius * 4.0F, radius * 2.0F));
        SpellDamageSource damageSource = this.getDamageSource(entity);

        for(Entity targetEntity : entities) {
            if (targetEntity.isAlive() && targetEntity.isPickable() && Utils.hasLineOfSight(entity.level(), smiteLocation.add(0.0F, 1.0F, 0.0F), targetEntity.getBoundingBox().getCenter(), true) && DamageSources.applyDamage(targetEntity, this.getDamage(spellLevel, entity), damageSource)) {
                this.handleKnockback(entity, (LivingEntity)targetEntity, spellLevel);
            }
        }

        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    public void handleKnockback(LivingEntity entity, LivingEntity targetEntity, int spellLevel) {
        Vec3 angleVector = entity.position().subtract(targetEntity.position()).normalize();
        Vec3 vec = angleVector.multiply(-2.0F, -1.0F, -2.0F).normalize().scale(-1.0F * this.getForce(spellLevel, entity));
        if (targetEntity instanceof ServerPlayer serverPlayer) {
            serverPlayer.hurtMarked = true;
        }

        targetEntity.setDeltaMovement(targetEntity.getDeltaMovement().add(vec));
        targetEntity.addEffect(new MobEffectInstance(ModMobEffects.PARALYSIS, 60,3));
        targetEntity.addEffect(new MobEffectInstance(ModMobEffects.STUNNED, 60,3));
        Vec3 particleLocation = targetEntity.position();
        MagicManager.spawnParticles(entity.level(), ParticleHelper.UNSTABLE_ENDER, particleLocation.x, particleLocation.y + (double)(targetEntity.getBbHeight() / 2.0F), particleLocation.z, 50, 0.0F, 0.0F, 0.0F, 0.2, false);
    }

    private float getDamage(int spellLevel, LivingEntity caster) {
        return this.getSpellPower(spellLevel, caster);
    }

    private float getForce(int spellLevel, LivingEntity entity) {return this.getSpellPower(spellLevel, entity) * 0.5F;}

    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.CHARGE_RAISED_HAND;
    }

    public AnimationHolder getCastFinishAnimation() {return ASSpellAnimations.ANIMATION_GROUND_FIST_SLAM;}

    public float getRadius(int spellPower, LivingEntity caster) {
        return 5.0F + (float)spellPower * 0.5F;
    }
}
