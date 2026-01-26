package net.redreaper.monsterspellbooks.entity.spells.soul_chain;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModMobEffects;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;
import net.redreaper.monsterspellbooks.particle.SoulChainParticleOptions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class SoulChain extends AbstractMagicProjectile {
    List<Entity> allVictims;
    List<Entity> lastVictims;
    Entity initialVictim;
    public int maxConnections = 2;
    public int maxConnectionsPerWave = 1;
    public float range = 3f;
    private final static Supplier<AbstractSpell> SPELL = ModSpellRegistry.SOUL_CHAIN;

    public SoulChain(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        allVictims = new ArrayList<>();
        lastVictims = new ArrayList<>();
        this.setNoGravity(true);
        this.noPhysics = true;
    }

    public SoulChain(Level level, Entity owner, Entity initialVictim) {
        this(ModEntities.SOUL_CHAIN.get(), level);
        this.setOwner(owner);
        this.setPos(initialVictim.position());
        this.initialVictim = initialVictim;
    }

    int getDuration;

    public int getDuration() {
        return getDuration;
    }

    public void setRendDuration(int rendDuration) {
        this.getDuration = rendDuration;
    }

    int hits;

    @Override
    public void tick() {
        super.tick();
        int f = tickCount - 1;
        if (!this.level().isClientSide && f % 6 == 0) {
            if (f == 0 && !allVictims.contains(initialVictim)) {
                doHurt(initialVictim);
                if (getOwner() != null) {
                    Vec3 start = getOwner().position().add(0, getOwner().getBbHeight() / 2, 0);
                    var dest = initialVictim.position().add(0, initialVictim.getBbHeight() / 2, 0);
                    ((ServerLevel) level()).sendParticles(new SoulChainParticleOptions(dest), start.x, start.y, start.z, 1, 0, 0, 0, 0);
                }

            } else {
                int j = lastVictims.size();
                AtomicInteger zapsThisWave = new AtomicInteger();
                //cannot be enhanced for
                for (int i = 0; i < j; i++) {
                    var entity = lastVictims.get(i);
                    var entities = level().getEntities(entity, entity.getBoundingBox().inflate(range), this::canHitEntity);
                    entities.sort(Comparator.comparingDouble(o -> o.distanceToSqr(entity)));
                    entities.forEach((victim) -> {
                        if (zapsThisWave.get() < maxConnectionsPerWave && hits < maxConnections && victim.distanceToSqr(entity) < range * range && Utils.hasLineOfSight(level(), entity.getEyePosition(), victim.getEyePosition(), true)) {
                            doHurt(victim);
                            victim.playSound(SoundEvents.CHAIN_BREAK, 5, 1);
                            zapsThisWave.getAndIncrement();
                            Vec3 start = new Vec3(entity.xOld, entity.yOld, entity.zOld).add(0, entity.getBbHeight() / 2, 0);
                            var dest = victim.position().add(0, victim.getBbHeight() / 2, 0);
                            ((ServerLevel) level()).sendParticles(new SoulChainParticleOptions(dest), start.x, start.y, start.z, 1, 0, 0, 0, 0);
                        }
                    });
                }
                lastVictims.removeAll(allVictims);
                if (lastVictims.isEmpty()) {
                    discard();
                }
            }
            allVictims.addAll(lastVictims);
        }
    }


    public void doHurt(Entity victim) {
        hits++;
        DamageSources.applyDamage(victim, damage, SPELL.get().getDamageSource(this, getOwner()));
        MagicManager.spawnParticles(level(), ModParticleHelper.REAPER_FIRE, victim.getX(), victim.getY() + victim.getBbHeight() / 2, victim.getZ(), 10, victim.getBbWidth() / 3, victim.getBbHeight() / 3, victim.getBbWidth() / 3, 0.1, false);
        if (victim instanceof LivingEntity livingEntity && livingEntity != getOwner())
            livingEntity.addEffect(new MobEffectInstance(ModMobEffects.SPACE_ANCHORED, getDuration(), 0));
        lastVictims.add(victim);
    }

    public boolean hasAlreadyZapped(Entity entity) {
        return allVictims.contains(entity) || lastVictims.contains(entity);
    }

    @Override
    protected boolean canHitEntity(Entity target) {
        return target instanceof LivingEntity && !DamageSources.isFriendlyFireBetween(target, getOwner()) && target != getOwner() && !hasAlreadyZapped(target) && super.canHitEntity(target);
    }

    @Override
    public void trailParticles() {

    }

    @Override
    public void impactParticles(double x, double y, double z) {

    }

    @Override
    public float getSpeed() {
        return 0;
    }

    @Override
    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.empty();
    }

    @Override
    public boolean shouldBeSaved() {
        return false;
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Duration", getDuration);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.getDuration = tag.getInt("Duration");
    }
}
