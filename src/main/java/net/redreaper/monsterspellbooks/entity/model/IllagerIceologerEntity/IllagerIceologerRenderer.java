package net.redreaper.monsterspellbooks.entity.model.IllagerIceologerEntity;

import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.redreaper.monsterspellbooks.entity.model.AbstractSpellCastingIllager.AbstractSpellCastingIllagerRenderer;
import net.redreaper.monsterspellbooks.entity.model.IllagerEnchanterEntity.IllagerEnchanterModel;

public class IllagerIceologerRenderer extends AbstractSpellCastingIllagerRenderer {

    public IllagerIceologerRenderer(EntityRendererProvider.Context context) {
        super(context, new IllagerIceologerModel());
    }
}
