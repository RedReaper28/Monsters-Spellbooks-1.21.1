package net.redreaper.monsterspellbooks.entity.spells.frost_breath;

import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.effect.HemorrhageMobEffect;
import net.redreaper.monsterspellbooks.effect.SpiritFrostbiteMobEffect;
import net.redreaper.monsterspellbooks.effect.VoidTouchedEffect;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModMobEffects;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class FrostBreathEntity extends AbstractMagicProjectile {
    public static final int lifetime = 100;
    HashMap<UUID, Integer> victims;

    public FrostBreathEntity(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.victims = new HashMap<>();
        this.setNoGravity(true);
    }

    public FrostBreathEntity(Level pLevel, LivingEntity pShooter) {
        this(ModEntities.FROST_BREATH.get(), pLevel);
        this.setOwner(pShooter);
    }

    public void impactParticles(double x, double y, double z) {
    }

    @Override public float getSpeed() {
        return 0.4f;
    }

    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.empty();
    }

    @Override
    public void tick() {
        super.tick();
        this.createParticleSphere();
        if (tickCount > lifetime) {
            discard();
            if (!level().isClientSide) {
                impactParticles(getX(), this.getBoundingBox().getCenter().y, getZ());
            }
        }
    }

    public void trailParticles() {
        Vec3 vec3 = this.getDeltaMovement();
        double d0 = this.getX() - vec3.x;
        double d1 = this.getY() - vec3.y;
        double d2 = this.getZ() - vec3.z;
        int count = Mth.clamp((int)(vec3.lengthSqr() * (double)4.0F), 1, 4);

        for(int i = 0; i < count; ++i) {
            Vec3 random = Utils.getRandomVec3((double)0.25F);
            float f = (float)i / (float)count;
            double x = Mth.lerp((double)f, d0, this.getX());
            double y = Mth.lerp((double)f, d1, this.getY());
            double z = Mth.lerp((double)f, d2, this.getZ());
            this.level().addParticle(ParticleHelper.SNOW_DUST, x - random.x, y + (double)0.5F - random.y, z - random.z, random.x * (double)0.5F, random.y * (double)0.5F, random.z * (double)0.5F);
        }
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
        DamageSources.applyDamage(target, getDamage(), ModSpellRegistry.FROST_BREATH.get().getDamageSource(this, getOwner()));
        if (pResult.getEntity() instanceof LivingEntity livingTarget)
        {
            livingTarget.addEffect(new MobEffectInstance(ModMobEffects.SPIRIT_FROSTBITE, 160, 1));
        }
        victims.put(target.getUUID(), target.tickCount);
    }

    private void createParticleSphere() {
        double radius = (double)(this.getBbWidth());
        int particleCount = 15;

        for(int i = 0; i < particleCount; ++i) {
            double theta = Math.toRadians(this.level().random.nextDouble() * (double)360.0F);
            double phi = Math.toRadians(this.level().random.nextDouble() * (double)180.0F);
            double randomRadius = radius * Math.pow(this.level().random.nextDouble(), 0.3333333333333333);
            double xOffset = randomRadius * Math.sin(phi) * Math.cos(theta);
            double yOffset = randomRadius * Math.cos(phi);
            double zOffset = randomRadius * Math.sin(phi) * Math.sin(theta);
            this.level().addParticle(ParticleHelper.SNOWFLAKE, this.getX() + xOffset, this.getY() + yOffset, this.getZ() + zOffset, (double)0.0F, (double)0.0F, (double)0.0F);
        }
    }
}

