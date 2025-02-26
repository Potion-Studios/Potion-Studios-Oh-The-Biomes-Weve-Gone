package net.potionstudios.biomeswevegone.world.level.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirtPathBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class BWGDirtPathBlock extends DirtPathBlock {

    private final Supplier<? extends Block> dirtBlock;

    public BWGDirtPathBlock(Supplier<? extends Block> dirtBlock, String id) {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT_PATH).setId(BiomesWeveGone.key(Registries.BLOCK, id)));
        this.dirtBlock = dirtBlock;
    }

    @Override
    public @NotNull BlockState getStateForPlacement(BlockPlaceContext context) {
        return !this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos())
                ? Block.pushEntitiesUp(this.defaultBlockState(), dirtBlock.get().defaultBlockState(), context.getLevel(), context.getClickedPos())
                : super.getStateForPlacement(context);
    }

    @Override
    public void tick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        BlockState blockState = pushEntitiesUp(state, dirtBlock.get().defaultBlockState(), level, pos);
        level.setBlockAndUpdate(pos, blockState);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(null, blockState));
    }
}
