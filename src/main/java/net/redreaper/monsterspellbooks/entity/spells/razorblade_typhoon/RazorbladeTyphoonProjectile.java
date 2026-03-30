package net.redreaper.monsterspellbooks.entity.spells.razorblade_typhoon;

import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModSounds;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class RazorbladeTyphoonProjectile extends AbstractMagicProjectile implements GeoEntity {
    private final AnimatableInstanceCache cache;
    private final RawAnimation idle;
    public static final int lifetime = 200;
    int bounces;
    HashMap<UUID, Integer> victims;

    public RazorbladeTyphoonProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.cache = GeckoLibUtil.createInstanceCache(this);
        this.idle = RawAnimation.begin().thenLoop("idle");
        this.victims = new HashMap<>();
        this.setNoGravity(true);
    }

    public RazorbladeTyphoonProjectile(Level pLevel, LivingEntity pShooter) {
        this(ModEntities.RAZORBLADE_TYPHOON.get(), pLevel);
        this.setOwner(pShooter);
    }

    @Override
    public void trailParticles() {
        for (int i = 0; i < 3; i++)
        {
            double speed = 0.05F;
            double dx = Math.random() * 2 * speed - speed;
            double dy = Math.random() * 2 * speed - speed;
            double dz = Math.random() * 2 * speed - speed;

            double radius = 4;

            Vec3 leftAdjust = this.position().add(new Vec3(Math.sin(Math.toRadians(getYRot() + 90)), 0, Math.cos(Math.toRadians(getYRot() + 90))).scale(radius));
            Vec3 rightAdjust = this.position().add(new Vec3(Math.sin(Math.toRadians(getYRot() - 90)), 0, Math.cos(Math.toRadians(getYRot() - 90))).scale(radius));

            level().addParticle(Utils.random.nextDouble() < 0.3 ? ModParticleHelper.HYDRO_BUBBLE : ParticleTypes.FALLING_WATER, (this.getX()) + dx, this.getY() + dy, this.getZ() + dz, dx, dy, dz);

            level().addParticle(Utils.random.nextDouble() < 0.3 ? ModParticleHelper.HYDRO_BUBBLE : ParticleTypes.FALLING_WATER, leftAdjust.x, leftAdjust.y, leftAdjust.z, dx, dy, dz);

            level().addParticle(Utils.random.nextDouble() < 0.3 ? ModParticleHelper.HYDRO_BUBBLE : ParticleTypes.FALLING_WATER, rightAdjust.x, rightAdjust.y, rightAdjust.z, dx, dy, dz);
        }
    }

    @Override
    public void impactParticles(double x, double y, double z) {
        MagicManager.spawnParticles(level(), ModParticleHelper.HYDRO_BUBBLE, x, y, z, 10, 0, 0, 0, 0.1, true);
    }

    @Override
    public float getSpeed() {
        return 1.5F;
    }

    @Override
    protected boolean canHitEntity(Entity pTarget) {
        return super.canHitEntity(pTarget) && canHitVictim(pTarget);
    }

    @Override
    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.of(ModSounds.BRIMSTONE_BUZZSAW_HIT);
    }

    @Override
    public void handleHitDetection() {
        Vec3 vec3 = this.getDeltaMovement();
        Vec3 pos = this.position();
        Vec3 vec32 = pos.add(vec3);
        HitResult hitresult = level().clip(new ClipContext(pos, vec32, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
        if (hitresult.getType() != HitResult.Type.MISS) {
            //block hits
            onHit(hitresult);
        } else {
            //entity hits
            var entities = level().getEntities(this, this.getBoundingBox().inflate(0.25f), this::canHitEntity);
            for (Entity entity : entities) {
                onHit(new EntityHitResult(entity, this.getBoundingBox().getCenter().add(entity.getBoundingBox().getCenter()).scale(0.5f)));
            }
        }
    }

    public boolean canHitVictim(Entity entity) {
        var timestamp = victims.get(entity.getUUID());
        return timestamp == null || entity.tickCount - timestamp >= 10;
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        var target = pResult.getEntity();
        if (target instanceof LivingEntity livingEntity) {
            DamageSources.ignoreNextKnockback(livingEntity);
        }
        DamageSources.applyDamage(target, getDamage(), ModSpellRegistry.RAZORBLADE_TYPHOON.get().getDamageSource(this, getOwner()));
        victims.put(target.getUUID(), target.tickCount);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        switch (pResult.getDirection()) {
            case UP, DOWN ->
                    this.setDeltaMovement(this.getDeltaMovement().multiply(1, this.isNoGravity() ? -1 : -.8f, 1));
            case EAST, WEST -> this.setDeltaMovement(this.getDeltaMovement().multiply(-1, 1, 1));
            case NORTH, SOUTH -> this.setDeltaMovement(this.getDeltaMovement().multiply(1, 1, -1));
        }
        if (++bounces >= 6) {
            discard();
        }
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
