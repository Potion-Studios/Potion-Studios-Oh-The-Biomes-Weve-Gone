package net.potionstudios.biomeswevegone.world.level.block.plants.tree.fruit;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.potionstudios.biomeswevegone.world.item.BWGItems;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWood;
import org.jetbrains.annotations.NotNull;

public class SoulFruitBlock extends BWGFruitBlock {
	public SoulFruitBlock() {
		super(() -> BWGItems.SOUL_FRUIT, BWGWood.FLOWERING_SPIRIT_LEAVES);
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return true;
	}

	@Override
	public void randomTick(BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
		super.randomTick(state, level, pos, random);
		//level.getNearbyPlayers(TargetingConditions.forNonCombat().range(25.0), null, );
	}
}
