package net.potionstudios.biomeswevegone.client.particle.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FireFlyParticle extends TextureSheetParticle {
	protected FireFlyParticle(ClientLevel level, double x, double y, double z) {
		super(level, x, y, z);
		this.gravity = 0.0F;
		this.lifetime = Mth.randomBetweenInclusive(this.random, 300, 800);
		this.setSize(0.01F, 0.01F);
	}

	protected FireFlyParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		super(level, x, y, z, xSpeed, ySpeed, zSpeed);
		this.gravity = 0.0F;
		this.lifetime = Mth.randomBetweenInclusive(this.random, 300, 800);
		this.setSize(0.01F, 0.01F);
	}

	@Override
	public @NotNull ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void tick() {
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		if (this.lifetime-- <= 0 || this.onGround)
			this.remove();
		else {
			this.move(this.xd, this.yd, this.zd);
			this.xd += (this.random.nextDouble() - 0.5) * 0.02;
			this.yd += (this.random.nextDouble() - 0.5) * 0.02;
			this.zd += (this.random.nextDouble() - 0.5) * 0.02;
			this.xd *= 0.9;
			this.yd *= 0.9;
			this.zd *= 0.9;
		}
	}

	public static class Provider implements ParticleProvider<SimpleParticleType> {

		private final SpriteSet sprite;

		public Provider(SpriteSet sprite) {
			this.sprite = sprite;
		}

		@Nullable
		@Override
		public Particle createParticle(@NotNull SimpleParticleType var1, @NotNull ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			FireFlyParticle fireFlyParticle = new FireFlyParticle(world, x, y, z, xSpeed, ySpeed, zSpeed);
			fireFlyParticle.lifetime = Mth.randomBetweenInclusive(world.random, 300, 800);
			fireFlyParticle.setColor(1.0f, 1.0f, 1.0f);
			fireFlyParticle.setSprite(this.sprite.get(world.random.nextInt(16), 16));
			return fireFlyParticle;
		}
	}
}
