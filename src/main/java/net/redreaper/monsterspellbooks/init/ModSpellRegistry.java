package net.redreaper.monsterspellbooks.init;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.spells.ender.EndersentSmashSpell;
import net.redreaper.monsterspellbooks.spells.ender.GravityWellSpell;
import net.redreaper.monsterspellbooks.spells.fire.CauterizingTouchSpell;
import net.redreaper.monsterspellbooks.spells.fire.FrenziedBurstSpell;
import net.redreaper.monsterspellbooks.spells.fire.OverheatSpell;
import net.redreaper.monsterspellbooks.spells.lightning.AncientFlashSpell;
import net.redreaper.monsterspellbooks.spells.nature.BeastHowlSpell;
import net.redreaper.monsterspellbooks.spells.necro.BansheeScreamSpell;
import net.redreaper.monsterspellbooks.spells.necro.ReaperAspectSpell;
import net.redreaper.monsterspellbooks.spells.necro.VileSlashSpell;

import java.util.function.Supplier;

import static io.redspace.ironsspellbooks.api.registry.SpellRegistry.SPELL_REGISTRY_KEY;

public class ModSpellRegistry {
    public static final DeferredRegister<AbstractSpell> SPELLS = DeferredRegister.create(SPELL_REGISTRY_KEY, MonstersSpellbooks.MOD_ID);


    public static Supplier<AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }

    //ENDER
    public static final Supplier<AbstractSpell> ENDERSENT_SMASH = registerSpell(new EndersentSmashSpell());
    public static final Supplier<AbstractSpell> GRAVITY_WELL = registerSpell(new GravityWellSpell());


    //FIRE
    public static final Supplier<AbstractSpell> CAUTERIZING_TOUCH = registerSpell(new CauterizingTouchSpell());
    public static final Supplier<AbstractSpell> FRENZIED_BURST = registerSpell(new FrenziedBurstSpell());
    public static final Supplier<AbstractSpell> OVERHEAT = registerSpell(new OverheatSpell());

    //LIGHTING
    public static final Supplier<AbstractSpell> ANCIENT_FLASH = registerSpell(new AncientFlashSpell());

    //NATURE
    public static final Supplier<AbstractSpell> BEAST_HOWL = registerSpell(new BeastHowlSpell());

    //NECRO
    public static final Supplier<AbstractSpell> BANSHEE_SCREAM = registerSpell(new BansheeScreamSpell());
    public static final Supplier<AbstractSpell> REAPER_ASPECT = registerSpell(new ReaperAspectSpell());
    public static final Supplier<AbstractSpell> VILE_SLASH = registerSpell(new VileSlashSpell());


    public static void register(IEventBus eventBus)
    {
        SPELLS.register(eventBus);
    }
}
