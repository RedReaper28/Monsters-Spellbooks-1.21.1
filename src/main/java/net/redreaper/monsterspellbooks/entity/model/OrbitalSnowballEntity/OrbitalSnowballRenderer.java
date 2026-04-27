package net.redreaper.monsterspellbooks.entity.model.OrbitalSnowballEntity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.summons.LivingLeafCrystalEntity;
import net.redreaper.monsterspellbooks.entity.living.summons.OrbitalSnowballEntity;
import net.redreaper.monsterspellbooks.entity.model.LivingLeafCrystal.LivingLeafCrystalModel;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OrbitalSnowballRenderer extends GeoEntityRenderer<OrbitalSnowballEntity> {
    public OrbitalSnowballRenderer(EntityRendererProvider.Context renderManager, GeoModel<OrbitalSnowballEntity> model) {
        super(renderManager, new OrbitalSnowballModel());
        this.shadowRadius = 0.0F;
    }

    @Override
    public ResourceLocation getTextureLocation(OrbitalSnowballEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/orbital_snowball/orbital_snowball.png");
    }

    @Override
    public void render(OrbitalSnowballEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    public RenderType getRenderType(OrbitalSnowballEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(texture);
    }
}
