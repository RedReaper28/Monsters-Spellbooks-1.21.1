package net.redreaper.monsterspellbooks.entity.model.IllagerEnchanterEntity;

import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class IllagerEnchanterRenderer extends AbstractSpellCastingMobRenderer {

    public IllagerEnchanterRenderer(EntityRendererProvider.Context context) {
        super(context, new IllagerEnchanterModel());
    }
}
