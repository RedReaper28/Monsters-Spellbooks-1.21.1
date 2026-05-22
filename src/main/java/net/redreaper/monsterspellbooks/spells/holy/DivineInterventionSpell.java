package net.redreaper.monsterspellbooks.spells.holy;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.ImpulseCastData;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.fireball.MagicFireball;
import io.redspace.ironsspellbooks.entity.spells.sunbeam.SunbeamEntity;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.spells.divine_intervention.DivineSwordProjectile;
import net.redreaper.monsterspellbooks.init.ModMobEffects;

import java.util.List;

public class DivineInterventionSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "divine_intervention");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
        Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.radius", Utils.stringTruncation(getRadius(spellLevel, caster), 2))
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(SchoolRegistry.HOLY_RESOURCE)
            .setMaxLevel(6)
            .setCooldownSeconds(30)
            .build();

    public DivineInterventionSpell() {
        this.manaCostPerLevel = 1;
        this.baseSpellPower = 5;
        this.spellPowerPerLevel = 1;
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
    public ICastDataSerializable getEmptyCastData() {
        return new ImpulseCastData();
    }

    @Override
    public void onClientCast(Level level, int spellLevel, LivingEntity entity, ICastData castData) {
        if (castData instanceof ImpulseCastData data) {
            entity.hasImpulse = data.hasImpulse;
            double y = Math.max(entity.getDeltaMovement().y, data.y);
            entity.setDeltaMovement(data.x, y, data.z);
        }
        super.onClientCast(level, spellLevel, entity, castData);
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        Vec3 origin = entity.getEyePosition();

        DivineSwordProjectile fireball = new DivineSwordProjectile(level, entity);

        fireball.setDamage(getDamage(spellLevel, entity));
        fireball.setExplosionRadius(getRadius(spellLevel, entity));

        fireball.setPos(entity.getX(),entity.getY()+7,entity.getZ());

        level.addFreshEntity(fireball);
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    public float getRadius(int spellLevel, LivingEntity caster) {return 8 + spellLevel;}

    public float getDamage(int spellLevel, LivingEntity caster) {return 4 + (getSpellPower(spellLevel, caster) * .50f);}
}

