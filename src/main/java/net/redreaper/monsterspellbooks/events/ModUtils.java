package net.redreaper.monsterspellbooks.events;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ModUtils {

    public static float getEntityHp(LivingEntity entity) {
        if (entity != null) {
            float entityHp = (float) (entity.getAttributeValue(Attributes.MAX_HEALTH));
            return entityHp;
        }
        return 0;
    }

    public static float getEntityExtraHp(LivingEntity entity) {
        if (entity != null) {
            float entityHp = (float) (entity.getAttributeValue(Attributes.MAX_ABSORPTION));
            return entityHp;
        }
        return 0;
    }
}
