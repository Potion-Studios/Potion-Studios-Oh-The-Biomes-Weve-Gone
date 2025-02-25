package net.potionstudios.biomeswevegone.world.level.levelgen.structure.lake;

import corgitaco.corgilib.world.level.RandomTickScheduler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.SectionPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.RegistryOps;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.synth.ImprovedNoise;
import net.minecraft.world.level.material.Fluids;
import net.potionstudios.biomeswevegone.util.UnsafeBoundingBox;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.BWGStructurePieceTypes;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class LargeLakePiece extends StructurePiece {

    private final BlockPos origin;
    private final int radius;
    private final int lakeDepth;
    private final HolderSet<PlacedFeature> lakeFeatures;


    protected LargeLakePiece(BlockPos origin, int radius, int lakeDepth, BoundingBox boundingBox, HolderSet<PlacedFeature> lakeFeatures) {
        super(BWGStructurePieceTypes.LARGE_LAKE.get(), 0, boundingBox);
        this.origin = origin;
        this.radius = radius;
        this.lakeDepth = lakeDepth;
        this.lakeFeatures = lakeFeatures;
    }

    public LargeLakePiece(StructurePieceSerializationContext context, CompoundTag tag) {
        super(BWGStructurePieceTypes.LARGE_LAKE.get(), tag);
        this.origin = NbtUtils.readBlockPos(tag, "origin").orElseThrow();
        this.radius = tag.getInt("radius");
        this.lakeDepth = tag.getInt("lakeDepth");
        RegistryOps<Tag> tagRegistryOps = RegistryOps.create(NbtOps.INSTANCE, context.registryAccess());
        this.lakeFeatures = PlacedFeature.LIST_CODEC.decode(tagRegistryOps, tag.get("lake_features")).getOrThrow().getFirst();
    }

    @Override
    protected void addAdditionalSaveData(StructurePieceSerializationContext context, CompoundTag tag) {
        tag.put("origin", NbtUtils.writeBlockPos(this.origin));
        tag.putInt("radius", this.radius);
        tag.putInt("lakeDepth", this.lakeDepth);
        RegistryOps<Tag> tagRegistryOps = RegistryOps.create(NbtOps.INSTANCE, context.registryAccess());
        tag.put("lake_features", PlacedFeature.LIST_CODEC.encodeStart(tagRegistryOps, this.lakeFeatures).result().orElseThrow());
    }

    @Override
    public void postProcess(WorldGenLevel worldGenLevel, @NotNull StructureManager structureManager, @NotNull ChunkGenerator generator, @NotNull RandomSource random, @NotNull BoundingBox box, ChunkPos chunkPos, @NotNull BlockPos pos) {

        ImprovedNoise noiseSampler = new ImprovedNoise(new XoroshiroRandomSource(worldGenLevel.getSeed()));

        UnsafeBoundingBox unsafeBoundingBox = new UnsafeBoundingBox();
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();


        ChunkAccess chunk = worldGenLevel.getChunk(chunkPos.x, chunkPos.z);

        boolean[] placedWater = new boolean[16 * 16];

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int blockX = chunkPos.getBlockX(x);
                int blockZ = chunkPos.getBlockZ(z);

                mutableBlockPos.set(blockX, 0, blockZ);

                BlockStateProvider stateProvider = new NoiseProvider(worldGenLevel.getSeed(), worldGenLevel.registryAccess().lookup(Registries.NOISE).orElseThrow().getOrThrow(Noises.BADLANDS_SURFACE).value(), 0.5F, List.of(Blocks.GRAVEL.defaultBlockState(), Blocks.MUD.defaultBlockState(), Blocks.CLAY.defaultBlockState()));

                int worldSurfaceY = worldGenLevel.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, blockX, blockZ);
                double radiusFrequency = 0.05;
                double noise = noiseSampler.noise(blockX * radiusFrequency, 0, blockZ * radiusFrequency) + 1; // 0-2 range, no negatives

                double localRadius = (int) Mth.clampedLerp(radius * 0.5, radius, noise * 0.5F);

                int blendWidth = 43;

                int rimSize = (int) (localRadius * 0.05);

                int fluidDepth = 1;

                BlockState[] topBlocks = getSurfaceBlocks(mutableBlockPos, blockX, blockZ, worldSurfaceY, chunk);

                blendTerrainChecked(mutableBlockPos, localRadius, blendWidth, worldSurfaceY, blockX, blockZ, chunk, topBlocks, unsafeBoundingBox);

                placedWater[x + z * 16] = buildLakeChecked(worldGenLevel, random, mutableBlockPos, localRadius, blendWidth, rimSize, noiseSampler, fluidDepth, blockX, blockZ, chunk, stateProvider, topBlocks, unsafeBoundingBox);
            }
        }
        if (unsafeBoundingBox.valid()) {
            this.boundingBox = unsafeBoundingBox.toBoundingBox();
        }


        SectionPos sectionPos = SectionPos.of(chunkPos, worldGenLevel.getMinSectionY());
        BlockPos blockPos = sectionPos.origin();

        Predicate<BlockPos> test = blockPos1 -> {
            int localX = blockPos1.getX() & 15;
            int localZ = blockPos1.getZ() & 15;

            return placedWater[localX + localZ * 16];
        };

        for (Holder<PlacedFeature> lakeFeature : this.lakeFeatures) {
            placeWithContext(new PlacementContext(worldGenLevel, generator, Optional.empty()), random, blockPos, lakeFeature.value(), test);
        }
    }

    private boolean placeWithContext(PlacementContext context, RandomSource source, BlockPos pos, PlacedFeature feature, Predicate<BlockPos> placingPos) {
        Stream<BlockPos> stream = Stream.of(pos);

        for (PlacementModifier placementModifier : feature.placement()) {
            stream = stream.flatMap(blockPos -> placementModifier.getPositions(context, source, blockPos));
        }

        ConfiguredFeature<?, ?> configuredFeature = feature.feature().value();
        MutableBoolean mutableBoolean = new MutableBoolean();

        stream.forEach(blockPos -> {
            if (placingPos.test(blockPos)) {
                if (configuredFeature.place(context.getLevel(), context.generator(), source, blockPos)) {
                    mutableBoolean.setTrue();
                }
            }
        });

        return mutableBoolean.isTrue();
    }



    private boolean buildLakeChecked(WorldGenLevel worldGenLevel, RandomSource random, BlockPos.MutableBlockPos mutableBlockPos, double localRadius, int blendWidth, int rimSize, ImprovedNoise noiseSampler, int waterLevel, int blockX, int blockZ, ChunkAccess chunk, BlockStateProvider stateProvider, BlockState[] topBlocks, UnsafeBoundingBox unsafeBoundingBox) {
        if (mutableBlockPos.setY(origin.getY()).closerThan(origin, localRadius - blendWidth - rimSize)) {

           return buildLake(worldGenLevel, random, localRadius, blendWidth, rimSize, mutableBlockPos, noiseSampler, waterLevel, blockX, blockZ, chunk, stateProvider, topBlocks, unsafeBoundingBox);
        }
        return false;
    }

    private boolean buildLake(WorldGenLevel worldGenLevel, RandomSource random, double localRadius, int blendWidth, int rimSize, BlockPos.MutableBlockPos mutableBlockPos, ImprovedNoise noiseSampler, int fluidLevel, int blockX, int blockZ, ChunkAccess chunk, BlockStateProvider stateProvider, BlockState[] topBlocks, UnsafeBoundingBox unsafeBoundingBox) {
        boolean placedWater = false;

        double offset = localRadius - blendWidth - rimSize;
        double delta = (mutableBlockPos.setY(origin.getY()).distSqr(origin) - Mth.square(offset)) / Mth.square(localRadius - offset);

        float frequency = 0.05F;
        double depthNoise = ((noiseSampler.noise((mutableBlockPos.getX() + 100000) * frequency, 0, ((mutableBlockPos.getZ() + 100000) * frequency))) + 1) * 0.5;


        double depthOffset = Mth.clampedLerp(0, 10, depthNoise);
        int minGenY = origin.getY() - lakeDepth;

        int waterGenY = origin.getY() - fluidLevel;
        int depth = (int) Mth.clampedLerp(origin.getY(), minGenY - depthOffset, -delta);


        for (int y = origin.getY(); y >= depth - 1; y--) {
            mutableBlockPos.set(blockX, y, blockZ);

            if (y == depth - 1) {
                chunk.setBlockState(mutableBlockPos, Blocks.STONE.defaultBlockState(), false);
            } else if (y <= depth + 3) {

                if (y < waterGenY) {
                    chunk.setBlockState(mutableBlockPos, stateProvider.getState(random, mutableBlockPos), false);
                } else {
                    chunk.setBlockState(mutableBlockPos, topBlocks[Math.min(origin.getY() - y, topBlocks.length - 1)], false);
                    ((RandomTickScheduler) chunk).scheduleRandomTick(mutableBlockPos.immutable());
                    chunk.markPosForPostprocessing(mutableBlockPos);
                }

            } else if (y > waterGenY) {
                chunk.setBlockState(mutableBlockPos, Blocks.AIR.defaultBlockState(), false);
            } else {
                placedWater = true;
                chunk.setBlockState(mutableBlockPos, Blocks.WATER.defaultBlockState(), false);
                worldGenLevel.scheduleTick(mutableBlockPos.immutable(), Fluids.WATER, 0);
            }
            unsafeBoundingBox.encapsulate(mutableBlockPos);
        }
        return placedWater;
    }

    private void blendTerrainChecked(BlockPos.MutableBlockPos mutableBlockPos, double localRadius, int blendWidth, int worldSurfaceY, int blockX, int blockZ, ChunkAccess chunk, BlockState[] topBlocks, UnsafeBoundingBox unsafeBoundingBox) {
        if (mutableBlockPos.setY(origin.getY()).closerThan(origin, localRadius)) {
            blendTerrain(localRadius, blendWidth, mutableBlockPos, worldSurfaceY, blockX, blockZ, chunk, topBlocks, unsafeBoundingBox);
        }
    }

    private void blendTerrain(double localRadius, int blendWidth, BlockPos.MutableBlockPos mutableBlockPos, int worldSurfaceY, int blockX, int blockZ, ChunkAccess chunk, BlockState[] topBlocks, UnsafeBoundingBox unsafeBoundingBox) {
        double offset = localRadius - blendWidth;
        double delta = (mutableBlockPos.setY(origin.getY()).distSqr(origin) - Mth.square(offset)) / Mth.square(localRadius - offset);
        int height = (int) Mth.clampedLerp(origin.getY(), worldSurfaceY, delta);

        if (origin.getY() >= worldSurfaceY) {
            for (int y = worldSurfaceY; y <= height; y++) {
                mutableBlockPos.set(blockX, y, blockZ);
                chunk.setBlockState(mutableBlockPos, topBlocks[topBlocks.length  -1], false);
                unsafeBoundingBox.encapsulate(mutableBlockPos);
            }
        } else {
            for (int y = worldSurfaceY; y > height; y--) {
                mutableBlockPos.set(blockX, y, blockZ);
                chunk.setBlockState(mutableBlockPos, Blocks.AIR.defaultBlockState(), false);
                unsafeBoundingBox.encapsulate(mutableBlockPos);
            }
        }

        for (int y = 0; y < topBlocks.length; y++) {
            mutableBlockPos.set(blockX, height - y, blockZ);

            chunk.setBlockState(mutableBlockPos, topBlocks[y], false);

            ((RandomTickScheduler) chunk).scheduleRandomTick(mutableBlockPos.immutable());
            chunk.markPosForPostprocessing(mutableBlockPos);
        }
    }

    private static BlockState @NotNull [] getSurfaceBlocks(BlockPos.MutableBlockPos mutableBlockPos, int blockX, int blockZ, int worldSurfaceY, ChunkAccess chunk) {
        BlockState[] topBlocks = new BlockState[]{
                Blocks.GRASS_BLOCK.defaultBlockState(),
                Blocks.DIRT.defaultBlockState(),
                Blocks.DIRT.defaultBlockState(),
                Blocks.STONE.defaultBlockState(),
                Blocks.STONE.defaultBlockState()
        };


        for (int y = 0; y < topBlocks.length; y++) {
            mutableBlockPos.set(blockX, worldSurfaceY - y, blockZ);

            BlockState blockState = chunk.getBlockState(mutableBlockPos);

            if (blockState.isAir() || !blockState.getFluidState().isEmpty()) {
                continue;
            }

            topBlocks[y] = blockState;
        }
        return topBlocks;
    }
}
