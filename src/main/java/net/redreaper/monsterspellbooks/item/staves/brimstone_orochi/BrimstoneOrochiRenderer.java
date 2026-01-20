package net.redreaper.monsterspellbooks.item.staves.brimstone_orochi;

import mod.azure.azurelib.common.render.item.AzItemRenderer;
import mod.azure.azurelib.common.render.item.AzItemRendererConfig;
import mod.azure.azurelib.common.render.layer.AzAutoGlowingLayer;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.item.staves.vilenova_staff.VileNovaStaffAnimator;

public class BrimstoneOrochiRenderer extends AzItemRenderer {
    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/item/staff/brimstone_orochi.geo.json");
    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/item/staff/brimstone_orochi.png");

    public BrimstoneOrochiRenderer() {
        super(AzItemRendererConfig.builder(GEO, TEX).setAnimatorProvider(BrimstoneOrochiAnimator::new).addRenderLayer(new AzAutoGlowingLayer<>()).build());
    }
}
