package net.redreaper.monsterspellbooks.item.weapons;

import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.redreaper.monsterspellbooks.init.ModExtendedWeaponTiers;
import net.redreaper.monsterspellbooks.procedures.effectsonhit.PoisonOnHit;


public class PoisonGlaive extends ExtendedSwordItem {
    public PoisonGlaive() {
        super(
                ModExtendedWeaponTiers.POISONED_SPEAR,
                ItemPropertiesHelper.equipment(1).fireResistant().rarity(Rarity.EPIC).attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.POISONED_SPEAR)));

    }
    public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
        boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
        PoisonOnHit.execute(entity);
        return retval;
    }
}
