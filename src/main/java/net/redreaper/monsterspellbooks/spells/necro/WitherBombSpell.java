package net.redreaper.monsterspellbooks.spells.necro;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.RecastInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.spells.wither_bomb.WitherBombProjectile;
import net.redreaper.monsterspellbooks.init.ModSpellSchools;

import java.util.List;
import java.util.Optional;

public class WitherBombSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "wither_bomb");
    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(ModSpellSchools.NECRO_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(45)
            .build();

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.radius", getRadius(spellLevel, caster))        );
    }

    public WitherBombSpell() {
        this.manaCostPerLevel = 15;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 0;
        this.baseManaCost = 100;
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
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of(SoundEvents.WITHER_SHOOT);
    }

    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        if (!world.isClientSide) {
            Vec3 look = entity.getLookAngle();
            int[] angles = new int[]{0, -60, 60};
            for (int angle : angles) {
                WitherBombProjectile fireball = new WitherBombProjectile(world, entity);
                fireball.setOwner(entity);
                fireball.setDamage(this.getDamage(spellLevel, entity));
                fireball.setExplosionRadius(this.getRadius(spellLevel, entity));
                fireball.setPos(entity.position().add((double) 0.0F, (double) entity.getEyeHeight() - (double) fireball.getBbHeight() * (double) 0.5F, (double) 0.0F));
                Vec3 dir = look.yRot((float) Math.toRadians((double) angle));
                fireball.shoot(dir.x, dir.y, dir.z, 1.25F, 0.0F);
                fireball.setCursorHoming(true);
                world.addFreshEntity(fireball);
                super.onCast(world, spellLevel, entity, castSource, playerMagicData);
            }
        }
    }


    public float getDamage(int spellLevel, LivingEntity caster) {
        return 5 + 5 * getSpellPower(spellLevel, caster);
    }

    public int getRadius(int spellLevel, LivingEntity caster) {
        return 2 + (int) getSpellPower(spellLevel, caster);
    }
}
