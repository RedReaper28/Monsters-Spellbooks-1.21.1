package net.redreaper.monsterspellbooks.spells.hydro;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.acetheeldritchking.aces_spell_utils.registries.ASSchoolRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.spells.razorblade_typhoon.RazorbladeTyphoonProjectile;
import net.redreaper.monsterspellbooks.init.ModSounds;

import java.util.List;
import java.util.Optional;

public class RazorbladeTyphoonSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "razor_typhoon");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getSpellPower(spellLevel, caster), 1)));
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.UNCOMMON)
            .setSchoolResource(ASSchoolRegistry.HYDRO_RESOURCE)
            .setMaxLevel(8)
            .setCooldownSeconds(60)
            .build();

    public RazorbladeTyphoonSpell() {
        this.manaCostPerLevel = 20;
        this.baseSpellPower = 15;
        this.spellPowerPerLevel = 3;
        this.castTime = 60;
        this.baseManaCost = 70;
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
        return Optional.of(SoundRegistry.RAISE_HELL_PREPARE.get());
    }

    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of(ModSounds.BRIMSTONE_BUZZSAW_SHOT.get());
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        RazorbladeTyphoonProjectile lance = new RazorbladeTyphoonProjectile(level, entity);
        lance.setPos(entity.position().add(0, entity.getEyeHeight(), 0).add(entity.getForward()));
        lance.shoot(entity.getLookAngle());
        lance.setDamage(getSpellPower(spellLevel, entity));
        lance.setCursorHoming(true);
        level.addFreshEntity(lance);
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    @Override
    public SpellDamageSource getDamageSource(Entity projectile, Entity attacker) {
        return super.getDamageSource(projectile, attacker).setIFrames(0);
    }

    @Override
    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.ANIMATION_CHARGED_CAST;
    }

    public boolean allowLooting() {
        return false;
    }
}
