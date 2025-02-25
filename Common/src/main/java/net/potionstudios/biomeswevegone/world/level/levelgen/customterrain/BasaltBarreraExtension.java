package net.potionstudios.biomeswevegone.world.level.levelgen.customterrain;

import corgitaco.corgilib.math.blendingfunction.BlendingFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import net.minecraft.world.level.levelgen.synth.ImprovedNoise;
import net.potionstudios.biomeswevegone.util.GeneratorHeightGetter;
import net.potionstudios.biomeswevegone.util.MathUtil;
import net.potionstudios.biomeswevegone.world.level.levelgen.biome.BWGBiomes;
import net.potionstudios.biomeswevegone.world.level.levelgen.util.BlendUtil;
import org.joml.Vector2d;
import org.joml.Vector4d;

import java.util.Random;
import java.util.function.Function;

public class BasaltBarreraExtension {


    public static void runBasaltBarreraExtension(Function<BlockPos, Holder<Biome>> biomeGetter, ChunkAccess chunk, WorldGenRegion region, ChunkGenerator generator) {
        ChunkPos pos = chunk.getPos();

        ImprovedNoise hexRadiusNoise = new ImprovedNoise(new XoroshiroRandomSource(region.getSeed()));
        ImprovedNoise hexHeightNoise = new ImprovedNoise(new XoroshiroRandomSource(region.getSeed() + 2394504));

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int worldX = pos.getBlockX(x);
                int worldZ = pos.getBlockZ(z);
                int landHeight = (chunk.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, worldX, worldZ) / 10) * 10;
                if (chunk.getMinY() >= landHeight) {
                    continue;
                }

                mutable.set(worldX, landHeight, worldZ);

                Holder<Biome> currentBiome = biomeGetter.apply(mutable);

                if (!currentBiome.is(BWGBiomes.BASALT_BARRERA)) {
                    continue;
                }

                double hexRadiusNoiseFreq = 0.01;

                double hexDelta = (hexRadiusNoise.noise((mutable.getX()) * hexRadiusNoiseFreq, 0, (mutable.getZ()) * hexRadiusNoiseFreq) + 1) * 0.5F;

                int hexRadius = (int) BlendingFunction.EaseInOutCirc.INSTANCE.apply(hexDelta, 4, 10);

                Vector4d vector4d = MathUtil.calcHexInfo(new Vector2d(mutable.getX(), mutable.getZ()), hexRadius);
                Vector2d hexCenter = new Vector2d(mutable.getX() - vector4d.x, mutable.getZ() - vector4d.y);

                mutable.set(hexCenter.x, mutable.getY(), hexCenter.y);

                Holder<Biome> hexCenterBiome = biomeGetter.apply(mutable);
                if (!hexCenterBiome.is(BWGBiomes.BASALT_BARRERA)) {
                    continue;
                }


                double biomeBlend = BlendUtil.blendBiomeEdge(currentBiome, biomeGetter, mutable, hexRadius * 2, 1);


                ChunkAccess hexCenterChunk = region.getChunk(mutable);

                if (hexCenterChunk instanceof GeneratorHeightGetter generatorHeightGetter) {
                    Random random = new Random(mutable.asLong() + region.getSeed());

                    BlockState state = random.nextBoolean() ? Blocks.BASALT.defaultBlockState() : Blocks.SMOOTH_BASALT.defaultBlockState();
                    int hexHeightOceanFloorHeight = generatorHeightGetter.getHeight(generator, Heightmap.Types.OCEAN_FLOOR_WG, mutable.getX(), mutable.getZ(), region.getLevel().getChunkSource().randomState(), true);

                    double noiseFreq = 0.1;


                    mutable.set(worldX, hexHeightOceanFloorHeight, worldZ);
                    double heightDelta = (hexHeightNoise.noise(hexCenter.x * noiseFreq, 0, hexCenter.y * noiseFreq) + 1) * 0.5F;


                    double addedHeight = BlendingFunction.EaseInOutCirc.INSTANCE.apply(heightDelta, 1, 4);


                    double topY = hexHeightOceanFloorHeight + addedHeight;
                    double blendedY = Mth.clampedLerp(landHeight, topY, biomeBlend);


                    for (int worldY = landHeight - 5; worldY <= blendedY; worldY++) {
                        mutable.set(worldX, worldY, worldZ);
                        chunk.setBlockState(mutable, state, false);
                    }
                }
            }
        }
    }
}
