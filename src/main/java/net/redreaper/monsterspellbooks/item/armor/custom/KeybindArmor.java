package net.redreaper.monsterspellbooks.item.armor.custom;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface KeybindArmor {
    void onKeyPacket(Player keyPresser, ItemStack itemStack, int type);
}
