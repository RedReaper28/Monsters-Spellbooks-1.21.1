package net.redreaper.monsterspellbooks.item.shields;

import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.redreaper.monsterspellbooks.item.extended.magic_shield.ExtendedShieldItem;

public class MithrilShieldItem extends ExtendedShieldItem {
    public MithrilShieldItem() {
        super(
                new Properties()
                        .stacksTo(1)
                        .fireResistant()
                        .durability(1541)
                        .rarity(Rarity.RARE)
        );
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return repair.is(ItemRegistry.MITHRIL_SCRAP.get()) && super.isValidRepairItem(toRepair, repair);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, net.neoforged.neoforge.common.ItemAbility itemAbility) {
        return net.neoforged.neoforge.common.ItemAbilities.DEFAULT_SHIELD_ACTIONS.contains(itemAbility);
    }
}
