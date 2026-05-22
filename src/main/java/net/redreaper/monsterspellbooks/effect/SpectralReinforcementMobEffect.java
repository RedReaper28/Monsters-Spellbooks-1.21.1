package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import io.redspace.ironsspellbooks.entity.spells.wisp.WispEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.redreaper.monsterspellbooks.init.ModAtributeRegistry;
import net.redreaper.monsterspellbooks.init.ModMobEffects;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@EventBusSubscriber
public class SpectralReinforcementMobEffect extends MagicMobEffect {
    private static final Map<UUID,Integer> internalCooldowns = new HashMap<>();

    public SpectralReinforcementMobEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }


    @SubscribeEvent
    public static void handleAbility(LivingIncomingDamageEvent event) {
        if(event.getSource().getEntity() != null) {
            if (!internalCooldowns.containsKey(event.getSource().getEntity().getUUID())) {
                internalCooldowns.put(event.getSource().getEntity().getUUID(), 0);
            }
            if (event.getSource().getEntity() instanceof LivingEntity living && living.hasEffect(ModMobEffects.SPECTRAL_REINFORCEMENT)) {
                if (event.getSource() instanceof SpellDamageSource && internalCooldowns.get(event.getSource().getEntity().getUUID()) == 0) {
                    double baseDamage = damageFor(event.getSource().getEntity());
                    LivingEntity victimPlayer = event.getEntity();
                    Level world = living.level();
                    WispEntity wispEntity = new WispEntity(world, (LivingEntity) event.getSource().getEntity(), (float) baseDamage);
                    wispEntity.setTarget(event.getEntity());
                    wispEntity.setPos(Utils.getPositionFromEntityLookDirection(event.getSource().getEntity(), 2).subtract(0, .2, 0));
                    world.addFreshEntity(wispEntity);
                    internalCooldowns.replace(living.getUUID(), 120);
                }
            }
        }
    }

    public static double damageFor(@Nullable Entity entity) {
        double baseDamage = 10.0;
        if (entity instanceof LivingEntity livingAttacker) {
            baseDamage = baseDamage * livingAttacker.getAttributeValue(AttributeRegistry.SPELL_POWER) * livingAttacker.getAttributeValue(ModAtributeRegistry.NECRO_MAGIC_POWER);
        }
        return baseDamage;
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (!internalCooldowns.containsKey(entity.getUUID())) {
            internalCooldowns.put(entity.getUUID(), 0);
        }
        if(internalCooldowns.get(entity.getUUID()) > 0){
            internalCooldowns.replace(entity.getUUID(),internalCooldowns.get(entity.getUUID()) - 1);
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplifier) {
        return true;
    }
}
