package net.redreaper.monsterspellbooks.entity.model.LivingLeafCrystal;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.summons.LivingLeafCrystalEntity;
import net.redreaper.monsterspellbooks.entity.living.summons.PoisonQuillVineEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.GeoModel;

public class LivingLeafCrystalModel extends DefaultedEntityGeoModel<LivingLeafCrystalEntity> {
    public LivingLeafCrystalModel() {
        super(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "leaf_crystal"));
    }

    @Override
    public ResourceLocation getModelResource(LivingLeafCrystalEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/entity/spells/leaf_crystal.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LivingLeafCrystalEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/leaf_crystal/leaf_crystal.png");
    }

    @Override
    public ResourceLocation getAnimationResource(LivingLeafCrystalEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "animations/entity/spells/leaf_crystal.animation.json");
    }

    @Override
    public void setCustomAnimations(LivingLeafCrystalEntity animatable, long instanceId, AnimationState<LivingLeafCrystalEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
    }
}

