package net.redreaper.monsterspellbooks.entity.living;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.NeutralWizard;
import io.redspace.ironsspellbooks.entity.mobs.goals.WizardAttackGoal;
import io.redspace.ironsspellbooks.entity.mobs.goals.WizardRecoverGoal;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;

import javax.annotation.Nullable;
import java.util.List;

public class WitherWarlockEntity extends AbstractSpellCastingMob implements Enemy {

    public WitherWarlockEntity(EntityType<? extends AbstractSpellCastingMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        xpReward = 15;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(4, new WizardAttackGoal(this, 1.25f, 35, 80)
                .setSpells(
                        List.of(ModSpellRegistry.BONE_DAGGER.get(), ModSpellRegistry.LIFE_DRAIN.get(), ModSpellRegistry.RANCORCALL.get()),
                        List.of(ModSpellRegistry.BANSHEE_SCREAM.get()),
                        List.of(),
                        List.of(SpellRegistry.SLOW_SPELL.get())
                )
                .setSingleUseSpell(ModSpellRegistry.SPECTRAL_BLAST.get(), 160, 350, 4, 5)
                .setDrinksPotions());
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(10, new WizardRecoverGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, NeutralWizard.class, true));
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.SKELETON_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.WITHER_SKELETON_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.WITHER_SKELETON_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.WITHER_SKELETON_STEP;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData) {
        RandomSource randomsource = Utils.random;
        this.populateDefaultEquipmentSlots(randomsource, pDifficulty);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData);
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource pRandom, DifficultyInstance pDifficulty) {
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(ItemRegistry.TARNISHED_CROWN.get()));
        this.setDropChance(EquipmentSlot.HEAD, 0.15f);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.MAX_HEALTH, 25.0)
                .add(Attributes.FOLLOW_RANGE, 25.0)
                .add(AttributeRegistry.SPELL_POWER, 0.75)
                .add(Attributes.MOVEMENT_SPEED, .25);
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return true;
    }
}
