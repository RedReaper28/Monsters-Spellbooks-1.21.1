package net.redreaper.monsterspellbooks.entity.spells.at_shield;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.entity.living.summons.SummonWeaponEntity;
import net.redreaper.monsterspellbooks.utils.RotationTools;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class AtShield extends SummonWeaponEntity implements GeoEntity {
    private static final int SPAWN_POSE_STAY_TICK = 2;

    private static final EntityDataAccessor<Integer> ANIMATION_STATE = SynchedEntityData.defineId(AtShield.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> GUARD_FLASH_SERIAL = SynchedEntityData.defineId(AtShield.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> ANIMATION_SPEED = SynchedEntityData.defineId(AtShield.class, EntityDataSerializers.FLOAT);

    private static final RawAnimation ANIM_SPAWN = RawAnimation.begin().thenPlayAndHold("spawn");
    private static final RawAnimation ANIM_GUARD_STANCE = RawAnimation.begin().thenPlayAndHold("guard_stance");

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public AtShield(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public AtShield(EntityType<?> pEntityType, Level pLevel, LivingEntity owner) {
        super(pEntityType, pLevel, owner);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(ANIMATION_STATE, AnimationState.SPAWN.id);
        builder.define(GUARD_FLASH_SERIAL, 0);
        builder.define(ANIMATION_SPEED, 1.0f);
    }

    @Override
    public void onClientRemoval() {
        super.onClientRemoval();
    }

    @Override
    public void tickOnServer(ServerLevel level) {
        if (!(getOwner() instanceof LivingEntity owner)) {
            discard();
            return;
        }

        followTargetPosition(getStandbyPosition());
        var animationState = AnimationState.of(entityData.get(ANIMATION_STATE));

        switch (animationState) {
            case SPAWN -> {
                applyGuardRotation(owner);
                if (tickCount >= SPAWN_POSE_STAY_TICK) {
                    entityData.set(ANIMATION_STATE, AnimationState.GUARD_STANCE.id);
                }
            }
            case GUARD_STANCE -> applyGuardRotation(owner);
        }

        hasImpulse = true;
    }

    @Override
    public Vec3 getStandbyPosition() {
        if (getOwner() instanceof LivingEntity owner) {
            return RotationTools.calculateBehindPosition(owner, -2, 0, -1);
        }

        return Vec3.ZERO;
    }

    private void applyGuardRotation(LivingEntity owner) {
        setYRot(owner.getYRot());
        setXRot(0.0f);
        setRot(getYRot(), getXRot());
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(
                this, "main", 0,
                state -> {
                    var animationState = AnimationState.of(entityData.get(ANIMATION_STATE));
                    switch (animationState) {
                        case SPAWN -> state.setAnimation(ANIM_SPAWN);
                        case GUARD_STANCE -> state.setAnimation(ANIM_GUARD_STANCE);
                    }
                    return PlayState.CONTINUE;
                }
        ).setAnimationSpeedHandler(e -> (double) e.entityData.get(ANIMATION_SPEED)));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void onSyncedDataUpdated(@NotNull EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);
        if (!level().isClientSide || !GUARD_FLASH_SERIAL.equals(key)) {
            return;
        }
    }


    private enum AnimationState {
        SPAWN(0),
        GUARD_STANCE(1);

        private final int id;

        AnimationState(int id) {
            this.id = id;
        }

        private static AnimationState of(int rawId) {
            return switch (rawId) {
                case 1 -> GUARD_STANCE;
                default -> SPAWN;
            };
        }
    }
}
