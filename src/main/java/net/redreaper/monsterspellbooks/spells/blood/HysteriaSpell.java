package net.redreaper.monsterspellbooks.spells.blood;

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
import net.redreaper.monsterspellbooks.effect.BloodLustMobEffect;
import net.redreaper.monsterspellbooks.init.ModMobEffects;

import java.util.List;

@AutoSpellConfig
public class HysteriaSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "hysteria");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.effect_length", Utils.timeFromTicks(getSpellPower(spellLevel, caster) * 10, 1)),
                Component.translatable("attribute.modifier.plus.1", Utils.stringTruncation(getPercentAttackDamage(spellLevel, caster), 0), Component.translatable("attribute.name.generic.attack_damage")),
                Component.translatable("attribute.modifier.plus.1", Utils.stringTruncation(getPercentSpellPower(spellLevel, caster), 0), Component.translatable("attribute.name.generic.attack_speed")),
                Component.translatable("attribute.modifier.plus.1", Utils.stringTruncation(getPercentSpeed(spellLevel, caster), 0), Component.translatable("attribute.name.generic.movement_speed"))
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(SchoolRegistry.BLOOD_RESOURCE)
            .setMaxLevel(3)
            .setCooldownSeconds(240)
            .build();

    public HysteriaSpell() {
        this.manaCostPerLevel = 25;
        this.baseSpellPower = 15;
        this.spellPowerPerLevel = 5;
        this.castTime = 0;
        this.baseManaCost = 150;
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
        entity.addEffect(new MobEffectInstance(ModMobEffects.BLOODLUST, (int) (getSpellPower(spellLevel, entity) * 10), spellLevel - 1, false, false, true));
        entity.addEffect(new MobEffectInstance(MobEffectRegistry.HEARTSTOP, (int) (getSpellPower(spellLevel, entity) * 10),0));
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    private float getPercentAttackDamage(int spellLevel, LivingEntity entity) {
        return spellLevel * BloodLustMobEffect.ATTACK_DAMAGE_PER_LEVEL * 100;
    }

    private float getPercentSpellPower(int spellLevel, LivingEntity entity) {
        return spellLevel * BloodLustMobEffect.ATTACK_SPEED_PER_LEVEL * 100;
    }

    private float getPercentSpeed(int spellLevel, LivingEntity entity) {
        return spellLevel * BloodLustMobEffect.SPEED_PER_LEVEL * 100;
    }

    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.SELF_CAST_ANIMATION;
    }
}
