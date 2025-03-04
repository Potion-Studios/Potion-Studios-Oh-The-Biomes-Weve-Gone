package net.potionstudios.biomeswevegone.world.level.block.plants.vegetation;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import org.jetbrains.annotations.NotNull;

public class AloeVeraBlock extends BushBlock implements BonemealableBlock {

    private static final MapCodec<AloeVeraBlock> CODEC = simpleCodec(AloeVeraBlock::new);

    public AloeVeraBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, RandomSource random) {
        if (random.nextInt(5) == 4)
            growBloomingAloeVera(level, pos);
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState state) {
        return true;
    }

    @Override
    protected @NotNull MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
        return state.is(BlockTags.SAND);
    }

    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader level, @NotNull BlockPos pos, @NotNull BlockState state) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(@NotNull Level level, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(@NotNull ServerLevel level, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState state) {
        growBloomingAloeVera(level, pos);
    }

    private void growBloomingAloeVera(ServerLevel level, BlockPos pos) {
        if (level.isEmptyBlock(pos.above())) {
            level.setBlock(pos, BWGBlocks.BLOOMING_ALOE_VERA.get().defaultBlockState().setValue(BloomingAloeVeraBlock.HALF, DoubleBlockHalf.LOWER), 3);
            level.setBlock(pos.above(), BWGBlocks.BLOOMING_ALOE_VERA.get().defaultBlockState().setValue(BloomingAloeVeraBlock.HALF, DoubleBlockHalf.UPPER), 3);
        }
    }
}
