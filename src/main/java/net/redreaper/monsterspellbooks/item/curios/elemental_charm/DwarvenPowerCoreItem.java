package net.redreaper.monsterspellbooks.item.curios.elemental_charm;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.compat.Curios;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.acetheeldritchking.aces_spell_utils.items.curios.FlatCooldownPassiveAbilityCurio;
import net.acetheeldritchking.aces_spell_utils.items.curios.ImbueableCurioItem;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.redreaper.monsterspellbooks.utils.ModRarities;
import top.theillusivec4.curios.api.SlotContext;

public class DwarvenPowerCoreItem extends FlatCooldownPassiveAbilityCurio {
    public DwarvenPowerCoreItem() {
        super(ItemPropertiesHelper.equipment().stacksTo(1).fireResistant().rarity(ModRarities.DWARVEN_RARITY_PROXY.getValue()), Curios.NECKLACE_SLOT);
    }

    public static final int COOLDOWN = 2*20;

    @Override
    protected int getCooldownTicks() {
        return COOLDOWN;
    }

    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> attr = LinkedHashMultimap.create();
        attr.put(AttributeRegistry.LIGHTNING_SPELL_POWER, new AttributeModifier(id, 0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        attr.put(AttributeRegistry.COOLDOWN_REDUCTION, new AttributeModifier(id, 0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        attr.put(AttributeRegistry.CASTING_MOVESPEED, new AttributeModifier(id, 0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return attr;
    }
}
