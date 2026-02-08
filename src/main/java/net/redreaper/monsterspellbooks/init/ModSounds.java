package net.redreaper.monsterspellbooks.init;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;

import java.util.function.Supplier;

public class ModSounds {


    public static final DeferredRegister<SoundEvent>SOUND_EVENTS=
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, MonstersSpellbooks.MOD_ID);

    public static final Supplier<SoundEvent>BRIMSTONE_FIRE=registerSoundEvent("brimstone_fire");
    public static final Supplier<SoundEvent>ENERGY_FIRE=registerSoundEvent("energy_fire");
    public static final Supplier<SoundEvent>SPELL_SCREAM=registerSoundEvent("spell_scream");
    public static final Supplier<SoundEvent>ENDERSENT_SMAsH_CAST=registerSoundEvent("endersent_smash_cast");


    public static DeferredHolder<SoundEvent, SoundEvent> NECRO_CAST = registerSoundEvent("necro_cast");


    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, name)));
    }

    public static void register (IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }
}
