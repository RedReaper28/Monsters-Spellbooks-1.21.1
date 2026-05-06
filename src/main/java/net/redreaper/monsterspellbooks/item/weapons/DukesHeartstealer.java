package net.redreaper.monsterspellbooks.item.weapons;

import io.redspace.ironsspellbooks.api.events.ModifySpellLevelEvent;
import io.redspace.ironsspellbooks.api.item.curios.AffinityData;
import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.item.UniqueItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import io.redspace.ironsspellbooks.util.TooltipsUtils;
import net.acetheeldritchking.aces_spell_utils.utils.ASRarities;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.redreaper.monsterspellbooks.init.ModExtendedWeaponTiers;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DukesHeartstealer extends MagicSwordItem implements UniqueItem {
    public static final int COOLDOWN = 20 * 20;
    public DukesHeartstealer() {
        super(
                ModExtendedWeaponTiers.BLOOD_IRON,
                ItemPropertiesHelper.equipment(1).fireResistant().rarity(ASRarities.ACCURSED_RARITY_PROXY.getValue()).attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.BLOOD_IRON)),
                SpellDataRegistryHolder.of(
                        new SpellDataRegistryHolder(SpellRegistry.BLOOD_SLASH_SPELL, 5))
        );
    }

    @Override
    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack == null) {
            return;
        }

        super.initializeSpellContainer(itemStack);
        AffinityData.setAffinityData(itemStack, SpellRegistry.BLOOD_SLASH_SPELL.get(), 1);
    }

    @EventBusSubscriber({Dist.CLIENT})
    public class SpellEvents {
        @SubscribeEvent
        public static void onModifySpellLevel(ModifySpellLevelEvent event) {
            LivingEntity caster = event.getEntity();
            if (caster != null) {
                if (event.getSpell() == SpellRegistry.BLOOD_SLASH_SPELL.get()) {
                    ItemStack mainHand = caster.getMainHandItem();
                    ItemStack offHand = caster.getOffhandItem();
                    boolean usingKnives = mainHand.getItem() instanceof DukesHeartstealer || offHand.getItem() instanceof DukesHeartstealer;
                    if (usingKnives) {
                        event.addLevels(1);
                    }

                }
            }
        }
    }

    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.irons_spellbooks.passive_ability", new Object[]{Component.literal(Utils.timeFromTicks((float)this.getPassiveCooldownTicks(), 1)).withStyle(ChatFormatting.LIGHT_PURPLE)}).withStyle(ChatFormatting.DARK_PURPLE));
            tooltipComponents.add(Component.literal(" ").append(Component.translatable(this.getDescriptionId() + ".desc")).withStyle(new ChatFormatting[]{ChatFormatting.RED}));
        }
        else {
            tooltipComponents.add(Component.translatable("item.aces_spell_utils.more_details1").withStyle(ChatFormatting.GRAY));
        }
    }

    protected int getPassiveCooldownTicks() {
        return COOLDOWN;
    }
}