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

public class ElectricExplosionParticlesPacket implements CustomPacketPayload {
    private final Vec3 pos1;
    private final float radius;
    public static final CustomPacketPayload.Type<ElectricExplosionParticlesPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "electric_explosion_particles"));
    public static final StreamCodec<RegistryFriendlyByteBuf, ElectricExplosionParticlesPacket> STREAM_CODEC = CustomPacketPayload.codec(ElectricExplosionParticlesPacket::write, ElectricExplosionParticlesPacket::new);

    public ElectricExplosionParticlesPacket(Vec3 pos1, float radius) {
        this.pos1 = pos1;
        this.radius = radius;
    }

    public ElectricExplosionParticlesPacket(FriendlyByteBuf buf) {
        pos1 = buf.readVec3();
        radius = buf.readFloat();
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeVec3(pos1);
        buf.writeFloat(radius);
    }

    public static void handle(ElectricExplosionParticlesPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            ModClientSpellCastHelper.handleClientboundElectricExplosion(packet.pos1, packet.radius);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}