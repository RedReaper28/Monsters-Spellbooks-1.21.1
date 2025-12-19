package net.redreaper.monsterspellbooks.entity.model.ShockEntity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.ShockEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ShockEntityRenderer extends GeoEntityRenderer<ShockEntity> {
    public ShockEntityRenderer(EntityRendererProvider.Context renderManager, GeoModel<ShockEntity> model) {
        super(renderManager, model);
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(ShockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/granite_elemental.png");
    }

    @Override
    public void render(ShockEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
