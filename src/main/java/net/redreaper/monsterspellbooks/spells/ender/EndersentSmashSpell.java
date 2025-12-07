package net.redreaper.monsterspellbooks.spells.ender;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.entity.spells.EarthquakeAoe;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class EndersentSmashSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "endersent_smash");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.distance", getRange(spellLevel, caster))
        );
    }
    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(SchoolRegistry.ENDER_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(240)
            .build();

    public EndersentSmashSpell() {
        this.manaCostPerLevel = 20;
        this.baseSpellPower = 15;
        this.spellPowerPerLevel = 5;
        this.castTime = 16;
        this.baseManaCost = 75;
    }

    @Override
    public Optional<SoundEvent> getCastFinishSound() {return Optional.of(SoundEvents.ENDERMAN_DEATH);}

    @Override
    public CastType getCastType() {
        return CastType.LONG;
    }

    @Override
    public boolean canBeInterrupted(Player player) {
        return false;
    }

    @Override
    public int getEffectiveCastTime(int spellLevel, @Nullable LivingEntity entity) {return getCastTime(spellLevel);}

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
        float radius = 5.0F;
        float range = 1.7F;
        MagicManager.spawnParticles(level, new BlastwaveParticleOptions(SchoolRegistry.ENDER.get().getTargetingColor(), radius), entity.getX(), entity.getY() + .165f, entity.getZ(), 1, 0, 0, 0, 0, true);
        Vec3 smiteLocation = Utils.raycastForBlock(entity.level(), entity.getEyePosition(), entity.getEyePosition().add(entity.getForward().multiply((double)range, (double)0.0F, (double)range)), ClipContext.Fluid.NONE).getLocation();
        List<Entity> entities = entity.level().getEntities(entity, AABB.ofSize(smiteLocation, (double)(radius * 2.0F), (double)(radius * 4.0F), (double)(radius * 2.0F)));
        SpellDamageSource damageSource = this.getDamageSource(entity);
        EarthquakeAoe aoe = new EarthquakeAoe(level);
        aoe.moveTo(smiteLocation);
        aoe.setOwner(entity);
        aoe.setCircular();
        aoe.setRadius(6.0F);
        aoe.setDuration(20);
        aoe.setDamage(0.0F);
        aoe.setSlownessAmplifier(0);
        level.addFreshEntity(aoe);

        for(Entity targetEntity : entities) {
            if (targetEntity.isAlive() && targetEntity.isPickable() && Utils.hasLineOfSight(entity.level(), smiteLocation.add((double)0.0F, (double)1.0F, (double)0.0F), targetEntity.getBoundingBox().getCenter(), true) && DamageSources.applyDamage(targetEntity, this.getDamage(spellLevel, entity), damageSource)) {
                this.handleKnockback(entity, (LivingEntity)targetEntity, spellLevel);
            }
        }

        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    public void handleKnockback(LivingEntity entity, LivingEntity targetEntity, int spellLevel) {
        Vec3 angleVector = entity.position().subtract(targetEntity.position()).normalize();
        Vec3 vec = angleVector.multiply((double)2.0F, (double)1.0F, (double)2.0F).normalize().scale((double)(-1.0F * this.getForce(spellLevel, entity)));
        if (targetEntity instanceof ServerPlayer serverPlayer) {
            serverPlayer.hurtMarked = true;
        }

        targetEntity.setDeltaMovement(targetEntity.getDeltaMovement().add(vec));
        targetEntity.addEffect(new MobEffectInstance(MobEffectRegistry.AIRBORNE, 60, 1));
        Vec3 particleLocation = targetEntity.position();
        MagicManager.spawnParticles(entity.level(), ParticleHelper.UNSTABLE_ENDER, particleLocation.x, particleLocation.y + (double)(targetEntity.getBbHeight() / 2.0F), particleLocation.z, 50, (double)0.0F, (double)0.0F, (double)0.0F, 0.2, false);
    }

    private float getForce(int spellLevel, LivingEntity entity) {return this.getSpellPower(spellLevel, entity) * 0.1F;}

    private float getDamage(int spellLevel, LivingEntity caster) {
        return getSpellPower(spellLevel, caster);
    }

    private int getRange(int spellLevel, LivingEntity caster) {return (int) (6 + spellLevel * getEntityPowerMultiplier(caster)); }

    @Override
    public AnimationHolder getCastStartAnimation() {return SpellAnimations.OVERHEAD_MELEE_SWING_ANIMATION;}

    @Override
    public AnimationHolder getCastFinishAnimation() {
        return AnimationHolder.pass();
    }

    @Override
    public boolean shouldAIStopCasting(int spellLevel, Mob mob, LivingEntity target) {
        float f = getRange(spellLevel, mob);
        return mob.distanceToSqr(target) > (f * f) * 1.2;
    }

    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of(SoundEvents.ENDER_DRAGON_AMBIENT);
    }

}

