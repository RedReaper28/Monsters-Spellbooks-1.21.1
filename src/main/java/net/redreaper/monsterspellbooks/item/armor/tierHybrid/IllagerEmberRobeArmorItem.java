package net.redreaper.monsterspellbooks.item.armor.tierHybrid;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.entity.armor.GenericCustomArmorRenderer;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.acetheeldritchking.aces_spell_utils.items.example.items.armor.ImbuableExtendedGeoArmorItem;
import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.redreaper.monsterspellbooks.entity.armor.IllagerEmberRobeArmorModel;
import net.redreaper.monsterspellbooks.init.ModExtendedArmorMaterials;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class IllagerEmberRobeArmorItem extends ImbuableExtendedGeoArmorItem {
    public IllagerEmberRobeArmorItem(Type slot, Properties settings) {
        super(ModExtendedArmorMaterials.ENCHANTER, slot, settings,
                new AttributeContainer(AttributeRegistry.EVOCATION_SPELL_POWER,0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.FIRE_SPELL_POWER,0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(ASAttributeRegistry.MAGIC_DAMAGE_CRIT_CHANCE,0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.MAX_MANA, 125, AttributeModifier.Operation.ADD_VALUE)
        );
    }


    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new GenericCustomArmorRenderer<>(new IllagerEmberRobeArmorModel());
    }
}