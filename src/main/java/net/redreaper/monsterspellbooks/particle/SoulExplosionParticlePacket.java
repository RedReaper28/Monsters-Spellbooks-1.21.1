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

public class SoulExplosionParticlePacket implements CustomPacketPayload {
    private final Vec3 pos1;
    private final float radius;
    public static final CustomPacketPayload.Type<SoulExplosionParticlePacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "soul_explosion_particles"));
    public static final StreamCodec<RegistryFriendlyByteBuf, SoulExplosionParticlePacket> STREAM_CODEC = CustomPacketPayload.codec(SoulExplosionParticlePacket::write, SoulExplosionParticlePacket::new);

    public SoulExplosionParticlePacket(Vec3 pos1, float radius) {
        this.pos1 = pos1;
        this.radius = radius;
    }

    public SoulExplosionParticlePacket(FriendlyByteBuf buf) {
        pos1 = buf.readVec3();
        radius = buf.readFloat();
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeVec3(pos1);
        buf.writeFloat(radius);
    }

    public static void handle(SoulExplosionParticlePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            ModClientSpellCastHelper.handleClientboundSoulExplosion(packet.pos1, packet.radius);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
