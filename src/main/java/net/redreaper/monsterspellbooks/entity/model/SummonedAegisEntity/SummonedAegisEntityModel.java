package net.redreaper.monsterspellbooks.entity.model.SummonedAegisEntity;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.SummonedAegisEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class SummonedAegisEntityModel extends DefaultedEntityGeoModel<SummonedAegisEntity> {
    public SummonedAegisEntityModel() {
        super(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "aegis"));
    }

    @Override
    public ResourceLocation getModelResource(SummonedAegisEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/entity/aegis.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SummonedAegisEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/aegis.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SummonedAegisEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "animations/entity/aegis.animation.json");
    }

    @Override
    public void setCustomAnimations(SummonedAegisEntity animatable, long instanceId, AnimationState<SummonedAegisEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
    }
}
