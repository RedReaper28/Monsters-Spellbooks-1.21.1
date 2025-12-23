package net.redreaper.monsterspellbooks.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> NEEDS_SANGUINITE_TOOL=createTag("needs_sanguinite_tool");
        public static final TagKey<Block> INCORRECT_FOR_SANGUINITE_TOOL=createTag("incorrect_for_sanguinite_tool");

        public static final TagKey<Block> NEEDS_IRON_LEVEL_TOOL =createTag("needs_iron_level_tool");
        public static final TagKey<Block> INCORRECT_FOR_IRON_LEVEL_TOOL =createTag("incorrect_for_iron_level_tool");

        public static final TagKey<Block> NEEDS_DIAMOND_LEVEL_TOOL =createTag("needs_diamond_level_tool");
        public static final TagKey<Block> INCORRECT_FOR_DIAMOND_LEVEL_TOOL =createTag("incorrect_for_diamond_level_tool");

        private static TagKey<Block>createTag(String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID,name));
        }
    }

    public static class Items{

        public static final TagKey<Item> NECRO_FOCUS=createTag("necro_focus");
        public static final TagKey<Item> AERO_FOCUS=createTag("aero_focus");
        public static final TagKey<Item> SUPERIOR_ARCANE_INGOT_BASE=createTag("superior_arcane_ingot_base");

        private static TagKey<Item>createTag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID,name));
        }
    }

    public static class Entities{
        public static final TagKey<EntityType<?>> DWARVEN_CONSTRUCT = TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "dwarven_construct"));
        public static final TagKey<EntityType<?>> FEAR_IMMUNE = TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "fear_immune"));


        private static TagKey<EntityType<?>> registerEntityTag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, name));
        }
    }
}
