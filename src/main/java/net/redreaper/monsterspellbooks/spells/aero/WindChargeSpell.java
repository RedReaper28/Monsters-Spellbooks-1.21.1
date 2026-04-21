package net.redreaper.monsterspellbooks.spells.aero;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.spells.wind_charge.ExtendedWindCharge;
import net.redreaper.monsterspellbooks.init.ModSpellSchools;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WindChargeSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "wind_charge");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.radius", Utils.stringTruncation(getRadius(spellLevel, caster), 1))
        );
    }


    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.COMMON)
            .setSchoolResource(ModSpellSchools.AERO_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(1)
            .build();

    public WindChargeSpell() {
        this.manaCostPerLevel = 2;
        this.baseSpellPower = 12;
        this.spellPowerPerLevel = 1;
        this.castTime = 0;
        this.baseManaCost = 10;
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
        ExtendedWindCharge firebolt = new ExtendedWindCharge(world, entity);
        firebolt.setPos(entity.position().add(0, entity.getEyeHeight() - firebolt.getBoundingBox().getYsize() * .5f, 0));
        firebolt.shoot(entity.getLookAngle());
        firebolt.setRadius(getRadius(spellLevel, entity));
        firebolt.setDamage(getDamage(spellLevel, entity));
        world.addFreshEntity(firebolt);
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    public float getRadius(int spellLevel, LivingEntity caster) {
        return 3 + getEntityPowerMultiplier(caster);
    }

    @Override
    public SpellDamageSource getDamageSource(@Nullable Entity projectile, Entity attacker) {
        return super.getDamageSource(projectile, attacker).setFireTicks(60);
    }

    private float getDamage(int spellLevel, LivingEntity entity) {
        return 0.2f * (float) Math.sqrt(getSpellPower(spellLevel, entity))/0.6324555f;
    }
}