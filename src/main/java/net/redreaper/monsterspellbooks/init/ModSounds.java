package net.redreaper.monsterspellbooks.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent>SOUND_EVENTS=
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, MonstersSpellbooks.MOD_ID);

    public static final Supplier<SoundEvent>BRIMSTONE_FIRE=registerSoundEvent("brimstone_fire");
    public static final Supplier<SoundEvent>ENERGY_FIRE=registerSoundEvent("energy_fire");


    public static final Supplier<SoundEvent>NECRO_CAST=registerSoundEvent("necro_cast");


    private static Supplier<SoundEvent>registerSoundEvent(String name){
        ResourceLocation id =ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID,name);
        return SOUND_EVENTS.register(name,()->SoundEvent.createVariableRangeEvent(id));
    }

    public static void register (IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }
}
