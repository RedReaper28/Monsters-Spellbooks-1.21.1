package net.redreaper.monsterspellbooks.item.weapons.magic_shield;

import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.item.weapons.IronsWeaponTier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class ExtendedShieldItem extends ShieldItem {
    public ExtendedShieldItem(Properties properties) {
        super(properties);
    }

    public static ItemAttributeModifiers createAttributes(IronsWeaponTier pTier)
    {
        var builder = ItemAttributeModifiers.builder()
                .add(
                        Attributes.ARMOR_TOUGHNESS,
                        new AttributeModifier(BASE_ATTACK_DAMAGE_ID, pTier.getAttackDamageBonus(), AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.OFFHAND
                )
                .add(
                        Attributes.ARMOR,
                        new AttributeModifier(BASE_ATTACK_SPEED_ID, pTier.getSpeed(), AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.OFFHAND
                );
        for (AttributeContainer holder : pTier.getAdditionalAttributes())
        {
            builder.add(holder.attribute(), holder.createModifier(EquipmentSlot.MAINHAND.getName()), EquipmentSlotGroup.OFFHAND);
        }
        return builder.build();
    }
}