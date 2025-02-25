package net.potionstudios.biomeswevegone.world.level.block.plants.vegetation.cattail;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.potionstudios.biomeswevegone.client.particle.BWGParticles;
import net.potionstudios.biomeswevegone.world.item.custom.PowderItem;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class FluorescentCattailPlantBlock extends CattailPlantBlock {

	public static final EnumProperty<ColorProperty> COLOR = EnumProperty.create("color", ColorProperty.class);

	public FluorescentCattailPlantBlock(Supplier<Supplier<Item>> sprout) {
		super(BlockBehaviour.Properties.of().noCollission().noCollission().sound(SoundType.WET_GRASS).strength(0.0F).lightLevel(level -> 12), sprout);
		this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER).setValue(WATERLOGGED, false).setValue(COLOR, ColorProperty.NO_COLOR));
	}

	@Override
	protected @NotNull InteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
		if (stack.getItem() instanceof PowderItem powderItem && (state.getValue(COLOR) == ColorProperty.NO_COLOR || player.isCreative())) {
			level.playSound(player, pos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS);
			level.setBlockAndUpdate(pos, state.setValue(COLOR, powderItem.getColor()));
			if (!player.isCreative()) stack.shrink(1);
			return InteractionResult.SUCCESS;
		}
		return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
	}

	@Override
	public void animateTick(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, RandomSource random) {
		if (random.nextDouble() > 0.25) return;
		BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
		
		mutableBlockPos.set(pos.getX() + random.nextInt(-6, 6), pos.getY() + random.nextInt(-1, 3), pos.getZ() + random.nextInt(-6, 6));
		if (!level.getBlockState(mutableBlockPos).isCollisionShapeFullBlock(level, mutableBlockPos)) {
			level.addParticle(
					BWGParticles.FIREFLY.get(),
					(double)mutableBlockPos.getX() + random.nextDouble(),
					(double)mutableBlockPos.getY() + random.nextDouble(),
					(double)mutableBlockPos.getZ() + random.nextDouble(),
					0.0,
					0.0,
					0.0
			);
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(COLOR);
		super.createBlockStateDefinition(builder);
	}
}

