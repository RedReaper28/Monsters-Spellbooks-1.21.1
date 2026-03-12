package net.redreaper.monsterspellbooks.entity.spells.infection_slash;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class InfectionSlashModel extends GeoModel<InfectionSlashProjectile> {
    public ResourceLocation getModelResource(InfectionSlashProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/entity/spells/infection_slash.geo.json");
    }

    public ResourceLocation getTextureResource(InfectionSlashProjectile animatable) {
        int frameCount = 4;
        int frameDuration = 2;
        int frame = animatable.tickCount / frameDuration % frameCount;
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/entity/infection_slash/infection_slash_large_" + frame + ".png");
    }

    public ResourceLocation getAnimationResource(InfectionSlashProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "animations/entity/spells/infection_slash.animation.json");
    }
}