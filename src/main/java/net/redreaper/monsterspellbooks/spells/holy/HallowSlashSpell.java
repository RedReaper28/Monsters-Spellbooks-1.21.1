package net.redreaper.monsterspellbooks.spells.holy;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import net.acetheeldritchking.aces_spell_utils.spells.ASSpellAnimations;
import net.acetheeldritchking.aces_spell_utils.utils.ASUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.spells.hallow_slash.HallowSlashProjectile;
import net.redreaper.monsterspellbooks.events.ModUtils;

import java.util.List;

@AutoSpellConfig
public class HallowSlashSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "hallow_slash");
    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(SchoolRegistry.HOLY_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(45)
            .build();

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.damage", getDamageText(spellLevel, caster)));
    }

    public HallowSlashSpell() {
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
        HallowSlashProjectile slash = new HallowSlashProjectile(world, entity);
        slash.setPos(entity.getEyePosition());
        slash.shoot(entity.getLookAngle());
        slash.setDamage(getSpellPower(spellLevel, entity));
        world.addFreshEntity(slash);
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    private float getDamage(int spellLevel, LivingEntity caster)
    {
        float baseDamage = ASUtils.getDamageForAttributes(this, caster, spellLevel, Attributes.MAX_HEALTH, 0.50f);
        float extraDamage = ASUtils.getDamageForAttributes(this, caster, spellLevel, Attributes.MAX_ABSORPTION, 0.25f);

        return baseDamage + extraDamage;
    }

    private String getDamageText(int spellLevel, LivingEntity caster)
    {
        if (caster != null)
        {
            float extraDamage = ModUtils.getEntityHp(caster) * .50f;
            float extraDamage2 = ModUtils.getEntityExtraHp(caster);
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
    public AnimationHolder getCastStartAnimation() {
        return ASSpellAnimations.ANIMATION_RIGHT_HORIZONTAL_SWORD_SLASH;
    }
}
