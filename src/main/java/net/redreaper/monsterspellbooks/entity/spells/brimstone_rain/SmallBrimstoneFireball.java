package net.redreaper.monsterspellbooks.entity.spells.brimstone_rain;

import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;

import java.util.Optional;

public class SmallBrimstoneFireball extends AbstractMagicProjectile {
    public SmallBrimstoneFireball(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setNoGravity(true);
    }

    public SmallBrimstoneFireball(Level pLevel, LivingEntity pShooter) {
        this(ModEntities.SMALL_BRIMSTONE_FIREBALL.get(), pLevel);
        this.setOwner(pShooter);
    }

    public void shoot(Vec3 rotation, float innaccuracy) {
        Vec3 offset = Utils.getRandomVec3(1).normalize().scale(innaccuracy);
        super.shoot(rotation.add(offset));
    }

    @Override
    public void trailParticles() {
        var vec = getDeltaMovement();
        var length = vec.length();
        int count = (int) Math.min(20, Math.round(length) * 4) + 1;
        float f = (float) length / count;
        for (int i = 0; i < count; i++) {
            Vec3 random = Utils.getRandomVec3(0.04);
            Vec3 p = vec.scale(f * i);
            level().addParticle(ModParticleHelper.BRIMSTONE_FIRE, this.getX() + random.x + p.x, this.getY() + random.y + p.y, this.getZ() + random.z + p.z, random.x, random.y, random.z);
        }
    }

    @Override
    public void impactParticles(double x, double y, double z) {
        MagicManager.spawnParticles(level(), new BlastwaveParticleOptions(ModSpellRegistry.BRIMSTONE_RAIN.get().getSchoolType().getTargetingColor(), 1.25f), x, y, z, 1, 0, 0, 0, 0, true);
    }

    @Override
    public float getSpeed() {
        return 1.85f;
    }

    @Override
    protected void doImpactSound(Holder<SoundEvent> sound) {
        level().playSound(null, getX(), getY(), getZ(), sound, SoundSource.NEUTRAL, .8f, 1.35f + Utils.random.nextFloat() * .3f);
    }

    @Override
    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.of(SoundEvents.GENERIC_EXPLODE);
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        createFireField(blockHitResult.getLocation());
        float explosionRadius = getExplosionRadius();
        var entities = level().getEntities(this, this.getBoundingBox().inflate(explosionRadius));
        for (Entity entity : entities) {
            double distance = entity.distanceToSqr(blockHitResult.getLocation());
            if (distance < explosionRadius * explosionRadius && canHitEntity(entity)) {
                DamageSources.applyDamage(entity, damage, ModSpellRegistry.BRIMSTONE_RAIN.get().getDamageSource(this, getOwner()));
            }
        }
        this.discardHelper(blockHitResult);
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        var target = entityHitResult.getEntity();
        DamageSources.applyDamage(target, getDamage(), ModSpellRegistry.BRIMSTONE_RAIN.get().getDamageSource(this, getOwner()));
        pierceOrDiscard();
    }

    public void createFireField(Vec3 location) {
        if (!level().isClientSide) {
            BrimstoneField fire = new BrimstoneField(level());
            fire.setOwner(getOwner());
            fire.setDuration(50);
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
