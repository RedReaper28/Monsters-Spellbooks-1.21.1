package net.redreaper.monsterspellbooks.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;

public class ModDamageTypes {
    public static ResourceKey<DamageType> register(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, name).toString()));
    }

    public static final ResourceKey<DamageType> NECRO_MAGIC = register("necro_magic");
    public static final ResourceKey<DamageType> AERO_MAGIC = register("aero_magic");

    public static final ResourceKey<DamageType> SNOW_CLOUD = register("snow_cloud");
    public static final ResourceKey<DamageType> PUTRESCENCE_FIELD = register("putrescence_field");



    public static void bootstrap(BootstrapContext<DamageType> context)
    {
        context.register(NECRO_MAGIC, new DamageType(NECRO_MAGIC.location().getPath(), DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0F));
        context.register(AERO_MAGIC, new DamageType(AERO_MAGIC.location().getPath(), DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0F));
        context.register(PUTRESCENCE_FIELD, new DamageType(SNOW_CLOUD.location().getPath(), DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0f));
        context.register(SNOW_CLOUD, new DamageType(PUTRESCENCE_FIELD.location().getPath(), DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0f));

    }
}
