package net.redreaper.monsterspellbooks.init;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.spells.blood.AnticoagulationSpell;
import net.redreaper.monsterspellbooks.spells.blood.BloodPierceSpell;
import net.redreaper.monsterspellbooks.spells.blood.HysteriaSpell;
import net.redreaper.monsterspellbooks.spells.blood.SanguiniteEviscerationSpell;
import net.redreaper.monsterspellbooks.spells.ender.*;
import net.redreaper.monsterspellbooks.spells.fire.*;
import net.redreaper.monsterspellbooks.spells.holy.AegisPatrolSpell;
import net.redreaper.monsterspellbooks.spells.holy.DivineInterventionSpell;
import net.redreaper.monsterspellbooks.spells.ice.BlizzardAspectSpell;
import net.redreaper.monsterspellbooks.spells.ice.FrostCoatingSpell;
import net.redreaper.monsterspellbooks.spells.ice.IceArsenalSpell;
import net.redreaper.monsterspellbooks.spells.ice.TundraTerrainSpell;
import net.redreaper.monsterspellbooks.spells.lightning.AncientFlashSpell;
import net.redreaper.monsterspellbooks.spells.lightning.StaticCleaveSpell;
import net.redreaper.monsterspellbooks.spells.nature.BeastHowlSpell;
import net.redreaper.monsterspellbooks.spells.nature.MountainCorpseSpell;
import net.redreaper.monsterspellbooks.spells.necro.*;

import java.util.function.Supplier;

import static io.redspace.ironsspellbooks.api.registry.SpellRegistry.SPELL_REGISTRY_KEY;

public class ModSpellRegistry {
    public static final DeferredRegister<AbstractSpell> SPELLS = DeferredRegister.create(SPELL_REGISTRY_KEY, MonstersSpellbooks.MOD_ID);


