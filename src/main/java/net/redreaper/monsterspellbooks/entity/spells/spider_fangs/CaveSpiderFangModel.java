package net.redreaper.monsterspellbooks.entity.spells.spider_fangs;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.entity.spells.poison_quill.PoisonQuillProjectile;
import software.bernie.geckolib.model.GeoModel;

public class CaveSpiderFangModel extends GeoModel<CaveSpiderFangProjectile> {
    public ResourceLocation getModelResource(CaveSpiderFangProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/entity/spells/spider_fang.geo.json");
    }

    public ResourceLocation getTextureResource(CaveSpiderFangProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/entity/spider_fangs/spider_fang.png");
    }

    public ResourceLocation getAnimationResource(CaveSpiderFangProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "animations/entity/spells/spider_fang.animation.json");
    }
}