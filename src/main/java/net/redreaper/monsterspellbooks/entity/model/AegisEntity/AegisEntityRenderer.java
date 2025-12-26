package net.redreaper.monsterspellbooks.entity.model.AegisEntity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.AegisEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AegisEntityRenderer extends GeoEntityRenderer<AegisEntity> {
    public AegisEntityRenderer(EntityRendererProvider.Context renderManager, GeoModel<AegisEntity> model) {
        super(renderManager, model);
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(AegisEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/aegis.png");
    }

    @Override
    public void render(AegisEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
