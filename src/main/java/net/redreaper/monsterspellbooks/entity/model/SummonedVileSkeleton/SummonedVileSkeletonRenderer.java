package net.redreaper.monsterspellbooks.entity.model.SummonedVileSkeleton;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.entity.living.SummonedVileSkeletonEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SummonedVileSkeletonRenderer extends GeoEntityRenderer<SummonedVileSkeletonEntity> {
    public SummonedVileSkeletonRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SummonedVileSkeletonModel());
        this.shadowRadius = 0.5F;
    }

    public RenderType getRenderType(SummonedVileSkeletonEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(this.getTextureLocation(animatable));
    }

    public void preRender(PoseStack poseStack, SummonedVileSkeletonEntity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int color) {
        float scale = 1.0F;
        this.scaleHeight = scale;
        this.scaleWidth = scale;
        super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, color);
    }

    protected float getDeathMaxRotation(SummonedVileSkeletonEntity entityLivingBaseIn) {
        return 0.0F;
    }
}
