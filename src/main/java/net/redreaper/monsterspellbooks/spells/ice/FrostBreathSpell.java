package net.redreaper.monsterspellbooks.spells.ice;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.acetheeldritchking.aces_spell_utils.spells.ASSpellAnimations;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.entity.spells.frost_breath.FrostBreathEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class FrostBreathSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "frost_breath");

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.COMMON)
            .setSchoolResource(SchoolRegistry.ICE_RESOURCE)
            .setMaxLevel(1)
            .setCooldownSeconds(1)
            .build();

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2)));
    }

    public FrostBreathSpell() {
        this.manaCostPerLevel = 1;
        this.baseSpellPower = 15;
        this.spellPowerPerLevel = 1;
        this.castTime = 0;
        this.baseManaCost = 10;
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

    public boolean allowCrafting() {
        return false;
    }

    public boolean allowLooting() {
        return false;
    }

    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of((SoundEvent) SoundRegistry.GUST_CAST.get());
    }

    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        Vec3 origin = entity.getEyePosition();
        Vec3 forward = entity.getForward();
        double offset = (double)0.5F;
        int numberOfEntities = 3;
        Vec3 right = (new Vec3(forward.z, (double)0.0F, -forward.x)).normalize();

        for(int i = 0; i < numberOfEntities; ++i) {
            FrostBreathEntity fireball = new FrostBreathEntity(world, entity);
            fireball.setDamage(this.getDamage(spellLevel, entity) / 3.0F);
            fireball.setExplosionRadius((float)this.getRadius(spellLevel, entity));
            fireball.setPos(origin.add(forward.scale((double)0.5F)).add(right.scale(offset * (double)i)).subtract((double)0.0F, (double)(fireball.getBbHeight() / 2.0F), (double)0.0F));
            fireball.shoot(entity.getLookAngle());
            fireball.setInfinitePiercing();
            world.addFreshEntity(fireball);
        }

        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    private float getDamage(int spellLevel, LivingEntity entity) {
        return getSpellPower(spellLevel, entity) * .5f;
    }

    @Override
    public SpellDamageSource getDamageSource(@Nullable Entity projectile, Entity attacker) {
        return super.getDamageSource(projectile, attacker).setFreezeTicks(160);
    }


    public int getRadius(int spellLevel, LivingEntity caster) {
        return 2 + (int)this.getSpellPower(spellLevel, caster);
    }

    public AnimationHolder getCastFinishAnimation() {
        return ASSpellAnimations.ANIMATION_SPELL_PUNCH;
    }
}