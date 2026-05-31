package net.redreaper.monsterspellbooks.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.init.ModItems;

@mezz.jei.api.JeiPlugin
public class ModJeiPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addItemStackInfo(new ItemStack(ModItems.POSEIDON_PEARL.get()), Component.translatable("item.monsterspellbooks.poseidon_pearl.guide"));
        registration.addItemStackInfo(new ItemStack(ModItems.RAW_ORICHALCUM.get()), Component.translatable("item.monsterspellbooks.raw_orichalcum.guide"));
        registration.addItemStackInfo(new ItemStack(ModItems.DWARVEN_ALLOY_SCRAP.get()), Component.translatable("item.monsterspellbooks.dwarven_alloy_scrap.guide"));
        registration.addItemStackInfo(new ItemStack(ModItems.RAW_SCORCHED_METAL.get()), Component.translatable("item.monsterspellbooks.raw_scorched_metal.guide"));
        registration.addItemStackInfo(new ItemStack(ModItems.NETHER_PYRITE.get()), Component.translatable("item.monsterspellbooks.nether_pyrite.guide"));
        registration.addItemStackInfo(new ItemStack(ModItems.NETHER_RUBY.get()), Component.translatable("item.monsterspellbooks.nether_pyrite.guide"));
        registration.addItemStackInfo(new ItemStack(ModItems.ILLAGERITE_INGOT.get()), Component.translatable("item.monsterspellbooks.illagerite_ingot.guide"));
        registration.addItemStackInfo(new ItemStack(ModItems.VOID_MATTER.get()), Component.translatable("item.monsterspellbooks.void_matter.guide"));
        registration.addItemStackInfo(new ItemStack(ModItems.HOMUNCULUS_BLOOD_ORB.get()), Component.translatable("item.monsterspellbooks.homunculus_blood_orb.guide"));
        registration.addItemStackInfo(new ItemStack(ModItems.VILE_BONE.get()), Component.translatable("item.monsterspellbooks.vile_bone.guide"));
        registration.addItemStackInfo(new ItemStack(ModItems.ECTOPLASM_BALL.get()), Component.translatable("item.monsterspellbooks.ectoplasm_ball.guide"));
        registration.addItemStackInfo(new ItemStack(ModItems.UPGRADE_RUNE.get()), Component.translatable("item.monsterspellbooks.upgrade_rune.guide"));
        registration.addItemStackInfo(new ItemStack(ModItems.WISEWOOD_BONE.get()), Component.translatable("item.monsterspellbooks.wisewood_bone.guide"));

    }
}
