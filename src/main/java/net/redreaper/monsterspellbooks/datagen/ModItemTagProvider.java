package net.redreaper.monsterspellbooks.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.init.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, MonstersSpellbooks.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(ItemTags.SWORDS)
                .add(ModItems.GREAT_AXEBLADE.get())
                .add(ModItems.MASTERS_KATANA.get())
                .add(ModItems.DANCERS_SWORD.get())
                .add(ModItems.ORICHALCUM_DAGGER.get())
                .add(ModItems.ORICHALCUM_GREATSWORD.get())
                .add(ModItems.ORICHALCUM_WARHAMMER.get())
                .add(ModItems.DWARVEN_WARHAMMER.get())
                .add(ModItems.SANGUINITE_DAGGER.get())
                .add(ModItems.SANGUINITE_SCYTHE.get())
                .add(ModItems.POISON_GLAIVE.get())
                .add(ModItems.CRESCENT_BLOOD_ROSE.get())
                .add(ModItems.CLAWS_OF_CALAMITY.get())
                .add(ModItems.DUCHEES_SEWING_NEEDLE.get())
                .add(ModItems.DUKES_HEARTSTEALER.get())
                .add(ModItems.FROSTMOURNE.get())
                .add(ModItems.STARLESS_NIGHT.get())
                .add(ModItems.STARSCOURGE_SWORD.get())
                .add(ModItems.SWORD_OF_COURAGE.get());

        tag(ItemTags.AXES)
                .add(ModItems.GREAT_AXEBLADE.get())
                .add(ModItems.ORICHALCUM_GREATAXE.get())
                .add(ModItems.ORICHALCUM_AXE.get())
                .add(ModItems.DWARVEN_GREATAXE.get())
                .add(ModItems.DWARVEN_AXE.get())
                .add(ModItems.PRISMARINE_AXE.get())
                .add(ModItems.SANGUINITE_AXE.get())
        ;

        tag(ItemTags.SWORD_ENCHANTABLE)
                .add(ModItems.ORICHALCUM_GREATAXE.get())
                .add(ModItems.DWARVEN_GREATAXE.get())
                .add(ModItems.SANGUINITE_AXE.get())
        ;

        tag(ItemTags.MACE_ENCHANTABLE)
                .add(ModItems.ENDERSENT_CRUSHER.get());

        tag(ItemTags.WEAPON_ENCHANTABLE)
                .add(ModItems.ENDERSENT_CRUSHER.get());

        tag(ItemTags.HEAD_ARMOR)
                .add(ModItems.FIERCE_DEITY_MASK.get())
                .add(ModItems.MAJORAS_MASK.get())
                .add(ModItems.SANGUINITE_HERO_HOOD.get())
                .add(ModItems.DWARVEN_ENGINEER_VISOR.get())
                .add(ModItems.PALE_OBSERVER_MASK.get())
                .add(ModItems.FORGE_MASTERPIECE_HELMET.get())
                .add(ModItems.STARSCOURGE_HELMET.get());

        tag(ItemTags.CHEST_ARMOR)
                .add(ModItems.SANGUINITE_HERO_CHESTPLATE.get())
                .add(ModItems.DWARVEN_ENGINEER_CHESTPLATE.get())
                .add(ModItems.PALE_OBSERVER_CHESTPLATE.get())
                .add(ModItems.FORGE_MASTERPIECE_CHESTPLATE.get())
                .add(ModItems.STARSCOURGE_CHESTPLATE.get());

        tag(ItemTags.LEG_ARMOR)
                .add(ModItems.SANGUINITE_HERO_LEGGINGS.get())
                .add(ModItems.DWARVEN_ENGINEER_LEGGINGS.get())
                .add(ModItems.PALE_OBSERVER_CHESTPLATE.get())
                .add(ModItems.FORGE_MASTERPIECE_LEGGINGS.get())
                .add(ModItems.STARSCOURGE_LEGGINGS.get());

        tag(ItemTags.FOOT_ARMOR)
                .add(ModItems.SANGUINITE_HERO_BOOTS.get())
                .add(ModItems.DWARVEN_ENGINEER_BOOTS.get())
                .add(ModItems.PALE_OBSERVER_BOOTS.get())
                .add(ModItems.FORGE_MASTERPIECE_BOOTS.get())
                .add(ModItems.STARSCOURGE_BOOTS.get());
    }
}
