package net.redreaper.monsterspellbooks.entity.spells.divine_intervention;

import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import io.redspace.ironsspellbooks.render.RenderHelper;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;

public class DivineSwordModel extends GeoModel<DivineSwordProjectile> {
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/divine_sword/divine_sword.png");
    public static final ResourceLocation MODEL = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/entity/spells/divine_sword.geo.json");
    @Override
    public ResourceLocation getModelResource(DivineSwordProjectile animatable) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(DivineSwordProjectile animatable) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(DivineSwordProjectile animatable) {
        return AbstractSpellCastingMob.animationInstantCast;
    }

    @Override
    public @Nullable RenderType getRenderType(DivineSwordProjectile animatable, ResourceLocation texture) {
        return RenderHelper.CustomerRenderType.magic(TEXTURE);
    }
}

