package net.redreaper.monsterspellbooks.spells.ice;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.spells.ice_arsenal.IceArsenalSword;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@AutoSpellConfig
public class IceArsenalSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "ice_arsenal");

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.EPIC)
            .setSchoolResource(SchoolRegistry.ICE_RESOURCE)
            .setMaxLevel(10)
            .setCooldownSeconds(30)
            .build();

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.projectile_count", getCount(spellLevel)));
    }

    public IceArsenalSpell() {
        this.manaCostPerLevel = 5;
        this.baseSpellPower = 20;
        this.spellPowerPerLevel = 3;
        this.castTime = 0;
        this.baseManaCost = 10;
    }

    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        int count = getCount(spellLevel);
        float damage = getDamage(spellLevel, entity);
        int degreesPerNeedle = 360 / count;
        var raycast = Utils.raycastForEntity(world, entity, 32, true);
        for (int i = 0; i < count; i++) {
            IceArsenalSword needle = new IceArsenalSword(world, entity);
            int rotation = degreesPerNeedle * i - (degreesPerNeedle / 2);
            needle.setDamage(damage);
            needle.setZRot(rotation);
            Vec3 spawn = entity.getEyePosition().add(new Vec3(0, 1.5, 0).zRot(rotation * Mth.DEG_TO_RAD).xRot(-entity.getXRot() * Mth.DEG_TO_RAD).yRot(-entity.getYRot() * Mth.DEG_TO_RAD));
            needle.moveTo(spawn);
            needle.shoot(raycast.getLocation().subtract(spawn).normalize());
            world.addFreshEntity(needle);
        }
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }


    public boolean allowLooting() {
        return false;
    }

    public CastType getCastType() {
        return CastType.INSTANT;
    }

    public DefaultConfig getDefaultConfig() {
        return this.defaultConfig;
    }

    public ResourceLocation getSpellResource() {
        return this.spellId;
    }

    private float getDamage(int spellLevel, LivingEntity entity) {
        return this.getSpellPower(spellLevel, entity) * 0.1F;
    }

    private int getCount(int spellLevel) {
        return spellLevel;
    }

    public SpellDamageSource getDamageSource(@Nullable Entity projectile, Entity attacker) {
        return super.getDamageSource(projectile, attacker).setFreezeTicks(80).setIFrames(0);
    }
}

