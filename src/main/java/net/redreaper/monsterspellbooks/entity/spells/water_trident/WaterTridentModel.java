package net.redreaper.monsterspellbooks.entity.spells.water_trident;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.entity.spells.stray_grasp.StrayGraspProjectile;
import software.bernie.geckolib.model.GeoModel;

public class WaterTridentModel extends GeoModel<WaterTridentProjectile> {
    public ResourceLocation getModelResource(WaterTridentProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/entity/spells/water_trident.geo.json");
    }

    public ResourceLocation getTextureResource(WaterTridentProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/entity/water_trident/water_trident.png");
    }

    public ResourceLocation getAnimationResource(WaterTridentProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "animations/entity/spells/water_trident.animation.json");
    }
}