package net.redreaper.monsterspellbooks.entity.model.SummonedVileSkeleton;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.SummonedVileSkeleton;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class SummonedVileSkeletonModel extends GeoModel<SummonedVileSkeleton> {
    public ResourceLocation getAnimationResource(SummonedVileSkeleton entity) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "animations/entity/vile_skeleton.animation.json");
    }

    public ResourceLocation getModelResource(SummonedVileSkeleton entity) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/entity/vile_skeleton.geo.json");
    }

    public ResourceLocation getTextureResource(SummonedVileSkeleton entity) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/vile_skeleton.png");
    }

    public void setCustomAnimations(SummonedVileSkeleton animatable, long instanceId, AnimationState animationState) {
        GeoBone head = this.getAnimationProcessor().getBone("head");
        if (head != null) {
            EntityModelData entityData = (EntityModelData)animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * ((float)Math.PI / 180F));
            head.setRotY(entityData.netHeadYaw() * ((float)Math.PI / 180F));
        }

    }
}
