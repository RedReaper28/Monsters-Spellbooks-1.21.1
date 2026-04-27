package net.redreaper.monsterspellbooks.entity.model.OrbitalSnowballEntity;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.summons.OrbitalSnowballEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class OrbitalSnowballModel extends DefaultedEntityGeoModel<OrbitalSnowballEntity> {
    public OrbitalSnowballModel() {
        super(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "orbital_snowball"));
    }

    @Override
    public ResourceLocation getModelResource(OrbitalSnowballEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/entity/spells/orbital_snowball.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(OrbitalSnowballEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/orbital_snowball/orbital_snowball.png");
    }

    @Override
    public ResourceLocation getAnimationResource(OrbitalSnowballEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "animations/entity/spells/orbital_snowball.animation.json");
    }

    @Override
    public void setCustomAnimations(OrbitalSnowballEntity animatable, long instanceId, AnimationState<OrbitalSnowballEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
    }
}

