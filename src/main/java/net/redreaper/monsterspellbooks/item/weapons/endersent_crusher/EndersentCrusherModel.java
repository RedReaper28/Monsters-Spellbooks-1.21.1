package net.redreaper.monsterspellbooks.item.weapons.endersent_crusher;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class EndersentCrusherModel extends DefaultedItemGeoModel<EndersentCrusherItem> {
    public EndersentCrusherModel() {super(ResourceLocation.fromNamespaceAndPath("monsterspellbooks", ""));}

    public ResourceLocation getModelResource(EndersentCrusherItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/item/weapons/endersent_crusher.geo.json");
    }

    public ResourceLocation getTextureResource(EndersentCrusherItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/item/weapons/endersent_crusher.png");
    }

    public ResourceLocation getAnimationResource(EndersentCrusherItem animatable) {
        return ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "animations/wizard_armor_animation.json");
    }
}

