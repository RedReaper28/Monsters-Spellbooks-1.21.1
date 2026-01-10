package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.redreaper.monsterspellbooks.entity.spells.blizzard_aspect.PowderSnowSplash;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;

import javax.annotation.Nullable;

public class BlizzardAspectMobEffect extends MagicMobEffect {
    public BlizzardAspectMobEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return pDuration % 75 == 0;
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int pAmplifier) {
        var radiusSqr = 400; //20
        entity.level().getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(10, 6, 10),
                        livingEntity -> livingEntity != entity &&
                                horizontalDistanceSqr(livingEntity, entity) < radiusSqr &&
                                livingEntity.isPickable() &&
                                !livingEntity.isSpectator() &&
                                !Utils.shouldHealEntity(entity, livingEntity) &&
                                Utils.hasLineOfSight(entity.level(), entity, livingEntity, false)
                )
                .forEach(targetEntity -> {
                    PowderSnowSplash snowCloud = new PowderSnowSplash(entity.level());
                    snowCloud.setOwner(entity);
                    snowCloud.setDamage(getDamageFromAmplifier(pAmplifier, entity));
                    snowCloud.setPos(targetEntity.position());
                    entity.level().addFreshEntity(snowCloud);
                });
        return true;
    }


    private float horizontalDistanceSqr(LivingEntity livingEntity, LivingEntity entity2) {
        var dx = livingEntity.getX() - entity2.getX();
        var dz = livingEntity.getZ() - entity2.getZ();
        return (float) (dx * dx + dz * dz);
    }

    public static float getDamageFromAmplifier(int effectAmplifier, @Nullable LivingEntity caster) {
        var power = caster == null ? 1 : ModSpellRegistry.BLIZZARD_ASPECT.get().getEntityPowerMultiplier(caster);
        return (((effectAmplifier)));
    }
}
