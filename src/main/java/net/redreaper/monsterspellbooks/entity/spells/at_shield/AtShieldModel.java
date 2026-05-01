package net.redreaper.monsterspellbooks.entity.spells.at_shield;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.entity.spells.paladin_throw.HolyHammerProjectile;
import software.bernie.geckolib.model.GeoModel;

public class AtShieldModel extends GeoModel<AtShield> {
    public ResourceLocation getModelResource(AtShield animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/entity/spells/at_shield.geo.json");
    }

    public ResourceLocation getTextureResource(AtShield animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/entity/at_shield/at_shield.png");
    }

    public ResourceLocation getAnimationResource(AtShield animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "animations/entity/spells/at_shield.animation.json");
    }
}