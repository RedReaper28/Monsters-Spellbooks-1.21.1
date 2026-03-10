package net.redreaper.monsterspellbooks.entity.model.MagmaAtronach;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.MagmaAtronachEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class MagmaAtronachModel extends DefaultedEntityGeoModel<MagmaAtronachEntity> {
    public MagmaAtronachModel() {
        super(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "magma_atronach"));
    }

    @Override
    public ResourceLocation getModelResource(MagmaAtronachEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/entity/magma_atronach.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MagmaAtronachEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/living/magma_atronach/magma_atronach.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MagmaAtronachEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "animations/entity/magma_atronach.animation.json");
    }

    @Override
    public void setCustomAnimations(MagmaAtronachEntity animatable, long instanceId, AnimationState<MagmaAtronachEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
    }
}