package net.redreaper.monsterspellbooks.spells.nature;

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
import net.redreaper.monsterspellbooks.effect.MountainCorpseMobEffect;
import net.redreaper.monsterspellbooks.init.ModMobEffects;

import java.util.List;

@AutoSpellConfig
public class MountainCorpseSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "mountain_corpse");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.effect_length", Utils.timeFromTicks(getSpellPower(spellLevel, caster) * 20, 1)),
                Component.translatable("attribute.modifier.plus.1", Utils.stringTruncation(getPercentAttackDamage(spellLevel, caster), 0), Component.translatable("attribute.name.generic.attack_damage")),
                Component.translatable("attribute.modifier.plus.1", Utils.stringTruncation(getAttackKnockback(spellLevel, caster), 0), Component.translatable("attribute.name.generic.attack_knockback")),
                Component.translatable("attribute.modifier.plus.1", Utils.stringTruncation(getKnockbackResistance(spellLevel, caster), 0), Component.translatable("attribute.name.generic.knockback_resistance")),
                Component.translatable("attribute.modifier.plus.2", Utils.stringTruncation(getPercentSpellPower(spellLevel, caster), 0), Component.translatable("attribute.irons_spellbooks.nature_spell_power"))
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.LEGENDARY)
            .setSchoolResource(SchoolRegistry.NATURE_RESOURCE)
            .setMaxLevel(1)
            .setCooldownSeconds(40)
            .build();

    public MountainCorpseSpell() {
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

        entity.addEffect(new MobEffectInstance(ModMobEffects.MOUNTAIN_CORPSE, (int) (getSpellPower(spellLevel, entity) * 20), spellLevel - 1, false, false, true));

        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    private float getPercentAttackDamage(int spellLevel, LivingEntity entity) {
        return spellLevel * MountainCorpseMobEffect.ATTACK_DAMAGE_PER_LEVEL * 100;
    }

    private float getAttackKnockback (int spellLevel, LivingEntity entity) {
        return spellLevel * MountainCorpseMobEffect.ATTACK_KNOCKBACK_PER_LEVEL* 100;
    }

    private float getKnockbackResistance (int spellLevel, LivingEntity entity) {
        return spellLevel * MountainCorpseMobEffect.KNOCKBACK_RESISTANCE_PER_LEVEL* 100;
    }

    private float getPercentSpellPower(int spellLevel, LivingEntity entity) {
        return spellLevel * MountainCorpseMobEffect.NATURE_SPELL_POWER_PER_LEVEL * 100;
    }

    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.SELF_CAST_ANIMATION;
    }
}
