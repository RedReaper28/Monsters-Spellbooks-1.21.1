package net.redreaper.monsterspellbooks.spells.fire;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.magic.SpellSelectionManager;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.acetheeldritchking.aces_spell_utils.spells.ASSpellAnimations;
import net.acetheeldritchking.aces_spell_utils.utils.ASUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.spells.cauterizing_touch.CauterizingTouch;
import net.redreaper.monsterspellbooks.init.ModMobEffects;

import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class CauterizingTouchSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "cauterizing_touch");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", getDamageText(spellLevel, caster)),
                Component.translatable("ui.monsterspellbooks.extra_damage"),
                Component.translatable("ui.monsterspellbooks.melee_damage"),
                Component.translatable("ui.irons_spellbooks.effect_length", Utils.timeFromTicks(getDuration(spellLevel, caster), 2))
                );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.LEGENDARY)
            .setSchoolResource(SchoolRegistry.FIRE_RESOURCE)
            .setMaxLevel(3)
            .setCooldownSeconds(180)
            .build();

    public CauterizingTouchSpell()
    {
        this.manaCostPerLevel = 5;
        this.baseSpellPower = 20;
        this.spellPowerPerLevel = 10;
        this.castTime = 10;
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
        return CastType.INSTANT;
    }

    @Override
    public AnimationHolder getCastStartAnimation() {
        return ASSpellAnimations.ANIMATION_SPELL_PUNCH;
    }

    public Optional<SoundEvent> getCastStartSound() {return Optional.of(SoundRegistry.FIRE_CAST.get());}

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        float radius = 2.15F;
        float distance = 1F;
        Vec3 hitLocation = entity.position().add(0, entity.getBbHeight() * 0.7F, 0).add(entity.getForward().multiply(distance, 0.35F, distance));
        var entities = entity.level().getEntities(entity, AABB.ofSize(hitLocation, radius * 2, radius, radius * 2));

        for (Entity target : entities)
        {
            if (entity.isPickable() && entity.distanceToSqr(target) < radius * radius && Utils.hasLineOfSight(level, entity.getEyePosition(), target.getBoundingBox().getCenter(), true) && !(target instanceof ItemEntity))
            {
                if (DamageSources.applyDamage(target, getDamage(spellLevel, entity), this.getDamageSource(entity)))
                {
                    int i = getDuration(spellLevel, entity);
                    ((LivingEntity) target).addEffect(new MobEffectInstance(ModMobEffects.HEAL_CUT,i,0, true, true, true));
                    target.setRemainingFireTicks(i/20);
                    MagicManager.spawnParticles(level, ParticleHelper.FIERY_SPARKS, target.getX(), target.getY() + target.getBbHeight() * .5f, target.getZ(), 50, target.getBbWidth() * .5f, target.getBbHeight() * .5f, target.getBbWidth() * .5f, .03, false);
                    MagicManager.spawnParticles(level, ParticleTypes.LARGE_SMOKE, target.getX(), target.getY() + target.getBbHeight() * .5f, target.getZ(), 50, target.getBbWidth() * .5f, target.getBbHeight() * .5f, target.getBbWidth() * .5f, .03, false);

                }
            }
        }
        boolean mirrored = playerMagicData.getCastingEquipmentSlot().equals(SpellSelectionManager.OFFHAND);

        CauterizingTouch swipe = new CauterizingTouch(level, mirrored);
        swipe.moveTo(hitLocation);
        swipe.setYRot(entity.getYRot());
        level.addFreshEntity(swipe);

        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    public int getDuration(int spellLevel, LivingEntity caster) {
        return (int) (getSpellPower(spellLevel, caster) * 30);
    }

    private float getDamage(int spellLevel, LivingEntity caster)
    {
        float baseDamage = ASUtils.getDamageForAttributes(this, caster, spellLevel, Attributes.ATTACK_DAMAGE, 0.35F);

        return baseDamage + getWeaponDamage(caster);
    }

    private float getWeaponDamage(LivingEntity caster)
    {
        float weaponDamage = Utils.getWeaponDamage(caster);

        return weaponDamage;
    }

    private String getDamageText(int spellLevel, LivingEntity caster)
    {
        if (caster != null)
        {
            float weaponDamage = Utils.getWeaponDamage(caster);
            String plus = "";
            if (weaponDamage > 0)
            {
                plus = String.format(" (+%s)", Utils.stringTruncation(weaponDamage, 1));
            }
            String damage = Utils.stringTruncation(getDamage(spellLevel, caster), 1);
            return damage + plus;
        }
        return "" + getSpellPower(spellLevel, caster);
    }
}
