package net.redreaper.monsterspellbooks.entity.model.JungleLeapLeaf;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.JungleLeapleafEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class JungleLeapLeafModel extends DefaultedEntityGeoModel<JungleLeapleafEntity> {
    public JungleLeapLeafModel() {
        super(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "jungle_leapleaf"));
    }

    @Override
    public ResourceLocation getModelResource(JungleLeapleafEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/entity/jungle_leapleaf.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(JungleLeapleafEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/living/jungle_leapleaf/jungle_leapleaf.png");
    }

    @Override
    public ResourceLocation getAnimationResource(JungleLeapleafEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "animations/entity/leapleaf.animation.json");
    }

    @Override
    public void setCustomAnimations(JungleLeapleafEntity animatable, long instanceId, AnimationState<JungleLeapleafEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
    }
}
