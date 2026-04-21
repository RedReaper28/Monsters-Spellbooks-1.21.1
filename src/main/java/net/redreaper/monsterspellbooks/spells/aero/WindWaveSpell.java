package net.redreaper.monsterspellbooks.spells.aero;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.init.ModSounds;
import net.redreaper.monsterspellbooks.init.ModSpellSchools;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class WindWaveSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "wind_wave");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.distance", getRange(spellLevel, caster))
        );
    }
    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(ModSpellSchools.AERO_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(20)
            .build();

    public WindWaveSpell() {
        this.manaCostPerLevel = 5;
        this.baseSpellPower = 10;
        this.spellPowerPerLevel = 2;
        this.castTime = 16;
        this.baseManaCost = 10;
    }

    @Override
    public Optional<SoundEvent> getCastStartSound() {return Optional.of(SoundEvents.WIND_CHARGE_THROW);}

    @Override
    public Optional<SoundEvent> getCastFinishSound() {return Optional.of(ModSounds.WIND_WAVE.get());}

    @Override
    public CastType getCastType() {
        return CastType.LONG;
    }

    @Override
    public boolean canBeInterrupted(Player player) {
        return false;
    }

    @Override
    public int getEffectiveCastTime(int spellLevel, @Nullable LivingEntity entity) {return getCastTime(spellLevel);}

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public ResourceLocation getSpellResource() {
        return spellId;
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        float radius = 5.0F;
        float range = 1.7F;
        MagicManager.spawnParticles(level, ParticleTypes.GUST, entity.getX(), entity.getY() + (double) 1.0F, entity.getZ(), 50, (double) 0.0F, (double) 0.0F, (double) 0.0F, (double) 1.0F, false);
        MagicManager.spawnParticles(level, new BlastwaveParticleOptions(ModSpellSchools.AERO.get().getTargetingColor(), radius), entity.getX(), entity.getY() + .165f, entity.getZ(), 1, 0, 0, 0, 0, true);
        Vec3 smiteLocation = Utils.raycastForBlock(entity.level(), entity.getEyePosition(), entity.getEyePosition().add(entity.getForward().multiply((double)range, (double)0.0F, (double)range)), ClipContext.Fluid.NONE).getLocation();
        List<Entity> entities = entity.level().getEntities(entity, AABB.ofSize(smiteLocation, (double)(radius * 2.0F), (double)(radius * 4.0F), (double)(radius * 2.0F)));
        SpellDamageSource damageSource = this.getDamageSource(entity);

        for(Entity targetEntity : entities) {
            if (targetEntity.isAlive() && targetEntity.isPickable() && Utils.hasLineOfSight(entity.level(), smiteLocation.add((double)0.0F, (double)1.0F, (double)0.0F), targetEntity.getBoundingBox().getCenter(), true) && DamageSources.applyDamage(targetEntity, this.getDamage(spellLevel, entity), damageSource)) {
                this.handleKnockback(entity, (LivingEntity)targetEntity, spellLevel);
            }
        }

        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    public void handleKnockback(LivingEntity entity, LivingEntity targetEntity, int spellLevel) {
        Vec3 angleVector = entity.position().subtract(targetEntity.position()).normalize();
        Vec3 vec = angleVector.multiply((double)2.0F, (double)1.0F, (double)2.0F).normalize().scale((double)(-1.0F * this.getForce(spellLevel, entity)));
        if (targetEntity instanceof ServerPlayer serverPlayer) {
            serverPlayer.hurtMarked = true;
        }

        targetEntity.setDeltaMovement(targetEntity.getDeltaMovement().add(vec));
        targetEntity.addEffect(new MobEffectInstance(MobEffectRegistry.AIRBORNE, 120, 1));
        targetEntity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 120, 1));
        targetEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 2));
        Vec3 particleLocation = targetEntity.position();
        MagicManager.spawnParticles(entity.level(), ParticleTypes.GUST, particleLocation.x, particleLocation.y + (double)(targetEntity.getBbHeight() / 2.0F), particleLocation.z, 50, (double)0.0F, (double)0.0F, (double)0.0F, 0.2, false);
    }

    private float getForce(int spellLevel, LivingEntity entity) {return this.getSpellPower(spellLevel, entity) * 0.1F;}

    private float getDamage(int spellLevel, LivingEntity caster) {
        return getSpellPower(spellLevel, caster)*.50f;
    }

    private int getRange(int spellLevel, LivingEntity caster) {return (int) (12 + spellLevel * getEntityPowerMultiplier(caster)); }

    @Override
    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.PREPARE_CROSS_ARMS;
    }

    public AnimationHolder getCastFinishAnimation() {
        return SpellAnimations.CAST_T_POSE;
    }

    @Override
    public boolean shouldAIStopCasting(int spellLevel, Mob mob, LivingEntity target) {
        float f = getRange(spellLevel, mob);
        return mob.distanceToSqr(target) > (f * f) * 1.2;
    }
}