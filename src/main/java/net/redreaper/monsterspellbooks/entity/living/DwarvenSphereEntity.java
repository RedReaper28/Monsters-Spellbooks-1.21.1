package net.redreaper.monsterspellbooks.entity.living;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.entity.mobs.IAnimatedAttacker;
import io.redspace.ironsspellbooks.entity.mobs.goals.melee.AttackAnimationData;
import io.redspace.ironsspellbooks.entity.mobs.wizards.GenericAnimatedWarlockAttackGoal;
import net.acetheeldritchking.aces_spell_utils.entity.mobs.UniqueAbstractSpellCastingMob;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModTags;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.EnumSet;
import java.util.List;

public class DwarvenSphereEntity extends UniqueAbstractSpellCastingMob implements GeoAnimatable, IAnimatedAttacker, Enemy {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    protected static final EntityDataAccessor<Byte> DATA_FLAGS_ID;

    public DwarvenSphereEntity(EntityType<DwarvenSphereEntity> type, Level world) {
        super(type, world);
        this.lookControl = createLookControl();
        this.moveControl = createMoveControl();
        this.xpReward = 5;
    }

    public DwarvenSphereEntity(Level level, LivingEntity owner) {
        this(ModEntities.DWARVEN_SPHERE.get(), level);
    }
    protected LookControl createLookControl()
    {
        return new LookControl(this)
        {
            @Override
            protected float rotateTowards(float from, float to, float maxDelta) {
                return super.rotateTowards(from, to, maxDelta * 2.5F);
            }

            @Override
            protected boolean resetXRotOnTick() {
                return getTarget() == null;
            }
        };
    }

