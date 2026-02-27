package net.redreaper.monsterspellbooks.events;

import io.redspace.ironsspellbooks.fluids.SimpleClientFluidType;
import io.redspace.ironsspellbooks.render.*;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.model.AegisEntity.AegisEntityModel;
import net.redreaper.monsterspellbooks.entity.model.AegisEntity.AegisEntityRenderer;
import net.redreaper.monsterspellbooks.entity.model.Blastling.BlastlingModel;
import net.redreaper.monsterspellbooks.entity.model.Blastling.BlastlingRenderer;
import net.redreaper.monsterspellbooks.entity.model.DeathKnight.DeathKnightModel;
import net.redreaper.monsterspellbooks.entity.model.DeathKnight.DeathKnightRenderer;
import net.redreaper.monsterspellbooks.entity.model.DripplerEntity.DripplerEntityModel;
import net.redreaper.monsterspellbooks.entity.model.DripplerEntity.DripplerEntityRenderer;
import net.redreaper.monsterspellbooks.entity.model.DwarvenSlicer.DwarvenSlicerRenderer;
import net.redreaper.monsterspellbooks.entity.model.DwarvenSphere.DwarvenSphereRenderer;
import net.redreaper.monsterspellbooks.entity.model.JungleWhisperer.JungleWhispererModel;
import net.redreaper.monsterspellbooks.entity.model.JungleWhisperer.JungleWhispererRenderer;
import net.redreaper.monsterspellbooks.entity.model.PoisonQuillVine.PoisonQuillVineModel;
import net.redreaper.monsterspellbooks.entity.model.PoisonQuillVine.PoisonQuillVineRenderer;
import net.redreaper.monsterspellbooks.entity.model.ShockEntity.ShockEntityModel;
import net.redreaper.monsterspellbooks.entity.model.ShockEntity.ShockEntityRenderer;
import net.redreaper.monsterspellbooks.entity.model.SoulWizardEntity.SoulWizardModel;
import net.redreaper.monsterspellbooks.entity.model.SoulWizardEntity.SoulWizardRenderer;
import net.redreaper.monsterspellbooks.entity.model.VileSkeleton.VileSkeletonRenderer;
import net.redreaper.monsterspellbooks.entity.spells.ancient_flash.AncientFlashRenderer;
import net.redreaper.monsterspellbooks.entity.spells.ancient_lightning_lance.AncientLightningLanceRenderer;
import net.redreaper.monsterspellbooks.entity.spells.blood_pierce_bullet.BloodPierceRenderer;
import net.redreaper.monsterspellbooks.entity.spells.blood_thorn.BloodThornRenderer;
import net.redreaper.monsterspellbooks.entity.spells.bone_dagger.BoneDaggerRenderer;
import net.redreaper.monsterspellbooks.entity.spells.brimstone_buzzsaw.BrimstoneBuzzsawRenderer;
import net.redreaper.monsterspellbooks.entity.spells.brimstone_rain.BrimstoneFireballRenderer;
import net.redreaper.monsterspellbooks.entity.spells.cauterizing_touch.CauterizingTouchRenderer;
import net.redreaper.monsterspellbooks.entity.spells.dragon_charge.DragonChargeRenderer;
import net.redreaper.monsterspellbooks.entity.spells.frenzied_burst.FrenziedBurstRenderer;
import net.redreaper.monsterspellbooks.entity.spells.frenzied_storm.FrenzyFireBallRenderer;
import net.redreaper.monsterspellbooks.entity.spells.graveyard_fissure.GraveyardHandRenderer;
import net.redreaper.monsterspellbooks.entity.spells.guardians_neutralizer.GuardiansNeutralizerRenderer;
import net.redreaper.monsterspellbooks.entity.spells.hallow_slash.HallowSlashRenderer;
import net.redreaper.monsterspellbooks.entity.spells.ice_arsenal.IceArsenalSwordRenderer;
import net.redreaper.monsterspellbooks.entity.spells.napalm_orb.NapalmOrbRenderer;
import net.redreaper.monsterspellbooks.entity.spells.paladin_throw.HolyHammerRenderer;
import net.redreaper.monsterspellbooks.entity.spells.poison_quill.PoisonQuillRenderer;
import net.redreaper.monsterspellbooks.entity.spells.putrescence_mass.PutrescenceMassRenderer;
import net.redreaper.monsterspellbooks.entity.spells.raigo.RaigoRenderer;
import net.redreaper.monsterspellbooks.entity.spells.rancorcall.RancorSkullRenderer;
import net.redreaper.monsterspellbooks.entity.spells.sangunite_eviceration.SanguiniteEviscerationRenderer;
import net.redreaper.monsterspellbooks.entity.spells.soul_firebolt.SoulFireBoltRenderer;
import net.redreaper.monsterspellbooks.entity.spells.space_rupture.SpaceRuptureRenderer;
import net.redreaper.monsterspellbooks.entity.spells.spectral_blast.SpectralBlastRenderer;
import net.redreaper.monsterspellbooks.entity.spells.static_cleave.StaticCleaveRenderer;
import net.redreaper.monsterspellbooks.entity.spells.stray_grasp.StrayGraspRenderer;
import net.redreaper.monsterspellbooks.entity.spells.vile_slash.VileSlashRenderer;
import net.redreaper.monsterspellbooks.entity.spells.wither_bomb.BigSkullProjectileRenderer;
import net.redreaper.monsterspellbooks.entity.spells.wither_nova.WitherNovaRenderer;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModFluids;
import net.redreaper.monsterspellbooks.init.ModParticleTypes;
import net.redreaper.monsterspellbooks.particle.*;
import net.redreaper.monsterspellbooks.render.ModChargeSpellLayer;


