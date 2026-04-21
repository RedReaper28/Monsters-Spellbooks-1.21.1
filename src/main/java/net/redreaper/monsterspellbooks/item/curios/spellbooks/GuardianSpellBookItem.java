package net.redreaper.monsterspellbooks.item.curios.spellbooks;

import io.redspace.ironsspellbooks.api.item.curios.AffinityData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.SpellBook;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import io.redspace.ironsspellbooks.util.TooltipsUtils;
import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.acetheeldritchking.aces_spell_utils.utils.ASRarities;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import net.redreaper.monsterspellbooks.utils.ModRarities;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class GuardianSpellBookItem extends SpellBook {
    public GuardianSpellBookItem() {
        super(10, ItemPropertiesHelper.equipment().fireResistant().stacksTo(1).rarity(ModRarities.PRISMARINE_RARITY_PROXY.getValue()));
        withSpellbookAttributes(new AttributeContainer(AttributeRegistry.MAX_MANA, 200, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(ASAttributeRegistry.HYDRO_MAGIC_POWER, 0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }
}
