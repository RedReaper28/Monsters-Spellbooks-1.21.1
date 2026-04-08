package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.effect.ISyncedMobEffect;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import io.redspace.ironsspellbooks.network.particles.FieryExplosionParticlesPacket;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.redreaper.monsterspellbooks.init.ModMobEffects;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.WeakHashMap;

@EventBusSubscriber
public class VoidTouchedEffect extends MagicMobEffect implements ISyncedMobEffect {
    public static final float DAMAGE_PER_LEVEL = 0.10F;
    private static final Map<LivingEntity, Entity> EFFECT_CREDIT = new WeakHashMap<>();

    public VoidTouchedEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    public static MobEffectInstance addVoidStack(LivingEntity entity, @Nullable Entity afflicter) {
        MobEffectInstance previous = entity.getEffect(ModMobEffects.VOID_TOUCHED);
        MobEffectInstance inst;
        if (previous != null) {
            inst = new MobEffectInstance(ModMobEffects.VOID_TOUCHED, 20 * 5, previous.getAmplifier() + 1, previous.isAmbient(), previous.isVisible(), previous.showIcon());
        } else {
            inst = new MobEffectInstance(ModMobEffects.VOID_TOUCHED, 20 * 5, 0, false, true, true);
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
        ParticleOptions particle = ParticleHelper.ENDER_SPARKS;
        if (amplifier >= 1) {
            particle = ParticleHelper.UNSTABLE_ENDER;
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
        var self = livingEntity.getEffect(MobEffectRegistry.IMMOLATE);
        float explosionRadius = 6;
        var level = livingEntity.level();
        if (level.isClientSide) {
            return true;
        }

        var entities = level.getEntities(null, livingEntity.getBoundingBox().inflate(explosionRadius));
        Vec3 losPoint = Utils.raycastForBlock(level, livingEntity.position(), livingEntity.position().add(0, 1, 0), ClipContext.Fluid.NONE).getLocation();
        for (Entity entity : entities) {
            double distanceSqr = entity.distanceToSqr(livingEntity.position());
        }
        PacketDistributor.sendToPlayersTrackingEntity(livingEntity, new FieryExplosionParticlesPacket(livingEntity.getBoundingBox().getCenter(), 1.5f));
        level.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), SoundEvents.GENERIC_EXPLODE.value(), livingEntity.getSoundSource(), 4.0F, (1.0F + (level.random.nextFloat() - level.random.nextFloat()) * 0.2F) * 0.7F);
        return false;
    }

    @SubscribeEvent
    public static void increaseDamage(LivingIncomingDamageEvent event) {
        var attacker = event.getSource().getEntity();
        if (attacker instanceof LivingEntity livingAttacker) {
                if (event.getEntity().hasEffect(ModMobEffects.VOID_TOUCHED)) {
                    int lvl = event.getEntity().getEffect(ModMobEffects.VOID_TOUCHED).getAmplifier() + 1;
                    float before = event.getAmount();
                    float multiplier = 1 + VoidTouchedEffect.DAMAGE_PER_LEVEL * lvl;
                    event.setAmount(event.getAmount() * multiplier);
                }
        }
    }
}

