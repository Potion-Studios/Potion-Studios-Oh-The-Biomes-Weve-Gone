package net.potionstudios.biomeswevegone.world.level.block.plants.tree.fruit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.potionstudios.biomeswevegone.config.configs.BWGMiscConfig;
import net.potionstudios.biomeswevegone.sounds.BWGSounds;
import net.potionstudios.biomeswevegone.world.item.BWGItems;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWood;
import org.jetbrains.annotations.NotNull;

public class SoulFruitBlock extends BWGFruitBlock {
	public SoulFruitBlock(BlockBehaviour.Properties properties) {
		super(properties, () -> BWGItems.SOUL_FRUIT, BWGWood.FLOWERING_SPIRIT_LEAVES);
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
		if (random.nextBoolean())
			level.playSound(null, pos, BWGSounds.SOUL_FRUIT_WAIL.get(), SoundSource.BLOCKS, 0.5f, 1.0f);
		if (!BWGMiscConfig.INSTANCE.soulFruit.ALLOW_SOUL_FRUIT_BLINDNESS.value()) return;
		Vec3 center = pos.getCenter();
		for (ServerPlayer player : level.getPlayers(player -> !player.isSpectator()))
			if (center.closerThan(player.position(), BWGMiscConfig.INSTANCE.soulFruit.SOUL_FRUIT_BLINDNESS_RANGE.value()))
				player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, BWGMiscConfig.INSTANCE.soulFruit.SOUL_FRUIT_BLINDNESS.value() * 20, 0, false, false));
	}

	@Override
	public void destroy(@NotNull LevelAccessor level, @NotNull BlockPos pos, @NotNull BlockState state) {
		if (level.isClientSide()) return;
		Vec3 center = pos.getCenter();
		for (ServerPlayer player : ((ServerLevel) level).getPlayers(player -> !player.isSpectator()))
			if (center.closerThan(player.position(), 25))
				player.removeEffect(MobEffects.DARKNESS);
	}
}
