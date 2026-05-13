package net.redreaper.monsterspellbooks.entity.model.IllagerFireologerEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.redreaper.monsterspellbooks.entity.model.AbstractSpellCastingIllager.AbstractSpellCastingIllagerRenderer;

public class IllagerFireologerRenderer extends AbstractSpellCastingIllagerRenderer {
    public IllagerFireologerRenderer(EntityRendererProvider.Context context) {
        super(context, new IllagerFireologerModel());
    }
}