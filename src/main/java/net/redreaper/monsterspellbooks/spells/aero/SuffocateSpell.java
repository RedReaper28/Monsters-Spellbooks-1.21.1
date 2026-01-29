package net.redreaper.monsterspellbooks.spells.aero;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.TargetEntityCastData;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.target_area.TargetedAreaEntity;
import io.redspace.ironsspellbooks.spells.TargetedTargetAreaCastData;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.init.ModMobEffects;
import net.redreaper.monsterspellbooks.init.ModSpellSchools;
import org.joml.Vector3f;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@AutoSpellConfig
public class SuffocateSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "suffocate");
    private static final int MAX_TARGETS = 10;

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.effect_length", Utils.timeFromTicks(getDuration(spellLevel, caster), 1)),
                Component.translatable("ui.irons_spellbooks.max_victims", MAX_TARGETS)
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.UNCOMMON)
            .setSchoolResource(ModSpellSchools.AERO_RESOURCE)
            .setMaxLevel(6)
            .setCooldownSeconds(60)
            .build();

    public SuffocateSpell() {
        this.manaCostPerLevel = 20;
        this.baseSpellPower = 15;
        this.spellPowerPerLevel = 5;
        this.castTime = 30;
        this.baseManaCost = 50;
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

    @Override public Optional<SoundEvent> getCastStartSound() {return Optional.of(SoundEvents.BREEZE_CHARGE);}

    @Override public Optional<SoundEvent> getCastFinishSound() {return Optional.of(SoundEvents.BREEZE_SHOOT);}

    @Override
    public boolean checkPreCastConditions(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData) {
        if (Utils.preCastTargetHelper(level, entity, playerMagicData, this, 32, .35f)) {
            float radius = 3f;
            var target = ((TargetEntityCastData) playerMagicData.getAdditionalCastData()).getTarget((ServerLevel) level);
            var area = TargetedAreaEntity.createTargetAreaEntity(level, target.position(), radius, ModMobEffects.SUFFOCATION.get().getColor(), target);
            playerMagicData.setAdditionalCastData(new TargetedTargetAreaCastData(target, area));
            return true;
        }
        return false;
    }

    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        if (playerMagicData.getAdditionalCastData() instanceof TargetedTargetAreaCastData targetData) {
            var targetEntity = targetData.getTarget((ServerLevel) world);
            if (targetEntity != null) {
                float radius = 3;
                AtomicInteger targets = new AtomicInteger(0);
                targetEntity.level().getEntitiesOfClass(LivingEntity.class, targetEntity.getBoundingBox().inflate(radius)).forEach((victim) -> {
                    if (targets.get() < MAX_TARGETS && victim != entity && victim.distanceToSqr(targetEntity) < radius * radius && !DamageSources.isFriendlyFireBetween(entity, victim)) {
                        victim.addEffect(new MobEffectInstance(ModMobEffects.SUFFOCATION, getDuration(spellLevel, entity), spellLevel));
                        targets.incrementAndGet();
                    }
                });
            }
        }
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    public int getDuration(int spellLevel, LivingEntity caster) {
        return (int) (getSpellPower(spellLevel, caster) * 7.5);
    }

    @Override
    public Vector3f getTargetingColor() {
        return Utils.deconstructRGB(ModMobEffects.SUFFOCATION.get().getColor());
    }
}