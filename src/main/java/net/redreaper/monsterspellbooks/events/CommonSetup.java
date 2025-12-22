package net.redreaper.monsterspellbooks.events;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.*;
import net.redreaper.monsterspellbooks.entity.model.DwarvenSphere.DwarvenSphereRenderer;
import net.redreaper.monsterspellbooks.init.ModEntities;

@EventBusSubscriber(modid = MonstersSpellbooks.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class CommonSetup {
    @SubscribeEvent
    public static void onAttributeCreateEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntities.VILE_SKELETON.get(), VileSkeleton.createAttributes().build());
        event.put(ModEntities.DWARVEN_SPHERE.get(), DwarvenSphere.createAttributes().build());
        event.put(ModEntities.SHOCK.get(), ShockEntity.createAttributes().build());
        event.put(ModEntities.SUMMONED_VILE_SKELETON.get(), SummonedVileSkeleton.createAttributes().build());
        event.put(ModEntities.SUMMONED_DEATH_KNIGHT.get(), DeathKnightEntity.createAttributes().build());
    }
}
