package net.redreaper.monsterspellbooks.item.curios.spellbooks;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.SpellBook;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.redreaper.monsterspellbooks.init.ModAtributeRegistry;
import net.redreaper.monsterspellbooks.utils.ModRarities;

public class UpdraftTomeItem extends SpellBook {
    public UpdraftTomeItem() {
        super(10, ItemPropertiesHelper.equipment().stacksTo(1).rarity(ModRarities.WIND_RARITY_PROXY.getValue()));
        withSpellbookAttributes(new AttributeContainer(AttributeRegistry.MAX_MANA, 200, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(ModAtributeRegistry.AERO_MAGIC_POWER, 0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }
}
