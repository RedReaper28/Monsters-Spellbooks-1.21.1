package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class BloodLustMobEffect extends MagicMobEffect {
    public static final float ATTACK_DAMAGE_PER_LEVEL = .2f;
    public static final float ATTACK_SPEED_PER_LEVEL = .15f;

    public BloodLustMobEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }
}