package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import io.redspace.ironsspellbooks.effect.ISyncedMobEffect;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForgeMod;

public class BleedingMobEffect extends MagicMobEffect implements ISyncedMobEffect {
    public static final float DAMAGE_PER_LEVEL = .5f;

    public BleedingMobEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }


    public boolean applyEffectTick(LivingEntity p_296276_, int p_296233_) {
            Registry<DamageType> dTypeReg = p_296276_.damageSources().damageTypes;
            Holder.Reference<DamageType> dType = dTypeReg.getHolder(NeoForgeMod.POISON_DAMAGE).orElse(dTypeReg.getHolderOrThrow(ISSDamageTypes.BLOOD_MAGIC));
            p_296276_.hurt(new DamageSource(dType), 1);
        return true;
    }

    public void clientTick(LivingEntity livingEntity, MobEffectInstance instance) {
        int amplifier = instance.getAmplifier();
        ParticleOptions particle = ParticleHelper.BLOOD;
        if (amplifier >= 1) {
            particle = ParticleHelper.BLOOD;
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
