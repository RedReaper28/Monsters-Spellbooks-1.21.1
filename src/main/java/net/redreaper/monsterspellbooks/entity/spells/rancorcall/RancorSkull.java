package net.redreaper.monsterspellbooks.entity.spells.rancorcall;

import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Optional;

public class RancorSkull extends AbstractMagicProjectile implements GeoEntity {
    private final AnimatableInstanceCache cache;
    private final RawAnimation idle;

    public RancorSkull(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.cache = GeckoLibUtil.createInstanceCache(this);
        this.idle = RawAnimation.begin().thenLoop("idle");
        this.setNoGravity(true);    }

    public RancorSkull(Level levelIn, LivingEntity shooter) {
        this(ModEntities.RANCOR_SKULL.get(), levelIn);
        setOwner(shooter);
    }

    public void shoot(Vec3 rotation, float inaccuracy) {
        var speed = rotation.length();
        Vec3 offset = Utils.getRandomVec3(1).normalize().scale(inaccuracy);
        var motion = rotation.normalize().add(offset).normalize().scale(speed);
        super.shoot(motion);
    }

    public void trailParticles() {
        Vec3 vec3 = this.position().subtract(getDeltaMovement());
        level().addParticle(ModParticleHelper.SOUL_FIRE, vec3.x, vec3.y, vec3.z, 0, 0, 0);
    }

    @Override
    public void impactParticles(double x, double y, double z) {
        MagicManager.spawnParticles(level(), ModParticleHelper.SOUL_FIRE, x, y, z, 5, .1, .1, .1, .5, false);
    }

    @Override public float getSpeed() {
        return 2f;
    }

    @Override public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.empty();
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        if (DamageSources.applyDamage(entityHitResult.getEntity(), damage, ModSpellRegistry.RANCORCALL.get().getDamageSource(this, getOwner()))) {
            if (entityHitResult.getEntity() instanceof LivingEntity livingEntity) {
                livingEntity.addEffect(new MobEffectInstance(MobEffectRegistry.GUIDING_BOLT, 5 * 20));
            }
        }
        pierceOrDiscard();

    }

    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        discardHelper(pResult);
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
