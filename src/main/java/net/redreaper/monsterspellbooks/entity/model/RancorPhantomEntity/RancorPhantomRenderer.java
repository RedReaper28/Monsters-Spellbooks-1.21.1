package net.redreaper.monsterspellbooks.entity.model.RancorPhantomEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.redreaper.monsterspellbooks.entity.living.summons.RancorPhantomEntity;
import net.redreaper.monsterspellbooks.entity.living.summons.WrathEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RancorPhantomRenderer extends GeoEntityRenderer<RancorPhantomEntity> {
    public RancorPhantomRenderer(EntityRendererProvider.Context context) {
        super(context, new RancorPhantomModel());
        this.shadowRadius = 0.0F;
    }

}

