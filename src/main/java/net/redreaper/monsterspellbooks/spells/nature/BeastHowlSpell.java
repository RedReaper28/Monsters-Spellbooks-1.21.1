package net.redreaper.monsterspellbooks.spells.nature;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.acetheeldritchking.aces_spell_utils.spells.ASSpellAnimations;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.init.ModMobEffects;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class BeastHowlSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "beast_howl");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.distance", getRange(spellLevel, caster))
        );
    }
    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.UNCOMMON)
            .setSchoolResource(SchoolRegistry.NATURE_RESOURCE)
            .setMaxLevel(6)
            .setCooldownSeconds(240)
            .build();

    public BeastHowlSpell() {
        this.manaCostPerLevel = 20;
        this.baseSpellPower = 5;
        this.spellPowerPerLevel = 2;
        this.castTime = 16;
        this.baseManaCost = 75;
    }

    public Optional<SoundEvent> getCastFinishSound() {return Optional.of(SoundEvents.ENDER_DRAGON_AMBIENT);}

    public CastType getCastType() {
        return CastType.LONG;
    }

    public boolean canBeInterrupted(Player player) {
        return false;
    }

    public int getEffectiveCastTime(int spellLevel, @Nullable LivingEntity entity) {return getCastTime(spellLevel);}

    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    public ResourceLocation getSpellResource() {
        return spellId;
    }

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        float radius = getRadius(spellLevel, entity);
        entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,600));
        MagicManager.spawnParticles(level, new BlastwaveParticleOptions(SchoolRegistry.BLOOD.get().getTargetingColor(), radius), entity.getX(), entity.getY() + .165f, entity.getZ(), 1, 0, 0, 0, 0, true);
        level.getEntities(entity, entity.getBoundingBox().inflate((double)radius, (double)4.0F, (double)radius), (target) -> !DamageSources.isFriendlyFireBetween(target, entity) && Utils.hasLineOfSight(level, entity, target, false)).forEach((target) -> {
            if (target instanceof LivingEntity livingEntity) {
                if (livingEntity.distanceToSqr(entity) < (double)(radius * radius)) {
                    DamageSources.applyDamage(target, this.getDamage(spellLevel, entity), this.getDamageSource(entity));
                    this.handleKnockback(entity, entity, spellLevel);
                    ((LivingEntity) target).addEffect(new MobEffectInstance(ModMobEffects.STUNNED,60));
                }
        }
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    });
    }

    public void handleKnockback(LivingEntity entity, LivingEntity targetEntity, int spellLevel) {
        Vec3 angleVector = entity.position().subtract(targetEntity.position()).normalize();
        Vec3 vec = angleVector.multiply((double)2.0F, (double)1.0F, (double)2.0F).normalize().scale((double)(-1.0F * this.getForce(spellLevel, entity)));
        if (targetEntity instanceof ServerPlayer serverPlayer) {
            serverPlayer.hurtMarked = true;
        }
        targetEntity.setDeltaMovement(targetEntity.getDeltaMovement().add(vec));
    }

    private float getForce(int spellLevel, LivingEntity entity) {return this.getSpellPower(spellLevel, entity) * 0.1F;}

    private float getDamage(int spellLevel, LivingEntity caster) {
        return getSpellPower(spellLevel, caster);
    }

    private int getRange(int spellLevel, LivingEntity caster) {return (int) (6 + spellLevel * getEntityPowerMultiplier(caster)); }

    public float getRadius(int spellPower, LivingEntity caster) {
        return 5.0F + (float)spellPower * 0.5F;
    }


    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.PREPARE_CROSS_ARMS;
    }

    public AnimationHolder getCastFinishAnimation() {
        return ASSpellAnimations.ANIMATION_WRATH_ROAR;
    }
}
