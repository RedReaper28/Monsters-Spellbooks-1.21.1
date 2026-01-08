package net.redreaper.monsterspellbooks.entity.model.SummonedAegisEntity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.SummonedAegisEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SummonedAegisEntityRenderer extends GeoEntityRenderer<SummonedAegisEntity> {
    public SummonedAegisEntityRenderer(EntityRendererProvider.Context renderManager, GeoModel<SummonedAegisEntity> model) {
        super(renderManager, model);
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(SummonedAegisEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/aegis.png");
    }

    @Override
    public void render(SummonedAegisEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
