package net.potionstudios.biomeswevegone.world.level.block.plants.tree.leaves;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class BWGChangingLeavesBlock extends BWGLeavesBlock implements BonemealableBlock {

    private final Supplier<LeavesBlock> next;
    private final float chance;

    public BWGChangingLeavesBlock(Properties properties, Supplier<LeavesBlock> next, float chance, Supplier<SimpleParticleType> particleTypes) {
        super(properties, particleTypes);
        this.next = next;
        this.chance = chance;
    }

    public BWGChangingLeavesBlock(Properties properties, Supplier<LeavesBlock> next, float chance) {
        super(properties);
        this.next = next;
        this.chance = chance;
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return !blockState.getValue(PERSISTENT);
    }

    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel serverLevel, @NotNull BlockPos pos, @NotNull RandomSource randomSource) {
        super.randomTick(state, serverLevel, pos, randomSource);

        if (randomSource.nextFloat() < chance)
            placeNext(state, serverLevel, pos);
    }

    private void placeNext(BlockState state, Level level, BlockPos pos) {
        level.setBlock(pos, next.get().defaultBlockState().setValue(DISTANCE, state.getValue(DISTANCE)).setValue(PERSISTENT, state.getValue(PERSISTENT)), 2);
    }

    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader blockGetter, @NotNull BlockPos blockPos, @NotNull BlockState blockState, boolean b) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(@NotNull Level level, RandomSource randomSource, @NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return randomSource.nextBoolean();
    }

    @Override
    public void performBonemeal(@NotNull ServerLevel level, @NotNull RandomSource randomSource, @NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        placeNext(blockState, level, blockPos);
    }
}
