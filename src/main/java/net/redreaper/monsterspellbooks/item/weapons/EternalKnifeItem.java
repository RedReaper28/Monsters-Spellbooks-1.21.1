package net.redreaper.monsterspellbooks.item.weapons;

import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.acetheeldritchking.aces_spell_utils.items.weapons.ActiveAndPassiveAbilityMagicSwordItem;
import net.acetheeldritchking.aces_spell_utils.utils.ASRarities;
import net.redreaper.monsterspellbooks.init.ModExtendedWeaponTiers;

public class EternalKnifeItem extends ActiveAndPassiveAbilityMagicSwordItem {
    public EternalKnifeItem() {
        super(
                ModExtendedWeaponTiers.CULTIST_KNIFE,
                ItemPropertiesHelper.equipment(1).fireResistant().rarity(ASRarities.FORBIDDEN_RARITY_PROXY.getValue()).attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.CULTIST_KNIFE)),
                SpellDataRegistryHolder.of(
                        new SpellDataRegistryHolder(SpellRegistry.SACRIFICE_SPELL, 10))
        );
    }

    @Override
    protected int getPassiveCooldownTicks() {
        return 10 * 20;
    }

    @Override
    protected int getActiveCooldownTicks() {
        return 10 * 20;
    }
}