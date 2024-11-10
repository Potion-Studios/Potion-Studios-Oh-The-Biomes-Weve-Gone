package net.potionstudios.biomeswevegone.world.level.block.plants.tree.leaves;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * The leaves block for Oh The Biomes We've Gone.
 * @see LeavesBlock
 * @author Joseph T. McQuigg
 */
public class BWGLeavesBlock extends LeavesBlock {

    private final @Nullable Supplier<SimpleParticleType> particleType;

    public BWGLeavesBlock(Properties properties, @Nullable Supplier<SimpleParticleType> particleType) {
        super(properties);
        this.particleType = particleType;
    }

    @Deprecated
    public BWGLeavesBlock(Properties properties) {
        this(properties, null);
    }

    @Override
    public void animateTick(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull RandomSource randomSource) {
        super.animateTick(blockState, level, blockPos, randomSource);
        if (particleType != null)
            if (randomSource.nextDouble() < 0.01F) {
                BlockPos below = blockPos.below();
                BlockState state = level.getBlockState(below);
                if (!state.canOcclude() || !state.isFaceSturdy(level, below, Direction.UP)) {
                    double x = (double) blockPos.getX() + randomSource.nextDouble();
                    double y = (double) blockPos.getY() - 0.05;
                    double z = (double) blockPos.getZ() + randomSource.nextDouble();
                    level.addParticle(particleType.get(), x, y, z, 0.0, 0, 0.0);
                }
            }
    }
}
