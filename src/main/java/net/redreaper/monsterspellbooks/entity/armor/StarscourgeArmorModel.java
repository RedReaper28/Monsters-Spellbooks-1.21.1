package net.redreaper.monsterspellbooks.entity.armor;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.item.armor.StarscourgeArmorItem;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class StarscourgeArmorModel extends DefaultedEntityGeoModel<StarscourgeArmorItem> {
    public StarscourgeArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath("monsterspellbooks", ""));
    }

    public ResourceLocation getModelResource(StarscourgeArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/armor/starscourge_armor.geo.json");
    }

    public ResourceLocation getTextureResource(StarscourgeArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/armor/starscourge_armor.png");
    }

    public ResourceLocation getAnimationResource(StarscourgeArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "animations/wizard_armor_animation.json");
    }
}
