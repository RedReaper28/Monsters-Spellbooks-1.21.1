package net.redreaper.monsterspellbooks.entity.spells.spider_fangs;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class IceSpiderFangModel extends GeoModel<IceSpiderFangProjectile> {
    public ResourceLocation getModelResource(IceSpiderFangProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/entity/spells/ice_spider_fang.geo.json");
    }

    public ResourceLocation getTextureResource(IceSpiderFangProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/entity/spider_fangs/ice_spider_fang.png");
    }

    public ResourceLocation getAnimationResource(IceSpiderFangProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "animations/entity/spells/spider_fang.animation.json");
    }
}