package net.redreaper.monsterspellbooks.item.weapons;

import io.redspace.ironsspellbooks.api.item.curios.AffinityData;
import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.effect.ImmolateEffect;
import io.redspace.ironsspellbooks.item.UniqueItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import io.redspace.ironsspellbooks.util.MinecraftInstanceHelper;
import net.acetheeldritchking.aces_spell_utils.utils.ASRarities;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.redreaper.monsterspellbooks.init.ModExtendedWeaponTiers;

import java.util.List;

public class Frostmourne extends MagicSwordItem implements UniqueItem {
    public Frostmourne() {
        super(
                ModExtendedWeaponTiers.FROST_TOUCHED_SUPERIOR_DEATHSILVER,
                ItemPropertiesHelper.equipment(1).fireResistant().rarity(ASRarities.GLACIAL_RARITY_PROXY.getValue()).attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.FROST_TOUCHED_SUPERIOR_DEATHSILVER)),
                SpellDataRegistryHolder.of());
    }

    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack == null) {
            return;
        }

        super.initializeSpellContainer(itemStack);
        AffinityData.setAffinityData(itemStack, SpellRegistry.RAISE_DEAD_SPELL.get(),1);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(
                Component.translatable(
                        "tooltip.monsterspellbooks.frostmourne_passive_ability"
                ).withStyle(ChatFormatting.DARK_AQUA)
        );
    }
}
