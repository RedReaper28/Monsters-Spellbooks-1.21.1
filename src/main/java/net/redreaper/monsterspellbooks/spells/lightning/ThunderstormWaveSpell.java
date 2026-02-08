package net.redreaper.monsterspellbooks.spells.lightning;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.spells.thunderstorm_wave.AncientLightningStrike;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class ThunderstormWaveSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "thunderstorm_wave");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.ring_count", getRings(spellLevel, caster)),
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2)));
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.COMMON)
            .setSchoolResource(SchoolRegistry.LIGHTNING_RESOURCE)
            .setMaxLevel(8)
            .setCooldownSeconds(30)
            .build();

    public ThunderstormWaveSpell() {
        this.manaCostPerLevel = 5;
        this.baseSpellPower = 8;
        this.spellPowerPerLevel = 1;
        this.castTime = 0;
        this.baseManaCost = 45;
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
    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of(SoundRegistry.SHOCKWAVE_CAST.get());
    }

    @Override public AnimationHolder getCastStartAnimation() {return SpellAnimations.ONE_HANDED_VERTICAL_UPSWING_ANIMATION;}

    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        int rings = getRings(spellLevel, entity);
        int count = 5;
        Vec3 center = entity.getEyePosition();
        float damage = getDamage(spellLevel, entity);
        float scale = 3;
        for (int r = 0; r < rings; r++) {
            float fangs = count + r * r;
            for (int i = 0; i < fangs; i++) {
                Vec3 spawn = center.add(new Vec3(0, 0, 1.5 * (r + 1)).yRot(entity.getYRot() * Mth.DEG_TO_RAD + ((15 / fangs) * i)));
                spawn = Utils.moveToRelativeGroundLevel(world, spawn, 5);
                if (!world.getBlockState(BlockPos.containing(spawn).below()).isAir()) {
                    AncientLightningStrike lightningStrike = new AncientLightningStrike(world, entity);
                    if (i % 2 == count % 2) {
                        lightningStrike.setSilent(true);
                    }
                    lightningStrike.moveTo(spawn.add(0, 0, 0));
                    lightningStrike.setDamage(damage);
                    lightningStrike.setYRot((entity.getYRot() - 45 + Utils.random.nextIntBetweenInclusive(-20, 20)));
                    lightningStrike.setXRot(Utils.random.nextIntBetweenInclusive(-15, 15));
                    world.addFreshEntity(lightningStrike);
                }
            }
        }
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    private float getDamage(int spellLevel, LivingEntity entity) {
        return getSpellPower(spellLevel, entity);
    }

    @Override
    public SpellDamageSource getDamageSource(@Nullable Entity projectile, Entity attacker) {
        return super.getDamageSource(projectile, attacker).setIFrames(0);
    }

    private int getRings(int spellLevel, LivingEntity entity) {
        return (spellLevel);
    }

    @Override
    public boolean shouldAIStopCasting(int spellLevel, Mob mob, LivingEntity target) {
        float d = 1.5f * (getRings(spellLevel, mob) + 1);
        return mob.distanceToSqr(target) > d * d * 1.2f;
    }
}