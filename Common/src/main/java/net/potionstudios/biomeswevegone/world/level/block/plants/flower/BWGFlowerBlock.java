package net.potionstudios.biomeswevegone.world.level.block.plants.flower;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

/**
 * A flower block that can only be placed on a specific block.
 * @see FlowerBlock
 * @author Joseph T. McQuigg
 */
public class BWGFlowerBlock extends FlowerBlock {

    /** The block that the flower can be placed on. */
    private final TagKey<Block> validGround;

    /** The shape of the flower block. */
    private final VoxelShape SHAPE;

    /**
     * Creates a flower block that can only be placed on the given block.
     * @param properties The properties of the block.
     * @param validGround The block that the flower can be placed on.
     */
    public BWGFlowerBlock(Properties properties, TagKey<Block> validGround, VoxelShape shape) {
        super(MobEffects.SATURATION, 7, properties);
        this.validGround = validGround;
        this.SHAPE = shape;
    }

    /**
     * Creates a flower block that can only be placed on dirt.
     * @see BlockTags#DIRT
     * @param properties The properties of the block.
     */
    public BWGFlowerBlock(Properties properties) {
        this(properties, BlockTags.DIRT, Block.box(5.0, 0.0, 5.0, 11.0, 10.0, 11.0));
    }

    /**
     * Checks if the block can be placed on the given block.
     * @param state The state of the block.
     * @param worldIn The world the block is in.
     * @param pos The position of the block.
     * @return If the block can be placed on the given block.
     */
    @Override
    protected boolean mayPlaceOn(BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos) {
        return state.is(validGround);
    }

    /**
     * Gets the shape of the block.
     * @param state The state of the block.
     * @param level The level the block is in.
     * @param pos The position of the block.
     * @param context The context of the collision.
     * @return The shape of the block.
     */
    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        Vec3 vec3 = state.getOffset(pos);
        return SHAPE.move(vec3.x, vec3.y, vec3.z);
    }
}
