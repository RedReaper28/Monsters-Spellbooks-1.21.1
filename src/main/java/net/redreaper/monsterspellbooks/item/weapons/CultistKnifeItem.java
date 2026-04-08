package net.redreaper.monsterspellbooks.item.weapons;

import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.acetheeldritchking.aces_spell_utils.utils.ASRarities;
import net.redreaper.monsterspellbooks.init.ModExtendedWeaponTiers;

public class CultistKnifeItem extends ExtendedSwordItem {
    public CultistKnifeItem() {
        super(
                ModExtendedWeaponTiers.CULTIST_KNIFE,
                ItemPropertiesHelper.equipment(1).fireResistant().rarity(ASRarities.FORBIDDEN_RARITY_PROXY.getValue()).attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.CULTIST_KNIFE))
        );
    }


}
