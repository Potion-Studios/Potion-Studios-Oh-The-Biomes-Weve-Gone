package net.potionstudios.biomeswevegone.forge.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.extensions.IForgeBlock;
import net.potionstudios.biomeswevegone.world.level.block.custom.BWGFarmLandBlock;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * Custom Farm Land Block for BWG that uses IForgeBlock to allow for plants to grow on it.
 * @see BWGFarmLandBlock
 * @see IForgeBlock
 */
public class BWGForgeFarmLandBlock extends BWGFarmLandBlock implements IForgeBlock {
    public BWGForgeFarmLandBlock(Supplier<Block> dirt) {
        super(dirt);
    }

    @Override
    public boolean canSustainPlant(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull Direction facing, @NotNull IPlantable plantable) {
        return true;
    }
}
