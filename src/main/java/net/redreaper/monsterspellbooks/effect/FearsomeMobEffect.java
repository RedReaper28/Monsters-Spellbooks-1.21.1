package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.ISyncedMobEffect;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.redreaper.monsterspellbooks.init.ModMobEffects;

import static io.redspace.ironsspellbooks.api.util.Utils.random;

@EventBusSubscriber
public class FearsomeMobEffect extends MagicMobEffect implements ISyncedMobEffect {
    public FearsomeMobEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int pAmplifier) {
        super.applyEffectTick(livingEntity, pAmplifier);

        var targetingCondition = TargetingConditions.forCombat().ignoreLineOfSight().selector(e -> {
            return (((Mob) e).getTarget() == livingEntity);
        });

        livingEntity.level().getNearbyEntities(Mob.class, targetingCondition, livingEntity, livingEntity.getBoundingBox().inflate(40D))
                .forEach(entityTargetingCaster -> {
                    entityTargetingCaster.setTarget(null);
                    entityTargetingCaster.setLastHurtByMob(null);
                    if (entityTargetingCaster.onGround()) {
                        Vec3 randomShake = new Vec3(random.nextFloat() - 0.5F, 0, random.nextFloat() - 0.5F).scale(0.1F);
                        entityTargetingCaster.setDeltaMovement(entityTargetingCaster.getDeltaMovement().multiply(0.7F, 1, 0.7F).add(randomShake));
                    }

                    entityTargetingCaster.targetSelector.getAvailableGoals().forEach(WrappedGoal::stop);
                    entityTargetingCaster.getBrain().setMemory(MemoryModuleType.AVOID_TARGET,entityTargetingCaster.getTarget());

                    entityTargetingCaster.getNavigation().stop();
                    if (entityTargetingCaster.getNavigation().isDone()) {
                        Vec3 vec = LandRandomPos.getPosAway((PathfinderMob) entityTargetingCaster, 7, 7, entityTargetingCaster.position());
                        if (vec != null) {
                            entityTargetingCaster.getNavigation().moveTo(vec.x, vec.y, vec.z, 2D);
                        }
                    }

                });
        return true;
    }

    @SubscribeEvent
    public static void onDealDamage(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity livingAttacker && livingAttacker.hasEffect(ModMobEffects.CHARMING)) {
            livingAttacker.removeEffect(ModMobEffects.CHARMING);
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplifier) {
        return true;
    }
}
