package net.redreaper.monsterspellbooks.init;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    public static final Tier OCEANITE=new SimpleTier(ModTags.Blocks.INCORRECT_FOR_IRON_LEVEL_TOOL,
            500,8f,2,15,()-> Ingredient.of(ModItems.OCEANITE_INGOT));

    public static final Tier ROSE_GOLD=new SimpleTier(ModTags.Blocks.INCORRECT_FOR_IRON_LEVEL_TOOL,
            1561,12.0F,0.0F,22,()-> Ingredient.of(ModItems.ROSE_GOLD_INGOT));

    public static final Tier PRISMARINE=new SimpleTier(ModTags.Blocks.INCORRECT_FOR_DIAMOND_LEVEL_TOOL,
            1400,6f,8,18,()-> Ingredient.of(Items.PRISMARINE_SHARD));

    public static final Tier SANGUINITE=new SimpleTier(ModTags.Blocks.INCORRECT_FOR_DIAMOND_LEVEL_TOOL,
            1400,6f,8,18,()-> Ingredient.of(ModItems.SANGUINITE_INGOT));

    public static final Tier ORICHALCUM=new SimpleTier(ModTags.Blocks.INCORRECT_FOR_DIAMOND_LEVEL_TOOL,
            1400,4f,10,10,()-> Ingredient.of(ModItems.ORICHALCUM_INGOT));

    public static final Tier DWARVEN_ALLOY=new SimpleTier(ModTags.Blocks.INCORRECT_FOR_DIAMOND_LEVEL_TOOL,
            1751,12f,0,10,()-> Ingredient.of(ModItems.DWARVEN_ALLOY_INGOT));
}
