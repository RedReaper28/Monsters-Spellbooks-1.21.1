package net.redreaper.monsterspellbooks.events;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.spells.ancient_flash.AncientFlashRenderer;
import net.redreaper.monsterspellbooks.entity.spells.cauterizing_touch.CauterizingTouchRenderer;
import net.redreaper.monsterspellbooks.entity.spells.vile_slash.VileSlashRenderer;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModParticleTypes;
import net.redreaper.monsterspellbooks.particle.AncientSparksParticle;
import net.redreaper.monsterspellbooks.particle.ReaperEmberParticle;
import net.redreaper.monsterspellbooks.particle.ReaperFireParticle;

@SuppressWarnings("removal")
@EventBusSubscriber(modid = MonstersSpellbooks.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void registerRenderer(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer(ModEntities.CAUTERIZING_TOUCH.get(), CauterizingTouchRenderer::new);
        event.registerEntityRenderer(ModEntities.ANCIENT_FLASH.get(), AncientFlashRenderer::new);
        event.registerEntityRenderer(ModEntities.VILE_SLASH_PROJECTILE.get(), VileSlashRenderer::new);
    }

@SubscribeEvent
public static void registerParticles(RegisterParticleProvidersEvent event)
{
    event.registerSpriteSet(ModParticleTypes.ANCIENT_SPARKS_PARTICLE.get(), AncientSparksParticle.Provider::new);
    event.registerSpriteSet(ModParticleTypes.REAPER_FIRE_PARTICLE.get(), ReaperFireParticle.Provider::new);
    event.registerSpriteSet(ModParticleTypes.REAPER_EMBERS_PARTICLE.get(), ReaperEmberParticle.Provider::new);
    event.registerSpriteSet(ModParticleTypes.FRENZY_FIRE_PARTICLE.get(), ReaperFireParticle.Provider::new);
    event.registerSpriteSet(ModParticleTypes.FRENZY_EMBERS_PARTICLE.get(), ReaperEmberParticle.Provider::new);
}


}

