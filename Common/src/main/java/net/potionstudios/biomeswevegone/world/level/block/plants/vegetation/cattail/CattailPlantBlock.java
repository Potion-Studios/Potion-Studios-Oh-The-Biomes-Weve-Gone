package net.potionstudios.biomeswevegone.world.level.block.plants.vegetation.cattail;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.redstone.Orientation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class CattailPlantBlock extends DoublePlantBlock implements SimpleWaterloggedBlock {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private final Supplier<Supplier<Item>> sprout;

    public CattailPlantBlock(BlockBehaviour.Properties properties, Supplier<Supplier<Item>> sprout) {
        super(properties);
        this.sprout = sprout;
        this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER).setValue(WATERLOGGED, false));
    }

    public CattailPlantBlock(Supplier<Supplier<Item>> sprout) {
        this(BlockBehaviour.Properties.of().noCollission().noCollission().sound(SoundType.WET_GRASS).strength(0.0F), sprout);
    }

    @Override
    public boolean canSurvive(BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos) {
        DoubleBlockHalf half = state.getValue(HALF);
        if (half.equals(DoubleBlockHalf.UPPER) && state.getValue(WATERLOGGED)) return false;
        else if (half.equals(DoubleBlockHalf.LOWER)) {
            BlockPos groundPos = pos.below();
            boolean water = level.getFluidState(pos).is(FluidTags.WATER);
            if (!water)
                for (Direction direction : Direction.Plane.HORIZONTAL)
                    if (level.getFluidState(groundPos.relative(direction)).is(FluidTags.WATER)) {
                        water = true;
                        break;
                    }

            BlockState ground = level.getBlockState(groundPos);
            return water && (ground.is(BlockTags.SAND) || ground.is(BlockTags.DIRT) || ground.is(Blocks.CLAY));
        }
        return super.canSurvive(state, level, pos);
    }

    @Override
    protected void neighborChanged(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Block neighborBlock, @Nullable Orientation orientation, boolean movedByPiston) {
        if (!canSurvive(state, level, pos)) {
            if (state.getValue(WATERLOGGED)) level.setBlockAndUpdate(pos, Fluids.WATER.defaultFluidState().createLegacyBlock());
            else level.destroyBlock(pos, false);
        }
    }

    @Override
    protected @NotNull ItemStack getCloneItemStack(@NotNull LevelReader level, @NotNull BlockPos pos, @NotNull BlockState state, boolean includeData) {
        return sprout.get().get().getDefaultInstance();
    }

    public BlockItem getSprout() {
        return (BlockItem) sprout.get().get();
    }

    @Override
    protected @NotNull BlockState updateShape(BlockState state, @NotNull LevelReader level, @NotNull ScheduledTickAccess scheduledTickAccess, @NotNull BlockPos pos, @NotNull Direction direction, @NotNull BlockPos neighborPos, @NotNull BlockState neighborState, @NotNull RandomSource random) {
        if (state.getValue(WATERLOGGED))
            scheduledTickAccess.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));

        return super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HALF, WATERLOGGED);
    }
}
