package net.redreaper.monsterspellbooks.init;

import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.item.curios.CurioBaseItem;
import io.redspace.ironsspellbooks.registries.ComponentRegistry;
import io.redspace.ironsspellbooks.registries.UpgradeOrbTypeRegistry;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import io.redspace.ironsspellbooks.item.UpgradeOrbItem;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.item.armor.FierceDeityMaskItem;
import net.redreaper.monsterspellbooks.item.armor.MajorasMaskArmorItem;
import net.redreaper.monsterspellbooks.item.armor.SanguiniteHeroArmorItem;
import net.redreaper.monsterspellbooks.item.armor.StarscourgeArmorItem;
import net.redreaper.monsterspellbooks.item.curios.elemental_charm.BrimstoneSigilItem;
import net.redreaper.monsterspellbooks.item.curios.rings.WitherWardCurio;
import net.redreaper.monsterspellbooks.item.curios.spellbooks.reaper_lantern.ReaperLanternSpellBook;
import net.redreaper.monsterspellbooks.item.weapons.*;
import net.redreaper.monsterspellbooks.item.weapons.claws_of_calamity.ClawsOfCalamityItem;
import net.redreaper.monsterspellbooks.item.weapons.crescent_blood_rose.CrescentBloodRoseItem;
import net.redreaper.monsterspellbooks.item.weapons.endersent_crusher.EndersentCrusherItem;
import net.redreaper.monsterspellbooks.item.weapons.magic_axe.ExtendedAxeItem;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister.Items ITEMS=DeferredRegister.createItems(MonstersSpellbooks.MOD_ID);
    public static final DeferredItem<Item>RAW_OCEANITE= ITEMS.register("raw_oceanite",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>OCEANITE_INGOT= ITEMS.register("oceanite_ingot",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>OCEANITE_NUGGET= ITEMS.register("oceanite_nugget",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>ROSE_GOLD_INGOT= ITEMS.register("rose_gold_ingot",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>ROSE_GOLD_NUGGET= ITEMS.register("rose_gold_nugget",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>POSEIDON_PEARL= ITEMS.register("poseidon_pearl",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>RAW_ORICHALCUM= ITEMS.register("raw_orichalcum",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>ORICHALCUM_INGOT= ITEMS.register("orichalcum_ingot",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>ORICHALCUM_NUGGET= ITEMS.register("orichalcum_nugget",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>DEATHSILVER_INGOT= ITEMS.register("deathsilver_ingot",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>DEATHSILVER_NUGGET= ITEMS.register("deathsilver_nugget",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>SANGUINITE_INGOT= ITEMS.register("sanguinite_ingot",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>DWARVEN_ALLOY_SCRAP= ITEMS.register("dwarven_alloy_scrap",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>DWARVEN_ALLOY_INGOT= ITEMS.register("dwarven_alloy_ingot",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>DWARVEN_ALLOY_NUGGET= ITEMS.register("dwarven_alloy_nugget",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>WHITE_GOLD_INGOT= ITEMS.register("white_gold_ingot",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>WHITE_GOLD_NUGGET= ITEMS.register("white_gold_nugget",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>RAW_SCORCHED_METAL= ITEMS.register("raw_scorched_metal",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>SCORCHED_METAL_INGOT= ITEMS.register("scorched_metal_ingot",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>SCORCHED_METAL_NUGGET= ITEMS.register("scorched_metal_nugget",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>RAW_VILE_IRON= ITEMS.register("raw_vile_iron",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>VILE_IRON_INGOT= ITEMS.register("vile_iron_ingot",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>VILE_IRON_NUGGET= ITEMS.register("vile_iron_nugget",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>NETHER_PYRITE= ITEMS.register("nether_pyrite",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>NETHER_RUBY= ITEMS.register("nether_ruby",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>SPECTRITE_INGOT= ITEMS.register("spectrite_ingot",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>BLAZESTEEL_INGOT= ITEMS.register("blazesteel_ingot",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>WITHER_ALLOY_INGOT= ITEMS.register("wither_alloy_ingot",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>WITHER_ALLOY_NUGGET= ITEMS.register("wither_alloy_nugget",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>VOID_MATTER= ITEMS.register("void_matter",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>VOID_OBSIDIAN_INGOT= ITEMS.register("void_obsidian_ingot",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>RESIN_BRICK= ITEMS.register("resin_brick",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>RESIN_HEART= ITEMS.register("resin_heart",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>ELECTRO_CORE= ITEMS.register("electro_core",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>DWARVEN_ALLOY_PLATE= ITEMS.register("dwarven_alloy_plate",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>DWARVEN_ALLOY_COG= ITEMS.register("dwarven_alloy_cog",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>STRIDER_COTTON_BALL= ITEMS.register("strider_cotton_boll",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>STRIDER_COTTON_STRING= ITEMS.register("strider_cotton_string",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>CINDER_CINNAMON= ITEMS.register("cinder_cinnamon",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>CINDER_CINNAMON_POWDER= ITEMS.register("cinder_cinnamon_powder",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>VILE_BONE= ITEMS.register("vile_bone",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>VILE_CORE= ITEMS.register("vile_core",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>UPGRADE_RUNE= ITEMS.register("upgrade_rune",
            ()->new Item(new Item.Properties()));
    public static final DeferredItem<Item>NECRO_RUNE= ITEMS.register("necro_rune",
            ()->new Item(new Item.Properties()));

    public static final DeferredItem<Item>LEMON=ITEMS.register("lemon",
            ()->new Item(new Item.Properties().food(ModFoods.LEMON)));
    public static final DeferredItem<Item>LEMON_PIE=ITEMS.register("lemon_pie",
            ()->new Item(new Item.Properties().food(ModFoods.LEMON_PIE)));
    public static final DeferredItem<Item>LEMON_MERINGUEIT=ITEMS.register("lemon_meringueit",
            ()->new Item(new Item.Properties().food(ModFoods.LEMON_PIE)));
    public static final DeferredItem<Item>ROSE_GOLD_APPLE=ITEMS.register("rose_gold_apple",
            ()->new Item(new Item.Properties().food(ModFoods.ROSE_GOLD_APPLE)));
    public static final DeferredItem<Item>GOLDEN_COD=ITEMS.register("golden_cod",
            ()->new Item(new Item.Properties().food(ModFoods.GOLDEN_COD)));
    public static final DeferredItem<Item>COOKED_GOLDEN_COD=ITEMS.register("cooked_golden_cod",
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

    public static final DeferredHolder<Item, Item> GREAT_AXEBLADE = ITEMS.register("great_axeblade", () ->
            new ExtendedAxeItem(Tiers.IRON, ItemPropertiesHelper.equipment().attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.ORICHALCUM_GREATAXE))));
    public static final DeferredHolder<Item, Item> MASTERS_KATANA = ITEMS.register("masters_katana", () ->
            new ExtendedSwordItem(ModToolTiers.ROSE_GOLD, ItemPropertiesHelper.equipment().attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.MASTERS_KATANA))));
    public static final DeferredHolder<Item, Item> DANCERS_SWORD = ITEMS.register("dancers_sword", () ->
            new ExtendedSwordItem(Tiers.IRON, ItemPropertiesHelper.equipment().attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.MASTERS_KATANA))));
    public static final DeferredItem<AxeItem>PRISMARINE_AXE=ITEMS.register("prismarine_axe", ()->
            new AxeItem(ModToolTiers.PRISMARINE, ItemPropertiesHelper.equipment().attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.PRISMARINE))));
    public static final DeferredHolder<Item, Item> ORICHALCUM_DAGGER = ITEMS.register("orichalcum_dagger", () ->
            new ExtendedSwordItem(ModToolTiers.ORICHALCUM, ItemPropertiesHelper.equipment().attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.ORICHALCUM_DAGGER))));
    public static final DeferredHolder<Item, Item> ORICHALCUM_GREATSWORD = ITEMS.register("orichalcum_greatsword", () ->
            new ExtendedSwordItem(ModToolTiers.ORICHALCUM, ItemPropertiesHelper.equipment().attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.ORICHALCUM_GREATSWORD))));
    public static final DeferredHolder<Item, Item> ORICHALCUM_GREATAXE = ITEMS.register("orichalcum_greataxe", () ->
            new ExtendedAxeItem(ModToolTiers.ORICHALCUM, ItemPropertiesHelper.equipment().attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.ORICHALCUM_GREATAXE))));
    public static final DeferredHolder<Item, Item> ORICHALCUM_WARHAMMER = ITEMS.register("orichalcum_warhammer", () ->
            new ExtendedAxeItem(ModToolTiers.ORICHALCUM, ItemPropertiesHelper.equipment().attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.ORICHALCUM_WARHAMMER))));
    public static final DeferredItem<AxeItem>ORICHALCUM_AXE=ITEMS.register("orichalcum_axe", ()->
            new AxeItem(ModToolTiers.ORICHALCUM, ItemPropertiesHelper.equipment().attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.ORICHALCUM_AXE))));
    public static final DeferredItem<SwordItem>SANGUINITE_DAGGER=ITEMS.register("sanguinite_dagger", ()->
            new SwordItem(ModToolTiers.SANGUINITE,new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.SANGUINITE,-3,-2.5f))));
    public static final DeferredItem<SwordItem>SANGUINITE_SCYTHE=ITEMS.register("sanguinite_scythe",
            ()->new SwordItem(ModToolTiers.SANGUINITE,new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.SANGUINITE,0,-3f))));
    public static final DeferredItem<AxeItem>SANGUINITE_AXE=ITEMS.register("sanguinite_axe",
            ()->new AxeItem(ModToolTiers.SANGUINITE,new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.SANGUINITE,3,-3.2f))));
    public static final DeferredItem<Item>POISON_GLAIVE = ITEMS.register("poison_glaive",
            PoisonGlaive::new);
    public static final DeferredItem<Item>CRESCENT_BLOOD_ROSE = ITEMS.register("crescent_blood_rose",
            CrescentBloodRoseItem::new);
    public static final DeferredItem<Item>CLAWS_OF_CALAMITY = ITEMS.register("claws_of_calamity",
            ClawsOfCalamityItem::new);
    public static final DeferredItem<Item>DUCHEES_SEWING_NEEDLE = ITEMS.register("duchees_sewing_needle",
            DuchessSewingNeedle::new);
    public static final DeferredItem<Item>DUKES_HEARTSTEALER = ITEMS.register("dukes_heartstealer",
            DukesHeartstealer::new);
    public static final DeferredItem<Item>FROStMOURNE = ITEMS.register("frostmourne",
            Frostmourne::new);
    public static final DeferredItem<Item>STARLESS_NIGHT = ITEMS.register("starless_night",
            StarlessNight::new);
    public static final DeferredItem<Item>STARSCOURGE_SWORD = ITEMS.register("starscourge_sword",
            StarscourgeSword::new);
    public static final DeferredItem<Item>SWORD_OF_COURAGE = ITEMS.register("sword_of_courage",
            SwordOfCourage::new);
    public static final DeferredItem<Item>REAPER_SICKLE = ITEMS.register("reaper_sickle",
            ReaperSickle::new);
    public static final DeferredItem<Item>ENDERSENT_CRUSHER = ITEMS.register("endersent_crusher",
            EndersentCrusherItem::new);
    public static final DeferredHolder<Item, Item> REAPER_LANTERN = ITEMS.register("reaper_lantern",
            ReaperLanternSpellBook::new);
    public static final Supplier<CurioBaseItem>WITHERWARD_RING = ITEMS.register("witherward_ring",
            WitherWardCurio::new);
    public static final Supplier<CurioBaseItem>BRIMSTONE_SIGIL = ITEMS.register("brimstone_sigil",
            BrimstoneSigilItem::new);

    public static final DeferredHolder<Item, Item> FIERCE_DEITY_MASK = ITEMS.register("fierce_deity_mask",
            () -> new FierceDeityMaskItem(ArmorItem.Type.HELMET, ItemPropertiesHelper.equipment(1).rarity(Rarity.UNCOMMON).durability(ArmorItem.Type.HELMET.getDurability(30))));
    public static final DeferredHolder<Item, Item> MAJORAS_MASK = ITEMS.register("majoras_mask",
            () -> new MajorasMaskArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper.equipment(1).rarity(Rarity.UNCOMMON).durability(ArmorItem.Type.HELMET.getDurability(30))));
    public static final DeferredHolder<Item, Item> SANGUINITE_HERO_HOOD = ITEMS.register("sanguinite_hero_hood", () ->
            new SanguiniteHeroArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper.equipment(1).durability(ArmorItem.Type.HELMET.getDurability(48))));
    public static final DeferredHolder<Item, Item> SANGUINITE_HERO_CHESTPLATE = ITEMS.register("sanguinite_hero_chestplate", ()
            -> new SanguiniteHeroArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper.equipment(1).durability(ArmorItem.Type.CHESTPLATE.getDurability(48))));
    public static final DeferredHolder<Item, Item> SANGUINITE_HERO_LEGGINGS = ITEMS.register("sanguinite_hero_leggings", () ->
            new SanguiniteHeroArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper.equipment(1).durability(ArmorItem.Type.LEGGINGS.getDurability(48))));
    public static final DeferredHolder<Item, Item> SANGUINITE_HERO_BOOTS = ITEMS.register("sanguinite_hero_boots", () ->
            new SanguiniteHeroArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper.equipment(1).durability(ArmorItem.Type.BOOTS.getDurability(48))));
    public static final DeferredHolder<Item, Item> STARSCOURGE_HELMET = ITEMS.register("starscourge_helmet", () ->
            new StarscourgeArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper.equipment(1).durability(ArmorItem.Type.HELMET.getDurability(48))));
    public static final DeferredHolder<Item, Item> STARSCOURGE_CHESTPLATE = ITEMS.register("starscourge_chestplate", ()
            -> new StarscourgeArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper.equipment(1).durability(ArmorItem.Type.CHESTPLATE.getDurability(48))));
    public static final DeferredHolder<Item, Item> STARSCOURGE_LEGGINGS = ITEMS.register("starscourge_leggings", () ->
            new StarscourgeArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper.equipment(1).durability(ArmorItem.Type.LEGGINGS.getDurability(48))));
    public static final DeferredHolder<Item, Item> STARSCOURGE_BOOTS = ITEMS.register("starscourge_boots", () ->
            new StarscourgeArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper.equipment(1).durability(ArmorItem.Type.BOOTS.getDurability(48))));

    public static final Supplier<DeferredSpawnEggItem> AEGIS_SPAWN_EGG = ITEMS.register("aegis_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.AEGIS, 5526633, 9864548, ItemPropertiesHelper.material().stacksTo(64)));
    public static final Supplier<DeferredSpawnEggItem> DWARVEN_SPHERE_SPAWN_EGG = ITEMS.register("dwarven_sphere_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.DWARVEN_SPHERE, 10050109, 16501864, ItemPropertiesHelper.material().stacksTo(64)));
    public static final Supplier<DeferredSpawnEggItem> DWARVEN_SLICER_SPAWN_EGG = ITEMS.register("dwarven_slicer_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.DWARVEN_SLICER, 10050109, 16501864, ItemPropertiesHelper.material().stacksTo(64)));
    public static final Supplier<DeferredSpawnEggItem> SHOCK_SPAWN_EGG = ITEMS.register("shock_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.SHOCK, 3873928, 11393785, ItemPropertiesHelper.material().stacksTo(64)));
    public static final Supplier<DeferredSpawnEggItem> VILE_SKELETON_SPAWN_EGG = ITEMS.register("vile_skeleton_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.VILE_SKELETON, 725001, 3622442, ItemPropertiesHelper.material().stacksTo(64)));

    public static final DeferredHolder<Item, Item> NECRO_UPGRADE_ORB = ITEMS.register("necro_upgrade_orb",
            () -> new UpgradeOrbItem(ItemPropertiesHelper.material().rarity(Rarity.UNCOMMON).component(ComponentRegistry.UPGRADE_ORB_TYPE, ModUpgradeTypeRegistry.NECRO_SPELL_POWER)));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
