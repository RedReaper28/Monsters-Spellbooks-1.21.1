package net.redreaper.monsterspellbooks.events;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.particle.SoulExplosionParticlesPacket;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MonstersSpellbooks.MOD_ID)
public class PayloadHandler {
    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar payloadRegistrar = event.registrar(MonstersSpellbooks.MOD_ID).versioned("1.0.0").optional();
        //PARTICLES
        payloadRegistrar.playToClient(SoulExplosionParticlesPacket.TYPE, SoulExplosionParticlesPacket.STREAM_CODEC, SoulExplosionParticlesPacket::handle);

    }
}
