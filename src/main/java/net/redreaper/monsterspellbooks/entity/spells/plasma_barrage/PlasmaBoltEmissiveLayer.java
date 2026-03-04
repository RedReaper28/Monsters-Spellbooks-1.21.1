package net.redreaper.monsterspellbooks.entity.spells.plasma_barrage;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class PlasmaBoltEmissiveLayer extends GeoRenderLayer<PlasmaBolt> {
    public PlasmaBoltEmissiveLayer(GeoEntityRenderer<PlasmaBolt> renderer) {
        super(renderer);
    }

    private static RenderType glowRenderType(ResourceLocation texture) {
        return RenderType.entityCutout(texture);
    }

    public void render(PoseStack poseStack, PlasmaBolt animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        if (!animatable.isInvisible()) {
            int frameCount = 4;
            int frame = animatable.tickCount % frameCount;
            ResourceLocation frameTexture = ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/entity/plasma_bolt/plasma_bolt.png");
            RenderType emissiveType = glowRenderType(frameTexture);
            VertexConsumer vertexConsumer = bufferSource.getBuffer(emissiveType);
            poseStack.pushPose();
            this.getRenderer().actuallyRender(poseStack, animatable, bakedModel, emissiveType, bufferSource, vertexConsumer, true, partialTick, 15728880, OverlayTexture.NO_OVERLAY, -1);
            poseStack.popPose();
        }
    }
}