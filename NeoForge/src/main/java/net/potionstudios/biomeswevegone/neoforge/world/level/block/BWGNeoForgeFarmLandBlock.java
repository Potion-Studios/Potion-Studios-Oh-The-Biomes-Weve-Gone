package net.potionstudios.biomeswevegone.neoforge.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.extensions.IBlockExtension;
import net.neoforged.neoforge.common.util.TriState;
import net.potionstudios.biomeswevegone.world.level.block.custom.BWGFarmLandBlock;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * Custom Farm Land Block for BWG that uses IBlockExtension to allow for plants to grow on it.
 * @see BWGFarmLandBlock
 * @see IBlockExtension
 */
public class BWGNeoForgeFarmLandBlock extends BWGFarmLandBlock implements IBlockExtension {
    public BWGNeoForgeFarmLandBlock(Supplier<Block> dirt) {
        super(dirt);
    }

    @Override
    public @NotNull TriState canSustainPlant(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos soilPosition, @NotNull Direction facing, @NotNull BlockState plant) {
        return TriState.TRUE;
    }
}
