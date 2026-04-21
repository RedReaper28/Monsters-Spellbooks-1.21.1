package net.redreaper.monsterspellbooks.events;

import io.redspace.ironsspellbooks.api.entity.IMagicEntity;
import io.redspace.ironsspellbooks.api.events.SpellPreCastEvent;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import io.redspace.ironsspellbooks.effect.ImmolateEffect;
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
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityTeleportEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.redreaper.monsterspellbooks.effect.HemorrhageMobEffect;
import net.redreaper.monsterspellbooks.effect.StaticMobEffect;
import net.redreaper.monsterspellbooks.init.ModDamageTypes;
import net.redreaper.monsterspellbooks.init.ModItems;
import net.redreaper.monsterspellbooks.init.ModMobEffects;
import net.redreaper.monsterspellbooks.item.curios.spellbooks.DiseaseEncyclopediaItem;

import java.util.Objects;

@EventBusSubscriber
public class ServerEvents {
    @SubscribeEvent
    public static void onLivingHealEvent(LivingHealEvent event) {
        MobEffectInstance antihealEffect = event.getEntity().getEffect(ModMobEffects.HEAL_CUT);
        MobEffectInstance antiheal2Effect = event.getEntity().getEffect(ModMobEffects.ACIDIC_VENOM);
        MobEffectInstance disabledEffect = event.getEntity().getEffect(MobEffectRegistry.HEARTSTOP);

        if (antihealEffect != null) {event.setCanceled(true);}
        if (antiheal2Effect!= null) {event.setCanceled(true);}
        if (disabledEffect != null) {event.setCanceled(true);}
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onSpellTeleport(EntityTeleportEvent event) {
        if (event.getEntity() instanceof LivingEntity livingEntity) {
            if (livingEntity.hasEffect(ModMobEffects.SPACE_ANCHORED)) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCastEvent(SpellPreCastEvent event) {
        var entity = event.getEntity();
        var spell = SpellRegistry.getSpell(event.getSpellId());

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
        LivingEntity entityTarget = event.getEntity();
        Entity entityAttacker = event.getSource().getDirectEntity();

        if (entityAttacker instanceof LivingEntity livingAttacker) {
            if (livingAttacker.hasEffect(ModMobEffects.OVERHEAT)) {
                entityTarget.setRemainingFireTicks(50);
            }
        }

        if (entityAttacker instanceof LivingEntity livingAttacker) {
            if (entityTarget.hasEffect(ModMobEffects.OVERHEAT)) {
                livingAttacker.setRemainingFireTicks(50);
            }
        }

        if (entityAttacker instanceof LivingEntity livingAttacker) {
            if (entityTarget.hasEffect(ModMobEffects.FROST_COATING)) {
                livingAttacker.setTicksFrozen(360);
            }
        }

        if (entityAttacker instanceof LivingEntity livingAttacker) {
            if (livingAttacker.hasEffect(ModMobEffects.DECAYING_TOUCH)) {
                entityTarget.addEffect(new MobEffectInstance(MobEffects.WITHER, 120, 1, true, true, true));
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

            if (livingEntity.getItemBySlot(EquipmentSlot.CHEST).is(ModItems.FLESH_MAIDEN) && event.getSource().is(ISSDamageTypes.HEARTSTOP)) {
                event.setCanceled(true);
                return;
            }

            if (livingEntity.hasEffect(ModMobEffects.SOUL_FORM) && !event.getSource().is(ISSDamageTypes.HOLY_MAGIC)) {
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

                // Frostmourne
                if (player.getItemBySlot(EquipmentSlot.MAINHAND).is(ModItems.FROSTMOURNE)) {
                    if (event.getSource().is(ISSDamageTypes.ICE_MAGIC))
                        target.addEffect(new MobEffectInstance(MobEffects.WITHER, 120, 1, true, true, true));
                }

                if (player.getItemBySlot(EquipmentSlot.MAINHAND).is(ModItems.FROSTMOURNE)) {
                    if (event.getSource().is(ModDamageTypes.NECRO_MAGIC))
                        Utils.addFreezeTicks(target, 240);
                }

                // SnowBow
                if (player.getItemBySlot(EquipmentSlot.MAINHAND).is(ModItems.SNOW_BOW)) {
                    if (event.getSource().is(DamageTypeTags.IS_PROJECTILE))
                        Utils.addFreezeTicks(target, 120);
                }

                // RedSnake
                if (player.getItemBySlot(EquipmentSlot.MAINHAND).is(ModItems.RED_SNAKE_BOW)) {
                    int randomNum = (int) (Math.random() * 5); // 0 to 10
                    if (event.getSource().is(DamageTypeTags.IS_PROJECTILE))
                        if (randomNum ==1 ) {
                            ImmolateEffect.addImmolateStack(target, sourceEntity);
                        }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onBeforeDamageTaken(LivingDamageEvent.Pre event) {
        var livingEntity = event.getEntity();
        var entity = event.getEntity();
        var source = event.getSource();
        var attacker = event.getSource().getEntity();

        if (attacker instanceof Player livingAttacker1) {
            if (event.getSource().is(ISSDamageTypes.FIRE_MAGIC) && event.getSource().getEntity() instanceof LivingEntity livingAttacker) {
                if (ASUtils.hasCurio((Player) livingAttacker, ModItems.BRIMSTONE_SIGIL.get())) {
                    ImmolateEffect.addImmolateStack(livingEntity, livingAttacker);
                }
            }


            if (attacker instanceof Player) {
                if (event.getSource().is(ISSDamageTypes.BLOOD_MAGIC) && event.getSource().getEntity() instanceof LivingEntity livingAttacker) {
                    if (ASUtils.hasCurio((Player) livingAttacker, ModItems.DREADHOUND_TOOTH_NECKLACE.get())) {
                        HemorrhageMobEffect.addHemorrhageStack(livingEntity, livingAttacker);
                    }
                }

                if (attacker instanceof Player) {
                    if (event.getSource().is(ISSDamageTypes.LIGHTNING_MAGIC) && event.getSource().getEntity() instanceof LivingEntity livingAttacker) {
                        if (ASUtils.hasCurio((Player) livingAttacker, ModItems.DWARVEN_POWER_CORE.get())) {
                            StaticMobEffect.addStaticStack((LivingEntity) attacker, attacker);
                        }
                    }
                }
            }

            if (attacker instanceof Player player) {
                // Disease Encyclopedia
                if (ASUtils.hasCurio(player, ModItems.DISEASE_ENCYCLOPEDIA.get()) && (!player.getCooldowns().isOnCooldown(ModItems.DISEASE_ENCYCLOPEDIA.get()))) {
                    int randomNum = (int) (Math.random() * 11); // 0 to 10
                    if (randomNum ==1 ) {entity.addEffect(new MobEffectInstance(ModMobEffects.MADNESS, 200, 1, true, true, true));}
                    if (randomNum == 2) {entity.addEffect(new MobEffectInstance(MobEffectRegistry.BLIGHT, 200, 2, true, true, true));}
                    if (randomNum == 3) {entity.addEffect(new MobEffectInstance(MobEffectRegistry.SLOWED, 200, 1, true, true, true));}
                    if (randomNum == 4) {entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0, true, true, true));}
                    if (randomNum == 5) {entity.addEffect(new MobEffectInstance(ModMobEffects.STUNNED, 150, 1, true, true, true));}
                    if (randomNum == 6) {entity.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 1, true, true, true));}
                    if (randomNum == 7) {entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 150, 0, true, true, true));}
                    if (randomNum == 8) {entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 150, 1, true, true, true));}
                    if (randomNum == 9) {entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 300, 3, true, true, true));}
                    if (randomNum == 10) {entity.addEffect(new MobEffectInstance(MobEffects.HARM, 10, 3, true, true, true));}
                    player.getCooldowns().addCooldown(ModItems.DISEASE_ENCYCLOPEDIA.get(), DiseaseEncyclopediaItem.COOLDOWN);
                }
            }
        }
    }


    @SubscribeEvent
    public static void revive(LivingDeathEvent event) {
        if (event.getEntity() instanceof LivingEntity livingEntity) {
            if (livingEntity.hasEffect(ModMobEffects.LICHDOM)) {
                event.setCanceled(true);
                livingEntity.removeEffect(ModMobEffects.LICHDOM);
                livingEntity.addEffect(new MobEffectInstance(ModMobEffects.SOUL_FORM, 150 ,4));
                livingEntity.addEffect(new MobEffectInstance(ModMobEffects.CURSE, 150 ,0));
                livingEntity.setHealth(10.0F);
                livingEntity.level().playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), SoundEvents.WITHER_DEATH, SoundSource.NEUTRAL, .8F, 1.3F);
            }

            if (livingEntity.hasEffect(ModMobEffects.EFFECT_OF_UNDYING)) {
                event.setCanceled(true);
                livingEntity.removeEffect(ModMobEffects.EFFECT_OF_UNDYING);
                livingEntity.removeEffectsCuredBy(net.neoforged.neoforge.common.EffectCures.PROTECTED_BY_TOTEM);
                livingEntity.setHealth(01.0F);
                livingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 900 ,1));
                livingEntity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800 ,0));
                livingEntity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100 ,1));
                livingEntity.level().playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), SoundEvents.TOTEM_USE, SoundSource.NEUTRAL, .8F, 1.3F);
            }
        }
    }
}




