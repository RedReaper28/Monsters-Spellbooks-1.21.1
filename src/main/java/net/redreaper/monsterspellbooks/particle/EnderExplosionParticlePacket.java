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

public class EnderExplosionParticlePacket implements CustomPacketPayload {
    private final Vec3 pos1;
    private final float radius;
    public static final CustomPacketPayload.Type<EnderExplosionParticlePacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "ender_explosion_particles"));
    public static final StreamCodec<RegistryFriendlyByteBuf, EnderExplosionParticlePacket> STREAM_CODEC = CustomPacketPayload.codec(EnderExplosionParticlePacket::write, EnderExplosionParticlePacket::new);

    public EnderExplosionParticlePacket(Vec3 pos1, float radius) {
        this.pos1 = pos1;
        this.radius = radius;
    }

    public EnderExplosionParticlePacket(FriendlyByteBuf buf) {
        pos1 = buf.readVec3();
        radius = buf.readFloat();
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeVec3(pos1);
        buf.writeFloat(radius);
    }

    public static void handle(EnderExplosionParticlePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            ModClientSpellCastHelper.handleClientboundEnderExplosion(packet.pos1, packet.radius);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}