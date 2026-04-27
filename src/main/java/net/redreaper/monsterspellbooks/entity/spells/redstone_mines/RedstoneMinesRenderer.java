package net.redreaper.monsterspellbooks.entity.spells.redstone_mines;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
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

public class RedstoneMinesRenderer extends EntityRenderer<RedstoneMinesProjectile> {
    public static final ModelLayerLocation MODEL_LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "ball_lightning_model"), "main");
    private static ResourceLocation TEXTURE = MonstersSpellbooks.id("textures/entity/redstone_mines/redstone_mines.png");
    private static ResourceLocation SWIRL_TEXTURES[] = {
            MonstersSpellbooks.id("textures/entity/redstone_mines/swirl_0.png"),
            MonstersSpellbooks.id("textures/entity/redstone_mines/swirl_1.png"),
            MonstersSpellbooks.id("textures/entity/redstone_mines/swirl_2.png"),
            MonstersSpellbooks.id("textures/entity/redstone_mines/swirl_3.png"),
            MonstersSpellbooks.id("textures/entity/redstone_mines/swirl_4.png"),
            MonstersSpellbooks.id("textures/entity/redstone_mines/swirl_5.png"),
            MonstersSpellbooks.id("textures/entity/redstone_mines/swirl_6.png"),
            MonstersSpellbooks.id("textures/entity/redstone_mines/swirl_7.png"),
            MonstersSpellbooks.id("textures/entity/redstone_mines/swirl_8.png"),
            MonstersSpellbooks.id("textures/entity/redstone_mines/swirl_9.png"),
            MonstersSpellbooks.id("textures/entity/redstone_mines/swirl_10.png")
    };
    private final ModelPart orb;
    private final ModelPart swirl;

    public RedstoneMinesRenderer(EntityRendererProvider.Context context) {
        super(context);
        ModelPart modelpart = context.bakeLayer(MODEL_LAYER_LOCATION);
        this.orb = modelpart.getChild("orb");
        this.swirl = modelpart.getChild("swirl");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("orb", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.ZERO);
        partdefinition.addOrReplaceChild("swirl", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 8, 8);
    }

    @Override
    public void render(RedstoneMinesProjectile entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource
            bufferSource, int light) {
        poseStack.pushPose();
        poseStack.translate(0, entity.getBoundingBox().getYsize() * .5f, 0);
        bufferSource.getBuffer(RenderType.entityCutoutNoCull(this.getTextureLocation(entity)));

        float scalePerLayer = 0.2F;
        float f = entity.tickCount + partialTicks;
        float swirlX = Mth.cos(.08f * f) * 180;
        float swirlY = Mth.sin(.08f * f) * 180;
        float swirlZ = Mth.cos(.08f * f + 5464) * 180;
        PoseStack.Pose pose = poseStack.last();
        Matrix4f poseMatrix = pose.pose();
        Matrix3f normalMatrix = pose.normal();
        Vec3 motion = entity.getDeltaMovement();
        float xRot = -((float) (Mth.atan2(motion.horizontalDistance(), motion.y) * (double) (180F / (float) Math.PI)) - 90.0F);
        float yRot = -((float) (Mth.atan2(motion.z, motion.x) * (double) (180F / (float) Math.PI)) + 90.0F);
        poseStack.mulPose(Axis.XP.rotationDegrees(swirlX));
        poseStack.mulPose(Axis.YP.rotationDegrees(swirlY));
        poseStack.mulPose(Axis.ZP.rotationDegrees(swirlZ));

        VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(entity)));
        float scale = 2.0F - (float) scalePerLayer;
        poseStack.scale(scale, scale, scale);
        this.orb.render(poseStack, consumer, 15728880, OverlayTexture.NO_OVERLAY);

        poseStack.mulPose(Axis.XP.rotationDegrees(swirlX));
        poseStack.mulPose(Axis.YP.rotationDegrees(swirlY));
        poseStack.mulPose(Axis.ZP.rotationDegrees(swirlZ));
        consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(getSwirlTextureLocation(entity)));
        poseStack.scale(1.15f, 1.15f, 1.15f);
        this.swirl.render(poseStack, consumer, light, OverlayTexture.NO_OVERLAY);

        poseStack.popPose();

        super.render(entity, yaw, partialTicks, poseStack, bufferSource, light);
    }

    public ResourceLocation getTextureLocation(RedstoneMinesProjectile entity) {return TEXTURE;}

    private ResourceLocation getSwirlTextureLocation(RedstoneMinesProjectile entity) {
        int frame = (entity.tickCount) % SWIRL_TEXTURES.length;
        return SWIRL_TEXTURES[frame];
    }
}

