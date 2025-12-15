package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3;


import java.util.Objects;

import static io.redspace.ironsspellbooks.api.util.Utils.random;

public class FearMobEffect extends MagicMobEffect {
    public FearMobEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int pAmplifier) {
        {
                    if (entity instanceof PathfinderMob mob && (!(mob instanceof TamableAnimal) || !((TamableAnimal) mob).isInSittingPose())) {
                        mob.setTarget(null);
                        mob.setLastHurtByMob(null);
                        if (mob.onGround()) {
                            Vec3 randomShake = new Vec3(random.nextFloat() - 0.5F, 0, random.nextFloat() - 0.5F).scale(0.1F);
                            mob.setDeltaMovement(mob.getDeltaMovement().multiply(0.7F, 1, 0.7F).add(randomShake));
                        }
                        if (mob.getNavigation().isDone()) {
                            Vec3 vec = LandRandomPos.getPosAway(mob, 15, 7, Objects.requireNonNull(LandRandomPos.getPos(mob, 4, 2)));
                            if (vec != null) {
                                mob.getNavigation().moveTo(vec.x, vec.y, vec.z, 2D);
                            }
                        }
                    }
                }
        return false;
    }
}



