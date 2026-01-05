package net.redreaper.monsterspellbooks.entity.spells.napalm_orb;

import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModMobEffects;

import java.util.Optional;

public class NapalmOrb extends AbstractMagicProjectile {
    public NapalmOrb(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public NapalmOrb(Level level, LivingEntity shooter) {
        this(ModEntities.NAPALM_ORB.get(), level);
        setOwner(shooter);
    }

    int rendLevel;
    int rendDuration;


    public int getRendLevel() {
        return rendLevel;
    }

    public void setRendLevel(int rendLevel) {
        this.rendLevel = rendLevel;
    }

    public int getRendDuration() {
        return rendDuration;
    }

    public void setRendDuration(int rendDuration) {
        this.rendDuration = rendDuration;
    }

    @Override
    public void trailParticles() {
        Vec3 vec3 = this.position().subtract(getDeltaMovement().scale(2));
        level().addParticle(ParticleHelper.FIERY_SPARKS, vec3.x, vec3.y, vec3.z, 0, 0, 0);
    }

    @Override
    public void impactParticles(double x, double y, double z) {
        MagicManager.spawnParticles(level(), ParticleHelper.FIERY_SPARKS, x, y, z, 55, .08, .08, .08, 0.3, true);
        MagicManager.spawnParticles(level(), ParticleHelper.FIERY_SMOKE, x, y, z, 25, .08, .08, .08, 0.3, false);
    }

    @Override
    public float getSpeed() {
        return 1;
    }

    @Override
    protected void onHit(HitResult hitresult) {
        super.onHit(hitresult);
        if (!this.level().isClientSide) {
            float explosionRadius = 3.5f;
            var entities = level().getEntities(this, this.getBoundingBox().inflate(explosionRadius));
            for (Entity entity : entities) {
                double distance = entity.position().distanceTo(hitresult.getLocation());
                if (distance < explosionRadius && Utils.hasLineOfSight(level(), hitresult.getLocation(), entity.getEyePosition(), true)) {
                    if (entity instanceof LivingEntity livingEntity && livingEntity != getOwner())
                        livingEntity.addEffect(new MobEffectInstance(ModMobEffects.INFLAMMABLE, getRendDuration(), getRendLevel()));
                }
            }
            this.discardHelper(hitresult);
        }
    }

    @Override
    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.of(SoundRegistry.ACID_ORB_IMPACT);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("RendLevel", rendLevel);
        tag.putInt("RendDuration", rendDuration);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.rendLevel = tag.getInt("RendLevel");
        this.rendDuration = tag.getInt("RendDuration");
    }
}
