package net.redreaper.monsterspellbooks.init;

import com.mojang.blaze3d.platform.InputConstants;
import io.redspace.ironsspellbooks.player.ExtendedKeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;

@EventBusSubscriber(modid = MonstersSpellbooks.MOD_ID, value = Dist.CLIENT)
public class ModKeybinds {

    public static final String KEY_BIND_GENERAL_CATEGORY = "key.monsterspellbooks.group";

    public static final ExtendedKeyMapping ABILITY_ARMOR = new ExtendedKeyMapping(getResourceName("ability_armor"), KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, InputConstants.KEY_H, KEY_BIND_GENERAL_CATEGORY);
    public static final ExtendedKeyMapping ABILITY_ARMOR_2 = new ExtendedKeyMapping(getResourceName("ability_armor_2"), KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, InputConstants.KEY_J, KEY_BIND_GENERAL_CATEGORY);
    public static final ExtendedKeyMapping ABILITY_CURIO = new ExtendedKeyMapping(getResourceName("ability_curio"), KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, InputConstants.KEY_Y, KEY_BIND_GENERAL_CATEGORY);
    public static final ExtendedKeyMapping ABILITY_SPELLBOOK = new ExtendedKeyMapping(getResourceName("ability_spellbook"), KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, InputConstants.KEY_Z, KEY_BIND_GENERAL_CATEGORY);
    public static final ExtendedKeyMapping ABILITY_WEAPON = new ExtendedKeyMapping(getResourceName("ability_weapon"), KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, InputConstants.KEY_X, KEY_BIND_GENERAL_CATEGORY);

    private static String getResourceName(String name) {
        return String.format("key.monsterspellbooks.%s", name);
    }

    @SubscribeEvent
    public static void onRegisterKeybinds(RegisterKeyMappingsEvent event) {
        event.register(ABILITY_ARMOR);
        event.register(ABILITY_ARMOR_2);
        event.register(ABILITY_CURIO);
        event.register(ABILITY_SPELLBOOK);
        event.register(ABILITY_WEAPON);
    }
}
