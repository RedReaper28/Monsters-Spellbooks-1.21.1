package net.redreaper.monsterspellbooks.item.curios.spellbooks;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.SpellBook;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.redreaper.monsterspellbooks.utils.ModRarities;

public class GuardianSpellBookItem extends SpellBook {
    public GuardianSpellBookItem() {
        super(10, ItemPropertiesHelper.equipment().fireResistant().stacksTo(1).rarity(ModRarities.PRISMARINE_RARITY_PROXY.getValue()));
        withSpellbookAttributes(new AttributeContainer(ASAttributeRegistry.HYDRO_MAGIC_POWER, 0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.MAX_MANA, 200, AttributeModifier.Operation.ADD_VALUE));
    }
}
