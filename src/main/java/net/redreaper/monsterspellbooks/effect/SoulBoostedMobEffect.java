package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class SoulBoostedMobEffect extends MagicMobEffect {
    public static final float MANA_REGEN_PER_LEVEL =  0.25F;
    public static final float CASTING_SPEED_PER_LEVEL =  0.25F;
    public static final float COOLDOWN_REDUCTION_PER_LEVEL =  0.15F;
    public static final float NECRO_POWER_PER_LEVEL =  0.10F;

    public SoulBoostedMobEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }
}
