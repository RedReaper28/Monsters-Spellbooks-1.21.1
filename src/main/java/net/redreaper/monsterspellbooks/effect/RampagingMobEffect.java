package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class RampagingMobEffect extends MagicMobEffect {
    public static final float ATTACK_SPEED_PER_LEVEL =  0.10F;

    public RampagingMobEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }
}

