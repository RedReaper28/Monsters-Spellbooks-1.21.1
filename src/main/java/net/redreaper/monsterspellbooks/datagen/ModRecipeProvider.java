package net.redreaper.monsterspellbooks.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
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
        List<ItemLike> OCEANITE_SMELTABLES=List.of(ModItems.RAW_OCEANITE);
        List<ItemLike> ORICHALCUM_SMELTABLES=List.of(ModItems.RAW_ORICHALCUM);
        List<ItemLike> DWARVEN_ALLOY_SMELTABLES=List.of(ModItems.DWARVEN_ALLOY_SCRAP);
        List<ItemLike> SCORCH_METAL_SMELTABLES=List.of(ModItems.RAW_SCORCHED_METAL);
        List<ItemLike> VILE_IRON_ALLOY_SMELTABLES=List.of(ModItems.RAW_VILE_IRON);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_OCEANITE_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A',ModItems.RAW_OCEANITE.get()).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.OCEANITE_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A',ModItems.OCEANITE_INGOT.get()).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ROSE_GOLD_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A',ModItems.ROSE_GOLD_INGOT.get()).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.POSEIDON_PEARL_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A',ModItems.POSEIDON_PEARL.get()).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_ORICHALCUM_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A',ModItems.RAW_ORICHALCUM.get()).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ORICHALCUM_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A',ModItems.RAW_ORICHALCUM.get()).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DEATHSILVER_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A',ModItems.DEATHSILVER_INGOT.get()).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SANGUINITE_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A',ModItems.SANGUINITE_INGOT.get()).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DWARVEN_ALLOY_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A',ModItems.DWARVEN_ALLOY_INGOT.get()).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.WHITE_GOLD_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A',ModItems.WHITE_GOLD_INGOT.get()).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_SCORCHED_METAL_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A',ModItems.RAW_SCORCHED_METAL.get()).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SCORCHED_METAL_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A',ModItems.SCORCHED_METAL_INGOT.get()).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_VILE_IRON_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A',ModItems.RAW_VILE_IRON.get()).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.VILE_IRON_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A',ModItems.VILE_IRON_INGOT.get()).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.NETHER_PYRITE_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A',ModItems.NETHER_PYRITE.get()).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.NETHER_RUBY_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A',ModItems.NETHER_RUBY.get()).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SPECTRITE_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A',ModItems.SPECTRITE_INGOT.get()).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.WITHER_ALLOY_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A',ModItems.WITHER_ALLOY_INGOT.get()).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLAZESTEEL_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A',ModItems.BLAZESTEEL_INGOT.get()).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.RAW_OCEANITE.get(),9)
                .requires(ModBlocks.RAW_OCEANITE_BLOCK).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.OCEANITE_INGOT.get(),9)
                .requires(ModBlocks.OCEANITE_BLOCK).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.OCEANITE_NUGGET.get(),9)
                .requires(ModItems.OCEANITE_INGOT).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.ROSE_GOLD_INGOT.get(),9)
                .requires(ModBlocks.ROSE_GOLD_BLOCK).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.ROSE_GOLD_NUGGET.get(),9)
                .requires(ModItems.ROSE_GOLD_INGOT).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.POSEIDON_PEARL.get(),9)
                .requires(ModBlocks.POSEIDON_PEARL_BLOCK).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.RAW_ORICHALCUM.get(),9)
                .requires(ModBlocks.RAW_ORICHALCUM_BLOCK).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.ORICHALCUM_INGOT.get(),9)
                .requires(ModBlocks.ORICHALCUM_BLOCK).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.ORICHALCUM_NUGGET.get(),9)
                .requires(ModItems.ORICHALCUM_INGOT).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.DEATHSILVER_INGOT.get(),9)
                .requires(ModBlocks.DEATHSILVER_BLOCK).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.DEATHSILVER_NUGGET.get(),9)
                .requires(ModItems.DEATHSILVER_INGOT).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SANGUINITE_INGOT.get(),9)
                .requires(ModBlocks.SANGUINITE_BLOCK).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.DWARVEN_ALLOY_INGOT.get(),9)
                .requires(ModBlocks.DWARVEN_ALLOY_BLOCK).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.DWARVEN_ALLOY_NUGGET.get(),9)
                .requires(ModItems.DWARVEN_ALLOY_INGOT).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.WHITE_GOLD_INGOT.get(),9)
                .requires(ModBlocks.WHITE_GOLD_BLOCK).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.WHITE_GOLD_NUGGET.get(),9)
                .requires(ModItems.WHITE_GOLD_INGOT).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.RAW_SCORCHED_METAL.get(),9)
                .requires(ModBlocks.RAW_SCORCHED_METAL_BLOCK).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SCORCHED_METAL_INGOT.get(),9)
                .requires(ModBlocks.SCORCHED_METAL_BLOCK).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SCORCHED_METAL_NUGGET.get(),9)
                .requires(ModItems.SCORCHED_METAL_INGOT).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.RAW_VILE_IRON.get(),9)
                .requires(ModBlocks.RAW_VILE_IRON_BLOCK).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.VILE_IRON_INGOT.get(),9)
                .requires(ModBlocks.VILE_IRON_BLOCK).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.VILE_IRON_NUGGET.get(),9)
                .requires(ModItems.VILE_IRON_INGOT).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.NETHER_PYRITE.get(),9)
                .requires(ModBlocks.NETHER_PYRITE_BLOCK).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.NETHER_RUBY.get(),9)
                .requires(ModBlocks.NETHER_RUBY_BLOCK).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SPECTRITE_INGOT.get(),9)
                .requires(ModBlocks.SPECTRITE_BLOCK).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BLAZESTEEL_INGOT.get(),9)
                .requires(ModBlocks.BLAZESTEEL_BLOCK).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.WITHER_ALLOY_INGOT.get(),9)
                .requires(ModBlocks.WITHER_ALLOY_BLOCK).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.WITHER_ALLOY_NUGGET.get(),9)
                .requires(ModItems.WITHER_ALLOY_INGOT).unlockedBy("has_material", has(ModItems.OCEANITE_INGOT)).save(recipeOutput);

        oreSmelting(recipeOutput,OCEANITE_SMELTABLES,RecipeCategory.MISC,ModItems.OCEANITE_INGOT,0.25f,200,"oceanite");
        oreBlasting(recipeOutput,OCEANITE_SMELTABLES,RecipeCategory.MISC,ModItems.OCEANITE_INGOT,0.25f,100,"oceanite");
        oreSmelting(recipeOutput,ORICHALCUM_SMELTABLES,RecipeCategory.MISC,ModItems.ORICHALCUM_INGOT,0.25f,200,"orichalcum");
        oreBlasting(recipeOutput,ORICHALCUM_SMELTABLES,RecipeCategory.MISC,ModItems.ORICHALCUM_INGOT,0.25f,100,"orichalcum");
        oreSmelting(recipeOutput,DWARVEN_ALLOY_SMELTABLES,RecipeCategory.MISC,ModItems.DWARVEN_ALLOY_INGOT,0.25f,200,"dwarven_alloy");
        oreBlasting(recipeOutput,DWARVEN_ALLOY_SMELTABLES,RecipeCategory.MISC,ModItems.DWARVEN_ALLOY_INGOT,0.25f,100,"dwarven_alloy");
        oreSmelting(recipeOutput,SCORCH_METAL_SMELTABLES,RecipeCategory.MISC,ModItems.SCORCHED_METAL_INGOT,0.25f,200,"scorched_metal");
        oreBlasting(recipeOutput,SCORCH_METAL_SMELTABLES,RecipeCategory.MISC,ModItems.SCORCHED_METAL_INGOT,0.25f,100,"scorched_metal");
        oreSmelting(recipeOutput,VILE_IRON_ALLOY_SMELTABLES,RecipeCategory.MISC,ModItems.VILE_IRON_INGOT,0.25f,200,"vile_iron");
        oreBlasting(recipeOutput,VILE_IRON_ALLOY_SMELTABLES,RecipeCategory.MISC,ModItems.VILE_IRON_INGOT,0.25f,100,"vile_iron");
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
