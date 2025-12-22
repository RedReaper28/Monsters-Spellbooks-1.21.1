package net.redreaper.monsterspellbooks.entity.model.DeathKnight;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.DeathKnightEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class DeathKnightModel extends DefaultedEntityGeoModel<DeathKnightEntity> {
    public DeathKnightModel() {
        super(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "death_knight"));
    }

    @Override
    public ResourceLocation getModelResource(DeathKnightEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/entity/death_knight.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DeathKnightEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/death_knight.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DeathKnightEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "animations/entity/death_knight.animation.json");
    }

    @Override
    public void setCustomAnimations(DeathKnightEntity animatable, long instanceId, AnimationState<DeathKnightEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
    }
}
