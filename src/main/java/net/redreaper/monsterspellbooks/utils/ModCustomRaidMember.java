package net.redreaper.monsterspellbooks.utils;

import net.minecraft.world.entity.raid.Raid;

public class ModCustomRaidMember {
    static {
        Raid.RaiderType.values();
    }

    public static final String ENCHANTER_INTERNAL_NAME = "illager_enchanter";
    public static final int[] ENCHANTER_COUNT_IN_WAVES = {0, 1, 0, 0, 3, 0, 1, 2};
    public static Raid.RaiderType ENCHANTER;

    public static final String ICEOLOGER_INTERNAL_NAME = "illager_iceologer";
    public static final int[] ICEOLOGER_COUNT_IN_WAVES = {0, 0, 0, 0, 0, 1, 1, 2};
    public static Raid.RaiderType ICEOLOGER;


}
