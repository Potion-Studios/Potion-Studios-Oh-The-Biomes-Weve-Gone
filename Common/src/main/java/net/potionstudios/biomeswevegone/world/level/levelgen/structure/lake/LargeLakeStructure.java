package net.potionstudios.biomeswevegone.world.level.levelgen.structure.lake;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.BWGStructureTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class LargeLakeStructure extends Structure {

    public static final MapCodec<LargeLakeStructure> CODEC = RecordCodecBuilder.mapCodec(builder ->
            builder.group(
                    settingsCodec(builder),
                    LargeLakeConfig.CODEC.fieldOf("config").forGetter(largeLakeStructure -> largeLakeStructure.largeLakeConfig)
            ).apply(builder, LargeLakeStructure::new)
    );
    private final LargeLakeConfig largeLakeConfig;

    public LargeLakeStructure(StructureSettings settings, LargeLakeConfig largeLakeConfig) {
        super(settings);
        this.largeLakeConfig = largeLakeConfig;
    }

    @Override
    protected @NotNull Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        ChunkPos chunkPos = context.chunkPos();
        WorldgenRandom random = context.random();

        int blockX = chunkPos.getBlockX(random.nextInt(16));
        int blockZ = chunkPos.getBlockZ(random.nextInt(16));

        int lakeRadius = random.nextIntBetweenInclusive(96, 128);
        RandomState randomState = context.randomState();
        ChunkGenerator chunkGenerator = context.chunkGenerator();
        LevelHeightAccessor levelHeightAccessor = context.heightAccessor();


        int baseHeight = chunkGenerator.getBaseHeight(blockX, blockZ, Heightmap.Types.OCEAN_FLOOR_WG, levelHeightAccessor, randomState);

        if (baseHeight <= chunkGenerator.getSeaLevel() || baseHeight < chunkGenerator.getBaseHeight(blockX, blockZ, Heightmap.Types.WORLD_SURFACE_WG, levelHeightAccessor, randomState)) { // Underwater lakes don't make sense. Avoid Aquifers.
            return Optional.empty();
        }

        return onTopOfChunkCenter(context, Heightmap.Types.OCEAN_FLOOR_WG, (piecesBuilder) -> {


            BlockPos lakeOrigin = new BlockPos(blockX, baseHeight, blockZ);


            createLakePieces(context, piecesBuilder, lakeRadius, lakeOrigin, this.largeLakeConfig.depth().sample(random), levelHeightAccessor);

        });
    }

    private void createLakePieces(GenerationContext context, StructurePiecesBuilder piecesBuilder, int arenaFloorRadius, BlockPos arenaOrigin, int lakeDepth, LevelHeightAccessor levelHeightAccessor) {
        for (int chunkX = -(SectionPos.blockToSectionCoord(arenaFloorRadius) + 1); chunkX <= SectionPos.blockToSectionCoord(arenaFloorRadius) + 1; chunkX++) {
            for (int chunkZ = -(SectionPos.blockToSectionCoord(arenaFloorRadius) + 1); chunkZ <= SectionPos.blockToSectionCoord(arenaFloorRadius) + 1; chunkZ++) {
                BlockPos chunkWorldPos = new BlockPos(SectionPos.sectionToBlockCoord(context.chunkPos().x + chunkX), arenaOrigin.getY(), SectionPos.sectionToBlockCoord(context.chunkPos().z + chunkZ));
                BoundingBox boundingBox = new BoundingBox(
                        chunkWorldPos.getX(), levelHeightAccessor.getMinY(), chunkWorldPos.getZ(),
                        chunkWorldPos.getX() + 15, levelHeightAccessor.getMaxY(), chunkWorldPos.getZ() + 15
                );
                piecesBuilder.addPiece(new LargeLakePiece(arenaOrigin, arenaFloorRadius, 20, boundingBox, this.largeLakeConfig.features()));
            }
        }
    }

    @Override
    public @NotNull StructureType<?> type() {
        return BWGStructureTypes.LARGE_LAKE.get();
    }
}
