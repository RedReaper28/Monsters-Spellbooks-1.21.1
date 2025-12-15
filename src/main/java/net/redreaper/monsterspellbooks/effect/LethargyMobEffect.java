package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class LethargyMobEffect extends MagicMobEffect {
    public static final float LIFE_DRAIN_PER_LEVEL =  -0.25F;

    public LethargyMobEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }
}
