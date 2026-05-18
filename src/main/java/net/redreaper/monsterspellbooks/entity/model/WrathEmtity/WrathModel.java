package net.redreaper.monsterspellbooks.entity.model.WrathEmtity;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.entity.living.summons.WrathEntity;
import software.bernie.geckolib.model.GeoModel;

public class WrathModel extends GeoModel<WrathEntity> {
    public ResourceLocation getModelResource(WrathEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/entity/spells/wrath.geo.json");
    }

    public ResourceLocation getTextureResource(WrathEntity animatable) {
        int frameCount = 4;
        int frameDuration = 2;
        int frame = animatable.tickCount / frameDuration % frameCount;
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/entity/wrath/wrath_" + frame + ".png");
    }

    public ResourceLocation getAnimationResource(WrathEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "animations/entity/spells/rancor_skull.animation.json");
    }
}
