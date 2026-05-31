package net.redreaper.monsterspellbooks.item.curios;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import top.theillusivec4.curios.api.CuriosApi;

public record MessageCurioKey(int playerId, int typ)
        implements CustomPacketPayload {

    public static final Type<MessageCurioKey> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "curio_key"));

    public static final StreamCodec<RegistryFriendlyByteBuf, MessageCurioKey>
            STREAM_CODEC =
            CustomPacketPayload.codec(
                    MessageCurioKey::write,
                    MessageCurioKey::new
            );

    public MessageCurioKey(FriendlyByteBuf buf) {
        this(buf.readInt(), buf.readInt());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeInt(this.playerId);
        buf.writeInt(this.typ);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(
            MessageCurioKey message,
            IPayloadContext context
    ) {
        if (!context.flow().isServerbound()) {
            return;
        }

        context.enqueueWork(() -> {
            Player player = context.player();

            if (player == null) {
                return;
            }

            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {

                for (var stacksHandler : handler.getCurios().values()) {

                    var stacks = stacksHandler.getStacks();

                    for (int i = 0; i < stacks.getSlots(); i++) {

                        ItemStack stack = stacks.getStackInSlot(i);

                        if (stack.isEmpty()) {
                            continue;
                        }

                        if (stack.getItem() instanceof KeybindCurio curio) {
                            curio.onKeyPacket(player, stack, message.typ);
                        }
                    }
                }
            });
        });
    }
}