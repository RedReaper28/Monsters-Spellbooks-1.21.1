package net.redreaper.monsterspellbooks.entity.armor;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.item.armor.DeathKnightArmorItem;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class DeathKnightArmorModel extends DefaultedItemGeoModel<DeathKnightArmorItem> {
    public DeathKnightArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath("monsterspellbooks", ""));
    }

    public ResourceLocation getModelResource(DeathKnightArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/armor/death_knight_armor.geo.json");
    }

    public ResourceLocation getTextureResource(DeathKnightArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/armor/death_knight_armor.png");
    }

    public ResourceLocation getAnimationResource(DeathKnightArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "animations/wizard_armor_animation.json");
    }
}
