package net.redreaper.monsterspellbooks.item.armor;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.acetheeldritchking.aces_spell_utils.entity.render.armor.EmissiveGenericCustomArmorRenderer;
import net.acetheeldritchking.aces_spell_utils.items.example.ImbuableExtendedGeoArmorItem;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.redreaper.monsterspellbooks.entity.armor.DwarvenEngineerArmorModel;
import net.redreaper.monsterspellbooks.init.ModExtendedArmorMaterials;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class DwarvenEngineerArmorItem extends ImbuableExtendedGeoArmorItem {
    private static final ResourceLocation LAYER = ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/armor/dwarven_engineer_armor_glow.png");
    private static final RenderType GLOW_RENDER_TYPE;

    public DwarvenEngineerArmorItem(Type slot, Properties settings) {
        super(ModExtendedArmorMaterials.DWARVEN_ENGINEER, slot, settings,
                new AttributeContainer(AttributeRegistry.LIGHTNING_SPELL_POWER,0.20, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.LIGHTNING_MAGIC_RESIST, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.MANA_REGEN, 0.10F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.CAST_TIME_REDUCTION, 0.10F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.MAX_MANA, 125, AttributeModifier.Operation.ADD_VALUE)
        );
    }

    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new EmissiveGenericCustomArmorRenderer<>(new DwarvenEngineerArmorModel(), LAYER, GLOW_RENDER_TYPE);
    }

    static {
        GLOW_RENDER_TYPE = RenderType.breezeEyes(LAYER);
    }
}