package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.ISyncedMobEffect;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.redreaper.monsterspellbooks.init.ModMobEffects;

@EventBusSubscriber
public class ShadowShiftMobEffect extends MagicMobEffect implements ISyncedMobEffect {
    public ShadowShiftMobEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int pAmplifier) {
        super.onEffectAdded(livingEntity, pAmplifier);

        var targetingCondition = TargetingConditions.forCombat().ignoreLineOfSight().selector(e -> {
            return (((Mob) e).getTarget() == livingEntity);
        });

        livingEntity.level().getNearbyEntities(Mob.class, targetingCondition, livingEntity, livingEntity.getBoundingBox().inflate(40D))
                .forEach(entityTargetingCaster -> {
                    entityTargetingCaster.setTarget(null);
                    entityTargetingCaster.targetSelector.getAvailableGoals().forEach(WrappedGoal::stop);
                    entityTargetingCaster.getBrain().eraseMemory(MemoryModuleType.ATTACK_TARGET);
                });
        return true;
    }

    @SubscribeEvent
    public static void onDealDamage(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity livingAttacker && livingAttacker.hasEffect(ModMobEffects.SHADOW_SHIFT)) {
            livingAttacker.removeEffect(ModMobEffects.SHADOW_SHIFT);
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplifier) {
        return true;
    }
}
