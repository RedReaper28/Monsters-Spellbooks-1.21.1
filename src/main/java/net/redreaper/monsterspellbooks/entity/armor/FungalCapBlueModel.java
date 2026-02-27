package net.redreaper.monsterspellbooks.entity.armor;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.item.armor.FunGalCapBlueItem;
import net.redreaper.monsterspellbooks.item.armor.FunGalCapItem;
import software.bernie.geckolib.model.GeoModel;

public class FungalCapBlueModel extends GeoModel<FunGalCapBlueItem> {

    public FungalCapBlueModel() {
        super();
    }

    @Override
    public ResourceLocation getModelResource(FunGalCapBlueItem object) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/armor/fun_gal_cap.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FunGalCapBlueItem object) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/armor/fun_gal_cap_blue.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FunGalCapBlueItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "animations/wizard_armor_animation.json");
    }
}