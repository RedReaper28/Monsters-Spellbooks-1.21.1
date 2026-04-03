package net.redreaper.monsterspellbooks.entity.spells.forceful_wind;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ForcefulWindModel extends GeoModel<ForcefulWindProjectile> {
    public ResourceLocation getModelResource(ForcefulWindProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/entity/spells/forceful_wind.geo.json");
    }

    public ResourceLocation getTextureResource(ForcefulWindProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/entity/pain_tornado/pain_tornado.png");
    }

    public ResourceLocation getAnimationResource(ForcefulWindProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "animations/entity/spells/pain_tornado.animation.json");
    }
}