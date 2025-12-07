package net.redreaper.monsterspellbooks.spells.fire;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.effect.OverheatMobEffect;
import net.redreaper.monsterspellbooks.init.ModMobEffects;

import java.util.List;

@AutoSpellConfig
public class OverheatSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "overheat");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.effect_length", Utils.timeFromTicks(getSpellPower(spellLevel, caster) * 20, 1)),
                Component.translatable("attribute.modifier.plus.1", Utils.stringTruncation(getPercentAttackDamage(spellLevel, caster), 0), Component.translatable("attribute.name.generic.attack_damage")),
                Component.translatable("attribute.modifier.plus.1", Utils.stringTruncation(getPercentSpellPower(spellLevel, caster), 0), Component.translatable("attribute.irons_spellbooks.fire_spell_power")),
                Component.translatable("ui.irons_spellbooks.rend", Utils.stringTruncation((getRendAmplifier(spellLevel, caster) + 1) * 5, 1))
                );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(SchoolRegistry.FIRE_RESOURCE)
            .setMaxLevel(3)
            .setCooldownSeconds(40)
            .build();

    public OverheatSpell() {
        this.manaCostPerLevel = 25;
        this.baseSpellPower = 30;
        this.spellPowerPerLevel = 8;
        this.castTime = 0;
        this.baseManaCost = 50;
    }

    public CastType getCastType() {
        return CastType.INSTANT;
    }

    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    public ResourceLocation getSpellResource() {
        return spellId;
    }

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {

        entity.addEffect(new MobEffectInstance(ModMobEffects.OVERHEAT, (int) (getSpellPower(spellLevel, entity) * 20), spellLevel - 1, false, false, true));

        entity.addEffect(new MobEffectInstance(MobEffectRegistry.REND, (int) (getSpellPower(spellLevel, entity) * 20), getRendAmplifier(spellLevel, entity)));

        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    private float getPercentAttackDamage(int spellLevel, LivingEntity entity) {
        return spellLevel * OverheatMobEffect.ATTACK_DAMAGE_PER_LEVEL * 100;
    }

    private float getPercentSpellPower(int spellLevel, LivingEntity entity) {
        return spellLevel * OverheatMobEffect.FIRE_SPELL_POWER_PER_LEVEL * 100;
    }

    public int getRendAmplifier(int spellLevel, LivingEntity caster) {
        return spellLevel-1;
    }

    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.SELF_CAST_ANIMATION;
    }
}
