package net.potionstudios.biomeswevegone.world.level.block.plants.bush;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.tags.BWGBlockTags;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import org.jetbrains.annotations.NotNull;

public class HydrangeaBushBlock extends BWGPlacementBushBlock implements BonemealableBlock {

    private static final MapCodec<HydrangeaBushBlock> CODEC = simpleCodec(HydrangeaBushBlock::new);

    public HydrangeaBushBlock() {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA).noCollission().setId(BiomesWeveGone.key(Registries.BLOCK, "hydrangea_bush")), Block.box(0, 0, 0, 16, 16, 16), BWGBlockTags.HYDRANGEA_BUSH_PLACEABLE);
    }

    public HydrangeaBushBlock(Properties properties) {
        super(properties, Block.box(0, 0, 0, 16, 16, 16), BWGBlockTags.HYDRANGEA_BUSH_PLACEABLE);
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
    public void performBonemeal(ServerLevel level, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState state) {
        level.setBlockAndUpdate(pos, BWGBlocks.HYDRANGEA_HEDGE.get().defaultBlockState());
    }

    @Override
    protected @NotNull MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }
}
