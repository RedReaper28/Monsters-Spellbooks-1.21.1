package net.redreaper.monsterspellbooks.entity.spells.space_rupture;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.entity.spells.raigo.RaigoProjectile;
import software.bernie.geckolib.model.GeoModel;

public class SpaceRuptureModel extends GeoModel<SpaceRupture> {
    public ResourceLocation getModelResource(SpaceRupture animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/entity/spells/space_rupture.geo.json");
    }

    public ResourceLocation getTextureResource(SpaceRupture animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/entity/space_rupture/space_rupture.png");
    }

    public ResourceLocation getAnimationResource(SpaceRupture animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "animations/entity/spells/space_rupture.animation.json");
    }
}