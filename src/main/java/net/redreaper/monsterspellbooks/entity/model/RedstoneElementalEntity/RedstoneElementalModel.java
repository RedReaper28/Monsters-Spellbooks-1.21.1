package net.redreaper.monsterspellbooks.entity.model.RedstoneElementalEntity;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.RedstoneElementalEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class RedstoneElementalModel extends DefaultedEntityGeoModel<RedstoneElementalEntity> {
    public RedstoneElementalModel() {
        super(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "redstone_elemental"));
    }

    @Override
    public ResourceLocation getModelResource(RedstoneElementalEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/entity/redstone_elemental.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RedstoneElementalEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/living/redstone_elemental/redstone_elemental.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RedstoneElementalEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "animations/entity/redstone_elemental.animation.json");
    }

    @Override
    public void setCustomAnimations(RedstoneElementalEntity animatable, long instanceId, AnimationState<RedstoneElementalEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
    }
}
