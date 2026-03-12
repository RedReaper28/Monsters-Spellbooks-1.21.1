package net.redreaper.monsterspellbooks.item.weapons;

import io.redspace.ironsspellbooks.api.item.curios.AffinityData;
import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.entity.spells.poison_cloud.PoisonSplash;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.redreaper.monsterspellbooks.init.ModExtendedWeaponTiers;
import net.redreaper.monsterspellbooks.procedures.effectsonhit.PoisonOnHit;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class PoisonGlaive extends ExtendedSwordItem {
    public PoisonGlaive() {
        super(
                ModExtendedWeaponTiers.POISONED_SPEAR,
                ItemPropertiesHelper.equipment(1).fireResistant().rarity(Rarity.EPIC).attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.POISONED_SPEAR)));

    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, TooltipContext context, @NotNull List<Component> lines, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemStack, context, lines, flag);
        var affinityData = AffinityData.getAffinityData(itemStack);
        lines.add(Component.translatable("tooltip.monsterspellbooks.poison_glaive").withStyle(new ChatFormatting[]{ChatFormatting.GREEN}));
    }

    public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceentity) {
        boolean retval = super.hurtEnemy(stack, entity, sourceentity);
        PoisonOnHit.execute(entity);
        if (entity.isDeadOrDying()) {
            PoisonSplash cloud = new PoisonSplash(sourceentity.level());
            cloud.setOwner(sourceentity);
            cloud.setDuration(200);
            cloud.setPos(entity.position());
            cloud.setDamage(10);
            sourceentity.level().addFreshEntity(cloud);
        }
        return retval;
    }
}
