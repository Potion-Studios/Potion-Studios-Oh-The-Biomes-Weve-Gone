package net.potionstudios.biomeswevegone.world.level.levelgen.customterrain;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.levelgen.biome.BWGBiomes;
import net.potionstudios.biomeswevegone.world.level.levelgen.util.BlendUtil;

import java.util.function.Function;

public class CragGardenExtension {

    public static void runCragGardenExtension(Function<BlockPos, Holder<Biome>> biomeGetter, ChunkAccess chunk, long worldSeed, NormalNoise.NoiseParameters noiseParameters, NormalNoise.NoiseParameters cliffSpacingParams) {
        ChunkPos pos = chunk.getPos();
        RandomSource randomSource = new XoroshiroRandomSource(worldSeed);
        RandomSource chunkRandom = new XoroshiroRandomSource(pos.toLong() + worldSeed);

        NormalNoise normalNoise = NormalNoise.create(randomSource, noiseParameters);
        NormalNoise cliffJumpNoise = NormalNoise.create(randomSource, cliffSpacingParams);

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        WeightedStateProvider topBlocksProvider = new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGBlocks.OVERGROWN_STONE.get().defaultBlockState(), 3).add(Blocks.MOSS_BLOCK.defaultBlockState(), 1));

        WeightedStateProvider stonesProvider = new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.STONE.defaultBlockState(), 1).add(Blocks.ANDESITE.defaultBlockState(), 1).add(BWGBlocks.ROCKY_STONE_SET.getBase().defaultBlockState(), 1));

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int worldX = pos.getBlockX(x);
                int worldZ = pos.getBlockZ(z);
                int landHeight = (chunk.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, worldX, worldZ) / 10) * 10;
                if (chunk.getMinY() >= landHeight) {
                    continue;
                }

                mutable.set(worldX, landHeight, worldZ);

                Holder<Biome> currentBiome = biomeGetter.apply(mutable);
                if (!currentBiome.is(BWGBiomes.CRAG_GARDENS)) {
                    continue;
                }


                double normalizedCliffNoise = cliffJumpNoise.getValue(worldX * 0.05, 0, worldZ * 0.05) + 1 * 0.5;
                int cliffJumpNoiseOffset = ((Mth.floor(normalizedCliffNoise * 10)));

                double blendRadius = BlendUtil.blendBiomeEdge(currentBiome, biomeGetter, mutable, 16, 1);
                int currentSurfaceHeight = (int) (getSurfaceHeight(normalNoise, worldX, worldZ, Math.max(10, cliffJumpNoiseOffset * 2)) * blendRadius);


                for (int y = -5; y <= currentSurfaceHeight; y++) {
                    mutable.set(worldX, y + landHeight, worldZ);
                    BlockState state = stonesProvider.getState(chunkRandom, mutable);

                    if (y == currentSurfaceHeight && chunk.getBlockState(mutable.move(Direction.UP)).getFluidState().isEmpty()) {
                        state = topBlocksProvider.getState(chunkRandom, mutable);
                        mutable.move(Direction.DOWN);
                    }


                    chunk.setBlockState(mutable, state, false);
                }
            }
        }
    }

    private static int getSurfaceHeight(NormalNoise normalNoise, int worldX, int worldZ, int spacing) {
        double normalizedNoise = (normalNoise.getValue(worldX * 0.005, 0, worldZ * 0.005) + 1) * 0.5;
        return (((Mth.floor(normalizedNoise * 50))) / spacing) * spacing;
    }
}
