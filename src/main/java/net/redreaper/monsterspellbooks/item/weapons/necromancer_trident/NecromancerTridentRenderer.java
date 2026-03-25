package net.redreaper.monsterspellbooks.item.weapons.necromancer_trident;

import mod.azure.azurelib.common.render.item.AzItemRenderer;
import mod.azure.azurelib.common.render.item.AzItemRendererConfig;
import mod.azure.azurelib.common.render.layer.AzAutoGlowingLayer;
import net.minecraft.resources.ResourceLocation;

public class NecromancerTridentRenderer extends AzItemRenderer {
    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/item/weapons/necromancer_trident.geo.json");
    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/item/weapons/necromancer_trident.png");

    public NecromancerTridentRenderer() {
        super(AzItemRendererConfig.builder(GEO, TEX).addRenderLayer(new AzAutoGlowingLayer<>()).build());
    }
}
