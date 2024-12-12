package net.potionstudios.biomeswevegone.world.level.levelgen.feature.placed;

import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ClampedInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.placement.*;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.configured.BWGVanillaConfiguredFeatures;

import java.util.List;

public class BWGVanillaPlacedFeatures {

    public static final ResourceKey<PlacedFeature> FLOWER_DEFAULT = PlacedFeaturesUtil.createPlacedFeature("vanilla/flower_default",
            BWGVanillaConfiguredFeatures.FLOWER_DEFAULT,
            () -> List.of(
                    RarityFilter.onAverageOnceEvery(32),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP,
                    BiomeFilter.biome()
            )
    );

    public static final ResourceKey<PlacedFeature> FLOWER_WARM = PlacedFeaturesUtil.createPlacedFeature("vanilla/flower_warm",
            BWGVanillaConfiguredFeatures.FLOWER_DEFAULT,
            () -> List.of(
                    RarityFilter.onAverageOnceEvery(16),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP,
                    BiomeFilter.biome()
            )
    );

    public static final ResourceKey<PlacedFeature> FLOWER_PLAINS = PlacedFeaturesUtil.createPlacedFeature("vanilla/flower_plains",
            BWGVanillaConfiguredFeatures.FLOWER_PLAINS,
            () -> List.of(
                    NoiseThresholdCountPlacement.of(-0.8, 15, 4),
                    RarityFilter.onAverageOnceEvery(32),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP,
                    BiomeFilter.biome()
            )
    );

    public static final ResourceKey<PlacedFeature> FOREST_FLOWERS = PlacedFeaturesUtil.createPlacedFeature("vanilla/forest_flowers",
            BWGVanillaConfiguredFeatures.FOREST_FLOWERS,
            () -> List.of(
                    RarityFilter.onAverageOnceEvery(7),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP,
                    CountPlacement.of(ClampedInt.of(UniformInt.of(-3, 1), 0, 1)),
                    BiomeFilter.biome()
            )
    );

    public static void init() {
        BiomesWeveGone.LOGGER.info("Creating and Registering Vanilla Placed Features");
    }
}
