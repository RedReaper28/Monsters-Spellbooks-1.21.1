package net.redreaper.monsterspellbooks.init;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    public static final Tier SANGUINITE=new SimpleTier(ModTags.Blocks.INCORRECT_FOR_SANGUINITE_TOOL,
            1400,6f,8,18,()-> Ingredient.of(ModItems.SANGUINITE_INGOT));
    public static final Tier DWARVEN_ALLOY=new SimpleTier(ModTags.Blocks.INCORRECT_FOR_DIAMOND_LEVEL_TOOL,
            1751,12f,0,25,()-> Ingredient.of(ModItems.DWARVEN_ALLOY_INGOT));
}
