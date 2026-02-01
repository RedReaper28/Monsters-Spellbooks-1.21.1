package net.redreaper.monsterspellbooks.spells.necro;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.CameraShakeData;
import io.redspace.ironsspellbooks.api.util.CameraShakeManager;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.capabilities.magic.SyncedSpellData;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.spells.wither_nova.WitherNovaVisualEntity;
import net.redreaper.monsterspellbooks.init.ModMobEffects;
import net.redreaper.monsterspellbooks.init.ModSpellSchools;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class WitherNovaSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "wither_nova");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.distance", Utils.stringTruncation(getRange(spellLevel, caster), 1)),
                Component.translatable("ui.irons_spellbooks.effect_length", Utils.timeFromTicks(getDuration(spellLevel, caster), 2))
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.LEGENDARY)
            .setSchoolResource(ModSpellSchools.NECRO_RESOURCE)
            .setMaxLevel(3)
            .setCooldownSeconds(120)
            .build();

    public WitherNovaSpell() {
        this.manaCostPerLevel = 10;
        this.baseSpellPower = 15;
        this.spellPowerPerLevel =10;
        this.castTime = 30-5;
        this.baseManaCost = 25;
    }

    @Override
    public int getCastTime(int spellLevel) {
        return castTime + 5 * spellLevel;
    }

    @Override
    public CastType getCastType() {
        return CastType.CONTINUOUS;
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public ResourceLocation getSpellResource() {
        return spellId;
    }

    @Override
    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of(SoundEvents.WITHER_AMBIENT);
    }

    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    @Override
    public void onServerCastTick(Level level, int spellLevel, LivingEntity entity, @Nullable MagicData playerMagicData) {
        if (playerMagicData != null && (playerMagicData.getCastDurationRemaining() + 1) % 5 == 0) {
            shot(level, spellLevel, entity);
        }
    }

    public void shot(Level level, int spellLevel, LivingEntity entity) {
        entity.addEffect(new MobEffectInstance(ModMobEffects.PARALYSIS,
                80, 2, true, true, true));
        entity.addEffect(new MobEffectInstance(ModMobEffects.FOCUS,
                getCastTime(spellLevel), 2, false, false, false));
        CameraShakeManager.addCameraShake(new CameraShakeData(level, 30, entity.position(), 10));

        var hitResult = Utils.raycastForEntity(level, entity, getRange(spellLevel, entity), true, .15f);
        level.addFreshEntity(new WitherNovaVisualEntity(level, entity.getEyePosition(), hitResult.getLocation(), entity));
        if (hitResult.getType() == HitResult.Type.ENTITY) {
            int i = getDuration(spellLevel, entity);
            Entity target = ((EntityHitResult) hitResult).getEntity();
            if (target instanceof LivingEntity) {
                DamageSources.applyDamage(target, this.getDamage(spellLevel, entity), this.getDamageSource(entity));
                ((LivingEntity) target).addEffect(new MobEffectInstance(ModMobEffects.SOUL_ROT,i,0, false, true, true));
                ((LivingEntity) target).addEffect(new MobEffectInstance(ModMobEffects.SOUL_REND,i,getAmplifier(spellLevel, entity), false, true, true));
                MagicManager.spawnParticles(level, ParticleHelper.ENDER_SPARKS, hitResult.getLocation().x, target.getY(), hitResult.getLocation().z, 4, 0, 0, 0, .3, true);
            }
        } else if (hitResult.getType() == HitResult.Type.BLOCK) {
            MagicManager.spawnParticles(level, ParticleHelper.VOID_TENTACLE_FOG, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z, 4, 0, 0, 0, .3, true);
        }
        MagicManager.spawnParticles(level, ParticleHelper.VOID_TENTACLE_FOG, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z, 50, 0, 0, 0, .3, false);
    }

    public int getDuration(int spellLevel, LivingEntity caster) {
        return (int) (getSpellPower(spellLevel, caster) * 20);
    }

    public int getAmplifier(int spellLevel, LivingEntity entity) {
        return 2 + (spellLevel - 1) / 4;
    }

    public static float getRange(int level, LivingEntity caster) {
        return 100;
    }

    private float getDamage(int spellLevel, LivingEntity caster)
    {
        return (float) (1.5 * getSpellPower(spellLevel, caster));
    }

    public static void ambientParticles(LivingEntity entity, SyncedSpellData spellData) {
        Vec3 vec3 = entity.getBoundingBox().getCenter();
        for (int i = 0; i < 2; i++) {
            Vec3 pos = vec3.add(Utils.getRandomVec3(entity.getBbHeight() * 2));
            Vec3 motion = vec3.subtract(pos).scale(0.10f);
            entity.level().addParticle(ModParticleHelper.SOUL_FIRE, pos.x, pos.y, pos.z, motion.x, motion.y, motion.z);
        }
    }
}
