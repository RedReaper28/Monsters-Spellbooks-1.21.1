package net.redreaper.monsterspellbooks.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.init.ModBlocks;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MonstersSpellbooks.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {


        wallItem(ModBlocks.PEARL_MARBLE_WALL, ModBlocks.PEARl_MARBLE);
        wallItem(ModBlocks.POLISHED_PEARL_MARBLE_WALL, ModBlocks.POLISHED_PEARl_MARBLE);
        wallItem(ModBlocks.PEARL_MARBLE_TILE_WALL, ModBlocks.PEARl_MARBLE_TILES);

        wallItem(ModBlocks.IVORYSTONE_WALL, ModBlocks.IVORYSTONE);
        wallItem(ModBlocks.POLISHED_IVORYSTONE_WALL, ModBlocks.POLISHED_IVORYSTONE);
        wallItem(ModBlocks.IVORYSTONE_BRICK_WALL, ModBlocks.IVORYSTONE_BRICKS);
        wallItem(ModBlocks.CRACKED_IVORYSTONE_BRICK_WALL, ModBlocks.CRACKED_IVORYSTONE_BRICKS);
    }

    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }
}
