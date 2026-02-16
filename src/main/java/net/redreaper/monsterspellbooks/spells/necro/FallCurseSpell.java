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
import net.acetheeldritchking.aces_spell_utils.spells.ASSpellAnimations;
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
import net.redreaper.monsterspellbooks.init.ModMobEffects;
import net.redreaper.monsterspellbooks.init.ModSpellSchools;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class FallCurseSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "fall_curse");

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.effect_length", Utils.timeFromTicks(getDuration(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.radius", new Object[]{Utils.stringTruncation((double)this.getRadius(spellLevel, caster), 2)}));
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.LEGENDARY)
            .setSchoolResource(ModSpellSchools.NECRO_RESOURCE)
            .setMaxLevel(3)
            .setCooldownSeconds(120)
            .build();

    public FallCurseSpell() {
        this.manaCostPerLevel = 25;
        this.baseSpellPower = 10;
        this.spellPowerPerLevel = 2;
        this.castTime = 35;
        this.baseManaCost = 100;
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
        return Optional.of(SoundEvents.WITHER_AMBIENT);
    }

    public ResourceLocation getSpellResource() {
        return this.spellId;
    }

    public int getDuration(int spellLevel, LivingEntity caster) {
        return (int)(this.getSpellPower(spellLevel, caster) * 10);
    }

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        float radius = getRadius(spellLevel, entity);
        int i = getDuration(spellLevel, entity);
        entity.addEffect(new MobEffectInstance(ModMobEffects.PARALYSIS,i,1));
        entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS,i));
        MagicManager.spawnParticles(level, new BlastwaveParticleOptions(SchoolRegistry.NATURE.get().getTargetingColor(), radius), entity.getX(), entity.getY() + .165f, entity.getZ(), 1, 0, 0, 0, 0, true);
        level.getEntities(entity, entity.getBoundingBox().inflate((double)radius, (double)4.0F, (double)radius), (target) -> !DamageSources.isFriendlyFireBetween(target, entity) && Utils.hasLineOfSight(level, entity, target, false)).forEach((target) -> {
            if (target instanceof LivingEntity livingEntity) {
                if (livingEntity.distanceToSqr(entity) < (double)(radius * radius)) {
                    livingEntity.addEffect(new MobEffectInstance(ModMobEffects.PARALYSIS,i,3, true, true, true));
                    livingEntity.addEffect(new MobEffectInstance(ModMobEffects.HEAVY_BODY,i,3, true, true, true));
                    livingEntity.addEffect(new MobEffectInstance(ModMobEffects.STUNNED,i,3, true, true, true));
                    livingEntity.addEffect(new MobEffectInstance(ModMobEffects.CURSE,i,0, true, true, true));
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,i,1,true, true, true));
                }
            }
            super.onCast(level, spellLevel, entity, castSource, playerMagicData);
        });
    }

    public AnimationHolder getCastFinishAnimation() {
        return ASSpellAnimations.ANIMATION_CLAP;
    }

    public float getRadius(int spellPower, LivingEntity caster) {
        return 15.0F + (float)spellPower * 2;
    }

    public boolean allowLooting() {
        return false;
    }
}
