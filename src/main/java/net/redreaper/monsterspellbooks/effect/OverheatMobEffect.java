package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.ISyncedMobEffect;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;


public class OverheatMobEffect extends MagicMobEffect implements ISyncedMobEffect {
    public static final float ATTACK_DAMAGE_PER_LEVEL = .1f;
    public static final float FIRE_SPELL_POWER_PER_LEVEL = .1f;

            public OverheatMobEffect(MobEffectCategory mobEffectCategory, int color) {
            super(mobEffectCategory, color);
            }
}
