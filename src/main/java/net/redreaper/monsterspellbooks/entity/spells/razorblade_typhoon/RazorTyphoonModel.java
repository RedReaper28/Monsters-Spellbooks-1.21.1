package net.redreaper.monsterspellbooks.entity.spells.razorblade_typhoon;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.entity.spells.raigo.RaigoProjectile;
import net.redreaper.monsterspellbooks.entity.spells.wither_bomb.WitherBombProjectile;
import software.bernie.geckolib.model.GeoModel;

public class RazorTyphoonModel extends GeoModel<RazorbladeTyphoonProjectile> {
    public ResourceLocation getModelResource(RazorbladeTyphoonProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/entity/spells/razorblade_typhoon.geo.json");
    }

    public ResourceLocation getTextureResource(RazorbladeTyphoonProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/entity/razor_typhon/razor_typhon.png");
    }

    public ResourceLocation getAnimationResource(RazorbladeTyphoonProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "animations/entity/spells/razor_typhon.animation.json");
    }
}