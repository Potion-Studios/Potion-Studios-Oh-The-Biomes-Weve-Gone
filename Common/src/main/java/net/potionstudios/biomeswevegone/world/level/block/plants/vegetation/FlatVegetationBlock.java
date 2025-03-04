package net.potionstudios.biomeswevegone.world.level.block.plants.vegetation;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FlatVegetationBlock extends BushBlock {

    private static final VoxelShape VOXEL_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1D, 16.0D);

    private final @Nullable TagKey<Block> validGround;
    private final MapCodec<FlatVegetationBlock> CODEC = simpleCodec(FlatVegetationBlock::new);

    public FlatVegetationBlock(BlockBehaviour.Properties properties, @Nullable TagKey<Block> validGround) {
        super(properties);
        this.validGround = validGround;
    }

    public FlatVegetationBlock(BlockBehaviour.Properties properties) {
        this(properties, null);
    }

    @Override
    protected @NotNull MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return VOXEL_SHAPE;
    }

    @Override
    protected boolean mayPlaceOn(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
        return validGround == null ? super.mayPlaceOn(state, level, pos) : state.is(validGround);
    }
}
