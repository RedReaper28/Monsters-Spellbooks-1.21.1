package net.redreaper.monsterspellbooks.entity.armor;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.item.armor.IllagerIceologerArmorItem;
import software.bernie.geckolib.model.GeoModel;

public class  IllagerIceologerArmorModel extends GeoModel<IllagerIceologerArmorItem> {

    public IllagerIceologerArmorModel() {
        super();

    }

    @Override
    public ResourceLocation getModelResource(IllagerIceologerArmorItem object) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/armor/illager_iceologer_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(IllagerIceologerArmorItem object) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/armor/illager_iceologer_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(IllagerIceologerArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "animations/wizard_armor_animation.json");
    }
}