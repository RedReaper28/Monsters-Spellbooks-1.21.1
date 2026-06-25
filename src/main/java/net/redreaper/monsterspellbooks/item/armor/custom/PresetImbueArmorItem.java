package net.redreaper.monsterspellbooks.item.armor.custom;

import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.api.spells.SpellData;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.acetheeldritchking.aces_spell_utils.items.example.items.armor.ExtendedGeoArmorItem;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public abstract class PresetImbueArmorItem extends ModImbuableExtendedGeoArmorItem implements IPresetSpellContainer {

    List<SpellData> spellData = null;
    SpellDataRegistryHolder[] spellDataRegistryHolders;

    public PresetImbueArmorItem(Holder<ArmorMaterial> pMaterial, Type pType, Properties pProperties, SpellDataRegistryHolder[] spellDataRegistryHolders, AttributeContainer... attributes) {
        super(pMaterial, pType, pProperties, attributes);
        this.spellDataRegistryHolders = spellDataRegistryHolders;
    }

    public List<SpellData> getSpells() {
        if (spellData == null) {
            spellData = Arrays.stream(spellDataRegistryHolders).map(SpellDataRegistryHolder::getSpellData).toList();
            spellDataRegistryHolders = null;
        }
        return spellData;
    }


    @Override
    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack == null) {
            return;
        }
        Item var3 = itemStack.getItem();
        ArmorItem armorItem = (ArmorItem) var3;
        if (!ISpellContainer.isSpellContainer(itemStack)) {
            if (armorItem.getType() == Type.CHESTPLATE && !ISpellContainer.isSpellContainer(itemStack)) {
                var spells = getSpells();
                var spellContainer = ISpellContainer.create(spells.size(), true, false).mutableCopy();
                spells.forEach(spellData -> spellContainer.addSpell(spellData.getSpell(), spellData.getLevel(), true));
                ISpellContainer.set(itemStack, spellContainer.toImmutable());
            }
        }
    }
}
