package net.potionstudios.biomeswevegone.world.level.levelgen.feature.placed;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.potionstudios.biomeswevegone.BiomesWeveGone;

import java.util.*;
import java.util.function.Supplier;

import static net.minecraft.data.worldgen.placement.VegetationPlacements.treePlacement;

public class PlacedFeaturesUtil {
    public static final Map<ResourceKey<PlacedFeature>, PlacedFeatureFactory> PLACED_FEATURE_FACTORIES = new Reference2ObjectOpenHashMap<>();

    private static final NoiseThresholdCountPlacement CLEARING_NOISE = NoiseThresholdCountPlacement.of(0.545, 1, 0);

    protected static Supplier<List<PlacementModifier>> clearingTreePlacement(PlacementModifier placementModifier) {
        List<PlacementModifier> placementModifiers = new ArrayList<>(treePlacement(placementModifier));
        placementModifiers.add(CLEARING_NOISE);
        return () -> placementModifiers;
    }

    public static Supplier<List<PlacementModifier>> treePlacementBaseOceanFloor(PlacementModifier... $$0) {
        return treePlacementBaseOceanFloor(OptionalInt.empty(), $$0);
    }

    public static Supplier<List<PlacementModifier>> treePlacementBaseOceanFloor(OptionalInt maxDepth, PlacementModifier... $$0) {
        ImmutableList.Builder<PlacementModifier> placementModifierBuilder = ImmutableList.<PlacementModifier>builder().add($$0).add(InSquarePlacement.spread()).add(PlacementUtils.HEIGHTMAP_TOP_SOLID).add(BiomeFilter.biome());
        if (maxDepth.isPresent())
            placementModifierBuilder.add(SurfaceWaterDepthFilter.forMaxDepth(maxDepth.getAsInt()));
        return placementModifierBuilder::build;
    }

    public static Supplier<List<PlacementModifier>> oceanFloorSquaredWithCount(int $$0, PlacementModifier... modifiers) {
        return oceanFloorSquaredWithCountAndMaxDepth($$0, OptionalInt.empty(), modifiers);
    }

    public static Supplier<List<PlacementModifier>> oceanFloorSquaredWithCountAndMaxDepth(int $$0, OptionalInt maxDepth, PlacementModifier... modifiers) {
        ArrayList<PlacementModifier> placementModifiers = new ArrayList<>(List.of(CountPlacement.of($$0), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
        if (maxDepth.isPresent())
            placementModifiers.add(SurfaceWaterDepthFilter.forMaxDepth(maxDepth.getAsInt()));
        placementModifiers.addAll(Arrays.asList(modifiers));
        return () -> placementModifiers;
    }

    public static <FC extends FeatureConfiguration> Holder<PlacedFeature> createPlacedFeatureDirect(Holder<ConfiguredFeature<?, ?>> feature, PlacementModifier... placementModifiers) {
        return createPlacedFeatureDirect(feature, List.of(placementModifiers));
    }

    public static <FC extends FeatureConfiguration> Holder<PlacedFeature> createPlacedFeatureDirect(Holder<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> placementModifiers) {
        return Holder.direct(new PlacedFeature(feature, placementModifiers));
    }

    protected static <FC extends FeatureConfiguration> ResourceKey<PlacedFeature> createPlacedFeature(String id, ResourceKey<ConfiguredFeature<?, ?>> feature, Supplier<List<PlacementModifier>> placementModifiers) {
        ResourceKey<PlacedFeature> placedFeatureKey = registerKey(id);
        PLACED_FEATURE_FACTORIES.put(placedFeatureKey, configuredFeatureHolderGetter -> new PlacedFeature(configuredFeatureHolderGetter.getOrThrow(feature), placementModifiers.get()));
        return placedFeatureKey;
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return BiomesWeveGone.key(Registries.PLACED_FEATURE, name);
    }

    @FunctionalInterface
    public interface PlacedFeatureFactory {
        PlacedFeature generate(HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureHolderGetter);
    }
}
