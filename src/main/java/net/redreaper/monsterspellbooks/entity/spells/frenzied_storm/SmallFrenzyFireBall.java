package net.redreaper.monsterspellbooks.entity.spells.frenzied_storm;

import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
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
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;

import java.util.Optional;

public class SmallFrenzyFireBall  extends AbstractMagicProjectile {
    public SmallFrenzyFireBall(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setNoGravity(false);
    }

    public SmallFrenzyFireBall(Level pLevel, LivingEntity pShooter) {
        this(ModEntities.SMALL_FRENZY_FIREBALL.get(), pLevel);
        this.setOwner(pShooter);
    }

    public void shoot(Vec3 rotation, float inaccuracy) {
        var speed = rotation.length();
        Vec3 offset = Utils.getRandomVec3(1).normalize().scale(inaccuracy);
        var motion = rotation.normalize().add(offset).normalize().scale(speed);
        super.shoot(motion);
    }

    @Override
    public void trailParticles() {
        if (tickCount <= 3) {
            return;
        }
        Vec3 vec3 = getDeltaMovement();
        double d0 = this.getX() - vec3.x;
        double d1 = this.getY() - vec3.y;
        double d2 = this.getZ() - vec3.z;
        var count = Mth.clamp((int) (vec3.lengthSqr() * 4), 1, 5);
        for (int i = 0; i < count; i++) {
            Vec3 random = Utils.getRandomVec3(.1);
            var f = i / ((float) count);
            var x = Mth.lerp(f, d0, this.getX());
            var y = Mth.lerp(f, d1, this.getY());
            var z = Mth.lerp(f, d2, this.getZ());
            this.level().addParticle(ModParticleHelper.FRENZY_FIRE, x - random.x, y + 0.5D - random.y, z - random.z, random.x * .5f, random.y * .5f, random.z * .5f);
        }
    }

    @Override
    public void impactParticles(double x, double y, double z) {
        MagicManager.spawnParticles(level(), ParticleHelper.FIERY_SPARKS, x, y, z, 5, 0, 0, 0, 0.25, true);
        MagicManager.spawnParticles(level(), ModParticleHelper.FRENZY_FIRE, x, y, z, 5, 0, 0, 0, 0.25, false);
    }

    @Override public float getSpeed() {return .80f;}


    @Override
    protected void onHit(HitResult hitresult) {
        if (!this.level().isClientSide) {
            super.onHit(hitresult);
            float explosionRadius = getExplosionRadius();

            Vec3 losPoint = Utils.raycastForBlock(level(), this.position(), this.position().add(0, 2, 0), ClipContext.Fluid.NONE).getLocation();
            MagicManager.spawnParticles(level(), new BlastwaveParticleOptions(ModSpellRegistry.FRENZIED_STORM.get().getSchoolType().getTargetingColor(), getExplosionRadius()), hitresult.getLocation().x() , hitresult.getLocation().y()  + .165f,  hitresult.getLocation().z() , 1, 0, 0, 0, 0, true);


            impactParticles(xOld, yOld, zOld);
            getImpactSound().ifPresent(this::doImpactSound);
            var entities = level().getEntities(this, this.getBoundingBox().inflate(explosionRadius));
            for (Entity entity : entities) {
                double distance = entity.distanceToSqr(hitresult.getLocation());
                if (distance < explosionRadius * explosionRadius && canHitEntity(entity)) {
                    if (Utils.hasLineOfSight(level(), hitresult.getLocation(), entity.position().add(0, entity.getEyeHeight() * .5f, 0), true)) {
                        double p = (1 - Math.pow(Math.sqrt(distance) / (explosionRadius), 3));
                        float damage = (float) (this.damage * p);
                        DamageSources.applyDamage(entity, damage, ModSpellRegistry.FRENZIED_STORM.get().getDamageSource(this, getOwner()));
                    }
                }
            }
            this.discardHelper(hitresult);
        }
    }

    float aoeDamage;

    public void setAoeDamage(float damage) {
        this.aoeDamage = damage;
    }

    public float getAoeDamage() {
        return aoeDamage;
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putFloat("AoeDamage", aoeDamage);
    }

    @Override
    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.of(SoundEvents.GENERIC_EXPLODE);
    }
}