    public static Supplier<AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }

    //BLOOD
    //In a radius around a targeted creature,apply the Anti Heal Effect to enemies,preventing them from healing in any way
    public static final Supplier<AbstractSpell> ANTICOAGULATION = registerSpell(new AnticoagulationSpell());
    // Shot a concentrated beam of piercing blood from your blood moon scythe to deal massive damage and inflict rend on the target
    public static final Supplier<AbstractSpell> BLOOD_PIERCE = registerSpell(new BloodPierceSpell());
    //Go all in,either you die or your opponent does,increases your attack damage and speed and inflicts yourself with Heartstop
    public static final Supplier<AbstractSpell> HYSTERIA = registerSpell(new HysteriaSpell());
    //Slash forward with your claws to inflict heavy bleeding.The damage scales off of your held weapon´s melee damage
    public static final Supplier<AbstractSpell> SANGUINITE_EVISCERATION = registerSpell(new SanguiniteEviscerationSpell());

    //ENDER
    //Crush your target with a powerful burst of gravitational force to force them to stay on the ground
    public static final Supplier<AbstractSpell> CRUSH = registerSpell(new CrushSpell());
    //Manifest your aura into a crushing aura that slows enemies movement and casting speed just by being near them
    public static final Supplier<AbstractSpell> CRUSHING_PRESENCE = registerSpell(new CrushingPresenceSpell());
    //Smash the ground with the force of an Endersent,making targets susceptible to kinetic damage
    public static final Supplier<AbstractSpell> ENDERSENT_SMASH = registerSpell(new EndersentSmashSpell());
    //Imbue yourself with the power of gravity,increasing your attack damage and speed
    public static final Supplier<AbstractSpell> GRAVITY_FORCE = registerSpell(new GravityForceSpell());
    //Stab the ground and pull enemies towards you,stunning them for a short time
    public static final Supplier<AbstractSpell> GRAVITY_WELL = registerSpell(new GravityWellSpell());

    public static final Supplier<AbstractSpell> SPACE_RUPTURE = registerSpell(new SpaceRuptureSpell());


    //FIRE
    //Launch a searing buzzsaw made of brimstone flame that melts the defense of the enemies it pierces and bouncing off blocks
    public static final Supplier<AbstractSpell> BRIMSTONE_BUZZSAW = registerSpell(new BrimstoneBuzzsawSpell());
    //Burn your targets skin and soul making it impossible for them to heal in any way.The damage scales off of your held weapon´s melee damage
    public static final Supplier<AbstractSpell> CAUTERIZING_TOUCH = registerSpell(new CauterizingTouchSpell());
    //Shot a thin burst of pure frenzy flame,inflicting madness to lower your targets maximum mana reserves
    public static final Supplier<AbstractSpell> FRENZIED_BURST = registerSpell(new FrenziedBurstSpell());
    //Throw a orb of napalm,splashing in a radius upon impact and covering creatures hit,making their bodies flammable and weak to fire magic
    public static final Supplier<AbstractSpell> NAPALM_ORB = registerSpell(new NapalmOrbSpell());
    //Overheat yourself to gain extra damage and fire spell power at the cost of melting part of your own armor
    public static final Supplier<AbstractSpell> OVERHEAT = registerSpell(new OverheatSpell());

    //HOLY
    public static final Supplier<AbstractSpell> AEGIS_PATROL = registerSpell(new AegisPatrolSpell());
    public static final Supplier<AbstractSpell> DIVINE_INTERVENTION = registerSpell(new DivineInterventionSpell());

    //ICE
    //Cast to conjure a blizzard centered around yourself,which strikes nearby creatures within sight with small clouds of frost every 2 seconds for the spell´s duration
    public static final Supplier<AbstractSpell> BLIZZARD_ASPECT = registerSpell(new BlizzardAspectSpell());
    //The great power of ice infuses itself into your skin granting armor for a duration
    public static final Supplier<AbstractSpell> FROST_COATING = registerSpell(new FrostCoatingSpell());
    //Fire a single burst of ice sword to your opponent.Level increases sword number
    public static final Supplier<AbstractSpell> ICE_ARSENAL = registerSpell(new IceArsenalSpell());
    //Conjure protective rings of Ice Spikes around you
    public static final Supplier<AbstractSpell> TUNDRA_TERRAIN = registerSpell(new TundraTerrainSpell());


    //LIGHTING
    //Boost your strike with ancient lighting to strike your target multiple times in a single cast.The damage scales off of your held weapon´s melee damage
    public static final Supplier<AbstractSpell> ANCIENT_FLASH = registerSpell(new AncientFlashSpell());
    public static final Supplier<AbstractSpell> STATIC_CLEAVE = registerSpell(new StaticCleaveSpell());


    //NATURE
    //Release a damaging howl that leaves your targets paralyzed and stunned while you gain a primordial strength
    public static final Supplier<AbstractSpell> BEAST_HOWL = registerSpell(new BeastHowlSpell());
    //Increases your size and stats giving the extra damage and knockback on your attacks, but you are more susceptible to gravity and you move slower
    public static final Supplier<AbstractSpell> MOUNTAIN_CORPSE = registerSpell(new MountainCorpseSpell());

    //NECRO
    // Instill yourself with necrotic magic,dealing additional melee,ranged,or spell damage towards creatures that are withering
    public static final Supplier<AbstractSpell> REAPER_ASPECT = registerSpell(new ReaperAspectSpell());
    //Release a horrible scream that wither any life that hears it
    public static final Supplier<AbstractSpell> BANSHEE_SCREAM = registerSpell(new BansheeScreamSpell());
    //Summons the once elite guards of the Withered Fortress,imposing creatures that obey their master
    public static final Supplier<AbstractSpell> SUMMON_DEATH_KNIGHT = registerSpell(new ConjureDeathKnightSpell());
    //A curse long forgotten than reduces your target to a defenseless state in many ways
    public static final Supplier<AbstractSpell> FALL_CURSE = registerSpell(new FallCurseSpell());

    public static final Supplier<AbstractSpell> LICHDOM = registerSpell(new LichdomSpell());

    //Absorb the life force of your target,making them weak while healing you for 100% of the damage done
    public static final Supplier<AbstractSpell> LIFE_DRAIN = registerSpell(new LifeDrainSpell());
    //Launch a mass made from putrescence ,dissolving on impact and leaving a field of fire where it lands
    public static final Supplier<AbstractSpell> PUTRESCENCE_MASS = registerSpell(new PutrescenceMassSpell());

    public static final Supplier<AbstractSpell> SOUL_CHAIN = registerSpell(new SoulChainSpell());


    public static final Supplier<AbstractSpell> SOUL_FORM = registerSpell(new SoulFormSpell());

    //Fires multiple blasts of spectral energy that builds up the Lethargy effect.Land all shots at max level to inflict a health decreasing effect.Level increases recasts
    public static final Supplier<AbstractSpell> SPECTRAL_BLAST = registerSpell(new SpectralBlastSpell());

    public static final Supplier<AbstractSpell>SPIRIT_STRIKE = registerSpell(new SpiritStrikeSpell());


    //Slash forward to send out a concentrated blade of spectral energy,slicing through creatures to wither their soul and healing 15% of damage done
    public static final Supplier<AbstractSpell> VILE_SLASH = registerSpell(new VileSlashSpell());
    //Summon a ravenous horde of Vile Skeletons that attack anything that isn't their caster,striping their armor and withering their flesh
    public static final Supplier<AbstractSpell> VILE_SUMMON = registerSpell(new VileSummonSpell());
    //Fire an enhanced wither skull which home towards your cursor,or the creature your cursor is targeting,exploding and dealing massive damage on impact
    public static final Supplier<AbstractSpell> WITHER_BOMB = registerSpell(new WitherBombSpell());




    public static void register(IEventBus eventBus)
    {
        SPELLS.register(eventBus);
    }
}
