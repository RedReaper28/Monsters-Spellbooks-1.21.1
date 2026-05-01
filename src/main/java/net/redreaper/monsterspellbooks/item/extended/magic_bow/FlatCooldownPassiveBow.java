package net.redreaper.monsterspellbooks.item.extended.magic_bow;

import net.minecraft.world.item.Tier;

public class FlatCooldownPassiveBow extends ExtendedBowItem{
    public FlatCooldownPassiveBow(Tier pTier, Properties properties) {
        super(pTier,properties);
    }

    protected int getPassiveCooldownTicks() {
        return 0;
    }


}