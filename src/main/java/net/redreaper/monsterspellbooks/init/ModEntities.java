package net.redreaper.monsterspellbooks.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.*;
import net.redreaper.monsterspellbooks.entity.spells.ancient_flash.AncientFlash;
import net.redreaper.monsterspellbooks.entity.spells.ancient_lightning_lance.AncientLightningLanceProjectile;
import net.redreaper.monsterspellbooks.entity.spells.ancient_lightning_lance.StaticField;
import net.redreaper.monsterspellbooks.entity.spells.blast_fungus.BlastFungusProjectile;
import net.redreaper.monsterspellbooks.entity.spells.blizzard_aspect.PowderSnowSplash;
import net.redreaper.monsterspellbooks.entity.spells.blizzard_aspect.SnowCloud;
import net.redreaper.monsterspellbooks.entity.spells.blood_pierce_bullet.BloodPierceVisualEntity;
import net.redreaper.monsterspellbooks.entity.spells.blood_thorn.BloodThornProjectile;
import net.redreaper.monsterspellbooks.entity.spells.bone_dagger.BoneDaggerProjectile;
import net.redreaper.monsterspellbooks.entity.spells.brimstone_buzzsaw.BrimstoneBuzzsawProjectile;
import net.redreaper.monsterspellbooks.entity.spells.brimstone_rain.BrimstoneField;
import net.redreaper.monsterspellbooks.entity.spells.brimstone_rain.SmallBrimstoneFireball;
import net.redreaper.monsterspellbooks.entity.spells.bubble_spray.BubbleSprayProjectile;
import net.redreaper.monsterspellbooks.entity.spells.cauterizing_touch.CauterizingTouch;
import net.redreaper.monsterspellbooks.entity.spells.dragon_charge.DragonChargeProjectile;
import net.redreaper.monsterspellbooks.entity.spells.elthor.ElthorBeamEntity;
import net.redreaper.monsterspellbooks.entity.spells.forceful_wind.ForcefulWindProjectile;
import net.redreaper.monsterspellbooks.entity.spells.frenzied_burst.FrenziedBurstVisualEntity;
import net.redreaper.monsterspellbooks.entity.spells.frenzied_storm.SmallFrenzyFireBall;
import net.redreaper.monsterspellbooks.entity.spells.graveyard_fissure.GraveyardHand;
import net.redreaper.monsterspellbooks.entity.spells.guardians_neutralizer.GuardiansNeutralizerVisualEntity;
import net.redreaper.monsterspellbooks.entity.spells.hallow_slash.HallowSlashProjectile;
import net.redreaper.monsterspellbooks.entity.spells.ice_arsenal.IceArsenalSword;
import net.redreaper.monsterspellbooks.entity.spells.infection_slash.InfectionSlashProjectile;
import net.redreaper.monsterspellbooks.entity.spells.ink_bomb.InkBombProjectile;
import net.redreaper.monsterspellbooks.entity.spells.life_drain.LifeDrainProjectile;
import net.redreaper.monsterspellbooks.entity.spells.napalm_orb.NapalmOrb;
import net.redreaper.monsterspellbooks.entity.spells.paladin_throw.HolyHammerProjectile;
import net.redreaper.monsterspellbooks.entity.spells.pale_thorn.PaleThornVisualEntity;
import net.redreaper.monsterspellbooks.entity.spells.plasma_barrage.PlasmaBolt;
import net.redreaper.monsterspellbooks.entity.spells.poison_quill.PoisonQuillProjectile;
import net.redreaper.monsterspellbooks.entity.spells.putrescence_mass.PutrescenceField;
import net.redreaper.monsterspellbooks.entity.spells.putrescence_mass.PutrescenceMass;
import net.redreaper.monsterspellbooks.entity.spells.raigo.RaigoProjectile;
import net.redreaper.monsterspellbooks.entity.spells.rancorcall.RancorSkull;
import net.redreaper.monsterspellbooks.entity.spells.razorblade_typhoon.RazorbladeTyphoonProjectile;
import net.redreaper.monsterspellbooks.entity.spells.sangunite_eviceration.SanguiniteEvisceration;
import net.redreaper.monsterspellbooks.entity.spells.soul_chain.SoulChain;
import net.redreaper.monsterspellbooks.entity.spells.soul_firebolt.SoulFireBoltProjectile;
import net.redreaper.monsterspellbooks.entity.spells.soul_scorch.SoulFireField;
import net.redreaper.monsterspellbooks.entity.spells.space_breaker.SpaceBreaker;
import net.redreaper.monsterspellbooks.entity.spells.space_rupture.DistortionField;
import net.redreaper.monsterspellbooks.entity.spells.space_rupture.SpaceRupture;
import net.redreaper.monsterspellbooks.entity.spells.spectral_blast.SpectralBlastVisualEntity;
import net.redreaper.monsterspellbooks.entity.spells.spider_fangs.CaveSpiderFangProjectile;
import net.redreaper.monsterspellbooks.entity.spells.spider_fangs.ChaurusMandiblesProjectile;
import net.redreaper.monsterspellbooks.entity.spells.spider_fangs.IceSpiderFangProjectile;
import net.redreaper.monsterspellbooks.entity.spells.static_cleave.StaticCleave;
import net.redreaper.monsterspellbooks.entity.spells.steam_stream.SteamStreamProjectile;
import net.redreaper.monsterspellbooks.entity.spells.stray_grasp.StrayGraspProjectile;
import net.redreaper.monsterspellbooks.entity.spells.thunderstorm_wave.AncientLightningStrike;
import net.redreaper.monsterspellbooks.entity.spells.vile_slash.VileSlashProjectileNew;
import net.redreaper.monsterspellbooks.entity.spells.voltaic_multishot.VoltArrowProjectile;
import net.redreaper.monsterspellbooks.entity.spells.water_trident.WaterTridentProjectile;
import net.redreaper.monsterspellbooks.entity.spells.wither_bomb.WitherBombProjectile;
import net.redreaper.monsterspellbooks.entity.spells.wither_nova.WitherNovaVisualEntity;
import net.redreaper.monsterspellbooks.entity.spells.torment_arrow.TormentArrowProjectile;

