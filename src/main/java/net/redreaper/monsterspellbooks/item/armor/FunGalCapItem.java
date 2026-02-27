package net.redreaper.monsterspellbooks.item.armor;

import io.redspace.ironsspellbooks.api.events.ModifySpellLevelEvent;
import io.redspace.ironsspellbooks.api.item.curios.AffinityData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.entity.armor.GenericCustomArmorRenderer;
import io.redspace.ironsspellbooks.item.armor.ImbuableChestplateArmorItem;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.registries.ComponentRegistry;
import io.redspace.ironsspellbooks.util.TooltipsUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.redreaper.monsterspellbooks.entity.armor.FungalCapModel;
import net.redreaper.monsterspellbooks.init.ModExtendedArmorMaterials;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.List;
import java.util.Map;

public class FunGalCapItem extends ImbuableChestplateArmorItem implements IPresetSpellContainer {
    public FunGalCapItem(ArmorItem.Type slot, Properties settings) {
        super(ModExtendedArmorMaterials.FUNGAL_CAP, slot, settings,
                new AttributeContainer(AttributeRegistry.NATURE_SPELL_POWER, 0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.MAX_MANA, 125, AttributeModifier.Operation.ADD_VALUE)
        );
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new GenericCustomArmorRenderer<>(new FungalCapModel());
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, TooltipContext context, @NotNull List<Component> lines, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemStack, context, lines, flag);
        var affinityData = AffinityData.getAffinityData(itemStack);
        if (!affinityData.affinityData().isEmpty()) {
            int i = TooltipsUtils.indexOfComponent(lines, "tooltip.irons_spellbooks.spellbook_spell_count");
            lines.addAll(i < 0 ? lines.size() : i + 1, affinityData.getDescriptionComponent());
        }
    }

    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack != null) {
            super.initializeSpellContainer(itemStack);
            itemStack.set(ComponentRegistry.AFFINITY_COMPONENT, new AffinityData(Map.of(SpellRegistry.BLAZE_STORM_SPELL.get().getSpellResource(), 1)));
        }
    }

    @EventBusSubscriber({Dist.CLIENT})
    public class SpellEvents {
        @SubscribeEvent
        public static void onModifySpellLevel(ModifySpellLevelEvent event) {
            LivingEntity caster = event.getEntity();
            if (caster != null) {
                if (event.getSpell() == SpellRegistry.BLAZE_STORM_SPELL.get()) {
                    ItemStack chestItem = caster.getItemBySlot(EquipmentSlot.HEAD);
                    if (chestItem.getItem() instanceof WildFireCrownItem) {
                        event.addLevels(1);
                    }

                }
            }
        }
    }
}
