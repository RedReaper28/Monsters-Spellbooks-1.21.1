package net.redreaper.monsterspellbooks.entity.spells.vile_slash;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModMobEffects;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VileSlashProjectileNew extends AbstractMagicProjectile implements GeoEntity {
    private final AnimatableInstanceCache cache;
    private final RawAnimation idle;
    private final List<Entity> victims = new ArrayList<>();
    private int hitsPerTick;
    private int timer;
    private int windupTimer = 25;
    private static final EntityDataAccessor<Integer> DATA_TIMER = SynchedEntityData.defineId(VileSlashProjectileNew.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_DELAY = SynchedEntityData.defineId(VileSlashProjectileNew.class, EntityDataSerializers.INT);

    public VileSlashProjectileNew(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.cache = GeckoLibUtil.createInstanceCache(this);
        this.idle = RawAnimation.begin().thenLoop("idle");
        this.setNoGravity(true);
        this.setPierceLevel(-1);
    }

    public VileSlashProjectileNew(Level levelIn, LivingEntity shooter) {
        this(ModEntities.VILE_SLASH.get(), levelIn);
        setOwner(shooter);
    }

    @Override
    public void trailParticles() {
        Vec3 vec3 = this.position().subtract(getDeltaMovement());
        level().addParticle(ModParticleHelper.REAPER_FIRE, vec3.x, vec3.y, vec3.z, 0, 0, 0);
    }

    @Override
    public void impactParticles(double x, double y, double z) {
        MagicManager.spawnParticles(level(), ModParticleHelper.SOUL_SMOKE, x, y, z, 5, .1, .1, .1, .5, false);
    }

    @Override public float getSpeed() {
        return 1.0f;
    }

    @Override
    public void tick() {
        super.tick();
        hitsPerTick = 0;

        if (tickCount > getDelay())
        {
            if (!level().isClientSide)
            {
                HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);

                if (hitresult.getType() == HitResult.Type.ENTITY)
                {
                    onHitEntity((EntityHitResult) hitresult);
                }
            }
        }

        if(tickCount==getDelay()){
            shoot(this.getLookAngle());
        }
    }


    @Override
    public void travel() {
        setPos(position().add(getDeltaMovement()));
        if (!this.isNoGravity()) {
            Vec3 vec34 = this.getDeltaMovement();
            this.setDeltaMovement(vec34.x, vec34.y - getDefaultGravity(), vec34.z);
        }
    }

    @Override
    protected void rotateWithMotion() {}


    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        Entity entity = pResult.getEntity();

        if (!victims.contains(entity))
        {
            DamageSources.applyDamage(entity, damage, ModSpellRegistry.VILE_SLASH.get().getDamageSource(this, getOwner()));
            victims.add(entity);
        }

        if (getPierceLevel() != 0)
        {
            if (hitsPerTick++ < 5)
            {
                HitResult hitResult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
                if (hitResult.getType() != HitResult.Type.MISS && !EventHooks.onProjectileImpact(this, hitResult))
                {
                    onHit(hitResult);
                }
            }
            pierceOrDiscard();
        } else
        {
            discard();
        }
    }

    BlockPos lastHitBlock;

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        {
            discard();
        }
    }

    @Override
    protected void onHit(HitResult hitresult) {
        if (!level().isClientSide)
        {
            var blockPos = BlockPos.containing(hitresult.getLocation());
            if (hitresult.getType() == HitResult.Type.BLOCK && !blockPos.equals(lastHitBlock))
            {
                lastHitBlock = blockPos;
            } else if (hitresult.getType() == HitResult.Type.ENTITY) {
                level().playSound(null, BlockPos.containing(position()), SoundRegistry.FORCE_IMPACT.get(), SoundSource.NEUTRAL, 2, .65f);
            }
        }

        super.onHit(hitresult);
    }

    @Override
    protected boolean shouldPierceShields() {
        return true;
    }

    @Override public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.of(SoundEvents.SOUL_ESCAPE);
    }

    @Override
    public void onAntiMagic(MagicData playerMagicData) {
        MagicManager.spawnParticles(level(), ModParticleHelper.REAPER_FIRE, this.getX(), this.getY(), this.getZ(), 1, 0, 0, 0, 0.1, true);
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

    // NBT
    public int getTimer()
    {
        return entityData.get(DATA_TIMER);
    }

    public void setTimer(int timer)
    {
        entityData.set(DATA_TIMER, timer);
    }

    public int getDelay()
    {
        return entityData.get(DATA_DELAY);
    }

    public void setDelay(int delay)
    {
        entityData.set(DATA_DELAY, delay);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(DATA_TIMER, -1);
        pBuilder.define(DATA_DELAY, 20);
    }
}