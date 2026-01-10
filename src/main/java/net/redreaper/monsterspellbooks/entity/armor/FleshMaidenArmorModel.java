package net.redreaper.monsterspellbooks.entity.armor;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.item.armor.DwarvenEngineerArmorItem;
import net.redreaper.monsterspellbooks.item.armor.FleshMaidenArmorItem;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class FleshMaidenArmorModel extends DefaultedItemGeoModel<FleshMaidenArmorItem> {
    public FleshMaidenArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath("monsterspellbooks", ""));
    }

    public ResourceLocation getModelResource(FleshMaidenArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/armor/flesh_maiden.geo.json");
    }

    public ResourceLocation getTextureResource(FleshMaidenArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/armor/flesh_maiden.png");
    }

    public ResourceLocation getAnimationResource(FleshMaidenArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "animations/wizard_armor_animation.json");
    }
}