package net.redreaper.monsterspellbooks.gui.overlays;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.redreaper.monsterspellbooks.init.ModMobEffects;

public class ModScreenEffectsOverlay implements LayeredDraw.Layer {
    public static final ModScreenEffectsOverlay instance = new ModScreenEffectsOverlay();

    public final static ResourceLocation MAGIC_AURA_TEXTURE = ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "textures/gui/overlays/enchanted_ward_vignette.png");
    public final static ResourceLocation HEARTSTOP_TEXTURE = ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "textures/gui/overlays/heartstop.png");
    public final static ResourceLocation ICE_BLOCK_TEXTURE = ResourceLocation.withDefaultNamespace("textures/block/ice.png");

    public void render(GuiGraphics guiHelper, DeltaTracker deltaTracker) {
        if (Minecraft.getInstance().options.hideGui || Minecraft.getInstance().player.isSpectator()) {
            return;
        }
        var screenWidth = guiHelper.guiWidth();
        var screenHeight = guiHelper.guiHeight();

        Player player = Minecraft.getInstance().player;
        if (player == null) {
            return;
        }
        if (player.hasEffect(ModMobEffects.SOUL_FORM)) {
            renderOverlayAdditive(guiHelper, MAGIC_AURA_TEXTURE, 0.130f, 0.255f, .221f, .25f, screenWidth, screenHeight);
        }
        if (player.hasEffect(ModMobEffects.LICHDOM)) {
            renderOverlayAdditive(guiHelper, HEARTSTOP_TEXTURE, 0.130f, 0.255f, .221f, .25f, screenWidth, screenHeight);
        }
}
    private static void renderOverlayAdditive(GuiGraphics gui, ResourceLocation texture, float r, float g, float b, float a, int screenWidth, int screenHeight) {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(
                GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE
        );
        gui.setColor(r, g, b, a);
        gui.blit(texture, 0, 0, -90, 0.0F, 0.0F, screenWidth, screenHeight, screenWidth, screenHeight);
        gui.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
    }

    private static void renderOverlay(GuiGraphics gui, ResourceLocation texture, float r, float g, float b, float a, int screenWidth, int screenHeight) {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        gui.setColor(r, g, b, a);
        gui.blit(texture, 0, 0, -90, 0.0F, 0.0F, screenWidth, screenHeight, screenWidth, screenHeight);
        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        gui.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    }


}

