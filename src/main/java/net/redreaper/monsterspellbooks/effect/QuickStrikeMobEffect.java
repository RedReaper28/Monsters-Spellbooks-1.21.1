package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class QuickStrikeMobEffect extends MagicMobEffect {
    public static final float ATTACK_SPEED_PER_LEVEL =  0.50F;

    public QuickStrikeMobEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }
}

