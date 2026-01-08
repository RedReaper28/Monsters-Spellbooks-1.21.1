package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.ISyncedMobEffect;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class MountainCorpseMobEffect extends MagicMobEffect implements ISyncedMobEffect {
    public static final float ATTACK_DAMAGE_PER_LEVEL = .25f;
    public static final float ATTACK_SPEED_PER_LEVEL = -.25f;
    public static final float SCALE_PER_LEVEL = 1F;
    public static final float ENTITY_RANGE_PER_LEVEL = .50F;
    public static final float BLOCK_RANGE_PER_LEVEL = .50F;
    public static final float ATTACK_KNOCKBACK_PER_LEVEL = 1F;
    public static final float KNOCKBACK_RESISTANCE_PER_LEVEL = 1F;
    public static final float GRAVITY_PER_LEVEL =1.0F;
    public static final float NATURE_SPELL_POWER_PER_LEVEL = .10f;
    public static final float STEP_PER_LEVEL = 1f;


    public MountainCorpseMobEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }
}