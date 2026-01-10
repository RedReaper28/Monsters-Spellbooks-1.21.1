package net.redreaper.monsterspellbooks.spells.ice;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.effect.BlizzardAspectMobEffect;
import net.redreaper.monsterspellbooks.init.ModMobEffects;

import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class BlizzardAspectSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "blizzard_aspect");

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.EPIC)
            .setSchoolResource(SchoolRegistry.ICE_RESOURCE)
            .setMaxLevel(8)
            .setCooldownSeconds(120)
            .build();

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(BlizzardAspectMobEffect.getDamageFromAmplifier(getAmplifierForLevel(spellLevel, caster), caster), 2)),
                Component.translatable("ui.irons_spellbooks.radius", 20),
                Component.translatable("ui.irons_spellbooks.effect_length", Utils.timeFromTicks(getDurationTicks(spellLevel, caster), 1))
        );
    }

    public BlizzardAspectSpell() {
        this.manaCostPerLevel = 15;
        this.baseSpellPower = 2;
        this.spellPowerPerLevel = 1;
        this.castTime = 60;
        this.baseManaCost = 145;
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
    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of(SoundRegistry.FROSTWAVE_PREPARE.value());
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        entity.addEffect(new MobEffectInstance(ModMobEffects.BLIZZARD_ASPECT, getDurationTicks(spellLevel, entity), getAmplifierForLevel(spellLevel, entity), false, false, true));
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    private int getAmplifierForLevel(int spellLevel, LivingEntity caster) {
        return 8 + (int) ((spellLevel - 1) * getEntityPowerMultiplier(caster));
    }

    public int getDurationTicks(int spellLevel, LivingEntity caster) {
        return (int) ((20 + (2 * (spellLevel - 1) * getEntityPowerMultiplier(caster))) * 20);
    }
}
