package net.redreaper.monsterspellbooks.item.shields.dwarven_riot_shield;

import mod.azure.azurelib.common.render.item.AzItemRenderer;
import mod.azure.azurelib.common.render.item.AzItemRendererConfig;
import mod.azure.azurelib.common.render.layer.AzAutoGlowingLayer;
import net.minecraft.resources.ResourceLocation;

public class DwarvenRiotShieldRenderer extends AzItemRenderer {
    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/item/weapons/dwarven_shield.geo.json");
    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/item/weapons/dwarven_shield.png");

    public DwarvenRiotShieldRenderer() {
        super(AzItemRendererConfig.builder(GEO, TEX).setAnimatorProvider(DwarvenRiotShieldAnimator::new).addRenderLayer(new AzAutoGlowingLayer<>()).build());
    }
}

