package net.redreaper.monsterspellbooks.spells;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.redreaper.monsterspellbooks.entity.living.summons.SummonWeaponEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;


public abstract class AbstractSummonWeaponSpell<T extends SummonWeaponEntity> extends AbstractSpell {
    private final Class<T> weaponType;

    protected AbstractSummonWeaponSpell(Class<T> weaponType) {
        this.weaponType = weaponType;
    }

    public final ICastDataSerializable getEmptyCastData() {
        return this.createCastData();
    }

    protected SummonWeaponSpellCastData createCastData() {
        return new SummonWeaponSpellCastData();
    }

    public abstract T onCastNoWeapon(Level var1, int var2, LivingEntity var3, MagicData var4);

    public abstract void onCastTickWithWeapon(Level var1, int var2, LivingEntity var3, MagicData var4, @NotNull T var5);

    public abstract CompleteCastTypes onCastCompleteWithWeapon(Level var1, int var2, LivingEntity var3, MagicData var4, boolean var5, @NotNull T var6);

    public void onServerPreCast(Level level, int spellLevel, LivingEntity entity, @Nullable MagicData playerMagicData) {
        if (this.getCastType() != CastType.CONTINUOUS && playerMagicData != null && !(playerMagicData.getAdditionalCastData() instanceof SummonWeaponSpellCastData)) {
            SummonWeaponSpellCastData castData = this.createCastData();
            T summon = this.onCastNoWeapon(level, spellLevel, entity, playerMagicData);
            castData.setEntity(summon);
            playerMagicData.setAdditionalCastData(castData);
        }

        super.onServerPreCast(level, spellLevel, entity, playerMagicData);
    }

    public final void onServerCastTick(Level level, int spellLevel, LivingEntity entity, @Nullable MagicData playerMagicData) {
        T summon = this.getFirearmEntityFromMagicData(playerMagicData, level);
        if (summon != null) {
            this.onCastTickWithWeapon(level, spellLevel, entity, playerMagicData, summon);
        }

        super.onServerCastTick(level, spellLevel, entity, playerMagicData);
    }

    public final void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        if (this.getCastType() == CastType.CONTINUOUS && !(playerMagicData.getAdditionalCastData() instanceof SummonWeaponSpellCastData)) {
            SummonWeaponSpellCastData castData = this.createCastData();
            T summon = this.onCastNoWeapon(level, spellLevel, entity, playerMagicData);
            castData.setEntity(summon);
            playerMagicData.setAdditionalCastData(castData);
        }

        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    public final void onServerCastComplete(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData, boolean cancelled) {
        T summon = this.getFirearmEntityFromMagicData(playerMagicData, level);
        if (summon != null) {
            CompleteCastTypes result = this.onCastCompleteWithWeapon(level, spellLevel, entity, playerMagicData, cancelled, summon);
            if (result == AbstractSummonWeaponSpell.CompleteCastTypes.RELEASE_WEAPON) {
                summon.releaseWeapon();
            }
        }

        super.onServerCastComplete(level, spellLevel, entity, playerMagicData, cancelled);
    }

    protected final T getFirearmEntityFromMagicData(MagicData playerMagicData, Level level) {
        if (level instanceof ServerLevel serverLevel) {
            if (playerMagicData == null) {
                return null;
            } else {
                ICastData var5 = playerMagicData.getAdditionalCastData();
                if (var5 instanceof SummonWeaponSpellCastData) {
                    SummonWeaponSpellCastData castData = (SummonWeaponSpellCastData)var5;
                    Entity summon = castData.getEntity(serverLevel);
                    if (summon == null) {
                        return null;
                    } else {
                        return (T)(!this.weaponType.isInstance(summon) ? null : (SummonWeaponEntity)this.weaponType.cast(summon));
                    }
                } else {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    public static enum CompleteCastTypes {
        RELEASE_WEAPON,
        KEEP_WEAPON;
    }

    public static class SummonWeaponSpellCastData implements ICastDataSerializable {
        private UUID entityId;

        public void setEntity(Entity entity) {
            this.entityId = entity.getUUID();
        }

        public Entity getEntity(ServerLevel level) {
            return this.entityId == null ? null : level.getEntity(this.entityId);
        }

        public void writeToBuffer(FriendlyByteBuf friendlyByteBuf) {
            boolean hasEntity = this.entityId != null;
            friendlyByteBuf.writeBoolean(hasEntity);
            if (hasEntity) {
                friendlyByteBuf.writeUUID(this.entityId);
            }

        }

        public void readFromBuffer(FriendlyByteBuf friendlyByteBuf) {
            this.entityId = friendlyByteBuf.readBoolean() ? friendlyByteBuf.readUUID() : null;
        }

        public void reset() {
            this.entityId = null;
        }

        public CompoundTag serializeNBT(HolderLookup.Provider provider) {
            CompoundTag tag = new CompoundTag();
            if (this.entityId != null) {
                tag.putUUID("Entity", this.entityId);
            }

            return tag;
        }

        public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
            this.entityId = nbt.hasUUID("Entity") ? nbt.getUUID("Entity") : null;
        }
    }
}
