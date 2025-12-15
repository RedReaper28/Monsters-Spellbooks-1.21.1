package net.redreaper.monsterspellbooks.spells.necro;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.capabilities.magic.RecastInstance;
import io.redspace.ironsspellbooks.damage.DamageSources;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.spells.spectral_blast.SpectralBlastVisualEntity;
import net.redreaper.monsterspellbooks.init.ModMobEffects;
import net.redreaper.monsterspellbooks.init.ModSpellSchools;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class SpectralBlastSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "spectral_blast");
    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(ModSpellSchools.NECRO_RESOURCE)
            .setMaxLevel(3)
            .setCooldownSeconds(90)
            .build();

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", new Object[]{Utils.stringTruncation((double)this.getDamage(spellLevel, caster), 2)}),
                Component.translatable("ui.irons_spellbooks.blast_count", new Object[]{this.getRecastCount(spellLevel, caster)}),
                Component.translatable("ui.irons_spellbooks.distance", new Object[]{Utils.stringTruncation((double)getRange(spellLevel, caster), 1)}));
    }

    public SpectralBlastSpell() {
        this.manaCostPerLevel = 15;
        this.baseSpellPower = 10;
        this.spellPowerPerLevel = 0;
        this.castTime = 0;
        this.baseManaCost = 20;
    }

    public CastType getCastType() {
        return CastType.INSTANT;
    }

    public ResourceLocation getSpellResource() {
        return this.spellId;
    }

    public DefaultConfig getDefaultConfig() {
        return this.defaultConfig;
    }

    public Optional<SoundEvent> getCastFinishSound() {return Optional.of(SoundEvents.WITHER_SHOOT);}

    public int getRecastCount(int spellLevel, @Nullable LivingEntity entity) {
        return spellLevel;
    }

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        if (!playerMagicData.getPlayerRecasts().hasRecastForSpell(this.getSpellId())) {
            playerMagicData.getPlayerRecasts().addRecast(new RecastInstance(this.getSpellId(), spellLevel, this.getRecastCount(spellLevel, entity), 80, castSource, (ICastDataSerializable)null), playerMagicData);
        }
        HitResult hitResult = Utils.raycastForEntity(level, entity, getRange(spellLevel, entity), true, 0.15F);
        level.addFreshEntity(new SpectralBlastVisualEntity(level, entity.getEyePosition().subtract((double)0.0F, (double)0.75F, (double)0.0F), hitResult.getLocation(), entity));
        if (hitResult.getType() == HitResult.Type.ENTITY) {
            Entity target = ((EntityHitResult)hitResult).getEntity();
            if (target instanceof LivingEntity) {
                DamageSources.applyDamage(target, this.getDamage(spellLevel, entity), this.getDamageSource(entity));
                ((LivingEntity) target).addEffect(new MobEffectInstance(MobEffects.WITHER, (int) (getSpellPower(spellLevel, entity) * 20),spellLevel-1, false, true, true));
                ((LivingEntity) target).addEffect(new MobEffectInstance(ModMobEffects.LETHARGY, (int) (getSpellPower(spellLevel, entity) * 20),  0, false, true, true));
            }
        }
        MagicManager.spawnParticles(level, ModParticleHelper.REAPER_FIRE, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z, 50, (double)0.0F, (double)0.0F, (double)0.0F, 0.3, false);
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    public static float getRange(int level, LivingEntity caster) {
        return 20F;
    }

    private float getDamage(int spellLevel, LivingEntity caster) {
        return this.getSpellPower(spellLevel, caster);
    }
}

