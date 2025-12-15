package net.redreaper.monsterspellbooks.mixins.spell;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.spells.evocation.GustSpell;
import net.redreaper.monsterspellbooks.init.ModSpellSchools;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GustSpell.class)
public abstract class GustSpellMixin extends AbstractSpell {
    @Override
    public SchoolType getSchoolType() {
        return ModSpellSchools.AERO.get();
    }
}
