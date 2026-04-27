package net.redreaper.monsterspellbooks.entity.living.summons;

import io.redspace.ironsspellbooks.api.entity.IMagicEntity;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.entity.mobs.IMagicSummon;
import io.redspace.ironsspellbooks.entity.mobs.goals.*;
import io.redspace.ironsspellbooks.util.OwnerHelper;
import net.acetheeldritchking.aces_spell_utils.entity.mobs.UniqueAbstractSpellCastingMob;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.UUID;

public class LivingLeafCrystalEntity extends UniqueAbstractSpellCastingMob implements IMagicSummon, GeoAnimatable, IMagicEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    protected LivingEntity cachedSummoner;
    protected UUID summonerUUID;

    public LivingLeafCrystalEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        xpReward = 0;
    }

    public LivingLeafCrystalEntity(Level level, LivingEntity owner) {
        this(ModEntities.LIVING_LEAF_CRYSTAL.get(), level);
    }

    @Override
    public @org.jetbrains.annotations.Nullable SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @org.jetbrains.annotations.Nullable SpawnGroupData spawnGroupData) {
        this.setNoGravity(true);
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(3, new WizardAttackGoal(this, 1.25f, 30, 55)
                .setSpells(
                        List.of(ModSpellRegistry.VITAL_BLAST.get()),
                        List.of(),
                        List.of(),
                        List.of()
                )
                .setSpellQuality(1.0f, 1.0f)
                .setIsFlying()
                .setSpellQuality(0.8f, 0.8f)
                .setAllowFleeing(false)
        );

        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
        this.goalSelector.addGoal(7, new GenericFollowOwnerGoal(this, this::getSummoner, 1.f, 8, 2, false, 50));
        this.targetSelector.addGoal(1, new GenericOwnerHurtByTargetGoal(this, this::getSummoner));
        this.targetSelector.addGoal(2, new GenericOwnerHurtTargetGoal(this, this::getSummoner));
        this.targetSelector.addGoal(3, new GenericCopyOwnerTargetGoal(this, this::getSummoner));
        this.targetSelector.addGoal(4, (new GenericHurtByTargetGoal(this, (entity) -> entity == getSummoner())).setAlertOthers());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.ATTACK_DAMAGE, 10.5)
                .add(Attributes.KNOCKBACK_RESISTANCE, 100.0)
                .add(Attributes.MAX_HEALTH, 50.0)
                .add(Attributes.FOLLOW_RANGE, 25.0)
                .add(Attributes.ENTITY_INTERACTION_RANGE, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0)
                .add(Attributes.GRAVITY, 0)
                ;
    }

    public boolean isAlliedTo(Entity pEntity) {
        return super.isAlliedTo(pEntity) || this.isAlliedHelper(pEntity);
    }

    // Attacks and Death
    @Override
    public void die(DamageSource pDamageSource) {
        this.onDeathHelper();
        super.die(pDamageSource);
    }

    @Override
    public void onRemovedFromLevel() {
        super.onRemovedFromLevel();
    }

    @Override
    public void onUnSummon() {
        if (!this.level().isClientSide) {
            MagicManager.spawnParticles(this.level(), ModParticleHelper.VITAL_SPARKS,
                    getX(), getY(), getZ(),
                    25, 0.4, 0.8, 0.4, 0.03, false);
            discard();
        }
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        return Utils.doMeleeAttack(this, entity, ModSpellRegistry.LEAF_CRYSTAL.get().getDamageSource(this, getSummoner()));
    }

    @Override
    public boolean dampensVibrations() {
        return true;
    }

    @Override
    public boolean canBeAffected(MobEffectInstance effectInstance) {return false;}

    public boolean hurt(DamageSource pSource, float pAmount) {return !this.shouldIgnoreDamage(pSource) && super.hurt(pSource, pAmount);}

    public boolean isPushedByFluid() {
        return false;
    }

    public boolean isOnFire() {
        return false;
    }

    public boolean canCollideWith(Entity entity) {
        return true;
    }

    public boolean canBeHitByProjectile() {
        return true;
    }

    public boolean isPushable() {
        return false;
    }

    public void push(Entity pEntity) {}

    public boolean canBeCollidedWith() {
        return false;
    }

    public boolean canChangeDimensions(Level oldLevel, Level newLevel) {
        return false;
    }

    public void tick() {
        super.tick();
        if (this.getSummoner() != null) {
            var owner = this.getSummoner();
            this.setPos(owner.getX(), owner.getY()+2, owner.getZ());
        }

        if (this.getSummoner() != null && !this.getSummoner().isAlive()) {
            this.discard();
        }
    }

        // Geckolib & Animations
        @Override
        public AnimatableInstanceCache getAnimatableInstanceCache () {
            return this.cache;
        }

        // NBT
        @Override
        public void readAdditionalSaveData (CompoundTag pCompound){
            super.readAdditionalSaveData(pCompound);
            this.summonerUUID = OwnerHelper.deserializeOwner(pCompound);
        }

        @Override
        public void addAdditionalSaveData (CompoundTag pCompound){
            super.addAdditionalSaveData(pCompound);
            OwnerHelper.serializeOwner(pCompound, summonerUUID);
        }
    }


