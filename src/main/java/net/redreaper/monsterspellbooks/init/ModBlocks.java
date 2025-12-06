package net.redreaper.monsterspellbooks.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MagmaBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS=DeferredRegister.createBlocks(MonstersSpellbooks.MOD_ID);

    public static final DeferredBlock<Block>WHITE_GOLD_BLOCK=registerBlock("white_gold_block",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK)));
    public static final DeferredBlock<Block>SCORCHED_METAL_ORE =registerBlock("scorched_metal_ore",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).sound(SoundType.GILDED_BLACKSTONE)));
    public static final DeferredBlock<Block>RAW_SCORCHED_METAL_BLOCK=registerBlock("raw_scorched_metal_block",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_GOLD_BLOCK).sound(SoundType.ANCIENT_DEBRIS)));
    public static final DeferredBlock<Block>SCORCHED_METAL_BLOCK=registerBlock("scorched_metal_block",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_GOLD_ORE).sound(SoundType.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block>VILE_IRON_ORE=registerBlock("vile_iron_ore",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).sound(SoundType.GILDED_BLACKSTONE).lightLevel(state -> 7)));
    public static final DeferredBlock<Block>RAW_VILE_IRON_BLOCK=registerBlock("raw_vile_iron_block",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).lightLevel(state -> 7)));
    public static final DeferredBlock<Block>VILE_IRON_BLOCK=registerBlock("vile_iron_block",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).lightLevel(state -> 7)));
    public static final DeferredBlock<Block>NETHER_PYRITE_ORE =registerBlock("nether_pyrite_ore",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.LAPIS_ORE).sound(SoundType.NETHER_ORE)));
    public static final DeferredBlock<Block>NETHER_PYRITE_BLOCK=registerBlock("nether_pyrite_block",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.LAPIS_BLOCK)));
    public static final DeferredBlock<Block>NETHER_RUBY_ORE=registerBlock("nether_ruby_ore",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_ORE).sound(SoundType.NETHER_ORE)));
    public static final DeferredBlock<Block>NETHER_RUBY_BLOCK=registerBlock("nether_ruby_block",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK)));
    public static final DeferredBlock<Block>SPECTRITE_BLOCK=registerBlock("spectrite_block",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block>WITHER_ALLOY_BLOCK=registerBlock("wither_alloy_block",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block>BLAZESTEEL_BLOCK=registerBlock("blazesteel_block",
            ()->new MagmaBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK)));
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn=BLOCKS.register(name,block);
        registerBlockItem(name,toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T>block){
        ModItems.ITEMS.register(name,()->new BlockItem(block.get(),new Item.Properties()));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
