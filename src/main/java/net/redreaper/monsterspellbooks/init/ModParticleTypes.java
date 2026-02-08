package net.redreaper.monsterspellbooks.init;

import com.mojang.serialization.MapCodec;
import io.redspace.ironsspellbooks.particle.ZapParticleOption;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.particle.AncientZapParticleOption;
import net.redreaper.monsterspellbooks.particle.SoulChainParticleOptions;
import net.redreaper.monsterspellbooks.particle.SpiritStrikeParticleOptions;

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
    public static final Supplier<SimpleParticleType> SOUL_FIRE_PARTICLE = PARTICLE_TYPES.register("soul_fire", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> SOUL_EMBERS_PARTICLE = PARTICLE_TYPES.register("soul_embers", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> PUTRESCENCE_BUBBLE_PARTICLE = PARTICLE_TYPES.register("putrescence_bubble", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> STEAM = PARTICLE_TYPES.register("steam", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> ELECTRIC_SMOKE = PARTICLE_TYPES.register("electric_smoke", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> SOUL_SMOKE = PARTICLE_TYPES.register("soul_smoke", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> BONE_FRAGMENTS = PARTICLE_TYPES.register("bone_fragments", () -> new SimpleParticleType(false));

    public static final Supplier<ParticleType<SpiritStrikeParticleOptions>> SPIRIT_STRIKE_PARTICLE = PARTICLE_TYPES.register("spirit_strike", () -> new ParticleType<>(true) {
        public MapCodec<SpiritStrikeParticleOptions> codec() {
            return SpiritStrikeParticleOptions.MAP_CODEC;
        }
        public StreamCodec<? super RegistryFriendlyByteBuf, SpiritStrikeParticleOptions> streamCodec() {
            return SpiritStrikeParticleOptions.STREAM_CODEC;
        }
    });
    public static final Supplier<ParticleType<SoulChainParticleOptions>> SOUL_CHAIN_PARTICLE = PARTICLE_TYPES.register("soul_chain", () -> new ParticleType<>(false) {
        public MapCodec<SoulChainParticleOptions> codec() {
            return SoulChainParticleOptions.MAP_CODEC;
        }

        public StreamCodec<? super RegistryFriendlyByteBuf, SoulChainParticleOptions> streamCodec() {
            return SoulChainParticleOptions.STREAM_CODEC;
        }
    });
    public static final Supplier<ParticleType<AncientZapParticleOption>> ANCIENT_ZAP_PARTICLE = PARTICLE_TYPES.register("ancient_zap", () -> new ParticleType<>(false) {
        public MapCodec<AncientZapParticleOption> codec() {
            return AncientZapParticleOption.MAP_CODEC;
        }

        public StreamCodec<? super RegistryFriendlyByteBuf, AncientZapParticleOption> streamCodec() {
            return AncientZapParticleOption.STREAM_CODEC;
        }
    });

    public static void register(IEventBus eventBus)
    {
        PARTICLE_TYPES.register(eventBus);
    }
}
