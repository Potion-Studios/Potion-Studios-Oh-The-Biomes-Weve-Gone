package net.potionstudios.biomeswevegone.world.level.block.plants.tree.leaves;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.potionstudios.biomeswevegone.world.level.block.plants.tree.fruit.BWGFruitBlock;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class BWGFruitLeavesBlock extends LeavesBlock implements BonemealableBlock {
    private final Supplier<BWGFruitBlock> fruitBlock;
    private final float tickSpawnChance;

    public BWGFruitLeavesBlock(Properties properties, Supplier<BWGFruitBlock> fruitBlock, float tickSpawnChance) {
        super(properties);
        this.fruitBlock = fruitBlock;
        this.tickSpawnChance = tickSpawnChance;
    }

    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        super.randomTick(state, level, pos, random);
        BlockPos fruitPos = pos.below();
        if (this.decaying(state)) {
            BlockState below = level.getBlockState(fruitPos);
            if (below.is(fruitBlock.get()))
                level.destroyBlock(fruitPos, below.getValue(BWGFruitBlock.AGE) == BWGFruitBlock.MAX_AGE || random.nextBoolean());
        } else if (level.getBlockState(fruitPos).isAir() && random.nextFloat() <= this.tickSpawnChance)
            placeFruit(level, fruitPos);
    }

    private void placeFruit(@NotNull Level level, @NotNull BlockPos pos) {
        level.setBlock(pos, fruitBlock.get().defaultBlockState().setValue(BWGFruitBlock.AGE, 0), 2);
    }

    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader level, @NotNull BlockPos pos, BlockState state) {
        return !state.getValue(PERSISTENT);
    }

    @Override
    public boolean isBonemealSuccess(@NotNull Level level, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState state) {
        BlockState below = level.getBlockState(pos.below());
            if (below.isAir() || below.is(fruitBlock.get()) && below.getValue(BWGFruitBlock.AGE) < 3)
                return random.nextBoolean();
        return false;
    }

    @Override
    public void performBonemeal(@NotNull ServerLevel level, @NotNull RandomSource random, BlockPos pos, @NotNull BlockState state) {
        BlockState below = level.getBlockState(pos.below());
        if (below.isAir()) placeFruit(level, pos.below());
        else if (below.is(fruitBlock.get())) level.setBlock(pos.below(), below.cycle(BWGFruitBlock.AGE), 2);
    }
}
