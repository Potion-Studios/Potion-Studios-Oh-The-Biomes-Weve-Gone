package net.potionstudios.biomeswevegone.world.level.levelgen.structure.plateau;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.QuartPos;
import net.minecraft.core.SectionPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.synth.ImprovedNoise;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.BWGStructureTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class GourPlateauStructure extends Structure {

    public static final MapCodec<GourPlateauStructure> CODEC = RecordCodecBuilder.mapCodec(builder ->
            builder.group(
                    settingsCodec(builder)
            ).apply(builder, GourPlateauStructure::new)
    );

    public GourPlateauStructure(StructureSettings settings) {
        super(settings);
    }

    @Override
    protected @NotNull Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        ChunkPos chunkPos = context.chunkPos();
        WorldgenRandom random = context.random();

        int blockX = chunkPos.getBlockX(random.nextInt(16));
        int blockZ = chunkPos.getBlockZ(random.nextInt(16));


        float twoPi = Mth.TWO_PI;

        int slices = 20;

        double sliceSize = twoPi / slices;
        int plateauRadius = random.nextIntBetweenInclusive(48, 96);

        int sampleY = QuartPos.fromBlock(context.chunkGenerator().getSeaLevel());

        int minPlateauRadius = 16;
        boolean shouldCancel = true;
        for (int modifiedPlateauRadius = plateauRadius; modifiedPlateauRadius >= minPlateauRadius; modifiedPlateauRadius -= 4) {
            boolean success = true;
            for (int i = 0; i <= slices; i++) {
                double angle = i * sliceSize;

                int offsetX = (int) Math.round((Math.sin(angle) * modifiedPlateauRadius));
                int offsetZ = (int) Math.round((Math.cos(angle) * modifiedPlateauRadius));


                // Verify this structure is within the confines of its biome.
                if (!context.validBiome().test(context.biomeSource().getNoiseBiome(QuartPos.fromBlock(blockX + offsetX), sampleY, QuartPos.fromBlock(blockZ + offsetZ), context.randomState().sampler()))) {
                    success = false;
                    break;
                }
            }

            if (success) {
                plateauRadius = modifiedPlateauRadius - 4;
                shouldCancel = false;
                break;
            }
        }
        if (shouldCancel) {
            return Optional.empty();
        }


        int finalPlateauRadius = plateauRadius;
        return onTopOfChunkCenter(context, Heightmap.Types.OCEAN_FLOOR_WG, (piecesBuilder) -> {

            RandomState randomState = context.randomState();
            ChunkGenerator chunkGenerator = context.chunkGenerator();


            WorldgenRandom worldgenRandom = new WorldgenRandom(new XoroshiroRandomSource(context.seed()));


            ImprovedNoise noiseSampler = new ImprovedNoise(worldgenRandom);


            LevelHeightAccessor levelHeightAccessor = context.heightAccessor();
            BlockPos plateauOrigin = new BlockPos(blockX, chunkGenerator.getBaseHeight(blockX, blockZ, Heightmap.Types.OCEAN_FLOOR_WG, levelHeightAccessor, randomState) - 10, blockZ);
            double noise = noiseSampler.noise(plateauOrigin.getX() * 0.0005, 0, plateauOrigin.getZ() * 0.0005) + 1;


            noise *= 0.5; // Limit 0-1 range;


            double height = (int) (noise * 100);


            createArenaFloor(context, piecesBuilder, finalPlateauRadius, plateauOrigin, (int) height + chunkGenerator.getSeaLevel(), levelHeightAccessor);

        });
    }

    private static void createArenaFloor(GenerationContext context, StructurePiecesBuilder piecesBuilder, int arenaFloorRadius, BlockPos arenaOrigin, int topY, LevelHeightAccessor levelHeightAccessor) {
        for (int chunkX = -(SectionPos.blockToSectionCoord(arenaFloorRadius) + 1); chunkX <= SectionPos.blockToSectionCoord(arenaFloorRadius) + 1; chunkX++) {
            for (int chunkZ = -(SectionPos.blockToSectionCoord(arenaFloorRadius) + 1); chunkZ <= SectionPos.blockToSectionCoord(arenaFloorRadius) + 1; chunkZ++) {
                BlockPos chunkWorldPos = new BlockPos(SectionPos.sectionToBlockCoord(context.chunkPos().x + chunkX), arenaOrigin.getY(), SectionPos.sectionToBlockCoord(context.chunkPos().z + chunkZ));
                BoundingBox boundingBox = new BoundingBox(
                        chunkWorldPos.getX(), levelHeightAccessor.getMinY(), chunkWorldPos.getZ(),
                        chunkWorldPos.getX() + 15, levelHeightAccessor.getMaxY(), chunkWorldPos.getZ() + 15
                );
                piecesBuilder.addPiece(new GourPlateauPiece(arenaOrigin, arenaFloorRadius, topY, boundingBox));
            }
        }
    }

    @Override
    public @NotNull StructureType<?> type() {
        return BWGStructureTypes.OVERHANG_PLATEAU.get();
    }
}
