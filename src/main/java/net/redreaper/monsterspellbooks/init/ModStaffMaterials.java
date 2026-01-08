package net.redreaper.monsterspellbooks.init;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.item.weapons.IronsWeaponTier;
import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class ModStaffMaterials implements IronsWeaponTier {
    public static ModStaffMaterials VILENOVA;
    float damage;
    float speed;
    AttributeContainer[] attributeContainers;

    public ModStaffMaterials(float damage, float speed, AttributeContainer... attributeContainers) {
        this.damage = damage;
        this.speed = speed;
        this.attributeContainers = attributeContainers;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public float getSpeed() {
        return this.speed;
    }

    public AttributeContainer[] getAdditionalAttributes() {
        return this.attributeContainers;
    }

    static {
        VILENOVA = new ModStaffMaterials(3.0F, -3.0F, new AttributeContainer[]{
                new AttributeContainer(ASAttributeRegistry.MANA_STEAL, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(ModAtributeRegistry.NECRO_MAGIC_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.SPELL_POWER, 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)});
    }
}
