package net.redreaper.monsterspellbooks.entity.armor;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.item.armor.DwarvenEngineerArmorItem;
import net.redreaper.monsterspellbooks.item.armor.ForgeMasterpieceArmorItem;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class DwarvenEngineerArmorModel extends DefaultedItemGeoModel<DwarvenEngineerArmorItem> {
    public DwarvenEngineerArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath("monsterspellbooks", ""));
    }

    public ResourceLocation getModelResource(DwarvenEngineerArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/armor/dwarven_engineer_armor.geo.json");
    }

    public ResourceLocation getTextureResource(DwarvenEngineerArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/armor/dwarven_engineer_armor.png");
    }

    public ResourceLocation getAnimationResource(DwarvenEngineerArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "animations/wizard_armor_animation.json");
    }
}