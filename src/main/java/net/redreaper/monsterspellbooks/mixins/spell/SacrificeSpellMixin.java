package net.redreaper.monsterspellbooks.mixins.spell;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.spells.blood.SacrificeSpell;
import net.redreaper.monsterspellbooks.init.ModSpellSchools;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SacrificeSpell.class)
public abstract class SacrificeSpellMixin extends AbstractSpell {
    @Override
    public SchoolType getSchoolType() {
        return ModSpellSchools.NECRO.get();
    }
}

