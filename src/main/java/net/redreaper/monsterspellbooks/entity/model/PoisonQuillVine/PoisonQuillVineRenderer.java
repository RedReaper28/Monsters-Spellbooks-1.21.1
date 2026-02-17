package net.redreaper.monsterspellbooks.entity.model.PoisonQuillVine;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.PoisonQuillVineEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class PoisonQuillVineRenderer extends GeoEntityRenderer<PoisonQuillVineEntity> {
    public PoisonQuillVineRenderer(EntityRendererProvider.Context renderManager, GeoModel<PoisonQuillVineEntity> model) {
        super(renderManager, model);
        this.shadowRadius = .6f;
        this.addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(PoisonQuillVineEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/living/poison_quill_vine/poison_quill_vine.png");
    }

    @Override
    public void render(PoisonQuillVineEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}

