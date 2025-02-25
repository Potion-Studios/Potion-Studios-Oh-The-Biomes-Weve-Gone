package net.potionstudios.biomeswevegone.world.level.block.plants.cactus;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class CarvedBarrelCactusBlock extends BWGCactusBlock {

	public static final EnumProperty<LiquidType> LIQUID = EnumProperty.create("liquid", LiquidType.class);

	public CarvedBarrelCactusBlock() {
		super(BlockBehaviour.Properties.ofFullCopy(Blocks.CACTUS).noOcclusion());
		this.registerDefaultState(this.stateDefinition.any().setValue(LIQUID, LiquidType.EMPTY));
	}

	private static @NotNull VoxelShape makeShape() {
		VoxelShape shape = Shapes.empty();
		shape = Shapes.join(shape, Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.0625, 0.9375), BooleanOp.OR);
		shape = Shapes.join(shape, Shapes.box(0.0625, 0.0625, 0, 0.9375, 1, 0.0625), BooleanOp.OR);
		shape = Shapes.join(shape, Shapes.box(0.0625, 0.0625, 0.9375, 0.9375, 1, 1), BooleanOp.OR);
		shape = Shapes.join(shape, Shapes.box(0, 0.0625, 0.0625, 0.0625, 1, 0.9375), BooleanOp.OR);
		shape = Shapes.join(shape, Shapes.box(0.9375, 0.0625, 0.0625, 1, 1, 0.9375), BooleanOp.OR);
		return shape;
	}

	@Override
	public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
		return makeShape();
	}

	@Override
	public @NotNull VoxelShape getCollisionShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
		return makeShape();
	}

	@Override
	public @NotNull VoxelShape getInteractionShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
		return makeShape();
	}

	@Override
	protected @NotNull InteractionResult useItemOn(ItemStack stack, @NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
		if (stack.is(Items.WATER_BUCKET)) {
			player.setItemInHand(hand, new ItemStack(Items.BUCKET));
			level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1, 1);
			level.gameEvent(null, GameEvent.FLUID_PLACE, pos);
			level.setBlockAndUpdate(pos, state.setValue(LIQUID, LiquidType.WATER));
			return InteractionResult.SUCCESS;
		} else if (stack.is(Items.HONEY_BOTTLE)) {
			player.setItemInHand(hand, new ItemStack(Items.GLASS_BOTTLE));
			level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1, 1);
			level.gameEvent(null, GameEvent.FLUID_PLACE, pos);
			level.setBlockAndUpdate(pos, state.setValue(LIQUID, LiquidType.HONEY));
			return InteractionResult.SUCCESS;
		} else if (stack.is(Items.BUCKET) && state.getValue(LIQUID).equals(LiquidType.WATER)) {
			player.setItemInHand(hand, new ItemStack(Items.WATER_BUCKET));
			level.playSound(null, pos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1, 1);
			level.gameEvent(player, GameEvent.FLUID_PICKUP, pos);
			level.setBlockAndUpdate(pos, state.setValue(LIQUID, LiquidType.EMPTY));
			return InteractionResult.SUCCESS;
		} else if (stack.is(Items.GLASS_BOTTLE) && state.getValue(LIQUID).equals(LiquidType.HONEY)) {
			player.setItemInHand(hand, new ItemStack(Items.HONEY_BOTTLE));
			level.playSound(null, pos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1, 1);
			level.gameEvent(player, GameEvent.FLUID_PICKUP, pos);
			level.setBlockAndUpdate(pos, state.setValue(LIQUID, LiquidType.EMPTY));
			return InteractionResult.SUCCESS;
		}
		return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
	}

	@Override
	public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
		super.randomTick(state, level, pos, random);
		if (level.isRainingAt(pos)) {
			level.setBlockAndUpdate(pos, state.setValue(LIQUID, LiquidType.WATER));
			level.gameEvent(null, GameEvent.FLUID_PLACE, pos);
		}
	}

	@Override
	public boolean canSurvive(@NotNull BlockState state, LevelReader level, BlockPos pos) {
		BlockState below = level.getBlockState(pos.below());
		return !below.getCollisionShape(level, pos.below()).getFaceShape(Direction.UP).isEmpty() || below.isFaceSturdy(level, pos.below(), Direction.UP);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(LIQUID);
	}
}
