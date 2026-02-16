package net.redreaper.monsterspellbooks.entity.model.JungleWhisperer;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.JungleWhispererEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class JungleWhispererModel extends DefaultedEntityGeoModel<JungleWhispererEntity> {
    public JungleWhispererModel() {
        super(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "jungle_whisperer"));
    }

    @Override
    public ResourceLocation getModelResource(JungleWhispererEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/entity/jungle_whisperer.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(JungleWhispererEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/living/jungle_whisperer/jungle_whisperer.png");
    }

    @Override
    public ResourceLocation getAnimationResource(JungleWhispererEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "animations/entity/jungle_whisperer.animation.json");
    }

    @Override
    public void setCustomAnimations(JungleWhispererEntity animatable, long instanceId, AnimationState<JungleWhispererEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
    }
}