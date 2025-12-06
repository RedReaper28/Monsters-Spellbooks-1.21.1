package net.redreaper.monsterspellbooks.init;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;

import java.util.function.Supplier;

import static io.redspace.ironsspellbooks.api.registry.SpellRegistry.SPELL_REGISTRY_KEY;

public class ModSpellRegistry {
    public static final DeferredRegister<AbstractSpell> SPELLS = DeferredRegister.create(SPELL_REGISTRY_KEY, MonstersSpellbooks.MOD_ID);




    public static void register(IEventBus eventBus)
    {
        SPELLS.register(eventBus);
    }
}
