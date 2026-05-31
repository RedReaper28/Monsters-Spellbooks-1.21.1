package net.redreaper.monsterspellbooks.item.curios;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface KeybindCurio  {

    void onKeyPacket(Player player, ItemStack stack, int type);

}
