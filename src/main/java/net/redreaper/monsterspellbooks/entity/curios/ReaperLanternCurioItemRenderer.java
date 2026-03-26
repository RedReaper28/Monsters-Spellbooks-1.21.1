package net.redreaper.monsterspellbooks.entity.curios;

import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;

public class ReaperLanternCurioItemRenderer extends AzArmorRenderer {
    public static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            MonstersSpellbooks.MOD_ID,
            "geo/curios/reaper_lantern.geo.json"
    );

    public static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            MonstersSpellbooks.MOD_ID,
            "textures/curios/reaper_lantern.png"
    );

    public ReaperLanternCurioItemRenderer() {
        super(
                AzArmorRendererConfig.builder(GEO, TEX)
                        .build()
        );
    }
}