package net.redreaper.monsterspellbooks.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature>ORICHALCUM_ORE_PLACED_KEY=registerKey("orichalcum_ore_placed");
    public static final ResourceKey<PlacedFeature>PEARL_ORE_PLACED_KEY=registerKey("pearl_ore_placed");
    public static final ResourceKey<PlacedFeature>SCORCHED_ORE_PLACED_KEY=registerKey("scorched_ore_placed");
    public static final ResourceKey<PlacedFeature>PYRITE_ORE_PLACED_KEY=registerKey("pyrite_ore_placed");
    public static final ResourceKey<PlacedFeature>RUBY_ORE_PLACED_KEY=registerKey("ruby_ore_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context,ORICHALCUM_ORE_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.ORICHALCUM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12, HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(480))));

        register(context,PEARL_ORE_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.PEARL_ORE_KEY),
                ModOrePlacement.commonOrePlacement(6, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));

        register(context,SCORCHED_ORE_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.SCORCHED_METAL_ORE_KEY),
                ModOrePlacement.commonOrePlacement(16, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));

        register(context,PYRITE_ORE_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_PYRITE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(8, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));

        register(context,RUBY_ORE_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_RUBY_ORE_KEY),
                ModOrePlacement.commonOrePlacement(4, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
