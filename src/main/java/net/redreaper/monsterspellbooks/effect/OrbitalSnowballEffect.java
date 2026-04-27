package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.redreaper.monsterspellbooks.entity.spells.frosted_snowbolt.FrostedSnowboltProjectile;
import net.redreaper.monsterspellbooks.init.ModMobEffects;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@EventBusSubscriber
public class OrbitalSnowballEffect extends MagicMobEffect {
    private static final Map<UUID,Integer> internalCooldowns = new HashMap<>();

    public OrbitalSnowballEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @SubscribeEvent
    public static void handleAbility(LivingIncomingDamageEvent event) {
        if(event.getSource().getEntity() != null) {
            if (!internalCooldowns.containsKey(event.getSource().getEntity().getUUID())) {
                internalCooldowns.put(event.getSource().getEntity().getUUID(), 0);
            }
            if (event.getSource().getEntity() instanceof LivingEntity living && living.hasEffect(ModMobEffects.ORBITAL_SNOWBALL)) {
                    LivingEntity victimPlayer = event.getEntity();
                    Level world = living.level();
                    FrostedSnowboltProjectile bullet = new FrostedSnowboltProjectile(living.level(), living, victimPlayer, Direction.Axis.X);
                    bullet.shoot(living.getLookAngle());
                    bullet.setPos(living.getBoundingBox().getCenter().add((double) 0.0F, (double) (bullet.getBbHeight() * 3F), (double) 0.0F));
                    world.addFreshEntity(bullet);
                    internalCooldowns.replace(living.getUUID(), 120);
            }
        }
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
