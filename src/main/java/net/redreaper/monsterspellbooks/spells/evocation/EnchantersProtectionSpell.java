package net.redreaper.monsterspellbooks.spells.evocation;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.TargetEntityCastData;
import io.redspace.ironsspellbooks.network.particles.HealParticlesPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.neoforged.neoforge.network.PacketDistributor;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.init.ModSounds;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.List;
import java.util.Optional;

public class EnchantersProtectionSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "enchanters_protection");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.effect_length", Utils.timeFromTicks(getDuration(spellLevel, caster), 1))
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(SchoolRegistry.EVOCATION_RESOURCE)
            .setMaxLevel(6)
            .setCooldownSeconds(10)
            .build();

    public EnchantersProtectionSpell() {
        this.manaCostPerLevel = 5;
        this.baseSpellPower = 5;
        this.spellPowerPerLevel = 2;
        this.castTime = 45;
        this.baseManaCost = 10;
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
    public boolean checkPreCastConditions(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData) {
        return Utils.preCastTargetHelper(level, entity, playerMagicData, this, 64, .35f);
    }

    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        if (playerMagicData.getAdditionalCastData() instanceof TargetEntityCastData healTargetingData) {
            var targetEntity = healTargetingData.getTarget((ServerLevel) world);
            if (targetEntity != null) {
                targetEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20 * 120, 1));
                targetEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20 * 120, 1));
                targetEntity.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 20 * 120, 3));
                targetEntity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 20 * 120, 3));
                targetEntity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 20 * 120, 0));
                PacketDistributor.sendToPlayersTrackingEntityAndSelf(targetEntity, new HealParticlesPacket(targetEntity.position()));
            }
        }

        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of(ModSounds.EVOCATION_BOOST.get());
    }

    @Nullable
    private LivingEntity findTarget(LivingEntity caster) {
        var target = Utils.raycastForEntity(caster.level(), caster, 32, true, 0.35f);
        if (target instanceof EntityHitResult entityHit && entityHit.getEntity() instanceof LivingEntity livingTarget) {
            return livingTarget;
        } else {
            return null;
        }
    }

    public int getDuration(int spellLevel, LivingEntity caster) {
        return (int) 20 * 120;
    }

    @Override
    public Vector3f getTargetingColor() {
        return new Vector3f(.85f, 0, 0);
    }
}

