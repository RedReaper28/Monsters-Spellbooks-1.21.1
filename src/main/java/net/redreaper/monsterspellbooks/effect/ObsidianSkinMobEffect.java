package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import io.redspace.ironsspellbooks.util.ModTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.redreaper.monsterspellbooks.init.ModMobEffects;

@EventBusSubscriber
public class ObsidianSkinMobEffect extends MagicMobEffect {
    public static final float ATTACK_PER_LEVEL =  0.25f;
    public static final float DEFENSE_PER_LEVEL  =  5;
    public static final float KNOCKBACK_RESISTANCE_PER_LEVEL = 15F;
    public ObsidianSkinMobEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        var effects = entity.getActiveEffects().stream().map(MobEffectInstance::getEffect).filter(effect -> effect.value().getCategory() == MobEffectCategory.HARMFUL && effect.is(ModTags.AFFECTED_BY_SPIDER_ASPECT)).toList();
        entity.setTicksFrozen(0);
        entity.removeEffect(MobEffects.WITHER);
        effects.forEach(entity::removeEffect);
        return true;
    }

    @SubscribeEvent
    public static void onLivingIncomingDamage(LivingIncomingDamageEvent event) {
        var livingEntity = event.getEntity();
        if ((livingEntity instanceof ServerPlayer)){
            if (event.getEntity().hasEffect(ModMobEffects.OBSIDIAN_SKIN) && event.getSource().is(DamageTypeTags.IS_FIRE)) {
                event.getEntity().clearFire();
                event.setCanceled(true);
                return;
            }
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }
}
