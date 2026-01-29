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
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.armor.ForgeMasterpieceArmorModel;
import net.redreaper.monsterspellbooks.entity.armor.PaleObserverArmorModel;
import net.redreaper.monsterspellbooks.init.ModExtendedArmorMaterials;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class PaleObserverArmorItem extends ImbuableExtendedGeoArmorItem {

    public PaleObserverArmorItem(Type slot, Properties settings) {
        super(ModExtendedArmorMaterials.PALE_OBSERVER, slot, settings,
                new AttributeContainer(AttributeRegistry.NATURE_SPELL_POWER,0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.ELDRITCH_SPELL_POWER, 0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(ASAttributeRegistry.MANA_STEAL, 0.05F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.MAX_MANA, 125, AttributeModifier.Operation.ADD_VALUE)
        );
    }

    private static final ResourceLocation LAYER = ResourceLocation.fromNamespaceAndPath(
            MonstersSpellbooks.MOD_ID,
            "textures/armor/pale_observer_armor_glow.png");

    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        RenderType GLOW_RENDER_TYPE = RenderType.eyes(LAYER);

        return new EmissiveGenericCustomArmorRenderer<>(new PaleObserverArmorModel(), LAYER, GLOW_RENDER_TYPE);
    }
}
