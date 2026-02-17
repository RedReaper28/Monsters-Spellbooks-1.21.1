package net.redreaper.monsterspellbooks.entity.spells.poison_quill;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PoisonQuillModel extends GeoModel<PoisonQuillProjectile> {
    public ResourceLocation getModelResource(PoisonQuillProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/entity/spells/poison_quill.geo.json");
    }

    public ResourceLocation getTextureResource(PoisonQuillProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/entity/poison_quill/poison_quill.png");
    }

    public ResourceLocation getAnimationResource(PoisonQuillProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "animations/entity/spells/poison_quill.animation.json");
    }
}