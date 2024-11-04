package net.potionstudios.biomeswevegone.world.level.block.plants.tree.fruit;

import com.google.common.base.Suppliers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class BWGFruitBlock extends Block implements BonemealableBlock {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    public static final int MAX_AGE = 3;

    private final Supplier<Supplier<Item>> fruit;
    private final Supplier<LeavesBlock> leaves;

    public BWGFruitBlock(Properties properties, Supplier<Supplier<Item>> fruit, String leaves) {
        super(properties);
        this.fruit = fruit;
        this.leaves = Suppliers.memoize(() -> (LeavesBlock) BuiltInRegistries.BLOCK.get(BiomesWeveGone.id(leaves)));
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    public BWGFruitBlock(Supplier<Supplier<Item>> fruit, String leaves) {
        this(Properties.copy(Blocks.SWEET_BERRY_BUSH), fruit, leaves);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return switch (state.getValue(AGE)) {
            case 0 -> Block.box(4.0D, 9.0D, 4.0D, 12.0D, 16.0D, 12.0D);
            case 1 -> Block.box(4.0D, 6.0D, 4.0D, 12.0D, 16.0D, 12.0D);
            case 2 -> Block.box(3.0D, 3.0D, 3.0D, 13.0D, 16.0D, 13.0D);
            case 3 -> Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
            default -> throw new IllegalStateException("Unexpected value: " + state.getValue(AGE));
        };
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(@NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull BlockState state) {
        return this.fruit.get().get().getDefaultInstance();
    }

    @Override
    public @NotNull InteractionResult use(BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        boolean maxAge = state.getValue(AGE) == MAX_AGE;

        if (!maxAge && player.getItemInHand(hand).is(Items.BONE_MEAL))
            return InteractionResult.PASS;

        if (maxAge) {
            popResource(level, pos, this.fruit.get().get().getDefaultInstance());
            level.playSound(player, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            level.setBlock(pos, state.setValue(AGE, 0), 2);

            return InteractionResult.sidedSuccess(level.isClientSide());
        }

        return super.use(state, level, pos, player, hand, hit);
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.above()).is(this.leaves.get());
    }

    @Override
    public @NotNull BlockState updateShape(BlockState state, @NotNull Direction direction, @NotNull BlockState neighborState, @NotNull LevelAccessor level, @NotNull BlockPos pos, @NotNull BlockPos neighborPos) {
        return !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < MAX_AGE;
    }

    @Override
    public void randomTick(BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        int age = state.getValue(AGE);
        if (age < MAX_AGE && level.getRawBrightness(pos.above(), 0) >= 9 && random.nextInt(5) == 0)
            level.setBlock(pos, state.setValue(AGE, age + 1), 2);
    }

    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader level, @NotNull BlockPos pos, BlockState state, boolean isClient) {
        return state.getValue(AGE) < MAX_AGE;
    }

    @Override
    public boolean isBonemealSuccess(@NotNull Level level, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, @NotNull RandomSource random, @NotNull BlockPos pos, BlockState state) {
        level.setBlock(pos, state.setValue(AGE, Math.min(MAX_AGE, state.getValue(AGE) + 1)), 2);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    public @NotNull Item getFruit() {
        return fruit.get().get();
    }

    public @NotNull LeavesBlock getLeaves() {
        return leaves.get();
    }
}
