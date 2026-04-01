package net.redreaper.monsterspellbooks.entity.model.IllagerIceologerEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.redreaper.monsterspellbooks.entity.model.AbstractSpellCastingIllager.AbstractSpellCastingIllagerRenderer;

public class IllagerIceologerRenderer extends AbstractSpellCastingIllagerRenderer {
    public IllagerIceologerRenderer(EntityRendererProvider.Context context) {
        super(context, new IllagerIceologerModel());
    }
}