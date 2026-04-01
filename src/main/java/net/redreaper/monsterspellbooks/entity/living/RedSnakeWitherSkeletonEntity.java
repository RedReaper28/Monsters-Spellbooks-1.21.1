package net.redreaper.monsterspellbooks.entity.living;

import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.redreaper.monsterspellbooks.init.ModItems;

import javax.annotation.Nullable;

public class RedSnakeWitherSkeletonEntity extends WitherSkeleton {
    public RedSnakeWitherSkeletonEntity(EntityType<? extends WitherSkeleton> pEntityType, Level pLevel) {
        super(EntityType.WITHER_SKELETON, pLevel);
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance pDifficulty) {
        super.populateDefaultEquipmentSlots(random, pDifficulty);
        ItemStack magehunter = new ItemStack(ModItems.RED_SNAKE_BOW.get());

        var sharpness = Utils.enchantmentFromKey(level().registryAccess(), Enchantments.POWER);
        if (sharpness != null) {
            magehunter.enchant(sharpness, 1);
        }
        setItemSlot(EquipmentSlot.MAINHAND, magehunter);
    }

    @Override
    protected AbstractArrow getArrow(ItemStack arrow, float velocity, @Nullable ItemStack weapon) {
        AbstractArrow abstractarrow = super.getArrow(arrow, velocity, weapon);
        if (abstractarrow instanceof Arrow) {
            ((Arrow)abstractarrow).addEffect(new MobEffectInstance(MobEffectRegistry.IMMOLATE, 200));
        }

        return abstractarrow;
    }
}
