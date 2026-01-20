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

@SuppressWarnings("unused")
public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab>CREATIVE_MODE_TAB=
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MonstersSpellbooks.MOD_ID);

    public static final Supplier<CreativeModeTab>MONSTERS_AND_GEAR_TAB=CREATIVE_MODE_TAB.register("monsters_and_gear_tab",
            ()-> CreativeModeTab.builder().icon(()->new ItemStack(ModItems.WITHERWARD_RING.get()))
                    .title(Component.translatable("creative_tab.monsterspellbooks.monsters_and_gear"))
                    .displayItems((itemDisplayParameters, output) ->{
                        output.accept(ModItems.REAPER_LANTERN.get());
                        output.accept(ModItems.VILENOVA_STAFF.get());
                        output.accept(ModItems.BRIMSTONE_OROCHI.get());
                        output.accept(ModItems.GREAT_AXEBLADE.get());
                        output.accept(ModItems.MASTERS_KATANA.get());
                        output.accept(ModItems.DANCERS_SWORD.get());
                        output.accept(ModItems.PRISMARINE_AXE.get());
                        output.accept(ModItems.ORICHALCUM_DAGGER.get());
                        output.accept(ModItems.ORICHALCUM_GREATSWORD.get());
                        output.accept(ModItems.ORICHALCUM_GREATAXE.get());
                        output.accept(ModItems.ORICHALCUM_WARHAMMER.get());
                        output.accept(ModItems.ORICHALCUM_AXE.get());

                        output.accept(ModItems.DWARVEN_GREATAXE.get());
                        output.accept(ModItems.DWARVEN_WARHAMMER.get());
                        output.accept(ModItems.DWARVEN_AXE.get());

                        output.accept(ModItems.SANGUINITE_DAGGER.get());
                        output.accept(ModItems.SANGUINITE_SCYTHE.get());
                        output.accept(ModItems.SANGUINITE_AXE.get());
                        output.accept(ModItems.CRESCENT_BLOOD_ROSE.get());
                        output.accept(ModItems.POISON_GLAIVE.get());
                        output.accept(ModItems.CLAWS_OF_CALAMITY.get());
                        output.accept(ModItems.DUCHEES_SEWING_NEEDLE.get());
                        output.accept(ModItems.DUKES_HEARTSTEALER.get());
                        output.accept(ModItems.SWORD_OF_COURAGE.get());
                        output.accept(ModItems.FROSTMOURNE.get());
                        output.accept(ModItems.MAGMATIC_MACUAHUITL.get());
                        output.accept(ModItems.STARLESS_NIGHT.get());
                        output.accept(ModItems.STARSCOURGE_SWORD.get());
                        output.accept(ModItems.DISRUPTION_NAGINATA.get());
                        output.accept(ModItems.ENDERSENT_CRUSHER.get());
                        output.accept(ModItems.REAPER_SICKLE.get());

                        output.accept(ModItems.WITHERWARD_RING.get());
                        output.accept(ModItems.CRITICAL_GLASSES.get());
                        output.accept(ModItems.BRIMSTONE_SIGIL.get());
                        output.accept(ModItems.DREADHOUND_TOOTH_NECKLACE.get());
                        output.accept(ModItems.DWARVEN_POWER_CORE.get());
                        output.accept(ModItems.OBSIDIAN_MEDAL.get());
                        output.accept(ModItems.ORB_SOUL.get());
                        output.accept(ModItems.BLIZZARD_CALLER.get());
                        output.accept(ModItems.CROSS_NECKLACE.get());

                        output.accept(ModItems.FIERCE_DEITY_MASK.get());
                        output.accept(ModItems.MAJORAS_MASK.get());
                        output.accept(ModItems.FLESH_MAIDEN.get());

                        output.accept(ModItems.SANGUINITE_HERO_HOOD.get());
                        output.accept(ModItems.SANGUINITE_HERO_CHESTPLATE.get());
                        output.accept(ModItems.SANGUINITE_HERO_LEGGINGS.get());
                        output.accept(ModItems.SANGUINITE_HERO_BOOTS.get());

                        output.accept(ModItems.DWARVEN_ENGINEER_VISOR.get());
                        output.accept(ModItems.DWARVEN_ENGINEER_CHESTPLATE.get());
                        output.accept(ModItems.DWARVEN_ENGINEER_LEGGINGS.get());
                        output.accept(ModItems.DWARVEN_ENGINEER_BOOTS.get());

                        output.accept(ModItems.DEATH_KNIGHT_HELMET.get());
                        output.accept(ModItems.DEATH_KNIGHT_CHESTPLATE.get());
                        output.accept(ModItems.DEATH_KNIGHT_LEGGINGS.get());
                        output.accept(ModItems.DEATH_KNIGHT_BOOTS.get());

                        output.accept(ModItems.PALE_OBSERVER_MASK.get());
                        output.accept(ModItems.PALE_OBSERVER_CHESTPLATE.get());
                        output.accept(ModItems.PALE_OBSERVER_LEGGINGS.get());
                        output.accept(ModItems.PALE_OBSERVER_BOOTS.get());

                        output.accept(ModItems.FORGE_MASTERPIECE_HELMET.get());
                        output.accept(ModItems.FORGE_MASTERPIECE_CHESTPLATE.get());
                        output.accept(ModItems.FORGE_MASTERPIECE_LEGGINGS.get());
                        output.accept(ModItems.FORGE_MASTERPIECE_BOOTS.get());

                        output.accept(ModItems.STARSCOURGE_HELMET.get());
                        output.accept(ModItems.STARSCOURGE_CHESTPLATE.get());
                        output.accept(ModItems.STARSCOURGE_LEGGINGS.get());
                        output.accept(ModItems.STARSCOURGE_BOOTS.get());
                    }).build());

    public static final Supplier<CreativeModeTab>MONSTERS_AND_MATERIALS_TAB=CREATIVE_MODE_TAB.register("monsters_and_materials_tab",
            ()-> CreativeModeTab.builder().icon(()->new ItemStack(ModItems.SCORCHED_METAL_INGOT.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID,"monsters_and_gear_tab"))
                    .title(Component.translatable("creative_tab.monsterspellbooks.monsters_and_materials"))
                    .displayItems((itemDisplayParameters, output) ->{
                        output.accept(ModItems.NECRO_UPGRADE_ORB.get());

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

                        output.accept(ModItems.RESIN_BRICK);
                        output.accept(ModItems.RESIN_HEART);
                        output.accept(ModItems.HELL_PEPPER);
                        output.accept(ModItems.HOMUNCULUS_BLOOD_ORB);
                        output.accept(ModItems.RAW_SANGUINITE_VIAL);
                        output.accept(ModItems.ELECTRO_CORE);
                        output.accept(ModItems.DWARVEN_ALLOY_PLATE);
                        output.accept(ModItems.DWARVEN_ALLOY_COG);
                        output.accept(ModItems.DWARVEN_UPGRADE);
                        output.accept(ModItems.STRIDER_COTTON_BALL);
                        output.accept(ModItems.STRIDER_COTTON_STRING);
                        output.accept(ModItems.CINDER_CINNAMON);
                        output.accept(ModItems.CINDER_CINNAMON_POWDER);
                        output.accept(ModItems.VILE_BONE);
                        output.accept(ModItems.VILE_CORE);
                        output.accept(ModItems.PUTRESCENCE_VIAL);
                        output.accept(ModItems.VOID_VIAL);
                        output.accept(ModItems.SUPERIOR_WEAPON_PARTS);
                        output.accept(ModItems.UPGRADE_RUNE);
                        output.accept(ModItems.NECRO_RUNE);

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
                        output.accept(ModItems.AEGIS_SPAWN_EGG.get());
                        output.accept(ModItems.DRIPPLER_SPAWN_EGG.get());
                        output.accept(ModItems.DWARVEN_SPHERE_SPAWN_EGG.get());
                        output.accept(ModItems.DWARVEN_SLICER_SPAWN_EGG.get());
                        output.accept(ModItems.SHOCK_SPAWN_EGG.get());
                        output.accept(ModItems.VILE_SKELETON_SPAWN_EGG.get());


                    }).build());

    public static final Supplier<CreativeModeTab>MONSTERS_AND_BLOCKS_TAB=CREATIVE_MODE_TAB.register("monsters_and_blocks_tab",
            ()-> CreativeModeTab.builder().icon(()->new ItemStack(ModBlocks.SCORCHED_METAL_BLOCK.get()))
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
                        output.accept(ModBlocks.DWARVEN_ALLOY_BLOCK);
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

                        output.accept(ModBlocks.VILESTONE);
                    }).build());
    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
