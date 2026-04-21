package net.redreaper.monsterspellbooks.entity.spells.wind_charge;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.windcharge.WindCharge;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SimpleExplosionDamageCalculator;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Function;

public class ExtendedWindCharge extends WindCharge implements AntiMagicSusceptible {
    protected float damage;
    protected float radius;

    public ExtendedWindCharge(EntityType<? extends WindCharge> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ExtendedWindCharge(Level levelIn, LivingEntity shooter) {
        this(ModEntities.EXTENDED_WIND_CHARGE.get(), levelIn);
        setOwner(shooter);
    }

    public void trailParticles() {}

    public void shoot(Vec3 rotation) {setDeltaMovement(rotation.scale(getSpeed()));}


    public void impactParticles(double x, double y, double z) {}

    public float getSpeed() {return 1.5f;}

    public void setRadius(float radius) {
        this.radius = radius;
    }


    public float getRadius() {
        return radius;
    }


    public void setDamage(float damage) {
        this.damage = damage;
    }


    public float getDamage() {
        return damage;
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult result) {
        super.onHitBlock(result);
        if (!this.level().isClientSide) {
            Vec3i vec3i = result.getDirection().getNormal();
            Vec3 vec3 = Vec3.atLowerCornerOf(vec3i).multiply(0.25, 0.25, 0.25);
            Vec3 vec31 = result.getLocation().add(vec3);
            this.explode(vec31);
            this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        //copied from AbstractWindCharge
        super.onHitEntity(pResult);
        if (!this.level().isClientSide) {
            Entity var4 = this.getOwner();
            LivingEntity var10000;
            if (var4 instanceof LivingEntity livingentity) {

                var10000 = (LivingEntity)var4;
            } else {
                var10000 = null;
            }

            LivingEntity livingentity = var10000;
            Entity entity = pResult.getEntity();
            if (livingentity != null) {
                livingentity.setLastHurtMob(entity);
                if (livingentity instanceof Player p) {
                    p.setIgnoreFallDamageFromCurrentImpulse(true);
                }
            }
            DamageSources.applyDamage(livingentity, getDamage(), ModSpellRegistry.WIND_CHARGE.get().getDamageSource(this, getOwner()));
            DamageSource damagesource = this.damageSources().windCharge(this, livingentity);
            if (entity.hurt(damagesource, 1.0F) && entity instanceof LivingEntity livingentity2) {
                livingentity.addEffect(new MobEffectInstance(MobEffectRegistry.AIRBORNE, 60, 3));
                EnchantmentHelper.doPostAttackEffects((ServerLevel)this.level(), livingentity2, damagesource);
            }

            this.explode(this.position());
        }
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            this.discard();
        }
    }

    protected void explode(Vec3 pos) {
        this.level().explode(
                this,
                null,
                getCalculator(1.22f*this.getDamage()),
                pos.x(), pos.y(), pos.z(),
                this.getRadius(),
                false,
                Level.ExplosionInteraction.TRIGGER,
                ParticleTypes.GUST_EMITTER_SMALL,
                ParticleTypes.GUST_EMITTER_LARGE,
                SoundEvents.WIND_CHARGE_BURST);
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    private static ExplosionDamageCalculator getCalculator(float damage) {
        return new SimpleExplosionDamageCalculator(true, false, Optional.of(0.75f), BuiltInRegistries.BLOCK.getTag(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity()));
    }

    @Override
    public void onAntiMagic(MagicData playerMagicData) {
        this.explode(this.position());
        this.discard();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putFloat("Damage", this.getDamage());
        tag.putInt("Age", tickCount);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.damage = tag.getFloat("Damage");
        this.tickCount = tag.getInt("Age");
    }
}
