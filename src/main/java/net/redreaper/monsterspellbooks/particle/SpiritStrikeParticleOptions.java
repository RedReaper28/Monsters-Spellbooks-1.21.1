package net.redreaper.monsterspellbooks.particle;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.codec.StreamCodec;
import net.redreaper.monsterspellbooks.init.ModParticleTypes;
import org.jetbrains.annotations.NotNull;

public class SpiritStrikeParticleOptions implements ParticleOptions {
    public final float scale;
    public final float xf;
    public final float yf;
    public final float zf;
    public final boolean mirror, vertical;

    public SpiritStrikeParticleOptions(float xf, float yf, float zf, boolean mirror, boolean vertical, float scale) {
        this.scale = scale;
        this.xf = xf;
        this.yf = yf;
        this.zf = zf;
        this.mirror = mirror;
        this.vertical = vertical;
    }

    public static StreamCodec<? super ByteBuf, SpiritStrikeParticleOptions> STREAM_CODEC = StreamCodec.of(
            (buf, option) -> {
                buf.writeFloat(option.xf);
                buf.writeFloat(option.yf);
                buf.writeFloat(option.zf);
                buf.writeBoolean(option.mirror);
                buf.writeBoolean(option.vertical);
                buf.writeFloat(option.scale);
            },
            (buf) -> new SpiritStrikeParticleOptions(buf.readFloat(), buf.readFloat(), buf.readFloat(), buf.readBoolean(), buf.readBoolean(), buf.readFloat())
    );

    public static MapCodec<SpiritStrikeParticleOptions> MAP_CODEC = RecordCodecBuilder.mapCodec(object ->
            object.group(
                    Codec.FLOAT.fieldOf("xf").forGetter(p -> ((SpiritStrikeParticleOptions) p).xf),
                    Codec.FLOAT.fieldOf("yf").forGetter(p -> ((SpiritStrikeParticleOptions) p).yf),
                    Codec.FLOAT.fieldOf("zf").forGetter(p -> ((SpiritStrikeParticleOptions) p).zf),
                    Codec.BOOL.fieldOf("mirror").forGetter(p -> ((SpiritStrikeParticleOptions) p).mirror),
                    Codec.BOOL.fieldOf("vertical").forGetter(p -> ((SpiritStrikeParticleOptions) p).vertical),
                    Codec.FLOAT.fieldOf("scale").forGetter(p -> ((SpiritStrikeParticleOptions) p).scale)
            ).apply(object, SpiritStrikeParticleOptions::new
            ));

    public @NotNull ParticleType<SpiritStrikeParticleOptions> getType() {
        return ModParticleTypes.SPIRIT_STRIKE_PARTICLE.get();
    }
}
