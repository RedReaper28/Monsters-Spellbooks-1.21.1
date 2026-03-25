package net.redreaper.monsterspellbooks.entity.model.IllagerEnchanterEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.redreaper.monsterspellbooks.entity.model.AbstractSpellCastingIllager.AbstractSpellCastingIllagerRenderer;

public class IllagerEnchanterRenderer extends AbstractSpellCastingIllagerRenderer {

    public IllagerEnchanterRenderer(EntityRendererProvider.Context context) {
        super(context, new IllagerEnchanterModel());
    }
}
