package net.redreaper.monsterspellbooks.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CursedMobEffect extends MobEffect {
    public CursedMobEffect() {
        super(MobEffectCategory.HARMFUL, 1315355);
    }

    public List<ItemStack> getCurativeItems() {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        return ret;
    }
}
