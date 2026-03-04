package net.redreaper.monsterspellbooks.events;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
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


    public static float getEntityMana(LivingEntity entity) {
        if (entity != null) {
            float entityHp = (float) (entity.getAttributeValue(AttributeRegistry.MAX_MANA));
            return entityHp;
        }
        return 0;
    }

    public static float getEntityManaRegen(LivingEntity entity) {
        if (entity != null) {
            float entityHp = (float) (entity.getAttributeValue(AttributeRegistry.MANA_REGEN));
            return entityHp;
        }
        return 0;
    }
}
