package net.redreaper.monsterspellbooks.item.weapons.crescent_blood_rose;

import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.item.UniqueItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.acetheeldritchking.aces_spell_utils.utils.ASRarities;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.redreaper.monsterspellbooks.init.ModExtendedWeaponTiers;
import net.redreaper.monsterspellbooks.init.ModSpellRegistry;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class CrescentBloodRoseItem extends MagicSwordItem implements GeoItem, UniqueItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final RawAnimation IDLE_ANIMATION = RawAnimation.begin().thenLoop("idle");
    private final AnimationController<CrescentBloodRoseItem> animationController = new AnimationController<>(this, "controller", 0, this::predicate);

    public CrescentBloodRoseItem() {
        super(ModExtendedWeaponTiers.SANGUINITE,
                ItemPropertiesHelper.equipment(1).fireResistant().rarity(ASRarities.ACCURSED_RARITY_PROXY.getValue()).attributes(ExtendedSwordItem.createAttributes(ModExtendedWeaponTiers.SANGUINITE)),
                SpellDataRegistryHolder.of(
                        new SpellDataRegistryHolder(ModSpellRegistry.BLOOD_PIERCE, 4)
                )
        );
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
    }

    private PlayState predicate(AnimationState<CrescentBloodRoseItem> event) {
        event.getController().setAnimation(IDLE_ANIMATION);
        return PlayState.CONTINUE;
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }


    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private CrescentBloodRoseRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if(this.renderer == null) {
                    renderer = new CrescentBloodRoseRenderer();
                }

                return this.renderer;
            }
        });
    }
}
