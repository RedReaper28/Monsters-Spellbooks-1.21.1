package net.redreaper.monsterspellbooks.entity.model.IllagerEnchanterEntity;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.illagers.AbstractSpellCastingIllager;
import net.redreaper.monsterspellbooks.entity.model.AbstractSpellCastingIllager.AbstractSpellCastingIllagerModel;

public class IllagerEnchanterModel extends AbstractSpellCastingIllagerModel {
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/illager_enchanter.png");
    public static final ResourceLocation MODEL = ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "geo/archevoker.geo.json");

    @Override
    public ResourceLocation getModelResource(AbstractSpellCastingIllager object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(AbstractSpellCastingIllager object) {
        return TEXTURE;
    }

}