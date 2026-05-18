package net.redreaper.monsterspellbooks.entity.model.WrathEmtity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.redreaper.monsterspellbooks.entity.living.summons.WrathEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WrathRenderer extends GeoEntityRenderer<WrathEntity> {
    public WrathRenderer(EntityRendererProvider.Context context) {
        super(context, new WrathModel());
        this.shadowRadius = 0.0F;
    }

}

