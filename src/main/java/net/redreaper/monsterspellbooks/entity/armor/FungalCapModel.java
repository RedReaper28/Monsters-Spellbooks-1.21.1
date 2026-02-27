package net.redreaper.monsterspellbooks.entity.armor;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.item.armor.FunGalCapItem;
import software.bernie.geckolib.model.GeoModel;

public class FungalCapModel extends GeoModel<FunGalCapItem> {

    public FungalCapModel() {super();}

    @Override
    public ResourceLocation getModelResource(FunGalCapItem object) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/armor/fun_gal_cap.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FunGalCapItem object) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/armor/fun_gal_cap_red.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FunGalCapItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "animations/wizard_armor_animation.json");
    }
}