package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ManaRegenMobEffect extends MagicMobEffect {
    public static final float MANA_REGEN_PER_LEVEL =  0.15F;

    public ManaRegenMobEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }
}
