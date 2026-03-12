package net.redreaper.monsterspellbooks.entity.model.DraugrVindicatorEntity;

import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class DraugrVindicatorRenderer extends AbstractSpellCastingMobRenderer {

    public DraugrVindicatorRenderer(EntityRendererProvider.Context context) {
        super(context, new DraugrVindicatorModel());
    }
}

