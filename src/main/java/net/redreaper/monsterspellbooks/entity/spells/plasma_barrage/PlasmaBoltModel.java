package net.redreaper.monsterspellbooks.entity.spells.plasma_barrage;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.entity.spells.raigo.RaigoProjectile;
import software.bernie.geckolib.model.GeoModel;

public class PlasmaBoltModel extends GeoModel<PlasmaBolt> {
    public ResourceLocation getModelResource(PlasmaBolt animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/entity/spells/plasma_bolt.geo.json");
    }

    public ResourceLocation getTextureResource(PlasmaBolt animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/entity/plasma_bolt/plasma_bolt.png");
    }

    public ResourceLocation getAnimationResource(PlasmaBolt animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "animations/entity/spells/plasma_bolt.animation.json");
    }
}