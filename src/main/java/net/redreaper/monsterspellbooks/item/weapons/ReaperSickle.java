package net.redreaper.monsterspellbooks.item.weapons;

import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.acetheeldritchking.aces_spell_utils.utils.ASRarities;
import net.redreaper.monsterspellbooks.init.ModExtendedWeaponTiers;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;

public class ReaperSickle extends MagicSwordItem {
    public ReaperSickle() {
        super(
                ModExtendedWeaponTiers.SPECTRITE,
                ItemPropertiesHelper.equipment(1).fireResistant().rarity(ASRarities.VERDANT_RARITY_PROXY.getValue()).attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.SPECTRITE)),
                SpellDataRegistryHolder.of(
                        new SpellDataRegistryHolder(ModSpellRegistry.VILE_SLASH, 6))
        );
    }
}

