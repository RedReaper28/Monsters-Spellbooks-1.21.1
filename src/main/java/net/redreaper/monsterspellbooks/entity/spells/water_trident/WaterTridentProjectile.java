package net.redreaper.monsterspellbooks.entity.spells.water_trident;

import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Optional;

public class WaterTridentProjectile extends AbstractMagicProjectile implements GeoEntity {
    private final AnimatableInstanceCache cache;
    private final RawAnimation idle;

    public WaterTridentProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.cache = GeckoLibUtil.createInstanceCache(this);
        this.idle = RawAnimation.begin().thenLoop("idle");
        this.setNoGravity(true);    }

    public WaterTridentProjectile(Level levelIn, LivingEntity shooter) {
        this(ModEntities.WATER_TRIDENT.get(), levelIn);
        setOwner(shooter);
    }

    @Override
    public void trailParticles() {
        Vec3 vec3 = this.position().subtract(getDeltaMovement());
        level().addParticle(ModParticleHelper.HYDRO_BUBBLE, vec3.x, vec3.y, vec3.z, 0, 0, 0);
    }

    public void setRotation(float x, float y) {
        this.setXRot(x);
        this.xRotO = x;
        this.setYRot(y);
        this.yRotO = y;
    }


    @Override
    public void impactParticles(double x, double y, double z) {
        MagicManager.spawnParticles(level(), ModParticleHelper.HYDRO_BUBBLE, x, y, z, 5, .1, .1, .1, 0.5, true);
        MagicManager.spawnParticles(level(), ParticleTypes.FALLING_WATER, x, y, z, 75, .1, .1, .1, .5, false);
    }

    @Override
    public float getSpeed() {
        return 1.5f;
    }

    @Override
    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.empty();
    }


    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        DamageSources.applyDamage(pResult.getEntity(), getDamage(),
                ModSpellRegistry.WATER_TRIDENT.get().getDamageSource(this, getOwner()));
        if (pResult.getEntity() instanceof LivingEntity livingTarget)
        {
        }
        discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        discard();
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
