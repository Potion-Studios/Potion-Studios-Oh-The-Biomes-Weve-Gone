package net.potionstudios.biomeswevegone.world.level.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SporeBlossomBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.potionstudios.biomeswevegone.client.particle.BWGParticles;
import org.jetbrains.annotations.NotNull;

public class WitchHazelBlossomBlock extends SporeBlossomBlock {
    public WitchHazelBlossomBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public void animateTick(@NotNull BlockState state, Level level, BlockPos pos, RandomSource random) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        level.addParticle(BWGParticles.WITCH_HAZEL_LEAVES.get(), x + random.nextDouble(), y + 0.7D, z + random.nextDouble(), 0.0D, 0.0D, 0.0D);
        BlockPos.MutableBlockPos level0 = new BlockPos.MutableBlockPos();

        for(int i = 0; i < 14; ++i) {
            level0.set(x + Mth.nextInt(random, -10, 10), y - random.nextInt(10), z + Mth.nextInt(random, -10, 10));
            BlockState state2 = level.getBlockState(level0);
            if (!state2.isCollisionShapeFullBlock(level, level0))
                level.addParticle(BWGParticles.WITCH_HAZEL_LEAVES.get(), (double)level0.getX() + random.nextDouble(), (double)level0.getY() + random.nextDouble(), (double)level0.getZ() + random.nextDouble(), 0.0D, 0.0D, 0.0D);
        }
    }
}
