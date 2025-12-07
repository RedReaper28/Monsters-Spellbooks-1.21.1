package net.redreaper.monsterspellbooks.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.init.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MonstersSpellbooks.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.OCEANITE_ORE_CLAY);
        blockWithItem(ModBlocks.OCEANITE_ORE_SAND);
        blockWithItem(ModBlocks.OCEANITE_ORE_GRAVEL);
        blockWithItem(ModBlocks.RAW_OCEANITE_BLOCK);
        blockWithItem(ModBlocks.OCEANITE_BLOCK);
        blockWithItem(ModBlocks.ROSE_GOLD_BLOCK);
        blockWithItem(ModBlocks.POSEIDON_PEARL_ORE);
        blockWithItem(ModBlocks.POSEIDON_PEARL_BLOCK);
        blockWithItem(ModBlocks.ORICHALCUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_ORICHALCUM_ORE);
        blockWithItem(ModBlocks.RAW_ORICHALCUM_BLOCK);
        blockWithItem(ModBlocks.ORICHALCUM_BLOCK);
        blockWithItem(ModBlocks.DEATHSILVER_BLOCK);
        blockWithItem(ModBlocks.SANGUINITE_BLOCK);
        blockWithItem(ModBlocks.DWARVEN_ALLOY_BLOCK);
        blockWithItem(ModBlocks.WHITE_GOLD_BLOCK);
        blockWithItem(ModBlocks.SCORCHED_METAL_ORE);
        blockWithItem(ModBlocks.RAW_SCORCHED_METAL_BLOCK);
        blockWithItem(ModBlocks.SCORCHED_METAL_BLOCK);
        blockWithItem(ModBlocks.VILE_IRON_ORE);
        blockWithItem(ModBlocks.RAW_VILE_IRON_BLOCK);
        blockWithItem(ModBlocks.VILE_IRON_BLOCK);
        blockWithItem(ModBlocks.NETHER_PYRITE_ORE);
        blockWithItem(ModBlocks.NETHER_PYRITE_BLOCK);
        blockWithItem(ModBlocks.NETHER_RUBY_ORE);
        blockWithItem(ModBlocks.NETHER_RUBY_BLOCK);
        blockWithItem(ModBlocks.SPECTRITE_BLOCK);
        blockWithItem(ModBlocks.WITHER_ALLOY_BLOCK);
        blockWithItem(ModBlocks.BLAZESTEEL_BLOCK);
        blockWithItem(ModBlocks.VOID_MATTER_ORE);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(),cubeAll(deferredBlock.get()));
    }
}
