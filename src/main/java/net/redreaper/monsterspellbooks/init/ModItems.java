package net.redreaper.monsterspellbooks.init;

import io.redspace.ironsspellbooks.item.curios.CurioBaseItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.item.curios.WitherWardCurio;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister.Items ITEMS=DeferredRegister.createItems(MonstersSpellbooks.MOD_ID);
    public static final DeferredItem<Item> RAW_OCEANITE= ITEMS.register("raw_oceanite",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> OCEANITE_INGOT= ITEMS.register("oceanite_ingot",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> OCEANITE_NUGGET= ITEMS.register("oceanite_nugget",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> ROSE_GOLD_INGOT= ITEMS.register("rose_gold_ingot",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> ROSE_GOLD_NUGGET= ITEMS.register("rose_gold_nugget",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> POSEIDON_PEARL= ITEMS.register("poseidon_pearl",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_ORICHALCUM= ITEMS.register("raw_orichalcum",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> ORICHALCUM_INGOT= ITEMS.register("orichalcum_ingot",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> ORICHALCUM_NUGGET= ITEMS.register("orichalcum_nugget",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> DEATHSILVER_INGOT= ITEMS.register("deathsilver_ingot",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> DEATHSILVER_NUGGET= ITEMS.register("deathsilver_nugget",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> SANGUINITE_INGOT= ITEMS.register("sanguinite_ingot",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> DWARVEN_ALLOY_SCRAP= ITEMS.register("dwarven_alloy_scrap",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> DWARVEN_ALLOY_INGOT= ITEMS.register("dwarven_alloy_ingot",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> DWARVEN_ALLOY_NUGGET= ITEMS.register("dwarven_alloy_nugget",
            ()->new Item(new Item.Properties()));

    public static final DeferredItem<Item> WHITE_GOLD_INGOT= ITEMS.register("white_gold_ingot",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> WHITE_GOLD_NUGGET= ITEMS.register("white_gold_nugget",
            ()->new Item(new Item.Properties()));
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
    public static final DeferredItem<Item> BLAZESTEEL_INGOT= ITEMS.register("blazesteel_ingot",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> WITHER_ALLOY_INGOT= ITEMS.register("wither_alloy_ingot",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> WITHER_ALLOY_NUGGET= ITEMS.register("wither_alloy_nugget",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> VOID_MATTER= ITEMS.register("void_matter",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> VOID_OBSIDIAN_INGOT= ITEMS.register("void_obsidian_ingot",
            ()->new Item(new Item.Properties()));

    public static final DeferredItem<Item> LEMON=ITEMS.register("lemon",
            ()->new Item(new Item.Properties().food(ModFoods.LEMON)));
    public static final DeferredItem<Item> LEMON_PIE=ITEMS.register("lemon_pie",
            ()->new Item(new Item.Properties().food(ModFoods.LEMON_PIE)));
    public static final DeferredItem<Item> LEMON_MERINGUEIT=ITEMS.register("lemon_meringueit",
            ()->new Item(new Item.Properties().food(ModFoods.LEMON_PIE)));
    public static final DeferredItem<Item> ROSE_GOLD_APPLE=ITEMS.register("rose_gold_apple",
            ()->new Item(new Item.Properties().food(ModFoods.ROSE_GOLD_APPLE)));
    public static final DeferredItem<Item> GOLDEN_COD=ITEMS.register("golden_cod",
            ()->new Item(new Item.Properties().food(ModFoods.GOLDEN_COD)));
    public static final DeferredItem<Item> COOKED_GOLDEN_COD=ITEMS.register("cooked_golden_cod",
            ()->new Item(new Item.Properties().food(ModFoods.COOKED_GOLDEN_COD)));
    public static final DeferredItem<Item>HELL_PEPPER=ITEMS.register("hell_pepper",
            ()->new Item(new Item.Properties().fireResistant().food(ModFoods.HELL_PEPPER)));
    public static final DeferredItem<Item>SOUL_PEPPER=ITEMS.register("soul_pepper",
            ()->new Item(new Item.Properties().fireResistant().food(ModFoods.SOUL_PEPPER)));
    public static final DeferredItem<Item>BASALT_RADISH=ITEMS.register("basalt_radish",
            ()->new Item(new Item.Properties().fireResistant().food(ModFoods.BASALT_RADISH)));
    public static final DeferredItem<Item>NETHER_CORN=ITEMS.register("nether_corn",
            ()->new Item(new Item.Properties().fireResistant().food(ModFoods.NETHER_CORN)));
    public static final DeferredItem<Item>NETHER_DOUGH =ITEMS.register("nether_dough",
            ()->new Item(new Item.Properties().fireResistant()));
    public static final DeferredItem<Item>NETHER_TORTILLA=ITEMS.register("nether_tortilla",
            ()->new Item(new Item.Properties().fireResistant().food(ModFoods.NETHER_TORTILLA)));
    public static final DeferredItem<Item>NETHER_TORTILLA_CHIPS=ITEMS.register("nether_tortilla_chips",
            ()->new Item(new Item.Properties().fireResistant().food(ModFoods.NETHER_TORTILLA_CHIPS)));
    public static final DeferredItem<Item>PAN_DE_MUERTO=ITEMS.register("pan_de_muerto",
            ()->new Item(new Item.Properties().fireResistant().food(ModFoods.PAN_DE_MUERTO)));
    public static final DeferredItem<Item>HOGLIN_CORN_DOG=ITEMS.register("hoglin_corn_dog",
            ()->new Item(new Item.Properties().fireResistant().food(ModFoods.HOGLIN_CORN_DOG)));
    public static final DeferredItem<Item>BATTLE_TACO=ITEMS.register("battle_taco",
            ()->new Item(new Item.Properties().fireResistant().food(ModFoods.BATTLE_TACO)));
    public static final DeferredItem<Item>MAGMA_ELOTE=ITEMS.register("magma_elote",
            ()->new Item(new Item.Properties().food(ModFoods.MAGMA_ELOTE)));
    public static final DeferredItem<Item>GOLD_TRUFFLE_SLICE=ITEMS.register("gold_truffle_slice",
            ()->new Item(new Item.Properties().fireResistant().food(ModFoods.GOLD_TRUFFLE_SLICE)));

    public static final Supplier<CurioBaseItem> WITHERWARD_RING = ITEMS.register("witherward_ring",
            WitherWardCurio::new);


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
