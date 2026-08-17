package net.redreaper.monsterspellbooks.item.shields.mithril_shield;

import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.item.extended.magic_shield.ExtendedShieldItem;

public class MithrilShieldItem extends ExtendedShieldItem {
    public MithrilShieldItem() {
        super(
                new Properties()
                        .stacksTo(1)
                        .fireResistant()
                        .durability(1541)
                        .rarity(Rarity.RARE)
        );
    }

    public void deflect(Projectile projectile, Entity deflector, int spellLevel) {
        if (deflector != null) {
            Vec3 vec3 = deflector.getLookAngle().normalize();
            if (deflector instanceof Player p) {
                vec3 = vec3.scale(0.15);
            }
            projectile.setOwner(deflector);
            projectile.setDeltaMovement(vec3);
            projectile.hasImpulse = true;
        }
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return repair.is(ItemRegistry.MITHRIL_SCRAP.get()) && super.isValidRepairItem(toRepair, repair);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, net.neoforged.neoforge.common.ItemAbility itemAbility) {
        return net.neoforged.neoforge.common.ItemAbilities.DEFAULT_SHIELD_ACTIONS.contains(itemAbility);
    }
}
