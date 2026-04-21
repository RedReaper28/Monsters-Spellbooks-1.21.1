package net.redreaper.monsterspellbooks.entity.model.IceSerpeant;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.summons.IceSerpeantEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class IceSerpeantRenderer extends GeoEntityRenderer<IceSerpeantEntity> {
    public IceSerpeantRenderer(EntityRendererProvider.Context renderManager, GeoModel<IceSerpeantEntity> model) {
        super(renderManager, model);
        this.shadowRadius = .6f;
        this.addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(IceSerpeantEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/living/ice_serpent/ice_serpent.png");
    }

    @Override
    public void render(IceSerpeantEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}

