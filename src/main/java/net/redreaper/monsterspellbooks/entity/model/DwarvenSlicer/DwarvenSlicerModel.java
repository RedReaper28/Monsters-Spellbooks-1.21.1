package net.redreaper.monsterspellbooks.entity.model.DwarvenSlicer;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.DwarvenSlicerEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class DwarvenSlicerModel extends GeoModel<DwarvenSlicerEntity> {
    public ResourceLocation getAnimationResource(DwarvenSlicerEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "animations/entity/dwarven_slicer.animation.json");
    }

    public ResourceLocation getModelResource(DwarvenSlicerEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/entity/dwarven_slicer.geo.json");
    }

    public ResourceLocation getTextureResource(DwarvenSlicerEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/dwarven_slicer.png");
    }

    public void setCustomAnimations(DwarvenSlicerEntity animatable, long instanceId, AnimationState animationState) {
        GeoBone head = this.getAnimationProcessor().getBone("head");
        if (head != null) {
            EntityModelData entityData = (EntityModelData)animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * ((float)Math.PI / 180F));
            head.setRotY(entityData.netHeadYaw() * ((float)Math.PI / 180F));
        }

    }
}
