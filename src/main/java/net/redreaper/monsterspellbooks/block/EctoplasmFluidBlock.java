package net.redreaper.monsterspellbooks.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.SoundAction;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.redreaper.monsterspellbooks.init.ModFluids;

import javax.annotation.Nullable;
import java.util.Optional;

public class EctoplasmFluidBlock extends LiquidBlock {
    public EctoplasmFluidBlock(FlowingFluid fluid, Properties properties) {
        super(fluid, properties);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity living) {
            if (level.isClientSide || living.isInPowderSnow || living.isDeadOrDying() || !entity.canFreeze()) return;

            living.setTicksFrozen(Math.min(entity.getTicksFrozen() + 3, 200));
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (!level.isClientSide()) return;
        if (random.nextInt(600) == 0) level.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.LAVA_AMBIENT, SoundSource.BLOCKS, 0.3f, 1.0f, false);
        BlockPos abovePos = pos.above();
            double x = (double) pos.getX() + random.nextDouble();
            double y = (double) pos.getY() + 1.0;
            double z = (double) pos.getZ() + random.nextDouble();
    }

    @Override
    public Optional<SoundEvent> getPickupSound() {
        return Optional.of(SoundEvents.BUCKET_FILL_LAVA);
    }
}