package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class FearMobEffect extends MagicMobEffect {
    public static final float CAST_TIME_PER_LEVEL =  -0.10F;

    public FearMobEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }
}



