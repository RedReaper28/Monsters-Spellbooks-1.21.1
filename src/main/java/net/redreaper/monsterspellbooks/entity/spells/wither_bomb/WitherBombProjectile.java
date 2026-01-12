package net.redreaper.monsterspellbooks.entity.spells.wither_bomb;

import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;

import java.util.Optional;

public class WitherBombProjectile extends AbstractMagicProjectile {
    public WitherBombProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setNoGravity(true);
    }

    public WitherBombProjectile(Level pLevel, LivingEntity pShooter) {
        this(ModEntities.WITHER_BOMB.get(), pLevel);
        this.setOwner(pShooter);
    }

    int witherDuration;

    public int getWitherDuration() {
        return witherDuration;
    }

    public void shoot(Vec3 rotation, float inaccuracy) {
        var speed = rotation.length();
        Vec3 offset = Utils.getRandomVec3(1).normalize().scale(inaccuracy);
        var motion = rotation.normalize().add(offset).normalize().scale(speed);
        super.shoot(motion);
    }

    @Override
    public void trailParticles() {
        Vec3 vec3 = getDeltaMovement();
        double d0 = this.getX() - vec3.x;
        double d1 = this.getY() - vec3.y;
        double d2 = this.getZ() - vec3.z;
        var count = Mth.clamp((int) (vec3.lengthSqr() * 2), 1, 4);
        for (int i = 0; i < count; i++) {
            Vec3 random = Utils.getRandomVec3(getBbHeight() * .2f);
            var f = i / ((float) count);
            var x = Mth.lerp(f, d0, this.getX() + vec3.x);
            var y = Mth.lerp(f, d1, this.getY() + vec3.y);
            var z = Mth.lerp(f, d2, this.getZ() + vec3.z);
            this.level().addParticle(ParticleTypes.SOUL, true,x - random.x, y + getBbHeight() * .5f - random.y, z - random.z, 0,0,0/*motion.x * .5f, motion.y * .5f, motion.z * .5f*/);
        }
    }

    @Override
    public void impactParticles(double x, double y, double z) {
    }

    @Override
    public float getSpeed() {
        return 1.5f;
    }

    @Override
    protected void onHit(HitResult hitResult) {
        if (!this.level().isClientSide) {
            impactParticles(xOld, yOld, zOld);
            float explosionRadius = getExplosionRadius();
            var explosionRadiusSqr = explosionRadius * explosionRadius;

            Vec3 losPoint = Utils.raycastForBlock(level(), this.position(), this.position().add(0, 2, 0), ClipContext.Fluid.NONE).getLocation();
            MagicManager.spawnParticles(level(), new BlastwaveParticleOptions(SchoolRegistry.ICE.get().getTargetingColor(), getExplosionRadius()), hitResult.getLocation().x() , hitResult.getLocation().y()  + .165f,  hitResult.getLocation().z() , 1, 0, 0, 0, 0, true);

            var entities = level().getEntities(this, this.getBoundingBox().inflate(explosionRadius));

            for (Entity entity : entities) {
                double distanceSqr = entity.distanceToSqr(hitResult.getLocation());
                if (distanceSqr < explosionRadiusSqr && canHitEntity(entity) && Utils.hasLineOfSight(level(), losPoint, entity.getBoundingBox().getCenter(), true)) {
                    double p = (1 - distanceSqr / explosionRadiusSqr);
                    float damage = (float) (this.damage * p);

                    if (entity instanceof LivingEntity livingEntity && livingEntity != getOwner())
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, 180, 0));
                    DamageSources.applyDamage(entity, damage, ModSpellRegistry.WITHER_BOMB.get().getDamageSource(this, getOwner()));

                }
            }
        }
        playSound(SoundEvents.GENERIC_EXPLODE.value(), 4.0F, (1.0F + (this.level().random.nextFloat() - this.level().random.nextFloat()) * 0.2F) * 0.7F);
            this.discardHelper(hitResult);
        }

        @Override public Optional<Holder<SoundEvent>> getImpactSound() {return Optional.of(SoundEvents.GENERIC_EXPLODE);}
    }
