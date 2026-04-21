package net.redreaper.monsterspellbooks.entity.model.IceSerpeant;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.summons.IceSerpeantEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class IceSerpeantModel extends DefaultedEntityGeoModel<IceSerpeantEntity> {
    public IceSerpeantModel() {
        super(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "ice_serpent"));
    }

    @Override
    public ResourceLocation getModelResource(IceSerpeantEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/entity/ice_serpent.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(IceSerpeantEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/living/ice_serpent/ice_serpent.png");
    }

    @Override
    public ResourceLocation getAnimationResource(IceSerpeantEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "animations/entity/ice_serpent.animation.json");
    }

    @Override
    public void setCustomAnimations(IceSerpeantEntity animatable, long instanceId, AnimationState<IceSerpeantEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
    }
}
