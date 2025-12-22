package net.redreaper.monsterspellbooks.entity.armor;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.item.armor.FierceDeityMaskItem;
import net.redreaper.monsterspellbooks.item.armor.MajorasMaskArmorItem;
import software.bernie.geckolib.model.GeoModel;

public class MajorasMaskArmorModel extends GeoModel<MajorasMaskArmorItem> {

    public MajorasMaskArmorModel() {
        super();

    }

    @Override
    public ResourceLocation getModelResource(MajorasMaskArmorItem object) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/armor/mask_of_evil.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MajorasMaskArmorItem object) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/armor/mask_of_evil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MajorasMaskArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "animations/wizard_armor_animation.json");
    }
}
