package net.redreaper.monsterspellbooks.entity.armor;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.item.armor.SanguiniteHeroArmorItem;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class SanguiniteHeroArmorModel extends DefaultedEntityGeoModel<SanguiniteHeroArmorItem> {
    public SanguiniteHeroArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath("monsterspellbooks", ""));
    }

    public ResourceLocation getModelResource(SanguiniteHeroArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/armor/sanguinite_hero_armor.geo.json");
    }

    public ResourceLocation getTextureResource(SanguiniteHeroArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/armor/sanguinite_hero_armor.png");
    }

    public ResourceLocation getAnimationResource(SanguiniteHeroArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "animations/wizard_armor_animation.json");
    }
}