package net.redreaper.monsterspellbooks.entity.spells.soul_casting_field;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SoulCastingFieldModel extends GeoModel<SoulCastingFieldAOE> {

    @Override
    public ResourceLocation getModelResource(SoulCastingFieldAOE animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/entity/spells/soul_casting_field.geo.json");
    }

    public ResourceLocation getTextureResource(SoulCastingFieldAOE animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/entity/soul_casting_field/soul_casting_field.png");
    }

    public ResourceLocation getAnimationResource(SoulCastingFieldAOE animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "animations/entity/spells/soul_casting_field.animation.json");
    }
}