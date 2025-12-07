package net.redreaper.monsterspellbooks.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.init.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MonstersSpellbooks.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.RAW_OCEANITE_BLOCK.get())
                .add(ModBlocks.OCEANITE_BLOCK.get())
                .add(ModBlocks.ROSE_GOLD_BLOCK.get())
                .add(ModBlocks.POSEIDON_PEARL_ORE.get())
                .add(ModBlocks.POSEIDON_PEARL_BLOCK.get())
                .add(ModBlocks.ORICHALCUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_ORICHALCUM_ORE.get())
                .add(ModBlocks.RAW_ORICHALCUM_BLOCK.get())
                .add(ModBlocks.ORICHALCUM_BLOCK.get())
                .add(ModBlocks.DEATHSILVER_BLOCK.get())
                .add(ModBlocks.SANGUINITE_BLOCK.get())
                .add(ModBlocks.DWARVEN_ALLOY_BLOCK.get())
                .add(ModBlocks.WHITE_GOLD_BLOCK.get())
                .add(ModBlocks.SCORCHED_METAL_ORE.get())
                .add(ModBlocks.RAW_SCORCHED_METAL_BLOCK.get())
                .add(ModBlocks.SCORCHED_METAL_BLOCK.get())
                .add(ModBlocks.VILE_IRON_ORE.get())
                .add(ModBlocks.RAW_VILE_IRON_BLOCK.get())
                .add(ModBlocks.VILE_IRON_BLOCK.get())
                .add(ModBlocks.NETHER_PYRITE_ORE.get())
                .add(ModBlocks.NETHER_PYRITE_BLOCK.get())
                .add(ModBlocks.NETHER_RUBY_ORE.get())
                .add(ModBlocks.NETHER_RUBY_BLOCK.get())
                .add(ModBlocks.SPECTRITE_BLOCK.get())
                .add(ModBlocks.WITHER_ALLOY_BLOCK.get())
                .add(ModBlocks.BLAZESTEEL_BLOCK.get())
                .add(ModBlocks.VOID_MATTER_ORE.get())
        ;


        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.OCEANITE_ORE_CLAY.get())
                .add(ModBlocks.OCEANITE_ORE_SAND.get())
                .add(ModBlocks.OCEANITE_ORE_GRAVEL.get())
        ;

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.OCEANITE_ORE_CLAY.get())
                .add(ModBlocks.OCEANITE_ORE_SAND.get())
                .add(ModBlocks.OCEANITE_ORE_GRAVEL.get())
                .add(ModBlocks.RAW_OCEANITE_BLOCK.get())
                .add(ModBlocks.OCEANITE_BLOCK.get())
                .add(ModBlocks.ROSE_GOLD_BLOCK.get())
                .add(ModBlocks.SCORCHED_METAL_ORE.get())
                .add(ModBlocks.RAW_SCORCHED_METAL_BLOCK.get())
                .add(ModBlocks.SCORCHED_METAL_BLOCK.get())
                .add(ModBlocks.VILE_IRON_ORE.get())
                .add(ModBlocks.RAW_VILE_IRON_BLOCK.get())
                .add(ModBlocks.VILE_IRON_BLOCK.get())
                .add(ModBlocks.NETHER_PYRITE_ORE.get())
                .add(ModBlocks.NETHER_PYRITE_BLOCK.get())
        ;

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.POSEIDON_PEARL_ORE.get())
                .add(ModBlocks.POSEIDON_PEARL_BLOCK.get())
                .add(ModBlocks.ORICHALCUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_ORICHALCUM_ORE.get())
                .add(ModBlocks.RAW_ORICHALCUM_BLOCK.get())
                .add(ModBlocks.ORICHALCUM_BLOCK.get())
                .add(ModBlocks.DEATHSILVER_BLOCK.get())
                .add(ModBlocks.SANGUINITE_BLOCK.get())
                .add(ModBlocks.DWARVEN_ALLOY_BLOCK.get())
                .add(ModBlocks.NETHER_RUBY_ORE.get())
                .add(ModBlocks.NETHER_RUBY_BLOCK.get())
        ;

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.SPECTRITE_BLOCK.get())
                .add(ModBlocks.WITHER_ALLOY_BLOCK.get())
                .add(ModBlocks.BLAZESTEEL_BLOCK.get())
                .add(ModBlocks.VOID_MATTER_ORE.get())
        ;

        tag(BlockTags.PIGLIN_REPELLENTS)
                .add(ModBlocks.SPECTRITE_BLOCK.get())
        ;


        tag(BlockTags.PIGLIN_REPELLENTS)
                .add(ModBlocks.SPECTRITE_BLOCK.get())
        ;

    }
}
