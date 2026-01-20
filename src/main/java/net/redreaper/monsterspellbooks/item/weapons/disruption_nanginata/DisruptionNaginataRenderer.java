package net.redreaper.monsterspellbooks.item.weapons.disruption_nanginata;

import mod.azure.azurelib.common.render.item.AzItemRenderer;
import mod.azure.azurelib.common.render.item.AzItemRendererConfig;
import mod.azure.azurelib.common.render.layer.AzAutoGlowingLayer;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.item.staves.brimstone_orochi.BrimstoneOrochiAnimator;

public class DisruptionNaginataRenderer extends AzItemRenderer {
    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/item/weapons/disruption_naginata.geo.json");
    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/item/weapons/disruption_naginata.png");

    public DisruptionNaginataRenderer() {
        super(AzItemRendererConfig.builder(GEO, TEX).addRenderLayer(new AzAutoGlowingLayer<>()).build());
    }
}
