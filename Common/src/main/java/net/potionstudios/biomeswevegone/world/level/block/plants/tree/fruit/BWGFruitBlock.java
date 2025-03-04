package net.potionstudios.biomeswevegone.world.level.block.plants.tree.fruit;

import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class BWGFruitBlock extends Block implements BonemealableBlock {

    public static final MapCodec<BWGFruitBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            propertiesCodec(),
            ResourceLocation.CODEC.fieldOf("fruit").forGetter(item -> BuiltInRegistries.ITEM.getKey(item.fruit.get().get())),
            ResourceLocation.CODEC.fieldOf("leaves").forGetter(block -> BuiltInRegistries.BLOCK.getKey(block.leaves.get()))
    ).apply(instance, BWGFruitBlock::new));

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

    public BWGFruitBlock(Properties properties, Supplier<Supplier<Item>> fruit, Supplier<LeavesBlock> leaves) {
        super(properties);
        this.fruit = fruit;
        this.leaves = leaves;
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    private BWGFruitBlock(Properties properties, ResourceLocation fruitLocation, ResourceLocation leavesLocation) {
        this(properties, Suppliers.memoize(() -> () -> BuiltInRegistries.ITEM.get(fruitLocation)), Suppliers.memoize(() -> (LeavesBlock) BuiltInRegistries.BLOCK.get(leavesLocation)));
    }

    public BWGFruitBlock(Supplier<Supplier<Item>> fruit, String leaves) {
        this(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY), fruit, leaves);
    }

    @Override
    protected @NotNull MapCodec<? extends Block> codec() {
        return CODEC;
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
    protected @NotNull ItemStack getCloneItemStack(@NotNull LevelReader level, @NotNull BlockPos pos, @NotNull BlockState state, boolean includeData) {
        return this.fruit.get().get().getDefaultInstance();
    }

    @Override
    protected @NotNull InteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if (player.isCreative() && state.getValue(AGE) != MAX_AGE && stack.is(getFruit())) {
            level.setBlock(pos, state.setValue(AGE, state.getValue(AGE) + 1), 2);
            return InteractionResult.SUCCESS;
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull BlockHitResult hitResult) {
        if (state.getValue(AGE) == MAX_AGE) {
            popResource(level, pos, this.fruit.get().get().getDefaultInstance());
            level.playSound(player, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            level.setBlock(pos, state.setValue(AGE, 0), 2);
            return InteractionResult.SUCCESS;
        }
        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.above()).is(this.leaves.get());
    }

    @Override
    protected @NotNull BlockState updateShape(@NotNull BlockState state, @NotNull LevelReader level, @NotNull ScheduledTickAccess scheduledTickAccess, @NotNull BlockPos pos, @NotNull Direction direction, @NotNull BlockPos neighborPos, @NotNull BlockState neighborState, @NotNull RandomSource random) {
        return !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
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
    public boolean isValidBonemealTarget(@NotNull LevelReader level, @NotNull BlockPos pos, BlockState state) {
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
    protected void onProjectileHit(@NotNull Level level, @NotNull BlockState state, @NotNull BlockHitResult hit, @NotNull Projectile projectile) {
        if (level.isClientSide()) return;
        level.destroyBlock(hit.getBlockPos(), true, projectile);
    }

    @Override
    protected @NotNull VoxelShape getCollisionShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        if (context instanceof EntityCollisionContext entityCollisionContext)
            if (entityCollisionContext.getEntity() instanceof LivingEntity)
                return Shapes.empty();
        return super.getCollisionShape(state, level, pos, context);
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
