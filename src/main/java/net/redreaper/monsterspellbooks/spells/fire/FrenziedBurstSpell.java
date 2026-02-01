package net.redreaper.monsterspellbooks.spells.fire;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.effect.MadnessMobEffect;
import net.redreaper.monsterspellbooks.entity.spells.frenzied_burst.FrenziedBurstVisualEntity;
import net.redreaper.monsterspellbooks.init.ModMobEffects;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;

import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class FrenziedBurstSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "frenzied_burst");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.distance", Utils.stringTruncation(getRange(spellLevel, caster), 1)),
                Component.translatable("ui.irons_spellbooks.effect_length", Utils.timeFromTicks(getDuration(spellLevel, caster), 2)),
                Component.translatable("attribute.modifier.take.1", Utils.stringTruncation(getPercentManaReduction(spellLevel, caster), 0), Component.translatable("attribute.irons_spellbooks.max_mana"))
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.EPIC)
            .setSchoolResource(SchoolRegistry.FIRE_RESOURCE)
            .setMaxLevel(3)
            .setCooldownSeconds(45)
            .build();

    public FrenziedBurstSpell() {
        this.manaCostPerLevel = 15;
        this.baseSpellPower = 6;
        this.spellPowerPerLevel =3;
        this.castTime = 30;
        this.baseManaCost = 30;
    }

    @Override
    public CastType getCastType() {
        return CastType.LONG;
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
    public Optional<SoundEvent> getCastStartSound() {return Optional.of(SoundRegistry.SUNBEAM_WINDUP.get());}

    @Override
        public Optional<SoundEvent> getCastFinishSound() {return Optional.of(SoundRegistry.FIRE_ERUPTION_SLAM.get());}

    @Override public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.CHARGE_SPIT_ANIMATION;
    }

    @Override public AnimationHolder getCastFinishAnimation() {
        return SpellAnimations.SPIT_FINISH_ANIMATION;
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        var hitResult = Utils.raycastForEntity(level, entity, getRange(spellLevel, entity), true, .15f);
        level.addFreshEntity(new FrenziedBurstVisualEntity(level, entity.getEyePosition(), hitResult.getLocation(), entity));
        if (hitResult.getType() == HitResult.Type.ENTITY) {
            int i = getDuration(spellLevel, entity);
            Entity target = ((EntityHitResult) hitResult).getEntity();
            if (target instanceof LivingEntity) {
                DamageSources.applyDamage(target, this.getDamage(spellLevel, entity), this.getDamageSource(entity));
                ((LivingEntity) target).addEffect(new MobEffectInstance(ModMobEffects.MADNESS,i,getFrenzyAmplifier(spellLevel, entity), false, true, true));
                target.setRemainingFireTicks(Math.min(i / 2, 160));
                MagicManager.spawnParticles(level, ModParticleHelper.FRENZY_FIRE, hitResult.getLocation().x, target.getY(), hitResult.getLocation().z, 4, 0, 0, 0, .3, true);
            }
        } else if (hitResult.getType() == HitResult.Type.BLOCK) {
            MagicManager.spawnParticles(level, ModParticleHelper.FRENZY_FIRE, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z, 4, 0, 0, 0, .3, true);
        }
        MagicManager.spawnParticles(level, ModParticleHelper.FRENZY_FIRE, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z, 50, 0, 0, 0, .3, false);
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    public int getDuration(int spellLevel, LivingEntity caster) {
        return (int) (getSpellPower(spellLevel, caster) * 100);
    }

    public int getFrenzyAmplifier(int spellLevel, LivingEntity caster) {
        return 2 + (spellLevel);
    }

    public static float getRange(int level, LivingEntity caster) {
        return 20;
    }

    private float getPercentManaReduction(int spellLevel, LivingEntity entity) {
        return spellLevel * MadnessMobEffect.MANA_REDUCTION_PER_LEVEL * 100;
    }

    public float getDamage(int spellLevel, LivingEntity caster) {
        if (caster == null) {
            return this.getSpellPower(spellLevel, (Entity)null) * 7.0F;
        } else {
            double firePower = caster.getAttributeValue(AttributeRegistry.FIRE_SPELL_POWER);
            double bloodPower = caster.getAttributeValue(AttributeRegistry.ELDRITCH_SPELL_POWER);
            return (float)((double)5.0F + (double)1.5F * (double)this.getSpellPower(spellLevel, caster) * ((double)0.5F * firePower + (double)0.5F * bloodPower));
        }
    }
}
