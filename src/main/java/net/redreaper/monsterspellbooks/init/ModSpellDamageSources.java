package net.redreaper.monsterspellbooks.init;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ModSpellDamageSources extends SpellDamageSource {
    AbstractSpell spell;
    float manasteal;
    protected ModSpellDamageSources(@NotNull Entity directEntity, @NotNull Entity causingEntity, @Nullable Vec3 damageSourcePosition, AbstractSpell spell) {
        super(directEntity, causingEntity, damageSourcePosition, spell);
    }

    public ModSpellDamageSources setManastealPercent(float manasteal) {
        this.manasteal = manasteal;
        return this;
    }

    public DamageSource get() {
        return this;
    }

    public AbstractSpell spell() {
        return this.spell;
    }

    public float getManastealPercent() {
        return this.manasteal;
    }

    public boolean hasPostHitEffects() {
        return getManastealPercent() > 0;
    }

}
