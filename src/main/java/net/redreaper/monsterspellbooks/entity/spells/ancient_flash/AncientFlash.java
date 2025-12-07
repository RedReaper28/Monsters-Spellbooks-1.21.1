package net.redreaper.monsterspellbooks.entity.spells.ancient_flash;

import io.redspace.ironsspellbooks.entity.spells.AoeEntity;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.redreaper.monsterspellbooks.init.ModEntities;

import java.util.Optional;

public class AncientFlash extends AoeEntity {
    private static final EntityDataAccessor<Boolean> DATA_IS_MIRRORED = SynchedEntityData.defineId(AncientFlash.class, EntityDataSerializers.BOOLEAN);
    protected int effectAmplifier;
    protected float effectDuration;

    public AncientFlash(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    LivingEntity target;

    public final int ticksPerFrame = 2;
    public final int deathTime = ticksPerFrame * 4;

    public AncientFlash(Level level, boolean mirrored)
    {
        this(ModEntities.ANCIENT_FLASH.get(), level);
        if (mirrored)
        {
            this.getEntityData().set(DATA_IS_MIRRORED, true);
        }
    }

    @Override
    public void tick() {
        if (!firstTick)
        {
            checkHits();
            firstTick = true;
        }
        if (tickCount >= deathTime)
        {
            discard();
        }
    }

    @Override
    public void applyEffect(LivingEntity target) {

    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(DATA_IS_MIRRORED, false);
    }

    public boolean isMirrored()
    {
        return this.getEntityData().get(DATA_IS_MIRRORED);
    }

    @Override
    public boolean shouldBeSaved() {
        return false;
    }

    @Override
    public float getParticleCount() {
        return 0;
    }

    @Override
    public Optional<ParticleOptions> getParticle() {
        return Optional.empty();
    }

    @Override
    public void recreateFromPacket(ClientboundAddEntityPacket pPacket) {
        super.recreateFromPacket(pPacket);
        this.xRotO = this.getXRot();
        this.yRotO = this.getYRot();
    }
}