    protected MoveControl createMoveControl()
    {
        return new MoveControl(this)
        {
            @Override
            protected float rotlerp(float sourceAngle, float targetAngle, float maximumChange) {
                double x = this.wantedX - this.mob.getX();
                double z = this.wantedZ - this.mob.getZ();

                if (x * x + z * z < 0.5F)
                {
                    return sourceAngle;
                }
                else
                {
                    return super.rotlerp(sourceAngle, targetAngle, maximumChange * 0.25F);
                }
            }
        };
    }

    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(4, new DwarvenSphereEntity.VexChargeAttackGoal());
        this.goalSelector.addGoal(1, new GenericAnimatedWarlockAttackGoal<>(this, 1.5F, 5, 10)
                .setMoveset(List.of(
                        new AttackAnimationData(20, "sword_stab", 15),
                        new AttackAnimationData(15, "sword_double_slash", 8),
                        new AttackAnimationData(20, "sword_single_upward", 7),
                        new AttackAnimationData(20, "sword_single_horizontal", 10)
                ))
                .setComboChance(.4f)
                .setMeleeAttackInverval(10, 20)
                .setMeleeBias(0.8f, 2f)
                .setMeleeMovespeedModifier(1.5f)
        );
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 0.9D));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, false, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractIllager.class, true));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, DwarvenSphereEntity.class).setAlertOthers());
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, DwarvenSlicerEntity.class).setAlertOthers());
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }


    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, .5);
        builder = builder.add(Attributes.MAX_HEALTH, 30.0F);
        builder = builder.add(Attributes.ARMOR, 15.0F);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 6.0F);
        builder = builder.add(Attributes.ENTITY_INTERACTION_RANGE, 3.5F);
        builder = builder.add(Attributes.FOLLOW_RANGE, 20.0F);
        builder = builder.add(Attributes.STEP_HEIGHT, 0.6);
        builder = builder.add(Attributes.ATTACK_KNOCKBACK, 0.1);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.5);
        return builder;
    }

    public boolean isAlliedTo(Entity entity) {
        if (super.isAlliedTo(entity)) {
            return true;
        } else {
            return entity.getType().is(ModTags.Entities.DWARVEN_CONSTRUCT) && this.getTeam() == null && entity.getTeam() == null;
        }
    }

    protected SoundEvent getAmbientSound() {return SoundEvents.IRON_GOLEM_REPAIR;}

    protected SoundEvent getDeathSound() {
        return SoundEvents.SNOW_GOLEM_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.IRON_GOLEM_HURT;
    }

    @Override protected void playStepSound(BlockPos pos, BlockState block) {this.playSound(SoundEvents.IRON_GOLEM_STEP, 1.0F, 1.0F);}

    @Override protected boolean shouldDespawnInPeaceful() {
        return true;
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_FLAGS_ID, (byte)0);
    }

    private boolean getVexFlag(int mask) {
        int i = (Byte)this.entityData.get(DATA_FLAGS_ID);
        return (i & mask) != 0;
    }

    private void setVexFlag(int mask, boolean value) {
        int i = (Byte)this.entityData.get(DATA_FLAGS_ID);
        if (value) {
            i |= mask;
        } else {
            i &= ~mask;
        }

        this.entityData.set(DATA_FLAGS_ID, (byte)(i & 255));
    }

    public boolean isCharging() {
        return this.getVexFlag(1);
    }

    public void setIsCharging(boolean charging) {
        this.setVexFlag(1, charging);
    }

    class VexChargeAttackGoal extends Goal {
        public VexChargeAttackGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse() {
            LivingEntity livingentity = DwarvenSphereEntity.this.getTarget();
            return livingentity != null && livingentity.isAlive() && !DwarvenSphereEntity.this.getMoveControl().hasWanted() && DwarvenSphereEntity.this.random.nextInt(reducedTickDelay(7)) == 0 && DwarvenSphereEntity.this.distanceToSqr(livingentity) > (double) 4.0F;
        }

        public boolean canContinueToUse() {
            return DwarvenSphereEntity.this.getMoveControl().hasWanted() && DwarvenSphereEntity.this.isCharging() && DwarvenSphereEntity.this.getTarget() != null && DwarvenSphereEntity.this.getTarget().isAlive();
        }

        public void start() {
            LivingEntity livingentity = DwarvenSphereEntity.this.getTarget();
            if (livingentity != null) {
                Vec3 vec3 = livingentity.getEyePosition();
                DwarvenSphereEntity.this.moveControl.setWantedPosition(vec3.x, vec3.y, vec3.z, (double)1.0F);
            }

            DwarvenSphereEntity.this.setIsCharging(true);
            DwarvenSphereEntity.this.playSound(SoundEvents.MINECART_RIDING, 1.0F, 1.0F);
        }

        public void stop() {
            DwarvenSphereEntity.this.setIsCharging(false);
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            LivingEntity livingentity = DwarvenSphereEntity.this.getTarget();
            if (livingentity != null) {
                if (DwarvenSphereEntity.this.getBoundingBox().intersects(livingentity.getBoundingBox())) {
                    DwarvenSphereEntity.this.doHurtTarget(livingentity);
                    DwarvenSphereEntity.this.setIsCharging(false);
                } else {
                    double d0 = DwarvenSphereEntity.this.distanceToSqr(livingentity);
                    if (d0 < (double)9.0F) {
                        Vec3 vec3 = livingentity.getEyePosition();
                        DwarvenSphereEntity.this.moveControl.setWantedPosition(vec3.x, vec3.y, vec3.z, (double)1.0F);
                    }
                }
            }

        }
    }

    static {
        DATA_FLAGS_ID = SynchedEntityData.defineId(DwarvenSphereEntity.class, EntityDataSerializers.BYTE);
    }

    // Geckolib & Animations
    RawAnimation animationToPlay = null;
    private final AnimationController<DwarvenSphereEntity> attackAnimationController = new AnimationController<>(this, "attack_controller", 0, this::attackPredicate);
    private final AnimationController<DwarvenSphereEntity> animationController = new AnimationController<>(this, "controller", 0, this::predicate);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(animationController);
        controllers.add(attackAnimationController);
    }

    public PlayState predicate(AnimationState event)
    {
        if (event.isMoving() && this.animationToPlay == null)
        {
            event.getController().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        else if (!event.isMoving() && this.animationToPlay == null)
        {
            event.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }

    private PlayState attackPredicate(AnimationState<DwarvenSphereEntity> event)
    {
        var controller = event.getController();

        if (this.animationToPlay != null)
        {
            // This should do the custom attack animations
            controller.forceAnimationReset();
            controller.setAnimation(animationToPlay);
            animationToPlay = null;
        }

        return PlayState.CONTINUE;
    }

    @Override
    public void playAnimation(String animationId) {
        try {
            animationToPlay = RawAnimation.begin().thenPlay(animationId);
        } catch (Exception ignored) {
            IronsSpellbooks.LOGGER.error("Entity {} Failed to play animation: {}", this, animationId);
        }
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public double getTick(Object object) {
        return this.tickCount;
    }
}
