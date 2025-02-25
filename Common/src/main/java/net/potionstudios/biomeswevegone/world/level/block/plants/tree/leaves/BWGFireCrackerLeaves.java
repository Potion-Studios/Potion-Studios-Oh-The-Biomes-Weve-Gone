package net.potionstudios.biomeswevegone.world.level.block.plants.tree.leaves;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.potionstudios.biomeswevegone.tags.BWGItemTags;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import org.jetbrains.annotations.NotNull;

public class BWGFireCrackerLeaves extends LeavesBlock {
    public BWGFireCrackerLeaves(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull InteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        RandomSource random = level.getRandom();
        if (stack.is(BWGItemTags.SHEARS) && level.getBlockState(pos.below()).is(BlockTags.DIRT)) {
            level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            double d = (double) pos.getX() + random.nextDouble();
            double e = (double) pos.getY() + 1.0D;
            double f = (double) pos.getZ() + random.nextDouble();
            level.setBlockAndUpdate(pos, BWGBlocks.FIRECRACKER_FLOWER_BUSH.getBlockState());
            level.neighborChanged(pos, BWGBlocks.FIRECRACKER_FLOWER_BUSH.getBlock(), null);
            level.addParticle(ParticleTypes.HAPPY_VILLAGER, d, e, f, 0.0D, 0.0D, 0.0D);
            level.gameEvent(player, GameEvent.SHEAR, pos);
            player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }
}
