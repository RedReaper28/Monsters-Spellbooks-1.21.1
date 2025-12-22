package net.redreaper.monsterspellbooks.init;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber
public class ModDamageSources {
    public static DamageSource get(Level level, ResourceKey<DamageType> damageType) {
        return level.damageSources().source(damageType);
    }

    public static Holder<DamageType> getHolderFromResource(Entity entity, ResourceKey<DamageType> damageTypeResourceKey) {
        var option = entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolder(damageTypeResourceKey);
        if (option.isPresent()) {
            return option.get();
        } else {
            return entity.level().damageSources().genericKill().typeHolder();
        }
    }

    @SubscribeEvent
    public static void postHitEffects(LivingDamageEvent.Post event) {
        var damageSource = event.getSource();
        if (damageSource instanceof ModSpellDamageSources spellDamageSource && spellDamageSource.hasPostHitEffects()) {
            float actualDamage = event.getNewDamage();
            var target = event.getEntity();
            var attacker = event.getSource().getEntity();
            if (attacker instanceof LivingEntity livingAttacker) {
                if (spellDamageSource.getManastealPercent() > 0) {
                    float manaAdd = (spellDamageSource.getManastealPercent() * actualDamage);
                    MagicData pmg = MagicData.getPlayerMagicData(livingAttacker);
                    pmg.setMana(pmg.getMana() + manaAdd);
                }
            }
        }
    }
}
