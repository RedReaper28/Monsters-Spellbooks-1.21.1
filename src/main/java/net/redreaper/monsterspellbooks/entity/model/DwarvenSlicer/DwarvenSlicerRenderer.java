package net.redreaper.monsterspellbooks.entity.model.DwarvenSlicer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.entity.living.DwarvenSlicerEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DwarvenSlicerRenderer extends GeoEntityRenderer<DwarvenSlicerEntity> {
    public DwarvenSlicerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DwarvenSlicerModel());
        this.shadowRadius = 0.5F;
    }

    public RenderType getRenderType(DwarvenSlicerEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(this.getTextureLocation(animatable));
    }

    public void preRender(PoseStack poseStack, DwarvenSlicerEntity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int color) {
        float scale = 1.0F;
        this.scaleHeight = scale;
        this.scaleWidth = scale;
        super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, color);
    }

    protected float getDeathMaxRotation(DwarvenSlicerEntity entityLivingBaseIn) {
        return 0.0F;
    }
}
