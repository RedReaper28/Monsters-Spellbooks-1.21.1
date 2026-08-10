package net.redreaper.monsterspellbooks.item.weapons;

import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import net.acetheeldritchking.aces_spell_utils.utils.ASRarities;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.redreaper.monsterspellbooks.init.ModExtendedWeaponTiers;
import net.redreaper.monsterspellbooks.item.extended.magic_bow.ImbueableBowItem;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RedSnakeBowItem extends ImbueableBowItem {
    public static final int COOLDOWN = 15 * 20;

    public RedSnakeBowItem() {
        super(
                new Item
                        .Properties()
                        .stacksTo(1)
                        .durability(451)
                        .rarity(ASRarities.ARID_RARITY_PROXY.getValue())
                        .fireResistant()
                        .attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.RED_SNAKE)
                        )
        );
    }


    public int getEnchantmentValue() {
        return 10;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, TooltipContext context, @NotNull List<Component> lines, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemStack, context, lines, flag);
        lines.add(Component.translatable("tooltip.monsterspellbooks.red_snake").withStyle(new ChatFormatting[]{ChatFormatting.DARK_RED}));
    }
}