package net.potionstudios.biomeswevegone.world.level.block.plants.cactus;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.potionstudios.biomeswevegone.tags.BWGItemTags;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import org.jetbrains.annotations.NotNull;

public class BarrelCactusBlock extends BWGCactusBlock implements BonemealableBlock {
	public BarrelCactusBlock() {
		super(BlockBehaviour.Properties.ofFullCopy(Blocks.CACTUS).noOcclusion());
	}

	@Override
	protected @NotNull InteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
		if (stack.is(BWGItemTags.SHEARS)) {
			if (!level.isClientSide()) {
				level.playSound(null, pos, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);
				level.setBlockAndUpdate(pos, BWGBlocks.CARVED_BARREL_CACTUS.get().defaultBlockState());
				level.gameEvent(player, GameEvent.SHEAR, pos);
				player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
			}
			return InteractionResult.SUCCESS;
		}
		return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
	}

	protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		return level.getBlockState(pos.below()).is(BlockTags.SAND) && state.getFluidState().isEmpty();
	}

	@Override
	public boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos) {
		return mayPlaceOn(level.getBlockState(pos.above()), level, pos);
	}

	@Override
	protected @NotNull BlockState updateShape(@NotNull BlockState state, @NotNull LevelReader level, @NotNull ScheduledTickAccess scheduledTickAccess, @NotNull BlockPos pos, @NotNull Direction direction, @NotNull BlockPos neighborPos, @NotNull BlockState neighborState, @NotNull RandomSource random) {
		if (!state.canSurvive(level, pos))
			return Blocks.AIR.defaultBlockState();
		return super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
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
	public void performBonemeal(@NotNull ServerLevel level, @NotNull RandomSource random, @NotNull BlockPos pos, BlockState state) {
		if (!state.is(BWGBlocks.FLOWERING_BARREL_CACTUS.get()))
			level.setBlockAndUpdate(pos, BWGBlocks.FLOWERING_BARREL_CACTUS.get().defaultBlockState());
	}
}
