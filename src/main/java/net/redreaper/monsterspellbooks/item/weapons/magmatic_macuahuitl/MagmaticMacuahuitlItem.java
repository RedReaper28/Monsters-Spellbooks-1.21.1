package net.redreaper.monsterspellbooks.item.weapons.magmatic_macuahuitl;

import io.redspace.ironsspellbooks.api.events.ModifySpellLevelEvent;
import io.redspace.ironsspellbooks.api.item.curios.AffinityData;
import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.item.UniqueItem;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.acetheeldritchking.aces_spell_utils.utils.ASRarities;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.redreaper.monsterspellbooks.init.ModExtendedWeaponTiers;
import net.redreaper.monsterspellbooks.init.ModItems;

import java.util.List;

@EventBusSubscriber
public class MagmaticMacuahuitlItem extends MagicSwordItem implements UniqueItem {
    private static final float KNIGHTMETAL_MULT_DAMAGE = 1.20F;
    public static final int COOLDOWN = 15 * 20;
    public MagmaticMacuahuitlItem() {
        super(
                ModExtendedWeaponTiers.MAGMATIC_OBSIDIAN,
                new Item
                        .Properties()
                        .stacksTo(1)
                        .fireResistant()
                        .rarity(ASRarities.ARID_RARITY_PROXY.getValue())
                        .attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.MAGMATIC_OBSIDIAN)
                        ),
                SpellDataRegistryHolder.of(
                        new SpellDataRegistryHolder(SpellRegistry.HEAT_SURGE_SPELL, 6)
                )
        );
    }

    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.irons_spellbooks.passive_ability", new Object[]{Component.literal(Utils.timeFromTicks((float)this.getPassiveCooldownTicks(), 1)).withStyle(ChatFormatting.LIGHT_PURPLE)}).withStyle(ChatFormatting.DARK_PURPLE));
            tooltipComponents.add(Component.literal(" ").append(Component.translatable(this.getDescriptionId() + ".desc")).withStyle(ChatFormatting.RED));
        }
        else {
            tooltipComponents.add(Component.translatable("item.aces_spell_utils.more_details1").withStyle(ChatFormatting.GRAY));
        }

    }

    protected int getPassiveCooldownTicks() {
        return 15 * 20;
    }

    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack == null) {
            return;
        }

        super.initializeSpellContainer(itemStack);
        AffinityData.setAffinityData(itemStack, SpellRegistry.HEAT_SURGE_SPELL.get(),2);
    }

    @EventBusSubscriber({Dist.CLIENT})
    public class SpellEvents {
        @SubscribeEvent
        public static void onModifySpellLevel(ModifySpellLevelEvent event) {
            LivingEntity caster = event.getEntity();
            if (caster != null) {
                if (event.getSpell() == SpellRegistry.HEAT_SURGE_SPELL.get()) {
                    ItemStack mainHand = caster.getMainHandItem();
                    ItemStack offHand = caster.getOffhandItem();
                    boolean usingKnives = mainHand.getItem() instanceof MagmaticMacuahuitlItem || offHand.getItem() instanceof MagmaticMacuahuitlItem;
                    if (usingKnives) {
                        event.addLevels(2);
                    }

                }
            }
        }
    }

    @SubscribeEvent
    public static void increaseDamage(LivingIncomingDamageEvent event) {
        var sourceEntity = event.getSource().getEntity();
        var target = event.getEntity();
        if (sourceEntity instanceof LivingEntity livingAttacker)
        {
            ItemStack mainhandItem = livingAttacker.getMainHandItem();
            if (target instanceof LivingEntity livingTarget) {
                if (mainhandItem.getItem() instanceof MagmaticMacuahuitlItem && (!(livingAttacker instanceof Player player) || !player.getCooldowns().isOnCooldown(ModItems.MAGMATIC_MACUAHUITL.get())))
                    if (event.getSource().is(DamageTypes.PLAYER_ATTACK)) {
                        if (livingTarget.getArmorValue() > 0) {
                            if (livingTarget.getArmorCoverPercentage() > 0) {
                                int moreBonus = (int) (KNIGHTMETAL_MULT_DAMAGE * livingTarget.getArmorCoverPercentage());
                                event.setAmount(event.getAmount() * moreBonus);
                                if (livingTarget.hasEffect(MobEffectRegistry.REND)){
                                    event.setAmount(event.getAmount() * moreBonus * 1.50f);
                                }
                            } else {
                                event.setAmount(event.getAmount() * KNIGHTMETAL_MULT_DAMAGE);
                            }

                            if (livingAttacker instanceof Player player)
                            {
                                player.getCooldowns().addCooldown(ModItems.MAGMATIC_MACUAHUITL.get(), MagmaticMacuahuitlItem.COOLDOWN);
                            }
                        }
                        livingTarget.addEffect(new MobEffectInstance(MobEffectRegistry.REND, 8*20, 2, true, true, true));
                        MagicManager.spawnParticles(livingTarget.level(), new BlastwaveParticleOptions(SchoolRegistry.FIRE.get().getTargetingColor(), 1.5f), livingTarget.getX(), livingTarget.getY() + 0.165F, livingTarget.getZ(), 1, 0, 0, 0, 0, true);
                    }
            }
        }
    }

}

