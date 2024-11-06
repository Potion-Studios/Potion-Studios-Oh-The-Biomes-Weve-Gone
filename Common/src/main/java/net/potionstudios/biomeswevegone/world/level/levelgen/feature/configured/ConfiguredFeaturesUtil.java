package net.potionstudios.biomeswevegone.world.level.levelgen.feature.configured;

import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.placed.PlacedFeaturesUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class ConfiguredFeaturesUtil {

    public static final Map<ResourceKey<ConfiguredFeature<?, ?>>, ConfiguredFeatureFactory> CONFIGURED_FEATURES_FACTORIES = new Reference2ObjectOpenHashMap<>();

    @SafeVarargs
    public static RandomFeatureConfiguration createRandomWeightedConfiguredFeature(HolderGetter<ConfiguredFeature<?, ?>> lookup, ResourceKey<ConfiguredFeature<?, ?>>... configuredFeatures) {
        int count = configuredFeatures.length;
        float weight = 1.0F / count;

        ArrayList<WeightedPlacedFeature> wightedPlacedFeatureList = new ArrayList<>();
        for (int i = 0; i < count - 1; i++)
            wightedPlacedFeatureList.add(
                    new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(configuredFeatures[i])), weight)
            );

        return new RandomFeatureConfiguration(wightedPlacedFeatureList, PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(configuredFeatures[count - 1])));
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> ResourceKey<ConfiguredFeature<?, ?>> createConfiguredFeature(String id, F feature, Function<BootstrapContext<ConfiguredFeature<?, ?>>, ? extends FC> config) {
        ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureResourceKey = registerKey(id);

        CONFIGURED_FEATURES_FACTORIES.put(configuredFeatureResourceKey, configuredFeatureHolderGetter -> new ConfiguredFeature<>(feature, config.apply(configuredFeatureHolderGetter)));

        return configuredFeatureResourceKey;
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> ResourceKey<ConfiguredFeature<?, ?>> createConfiguredFeature(String id, Supplier<? extends F> feature, Supplier<? extends FC> config) {
        ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureResourceKey = registerKey(id);

        CONFIGURED_FEATURES_FACTORIES.put(configuredFeatureResourceKey, configuredFeatureHolderGetter -> new ConfiguredFeature<>(feature.get(), config.get()));

        return configuredFeatureResourceKey;
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> ResourceKey<ConfiguredFeature<?, ?>> createConfiguredFeature(String id, F feature, Supplier<? extends FC> config) {
        ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureResourceKey = registerKey(id);

        CONFIGURED_FEATURES_FACTORIES.put(configuredFeatureResourceKey, configuredFeatureHolderGetter -> new ConfiguredFeature<>(feature, config.get()));

        return configuredFeatureResourceKey;
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<?, ?>> createConfiguredFeature(F feature, Supplier<? extends FC> config) {
        return Holder.direct(new ConfiguredFeature<>(feature, config.get()));
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<?, ?>> createConfiguredFeature(F feature, FC config) {
        return Holder.direct(new ConfiguredFeature<>(feature, config));
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<?, ?>> createConfiguredFeature(Supplier<F> feature, Supplier<FC> config) {
        return Holder.direct(new ConfiguredFeature<>(feature.get(), config.get()));
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> ResourceKey<ConfiguredFeature<?, ?>> createFlowerConfiguredFeature(String id, Supplier<? extends Block> flowerBlock) {
        return createConfiguredFeature(id, Feature.FLOWER, (configuredFeatureBootstrapContext) -> VegetationFeatures.grassPatch(SimpleStateProvider.simple(flowerBlock.get().defaultBlockState()), 15));
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> ResourceKey<ConfiguredFeature<?, ?>> createPatchConfiguredFeatureWithBlock(String id, Supplier<? extends Block> block, int tries) {
        return createPatchConfiguredFeatureWithState(id, () -> block.get().defaultBlockState(), tries);
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createPatchConfiguredFeatureWithState(String id, Supplier<BlockState> state, int tries) {
        return createConfiguredFeature(id, Feature.RANDOM_PATCH, (configuredFeatureBootstrapContext) -> FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(state.get())), List.of(), tries));
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<?, ?>> createPatchConfiguredFeatureWithState(Block block, int tries) {
        return Holder.direct(new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(block)), List.of(), tries)));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createSimpleBlockConfiguredFeatureWithBlock(String id, Supplier<Block> block) {
        return createSimpleBlockConfiguredFeatureWithState(id, () -> block.get().defaultBlockState());
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createSimpleBlockConfiguredFeatureWithState(String id, Supplier<BlockState> state) {
        return createConfiguredFeature(id, Feature.SIMPLE_BLOCK, (configuredFeatureBootstrapContext) -> new SimpleBlockConfiguration(BlockStateProvider.simple(state.get())));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return BiomesWeveGone.key(Registries.CONFIGURED_FEATURE, name);
    }

    @FunctionalInterface
    public interface ConfiguredFeatureFactory {
        ConfiguredFeature<?, ?> generate(BootstrapContext<ConfiguredFeature<?, ?>> configuredFeatureHolderGetter);
    }
}
