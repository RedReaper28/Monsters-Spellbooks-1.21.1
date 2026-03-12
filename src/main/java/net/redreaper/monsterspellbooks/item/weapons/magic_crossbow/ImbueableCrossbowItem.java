package net.redreaper.monsterspellbooks.item.weapons.magic_crossbow;

import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class ImbueableCrossbowItem extends ExtendedCrossbow implements IPresetSpellContainer {
    public ImbueableCrossbowItem(Tier pTier, Properties properties) {
        super(pTier,properties);
    }

    @Override
    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack == null)
        {
            return;
        }

        if (itemStack.getItem() instanceof ExtendedCrossbow axeItem)
        {
            if (!ISpellContainer.isSpellContainer(itemStack))
            {
                var spellContainer = ISpellContainer.create(1, true, false);
                ISpellContainer.set(itemStack, spellContainer);
            }
        }
    }
}