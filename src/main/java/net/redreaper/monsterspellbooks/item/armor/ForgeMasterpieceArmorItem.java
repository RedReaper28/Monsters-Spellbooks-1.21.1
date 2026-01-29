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
import net.redreaper.monsterspellbooks.init.ModExtendedArmorMaterials;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class ForgeMasterpieceArmorItem extends ImbuableExtendedGeoArmorItem {

    public ForgeMasterpieceArmorItem(Type slot, Properties settings) {
        super(ModExtendedArmorMaterials.FORGE_MASTERPIECE_ARMOR, slot, settings,
                new AttributeContainer(AttributeRegistry.FIRE_SPELL_POWER, 0.20, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.FIRE_MAGIC_RESIST, 0.15F    , AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(ASAttributeRegistry.SPELL_RES_PENETRATION, 0.05F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.MAX_MANA, 125, AttributeModifier.Operation.ADD_VALUE));
    }

    private static final ResourceLocation LAYER = ResourceLocation.fromNamespaceAndPath(
            MonstersSpellbooks.MOD_ID,
            "textures/armor/forge_master_armor_glow.png");

    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        RenderType GLOW_RENDER_TYPE = RenderType.eyes(LAYER);

        return new EmissiveGenericCustomArmorRenderer<>(new ForgeMasterpieceArmorModel(), LAYER, GLOW_RENDER_TYPE);
    }
}