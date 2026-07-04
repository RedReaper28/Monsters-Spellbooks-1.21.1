package net.redreaper.monsterspellbooks.utils;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.registries.EntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.ServerLevelAccessor;
import net.neoforged.neoforge.common.Tags;

public class ModUtils {

    public static float getEntityArmor(LivingEntity entity) {
        if (entity != null) {
            float entityArmor = (float) (entity.getAttributeValue(Attributes.ARMOR));
            return entityArmor;
        }
        return 0;
    }

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

    public static boolean checkUndergroundMobSpawnRules(EntityType<? extends LivingEntity> redstone, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return pos.getY() <= level.getSeaLevel() - 33 && level.getRawBrightness(pos, 0) == 0;
    }

    public static boolean checkUndergroundMonsterSpawnRules(ServerLevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        return !pLevel.getBiome(pPos).is(Tags.Biomes.NO_DEFAULT_MONSTERS) && pLevel.getDifficulty() != Difficulty.PEACEFUL && pPos.getY() <= pLevel.getSeaLevel() - 33 && Monster.isDarkEnoughToSpawn(pLevel, pPos, pRandom) && Monster.checkMobSpawnRules(EntityRegistry.NECROMANCER.get(), pLevel, pSpawnType, pPos, pRandom);
    }
}
