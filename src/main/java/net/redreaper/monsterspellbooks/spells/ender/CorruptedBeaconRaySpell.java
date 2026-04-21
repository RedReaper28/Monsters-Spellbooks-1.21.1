package net.redreaper.monsterspellbooks.spells.ender;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.spells.CastingMobAimingData;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.effect.VoidTouchedEffect;
import net.redreaper.monsterspellbooks.init.ModSounds;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class CorruptedBeaconRaySpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "corrupted_beacon_ray");
    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.EPIC)
            .setSchoolResource(SchoolRegistry.ENDER_RESOURCE)
            .setMaxLevel(10)
            .setCooldownSeconds(25)
            .build();

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getTickDamage(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.distance", Utils.stringTruncation(getRange(spellLevel), 1)));
    }

    public CorruptedBeaconRaySpell() {
        this.manaCostPerLevel = 2;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 90;
        this.baseManaCost = 20;
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
        return Optional.of(ModSounds.CORRUPTED_BEACON_FIRE.get());
    }

    @Override
    public ICastDataSerializable getEmptyCastData() {
        return new CastingMobAimingData();
    }

    @Override
    public void onServerCastTick(Level level, int spellLevel, LivingEntity entity, @Nullable MagicData playerMagicData) {
        super.onServerCastTick(level, spellLevel, entity, playerMagicData);
        if (playerMagicData.getAdditionalCastData() instanceof CastingMobAimingData aimData && entity instanceof Mob mob) {
            var target = mob.getTarget();
            if (target != null) {
                aimData.updateAim(target, .50f);
            }
        }
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        Vec3 forward = entity.getForward();
        if (playerMagicData.getAdditionalCastData() instanceof CastingMobAimingData aimData && entity instanceof Mob mob) {
            forward = aimData.getForward(entity);
        }
        var hitResult = Utils.raycastForEntity(level, entity, entity.getEyePosition(), entity.getEyePosition().add(forward.scale(getRange(spellLevel))), true, .15f, Utils::canHitWithRaycast);
        if (hitResult.getType() == HitResult.Type.ENTITY) {
            Entity target = ((EntityHitResult) hitResult).getEntity();
            if (target.canBeHitByProjectile()) {
                if (DamageSources.applyDamage(target, getTickDamage(spellLevel, entity), getDamageSource(entity))) {
                    VoidTouchedEffect.addVoidStack((LivingEntity) target, entity);
                }
            }
        }
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    @Override
    public SpellDamageSource getDamageSource(@Nullable Entity projectile, Entity attacker) {
        return super.getDamageSource(projectile, attacker).setIFrames(0);
    }

    public static float getRange(int level) {
        return 40;
    }

    private float getTickDamage(int spellLevel, LivingEntity caster)
    {
        return (float) (1 * getSpellPower(spellLevel, caster));
    }

    @Override
    public boolean shouldAIStopCasting(int spellLevel, Mob mob, LivingEntity target) {
        return mob.distanceToSqr(target) > (getRange(spellLevel) * getRange(spellLevel)) * 1.2;
    }

    @Override
    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.ANIMATION_CONTINUOUS_CAST;
    }

    public boolean allowLooting() {
        return false;
    }
}

