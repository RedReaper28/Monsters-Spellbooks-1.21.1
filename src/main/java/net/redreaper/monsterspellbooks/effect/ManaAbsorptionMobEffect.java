package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ManaAbsorptionMobEffect extends MagicMobEffect {
    public static final float MANA_STEAL_PER_LEVEL =  0.10F;

    public ManaAbsorptionMobEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }
}
