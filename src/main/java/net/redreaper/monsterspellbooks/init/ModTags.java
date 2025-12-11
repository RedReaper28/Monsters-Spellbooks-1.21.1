package net.redreaper.monsterspellbooks.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> NEEDS_SANGUINITE_TOOL=createTag("needs_sanguinite_tool");
        public static final TagKey<Block> INCORRECT_FOR_SANGUINITE_TOOL=createTag("incorrect_for_sanguinite_tool");



        private static TagKey<Block>createTag(String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID,name));
        }
    }

    public static class Items{

        public static final TagKey<Item> NECRO_FOCUS=createTag("necro_focus");

        private static TagKey<Item>createTag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID,name));
        }
    }
}
