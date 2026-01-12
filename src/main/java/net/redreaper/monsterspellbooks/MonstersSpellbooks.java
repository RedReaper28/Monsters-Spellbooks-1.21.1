package net.redreaper.monsterspellbooks;

import io.redspace.ironsspellbooks.item.SpellBook;
import io.redspace.ironsspellbooks.registries.FluidRegistry;
import io.redspace.ironsspellbooks.registries.UpgradeOrbTypeRegistry;
import io.redspace.ironsspellbooks.render.SpellBookCurioRenderer;
import mod.azure.azurelib.common.animation.cache.AzIdentityRegistry;
import mod.azure.azurelib.common.render.armor.AzArmorRendererRegistry;
import mod.azure.azurelib.common.render.item.AzItemRendererRegistry;
import net.acetheeldritchking.aces_spell_utils.entity.render.items.SheathCurioRenderer;
import net.acetheeldritchking.aces_spell_utils.items.curios.SheathCurioItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.redreaper.monsterspellbooks.init.*;
import net.redreaper.monsterspellbooks.item.staves.vilenova_staff.VileNovaStaffRenderer;
import org.jetbrains.annotations.NotNull;
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
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

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
        ModExtendedArmorMaterials.register(modEventBus);

        ModMobEffects.register(modEventBus);
        ModAtributeRegistry.register(modEventBus);
        ModParticleTypes.register(modEventBus);
        ModEntities.register(modEventBus);
        ModSpellRegistry.register(modEventBus);
        ModSpellSchools.register(modEventBus);
        ModFluids.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public static ResourceLocation id(@NotNull String path)
    {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, path);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @EventBusSubscriber({Dist.CLIENT})
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            AzItemRendererRegistry.register(VileNovaStaffRenderer::new, (Item)ModItems.VILENOVA_STAFF.get(), new Item[0]);
        }
    }
}
