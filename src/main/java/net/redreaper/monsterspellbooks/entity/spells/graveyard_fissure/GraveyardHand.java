package net.redreaper.monsterspellbooks.entity.spells.graveyard_fissure;

import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AoeEntity;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModMobEffects;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Optional;

public class GraveyardHand extends AoeEntity implements GeoEntity {
    private final AnimatableInstanceCache cache;
    private final RawAnimation idle;

    public GraveyardHand(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.cache = GeckoLibUtil.createInstanceCache(this);
        this.idle = RawAnimation.begin().thenLoop("idle");
    }

    public GraveyardHand(Level levelIn, LivingEntity shooter) {
        this(ModEntities.GRAVEYARD_HAND.get(), levelIn);
        setOwner(shooter);
    }

    @Override
    public void tick() {
        if (tickCount == waitTime) {
            this.playSound(SoundEvents.SKELETON_AMBIENT, 0.5f, 1);
        } else if (tickCount == warmupTime) {
            if (level().isClientSide) {
                float y = this.getYRot();
                int countPerSide = 60;
                for (int i = -countPerSide; i < countPerSide; i++) {
                    Vec3 motion = new Vec3(0, Math.abs(countPerSide) - i, countPerSide * .5f).yRot(y).normalize().multiply(.4f, .8f, .4f);
                    level().addParticle(ModParticleHelper.BONE_FRAGMENTS, getX(), getY() + .5f, getZ(), motion.x, motion.y, motion.z);
                }
            } else {
                checkHits();
            }
        } else if (tickCount > deathTime)
            discard();
    }

    @Override
    public void applyEffect(LivingEntity target) {
            if (DamageSources.applyDamage(target, getDamage(), ModSpellRegistry.GRAVEYARD_FISSURE.get().getDamageSource(this, getOwner())) && getOwner() instanceof LivingEntity livingOwner) {
                target.hurtMarked = true;
                if (target.isDeadOrDying()) {
                    var oldVigor = livingOwner.getEffect(ModMobEffects.MANA_REGEN);
                    var addition = 0;
                    if (oldVigor != null)
                        addition = oldVigor.getAmplifier() + 1;
                    livingOwner.addEffect(new MobEffectInstance(ModMobEffects.MANA_REGEN, 20 * 60, Math.min(manaRegenLevel + addition, 9), false, false, true));
                    livingOwner.heal((manaRegenLevel + 1) * 2);
                }
            }
    }

    public final int waitTime = 5;
    public final int warmupTime = waitTime + 8;
    public final int deathTime = warmupTime + 8;

    public int manaRegenLevel;

    @Override
    public float getParticleCount() {
        return 0;
    }

    @Override
    public Optional<ParticleOptions> getParticle() {
        return Optional.empty();
    }

    private PlayState predicate(AnimationState event) {
        event.getController().setAnimation(this.idle);
        return PlayState.CONTINUE;
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController(this, "controller", 0, this::predicate));
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
