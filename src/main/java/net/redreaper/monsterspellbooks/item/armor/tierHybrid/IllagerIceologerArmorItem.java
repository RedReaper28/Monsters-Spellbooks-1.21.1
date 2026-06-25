package net.redreaper.monsterspellbooks.item.armor.tierHybrid;

import io.redspace.ironsspellbooks.api.events.ModifySpellLevelEvent;
import io.redspace.ironsspellbooks.api.item.curios.AffinityData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.entity.armor.GenericCustomArmorRenderer;
import io.redspace.ironsspellbooks.item.armor.IArmorCapeProvider;
import io.redspace.ironsspellbooks.item.armor.ImbuableChestplateArmorItem;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.registries.ComponentRegistry;
import io.redspace.ironsspellbooks.util.TooltipsUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.armor.IllagerIceologerArmorModel;
import net.redreaper.monsterspellbooks.init.ModExtendedArmorMaterials;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import net.redreaper.monsterspellbooks.item.armor.custom.PresetImbueArmorItem;
import net.redreaper.monsterspellbooks.item.armor.tierUnique.WildFireCrownItem;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.List;
import java.util.Map;

public class IllagerIceologerArmorItem extends PresetImbueArmorItem implements IArmorCapeProvider {
    public IllagerIceologerArmorItem(Type slot, Properties settings) {
        super(ModExtendedArmorMaterials.ICEOLOGER, slot, settings, SpellDataRegistryHolder.of(
                new SpellDataRegistryHolder(SpellRegistry.ICE_BLOCK_SPELL, 6)),
                new AttributeContainer(AttributeRegistry.EVOCATION_SPELL_POWER,0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.ICE_SPELL_POWER,0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.MAX_MANA, 125, AttributeModifier.Operation.ADD_VALUE)
        );
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, TooltipContext context, @NotNull List<Component> lines, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemStack, context, lines, flag);
        var affinityData = AffinityData.getAffinityData(itemStack);
        if (this.type == Type.CHESTPLATE) {
            if (!affinityData.affinityData().isEmpty()) {
                int i = TooltipsUtils.indexOfComponent(lines, "tooltip.irons_spellbooks.spellbook_spell_count");
                lines.addAll(i < 0 ? lines.size() : i + 1, affinityData.getDescriptionComponent());
            }
        }
    }

    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack != null) {
            super.initializeSpellContainer(itemStack);
            itemStack.set(ComponentRegistry.AFFINITY_COMPONENT, new AffinityData(Map.of(SpellRegistry.ICE_BLOCK_SPELL.get().getSpellResource(), 1)));
        }
    }

    @EventBusSubscriber({Dist.CLIENT})
    public class SpellEvents {
        @SubscribeEvent
        public static void onModifySpellLevel(ModifySpellLevelEvent event) {
            LivingEntity caster = event.getEntity();
            if (caster != null) {
                if (event.getSpell() == SpellRegistry.ICE_BLOCK_SPELL.get()) {
                    ItemStack chestItem = caster.getItemBySlot(EquipmentSlot.CHEST);
                    if (chestItem.getItem() instanceof IllagerIceologerArmorItem) {
                        event.addLevels(1);
                    }
                }
            }
        }
    }

    @Override
    public ResourceLocation getCapeResourceLocation() {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/armor/illager_iceologer_armor_cape.png");
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new GenericCustomArmorRenderer<>(new IllagerIceologerArmorModel());
    }
}
