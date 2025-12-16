package net.redreaper.monsterspellbooks.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.living.DwarvenSphere;
import net.redreaper.monsterspellbooks.entity.living.VileSkeleton;
import net.redreaper.monsterspellbooks.entity.spells.ancient_flash.AncientFlash;
import net.redreaper.monsterspellbooks.entity.spells.brimstone_buzzsaw.BrimstoneBuzzsawProjectile;
import net.redreaper.monsterspellbooks.entity.spells.cauterizing_touch.CauterizingTouch;
import net.redreaper.monsterspellbooks.entity.spells.frenzied_burst.FrenziedBurstVisualEntity;
import net.redreaper.monsterspellbooks.entity.spells.ice_arsenal.IceArsenalSword;
import net.redreaper.monsterspellbooks.entity.spells.life_drain.LifeDrainProjectile;
import net.redreaper.monsterspellbooks.entity.spells.sangunite_eviceration.SanguiniteEvisceration;
import net.redreaper.monsterspellbooks.entity.spells.spectral_blast.SpectralBlastVisualEntity;
import net.redreaper.monsterspellbooks.entity.spells.vile_slash.VileSlashProjectile;
import net.redreaper.monsterspellbooks.entity.spells.wither_bomb.WitherBombProjectile;

import java.util.function.Supplier;

import static net.minecraft.core.registries.Registries.ENTITY_TYPE;

public class ModEntities {
    private static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ENTITY_TYPE, MonstersSpellbooks.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<SanguiniteEvisceration>>SANGUINITE_EVISCERATION =
            ENTITIES.register("sanguinite_evisceration", () -> EntityType.Builder.<SanguiniteEvisceration>of(SanguiniteEvisceration::new, MobCategory.MISC)
                    .sized(2f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "sanguinite_evisceration").toString())
            );
    public static final DeferredHolder<EntityType<?>, EntityType<CauterizingTouch>>CAUTERIZING_TOUCH =
            ENTITIES.register("cauterizing_touch", () -> EntityType.Builder.<CauterizingTouch>of(CauterizingTouch::new, MobCategory.MISC)
                    .sized(2f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "cauterizing_touch").toString())
            );
    public static final DeferredHolder<EntityType<?>, EntityType<BrimstoneBuzzsawProjectile>> BRIMSTONE_BUZZSAW_PROJECTILE =
            ENTITIES.register("brimstone_buzzsaw", () -> EntityType.Builder.<BrimstoneBuzzsawProjectile>of(BrimstoneBuzzsawProjectile::new, MobCategory.MISC)
                    .sized(7f, 1f)
                    .clientTrackingRange(128)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "brimstone_buzzsaw").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<FrenziedBurstVisualEntity>> FRENZIED_BURST_VISUAL_ENTITY =
            ENTITIES.register("frenzied_burst", () -> EntityType.Builder.<FrenziedBurstVisualEntity>of(FrenziedBurstVisualEntity::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "frenzied_burst").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<IceArsenalSword>> ICE_SWORD =
            ENTITIES.register("ice_sword", () -> EntityType.Builder.<IceArsenalSword>of(IceArsenalSword::new, MobCategory.MISC)
                    .sized(.5f, .5f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "ice_sword").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<AncientFlash>> ANCIENT_FLASH =
            ENTITIES.register("ancient_flash", () -> EntityType.Builder.<AncientFlash>of(AncientFlash::new, MobCategory.MISC)
                    .sized(2f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "ancient_flash").toString())
            );

    public static final DeferredHolder<EntityType<?>, EntityType<VileSlashProjectile>> VILE_SLASH_PROJECTILE =
            ENTITIES.register("vile_slash", () -> EntityType.Builder.<VileSlashProjectile>of(VileSlashProjectile::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "vile_slash").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<SpectralBlastVisualEntity>> SPECTRAL_BLAST_VISUAL_ENTITY =
            ENTITIES.register("spectral_blast", () -> EntityType.Builder.<SpectralBlastVisualEntity>of(SpectralBlastVisualEntity::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "spectral_blast").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<LifeDrainProjectile>> LIFE_DRAIN_PROJECTILE =
            ENTITIES.register("life_drain", () -> EntityType.Builder.<LifeDrainProjectile>of(LifeDrainProjectile::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "life_drain").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<WitherBombProjectile>> WITHER_BOMB =
            ENTITIES.register("wither_bomb", () -> EntityType.Builder.<WitherBombProjectile>of(WitherBombProjectile::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(4)
                    .build(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "wither_bomb").toString()));


    public static final Supplier<EntityType<VileSkeleton>>VILE_SKELETON=
            ENTITIES.register("vile_skeleton",()->EntityType.Builder.of(VileSkeleton::new,MobCategory.MONSTER)
                    .sized(0.6F, 1.9F).build("vile_skeleton"));

    public static final Supplier<EntityType<DwarvenSphere>>DWARVEN_SPHERE=
            ENTITIES.register("dwarven_sphere",()->EntityType.Builder.of(DwarvenSphere::new,MobCategory.MONSTER)
                    .sized(0.8F, 2F).build("dwarven_sphere"));




    public static void register(IEventBus eventBus)
    {
        ENTITIES.register(eventBus);
    }
}
