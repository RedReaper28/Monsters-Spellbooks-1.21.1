package net.redreaper.monsterspellbooks.entity.model.SoulWraithEntity;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.SoulWraithEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class SoulWraithModel extends DefaultedEntityGeoModel<SoulWraithEntity> {
    public SoulWraithModel() {
        super(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "soul_wraith"));
    }

    @Override
    public ResourceLocation getModelResource(SoulWraithEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/entity/soul_wraith.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SoulWraithEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/living/soul_wraith/soul_wraith.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SoulWraithEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "animations/entity/soul_wraith.animation.json");
    }

    @Override
    public void setCustomAnimations(SoulWraithEntity animatable, long instanceId, AnimationState<SoulWraithEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
    }
}