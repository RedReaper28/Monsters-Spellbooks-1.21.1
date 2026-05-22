package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.CustomDescriptionMobEffect;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber
public class DivineProtectionMobEffect extends CustomDescriptionMobEffect {
    public static final float BASE_REDUCTION = .50f;
    public static final float EVASION_PER_LEVEL =  .25f;

    public DivineProtectionMobEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public Component getDescriptionLine(MobEffectInstance instance) {
        int amp = instance.getAmplifier() + 1;
        float reductionAmount = getReductionAmount(amp);
        return Component.translatable("tooltip.irons_spellbooks.oakskin_description", (int) (reductionAmount * 100)).withStyle(ChatFormatting.BLUE);
    }

    @SubscribeEvent
    public static void reduceDamage(LivingIncomingDamageEvent event) {
        var entity = event.getEntity();
        var effect = entity.getEffect(MobEffectRegistry.OAKSKIN);
        if (effect != null) {
            int lvl = effect.getAmplifier() + 1;
            float before = event.getAmount();
            float multiplier = 1 - getReductionAmount(lvl);
            event.setAmount(event.getAmount() * multiplier);
        }
    }

    public static float getReductionAmount(int level) {
        return BASE_REDUCTION * level;
    }
}
