package net.redreaper.monsterspellbooks.entity.spells.redstone_mines;

import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModParticleTypes;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import net.redreaper.monsterspellbooks.particle.AncientZapParticleOption;

import java.util.Optional;

public class RedstoneMinesProjectile extends AbstractMagicProjectile implements AntiMagicSusceptible {
    private int duration;

    public RedstoneMinesProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.duration = 100;
        this.setNoGravity(true);
    }

    public RedstoneMinesProjectile(Level pLevel, LivingEntity pShooter) {
        this(ModEntities.REDSTONE_MINES.get(), pLevel);
        this.setOwner(pShooter);
    }

    @Override
    public void trailParticles() {
        Vec3 pos = this.getBoundingBox().getCenter().add(this.getDeltaMovement());
        Vec3 random = Utils.getRandomVec3((double)0.25F).add(pos);
        pos = pos.add(this.getDeltaMovement());
        this.level().addParticle(new AncientZapParticleOption(random), pos.x, pos.y, pos.z, (double)0.0F, (double)0.0F, (double)0.0F);
    }

    @Override public void impactParticles(double x, double y, double z) {}

    @Override public float getSpeed() {return 0.1f;}

    @Override public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.empty();
    }

    public int getDuration() {return this.duration;}

    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putInt("Age", this.tickCount);
        pCompound.putInt("Duration", this.duration);
        super.addAdditionalSaveData(pCompound);
    }

    protected void readAdditionalSaveData(CompoundTag pCompound) {
        this.tickCount = pCompound.getInt("Age");
        this.duration = pCompound.getInt("Duration");
        super.readAdditionalSaveData(pCompound);
    }

    public void tick() {
        super.tick();
        AABB bb = this.getBoundingBox();
        if (!this.level().isClientSide && this.tickCount > this.duration) {
            float explosionRadius = getExplosionRadius();
            var explosionRadiusSqr = explosionRadius * explosionRadius;
            var entities = level().getEntities(this, this.getBoundingBox().inflate(explosionRadius));
            Vec3 losPoint = Utils.raycastForBlock(this.level(), this.position(), this.position().add((double)0.0F, (double)2.0F, (double)0.0F), ClipContext.Fluid.NONE).getLocation();

            for (Entity entity : entities) {
                double distanceSqr = entity.distanceToSqr(bb.getCenter());
                if (distanceSqr < explosionRadiusSqr && canHitEntity(entity) && Utils.hasLineOfSight(level(), losPoint, entity.getBoundingBox().getCenter(), true)) {
                    DamageSources.applyDamage(entity, damage, ModSpellRegistry.REDSTONE_MINES.get().getDamageSource(this, getOwner()));
                }
            }
            this.level().playSound(null, this.blockPosition(), SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.NEUTRAL, 3.5F, (float)Utils.random.nextIntBetweenInclusive(9, 11) * 0.3F);
            MagicManager.spawnParticles(this.level(), ModParticleTypes.ANCIENT_SPARKS_PARTICLE.get(), this.getX(), this.getY() + (double)this.getExplosionRadius(), this.getZ(), 100, (double)1.0F, (double)1.0F, (double)1.0F, (double)0.5F, true);
            this.discard();
        }
    }
}