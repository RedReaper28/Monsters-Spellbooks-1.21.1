package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import io.redspace.ironsspellbooks.network.SyncManaPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

public class SoulRendMobEffect extends MagicMobEffect {
    public static final float MANA_SLOWNESS_PER_LEVEL = -100;

    public SoulRendMobEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyInstantenousEffect(@Nullable Entity pSource, @Nullable Entity pIndirectSource, LivingEntity livingEntity, int pAmplifier, double pHealth) {
        int manaAdd = (int) (pAmplifier * -0.5f);
        MagicData pmg = MagicData.getPlayerMagicData(livingEntity);
        if (pmg.getMana() > 100) {
            pmg.setMana(pmg.getMana() + manaAdd);
            if (livingEntity instanceof ServerPlayer serverPlayer) {
                PacketDistributor.sendToPlayer(serverPlayer, new SyncManaPacket(pmg));
            }
        }
    }

    @Override public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int pAmplifier) {
        applyInstantenousEffect(null, null, livingEntity, pAmplifier, livingEntity.getHealth());
        return true;
    }
}
