package net.redreaper.monsterspellbooks.mixins.spell;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.spells.evocation.SlowSpell;
import net.redreaper.monsterspellbooks.init.ModSpellSchools;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SlowSpell.class)
public abstract class SlowSpellMixin extends AbstractSpell {
    @Override
    public SchoolType getSchoolType() {
        return ModSpellSchools.NECRO.get();
    }
}
