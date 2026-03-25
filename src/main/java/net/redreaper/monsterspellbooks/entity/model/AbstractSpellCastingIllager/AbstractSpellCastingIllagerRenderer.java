package net.redreaper.monsterspellbooks.entity.model.AbstractSpellCastingIllager;

import com.mojang.blaze3d.vertex.PoseStack;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.entity.mobs.HumanoidRenderer;
import io.redspace.ironsspellbooks.player.ClientMagicData;
import io.redspace.ironsspellbooks.render.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.redreaper.monsterspellbooks.entity.living.AbstractSpellCastingIllager;
import org.jetbrains.annotations.Nullable;

public class AbstractSpellCastingIllagerRenderer extends HumanoidRenderer<AbstractSpellCastingIllager> {
    private ResourceLocation textureResource;

    public AbstractSpellCastingIllagerRenderer(EntityRendererProvider.Context renderManager, AbstractSpellCastingIllagerModel model) {
        super(renderManager, model);
    }

    public static ItemStack makePotion(AbstractSpellCastingIllager entity) {
        ItemStack healthPotion = new ItemStack(Items.POTION);
        return Utils.setPotion(healthPotion, entity.isInvertedHealAndHarm() ? Potions.HARMING : Potions.HEALING);
    }

    @Override
    public void render(AbstractSpellCastingIllager entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);

        SpellRenderingHelper.renderSpellHelper(ClientMagicData.getSyncedSpellData(entity), entity, poseStack, bufferSource, partialTick);

    }

    @Override
    public RenderType getRenderType(AbstractSpellCastingIllager animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return animatable.isInvisible() ? RenderType.entityTranslucent(texture) : super.getRenderType(animatable, texture, bufferSource, partialTick);
    }
}
