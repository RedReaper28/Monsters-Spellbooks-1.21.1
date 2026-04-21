package net.redreaper.monsterspellbooks.spells.hydro;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.ImpulseCastData;
import io.redspace.ironsspellbooks.player.SpinAttackType;
import io.redspace.ironsspellbooks.spells.fire.BurningDashSpell;
import net.acetheeldritchking.aces_spell_utils.registries.ASSchoolRegistry;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.init.ModMobEffects;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;

import java.util.List;

public class RiptideDashSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "riptide_dash");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.damage", getDamageText(spellLevel, caster)));
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.COMMON)
            .setSchoolResource(ASSchoolRegistry.HYDRO_RESOURCE)
            .setMaxLevel(10)
            .setCooldownSeconds(10)
            .build();

    public RiptideDashSpell() {
        this.manaCostPerLevel = 2;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 0;
        this.baseManaCost = 20;
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
    public void onClientCast(Level level, int spellLevel, LivingEntity entity, ICastData castData) {
        if (castData instanceof ImpulseCastData bdcd) {
            entity.hasImpulse = bdcd.hasImpulse;
            entity.setDeltaMovement(entity.getDeltaMovement().add(bdcd.x, bdcd.y, bdcd.z));
        }

        super.onClientCast(level, spellLevel, entity, castData);
    }

    @Override
    public ICastDataSerializable getEmptyCastData() {
        return new ImpulseCastData();
    }

    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        entity.hasImpulse = true;
        float multiplier = (15 + getSpellPower(spellLevel, entity)) / 12f;

        Vec3 forward = entity.getLookAngle();
        if (playerMagicData.getAdditionalCastData() instanceof BurningDashSpell.BurningDashDirectionOverrideCastData) {
            if (Utils.random.nextBoolean())
                forward = forward.yRot(90);
            else
                forward = forward.yRot(-90);
        }

        var vec = forward.multiply(3, 1, 3).normalize().add(0, .25, 0).scale(multiplier);
        if (entity.isInWater()) {
            entity.setPos(entity.position().add(0, 1.5, 0));
            vec.add(0, 0.25, 0);
        }
        playerMagicData.setAdditionalCastData(new ImpulseCastData((float) vec.x, (float) vec.y, (float) vec.z, true));
        entity.setDeltaMovement(new Vec3(
                Mth.lerp(.75f, entity.getDeltaMovement().x, vec.x),
                Mth.lerp(.75f, entity.getDeltaMovement().y, vec.y),
                Mth.lerp(.75f, entity.getDeltaMovement().z, vec.z)
        ));

        entity.addEffect(new MobEffectInstance(ModMobEffects.RIPTIDE_DASH, 15, getDamage(spellLevel, entity), false, false, false));
        entity.invulnerableTime = 20;
        playerMagicData.getSyncedData().setSpinAttackType(SpinAttackType.RIPTIDE);
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    private int getDamage(int spellLevel, LivingEntity caster) {
        return (int) (5 + getSpellPower(spellLevel, caster)+ getAdditionalDamage(caster));
    }

    private float getAdditionalDamage(LivingEntity entity) {
        if (entity == null) {
            return 0;
        }
        float weaponDamage = Utils.getWeaponDamage(entity);
        var weaponItem = entity.getWeaponItem();
        if (!weaponItem.isEmpty() && weaponItem.has(DataComponents.ENCHANTMENTS)) {
            weaponDamage += Utils.getEnchantmentLevel(entity.level(), Enchantments.RIPTIDE, weaponItem.get(DataComponents.ENCHANTMENTS));
        }
        return weaponDamage;
    }


    private String getDamageText(int spellLevel, LivingEntity entity) {
        if (entity != null) {
            float weaponDamage = Utils.getWeaponDamage(entity);
            String plus = "";
            if (weaponDamage > 0) {
                plus = String.format(" (+%s)", Utils.stringTruncation(weaponDamage, 1));
            }
            String damage = Utils.stringTruncation(getDamage(spellLevel, entity), 1);
            return damage + plus;
        }
        return "" + getSpellPower(spellLevel, entity);
    }

    public static void ambientParticles(ClientLevel level, LivingEntity entity) {
        for (int i = 0; i < 2; i++) {
            Vec3 random = Utils.getRandomVec3(.2);
            level.addParticle(ModParticleHelper.HYDRO_BUBBLE, entity.getRandomX(0.75), entity.getY() + Utils.getRandomScaled(0.75), entity.getRandomZ(0.75), random.x, random.y, random.z);
        }
        for (int i = 0; i < 6; i++) {
            Vec3 random = Utils.getRandomVec3(.2);
            level.addParticle(ParticleTypes.FALLING_WATER, entity.getRandomX(0.75), entity.getY() + Utils.getRandomScaled(0.75), entity.getRandomZ(0.75), random.x, random.y, random.z);
        }
    }

    public static class RiptideDashSpellDirectionOverrideCastData implements ICastData {
        @Override
        public void reset() {
        }
    }
}
