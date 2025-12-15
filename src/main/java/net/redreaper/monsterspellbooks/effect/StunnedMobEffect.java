package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class StunnedMobEffect extends MagicMobEffect {
    public static final float ATTACK_SLOWNESS_PER_LEVEL =  -0.2F;

    public StunnedMobEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }
}
