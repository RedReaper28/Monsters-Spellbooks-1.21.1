package net.redreaper.monsterspellbooks.entity.spells.redstone_lasers;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PlasmaBoltModel extends GeoModel<PlasmaBolt> {
    public ResourceLocation getModelResource(PlasmaBolt animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/entity/spells/plasma_bolt.geo.json");
    }

    public ResourceLocation getTextureResource(PlasmaBolt animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/entity/redstone_laser/redstone_laser.png");
    }

    public ResourceLocation getAnimationResource(PlasmaBolt animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "animations/entity/spells/plasma_bolt.animation.json");
    }
}