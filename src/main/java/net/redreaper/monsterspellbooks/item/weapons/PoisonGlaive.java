package net.redreaper.monsterspellbooks.item.weapons;

import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.redreaper.monsterspellbooks.init.ModExtendedWeaponTiers;

import java.util.List;


public class PoisonGlaive extends ExtendedSwordItem {
    public PoisonGlaive() {
        super(
                ModExtendedWeaponTiers.POISONED_SPEAR,
                new Item
                        .Properties()
                        .stacksTo(1)
                        .rarity(Rarity.EPIC)
                        .fireResistant()
                        .attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.POISONED_SPEAR)
                        )
        );
    }

    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.irons_spellbooks.passive_ability_no_cooldown").withStyle(ChatFormatting.DARK_PURPLE));
            tooltipComponents.add(Component.literal(" ").append(Component.translatable(this.getDescriptionId() + ".desc")).withStyle(ChatFormatting.DARK_GREEN));
        }
        else {
            tooltipComponents.add(Component.translatable("item.aces_spell_utils.more_details1").withStyle(ChatFormatting.GRAY));
        }
    }

}
