package net.redreaper.monsterspellbooks.init;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;

import java.util.function.Supplier;

public class ModParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, MonstersSpellbooks.MOD_ID);

    public static final Supplier<SimpleParticleType> ANCIENT_SPARKS_PARTICLE = PARTICLE_TYPES.register("ancient_sparks", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> REAPER_FIRE_PARTICLE = PARTICLE_TYPES.register("reaper_fire", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> REAPER_EMBERS_PARTICLE = PARTICLE_TYPES.register("reaper_embers", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> FRENZY_FIRE_PARTICLE = PARTICLE_TYPES.register("frenzy_fire", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> FRENZY_EMBERS_PARTICLE = PARTICLE_TYPES.register("frenzy_embers", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> BRIMSTONE_FIRE_PARTICLE = PARTICLE_TYPES.register("brimstone_fire", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> BRIMSTONE_EMBERS_PARTICLE = PARTICLE_TYPES.register("brimstone_embers", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> PUTRESCENCE_BUBBLE_PARTICLE = PARTICLE_TYPES.register("putrescence_bubble", () -> new SimpleParticleType(false));




    public static void register(IEventBus eventBus)
    {
        PARTICLE_TYPES.register(eventBus);
    }
}
