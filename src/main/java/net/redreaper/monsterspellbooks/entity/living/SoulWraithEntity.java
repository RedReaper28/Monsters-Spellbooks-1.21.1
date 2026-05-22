package net.redreaper.monsterspellbooks.entity.living;

import io.redspace.ironsspellbooks.api.entity.IMagicEntity;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.entity.mobs.goals.WizardAttackGoal;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.acetheeldritchking.aces_spell_utils.entity.mobs.UniqueAbstractSpellCastingMob;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public class SoulWraithEntity extends UniqueAbstractSpellCastingMob implements GeoAnimatable, IMagicEntity, Enemy {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public SoulWraithEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        xpReward = 0;
        this.lookControl = createLookControl();
        this.moveControl = new FlyingMoveControl(this, 20, true);
    }

    public SoulWraithEntity(Level level, LivingEntity owner) {
        this(ModEntities.SOUL_WRAITH.get(), level);
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

    protected @NotNull PathNavigation createNavigation(@NotNull Level pLevel) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, pLevel);
        flyingpathnavigation.setCanOpenDoors(true);
        flyingpathnavigation.setCanFloat(true);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }

    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(3, new WizardAttackGoal(this, 0.75f, 30, 55)
                .setSpells(
                        List.of(ModSpellRegistry.SOUL_FIREBOLT.get(),ModSpellRegistry.SOUL_SCORCH.get()),
                        List.of(),
                        List.of(ModSpellRegistry.SOUL_FIRE_TELEPORT.get()),
                        List.of()
                )                .setSpellQuality(0.50f, 0.80f)
        );
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, (double)1.0F));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0F, 0.0F));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractIllager.class, true));
    }

    public @NotNull SoundEvent getHurtSound(@NotNull DamageSource damageSource) {
        return SoundRegistry.LIGHTNING_CAST.get();
    }

    public static AttributeSupplier.Builder createAttributes()
    {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.ATTACK_DAMAGE, 10.5)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.FOLLOW_RANGE, 30.0)
                .add(Attributes.ENTITY_INTERACTION_RANGE, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.FLYING_SPEED, 0.25)
                .add(AttributeRegistry.SPELL_POWER, 1.5)
                .add(AttributeRegistry.SPELL_RESIST, 1.5)
                ;
    }

    // Attacks and Death
    @Override
    public void die(@NotNull DamageSource pDamageSource) {super.die(pDamageSource);}

    protected boolean shouldDespawnInPeaceful() {return true;}

    public boolean shouldDropExperience() {
        return true;
    }

    protected boolean shouldDropLoot() {
        return true;
    }

    @Override
    public boolean dampensVibrations() {
        return true;
    }

    @Override
    public boolean canBeAffected(@NotNull MobEffectInstance effectInstance) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {}

    // Geckolib & Animations
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

}
