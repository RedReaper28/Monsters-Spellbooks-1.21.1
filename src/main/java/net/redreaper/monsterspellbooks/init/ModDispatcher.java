package net.redreaper.monsterspellbooks.init;

import mod.azure.azurelib.common.animation.dispatch.command.AzCommand;
import mod.azure.azurelib.common.animation.play_behavior.AzPlayBehaviors;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

public class ModDispatcher {

    private static final AzCommand IDLE_COMMAND = AzCommand.create(
            "base_controller",
            "idle",
            AzPlayBehaviors.LOOP
    );

    private static final AzCommand ELYTRA_FLIGHT_COMMAND = AzCommand.create(
            "base_controller",
            "flying",
            AzPlayBehaviors.LOOP
    );

    private static final AzCommand SHIELD_BLOCKING_RELEASE_COMMAND = AzCommand.create(
            "base_controller",
            "block_release",
            AzPlayBehaviors.HOLD_ON_LAST_FRAME
    );

    private static final AzCommand SHIELD_BLOCKING_COMMAND = AzCommand.create(
            "base_controller",
            "block",
            AzPlayBehaviors.HOLD_ON_LAST_FRAME
    );

    public void idle(Entity entity, ItemStack itemStack) {
        IDLE_COMMAND.sendForItem(entity, itemStack);
    }

    public void flight(Entity entity, ItemStack itemStack) {
        ELYTRA_FLIGHT_COMMAND.sendForItem(entity, itemStack);
    }

    public void block_release (Entity entity, ItemStack itemStack) {
        SHIELD_BLOCKING_RELEASE_COMMAND.sendForItem(entity, itemStack);
    }

    public void block (Entity entity, ItemStack itemStack) {
        SHIELD_BLOCKING_COMMAND.sendForItem(entity, itemStack);
    }
}
