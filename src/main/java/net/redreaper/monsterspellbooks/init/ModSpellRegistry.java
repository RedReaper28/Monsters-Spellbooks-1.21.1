package net.redreaper.monsterspellbooks.init;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.spells.blood.SanguiniteEviscerationSpell;
import net.redreaper.monsterspellbooks.spells.ender.EndersentSmashSpell;
import net.redreaper.monsterspellbooks.spells.ender.GravityWellSpell;
import net.redreaper.monsterspellbooks.spells.fire.CauterizingTouchSpell;
import net.redreaper.monsterspellbooks.spells.fire.FrenziedBurstSpell;
import net.redreaper.monsterspellbooks.spells.fire.OverheatSpell;
import net.redreaper.monsterspellbooks.spells.ice.IceArsenalSpell;
import net.redreaper.monsterspellbooks.spells.lightning.AncientFlashSpell;
import net.redreaper.monsterspellbooks.spells.nature.BeastHowlSpell;
import net.redreaper.monsterspellbooks.spells.necro.BansheeScreamSpell;
import net.redreaper.monsterspellbooks.spells.necro.LifeDrainSpell;
import net.redreaper.monsterspellbooks.spells.necro.ReaperAspectSpell;
import net.redreaper.monsterspellbooks.spells.necro.VileSlashSpell;

import java.util.function.Supplier;

import static io.redspace.ironsspellbooks.api.registry.SpellRegistry.SPELL_REGISTRY_KEY;

public class ModSpellRegistry {
    public static final DeferredRegister<AbstractSpell> SPELLS = DeferredRegister.create(SPELL_REGISTRY_KEY, MonstersSpellbooks.MOD_ID);


    public static Supplier<AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }

    //BLOOD
    //Slash forward with your claws to inflict heavy bleeding.The damage scales off of your held weapon´s melee damage
    public static final Supplier<AbstractSpell> SANGUINITE_EVISCERATION = registerSpell(new SanguiniteEviscerationSpell());

    //ENDER
    //Smash the ground with the force of an Endersent,making targets susceptible to kinetic damage
    public static final Supplier<AbstractSpell> ENDERSENT_SMASH = registerSpell(new EndersentSmashSpell());
    //Stab the ground and pull enemies towards you,stunning them for a short time
    public static final Supplier<AbstractSpell> GRAVITY_WELL = registerSpell(new GravityWellSpell());


    //FIRE
    //Burn your targets skin and soul making it impossible for them to heal in any way.The damage scales off of your held weapon´s melee damage
    public static final Supplier<AbstractSpell> CAUTERIZING_TOUCH = registerSpell(new CauterizingTouchSpell());
    //Shot a thin burst of pure frenzy flame,inflicting madness to lower your targets maximum mana reserves
    public static final Supplier<AbstractSpell> FRENZIED_BURST = registerSpell(new FrenziedBurstSpell());
    //Overheat yourself to gain extra damage and fire spell power at the cost of melting part of your own armor
    public static final Supplier<AbstractSpell> OVERHEAT = registerSpell(new OverheatSpell());

    //ICE
    //Fire a single burst of ice sword to your opponent.Level increases sword number
    public static final Supplier<AbstractSpell> ICE_ARSENAL = registerSpell(new IceArsenalSpell());

    //LIGHTING
    //Boost your strike with ancient lighting to strike your target multiple times in a single cast.The damage scales off of your held weapon´s melee damage
    public static final Supplier<AbstractSpell> ANCIENT_FLASH = registerSpell(new AncientFlashSpell());

    //NATURE
    //Release a damaging howl that leaves your targets paralyzed and stunned while you gain a primordial strength
    public static final Supplier<AbstractSpell> BEAST_HOWL = registerSpell(new BeastHowlSpell());

    //NECRO
    //Release a horrible scream that wither any life that hears it
    public static final Supplier<AbstractSpell> BANSHEE_SCREAM = registerSpell(new BansheeScreamSpell());
    //Instill yourself with necrotic magic,dealing additional melee,ranged,or spell damage towards creatures that are withering
    public static final Supplier<AbstractSpell> REAPER_ASPECT = registerSpell(new ReaperAspectSpell());
    //Slash forward to send out a concentrated blade of spectral energy,slicing through creatures to wither their body and soul and healing 15% of damage done
    public static final Supplier<AbstractSpell> VILE_SLASH = registerSpell(new VileSlashSpell());
    //Absorb the life force of your target,making them weak while you healing you for 100% of the damage done
    public static final Supplier<AbstractSpell> LIFE_DRAIN = registerSpell(new LifeDrainSpell());



    public static void register(IEventBus eventBus)
    {
        SPELLS.register(eventBus);
    }
}
