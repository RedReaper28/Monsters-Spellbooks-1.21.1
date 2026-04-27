package net.redreaper.monsterspellbooks.entity.model.LivingLeafCrystal;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.summons.LivingLeafCrystalEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class LivingLeafCrystalRenderer extends GeoEntityRenderer<LivingLeafCrystalEntity> {
    public LivingLeafCrystalRenderer(EntityRendererProvider.Context renderManager, GeoModel<LivingLeafCrystalEntity> model) {
        super(renderManager, new LivingLeafCrystalModel());
        this.shadowRadius = 0.0F;
    }

    @Override
    public ResourceLocation getTextureLocation(LivingLeafCrystalEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/leaf_crystal/leaf_crystal.png");
    }

    @Override
    public void render(LivingLeafCrystalEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    public RenderType getRenderType(LivingLeafCrystalEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(texture);
    }
}
