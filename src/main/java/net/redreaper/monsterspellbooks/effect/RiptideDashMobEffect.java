package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import io.redspace.ironsspellbooks.mixin.LivingEntityAccessor;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;

import java.util.List;

public class RiptideDashMobEffect extends MagicMobEffect {
    public RiptideDashMobEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        List<Entity> list = livingEntity.level().getEntities(livingEntity, livingEntity.getBoundingBox().inflate(.25, .5, .25));
        if (!list.isEmpty()) {
            for (Entity entity : list) {
                if (entity instanceof LivingEntity) {
                    DamageSources.applyDamage(entity, amplifier, ModSpellRegistry.RIPTIDE_DASH.get().getDamageSource(livingEntity));
                    entity.invulnerableTime = 20;
                }
            }
        } else if (livingEntity.horizontalCollision) {
            return false;
        }
        livingEntity.fallDistance = 0;
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }

    @Override
    public void onEffectAdded(LivingEntity pLivingEntity, int pAmplifier) {
        super.onEffectAdded(pLivingEntity, pAmplifier);
        ((LivingEntityAccessor) pLivingEntity).setLivingEntityFlagInvoker(4, true);
    }

    @Override
    public void onEffectRemoved(LivingEntity pLivingEntity, int pAmplifier) {
        super.onEffectRemoved(pLivingEntity, pAmplifier);
        ((LivingEntityAccessor) pLivingEntity).setLivingEntityFlagInvoker(4, false);
    }
}
