package net.redreaper.monsterspellbooks.item.curios.spellbooks;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.SpellBook;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.acetheeldritchking.aces_spell_utils.utils.ASRarities;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.redreaper.monsterspellbooks.init.ModAtributeRegistry;

public class BookOfSoulsItem extends SpellBook {
    public BookOfSoulsItem() {
        super(10, ItemPropertiesHelper.equipment().fireResistant().stacksTo(1).rarity(ASRarities.VERDANT_RARITY_PROXY.getValue()));
        withSpellbookAttributes(new AttributeContainer(AttributeRegistry.MAX_MANA, 200, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(ModAtributeRegistry.NECRO_MAGIC_POWER, 0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }
}
