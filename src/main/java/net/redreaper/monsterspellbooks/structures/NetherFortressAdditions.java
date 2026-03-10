package net.redreaper.monsterspellbooks.structures;

import com.mojang.serialization.MapCodec;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.structures.NetherFortressStructure;
import net.redreaper.monsterspellbooks.init.ModEntities;

public class NetherFortressAdditions extends NetherFortressStructure {
    public static final WeightedRandomList<MobSpawnSettings.SpawnerData> FORTRESS_ENEMIES;
    public static final MapCodec<NetherFortressStructure> CODEC;

    public NetherFortressAdditions(Structure.StructureSettings settings) {
        super(settings);
    }


    static {
        FORTRESS_ENEMIES = WeightedRandomList.create(new MobSpawnSettings.SpawnerData[]{new MobSpawnSettings.SpawnerData(ModEntities.MAGMA_ATRONACH.get(), 10, 2, 3), new MobSpawnSettings.SpawnerData(ModEntities.VILE_SKELETON.get(), 3, 4, 4)});
        CODEC = simpleCodec(NetherFortressStructure::new);
    }
}
