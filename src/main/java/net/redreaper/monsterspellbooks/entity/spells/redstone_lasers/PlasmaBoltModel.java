package net.redreaper.monsterspellbooks.entity.spells.redstone_lasers;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PlasmaBoltModel extends GeoModel<RedstoneBolt> {
    public ResourceLocation getModelResource(RedstoneBolt animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/entity/spells/plasma_bolt.geo.json");
    }

    public ResourceLocation getTextureResource(RedstoneBolt animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/entity/redstone_laser/redstone_laser.png");
    }

    public ResourceLocation getAnimationResource(RedstoneBolt animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "animations/entity/spells/plasma_bolt.animation.json");
    }
}