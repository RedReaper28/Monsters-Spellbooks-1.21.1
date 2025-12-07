package net.redreaper.monsterspellbooks.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.spells.ancient_flash.AncientFlash;

import static net.minecraft.core.registries.Registries.ENTITY_TYPE;

public class ModEntities {
    private static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ENTITY_TYPE, MonstersSpellbooks.MOD_ID);



    public static final DeferredHolder<EntityType<?>, EntityType<AncientFlash>> ANCIENT_FLASH =
            ENTITIES.register("ancient_flash", () -> EntityType.Builder.<AncientFlash>of(AncientFlash::new, MobCategory.MISC)
                    .sized(2f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "ancient_flash").toString())
            );
}
