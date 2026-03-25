package net.redreaper.monsterspellbooks.entity.living;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.entity.mobs.SupportMob;
import io.redspace.ironsspellbooks.entity.mobs.goals.*;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.redreaper.monsterspellbooks.init.ModItems;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;

import javax.annotation.Nullable;
import java.util.List;

public class IllagerEnchanterEntity extends AbstractSpellCastingIllager implements Enemy, SupportMob {
    public GoalSelector supportTargetSelector;

    public IllagerEnchanterEntity(EntityType<? extends AbstractSpellCastingIllager> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        xpReward = 25;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new WizardSupportGoal<>(this, 1.25f, 40, 60)
                .setSpells(
                        List.of(SpellRegistry.BLESSING_OF_LIFE_SPELL.get(), SpellRegistry.HEALING_CIRCLE_SPELL.get(),ModSpellRegistry.ENCHANTERS_BOOST.get(),ModSpellRegistry.ENCHANTERS_PROTECTION.get()),
                        List.of(SpellRegistry.FORTIFY_SPELL.get())
                ));
        this.goalSelector.addGoal(3, new WizardAttackGoal(this, 1.25f, 35, 70)
                .setSpells(
                        List.of(),
                        List.of(SpellRegistry.GUST_SPELL.get(),SpellRegistry.COUNTERSPELL_SPELL.get()),
                        List.of(SpellRegistry.TELEPORT_SPELL.get()),
                        List.of(SpellRegistry.HEAL_SPELL.get()))
                .setSpellQuality(0.3f, 0.5f)
                .setDrinksPotions());
        this.goalSelector.addGoal(1, new ObtainRaidLeaderBannerGoal<>(this));
        this.goalSelector.addGoal(3, new PathfindToRaidGoal<>(this));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6));        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 15.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 15.0F));        this.goalSelector.addGoal(10, new WizardRecoverGoal(this));
        this.goalSelector.addGoal(2, new Raider.HoldGroundAttackGoal(this, 10.0F));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, Raider.class).setAlertOthers());
        this.goalSelector.addGoal(10, new WizardRecoverGoal(this));
        this.supportTargetSelector = new GoalSelector(this.level().getProfilerSupplier());
        this.supportTargetSelector.addGoal(0, new FindSupportableTargetGoal<>(this, LivingEntity.class, true,
                (mob) ->mob.getHealth() * 1.25f < mob.getMaxHealth() && (mob.getType().is(EntityTypeTags.ILLAGER_FRIENDS)))
        );
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData) {
        RandomSource randomsource = Utils.random;
        this.populateDefaultEquipmentSlots(randomsource, pDifficulty);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData);
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource pRandom, DifficultyInstance pDifficulty) {
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(ModItems.ILLAGER_ENCHANTER_HELMET.get()));
        this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(ModItems.ILLAGER_ENCHANTER_CHESTPLATE.get()));
        this.setDropChance(EquipmentSlot.HEAD, 0.0F);
        this.setDropChance(EquipmentSlot.CHEST, 0.0F);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.FOLLOW_RANGE, 30.0)
                .add(AttributeRegistry.CAST_TIME_REDUCTION, 2)
                .add(Attributes.MOVEMENT_SPEED, .25);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.EVOKER_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.EVOKER_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.EVOKER_HURT;
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return true;
    }

    LivingEntity supportTarget;

    @org.jetbrains.annotations.Nullable
    @Override
    public LivingEntity getSupportTarget() {
        return supportTarget;
    }

    @Override
    public void setSupportTarget(LivingEntity target) {
        this.supportTarget = target;
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        if (this.tickCount % 4 == 0 && this.tickCount > 1) {
            this.supportTargetSelector.tick();
        }
    }

    public boolean isAlliedTo(Entity entity) {
        if (super.isAlliedTo(entity)) {
            return true;
        } else {
            return entity.getType().is(EntityTypeTags.ILLAGER_FRIENDS) && this.getTeam() == null && entity.getTeam() == null;
        }
    }
}