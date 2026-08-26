package net.redreaper.monsterspellbooks.effect;

import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.redreaper.monsterspellbooks.init.ModDataAttachment;

public class StaticEffectData {
    private int hitCount;
    public int vfxTimestamp;

    public StaticEffectData(int hitsRemaining) {
        this.hitCount = hitsRemaining;
    }

    public StaticEffectData(IAttachmentHolder holder) {

    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    public boolean hasHitsRemaining() {
        return hitCount > 0;
    }

    public void decrementHit() {
        if (hitCount > 0) {
            hitCount--;
        }
    }

    public void clear() {
        hitCount = 0;
    }

    public static StaticEffectData get(LivingEntity entity) {
        return entity.getData(ModDataAttachment.STATIC_STRIKES_DATA);
    }

    public static void remove(LivingEntity livingEntity) {
        livingEntity.removeData(ModDataAttachment.STATIC_STRIKES_DATA);
    }

    public static boolean has(LivingEntity livingEntity) {
        return livingEntity.hasData(ModDataAttachment.STATIC_STRIKES_DATA);
    }
}
