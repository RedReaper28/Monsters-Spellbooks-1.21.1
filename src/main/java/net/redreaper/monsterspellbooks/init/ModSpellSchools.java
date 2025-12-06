package net.redreaper.monsterspellbooks.init;

import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;

import java.util.function.Supplier;

import static io.redspace.ironsspellbooks.api.registry.SchoolRegistry.SCHOOL_REGISTRY_KEY;

public class ModSpellSchools  {
    private static final DeferredRegister<SchoolType> MONSTER_SCHOOLS = DeferredRegister.create(SCHOOL_REGISTRY_KEY, MonstersSpellbooks.MOD_ID);

    public static void register(IEventBus eventBus)
    {
        MONSTER_SCHOOLS.register(eventBus);
    }

    private static Supplier<SchoolType> registerSchool(SchoolType type)
    {
        return MONSTER_SCHOOLS.register(type.getId().getPath(), () -> type);
    }

    public static final ResourceLocation NECRO_RESOURCE = MonstersSpellbooks.id("necro");

    public static final Supplier<SchoolType> NECRO = registerSchool(new SchoolType
            (
                    NECRO_RESOURCE,
                    ModTags.Items.NECRO_FOCUS,
                    Component.translatable("school.monsterspellbooks.necro").withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GREEN)),
                    ModAtributeRegistry.NECRO_MAGIC_POWER,
                    ModAtributeRegistry.NECRO_MAGIC_RESIST,
                    SoundRegistry.EVOCATION_CAST,
                    ModDamageTypes.NECRO_MAGIC
            ));
}
