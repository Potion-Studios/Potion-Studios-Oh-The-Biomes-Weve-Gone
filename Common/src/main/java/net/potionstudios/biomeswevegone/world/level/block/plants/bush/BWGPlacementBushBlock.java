package net.potionstudios.biomeswevegone.world.level.block.plants.bush;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BWGPlacementBushBlock extends BushBlock {
    private final @Nullable VoxelShape SHAPE;
    private final TagKey<Block> validGround;
    private static final MapCodec<BWGPlacementBushBlock> CODEC = simpleCodec(BWGPlacementBushBlock::new);

    public BWGPlacementBushBlock(Properties properties, @Nullable VoxelShape shape, TagKey<Block> validGround) {
        super(properties);
        this.SHAPE = shape;
        this.validGround = validGround;
    }

    public BWGPlacementBushBlock(Properties properties) {
        this(properties, null, BlockTags.DIRT);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        if (SHAPE == null) return super.getShape(state, level, pos, context);
        Vec3 Vector3d = state.getOffset(pos);
        return SHAPE.move(Vector3d.x(), Vector3d.y(), Vector3d.z());
    }

    @Override
    protected @NotNull MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
        return state.is(validGround);
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos) {
        return this.mayPlaceOn(level.getBlockState(pos.below()), level, pos);
    }
}
