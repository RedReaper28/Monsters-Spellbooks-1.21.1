package net.redreaper.monsterspellbooks.entity.spells.guardians_neutralizer;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.entity.IEntityWithComplexSpawn;
import net.redreaper.monsterspellbooks.init.ModEntities;

public class GuardiansNeutralizerVisualEntity extends Entity implements IEntityWithComplexSpawn {
    public static final int lifetime = 15;

    public GuardiansNeutralizerVisualEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public float distance;

    public GuardiansNeutralizerVisualEntity(Level level, Vec3 start, Vec3 end, LivingEntity owner) {
        super(ModEntities.GUARDIAN_BEAM_VISUAL_ENTITY.get(), level);
        this.setPos(start.subtract(0, .5f, 0));
        this.distance = (float) start.distanceTo(end);
        this.setRot(owner.getYRot(), owner.getXRot());
    }

    @Override
    public void tick() {
        if (++tickCount > lifetime) {
            this.discard();
        }
    }

    @Override
    public boolean shouldRender(double pX, double pY, double pZ) {
        return true;
    }

    @Override
    public boolean shouldBeSaved() {
        return false;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {}

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {}

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {}

    @Override
    public void writeSpawnData(RegistryFriendlyByteBuf buffer) {
        buffer.writeInt((int) (distance * 10));
    }

    @Override
    public void readSpawnData(RegistryFriendlyByteBuf additionalData) {
        this.distance = additionalData.readInt() / 10f;
    }
}