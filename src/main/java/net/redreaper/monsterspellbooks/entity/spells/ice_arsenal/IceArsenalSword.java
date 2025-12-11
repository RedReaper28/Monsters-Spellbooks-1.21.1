package net.redreaper.monsterspellbooks.entity.spells.ice_arsenal;

import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;

import java.util.Optional;

public class IceArsenalSword extends AbstractMagicProjectile {
    private static final EntityDataAccessor<Float> DATA_Z_ROT = SynchedEntityData.defineId(IceArsenalSword.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> DATA_SCALE = SynchedEntityData.defineId(IceArsenalSword.class, EntityDataSerializers.FLOAT);

    public IceArsenalSword(EntityType<? extends IceArsenalSword> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);
    }

    public IceArsenalSword(Level levelIn, LivingEntity shooter) {super(ModEntities.ICE_SWORD.get(), levelIn);setOwner(shooter);}

    public void setZRot(float zRot) {if (!level().isClientSide) entityData.set(DATA_Z_ROT, zRot);}

    public void setScale(float scale) {if (!level().isClientSide) entityData.set(DATA_SCALE, scale);}

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        pBuilder.define(DATA_Z_ROT, 0f);
        pBuilder.define(DATA_SCALE, 1f);
        super.defineSynchedData(pBuilder);
    }

    public float getZRot() {
        return entityData.get(DATA_Z_ROT);
    }

    public float getScale() {
        return entityData.get(DATA_SCALE);
    }

    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putFloat("zRot", getZRot());
        if (getScale() != 1)
            tag.putFloat("Scale", getScale());
    }

    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setZRot(tag.getFloat("zRot"));
        if (tag.contains("Scale"))
            setScale(tag.getFloat("Scale"));
    }

    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        DamageSources.applyDamage(entityHitResult.getEntity(), getDamage(), ModSpellRegistry.ICE_ARSENAL.get().getDamageSource(this, getOwner()));
    }

    protected void onHit(HitResult hitresult) {
        super.onHit(hitresult);
        discard();
    }

    private static int soundTimestamp;

    @Override
    protected void doImpactSound(Holder<SoundEvent> sound) {
        if (soundTimestamp != this.tickCount) {
            super.doImpactSound(sound);
            soundTimestamp = this.tickCount;
        }
    }

    public void trailParticles() {

        for (int i = 0; i < 2; i++) {
            double speed = .05;
            double dx = Utils.random.nextDouble() * 2 * speed - speed;
            double dy = Utils.random.nextDouble() * 2 * speed - speed;
            double dz = Utils.random.nextDouble() * 2 * speed - speed;
            level().addParticle(ParticleHelper.SNOWFLAKE, this.getX() + dx, this.getY() + dy, this.getZ() + dz, dx, dy, dz);

        }
    }

    public void impactParticles(double x, double y, double z) {
        MagicManager.spawnParticles(level(), ParticleHelper.ICY_FOG, x, y, z, 15, .1, .1, .1, .18, true);
    }

    public float getSpeed() {
        return 2.5f;
    }

    @Override
    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.of(SoundRegistry.ICE_IMPACT);
    }
}
