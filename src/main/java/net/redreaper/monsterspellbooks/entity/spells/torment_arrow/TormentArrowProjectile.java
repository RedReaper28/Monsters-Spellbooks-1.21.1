package net.redreaper.monsterspellbooks.entity.spells.torment_arrow;

import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TormentArrowProjectile extends AbstractMagicProjectile {
    private final List<Entity> victims = new ArrayList<>();
    private int hitsPerTick;
    public int delay;
    public @Nullable Vec3 ownerTrack;
    private @Nullable UUID targetEntity;
    private @Nullable Entity cachedTarget;
    int age;
    boolean isGrounded;
    public @Nullable Vec3 launchDir; // new: direction to use when launching after delay

    public TormentArrowProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setNoGravity(true);
        this.setPierceLevel(-1);
    }

    public TormentArrowProjectile(Level levelIn, LivingEntity shooter) {
        this(ModEntities.TORMENT_ARROW_PROJECTILE.get(), levelIn);
        setOwner(shooter);
    }

    @Override
    public void trailParticles() {
        var vec = getDeltaMovement();
        var length = vec.length();
        int count = (int) Math.min(20, Math.round(length) * 2) + 1;
        float f = (float) length / count;
        for (int i = 0; i < count; i++) {
            Vec3 random = Utils.getRandomVec3(0.025);
            Vec3 p = vec.scale(f * i);
            level().addParticle(ParticleTypes.SOUL, this.getX() + random.x + p.x, this.getY() + random.y + p.y, this.getZ() + random.z + p.z, random.x, random.y, random.z);
        }
    }

    @Override
    public void impactParticles(double x, double y, double z) {
        MagicManager.spawnParticles(level(), ModParticleHelper.SOUL_SMOKE, x, y, z, 10, .1, .1, .1, .4, false);
    }

    @Override
    public float getSpeed() {
        return 0.7f;
    }

    @Override
    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.empty();
    }


    @Override
    protected void onHitBlock(BlockHitResult pResult) {

    }

    public boolean isTrackingOwner() {
        return this.ownerTrack != null;
    }

    @Override
    public void tick() {
        if (this.age++ < this.delay) {
            Entity owner = this.getOwner();
            float strength = 0.5F;

            if (owner != null && this.isTrackingOwner()) {
                // follow owner movement
                Vec3 ownerMotion = owner.position().subtract(owner.xOld, owner.yOld, owner.zOld);
                this.setPos(this.position().add(ownerMotion));
            }

            if (owner != null) {
                float yRot = owner.getYRot();
                float xRot = owner.getXRot();
                this.setYRot(yRot);
                this.setXRot(xRot);
                this.yRotO = yRot;
                this.xRotO = xRot;
            }

            Entity target = this.getTargetEntity();
            if (target != null) {
                Vec3 targetPos = target.getBoundingBox().getCenter();
                Vec3 targetMotion = targetPos.subtract(this.position()).normalize().scale((double)this.getSpeed());
                Vec3 currentMotion = this.getDeltaMovement();
                this.deltaMovementOld = currentMotion;
                this.setDeltaMovement(currentMotion.add(targetMotion.subtract(currentMotion).scale((double)strength)));
                if (this.tickCount == 1) {
                    this.deltaMovementOld = this.getDeltaMovement();
                }
            }

            if (this.age == this.delay) {
                if (this.launchDir == null && owner != null) {
                    Vec3 ownerLook = owner.getLookAngle();
                    if (ownerLook != null) {
                        this.launchDir = ownerLook;
                    }
                }

                if (this.launchDir != null) {
                    if (!this.level().isClientSide) {
                        this.isGrounded = false;
                        this.setNoGravity(true);
                        Vec3 dir = this.launchDir.normalize().scale(this.getSpeed());
                        this.setDeltaMovement(dir);
                        this.deltaMovementOld = this.getDeltaMovement();
                    }
                    this.ownerTrack = null;
                    // Keep launchDir for consistent movement direction

                    if (Utils.random.nextFloat() < 0.25F) {
                        this.playSound(SoundRegistry.FIERY_DAGGER_THROW.get(), 0.75F, (float)Utils.random.nextIntBetweenInclusive(90, 110) * 0.01F);
                    } else {
                        this.playSound(SoundRegistry.FIERY_DAGGER_THROW.get(), 2.0F, (float)Utils.random.nextIntBetweenInclusive(90, 110) * 0.01F);
                    }
                } else {
                    if (this.isGrounded) {
                        if (!this.level().isClientSide) {
                            this.isGrounded = false;
                            this.setNoGravity(true);
                            this.setDeltaMovement(0.0D, this.getSpeed(), 0.0D);
                            this.deltaMovementOld = this.getDeltaMovement();
                        }
                        if (Utils.random.nextFloat() < 0.25F) {
                            this.playSound(SoundRegistry.FIERY_DAGGER_THROW.get(), 0.75F, (float)Utils.random.nextIntBetweenInclusive(90, 110) * 0.01F);
                        }
                    } else {
                        this.playSound(SoundRegistry.FIERY_DAGGER_THROW.get(), 2.0F, (float)Utils.random.nextIntBetweenInclusive(90, 110) * 0.01F);
                    }
                }

                List<Entity> hits = this.level().getEntities(this, this.getBoundingBox().inflate(0.4D), this::canHitEntity);
                EntityHitResult hitResult = hits.isEmpty() ? null : new EntityHitResult(hits.get(0));
                if (hitResult != null) {
                    this.onHit(hitResult);
                }
            }

            if (this.level().isClientSide) {
                this.level().addParticle(ParticleHelper.EMBERS, this.getX(), this.getY() + (double)(this.getBbHeight() * 0.5F), this.getZ(), 0.0, 0.0, 0.0);
            }
        } else {
            super.tick();
        }
    }

    public Entity getTargetEntity() {
        if (this.cachedTarget != null && this.cachedTarget.isAlive()) {
            return this.cachedTarget;
        } else {
            if (this.targetEntity != null) {
                Level var2 = this.level();
                if (var2 instanceof ServerLevel) {
                    ServerLevel serverLevel = (ServerLevel)var2;
                    this.cachedTarget = serverLevel.getEntity(this.targetEntity);
                    if (this.cachedTarget == null) {
                        this.targetEntity = null;
                    }

                    return this.cachedTarget;
                }
            }

            return null;
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        if (!victims.contains(entity)) {
            DamageSources.applyDamage(entity, damage, ModSpellRegistry.TORMENT_ARROW.get().getDamageSource(this, getOwner()));
            victims.add(entity);
        }
        if (getPierceLevel() != 0) {
            if (hitsPerTick++ < 5) {
                HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
                if (hitresult.getType() != HitResult.Type.MISS && !EventHooks.onProjectileImpact(this, hitresult)) {
                    onHit(hitresult);
                }
            }
            pierceOrDiscard();
        } else {
            discard();
        }
    }

    BlockPos lastHitBlock;

    @Override
    protected void onHit(HitResult result) {
        if (!level().isClientSide) {
            var blockPos = BlockPos.containing(result.getLocation());
            if (result.getType() == HitResult.Type.BLOCK && !blockPos.equals(lastHitBlock)) {
                lastHitBlock = blockPos;
            } else if (result.getType() == HitResult.Type.ENTITY) {
                level().playSound(null, BlockPos.containing(position()), SoundRegistry.FORCE_IMPACT.get(), SoundSource.NEUTRAL, 2, .65f);
            }
        }
        super.onHit(result);
    }

    @Override
    protected boolean shouldPierceShields() {
        return true;
    }

    public double getTick(Object object) {
        return this.tickCount;
    }
}
