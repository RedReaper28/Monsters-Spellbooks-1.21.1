package net.redreaper.monsterspellbooks.init;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;

public class ModItems {
    public static final DeferredRegister.Items ITEMS=DeferredRegister.createItems(MonstersSpellbooks.MOD_ID);

    public static final DeferredItem<Item> RAW_SCORCHED_METAL= ITEMS.register("raw_scorched_metal",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> SCORCHED_METAL_INGOT= ITEMS.register("scorched_metal_ingot",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> SCORCHED_METAL_NUGGET= ITEMS.register("scorched_metal_nugget",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_VILE_IRON= ITEMS.register("raw_vile_iron",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> VILE_IRON_INGOT= ITEMS.register("vile_iron_ingot",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> VILE_IRON_NUGGET= ITEMS.register("vile_iron_nugget",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> NETHER_PYRITE= ITEMS.register("nether_pyrite",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> NETHER_RUBY= ITEMS.register("nether_ruby",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> SPECTRITE_INGOT= ITEMS.register("spectrite_ingot",
            ()->new Item(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
