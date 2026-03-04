package net.redreaper.monsterspellbooks.spells.fire;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.TargetEntityCastData;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.spells.brimstone_rain.SmallBrimstoneFireball;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@AutoSpellConfig
public class BrimstoneRainSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "brimstone_rain");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.aoe_damage", Utils.stringTruncation(getAoeDamage(spellLevel, caster), 1)),
                Component.translatable("ui.irons_spellbooks.radius", Utils.stringTruncation(getRadius(caster), 1))
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.UNCOMMON)
            .setSchoolResource(SchoolRegistry.FIRE_RESOURCE)
            .setMaxLevel(10)
            .setCooldownSeconds(30)
            .build();

    public BrimstoneRainSpell() {
        this.manaCostPerLevel = 4;
        this.baseSpellPower = 15;
        this.spellPowerPerLevel = 3;
        this.castTime = 60 - 5;
        this.baseManaCost = 5;
    }

    @Override public int getCastTime(int spellLevel) {
        return castTime + 5 * spellLevel;
    }

    public CastType getCastType() {
        return CastType.CONTINUOUS;
    }

    public DefaultConfig getDefaultConfig() {
        return this.defaultConfig;
    }

    public ResourceLocation getSpellResource() {
        return this.spellId;
    }


    public boolean checkPreCastConditions(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData) {
        Utils.preCastTargetHelper(level, entity, playerMagicData, this, 32, 0.35F, false);
        return true;
    }

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        Vec3 targetPos = null;
        ICastData castData = playerMagicData.getAdditionalCastData();
        if (castData instanceof TargetEntityCastData targetData) {
            if (level instanceof ServerLevel serverLevel) {
                targetPos = targetData.getTargetPosition(serverLevel);
            }
        }

        if (targetPos == null) {
            HitResult raycast = Utils.raycastForEntity(level, entity, 45.0F, true);
            if (raycast.getType() == HitResult.Type.ENTITY) {
                targetPos = ((EntityHitResult)raycast).getEntity().position();
            } else {
                targetPos = Utils.moveToRelativeGroundLevel(level, raycast.getLocation(), 5);
            }
        }

        if (targetPos != null) {
            for(int i = 0; i < 2; ++i) {
                double offsetX = (entity.getRandom().nextDouble() - (double)0.5F) * (double)5.0F;
                double offsetZ = (entity.getRandom().nextDouble() - (double)0.5F) * (double)5.0F;
                Vec3 spawnPos = targetPos.add(offsetX, (double)8.0F, offsetZ);
                Vec3 trajectory = targetPos.subtract(spawnPos).normalize();
                SmallBrimstoneFireball fireball = new SmallBrimstoneFireball(level, entity);
                fireball.setPos(spawnPos);
                fireball.shoot(trajectory);
                fireball.setDamage(getDamage(spellLevel, entity));
                fireball.setAoeDamage(getAoeDamage(spellLevel, entity));
                fireball.setExplosionRadius(2f);
                level.addFreshEntity(fireball);
            }

            super.onCast(level, spellLevel, entity, castSource, playerMagicData);
        }
    }

    public SpellDamageSource getDamageSource(@Nullable Entity projectile, Entity attacker) {
        return super.getDamageSource(projectile, attacker).setFireTicks(10).setIFrames(0);
    }

    private float getDamage(int spellLevel, LivingEntity caster) {
        return getSpellPower(spellLevel, caster) * .10f;
    }

    private float getRadius(LivingEntity caster) {
        return 2;
    }

    public float getAoeDamage(int spellLevel, LivingEntity caster) {
        return 1 + getSpellPower(spellLevel, caster) * .05f;
    }
}


