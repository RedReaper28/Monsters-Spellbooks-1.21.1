package net.redreaper.monsterspellbooks.item.shields.mithril_shield;

import mod.azure.azurelib.common.render.item.AzItemRenderer;
import mod.azure.azurelib.common.render.item.AzItemRendererConfig;
import mod.azure.azurelib.common.render.layer.AzAutoGlowingLayer;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.item.shields.dwarven_riot_shield.DwarvenRiotShieldAnimator;

public class MithrilShieldRenderer extends AzItemRenderer {
    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/item/weapons/mithril_shield.geo.json");
    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/item/weapons/mithril_shield.png");

    public MithrilShieldRenderer() {
        super(AzItemRendererConfig.builder(GEO, TEX).setAnimatorProvider(DwarvenRiotShieldAnimator::new).addRenderLayer(new AzAutoGlowingLayer<>()).build());
    }
}
