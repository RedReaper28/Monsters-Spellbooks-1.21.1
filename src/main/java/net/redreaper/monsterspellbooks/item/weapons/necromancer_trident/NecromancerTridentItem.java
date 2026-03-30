package net.redreaper.monsterspellbooks.item.weapons.necromancer_trident;

import io.redspace.ironsspellbooks.api.item.curios.AffinityData;
import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.item.UniqueItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import io.redspace.ironsspellbooks.util.MinecraftInstanceHelper;
import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.acetheeldritchking.aces_spell_utils.utils.ASRarities;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.redreaper.monsterspellbooks.entity.spells.water_trident.WaterTridentProjectile;
import net.redreaper.monsterspellbooks.init.ModExtendedWeaponTiers;
import net.redreaper.monsterspellbooks.utils.ModRarities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class NecromancerTridentItem extends MagicSwordItem implements UniqueItem {
    public int MAX_MANA_COST = 100;
    public int BASE_SPIRIT_POWER_SCALE = 10;

    public NecromancerTridentItem() {
        super(ModExtendedWeaponTiers.NECROMANCER_PRISMARINE,
                ItemPropertiesHelper.equipment(1).fireResistant().rarity(ModRarities.PRISMARINE_RARITY_PROXY.getValue())
                        .attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.NECROMANCER_PRISMARINE)),
                SpellDataRegistryHolder.of(
                        new SpellDataRegistryHolder(SpellRegistry.VOLT_STRIKE_SPELL, 8)
                )
        );
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.SPEAR;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return super.supportsEnchantment(stack, enchantment) ||enchantment.is(Enchantments.CHANNELING);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int use) {
        if (livingEntity instanceof Player player) {
            int i = this.getUseDuration(stack, livingEntity) - use;
            if (i < 0) {
                return;
            }

            float f = getPowerForTime(i);
            float damage = (f * (float) (player.getAttributeValue(ASAttributeRegistry.HYDRO_MAGIC_POWER) * (BASE_SPIRIT_POWER_SCALE + getHydroPowerScale(stack, livingEntity))));
            WaterTridentProjectile spiritArrow = new WaterTridentProjectile(level, livingEntity);
            spiritArrow.setPos(player.position().add(0, 1.5, 0));
            spiritArrow.shoot(player.getLookAngle());
            spiritArrow.setDamage(damage);
            spiritArrow.tick();

            level.addFreshEntity(spiritArrow);

            int manaConsume = MAX_MANA_COST + getManaOnUse(stack, player);
            MagicData playerMana = MagicData.getPlayerMagicData(player);
            if (!player.isCreative()) {
                playerMana.addMana(-(f * manaConsume));
            }
            stack.hurtAndBreak(this.getDurabilityUse(stack), livingEntity, LivingEntity.getSlotForHand(livingEntity.getUsedItemHand()));

            level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
            player.awardStat(Stats.ITEM_USED.get(this));
        }
    }

    public static int getHydroPowerScale(ItemStack stack, LivingEntity livingEntity) {
        if (livingEntity.level() instanceof ServerLevel serverLevel) {
            int powerScalePoint =10;
            return Mth.floor(powerScalePoint);
        } else return 0;
    }

    public static int getManaOnUse(ItemStack stack, LivingEntity livingEntity) {
        if (livingEntity.level() instanceof ServerLevel serverLevel) {
            int manaUseAmount = 50;
            return Mth.floor(manaUseAmount);
        } else return 0;
    }

    public static float getPowerForTime(int p_40662_) {
        float f = (float)p_40662_ / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    private static int getDurabilityUse(ItemStack stack) {
        return 10;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        MagicData magicData = MagicData.getPlayerMagicData(player);
        if (magicData.getMana() < (MAX_MANA_COST + getManaOnUse(itemstack, player)) && !player.isCreative()) {
            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.connection.send(new ClientboundSetActionBarTextPacket(Component.translatable("ui.monsterspellbooks.magic_weapon_not_enough_mana").withStyle(ChatFormatting.RED)));
            }
            return InteractionResultHolder.fail(itemstack);
        } else {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(itemstack);
        }
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, TooltipContext context, @NotNull List<Component> lines, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemStack, context, lines, flag);
        var affinityData = AffinityData.getAffinityData(itemStack);
        lines.add(Component.translatable("tooltip.monsterspellbooks.necromancer_trident").withStyle(new ChatFormatting[]{ChatFormatting.DARK_BLUE}));
    }
}