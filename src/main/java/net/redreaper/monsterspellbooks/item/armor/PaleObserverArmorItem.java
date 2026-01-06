package net.redreaper.monsterspellbooks.item.armor;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.acetheeldritchking.aces_spell_utils.entity.render.armor.EmissiveGenericCustomArmorRenderer;
import net.acetheeldritchking.aces_spell_utils.items.example.ImbuableExtendedGeoArmorItem;
import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.redreaper.monsterspellbooks.entity.armor.PaleObserverArmorModel;
import net.redreaper.monsterspellbooks.init.ModExtendedArmorMaterials;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class PaleObserverArmorItem extends ImbuableExtendedGeoArmorItem {
    private static final ResourceLocation LAYER = ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/armor/pale_observer_armor_glow.png");
    private static final RenderType GLOW_RENDER_TYPE;

    public PaleObserverArmorItem(Type slot, Properties settings) {
        super(ModExtendedArmorMaterials.PALE_OBSERVER, slot, settings,
                new AttributeContainer(AttributeRegistry.NATURE_SPELL_POWER,0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.ELDRITCH_SPELL_POWER, 0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(ASAttributeRegistry.MANA_STEAL, 0.05F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.MAX_MANA, 125, AttributeModifier.Operation.ADD_VALUE)
        );
    }

    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new EmissiveGenericCustomArmorRenderer<>(new PaleObserverArmorModel(), LAYER, GLOW_RENDER_TYPE);
    }

    static {
        GLOW_RENDER_TYPE = RenderType.breezeEyes(LAYER);
    }
}
