package net.redreaper.monsterspellbooks.item.weapons;

import io.redspace.ironsspellbooks.api.item.curios.AffinityData;
import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.util.TooltipsUtils;
import net.acetheeldritchking.aces_spell_utils.utils.ASRarities;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.redreaper.monsterspellbooks.init.ModExtendedWeaponTiers;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DeathsilverSwordItem extends ExtendedSwordItem {

    public DeathsilverSwordItem() {
        super(
                ModExtendedWeaponTiers.DEATHSILVER_SWORD,
                new Properties()
                        .stacksTo(1)
                        .rarity(ASRarities.GLACIAL_RARITY_PROXY.getValue())
                        .attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.DEATHSILVER_SWORD)
                        )
        );
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @NotNull TooltipContext context, @NotNull List<Component> lines, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemStack, context, lines, flag);
        var affinityData = AffinityData.getAffinityData(itemStack);
        lines.add(Component.translatable("tooltip.monsterspellbooks.deathsilver").withStyle(new ChatFormatting[]{ChatFormatting.GRAY}));
    }

}
