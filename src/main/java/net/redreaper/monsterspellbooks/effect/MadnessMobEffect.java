package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class MadnessMobEffect extends MagicMobEffect {
    public static final float MANA_REDUCTION_PER_LEVEL = -0.08f;

    public MadnessMobEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

}
