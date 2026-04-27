package net.redreaper.monsterspellbooks.entity.spells.leaf_crystal;

import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.redreaper.monsterspellbooks.init.ModEntities;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.UUID;

public class LeafCrystalEntity extends Entity implements GeoEntity {
    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerUUID;
    private int duration;
    private static final EntityDataAccessor<Integer> OWNER_ID;
    private int age;
    private final RawAnimation idle;
    private final AnimatableInstanceCache cache;

    public LeafCrystalEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.duration = 300;
        this.idle = RawAnimation.begin().thenLoop("idle");
        this.cache = GeckoLibUtil.createInstanceCache(this);
    }

    public LeafCrystalEntity(Level level, LivingEntity owner, int duration) {
        this(ModEntities.LEAF_CRYSTAL.get(), level);
        this.setOwner(owner);
        this.duration = duration;
    }

    public boolean isPushedByFluid() {
        return false;
    }

    public boolean isOnFire() {
        return false;
    }

    public boolean canCollideWith(Entity entity) {
        return false;
    }

    public boolean canBeHitByProjectile() {
        return false;
    }

    public boolean hurt(DamageSource pSource, float pAmount) {
        return false;
    }

    public boolean isInvulnerable() {
        return true;
    }

    public boolean isPushable() {
        return false;
    }

    public void push(Entity pEntity) {
    }

    public boolean canBeCollidedWith() {
        return false;
    }

    public boolean canChangeDimensions(Level oldLevel, Level newLevel) {
        return false;
    }

    public void tick() {
        super.tick();
        if (this.getOwner() != null) {
            var owner = this.getOwner();
            this.setPos(owner.getX(), owner.getY() + 3, owner.getZ());
        }

        if (this.getOwner() != null && this.getOwner().getActiveEffects().stream().noneMatch(this::isNatureEffect)) {
            this.discard();
        }

        if (this.getOwner() != null && !this.getOwner().isAlive()) {
                this.discard();
        }

        ++this.age;
    }

    private boolean isNatureEffect(MobEffectInstance effect) {
        return effect.is(MobEffectRegistry.OAKSKIN);
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(OWNER_ID, -1);
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        this.age = pCompound.getInt("Age");
        this.duration = pCompound.getInt("Duration");
        if (pCompound.hasUUID("Owner")) {
            this.ownerUUID = pCompound.getUUID("Owner");
        }

    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putInt("Age", this.age);
        pCompound.putInt("Duration", this.duration);
        if (this.ownerUUID != null) {
            pCompound.putUUID("Owner", this.ownerUUID);
        }

    }

    public void setOwner(@Nullable LivingEntity pOwner) {
        this.owner = pOwner;
        this.ownerUUID = pOwner == null ? null : pOwner.getUUID();
        this.entityData.set(OWNER_ID, pOwner == null ? -1 : pOwner.getId());
    }

    @Nullable
    public LivingEntity getOwner() {
        if (this.owner == null) {
            if (this.level() instanceof ServerLevel && this.ownerUUID != null) {
                Entity entity = ((ServerLevel)this.level()).getEntity(this.ownerUUID);
                if (entity instanceof LivingEntity) {
                    LivingEntity le = (LivingEntity)entity;
                    this.owner = le;
                }
            } else if (this.level().isClientSide) {
                int id = (Integer)this.entityData.get(OWNER_ID);
                if (id != -1) {
                    Entity entity = this.level().getEntity(id);
                    if (entity instanceof LivingEntity) {
                        LivingEntity le = (LivingEntity)entity;
                        this.owner = le;
                    }
                }
            }
        }

        return this.owner;
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController(this, "idle", 0, this::idlePredicate));
    }

    private PlayState idlePredicate(AnimationState event) {
        event.getController().setAnimation(this.idle);
        return PlayState.CONTINUE;
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    static {
        OWNER_ID = SynchedEntityData.defineId(LeafCrystalEntity.class, EntityDataSerializers.INT);
    }
}

