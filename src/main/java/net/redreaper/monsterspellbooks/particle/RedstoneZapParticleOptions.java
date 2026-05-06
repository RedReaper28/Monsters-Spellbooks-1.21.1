package net.redreaper.monsterspellbooks.particle;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.init.ModParticleTypes;

import java.util.stream.IntStream;

public class RedstoneZapParticleOptions implements ParticleOptions {
    public static StreamCodec<? super ByteBuf, RedstoneZapParticleOptions> STREAM_CODEC = StreamCodec.of(
            (buf, option) -> {
                buf.writeInt((int) option.destination.x * 10);
                buf.writeInt((int) option.destination.y * 10);
                buf.writeInt((int) option.destination.z * 10);
            },
            (buf) -> {
                return new RedstoneZapParticleOptions(buf.readInt() / 10f, buf.readInt() / 10f, buf.readInt() / 10f);
            }
    );

    public static MapCodec<RedstoneZapParticleOptions> MAP_CODEC = RecordCodecBuilder.mapCodec(object ->
            object.group(
                    Codec.INT_STREAM.fieldOf("destination").forGetter((option) -> IntStream.of((int) option.destination.x * 10, (int) option.destination.y * 10, (int) option.destination.z * 10))
            ).apply(object, (stream) -> {
                        var array = stream.toArray();
                        return new RedstoneZapParticleOptions(new Vec3(array[0] / 10f, array[1] / 10f, array[2] / 10f));
                    }
            ));

    private final Vec3 destination;

    public RedstoneZapParticleOptions(Vec3 destination) {
        this.destination = destination;
    }

    public RedstoneZapParticleOptions(float x, float y, float z) {
        this(new Vec3(x, y, z));
    }

    public ParticleType<RedstoneZapParticleOptions> getType() {
        return ModParticleTypes.REDSTONE_ZAP_PARTICLE.get();
    }

    public Vec3 getDestination() {
        return this.destination;
    }

}
