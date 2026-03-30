package net.redreaper.monsterspellbooks.entity.model.PrismarineKeeper;

import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobModel;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;

public class PrismarineKeeperModel extends AbstractSpellCastingMobModel {
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/living/prismarine_keeper/prismarine_keeper.png");
    public static final ResourceLocation modelResource = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/entity/prismarine_keeper.geo.json");

    @Override
    public ResourceLocation getTextureResource(AbstractSpellCastingMob object) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getModelResource(AbstractSpellCastingMob object) {
        return modelResource;
    }
}
