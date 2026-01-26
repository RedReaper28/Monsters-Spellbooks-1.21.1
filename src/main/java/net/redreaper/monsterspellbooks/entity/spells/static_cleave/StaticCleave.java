package net.redreaper.monsterspellbooks.entity.spells.static_cleave;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.core.Holder;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModMobEffects;
import net.redreaper.monsterspellbooks.init.ModParticleTypes;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;

import java.util.Optional;

public class StaticCleave extends AbstractMagicProjectile implements AntiMagicSusceptible {
    private int lifetimeInTicks = 20 * 10;

    public StaticCleave(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        this.setNoGravity(true);
    }

    public StaticCleave(EntityType<? extends Projectile> entityType, Level level, LivingEntity shooter)
    {
        this(entityType, level);
        setOwner(shooter);
        setYRot(shooter.getYRot());
        setXRot(shooter.getXRot());
    }

    public StaticCleave(Level level, LivingEntity shooter)
    {
        this(ModEntities.STATIC_CLEAVE.get(), level, shooter);
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
                double dz = Math.random() * speed * 5 - speed;
                level().addParticle(ModParticleTypes.ANCIENT_SPARKS_PARTICLE.get(), false, x + rotX + dx, y + dy, z + rotZ + dz, dx, dy, dz);
            }
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
    public void impactParticles(double x, double y, double z) {
        MagicManager.spawnParticles(level(), ModParticleHelper.ANCIENT_SPARKS, x, y, z, 1, 0, 0, 0, 0.1, true);
    }

    @Override
    public float getSpeed() {
        return 1.0F;
    }

    @Override
    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.of(SoundRegistry.SMALL_LIGHTNING_STRIKE);
    }

    @Override
    public void setDamage(float damage) {
        this.damage = damage;
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        DamageSources.applyDamage(pResult.getEntity(), getDamage(),
                ModSpellRegistry.STATIC_CLEAVE.get().getDamageSource(this, getOwner()));
        if (pResult.getEntity() instanceof LivingEntity livingTarget)
        {
            livingTarget.addEffect(new MobEffectInstance(ModMobEffects.PARALYSIS, 100, 3));
        }
    }

    @Override protected void onHit(HitResult hitresult) {super.onHit(hitresult);}

    @Override protected boolean canHitEntity(Entity pTarget) {return pTarget != getOwner() && super.canHitEntity(pTarget);}

    @Override
    public void onAntiMagic(MagicData playerMagicData) {
        MagicManager.spawnParticles(level(), ModParticleHelper.ANCIENT_SPARKS, this.getX(), this.getY(), this.getZ(), 1, 0, 0, 0, 0.1, true);
    }

    @Override
    public void recreateFromPacket(ClientboundAddEntityPacket pPacket) {
        super.recreateFromPacket(pPacket);
        this.xRotO = this.getXRot();
        this.yRotO = this.getYRot();
    }
}

