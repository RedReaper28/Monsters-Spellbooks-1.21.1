package net.redreaper.monsterspellbooks.entity.living;

import io.redspace.ironsspellbooks.api.entity.IMagicEntity;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.entity.mobs.goals.WizardAttackGoal;
import net.acetheeldritchking.aces_spell_utils.entity.mobs.UniqueAbstractSpellCastingMob;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;
import net.redreaper.monsterspellbooks.init.ModEntities;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public class DripplerEntity extends UniqueAbstractSpellCastingMob implements GeoAnimatable,IMagicEntity,Enemy {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public DripplerEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        xpReward = 0;
        this.lookControl = createLookControl();
        this.moveControl = new FlyingMoveControl(this, 20, true);
    }

    public DripplerEntity(Level level, LivingEntity owner) {
        this(ModEntities.DRIPPLER.get(), level);
    }

    protected LookControl createLookControl() {
        return new LookControl(this) {
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

    protected PathNavigation createNavigation(Level pLevel) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, pLevel);
        flyingpathnavigation.setCanOpenDoors(true);
        flyingpathnavigation.setCanFloat(true);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(3, new WizardAttackGoal(this, 1.25f, 30, 55)
                .setSpells(
                        List.of(
                                SpellRegistry.BLOOD_NEEDLES_SPELL.get(),
                                SpellRegistry.RAY_OF_SIPHONING_SPELL.get()
                        ),
                        List.of(),
                        List.of(),
                        List.of())
                .setSpellQuality(1.0f, 1.0f)
                .setIsFlying()
                .setSpellQuality(0.8f, 0.8f)
                .setAllowFleeing(false)
        );
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomFlyingGoal(this, 1.0));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, DripplerEntity.class).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Villager.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractGolem.class, true));
    }
    public static AttributeSupplier.Builder createAttributes()
    {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.ATTACK_DAMAGE, 5.0)
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.FOLLOW_RANGE, 45.0)
                .add(Attributes.ENTITY_INTERACTION_RANGE, 8.0)
                .add(Attributes.MOVEMENT_SPEED, .2)
                .add(Attributes.FLYING_SPEED, 0.25)
                .add(AttributeRegistry.SPELL_POWER, 1.1)
                .add(AttributeRegistry.SPELL_RESIST, 1.1);
    }

    @Override
    public boolean dampensVibrations() {return true;}

    @Override public boolean isNoGravity() {
        return true;
    }

    @Override public boolean canBeAffected(MobEffectInstance effectInstance) {return false;}

    @Override protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {}

    public static boolean isDarkEnoughToSpawn(ServerLevelAccessor level, BlockPos pos, RandomSource random) {
        if (level.getBrightness(LightLayer.SKY, pos) > random.nextInt(32)) {
            return false;
        } else {
            DimensionType dimensiontype = level.dimensionType();
            int i = dimensiontype.monsterSpawnBlockLightLimit();
            if (i < 15 && level.getBrightness(LightLayer.BLOCK, pos) > i) {
                return false;
            } else {
                int j = level.getLevel().isThundering() ? level.getMaxLocalRawBrightness(pos, 10) : level.getMaxLocalRawBrightness(pos);
                return j <= dimensiontype.monsterSpawnLightTest().sample(random);
            }
        }
    }

    public static boolean checkDripplerSpawnRules(EntityType<? extends Monster> type, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return level.getDifficulty() != Difficulty.PEACEFUL && (MobSpawnType.ignoresLightRequirements(spawnType) || isDarkEnoughToSpawn(level, pos, random)) && checkMobSpawnRules(type, level, spawnType, pos, random);
    }

    public static boolean checkAnyLightMonsterSpawnRules(EntityType<? extends Monster> type, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return level.getDifficulty() != Difficulty.PEACEFUL && checkMobSpawnRules(type, level, spawnType, pos, random);
    }

    protected boolean shouldDespawnInPeaceful() {return true;}

    public boolean shouldDropExperience() {
        return true;
    }

    protected boolean shouldDropLoot() {
        return true;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
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
