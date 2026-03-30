package net.redreaper.monsterspellbooks.spells.lightning;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.CameraShakeData;
import io.redspace.ironsspellbooks.api.util.CameraShakeManager;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.acetheeldritchking.aces_spell_utils.utils.ASUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.utils.ModUtils;
import net.redreaper.monsterspellbooks.init.ModMobEffects;
import net.redreaper.monsterspellbooks.particle.PlasmaZapParticleOptions;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.List;
import java.util.Optional;

public class DischargeSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "discharge");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", getDamageText(spellLevel, caster)),
                Component.translatable("ui.irons_spellbooks.radius", Utils.stringTruncation(getRadius(spellLevel, caster), 2))
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(SchoolRegistry.LIGHTNING_RESOURCE)
            .setMaxLevel(6)
            .setCooldownSeconds(20)
            .build();

    public DischargeSpell() {
        this.manaCostPerLevel = 1;
        this.baseSpellPower = 5;
        this.spellPowerPerLevel = 1;
        this.castTime = 20 - 5;
        this.baseManaCost = 5;
    }

    @Override public int getCastTime(int spellLevel) {
        return castTime + 5 * spellLevel;
    }

    @Override public CastType getCastType() {
        return CastType.CONTINUOUS;
    }

    @Override public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override public ResourceLocation getSpellResource() {
        return spellId;
    }

    @Override public Optional<SoundEvent> getCastStartSound() {return Optional.of(SoundRegistry.SHOCKWAVE_PREPARE.get());}

    @Override public Optional<SoundEvent> getCastFinishSound() {
        return Optional.empty();
    }

    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    @Override
    public void onServerCastTick(Level level, int spellLevel, LivingEntity entity, @Nullable MagicData playerMagicData) {
        if (playerMagicData != null && (playerMagicData.getCastDurationRemaining() + 1) % 5 == 0) {
            scream(level, spellLevel, entity);
        }
    }

    public void scream(Level level, int spellLevel, LivingEntity entity) {
        float radius = getRadius(spellLevel, entity);
        Vector3f edge = new Vector3f(.7f, 1f, 1f);
        Vector3f center = new Vector3f(1, 1f, 1f);
        //this is immaculately stupid
        MagicManager.spawnParticles(level, new BlastwaveParticleOptions(edge, radius * 1.02f), entity.getX(), entity.getY() + .15f, entity.getZ(), 1, 0, 0, 0, 0, true);
        MagicManager.spawnParticles(level, new BlastwaveParticleOptions(edge, radius * 0.98f), entity.getX(), entity.getY() + .15f, entity.getZ(), 1, 0, 0, 0, 0, true);
        MagicManager.spawnParticles(level, new BlastwaveParticleOptions(center, radius), entity.getX(), entity.getY() + .165f, entity.getZ(), 1, 0, 0, 0, 0, true);
        MagicManager.spawnParticles(level, new BlastwaveParticleOptions(center, radius), entity.getX(), entity.getY() + .135f, entity.getZ(), 1, 0, 0, 0, 0, true);
        MagicManager.spawnParticles(level, ParticleHelper.FIERY_SPARKS, entity.getX(), entity.getY() + 1, entity.getZ(), 80, .25, .25, .25, 0.7f + radius * .1f, false);
        CameraShakeManager.addCameraShake(new CameraShakeData(level, 30, entity.position(), radius * 2));

        Vec3 start = entity.getBoundingBox().getCenter();
        float damage = getDamage(spellLevel, entity);
        var dummyLightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
        dummyLightningBolt.setDamage(0);
        dummyLightningBolt.setVisualOnly(true);
        level.getEntities(entity, entity.getBoundingBox().inflate(radius, radius, radius), (target) -> !DamageSources.isFriendlyFireBetween(target, entity) && Utils.hasLineOfSight(level, entity, target, true)).forEach(target -> {
            if (target instanceof LivingEntity livingEntity && canHit(entity, target) && livingEntity.distanceToSqr(entity) < radius * radius) {
                Vec3 dest = livingEntity.getBoundingBox().getCenter();
                ((ServerLevel) level).sendParticles(new PlasmaZapParticleOptions(dest), start.x, start.y, start.z, 1, 0, 0, 0, 0);
                MagicManager.spawnParticles(level, ParticleHelper.FIERY_SPARKS, livingEntity.getX(), livingEntity.getY() + livingEntity.getBbHeight() / 2, livingEntity.getZ(), 10, livingEntity.getBbWidth() / 3, livingEntity.getBbHeight() / 3, livingEntity.getBbWidth() / 3, 0.1, false);
                DamageSources.applyDamage(target, damage, getDamageSource(entity));
                if (target instanceof Creeper creeper) {
                    creeper.thunderHit((ServerLevel) level, dummyLightningBolt);
                }
            }
        });
        for (int i = 0; i < 7; i++) {
            Vec3 dest = start.add(Utils.getRandomVec3(1).multiply(4, 2.5, 4).add(0, 4, 0));
            ((ServerLevel) level).sendParticles(new PlasmaZapParticleOptions(dest), start.x, start.y, start.z, 1, 0, 0, 0, 0);
        }
    }

    private boolean canHit(Entity owner, Entity target) {
        return target != owner && target.isAlive() && target.isPickable() && !target.isSpectator();
    }

    public float getRadius(int spellLevel, LivingEntity caster) {
        return 6 + spellLevel * .10f;
    }

    public int getDuration(int spellLevel, LivingEntity caster) {
        return (int) (getSpellPower(spellLevel, caster) * 20);
    }

    private float getDamage(int spellLevel, LivingEntity caster)
    {
        float baseDamage = ASUtils.getDamageForAttributes(this, caster, spellLevel, AttributeRegistry.MAX_MANA, 0.02f);
        float extraDamage = ASUtils.getDamageForAttributes(this, caster, spellLevel, AttributeRegistry.MANA_REGEN, .10f);

        return baseDamage + extraDamage;
    }

    private String getDamageText(int spellLevel, LivingEntity caster)
    {
        if (caster != null)
        {
            float extraDamage = ModUtils.getEntityMana(caster) * 0.02f;
            float extraDamage2 = ModUtils.getEntityManaRegen(caster);
            float totalDamage = extraDamage + extraDamage2;
            String plus = "";
            if (extraDamage > 0)
            {
                plus = String.format(" (+%s)", Utils.stringTruncation(totalDamage, 1));
            }
            String damage = Utils.stringTruncation(getDamage(spellLevel, caster), 1);
            return damage + plus;
        }
        return "" + getSpellPower(spellLevel, caster);
    }

    @Override
    public void onServerCastComplete(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData, boolean cancelled) {
        entity.addEffect(new MobEffectInstance(ModMobEffects.SOUL_REND, (int) (getSpellPower(spellLevel, entity) * 20), 1, false, false, true));

        super.onServerCastComplete(level, spellLevel, entity, playerMagicData, cancelled);
    }

    @Override
    public SpellDamageSource getDamageSource(@Nullable Entity projectile, Entity attacker) {
        return super.getDamageSource(projectile, attacker).setIFrames(0);
    }

    @Override
    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.TOUCH_GROUND_ANIMATION;
    }
}
