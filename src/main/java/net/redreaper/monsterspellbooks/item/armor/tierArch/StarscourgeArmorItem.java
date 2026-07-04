package net.redreaper.monsterspellbooks.item.armor.tierArch;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.entity.armor.GenericCustomArmorRenderer;
import io.redspace.ironsspellbooks.item.armor.IDisableJacket;
import io.redspace.ironsspellbooks.item.armor.ImbuableChestplateArmorItem;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;
import net.redreaper.monsterspellbooks.entity.armor.StarscourgeArmorModel;
import net.redreaper.monsterspellbooks.init.ModExtendedArmorMaterials;
import net.redreaper.monsterspellbooks.init.ModItems;
import net.redreaper.monsterspellbooks.init.ModKeybinds;
import net.redreaper.monsterspellbooks.init.ModMobEffects;
import net.redreaper.monsterspellbooks.item.armor.custom.KeybindArmor;
import net.redreaper.monsterspellbooks.item.armor.custom.MessageArmorKey;
import net.redreaper.monsterspellbooks.particle.ModParticleHelper;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.List;

public class StarscourgeArmorItem extends ImbuableChestplateArmorItem implements IDisableJacket, KeybindArmor {
    public StarscourgeArmorItem(Type slot, Properties settings) {
        super(ModExtendedArmorMaterials.STARSCOURGE_ARMOR, slot, settings,
                new AttributeContainer(AttributeRegistry.MAX_MANA, 125, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(AttributeRegistry.ENDER_SPELL_POWER, 0.20, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (entity instanceof Player player && !level.isClientSide() && isWearingFullSet(player)) {
        }

        if (entity instanceof Player player) {
            if (level.isClientSide) {
                if (this.type == Type.CHESTPLATE && player.getItemBySlot(EquipmentSlot.CHEST) == stack && ModKeybinds.ABILITY_ARMOR.consumeClick()) {
                    PacketDistributor.sendToServer(new MessageArmorKey(EquipmentSlot.CHEST.ordinal(), player.getId(), 5), new CustomPacketPayload[0]);
                    this.onKeyPacket(player, stack, 5);
                }
                return;
            }
        }
    }

    private boolean isWearingFullSet(Player player) {
        return player.getItemBySlot(Type.HELMET.getSlot()).getItem() instanceof StarscourgeArmorItem &&
                player.getItemBySlot(Type.CHESTPLATE.getSlot()).getItem() instanceof StarscourgeArmorItem &&
                player.getItemBySlot(Type.LEGGINGS.getSlot()).getItem() instanceof StarscourgeArmorItem &&
                player.getItemBySlot(Type.BOOTS.getSlot()).getItem() instanceof StarscourgeArmorItem;
    }

    public void onKeyPacket(Player player, ItemStack itemStack, int Type) {
        if (player != null) {
            if (Type == 5 && isWearingFullSet(player) && !player.getCooldowns().isOnCooldown((Item) ModItems.STARSCOURGE_CHESTPLATE.get())) {
                player.addEffect(new MobEffectInstance(ModMobEffects.OBSIDIAN_SKIN, 15*20, 0, true, true, true));
                player.addEffect(new MobEffectInstance(ModMobEffects.HEAL_CUT, 15*20, 0, true, true, true));
                player.getCooldowns().addCooldown(ModItems.STARSCOURGE_CHESTPLATE.get(), 40*20);

                if (player.level() instanceof ServerLevel serverLevel) {
                    double x = player.getX();
                    double y = player.getY() + player.getEyeHeight() * 0.5D;
                    double z = player.getZ();

                    int count = 40;
                    double speed = 0.4;

                    for (int i = 0; i < count; i++) {
                        double angle = (2 * Math.PI * i) / count;

                        double dx = Math.cos(angle);
                        double dz = Math.sin(angle);

                        serverLevel.sendParticles(ModParticleHelper.SPACE_SHARD, x, y, z, 1, dx * speed, 0.05, dz * speed, 0.0);
                    }

                    serverLevel.sendParticles(ModParticleHelper.SPACE_SHARD, x, y, z, 1, 0.0, 0.0, 0.0, 0.0);

                    try {
                        serverLevel.playSound(null, x, y, z, SoundRegistry.FORCE_IMPACT, SoundSource.PLAYERS, 1.5F, 1.0F);
                    }
                    catch (Exception ignored) {
                    }
                }
            }
        }
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, TooltipContext context, @NotNull List<Component> lines, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemStack, context, lines, flag);
        if (this.type == Type.CHESTPLATE) {
            lines.add(Component.translatable("tooltip.monsterspellbooks.ability_cooldown", ModKeybinds.ABILITY_ARMOR.getTranslatedKeyMessage(),40));
            lines.add(Component.translatable("tooltip.monsterspellbooks.star_set").withStyle(Style.EMPTY.withColor(11893759)));
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new GenericCustomArmorRenderer<>(new StarscourgeArmorModel());
    }
}