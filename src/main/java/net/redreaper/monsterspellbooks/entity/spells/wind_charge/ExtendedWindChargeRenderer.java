package net.redreaper.monsterspellbooks.entity.spells.wind_charge;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ExtendedWindChargeRenderer extends EntityRenderer<ExtendedWindCharge> {
    private static final float MIN_CAMERA_DISTANCE_SQUARED = Mth.square(3.5F);
    private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/projectiles/wind_charge.png");

    private final ExtendedWindChargeModel model;
    public ExtendedWindChargeRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new ExtendedWindChargeModel(context.bakeLayer(ModelLayers.WIND_CHARGE));
    }

    @Override
    public void render(ExtendedWindCharge entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if (entity.tickCount >= 2 || !(this.entityRenderDispatcher.camera.getEntity().distanceToSqr(entity) < (double)MIN_CAMERA_DISTANCE_SQUARED)) {
            float f = (float)entity.tickCount + partialTick;
            VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderType.breezeWind(TEXTURE_LOCATION, this.xOffset(entity, f) % 1.0F, 0.0F));
            this.model.setupAnim(entity, 0.0F, 0.0F, f, 0.0F, 0.0F);
            this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
            super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        }
    }

    protected float xOffset(ExtendedWindCharge entity, float tickCount) {
        return tickCount * 0.03F * entity.getDamage() * 1.2f;
    }

    @Override
    public ResourceLocation getTextureLocation(ExtendedWindCharge magicWindCharge) {
        return TEXTURE_LOCATION;
    }
}