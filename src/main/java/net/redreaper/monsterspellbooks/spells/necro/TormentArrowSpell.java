package net.redreaper.monsterspellbooks.spells.necro;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.spells.torment_arrow.TormentArrowProjectile;
import net.redreaper.monsterspellbooks.init.ModSpellSchools;

import java.util.List;
import java.util.Optional;

public class TormentArrowSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "torment_arrow");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getSpellPower(spellLevel, caster), 1)));
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(ModSpellSchools.NECRO_RESOURCE)
            .setMaxLevel(10)
            .setCooldownSeconds(8)
            .build();

    public TormentArrowSpell() {
        this.manaCostPerLevel = 5;
        this.baseSpellPower = 10;
        this.spellPowerPerLevel = 5;
        this.castTime = 15;
        this.baseManaCost = 40;
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
        return Optional.of(SoundRegistry.MAGIC_ARROW_CHARGE.get());
    }

    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of(SoundRegistry.MAGIC_ARROW_RELEASE.get());
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        int baseDelay = 10;
        int delayBetween = 2;
        TormentArrowProjectile magicArrow = new TormentArrowProjectile(level, entity);
        magicArrow.setPos(entity.position().add(0, entity.getEyeHeight() - magicArrow.getBoundingBox().getYsize() * .5f, 0).add(entity.getForward()));
        magicArrow.shoot(entity.getLookAngle());
        magicArrow.setDamage(getSpellPower(spellLevel, entity));
        magicArrow.delay = baseDelay  * delayBetween;
        level.addFreshEntity(magicArrow);
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    @Override
    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.BOW_CHARGE_ANIMATION;
    }

    @Override
    public AnimationHolder getCastFinishAnimation() {
        return AnimationHolder.none();
    }
}