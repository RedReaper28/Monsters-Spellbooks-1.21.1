package net.redreaper.monsterspellbooks.entity.curios.orb_soul;

import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;

public class OrbSoulCurioItemRenderer extends AzArmorRenderer {
    public static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            MonstersSpellbooks.MOD_ID,
            "geo/curios/orb_soul.geo.json"
    );

    public static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            MonstersSpellbooks.MOD_ID,
            "textures/curios/orb_soul.png"
    );

    public OrbSoulCurioItemRenderer() {
        super(
                AzArmorRendererConfig.builder(GEO, TEX)
                        .build()
        );
    }
}