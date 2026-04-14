package net.redreaper.monsterspellbooks.entity.spells.spinblade;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DwarvenSpinbladeModel extends GeoModel<DwarvenSpinbladeProjectile> {
    public ResourceLocation getModelResource(DwarvenSpinbladeProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/entity/spells/dwarven_spinblade.geo.json");
    }

    public ResourceLocation getTextureResource(DwarvenSpinbladeProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/entity/spinblade/dwarven_spinblade.png");
    }

    public ResourceLocation getAnimationResource(DwarvenSpinbladeProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "animations/entity/spells/dwarven_spinblade.animation.json");
    }
}
