package net.redreaper.monsterspellbooks.init;

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

    public static final Supplier<SoundEvent>BRIMSTONE_WRATH_FIRE =registerSoundEvent("spell_brimstone_fire");
    public static final Supplier<SoundEvent>ENERGY_FIRE=registerSoundEvent("spell_energy_fire");
    public static final Supplier<SoundEvent>GUARDIAN_LASER=registerSoundEvent("spell_guardian_laser_loop");
    public static final Supplier<SoundEvent>SPELL_SCREAM=registerSoundEvent("spell_scream");
    public static final Supplier<SoundEvent>SOUL_SCREAM=registerSoundEvent("spell_soul_scream");
    public static final Supplier<SoundEvent>ENDERSENT_SMASH_CAST =registerSoundEvent("spell_endersent_smash_cast");
    public static final Supplier<SoundEvent>GRAVITY_WAVE =registerSoundEvent("spell_gravity_wave");
    public static final Supplier<SoundEvent>GRAVEYARD_FISSURE =registerSoundEvent("spell_graveyard_fissure");
    public static final Supplier<SoundEvent>SOUL_WIZARD_SUMMON =registerSoundEvent("spell_soul_wizard_summon");

    public static DeferredHolder<SoundEvent,SoundEvent>BRIMSTONE_BUZZSAW_SHOT =registerSoundEvent("spell_brimstone_sawblade_shot");
    public static DeferredHolder<SoundEvent,SoundEvent>BRIMSTONE_BUZZSAW_HIT =registerSoundEvent("spell_brimstone_sawblade_hit");
    public static DeferredHolder<SoundEvent, SoundEvent> NECRO_CAST = registerSoundEvent("spell_necro_cast");

    public static final Supplier<SoundEvent>WHISPERER_AMBIENT =registerSoundEvent("whisperer_ambient");
    public static final Supplier<SoundEvent>WHISPERER_MELEE_ATTACK =registerSoundEvent("whisperer_melee_attack");
    public static final Supplier<SoundEvent>WHISPERER_DEATH =registerSoundEvent("whisperer_death");
    public static final Supplier<SoundEvent>SOUL_WIZARD_HURT =registerSoundEvent("soul_wizard_hurt");



    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, name)));
    }

    public static void register (IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }
}
