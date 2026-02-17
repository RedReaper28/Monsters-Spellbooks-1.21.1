package net.redreaper.monsterspellbooks.entity.model.PoisonQuillVine;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.PoisonQuillVineEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class PoisonQuillVineModel extends DefaultedEntityGeoModel<PoisonQuillVineEntity> {
    public PoisonQuillVineModel() {
        super(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "poison_quill_vine"));
    }

    @Override
    public ResourceLocation getModelResource(PoisonQuillVineEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/entity/poison_quill_vine.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PoisonQuillVineEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/living/poison_quill_vine/poison_quill_vine.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PoisonQuillVineEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "animations/entity/poison_quill_vine.animation.json");
    }

    @Override
    public void setCustomAnimations(PoisonQuillVineEntity animatable, long instanceId, AnimationState<PoisonQuillVineEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
    }
}
