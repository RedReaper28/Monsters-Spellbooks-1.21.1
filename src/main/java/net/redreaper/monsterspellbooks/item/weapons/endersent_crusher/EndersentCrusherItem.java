package net.redreaper.monsterspellbooks.item.weapons.endersent_crusher;

import io.redspace.ironsspellbooks.api.item.curios.AffinityData;
import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.acetheeldritchking.aces_spell_utils.utils.ASRarities;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.redreaper.monsterspellbooks.init.ModExtendedWeaponTiers;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import net.redreaper.monsterspellbooks.item.weapons.magic_mace.MagicMaceItem;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class EndersentCrusherItem extends MagicMaceItem implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final RawAnimation IDLE_ANIMATION = RawAnimation.begin().thenLoop("idle");
    private final AnimationController<EndersentCrusherItem> animationController = new AnimationController(this, "controller", 0, this::predicate);

    public EndersentCrusherItem() {
        super(ModExtendedWeaponTiers.HEAVY_VOID_OBSIDIAN,
                ItemPropertiesHelper.equipment(1).fireResistant().rarity(ASRarities.COSMIC_RARITY_PROXY.getValue()).attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.HEAVY_VOID_OBSIDIAN)),
                SpellDataRegistryHolder.of(
                        new SpellDataRegistryHolder(ModSpellRegistry.ENDERSENT_SMASH, 3)
                )
        );
    }

    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack == null) {
            return;
        }

        super.initializeSpellContainer(itemStack);
        AffinityData.setAffinityData(itemStack, ModSpellRegistry.ENDERSENT_SMASH.get());
    }

    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.level().isClientSide) {
            attacker.level().playSound((Player)null, target.getX(), target.getY(), target.getZ(), SoundEvents.ANVIL_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F);
        }

        return super.hurtEnemy(stack, target, attacker);
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
    }

    private PlayState predicate(AnimationState<EndersentCrusherItem> event) {
        event.getController().setAnimation(IDLE_ANIMATION);
        return PlayState.CONTINUE;
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }


    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private EndersentCrusherRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if(this.renderer == null) {
                    renderer = new EndersentCrusherRenderer();
                }

                return this.renderer;
            }
        }
        );
    }
}

