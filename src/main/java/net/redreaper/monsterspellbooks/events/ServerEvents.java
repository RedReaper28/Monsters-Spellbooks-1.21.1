package net.redreaper.monsterspellbooks.events;

import io.redspace.ironsspellbooks.api.entity.IMagicEntity;
import io.redspace.ironsspellbooks.api.events.SpellPreCastEvent;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.config.ServerConfigs;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import io.redspace.ironsspellbooks.datagen.DamageTypeTagGenerator;
import io.redspace.ironsspellbooks.effect.AbyssalShroudEffect;
import io.redspace.ironsspellbooks.effect.EvasionEffect;
import io.redspace.ironsspellbooks.effect.ImmolateEffect;
import io.redspace.ironsspellbooks.entity.spells.ice_tomb.IceTombEntity;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.acetheeldritchking.aces_spell_utils.utils.ASUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.redreaper.monsterspellbooks.init.ModItems;
import net.redreaper.monsterspellbooks.init.ModMobEffects;

import java.util.Objects;

@EventBusSubscriber
public class ServerEvents {
    @SubscribeEvent
    public static void onLivingHealEvent(LivingHealEvent event) {
        MobEffectInstance antihealEffect = event.getEntity().getEffect(ModMobEffects.HEAL_CUT);
        MobEffectInstance disabledEffect = event.getEntity().getEffect(MobEffectRegistry.HEARTSTOP);


        if (antihealEffect != null) {
            event.setCanceled(true);
        }

        if (disabledEffect != null) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onPlayerCastEvent(SpellPreCastEvent event) {
        var entity = event.getEntity();
        var spell = SpellRegistry.getSpell(event.getSpellId());

        // Curse
        boolean isCursed = entity.hasEffect(ModMobEffects.CURSE);
        if (entity instanceof ServerPlayer player && !player.level().isClientSide()) {
            if (isCursed) {
                event.setCanceled(true);
                int time = Objects.requireNonNull(player.getEffect(ModMobEffects.CURSE)).getDuration();
                String formattedTime = ASUtils.convertTicksToTime(time);

                if (player instanceof ServerPlayer serverPlayer) {
                    serverPlayer.connection.send(new ClientboundSetActionBarTextPacket(Component.translatable("display.monsterspellbooks.curse_warning").append(formattedTime)
                            .withStyle(s -> s.withColor(TextColor.fromRgb(0xF35F5F)))));
                    serverPlayer.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                            SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 0.5f, 1f);
                }
            }
        }
    }


    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Post event) {
        LivingEntity livingTarget = event.getEntity();
        Entity entityAttacker = event.getSource().getDirectEntity();

        if (entityAttacker instanceof LivingEntity livingAttacker) {
            if (livingAttacker.hasEffect(ModMobEffects.OVERHEAT)) {
                livingTarget.setRemainingFireTicks(50);
            }
        } else if (entityAttacker instanceof LivingEntity livingAttacker) {
            if (livingTarget.hasEffect(ModMobEffects.OVERHEAT)) {
                livingAttacker.setRemainingFireTicks(50);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingIncomingDamage(LivingIncomingDamageEvent event) {
        var livingEntity = event.getEntity();
        if ((livingEntity instanceof ServerPlayer) || (livingEntity instanceof IMagicEntity)) {
            if (ModItems.BRIMSTONE_SIGIL.get().isEquippedBy(livingEntity) && event.getSource().is(DamageTypeTags.IS_FIRE)) {
                event.getEntity().clearFire();
                event.setCanceled(true);
                return;
            }
        }
    }

    @SubscribeEvent
    public static void livingDamageEventPost(LivingDamageEvent.Post event) {
        var sourceEntity = event.getSource().getEntity();
        var target = event.getEntity();
        var projectile = event.getSource().getDirectEntity();

        // Curios
        if (sourceEntity != null) {
            if (sourceEntity instanceof Player player) {
                // Brimstone Sigil
                if (ASUtils.hasCurio(player, ModItems.BRIMSTONE_SIGIL.get())) {
                    if (event.getSource().is(ISSDamageTypes.FIRE_MAGIC))
                        target.addEffect(new MobEffectInstance(ModMobEffects.BRIMSTONE_FLAME, 60, 0, true, true, true));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onBeforeDamageTaken(LivingDamageEvent.Pre event) {
        var livingEntity = event.getEntity();
        if (livingEntity instanceof IMagicEntity || livingEntity instanceof ServerPlayer) {
            var playerMagicData = MagicData.getPlayerMagicData(livingEntity);
            if (livingEntity.hasEffect(MobEffectRegistry.HEARTSTOP)) {
                playerMagicData.getSyncedData().addHeartstopDamage(event.getOriginalDamage() * .5f);
                event.setNewDamage(0);
            }
        }
        if (event.getSource().is(ISSDamageTypes.FIRE_MAGIC) && event.getSource().getEntity() instanceof LivingEntity livingAttacker) {
            if (ASUtils.hasCurio((Player) livingAttacker, ModItems.BRIMSTONE_SIGIL.get())) {
                ImmolateEffect.addImmolateStack(livingEntity, livingAttacker);
            }
        }
    }
}


