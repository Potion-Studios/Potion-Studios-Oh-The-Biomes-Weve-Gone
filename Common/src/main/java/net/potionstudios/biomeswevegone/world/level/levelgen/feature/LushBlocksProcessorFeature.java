package net.potionstudios.biomeswevegone.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.synth.ImprovedNoise;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;

public class LushBlocksProcessorFeature extends Feature<NoneFeatureConfiguration> {

    public static final Direction[] DIRECTIONS = new Direction[]{Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};

    public LushBlocksProcessorFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        ChunkAccess chunk = context.level().getChunk(context.origin());
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        ChunkPos pos = chunk.getPos();
        int minWorldX = pos.getMinBlockX();
        int minWorldZ = pos.getMinBlockZ();

        WorldGenLevel level = context.level();


        ImprovedNoise noise = new ImprovedNoise(new XoroshiroRandomSource(context.level().getSeed()));
        WeightedStateProvider lushStatesProvider = new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.MOSSY_COBBLESTONE.defaultBlockState(), 1).add(Blocks.MOSS_BLOCK.defaultBlockState(), 1).add(BWGBlocks.MOSSY_STONE_SET.getBase().defaultBlockState(), 1));


        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int height = chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, x, z);

                for (int y = height; y > chunk.getMinY(); y--) {
                    mutableBlockPos.set(minWorldX + x, y, minWorldZ + z);
                    BlockState blockState = chunk.getBlockState(mutableBlockPos);

                    if (blockState.is(BlockTags.BASE_STONE_OVERWORLD)) {
                        double sampleNoise = (noise.noise(mutableBlockPos.getX() * 0.05, mutableBlockPos.getY() * 0.05, mutableBlockPos.getZ() * 0.05) + 1) * 0.5;
                        if (sampleNoise < 0.5) {
                            for (Direction direction : DIRECTIONS) {
                                mutableBlockPos.set(minWorldX + x, y, minWorldZ + z).move(direction);
                                int offsetHeight = level.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, mutableBlockPos.getX(), mutableBlockPos.getZ());
                                if (level.getBlockState(mutableBlockPos).isAir() && mutableBlockPos.getY() > offsetHeight) {
                                    mutableBlockPos.set(minWorldX + x, y, minWorldZ + z);
                                    chunk.setBlockState(mutableBlockPos, lushStatesProvider.getState(context.random(), mutableBlockPos), false);
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
