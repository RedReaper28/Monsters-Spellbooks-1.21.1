package net.redreaper.monsterspellbooks.entity.spells.stray_grasp;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class StrayGraspModel extends GeoModel<StrayGraspProjectile> {
    public ResourceLocation getModelResource(StrayGraspProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/entity/spells/stray_grasp.geo.json");
    }

    public ResourceLocation getTextureResource(StrayGraspProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/entity/stray_grasp/stray_grasp.png");
    }

    public ResourceLocation getAnimationResource(StrayGraspProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "animations/entity/spells/stray_grasp.animation.json");
    }
}