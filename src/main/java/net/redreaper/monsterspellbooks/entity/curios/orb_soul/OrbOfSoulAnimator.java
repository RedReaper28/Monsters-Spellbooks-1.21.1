package net.redreaper.monsterspellbooks.entity.curios.orb_soul;

import mod.azure.azurelib.common.animation.controller.AzAnimationController;
import mod.azure.azurelib.common.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.common.animation.easing.AzEasingTypeRegistry;
import mod.azure.azurelib.common.animation.impl.AzItemAnimator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import org.jetbrains.annotations.NotNull;

public class OrbOfSoulAnimator extends AzItemAnimator {
    private static final ResourceLocation ANIMATIONS = ResourceLocation.fromNamespaceAndPath(
            MonstersSpellbooks.MOD_ID,
            "animations/curios/orb_soul.animation.json"
    );

    @Override
    public void registerControllers(AzAnimationControllerContainer<ItemStack> animationControllerContainer) {
        animationControllerContainer.add(
                AzAnimationController.builder(this, "base_controller")
                        .setEasingType(AzEasingTypeRegistry.getOrNull("linear"))
                        .setTransitionLength(6)
                        .build()
        );
    }

    @Override
    public @NotNull ResourceLocation getAnimationLocation(ItemStack animatable) {
        return ANIMATIONS;
    }
}