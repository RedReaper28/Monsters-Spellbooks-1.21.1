package net.redreaper.monsterspellbooks.procedures.effectsonhit;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.redreaper.monsterspellbooks.init.ModMobEffects;

public class PaladinProtectionOnHit {
    public static void execute(Entity sourceentity) {
        if (sourceentity != null) {
            if (sourceentity instanceof LivingEntity _entity) {
                if (!_entity.level().isClientSide()) {
                    _entity.addEffect(new MobEffectInstance(ModMobEffects.PALADINS_DEFENSE, 160, 0, true, true,true));
                }
            }

        }
    }
}
