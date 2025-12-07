package net.redreaper.monsterspellbooks.item.weapons;

import io.redspace.ironsspellbooks.api.item.curios.AffinityData;
import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.item.UniqueItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.redreaper.monsterspellbooks.init.ModExtendedWeaponTiers;

public class DuchessSewingNeedle extends MagicSwordItem implements UniqueItem {
    public DuchessSewingNeedle() {
        super(
                ModExtendedWeaponTiers.RITUAL_BONE,
                ItemPropertiesHelper.equipment(1).fireResistant().rarity(Rarity.RARE).attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.RITUAL_BONE)),
                SpellDataRegistryHolder.of(
                        new SpellDataRegistryHolder(SpellRegistry.BLOOD_NEEDLES_SPELL, 10))
        );
    }

    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack == null) {
            return;
        }

        super.initializeSpellContainer(itemStack);
        AffinityData.setAffinityData(itemStack, SpellRegistry.BLOOD_NEEDLES_SPELL.get());
    }
}