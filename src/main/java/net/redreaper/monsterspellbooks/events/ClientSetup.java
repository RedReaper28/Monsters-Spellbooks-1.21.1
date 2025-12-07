package net.redreaper.monsterspellbooks.events;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.init.ModParticleTypes;
import net.redreaper.monsterspellbooks.particle.AncientSparksParticle;

@EventBusSubscriber(modid = MonstersSpellbooks.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void registerRenderer(EntityRenderersEvent.RegisterRenderers event)
    {


    }

@SubscribeEvent
public static void registerParticles(RegisterParticleProvidersEvent event)
{
    event.registerSpriteSet(ModParticleTypes.ANCIENT_SPARKS_PARTICLE.get(), AncientSparksParticle.Provider::new);
}


}

