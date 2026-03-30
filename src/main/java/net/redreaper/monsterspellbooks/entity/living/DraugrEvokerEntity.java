package net.redreaper.monsterspellbooks.entity.living;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import io.redspace.ironsspellbooks.entity.mobs.goals.*;
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
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.redreaper.monsterspellbooks.init.ModAtributeRegistry;
import net.redreaper.monsterspellbooks.init.ModItems;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;

import javax.annotation.Nullable;
import java.util.List;

public class DraugrEvokerEntity extends AbstractSpellCastingMob implements Enemy {

    public DraugrEvokerEntity(EntityType<? extends AbstractSpellCastingMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        xpReward = 25;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SpellBarrageGoal(this, SpellRegistry.SUMMON_VEX_SPELL.get(), 1, 3, 100, 260, 1));
        this.goalSelector.addGoal(2, new WizardAttackGoal(this, 1.5f, 30, 80)
                .setSpells(
                        List.of(SpellRegistry.FANG_STRIKE_SPELL.get(), SpellRegistry.ICE_SPIKES_SPELL.get(),SpellRegistry.COUNTERSPELL_SPELL.get(),ModSpellRegistry.STRAY_GRASP.get()),
                        List.of(ModSpellRegistry.TUNDRA_TERRAIN.get(), SpellRegistry.ICE_TOMB_SPELL.get()),
                        List.of(),
                        List.of())
                .setSpellQuality(.4f, .6f)
                .setSingleUseSpell(ModSpellRegistry.LICHDOM.get(), 150, 200, 1, 1)
                .setDrinksPotions());
        this.goalSelector.addGoal(3, new PatrolNearLocationGoal(this, 30, .75f));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(10, new WizardRecoverGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }


    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData) {
        RandomSource randomsource = Utils.random;
        this.populateDefaultEquipmentSlots(randomsource, pDifficulty);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData);
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource pRandom, DifficultyInstance pDifficulty) {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.FROZEN_COMMANDER_STAFF.get()));
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(ModItems.DRAUGR_ELITE_HELMET.get()));
        this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(ModItems.DRAUGR_ELITE_CHESTPLATE.get()));
        this.setItemSlot(EquipmentSlot.LEGS, new ItemStack(ModItems.DEATH_KNIGHT_LEGGINGS.get()));
        this.setItemSlot(EquipmentSlot.FEET, new ItemStack(ModItems.DEATH_KNIGHT_BOOTS.get()));
        this.setDropChance(EquipmentSlot.MAINHAND, 0.25F);
        this.setDropChance(EquipmentSlot.HEAD, 0.0F);
        this.setDropChance(EquipmentSlot.CHEST, 0.0F);
        this.setDropChance(EquipmentSlot.LEGS, 0.0F);
        this.setDropChance(EquipmentSlot.FEET, 0.0F);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.MAX_HEALTH, 60.0)
                .add(Attributes.FOLLOW_RANGE, 24.0)
                .add(AttributeRegistry.ICE_SPELL_POWER, 15)
                .add(ModAtributeRegistry.NECRO_MAGIC_POWER, 10)
                .add(AttributeRegistry.CAST_TIME_REDUCTION, 2)
                .add(Attributes.MOVEMENT_SPEED, .20);
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
}

