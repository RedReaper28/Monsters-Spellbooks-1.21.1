package net.redreaper.monsterspellbooks.events;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.particle.ElectricExplosionParticlesPacket;
import net.redreaper.monsterspellbooks.particle.EnderExplosionParticlePacket;
import net.redreaper.monsterspellbooks.particle.SoulExplosionParticlePacket;
import net.redreaper.monsterspellbooks.particle.ThunderStepParticlePacket;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MonstersSpellbooks.MOD_ID)
public class PayloadHandler {
    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar payloadRegistrar = event.registrar(MonstersSpellbooks.MOD_ID).versioned("1.0.0").optional();

        //PARTICLES
        payloadRegistrar.playToClient(ElectricExplosionParticlesPacket.TYPE, ElectricExplosionParticlesPacket.STREAM_CODEC, ElectricExplosionParticlesPacket::handle);
        payloadRegistrar.playToClient(SoulExplosionParticlePacket.TYPE, SoulExplosionParticlePacket.STREAM_CODEC, SoulExplosionParticlePacket::handle);
        payloadRegistrar.playToClient(EnderExplosionParticlePacket.TYPE, EnderExplosionParticlePacket.STREAM_CODEC, EnderExplosionParticlePacket::handle);
        payloadRegistrar.playToClient(ThunderStepParticlePacket.TYPE, ThunderStepParticlePacket.STREAM_CODEC, ThunderStepParticlePacket::handle);

    }
}
