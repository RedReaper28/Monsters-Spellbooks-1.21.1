package net.redreaper.monsterspellbooks.entity.spells.brimstone_buzzsaw;

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
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import net.minecraft.util.Mth;
import java.util.Random;

public class BrimstoneBuzzsawRenderer extends EntityRenderer<BrimstoneBuzzsawProjectile> {
    private static final ResourceLocation[] TEXTURES = {
            MonstersSpellbooks.id("textures/entity/brimstone_buzzsaw/brimstone_buzzsaw_1.png"),
            MonstersSpellbooks.id("textures/entity/brimstone_buzzsaw/brimstone_buzzsaw_2.png"),
            MonstersSpellbooks.id("textures/entity/brimstone_buzzsaw/brimstone_buzzsaw_3.png"),
            MonstersSpellbooks.id("textures/entity/brimstone_buzzsaw/brimstone_buzzsaw_4.png")
    };
    public BrimstoneBuzzsawRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(BrimstoneBuzzsawProjectile pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();

        PoseStack.Pose pose = pPoseStack.last();

        pPoseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(pPartialTick, pEntity.yRotO, pEntity.getYRot())));
        pPoseStack.mulPose(Axis.XP.rotationDegrees(-Mth.lerp(pPartialTick, pEntity.xRotO, pEntity.getXRot())));
        float randomZ = new Random(31L * pEntity.getId()).nextInt(-5, 5);
        pPoseStack.mulPose(Axis.XP.rotationDegrees(randomZ));


        drawSlash(pose, pEntity, pBuffer, pEntity.getBbWidth() * 1.5F);

        pPoseStack.popPose();

        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    private void drawSlash(PoseStack.Pose pose, BrimstoneBuzzsawProjectile entity, MultiBufferSource bufferSource, float width)
    {
        Matrix4f poseMatrix = pose.pose();
        Matrix3f normalMatrix = pose.normal();

        VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(entity)));

        float halfWidth = width * 0.5F;
        float height = entity.getBbHeight() * 0.5F;

        consumer.addVertex(poseMatrix, -halfWidth, height, -halfWidth).setColor(255, 255, 255, 255).setUv(0F, 1F).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(0F, 1F, 0F);
        consumer.addVertex(poseMatrix, halfWidth, height, -halfWidth).setColor(255, 255, 255, 255).setUv(1F, 1F).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(0F, 1F, 0F);
        consumer.addVertex(poseMatrix, halfWidth, height, halfWidth).setColor(255, 255, 255, 255).setUv(1F, 0F).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(0F, 1F, 0F);
        consumer.addVertex(poseMatrix, -halfWidth, height, halfWidth).setColor(255, 255, 255, 255).setUv(0F, 0F).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(0F, 1F, 0F);
    }

    @Override
    public ResourceLocation getTextureLocation(BrimstoneBuzzsawProjectile entity) {
        int frame = (entity.tickCount / 2) % TEXTURES.length;
        return TEXTURES[frame];
    }
}