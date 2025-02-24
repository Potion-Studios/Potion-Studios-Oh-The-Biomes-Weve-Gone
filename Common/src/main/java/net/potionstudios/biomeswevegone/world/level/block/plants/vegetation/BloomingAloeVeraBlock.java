package net.potionstudios.biomeswevegone.world.level.block.plants.vegetation;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.potionstudios.biomeswevegone.tags.BWGItemTags;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import org.jetbrains.annotations.NotNull;

public class BloomingAloeVeraBlock extends DoublePlantBlock {
    public BloomingAloeVeraBlock() {
        super(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.0f).sound(SoundType.WET_GRASS)
                .noOcclusion().noCollission().pushReaction(PushReaction.DESTROY));
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull BlockHitResult hitResult) {
        use(level, pos);
        return InteractionResult.SUCCESS;
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if (stack.is(BWGItemTags.SHEARS)) {
            use(level, pos);
            return ItemInteractionResult.sidedSuccess(level.isClientSide());
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    private void use(@NotNull Level level, @NotNull BlockPos pos) {
        level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1, 0.8f + level.random.nextFloat() * 0.4f);
        popResource(level, pos, new ItemStack(BWGBlocks.ALOE_VERA.get().asItem(), level.random.nextInt(1, 3)));
        level.setBlockAndUpdate(level.getBlockState(pos.below()).is(BlockTags.SAND) ? pos : pos.below(), BWGBlocks.ALOE_VERA.get().defaultBlockState());
        level.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX() + level.random.nextDouble(), pos.getY() + 1.0D, pos.getZ() + level.random.nextDouble(), 0.0D, 0.0D, 0.0D);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
        return state.is(BlockTags.SAND);
    }

    @Override
    protected ItemStack getCloneItemStack(@NotNull LevelReader level, @NotNull BlockPos pos, @NotNull BlockState state, boolean includeData) {
        return BWGBlocks.ALOE_VERA.get().asItem().getDefaultInstance();
    }
}
