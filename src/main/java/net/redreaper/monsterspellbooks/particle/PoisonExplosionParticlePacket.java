package net.redreaper.monsterspellbooks.particle;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.player.ModClientSpellCastHelper;

public class PoisonExplosionParticlePacket implements CustomPacketPayload {
    private final Vec3 pos1;
    private final float radius;
    public static final Type<PoisonExplosionParticlePacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "poison_explosion_particles"));
    public static final StreamCodec<RegistryFriendlyByteBuf, PoisonExplosionParticlePacket> STREAM_CODEC = CustomPacketPayload.codec(PoisonExplosionParticlePacket::write, PoisonExplosionParticlePacket::new);

    public PoisonExplosionParticlePacket(Vec3 pos1, float radius) {
        this.pos1 = pos1;
        this.radius = radius;
    }

    public PoisonExplosionParticlePacket(FriendlyByteBuf buf) {
        pos1 = buf.readVec3();
        radius = buf.readFloat();
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeVec3(pos1);
        buf.writeFloat(radius);
    }

    public static void handle(PoisonExplosionParticlePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            ModClientSpellCastHelper.handleClientboundPoisonExplosion(packet.pos1, packet.radius);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}