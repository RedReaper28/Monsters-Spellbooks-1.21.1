package net.redreaper.monsterspellbooks.spells.hydro;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.acetheeldritchking.aces_spell_utils.registries.ASSchoolRegistry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.init.ModMobEffects;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class GlowInkReleaseSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "glow_ink_release");

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.effect_length", Utils.timeFromTicks(getDuration(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.radius", new Object[]{Utils.stringTruncation((double) this.getRadius(spellLevel, caster), 2)})
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.COMMON)
            .setSchoolResource(ASSchoolRegistry.HYDRO_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(60)
            .build();

    public GlowInkReleaseSpell() {
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

    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of(SoundEvents.SQUID_HURT);
    }

    public ResourceLocation getSpellResource() {
        return this.spellId;
    }

    public int getDuration(int spellLevel, LivingEntity caster) {
        return (int) (this.getSpellPower(spellLevel, caster) * 20f);
    }

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        float radius = getRadius(spellLevel, entity);
        MagicManager.spawnParticles(level, new BlastwaveParticleOptions(SchoolRegistry.ICE.get().getTargetingColor(), radius), entity.getX(), entity.getY() + .165f, entity.getZ(), 1, 0, 0, 0, 0, true);
        MagicManager.spawnParticles(level, ParticleTypes.GLOW_SQUID_INK, entity.getX(), entity.getY() + (double) 1.0F, entity.getZ(), 50, (double) 0.0F, (double) 0.0F, (double) 0.0F, (double) 1.0F, false);
        level.getEntities(entity, entity.getBoundingBox().inflate((double) radius, (double) 4.0F, (double) radius), (target) -> !DamageSources.isFriendlyFireBetween(target, entity) && Utils.hasLineOfSight(level, entity, target, false)).forEach((target) -> {
            if (target instanceof LivingEntity livingEntity) {
                if (livingEntity.distanceToSqr(entity) < (double) (radius * radius)) {
                    int i = this.getDuration(spellLevel, entity);
                    livingEntity.addEffect(new MobEffectInstance(MobEffectRegistry.GUIDING_BOLT, i,0));
                    livingEntity.addEffect(new MobEffectInstance(ModMobEffects.FOCUS, i, 0));
                    livingEntity.addEffect(new MobEffectInstance(ModMobEffects.STUNNED, i, 1));
                }
            }
        });
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
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
