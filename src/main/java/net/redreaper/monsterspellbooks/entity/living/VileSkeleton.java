package net.redreaper.monsterspellbooks.entity.living;

import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.redreaper.monsterspellbooks.init.ModEntities;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Predicate;

public class VileSkeleton extends Monster implements GeoEntity {
    private static final Predicate<LivingEntity> LIVING_ENTITY_SELECTOR;
    private static final TargetingConditions TARGETING_CONDITIONS;
    public static final EntityDataAccessor<Boolean> SHOOT;
    public static final EntityDataAccessor<String> ANIMATION;
    public static final EntityDataAccessor<String> TEXTURE;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private boolean swinging;
    private boolean lastloop;
    private long lastSwing;
    public String animationprocedure = "empty";
    String prevAnim = "empty";

    public VileSkeleton(EntityType<? extends Monster> type, Level world) {
        super(type, world);
        this.animationprocedure = "empty";
        this.xpReward = 5;
        this.setNoAi(false);
    }

    public void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(SHOOT, false);
        builder.define(ANIMATION, "undefined");
        builder.define(TEXTURE, "vile_skeleton");
    }

    public void setTexture(String texture) {
        this.entityData.set(TEXTURE, texture);
    }

    public String getTexture() {
        return this.entityData.get(TEXTURE);
    }


    public void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, (double)1.0F, false) {
            protected boolean canPerformAttack(@NotNull LivingEntity entity) {
                return this.isTimeToAttack() && this.mob.distanceToSqr(entity) < (double)(this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth()) && this.mob.getSensing().hasLineOfSight(entity);
            }
        });
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, Mob.class, 5, false, false, (p_28879_) -> p_28879_ instanceof Enemy && !(p_28879_ instanceof Creeper)));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, AbstractIllager.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, AbstractPiglin.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, AbstractVillager.class, true));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, VileSkeleton.class).setAlertOthers());
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new RestrictSunGoal(this) {
        });
    }

    public SoundEvent getAmbientSound() {
        return SoundEvents.WITHER_SKELETON_AMBIENT;
    }

    public @NotNull SoundEvent getHurtSound(@NotNull DamageSource damageSource) {
        return SoundEvents.WITHER_SKELETON_HURT;
    }

    public @NotNull SoundEvent getDeathSound() {
        return SoundEvents.WITHER_SKELETON_DEATH;
    }

    public boolean hurt(DamageSource source, float amount) {return !source.is(DamageTypes.DROWN) && super.hurt(source, amount);}

    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putString("Texture", this.getTexture());
    }

    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("Texture")) {
            this.setTexture(compound.getString("Texture"));
        }

    }

    public @NotNull EntityDimensions getDefaultDimensions(@NotNull Pose pose) {
        return super.getDefaultDimensions(pose).scale(1.0F);
    }

    public static void init(RegisterSpawnPlacementsEvent event) {
        event.register((EntityType) ModEntities.VILE_SKELETON.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) -> {
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            return false;
        }, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 1.);
        builder = builder.add(Attributes.MAX_HEALTH, (double)15.0F);
        builder = builder.add(Attributes.ARMOR, (double)0.0F);
        builder = builder.add(Attributes.ATTACK_DAMAGE, (double)4.0F);
        builder = builder.add(Attributes.FOLLOW_RANGE, (double)32.0F);
        builder = builder.add(Attributes.STEP_HEIGHT, 0.6);
        builder = builder.add(Attributes.ATTACK_KNOCKBACK, 0.1);
        return builder;
    }

    private PlayState movementPredicate(AnimationState event) {
        if (!this.animationprocedure.equals("empty")) {
            return PlayState.STOP;
        } else if ((event.isMoving() || !(event.getLimbSwingAmount() > -0.15F) || !(event.getLimbSwingAmount() < 0.15F)) && !this.isAggressive()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("animation.vile_skeleton.walk"));
        } else {
            return this.isAggressive() && event.isMoving() ? event.setAndContinue(RawAnimation.begin().thenLoop("animation.vile_skeleton.angry")) : event.setAndContinue(RawAnimation.begin().thenLoop("animation.vile_skeleton.idle"));
        }
    }

    private PlayState procedurePredicate(AnimationState event) {
        if (!this.animationprocedure.equals("empty") && event.getController().getAnimationState() == AnimationController.State.STOPPED || !this.animationprocedure.equals(this.prevAnim) && !this.animationprocedure.equals("empty")) {
            if (!this.animationprocedure.equals(this.prevAnim)) {
                event.getController().forceAnimationReset();
            }

            event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationprocedure));
            if (event.getController().getAnimationState() == AnimationController.State.STOPPED) {
                this.animationprocedure = "empty";
                event.getController().forceAnimationReset();
            }
        } else if (this.animationprocedure.equals("empty")) {
            this.prevAnim = "empty";
            return PlayState.STOP;
        }

        this.prevAnim = this.animationprocedure;
        return PlayState.CONTINUE;
    }

    protected void tickDeath() {
        ++this.deathTime;
        if (this.deathTime == 20) {
            this.remove(RemovalReason.KILLED);
            this.dropExperience(this);
        }

    }

    public boolean doHurtTarget(Entity entity) {
        if (!super.doHurtTarget(entity)) {
            return false;
        } else {
            if (entity instanceof LivingEntity) {
                ((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.WITHER, 200), this);
                ((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffectRegistry.REND, 200,2), this);
            }

            return true;
        }
    }

    public boolean canBeAffected(MobEffectInstance potioneffect) {
        return potioneffect.is(MobEffects.WITHER) ? false : super.canBeAffected(potioneffect);
    }

    static {
        LIVING_ENTITY_SELECTOR = (p_348303_) -> !p_348303_.getType().is(EntityTypeTags.WITHER_FRIENDS) && p_348303_.attackable();
        TARGETING_CONDITIONS = TargetingConditions.forCombat().range((double)20.0F).selector(LIVING_ENTITY_SELECTOR);
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController[]{new AnimationController(this, "movement", 1, this::movementPredicate)});
        data.add(new AnimationController[]{new AnimationController(this, "procedure", 1, this::procedurePredicate)});
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    static {
        SHOOT = SynchedEntityData.defineId(VileSkeleton.class, EntityDataSerializers.BOOLEAN);
        ANIMATION = SynchedEntityData.defineId(VileSkeleton.class, EntityDataSerializers.STRING);
        TEXTURE = SynchedEntityData.defineId(VileSkeleton.class, EntityDataSerializers.STRING);
    }
}
