package net.redreaper.monsterspellbooks.entity.armor;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.item.armor.PaleObserverArmorItem;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class PaleObserverArmorModel extends DefaultedItemGeoModel<PaleObserverArmorItem> {
    public PaleObserverArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath("monsterspellbooks", ""));
    }

    public ResourceLocation getModelResource(PaleObserverArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "geo/armor/pale_observer_armor.geo.json");
    }

    public ResourceLocation getTextureResource(PaleObserverArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "textures/armor/pale_observer_armor.png");
    }

    public ResourceLocation getAnimationResource(PaleObserverArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "animations/wizard_armor_animation.json");
    }
}