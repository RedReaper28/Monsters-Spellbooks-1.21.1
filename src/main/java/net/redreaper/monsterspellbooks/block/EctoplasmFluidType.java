package net.redreaper.monsterspellbooks.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.SoundAction;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;

import javax.annotation.Nullable;

public class EctoplasmFluidType extends FluidType {
    public EctoplasmFluidType(Properties properties) {
        super(properties);
    }

    @Override
    public void onVaporize(@Nullable Player player, Level level, BlockPos pos, FluidStack stack) {
        level.playSound(player, pos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (level.random.nextFloat() - level.random.nextFloat()) * 0.8F);
    }

    @Override
    public @Nullable SoundEvent getSound(SoundAction action) {
        if (action == SoundActions.FLUID_VAPORIZE) return SoundEvents.LAVA_EXTINGUISH;
        else if (action == SoundActions.BUCKET_EMPTY) return SoundEvents.BUCKET_EMPTY_LAVA;
        else if (action == SoundActions.BUCKET_FILL) return SoundEvents.BUCKET_FILL_LAVA;
        return null;
    }
}