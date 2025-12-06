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

    public static final Supplier<CreativeModeTab>MONSTERS_AND_MATERIALS_TAB=CREATIVE_MODE_TAB.register("monsters_and_materials_tab",
            ()-> CreativeModeTab.builder().icon(()->new ItemStack(ModItems.SCORCHED_METAL_INGOT.get()))
                    .title(Component.translatable("creative_tab.monsterspellbooks.monsters_and_materials"))
                    .displayItems((itemDisplayParameters, output) ->{
                        output.accept(ModItems.RAW_SCORCHED_METAL);
                        output.accept(ModItems.SCORCHED_METAL_INGOT);
                        output.accept(ModItems.SCORCHED_METAL_NUGGET);
                        output.accept(ModItems.RAW_VILE_IRON);
                        output.accept(ModItems.VILE_IRON_INGOT);
                        output.accept(ModItems.VILE_IRON_NUGGET);
                        output.accept(ModItems.NETHER_PYRITE);
                        output.accept(ModItems.NETHER_RUBY);
                        output.accept(ModItems.SPECTRITE_INGOT);
                    }).build());

    public static final Supplier<CreativeModeTab>MONSTERS_AND_BLOCKS_TAB=CREATIVE_MODE_TAB.register("monsters_and_blocks_tab",
            ()-> CreativeModeTab.builder().icon(()->new ItemStack(ModItems.RAW_SCORCHED_METAL.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID,"monsters_and_materials_tab"))
                    .title(Component.translatable("creative_tab.monsterspellbooks.monsters_and_blocks"))
                    .displayItems((itemDisplayParameters, output) ->{
                        output.accept(ModItems.RAW_SCORCHED_METAL);
                    }).build());



    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
