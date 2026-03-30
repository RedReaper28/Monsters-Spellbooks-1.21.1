package net.redreaper.monsterspellbooks.entity.curios.obsidian_medal;

import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;

public class ObsidianMedalCurioItemRenderer extends AzArmorRenderer {
    public static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            MonstersSpellbooks.MOD_ID,
            "geo/curios/obsidian_medal.geo.json"
    );

    public static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            MonstersSpellbooks.MOD_ID,
            "textures/curios/obsidian_medal.png"
    );

    public ObsidianMedalCurioItemRenderer() {
        super(
                AzArmorRendererConfig.builder(GEO, TEX)
                        .build()
        );
    }
}