@SuppressWarnings("removal")
@EventBusSubscriber(modid = MonstersSpellbooks.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void registerRenderer(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer(ModEntities.SANGUINITE_EVISCERATION.get(), SanguiniteEviscerationRenderer::new);
        event.registerEntityRenderer(ModEntities.BLOOD_PIERCE_VISUAL_ENTITY.get(), BloodPierceRenderer::new);
        event.registerEntityRenderer(ModEntities.CAUTERIZING_TOUCH.get(), CauterizingTouchRenderer::new);
        event.registerEntityRenderer(ModEntities.BRIMSTONE_BUZZSAW_PROJECTILE.get(), BrimstoneBuzzsawRenderer::new);
        event.registerEntityRenderer(ModEntities.FRENZIED_BURST_VISUAL_ENTITY.get(), FrenziedBurstRenderer::new);
        event.registerEntityRenderer(ModEntities.SNOW_CLOUD.get(), NoopRenderer::new);
        event.registerEntityRenderer(ModEntities.POWDER_SNOW_SPLASH.get(), NoopRenderer::new);
        event.registerEntityRenderer(ModEntities.PUTRESCENCE_FIELD.get(), NoopRenderer::new);
        event.registerEntityRenderer(ModEntities.DISTORTION_FIELD.get(), NoopRenderer::new);
        event.registerEntityRenderer(ModEntities.SOUL_FIRE_FIELD.get(), NoopRenderer::new);
        event.registerEntityRenderer(ModEntities.STATIC_FIELD.get(), NoopRenderer::new);
        event.registerEntityRenderer(ModEntities.BRIMSTONE_FIELD.get(), NoopRenderer::new);
        event.registerEntityRenderer(ModEntities.ICE_SWORD.get(), IceArsenalSwordRenderer::new);
        event.registerEntityRenderer(ModEntities.ANCIENT_FLASH.get(), AncientFlashRenderer::new);
        event.registerEntityRenderer(ModEntities.VILE_SLASH_PROJECTILE.get(), VileSlashRenderer::new);
        event.registerEntityRenderer(ModEntities.HALLOW_SLASH_PROJECTILE.get(), HallowSlashRenderer::new);
        event.registerEntityRenderer(ModEntities.STATIC_CLEAVE.get(), StaticCleaveRenderer::new);
        event.registerEntityRenderer(ModEntities.LIFE_DRAIN_PROJECTILE.get(), NoopRenderer::new);
        event.registerEntityRenderer(ModEntities.STEAM_STEAM.get(), NoopRenderer::new);
        event.registerEntityRenderer(ModEntities.SPECTRAL_BLAST_VISUAL_ENTITY.get(), SpectralBlastRenderer::new);
        event.registerEntityRenderer(ModEntities.GUARDIAN_BEAM_VISUAL_ENTITY.get(), GuardiansNeutralizerRenderer::new);
        event.registerEntityRenderer(ModEntities.WITHER_NOVA_VISUAL_ENTITY.get(), WitherNovaRenderer::new);
        event.registerEntityRenderer(ModEntities.WITHER_BOMB.get(), (context) -> new BigSkullProjectileRenderer(context, MonstersSpellbooks.id("textures/entity/wither_bomb/wither_bomb.png")));
        event.registerEntityRenderer(ModEntities.NAPALM_ORB.get(), NapalmOrbRenderer::new);
        event.registerEntityRenderer(ModEntities.PUTRESCENCE_MASS.get(), PutrescenceMassRenderer::new);
        event.registerEntityRenderer(ModEntities.SPACE_RUPTURE.get(), SpaceRuptureRenderer::new);
        event.registerEntityRenderer(ModEntities.ANCIENT_LIGHTNING_LANCE_PROJECTILE.get(), AncientLightningLanceRenderer::new);
        event.registerEntityRenderer(ModEntities.RAIGO.get(), RaigoRenderer::new);
        event.registerEntityRenderer(ModEntities.BLOOD_THORN.get(), BloodThornRenderer::new);
        event.registerEntityRenderer(ModEntities.SOUL_CHAIN.get(), NoopRenderer::new);
        event.registerEntityRenderer(ModEntities.ANCIENT_LIGHTNING_STRIKE.get(), NoopRenderer::new);
        event.registerEntityRenderer(ModEntities.SMALL_FRENZY_FIREBALL.get(), (context) -> new FrenzyFireBallRenderer(context, 0.75f));
        event.registerEntityRenderer(ModEntities.SMALL_BRIMSTONE_FIREBALL.get(), (context) -> new BrimstoneFireballRenderer(context, 0.50f));
        event.registerEntityRenderer(ModEntities.DRAGON_CHARGE_PROJECTILE.get(), (context) -> new DragonChargeRenderer(context, 1));
        event.registerEntityRenderer(ModEntities.STRAY_GRASP.get(), StrayGraspRenderer::new);
        event.registerEntityRenderer(ModEntities.HOLY_HAMMER.get(), HolyHammerRenderer::new);
        event.registerEntityRenderer(ModEntities.RANCOR_SKULL.get(), RancorSkullRenderer::new);
        event.registerEntityRenderer(ModEntities.BONE_DAGGER.get(), BoneDaggerRenderer::new);
        event.registerEntityRenderer(ModEntities.POISON_QUILL.get(), PoisonQuillRenderer::new);
        event.registerEntityRenderer(ModEntities.GRAVEYARD_HAND.get(), GraveyardHandRenderer::new);
        event.registerEntityRenderer(ModEntities.SOUL_FIREBOLT_PROJECTILE.get(), SoulFireBoltRenderer::new);

        event.registerEntityRenderer(ModEntities.VILE_SKELETON.get(), VileSkeletonRenderer::new);
        event.registerEntityRenderer(ModEntities.DWARVEN_SPHERE.get(), DwarvenSphereRenderer::new);
        event.registerEntityRenderer(ModEntities.DWARVEN_SLICER.get(), DwarvenSlicerRenderer::new);
        event.registerEntityRenderer(ModEntities.SHOCK.get(), context -> {return new ShockEntityRenderer(context, new ShockEntityModel());});
        event.registerEntityRenderer(ModEntities.AEGIS.get(), context -> {return new AegisEntityRenderer(context, new AegisEntityModel());});
        event.registerEntityRenderer(ModEntities.DRIPPLER.get(), context -> {return new DripplerEntityRenderer(context, new DripplerEntityModel());});
        event.registerEntityRenderer(ModEntities.JUNGLE_WHISPERER.get(), context -> {return new JungleWhispererRenderer(context, new JungleWhispererModel());});
        event.registerEntityRenderer(ModEntities.BLASTLING.get(), context -> {return new BlastlingRenderer(context, new BlastlingModel());});

        event.registerEntityRenderer(ModEntities.SUMMONED_DEATH_KNIGHT.get(), context -> {return new DeathKnightRenderer(context, new DeathKnightModel());});
        event.registerEntityRenderer(ModEntities.SUMMONED_VILE_SKELETON.get(), VileSkeletonRenderer::new);
        event.registerEntityRenderer(ModEntities.SUMMONED_AEGIS.get(), context -> {return new AegisEntityRenderer(context, new AegisEntityModel());});
        event.registerEntityRenderer(ModEntities.SOUL_WIZARD.get(), context -> {return new SoulWizardRenderer(context, new SoulWizardModel());});
        event.registerEntityRenderer(ModEntities.POISON_QUILL_VINE.get(), context -> {return new PoisonQuillVineRenderer(context, new PoisonQuillVineModel());});

    }




@SubscribeEvent
public static void registerParticles(RegisterParticleProvidersEvent event)
{
    event.registerSpriteSet(ModParticleTypes.ANCIENT_SPARKS_PARTICLE.get(), AncientSparksParticle.Provider::new);
    event.registerSpriteSet(ModParticleTypes.REAPER_FIRE_PARTICLE.get(), ReaperFireParticle.Provider::new);
    event.registerSpriteSet(ModParticleTypes.REAPER_EMBERS_PARTICLE.get(), ReaperEmberParticle.Provider::new);
    event.registerSpriteSet(ModParticleTypes.FRENZY_FIRE_PARTICLE.get(), FrenzyFireParticle.Provider::new);
    event.registerSpriteSet(ModParticleTypes.FRENZY_EMBERS_PARTICLE.get(), FrenzyEmberParticle.Provider::new);
    event.registerSpriteSet(ModParticleTypes.BRIMSTONE_FIRE_PARTICLE.get(), BrimstoneFireParticle.Provider::new);
    event.registerSpriteSet(ModParticleTypes.BRIMSTONE_EMBERS_PARTICLE.get(), BrimstoneEmberParticle.Provider::new);
    event.registerSpriteSet(ModParticleTypes.SOUL_FIRE_PARTICLE.get(), SoulFireParticle.Provider::new);
    event.registerSpriteSet(ModParticleTypes.SOUL_EMBERS_PARTICLE.get(), SoulEmberParticle.Provider::new);
    event.registerSpriteSet(ModParticleTypes.PUTRESCENCE_BUBBLE_PARTICLE.get(), PutrescenceBubbleParticle.Provider::new);
    event.registerSpriteSet(ModParticleTypes.STEAM.get(), SteamParticle.Provider::new);
    event.registerSpriteSet(ModParticleTypes.ELECTRIC_SMOKE.get(), ElectricSmokeParticle.Provider::new);
    event.registerSpriteSet(ModParticleTypes.ENDER_SMOKE.get(), EnderSmokeParticle.Provider::new);
    event.registerSpriteSet(ModParticleTypes.SOUL_SMOKE.get(), SoulSmokeParticle.Provider::new);
    event.registerSpriteSet(ModParticleTypes.BONE_FRAGMENTS.get(), BoneFragmentsParticle.Provider::new);
    event.registerSpriteSet(ModParticleTypes.SPIRIT_STRIKE_PARTICLE.get(), SpiritStrikeParticle.Provider::new);
    event.registerSpriteSet(ModParticleTypes.SOUL_CHAIN_PARTICLE.get(), SoulChainParticle.Provider::new);
    event.registerSpriteSet(ModParticleTypes.ANCIENT_ZAP_PARTICLE.get(), AncientZapParticle.Provider::new);


}

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(FrenziedBurstRenderer.MODEL_LAYER_LOCATION, FrenziedBurstRenderer::createBodyLayer);
        event.registerLayerDefinition(SpectralBlastRenderer.MODEL_LAYER_LOCATION, SpectralBlastRenderer::createBodyLayer);
        event.registerLayerDefinition(BloodPierceRenderer.MODEL_LAYER_LOCATION, BloodPierceRenderer::createBodyLayer);
        event.registerLayerDefinition(GuardiansNeutralizerRenderer.MODEL_LAYER_LOCATION, GuardiansNeutralizerRenderer::createBodyLayer);
        event.registerLayerDefinition(WitherNovaRenderer.MODEL_LAYER_LOCATION, WitherNovaRenderer::createBodyLayer);
        event.registerLayerDefinition(NapalmOrbRenderer.MODEL_LAYER_LOCATION, NapalmOrbRenderer::createBodyLayer);
        event.registerLayerDefinition(BigSkullProjectileRenderer.MODEL_LAYER_LOCATION, BigSkullProjectileRenderer::createBodyLayer);
        event.registerLayerDefinition(SoulFireBoltRenderer.MODEL_LAYER_LOCATION, SoulFireBoltRenderer::createBodyLayer);


    }

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerFluidType(new SimpleClientFluidType(MonstersSpellbooks.id("block/raw_sanguinite")), ModFluids.RAW_SANGUINITE_TYPE);
        event.registerFluidType(new SimpleClientFluidType(MonstersSpellbooks.id("block/putrescence")), ModFluids.PUTRESCENCE_TYPE);
        event.registerFluidType(new SimpleClientFluidType(MonstersSpellbooks.id("block/void_fluid")), ModFluids.VOID_FLUID_TYPE);
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.AddLayers event) {
        addLayerToPlayerSkin(event, PlayerSkin.Model.SLIM);
        addLayerToPlayerSkin(event, PlayerSkin.Model.WIDE);
        for (EntityType type : event.getEntityTypes()) {
            var renderer = event.getRenderer(type);
            if (renderer instanceof LivingEntityRenderer livingRenderer) {
                livingRenderer.addLayer(new SpellTargetingLayer.Vanilla<>(livingRenderer));
            }
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void addLayerToPlayerSkin(EntityRenderersEvent.AddLayers event, PlayerSkin.Model skinName) {
        EntityRenderer<? extends Player> render = event.getSkin(skinName);
        if (render instanceof LivingEntityRenderer livingRenderer) {
            livingRenderer.addLayer(new ModChargeSpellLayer.Vanilla<>(livingRenderer));
        }
    }
}

