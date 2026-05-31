package net.redreaper.monsterspellbooks.item.armor.custom;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;

public record MessageArmorKey (int equipmentSlot, int playerId, int typ) implements CustomPacketPayload {
    public static final Type<MessageArmorKey> TYPE = new Type(ResourceLocation.fromNamespaceAndPath(MonstersSpellbooks.MOD_ID, "armor_key"));
    public static final StreamCodec<RegistryFriendlyByteBuf, MessageArmorKey> STREAM_CODEC = CustomPacketPayload.codec(MessageArmorKey::write, MessageArmorKey::new);

    public MessageArmorKey(FriendlyByteBuf buf) {
        this(buf.readInt(), buf.readInt(), buf.readInt());
    }

    public MessageArmorKey(int equipmentSlot, int playerId, int typ) {
        this.equipmentSlot = equipmentSlot;
        this.playerId = playerId;
        this.typ = typ;
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeInt(this.equipmentSlot());
        buf.writeInt(this.playerId());
        buf.writeInt(this.typ());
    }

    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(MessageArmorKey message, IPayloadContext context) {
        if (context.flow().isServerbound()) {
            context.enqueueWork(() -> {
                Player player = context.player();
                EquipmentSlot equipmentSlot1 = EquipmentSlot.values()[Mth.clamp(message.equipmentSlot, 0, EquipmentSlot.values().length - 1)];
                ItemStack stack = player.getItemBySlot(equipmentSlot1);
                Item patt0$temp = stack.getItem();
                if (patt0$temp instanceof KeybindArmor armor) {
                    armor.onKeyPacket(player, stack, message.typ);
                }
            });
        }
    }

    public int equipmentSlot() {
        return this.equipmentSlot;
    }

    public int playerId() {
        return this.playerId;
    }

    public int typ() {
        return this.typ;
    }
}
