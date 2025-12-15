package net.redreaper.monsterspellbooks.init;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.item.weapons.IronsWeaponTier;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;
public class ModExtendedWeaponTiers implements Tier, IronsWeaponTier {

    public static ModExtendedWeaponTiers FORBIDDEN_TOOL = new ModExtendedWeaponTiers(1000, 9F, -2.8F, 15, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, () -> Ingredient.of(ItemRegistry.MITHRIL_INGOT.get()),
            new AttributeContainer(AttributeRegistry.BLOOD_SPELL_POWER, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(AttributeRegistry.FIRE_MAGIC_RESIST, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(AttributeRegistry.LIGHTNING_SPELL_POWER, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    public static ModExtendedWeaponTiers VOID_OBSIDIAN = new ModExtendedWeaponTiers(2000, 14F, -2.8F, 25, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, () -> Ingredient.of(ModItems.VOID_OBSIDIAN_INGOT.get()),
            new AttributeContainer(AttributeRegistry.ENDER_SPELL_POWER, 0.20, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(Attributes.ATTACK_KNOCKBACK, 0.20, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    public static ModExtendedWeaponTiers HEAVY_VOID_OBSIDIAN = new ModExtendedWeaponTiers(2000, 9, -3.5f, 25, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, () -> Ingredient.of(ModItems.VOID_OBSIDIAN_INGOT.get()),
            new AttributeContainer(AttributeRegistry.ENDER_SPELL_POWER, 0.20, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(Attributes.ATTACK_KNOCKBACK, 0.20, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(Attributes.KNOCKBACK_RESISTANCE, 0.25, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    public static ModExtendedWeaponTiers HALLOWED_STEEL = new ModExtendedWeaponTiers(2000, 11F, -3.0F, 25, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, () -> Ingredient.of(ItemRegistry.DIVINE_PEARL.get()),
            new AttributeContainer(AttributeRegistry.HOLY_SPELL_POWER, 0.20, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(AttributeRegistry.BLOOD_MAGIC_RESIST, 0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(ModAtributeRegistry.NECRO_MAGIC_RESIST, 0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(Attributes.ATTACK_DAMAGE, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    public static ModExtendedWeaponTiers RITUAL_BONE = new ModExtendedWeaponTiers(1000, 9F, -2.8F, 15, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, () -> Ingredient.of(ItemRegistry.BLOOD_VIAL.get()),
            new AttributeContainer(AttributeRegistry.BLOOD_SPELL_POWER, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(AttributeRegistry.SUMMON_DAMAGE, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    public static ModExtendedWeaponTiers BLOOD_RELIC_IRON = new ModExtendedWeaponTiers(1000, 11F, -2.8F, 15, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, () -> Ingredient.of(ItemRegistry.BLOOD_VIAL.get()),
            new AttributeContainer(AttributeRegistry.BLOOD_SPELL_POWER, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(ASAttributeRegistry.MANA_REND, 0.250, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    public static ModExtendedWeaponTiers SUPERIOR_DEATHSILVER = new ModExtendedWeaponTiers(2000, 11F, -2.8F, 15, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, () -> Ingredient.of(ModItems.DEATHSILVER_INGOT.get()),
            new AttributeContainer(AttributeRegistry.ICE_SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(ModAtributeRegistry.NECRO_MAGIC_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(ASAttributeRegistry.MANA_STEAL, 0.10D, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    public static ModExtendedWeaponTiers SPECTRITE = new ModExtendedWeaponTiers(2000, 11F, -2.8F, 15, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, () -> Ingredient.of(ModItems.SPECTRITE_INGOT.get()),
            new AttributeContainer(ModAtributeRegistry.NECRO_MAGIC_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(ASAttributeRegistry.MANA_REND, 0.250, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    public static ModExtendedWeaponTiers SANGUINITE = new ModExtendedWeaponTiers(1200, 9F, -2.8F, 15, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, () -> Ingredient.of(ModItems.SANGUINITE_INGOT.get()),
            new AttributeContainer(AttributeRegistry.BLOOD_SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(ASAttributeRegistry.GOLIATH_SLAYER, 0.01, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));


    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final TagKey<Block> incorrectBlocksForDrops;
    private final Supplier<Ingredient> repairIngredient;
    private final AttributeContainer[] attributeContainers;

    private ModExtendedWeaponTiers(int uses, float damage, float speed, int enchantmentValue, TagKey<Block> incorrectBlocksForDrops, Supplier<Ingredient> repairIngredient, AttributeContainer... attributes) {
        //this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.incorrectBlocksForDrops = incorrectBlocksForDrops;
        this.repairIngredient = repairIngredient;
        this.attributeContainers = attributes;
    }

    @Override
    public AttributeContainer[] getAdditionalAttributes() {
        return this.attributeContainers;
    }

    @Override
    public int getUses() {
        return uses;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return damage;
    }

    @Override
    public @NotNull TagKey<Block> getIncorrectBlocksForDrops() {
        return incorrectBlocksForDrops;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

}
