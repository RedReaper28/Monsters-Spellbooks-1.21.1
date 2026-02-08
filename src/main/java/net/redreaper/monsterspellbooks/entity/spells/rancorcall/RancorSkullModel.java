package net.redreaper.monsterspellbooks.entity.spells.rancorcall;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class RancorSkullModel extends GeoModel<RancorSkull> {
    public ResourceLocation getModelResource(RancorSkull animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/entity/spells/rancor_skull.geo.json");
    }

    public ResourceLocation getTextureResource(RancorSkull animatable) {
        int frameCount = 4;
        int frameDuration = 2;
        int frame = animatable.tickCount / frameDuration % frameCount;
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/entity/rancorcall/rancor_skull_" + frame + ".png");
    }

    public ResourceLocation getAnimationResource(RancorSkull animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "animations/entity/spells/rancor_skull_.animation.json");
    }
}