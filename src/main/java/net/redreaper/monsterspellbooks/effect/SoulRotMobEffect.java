package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.ISyncedMobEffect;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
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
import net.redreaper.monsterspellbooks.init.ModDamageTypes;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;

public class SoulRotMobEffect extends MagicMobEffect implements ISyncedMobEffect {

    public SoulRotMobEffect() {
        super(MobEffectCategory.HARMFUL, 6354426);
    }


    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        int i = pAmplifier + 1;
        Registry<DamageType> dTypeReg = pLivingEntity.damageSources().damageTypes;
        Holder.Reference<DamageType> dType = dTypeReg.getHolder(NeoForgeMod.POISON_DAMAGE).orElse(dTypeReg.getHolderOrThrow(ModDamageTypes.NECRO_MAGIC));
        pLivingEntity.hurt(new DamageSource(dType), i);
        return true;
    }

    public void clientTick(LivingEntity livingEntity, MobEffectInstance instance) {
        ParticleOptions particle = ModParticleHelper.REAPER_FIRE;
        var random = livingEntity.getRandom();
        for (int i = 0; i < 2; i++) {
            Vec3 motion = new Vec3(
                    random.nextFloat() * 2 - 1,
                    random.nextFloat() * 2 - 1,
                    random.nextFloat() * 2 - 1
            );
            motion = motion.scale(.04f);
            livingEntity.level().addParticle(particle, livingEntity.getRandomX(.4f), livingEntity.getRandomY(), livingEntity.getRandomZ(.2f), motion.x, motion.y, motion.z);
        }
    }

    public boolean shouldApplyEffectTickThisTick(int p_295368_, int p_294232_) {
        int i = 25 >> p_294232_;
        return i > 0 ? p_295368_ % i == 0 : true;
    }
}