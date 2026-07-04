package net.redreaper.monsterspellbooks.item.armor.tierArch;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.entity.armor.GenericCustomArmorRenderer;
import io.redspace.ironsspellbooks.item.armor.IDisableJacket;
import io.redspace.ironsspellbooks.item.armor.ImbuableChestplateArmorItem;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;
import net.redreaper.monsterspellbooks.entity.armor.SanguiniteHeroArmorModel;
import net.redreaper.monsterspellbooks.init.ModExtendedArmorMaterials;
import net.redreaper.monsterspellbooks.init.ModItems;
import net.redreaper.monsterspellbooks.init.ModKeybinds;
import net.redreaper.monsterspellbooks.init.ModMobEffects;
import net.redreaper.monsterspellbooks.item.armor.custom.KeybindArmor;
import net.redreaper.monsterspellbooks.item.armor.custom.MessageArmorKey;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.List;

public class SanguiniteHeroArmorItem extends ImbuableChestplateArmorItem implements IDisableJacket, KeybindArmor {
    public SanguiniteHeroArmorItem(Type slot, Properties settings) {
        super(ModExtendedArmorMaterials.SANGUINITE_ARMOR, slot, settings,
                new AttributeContainer(AttributeRegistry.MAX_MANA, 125, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(Attributes.ATTACK_SPEED, 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.BLOOD_SPELL_POWER, 0.20, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (entity instanceof Player player && !level.isClientSide() && isWearingFullSet(player)) {
            evaluateArmorEffects(player);
            if (entity.isCrouching()){
                player.addEffect(new MobEffectInstance(MobEffectRegistry.TRUE_INVISIBILITY, 1*20, 0, false, false, false));
            }
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

    private void evaluateArmorEffects(Player player) {
        if (!player.hasEffect(MobEffects.MOVEMENT_SPEED)) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 5*20, 1, false, false, false));
        }
    }

    private boolean isWearingFullSet(Player player) {
        return player.getItemBySlot(Type.HELMET.getSlot()).getItem() instanceof SanguiniteHeroArmorItem &&
                player.getItemBySlot(Type.CHESTPLATE.getSlot()).getItem() instanceof SanguiniteHeroArmorItem &&
                player.getItemBySlot(Type.LEGGINGS.getSlot()).getItem() instanceof SanguiniteHeroArmorItem &&
                player.getItemBySlot(Type.BOOTS.getSlot()).getItem() instanceof SanguiniteHeroArmorItem;
    }

    public void onKeyPacket(Player player, ItemStack itemStack, int Type) {
        if (player != null) {
            if (Type == 5 && isWearingFullSet(player) && !player.getCooldowns().isOnCooldown((Item) ModItems.SANGUINITE_HERO_CHESTPLATE.get())) {
                player.addEffect(new MobEffectInstance(ModMobEffects.BLOODLUST, 5*20, 0, true, true, true));
                player.getCooldowns().addCooldown(ModItems.SANGUINITE_HERO_CHESTPLATE.get(), 20*20);

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

                        serverLevel.sendParticles(ParticleHelper.BLOOD, x, y, z, 1, dx * speed, 0.05, dz * speed, 0.0);
                    }

                    serverLevel.sendParticles(ParticleHelper.BLOOD, x, y, z, 1, 0.0, 0.0, 0.0, 0.0);

                    try {
                        serverLevel.playSound(null, x, y, z, SoundRegistry.HEARTSTOP_CAST, SoundSource.PLAYERS, 1.5F, 1.0F);
                    }
                    catch (Exception ignored) {
                    }
                }
            }
        }
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @NotNull TooltipContext context, @NotNull List<Component> lines, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemStack, context, lines, flag);
        if (this.type == Type.CHESTPLATE) {
            lines.add(Component.translatable("tooltip.monsterspellbooks.on_full_set"));
            lines.add(Component.translatable("tooltip.monsterspellbooks.sanguinite_set_passive").withStyle(Style.EMPTY.withColor(16728254)));
            lines.add(Component.translatable("tooltip.monsterspellbooks.ability_cooldown", ModKeybinds.ABILITY_ARMOR.getTranslatedKeyMessage(),20));
            lines.add(Component.translatable("tooltip.monsterspellbooks.sanguinite_set").withStyle(Style.EMPTY.withColor(16728254)));
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new GenericCustomArmorRenderer<>(new SanguiniteHeroArmorModel());
    }
}