package net.redreaper.monsterspellbooks.entity.living;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.entity.mobs.goals.MomentHurtByTargetGoal;
import io.redspace.ironsspellbooks.entity.mobs.goals.melee.AttackAnimationData;
import net.acetheeldritchking.aces_spell_utils.entity.mobs.UniqueAbstractMeleeCastingMob;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModSounds;
import net.redreaper.monsterspellbooks.init.ModTags;
import net.redreaper.monsterspellbooks.utils.goals.LeapLeafAttackGoal;
import net.redreaper.monsterspellbooks.utils.goals.LeapleafPounceGoal;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public class JungleLeapleafEntity extends UniqueAbstractMeleeCastingMob implements Enemy {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public LeapLeafAttackGoal attackGoal;


    public JungleLeapleafEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        xpReward = 25;
        noCulling = true;
    }

    public JungleLeapleafEntity(Level level, LivingEntity owner) {
        this(ModEntities.JUNGLE_LEAPLEAF.get(), level);
    }

    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(1, new LeapleafPounceGoal(this));
        attackGoal = (LeapLeafAttackGoal) new LeapLeafAttackGoal(this, 1.5F, 5, 10)
                .setMoveset(List.of(
                        new AttackAnimationData(15, "whack_1", 10),
                        new AttackAnimationData(15, "whack_2", 10)
                ))
                .setComboChance(1.5f)
                .setMeleeAttackInverval(15, 25)
                .setMeleeBias(0.8f, 1.0f)
                .setMeleeMovespeedModifier(1.0f)
                .setSpells(List.of(SpellRegistry.STOMP_SPELL.get()), List.of(), List.of(), List.of())
                .setSpellQuality(0.5f, 0.15f);
        this.goalSelector.addGoal(2, attackGoal);
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 32, 0.08f));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.7));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new MomentHurtByTargetGoal(this, JungleLeapleafEntity.class));
        this.targetSelector.addGoal(1, new MomentHurtByTargetGoal(this, JungleWhispererEntity.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true, livingEntity ->
                livingEntity instanceof Player || livingEntity instanceof IronGolem));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true, livingEntity ->
                livingEntity instanceof Animal || livingEntity instanceof AbstractVillager || livingEntity instanceof Raider));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.ATTACK_KNOCKBACK, 3.0)
                .add(Attributes.ATTACK_DAMAGE, 10.0)
                .add(Attributes.MAX_HEALTH, 250)
                .add(Attributes.KNOCKBACK_RESISTANCE, 5)
                .add(Attributes.FOLLOW_RANGE, 64)
                .add(Attributes.ENTITY_INTERACTION_RANGE, 4)
                .add(Attributes.STEP_HEIGHT, 1.5)
                .add(Attributes.MOVEMENT_SPEED, .35)
                .add(AttributeRegistry.NATURE_MAGIC_RESIST, 1.5f)
                .add(AttributeRegistry.FIRE_MAGIC_RESIST, -.5f)
                ;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ModSounds.LEAPLEAF_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.LEAPLEAF_DEATH.get();
    }

    @org.jetbrains.annotations.Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.LEAPLEAF_AMBIENT.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState block) {
        this.playSound(ModSounds.LEAPLEAF_STEP.get(), 0.15F, 1.0F);
    }

    // Attacks and Death
    @Override
    public void die(DamageSource pDamageSource) {
        super.die(pDamageSource);
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        if (!super.doHurtTarget(entity)) {
            if (entity instanceof Player player && player.isBlocking()) {
                player.disableShield();
            }
            return false;
        } else {
            if (entity instanceof Player player && player.isBlocking()) {
                player.disableShield();
            }
            return true;
        }
    }


    @Override
    public boolean canBeAffected(MobEffectInstance potioneffect) {
        return !potioneffect.is(MobEffects.POISON) && super.canBeAffected(potioneffect);
    }

    protected boolean shouldDespawnInPeaceful() {return true;}

    public boolean shouldDropExperience() {
        return true;
    }

    protected boolean shouldDropLoot() {
        return true;
    }

    public boolean isAlliedTo(Entity entity) {
        if (super.isAlliedTo(entity)) {
            return true;
        } else {
            return entity.getType().is(ModTags.Entities.JUNGLE_ABOMINATIONS) && this.getTeam() == null && entity.getTeam() == null;
        }
    }

    // Geckolib & Animations
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
