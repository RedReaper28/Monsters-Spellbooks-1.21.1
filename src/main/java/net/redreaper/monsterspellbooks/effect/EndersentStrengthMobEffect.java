package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.ISyncedMobEffect;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class EndersentStrengthMobEffect extends MagicMobEffect implements ISyncedMobEffect {
    public static final float SCALE_PER_LEVEL = 1.5F;
    public static final float ENTITY_RANGE_PER_LEVEL = .50F;
    public static final float BLOCK_RANGE_PER_LEVEL = .50F;
    public static final float ATTACK_PER_LEVEL =  0.30f;
    public static final float ATTACK_SPEED_PER_LEVEL =  -0.30f;
    public static final float ATTACK_KNOCKBACK_PER_LEVEL = 1F;
    public static final float KNOCKBACK_RESISTANCE_PER_LEVEL = 5F;
    public static final float STEP_PER_LEVEL = 1f;

    public EndersentStrengthMobEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }
}
