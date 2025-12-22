package net.redreaper.monsterspellbooks.item.armor;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.entity.armor.GenericCustomArmorRenderer;
import io.redspace.ironsspellbooks.item.armor.ExtendedArmorItem;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.redreaper.monsterspellbooks.entity.armor.MajorasMaskArmorModel;
import net.redreaper.monsterspellbooks.init.ModAtributeRegistry;
import net.redreaper.monsterspellbooks.init.ModExtendedArmorMaterials;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class MajorasMaskArmorItem extends ExtendedArmorItem implements IPresetSpellContainer {
    public MajorasMaskArmorItem(ArmorItem.Type slot, Properties settings) {
        super(ModExtendedArmorMaterials.MAJORAS_MASK, slot, settings,
                new AttributeContainer(AttributeRegistry.BLOOD_SPELL_POWER, 0.25, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(ModAtributeRegistry.NECRO_MAGIC_POWER, 0.25, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.ELDRITCH_SPELL_POWER, 0.5, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(ASAttributeRegistry.MANA_STEAL, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.MAX_MANA, -.25, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
        );
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new GenericCustomArmorRenderer<>(new MajorasMaskArmorModel());
    }

    @Override
    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack == null) {
            return;
        }

        if (!ISpellContainer.isSpellContainer(itemStack)) {
            var spellContainer = ISpellContainer.create(1, true, true);
            ISpellContainer.set(itemStack, spellContainer);
        }
    }
}