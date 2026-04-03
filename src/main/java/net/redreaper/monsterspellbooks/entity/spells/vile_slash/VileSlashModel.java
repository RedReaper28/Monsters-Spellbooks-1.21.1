package net.redreaper.monsterspellbooks.entity.spells.vile_slash;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class VileSlashModel extends GeoModel<VileSlashProjectileNew> {
    public ResourceLocation getModelResource(VileSlashProjectileNew animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/entity/spells/vile_slash.geo.json");
    }

    public ResourceLocation getTextureResource(VileSlashProjectileNew animatable) {
        int frameCount = 4;
        int frameDuration = 4;
        int frame = animatable.tickCount / frameDuration % frameCount;
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/entity/vile_slash/vile_slash_" + frame + ".png");
    }

    public ResourceLocation getAnimationResource(VileSlashProjectileNew animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "animations/entity/spells/vile_slash.animation.json");
    }
}