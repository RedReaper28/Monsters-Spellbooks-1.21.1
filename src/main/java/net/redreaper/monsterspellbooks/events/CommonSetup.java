package net.redreaper.monsterspellbooks.events;

import io.redspace.ironsspellbooks.api.util.Utils;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.*;
import net.redreaper.monsterspellbooks.init.ModEntities;

@EventBusSubscriber(modid = MonstersSpellbooks.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class CommonSetup {
    @SubscribeEvent
    public static void onAttributeCreateEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntities.VILE_SKELETON.get(), VileSkeletonEntity.createAttributes().build());
        event.put(ModEntities.DWARVEN_SPHERE.get(), DwarvenSphereEntity.createAttributes().build());
        event.put(ModEntities.DWARVEN_SLICER.get(), DwarvenSlicerEntity.createAttributes().build());
        event.put(ModEntities.SHOCK.get(), ShockEntity.createAttributes().build());
        event.put(ModEntities.AEGIS.get(), AegisEntity.createAttributes().build());
        event.put(ModEntities.DRIPPLER.get(), DripplerEntity.createAttributes().build());
        event.put(ModEntities.JUNGLE_WHISPERER.get(), JungleWhispererEntity.createAttributes().build());
        event.put(ModEntities.BLASTLING.get(), BlastlingEntity.createAttributes().build());
        event.put(ModEntities.POISON_QUILL_VINE.get(), PoisonQuillVineEntity.createAttributes().build());
        event.put(ModEntities.SUMMONED_AEGIS.get(), SummonedAegisEntity.createAttributes().build());
        event.put(ModEntities.SUMMONED_VILE_SKELETON.get(), SummonedVileSkeletonEntity.createAttributes().build());
        event.put(ModEntities.SUMMONED_DEATH_KNIGHT.get(), DeathKnightEntity.createAttributes().build());
        event.put(ModEntities.SOUL_WIZARD.get(), SoulWizardEntity.createAttributes().build());

    }

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event){

        event.register(ModEntities.DRIPPLER.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (type, serverLevelAccessor, spawnType, blockPos, random) -> Utils.checkMonsterSpawnRules(serverLevelAccessor, spawnType, blockPos, random), RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(ModEntities.DWARVEN_SPHERE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules,RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(ModEntities.JUNGLE_WHISPERER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (type, serverLevelAccessor, spawnType, blockPos, random) -> Utils.checkMonsterSpawnRules(serverLevelAccessor, spawnType, blockPos, random), RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(ModEntities.VILE_SKELETON.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules,RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(ModEntities.BLASTLING.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (type, serverLevelAccessor, spawnType, blockPos, random) -> Utils.checkMonsterSpawnRules(serverLevelAccessor, spawnType, blockPos, random), RegisterSpawnPlacementsEvent.Operation.REPLACE);

    }
}
