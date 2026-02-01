package net.redreaper.monsterspellbooks.entity.spells.ancient_lightning_lance;

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
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class AncientLightningLanceRenderer extends EntityRenderer<AncientLightningLanceProjectile> {
    private static final ResourceLocation TEXTURE = MonstersSpellbooks.id("textures/entity/ancient_lightning_lance/ancient_lightning_lance.png");

    public AncientLightningLanceRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(AncientLightningLanceProjectile entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int light) {
        poseStack.pushPose();
        Vec3 motion = entity.getDeltaMovement();
        float xRot = -((float) (Mth.atan2(motion.horizontalDistance(), motion.y) * (double) (180F / (float) Math.PI)) - 90.0F);
        float yRot = -((float) (Mth.atan2(motion.z, motion.x) * (double) (180F / (float) Math.PI)) + 90.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(yRot));
        poseStack.mulPose(Axis.XP.rotationDegrees(xRot));
        renderModel(poseStack, bufferSource, entity.getAge());
        poseStack.popPose();

        super.render(entity, yaw, partialTicks, poseStack, bufferSource, light);
    }

    public static void renderModel(PoseStack poseStack, MultiBufferSource bufferSource, int animOffset) {
        PoseStack.Pose pose = poseStack.last();
        Matrix4f poseMatrix = pose.pose();
        Matrix3f normalMatrix = pose.normal();

        VertexConsumer consumer = bufferSource.getBuffer(RenderType.energySwirl(TEXTURE, 0, 0));
        int framecout = 7;
        int anim = animOffset % framecout;
        float uvMin = anim / (float) framecout;
        float uvMax = (anim + 1) / (float) framecout;

        float halfWidth = 2;
        float halfHeight = 1;
        float angleCorrection = 50;
        float texturefix = -.2f;
        //Vertical plane
        poseStack.mulPose(Axis.XP.rotationDegrees(angleCorrection));
        consumer.addVertex(poseMatrix, 0, -halfWidth, -halfHeight + texturefix).setColor(255, 255, 255, 255).setUv(uvMin, 1f).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(0f, 1f, 0f);
        consumer.addVertex(poseMatrix, 0, halfWidth, -halfHeight + texturefix).setColor(255, 255, 255, 255).setUv(uvMin, 0f).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(0f, 1f, 0f);
        consumer.addVertex(poseMatrix, 0, halfWidth, halfHeight + texturefix).setColor(255, 255, 255, 255).setUv(uvMax, 0f).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(0f, 1f, 0f);
        consumer.addVertex(poseMatrix, 0, -halfWidth, halfHeight + texturefix).setColor(255, 255, 255, 255).setUv(uvMax, 1f).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(0f, 1f, 0f);
        poseStack.mulPose(Axis.XP.rotationDegrees(-angleCorrection));

        poseStack.mulPose(Axis.YP.rotationDegrees(-angleCorrection));
        consumer.addVertex(poseMatrix, -halfWidth - texturefix, 0, -halfHeight).setColor(255, 255, 255, 255).setUv(uvMin, 1f).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(0f, 1f, 0f);
        consumer.addVertex(poseMatrix, halfWidth - texturefix, 0, -halfHeight).setColor(255, 255, 255, 255).setUv(uvMin, 0f).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(0f, 1f, 0f);
        consumer.addVertex(poseMatrix, halfWidth - texturefix, 0, halfHeight).setColor(255, 255, 255, 255).setUv(uvMax, 0f).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(0f, 1f, 0f);
        consumer.addVertex(poseMatrix, -halfWidth - texturefix, 0, halfHeight).setColor(255, 255, 255, 255).setUv(uvMax, 1f).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(0f, 1f, 0f);
        poseStack.mulPose(Axis.YP.rotationDegrees(angleCorrection));
    }

    @Override
    public ResourceLocation getTextureLocation(AncientLightningLanceProjectile entity) {
        return TEXTURE;
    }
}
