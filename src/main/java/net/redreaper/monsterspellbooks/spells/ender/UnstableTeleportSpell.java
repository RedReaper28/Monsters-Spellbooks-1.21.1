package net.redreaper.monsterspellbooks.spells.ender;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.CameraShakeData;
import io.redspace.ironsspellbooks.api.util.CameraShakeManager;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.registries.ParticleRegistry;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.spells.ender.TeleportSpell;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.particle.EnderExplosionParticlePacket;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;
import org.joml.Vector3f;

import java.util.List;
import java.util.Optional;

public class UnstableTeleportSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "unstable_teleport");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.distance", Utils.stringTruncation(getDistance(spellLevel, caster), 1)),
                Component.translatable("ui.irons_spellbooks.radius", Utils.stringTruncation(getRadius(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2))
        );

    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(SchoolRegistry.ENDER_RESOURCE)
            .setMaxLevel(1)
            .setCooldownSeconds(12)
            .setAllowCrafting(false)
            .build();

    public UnstableTeleportSpell() {
        this.baseSpellPower = 5;
        this.spellPowerPerLevel = 5;
        this.baseManaCost = 25;
        this.manaCostPerLevel = 5;
        this.castTime = 20;
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

    public boolean allowLooting() {
        return false;
    }

    @Override
    public Optional<SoundEvent> getCastStartSound() {
        return Optional.empty();
    }

    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of(SoundRegistry.FORCE_IMPACT.get());
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        var teleportData = (TeleportSpell.TeleportData) playerMagicData.getAdditionalCastData();
        float radius = getRadius(spellLevel, entity);
        Vector3f edge = new Vector3f(.7f, 1f, 1f);
        Vector3f center = new Vector3f(1, 1f, 1f);

        //this is immaculately stupid
        MagicManager.spawnParticles(entity.level(), new BlastwaveParticleOptions(SchoolRegistry.ENDER.get().getTargetingColor(), radius * 2), entity.getX(), entity.getY(), entity.getZ(), 2, 0, 0, 0, 0, true);
        MagicManager.spawnParticles(level, ParticleHelper.ENDER_SPARKS, entity.getX(), entity.getY() + 1, entity.getZ(), 80, .25, .25, .25, 0.7f + radius * .1f, false);
        CameraShakeManager.addCameraShake(new CameraShakeData(level, 30, entity.position(), radius * 2));


        Vec3 start = entity.getBoundingBox().getCenter();
        float damage = getDamage(spellLevel, entity);
        level.getEntities(entity, entity.getBoundingBox().inflate(radius, radius, radius), (target) -> !DamageSources.isFriendlyFireBetween(target, entity) && Utils.hasLineOfSight(level, entity, target, true)).forEach(target -> {
            if (target instanceof LivingEntity livingEntity && canHit(entity, target) && livingEntity.distanceToSqr(entity) < radius * radius) {
                Vec3 dest = livingEntity.getBoundingBox().getCenter();
                MagicManager.spawnParticles(level, ParticleHelper.UNSTABLE_ENDER, livingEntity.getX(), livingEntity.getY() + livingEntity.getBbHeight() / 2, livingEntity.getZ(), 10, livingEntity.getBbWidth() / 3, livingEntity.getBbHeight() / 3, livingEntity.getBbWidth() / 3, 0.1, false);
                DamageSources.applyDamage(target, damage, getDamageSource(entity));
            }
        });


        Vec3 dest = null;
        if (teleportData != null) {
            var potentialTarget = teleportData.getTeleportTargetPosition();
            dest = potentialTarget;
        }

        if (dest == null) {
            dest = findTeleportLocation(spellLevel, level, entity);
        }

        PacketDistributor.sendToPlayersTrackingEntityAndSelf(entity, new EnderExplosionParticlePacket(entity.position(), radius));

        if (entity.isPassenger()) {
            entity.stopRiding();
        }
        Utils.handleSpellTeleport(this, entity, dest);
        entity.resetFallDistance();
        level.playSound(null, dest.x, dest.y, dest.z, getCastFinishSound().get(), SoundSource.NEUTRAL, 1f, 1f);

        playerMagicData.resetAdditionalCastData();

        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    private boolean canHit(Entity owner, Entity target) {
        return target != owner && target.isAlive() && target.isPickable() && !target.isSpectator();
    }

    private Vec3 findTeleportLocation(int spellLevel, Level level, LivingEntity entity) {
        return TeleportSpell.findTeleportLocation(level, entity, getDistance(spellLevel, entity));
    }

    public float getRadius(int spellLevel, LivingEntity caster) {
        return 8 + spellLevel;
    }

    public static void particleCloud(Level level, Vec3 pos) {
        if (level.isClientSide) {
            double width = 10;
            float height = 5;
            for (int i = 0; i < 25; i++) {
                double x = pos.x + Utils.random.nextDouble() * width * 2 - width;
                double y = pos.y + height + Utils.random.nextDouble() * height * 1.2 * 2 - height * 1.2;
                double z = pos.z + Utils.random.nextDouble() * width * 2 - width;
                double dx = Utils.random.nextDouble() * .1 * (Utils.random.nextBoolean() ? 1 : -1);
                double dy = Utils.random.nextDouble() * .1 * (Utils.random.nextBoolean() ? 1 : -1);
                double dz = Utils.random.nextDouble() * .1 * (Utils.random.nextBoolean() ? 1 : -1);
                level.addParticle(ModParticleHelper.ENDER_SMOKE, true, x, y, z, dx, dy, dz);
                level.addParticle(ParticleRegistry.UNSTABLE_ENDER_PARTICLE.get(), true, x, y, z, -dx, -dy, -dz);
            }
        }
    }

    private float getDistance(int spellLevel, LivingEntity sourceEntity) {
        return 10 + (float) (Utils.softCapFormula(getEntityPowerMultiplier(sourceEntity)) * spellLevel * 2);
    }

    private float getDamage(int spellLevel, LivingEntity caster) {
        return this.getSpellPower(spellLevel, caster)*2;
    }

    @Override
    public AnimationHolder getCastStartAnimation() {
        return AnimationHolder.none();
    }
}
