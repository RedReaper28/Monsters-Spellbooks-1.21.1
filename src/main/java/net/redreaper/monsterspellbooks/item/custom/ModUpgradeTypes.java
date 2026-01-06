package net.redreaper.monsterspellbooks.item.custom;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.armor.UpgradeType;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.init.ModAtributeRegistry;
import net.redreaper.monsterspellbooks.init.ModItems;

import java.util.Optional;

public enum  ModUpgradeTypes implements UpgradeType {
    ELDRITCH_SPELL_POWER("eldritch_power", ModItems.NECRO_UPGRADE_ORB, ModAtributeRegistry.NECRO_MAGIC_POWER, AttributeModifier.Operation.ADD_MULTIPLIED_BASE, 0.05F);

    final Holder<Attribute> attribute;
    final AttributeModifier.Operation operation;
    final float amountPerUpgrade;
    final ResourceLocation id;
    final Optional<Holder<Item>> containerItem;

    ModUpgradeTypes(String key, Holder<Item> containerItem, Holder<Attribute> attribute, AttributeModifier.Operation operation, float amountPerUpgrade)
    {
        this(key, Optional.of(containerItem), attribute, operation, amountPerUpgrade);
    }

    ModUpgradeTypes(String key, Optional<Holder<Item>> containerItem, Holder<Attribute> attribute, AttributeModifier.Operation operation, float amountPerUpgrade)
    {
        this.id = MonstersSpellbooks.id(key);
        this.attribute = attribute;
        this.operation = operation;
        this.amountPerUpgrade = amountPerUpgrade;
        this.containerItem = containerItem;
        UpgradeType.registerUpgrade(this);
    }

    @Override
    public Holder<Attribute> getAttribute() {
        return attribute;
    }

    @Override
    public AttributeModifier.Operation getOperation() {
        return operation;
    }

    @Override
    public float getAmountPerUpgrade() {
        return amountPerUpgrade;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public Optional<Holder<Item>> getContainerItem() {
        return containerItem;
    }
}
