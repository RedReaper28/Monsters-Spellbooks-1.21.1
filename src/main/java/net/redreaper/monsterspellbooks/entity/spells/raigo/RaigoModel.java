package net.redreaper.monsterspellbooks.entity.spells.raigo;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class RaigoModel extends GeoModel<RaigoProjectile> {
    public ResourceLocation getModelResource(RaigoProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/entity/spells/raigo.geo.json");
    }

    public ResourceLocation getTextureResource(RaigoProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/entity/raigo/raigo.png");
    }

    public ResourceLocation getAnimationResource(RaigoProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "animations/entity/spells/raigo.animation.json");
    }
}