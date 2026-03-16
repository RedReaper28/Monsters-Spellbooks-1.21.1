package net.redreaper.monsterspellbooks.entity.armor;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.item.armor.DraugrEliteArmorItem;
import net.redreaper.monsterspellbooks.item.armor.IllagerEnchanterArmorItem;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class IllagerEnchanterArmorModel extends DefaultedItemGeoModel<IllagerEnchanterArmorItem> {
    public IllagerEnchanterArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath("monsterspellbooks", ""));
    }

    public ResourceLocation getModelResource(IllagerEnchanterArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/armor/illager_enchanter_armor.geo.json");
    }

    public ResourceLocation getTextureResource(IllagerEnchanterArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/armor/illager_enchanter_armor.png");
    }

    public ResourceLocation getAnimationResource(IllagerEnchanterArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "animations/wizard_armor_animation.json");
    }
}