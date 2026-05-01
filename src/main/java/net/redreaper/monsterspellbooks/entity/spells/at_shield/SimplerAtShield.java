package net.redreaper.monsterspellbooks.entity.spells.at_shield;

import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.entity.mobs.IMagicSummon;
import io.redspace.ironsspellbooks.entity.spells.AbstractShieldEntity;
import io.redspace.ironsspellbooks.entity.spells.ShieldPart;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.entity.PartEntity;
import net.redreaper.monsterspellbooks.init.ModEntities;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;

public class SimplerAtShield extends AbstractShieldEntity implements GeoEntity, IMagicSummon {
    private final AnimatableInstanceCache cache;
    private final RawAnimation spawn;

    protected ShieldPart[] subEntities;
    protected final Vec3[] subPositions;
    protected final int LIFETIME;
    protected int width;
    protected int height;
    protected int age;

    public SimplerAtShield(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        width = 5;
        height = 5;
        subEntities = new ShieldPart[width * height];
        subPositions = new Vec3[width * height];
        this.setHealth(10);
        LIFETIME = 20 * 20;
        this.cache = GeckoLibUtil.createInstanceCache(this);
        this.spawn = RawAnimation.begin().thenLoop("spawn");
        createShield();
    }

    public SimplerAtShield(Level level, float health) {
        this(ModEntities.AT_SHIELD.get(), level);
        this.setHealth(health);
    }

    @Override
    protected void createShield() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int i = x * height + y;
                subEntities[i] = new ShieldPart(this, "part" + (i + 1), 0.5F, 0.5F, true);
                subPositions[i] = new Vec3((x - width / 2f) * .5f + .25f, (y - height / 2f) * .5f, 0);//.xRot(getXRot()).yRot(getYRot());
            }
        }
    }

    @Override
    public void takeDamage(DamageSource source, float amount, @Nullable Vec3 location) {
        if (!this.isInvulnerableTo(source)) {
            this.setHealth(this.getHealth() - amount);
            if (!level().isClientSide && location != null) {
                MagicManager.spawnParticles(level(), ParticleTypes.ELECTRIC_SPARK, location.x, location.y, location.z, 30, .1, .1, .1, .5, false);
                level().playSound(null, location.x, location.y, location.z, SoundRegistry.FORCE_IMPACT.get(), SoundSource.NEUTRAL, .8f, 1f);
            }
        }
    }

    @Override
    public void tick() {

        if (this.getSummoner() != null) {
        var owner = this.getSummoner();
        this.setPos(owner.getX(), owner.getY(), owner.getZ());
        }

        if (this.getSummoner() != null && !this.getSummoner().isAlive()) {
            this.discard();
        }

        hurtThisTick = false;
        if (getHealth() <= 0) {
            destroy();
        } else if (++age > LIFETIME) {
            if (!this.level().isClientSide) {
                level().playSound(null, getX(), getY(), getZ(), SoundEvents.RESPAWN_ANCHOR_SET_SPAWN, SoundSource.NEUTRAL, 1, 1.4f);
            }
            discard();
        } else {
            for (int i = 0; i < subEntities.length; i++) {
                var subEntity = subEntities[i];

                Vec3 pos = subPositions[i].xRot(Mth.DEG_TO_RAD * -this.getXRot()).yRot(Mth.DEG_TO_RAD * -this.getYRot()).add(this.position());
                subEntity.setPos(pos);
                //subEntity.setDeltaMovement(newVector);
                //var vec3 = new Vec3(subEntity.getX(), subEntity.getY(), subEntity.getZ());
                subEntity.xo = pos.x;
                subEntity.yo = pos.y;
                subEntity.zo = pos.z;
                subEntity.xOld = pos.x;
                subEntity.yOld = pos.y;
                subEntity.zOld = pos.z;
            }
        }
    }

    @Override public void onRemovedFromLevel() {
        super.onRemovedFromLevel();
    }

    @Override
    public void onUnSummon() {
        if (!this.level().isClientSide) {
            MagicManager.spawnParticles(this.level(), ParticleHelper.SNOWFLAKE,
                    getX(), getY(), getZ(),
                    25, 0.4, 0.8, 0.4, 0.03, false);
            discard();
        }
    }

    @Override public PartEntity<?>[] getParts() {
        return this.subEntities;
    }

    @Override
    protected void destroy() {
        if (!this.level().isClientSide) {
            level().playSound(null, getX(), getY(), getZ(), SoundEvents.GLASS_BREAK, SoundSource.NEUTRAL, 2, .65f);
        }
        super.destroy();
    }

    private PlayState predicate(AnimationState event) {
        event.getController().setAnimation(this.spawn);
        return PlayState.CONTINUE;
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
