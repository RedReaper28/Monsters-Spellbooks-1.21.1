package net.redreaper.monsterspellbooks.spells.necro;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.CameraShakeData;
import io.redspace.ironsspellbooks.api.util.CameraShakeManager;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.capabilities.magic.SyncedSpellData;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.spells.CastingMobAimingData;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.spells.wither_nova.WitherNovaVisualEntity;
import net.redreaper.monsterspellbooks.init.ModMobEffects;
import net.redreaper.monsterspellbooks.init.ModSpellSchools;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class WitherNovaSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "wither_nova");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.distance", Utils.stringTruncation(getRange(spellLevel), 1)));
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
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        entity.addEffect(new MobEffectInstance(ModMobEffects.PARALYSIS, 80, 2, true, true, true));
        entity.addEffect(new MobEffectInstance(ModMobEffects.FOCUS, 20, 2, true, true, true));

        CameraShakeManager.addCameraShake(new CameraShakeData(level, 30, entity.position(), 10));
        Vec3 forward = entity.getForward();
        if (playerMagicData.getAdditionalCastData() instanceof CastingMobAimingData aimData && entity instanceof Mob mob) {
            forward = aimData.getForward(entity);
        }
        var hitResult = Utils.raycastForEntity(level, entity, entity.getEyePosition(), entity.getEyePosition().add(forward.scale(getRange(spellLevel))), true, .50f, Utils::canHitWithRaycast);
        if (hitResult.getType() == HitResult.Type.ENTITY) {
            Entity target = ((EntityHitResult) hitResult).getEntity();
            if (target instanceof LivingEntity) {
                if (DamageSources.applyDamage(target, getDamage(spellLevel, entity), getDamageSource(entity))) {
                    ((LivingEntity) target).addEffect(new MobEffectInstance(ModMobEffects.SOUL_REND,20,0, false, true, true));
                }
            }
        }
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    public static float getRange(int level) {
        return 100;
    }

    private float getDamage(int spellLevel, LivingEntity caster)
    {
        return (float) (1.5 * getSpellPower(spellLevel, caster));
    }

    @Override
    public SpellDamageSource getDamageSource(@Nullable Entity projectile, Entity attacker) {
        return super.getDamageSource(projectile, attacker).setIFrames(0);
    }

    public static void ambientParticles(LivingEntity entity, SyncedSpellData spellData) {
        Vec3 vec3 = entity.getBoundingBox().getCenter();
        for (int i = 0; i < 2; i++) {
            Vec3 pos = vec3.add(Utils.getRandomVec3(entity.getBbHeight() * 2));
            Vec3 motion = vec3.subtract(pos).scale(0.10f);
            entity.level().addParticle(ModParticleHelper.SOUL_FIRE, pos.x, pos.y, pos.z, motion.x, motion.y, motion.z);
        }
    }

    public boolean allowLooting() {
        return false;
    }
}
