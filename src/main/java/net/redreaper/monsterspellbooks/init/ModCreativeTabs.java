package net.redreaper.monsterspellbooks.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;

import java.util.function.Supplier;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab>CREATIVE_MODE_TAB=
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MonstersSpellbooks.MOD_ID);

    public static final Supplier<CreativeModeTab>MONSTERS_AND_GEAR_TAB=CREATIVE_MODE_TAB.register("monsters_and_gear_tab",
            ()-> CreativeModeTab.builder().icon(()->new ItemStack(ModItems.WITHERWARD_RING.get()))
                    .title(Component.translatable("creative_tab.monsterspellbooks.monsters_and_gear"))
                    .displayItems((itemDisplayParameters, output) ->{
                        output.accept(ModItems.WITHERWARD_RING.get());
                    }).build());

    public static final Supplier<CreativeModeTab>MONSTERS_AND_MATERIALS_TAB=CREATIVE_MODE_TAB.register("monsters_and_materials_tab",
            ()-> CreativeModeTab.builder().icon(()->new ItemStack(ModItems.SCORCHED_METAL_INGOT.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID,"monsters_and_gear_tab"))
                    .title(Component.translatable("creative_tab.monsterspellbooks.monsters_and_materials"))
                    .displayItems((itemDisplayParameters, output) ->{
                        output.accept(ModItems.RAW_OCEANITE);
                        output.accept(ModItems.OCEANITE_INGOT);
                        output.accept(ModItems.OCEANITE_NUGGET);
                        output.accept(ModItems.ROSE_GOLD_INGOT);
                        output.accept(ModItems.ROSE_GOLD_NUGGET);
                        output.accept(ModItems.POSEIDON_PEARL);
                        output.accept(ModItems.RAW_ORICHALCUM);
                        output.accept(ModItems.ORICHALCUM_INGOT);
                        output.accept(ModItems.ORICHALCUM_NUGGET);
                        output.accept(ModItems.DEATHSILVER_INGOT);
                        output.accept(ModItems.DEATHSILVER_NUGGET);
                        output.accept(ModItems.SANGUINITE_INGOT);
                        output.accept(ModItems.DWARVEN_ALLOY_SCRAP);
                        output.accept(ModItems.DWARVEN_ALLOY_INGOT);
                        output.accept(ModItems.DWARVEN_ALLOY_NUGGET);

                        output.accept(ModItems.WHITE_GOLD_INGOT);
                        output.accept(ModItems.WHITE_GOLD_NUGGET);
                        output.accept(ModItems.RAW_SCORCHED_METAL);
                        output.accept(ModItems.SCORCHED_METAL_INGOT);
                        output.accept(ModItems.SCORCHED_METAL_NUGGET);
                        output.accept(ModItems.RAW_VILE_IRON);
                        output.accept(ModItems.VILE_IRON_INGOT);
                        output.accept(ModItems.VILE_IRON_NUGGET);
                        output.accept(ModItems.NETHER_PYRITE);
                        output.accept(ModItems.NETHER_RUBY);
                        output.accept(ModItems.SPECTRITE_INGOT);
                        output.accept(ModItems.BLAZESTEEL_INGOT);
                        output.accept(ModItems.WITHER_ALLOY_INGOT);
                        output.accept(ModItems.WITHER_ALLOY_NUGGET);
                        output.accept(ModItems.VOID_MATTER);
                        output.accept(ModItems.VOID_OBSIDIAN_INGOT);

                        output.accept(ModItems.LEMON);
                        output.accept(ModItems.LEMON_PIE);
                        output.accept(ModItems.LEMON_MERINGUEIT);
                        output.accept(ModItems.ROSE_GOLD_APPLE);
                        output.accept(ModItems.GOLDEN_COD);
                        output.accept(ModItems.COOKED_GOLDEN_COD);
                        output.accept(ModItems.HELL_PEPPER);
                        output.accept(ModItems.SOUL_PEPPER);
                        output.accept(ModItems.BASALT_RADISH);
                        output.accept(ModItems.NETHER_CORN);
                        output.accept(ModItems.NETHER_DOUGH);
                        output.accept(ModItems.NETHER_TORTILLA);
                        output.accept(ModItems.NETHER_TORTILLA_CHIPS);
                        output.accept(ModItems.PAN_DE_MUERTO);
                        output.accept(ModItems.HOGLIN_CORN_DOG);
                        output.accept(ModItems.BATTLE_TACO);
                        output.accept(ModItems.MAGMA_ELOTE);
                        output.accept(ModItems.GOLD_TRUFFLE_SLICE);
                    }).build());

    public static final Supplier<CreativeModeTab>MONSTERS_AND_BLOCKS_TAB=CREATIVE_MODE_TAB.register("monsters_and_blocks_tab",
            ()-> CreativeModeTab.builder().icon(()->new ItemStack(ModItems.RAW_SCORCHED_METAL.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID,"monsters_and_materials_tab"))
                    .title(Component.translatable("creative_tab.monsterspellbooks.monsters_and_blocks"))
                    .displayItems((itemDisplayParameters, output) ->{
                        output.accept(ModBlocks.OCEANITE_ORE_CLAY);
                        output.accept(ModBlocks.OCEANITE_ORE_SAND);
                        output.accept(ModBlocks.OCEANITE_ORE_GRAVEL);
                        output.accept(ModBlocks.RAW_OCEANITE_BLOCK);
                        output.accept(ModBlocks.OCEANITE_BLOCK);
                        output.accept(ModBlocks.ROSE_GOLD_BLOCK);
                        output.accept(ModBlocks.POSEIDON_PEARL_ORE);
                        output.accept(ModBlocks.POSEIDON_PEARL_BLOCK);
                        output.accept(ModBlocks.ORICHALCUM_ORE);
                        output.accept(ModBlocks.DEEPSLATE_ORICHALCUM_ORE);
                        output.accept(ModBlocks.RAW_ORICHALCUM_BLOCK);
                        output.accept(ModBlocks.ORICHALCUM_BLOCK);
                        output.accept(ModBlocks.DEATHSILVER_BLOCK);
                        output.accept(ModBlocks.SANGUINITE_BLOCK);
                        output.accept(ModBlocks.DWARVEN_ALLOY);
                        output.accept(ModBlocks.WHITE_GOLD_BLOCK);
                        output.accept(ModBlocks.SCORCHED_METAL_ORE);
                        output.accept(ModBlocks.RAW_SCORCHED_METAL_BLOCK);
                        output.accept(ModBlocks.SCORCHED_METAL_BLOCK);
                        output.accept(ModBlocks.VILE_IRON_ORE);
                        output.accept(ModBlocks.RAW_VILE_IRON_BLOCK);
                        output.accept(ModBlocks.VILE_IRON_BLOCK);
                        output.accept(ModBlocks.NETHER_PYRITE_ORE);
                        output.accept(ModBlocks.NETHER_PYRITE_BLOCK);
                        output.accept(ModBlocks.NETHER_RUBY_ORE);
                        output.accept(ModBlocks.NETHER_RUBY_BLOCK);
                        output.accept(ModBlocks.SPECTRITE_BLOCK);
                        output.accept(ModBlocks.WITHER_ALLOY_BLOCK);
                        output.accept(ModBlocks.BLAZESTEEL_BLOCK);
                        output.accept(ModBlocks.VOID_MATTER_ORE);
                    }).build());
    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
