package net.redreaper.monsterspellbooks.spells.ice;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.capabilities.magic.TargetEntityCastData;
import io.redspace.ironsspellbooks.effect.HastenedEffect;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.effect.FrostCoatingMobEffect;
import net.redreaper.monsterspellbooks.init.ModMobEffects;

import java.util.List;
import java.util.Optional;

public class FrostCoatingSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "frost_coating");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.monsterspellbooks.frost_coating", Utils.stringTruncation(HastenedEffect.getPercentForAmplifier(getAmplifier(spellLevel, caster), caster) * 100, 1)),
                Component.translatable("ui.irons_spellbooks.effect_length", Utils.timeFromTicks(getDuration(spellLevel, caster), 1))
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.COMMON)
            .setSchoolResource(SchoolRegistry.ICE_RESOURCE)
            .setMaxLevel(8)
            .setCooldownSeconds(40)
            .build();

    public FrostCoatingSpell() {
        this.manaCostPerLevel = 25;
        this.baseSpellPower = 30;
        this.spellPowerPerLevel = 8;
        this.castTime = 0;
        this.baseManaCost = 100;
    }

    @Override
    public CastType getCastType() {
        return CastType.INSTANT;
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
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        if (playerMagicData.getAdditionalCastData() instanceof TargetEntityCastData targetData) {
            if (targetData.getTarget((ServerLevel) world) instanceof LivingEntity targetEntity) {
                targetEntity.addEffect(new MobEffectInstance(ModMobEffects.FROST_COATING, getDuration(spellLevel, entity), getAmplifier(spellLevel, entity), false, false, true));
                MagicManager.spawnParticles(world, ParticleHelper.SNOWFLAKE, targetEntity.getX(), targetEntity.getY() + .25, targetEntity.getZ(), 15, targetEntity.getBbWidth() * 0.5, targetEntity.getBbWidth() * 0.5, targetEntity.getBbWidth() * 0.5, 0, false);
            }
        }
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    public int getAmplifier(int spellLevel, LivingEntity caster) {
        int base = 8 - 1; // 20%
        return (int) (base * getEntityPowerMultiplier(caster));
    }

    public int getDuration(int spellLevel, LivingEntity caster) {
        return (int) (getSpellPower(spellLevel, caster) * 20);
    }

    @Override public Optional<SoundEvent> getCastFinishSound() {return Optional.of(SoundRegistry.FROSTBITE_FREEZE.get());}

    @Override public AnimationHolder getCastStartAnimation() {return SpellAnimations.SELF_CAST_ANIMATION;}

}
