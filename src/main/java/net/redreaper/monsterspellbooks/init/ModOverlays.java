package net.redreaper.monsterspellbooks.init;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.gui.overlays.ModScreenEffectsOverlay;

@EventBusSubscriber(modid = MonstersSpellbooks.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)

public class ModOverlays {
    @SubscribeEvent
    public static void onRegisterOverlays(RegisterGuiLayersEvent event) {


        event.registerAboveAll(MonstersSpellbooks.id("screen_effects"), ModScreenEffectsOverlay.instance);
    }
}
