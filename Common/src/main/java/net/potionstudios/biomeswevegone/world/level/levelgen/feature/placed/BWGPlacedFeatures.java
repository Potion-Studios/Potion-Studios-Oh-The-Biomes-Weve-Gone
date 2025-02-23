package net.potionstudios.biomeswevegone.world.level.levelgen.feature.placed;

import com.google.common.collect.ImmutableList;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.placement.*;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.configured.BWGConfiguredFeatures;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.configured.BWGOverworldConfiguredFeatures;

import java.util.Collections;
import java.util.List;

public class BWGPlacedFeatures {

    public static final ResourceKey<PlacedFeature> MOSSY_STONE_BOULDER = PlacedFeaturesUtil.createPlacedFeature("mossy_stone_boulder", BWGConfiguredFeatures.MOSSY_STONE_BOULDER, () -> List.of(CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final ResourceKey<PlacedFeature> ROCKY_STONE_BOULDER = PlacedFeaturesUtil.createPlacedFeature("rocky_stone_boulder", BWGConfiguredFeatures.ROCKY_STONE_BOULDER, () -> List.of(CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final ResourceKey<PlacedFeature> ORANGE_TERRACOTTA_BOULDER = PlacedFeaturesUtil.createPlacedFeature("orange_terracotta_boulder", BWGConfiguredFeatures.ORANGE_TERRACOTTA_BOULDER, () -> List.of(CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final ResourceKey<PlacedFeature> BOREALIS_ICE_SHARPENED_SPIKE = PlacedFeaturesUtil.createPlacedFeature("borealis_ice_sharpened_spike", BWGOverworldConfiguredFeatures.BOREALIS_ICE_SHARPENED_SPIKE, () -> List.of(CountPlacement.of(1), RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
    public static final ResourceKey<PlacedFeature> LUSH_ROUNDED_ROCK = PlacedFeaturesUtil.createPlacedFeature("lush_rounded_rock", BWGOverworldConfiguredFeatures.LUSH_ROUNDED_ROCK, () -> List.of(CountPlacement.of(1), RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
    public static final ResourceKey<PlacedFeature> DRIPSTONE_ROUNDED_ROCK = PlacedFeaturesUtil.createPlacedFeature("dripstone_rounded_rock", BWGOverworldConfiguredFeatures.DRIPSTONE_ROUNDED_ROCK, () -> List.of(CountPlacement.of(1), RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
    public static final ResourceKey<PlacedFeature> WINDSWEPT_BOULDER = PlacedFeaturesUtil.createPlacedFeature("windswept_boulder", BWGOverworldConfiguredFeatures.WINDSWEPT_BOULDER, () -> List.of(CountPlacement.of(1), RarityFilter.onAverageOnceEvery(10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
    public static final ResourceKey<PlacedFeature> BOULDER = PlacedFeaturesUtil.createPlacedFeature("stone_boulder", BWGOverworldConfiguredFeatures.STONE_BOULDER, () -> List.of(CountPlacement.of(1), RarityFilter.onAverageOnceEvery(10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
    public static final ResourceKey<PlacedFeature> VINE_PROCESSOR = PlacedFeaturesUtil.createPlacedFeature("vine_processor", BWGOverworldConfiguredFeatures.VINE_PROCESSOR, List::of);
    public static final ResourceKey<PlacedFeature> LUSH_BLOCKS_PROCESSOR = PlacedFeaturesUtil.createPlacedFeature("lush_blocks_processor", BWGOverworldConfiguredFeatures.LUSH_BLOCKS_PROCESSOR, List::of);
    public static final ResourceKey<PlacedFeature> CRAG_LAKE = PlacedFeaturesUtil.createPlacedFeature("crag_lake", BWGOverworldConfiguredFeatures.CRAG_LAKE, List::of);
    public static final ResourceKey<PlacedFeature> STONE_PILLAR = PlacedFeaturesUtil.createPlacedFeature("jungle_pillar", BWGOverworldConfiguredFeatures.JUNGLE_PILLAR, () -> List.of(InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(5), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    //public static final ResourceKey<PlacedFeature> BLACK_ICE_SNOW = PlacedFeaturesUtil.createPlacedFeature("black_ice_snow", BWGConfiguredFeatures.BLACK_ICE, () -> ChunkCoveringPlacement.INSTANCE, () -> PlacementUtils.HEIGHTMAP_WORLD_SURFACE, () -> RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter::biome, () -> BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(BlockPos.ZERO, Blocks.WATER)), () -> BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(new BlockPos(0, 1, 0), Blocks.AIR)));
    public static final ResourceKey<PlacedFeature> DISK_MUD = PlacedFeaturesUtil.createPlacedFeature("disk_mud", BWGConfiguredFeatures.DISK_MUD, () -> ImmutableList.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
    public static final ResourceKey<PlacedFeature> BASALT_DELTA = PlacedFeaturesUtil.createPlacedFeature("basalt_delta", BWGConfiguredFeatures.BASALT_DELTA, () -> ImmutableList.of(CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
    public static final ResourceKey<PlacedFeature> LARGE_BASALT_COLUMN = PlacedFeaturesUtil.createPlacedFeature("large_basalt_column", BWGConfiguredFeatures.LARGE_BASALT_COLUMN, () -> ImmutableList.of(CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
    public static final ResourceKey<PlacedFeature> SMALL_BASALT_COLUMN = PlacedFeaturesUtil.createPlacedFeature("small_basalt_column", BWGConfiguredFeatures.SMALL_BASALT_COLUMN, () -> ImmutableList.of(CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
    public static final ResourceKey<PlacedFeature> SWAMP_GRASS_BLOCK_DELTA = PlacedFeaturesUtil.createPlacedFeature("swamp_grass_block_delta", BWGConfiguredFeatures.SWAMP_GRASS_BLOCK_DELTA, PlacedFeaturesUtil.oceanFloorSquaredWithCount(1, RandomOffsetPlacement.vertical(ConstantInt.of(-1))));
    public static final ResourceKey<PlacedFeature> BOREALIS_ICE_FREEZE_TOP_LAYER = PlacedFeaturesUtil.createPlacedFeature("borealis_ice_freeze_top_layer", BWGOverworldConfiguredFeatures.BOREALIS_ICE_FREEZE_TOP_LAYER,() -> Collections.singletonList(BiomeFilter.biome()));

    public static void placedFeatures() {
        BiomesWeveGone.LOGGER.info("Registering Oh The Biomes We've Gone Placed Features");
        BWGOverworldTreePlacedFeatures.init();
        BWGOverworldVegationPlacedFeatures.init();
        BWGVanillaPlacedFeatures.init();
    }

}
