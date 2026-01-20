package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class SoulRendMobEffect extends MagicMobEffect {
    public static final float MANA_SLOWNESS_PER_LEVEL =  -100;

    public SoulRendMobEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

}
