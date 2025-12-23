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
                .add(ModItems.ORICHALCUM_DAGGER.get())
                .add(ModItems.ORICHALCUM_GREATSWORD.get())
                .add(ModItems.ORICHALCUM_WARHAMMER.get())

                .add(ModItems.SANGUINITE_DAGGER.get())
                .add(ModItems.SANGUINITE_SCYTHE.get())
                .add(ModItems.CRESCENT_BLOOD_ROSE.get())
                .add(ModItems.CLAWS_OF_CALAMITY.get())
                .add(ModItems.DUCHEES_SEWING_NEEDLE.get())
                .add(ModItems.DUKES_HEARTSTEALER.get())
                .add(ModItems.FROStMOURNE.get())
                .add(ModItems.STARLESS_NIGHT.get())
                .add(ModItems.STARSCOURGE_SWORD.get())
                .add(ModItems.SWORD_OF_COURAGE.get());

        tag(ItemTags.AXES)
                .add(ModItems.ORICHALCUM_GREATAXE.get())
                .add(ModItems.ORICHALCUM_AXE.get())
                .add(ModItems.SANGUINITE_AXE.get())
        ;

        tag(ItemTags.SWORD_ENCHANTABLE)
                .add(ModItems.ORICHALCUM_GREATAXE.get())
                .add(ModItems.SANGUINITE_AXE.get())
        ;

        tag(ItemTags.MACE_ENCHANTABLE)
                .add(ModItems.ENDERSENT_CRUSHER.get());
    }




}
