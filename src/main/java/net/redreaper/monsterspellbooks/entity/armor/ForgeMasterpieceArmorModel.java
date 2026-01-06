package net.redreaper.monsterspellbooks.entity.armor;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.item.armor.ForgeMasterpieceArmorItem;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class ForgeMasterpieceArmorModel extends DefaultedItemGeoModel<ForgeMasterpieceArmorItem> {
    public ForgeMasterpieceArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath("monsterspellbooks", ""));
    }

    public ResourceLocation getModelResource(ForgeMasterpieceArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/armor/forge_masterpiece_armor.geo.json");
    }

    public ResourceLocation getTextureResource(ForgeMasterpieceArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/armor/forge_master_armor.png");
    }

    public ResourceLocation getAnimationResource(ForgeMasterpieceArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "animations/wizard_armor_animation.json");
    }
}