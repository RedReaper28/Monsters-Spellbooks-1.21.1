package net.redreaper.monsterspellbooks.entity.spells.dragon_charge;

import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.network.particles.FieryExplosionParticlesPacket;
import io.redspace.ironsspellbooks.registries.EntityRegistry;
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
import net.neoforged.neoforge.network.PacketDistributor;
import net.redreaper.monsterspellbooks.entity.spells.space_rupture.DistortionField;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import net.redreaper.monsterspellbooks.particle.ElectricExplosionParticlesPacket;
import net.redreaper.monsterspellbooks.particle.EnderExplosionParticlePacket;

import java.util.Optional;

public class DragonChargeProjectile extends AbstractMagicProjectile {
    public DragonChargeProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setNoGravity(true);
    }

    public DragonChargeProjectile(Level pLevel, LivingEntity pShooter) {
        this(ModEntities.DRAGON_CHARGE_PROJECTILE.get(), pLevel);
        this.setOwner(pShooter);
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
            this.level().addParticle(ParticleHelper.UNSTABLE_ENDER, true,x - random.x, y + getBbHeight() * .5f - random.y, z - random.z, 0,0,0/*motion.x * .5f, motion.y * .5f, motion.z * .5f*/);
        }
    }


    @Override
    public void impactParticles(double x, double y, double z) {
    }

    @Override
    public float getSpeed() {
        return 2;
    }

    @Override
    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.of(SoundEvents.GENERIC_EXPLODE);
    }

    @Override
    protected void onHit(HitResult hitResult) {
        if (!this.level().isClientSide) {
            impactParticles(xOld, yOld, zOld);
            createDistortionField(hitResult.getLocation());
            float explosionRadius = getExplosionRadius();
            var explosionRadiusSqr = explosionRadius * explosionRadius;
            var entities = level().getEntities(this, this.getBoundingBox().inflate(explosionRadius));
            Vec3 losPoint = Utils.raycastForBlock(level(), this.position(), this.position().add(0, 2, 0), ClipContext.Fluid.NONE).getLocation();
            for (Entity entity : entities) {
                double distanceSqr = entity.distanceToSqr(hitResult.getLocation());
                if (distanceSqr < explosionRadiusSqr && canHitEntity(entity) && Utils.hasLineOfSight(level(), losPoint, entity.getBoundingBox().getCenter(), true)) {
                    double p = (1 - distanceSqr / explosionRadiusSqr);
                    float damage = (float) (this.damage * p);
                    DamageSources.applyDamage(entity, damage, ModSpellRegistry.DRAGON_CHARGE.get().getDamageSource(this, getOwner()));
                }
            }
            PacketDistributor.sendToPlayersTrackingEntity(this, new EnderExplosionParticlePacket(hitResult.getLocation().subtract(getDeltaMovement().scale(0.5)), getExplosionRadius()));
            playSound(SoundEvents.GENERIC_EXPLODE.value(), 4.0F, (1.0F + (this.level().random.nextFloat() - this.level().random.nextFloat()) * 0.2F) * 0.7F);
            this.discardHelper(hitResult);
        }
    }

    public void createDistortionField(Vec3 location) {
        if (!level().isClientSide) {
            DistortionField fire = new DistortionField(level());
            fire.setOwner(getOwner());
            fire.setDuration(200);
            fire.setDamage(aoeDamage);
            fire.setRadius(getExplosionRadius());
            fire.setCircular();
            fire.moveTo(location);
            level().addFreshEntity(fire);
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
    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.aoeDamage = tag.getFloat("AoeDamage");
    }
}
