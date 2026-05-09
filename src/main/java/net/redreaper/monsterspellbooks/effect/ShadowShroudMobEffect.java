package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.ISyncedMobEffect;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
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
public class ShadowShroudMobEffect extends MagicMobEffect implements ISyncedMobEffect {
    public static final float DAMAGE_PER_LEVEL = 2;
    public ShadowShroudMobEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void onEffectAdded(LivingEntity livingEntity, int pAmplifier) {
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
    }

    @SubscribeEvent
    public static void increaseDamage(LivingIncomingDamageEvent event) {
        var attacker = event.getSource().getEntity();
        if (attacker instanceof LivingEntity livingAttacker) {
            if (livingAttacker.hasEffect(ModMobEffects.SHADOW_SHROUD)) {
                    int lvl = livingAttacker.getEffect(ModMobEffects.SHADOW_SHROUD).getAmplifier() + 1;
                    float before = event.getAmount();
                    float multiplier = ShadowShroudMobEffect.DAMAGE_PER_LEVEL * lvl;
                    event.setAmount(event.getAmount() * multiplier);
            }
            livingAttacker.removeEffect(ModMobEffects.SHADOW_SHROUD);
        }

    }
}

