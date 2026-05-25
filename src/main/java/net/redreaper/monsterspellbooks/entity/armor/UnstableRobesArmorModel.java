package net.redreaper.monsterspellbooks.entity.armor;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.item.armor.UnstableRobeArmorItem;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class UnstableRobesArmorModel extends DefaultedEntityGeoModel<UnstableRobeArmorItem> {
    public UnstableRobesArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath("monsterspellbooks", ""));
    }

    public ResourceLocation getModelResource(UnstableRobeArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/armor/unstable_armor.geo.json");
    }

    public ResourceLocation getTextureResource(UnstableRobeArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/armor/unstable_armor.png");
    }

    public ResourceLocation getAnimationResource(UnstableRobeArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "animations/wizard_armor_animation.json");
    }
}
