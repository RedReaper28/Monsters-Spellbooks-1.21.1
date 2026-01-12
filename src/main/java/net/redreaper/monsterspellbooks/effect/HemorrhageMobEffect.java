package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import io.redspace.ironsspellbooks.effect.ISyncedMobEffect;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.init.ModMobEffects;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.WeakHashMap;

public class HemorrhageMobEffect extends MagicMobEffect implements ISyncedMobEffect {

    public static final int STACKS_REQUIRED = 3;
    public static final int STACKS_REQUIRED_AMPLIFIER = STACKS_REQUIRED - 1;

    private static final Map<LivingEntity, Entity> EFFECT_CREDIT = new WeakHashMap<>();

    private static final Map<MobEffectInstance, Integer> DELAYED_INSTANCES = new WeakHashMap<>();

    public HemorrhageMobEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    public static MobEffectInstance addHemorrhageStack(LivingEntity entity, @Nullable Entity afflicter) {
        MobEffectInstance previous = entity.getEffect(ModMobEffects.HEMORRHAGE);
        MobEffectInstance inst;
        if (previous != null) {
            inst = new MobEffectInstance(ModMobEffects.HEMORRHAGE, 20 * 15, previous.getAmplifier() + 1, previous.isAmbient(), previous.isVisible(), previous.showIcon());
        } else {
            inst = new MobEffectInstance(ModMobEffects.HEMORRHAGE, 20 * 15, 0, false, false, true);
        }
        if (afflicter != null) {
            EFFECT_CREDIT.put(entity, afflicter);
        }
        entity.addEffect(inst);
        return inst;
    }

    @Override
    public void clientTick(LivingEntity livingEntity, MobEffectInstance instance) {
        int amplifier = instance.getAmplifier();
        ParticleOptions particle = ParticleHelper.BLOOD;
        if (amplifier >= 1) {
            particle = ParticleHelper.BLOOD;
        }
        var random = livingEntity.getRandom();
        for (int i = 0; i < 2; i++) {
            Vec3 motion = new Vec3(
                    random.nextFloat() * 2 - 1,
                    random.nextFloat() * 2 - 1,
                    random.nextFloat() * 2 - 1
            );
            motion = motion.scale(.04f);
            livingEntity.level().addParticle(particle, livingEntity.getRandomX(.4f), livingEntity.getRandomY(), livingEntity.getRandomZ(.4f), motion.x, motion.y, motion.z);
        }
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        var self = livingEntity.getEffect(ModMobEffects.HEMORRHAGE);
        if (DELAYED_INSTANCES.containsKey(self) && !(DELAYED_INSTANCES.get(self) - duration > 4)) {
            return true;
        }
        float explosionRadius = 6;
        var level = livingEntity.level();
        if (level.isClientSide) {
            return true;
        }
        @Nullable Entity attacker = EFFECT_CREDIT.remove(livingEntity);
        double baseDamage = damageFor(attacker);

        var source = new DamageSource(level.damageSources().damageTypes.getHolderOrThrow(ISSDamageTypes.BLOOD_MAGIC), attacker);

        var explosionRadiusSqr = explosionRadius * explosionRadius;
        var entities = level.getEntities(null, livingEntity.getBoundingBox().inflate(explosionRadius));
        Vec3 losPoint = Utils.raycastForBlock(level, livingEntity.position(), livingEntity.position().add(0, 1, 0), ClipContext.Fluid.NONE).getLocation();
        for (Entity entity : entities) {
            double distanceSqr = entity.distanceToSqr(livingEntity.position());
            if (distanceSqr < explosionRadiusSqr && entity.canBeHitByProjectile() && !DamageSources.isFriendlyFireBetween(attacker, entity) && Utils.hasLineOfSight(level, losPoint, entity.getBoundingBox().getCenter(), true)) {
                double p = (1 - distanceSqr / explosionRadiusSqr);
                float damage = (float) (baseDamage * p);
                if (entity.hurt(source, damage) && entity instanceof LivingEntity livingVictim) {
                    var inst = addHemorrhageStack(livingVictim, attacker);
                    DELAYED_INSTANCES.put(inst, inst.getDuration());
                }
            }
        }
        level.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), SoundRegistry.BLOOD_EXPLOSION.value(), livingEntity.getSoundSource(), 4.0F, (1.0F + (level.random.nextFloat() - level.random.nextFloat()) * 0.2F) * 0.7F);
        return false;
    }

    public static double damageFor(@Nullable Entity entity) {
        double baseDamage = 10.0;
        if (entity instanceof LivingEntity livingAttacker) {
            baseDamage = baseDamage * livingAttacker.getAttributeValue(AttributeRegistry.SPELL_POWER) * livingAttacker.getAttributeValue(AttributeRegistry.BLOOD_SPELL_POWER);
        }
        return baseDamage;
    }

    static int duration;

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        HemorrhageMobEffect.duration = duration;
        return amplifier >= STACKS_REQUIRED_AMPLIFIER;
    }
}
