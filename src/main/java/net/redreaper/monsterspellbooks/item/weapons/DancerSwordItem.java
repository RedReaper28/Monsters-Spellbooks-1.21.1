package net.redreaper.monsterspellbooks.item.weapons;


import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.redreaper.monsterspellbooks.init.ModExtendedWeaponTiers;

import java.util.List;


public class DancerSwordItem extends ExtendedSwordItem {
    public static final int COOLDOWN = 10 * 20;
    public DancerSwordItem() {

        super(
                ModExtendedWeaponTiers.DANCERS_SWORD,
                ItemPropertiesHelper.equipment(1).fireResistant().rarity(Rarity.UNCOMMON).attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.DANCERS_SWORD))
        );
    }

    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.irons_spellbooks.passive_ability", new Object[]{Component.literal(Utils.timeFromTicks((float)this.getPassiveCooldownTicks(), 1)).withStyle(ChatFormatting.LIGHT_PURPLE)}).withStyle(ChatFormatting.DARK_PURPLE));
            tooltipComponents.add(Component.literal(" ").append(Component.translatable(this.getDescriptionId() + ".desc")).withStyle(Style.EMPTY.withColor(3461535)));
        }
        else {
            tooltipComponents.add(Component.translatable("item.aces_spell_utils.more_details1").withStyle(ChatFormatting.GRAY));
        }

    }

    protected int getPassiveCooldownTicks() {
        return COOLDOWN;
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }
}
