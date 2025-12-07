package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.ISyncedMobEffect;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class OverheatMobEffect extends MagicMobEffect implements ISyncedMobEffect {
    public static final float ATTACK_DAMAGE_PER_LEVEL = .2f;
    public static final float FIRE_SPELL_POWER_PER_LEVEL = .15f;

            public OverheatMobEffect(MobEffectCategory mobEffectCategory, int color) {
            super(mobEffectCategory, color);
        }

    }

