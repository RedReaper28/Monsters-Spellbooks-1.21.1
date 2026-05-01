package net.redreaper.monsterspellbooks.spells.necro;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.entity.spells.AbstractConeProjectile;
import io.redspace.ironsspellbooks.spells.EntityCastData;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.spells.life_drain.LifeDrainProjectile;
import net.redreaper.monsterspellbooks.init.ModMobEffects;
import net.redreaper.monsterspellbooks.init.ModSpellSchools;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LifeDrainSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "life_drain");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.monsterspellbooks.mana_steal", Utils.stringTruncation((getManaStealAmplifier(spellLevel, caster)) * 10, 1)),
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2)));
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.UNCOMMON)
            .setSchoolResource(ModSpellSchools.NECRO_RESOURCE)
            .setMaxLevel(10)
            .setCooldownSeconds(45)
            .build();

    public LifeDrainSpell() {
        this.manaCostPerLevel = 5;
        this.baseSpellPower = 0;
        this.spellPowerPerLevel = 1;
        this.castTime = 100;
        this.baseManaCost = 5;
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

    public AnimationHolder getCastStartAnimation() {return SpellAnimations.ANIMATION_CONTINUOUS_CAST_ONE_HANDED;}

    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        entity.addEffect(new MobEffectInstance(ModMobEffects.MANA_ABSORPTION,20, getManaStealAmplifier(spellLevel, entity), false, false, false));
        if (playerMagicData.isCasting()
                && playerMagicData.getCastingSpellId().equals(this.getSpellId())
                && playerMagicData.getAdditionalCastData() instanceof EntityCastData entityCastData
                && entityCastData.getCastingEntity() instanceof AbstractConeProjectile cone) {
            cone.setDealDamageActive();
        } else {
            LifeDrainProjectile coneOfColdProjectile = new LifeDrainProjectile(world, entity);
            coneOfColdProjectile.setPos(entity.position().add(0, entity.getEyeHeight() * .7, 0));
            coneOfColdProjectile.setDamage(getDamage(spellLevel, entity));
            world.addFreshEntity(coneOfColdProjectile);
            playerMagicData.setAdditionalCastData(new EntityCastData(coneOfColdProjectile));
            super.onCast(world, spellLevel, entity, castSource, playerMagicData);
        }
    }

    public int getManaStealAmplifier(int spellLevel, LivingEntity caster) {
        return (spellLevel);
    }

    public float getDamage(int spellLevel, LivingEntity caster) {return getSpellPower(spellLevel, caster) * .25f;}

    public SpellDamageSource getDamageSource(@Nullable Entity projectile, Entity attacker) {return super.getDamageSource(projectile, attacker);}

    @Override
    public boolean shouldAIStopCasting(int spellLevel, Mob mob, LivingEntity target) {
        return mob.distanceToSqr(target) > (10 * 10) * 1.2;
    }
}

