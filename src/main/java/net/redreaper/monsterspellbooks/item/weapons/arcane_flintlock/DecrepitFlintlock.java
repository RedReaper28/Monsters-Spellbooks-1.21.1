package net.redreaper.monsterspellbooks.item.weapons.arcane_flintlock;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.entity.spells.firebolt.FireboltProjectile;
import io.redspace.ironsspellbooks.util.MinecraftInstanceHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Predicate;


public class DecrepitFlintlock extends ProjectileWeaponItem {
    public int MAX_MANA_COST = 100;
    public int BASE_SPIRIT_POWER_SCALE = 10;

    public DecrepitFlintlock(Properties properties) {
        super(properties);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int use) {
        if (livingEntity instanceof Player player) {
            int i = this.getUseDuration(stack, livingEntity) - use;
            if (i < 0) {
                return;
            }

            float f = getPowerForTime(i);
            float damage = (f * (float) (player.getAttributeValue(AttributeRegistry.FIRE_SPELL_POWER) * (BASE_SPIRIT_POWER_SCALE + getFirePowerScale(stack, livingEntity))));
            FireboltProjectile firebolt = new FireboltProjectile(level, livingEntity);
            firebolt.setPos(player.position().add(0, 1.5, 0));
            firebolt.shoot(player.getLookAngle());
            firebolt.setDamage(damage);
            firebolt.tick();

            level.addFreshEntity(firebolt);

            int manaConsume = MAX_MANA_COST + getManaOnUse(stack, player);
            MagicData playerMana = MagicData.getPlayerMagicData(player);
            if (!player.isCreative()) {
                playerMana.addMana(-(f * manaConsume));
            }
            stack.hurtAndBreak(this.getDurabilityUse(stack), livingEntity, LivingEntity.getSlotForHand(livingEntity.getUsedItemHand()));

            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
            player.awardStat(Stats.ITEM_USED.get(this));
        }

    }

    public static int getFirePowerScale(ItemStack stack, LivingEntity livingEntity) {
        if (livingEntity.level() instanceof ServerLevel serverLevel) {
            int powerScalePoint =10;
            return Mth.floor(powerScalePoint);
        } else return 0;
    }

    public static int getManaOnUse(ItemStack stack, LivingEntity livingEntity) {
        if (livingEntity.level() instanceof ServerLevel serverLevel) {
            int manaUseAmount = 0;
            return Mth.floor(manaUseAmount);
        } else return 0;
    }

    @Override
    protected void shootProjectile(LivingEntity p_331372_, Projectile p_332000_, int p_330631_, float p_331251_, float p_331199_, float p_330857_, @javax.annotation.Nullable LivingEntity p_331572_) {
        p_332000_.shootFromRotation(p_331372_, p_331372_.getXRot(), p_331372_.getYRot() + p_330857_, 0.0F, p_331251_, p_331199_);
    }

    public static float getPowerForTime(int p_40662_) {
        float f = (float)p_40662_ / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_40678_) {
        return UseAnim.CROSSBOW;
    }

    public int getUseDuration(ItemStack stack, LivingEntity livingEntity) {
        return 7200;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player,   InteractionHand hand) {
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
    public @NotNull Predicate<ItemStack> getAllSupportedProjectiles() {
        return ARROW_ONLY;
    }

    public int getDefaultProjectileRange() {
        return 20;
    }

    public int getEnchantmentValue() {
        return 1;
    }

    //Client call for damage tooltip
    public static double getDisplayMaxDamage(ItemStack stack, LivingEntity livingEntity) {
        double baseMaxDamage = 10;
        if (livingEntity instanceof Player player) {
            double modifiedMaxDamage = baseMaxDamage * player.getAttributeValue(AttributeRegistry.FIRE_SPELL_POWER);
            if (!stack.isEmpty() && stack.has(DataComponents.ENCHANTMENTS)) {
            } return modifiedMaxDamage;
        }
        return baseMaxDamage;
    }

    public static double getDisplayManaCost(ItemStack stack, Entity entity) {
        double baseManaCost = 100;
        if (!stack.isEmpty() && stack.has(DataComponents.ENCHANTMENTS) && entity != null) {
        } return baseManaCost;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, @NotNull List<Component> components, TooltipFlag flag) {
        super.appendHoverText(stack, context, components, flag);
        components.add(Component.literal(" ").append(Component.translatable(this.getDescriptionId() + ".description")).withStyle(ChatFormatting.GOLD));
        components.add(Component.literal(" ").append(Component.translatable(this.getDescriptionId() + ".description.damage",
                Component.literal(Utils.stringTruncation(getDisplayMaxDamage(stack, MinecraftInstanceHelper.getPlayer()), 1)).withStyle(ChatFormatting.DARK_RED))
        ).withStyle(ChatFormatting.GOLD));
        components.add(Component.literal(" ").append(Component.translatable(this.getDescriptionId() + ".description.mana_cost",
                Component.literal(Utils.stringTruncation(getDisplayManaCost(stack, MinecraftInstanceHelper.getPlayer()), 1)).withStyle(ChatFormatting.DARK_RED))
        ).withStyle(ChatFormatting.GOLD));
    }
}
