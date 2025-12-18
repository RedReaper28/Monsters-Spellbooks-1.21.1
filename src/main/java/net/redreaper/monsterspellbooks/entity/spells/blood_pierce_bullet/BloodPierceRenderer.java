package net.redreaper.monsterspellbooks.entity.spells.blood_pierce_bullet;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import io.redspace.ironsspellbooks.render.RenderHelper;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;

public class BloodPierceRenderer extends EntityRenderer<BloodPierceVisualEntity> {

    public static final ModelLayerLocation MODEL_LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "blood_pierce_model"), "main");
    private static final ResourceLocation TEXTURE_CORE = MonstersSpellbooks.id("textures/entity/blood_pierce/core.png");
    private static final ResourceLocation TEXTURE_OVERLAY = MonstersSpellbooks.id("textures/entity/blood_pierce/overlay.png");

    private final ModelPart body;

    public BloodPierceRenderer(EntityRendererProvider.Context context) {
        super(context);
        ModelPart modelpart = context.bakeLayer(MODEL_LAYER_LOCATION);
        this.body = modelpart.getChild("body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-8, -16, -8, 16, 32, 16), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public boolean shouldRender(BloodPierceVisualEntity pLivingEntity, Frustum pCamera, double pCamX, double pCamY, double pCamZ) {
        return true;
    }

    @Override
    public void render(BloodPierceVisualEntity entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int light) {
        poseStack.pushPose();
        float lifetime = BloodPierceVisualEntity.lifetime;
        float scalar = .25f;
        float length = 32 * scalar * scalar;
        float f = entity.tickCount + partialTicks;
        poseStack.translate(0, entity.getBoundingBox().getYsize() * .5f, 0);
        poseStack.mulPose(Axis.YP.rotationDegrees(-entity.getYRot() - 180.0F));
        poseStack.mulPose(Axis.XP.rotationDegrees(-entity.getXRot() - 90));
        poseStack.scale(scalar, scalar, scalar);

        float alpha = Mth.clamp(1f - f / lifetime, 0, 1);

        for (float i = 0; i < entity.distance * 4; i += length) {
            poseStack.translate(0, length, 0);
            VertexConsumer consumer = bufferSource.getBuffer(RenderHelper.CustomerRenderType.magicNoCull(TEXTURE_OVERLAY));
            {
                poseStack.pushPose();
                float expansion = Mth.clampedLerp(1.2f, 0, f / (lifetime));
                poseStack.mulPose(Axis.YP.rotationDegrees(f * 5));
                poseStack.scale(expansion, 1, expansion);
                poseStack.mulPose(Axis.YP.rotationDegrees(45));
                this.body.render(poseStack, consumer, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, FastColor.ARGB32.color((int) (alpha * 255),255,255,255));
                poseStack.popPose();
            }
            consumer = bufferSource.getBuffer(RenderHelper.CustomerRenderType.darkGlow(TEXTURE_CORE));
            {
                poseStack.pushPose();
                float expansion = Mth.clampedLerp(1, 0, f / (lifetime - 5));
                poseStack.scale(expansion, 1, expansion);
                poseStack.mulPose(Axis.YP.rotationDegrees(f * -10));
                this.body.render(poseStack, consumer, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, -1);
                poseStack.popPose();
            }
        }
        poseStack.popPose();

        super.render(entity, yaw, partialTicks, poseStack, bufferSource, light);
    }

    @Override
    public ResourceLocation getTextureLocation(BloodPierceVisualEntity entity) {
        return TEXTURE_CORE;
    }
}

