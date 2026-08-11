package net.redreaper.monsterspellbooks.item.weapons;

import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import net.minecraft.world.item.Rarity;
import net.redreaper.monsterspellbooks.init.ModExtendedWeaponTiers;
import net.redreaper.monsterspellbooks.item.extended.magic_axe.ExtendedAxeItem;

public class GreatAxebladeItem extends ExtendedAxeItem {

    public GreatAxebladeItem() {
        super(
                ModExtendedWeaponTiers.GREAT_AXEBLADE,
                new Properties()
                        .stacksTo(1)
                        .fireResistant()
                        .rarity(Rarity.UNCOMMON)
                        .attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.GREAT_AXEBLADE)
                        )
        );
    }

}