import java.util.function.Supplier;

import static net.minecraft.core.registries.Registries.ENTITY_TYPE;

public class ModEntities {
    private static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ENTITY_TYPE, MonstersSpellbooks.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<TormentArrowProjectile>> TORMENT_ARROW_PROJECTILE =
            ENTITIES.register("torment_arrow", () -> EntityType.Builder.<TormentArrowProjectile>of(TormentArrowProjectile::new, MobCategory.MISC)
                    .sized(.8f, .8f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "torment_arrow").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<RedSnakeWitherSkeletonEntity>> REDSNAKE_WITHER_SKELETON =
            ENTITIES.register("redsnake_wither_skeleton", () -> EntityType.Builder.<RedSnakeWitherSkeletonEntity>of(RedSnakeWitherSkeletonEntity::new, MobCategory.MONSTER)
                    .sized(1.5f, .95f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "redsnake_wither_skeleton").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<PrismarineKeeper>> PRISMARINE_KEEPER =
            ENTITIES.register("prismarine_keeper", () -> EntityType.Builder.<PrismarineKeeper>of(PrismarineKeeper::new, MobCategory.MONSTER)
                    .sized(.85f, 2.3f)
                    .clientTrackingRange(64)
                    .eyeHeight(2.3f)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "prismarine_keeper").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<SummonedPrismarineKeeper>> SUMMONED_PRISMARINE_KEEPER =
            ENTITIES.register("summoned_prismarine_keeper", () -> EntityType.Builder.<SummonedPrismarineKeeper>of(SummonedPrismarineKeeper::new, MobCategory.MONSTER)
                    .sized(.85f, 2.3f)
                    .clientTrackingRange(64)
                    .eyeHeight(2.3f)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "summoned_prismarine_keeper").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<InkBombProjectile>> INK_BOMB =
            ENTITIES.register("ink_bomb", () -> EntityType.Builder.<InkBombProjectile>of(InkBombProjectile::new, MobCategory.MISC)
                    .sized(0.75F, 0.75F)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "ink_bomb").toString()));


