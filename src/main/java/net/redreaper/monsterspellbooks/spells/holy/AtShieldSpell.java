package net.redreaper.monsterspellbooks.spells.holy;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellAnimations;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.spells.at_shield.AtShield;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.init.ModMobEffects;
import net.redreaper.monsterspellbooks.spells.AbstractSummonWeaponSpell;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class AtShieldSpell extends AbstractSummonWeaponSpell<AtShield> {
    private static final int GUARD_EFFECT_REFRESH_TICK = 5;

    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "at_shield");
    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(SchoolRegistry.HOLY_RESOURCE)
            .setMaxLevel(1)
            .setCooldownSeconds(10)
            .build();

    public AtShieldSpell() {
        super(AtShield.class);
        this.baseSpellPower = 100;
        this.spellPowerPerLevel = 30;
        this.baseManaCost = 30;
        this.manaCostPerLevel = 5;
        this.castTime = 200;
    }

    public ResourceLocation getSpellResource() {
        return this.spellId;
    }

    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override public int getCastTime(int spellLevel) {
        return castTime + 5 * spellLevel;
    }

    @Override public CastType getCastType() {
        return CastType.CONTINUOUS;
    }

    public boolean canBeInterrupted(@Nullable Player player) {
        return false;
    }

    public int getEffectiveCastTime(int spellLevel, LivingEntity entity) {
        return this.getCastTime(spellLevel);
    }

    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of((SoundEvent) SoundRegistry.HOLY_CAST.get());
    }

    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.empty();
    }

    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.ANIMATION_CONTINUOUS_CAST_ONE_HANDED;
    }

    public AnimationHolder getCastFinishAnimation() {
        return AnimationHolder.none();
    }

    public AtShield onCastNoWeapon(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData) {
        AtShield summon = new AtShield(ModEntities.AT_SHIELD.get(), level, entity);
        level.addFreshEntity(summon);
        this.applyGuardState(level, entity);
        return summon;
    }

    public void onCastTickWithWeapon(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData, @NotNull AtShield weapon) {
        this.applyGuardState(level, entity);
    }

    public AbstractSummonWeaponSpell.CompleteCastTypes onCastCompleteWithWeapon(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData, boolean cancelled, @NotNull AtShield weapon) {
        return CompleteCastTypes.KEEP_WEAPON;
    }

    private void applyGuardState(Level level, LivingEntity entity) {
        if (!level.isClientSide) {
            int guardAmplifier = 1;
            entity.addEffect(new MobEffectInstance(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(ModMobEffects.DIVINE_PROTECTION.get()), 5, guardAmplifier, false, false, true));
        }
    }
}
