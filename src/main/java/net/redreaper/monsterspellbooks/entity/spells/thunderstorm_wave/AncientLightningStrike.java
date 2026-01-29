package net.redreaper.monsterspellbooks.entity.spells.thunderstorm_wave;

import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AoeEntity;
import io.redspace.ironsspellbooks.particle.ShockwaveParticleOptions;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import net.redreaper.monsterspellbooks.particle.AncientZapParticleOption;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;

import java.util.Optional;

public class AncientLightningStrike extends AoeEntity {
    public AncientLightningStrike(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        setRadius(3);
        setCircular();
    }

    public AncientLightningStrike(Level level) {
        this(ModEntities.ANCIENT_LIGHTNING_STRIKE.get(), level);
    }

    static final int chargeTime = 20;
    static final int vfxHeight = 15;

    @Override
    public void tick() {
        if (level().isClientSide) {
            return;
        }
        if (tickCount == 1) {
            int total = 5;
            int light = Utils.random.nextInt(total);
            Vec3 location = this.position().add(0, vfxHeight, 0);
            MagicManager.spawnParticles(level(), ParticleHelper.FOG_THUNDER_DARK, location.x, location.y, location.z, light, 1, 1, 1, 1, true);
            MagicManager.spawnParticles(level(), ParticleHelper.FOG_THUNDER_DARK, location.x, location.y, location.z, total - light, 1, 1, 1, 1, true);
            MagicManager.spawnParticles(level(), new ShockwaveParticleOptions(SchoolRegistry.BLOOD.get().getTargetingColor(), chargeTime * -1.5f * .05f, true), getX(), getY(), getZ(), 1, 0, 0, 0, 0, true);
        }
        if (tickCount == chargeTime) {
            checkHits();
            MagicManager.spawnParticles(level(), ModParticleHelper.ANCIENT_SPARKS, getX(), getY(), getZ(), 5, .2f, .2f, .2f, .25, true);
            MagicManager.spawnParticles(level(), ParticleHelper.FIERY_SPARKS, getX(), getY(), getZ(), 5, .2f, .2f, .2f, .125, true);
            Vec3 bottom = this.position();
            Vec3 top = bottom.add(0, vfxHeight, 0);
            Vec3 middle = bottom.add(Utils.getRandomScaled(2), Utils.random.nextIntBetweenInclusive(3, vfxHeight - 3), Utils.getRandomScaled(2));
            MagicManager.spawnParticles(level(), new AncientZapParticleOption(top), middle.x, middle.y, middle.z, 1, 0, 0, 0, 0, true);
            MagicManager.spawnParticles(level(), new AncientZapParticleOption(middle), getX(), getY(), getZ(), 1, 0, 0, 0, 0, true);
            if (Utils.random.nextFloat() < .3f) {
                Vec3 split = middle.add(Utils.getRandomScaled(2), -Math.abs(Utils.getRandomScaled(2)), Utils.getRandomScaled(2));
                MagicManager.spawnParticles(level(), new AncientZapParticleOption(middle), split.x, split.y, split.z, 1, 0, 0, 0, 0, true);
            }
            playSound(SoundRegistry.SPEAR_CHANNELING_STRIKE.get(), 2f, .8f + random.nextFloat() * .5f);
        }
        if (this.tickCount > chargeTime) {
            discard();
        }
    }

    @Override
    public void applyEffect(LivingEntity target) {
        DamageSources.applyDamage(target, getDamage(), ModSpellRegistry.THUNDERSTORM_WAVE   .get().getDamageSource(this, getOwner()));
    }

    @Override
    public float getParticleCount() {
        return 0;
    }

    @Override
    public Optional<ParticleOptions> getParticle() {
        return Optional.empty();
    }
}
