package net.redreaper.monsterspellbooks.spells.necro;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import net.acetheeldritchking.aces_spell_utils.spells.ASSpellAnimations;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.spells.vile_slash.VileSlashProjectile;
import net.redreaper.monsterspellbooks.init.ModSpellSchools;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@AutoSpellConfig
public class VileSlashSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "vile_slash");
    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(ModSpellSchools.NECRO_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(35)
            .build();

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getSpellPower(spellLevel, caster), 2)));
    }

    public VileSlashSpell() {
        this.manaCostPerLevel = 5;
        this.baseSpellPower = 10;
        this.spellPowerPerLevel = 1;
        this.castTime = 0;
        this.baseManaCost = 25;
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
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        VileSlashProjectile vileSlash = new VileSlashProjectile(world, entity);
        vileSlash.setPos(entity.getEyePosition());
        vileSlash.shoot(entity.getLookAngle());
        vileSlash.setDamage(getSpellPower(spellLevel, entity));
        world.addFreshEntity(vileSlash);
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    @Override
    public SpellDamageSource getDamageSource(@Nullable Entity projectile, Entity attacker) {
        return super.getDamageSource(projectile, attacker).setLifestealPercent(.5f);
    }

    @Override
    public AnimationHolder getCastStartAnimation() {
        return ASSpellAnimations.ANIMATION_RIGHT_HORIZONTAL_SWORD_SLASH;
    }
}

