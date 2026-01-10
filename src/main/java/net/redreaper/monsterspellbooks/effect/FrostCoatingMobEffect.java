package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.ISyncedMobEffect;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class FrostCoatingMobEffect extends MagicMobEffect implements ISyncedMobEffect {
    public static final float ARMOR_PER_LEVEL =  1f;
    public static final float SPELL_RESISTANCE_PER_LEVEL =  0.1f;

    public FrostCoatingMobEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    public void clientTick(LivingEntity livingEntity, MobEffectInstance instance) {
        int amplifier = instance.getAmplifier();
        ParticleOptions particle = ParticleHelper.SNOWFLAKE;
        if (amplifier >= 1) {
            particle = ParticleHelper.SNOWFLAKE;
        }
        var random = livingEntity.getRandom();
        for (int i = 0; i < 2; i++) {
            Vec3 motion = new Vec3(
                    random.nextFloat() * 2 - 1,
                    random.nextFloat() * 2 - 1,
                    random.nextFloat() * 2 - 1
            );
            motion = motion.scale(.04f);
            livingEntity.level().addParticle(particle, livingEntity.getRandomX(.4f), livingEntity.getRandomY(), livingEntity.getRandomZ(.4f), motion.x, motion.y, motion.z);
        }
    }

    public boolean shouldApplyEffectTickThisTick(int p_295368_, int p_294232_) {
        int i = 25 >> p_294232_;
        return i > 0 ? p_295368_ % i == 0 : true;
    }
}
