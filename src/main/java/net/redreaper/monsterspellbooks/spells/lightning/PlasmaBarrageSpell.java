package net.redreaper.monsterspellbooks.spells.lightning;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.spells.plasma_barrage.PlasmaBolt;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class PlasmaBarrageSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "plasma_barrage");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2)));
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(SchoolRegistry.LIGHTNING_RESOURCE)
            .setMaxLevel(8)
            .setCooldownSeconds(25)
            .build();

    public PlasmaBarrageSpell() {
        this.manaCostPerLevel = 2;
        this.baseSpellPower = 5;
        this.spellPowerPerLevel = 1;
        this.castTime = 60 - 5;
        this.baseManaCost = 5;
    }

    @Override
    public int getCastTime(int spellLevel) {
        return castTime + 5 * spellLevel;
    }

    @Override
    public CastType getCastType() {
        return CastType.CONTINUOUS;
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
        return Optional.of(SoundEvents.BLAZE_AMBIENT);
    }

    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.empty();
    }

    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    @Override
    public void onServerCastTick(Level level, int spellLevel, LivingEntity entity, @Nullable MagicData playerMagicData) {
        if (playerMagicData != null && (playerMagicData.getCastDurationRemaining() + 1) % 5 == 0) {
            shootBlazeFireball(level, spellLevel, entity);
        }
    }

    public float getDamage(int spellLevel, LivingEntity caster) {
        if (caster == null) {
            return this.getSpellPower(spellLevel, (Entity)null) * 7.0F;
        } else {
            double firePower = caster.getAttributeValue(AttributeRegistry.FIRE_SPELL_POWER);
            double bloodPower = caster.getAttributeValue(AttributeRegistry.LIGHTNING_SPELL_POWER);
            return (float)((double).6F + (double).6F * (double)this.getSpellPower(spellLevel, caster) * ((double)0.2F * firePower + (double)0.2F * bloodPower));
        }
    }

    public void shootBlazeFireball(Level world, int spellLevel, LivingEntity entity) {
        Vec3 origin = entity.getEyePosition().add(entity.getForward().normalize().scale(.4f));
        PlasmaBolt fireball = new PlasmaBolt(world, entity);
        fireball.setPos(origin.subtract(0, fireball.getBbHeight(), 0));
        fireball.shoot(entity.getLookAngle(), .01f);
        fireball.setDamage(getDamage(spellLevel, entity));
        world.playSound(null, origin.x, origin.y, origin.z, SoundRegistry.LIGHTNING_CAST, SoundSource.PLAYERS, 2.0f, 1.0f);
        world.addFreshEntity(fireball);
    }

    @Override
    public SpellDamageSource getDamageSource(@Nullable Entity projectile, Entity attacker) {
        return super.getDamageSource(projectile, attacker).setFireTicks(40).setIFrames(0);
    }
}
