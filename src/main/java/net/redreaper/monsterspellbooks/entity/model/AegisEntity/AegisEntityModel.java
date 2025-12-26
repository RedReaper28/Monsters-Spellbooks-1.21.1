package net.redreaper.monsterspellbooks.entity.model.AegisEntity;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.AegisEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class AegisEntityModel extends DefaultedEntityGeoModel<AegisEntity> {
    public AegisEntityModel() {
        super(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "aegis"));
    }

    @Override
    public ResourceLocation getModelResource(AegisEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/entity/aegis.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AegisEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/aegis.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AegisEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "animations/entity/aegis.animation.json");
    }

    @Override
    public void setCustomAnimations(AegisEntity animatable, long instanceId, AnimationState<AegisEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
    }
}
