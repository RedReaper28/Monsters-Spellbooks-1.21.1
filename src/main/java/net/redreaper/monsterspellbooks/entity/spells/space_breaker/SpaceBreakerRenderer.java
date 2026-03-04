package net.redreaper.monsterspellbooks.entity.spells.space_breaker;

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

public class SpaceBreakerRenderer extends EntityRenderer<SpaceBreaker> {
    private static final ResourceLocation[] TEXTURES = {
            MonstersSpellbooks.id("textures/entity/space_breaker/space_breaker_1.png"),
            MonstersSpellbooks.id("textures/entity/space_breaker/space_breaker_2.png"),
            MonstersSpellbooks.id("textures/entity/space_breaker/space_breaker_3.png"),
            MonstersSpellbooks.id("textures/entity/space_breaker/space_breaker_4.png"),
            MonstersSpellbooks.id("textures/entity/space_breaker/space_breaker_5.png")
    };
    public SpaceBreakerRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(SpaceBreaker spaceBreaker, float entityYaw, float partialTick, PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();

        PoseStack.Pose pose = poseStack.last();
        poseStack.mulPose(Axis.YP.rotationDegrees(180 - spaceBreaker.getYRot()));
        poseStack.mulPose(Axis.ZP.rotationDegrees(180-spaceBreaker.getXRot()));
        float randomZ = new Random(31L * spaceBreaker.getId()).nextInt(-5, 5);
        poseStack.mulPose(Axis.XP.rotationDegrees(-90-randomZ));

        drawSlash(pose, spaceBreaker, bufferSource, spaceBreaker.getBbWidth() * 1.5F);

        poseStack.popPose();

        super.render(spaceBreaker, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    private void drawSlash(PoseStack.Pose pose, SpaceBreaker entity, MultiBufferSource bufferSource, float width)
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
    public @NotNull ResourceLocation getTextureLocation(SpaceBreaker spaceBreaker) {
        int frame = (spaceBreaker.tickCount / spaceBreaker.ticksPerFrame) % TEXTURES.length;
        return TEXTURES[frame];
    }
}