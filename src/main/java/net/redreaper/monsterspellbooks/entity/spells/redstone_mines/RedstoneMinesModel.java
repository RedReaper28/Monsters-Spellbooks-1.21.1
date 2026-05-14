package net.redreaper.monsterspellbooks.entity.spells.redstone_mines;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class RedstoneMinesModel extends GeoModel<RedstoneMinesProjectile> {
    public ResourceLocation getModelResource(RedstoneMinesProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/entity/spells/redstone_mine.geo.json");
    }

    public ResourceLocation getTextureResource(RedstoneMinesProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/entity/redstone_mines/redstone_mines.png");
    }

    public ResourceLocation getAnimationResource(RedstoneMinesProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "animations/entity/spells/redstone_mine.animation.json");
    }
}