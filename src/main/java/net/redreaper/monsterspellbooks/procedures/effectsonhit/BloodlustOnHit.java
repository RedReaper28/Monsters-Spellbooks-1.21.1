package net.redreaper.monsterspellbooks.procedures.effectsonhit;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.redreaper.monsterspellbooks.init.ModMobEffects;

public class BloodlustOnHit {
    public static void execute(Entity sourceentity) {
        if (sourceentity != null) {
            if (sourceentity instanceof LivingEntity _entity) {
                if (!_entity.level().isClientSide()) {
                    _entity.addEffect(new MobEffectInstance(ModMobEffects.BLOODLUST, 160, 1, true, true,true));
                }
            }

        }
    }
}
