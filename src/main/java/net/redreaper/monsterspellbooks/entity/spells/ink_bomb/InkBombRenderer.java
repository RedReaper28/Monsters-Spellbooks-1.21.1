package net.redreaper.monsterspellbooks.entity.spells.ink_bomb;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.entity.spells.raigo.RaigoEmissiveLayer;
import net.redreaper.monsterspellbooks.entity.spells.raigo.RaigoModel;
import net.redreaper.monsterspellbooks.entity.spells.raigo.RaigoProjectile;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class InkBombRenderer extends GeoEntityRenderer<InkBombProjectile> {
    public InkBombRenderer(EntityRendererProvider.Context context) {
        super(context, new InkBombModel());
        this.shadowRadius = 0.0F;
    }

    public void preRender(PoseStack poseStack, InkBombProjectile animatable, BakedGeoModel model, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
        Vec3 motion = animatable.getDeltaMovement();
        float xRot = (float)(-(Mth.atan2(motion.y, motion.horizontalDistance()) * (180D / Math.PI)));
        float yRot = -((float)(Mth.atan2(motion.z, motion.x) * (double)(180F / (float)Math.PI)) + 90.0F) + 180.0F;
        poseStack.mulPose(Axis.YP.rotationDegrees(yRot));
        poseStack.mulPose(Axis.XP.rotationDegrees(xRot));
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
    }

    public RenderType getRenderType(InkBombProjectile animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityCutout(texture);
    }
}
