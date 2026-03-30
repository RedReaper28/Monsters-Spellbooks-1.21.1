package net.redreaper.monsterspellbooks.utils;

import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Rarity;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;

import java.util.function.UnaryOperator;

public class ModRarities {
    public static final EnumProxy<Rarity> ILLAGER_RARITY_PROXY = new EnumProxy<>(Rarity.class,
        -1,
        "monsterspellbooks:illager",
        (UnaryOperator<Style>) ((style) -> style.withColor(8497057))
    );

    public static final EnumProxy<Rarity> PRISMARINE_RARITY_PROXY = new EnumProxy<>(Rarity.class,
            -1,
            "monsterspellbooks:prismarine",
            (UnaryOperator<Style>) ((style) -> style.withColor(6664847))
    );

    public static final EnumProxy<Rarity> SOUL_RARITY_PROXY = new EnumProxy<>(Rarity.class,
            -1,
            "monsterspellbooks:soul",
            (UnaryOperator<Style>) ((style) -> style.withColor(316109))
    );

    public static final EnumProxy<Rarity> DWARVEN_RARITY_PROXY = new EnumProxy<>(Rarity.class,
            -1,
            "monsterspellbooks:dwarven",
            (UnaryOperator<Style>) ((style) -> style.withColor(16501864))
    );

    public static final EnumProxy<Rarity> BRIMSTONE_RARITY_PROXY = new EnumProxy<>(Rarity.class,
            -1,
            "monsterspellbooks:brimstone",
            (UnaryOperator<Style>) ((style) -> style.withColor(16746085))
    );

    public static final EnumProxy<Rarity> SANGUINITE_RARITY_PROXY = new EnumProxy<>(Rarity.class,
            -1,
            "monsterspellbooks:sanguinite",
            (UnaryOperator<Style>) ((style) -> style.withColor(16716189))
    );

}
