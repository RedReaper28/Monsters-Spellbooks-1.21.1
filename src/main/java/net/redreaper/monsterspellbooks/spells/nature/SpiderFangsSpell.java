package net.redreaper.monsterspellbooks.spells.nature;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.acetheeldritchking.aces_spell_utils.utils.ASUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.spells.spider_fangs.CaveSpiderFangProjectile;
import net.redreaper.monsterspellbooks.entity.spells.spider_fangs.ChaurusMandiblesProjectile;
import net.redreaper.monsterspellbooks.entity.spells.spider_fangs.IceSpiderFangProjectile;
import net.redreaper.monsterspellbooks.init.ModItems;

import java.util.List;
import java.util.Optional;

public class SpiderFangsSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "spider_fangs");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 1)),
                Component.translatable("ui.irons_spellbooks.radius", Utils.stringTruncation(getRadius(spellLevel, caster), 1)),
                Component.translatable("ui.irons_spellbooks.aoe_damage", Utils.stringTruncation(getDamage(spellLevel, caster) * .5, 1)));
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(SchoolRegistry.NATURE_RESOURCE)
            .setMaxLevel(10)
            .setCooldownSeconds(16)
            .build();

    public SpiderFangsSpell() {
        this.manaCostPerLevel = 5;
        this.baseSpellPower = 15;
        this.spellPowerPerLevel = 1;
        this.castTime = 45;
        this.baseManaCost = 50;
    }

    @Override
    public CastType getCastType() {
        return CastType.LONG;
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
        return Optional.of(SoundRegistry.ACID_ORB_CHARGE.get());
    }

    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of(SoundRegistry.SPIDER_ASPECT_CAST.get());
    }

    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        if (!world.isClientSide) {
            Vec3 look = entity.getLookAngle();

            boolean hasIceSpellBook = ASUtils.hasCurio((Player) entity, ItemRegistry.ICE_SPELL_BOOK.get());
            boolean hasPaleSpellBook = ASUtils.hasCurio((Player) entity, ModItems.DISEASE_ENCYCLOPEDIA.get());
            if (hasIceSpellBook) {
                int[] angles = new int[]{15,-15};
                for (int angle : angles) {
                    IceSpiderFangProjectile dagger = new IceSpiderFangProjectile(world, entity);
                    dagger.shoot(entity.getLookAngle());
                    dagger.setDamage(getDamageIce(spellLevel, entity));
                    dagger.setPos(entity.position().add((double) 0.0F, (double) entity.getEyeHeight() - (double) dagger.getBbHeight() * (double) 0.5F, (double) 0.0F));
                    Vec3 dir = look.yRot((float) Math.toRadians((double) angle));
                    dagger.shoot(dir.x, dir.y, dir.z, 1.25F, 0.0F);
                    world.addFreshEntity(dagger);
                }
            } else if (hasPaleSpellBook){
                int[] angles2 = new int[]{0};
                for (int angle : angles2) {
                    ChaurusMandiblesProjectile dagger = new ChaurusMandiblesProjectile(world, entity);
                    dagger.shoot(entity.getLookAngle());
                    dagger.setDamage(getDamagePale(spellLevel, entity));
                    dagger.setPos(entity.position().add((double) 0.0F, (double) entity.getEyeHeight() - (double) dagger.getBbHeight() * (double) 0.5F, (double) 0.0F));
                    Vec3 dir = look.yRot((float) Math.toRadians((double) angle));
                    dagger.shoot(dir.x, dir.y, dir.z, 1.25F, 0.0F);
                    world.addFreshEntity(dagger);
                }
            } else{
                int[] angles2 = new int[]{0, -15, 15,-30,30};
                for (int angle : angles2) {
                    CaveSpiderFangProjectile dagger = new CaveSpiderFangProjectile(world, entity);
                    dagger.shoot(entity.getLookAngle());
                    dagger.setDamage(getDamage1(spellLevel, entity));
                    dagger.setPos(entity.position().add((double) 0.0F, (double) entity.getEyeHeight() - (double) dagger.getBbHeight() * (double) 0.5F, (double) 0.0F));
                    Vec3 dir = look.yRot((float) Math.toRadians((double) angle));
                    dagger.shoot(dir.x, dir.y, dir.z, 1.25F, 0.0F);
                    world.addFreshEntity(dagger);
                }
            }
        }
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }


    public float getDamage(int spellLevel, LivingEntity caster) {
        return getSpellPower(spellLevel, caster);
    }

    public float getDamageIce(int spellLevel, LivingEntity caster) {
        double icePower = caster.getAttributeValue(AttributeRegistry.ICE_SPELL_POWER);
        return (float) (getSpellPower(spellLevel, caster)+icePower);
    }

    public float getDamagePale(int spellLevel, LivingEntity caster) {
        double icePower = caster.getAttributeValue(AttributeRegistry.ELDRITCH_SPELL_POWER);
        return (float) (getSpellPower(spellLevel, caster)+icePower);
    }

    public float getDamage1(int spellLevel, LivingEntity caster) {
        return getSpellPower(spellLevel, caster)* 0.5f;
    }

    public float getRadius(int spellLevel, LivingEntity caster) {
        return 3;
    }

    @Override
    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.BOW_CHARGE_ANIMATION;
    }

    @Override
    public AnimationHolder getCastFinishAnimation() {
        return SpellAnimations.ONE_HANDED_HORIZONTAL_SWING_ANIMATION;
    }
}

