package net.redreaper.monsterspellbooks.item.weapons;

import io.redspace.ironsspellbooks.api.events.ModifySpellLevelEvent;
import io.redspace.ironsspellbooks.api.item.curios.AffinityData;
import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.entity.spells.poison_cloud.PoisonSplash;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import io.redspace.ironsspellbooks.util.TooltipsUtils;
import net.acetheeldritchking.aces_spell_utils.utils.ASRarities;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.redreaper.monsterspellbooks.init.ModExtendedWeaponTiers;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PoisonBiterItem extends MagicSwordItem {
    public PoisonBiterItem() {
        super(
                ModExtendedWeaponTiers.POISONED_SICKLE,
                ItemPropertiesHelper.equipment(1).fireResistant().rarity(Rarity.EPIC).attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.POISONED_SICKLE)),
                SpellDataRegistryHolder.of(
                        new SpellDataRegistryHolder(ModSpellRegistry.INFECTION_SLASH, 5))
        );
    }

    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack == null) {
            return;
        }

        super.initializeSpellContainer(itemStack);
        AffinityData.setAffinityData(itemStack, ModSpellRegistry.INFECTION_SLASH.get(),1);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @NotNull TooltipContext context, @NotNull List<Component> lines, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemStack, context, lines, flag);
        var affinityData = AffinityData.getAffinityData(itemStack);
        if (!affinityData.affinityData().isEmpty()) {
            int i = TooltipsUtils.indexOfComponent(lines, "tooltip.irons_spellbooks.spellbook_spell_count");
            lines.addAll(i < 0 ? lines.size() : i + 1, affinityData.getDescriptionComponent());
        }
        lines.add(Component.translatable("tooltip.monsterspellbooks.poison_bite").withStyle(new ChatFormatting[]{ChatFormatting.GREEN}));
    }

    @EventBusSubscriber({Dist.CLIENT})
    public class SpellEvents {
        @SubscribeEvent
        public static void onModifySpellLevel(ModifySpellLevelEvent event) {
            LivingEntity caster = event.getEntity();
            if (caster != null) {
                if (event.getSpell() == ModSpellRegistry.INFECTION_SLASH.get()) {
                    ItemStack mainHand = caster.getMainHandItem();
                    ItemStack offHand = caster.getOffhandItem();
                    boolean usingKnives = mainHand.getItem() instanceof PoisonBiterItem || offHand.getItem() instanceof PoisonBiterItem;
                    if (usingKnives) {
                        event.addLevels(1);
                    }

                }
            }
        }
    }

    public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceentity) {
        boolean retval = super.hurtEnemy(stack, entity, sourceentity);
        if (entity.isDeadOrDying()) {
            PoisonSplash cloud = new PoisonSplash(sourceentity.level());
            cloud.setOwner(sourceentity);
            cloud.setDuration(200);
            cloud.setPos(entity.position());
            cloud.setDamage(10);
            sourceentity.level().addFreshEntity(cloud);
        }
        return retval;
    }
}
