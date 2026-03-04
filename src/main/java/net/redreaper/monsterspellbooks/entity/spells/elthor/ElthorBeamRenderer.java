package net.redreaper.monsterspellbooks.entity.spells.elthor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.render.RenderHelper;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.redreaper.monsterspellbooks.render.ModSpellRenderingHelper;

public class ElthorBeamRenderer extends EntityRenderer<ElthorBeamEntity> {

    public ElthorBeamRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public boolean shouldRender(ElthorBeamEntity pLivingEntity, Frustum pCamera, double pCamX, double pCamY, double pCamZ) {
        return true;
    }

    @Override
    public void render(ElthorBeamEntity entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int light) {
        poseStack.pushPose();

        float maxRadius = 5;
        float minRadius = 1;
        float deltaTicks = entity.tickCount + partialTicks;
        float deltaUV = -deltaTicks % 10;
        float max = Mth.frac(deltaUV * 0.2F - (float) Mth.floor(deltaUV * 0.1F));
        float min = -1.0F + max;
        float f = deltaTicks / ElthorBeamEntity.WARMUP_TIME;
        f *= f;
        float radius = Mth.clampedLerp(maxRadius, minRadius, f);
        VertexConsumer inner = bufferSource.getBuffer(RenderHelper.CustomerRenderType.magic(ModSpellRenderingHelper.ELTHOR_BEAM));
        float halfRadius = radius * .5f;
        float quarterRadius = halfRadius * .5f;
        float yMin = entity.onGround() ? 0 : Utils.findRelativeGroundLevel(entity.level(), entity.position(), 8) - (float) entity.getY();
        for (int i = 0; i < 4; i++) {
            //orange glow
            RenderHelper.quadBuilder()
                    .vertex(-halfRadius, yMin, -halfRadius).uv(0, min).normal(0, 1, 0)
                    .vertex(-halfRadius, yMin, halfRadius).uv(1, min).normal(0, 1, 0)
                    .vertex(-halfRadius, 250, halfRadius).uv(1, max).normal(0, 1, 0)
                    .vertex(-halfRadius, 250, -halfRadius).uv(0, max).normal(0, 1, 0)
                    .color(1, 1, 1, .3f)
                    .light(LightTexture.FULL_BRIGHT)
                    .overlay(OverlayTexture.NO_OVERLAY)
                    .matrix(poseStack.last().pose())
                    .build(inner);
            //yellow core
            RenderHelper.quadBuilder()
                    .vertex(-quarterRadius, yMin, -quarterRadius).uv(0, min).normal(0, 1, 0)
                    .vertex(-quarterRadius, yMin, quarterRadius).uv(1, min).normal(0, 1, 0)
                    .vertex(-quarterRadius, 250, quarterRadius).uv(1, max).normal(0, 1, 0)
                    .vertex(-quarterRadius, 250, -quarterRadius).uv(0, max).normal(0, 1, 0)
                    .color(Mth.clamp(.8f * f, 0, 1), Mth.clamp(.8f * f * f, 0, 1), Mth.clamp(.5f * f * f, 0, 1))
                    .light(LightTexture.FULL_BRIGHT)
                    .overlay(OverlayTexture.NO_OVERLAY)
                    .matrix(poseStack.last().pose())
                    .build(inner);
            poseStack.mulPose(Axis.YP.rotationDegrees(90));
        }

        poseStack.popPose();

        super.render(entity, yaw, partialTicks, poseStack, bufferSource, light);
    }

    @Override
    public ResourceLocation getTextureLocation(ElthorBeamEntity entity) {
        return ModSpellRenderingHelper.ELTHOR_BEAM;
    }
}
