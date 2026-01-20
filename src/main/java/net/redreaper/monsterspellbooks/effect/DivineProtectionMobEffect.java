package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class DivineProtectionMobEffect extends MagicMobEffect {
    public static final float EVASION_PER_LEVEL =  1.f;

    public DivineProtectionMobEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }
}
