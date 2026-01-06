package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class HeavyBodyMobEffect extends MagicMobEffect {
    public static final float GRAVITY_PER_LEVEL =  0.05F;

    public HeavyBodyMobEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }
}
