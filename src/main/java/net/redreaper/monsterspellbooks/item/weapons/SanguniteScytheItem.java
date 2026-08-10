package net.redreaper.monsterspellbooks.item.weapons;

import io.redspace.ironsspellbooks.api.item.curios.AffinityData;
import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.util.MinecraftInstanceHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.redreaper.monsterspellbooks.effect.HemorrhageMobEffect;
import net.redreaper.monsterspellbooks.init.ModExtendedWeaponTiers;
import net.redreaper.monsterspellbooks.utils.ModRarities;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SanguniteScytheItem extends ExtendedSwordItem {

    public SanguniteScytheItem() {
        super(
                ModExtendedWeaponTiers.SANGUINITE_SCYTHE,
                new Properties()
                        .stacksTo(1)
                        .rarity(ModRarities.SANGUINITE_RARITY_PROXY.getValue())
                        .attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.SANGUINITE_SCYTHE)
                        )
        );
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @NotNull TooltipContext context, @NotNull List<Component> lines, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemStack, context, lines, flag);
        var affinityData = AffinityData.getAffinityData(itemStack);
        lines.add(Component.translatable("tooltip.monsterspellbooks.sanguinite").withStyle(Style.EMPTY.withColor(16722350)));
        lines.add(Component.translatable("effect.monsterspellbooks.hemorrhage.description",
                Component.literal(Utils.stringTruncation(HemorrhageMobEffect.damageFor(MinecraftInstanceHelper.getPlayer()), 1)).withStyle(ChatFormatting.RED)
        ).withStyle(Style.EMPTY.withColor(16722350)));
    }

}
