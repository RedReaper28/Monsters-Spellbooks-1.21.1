package net.redreaper.monsterspellbooks.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.init.ModEntities;

import java.util.List;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier>ADD_ORICHALCUM_ORE=registerKey("add_orichalcum_ore");
    public static final ResourceKey<BiomeModifier>ADD_PEARL_ORE=registerKey("add_pearl_ore");
    public static final ResourceKey<BiomeModifier>ADD_SCORCHED_ORE=registerKey("add_scorched_ore");
    public static final ResourceKey<BiomeModifier>ADD_PYRITE_ORE=registerKey("add_pyrite_ore");
    public static final ResourceKey<BiomeModifier>ADD_RUBY_ORE=registerKey("add_ruby_ore");

    public static final ResourceKey<BiomeModifier>ADD_VOID_ORE=registerKey("add_void_ore");

    public static final ResourceKey<BiomeModifier>ADD_GRAVISTONE=registerKey("add_gravistone");

    public static final ResourceKey<BiomeModifier>SPAWN_AEGIS=registerKey("spawn_aegis");
    public static final ResourceKey<BiomeModifier>SPAWN_DRIPPLER=registerKey("spawn_drippler");
    public static final ResourceKey<BiomeModifier>SPAWN_DWARVEN_SPHERE=registerKey("spawn_dwarven_sphere");
    public static final ResourceKey<BiomeModifier>SPAWN_JUNGLE_WHISPERER =registerKey("spawn_jungle_whisperer");
    public static final ResourceKey<BiomeModifier>SPAWN_VILE_SKELETON=registerKey("spawn_vile_skeleton");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_ORICHALCUM_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_MOUNTAIN),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ORICHALCUM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_PEARL_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OCEAN),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PEARL_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_SCORCHED_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SCORCHED_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_PYRITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PYRITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_RUBY_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.RUBY_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_VOID_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.VOID_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_GRAVISTONE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.GRAVISTONE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_DECORATION));

        context.register(SPAWN_AEGIS, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.HAS_VILLAGE_PLAINS),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.AEGIS.get(),2,1,4))));

        context.register(SPAWN_DRIPPLER, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.DRIPPLER.get(),10,1,2))));

        context.register(SPAWN_DWARVEN_SPHERE, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_MOUNTAIN),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.DWARVEN_SPHERE.get(),10,1,2))));

        context.register(SPAWN_VILE_SKELETON, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.HAS_NETHER_FOSSIL),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.VILE_SKELETON.get(),15,3,6))));

        context.register(SPAWN_JUNGLE_WHISPERER, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_JUNGLE),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.JUNGLE_WHISPERER.get(),10,1,3))));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, name));
    }
}
