package net.redreaper.monsterspellbooks.spells.ender;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.effect.EndersentStrengthMobEffect;
import net.redreaper.monsterspellbooks.init.ModMobEffects;

import java.util.List;

public class EndersentFormSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "endersent_form");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.effect_length", Utils.timeFromTicks(getSpellPower(spellLevel, caster) * 20, 1)),
                Component.translatable("attribute.modifier.plus.1", Utils.stringTruncation(getAttackDamage(spellLevel, caster), 0), Component.translatable("attribute.name.generic.attack_damage")),
                Component.translatable("attribute.modifier.take.1", Utils.stringTruncation(getAttackSpeed(spellLevel, caster), 0), Component.translatable("attribute.name.generic.attack_speed")),
                Component.translatable("attribute.modifier.plus.1", Utils.stringTruncation(getAttackKnockback(spellLevel, caster), 0), Component.translatable("attribute.name.generic.attack_knockback")),
                Component.translatable("attribute.modifier.plus.1", Utils.stringTruncation(getKnockbackResistance(spellLevel, caster), 0), Component.translatable("attribute.name.generic.knockback_resistance")),
                Component.translatable("attribute.modifier.plus.1", Utils.stringTruncation(getScale(spellLevel, caster), 0), Component.translatable("attribute.name.generic.scale"))
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.LEGENDARY)
            .setSchoolResource(SchoolRegistry.ENDER_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(40)
            .build();

    public EndersentFormSpell() {
        this.manaCostPerLevel = 25;
        this.baseSpellPower = 30;
        this.spellPowerPerLevel = 8;
        this.castTime = 0;
        this.baseManaCost = 50;
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
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {

        entity.addEffect(new MobEffectInstance(ModMobEffects.ENDERSENT_STRENGTH, (int) (getSpellPower(spellLevel, entity) * 20), 0, false, false, true));

        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    private float getAttackDamage (int spellLevel, LivingEntity entity) {
        return EndersentStrengthMobEffect.ATTACK_PER_LEVEL* 100;
    }

    private float getAttackSpeed (int spellLevel, LivingEntity entity) {
        return EndersentStrengthMobEffect.ATTACK_SPEED_PER_LEVEL* -100;
    }

    private float getAttackKnockback (int spellLevel, LivingEntity entity) {
        return EndersentStrengthMobEffect.ATTACK_KNOCKBACK_PER_LEVEL* 100;
    }

    private float getKnockbackResistance (int spellLevel, LivingEntity entity) {
        return EndersentStrengthMobEffect.KNOCKBACK_RESISTANCE_PER_LEVEL* 100;
    }

    private float getScale (int spellLevel, LivingEntity entity) {
        return EndersentStrengthMobEffect.SCALE_PER_LEVEL* 100;
    }

    @Override
    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.SELF_CAST_ANIMATION;
    }
}

