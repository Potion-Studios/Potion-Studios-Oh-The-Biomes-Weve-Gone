package net.potionstudios.biomeswevegone.world.level.block.sand;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ColoredFallingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.potionstudios.biomeswevegone.world.damagesource.BWGDamageTypes;
import org.jetbrains.annotations.NotNull;

public class BWGQuickSand extends ColoredFallingBlock {
	public BWGQuickSand(int dustColor, BlockBehaviour.Properties properties) {
		super(new ColorRGBA(dustColor), properties);
	}

	@Override
	protected @NotNull VoxelShape getOcclusionShape(@NotNull BlockState state) {
		return Shapes.empty();
	}

	@Override
	public void entityInside(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Entity entity) {
		if (entity instanceof LivingEntity) {
			entity.makeStuckInBlock(state, new Vec3(0.9F, 1.5, 0.9F));
			if (level.isClientSide) {
				RandomSource randomSource = level.getRandom();
				boolean bl = entity.xOld != entity.getX() || entity.zOld != entity.getZ();
				if (bl && randomSource.nextBoolean()) {
					level.addParticle(
							new BlockParticleOption(ParticleTypes.FALLING_DUST, state),
							entity.getX() + Mth.randomBetween(randomSource, -1.0F, 1.0F),
							entity.getEyeY() + Mth.randomBetween(randomSource,0,  1.0F),
							entity.getZ() + Mth.randomBetween(randomSource, -1.0F, 1.0F),
							Mth.randomBetween(randomSource, -1.0F, 1.0F) * 0.083333336F,
							0.05F,
							Mth.randomBetween(randomSource, -1.0F, 1.0F) * 0.083333336F
					);
				}
			}
			BlockPos headPos = new BlockPos(entity.getBlockX(), (int) entity.getEyeY(), entity.getBlockZ());
			if (level.getBlockState(headPos).getBlock() instanceof BWGQuickSand)
				entity.hurt(new DamageSource(level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(BWGDamageTypes.IN_QUICKSAND)), 0.5F);
		}

		if (level instanceof ServerLevel serverLevel) {
			if (entity.isOnFire() && (serverLevel.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING) || entity instanceof Player) && entity.mayInteract(serverLevel, pos)) {
				level.destroyBlock(pos, false);
			}

			entity.setSharedFlagOnFire(false);
		}
	}

	@Override
	public void fallOn(@NotNull Level level, @NotNull BlockState state, @NotNull BlockPos pos, @NotNull Entity entity, float fallDistance) {
		if (!((double)fallDistance < 4.0) && entity instanceof LivingEntity livingEntity) {
			LivingEntity.Fallsounds fallsounds = livingEntity.getFallSounds();
			SoundEvent soundEvent = (double)fallDistance < 7.0 ? fallsounds.small() : fallsounds.big();
			entity.playSound(soundEvent, 1.0F, 1.0F);
		}
	}

	@Override
	public @NotNull VoxelShape getVisualShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
		return Shapes.empty();
	}
}
