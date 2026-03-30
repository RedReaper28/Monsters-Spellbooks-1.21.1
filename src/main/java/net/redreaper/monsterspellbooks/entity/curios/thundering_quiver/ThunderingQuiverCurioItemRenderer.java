package net.redreaper.monsterspellbooks.entity.curios.thundering_quiver;

import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;

public class ThunderingQuiverCurioItemRenderer extends AzArmorRenderer {
    public static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            MonstersSpellbooks.MOD_ID,
            "geo/curios/thundering_quiver.geo.json"
    );

    public static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            MonstersSpellbooks.MOD_ID,
            "textures/curios/thundering_quiver.png"
    );

    public ThunderingQuiverCurioItemRenderer() {
        super(
                AzArmorRendererConfig.builder(GEO, TEX)
                        .build()
        );
    }
}