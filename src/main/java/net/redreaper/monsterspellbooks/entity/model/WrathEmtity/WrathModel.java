package net.redreaper.monsterspellbooks.entity.model.WrathEmtity;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.entity.living.summons.WrathEntity;
import software.bernie.geckolib.model.GeoModel;

public class WrathModel extends GeoModel<WrathEntity> {
    public static final ResourceLocation modelResource = ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "geo/wisp.geo.json");
    public static final ResourceLocation textureResource = ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "textures/entity/wisp/wisp.png");
    public static final ResourceLocation animationResource = ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "animations/wisp.animation.json");


    @Override
    public ResourceLocation getModelResource(WrathEntity object) {
        return modelResource;
    }

    @Override
    public ResourceLocation getTextureResource(WrathEntity object) {
        return textureResource;
    }

    @Override
    public ResourceLocation getAnimationResource(WrathEntity animatable) {
        return animationResource;
    }
}
