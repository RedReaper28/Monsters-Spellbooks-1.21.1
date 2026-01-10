package net.redreaper.monsterspellbooks.entity.spells.blizzard_aspect;

import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AoeEntity;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.redreaper.monsterspellbooks.init.ModDamageTypes;
import net.redreaper.monsterspellbooks.init.ModEntities;

import java.util.Optional;

public class SnowCloud extends AoeEntity {
    private DamageSource damageSource;

    public SnowCloud(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);}

    public SnowCloud(Level level) {
        this(ModEntities.SNOW_CLOUD.get(), level);
    }

    @Override
    public void applyEffect(LivingEntity target) {
        if (damageSource == null) {
            damageSource = new DamageSource(DamageSources.getHolderFromResource(target, ModDamageTypes.SNOW_CLOUD), this, getOwner());
        }
        target.hurt(damageSource, getDamage());
        target.addEffect(new MobEffectInstance(MobEffectRegistry.CHILLED, 270, 0));
    }

    @Override
    public float getParticleCount() {
        return .15f;
    }

    @Override
    public Optional<ParticleOptions> getParticle() {
        return Optional.of(ParticleHelper.ICY_FOG);
    }
}

