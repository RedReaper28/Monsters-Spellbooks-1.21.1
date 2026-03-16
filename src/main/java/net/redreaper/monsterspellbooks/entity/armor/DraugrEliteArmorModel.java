package net.redreaper.monsterspellbooks.entity.armor;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.item.armor.DraugrEliteArmorItem;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class DraugrEliteArmorModel extends DefaultedItemGeoModel<DraugrEliteArmorItem> {
    public DraugrEliteArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath("monsterspellbooks", ""));
    }

    public ResourceLocation getModelResource(DraugrEliteArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/armor/elite_draugr_armor.geo.json");
    }

    public ResourceLocation getTextureResource(DraugrEliteArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/armor/elite_draugr_armor.png");
    }

    public ResourceLocation getAnimationResource(DraugrEliteArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "animations/wizard_armor_animation.json");
    }
}