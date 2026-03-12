package net.redreaper.monsterspellbooks.entity.model.DraugrPillagerEntity;

import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class DraugrPillagerRenderer extends AbstractSpellCastingMobRenderer {

    public DraugrPillagerRenderer(EntityRendererProvider.Context context) {
        super(context, new DraugrPillagerModel());
    }
}

