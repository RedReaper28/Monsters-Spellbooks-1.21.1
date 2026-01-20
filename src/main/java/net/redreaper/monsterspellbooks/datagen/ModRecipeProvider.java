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
import net.redreaper.monsterspellbooks.init.ModTags;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput){

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VILENOVA_STAFF.get())
                .pattern(" BC")
                .pattern(" AB")
                .pattern("A  ")
                .define('A',ModItems.VILE_BONE.get())
                .define('B',ModItems.WITHER_ALLOY_INGOT.get())
                .define('C',Items.NETHER_STAR)
                        .unlockedBy("has_material", has(Items.NETHER_STAR)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MASTERS_KATANA.get())
                .pattern("  A")
                .pattern(" A ")
                .pattern("B  ")
                .define('A',ModItems.ROSE_GOLD_INGOT.get())
                .define('B',Items.STICK)
                .unlockedBy("has_material", has(ModItems.ROSE_GOLD_INGOT)).save(recipeOutput);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ORICHALCUM_DAGGER.get())
                .pattern(" A ")
                .pattern("B  ")
                .define('A',ModItems.ORICHALCUM_INGOT.get())
                .define('B',Items.STICK)
                .unlockedBy("has_material", has(ModItems.ORICHALCUM_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ORICHALCUM_GREATSWORD.get())
                .pattern("  A")
                .pattern(" A ")
                .pattern("B  ")
                .define('A',ModItems.ORICHALCUM_INGOT.get())
                .define('B',Items.STICK)
                .unlockedBy("has_material", has(ModItems.ORICHALCUM_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ORICHALCUM_GREATAXE.get())
                .pattern(" BA")
                .pattern("AC ")
                .pattern("C  ")
                .define('A',ModItems.ORICHALCUM_INGOT.get())
                .define('B',ModBlocks.ORICHALCUM_BLOCK.get())
                .define('C',Items.STICK)
                .unlockedBy("has_material", has(ModItems.ORICHALCUM_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ORICHALCUM_WARHAMMER.get())
                .pattern(" BA")
                .pattern(" CB")
                .pattern("C  ")
                .define('A',ModItems.ORICHALCUM_INGOT.get())
                .define('B',ModBlocks.ORICHALCUM_BLOCK.get())
                .define('C',Items.STICK)
                .unlockedBy("has_material", has(ModItems.ORICHALCUM_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ORICHALCUM_AXE.get())
                .pattern("AA")
                .pattern("AB")
                .pattern(" B")
                .define('A',ModItems.ORICHALCUM_INGOT.get())
                .define('B',Items.STICK)
                .unlockedBy("has_material", has(ModItems.ORICHALCUM_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DWARVEN_GREATAXE.get())
                .pattern(" BA")
                .pattern("AC ")
                .pattern("C  ")
                .define('A',ModItems.DWARVEN_ALLOY_INGOT.get())
                .define('B',ModBlocks.DWARVEN_ALLOY_BLOCK.get())
                .define('C',Items.STICK)
                .unlockedBy("has_material", has(ModItems.DWARVEN_ALLOY_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DWARVEN_WARHAMMER.get())
                .pattern(" BA")
                .pattern(" CB")
                .pattern("C  ")
                .define('A',ModItems.DWARVEN_ALLOY_INGOT.get())
                .define('B',ModBlocks.DWARVEN_ALLOY_BLOCK.get())
                .define('C',Items.STICK)
                .unlockedBy("has_material", has(ModItems.DWARVEN_ALLOY_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DWARVEN_AXE.get())
                .pattern("AA")
                .pattern("AB")
                .pattern(" B")
                .define('A',ModItems.DWARVEN_ALLOY_INGOT.get())
                .define('B',Items.STICK)
                .unlockedBy("has_material", has(ModItems.DWARVEN_ALLOY_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SANGUINITE_DAGGER.get())
                .pattern(" A ")
                .pattern("B  ")
                .define('A',ModItems.SANGUINITE_INGOT.get())
                .define('B',Items.STICK)
                .unlockedBy("has_material", has(ModItems.SANGUINITE_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SANGUINITE_SCYTHE.get())
                .pattern("AAB")
                .pattern(" B ")
                .pattern("B  ")
                .define('A',ModItems.SANGUINITE_INGOT.get())
                .define('B',Items.STICK)
                .unlockedBy("has_material", has(ModItems.SANGUINITE_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SANGUINITE_AXE.get())
                .pattern(" BA")
                .pattern("AC ")
                .pattern("C  ")
                .define('A',ModItems.SANGUINITE_INGOT.get())
                .define('B',ModBlocks.SANGUINITE_BLOCK.get())
                .define('C',Items.STICK)
                .unlockedBy("has_material", has(ModItems.SANGUINITE_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STARSCOURGE_SWORD.get())
                .pattern("  A")
                .pattern("CA ")
                .pattern("BC ")
                .define('A',ModItems.VOID_OBSIDIAN_INGOT.get())
                .define('B',ModItems.SUPERIOR_WEAPON_PARTS.get())
                .define('C',Items.GOLD_INGOT)
                .unlockedBy("has_material", has(ModItems.VOID_OBSIDIAN_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DISRUPTION_NAGINATA.get())
                .pattern("  A")
                .pattern(" BA")
                .pattern("C  ")
                .define('A',ModItems.VOID_OBSIDIAN_INGOT.get())
                .define('B',ModItems.SUPERIOR_WEAPON_PARTS.get())
                .define('C',Items.GOLD_INGOT)
                .unlockedBy("has_material", has(ModItems.VOID_OBSIDIAN_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENDERSENT_CRUSHER.get())
                .pattern("ABA")
                .pattern(" C ")
                .pattern(" D ")
                .define('A',ModItems.VOID_OBSIDIAN_INGOT.get())
                .define('B',ItemRegistry.ENDER_UPGRADE_ORB.get())
                .define('C',Items.MACE)
                .define('D',ModItems.SUPERIOR_WEAPON_PARTS.get())
                .unlockedBy("has_material", has(ModItems.VOID_OBSIDIAN_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.REAPER_SICKLE.get())
                .pattern("AAB")
                .pattern(" B ")
                .pattern("B  ")
                .define('A',ModItems.SPECTRITE_INGOT)
                .define('B',ModItems.WITHER_ALLOY_INGOT)
                .unlockedBy("has_material", has(ModItems.WITHER_ALLOY_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WITHERWARD_RING.get())
                .pattern("A ")
                .pattern(" B")
                .define('A',ModItems.NECRO_RUNE.get())
                .define('B', ItemRegistry.MITHRIL_SCRAP.get())
                .unlockedBy("has_material", has(ItemRegistry.MITHRIL_SCRAP.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.UPGRADE_RUNE.get())
                .pattern("ACA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ItemRegistry.ARCANE_INGOT.get())
                .define('B', ItemRegistry.MITHRIL_INGOT.get())
                .define('C',ModItems.UPGRADE_RUNE.get())
                .unlockedBy("has_material", has(ModItems.UPGRADE_RUNE.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NECRO_RUNE.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ModTags.Items.NECRO_FOCUS)
                .define('B', ItemRegistry.BLANK_RUNE.get())
                .unlockedBy("has_material", has(ItemRegistry.BLANK_RUNE.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NECRO_UPGRADE_ORB.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A',ModItems.NECRO_RUNE.get())
                .define('B', ItemRegistry.UPGRADE_ORB.get())
                .unlockedBy("has_material", has(ItemRegistry.UPGRADE_ORB.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ROSE_GOLD_APPLE.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A',ModItems.ROSE_GOLD_INGOT.get())
                .define('B', Items.APPLE)
                .unlockedBy("has_material", has(ModItems.ROSE_GOLD_INGOT.get())).save(recipeOutput);



        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.STRIDER_COTTON_STRING.get(),4)
                .requires(ModItems.STRIDER_COTTON_BALL).unlockedBy("has_material", has(ModItems.STRIDER_COTTON_BALL)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.CINDER_CINNAMON_POWDER.get(),1)
                .requires(ModItems.CINDER_CINNAMON).unlockedBy("has_material", has(ModItems.CINDER_CINNAMON)).save(recipeOutput);


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
