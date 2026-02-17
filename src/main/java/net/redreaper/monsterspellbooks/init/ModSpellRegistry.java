package net.redreaper.monsterspellbooks.init;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.spells.poison_quill.PoisonQuillProjectile;
import net.redreaper.monsterspellbooks.spells.aero.AirBubbleSpell;
import net.redreaper.monsterspellbooks.spells.aero.AirPropulsionSpell;
import net.redreaper.monsterspellbooks.spells.aero.SteamSteamSpell;
import net.redreaper.monsterspellbooks.spells.aero.SuffocateSpell;
import net.redreaper.monsterspellbooks.spells.blood.*;
import net.redreaper.monsterspellbooks.spells.ender.*;
import net.redreaper.monsterspellbooks.spells.fire.*;
import net.redreaper.monsterspellbooks.spells.holy.AegisPatrolSpell;
import net.redreaper.monsterspellbooks.spells.holy.DivineInterventionSpell;
import net.redreaper.monsterspellbooks.spells.holy.PaladinThrowSpell;
import net.redreaper.monsterspellbooks.spells.ice.BlizzardAspectSpell;
import net.redreaper.monsterspellbooks.spells.ice.FrostCoatingSpell;
import net.redreaper.monsterspellbooks.spells.ice.IceArsenalSpell;
import net.redreaper.monsterspellbooks.spells.ice.TundraTerrainSpell;
import net.redreaper.monsterspellbooks.spells.lightning.*;
import net.redreaper.monsterspellbooks.spells.nature.BeastHowlSpell;
import net.redreaper.monsterspellbooks.spells.nature.MountainCorpseSpell;
import net.redreaper.monsterspellbooks.spells.nature.PoisonQuillSpell;
import net.redreaper.monsterspellbooks.spells.nature.SummonPoisonVinesSpell;
import net.redreaper.monsterspellbooks.spells.necro.*;

import java.util.function.Supplier;

import static io.redspace.ironsspellbooks.api.registry.SpellRegistry.SPELL_REGISTRY_KEY;

public class ModSpellRegistry {
    public static final DeferredRegister<AbstractSpell> SPELLS = DeferredRegister.create(SPELL_REGISTRY_KEY, MonstersSpellbooks.MOD_ID);


