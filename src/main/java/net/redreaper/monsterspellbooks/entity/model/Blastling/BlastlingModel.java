package net.redreaper.monsterspellbooks.entity.model.Blastling;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.BlastlingEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class BlastlingModel extends DefaultedEntityGeoModel<BlastlingEntity> {
    public BlastlingModel() {
        super(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "blastling"));
    }

    @Override
    public ResourceLocation getModelResource(BlastlingEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/entity/blastling.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BlastlingEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/living/blastling/blastling.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BlastlingEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "animations/entity/blastling.animation.json");
    }

    @Override
    public void setCustomAnimations(BlastlingEntity animatable, long instanceId, AnimationState<BlastlingEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
    }
}