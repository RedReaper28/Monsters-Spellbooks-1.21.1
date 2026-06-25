package net.redreaper.monsterspellbooks.item.armor.tierArch;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.entity.armor.GenericCustomArmorRenderer;
import io.redspace.ironsspellbooks.item.armor.ImbuableChestplateArmorItem;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.redreaper.monsterspellbooks.entity.armor.StarscourgeArmorModel;
import net.redreaper.monsterspellbooks.init.ModExtendedArmorMaterials;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class StarscourgeArmorItem extends ImbuableChestplateArmorItem {
    public StarscourgeArmorItem(Type slot, Properties settings) {
        super(ModExtendedArmorMaterials.STARSCOURGE_ARMOR, slot, settings,
                new AttributeContainer(AttributeRegistry.MAX_MANA, 125, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(AttributeRegistry.ENDER_SPELL_POWER, 0.20, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new GenericCustomArmorRenderer<>(new StarscourgeArmorModel());
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }
}