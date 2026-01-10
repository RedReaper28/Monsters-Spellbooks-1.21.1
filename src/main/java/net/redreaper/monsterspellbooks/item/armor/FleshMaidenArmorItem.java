package net.redreaper.monsterspellbooks.item.armor;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.acetheeldritchking.aces_spell_utils.entity.render.armor.EmissiveGenericCustomArmorRenderer;
import net.acetheeldritchking.aces_spell_utils.items.example.ImbuableExtendedGeoArmorItem;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.redreaper.monsterspellbooks.entity.armor.FleshMaidenArmorModel;
import net.redreaper.monsterspellbooks.init.ModExtendedArmorMaterials;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import software.bernie.geckolib.renderer.GeoArmorRenderer;


public class FleshMaidenArmorItem extends ImbuableExtendedGeoArmorItem {
    private static final ResourceLocation LAYER = ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/armor/flesh_maiden_glow.png");
    private static final RenderType GLOW_RENDER_TYPE;

    public FleshMaidenArmorItem(Type slot, Properties settings) {
        super(ModExtendedArmorMaterials.FLESH_MAIDEN, slot, settings,
                new AttributeContainer(AttributeRegistry.MAX_MANA, 125, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(AttributeRegistry.BLOOD_SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        SpellDataRegistryHolder.of(
                new SpellDataRegistryHolder(ModSpellRegistry.HYSTERIA, 4)
        );
    }


    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new EmissiveGenericCustomArmorRenderer<>(new FleshMaidenArmorModel(), LAYER, GLOW_RENDER_TYPE);
    }

    static {
        GLOW_RENDER_TYPE = RenderType.eyes(LAYER);
    }
}
