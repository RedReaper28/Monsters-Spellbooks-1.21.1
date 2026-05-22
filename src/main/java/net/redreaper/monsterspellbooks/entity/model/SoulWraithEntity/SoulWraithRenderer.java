package net.redreaper.monsterspellbooks.entity.model.SoulWraithEntity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.SoulWraithEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class SoulWraithRenderer  extends GeoEntityRenderer<SoulWraithEntity> {
    public SoulWraithRenderer(EntityRendererProvider.Context renderManager, GeoModel<SoulWraithEntity> model) {
        super(renderManager, model);
        this.shadowRadius = .6f;
        this.addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(SoulWraithEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/living/soul_wraith/soul_wraith.png");
    }

    @Override
    public void render(SoulWraithEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
