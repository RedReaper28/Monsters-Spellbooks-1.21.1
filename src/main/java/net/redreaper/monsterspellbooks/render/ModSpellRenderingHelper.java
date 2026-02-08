package net.redreaper.monsterspellbooks.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.SyncedSpellData;
import io.redspace.ironsspellbooks.spells.CastingMobAimingData;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import net.redreaper.monsterspellbooks.spells.fire.BrimstoneWrathSpell;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

@OnlyIn(Dist.CLIENT)
public class ModSpellRenderingHelper {
    public static final ResourceLocation SOLID = MonstersSpellbooks.id("textures/entity/brimstone_wrath/solid.png");
    public static final ResourceLocation BRIMSTONE_CORE = MonstersSpellbooks.id("textures/entity/brimstone_wrath/beacon_beam.png");
    public static final ResourceLocation BRIMSTONE_TWISTING_GLOW = MonstersSpellbooks.id("textures/entity/brimstone_wrath/twisting_glow.png");
    public static final ResourceLocation STRAIGHT_GLOW = MonstersSpellbooks.id("textures/brimstone_wrath/ray/ribbon_glow.png");

    public static void renderSpellHelper(SyncedSpellData spellData, LivingEntity castingMob, PoseStack poseStack, MultiBufferSource bufferSource, float partialTicks) {
        if (ModSpellRegistry.BRIMSTONE_WRATH.get().getSpellId().equals(spellData.getCastingSpellId())) {
            renderBrimstoneWrath(castingMob, poseStack, bufferSource, partialTicks);
        }
    }

    public static void renderBrimstoneWrath(LivingEntity entity, PoseStack poseStack, MultiBufferSource bufferSource, float partialTicks) {

        poseStack.pushPose();
        poseStack.translate(0, entity.getEyeHeight() * .8f, 0);

        var pose = poseStack.last();
        Vec3 start = Vec3.ZERO;
        Vec3 end;
        Vec3 rayEndPos;
        if (entity instanceof Mob mob && MagicData.getPlayerMagicData(mob).getAdditionalCastData() instanceof CastingMobAimingData aimingData) {
            rayEndPos = aimingData.getAimPosition(partialTicks);
        } else {
            rayEndPos = Utils.raycastForEntity(entity.level(), entity, BrimstoneWrathSpell.getRange(0), true).getLocation();
        }
        float distance = (float) entity.getEyePosition().distanceTo(rayEndPos);
        float radius = .48f;
        int r = (int) (255 * .7f);
        int g = (int) (255 * 0f);
        int b = (int) (255 * 0f);
        int a = (int) (255 * 1f);

        float deltaTicks = entity.tickCount + partialTicks;
        float deltaUV = -deltaTicks % 10;
        float max = Mth.frac(deltaUV * 0.2F - (float) Mth.floor(deltaUV * 0.1F));
        float min = -1.0F + max;

        var dir = entity.getLookAngle().normalize();

        float dx = (float) dir.x;
        float dz = (float) dir.z;
        float yRot = (float) Mth.atan2(dz, dx) - 1.5707f; // for some reason, we are rotated 90 degrees the wrong way. subtracting 2 pi here.
        float dxz = Mth.sqrt(dx * dx + dz * dz);
        float dy = (float) dir.y;
        float xRot = (float) Mth.atan2(dy, dxz);
        poseStack.mulPose(Axis.YP.rotation(-yRot));
        poseStack.mulPose(Axis.XP.rotation(-xRot));
        for (float j = 1; j <= distance; j += .5f) {
            Vec3 wiggle = new Vec3(
                    Mth.sin(deltaTicks * .8f) * .02f,
                    Mth.sin(deltaTicks * .8f + 100) * .02f,
                    Mth.cos(deltaTicks * .8f) * .02f
            );
            //end = dir.scale(Math.min(j, distance)).add(wiggle);
            end = new Vec3(0, 0, Math.min(j, distance)).add(wiggle);
            VertexConsumer inner = bufferSource.getBuffer(RenderType.entityTranslucent(BRIMSTONE_CORE, true));
            drawHull(start, end, radius, radius, pose, inner, r, g, b, a, min, max);
            //drawHull(start, end, .25f, .25f, pose, outer, r / 2, g / 2, b / 2, a / 2);
            VertexConsumer outer = bufferSource.getBuffer(RenderType.entityTranslucent(BRIMSTONE_TWISTING_GLOW));
            drawQuad(start, end, radius * 4f, 0, pose, outer, r, g, b, a, min, max);
            drawQuad(start, end, 0, radius * 4f, pose, outer, r, g, b, a, min, max);
            start = end;
        }
        poseStack.popPose();
    }

    public static void drawHull(Vec3 from, Vec3 to, float width, float height, PoseStack.Pose pose, VertexConsumer consumer, int r, int g, int b, int a, float uvMin, float uvMax) {
        //Bottom
        drawQuad(from.subtract(0, height * 0.5, 0), to.subtract(0, height * 0.5, 0), width, 0, pose, consumer, r, g, b, a, uvMin, uvMax);
        //Top
        drawQuad(from.add(0, height * 0.5, 0), to.add(0, height * 0.5, 0), width, 0, pose, consumer, r, g, b, a, uvMin, uvMax);
        //Left
        drawQuad(from.subtract(width * 0.5, 0, 0), to.subtract(width * 0.5, 0, 0), 0, height, pose, consumer, r, g, b, a, uvMin, uvMax);
        //Right
        drawQuad(from.add(width * 0.5, 0, 0), to.add(width * 0.5, 0, 0), 0, height, pose, consumer, r, g, b, a, uvMin, uvMax);
    }

    public static void drawQuad(Vec3 from, Vec3 to, float width, float height, PoseStack.Pose pose, VertexConsumer consumer, int r, int g, int b, int a, float uvMin, float uvMax) {
        Matrix4f poseMatrix = pose.pose();
        Matrix3f normalMatrix = pose.normal();

        float halfWidth = width * .5f;
        float halfHeight = height * .5f;

        consumer.addVertex(poseMatrix, (float) from.x - halfWidth, (float) from.y - halfHeight, (float) from.z).setColor(255, 255, 255, 255).setUv(0f, uvMin).setOverlay(OverlayTexture.NO_OVERLAY).setLight(240).setNormal(0f, 1f, 0f);
        consumer.addVertex(poseMatrix, (float) from.x + halfWidth, (float) from.y + halfHeight, (float) from.z).setColor(255, 255, 255, 255).setUv(1f, uvMin).setOverlay(OverlayTexture.NO_OVERLAY).setLight(240).setNormal(0f, 1f, 0f);
        consumer.addVertex(poseMatrix, (float) to.x + halfWidth, (float) to.y + halfHeight, (float) to.z).setColor(255, 255, 255, 255).setUv(1f, uvMax).setOverlay(OverlayTexture.NO_OVERLAY).setLight(240).setNormal(0f, 1f, 0f);
        consumer.addVertex(poseMatrix, (float) to.x - halfWidth, (float) to.y - halfHeight, (float) to.z).setColor(255, 255, 255, 255).setUv(0f, uvMax).setOverlay(OverlayTexture.NO_OVERLAY).setLight(240).setNormal(0f, 1f, 0f);
    }
}
