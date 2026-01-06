package net.redreaper.monsterspellbooks.init;

import io.redspace.ironsspellbooks.item.armor.UpgradeOrbType;
import io.redspace.ironsspellbooks.registries.UpgradeOrbTypeRegistry;
import net.minecraft.resources.ResourceKey;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;


public class ModUpgradeTypeRegistry  {
    public static ResourceKey<UpgradeOrbType> NECRO_SPELL_POWER;

    static {
        NECRO_SPELL_POWER = ResourceKey.create(UpgradeOrbTypeRegistry.UPGRADE_ORB_REGISTRY_KEY, MonstersSpellbooks.id("necro_power"));
    }
}
