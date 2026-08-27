package net.redreaper.monsterspellbooks.item.curios.elemental_charm;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.events.SpellOnCastEvent;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.compat.Curios;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.acetheeldritchking.aces_spell_utils.items.curios.FlatCooldownPassiveAbilityCurio;
import net.acetheeldritchking.aces_spell_utils.utils.ASUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.redreaper.monsterspellbooks.effect.StaticMobEffect;
import net.redreaper.monsterspellbooks.init.ModItems;
import net.redreaper.monsterspellbooks.utils.ModRarities;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

@EventBusSubscriber
public class DwarvenPowerCoreItem extends FlatCooldownPassiveAbilityCurio {
    public DwarvenPowerCoreItem() {
        super(ItemPropertiesHelper.equipment().stacksTo(1).fireResistant().rarity(ModRarities.DWARVEN_RARITY_PROXY.getValue()), Curios.NECKLACE_SLOT);
    }

    @SubscribeEvent
    public static void refundMana(SpellOnCastEvent event) {
        var spell = SpellRegistry.getSpell(event.getSpellId());
        var caster = event.getEntity();

        if (ASUtils.hasCurio(caster, ModItems.DWARVEN_POWER_CORE.get())) {
            if (spell.getSchoolType() == SchoolRegistry.LIGHTNING.get() && (!caster.getCooldowns().isOnCooldown(ModItems.DWARVEN_POWER_CORE.get()))) {
                StaticMobEffect.addStaticStack(caster, caster);
                caster.getCooldowns().addCooldown(ModItems.DWARVEN_POWER_CORE.get(), DwarvenPowerCoreItem.COOLDOWN);
            }
        }
    }

    public static final int COOLDOWN = 5*20;

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

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @NotNull TooltipContext context, @NotNull List<Component> lines, @NotNull TooltipFlag flag) {
        lines.add(Component.translatable("effect.monsterspellbooks.static.description").withStyle(ChatFormatting.AQUA));
    }

}
