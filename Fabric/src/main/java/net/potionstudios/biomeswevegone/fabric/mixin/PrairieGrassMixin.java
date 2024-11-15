package net.potionstudios.biomeswevegone.fabric.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.levelgen.biome.BWGBiomes;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.placed.BWGOverworldVegationPlacedFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Optional;

@Mixin(GrassBlock.class)
public abstract class PrairieGrassMixin extends SpreadingSnowyDirtBlock implements BonemealableBlock {

    protected PrairieGrassMixin(Properties properties) {
        super(properties);
    }

    /**
     * @reason Allows for Bonemealing of Prairie Grass to spawn Prairie Grass and other features.
     * @see GrassBlock#performBonemeal(ServerLevel, RandomSource, BlockPos, BlockState)
     * @author Joseph T. McQuigg
     */
    @Inject(method = "performBonemeal", at = @At("HEAD"), cancellable = true)
    private void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state, CallbackInfo ci) {
        if (level.getBiome(pos).is(BWGBiomes.PRAIRIE)) {
            BlockPos blockPos = pos.above();
            BlockState blockState = BWGBlocks.PRAIRIE_GRASS.get().defaultBlockState();
            Optional<Holder.Reference<PlacedFeature>> optional = level.registryAccess()
                    .registryOrThrow(Registries.PLACED_FEATURE)
                    .getHolder(BWGOverworldVegationPlacedFeatures.PRAIRIE_GRASS_BONEMEAL);

            label49:
            for (int i = 0; i < 128; i++) {
                BlockPos blockPos2 = blockPos;

                for (int j = 0; j < i / 16; j++) {
                    blockPos2 = blockPos2.offset(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                    if (!level.getBlockState(blockPos2.below()).is(this) || level.getBlockState(blockPos2).isCollisionShapeFullBlock(level, blockPos2)) {
                        continue label49;
                    }
                }

                BlockState blockState2 = level.getBlockState(blockPos2);
                if (blockState2.is(blockState.getBlock()) && random.nextInt(10) == 0) {
                    ((BonemealableBlock)blockState.getBlock()).performBonemeal(level, random, blockPos2, blockState2);
                }

                if (blockState2.isAir()) {
                    Holder<PlacedFeature> holder;
                    if (random.nextInt(8) == 0) {
                        List<ConfiguredFeature<?, ?>> list = level.getBiome(blockPos2).value().getGenerationSettings().getFlowerFeatures();
                        if (list.isEmpty()) continue;

                        holder = ((RandomPatchConfiguration) list.getFirst().config()).feature();
                    } else {
                        if (optional.isEmpty()) continue;
                        holder = optional.get();
                    }

                    holder.value().place(level, level.getChunkSource().getGenerator(), random, blockPos2);
                }
            }
            ci.cancel();
        }
    }
}
