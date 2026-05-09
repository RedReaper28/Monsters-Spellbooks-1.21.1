package net.redreaper.monsterspellbooks.item.shields.dwarven_riot_shield;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import io.redspace.ironsspellbooks.util.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingShieldBlockEvent;
import net.redreaper.monsterspellbooks.entity.spells.redstone_mines.RedstoneExplosionEntity;
import net.redreaper.monsterspellbooks.init.ModDispatcher;
import net.redreaper.monsterspellbooks.init.ModItems;
import net.redreaper.monsterspellbooks.item.extended.magic_shield.ExtendedShieldItem;
import net.redreaper.monsterspellbooks.utils.ModRarities;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@EventBusSubscriber
public class DwarvenRiotShieldItem extends ExtendedShieldItem {
    public final ModDispatcher dispatcher = new ModDispatcher();
    public static final int COOLDOWN = 10 * 20;

    public DwarvenRiotShieldItem() {
        super(ItemPropertiesHelper.equipment(1).fireResistant().durability(1541).rarity(ModRarities.DWARVEN_RARITY_PROXY.getValue()));
    }

    public UseAnim getUseAnimation(ItemStack p_77661_1_) {
        return UseAnim.BLOCK;
    }

    @SubscribeEvent
    public static void onDamageBlock(LivingShieldBlockEvent event) {
        for (InteractionHand interactionhand : InteractionHand.values()) {
            LivingEntity entityBlocker = event.getEntity();
            ItemStack offhandItem = entityBlocker.getItemInHand(interactionhand);
            if (offhandItem.getItem() instanceof DwarvenRiotShieldItem && (!(entityBlocker instanceof Player player) || !player.getCooldowns().isOnCooldown(ModItems.DWARVEN_SHIELD.get()))) {
                if (entityBlocker.isCrouching()) {
                    MagicManager.spawnParticles(entityBlocker.level(), new BlastwaveParticleOptions(SchoolRegistry.LIGHTNING.get().getTargetingColor(), 5f), entityBlocker.getX(), entityBlocker.getY() + 0.325F, entityBlocker.getZ(), 1, 0, 0, 0, 0, true);
                    if (entityBlocker instanceof LivingEntity livingAttacker) {
                        double baseDamage = damageFor(entityBlocker);
                        RedstoneExplosionEntity chainLightning = new RedstoneExplosionEntity(livingAttacker.level(), livingAttacker, entityBlocker);
                        chainLightning.setDamage((float) baseDamage);
                        chainLightning.range = 15;
                        chainLightning.maxConnections = 5;
                        livingAttacker.level().addFreshEntity(chainLightning);
                    }

                    if (entityBlocker instanceof Player player) {
                        player.getCooldowns().addCooldown(ModItems.DWARVEN_SHIELD.get(), DwarvenRiotShieldItem.COOLDOWN);
                        player.disableShield();
                    }
                }
            }
        }
    }

    public static double damageFor(@Nullable Entity entity) {
        double baseDamage = 10.0;
        if (entity instanceof LivingEntity livingAttacker) {
            baseDamage = baseDamage * livingAttacker.getAttributeValue(AttributeRegistry.SPELL_POWER) * livingAttacker.getAttributeValue(AttributeRegistry.LIGHTNING_SPELL_POWER);
        }
        return baseDamage;
    }

    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.irons_spellbooks.passive_ability", new Object[]{Component.literal(Utils.timeFromTicks((float) this.getPassiveCooldownTicks(), 1)).withStyle(ChatFormatting.LIGHT_PURPLE)}).withStyle(ChatFormatting.DARK_PURPLE));
            tooltipComponents.add(Component.literal(" ").append(Component.translatable(this.getDescriptionId() + ".desc")).withStyle(ChatFormatting.BLUE));
        } else {
            tooltipComponents.add(Component.translatable("item.aces_spell_utils.more_details1").withStyle(ChatFormatting.GRAY));
        }
    }

    protected int getPassiveCooldownTicks() {
        return COOLDOWN;
    }

    private static final int BLOCK_ANIMATION = 0;

    private void handleBLockState(Player player, ItemStack stack) {
        if (BLOCK_ANIMATION == 0) {
            dispatcher.block(player, stack);
        }
    }

    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (!(entity instanceof Player player) || player.level().isClientSide) return;
        if (player.isBlocking()) {
            handleBLockState(player, stack);
        } else {
            dispatcher.block_release(player, stack);
        }
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return repair.is(ModItems.DWARVEN_ALLOY_PLATE.get()) || !repair.is(ModTags.LIGHTNING_FOCUS) && super.isValidRepairItem(toRepair, repair);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, net.neoforged.neoforge.common.ItemAbility itemAbility) {
        return net.neoforged.neoforge.common.ItemAbilities.DEFAULT_SHIELD_ACTIONS.contains(itemAbility);
    }
}



