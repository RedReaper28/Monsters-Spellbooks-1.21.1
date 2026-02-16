package net.redreaper.monsterspellbooks.events;

import io.redspace.ironsspellbooks.api.entity.IMagicEntity;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.player.ClientMagicData;
import io.redspace.ironsspellbooks.player.SpinAttackType;
import io.redspace.ironsspellbooks.spells.blood.RayOfSiphoningSpell;
import io.redspace.ironsspellbooks.spells.ender.RecallSpell;
import io.redspace.ironsspellbooks.spells.fire.BurningDashSpell;
import io.redspace.ironsspellbooks.spells.fire.RaiseHellSpell;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import net.redreaper.monsterspellbooks.render.ModSpellRenderingHelper;
import net.redreaper.monsterspellbooks.spells.necro.WitherNovaSpell;

import java.util.List;

@EventBusSubscriber(Dist.CLIENT)
public class ClientPlayerEvents {

    @SubscribeEvent
    public static void afterLivingRender(RenderLivingEvent.Post<? extends LivingEntity, ? extends EntityModel<? extends LivingEntity>> event) {
        var livingEntity = event.getEntity();
        if (livingEntity instanceof Player) {
            var syncedData = ClientMagicData.getSyncedSpellData(livingEntity);
            if (syncedData.isCasting()) {
                ModSpellRenderingHelper.renderSpellHelper(syncedData, livingEntity, event.getPoseStack(), event.getMultiBufferSource(), event.getPartialTick());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event) {
        if (event.getEntity() == Minecraft.getInstance().player) {
            var level = Minecraft.getInstance().level;
            if (level != null) {
                List<Entity> spellcasters = level.getEntities((Entity) null, event.getEntity().getBoundingBox().inflate(64), (mob) -> mob instanceof Player || mob instanceof IMagicEntity);
                spellcasters.forEach((entity) -> {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    var spellData = ClientMagicData.getSyncedSpellData(livingEntity);
                    if (spellData.getCastingSpellId().equals(ModSpellRegistry.WITHER_NOVA.get().getSpellId())) {
                            WitherNovaSpell.ambientParticles(livingEntity, spellData);
                    }
                });
            }
        }
    }
}
