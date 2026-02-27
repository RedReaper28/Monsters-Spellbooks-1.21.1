package net.redreaper.monsterspellbooks.init;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.entity.spells.devour_jaw.DevourJaw;
import io.redspace.ironsspellbooks.entity.spells.fireball.MagicFireball;
import io.redspace.ironsspellbooks.entity.spells.firebolt.FireboltProjectile;
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
import net.redreaper.monsterspellbooks.entity.spells.blizzard_aspect.PowderSnowSplash;
import net.redreaper.monsterspellbooks.entity.spells.blizzard_aspect.SnowCloud;
import net.redreaper.monsterspellbooks.entity.spells.blood_pierce_bullet.BloodPierceVisualEntity;
import net.redreaper.monsterspellbooks.entity.spells.blood_thorn.BloodThornProjectile;
import net.redreaper.monsterspellbooks.entity.spells.bone_dagger.BoneDaggerProjectile;
import net.redreaper.monsterspellbooks.entity.spells.brimstone_buzzsaw.BrimstoneBuzzsawProjectile;
import net.redreaper.monsterspellbooks.entity.spells.brimstone_rain.BrimstoneField;
import net.redreaper.monsterspellbooks.entity.spells.brimstone_rain.SmallBrimstoneFireball;
import net.redreaper.monsterspellbooks.entity.spells.cauterizing_touch.CauterizingTouch;
import net.redreaper.monsterspellbooks.entity.spells.dragon_charge.DragonChargeProjectile;
import net.redreaper.monsterspellbooks.entity.spells.frenzied_burst.FrenziedBurstVisualEntity;
import net.redreaper.monsterspellbooks.entity.spells.frenzied_storm.SmallFrenzyFireBall;
import net.redreaper.monsterspellbooks.entity.spells.graveyard_fissure.GraveyardHand;
import net.redreaper.monsterspellbooks.entity.spells.guardians_neutralizer.GuardiansNeutralizerVisualEntity;
import net.redreaper.monsterspellbooks.entity.spells.hallow_slash.HallowSlashProjectile;
import net.redreaper.monsterspellbooks.entity.spells.ice_arsenal.IceArsenalSword;
import net.redreaper.monsterspellbooks.entity.spells.life_drain.LifeDrainProjectile;
import net.redreaper.monsterspellbooks.entity.spells.napalm_orb.NapalmOrb;
import net.redreaper.monsterspellbooks.entity.spells.paladin_throw.HolyHammerProjectile;
import net.redreaper.monsterspellbooks.entity.spells.poison_quill.PoisonQuillProjectile;
import net.redreaper.monsterspellbooks.entity.spells.putrescence_mass.PutrescenceField;
import net.redreaper.monsterspellbooks.entity.spells.putrescence_mass.PutrescenceMass;
import net.redreaper.monsterspellbooks.entity.spells.raigo.RaigoProjectile;
import net.redreaper.monsterspellbooks.entity.spells.rancorcall.RancorSkull;
import net.redreaper.monsterspellbooks.entity.spells.sangunite_eviceration.SanguiniteEvisceration;
import net.redreaper.monsterspellbooks.entity.spells.soul_chain.SoulChain;
import net.redreaper.monsterspellbooks.entity.spells.soul_firebolt.SoulFireBoltProjectile;
import net.redreaper.monsterspellbooks.entity.spells.soul_scorch.SoulFireField;
import net.redreaper.monsterspellbooks.entity.spells.space_rupture.DistortionField;
import net.redreaper.monsterspellbooks.entity.spells.space_rupture.SpaceRupture;
import net.redreaper.monsterspellbooks.entity.spells.spectral_blast.SpectralBlastVisualEntity;
import net.redreaper.monsterspellbooks.entity.spells.static_cleave.StaticCleave;
import net.redreaper.monsterspellbooks.entity.spells.steam_stream.SteamStreamProjectile;
import net.redreaper.monsterspellbooks.entity.spells.stray_grasp.StrayGraspProjectile;
import net.redreaper.monsterspellbooks.entity.spells.thunderstorm_wave.AncientLightningStrike;
import net.redreaper.monsterspellbooks.entity.spells.vile_slash.VileSlashProjectile;
import net.redreaper.monsterspellbooks.entity.spells.wither_bomb.WitherBombProjectile;
import net.redreaper.monsterspellbooks.entity.spells.wither_nova.WitherNovaVisualEntity;

import java.util.function.Supplier;

import static net.minecraft.core.registries.Registries.ENTITY_TYPE;

public class ModEntities {
    private static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ENTITY_TYPE, MonstersSpellbooks.MOD_ID);

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

    public static final DeferredHolder<EntityType<?>, EntityType<HolyHammerProjectile>> HOLY_HAMMER =
            ENTITIES.register("holy_hammer", () -> EntityType.Builder.<HolyHammerProjectile>of(HolyHammerProjectile::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "holy_hammer").toString()));

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

    public static final DeferredHolder<EntityType<?>, EntityType<VileSlashProjectile>> VILE_SLASH_PROJECTILE =
            ENTITIES.register("vile_slash", () -> EntityType.Builder.<VileSlashProjectile>of(VileSlashProjectile::new, MobCategory.MISC)
                    .sized(2f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "vile_slash").toString()));

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
