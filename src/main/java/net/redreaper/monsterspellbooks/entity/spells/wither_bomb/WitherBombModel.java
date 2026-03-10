package net.redreaper.monsterspellbooks.entity.spells.wither_bomb;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WitherBombModel extends GeoModel<WitherBombProjectile> {
    public ResourceLocation getModelResource(WitherBombProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/entity/spells/wither_bomb.geo.json");
    }

    public ResourceLocation getTextureResource(WitherBombProjectile animatable) {
        int frameCount = 4;
        int frameDuration = 2;
        int frame = animatable.tickCount / frameDuration % frameCount;
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/entity/wither_bomb/wither_bomb_" + frame + ".png");
    }

    public ResourceLocation getAnimationResource(WitherBombProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "animations/entity/spells/rancor_skull_.animation.json");
    }
}