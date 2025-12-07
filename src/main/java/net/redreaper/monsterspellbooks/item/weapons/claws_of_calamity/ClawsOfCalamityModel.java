package net.redreaper.monsterspellbooks.item.weapons.claws_of_calamity;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class ClawsOfCalamityModel extends DefaultedItemGeoModel<ClawsOfCalamityItem> {
    public ClawsOfCalamityModel() {super(ResourceLocation.fromNamespaceAndPath("monsterspellbooks", ""));}

    public ResourceLocation getModelResource(ClawsOfCalamityItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/item/weapons/claws_of_calamity.geo.json");
    }

    public ResourceLocation getTextureResource(ClawsOfCalamityItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/item/weapons/claws_of_calamity.png");
    }

    public ResourceLocation getAnimationResource(ClawsOfCalamityItem animatable) {
        return ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "animations/wizard_armor_animation.json");
    }
}

