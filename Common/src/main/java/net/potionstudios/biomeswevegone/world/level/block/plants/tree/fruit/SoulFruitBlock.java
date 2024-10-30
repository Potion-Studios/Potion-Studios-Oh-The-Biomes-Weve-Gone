package net.potionstudios.biomeswevegone.world.level.block.plants.tree.fruit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.potionstudios.biomeswevegone.client.BWGSounds;
import net.potionstudios.biomeswevegone.world.item.BWGItems;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWood;
import org.jetbrains.annotations.NotNull;

public class SoulFruitBlock extends BWGFruitBlock {
	public SoulFruitBlock() {
		super(Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH).lightLevel(light -> 10).mapColor(MapColor.COLOR_PURPLE), () -> BWGItems.SOUL_FRUIT, BWGWood.FLOWERING_SPIRIT_LEAVES);
	}

	@Override
	public void animateTick(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull RandomSource random) {
		if (state.getValue(AGE) == MAX_AGE && level.getBlockState(pos.below()).isAir())
			ParticleUtils.spawnParticleBelow(level, pos, random, new BlockParticleOption(ParticleTypes.FALLING_DUST, state));
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return true;
	}

	@Override
	public void randomTick(BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
		super.randomTick(state, level, pos, random);
		level.playSound(null, pos, BWGSounds.SOUL_FRUIT_WAIL.get(), SoundSource.BLOCKS);
		level.getPlayers(player -> player.distanceToSqr(pos.getX(), pos.getY(), pos.getZ()) < 25).stream().filter(player -> !player.isSpectator()).forEach(player ->
				player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 1200), player));
	}
}
