package net.redreaper.monsterspellbooks.datagen;

import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.init.ModBlocks;
import net.redreaper.monsterspellbooks.init.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput){

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.REAPER_LANTERN.get())
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A',ModItems.SPECTRITE_INGOT.get())
                .define('B',ModItems.WITHER_ALLOY_INGOT.get())
                .define('C',ModItems.BOOK_OF_SOULS.get())
                        .unlockedBy("has_material", has(ModItems.BOOK_OF_SOULS.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PEARl_MARBLE.get())
                .pattern("AAA")
                .pattern("ABC")
                .pattern("CCC")
                .define('A',ModItems.POSEIDON_PEARL.get())
                .define('B',ModItems.RAW_OCEANITE.get())
                .define('C',Items.SAND)
                .unlockedBy("has_material", has(ModItems.POSEIDON_PEARL.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FUNGAL_CAP_RED.get())
                .pattern("AAA")
                .pattern("CBC")
                .define('A',Items.RED_MUSHROOM)
                .define('B',ItemRegistry.NATURE_RUNE.get())
                .define('C',ItemRegistry.MAGIC_CLOTH.get())
                .unlockedBy("has_material", has(ItemRegistry.NATURE_RUNE.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PEARl_MARBLE_PILLAR.get())
                .pattern("A")
                .pattern("A")
                .define('A',ModBlocks.POLISHED_PEARL_MARBLE_SLAB)
                .unlockedBy("has_material", has(ModBlocks.POLISHED_PEARL_MARBLE_SLAB.get())).save(recipeOutput);

        stairBuilder(ModBlocks.PEARL_MARBLE_STAIRS.get(),Ingredient.of(ModBlocks.PEARl_MARBLE)).group("pearl_marble")
                .unlockedBy("has_material", has(ModBlocks.PEARl_MARBLE.get())).save(recipeOutput);
        slab(recipeOutput,RecipeCategory.BUILDING_BLOCKS,ModBlocks.PEARL_MARBLE_SLAB.get(),ModBlocks.PEARl_MARBLE.get());
        wall(recipeOutput,RecipeCategory.BUILDING_BLOCKS,ModBlocks.PEARL_MARBLE_WALL.get(),ModBlocks.PEARl_MARBLE.get());


        stairBuilder(ModBlocks.POLISHED_PEARL_MARBLE_STAIRS.get(),Ingredient.of(ModBlocks.POLISHED_PEARl_MARBLE)).group("polished_pearl_marble")
                .unlockedBy("has_material", has(ModBlocks.POLISHED_PEARl_MARBLE.get())).save(recipeOutput);
        slab(recipeOutput,RecipeCategory.BUILDING_BLOCKS,ModBlocks.POLISHED_PEARL_MARBLE_SLAB.get(),ModBlocks.POLISHED_PEARl_MARBLE.get());
        wall(recipeOutput,RecipeCategory.BUILDING_BLOCKS,ModBlocks.POLISHED_PEARL_MARBLE_WALL.get(),ModBlocks.POLISHED_PEARl_MARBLE.get());


        stairBuilder(ModBlocks.PEARL_MARBLE_TILE_STAIRS.get(),Ingredient.of(ModBlocks.PEARl_MARBLE_TILES)).group("pearl_marble_tile")
                .unlockedBy("has_material", has(ModBlocks.PEARl_MARBLE_TILES.get())).save(recipeOutput);
        slab(recipeOutput,RecipeCategory.BUILDING_BLOCKS,ModBlocks.PEARL_MARBLE_TILE_SLAB.get(),ModBlocks.PEARl_MARBLE_TILES.get());
        wall(recipeOutput,RecipeCategory.BUILDING_BLOCKS,ModBlocks.PEARL_MARBLE_TILE_WALL.get(),ModBlocks.PEARl_MARBLE_TILES.get());

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PEARL_MARBLE_STAIRS, ModBlocks.PEARl_MARBLE);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PEARL_MARBLE_SLAB, ModBlocks.PEARl_MARBLE,2);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PEARL_MARBLE_WALL, ModBlocks.PEARl_MARBLE);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PEARL_MARBLE_STAIRS, ModBlocks.POLISHED_PEARl_MARBLE);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PEARL_MARBLE_SLAB, ModBlocks.POLISHED_PEARl_MARBLE,2);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PEARL_MARBLE_WALL, ModBlocks.POLISHED_PEARl_MARBLE);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PEARl_MARBLE_TILES, ModBlocks.PEARl_MARBLE_TILES);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PEARL_MARBLE_TILE_SLAB, ModBlocks.PEARl_MARBLE_TILES,2);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PEARL_MARBLE_TILE_WALL, ModBlocks.PEARl_MARBLE_TILES);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PEARl_MARBLE_PILLAR, ModBlocks.PEARl_MARBLE);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.IVORYSTONE_STAIRS, ModBlocks.IVORYSTONE);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.IVORYSTONE_SLAB, ModBlocks.IVORYSTONE,2);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.IVORYSTONE_WALL, ModBlocks.IVORYSTONE);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_IVORYSTONE, ModBlocks.IVORYSTONE);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_IVORYSTONE_STAIRS, ModBlocks.POLISHED_IVORYSTONE);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_IVORYSTONE_SLAB, ModBlocks.POLISHED_IVORYSTONE,2);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_IVORYSTONE_WALL, ModBlocks.POLISHED_IVORYSTONE);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.IVORYSTONE_BRICKS, ModBlocks.IVORYSTONE);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.IVORYSTONE_BRICK_STAIRS, ModBlocks.IVORYSTONE_BRICKS);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.IVORYSTONE_BRICK_SLAB, ModBlocks.IVORYSTONE_BRICKS,2);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.IVORYSTONE_BRICK_WALL, ModBlocks.IVORYSTONE_BRICKS);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_IVORYSTONE_BRICKS, ModBlocks.IVORYSTONE_BRICKS);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_IVORYSTONE_BRICK_STAIRS, ModBlocks.CRACKED_IVORYSTONE_BRICKS,2);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_IVORYSTONE_BRICK_SLAB, ModBlocks.CRACKED_IVORYSTONE_BRICKS);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_IVORYSTONE_BRICK_WALL, ModBlocks.CRACKED_IVORYSTONE_BRICKS);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_IVORYSTONE_BRICKS, ModBlocks.IVORYSTONE_BRICKS);

    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, MonstersSpellbooks.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
