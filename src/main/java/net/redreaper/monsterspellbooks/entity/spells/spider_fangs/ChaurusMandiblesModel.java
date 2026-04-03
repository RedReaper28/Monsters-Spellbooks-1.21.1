package net.redreaper.monsterspellbooks.entity.spells.spider_fangs;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ChaurusMandiblesModel extends GeoModel<ChaurusMandiblesProjectile> {
    public ResourceLocation getModelResource(ChaurusMandiblesProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/entity/spells/chaurus_mandibles.geo.json");
    }

    public ResourceLocation getTextureResource(ChaurusMandiblesProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/entity/spider_fangs/chaurus_mandibles.png");
    }

    public ResourceLocation getAnimationResource(ChaurusMandiblesProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "animations/entity/spells/chaurus_mandibles.animation.json");
    }
}