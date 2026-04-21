package net.redreaper.monsterspellbooks.entity.living.draugr;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.entity.mobs.IAnimatedAttacker;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.NeutralWizard;
import io.redspace.ironsspellbooks.entity.mobs.goals.PatrolNearLocationGoal;
import io.redspace.ironsspellbooks.entity.mobs.goals.melee.AttackAnimationData;
import io.redspace.ironsspellbooks.entity.mobs.wizards.GenericAnimatedWarlockAttackGoal;
import io.redspace.ironsspellbooks.entity.mobs.wizards.fire_boss.NotIdioticNavigation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.redreaper.monsterspellbooks.init.ModItems;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import net.redreaper.monsterspellbooks.init.ModTags;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.AnimationState;

import javax.annotation.Nullable;
import java.util.List;

public class DraugrEliteVindicatorEntity extends DraugrIllagerEntity implements Enemy, IAnimatedAttacker {

    public DraugrEliteVindicatorEntity(EntityType<? extends DraugrIllagerEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        xpReward = 25;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(3, new GenericAnimatedWarlockAttackGoal<>(this, 1.20f, 50, 75)
                .setMoveset(List.of(
                        new AttackAnimationData(9, "simple_sword_upward_swipe", 5),
                        new AttackAnimationData(8, "simple_sword_lunge_stab", 6),
                        new AttackAnimationData(10, "simple_sword_stab_alternate", 8),
                        new AttackAnimationData(10, "simple_sword_horizontal_cross_swipe", 8)
                ))
                .setComboChance(.4f)
                .setMeleeAttackInverval(10, 30)
                .setMeleeMovespeedModifier(1.25f)
                .setSpells(
                        List.of(SpellRegistry.ICICLE_SPELL.get(),ModSpellRegistry.ICE_ARSENAL.get(),ModSpellRegistry.SOUL_CHAIN.get()),
                        List.of(ModSpellRegistry.FROST_COATING.get()),
                        List.of(),
                        List.of()
                )
        );
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(4, new PatrolNearLocationGoal(this, 30, .75f));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, NeutralWizard.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData) {
        RandomSource randomsource = Utils.random;
        this.populateDefaultEquipmentSlots(randomsource, pDifficulty);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData);
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource pRandom, DifficultyInstance pDifficulty) {
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(ModItems.DRAUGR_ELITE_HELMET.get()));
        this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(ModItems.DRAUGR_ELITE_CHESTPLATE.get()));
        this.setItemSlot(EquipmentSlot.FEET, new ItemStack(ModItems.DRAUGR_ELITE_BOOTS.get()));
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.DEATHSILVER_SWORD.get()));
        this.setDropChance(EquipmentSlot.HEAD, 0.15f);
        this.setDropChance(EquipmentSlot.CHEST, 0.15f);
        this.setDropChance(EquipmentSlot.FEET, 0.15f);
        this.setDropChance(EquipmentSlot.MAINHAND, 0.5f);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.ATTACK_DAMAGE, 7.0F)
                .add(Attributes.ATTACK_KNOCKBACK, 1.5F)
                .add(Attributes.ENTITY_INTERACTION_RANGE, 4)
                .add(Attributes.SCALE, 1.2)
                .add(Attributes.MAX_HEALTH, 35.0F)
                .add(Attributes.ARMOR, 10)
                .add(Attributes.FOLLOW_RANGE, 24.0)
                .add(Attributes.MOVEMENT_SPEED, 0.35F)
                .add(AttributeRegistry.ICE_SPELL_POWER, 5);

    }


    protected SoundEvent getAmbientSound() {
        return SoundEvents.VINDICATOR_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.VINDICATOR_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.VINDICATOR_HURT;
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return true;
    }

    @Override
    public boolean shouldSheathSword() {
        return false;
    }

    RawAnimation animationToPlay = null;
    private final AnimationController<DraugrEliteVindicatorEntity> meleeController = new AnimationController<>(this, "keeper_animations", 0, this::predicate);

    @Override
    public void playAnimation(String animationId) {
        try {
            animationToPlay = RawAnimation.begin().thenPlay(animationId);
        } catch (Exception ignored) {
            IronsSpellbooks.LOGGER.error("Entity {} Failed to play animation: {}", this, animationId);
        }
    }

    private PlayState predicate(AnimationState<DraugrEliteVindicatorEntity> animationEvent) {
        var controller = animationEvent.getController();

        if (this.animationToPlay != null) {
            controller.forceAnimationReset();
            controller.setAnimation(animationToPlay);
            animationToPlay = null;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(meleeController);
        super.registerControllers(controllerRegistrar);
    }

    @Override
    public boolean isAnimating() {
        return meleeController.getAnimationState() != AnimationController.State.STOPPED || super.isAnimating();
    }

    public boolean isAlliedTo(Entity entity) {
        if (super.isAlliedTo(entity)) {
            return true;
        } else {
            return entity.getType().is(ModTags.Entities.DRAUGR_ALLIES) && this.getTeam() == null && entity.getTeam() == null;
        }
    }

    @Override
    protected PathNavigation createNavigation(Level pLevel) {
        return new NotIdioticNavigation(this, pLevel);
    }
}
