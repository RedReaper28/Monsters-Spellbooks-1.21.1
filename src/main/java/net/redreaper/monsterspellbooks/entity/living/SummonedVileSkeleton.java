package net.redreaper.monsterspellbooks.entity.living;

import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.entity.mobs.IMagicSummon;
import io.redspace.ironsspellbooks.entity.mobs.goals.*;
import io.redspace.ironsspellbooks.util.OwnerHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModParticleTypes;

import javax.annotation.Nullable;
import java.util.UUID;

public class SummonedVileSkeleton extends VileSkeleton implements IMagicSummon {
    protected LivingEntity cachedSummoner;
    protected UUID summonerUUID;

    public SummonedVileSkeleton(EntityType entity, Level world) {
        super(entity, world);
        xpReward = 0;
    }

    public SummonedVileSkeleton(Level level, LivingEntity owner) {
        this(ModEntities.SUMMONED_VILE_SKELETON.get(), level);
        setSummoner(owner);
    }

    @Override
    public void registerGoals() {
        this.goalSelector.getAvailableGoals().removeIf(goal ->
                goal.getGoal() instanceof HurtByTargetGoal || goal.getGoal() instanceof NearestAttackableTargetGoal
        );
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.5f, true));
        this.goalSelector.addGoal(3, new GenericFollowOwnerGoal(this, this::getSummoner, 1.0f, 10, 2, false, 50));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F, 1.0F));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Mob.class, 9.0F));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0, 80));

        this.targetSelector.addGoal(1, new GenericOwnerHurtByTargetGoal(this, this::getSummoner));
        this.targetSelector.addGoal(2, new GenericOwnerHurtTargetGoal(this, this::getSummoner));
        this.targetSelector.addGoal(3, new GenericCopyOwnerTargetGoal(this, this::getSummoner));
        this.targetSelector.addGoal(4, (new GenericHurtByTargetGoal(this, (entity) -> entity == getSummoner())).setAlertOthers());
        this.targetSelector.addGoal(5, new GenericProtectOwnerTargetGoal(this, this::getSummoner));
        super.registerGoals();
    }

    @Override
    public LivingEntity getSummoner() {
        return OwnerHelper.getAndCacheOwner(level(), cachedSummoner, summonerUUID);
    }

    public void setSummoner(@Nullable LivingEntity owner)
    {
        if (owner != null)
        {
            this.summonerUUID = owner.getUUID();
            this.cachedSummoner = owner;
        }
    }
    @Override
    public void die(DamageSource pDamageSource) {
        this.onDeathHelper();
        super.die(pDamageSource);
    }

    @Override
    public void onRemovedFromLevel() {
        this.onRemovedHelper(this);
        super.onRemovedFromLevel();
    }

    @Override
    public void onUnSummon() {
        if (!level().isClientSide)
        {
            MagicManager.spawnParticles(level(), ModParticleTypes.REAPER_EMBERS_PARTICLE.get(),
                    getX(), getY(), getZ(),
                    25,
                    level().random.nextGaussian() * 0.007D,
                    level().random.nextGaussian() * 0.009D,
                    level().random.nextGaussian() * 0.007D,
                    0.1, false);
            discard();
        }
    }

    @Override
    public boolean isAlliedTo(Entity entityIn) {
        if (entityIn == this)
        {
            return true;
        }
        else if (entityIn == getSummoner() || this.isAlliedHelper(entityIn))
        {
            return true;
        }
        else if (getSummoner() != null && !entityIn.isAlliedTo(getSummoner()))
        {
            return false;
        }
        else
        {
            return this.getTeam() == null && entityIn.getTeam() == null;
        }
    }

    // NBT
    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.summonerUUID = OwnerHelper.deserializeOwner(pCompound);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        OwnerHelper.serializeOwner(pCompound, summonerUUID);
    }
}
