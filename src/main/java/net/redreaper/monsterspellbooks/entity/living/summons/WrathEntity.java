package net.redreaper.monsterspellbooks.entity.living.summons;

import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.mobs.goals.WispAttackGoal;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import net.redreaper.monsterspellbooks.spells.fire.WrathSpell;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.UUID;

public class WrathEntity extends PathfinderMob implements GeoEntity {

    @Nullable
    private UUID ownerUUID;

    @Nullable
    private Entity cachedOwner;

    private final RawAnimation animation = RawAnimation.begin().thenPlay("idle");

    private Vec3 targetSearchStart;
    private Vec3 lastTickPos;
    private float damageAmount;
    private float explosionRadius;


    public WrathEntity(EntityType<? extends WrathEntity> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);
    }

    public WrathEntity(Level levelIn, LivingEntity owner, float damageAmount,float explosionRadius) {
        this(ModEntities.WRATH.get(), levelIn);
        this.moveControl = new FlyingMoveControl(this, 90, true);
        this.damageAmount = damageAmount;
        this.explosionRadius = explosionRadius;

        setOwner(owner);

        var xRot = owner.getXRot();
        var yRot = owner.getYRot();
        var yHeadRot = owner.getYHeadRot();

        this.setYRot(yRot);
        this.setXRot(xRot);
        this.setYBodyRot(yRot);
        this.setYHeadRot(yHeadRot);
        this.lastTickPos = this.position();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new WispAttackGoal(this, 1));
    }

    public static boolean isValidTarget(@Nullable Entity entity) {
        return entity instanceof LivingEntity livingEntity &&
                livingEntity.isAlive() &&
                livingEntity instanceof Enemy;
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public LivingEntity getTarget() {
        return super.getTarget();
    }

    public float getExplosionRadius() {return this.explosionRadius;}

    @Override
    public void tick() {
        super.tick();
        AABB bb = this.getBoundingBox();

        if (level().isClientSide) {
            spawnParticles();
        } else {
            var target = this.getTarget();
            if (target == null || target.isRemoved()) {
                if (tickCount > 10) {
                    this.popAndDie();
                }
            } else {
                if (this.getBoundingBox().intersects(target.getBoundingBox())) {
                    float explosionRadius = getExplosionRadius();
                    var explosionRadiusSqr = explosionRadius * explosionRadius;
                    var entities = level().getEntities(this, this.getBoundingBox().inflate(explosionRadius));
                    Vec3 losPoint = Utils.raycastForBlock(this.level(), this.position(), this.position().add((double)0.0F, (double)2.0F, (double)0.0F), ClipContext.Fluid.NONE).getLocation();

                    for (Entity entity : entities) {
                        double distanceSqr = entity.distanceToSqr(bb.getCenter());
                        if (distanceSqr < explosionRadiusSqr && hasLineOfSight(entity) && Utils.hasLineOfSight(level(), losPoint, entity.getBoundingBox().getCenter(), true)) {
                            DamageSources.applyDamage(entity, damageAmount, ModSpellRegistry.WRATH.get().getDamageSource(this, cachedOwner));
                        }
                    }
                    var p = target.getEyePosition();
                    this.playSound(WrathSpell.getImpactSound(), 1.0f, 1.0f);
                    MagicManager.spawnParticles(level(), new BlastwaveParticleOptions(SchoolRegistry.FIRE.get().getTargetingColor(), getExplosionRadius()),p.x, p.y, p.z, 1, 0, 0, 0, 0, true);
                    MagicManager.spawnParticles(level(), ParticleHelper.FIERY_SMOKE, p.x, p.y, p.z, 25, 0, 0, 0, .18, true);
                    discard();
                }
            }
        }
        lastTickPos = this.position();
    }

    public void setOwner(@Nullable Entity pOwner) {
        if (pOwner != null) {
            this.ownerUUID = pOwner.getUUID();
            this.cachedOwner = pOwner;
        }
    }

    @Override
    protected @NotNull PathNavigation createNavigation(Level pLevel) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, pLevel) {
            public boolean isStableDestination(BlockPos blockPos) {
                return !this.level.getBlockState(blockPos.below()).isAir();
            }

            public void tick() {
                super.tick();
            }
        };
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(true);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }

    @Override
    public void travel(Vec3 pTravelVector) {
        if (this.isEffectiveAi() || this.isControlledByLocalInstance()) {
            if (this.isInWater()) {
                this.moveRelative(0.02F, pTravelVector);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale((double) 0.8F));
            } else if (this.isInLava()) {
                this.moveRelative(0.02F, pTravelVector);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale(0.5D));
            } else {
                this.moveRelative(this.getSpeed(), pTravelVector);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale((double) 0.91F));
            }
        }

        this.calculateEntityAnimation(false);
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public void setTarget(@org.jetbrains.annotations.Nullable LivingEntity target) {
        super.setTarget(target);

    }

    @Override
    protected void customServerAiStep() {
        if (this.cachedOwner == null || !this.cachedOwner.isAlive()) {
            this.discard();
        }
    }

    private PlayState predicate(AnimationState event) {
        event.getController().setAnimation(animation);
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public static AttributeSupplier.Builder createAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.ATTACK_KNOCKBACK, 1.0)
                .add(Attributes.ATTACK_DAMAGE, 5.0)
                .add(Attributes.MAX_HEALTH, 50.0)
                .add(Attributes.FOLLOW_RANGE, 60.0)
                .add(Attributes.FLYING_SPEED, .15)
                .add(Attributes.MOVEMENT_SPEED, .15);
    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return Collections.singleton(ItemStack.EMPTY);
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot pSlot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setItemSlot(EquipmentSlot pSlot, ItemStack pStack) {

    }

    public boolean hurt(DamageSource pSource, float pAmount) {
        if (!this.level().isClientSide) {
            this.popAndDie();
        }

        return true;
    }

    private void popAndDie() {
        this.playSound(SoundEvents.GENERIC_BURN, 1.0F, 1.0F);
        ((ServerLevel) this.level()).sendParticles(ParticleHelper.FIRE, this.getX(), this.getY(), this.getZ(), 15, 0.2D, 0.2D, 0.2D, 0.0D);
        this.discard();
    }

    @Override
    public HumanoidArm getMainArm() {
        return HumanoidArm.LEFT;
    }

    public void spawnParticles() {

    }
}

