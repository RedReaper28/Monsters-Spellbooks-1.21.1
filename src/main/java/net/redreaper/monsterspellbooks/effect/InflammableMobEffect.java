package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class InflammableMobEffect extends MagicMobEffect {
    public static final float FIRE_WEAKNESS_PER_LEVEL =  -0.025F;
    public static final float BURN_TIME_PER_LEVEL =  0.05F;

    public InflammableMobEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

}
