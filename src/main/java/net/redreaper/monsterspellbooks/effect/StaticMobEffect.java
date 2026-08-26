package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.effect.ISyncedMobEffect;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.redreaper.monsterspellbooks.init.ModMobEffects;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.WeakHashMap;

@EventBusSubscriber
public class StaticMobEffect extends MagicMobEffect implements ISyncedMobEffect {
    private static final Map<LivingEntity, Entity> EFFECT_CREDIT = new WeakHashMap<>();

    private static final Map<MobEffectInstance, Integer> DELAYED_INSTANCES = new WeakHashMap<>();

    public StaticMobEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    public static MobEffectInstance addStaticStack(LivingEntity entity, @Nullable Entity afflicter) {
        MobEffectInstance previous = entity.getEffect(ModMobEffects.STATIC);
        MobEffectInstance inst;
        if (previous != null) {
            inst = new MobEffectInstance(ModMobEffects.STATIC, 20 * 15, previous.getAmplifier() + 1, previous.isAmbient(), previous.isVisible(), previous.showIcon());
        } else {
            inst = new MobEffectInstance(ModMobEffects.STATIC, 20 * 15, 0, false, false, true);
        }
        if (afflicter != null) {
            EFFECT_CREDIT.put(entity, afflicter);
        }
        entity.addEffect(inst);
        StaticEffectData.get(entity).setHitCount(inst.getAmplifier());

        return inst;
    }

    @Override
    public void onEffectRemoved(LivingEntity pLivingEntity, int pAmplifier) {
        StaticEffectData.remove(pLivingEntity);
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Post event) {
        LivingEntity entityTarget = event.getEntity();
        Entity entityAttacker = event.getSource().getDirectEntity();
        var effect = entityTarget.getEffect(ModMobEffects.STATIC);
        if (effect == null) {
            return;
        }

        var data = StaticEffectData.get(entityTarget);

        if (entityAttacker instanceof LivingEntity livingAttacker) {
                var source = new DamageSource(entityTarget.level().damageSources().damageTypes.getHolderOrThrow(DamageTypes.LIGHTNING_BOLT), entityTarget);
                float baseDamage = event.getOriginalDamage();
                float thornDamage = baseDamage * .5f;
                livingAttacker.hurt(source,baseDamage);
                livingAttacker.addEffect(new MobEffectInstance(ModMobEffects.PARALYSIS, 5*20,1));
                MagicManager.spawnParticles(entityAttacker.level(), ModParticleHelper.REDSTONE_SPARKS, entityAttacker.getRandomX(entityAttacker.getBbWidth() * 0.45), entityAttacker.getRandomY() + 0.25, entityAttacker.getRandomZ(entityAttacker.getBbWidth() * 0.45), 1, 0, 0, 0, 0.25, false);
        }

        data.decrementHit();
        if (!data.hasHitsRemaining()) {
            entityTarget.removeEffect(ModMobEffects.STATIC);
        }
    }

}
