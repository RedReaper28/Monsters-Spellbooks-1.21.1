package net.redreaper.monsterspellbooks.item.armor;

import io.redspace.ironsspellbooks.api.events.ModifySpellLevelEvent;
import io.redspace.ironsspellbooks.api.item.curios.AffinityData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.item.armor.ImbuableChestplateArmorItem;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.registries.ComponentRegistry;
import io.redspace.ironsspellbooks.util.TooltipsUtils;
import net.acetheeldritchking.aces_spell_utils.entity.render.armor.EmissiveGenericCustomArmorRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.armor.WildFireCrownModel;
import net.redreaper.monsterspellbooks.init.ModExtendedArmorMaterials;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.List;
import java.util.Map;

public class WildFireCrownItem extends ImbuableChestplateArmorItem implements IPresetSpellContainer {
    public WildFireCrownItem(ArmorItem.Type slot, Properties settings) {
        super(ModExtendedArmorMaterials.WILDFIRE_CROWN, slot, settings,
                new AttributeContainer(AttributeRegistry.FIRE_SPELL_POWER, 0.25, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.MAX_MANA, 125, AttributeModifier.Operation.ADD_VALUE)
        );
    }

    private static final ResourceLocation LAYER = ResourceLocation.fromNamespaceAndPath(
            MonstersSpellbooks.MOD_ID,
            "textures/armor/wildfire_crown_glow.png");

    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        RenderType GLOW_RENDER_TYPE = RenderType.eyes(LAYER);

        return new EmissiveGenericCustomArmorRenderer<>(new WildFireCrownModel(), LAYER, GLOW_RENDER_TYPE);
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