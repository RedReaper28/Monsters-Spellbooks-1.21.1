package net.redreaper.monsterspellbooks.effect;

import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.neoforge.common.NeoForgeMod;

public class FlightMobEffect extends MagicMobEffect {
    public FlightMobEffect() {super(MobEffectCategory.HARMFUL, 8028868);
        this.addAttributeModifier(NeoForgeMod.CREATIVE_FLIGHT, ResourceLocation.fromNamespaceAndPath("monsterspellbooks", "flight"), (double)1.0F, AttributeModifier.Operation.ADD_VALUE);
    }
}
