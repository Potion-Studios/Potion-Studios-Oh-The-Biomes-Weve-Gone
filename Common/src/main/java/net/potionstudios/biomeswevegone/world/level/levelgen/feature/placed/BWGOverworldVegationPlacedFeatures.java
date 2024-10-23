package net.potionstudios.biomeswevegone.world.level.levelgen.feature.placed;

import net.minecraft.data.worldgen.features.CaveFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.configured.BWGOverworldVegetationConfiguredFeatures;

import java.util.List;
import java.util.OptionalInt;

public class BWGOverworldVegationPlacedFeatures {

    public static final ResourceKey<PlacedFeature> ROSE = createWorldSurfaceSquared("rose", BWGBlocks.ROSE.getFeature(), 1);
    public static final ResourceKey<PlacedFeature> BLACK_ROSE = createWorldSurfaceSquared("black_rose", BWGBlocks.BLACK_ROSE.getFeature(), 1);
    public static final ResourceKey<PlacedFeature> CYAN_ROSE = createWorldSurfaceSquared("cyan_rose", BWGBlocks.CYAN_ROSE.getFeature(), 1);
    public static final ResourceKey<PlacedFeature> OSIRIA_ROSE = createWorldSurfaceSquared("osiria_rose", BWGBlocks.OSIRIA_ROSE.getFeature(), 1);
    public static final ResourceKey<PlacedFeature> WINTER_ROSE = createWorldSurfaceSquared("winter_rose", BWGOverworldVegetationConfiguredFeatures.WINTER_ROSE, 2);
    public static final ResourceKey<PlacedFeature> BLUE_ROSE_BUSH = createWorldSurfaceSquared("blue_rose_bush", BWGOverworldVegetationConfiguredFeatures.BLUE_ROSE_BUSH, 1);
    public static final ResourceKey<PlacedFeature> ORANGE_DAISY = createWorldSurfaceSquared("orange_daisy", BWGBlocks.ORANGE_DAISY.getFeature(), 1);

    public static final ResourceKey<PlacedFeature> JAPANESE_ORCHID = createWorldSurfaceSquared("japanese_orchid", BWGOverworldVegetationConfiguredFeatures.JAPANESE_ORCHID, 1);

    public static final ResourceKey<PlacedFeature> CLOVER_PATCH = createWorldSurfaceSquared("clover_patch", BWGOverworldVegetationConfiguredFeatures.CLOVER_PATCH, 5);
    public static final ResourceKey<PlacedFeature> CLOVER_FLOWERS = createWorldSurfaceSquared("clover_flower_patch", BWGOverworldVegetationConfiguredFeatures.CLOVER_AND_FLOWERS, 2);
    public static final ResourceKey<PlacedFeature> LEAF_PILE = createWorldSurfaceSquared("leaf_pile", BWGOverworldVegetationConfiguredFeatures.LEAF_PILE, 2);
    public static final ResourceKey<PlacedFeature> FLOWER_PATCHES = createWorldSurfaceSquared("flower_patches", BWGOverworldVegetationConfiguredFeatures.FLOWER_PATCH, 5);
    public static final ResourceKey<PlacedFeature> SAKURA_PETALS = createWorldSurfaceSquared("sakura_petals", BWGOverworldVegetationConfiguredFeatures.SAKURA_PETALS, 9);


    public static final ResourceKey<PlacedFeature> ALLIUM_SHRUBLAND_FLOWERS = createWorldSurfaceSquared("allium_shrubland_flowers", BWGOverworldVegetationConfiguredFeatures.ALLIUM_SHRUBLAND_FLOWERS, 15);
    public static final ResourceKey<PlacedFeature> AMARANTH_GRASSLAND_FLOWERS = createWorldSurfaceSquared("amaranth_grassland_flowers", BWGOverworldVegetationConfiguredFeatures.AMARANTH_GRASSLAND_FLOWERS,15);
    public static final ResourceKey<PlacedFeature> ROSE_FIELD_FLOWERS = createWorldSurfaceSquared("rose_field_flowers", BWGOverworldVegetationConfiguredFeatures.ROSE_FIELD_FLOWERS, 25);

