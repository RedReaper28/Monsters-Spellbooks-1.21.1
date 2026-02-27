package net.redreaper.monsterspellbooks.item.staves.eyebloosom_staff;

import mod.azure.azurelib.common.render.item.AzItemRenderer;
import mod.azure.azurelib.common.render.item.AzItemRendererConfig;
import mod.azure.azurelib.common.render.layer.AzAutoGlowingLayer;
import net.minecraft.resources.ResourceLocation;

public class EyebloosomStaffRenderer extends AzItemRenderer {
    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "geo/item/staff/eyebloosom_staff.geo.json");
    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "textures/item/staff/eyebloosom_staff.png");

    public EyebloosomStaffRenderer() {
        super(AzItemRendererConfig.builder(GEO, TEX).setAnimatorProvider(EyebloosomStaffAnimator::new).addRenderLayer(new AzAutoGlowingLayer<>()).build());
    }
}
