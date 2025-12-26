package net.redreaper.monsterspellbooks.procedures.effectsonhit;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class PoisonOnHit {
    public static void execute(Entity entity) {
        if (entity != null) {
            if (entity instanceof LivingEntity _entity) {
                if (!_entity.level().isClientSide()) {
                    _entity.addEffect(new MobEffectInstance(MobEffects.POISON, 120, 1, true, true));
                }
            }

        }
    }
}

