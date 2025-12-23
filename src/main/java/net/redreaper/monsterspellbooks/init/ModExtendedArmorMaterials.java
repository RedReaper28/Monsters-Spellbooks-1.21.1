package net.redreaper.monsterspellbooks.init;

import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModExtendedArmorMaterials {
    private static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, MonstersSpellbooks.MOD_ID);


    public static DeferredHolder<ArmorMaterial, ArmorMaterial> FIERCE_DEITY = register("fierce_deity",
            makeArmorMap(2, 0, 0, 0),
            15,
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.of(ItemRegistry.DIVINE_PEARL.get()),
            0,
            0);

    public static DeferredHolder<ArmorMaterial, ArmorMaterial> MAJORAS_MASK = register("majoras_mask",
            makeArmorMap(2, 0, 0, 0),
            15,
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.of(ModTags.Items.NECRO_FOCUS),
            0,
            0);

    public static DeferredHolder<ArmorMaterial,ArmorMaterial>STARSCOURGE_ARMOR=register("starscourge_armor",
            schoolUpgradedArmorMap(),
            25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(ModItems.VOID_OBSIDIAN_INGOT.get()),
            3,
            0.2F);

    public static DeferredHolder<ArmorMaterial,ArmorMaterial>SANGUINITE_ARMOR=register("sanguinite_armor",
            schoolUpgradedArmorMap(),
            20,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(ModItems.SANGUINITE_INGOT.get()),
            3,
            0.1F);

    private static DeferredHolder<ArmorMaterial, ArmorMaterial> register(
            String name,
            EnumMap<ArmorItem.Type, Integer> defense,
            int enchantmentValue,
            Holder<SoundEvent> equipSound,
            Supplier<Ingredient> repairIngredient,
            float toughness,
            float knockbackResistance
    )
    {
        List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(MonstersSpellbooks.id(name)));
        return ARMOR_MATERIALS.register(name, () -> new ArmorMaterial(defense, enchantmentValue, equipSound, repairIngredient, list, toughness, knockbackResistance));
    }

    public static EnumMap<ArmorItem.Type, Integer> makeArmorMap(int helmet, int chestplate, int leggings, int boots)
    {
        return Util.make(new EnumMap<>(ArmorItem.Type.class), (typeIntegerEnumMap) -> {
            typeIntegerEnumMap.put(ArmorItem.Type.HELMET, helmet);
            typeIntegerEnumMap.put(ArmorItem.Type.CHESTPLATE, chestplate);
            typeIntegerEnumMap.put(ArmorItem.Type.LEGGINGS, leggings);
            typeIntegerEnumMap.put(ArmorItem.Type.BOOTS, boots);
        });
    }
    static public EnumMap<ArmorItem.Type, Integer> schoolArmorMap() {
        return makeArmorMap(3, 8, 6, 3);
    }

    static public EnumMap<ArmorItem.Type, Integer> schoolHybridArmorMap() {
        return makeArmorMap(3, 8, 6, 3);
    }

    static public EnumMap<ArmorItem.Type, Integer> schoolUpgradedArmorMap() {
        return makeArmorMap(4, 9, 5, 5);
    }

    public static void register(IEventBus eventBus)
    {
        ARMOR_MATERIALS.register(eventBus);
    }
}
