package net.redreaper.monsterspellbooks.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.init.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MonstersSpellbooks.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.RAW_OCEANITE.get());
        basicItem(ModItems.OCEANITE_INGOT.get());
        basicItem(ModItems.OCEANITE_NUGGET.get());
        basicItem(ModItems.ROSE_GOLD_INGOT.get());
        basicItem(ModItems.ROSE_GOLD_NUGGET.get());
        basicItem(ModItems.POSEIDON_PEARL.get());
        basicItem(ModItems.RAW_ORICHALCUM.get());
        basicItem(ModItems.ORICHALCUM_INGOT.get());
        basicItem(ModItems.ORICHALCUM_NUGGET.get());
        basicItem(ModItems.DEATHSILVER_INGOT.get());
        basicItem(ModItems.DEATHSILVER_NUGGET.get());
        basicItem(ModItems.SANGUINITE_INGOT.get());
        basicItem(ModItems.DWARVEN_ALLOY_SCRAP.get());
        basicItem(ModItems.DWARVEN_ALLOY_INGOT.get());
        basicItem(ModItems.DWARVEN_ALLOY_NUGGET.get());
        basicItem(ModItems.WHITE_GOLD_INGOT.get());
        basicItem(ModItems.WHITE_GOLD_NUGGET.get());
        basicItem(ModItems.RAW_SCORCHED_METAL.get());
        basicItem(ModItems.SCORCHED_METAL_INGOT.get());
        basicItem(ModItems.SCORCHED_METAL_NUGGET.get());
        basicItem(ModItems.RAW_VILE_IRON.get());
        basicItem(ModItems.VILE_IRON_INGOT.get());
        basicItem(ModItems.VILE_IRON_NUGGET.get());
        basicItem(ModItems.NETHER_PYRITE.get());
        basicItem(ModItems.NETHER_RUBY.get());
        basicItem(ModItems.SPECTRITE_INGOT.get());
        basicItem(ModItems.BLAZESTEEL_INGOT.get());
        basicItem(ModItems.WITHER_ALLOY_INGOT.get());
        basicItem(ModItems.WITHER_ALLOY_NUGGET.get());
        basicItem(ModItems.VOID_MATTER.get());
        basicItem(ModItems.VOID_OBSIDIAN_INGOT.get());

        basicItem(ModItems.VILE_BONE.get());
        basicItem(ModItems.NECRO_RUNE.get());

        basicItem(ModItems.LEMON.get());
        basicItem(ModItems.LEMON_PIE.get());
        basicItem(ModItems.LEMON_MERINGUEIT.get());
        basicItem(ModItems.ROSE_GOLD_APPLE.get());
        basicItem(ModItems.GOLDEN_COD.get());
        basicItem(ModItems.COOKED_GOLDEN_COD.get());
        basicItem(ModItems.HELL_PEPPER.get());
        basicItem(ModItems.SOUL_PEPPER.get());
        basicItem(ModItems.BASALT_RADISH.get());
        basicItem(ModItems.NETHER_CORN.get());
        basicItem(ModItems.NETHER_DOUGH.get());
        basicItem(ModItems.NETHER_TORTILLA.get());
        basicItem(ModItems.NETHER_TORTILLA_CHIPS.get());
        basicItem(ModItems.PAN_DE_MUERTO.get());
        basicItem(ModItems.HOGLIN_CORN_DOG.get());
        basicItem(ModItems.BATTLE_TACO.get());
        basicItem(ModItems.MAGMA_ELOTE.get());
        basicItem(ModItems.GOLD_TRUFFLE_SLICE.get());
        basicItem(ModItems.FIERCE_DEITY_MASK.get());
        basicItem(ModItems.MAJORAS_MASK.get());
        basicItem(ModItems.SANGUINITE_HERO_HOOD.get());
        basicItem(ModItems.SANGUINITE_HERO_CHESTPLATE.get());
        basicItem(ModItems.SANGUINITE_HERO_LEGGINGS.get());
        basicItem(ModItems.SANGUINITE_HERO_BOOTS.get());
        basicItem(ModItems.STARSCOURGE_HELMET.get());
        basicItem(ModItems.STARSCOURGE_CHESTPLATE.get());
        basicItem(ModItems.STARSCOURGE_LEGGINGS.get());
        basicItem(ModItems.STARSCOURGE_BOOTS.get());

        basicItem(ModItems.WITHERWARD_RING.get());
    }
}
