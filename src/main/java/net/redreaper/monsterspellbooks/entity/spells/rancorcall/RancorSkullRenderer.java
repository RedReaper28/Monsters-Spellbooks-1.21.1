package net.redreaper.monsterspellbooks.entity.spells.rancorcall;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RancorSkullRenderer extends GeoEntityRenderer<RancorSkull> {
    public RancorSkullRenderer(EntityRendererProvider.Context context) {
        super(context, new RancorSkullModel());
        this.shadowRadius = 0.0F;
    }

    public void preRender(PoseStack poseStack, RancorSkull animatable, BakedGeoModel model, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
        Vec3 motion = animatable.getDeltaMovement();
        float xRot = (float)(-(Mth.atan2(motion.y, motion.horizontalDistance()) * (180D / Math.PI)));
        float yRot = -((float)(Mth.atan2(motion.z, motion.x) * (double)(180F / (float)Math.PI)) + 90.0F) + 180.0F;
        poseStack.mulPose(Axis.YP.rotationDegrees(yRot));
        poseStack.mulPose(Axis.XP.rotationDegrees(xRot));
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
    }
}
