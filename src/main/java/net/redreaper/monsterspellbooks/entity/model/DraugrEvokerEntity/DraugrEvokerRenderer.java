package net.redreaper.monsterspellbooks.entity.model.DraugrEvokerEntity;

import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class DraugrEvokerRenderer extends AbstractSpellCastingMobRenderer {

    public DraugrEvokerRenderer(EntityRendererProvider.Context context) {
        super(context, new DraugrEvokerModel());
    }
}

