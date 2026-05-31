package net.redreaper.monsterspellbooks.entity.model.RancorPhantomEntity;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.entity.living.summons.RancorPhantomEntity;
import net.redreaper.monsterspellbooks.entity.living.summons.WrathEntity;
import net.redreaper.monsterspellbooks.entity.spells.rancorcall.RancorSkull;
import software.bernie.geckolib.model.GeoModel;

public class RancorPhantomModel extends GeoModel<RancorPhantomEntity> {
    public ResourceLocation getModelResource(RancorPhantomEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/entity/spells/rancor_skull.geo.json");
    }

    public ResourceLocation getTextureResource(RancorPhantomEntity animatable) {
        int frameCount = 4;
        int frameDuration = 2;
        int frame = animatable.tickCount / frameDuration % frameCount;
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/entity/rancorcall/rancor_skull_" + frame + ".png");
    }

    public ResourceLocation getAnimationResource(RancorPhantomEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "animations/entity/spells/rancor_skull.animation.json");
    }
}
