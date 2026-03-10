package net.redreaper.monsterspellbooks.spells.ender;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.magic.SpellSelectionManager;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.EarthquakeAoe;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.acetheeldritchking.aces_spell_utils.spells.ASSpellAnimations;
import net.acetheeldritchking.aces_spell_utils.utils.ASUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.spells.space_breaker.SpaceBreaker;
import net.redreaper.monsterspellbooks.init.ModMobEffects;
import net.redreaper.monsterspellbooks.init.ModSounds;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;

import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class SpaceBreakerSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "space_breaker");

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
            .setCooldownSeconds(40)
            .build();

    public SpaceBreakerSpell()
    {
        this.manaCostPerLevel = 5;
        this.baseSpellPower = 20;
        this.spellPowerPerLevel = 10;
        this.castTime = 20;
        this.baseManaCost = 50;
    }

    @Override
    public ResourceLocation getSpellResource() {
        return spellId;
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public CastType getCastType() {
        return CastType.LONG;
    }

    @Override
    public AnimationHolder getCastFinishAnimation() {
        return ASSpellAnimations.ANIMATION_SPELL_PUNCH;
    }

    @Override
    public AnimationHolder getCastStartAnimation() {
        return AnimationHolder.pass();
    }

    public Optional<SoundEvent> getCastStartSound() {return Optional.of(ModSounds.ENDERSENT_SMASH_CAST.get());}

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        float radius = 3F;
        float distance = 1.5F;
        Vec3 hitLocation = entity.position().add(0, entity.getBbHeight() * 1.F, 0).add(entity.getForward().multiply(distance, 0.35F, distance));
        var entities = entity.level().getEntities(entity, AABB.ofSize(hitLocation, radius * 2, radius, radius * 2));

        for (Entity target : entities)
        {
            if (target instanceof LivingEntity && entity.isAlive() && entity.isPickable() && entity.distanceToSqr(target) < radius * radius && Utils.hasLineOfSight(level, entity.getEyePosition(), target.getBoundingBox().getCenter(), true) && !(target instanceof ItemEntity))
            {
                if (DamageSources.applyDamage(target, getDamage(spellLevel, entity), this.getDamageSource(entity)))
                {
                    float AOEradius = 5.0F;
                    float AOErange = 1.7F;
                    MagicManager.spawnParticles(level, new BlastwaveParticleOptions(SchoolRegistry.ENDER.get().getTargetingColor(), radius), entity.getX(), entity.getY() + .165f, entity.getZ(), 1, 0, 0, 0, 0, true);
                    Vec3 smiteLocation = Utils.raycastForBlock(entity.level(), entity.getEyePosition(), entity.getEyePosition().add(entity.getForward().multiply((double)AOEradius, (double)0.0F, (double)AOErange)), ClipContext.Fluid.NONE).getLocation();
                    List<Entity> entities2 = entity.level().getEntities(entity, AABB.ofSize(smiteLocation, (double)(radius * 2.0F), (double)(radius * 4.0F), (double)(radius * 2.0F)));
                    EarthquakeAoe aoe = new EarthquakeAoe(level);
                    aoe.moveTo(smiteLocation);
                    aoe.setOwner(entity);
                    aoe.setCircular();
                    aoe.setRadius(6.0F);
                    aoe.setDuration(20);
                    aoe.setDamage(0.0F);
                    aoe.setSlownessAmplifier(0);
                    level.addFreshEntity(aoe);

                    ((LivingEntity) target).addEffect(new MobEffectInstance(ModMobEffects.PARALYSIS,75,1, true, true, true));
                    ((LivingEntity) target).addEffect(new MobEffectInstance(ModMobEffects.STUNNED,75,1, true, true, true));
                    ((LivingEntity) target).addEffect(new MobEffectInstance(MobEffects.BLINDNESS,75,0, true, true, true));
                    MagicManager.spawnParticles(level, ModParticleHelper.SPACE_SHARD, target.getX(), target.getY() + target.getBbHeight() * .5f, target.getZ(), 100, target.getBbWidth() * .5f, target.getBbHeight() * .5f, target.getBbWidth() * .5f, 1, false);
                    MagicManager.spawnParticles(level, ParticleHelper.UNSTABLE_ENDER, target.getX(), target.getY() + target.getBbHeight() * .5f, target.getZ(), 20, target.getBbWidth() * .5f, target.getBbHeight() * .5f, target.getBbWidth() * .5f, 0.3, false);

                    for(Entity targetEntity : entities) {
                        if (targetEntity.isAlive() && targetEntity.isPickable() && Utils.hasLineOfSight(entity.level(), smiteLocation.add((double)0.0F, (double)1.0F, (double)0.0F), targetEntity.getBoundingBox().getCenter(), true)) {
                            this.handleKnockback(entity, (LivingEntity)targetEntity, spellLevel);
                        }
                    }
                }
            }
        }
        boolean mirrored = playerMagicData.getCastingEquipmentSlot().equals(SpellSelectionManager.OFFHAND);

        SpaceBreaker swipe = new SpaceBreaker(level, mirrored);
        swipe.moveTo(hitLocation);
        swipe.setYRot(entity.getYRot());
        level.addFreshEntity(swipe);

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

    private float getDamage(int spellLevel, LivingEntity caster)
    {
        return ASUtils.getDamageForAttributes(this, caster, spellLevel, Attributes.ATTACK_DAMAGE, 0.50F);
    }

    private int getRange(int spellLevel, LivingEntity caster) {return (int) (6 + spellLevel * getEntityPowerMultiplier(caster)); }

}
