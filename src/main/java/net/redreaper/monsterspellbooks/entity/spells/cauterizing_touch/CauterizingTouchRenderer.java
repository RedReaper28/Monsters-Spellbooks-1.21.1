package net.redreaper.monsterspellbooks.entity.spells.cauterizing_touch;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import java.util.Random;

public class CauterizingTouchRenderer extends EntityRenderer<CauterizingTouch> {
    private static final ResourceLocation TEXTURE = MonstersSpellbooks.id("textures/entity/cauterizing_touch/cauterizing_touch.png");

    public CauterizingTouchRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(CauterizingTouch esotericEdge, float entityYaw, float partialTick, PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();

        PoseStack.Pose pose = poseStack.last();
        poseStack.mulPose(Axis.YP.rotationDegrees(180 - esotericEdge.getYRot()));
        poseStack.mulPose(Axis.ZP.rotationDegrees(180-esotericEdge.getXRot()));
        float randomZ = new Random(31L * esotericEdge.getId()).nextInt(-5, 5);
        poseStack.mulPose(Axis.XP.rotationDegrees(-90-randomZ));

        drawSlash(pose, esotericEdge, bufferSource, esotericEdge.getBbWidth() * 1.5F);

        poseStack.popPose();

        super.render(esotericEdge, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    private void drawSlash(PoseStack.Pose pose, CauterizingTouch entity, MultiBufferSource bufferSource, float width)
    {
        Matrix4f poseMatrix = pose.pose();
        Matrix3f normalMatrix = pose.normal();

        VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(entity)));
        float halfWidth = width * 0.5F;
        float height = entity.getBbHeight() * 0.5F;

        consumer.addVertex(poseMatrix, -halfWidth, height, -halfWidth).setColor(255, 255, 255, 255).setUv(0f,1f).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(0f, 1f, 0f);
        consumer.addVertex(poseMatrix, halfWidth, height, -halfWidth).setColor(255, 255, 255, 255).setUv(1f,1f).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(0f, 1f, 0f);
        consumer.addVertex(poseMatrix, halfWidth, height, halfWidth).setColor(255, 255, 255, 255).setUv(1f,0f).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(0f, 1f, 0f);
        consumer.addVertex(poseMatrix, -halfWidth, height, halfWidth).setColor(255, 255, 255, 255).setUv(0f,0f).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(0f, 1f, 0f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull CauterizingTouch esotericStrike) {return TEXTURE;}
}