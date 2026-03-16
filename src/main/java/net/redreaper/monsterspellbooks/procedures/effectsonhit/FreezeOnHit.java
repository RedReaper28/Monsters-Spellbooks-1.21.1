package net.redreaper.monsterspellbooks.procedures.effectsonhit;

import io.redspace.ironsspellbooks.api.util.Utils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class FreezeOnHit {
    public static void execute(Entity entity) {
        if (entity != null) {
            if (entity instanceof LivingEntity _entity) {
                if (!_entity.level().isClientSide()) {
                    Utils.addFreezeTicks(_entity, 50);
                }
            }
        }
    }
}





