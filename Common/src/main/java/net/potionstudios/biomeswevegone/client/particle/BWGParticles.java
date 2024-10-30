package net.potionstudios.biomeswevegone.client.particle;

import net.minecraft.core.particles.SimpleParticleType;
import net.potionstudios.biomeswevegone.PlatformHandler;

import java.util.function.Supplier;

/**
 * Particles for Oh The Biomes We've Gone.
 * @see SimpleParticleType
 * @author Joseph T. McQuigg
 */
public class BWGParticles {

    public static final Supplier<SimpleParticleType> WITCH_HAZEL_LEAVES = register("witch_hazel_leaves");
    public static final Supplier<SimpleParticleType> SPIRIT_LEAVES = register("spirit_leaves");
    public static final Supplier<SimpleParticleType> WHITE_SAKURA_LEAVES = register("white_sakura_leaves");
    public static final Supplier<SimpleParticleType> YELLOW_SAKURA_LEAVES = register("yellow_sakura_leaves");
    public static final Supplier<SimpleParticleType> RED_MAPLE_LEAVES = register("red_maple_leaves");
    public static final Supplier<SimpleParticleType> SILVER_MAPLE_LEAVES = register("silver_maple_leaves");
    public static final Supplier<SimpleParticleType> IRONWOOD_LEAVES = register("ironwood_leaves");
    public static final Supplier<SimpleParticleType> BOREALIS_GLINT = register("borealis_glint");
    public static final Supplier<SimpleParticleType> FIREFLY = register("firefly");
    public static final Supplier<SimpleParticleType> SPIRIT = register("spirit");


    private static Supplier<SimpleParticleType> register(String id) {
        return PlatformHandler.PLATFORM_HANDLER.registerCreateParticle(id);
    }
}
