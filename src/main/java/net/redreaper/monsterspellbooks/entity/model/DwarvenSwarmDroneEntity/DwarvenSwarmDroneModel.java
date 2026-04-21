package net.redreaper.monsterspellbooks.entity.model.DwarvenSwarmDroneEntity;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.summons.DwarvenSwarmDroneEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class DwarvenSwarmDroneModel extends DefaultedEntityGeoModel<DwarvenSwarmDroneEntity> {
    public DwarvenSwarmDroneModel() {
        super(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "dwarven_swarm_drone"));
    }

    @Override
    public ResourceLocation getModelResource(DwarvenSwarmDroneEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/entity/dwarven_swarm_drone.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DwarvenSwarmDroneEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/entity/living/dwarven_swarm_drone/dwarven_swarm_drone.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DwarvenSwarmDroneEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "animations/entity/dwarven_swarm_drone.animation.json");
    }

    @Override
    public void setCustomAnimations(DwarvenSwarmDroneEntity animatable, long instanceId, AnimationState<DwarvenSwarmDroneEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
    }
}
