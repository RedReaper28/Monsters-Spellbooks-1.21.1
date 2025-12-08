package net.redreaper.monsterspellbooks.item.curios.rings;

import io.redspace.ironsspellbooks.compat.Curios;
import io.redspace.ironsspellbooks.item.curios.SimpleDescriptiveCurio;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.acetheeldritchking.aces_spell_utils.utils.ASRarities;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

public class WitherWardCurio extends SimpleDescriptiveCurio {
    public WitherWardCurio() {
        super(ItemPropertiesHelper.equipment().stacksTo(1).rarity(ASRarities.FORBIDDEN_RARITY_PROXY.getValue()).fireResistant(), Curios.RING_SLOT);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        super.curioTick(slotContext, stack);
        slotContext.entity().removeEffect(MobEffects.WITHER);
    }
}
