package net.redreaper.monsterspellbooks.utils.goals;

import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.entity.mobs.wizards.GenericAnimatedWarlockAttackGoal;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.world.entity.LivingEntity;
import net.redreaper.monsterspellbooks.entity.living.JungleLeapleafEntity;
import net.redreaper.monsterspellbooks.init.ModSounds;

public class LeapLeafAttackGoal extends GenericAnimatedWarlockAttackGoal<JungleLeapleafEntity> {
    public LeapLeafAttackGoal(JungleLeapleafEntity abstractSpellCastingMob, double pSpeedModifier, int minAttackInterval, int maxAttackInterval) {
        super(abstractSpellCastingMob, pSpeedModifier, minAttackInterval, maxAttackInterval);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void handleAttackLogic(double distanceSquared) {
        super.handleAttackLogic(distanceSquared);
    }

    @Override
    public void playSwingSound() {
        if (currentAttack != null) {
            if (currentAttack.animationId.contains("whack_1")) {
                mob.playSound(ModSounds.LEAPLEAF_MELEE_ATTACK.get());
            }
            if (currentAttack.animationId.contains("whack_2")) {
                mob.playSound(ModSounds.LEAPLEAF_MELEE_ATTACK.get());
            }
        }
        mob.playSound(ModSounds.LEAPLEAF_MELEE_ATTACK.get(), 1, Utils.random.nextIntBetweenInclusive(9, 11) * .1f);
    }

    @Override
    public void playImpactSound() {
        //todo: custom sound
//        mob.playSound(SoundRegistry.KEEPER_SWORD_IMPACT.get(), 1, Mth.randomBetweenInclusive(mob.getRandom(), 9, 13) * .1f);
    }

    public void setTarget(LivingEntity target) {
        this.target = target;
    }


    @Override
    // change access modifier
    public void doMovement(double distanceSquared) {
        super.doMovement(distanceSquared);
    }
}
