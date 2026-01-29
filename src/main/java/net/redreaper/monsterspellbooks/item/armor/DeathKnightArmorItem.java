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
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.armor.DeathKnightArmorModel;
import net.redreaper.monsterspellbooks.init.ModAtributeRegistry;
import net.redreaper.monsterspellbooks.init.ModExtendedArmorMaterials;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class DeathKnightArmorItem extends ImbuableExtendedGeoArmorItem {
    public DeathKnightArmorItem(Type slot, Properties settings) {
        super(ModExtendedArmorMaterials.FROST_DEATHSILVER, slot, settings,
                new AttributeContainer(AttributeRegistry.ICE_SPELL_POWER,0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(ModAtributeRegistry.NECRO_MAGIC_POWER, 0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.MAX_MANA, 125, AttributeModifier.Operation.ADD_VALUE)
        );
    }

    private static final ResourceLocation LAYER = ResourceLocation.fromNamespaceAndPath(
            MonstersSpellbooks.MOD_ID,
            "textures/armor/death_knight_armor_glow.png");

    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        RenderType GLOW_RENDER_TYPE = RenderType.eyes(LAYER);

        return new EmissiveGenericCustomArmorRenderer<>(new DeathKnightArmorModel(), LAYER, GLOW_RENDER_TYPE);
    }
}
