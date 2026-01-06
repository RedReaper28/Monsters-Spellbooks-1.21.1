package net.redreaper.monsterspellbooks.item.armor;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.entity.armor.GenericCustomArmorRenderer;
import io.redspace.ironsspellbooks.item.armor.ImbuableChestplateArmorItem;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.redreaper.monsterspellbooks.entity.armor.StarscourgeArmorModel;
import net.redreaper.monsterspellbooks.init.ModExtendedArmorMaterials;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class ForgeMasterpieceArmorItem extends ImbuableChestplateArmorItem {
    public ForgeMasterpieceArmorItem(Type slot, Properties settings) {
        super(ModExtendedArmorMaterials.FORGE_MASTERPIECE_ARMOR, slot, settings,
                new AttributeContainer(AttributeRegistry.FIRE_MAGIC_RESIST, 12.5, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(ASAttributeRegistry.SPELL_RES_PENETRATION, 5, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.MAX_MANA, 50, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(AttributeRegistry.FIRE_SPELL_POWER, 0.20, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new GenericCustomArmorRenderer<>(new StarscourgeArmorModel());
    }
}