package net.potionstudios.biomeswevegone.world.level.block.plants.vegetation.cattail;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class CattailSproutBlock extends Block implements SimpleWaterloggedBlock, BonemealableBlock {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private final Supplier<? extends CattailPlantBlock> cattailBlock;

    public CattailSproutBlock(BlockBehaviour.Properties properties, Supplier<? extends CattailPlantBlock> cattailBlock) {
        super(properties);
        this.cattailBlock = cattailBlock;
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
    }

    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, RandomSource random) {
        if (random.nextInt(5) == 4)
            if (level.isEmptyBlock(pos.above()))
                growCattail(level, pos);
    }

    @Override
    public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        if (context.getLevel().getFluidState(context.getClickedPos()).is(FluidTags.WATER))
            return super.getStateForPlacement(context).setValue(WATERLOGGED, true);
        return this.defaultBlockState();
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return Block.box(4.0, 0.0, 4.0, 12.0, 12.0, 12.0);
    }

    @Override
    protected @NotNull BlockState updateShape(@NotNull BlockState state, @NotNull LevelReader level, @NotNull ScheduledTickAccess scheduledTickAccess, @NotNull BlockPos pos, @NotNull Direction direction, @NotNull BlockPos neighborPos, @NotNull BlockState neighborState, @NotNull RandomSource random) {
        if (state.getValue(WATERLOGGED))
            scheduledTickAccess.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));

        if (canSurvive(state, level, pos)) return state;
        else return Blocks.AIR.defaultBlockState();
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, LevelReader level, BlockPos pos) {
        BlockState below = level.getBlockState(pos.below());
        if (below.is(BlockTags.DIRT) || below.is(BlockTags.SAND)) {
            if (level.isEmptyBlock(pos.above()) && state.getFluidState().is(FluidTags.WATER))
                return true;

            for (Direction direction : Direction.Plane.HORIZONTAL) {
                BlockState neighbor = level.getBlockState(pos.below().relative(direction));
                if (neighbor.getFluidState().is(FluidTags.WATER) || neighbor.is(Blocks.FROSTED_ICE))
                    return true;
            }
        }
        return false;
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
    public void performBonemeal(ServerLevel level, @NotNull RandomSource random, BlockPos pos, @NotNull BlockState state) {
        if (level.isEmptyBlock(pos.above()))
            growCattail(level, pos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }

    private void growCattail(ServerLevel level, BlockPos pos) {
        if (level.getFluidState(pos).is(FluidTags.WATER))
            level.setBlock(pos, cattailBlock.get().defaultBlockState().setValue(CattailPlantBlock.HALF, DoubleBlockHalf.LOWER).setValue(CattailPlantBlock.WATERLOGGED, true), 3);
        else
            level.setBlock(pos, cattailBlock.get().defaultBlockState().setValue(CattailPlantBlock.HALF, DoubleBlockHalf.LOWER).setValue(CattailPlantBlock.WATERLOGGED, false), 3);
        level.setBlock(pos.above(), cattailBlock.get().defaultBlockState().setValue(CattailPlantBlock.HALF, DoubleBlockHalf.UPPER).setValue(CattailPlantBlock.WATERLOGGED, false), 3);
    }
}
