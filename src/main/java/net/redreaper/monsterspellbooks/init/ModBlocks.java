package net.redreaper.monsterspellbooks.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS=DeferredRegister.createBlocks(MonstersSpellbooks.MOD_ID);

    public static final DeferredBlock<Block>OCEANITE_ORE_CLAY=registerBlock("oceanite_ore_clay",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).sound(SoundType.GRAVEL)));
    public static final DeferredBlock<Block>OCEANITE_ORE_SAND=registerBlock("oceanite_ore_sand",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).sound(SoundType.SAND)));
    public static final DeferredBlock<Block>OCEANITE_ORE_GRAVEL=registerBlock("oceanite_ore_gravel",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).sound(SoundType.GRAVEL)));
    public static final DeferredBlock<Block>RAW_OCEANITE_BLOCK=registerBlock("raw_oceanite_block",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).sound(SoundType.PACKED_MUD)));
    public static final DeferredBlock<Block>OCEANITE_BLOCK=registerBlock("oceanite_block",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.MUD_BRICKS)));
    public static final DeferredBlock<Block>ROSE_GOLD_BLOCK=registerBlock("rose_gold_block",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.COPPER)));
    public static final DeferredBlock<Block>POSEIDON_PEARL_ORE=registerBlock("poseidon_pearl_ore",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK).sound(SoundType.AMETHYST)));
    public static final DeferredBlock<Block>POSEIDON_PEARL_BLOCK=registerBlock("poseidon_pearl_block",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK).sound(SoundType.AMETHYST)));
    public static final DeferredBlock<Block>ORICHALCUM_ORE=registerBlock("orichalcum_ore",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE).sound(SoundType.STONE)));
    public static final DeferredBlock<Block>DEEPSLATE_ORICHALCUM_ORE=registerBlock("deepslate_orichalcum_ore",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE).sound(SoundType.STONE)));
    public static final DeferredBlock<Block>RAW_ORICHALCUM_BLOCK=registerBlock("raw_orichalcum_block",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).sound(SoundType.METAL)));
    public static final DeferredBlock<Block>ORICHALCUM_BLOCK=registerBlock("orichalcum_block",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).sound(SoundType.METAL)));
    public static final DeferredBlock<Block>DEATHSILVER_BLOCK=registerBlock("deathsilver_block",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).sound(SoundType.METAL)));
    public static final DeferredBlock<Block>SANGUINITE_BLOCK=registerBlock("sanguinite_block",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).sound(SoundType.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block>DWARVEN_ALLOY_BLOCK =registerBlock("dwarven_alloy_block",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).sound(SoundType.NETHERITE_BLOCK)));

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
    public static final DeferredBlock<Block>VOID_MATTER_ORE=registerBlock("void_matter_ore",
            ()->new MagmaBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ANCIENT_DEBRIS).sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block>CORAL_LOG=registerBlock("coral_log",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS).sound(SoundType.CORAL_BLOCK)));
    public static final DeferredBlock<Block>CORAL_PLANKS=registerBlock("coral_planks",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS)));

    public static final DeferredBlock<Block>PEARl_MARBLE=registerBlock("pearl_marble",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE)));
    public static final DeferredBlock<Block>POLISHED_PEARl_MARBLE=registerBlock("polished_pearl_marble",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_BRICKS)));
    public static final DeferredBlock<Block>PEARl_MARBLE_TILES=registerBlock("pearl_marble_tiles",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_BRICKS)));
    public static final DeferredBlock<Block>PEARl_MARBLE_PILLAR=registerBlock("pearl_marble_pillar",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE)));


    public static final DeferredBlock<Block>VILESTONE=registerBlock("vilestone",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE)));
    public static final DeferredBlock<Block>GRAVISTONE=registerBlock("gravistone",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)));

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
