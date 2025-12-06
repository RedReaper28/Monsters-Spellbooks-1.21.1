package net.redreaper.monsterspellbooks;

import net.redreaper.monsterspellbooks.init.ModBlocks;
import net.redreaper.monsterspellbooks.init.ModCreativeTabs;
import net.redreaper.monsterspellbooks.init.ModItems;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(MonstersSpellbooks.MOD_ID)
public class MonstersSpellbooks {
    public static final String MOD_ID = "monsterspellbooks";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MonstersSpellbooks(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::addCreative);

        NeoForge.EVENT_BUS.register(this);

        ModCreativeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);


        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
