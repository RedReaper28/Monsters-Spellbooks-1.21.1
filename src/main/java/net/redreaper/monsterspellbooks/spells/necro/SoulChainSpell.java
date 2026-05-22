package net.redreaper.monsterspellbooks.spells.necro;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.capabilities.magic.TargetEntityCastData;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.spells.soul_chain.SoulChain;
import net.redreaper.monsterspellbooks.init.ModMobEffects;
import net.redreaper.monsterspellbooks.init.ModSpellSchools;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;

import java.util.List;

public class SoulChainSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "soul_chain");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.effect_length", Utils.stringTruncation(getDuration(spellLevel, caster), 1)),
                Component.translatable("ui.irons_spellbooks.distance", Utils.stringTruncation(getRange(spellLevel, caster), 1))
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.COMMON)
            .setSchoolResource(ModSpellSchools.NECRO_RESOURCE)
            .setMaxLevel(10)
            .setCooldownSeconds(1)
            .build();

    public SoulChainSpell() {
        this.manaCostPerLevel = 2;
        this.baseSpellPower = 1;
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
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        Vec3 forward = entity.getForward().normalize();
        var hitResult = Utils.raycastForEntity(level, entity, getRange(spellLevel, entity), true, .15f);
        if (hitResult.getType() == HitResult.Type.ENTITY) {
            int i = getDuration(spellLevel, entity);
            Entity target = ((EntityHitResult) hitResult).getEntity();
            if (target instanceof LivingEntity) {
                DamageSources.applyDamage(target, this.getDamage(spellLevel, entity), this.getDamageSource(entity));
                ((LivingEntity) target).addEffect(new MobEffectInstance(ModMobEffects.SPACE_ANCHORED,i,0, false, true, true));
                MagicManager.spawnParticles(level, ModParticleHelper.SIMPLE_SOUL_CHAIN, hitResult.getLocation().x, target.getY(), hitResult.getLocation().z, 4, 0, 0, 0, .3, true);
            }
        } else if (hitResult.getType() == HitResult.Type.BLOCK) {
            MagicManager.spawnParticles(level, ModParticleHelper.SOUL_SMOKE, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z, 4, 0, 0, 0, .3, true);
        }
        MagicManager.spawnParticles(level, ModParticleHelper.SOUL_SMOKE, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z, 50, 0, 0, 0, .3, false);

        double distance = entity.position().distanceTo(hitResult.getLocation());
        for (float i = 1; i < distance; i += .5f) {
            Vec3 pos = entity.getEyePosition().add(forward.scale(i));
            MagicManager.spawnParticles(level, ModParticleHelper.SIMPLE_SOUL_CHAIN, pos.x, pos.y, pos.z, 1, 0, 0, 0, 0, false);
        }

        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    public int getDuration(int spellLevel, LivingEntity caster) {
        return (int) (getSpellPower(spellLevel, caster) * 20);
    }

    public float getDamage(int spellLevel, LivingEntity caster) {
        return getSpellPower(spellLevel, caster);
    }

    public float getRange(int spellLevel, LivingEntity caster) {
        return 4f + getSpellPower(spellLevel, caster) * .5f;
    }

}
