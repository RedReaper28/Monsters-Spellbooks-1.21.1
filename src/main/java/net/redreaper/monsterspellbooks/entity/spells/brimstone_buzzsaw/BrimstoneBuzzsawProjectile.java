package net.redreaper.monsterspellbooks.entity.spells.brimstone_buzzsaw;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModMobEffects;
import net.redreaper.monsterspellbooks.init.ModSounds;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class BrimstoneBuzzsawProjectile extends AbstractMagicProjectile implements AntiMagicSusceptible {
    private int lifetimeInTicks = 20 * 10;
    int bounces;

    public BrimstoneBuzzsawProjectile(EntityType<? extends BrimstoneBuzzsawProjectile> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);
    }

    public BrimstoneBuzzsawProjectile(EntityType<? extends BrimstoneBuzzsawProjectile> entityType, Level levelIn, LivingEntity shooter) {
        this(entityType, levelIn);
        setOwner(shooter);
        setYRot(shooter.getYRot());
        setXRot(shooter.getXRot());
    }

    public BrimstoneBuzzsawProjectile(Level levelIn, LivingEntity shooter) {
        this(ModEntities.BRIMSTONE_BUZZSAW_PROJECTILE.get(), levelIn, shooter);
    }

    @Override
    public void travel() {
        this.setPos(this.position().add(this.getDeltaMovement()));
        if (!this.isNoGravity())
        {
            Vec3 vec3 = this.getDeltaMovement();
            this.setDeltaMovement(vec3.x, vec3.y - 0.05000000074505806, vec3.z);
        }
    }

    @Override
    public void tick() {
        lifetimeInTicks--;
        if (lifetimeInTicks <= 0)
        {
            this.discard();
        }

        Vec3 deltaMovement = getDeltaMovement();
        double distance = deltaMovement.horizontalDistance();

        double x = deltaMovement.x;
        double y = deltaMovement.y;
        double z = deltaMovement.z;

        setYRot((float) (Mth.atan2(x, z) * (180 / Math.PI)));
        setXRot((float) (Mth.atan2(y, distance) * (180 / Math.PI)));
        setXRot(lerpRotation(xRotO, getXRot()));
        setYRot(lerpRotation(yRotO, getYRot()));

        super.tick();
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

            level().addParticle(Utils.random.nextDouble() < 0.3 ? ModParticleHelper.BRIMSTONE_FIRE : ParticleTypes.LAVA, (this.getX()) + dx, this.getY() + dy, this.getZ() + dz, dx, dy, dz);

            level().addParticle(Utils.random.nextDouble() < 0.3 ? ModParticleHelper.BRIMSTONE_FIRE : ParticleTypes.LAVA, leftAdjust.x, leftAdjust.y, leftAdjust.z, dx, dy, dz);

            level().addParticle(Utils.random.nextDouble() < 0.3 ? ModParticleHelper.BRIMSTONE_FIRE : ParticleTypes.LAVA, rightAdjust.x, rightAdjust.y, rightAdjust.z, dx, dy, dz);
        }
    }

    @Override
    public void impactParticles(double x, double y, double z) {
        MagicManager.spawnParticles(level(), ParticleHelper.FIERY_SMOKE, x, y, z, 10, 0, 0, 0, 0.1, true);
    }

    @Override
    public float getSpeed() {
        return 3.0F;
    }

    @Override
    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.of(ModSounds.BRIMSTONE_BUZZSAW_HIT);
    }


    public void setDamage(float damage) {
        this.damage = damage;
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);

        DamageSources.applyDamage(pResult.getEntity(), getDamage(),
                ModSpellRegistry.BRIMSTONE_BUZZSAW.get().getDamageSource(this, getOwner()));

        if (pResult.getEntity() instanceof LivingEntity livingTarget)
        {
            livingTarget.addEffect(new MobEffectInstance(ModMobEffects.BRIMSTONE_FLAME, 400, 3));
        }
    }

    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        switch (pResult.getDirection()) {
            case UP:
            case DOWN:
                this.setDeltaMovement(this.getDeltaMovement().multiply((double)1.0F, this.isNoGravity() ? (double)-1.0F : (double)-0.8F, (double)1.0F));
                break;
            case EAST:
            case WEST:
                this.setDeltaMovement(this.getDeltaMovement().multiply((double)-1.0F, (double)1.0F, (double)1.0F));
                break;
            case NORTH:
            case SOUTH:
                this.setDeltaMovement(this.getDeltaMovement().multiply((double)1.0F, (double)1.0F, (double)-1.0F));
        }
        if (++this.bounces >= 6) {
            this.discard();
        }

    }

    protected boolean canHitEntity(Entity entity) {
        return entity != getOwner() && super.canHitEntity(entity);
    }

    public void onAntiMagic(MagicData playerMagicData) {
        this.discard();
    }

    @Override
    public void recreateFromPacket(@NotNull ClientboundAddEntityPacket pPacket) {
        super.recreateFromPacket(pPacket);
        this.xRotO = this.getXRot();
        this.yRotO = this.getYRot();
    }
}
