package net.redreaper.monsterspellbooks.item.weapons.magic_bow;

import io.redspace.ironsspellbooks.api.util.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

import java.util.List;

public class FlatCooldownPassiveBow extends ExtendedBowItem{
    public FlatCooldownPassiveBow(Tier pTier, Properties properties) {
        super(pTier,properties);
    }

    protected int getPassiveCooldownTicks() {
        return 0;
    }


}