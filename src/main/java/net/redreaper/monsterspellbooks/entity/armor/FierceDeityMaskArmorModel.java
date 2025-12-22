package net.redreaper.monsterspellbooks.entity.armor;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.item.armor.FierceDeityMaskItem;
import software.bernie.geckolib.model.GeoModel;

public class FierceDeityMaskArmorModel extends GeoModel<FierceDeityMaskItem> {

    public FierceDeityMaskArmorModel() {
        super();

    }

    @Override
    public ResourceLocation getModelResource(FierceDeityMaskItem object) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID,"geo/armor/fierce_deity_mask.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FierceDeityMaskItem object) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/armor/fierce_deity_mask.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FierceDeityMaskItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "animations/wizard_armor_animation.json");
    }
}
