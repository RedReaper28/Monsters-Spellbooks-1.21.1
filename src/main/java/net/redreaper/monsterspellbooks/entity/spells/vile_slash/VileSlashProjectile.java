package net.redreaper.monsterspellbooks.entity.spells.vile_slash;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModMobEffects;
import net.redreaper.monsterspellbooks.init.ModParticleTypes;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;

import java.util.Optional;

public class VileSlashProjectile extends AbstractMagicProjectile implements AntiMagicSusceptible {
    private int lifetimeInTicks = 20 * 10;


    public VileSlashProjectile(EntityType<? extends VileSlashProjectile> entityType, Level level) {
        super(entityType, level);

        this.setNoGravity(true);
    }

    public VileSlashProjectile(EntityType<? extends VileSlashProjectile> entityType, Level level, LivingEntity shooter) {
        this(entityType, level);
        setOwner(shooter);
        setYRot(shooter.getYRot());
        setXRot(shooter.getXRot());
    }

    public VileSlashProjectile(Level level, LivingEntity shooter) {
        this(ModEntities.VILE_SLASH_PROJECTILE.get(), level, shooter);
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

    public void trailParticles() {
        if (level().isClientSide){

            float width = (float) getBoundingBox().getXsize();
            float step = .25f;
            float radians = Mth.DEG_TO_RAD * getYRot();
            float speed = .1f;
            for (int i = 0; i < width / step; i++) {
                double x = getX();
                double y = getY();
                double z = getZ();
                double offset = step * (i - width / step / 2);
                double rotX = offset * Math.cos(radians);
                double rotZ = -offset * Math.sin(radians);

                double dx = Math.random() * speed * 2 - speed;
                double dy = Math.random() * speed * 2 - speed;
                double dz = Math.random() * speed * 2 - speed;
                level().addParticle(ModParticleTypes.REAPER_FIRE_PARTICLE.get(), false, x + rotX + dx, y + dy, z + rotZ + dz, dx, dy, dz);
            }
        }
    }


    @Override
    public void impactParticles(double x, double y, double z) {
        MagicManager.spawnParticles(level(), ModParticleHelper.REAPER_FIRE, x, y, z, 1, 0, 0, 0, 0.1, true);
    }

    @Override
    public float getSpeed() {
        return 2.0F;
    }

    @Override
    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.of(SoundEvents.SOUL_ESCAPE);
    }

    @Override
    public void setDamage(float damage) {
        this.damage = damage;
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);

        DamageSources.applyDamage(pResult.getEntity(), getDamage(),
                ModSpellRegistry.VILE_SLASH.get().getDamageSource(this, getOwner()));

        if (pResult.getEntity() instanceof LivingEntity livingTarget)
        {
            livingTarget.addEffect(new MobEffectInstance(ModMobEffects.SOUL_ROT, 100, 1));
        }

    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        this.discard();
    }

    @Override
    protected void onHit(HitResult hitresult) {
        super.onHit(hitresult);
    }


    protected boolean canHitEntity(Entity entity) {
        return entity != getOwner() && super.canHitEntity(entity);
    }

    public void onAntiMagic(MagicData playerMagicData) {
        this.discard();
    }

    protected void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putFloat("Damage", this.damage);
    }

    protected void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.damage = pCompound.getFloat("Damage");
    }
}
