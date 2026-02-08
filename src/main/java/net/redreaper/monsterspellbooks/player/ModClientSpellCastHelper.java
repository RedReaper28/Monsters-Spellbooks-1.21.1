package net.redreaper.monsterspellbooks.player;

import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.util.MinecraftInstanceHelper;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;
import net.redreaper.monsterspellbooks.spells.lightning.ThunderStepSpell;
import org.joml.Vector3f;

public class ModClientSpellCastHelper {

    public static void handleClientboundElectricExplosion(Vec3 pos, float radius) {
        MinecraftInstanceHelper.ifPlayerPresent(player -> {
            var level = player.level();
            var x = pos.x;
            var y = pos.y;
            var z = pos.z;
            //Blastwave
            level.addParticle(new BlastwaveParticleOptions(new Vector3f(1, .6f, 0.3f), radius + 1), x, y, z, 0, 0, 0);
            //Billowing wave
            int c = (int) (6.28 * radius) * 2;
            float step = 360f / c * Mth.DEG_TO_RAD;
            float speed = (0.06f + 0.01f * radius) * 2;
            for (int i = 0; i < c; i++) {
                Vec3 vec3 = new Vec3(Mth.cos(step * i), 0, Mth.sin(step * i)).scale(speed);
                Vec3 posOffset = Utils.getRandomVec3(.5f).add(vec3.scale(10));
                vec3 = vec3.add(Utils.getRandomVec3(0.01));
                level.addParticle(ModParticleHelper.ELECTRIC_SMOKE, x + posOffset.x, y + posOffset.y, z + posOffset.z, vec3.x, vec3.y, vec3.z);
            }
            //Smoke Cloud
            int cloudDensity = 50 + (int) (25 * radius);
            for (int i = 0; i < cloudDensity; i++) {
                Vec3 posOffset = Utils.getRandomVec3(1).scale(radius * .125f);
                Vec3 motion = posOffset.normalize().scale(speed * .5f);
                posOffset = posOffset.add(motion.scale(Utils.getRandomScaled(1)));
                motion = motion.add(Utils.getRandomVec3(speed * .1f));
                level.addParticle(ModParticleHelper.ELECTRIC_SMOKE, x + posOffset.x, y + posOffset.y, z + posOffset.z, motion.x, motion.y, motion.z);
            }
            //Cloud
            for (int i = 0; i < cloudDensity; i += 2) {
                Vec3 posOffset = Utils.getRandomVec3(1).scale(radius * .4f);
                Vec3 motion = posOffset.normalize().scale(speed * .5f);
                motion = motion.add(Utils.getRandomVec3(0.25));
                level.addParticle(ParticleHelper.ELECTRICITY, true, x + posOffset.x, y + posOffset.y, z + posOffset.z, motion.x, motion.y, motion.z);
                level.addParticle(ParticleHelper.ELECTRICITY, x + posOffset.x * .5f, y + posOffset.y * .5f, z + posOffset.z * .5f, motion.x, motion.y, motion.z);
            }
            //Sparks
            for (int i = 0; i < cloudDensity; i += 2) {
                Vec3 posOffset = Utils.getRandomVec3(radius).scale(.2f);
                Vec3 motion = posOffset.normalize().scale(0.8);
                motion = motion.add(Utils.getRandomVec3(0.18));
                level.addParticle(ParticleHelper.ELECTRIC_SPARKS, x + posOffset.x * .5f, y + posOffset.y * .5f, z + posOffset.z * .5f, motion.x, motion.y, motion.z);
            }
        });
    }

    public static void handleClientboundSoulExplosion(Vec3 pos, float radius) {
        MinecraftInstanceHelper.ifPlayerPresent(player -> {
            var level = player.level();
            var x = pos.x;
            var y = pos.y;
            var z = pos.z;
            //Blastwave
            level.addParticle(new BlastwaveParticleOptions(new Vector3f(1, .6f, 0.3f), radius + 1), x, y, z, 0, 0, 0);
            //Billowing wave
            int c = (int) (6.28 * radius) * 2;
            float step = 360f / c * Mth.DEG_TO_RAD;
            float speed = (0.06f + 0.01f * radius) * 2;
            for (int i = 0; i < c; i++) {
                Vec3 vec3 = new Vec3(Mth.cos(step * i), 0, Mth.sin(step * i)).scale(speed);
                Vec3 posOffset = Utils.getRandomVec3(.5f).add(vec3.scale(10));
                vec3 = vec3.add(Utils.getRandomVec3(0.01));
                level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + posOffset.x, y + posOffset.y, z + posOffset.z, vec3.x, vec3.y, vec3.z);
            }
            //Smoke Cloud
            int cloudDensity = 50 + (int) (25 * radius);
            for (int i = 0; i < cloudDensity; i++) {
                Vec3 posOffset = Utils.getRandomVec3(1).scale(radius * .125f);
                Vec3 motion = posOffset.normalize().scale(speed * .5f);
                posOffset = posOffset.add(motion.scale(Utils.getRandomScaled(1)));
                motion = motion.add(Utils.getRandomVec3(speed * .1f));
                level.addParticle(ModParticleHelper.SOUL_SMOKE, x + posOffset.x, y + posOffset.y, z + posOffset.z, motion.x, motion.y, motion.z);
            }
            //Cloud
            for (int i = 0; i < cloudDensity; i += 2) {
                Vec3 posOffset = Utils.getRandomVec3(1).scale(radius * .4f);
                Vec3 motion = posOffset.normalize().scale(speed * .5f);
                motion = motion.add(Utils.getRandomVec3(0.25));
                level.addParticle(ModParticleHelper.SOUL_FIRE, true, x + posOffset.x, y + posOffset.y, z + posOffset.z, motion.x, motion.y, motion.z);
                level.addParticle(ParticleTypes.SOUL, x + posOffset.x * .5f, y + posOffset.y * .5f, z + posOffset.z * .5f, motion.x, motion.y, motion.z);
            }
            //Sparks
            for (int i = 0; i < cloudDensity; i += 2) {
                Vec3 posOffset = Utils.getRandomVec3(radius).scale(.2f);
                Vec3 motion = posOffset.normalize().scale(0.8);
                motion = motion.add(Utils.getRandomVec3(0.18));
                level.addParticle(ParticleHelper.ELECTRIC_SPARKS, x + posOffset.x * .5f, y + posOffset.y * .5f, z + posOffset.z * .5f, motion.x, motion.y, motion.z);
            }
        });
    }

    public static void handleClientboundThunderStep(Vec3 pos1, Vec3 pos2) {
        var player = Minecraft.getInstance().player;

        if (player != null) {
            var level = Minecraft.getInstance().player.level();
            ThunderStepSpell.particleCloud(level, pos1);
            ThunderStepSpell.particleCloud(level, pos2);
        }
    }
}
