package net.redreaper.monsterspellbooks.spells.necro;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.init.ModSpellSchools;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class BansheeScreamSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "banshee_scream");

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.damage", new Object[]{Utils.stringTruncation((double)this.getDamage(spellLevel, caster), 2)}), Component.translatable("ui.irons_spellbooks.radius", new Object[]{Utils.stringTruncation((double)this.getRadius(spellLevel, caster), 2)}));
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.COMMON)
            .setSchoolResource(ModSpellSchools.NECRO_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(60)
            .build();

    public BansheeScreamSpell() {
        this.manaCostPerLevel = 22;
        this.baseSpellPower = 6;
        this.spellPowerPerLevel = 3;
        this.castTime = 24;
        this.baseManaCost = 90;
    }

    public DefaultConfig getDefaultConfig() {
        return this.defaultConfig;
    }

    public CastType getCastType() {
        return CastType.LONG;
    }

    public int getEffectiveCastTime(int spellLevel, @Nullable LivingEntity entity) {
        return this.getCastTime(spellLevel);
    }

    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of(SoundEvents.SCULK_SHRIEKER_SHRIEK);
    }

    public ResourceLocation getSpellResource() {
        return this.spellId;
    }

    public int getDuration(int spellLevel, LivingEntity caster) {
        return (int)(this.getSpellPower(spellLevel, caster) * 20.0F);
    }

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        float radius = getRadius(spellLevel, entity);
        MagicManager.spawnParticles(level, new BlastwaveParticleOptions(SchoolRegistry.ICE.get().getTargetingColor(), radius), entity.getX(), entity.getY() + .165f, entity.getZ(), 1, 0, 0, 0, 0, true);
        MagicManager.spawnParticles(level, ParticleTypes.SOUL, entity.getX(), entity.getY() + (double)1.0F, entity.getZ(), 50, (double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F, false);
        level.getEntities(entity, entity.getBoundingBox().inflate((double)radius, (double)4.0F, (double)radius), (target) -> !DamageSources.isFriendlyFireBetween(target, entity) && Utils.hasLineOfSight(level, entity, target, false)).forEach((target) -> {
            if (target instanceof LivingEntity livingEntity) {
                if (livingEntity.distanceToSqr(entity) < (double)(radius * radius)) {
                    int i = this.getDuration(spellLevel, entity);
                    DamageSources.applyDamage(target, this.getDamage(spellLevel, entity), this.getDamageSource(entity));
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, i, this.getWitherAmplifier(spellLevel, entity)));
                }
            }

        });
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    public int getWitherAmplifier(int spellLevel, LivingEntity caster) {
        return 1 + spellLevel;
    }

    private float getDamage(int spellLevel, LivingEntity caster) {
        return this.getSpellPower(spellLevel, caster);
    }

    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.PREPARE_CROSS_ARMS;
    }

    public AnimationHolder getCastFinishAnimation() {
        return SpellAnimations.CAST_T_POSE;
    }

    public float getRadius(int spellPower, LivingEntity caster) {
        return 5.0F + (float)spellPower * 0.5F;
    }
}