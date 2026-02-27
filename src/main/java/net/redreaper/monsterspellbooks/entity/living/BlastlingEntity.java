package net.redreaper.monsterspellbooks.entity.living;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.entity.mobs.IAnimatedAttacker;
import io.redspace.ironsspellbooks.entity.mobs.goals.WizardAttackGoal;
import net.acetheeldritchking.aces_spell_utils.entity.mobs.UniqueAbstractSpellCastingMob;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModSounds;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public class BlastlingEntity extends UniqueAbstractSpellCastingMob implements GeoAnimatable,IAnimatedAttacker,Enemy {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public BlastlingEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        xpReward = 10;
        this.lookControl = createLookControl();
        this.moveControl = createMoveControl();
        noCulling = true;
    }

    public BlastlingEntity(Level level, LivingEntity owner) {
        this(ModEntities.BLASTLING.get(), level);
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
        this.goalSelector.addGoal(3, new WizardAttackGoal(this, 1.25f, 50, 100)
                .setSpells(
                        // Attack
                        List.of(
                                ModSpellRegistry.SPACE_RUPTURE.get()
                        ),
                        // Defense
                        List.of(),
                        // Movement
                        List.of(
                                SpellRegistry.TELEPORT_SPELL.get()
                        ),
                        // Support
                        List.of()
                )
                .setSpellQuality(1.0f, 1.0f)
                .setIsFlying()
                .setSpellQuality(0.8f, 0.8f)
                .setAllowFleeing(true)
        );
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, EnderMan.class).setAlertOthers());
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, BlastlingEntity.class).setAlertOthers());
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 15.0F));
    }

    public static AttributeSupplier.Builder createAttributes()
    {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.ATTACK_DAMAGE, 10)
                .add(Attributes.ATTACK_KNOCKBACK, 1.0)
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.KNOCKBACK_RESISTANCE, 2)
                .add(Attributes.FOLLOW_RANGE, 30.0)
                .add(Attributes.ENTITY_INTERACTION_RANGE, 2.0)
                .add(Attributes.MOVEMENT_SPEED, .15);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState block) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    public SoundEvent getAmbientSound() {return ModSounds.BLASTLING_AMBIENT.get();}

    public SoundEvent getStepSound() {
        return ModSounds.BLASTLING_STEP.get();
    }

    public @NotNull SoundEvent getHurtSound(@NotNull DamageSource damageSource) {return ModSounds.BLASTLING_HURT.get();}

    public @NotNull SoundEvent getDeathSound() {
        return ModSounds.BLASTLING_DEATH.get();
    }

    // Attacks and Death
    @Override
    public void die(DamageSource pDamageSource) {
        super.die(pDamageSource);
    }

    protected boolean shouldDespawnInPeaceful() {return true;}

    public boolean shouldDropExperience() {
        return true;
    }

    protected boolean shouldDropLoot() {
        return true;
    }

    // Geckolib & Animations
    RawAnimation animationToPlay = null;
    private final AnimationController<BlastlingEntity> attackAnimationController = new AnimationController<>(this, "attack_controller", 0, this::attackPredicate);
    private final AnimationController<BlastlingEntity> animationController = new AnimationController<>(this, "controller", 0, this::predicate);
    private final AnimationController<BlastlingEntity> castingAnimationController = new AnimationController<>(this, "casting_controller", 0, this::castingPredicate);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(animationController);
        controllers.add(attackAnimationController);
        controllers.add(castingAnimationController);
    }

    private PlayState predicate(AnimationState<BlastlingEntity> event)
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

    private PlayState attackPredicate(AnimationState<BlastlingEntity> event)
    {
        var controller = event.getController();

        if (this.animationToPlay != null)
        {
            controller.forceAnimationReset();
            controller.setAnimation(animationToPlay);
            animationToPlay = null;
        }

        return PlayState.CONTINUE;
    }

    private PlayState castingPredicate(AnimationState<BlastlingEntity> event)
    {
        var controller = event.getController();
        if (isCasting() && this.animationToPlay == null)
        {
            controller.forceAnimationReset();
            event.getController().setAnimation(RawAnimation.begin().thenPlay("long_cast"));
        }
        return PlayState.CONTINUE;
    }

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

    // NBT
    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
    }
}

