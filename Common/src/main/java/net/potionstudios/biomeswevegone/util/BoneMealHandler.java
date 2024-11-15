package net.potionstudios.biomeswevegone.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;
import java.util.Optional;

public final class BoneMealHandler {
    public static boolean grassBoneMealHandler(ServerLevel level, BlockPos blockPos, Block grass, ResourceKey<PlacedFeature> placedFeatureResourceKey, boolean randomizeFlower) {
        BlockState blockState = grass.defaultBlockState();
        Optional<Holder.Reference<PlacedFeature>> optional = level.registryAccess()
                .registryOrThrow(Registries.PLACED_FEATURE)
                .getHolder(placedFeatureResourceKey);

        label49:
        for(int i = 0; i < 128; ++i) {
            BlockPos blockPos2 = blockPos;
            RandomSource random = level.getRandom();
            for (int j = 0; j < i / 16; ++j) {
                blockPos2 = blockPos2.offset(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                if (!level.getBlockState(blockPos2.below()).is(Blocks.GRASS_BLOCK) || level.getBlockState(blockPos2).isCollisionShapeFullBlock(level, blockPos2))
                    continue label49;
            }

            BlockState blockState2 = level.getBlockState(blockPos2);
            if (blockState2.is(blockState.getBlock()) && random.nextInt(10) == 0)
                ((BonemealableBlock) blockState.getBlock()).performBonemeal(level, random, blockPos2, blockState2);

            if (blockState2.isAir()) {
                Holder<PlacedFeature> holder;
                if (random.nextInt(8) == 0) {
                    List<ConfiguredFeature<?, ?>> list = level.getBiome(blockPos2).value().getGenerationSettings().getFlowerFeatures();
                    if (list.isEmpty()) continue;

                    holder = randomizeFlower ? getRandElement(list, random) : ((RandomPatchConfiguration) list.getFirst().config()).feature();
                } else {
                    if (optional.isEmpty()) continue;
                    holder = optional.get();
                }

                holder.value().place(level, level.getChunkSource().getGenerator(), random, blockPos2);
            }
        }
        return true;
    }

    private static Holder<PlacedFeature> getRandElement(List<ConfiguredFeature<?, ?>> list, RandomSource random) {
        return ((RandomPatchConfiguration) list.get(random.nextInt(list.size())).config()).feature();
    }
}
