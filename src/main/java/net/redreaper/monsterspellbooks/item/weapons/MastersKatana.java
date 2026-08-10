package net.redreaper.monsterspellbooks.item.weapons;

import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import net.minecraft.world.item.Rarity;
import net.redreaper.monsterspellbooks.init.ModExtendedWeaponTiers;
import net.redreaper.monsterspellbooks.item.extended.magic_axe.ExtendedAxeItem;

public class MastersKatana extends ExtendedAxeItem {

    public MastersKatana() {
        super(
                ModExtendedWeaponTiers.MASTERS_KATANA,
                new Properties()
                        .stacksTo(1)
                        .rarity(Rarity.UNCOMMON)
                        .attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.MASTERS_KATANA)
                        )
        );
    }

}