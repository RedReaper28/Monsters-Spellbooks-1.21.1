package net.redreaper.monsterspellbooks.entity.model.RedstoneElementalEntity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.RedstoneElementalEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class RedstoneElementalRenderer extends GeoEntityRenderer<RedstoneElementalEntity> {
    public RedstoneElementalRenderer(EntityRendererProvider.Context renderManager, GeoModel<RedstoneElementalEntity> model) {
        super(renderManager, model);
        this.shadowRadius = 0.5f;
        this.addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(RedstoneElementalEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/living/redstone_elemental/redstone_elemental.png");
    }

    @Override
    public void render(RedstoneElementalEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}