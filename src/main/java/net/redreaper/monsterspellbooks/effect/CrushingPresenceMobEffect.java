package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.redreaper.monsterspellbooks.init.ModMobEffects;

public class CrushingPresenceMobEffect extends MagicMobEffect {

    public CrushingPresenceMobEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return pDuration % 75 == 0;
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int pAmplifier) {
        float radiusSqr = 300;
        entity.level().getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(10, 6, 10),
                        livingEntity -> livingEntity != entity &&
                                horizontalDistanceSqr(livingEntity, entity) < radiusSqr &&
                                livingEntity.isPickable() &&
                                !livingEntity.isSpectator() &&
                                Utils.hasLineOfSight(entity.level(), entity, livingEntity, false)
                )
                .forEach(targetEntity -> {
                    targetEntity.addEffect(new MobEffectInstance(ModMobEffects.PARALYSIS, 60, 1));
                    targetEntity.addEffect(new MobEffectInstance(ModMobEffects.FEAR, 60, 1));
                });
        return true;
    }

    private float horizontalDistanceSqr(LivingEntity livingEntity, LivingEntity entity2) {
        var dx = livingEntity.getX() - entity2.getX();
        var dz = livingEntity.getZ() - entity2.getZ();
        return (float) (dx * dx + dz * dz);
    }
}