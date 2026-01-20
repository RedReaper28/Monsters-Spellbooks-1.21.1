package net.redreaper.monsterspellbooks.item.staves.brimstone_orochi;

import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.acetheeldritchking.aces_spell_utils.items.example.ASWeaponTiers;
import net.acetheeldritchking.aces_spell_utils.items.weapons.MagicGunItem;
import net.acetheeldritchking.aces_spell_utils.utils.ASRarities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.redreaper.monsterspellbooks.init.ModDispatcher;
import net.redreaper.monsterspellbooks.init.ModStaffMaterials;

public class BrimstoneOrochiItem extends MagicGunItem {
    public final ModDispatcher dispatcher = new ModDispatcher();
    public BrimstoneOrochiItem() {
        super(
                ASWeaponTiers.EXAMPLE_GUN,
                ItemPropertiesHelper.equipment(1).fireResistant().rarity(ASRarities.ARID_RARITY_PROXY.getValue()).attributes(ExtendedSwordItem.createAttributes(ModStaffMaterials.BRIMSTONE_OROCHI)),
                SpellDataRegistryHolder.of(
                )
        );
    }

    @Override
    public boolean isHeavyGun() {
        return true;
    }

    @Override
    protected int getPassiveCooldownTicks() {
        return 10 * 20;
    }

    @Override
    protected int getActiveCooldownTicks() {
        return 10 * 20;
    }

    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (!level.isClientSide && entity instanceof Player player) {
            this.dispatcher.idle(player, stack);
        }

    }
}