    public static final DeferredHolder<EntityType<?>, EntityType<VoltArrowProjectile>> VOLT_ARROW_PROJECTILE =
            ENTITIES.register("volt_arrow", () -> EntityType.Builder.<VoltArrowProjectile>of(VoltArrowProjectile::new, MobCategory.MISC)
                    .sized(.8f, .8f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "volt_arrow").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<ElthorBeamEntity>> ELTHOR_BEAM =
            ENTITIES.register("elthor_beam", () -> EntityType.Builder.<ElthorBeamEntity>of(ElthorBeamEntity::new, MobCategory.MISC)
                    .sized(2, 14f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "elthor_beam").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<PlasmaBolt>> PLASMA_BOLT =
            ENTITIES.register("plasma_bolt", () -> EntityType.Builder.<PlasmaBolt>of(PlasmaBolt::new, MobCategory.MISC)
                    .sized(1, 1)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "plasma_bolt").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<SoulFireBoltProjectile>> SOUL_FIREBOLT_PROJECTILE =
            ENTITIES.register("soul_firebolt", () -> EntityType.Builder.<SoulFireBoltProjectile>of(SoulFireBoltProjectile::new, MobCategory.MISC)
                    .sized(.5f, .5f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "soul_firebolt").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<GraveyardHand>> GRAVEYARD_HAND =
            ENTITIES.register("graveyard_hand", () -> EntityType.Builder.<GraveyardHand>of(GraveyardHand::new, MobCategory.MISC)
                    .sized(1f, 1.5f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "graveyard_hand").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<RaigoProjectile>> RAIGO =
            ENTITIES.register("raigo", () -> EntityType.Builder.<RaigoProjectile>of(RaigoProjectile::new, MobCategory.MISC)
                    .sized(4f, 4f)
                    .clientTrackingRange(4)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "raigo").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<AncientLightningLanceProjectile>> ANCIENT_LIGHTNING_LANCE_PROJECTILE =
            ENTITIES.register("ancient_lightning_lance", () -> EntityType.Builder.<AncientLightningLanceProjectile>of(AncientLightningLanceProjectile::new, MobCategory.MISC)
                    .sized(1.25f, 1.25f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "ancient_lightning_lance").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<StrayGraspProjectile>> STRAY_GRASP =
            ENTITIES.register("stray_grasp", () -> EntityType.Builder.<StrayGraspProjectile>of(StrayGraspProjectile::new, MobCategory.MISC)
                    .sized(1, 1)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "stray_grasp").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<WaterTridentProjectile>> WATER_TRIDENT =
            ENTITIES.register("water_trident", () -> EntityType.Builder.<WaterTridentProjectile>of(WaterTridentProjectile::new, MobCategory.MISC)
                    .sized(3, 1.5f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "water_trident").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<VileSlashProjectileNew>> VILE_SLASH =
            ENTITIES.register("vile_slash", () -> EntityType.Builder.<VileSlashProjectileNew>of(VileSlashProjectileNew::new, MobCategory.MISC)
                    .sized(2, 0.5f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "vile_slash").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<HolyHammerProjectile>> HOLY_HAMMER =
            ENTITIES.register("holy_hammer", () -> EntityType.Builder.<HolyHammerProjectile>of(HolyHammerProjectile::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "holy_hammer").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<ForcefulWindProjectile>> FORCEFUL_WIND =
            ENTITIES.register("forceful_wind", () -> EntityType.Builder.<ForcefulWindProjectile>of(ForcefulWindProjectile::new, MobCategory.MISC)
                    .sized(1.5f, 2f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "forceful_wind").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<BoneDaggerProjectile>> BONE_DAGGER =
            ENTITIES.register("bone_dagger", () -> EntityType.Builder.<BoneDaggerProjectile>of(BoneDaggerProjectile::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "bone_dagger").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<PoisonQuillProjectile>> POISON_QUILL =
            ENTITIES.register("poison_quill", () -> EntityType.Builder.<PoisonQuillProjectile>of(PoisonQuillProjectile::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "poison_quill").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<CaveSpiderFangProjectile>> CAVE_SPIDER_FANG =
            ENTITIES.register("cave_spider_fang", () -> EntityType.Builder.<CaveSpiderFangProjectile>of(CaveSpiderFangProjectile::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "cave_spider_fang").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<IceSpiderFangProjectile>> ICE_SPIDER_FANG =
            ENTITIES.register("ice_spider_fang", () -> EntityType.Builder.<IceSpiderFangProjectile>of(IceSpiderFangProjectile::new, MobCategory.MISC)
                    .sized(1.5f, 1.5f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "ice_spider_fang").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<ChaurusMandiblesProjectile>> CHAURUS_MANDIBLES =
            ENTITIES.register("chaurus_mandibles", () -> EntityType.Builder.<ChaurusMandiblesProjectile>of(ChaurusMandiblesProjectile::new, MobCategory.MISC)
                    .sized(3f, 0.5F)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "chaurus_mandibles").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<RancorSkull>> RANCOR_SKULL =
            ENTITIES.register("rancor_skull", () -> EntityType.Builder.<RancorSkull>of(RancorSkull::new, MobCategory.MISC)
                    .sized(1, 1)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "rancor_skull").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<BloodThornProjectile>> BLOOD_THORN =
            ENTITIES.register("blood_thorn", () -> EntityType.Builder.<BloodThornProjectile>of(BloodThornProjectile::new, MobCategory.MISC)
                    .sized(0.50f, 0.50f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "blood_thorn").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<AncientLightningStrike>> ANCIENT_LIGHTNING_STRIKE =
            ENTITIES.register("ancient_lightning_strike", () -> EntityType.Builder.<AncientLightningStrike>of(AncientLightningStrike::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "ancient_lightning_strike").toString()));


    public static final DeferredHolder<EntityType<?>, EntityType<SmallFrenzyFireBall>> SMALL_FRENZY_FIREBALL =
            ENTITIES.register("small_frenzy_fireball", () -> EntityType.Builder.<SmallFrenzyFireBall>of(SmallFrenzyFireBall::new, MobCategory.MISC)
                    .sized(.5f, .5f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "small_frenzy_fireball").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<SmallBrimstoneFireball>> SMALL_BRIMSTONE_FIREBALL =
            ENTITIES.register("small_brimstone_fireball", () -> EntityType.Builder.<SmallBrimstoneFireball>of(SmallBrimstoneFireball::new, MobCategory.MISC)
                    .sized(.5f, .5f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "small_brimstone_fireball").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<DragonChargeProjectile>> DRAGON_CHARGE_PROJECTILE =
            ENTITIES.register("dragon_charge", () -> EntityType.Builder.<DragonChargeProjectile>of(DragonChargeProjectile::new, MobCategory.MISC)
                    .sized(.5f, .5f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "dragon_charge").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<SoulChain>> SOUL_CHAIN =
            ENTITIES.register("soul_chain", () -> EntityType.Builder.<SoulChain>of(SoulChain::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "soul_chain").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<SpaceRupture>> SPACE_RUPTURE =
            ENTITIES.register("space_rupture", () -> EntityType.Builder.<SpaceRupture>of(SpaceRupture::new, MobCategory.MISC)
                    .sized(2f, 2f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "space_rupture").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<SanguiniteEvisceration>>SANGUINITE_EVISCERATION =
            ENTITIES.register("sanguinite_evisceration", () -> EntityType.Builder.<SanguiniteEvisceration>of(SanguiniteEvisceration::new, MobCategory.MISC)
                    .sized(2f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "sanguinite_evisceration").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<BloodPierceVisualEntity>> BLOOD_PIERCE_VISUAL_ENTITY =
            ENTITIES.register("blood_pierce", () -> EntityType.Builder.<BloodPierceVisualEntity>of(BloodPierceVisualEntity::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "blood_pierce").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<PaleThornVisualEntity>> PALE_THORN_VISUAL_ENTITY=
            ENTITIES.register("pale_thorn", () -> EntityType.Builder.<PaleThornVisualEntity>of(PaleThornVisualEntity::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "pale_thorn").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<GuardiansNeutralizerVisualEntity>> GUARDIAN_BEAM_VISUAL_ENTITY =
            ENTITIES.register("guardians_neutralizer", () -> EntityType.Builder.<GuardiansNeutralizerVisualEntity>of(GuardiansNeutralizerVisualEntity::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "guardians_neutralizer").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<WitherNovaVisualEntity>> WITHER_NOVA_VISUAL_ENTITY =
            ENTITIES.register("wither_nova", () -> EntityType.Builder.<WitherNovaVisualEntity>of(WitherNovaVisualEntity::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "wither_nova").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<CauterizingTouch>>CAUTERIZING_TOUCH =
            ENTITIES.register("cauterizing_touch", () -> EntityType.Builder.<CauterizingTouch>of(CauterizingTouch::new, MobCategory.MISC)
                    .sized(2f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "cauterizing_touch").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<SpaceBreaker>>SPACE_BREAKER =
            ENTITIES.register("space_breaker", () -> EntityType.Builder.<SpaceBreaker>of(SpaceBreaker::new, MobCategory.MISC)
                    .sized(1.5f, 1)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "space_breaker").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<BrimstoneBuzzsawProjectile>> BRIMSTONE_BUZZSAW_PROJECTILE =
            ENTITIES.register("brimstone_buzzsaw", () -> EntityType.Builder.<BrimstoneBuzzsawProjectile>of(BrimstoneBuzzsawProjectile::new, MobCategory.MISC)
                    .sized(7f, 1f)
                    .clientTrackingRange(128)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "brimstone_buzzsaw").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<FrenziedBurstVisualEntity>> FRENZIED_BURST_VISUAL_ENTITY =
            ENTITIES.register("frenzied_burst", () -> EntityType.Builder.<FrenziedBurstVisualEntity>of(FrenziedBurstVisualEntity::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "frenzied_burst").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<IceArsenalSword>> ICE_SWORD =
            ENTITIES.register("ice_sword", () -> EntityType.Builder.<IceArsenalSword>of(IceArsenalSword::new, MobCategory.MISC)
                    .sized(.5f, .5f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "ice_sword").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<SnowCloud>> SNOW_CLOUD =
            ENTITIES.register("snow_cloud", () -> EntityType.Builder.<SnowCloud>of(SnowCloud::new, MobCategory.MISC)
                    .sized(4f, 1.2f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "snow_cloud").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<PowderSnowSplash>> POWDER_SNOW_SPLASH =
            ENTITIES.register("powder_snow_splash", () -> EntityType.Builder.<PowderSnowSplash>of(PowderSnowSplash::new, MobCategory.MISC)
                    .sized(3.5f, 4f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "powder_snow_splash").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<AncientFlash>> ANCIENT_FLASH =
            ENTITIES.register("ancient_flash", () -> EntityType.Builder.<AncientFlash>of(AncientFlash::new, MobCategory.MISC)
                    .sized(2f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "ancient_flash").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<InfectionSlashProjectile>> INFECTION_SLASH_PROJECTILE =
            ENTITIES.register("infection_slash", () -> EntityType.Builder.<InfectionSlashProjectile>of(InfectionSlashProjectile::new, MobCategory.MISC)
                    .sized(2f, 2f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "infection_slash").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<HallowSlashProjectile>>HALLOW_SLASH_PROJECTILE =
            ENTITIES.register("hallow_slash", () -> EntityType.Builder.<HallowSlashProjectile>of(HallowSlashProjectile::new, MobCategory.MISC)
                    .sized(2.5f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "hallow_slash").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<StaticCleave>> STATIC_CLEAVE =
            ENTITIES.register("static_cleave", () -> EntityType.Builder.<StaticCleave>of(StaticCleave::new, MobCategory.MISC)
                    .sized(3f, 1.5f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "static_cleave").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<SpectralBlastVisualEntity>> SPECTRAL_BLAST_VISUAL_ENTITY =
            ENTITIES.register("spectral_blast", () -> EntityType.Builder.<SpectralBlastVisualEntity>of(SpectralBlastVisualEntity::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "spectral_blast").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<LifeDrainProjectile>> LIFE_DRAIN_PROJECTILE =
            ENTITIES.register("life_drain", () -> EntityType.Builder.<LifeDrainProjectile>of(LifeDrainProjectile::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "life_drain").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<BubbleSprayProjectile>> BUBBLE_SPRAY_PROJECTILE =
            ENTITIES.register("bubble_spray", () -> EntityType.Builder.<BubbleSprayProjectile>of(BubbleSprayProjectile::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "bubble_spray").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<SteamStreamProjectile>> STEAM_STEAM =
            ENTITIES.register("steam_stream", () -> EntityType.Builder.<SteamStreamProjectile>of(SteamStreamProjectile::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "steam_stream").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<WitherBombProjectile>> WITHER_BOMB =
            ENTITIES.register("wither_bomb", () -> EntityType.Builder.<WitherBombProjectile>of(WitherBombProjectile::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(4)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "wither_bomb").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<RazorbladeTyphoonProjectile>> RAZORBLADE_TYPHOON =
            ENTITIES.register("razorblade_typhoon", () -> EntityType.Builder.<RazorbladeTyphoonProjectile>of(RazorbladeTyphoonProjectile::new, MobCategory.MISC)
                    .sized(4f, 1f)
                    .clientTrackingRange(4)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "razorblade_typhoon").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<NapalmOrb>> NAPALM_ORB =
            ENTITIES.register("napalm_orb", () -> EntityType.Builder.<NapalmOrb>of(NapalmOrb::new, MobCategory.MISC)
                    .sized(0.75F, 0.75F)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "napalm_orb").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<PutrescenceField>> PUTRESCENCE_FIELD =
            ENTITIES.register("putrescence_field", () -> EntityType.Builder.<PutrescenceField>of(PutrescenceField::new, MobCategory.MISC)
                    .sized(4f, 1.2f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "putrescence_field").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<BrimstoneField>> BRIMSTONE_FIELD =
            ENTITIES.register("brimstone_field", () -> EntityType.Builder.<BrimstoneField>of(BrimstoneField::new, MobCategory.MISC)
                    .sized(4f, 1.2f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "brimstone_field").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<DistortionField>> DISTORTION_FIELD =
            ENTITIES.register("distortion_field", () -> EntityType.Builder.<DistortionField>of(DistortionField::new, MobCategory.MISC)
                    .sized(4f, 12f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "distortion_field").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<StaticField>> STATIC_FIELD =
            ENTITIES.register("static_field", () -> EntityType.Builder.<StaticField>of(StaticField::new, MobCategory.MISC)
                    .sized(4f, 1.2f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "static_field").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<SoulFireField>> SOUL_FIRE_FIELD =
            ENTITIES.register("soul_fire_field", () -> EntityType.Builder.<SoulFireField>of(SoulFireField::new, MobCategory.MISC)
                    .sized(4f, 1.2f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "soul_fire_field").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<PutrescenceMass>> PUTRESCENCE_MASS =
            ENTITIES.register("putrescence_mass", () -> EntityType.Builder.<PutrescenceMass>of(PutrescenceMass::new, MobCategory.MISC)
                    .sized(0.75F, 0.75F)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "putrescence_mass").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<BlastFungusProjectile>> BLAST_FUNGUS_PROJECTILE =
            ENTITIES.register("blast_fungus_projectile", () -> EntityType.Builder.<BlastFungusProjectile>of(BlastFungusProjectile::new, MobCategory.MISC)
                    .sized(0.75F, 0.75F)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "blast_fungus_projectile").toString()));

    public static final Supplier<EntityType<AegisEntity>>AEGIS=
            ENTITIES.register("aegis", () -> EntityType.Builder.of(AegisEntity::new, MobCategory.MONSTER).
                    sized(.8f, 1.8f).build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "aegis").toString()));
    public static final Supplier<EntityType<DwarvenSphereEntity>>DWARVEN_SPHERE=
            ENTITIES.register("dwarven_sphere",()->EntityType.Builder.of(DwarvenSphereEntity::new,MobCategory.MONSTER)
                    .sized(0.8F, 2.5F).build("dwarven_sphere"));
    public static final Supplier<EntityType<DwarvenSlicerEntity>>DWARVEN_SLICER=
            ENTITIES.register("dwarven_slicer",()->EntityType.Builder.of(DwarvenSlicerEntity::new,MobCategory.MONSTER)
                    .sized(0.6F, 0.50f).build("dwarven_slicer"));
    public static final DeferredHolder<EntityType<?>, EntityType<ShockEntity>> SHOCK =
            ENTITIES.register("shock", () -> EntityType.Builder.<ShockEntity>of(ShockEntity::new, MobCategory.MONSTER).
                    sized(.6f, .80f).build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "shock").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<DripplerEntity>> DRIPPLER =
            ENTITIES.register("drippler", () -> EntityType.Builder.<DripplerEntity>of
                            (DripplerEntity::new, MobCategory.MONSTER).
                    sized(.6f, 1.8f)
                    .build(
                            ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "drippler").toString()
                    ));

    public static final DeferredHolder<EntityType<?>, EntityType<SprigganEntity>> SPRIGGAN =
            ENTITIES.register("spriggan", () -> EntityType.Builder.of(SprigganEntity::new, MobCategory.MONSTER)
                    .sized(.6f, 2.75f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "spriggan").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<IllagerEnchanterEntity>> ILLAGER_ENCHANTER =
            ENTITIES.register("illager_enchanter", () -> EntityType.Builder.of(IllagerEnchanterEntity::new, MobCategory.MONSTER)
                    .sized(.6f, 2.75f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "illager_enchanter").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<IllagerIceologerEntity>> ILLAGER_ICEOLOGER =
            ENTITIES.register("illager_iceologer", () -> EntityType.Builder.of(IllagerIceologerEntity::new, MobCategory.MONSTER)
                    .sized(.6f, 2.75f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "illager_iceologer").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<DraugrEvokerEntity>> DRAUGR_EVOKER=
            ENTITIES.register("draugr_evoker", () -> EntityType.Builder.of(DraugrEvokerEntity::new, MobCategory.MONSTER)
                    .sized(.6f, 2f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "draugr_evoker").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<DraugrVindicatorEntity>> DRAUGR_VINDICATOR=
            ENTITIES.register("draugr_vindicator", () -> EntityType.Builder.of(DraugrVindicatorEntity::new, MobCategory.MONSTER)
                    .sized(.6f, 2f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "draugr_vindicator").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<DraugrEliteVindicatorEntity>> DRAUGR_ELITE_VINDICATOR=
            ENTITIES.register("draugr_elite_vindicator", () -> EntityType.Builder.of(DraugrEliteVindicatorEntity::new, MobCategory.MONSTER)
                    .sized(.6f, 2f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "draugr_elite_vindicator").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<DraugrPillagerEntity>> DRAUGR_PILLAGER=
            ENTITIES.register("draugr_pillager", () -> EntityType.Builder.of(DraugrPillagerEntity::new, MobCategory.MONSTER)
                    .sized(.6f, 2f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "draugr_pillager").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<SoulWizardEntity>> SOUL_WIZARD =
            ENTITIES.register("soul_wizard", () -> EntityType.Builder.<SoulWizardEntity>of
                            (SoulWizardEntity::new, MobCategory.MONSTER).
                    sized(.6f, .8f)
                    .build(
                            ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "soul_wizard").toString()
                    ));

    public static final DeferredHolder<EntityType<?>, EntityType<JungleWhispererEntity>> JUNGLE_WHISPERER =
            ENTITIES.register("jungle_whisperer", () -> EntityType.Builder.<JungleWhispererEntity>of
                            (JungleWhispererEntity::new, MobCategory.MONSTER).
                    sized(.6f, 2.5f)
                    .build(
                            ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "jungle_whisperer").toString()
                    ));

    public static final DeferredHolder<EntityType<?>, EntityType<MagmaAtronachEntity>> MAGMA_ATRONACH =
            ENTITIES.register("magma_atronach", () -> EntityType.Builder.<MagmaAtronachEntity>of
                            (MagmaAtronachEntity::new, MobCategory.MONSTER).
                    sized(1f, 4)
                    .build(
                            ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "magma_atronach").toString()
                    ));

    public static final DeferredHolder<EntityType<?>, EntityType<BlastlingEntity>> BLASTLING =
            ENTITIES.register("blastling", () -> EntityType.Builder.<BlastlingEntity>of
                            (BlastlingEntity::new, MobCategory.MONSTER).
                    sized(.8f, 2.5f)
                    .build(
                            ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "blastling").toString()
                    ));

    public static final DeferredHolder<EntityType<?>, EntityType<PoisonQuillVineEntity>> POISON_QUILL_VINE =
            ENTITIES.register("poison_quill_vine", () -> EntityType.Builder.<PoisonQuillVineEntity>of
                            (PoisonQuillVineEntity::new, MobCategory.MONSTER).
                    sized(.6f, 5.5f)
                    .build(
                            ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "poison_quill_vine").toString()
                    ));

    public static final Supplier<EntityType<VileSkeletonEntity>>VILE_SKELETON=
            ENTITIES.register("vile_skeleton",()->EntityType.Builder.of(VileSkeletonEntity::new,MobCategory.MONSTER)
                    .sized(0.6F, 1.9F).build("vile_skeleton"));


    public static final DeferredHolder<EntityType<?>, EntityType<SummonedAegisEntity>> SUMMONED_AEGIS =
            ENTITIES.register("summoned_aegis", () -> EntityType.Builder.<SummonedAegisEntity>of(SummonedAegisEntity::new, MobCategory.MISC).
                    sized(.8f, 1.8f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "summoned_aegis").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<SummonedVileSkeletonEntity>> SUMMONED_VILE_SKELETON =
            ENTITIES.register("summoned_vile_skeleton", () -> EntityType.Builder.<SummonedVileSkeletonEntity>of(SummonedVileSkeletonEntity::new, MobCategory.MISC)
                    .sized(.6f, 1.9f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "summoned_vile_skeleton").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<DeathKnightEntity>> SUMMONED_DEATH_KNIGHT =
            ENTITIES.register("death_knight", () -> EntityType.Builder.<DeathKnightEntity>of(DeathKnightEntity::new, MobCategory.MISC)
                    .sized(2, 3F)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "death_knight").toString()));



    public static void register(IEventBus eventBus)
    {
        ENTITIES.register(eventBus);
    }
}