    public static final ResourceKey<PlacedFeature> BLUE_BERRY_BUSH = PlacedFeaturesUtil.createPlacedFeature("blue_berry_bush", BWGOverworldVegetationConfiguredFeatures.PATCH_BLUEBERRY, () -> List.of(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
    public static final ResourceKey<PlacedFeature> BLUE_BERRY_BUSH_LUSH = PlacedFeaturesUtil.createPlacedFeature("blue_berry_bush_lush", BWGOverworldVegetationConfiguredFeatures.PATCH_BLUEBERRY, () -> List.of(RarityFilter.onAverageOnceEvery(24), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));

    public static final ResourceKey<PlacedFeature> MUSHROOMS = PlacedFeaturesUtil.createPlacedFeature("mushrooms", BWGOverworldVegetationConfiguredFeatures.MUSHROOMS, () -> VegetationPlacements.getMushroomPlacement(6, null));

    public static final ResourceKey<PlacedFeature> PATCH_BEACH_GRASS_NOISE = PlacedFeaturesUtil.createPlacedFeature("patch_beach_grass_noise", BWGOverworldVegetationConfiguredFeatures.BEACH_GRASSES, () -> List.of(NoiseThresholdCountPlacement.of(-0.45D, 7, 0), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
    public static final ResourceKey<PlacedFeature> PATCH_BEACH_GRASS = PlacedFeaturesUtil.createPlacedFeature("patch_beach_grass", BWGOverworldVegetationConfiguredFeatures.BEACH_GRASSES, () -> List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
    public static final ResourceKey<PlacedFeature> PRAIRIE_GRASS = PlacedFeaturesUtil.createPlacedFeature("prairie_grass", BWGOverworldVegetationConfiguredFeatures.PRAIRIE_GRASS, () -> List.of(CountPlacement.of(25), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
    public static final ResourceKey<PlacedFeature> PRAIRIE_GRASS_BONEMEAL = PlacedFeaturesUtil.createPlacedFeature("prairie_grass_bonemeal", BWGOverworldVegetationConfiguredFeatures.SINGLE_PRAIRIE_GRASS, () -> List.of(new PlacementModifier[]{PlacementUtils.isEmpty()}));

    public static final ResourceKey<PlacedFeature> WINDSWEPT_DESERT_VEGETATION = createWorldSurfaceSquared("windswept_desert_vegetation", BWGOverworldVegetationConfiguredFeatures.WINDSWEPT_DESERT_VEGETATION, 8);
    public static final ResourceKey<PlacedFeature> MOJAVE_DESERT_VEGETATION = createWorldSurfaceSquared("mojave_desert_vegetation", BWGOverworldVegetationConfiguredFeatures.MOJAVE_DESERT_VEGETATION, 2);
    public static final ResourceKey<PlacedFeature> ATACAMA_OUTBACK_VEGETATION = createWorldSurfaceSquared("atacama_outback_vegetation", BWGOverworldVegetationConfiguredFeatures.ATACAMA_OUTBACK_VEGETATION, 6);
    public static final ResourceKey<PlacedFeature> RUGGED_BADLANDS_VEGETATION = createWorldSurfaceSquared("rugged_badlands_vegetation", BWGOverworldVegetationConfiguredFeatures.RUGGED_BADLANDS_VEGETATION, 6);

    public static final ResourceKey<PlacedFeature> CYAN_PITCHER_PLANT =  createWorldSurfaceSquared("cyan_pitcher_plant", BWGOverworldVegetationConfiguredFeatures.CYAN_PITCHER_PLANT, 1);
    public static final ResourceKey<PlacedFeature> MAGENTA_PITCHER_PLANT =  createWorldSurfaceSquared("magenta_pitcher_plant", BWGOverworldVegetationConfiguredFeatures.MAGENTA_PITCHER_PLANT, 1);

    public static final ResourceKey<PlacedFeature> DELPHINIUM =  createWorldSurfaceSquared("delphinium", BWGOverworldVegetationConfiguredFeatures.DELPHINIUM, 1);
    public static final ResourceKey<PlacedFeature> DELPHINIUM_PILLAR =  PlacedFeaturesUtil.createPlacedFeature("delphinium_pillar", BWGOverworldVegetationConfiguredFeatures.DELPHINIUM, () -> List.of(CountPlacement.of(25), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, SurfaceRelativeThresholdFilter.of(Heightmap.Types.WORLD_SURFACE_WG, 5, Integer.MAX_VALUE), BiomeFilter.biome()));
    public static final ResourceKey<PlacedFeature> FOXGLOVES =  createWorldSurfaceSquared("foxgloves", BWGOverworldVegetationConfiguredFeatures.FOXGLOVE, 1);
    public static final ResourceKey<PlacedFeature> CROCUS = createWorldSurfaceSquared("crocus", BWGBlocks.CROCUS.getFeature(), 1);
    public static final ResourceKey<PlacedFeature> FAIRY_SLIPPER = createWorldSurfaceSquared("fairy_slipper", BWGBlocks.FAIRY_SLIPPER.getFeature(), 1);
    public static final ResourceKey<PlacedFeature> PINK_ALLIUMS = createWorldSurfaceSquared("pink_alliums", BWGBlocks.PINK_ALLIUM.getFeature(), 2);
    public static final ResourceKey<PlacedFeature> WHITE_ALLIUMS = createWorldSurfaceSquared("white_alliums", BWGBlocks.WHITE_ALLIUM.getFeature(), 2);
    public static final ResourceKey<PlacedFeature> LOLLIPOP_FLOWERS = createWorldSurfaceSquared("lollipop_flowers", BWGBlocks.LOLLIPOP_FLOWER.getFeature(), 1);
    public static final ResourceKey<PlacedFeature> YELLOW_DAFFODIL = createWorldSurfaceSquared("yellow_daffodil", BWGBlocks.YELLOW_DAFFODIL.getFeature(), 1);
    public static final ResourceKey<PlacedFeature> DAFFODIL = createWorldSurfaceSquared("daffodil", BWGBlocks.DAFFODIL.getFeature(), 1);
    public static final ResourceKey<PlacedFeature> PINK_DAFFODIL = createWorldSurfaceSquared("pink_daffodil", BWGBlocks.PINK_DAFFODIL.getFeature(), 1);

    public static final ResourceKey<PlacedFeature> ANGELICA = createWorldSurfaceSquared("angelica", BWGBlocks.ANGELICA.getFeature(), 1);
    public static final ResourceKey<PlacedFeature> BISTORT = createWorldSurfaceSquared("bistort", BWGBlocks.BISTORT.getFeature(), 1);

    public static final ResourceKey<PlacedFeature> ANEMONES = createWorldSurfaceSquared("anemones", BWGOverworldVegetationConfiguredFeatures.ANEMONES, 1);
    public static final ResourceKey<PlacedFeature> TULIPS = createWorldSurfaceSquared("tulips", BWGOverworldVegetationConfiguredFeatures.TULIPS, 1);
    public static final ResourceKey<PlacedFeature> ALPINE_BELLFLOWER = createWorldSurfaceSquared("alpine_bellflower", BWGBlocks.ALPINE_BELLFLOWER.getFeature(), 1);
    public static final ResourceKey<PlacedFeature> IRIS = createWorldSurfaceSquared("iris", BWGBlocks.IRIS.getFeature(), 1);
    public static final ResourceKey<PlacedFeature> WINTER_SUCCULENT = createWorldSurfaceSquared("winter_succulent", BWGBlocks.WINTER_SUCCULENT.getFeature(), 2);
    public static final ResourceKey<PlacedFeature> WINTER_SCILLA = createWorldSurfaceSquared("winter_scilla", BWGOverworldVegetationConfiguredFeatures.WINTER_SCILLA, 2);
    public static final ResourceKey<PlacedFeature> WINTER_CYCLAMEN = createWorldSurfaceSquared("winter_cyclamen", BWGOverworldVegetationConfiguredFeatures.WINTER_CYCLAMEN, 1);

    public static final ResourceKey<PlacedFeature> SAGES = createWorldSurfaceSquared("sages", BWGOverworldVegetationConfiguredFeatures.SAGES, 1);
    public static final ResourceKey<PlacedFeature> JUNGLE_FLOWERS = createWorldSurfaceSquared("jungle_flowers", BWGOverworldVegetationConfiguredFeatures.JUNGLE_FLOWERS, 1);
    public static final ResourceKey<PlacedFeature> JUNGLE_FLOWERS_PILLAR = PlacedFeaturesUtil.createPlacedFeature("jungle_flowers_pillar", BWGOverworldVegetationConfiguredFeatures.JUNGLE_FLOWERS, () -> List.of(CountPlacement.of(25), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, SurfaceRelativeThresholdFilter.of(Heightmap.Types.WORLD_SURFACE_WG, 5, Integer.MAX_VALUE), BiomeFilter.biome()));
    public static final ResourceKey<PlacedFeature> CALIFORNIA_POPPY = createWorldSurfaceSquared("california_poppy", BWGBlocks.CALIFORNIA_POPPY.getFeature(), 1);
    public static final ResourceKey<PlacedFeature> KOVAN_FLOWER = createWorldSurfaceSquared("kovan_flower", BWGBlocks.KOVAN_FLOWER.getFeature(), 1);
    public static final ResourceKey<PlacedFeature> HORSEWEED = createWorldSurfaceSquared("horseweed", BWGBlocks.HORSEWEED.getFeature(), 2);
    public static final ResourceKey<PlacedFeature> LEATHER_FLOWERS = createWorldSurfaceSquared("leather_flowers", BWGOverworldVegetationConfiguredFeatures.LEATHER_FLOWERS, 2);
    public static final ResourceKey<PlacedFeature> WHITE_PUFFBALL = PlacedFeaturesUtil.createPlacedFeature("white_puffball", BWGOverworldVegetationConfiguredFeatures.WHITE_PUFFBALL, () -> VegetationPlacements.getMushroomPlacement(6, null));

    public static final ResourceKey<PlacedFeature> JACARANDA_BUSHES = PlacedFeaturesUtil.createPlacedFeature("jacaranda_bushes", BWGOverworldVegetationConfiguredFeatures.JACARANDA_BUSHES, () -> List.of(RarityFilter.onAverageOnceEvery(40), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
    public static final ResourceKey<PlacedFeature> HYDRANGEAS = createWorldSurfaceSquared("hydrangeas", BWGOverworldVegetationConfiguredFeatures.HYDRANGEAS, 2);
    public static final ResourceKey<PlacedFeature> SHRUB = createWorldSurfaceSquared("shrub", BWGOverworldVegetationConfiguredFeatures.SHRUB, 1);
    public static final ResourceKey<PlacedFeature> FIRECRACKER_BUSH = createWorldSurfaceSquared("firecracker_bush", BWGOverworldVegetationConfiguredFeatures.FIRECRACKER_BUSH, 3);

    public static final ResourceKey<PlacedFeature> SWAMP_WATER_VEGETATION = createWorldSurfaceSquared("swamp_water_vegetation", BWGOverworldVegetationConfiguredFeatures.SWAMP_WATER_VEGETATION, 20);
    public static final ResourceKey<PlacedFeature> MANGROVE_SWAMP_WATER_VEGETATION = createWorldSurfaceSquared("mangrove_swamp_water_vegetation", BWGOverworldVegetationConfiguredFeatures.MANGROVE_SWAMP_WATER_VEGETATION, 15);
    public static final ResourceKey<PlacedFeature> CATTAILS = PlacedFeaturesUtil.createPlacedFeature("cattails", BWGOverworldVegetationConfiguredFeatures.CATTAILS, PlacedFeaturesUtil.oceanFloorSquaredWithCountAndMaxDepth(16, OptionalInt.of(2)));

    public static final ResourceKey<PlacedFeature> IRONWOOD_PLATEAU_GLOW_LICHEN = PlacedFeaturesUtil.createPlacedFeature("ironwood_plateau_glow_lichen", CaveFeatures.GLOW_LICHEN, () -> List.of(CountPlacement.of(256), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(63), VerticalAnchor.absolute(140)), SurfaceRelativeThresholdFilter.of(Heightmap.Types.WORLD_SURFACE, Integer.MIN_VALUE, -1), SurfaceRelativeThresholdFilter.of(Heightmap.Types.WORLD_SURFACE_WG, 1, Integer.MAX_VALUE), BiomeFilter.biome()));


    public static final ResourceKey<PlacedFeature> IRONWOOD_PLATEAU_PATCH_GRASS_WORLD_SURFACE = PlacedFeaturesUtil.createPlacedFeature("ironwood_plateau_patch_grass_world_surface", VegetationFeatures.PATCH_GRASS, () -> List.of(CountPlacement.of(20), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, SurfaceRelativeThresholdFilter.of(Heightmap.Types.WORLD_SURFACE_WG, 5, Integer.MAX_VALUE), BiomeFilter.biome()));
    public static final ResourceKey<PlacedFeature> PATCH_GRASS_JUNGLE_WORLD_SURFACE = PlacedFeaturesUtil.createPlacedFeature("patch_grass_jungle_pillar", VegetationFeatures.PATCH_GRASS_JUNGLE, () -> List.of(CountPlacement.of(25), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, SurfaceRelativeThresholdFilter.of(Heightmap.Types.WORLD_SURFACE_WG, 5, Integer.MAX_VALUE), BiomeFilter.biome()));

    public static final ResourceKey<PlacedFeature> CRAG_BAMBOO = PlacedFeaturesUtil.createPlacedFeature("crag_bamboo", VegetationFeatures.BAMBOO_NO_PODZOL, () -> List.of(NoiseBasedCountPlacement.of(160, 80.0, 0.3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
    public static final ResourceKey<PlacedFeature> CRAG_LAKE_VEGETATION = PlacedFeaturesUtil.createPlacedFeature("crag_lake_vegetation", BWGOverworldVegetationConfiguredFeatures.WATER_VEGETATION, () -> List.of(CountPlacement.of(40), InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES), BiomeFilter.biome()));
    public static final ResourceKey<PlacedFeature> COLD_LAKE_VEGETATION = PlacedFeaturesUtil.createPlacedFeature("cold_lake_vegetation", BWGOverworldVegetationConfiguredFeatures.WATER_VEGETATION, () -> List.of(CountPlacement.of(40), InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES)));

    public static final ResourceKey<PlacedFeature> HUGE_MUSHROOMS = PlacedFeaturesUtil.createPlacedFeature("huge_mushrooms", BWGOverworldVegetationConfiguredFeatures.HUGE_MUSHROOMS, () -> List.of(CountPlacement.of(1), RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
    public static final ResourceKey<PlacedFeature> LARGE_PUMPKIN = PlacedFeaturesUtil.createPlacedFeature("large_pumpkin", BWGOverworldVegetationConfiguredFeatures.LARGE_PUMPKIN, () -> List.of(CountPlacement.of(5), RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
    public static final ResourceKey<PlacedFeature> MEDIUM_PUMPKIN = PlacedFeaturesUtil.createPlacedFeature("medium_pumpkin", BWGOverworldVegetationConfiguredFeatures.MEDIUM_PUMPKIN, () -> List.of(CountPlacement.of(6), RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
    public static final ResourceKey<PlacedFeature> SMALL_PUMPKIN = PlacedFeaturesUtil.createPlacedFeature("small_pumpkin", VegetationFeatures.PATCH_PUMPKIN, () -> List.of(CountPlacement.of(9), RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));

    private static ResourceKey<PlacedFeature> createWorldSurfaceSquared(String id, ResourceKey<ConfiguredFeature<?, ?>> feature, int count) {
        return  PlacedFeaturesUtil.createPlacedFeature(id, feature, () -> VegetationPlacements.worldSurfaceSquaredWithCount(count));
    }

    public static void init() {
        BiomesWeveGone.LOGGER.info("Creating and Registering Overworld Vegetation Placed Features");
    }
}
