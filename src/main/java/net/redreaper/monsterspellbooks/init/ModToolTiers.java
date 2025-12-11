package net.redreaper.monsterspellbooks.init;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    public static final Tier SANGUINITE=new SimpleTier(ModTags.Blocks.INCORRECT_FOR_SANGUINITE_TOOL,
            1400,6f,8,20,()-> Ingredient.of(ModItems.SANGUINITE_INGOT));
}
