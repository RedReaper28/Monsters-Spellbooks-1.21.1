package net.redreaper.monsterspellbooks.item.weapons;

import io.redspace.ironsspellbooks.api.events.ModifySpellLevelEvent;
import io.redspace.ironsspellbooks.api.item.curios.AffinityData;
import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.effect.ImmolateEffect;
import io.redspace.ironsspellbooks.item.UniqueItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import io.redspace.ironsspellbooks.util.MinecraftInstanceHelper;
import io.redspace.ironsspellbooks.util.TooltipsUtils;
import net.acetheeldritchking.aces_spell_utils.utils.ASRarities;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.redreaper.monsterspellbooks.init.ModExtendedWeaponTiers;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import net.redreaper.monsterspellbooks.procedures.effectsonhit.PoisonOnHit;
import net.redreaper.monsterspellbooks.procedures.effectsonhit.WitherOnHit;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Frostmourne extends MagicSwordItem implements UniqueItem {
    public Frostmourne() {
        super(
                ModExtendedWeaponTiers.FROST_TOUCHED_SUPERIOR_DEATHSILVER,
                ItemPropertiesHelper.equipment(1).fireResistant().rarity(ASRarities.GLACIAL_RARITY_PROXY.getValue()).attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.FROST_TOUCHED_SUPERIOR_DEATHSILVER)),
                SpellDataRegistryHolder.of(
                        new SpellDataRegistryHolder(ModSpellRegistry.BLIZZARD_ASPECT, 8)));
    }

    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack == null) {
            return;
        }

        super.initializeSpellContainer(itemStack);
        AffinityData.setAffinityData(itemStack, ModSpellRegistry.BLIZZARD_ASPECT.get(),1);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, TooltipContext context, @NotNull List<Component> lines, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemStack, context, lines, flag);
        var affinityData = AffinityData.getAffinityData(itemStack);
        if (!affinityData.affinityData().isEmpty()) {
            int i = TooltipsUtils.indexOfComponent(lines, "tooltip.irons_spellbooks.spellbook_spell_count");
            lines.addAll(i < 0 ? lines.size() : i + 1, affinityData.getDescriptionComponent());
        }
        lines.add(Component.translatable("tooltip.monsterspellbooks.frostmourne_passive_ability").withStyle(new ChatFormatting[]{ChatFormatting.DARK_AQUA}));
    }

    @EventBusSubscriber({Dist.CLIENT})
    public class SpellEvents {
        @SubscribeEvent
        public static void onModifySpellLevel(ModifySpellLevelEvent event) {
            LivingEntity caster = event.getEntity();
            if (caster != null) {
                if (event.getSpell() == ModSpellRegistry.BLIZZARD_ASPECT.get()) {
                    ItemStack mainHand = caster.getMainHandItem();
                    ItemStack offHand = caster.getOffhandItem();
                    boolean usingKnives = mainHand.getItem() instanceof Frostmourne || offHand.getItem() instanceof Frostmourne;
                    if (usingKnives) {
                        event.addLevels(1);
                    }

                }
            }
        }
    }


    public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
        boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
        WitherOnHit.execute(entity);
        return retval;
    }
}
