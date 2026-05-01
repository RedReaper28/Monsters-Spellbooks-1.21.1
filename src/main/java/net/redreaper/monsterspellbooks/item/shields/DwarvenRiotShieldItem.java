package net.redreaper.monsterspellbooks.item.shields;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.entity.spells.ChainLightning;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingShieldBlockEvent;
import net.redreaper.monsterspellbooks.init.ModItems;
import net.redreaper.monsterspellbooks.item.extended.magic_shield.ExtendedShieldItem;
import net.redreaper.monsterspellbooks.utils.ModRarities;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@EventBusSubscriber
public class DwarvenRiotShieldItem extends ExtendedShieldItem {
    public static final int COOLDOWN = 10 * 20;
    public static final int PASSIVE_COOLDOWN = 15 * 20;

    public DwarvenRiotShieldItem() {
        super(ItemPropertiesHelper.equipment(1).fireResistant().rarity(ModRarities.DWARVEN_RARITY_PROXY.getValue()));
    }

    @SubscribeEvent
    public static void onDamageBlock(LivingShieldBlockEvent event) {
        LivingEntity entityBlocker = event.getEntity();
        ItemStack offhandItem = entityBlocker.getOffhandItem();
        if (offhandItem.getItem() instanceof DwarvenRiotShieldItem && (!(entityBlocker instanceof Player player) || !player.getCooldowns().isOnCooldown(ModItems.DWARVEN_SHIELD.get()))) {
            if (entityBlocker.isCrouching()){
            if (entityBlocker instanceof LivingEntity livingAttacker) {
                double baseDamage = damageFor(entityBlocker);
                ChainLightning chainLightning = new ChainLightning(livingAttacker.level(), livingAttacker, entityBlocker);
                chainLightning.setDamage((float) baseDamage);
                chainLightning.range = 15;
                chainLightning.maxConnections = 5;
                livingAttacker.level().addFreshEntity(chainLightning);
            }

            if (entityBlocker instanceof Player player) {
                player.getCooldowns().addCooldown(ModItems.DWARVEN_SHIELD.get(), DwarvenRiotShieldItem.PASSIVE_COOLDOWN);
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
            tooltipComponents.add(Component.translatable("tooltip.irons_spellbooks.passive_ability", new Object[]{Component.literal(Utils.timeFromTicks((float)this.getPassiveCooldownTicks(), 1)).withStyle(ChatFormatting.LIGHT_PURPLE)}).withStyle(ChatFormatting.DARK_PURPLE));
            tooltipComponents.add(Component.literal(" ").append(Component.translatable(this.getDescriptionId() + ".desc")).withStyle(ChatFormatting.AQUA));
        }
        else {
            tooltipComponents.add(Component.translatable("item.aces_spell_utils.more_details1").withStyle(ChatFormatting.GRAY));
        }
    }

    protected int getPassiveCooldownTicks() {
        return 5 * 20;
    }

    protected int getActiveCooldownTicks() {
        return 5 * 20;
    }
}


