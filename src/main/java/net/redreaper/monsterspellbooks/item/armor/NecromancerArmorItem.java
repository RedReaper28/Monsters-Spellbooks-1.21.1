package net.redreaper.monsterspellbooks.item.armor;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.armor.IArmorCapeProvider;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.registries.ArmorMaterialRegistry;
import net.acetheeldritchking.aces_spell_utils.entity.render.armor.EmissiveGenericCustomArmorRenderer;
import net.acetheeldritchking.aces_spell_utils.items.example.items.armor.ImbuableExtendedGeoArmorItem;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.armor.NecromancerArmorModel;
import net.redreaper.monsterspellbooks.init.ModAtributeRegistry;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class NecromancerArmorItem extends ImbuableExtendedGeoArmorItem implements IArmorCapeProvider {
    public NecromancerArmorItem(ArmorItem.Type slot, Properties settings) {
        super(ArmorMaterialRegistry.SCHOOL, slot, settings,
                new AttributeContainer(AttributeRegistry.MAX_MANA, 125, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(ModAtributeRegistry.NECRO_MAGIC_POWER, 0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.SPELL_POWER, 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }

    @Override
    public ResourceLocation getCapeResourceLocation() {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/armor/necromancer_cape.png");
    }

    private static final ResourceLocation LAYER = ResourceLocation.fromNamespaceAndPath(
            MonstersSpellbooks.MOD_ID,
            "textures/armor/necromancer_armor_glow.png");

    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        RenderType GLOW_RENDER_TYPE = RenderType.eyes(LAYER);

        return new EmissiveGenericCustomArmorRenderer<>(new NecromancerArmorModel(), LAYER, GLOW_RENDER_TYPE);
    }
}