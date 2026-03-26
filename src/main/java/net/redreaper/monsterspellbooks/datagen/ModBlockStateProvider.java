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
        slabBlock(ModBlocks.PEARL_MARBLE_TILE_SLAB.get(),blockTexture(ModBlocks.PEARl_MARBLE_TILES.get()),blockTexture(ModBlocks.PEARl_MARBLE_TILES.get()));
        wallBlock(ModBlocks.PEARL_MARBLE_TILE_WALL.get(),blockTexture(ModBlocks.PEARl_MARBLE_TILES.get()));

        blockWithItem(ModBlocks.IVORYSTONE);
        stairsBlock(ModBlocks.IVORYSTONE_STAIRS.get(),blockTexture(ModBlocks.IVORYSTONE.get()));
        slabBlock(ModBlocks.IVORYSTONE_SLAB.get(),blockTexture(ModBlocks.IVORYSTONE.get()),blockTexture(ModBlocks.IVORYSTONE.get()));
        wallBlock(ModBlocks.IVORYSTONE_WALL.get(),blockTexture(ModBlocks.IVORYSTONE.get()));

        blockWithItem(ModBlocks.POLISHED_IVORYSTONE);
        stairsBlock(ModBlocks.POLISHED_IVORYSTONE_STAIRS.get(),blockTexture(ModBlocks.POLISHED_IVORYSTONE.get()));
        slabBlock(ModBlocks.POLISHED_IVORYSTONE_SLAB.get(),blockTexture(ModBlocks.POLISHED_IVORYSTONE.get()),blockTexture(ModBlocks.POLISHED_IVORYSTONE.get()));
        wallBlock(ModBlocks.POLISHED_IVORYSTONE_WALL.get(),blockTexture(ModBlocks.POLISHED_IVORYSTONE.get()));

        blockWithItem(ModBlocks.IVORYSTONE_BRICKS);
        stairsBlock(ModBlocks.IVORYSTONE_BRICK_STAIRS.get(),blockTexture(ModBlocks.IVORYSTONE_BRICKS.get()));
        slabBlock(ModBlocks.IVORYSTONE_BRICK_SLAB.get(),blockTexture(ModBlocks.IVORYSTONE_BRICKS.get()),blockTexture(ModBlocks.IVORYSTONE_BRICKS.get()));
        wallBlock(ModBlocks.IVORYSTONE_BRICK_WALL.get(),blockTexture(ModBlocks.IVORYSTONE_BRICKS.get()));

        blockWithItem(ModBlocks.CRACKED_IVORYSTONE_BRICKS);
        stairsBlock(ModBlocks.CRACKED_IVORYSTONE_BRICK_STAIRS.get(),blockTexture(ModBlocks.CRACKED_IVORYSTONE_BRICKS.get()));
        slabBlock(ModBlocks.CRACKED_IVORYSTONE_BRICK_SLAB.get(),blockTexture(ModBlocks.CRACKED_IVORYSTONE_BRICKS.get()),blockTexture(ModBlocks.CRACKED_IVORYSTONE_BRICKS.get()));
        wallBlock(ModBlocks.CRACKED_IVORYSTONE_BRICK_WALL.get(),blockTexture(ModBlocks.CRACKED_IVORYSTONE_BRICKS.get()));

        blockWithItem(ModBlocks.CHISELED_IVORYSTONE_BRICKS);

        blockWithItem(ModBlocks.FROST_IVORYSTONE_BRICKS);
        stairsBlock(ModBlocks.FROST_IVORYSTONE_BRICK_STAIRS.get(),blockTexture(ModBlocks.FROST_IVORYSTONE_BRICKS.get()));
        slabBlock(ModBlocks.FROST_IVORYSTONE_BRICK_SLAB.get(),blockTexture(ModBlocks.FROST_IVORYSTONE_BRICKS.get()),blockTexture(ModBlocks.FROST_IVORYSTONE_BRICKS.get()));
        wallBlock(ModBlocks.FROST_IVORYSTONE_BRICK_WALL.get(),blockTexture(ModBlocks.FROST_IVORYSTONE_BRICKS.get()));

        blockWithItem(ModBlocks.VILESTONE);
        stairsBlock(ModBlocks.VILESTONE_STAIRS.get(),blockTexture(ModBlocks.VILESTONE.get()));
        slabBlock(ModBlocks.VILESTONE_SLAB.get(),blockTexture(ModBlocks.VILESTONE.get()),blockTexture(ModBlocks.VILESTONE.get()));
        wallBlock(ModBlocks.VILESTONE_WALL.get(),blockTexture(ModBlocks.VILESTONE.get()));

        blockWithItem(ModBlocks.VILESTONE_BRICKS);
        stairsBlock(ModBlocks.VILESTONE_BRICK_STAIRS.get(),blockTexture(ModBlocks.VILESTONE_BRICKS.get()));
        slabBlock(ModBlocks.VILESTONE_BRICK_SLAB.get(),blockTexture(ModBlocks.VILESTONE_BRICKS.get()),blockTexture(ModBlocks.VILESTONE_BRICKS.get()));
        wallBlock(ModBlocks.VILESTONE_BRICK_WALL.get(),blockTexture(ModBlocks.VILESTONE_BRICKS.get()));

        blockWithItem(ModBlocks.CHISELED_VILESTONE_BRICKS);

        blockWithItem(ModBlocks.GRAVISTONE);

        blockItem(ModBlocks.PEARL_MARBLE_STAIRS);
        blockItem(ModBlocks.PEARL_MARBLE_SLAB);

        blockItem(ModBlocks.POLISHED_PEARL_MARBLE_STAIRS);
        blockItem(ModBlocks.POLISHED_PEARL_MARBLE_SLAB);

        blockItem(ModBlocks.PEARL_MARBLE_TILE_STAIRS);
        blockItem(ModBlocks.PEARL_MARBLE_TILE_SLAB);

        blockItem(ModBlocks.IVORYSTONE_STAIRS);
        blockItem(ModBlocks.IVORYSTONE_SLAB);

        blockItem(ModBlocks.POLISHED_IVORYSTONE_STAIRS);
        blockItem(ModBlocks.POLISHED_IVORYSTONE_SLAB);

        blockItem(ModBlocks.IVORYSTONE_BRICK_STAIRS);
        blockItem(ModBlocks.IVORYSTONE_BRICK_SLAB);

        blockItem(ModBlocks.CRACKED_IVORYSTONE_BRICK_STAIRS);
        blockItem(ModBlocks.CRACKED_IVORYSTONE_BRICK_SLAB);

        blockItem(ModBlocks.FROST_IVORYSTONE_BRICK_STAIRS);
        blockItem(ModBlocks.FROST_IVORYSTONE_BRICK_SLAB);

        blockItem(ModBlocks.VILESTONE_STAIRS);
        blockItem(ModBlocks.VILESTONE_SLAB);

        blockItem(ModBlocks.VILESTONE_BRICK_STAIRS);
        blockItem(ModBlocks.VILESTONE_BRICK_SLAB);
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
