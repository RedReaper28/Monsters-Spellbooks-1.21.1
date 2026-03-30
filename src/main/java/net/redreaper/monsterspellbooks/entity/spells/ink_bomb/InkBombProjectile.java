package net.redreaper.monsterspellbooks.entity.spells.ink_bomb;

import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
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
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Optional;

public class InkBombProjectile extends AbstractMagicProjectile implements GeoEntity {
    private final AnimatableInstanceCache cache;
    private final RawAnimation idle;

    public InkBombProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.cache = GeckoLibUtil.createInstanceCache(this);
        this.idle = RawAnimation.begin().thenLoop("idle");
    }

    public InkBombProjectile(Level level, LivingEntity shooter) {
        this(ModEntities.INK_BOMB.get(), level);
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
        Vec3 vec3 = getDeltaMovement();
        double d0 = this.getX() - vec3.x;
        double d1 = this.getY() - vec3.y;
        double d2 = this.getZ() - vec3.z;
        for (int i = 0; i < 4; i++) {
            Vec3 random = Utils.getRandomVec3(.2);
            this.level().addParticle(ParticleTypes.SQUID_INK, d0 - random.x, d1 + 0.5D - random.y, d2 - random.z, random.x * .5f, random.y * .5f, random.z * .5f);
        }
    }

    @Override
    public void impactParticles(double x, double y, double z) {
        MagicManager.spawnParticles(level(), ParticleTypes.SQUID_INK, x, y, z, 30, 1.5, .1, 1.5, 1, false);
    }

    @Override
    public float getSpeed() {
        return .65f;
    }

    @Override
    protected void onHit(HitResult hitresult) {
        super.onHit(hitresult);
        if (!this.level().isClientSide) {
            impactParticles(xOld, yOld, zOld);
            float explosionRadius = getExplosionRadius();
            var explosionRadiusSqr = explosionRadius * explosionRadius;
            var entities = level().getEntities(this, this.getBoundingBox().inflate(explosionRadius));
            Vec3 losPoint = Utils.raycastForBlock(level(), this.position(), this.position().add(0, 2, 0), ClipContext.Fluid.NONE).getLocation();
            for (Entity entity : entities) {
                double distanceSqr = entity.distanceToSqr(hitresult.getLocation());
                if (distanceSqr < explosionRadiusSqr && canHitEntity(entity) && Utils.hasLineOfSight(level(), losPoint, entity.getBoundingBox().getCenter(), true)) {
                    double p = (1 - distanceSqr / explosionRadiusSqr);
                    float damage = (float) (this.damage * p);
                    DamageSources.applyDamage(entity, damage, ModSpellRegistry.INK_BOMB.get().getDamageSource(this, getOwner()));
                    if (entity instanceof LivingEntity livingEntity && livingEntity != getOwner())
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, getRendDuration(), 0));
                    if (entity instanceof LivingEntity livingEntity && livingEntity != getOwner())
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, getRendDuration(), 0));
                    if (entity instanceof LivingEntity livingEntity && livingEntity != getOwner())
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, getRendDuration(), 1));
                }
            }
            this.discardHelper(hitresult);
        }
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

    @Override
    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.of(SoundRegistry.ACID_ORB_IMPACT);
    }


    private PlayState predicate(AnimationState event) {
        event.getController().setAnimation(this.idle);
        return PlayState.CONTINUE;
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController(this, "controller", 0, this::predicate));
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
