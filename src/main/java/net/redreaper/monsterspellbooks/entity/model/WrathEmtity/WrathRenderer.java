package net.redreaper.monsterspellbooks.entity.model.WrathEmtity;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.entity.living.summons.WrathEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WrathRenderer extends GeoEntityRenderer<WrathEntity> {
    public static final ResourceLocation textureLocation = ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "textures/entity/wisp/wisp.png");

    public WrathRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WrathModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(WrathEntity animatable) {
        return textureLocation;
    }

    @Override
    public RenderType getRenderType(WrathEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.energySwirl(texture, 0, 0);
    }
}

