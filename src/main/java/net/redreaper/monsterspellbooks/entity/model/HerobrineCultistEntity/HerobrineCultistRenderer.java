package net.redreaper.monsterspellbooks.entity.model.HerobrineCultistEntity;

import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class HerobrineCultistRenderer extends AbstractSpellCastingMobRenderer {

    public HerobrineCultistRenderer(EntityRendererProvider.Context context) {
        super(context, new HerobrineCultistModel());
    }

}
