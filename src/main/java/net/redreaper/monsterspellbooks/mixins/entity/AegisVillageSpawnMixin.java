package net.redreaper.monsterspellbooks.mixins.entity;

import com.mojang.datafixers.util.Either;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.redreaper.monsterspellbooks.entity.living.AegisEntity;
import net.redreaper.monsterspellbooks.init.ModEntities;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SinglePoolElement.class)
public abstract class AegisVillageSpawnMixin {

    @Shadow
    @Final
    protected Either<ResourceLocation, StructureTemplate> template;

    @Inject(at = @At(value = "RETURN"), method = "place", cancellable = true)
    public void place(StructureTemplateManager structureTemplateManager, WorldGenLevel level, StructureManager structureManager, ChunkGenerator generator, BlockPos offset, BlockPos pos, Rotation rotation, BoundingBox box, RandomSource random, LiquidSettings liquidSettings, boolean keepJigsaws, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) return;

        this.template.left().ifPresent(resourceLocation -> {

            String path = resourceLocation.getPath();

            if (!path.startsWith("village/")) return;

            if (!path.contains("town_centers") && !path.contains("starts")) return;

            int fixedGuardCount = 4;

            for (int i = 0; i < fixedGuardCount; i++) {
                AegisEntity guard = ModEntities.AEGIS.get().create(level.getLevel());
                if (guard == null) return;
                guard.setPersistenceRequired();

                guard.moveTo(
                        offset.getX() + 0.5,
                        offset.getY()+10,
                        offset.getZ() + 0.5,
                        random.nextFloat() * 360F,
                        0
                );

                guard.finalizeSpawn(
                        level,
                        level.getCurrentDifficultyAt(offset),
                        MobSpawnType.STRUCTURE,
                        null
                );

                guard.setPersistenceRequired();

                level.addFreshEntityWithPassengers(guard);
            }
        });
    }
}
