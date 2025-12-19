package net.redreaper.monsterspellbooks.entity.model.ShockEntity;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.ShockEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class ShockEntityModel extends DefaultedEntityGeoModel<ShockEntity> {
    public ShockEntityModel() {
        super(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "shock"));
    }

    @Override
    public ResourceLocation getModelResource(ShockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/entity/granite_elemental.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ShockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/granite_elemental.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ShockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "animations/entity/shock.animation.json");
    }

    @Override
    public void setCustomAnimations(ShockEntity animatable, long instanceId, AnimationState<ShockEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
    }
}
