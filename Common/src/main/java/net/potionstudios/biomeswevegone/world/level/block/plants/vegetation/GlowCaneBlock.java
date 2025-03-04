package net.potionstudios.biomeswevegone.world.level.block.plants.vegetation;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.redstone.Orientation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class GlowCaneBlock extends SugarCaneBlock implements SimpleWaterloggedBlock {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private final Supplier<Supplier<Item>> shoot;

    public GlowCaneBlock(Properties properties, Supplier<Supplier<Item>> shoot) {
        super(properties);
        this.shoot = shoot;
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(AGE, 0));
    }


    @Override
    protected void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (level.isEmptyBlock(pos.above())) {
            int i = 1;

            while (level.getBlockState(pos.below(i)).is(this))
                i++;

            if (i < 4) {
                int j = state.getValue(AGE);
                if (j == 15) {
                    level.setBlockAndUpdate(pos.above(), this.defaultBlockState());
                    level.setBlock(pos, state.setValue(AGE, 0), 4);
                } else level.setBlock(pos, state.setValue(AGE, j + 1), 4);
            }
        }
    }

    @Override
    protected boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos) {
        BlockState blockState = level.getBlockState(pos.below());
        if (blockState.is(this)) return true;
        else if (blockState.is(BlockTags.DIRT) || blockState.is(BlockTags.SAND)) {
            BlockPos blockPos = pos.below();
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                BlockState blockState2 = level.getBlockState(blockPos.relative(direction));
                FluidState fluidState = level.getFluidState(blockPos.relative(direction));
                if (fluidState.is(FluidTags.WATER) || blockState2.is(Blocks.FROSTED_ICE)) return true;
            }
	        return level.getFluidState(pos).is(FluidTags.WATER);
        } return false;
    }

    @Override
    protected void neighborChanged(BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Block neighborBlock, @Nullable Orientation orientation, boolean movedByPiston) {
        if (!state.canSurvive(level, pos)) {
            if (state.getValue(WATERLOGGED)) level.setBlockAndUpdate(pos, Fluids.WATER.defaultFluidState().createLegacyBlock());
            else level.destroyBlock(pos, true);
        }
    }

    @Override
    protected @NotNull ItemStack getCloneItemStack(@NotNull LevelReader level, @NotNull BlockPos pos, @NotNull BlockState state, boolean includeData) {
        return this.shoot.get().get().getDefaultInstance();
    }

    @Override
    protected @NotNull FluidState getFluidState(@NotNull BlockState state) {
       return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        if (context.getLevel().getFluidState(context.getClickedPos()).is(FluidTags.WATER))
            return super.getStateForPlacement(context).setValue(WATERLOGGED, true);
        return this.defaultBlockState();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
        super.createBlockStateDefinition(builder);
    }
}
