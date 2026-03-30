package net.redreaper.monsterspellbooks.entity.armor;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.item.armor.ForgeMasterpieceArmorItem;
import net.redreaper.monsterspellbooks.item.armor.NecromancerArmorItem;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class NecromancerArmorModel extends DefaultedItemGeoModel<NecromancerArmorItem> {
    public NecromancerArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath("monsterspellbooks", ""));
    }

    public ResourceLocation getModelResource(NecromancerArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/armor/necromancer_armor.geo.json");
    }

    public ResourceLocation getTextureResource(NecromancerArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/armor/necromancer_armor.png");
    }

    public ResourceLocation getAnimationResource(NecromancerArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "animations/wizard_armor_animation.json");
    }
}