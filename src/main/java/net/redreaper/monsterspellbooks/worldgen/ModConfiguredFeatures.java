package net.redreaper.monsterspellbooks.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.init.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?,?>>ORICHALCUM_ORE_KEY=registerKey("orichalcum_ore");
    public static final ResourceKey<ConfiguredFeature<?,?>>PEARL_ORE_KEY=registerKey("pearl_ore");
    public static final ResourceKey<ConfiguredFeature<?,?>>SCORCHED_METAL_ORE_KEY=registerKey("scorched_metal_ore");
    public static final ResourceKey<ConfiguredFeature<?,?>>NETHER_PYRITE_ORE_KEY=registerKey("nether_pyrite_ore");
    public static final ResourceKey<ConfiguredFeature<?,?>>NETHER_RUBY_ORE_KEY=registerKey("nether_ruby_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables=new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables=new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest sandReplaceables=new BlockMatchTest(Blocks.SAND);
        RuleTest netherReplaceables=new BlockMatchTest(Blocks.NETHERRACK);

        List<OreConfiguration.TargetBlockState>orichalcumOres=List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.ORICHALCUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_ORICHALCUM_ORE.get().defaultBlockState()));

        register(context,ORICHALCUM_ORE_KEY,Feature.ORE,new OreConfiguration(orichalcumOres,5));
        register(context,PEARL_ORE_KEY,Feature.ORE,new OreConfiguration(sandReplaceables,
                ModBlocks.POSEIDON_PEARL_ORE.get().defaultBlockState(),4));
        register(context,SCORCHED_METAL_ORE_KEY,Feature.ORE,new OreConfiguration(netherReplaceables,
                ModBlocks.SCORCHED_METAL_ORE.get().defaultBlockState(),9));
        register(context,NETHER_PYRITE_ORE_KEY,Feature.ORE,new OreConfiguration(netherReplaceables,
                ModBlocks.NETHER_PYRITE_ORE.get().defaultBlockState(),12));
        register(context,NETHER_RUBY_ORE_KEY,Feature.ORE,new OreConfiguration(netherReplaceables,
                ModBlocks.NETHER_RUBY_ORE.get().defaultBlockState(),3));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}
