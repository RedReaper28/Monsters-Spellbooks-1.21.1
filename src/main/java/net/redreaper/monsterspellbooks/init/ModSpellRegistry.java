package net.redreaper.monsterspellbooks.init;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.spells.ender.EndersentSmashSpell;
import net.redreaper.monsterspellbooks.spells.ender.GravityWellSpell;
import net.redreaper.monsterspellbooks.spells.fire.OverheatSpell;
import net.redreaper.monsterspellbooks.spells.lightning.AncientFlashSpell;
import net.redreaper.monsterspellbooks.spells.necro.BansheeScreamSpell;

import java.util.function.Supplier;

import static io.redspace.ironsspellbooks.api.registry.SpellRegistry.SPELL_REGISTRY_KEY;

public class ModSpellRegistry {
    public static final DeferredRegister<AbstractSpell> SPELLS = DeferredRegister.create(SPELL_REGISTRY_KEY, MonstersSpellbooks.MOD_ID);


    public static Supplier<AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }

    //ENDER
    public static final Supplier<AbstractSpell> GRAVITY_WELL = registerSpell(new GravityWellSpell());
    public static final Supplier<AbstractSpell> ENDERSENT_SMASH = registerSpell(new EndersentSmashSpell());

    //FIRE
    public static final Supplier<AbstractSpell> OVERHEAT = registerSpell(new OverheatSpell());


    //LIGHTING
    public static final Supplier<AbstractSpell> ANCIENT_FLASH = registerSpell(new AncientFlashSpell());

    //NECRO
    public static final Supplier<AbstractSpell> BANSHEE_SCREAM = registerSpell(new BansheeScreamSpell());





    public static void register(IEventBus eventBus)
    {
        SPELLS.register(eventBus);
    }
}