    public static Supplier<AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }
    //AERO
    public static final Supplier<AbstractSpell> AIR_BUBBLE = registerSpell(new AirBubbleSpell());
    public static final Supplier<AbstractSpell> AIR_PROPULSION = registerSpell(new AirPropulsionSpell());
    public static final Supplier<AbstractSpell> STEAM_STREAM = registerSpell(new SteamSteamSpell());
    public static final Supplier<AbstractSpell> SUFFOCATE = registerSpell(new SuffocateSpell());

    //BLOOD
    public static final Supplier<AbstractSpell> ANTICOAGULATION = registerSpell(new AnticoagulationSpell());
    public static final Supplier<AbstractSpell> BLOOD_PIERCE = registerSpell(new BloodPierceSpell());
    public static final Supplier<AbstractSpell> BLOOD_THORN = registerSpell(new BloodThornSpell());
    public static final Supplier<AbstractSpell> HYSTERIA = registerSpell(new HysteriaSpell());
    public static final Supplier<AbstractSpell> SANGUINITE_EVISCERATION = registerSpell(new SanguiniteEviscerationSpell());

    //ENDER
    public static final Supplier<AbstractSpell> CRUSH = registerSpell(new CrushSpell());
    public static final Supplier<AbstractSpell> CRUSHING_PRESENCE = registerSpell(new CrushingPresenceSpell());
    public static final Supplier<AbstractSpell> ENDERSENT_SMASH = registerSpell(new EndersentSmashSpell());
    public static final Supplier<AbstractSpell> GRAVITY_FORCE = registerSpell(new GravityForceSpell());
    public static final Supplier<AbstractSpell> GRAVITY_WELL = registerSpell(new GravityWellSpell());
    public static final Supplier<AbstractSpell> SPACE_RUPTURE = registerSpell(new SpaceRuptureSpell());

    //FIRE
    public static final Supplier<AbstractSpell> BRIMSTONE_BUZZSAW = registerSpell(new BrimstoneBuzzsawSpell());
    public static final Supplier<AbstractSpell> BRIMSTONE_WRATH = registerSpell(new BrimstoneWrathSpell());
    public static final Supplier<AbstractSpell> CAUTERIZING_TOUCH = registerSpell(new CauterizingTouchSpell());
    public static final Supplier<AbstractSpell> FRENZIED_BURST = registerSpell(new FrenziedBurstSpell());
    public static final Supplier<AbstractSpell> FRENZIED_STORM = registerSpell(new FrenziedStormSpell());
    public static final Supplier<AbstractSpell> FRENZY_SURGE = registerSpell(new FrenzySurgeSpell());
    public static final Supplier<AbstractSpell> NAPALM_ORB = registerSpell(new NapalmOrbSpell());
    public static final Supplier<AbstractSpell> OVERHEAT = registerSpell(new OverheatSpell());

    //HOLY
    public static final Supplier<AbstractSpell> AEGIS_PATROL = registerSpell(new AegisPatrolSpell());
    public static final Supplier<AbstractSpell> DIVINE_INTERVENTION = registerSpell(new DivineInterventionSpell());
    public static final Supplier<AbstractSpell> PALADIN_THROW = registerSpell(new PaladinThrowSpell());

    //ICE
    public static final Supplier<AbstractSpell> BLIZZARD_ASPECT = registerSpell(new BlizzardAspectSpell());
    public static final Supplier<AbstractSpell> FROST_COATING = registerSpell(new FrostCoatingSpell());
    public static final Supplier<AbstractSpell> ICE_ARSENAL = registerSpell(new IceArsenalSpell());
    public static final Supplier<AbstractSpell> TUNDRA_TERRAIN = registerSpell(new TundraTerrainSpell());

    //LIGHTING
    public static final Supplier<AbstractSpell> ANCIENT_FLASH = registerSpell(new AncientFlashSpell());
    public static final Supplier<AbstractSpell> ANCIENT_LANCE = registerSpell(new AncientLightningLanceSpell());
    public static final Supplier<AbstractSpell> GUARDIANS_NEUTRALIZER = registerSpell(new GuardiansNeutralizerSpell());
    public static final Supplier<AbstractSpell> RAIGO = registerSpell(new RaigoSpell());
    public static final Supplier<AbstractSpell> STATIC_CLEAVE = registerSpell(new StaticCleaveSpell());
    public static final Supplier<AbstractSpell> THUNDER_STEP = registerSpell(new ThunderStepSpell());
    public static final Supplier<AbstractSpell> THUNDERSTORM_WAVE = registerSpell(new ThunderstormWaveSpell());

    //NATURE
    public static final Supplier<AbstractSpell> BEAST_HOWL = registerSpell(new BeastHowlSpell());
    public static final Supplier<AbstractSpell> MOUNTAIN_CORPSE = registerSpell(new MountainCorpseSpell());
    public static final Supplier<AbstractSpell> POISON_QUILL = registerSpell(new PoisonQuillSpell());
    public static final Supplier<AbstractSpell> SUMMON_POISON_VINE = registerSpell(new SummonPoisonVinesSpell());

    //NECRO
    public static final Supplier<AbstractSpell> REAPER_ASPECT = registerSpell(new ReaperAspectSpell());
    public static final Supplier<AbstractSpell> BANSHEE_SCREAM = registerSpell(new BansheeScreamSpell());
    public static final Supplier<AbstractSpell> BONE_DAGGER = registerSpell(new BoneDaggerSpell());
    public static final Supplier<AbstractSpell> SUMMON_DEATH_KNIGHT = registerSpell(new ConjureDeathKnightSpell());
    public static final Supplier<AbstractSpell> FALL_CURSE = registerSpell(new FallCurseSpell());
    public static final Supplier<AbstractSpell> GRAVEYARD_FISSURE = registerSpell(new GraveyardFissureSpell());
    public static final Supplier<AbstractSpell> LICHDOM = registerSpell(new LichdomSpell());
    public static final Supplier<AbstractSpell> LIFE_DRAIN = registerSpell(new LifeDrainSpell());
    public static final Supplier<AbstractSpell> PUTRESCENCE_MASS = registerSpell(new PutrescenceMassSpell());
    public static final Supplier<AbstractSpell> RANCORCALL = registerSpell(new RancorCallSpell());
    public static final Supplier<AbstractSpell> SOUL_CHAIN = registerSpell(new SoulChainSpell());
    public static final Supplier<AbstractSpell> SOUL_FIREBOLT = registerSpell(new SoulFireBoltSpell());
    public static final Supplier<AbstractSpell> SOUL_FORM = registerSpell(new SoulFormSpell());
    public static final Supplier<AbstractSpell> SOUL_SCORCH = registerSpell(new SoulScorchSpell());
    public static final Supplier<AbstractSpell> SPECTRAL_BLAST = registerSpell(new SpectralBlastSpell());
    public static final Supplier<AbstractSpell> SPIRIT_STRIKE = registerSpell(new SpiritStrikeSpell());
    public static final Supplier<AbstractSpell> STRAY_GRASP = registerSpell(new StrayGraspSpell());
    public static final Supplier<AbstractSpell> SUMMON_SOUL_WIZARD = registerSpell(new SummonSoulWizardSpell());
    public static final Supplier<AbstractSpell> VILE_SLASH = registerSpell(new VileSlashSpell());
    public static final Supplier<AbstractSpell> VILE_SUMMON = registerSpell(new VileSummonSpell());
    public static final Supplier<AbstractSpell> WITHER_BOMB = registerSpell(new WitherBombSpell());
    public static final Supplier<AbstractSpell> WITHER_NOVA = registerSpell(new WitherNovaSpell());

    public static void register(IEventBus eventBus)
    {
        SPELLS.register(eventBus);
    }
}
