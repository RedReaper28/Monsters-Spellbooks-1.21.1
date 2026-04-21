package net.redreaper.monsterspellbooks.entity.living.summons;

import io.redspace.ironsspellbooks.api.util.Utils;
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
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.redreaper.monsterspellbooks.entity.living.PrismarineKeeper;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;

import java.util.UUID;

public class SummonedPrismarineKeeper extends PrismarineKeeper implements IMagicSummon {
    protected UUID summonerUUID;

    public SummonedPrismarineKeeper(EntityType entity, Level world) {
        super(entity, world);
        xpReward = 0;
    }

    public SummonedPrismarineKeeper(Level level, LivingEntity owner) {
        this(ModEntities.SUMMONED_PRISMARINE_KEEPER.get(), level);
    }

    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(7, new GenericFollowOwnerGoal(this, this::getSummoner, 1.f, 8, 2, false, 50));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));

        this.targetSelector.addGoal(1, new GenericOwnerHurtByTargetGoal(this, this::getSummoner));
        this.targetSelector.addGoal(2, new GenericOwnerHurtTargetGoal(this, this::getSummoner));
        this.targetSelector.addGoal(3, new GenericCopyOwnerTargetGoal(this, this::getSummoner));
        this.targetSelector.addGoal(4, (new GenericHurtByTargetGoal(this, (entity) -> entity == getSummoner())).setAlertOthers());
        this.targetSelector.addGoal(5, new GenericProtectOwnerTargetGoal(this, this::getSummoner));
        super.registerGoals();
    }

    @Override
    public boolean isAlliedTo(Entity entityIn) {
        return super.isAlliedTo(entityIn) || this.isAlliedHelper(entityIn);
    }

    // Attacks and Death
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
        if (!this.level().isClientSide)
        {
            MagicManager.spawnParticles(this.level(), ModParticleHelper.HYDRO_BUBBLE,
                    getX(), getY(), getZ(),
                    25, 0.4, 0.8, 0.4, 0.03, false);
            discard();
        }
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        return Utils.doMeleeAttack(this, entity, ModSpellRegistry.SUMMON_PRISMARINE_SQUAD.get().getDamageSource(this, getSummoner()));
    }

    public boolean hurt(DamageSource pSource, float pAmount) {
        return !this.shouldIgnoreDamage(pSource) && super.hurt(pSource, pAmount);
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return false;
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
