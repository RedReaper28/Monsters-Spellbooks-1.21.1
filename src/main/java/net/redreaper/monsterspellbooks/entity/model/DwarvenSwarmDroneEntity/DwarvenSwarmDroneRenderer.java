package net.redreaper.monsterspellbooks.entity.model.DwarvenSwarmDroneEntity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.summons.DwarvenSwarmDroneEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class DwarvenSwarmDroneRenderer extends GeoEntityRenderer<DwarvenSwarmDroneEntity> {
    public DwarvenSwarmDroneRenderer(EntityRendererProvider.Context renderManager, GeoModel<DwarvenSwarmDroneEntity> model) {
        super(renderManager, model);
        this.shadowRadius = 1.5f;
        this.addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(DwarvenSwarmDroneEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/living/dwarven_swarm_drone/dwarven_swarm_drone.png");
    }

    @Override
    public void render(DwarvenSwarmDroneEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}