package net.potionstudios.biomeswevegone.world.level.block.plants.tree.leaves;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
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
    public boolean isRandomlyTicking(@NotNull BlockState state) {
        return !state.getValue(PERSISTENT);
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
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if (player.isCreative() && !level.isClientSide())
            if (player.getItemInHand(hand).is(fruitBlock.get().getFruit()) && level.getBlockState(pos.below()).isAir()) {
                placeFruit(level, pos.below());
                return InteractionResult.SUCCESS;
            }
        return super.use(state, level, pos, player, hand, hit);
    }

    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader level, @NotNull BlockPos pos, @NotNull BlockState state, boolean isClient) {
        return !state.getValue(PERSISTENT) && level.getBlockState(pos.below()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(@NotNull Level level, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState state) {
        return random.nextBoolean();
    }

    @Override
    public void performBonemeal(@NotNull ServerLevel level, @NotNull RandomSource random, BlockPos pos, @NotNull BlockState state) {
        placeFruit(level, pos.below());
    }
}
