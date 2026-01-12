package net.redreaper.monsterspellbooks.entity.spells.putrescence_mass;

import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AoeEntity;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.redreaper.monsterspellbooks.init.ModDamageTypes;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;

import java.util.Optional;

public class PutrescenceField extends AoeEntity {

    private DamageSource damageSource;

    public PutrescenceField(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public PutrescenceField(Level level) {
        this(ModEntities.PUTRESCENCE_FIELD.get(), level);
    }

    @Override
    public void applyEffect(LivingEntity target) {
        if (damageSource == null) {
            damageSource = new DamageSource(DamageSources.getHolderFromResource(target, ModDamageTypes.PUTRESCENCE_FIELD), this, getOwner());
        }
        if (!DamageSources.isFriendlyFireBetween(this.getOwner(), target)) {
            DamageSources.ignoreNextKnockback(target);
            if (target.hurt(damageSource, getDamage())) {
            }
        }
    }

    @Override
    public float getParticleCount() {
        return 1.5f * getRadius();
    }

    @Override
    protected float particleYOffset() {
        return .25f;
    }

    @Override
    protected float getParticleSpeedModifier() {
        return 1.4f;
    }

    @Override
    public Optional<ParticleOptions> getParticle() {
        return Optional.of(ModParticleHelper.PUTRESCENCE_BUBBLE);
    }
}