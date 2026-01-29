package net.redreaper.monsterspellbooks.item.weapons.magic_axe;

import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class ImbueableAxeItem extends ExtendedAxeItem implements IPresetSpellContainer {
    public ImbueableAxeItem(Tier pTier, Properties properties) {
        super(pTier,properties);
    }

    @Override
    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack == null)
        {
            return;
        }

        if (itemStack.getItem() instanceof ExtendedAxeItem axeItem)
        {
            if (!ISpellContainer.isSpellContainer(itemStack))
            {
                var spellContainer = ISpellContainer.create(1, true, false);
                ISpellContainer.set(itemStack, spellContainer);
            }
        }
    }
}