package net.redreaper.monsterspellbooks.entity.spells.ice_arsenal;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import io.redspace.ironsspellbooks.render.RenderHelper;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.spells.divine_intervention.DivineSwordProjectile;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;

public class IceSwordModel extends GeoModel<IceArsenalSword> {
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/ice_arsenal/ice_sword.png");
    public static final ResourceLocation MODEL = ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "geo/fiery_dagger.geo.json");

    @Override
    public ResourceLocation getModelResource(IceArsenalSword animatable) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(IceArsenalSword animatable) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(IceArsenalSword animatable) {
        return AbstractSpellCastingMob.animationInstantCast;
    }
}

