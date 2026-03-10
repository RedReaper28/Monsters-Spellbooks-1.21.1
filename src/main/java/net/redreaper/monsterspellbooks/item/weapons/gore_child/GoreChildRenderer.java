package net.redreaper.monsterspellbooks.item.weapons.gore_child;

import mod.azure.azurelib.common.render.item.AzItemRenderer;
import mod.azure.azurelib.common.render.item.AzItemRendererConfig;
import net.minecraft.resources.ResourceLocation;

public class GoreChildRenderer extends AzItemRenderer {
    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/item/weapons/gore_child.geo.json");
    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/item/weapons/gore_child.png");

    public GoreChildRenderer() {
        super(AzItemRendererConfig.builder(GEO, TEX).build());
    }
}
