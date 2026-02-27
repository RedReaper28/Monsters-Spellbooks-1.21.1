package net.redreaper.monsterspellbooks.entity.goals;

import io.redspace.ironsspellbooks.entity.mobs.goals.melee.AttackKeyframe;
import io.redspace.ironsspellbooks.entity.mobs.wizards.GenericAnimatedWarlockAttackGoal;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.world.entity.LivingEntity;
import net.redreaper.monsterspellbooks.entity.living.BlastlingEntity;

public class BlastlingAnimatedWarlockAttackGoal extends GenericAnimatedWarlockAttackGoal<BlastlingEntity> {
    final BlastlingEntity blastling;

    public BlastlingAnimatedWarlockAttackGoal(BlastlingEntity entity, double pSpeedModifier, int minAttackInterval, int maxAttackInterval) {
        super(entity, pSpeedModifier, minAttackInterval, maxAttackInterval);
        this.blastling = entity;
        this.wantsToMelee = true;
    }

    @Override
    public void playSwingSound() {
        blastling.playSound(SoundRegistry.FORCE_IMPACT.get(), 5.0F, blastling.getVoicePitch());
    }

    @Override
    protected void handleAttackLogic(double distanceSquared) {
        super.handleAttackLogic(distanceSquared);

        if (currentAttack != null)
        {
            if (currentAttack.animationId.equals("whack"))
            {
                if (currentAttack.lengthInTicks >= 15 && isMeleeing())
                {
                    this.mob.getNavigation().stop();
                    this.mob.lerpMotion(0, 0, 0);
                }
                if (currentAttack.isHitFrame(meleeAnimTimer))
                {
                    AttackKeyframe attackData = currentAttack.getHitFrame(meleeAnimTimer);

                    var targets = mob.level().getEntitiesOfClass(target.getClass(), mob.getBoundingBox().inflate(1.25));
                    for (LivingEntity target : targets)
                    {
                        handleDamaging(target, attackData);
                    }

                }
            }
        }
    }
}