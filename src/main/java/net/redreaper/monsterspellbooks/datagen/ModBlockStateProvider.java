package net.redreaper.monsterspellbooks.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
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
        blockWithItem(ModBlocks.CORAL_LOG);
        blockWithItem(ModBlocks.CORAL_PLANKS);

        blockWithItem(ModBlocks.PEARl_MARBLE);
        stairsBlock(ModBlocks.PEARL_MARBLE_STAIRS.get(),blockTexture(ModBlocks.PEARl_MARBLE.get()));
        slabBlock(ModBlocks.PEARL_MARBLE_SLAB.get(),blockTexture(ModBlocks.PEARl_MARBLE.get()),blockTexture(ModBlocks.PEARl_MARBLE.get()));
        wallBlock(ModBlocks.PEARL_MARBLE_WALL.get(),blockTexture(ModBlocks.PEARl_MARBLE.get()));


        blockWithItem(ModBlocks.POLISHED_PEARl_MARBLE);
        stairsBlock(ModBlocks.POLISHED_PEARL_MARBLE_STAIRS.get(),blockTexture(ModBlocks.POLISHED_PEARl_MARBLE.get()));
        slabBlock(ModBlocks.POLISHED_PEARL_MARBLE_SLAB.get(),blockTexture(ModBlocks.POLISHED_PEARl_MARBLE.get()),blockTexture(ModBlocks.POLISHED_PEARl_MARBLE.get()));
        wallBlock(ModBlocks.POLISHED_PEARL_MARBLE_WALL.get(),blockTexture(ModBlocks.POLISHED_PEARl_MARBLE.get()));



        blockWithItem(ModBlocks.PEARl_MARBLE_TILES);
        stairsBlock(ModBlocks.PEARL_MARBLE_TILE_STAIRS.get(),blockTexture(ModBlocks.PEARl_MARBLE_TILES.get()));
        slabBlock(ModBlocks.PEARL_MARBLE_TILE_SLAB.get(),blockTexture(ModBlocks.PEARl_MARBLE_TILES.get()),blockTexture(ModBlocks.PEARl_MARBLE_TILES.get()));        wallBlock(ModBlocks.POLISHED_PEARL_MARBLE_WALL.get(),blockTexture(ModBlocks.POLISHED_PEARl_MARBLE.get()));
        wallBlock(ModBlocks.PEARL_MARBLE_TILE_WALL.get(),blockTexture(ModBlocks.PEARl_MARBLE_TILES.get()));




        blockWithItem(ModBlocks.VILESTONE);
        blockWithItem(ModBlocks.GRAVISTONE);

        blockItem(ModBlocks.PEARL_MARBLE_STAIRS);
        blockItem(ModBlocks.PEARL_MARBLE_SLAB);

        blockItem(ModBlocks.POLISHED_PEARL_MARBLE_STAIRS);
        blockItem(ModBlocks.POLISHED_PEARL_MARBLE_SLAB);

        blockItem(ModBlocks.PEARL_MARBLE_TILE_STAIRS);
        blockItem(ModBlocks.PEARL_MARBLE_TILE_SLAB);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(),cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("monsterspellbooks:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("monsterspellbooks:block/" + deferredBlock.getId().getPath() + appendix));
    }
}
