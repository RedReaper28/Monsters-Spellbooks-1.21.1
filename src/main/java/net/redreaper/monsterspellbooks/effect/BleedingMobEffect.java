package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.NeoForgeMod;

public class BleedingMobEffect extends MagicMobEffect {
    public static final float DAMAGE_PER_LEVEL = .5f;

    public BleedingMobEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }


    public boolean applyEffectTick(LivingEntity p_296276_, int p_296233_) {
        if (p_296276_.getHealth() > 1.0F) {
            Registry<DamageType> dTypeReg = p_296276_.damageSources().damageTypes;
            Holder.Reference<DamageType> dType = dTypeReg.getHolder(NeoForgeMod.POISON_DAMAGE).orElse(dTypeReg.getHolderOrThrow(ISSDamageTypes.BLOOD_MAGIC));
            p_296276_.hurt(new DamageSource(dType), 1+DAMAGE_PER_LEVEL);
        }
        return true;
    }

    public boolean shouldApplyEffectTickThisTick(int p_295368_, int p_294232_) {
        int i = 25 >> p_294232_;
        return i > 0 ? p_295368_ % i == 0 : true;
    }

}
