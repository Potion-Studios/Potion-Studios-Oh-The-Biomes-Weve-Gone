package net.potionstudios.biomeswevegone.world.level.levelgen.feature.configured;

import com.google.common.collect.ImmutableList;
import dev.corgitaco.ohthetreesyoullgrow.world.level.levelgen.feature.TYGFeatures;
import dev.corgitaco.ohthetreesyoullgrow.world.level.levelgen.feature.configurations.TreeFromStructureNBTConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.material.Fluids;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.block.plants.bush.BWGBerryBush;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.BWGFeatures;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.placed.PlacedFeaturesUtil;

import java.util.List;
import java.util.function.Supplier;

public class BWGOverworldVegetationConfiguredFeatures {
    private static final BlockPredicateFilter SAND_FILTER = BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(BlockPos.ZERO.below(), BlockTags.SAND));
    private static final BlockPredicateFilter ON_WATER_FILTER = BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(BlockPos.ZERO.below(), Fluids.WATER));
    private static final BlockPredicateFilter IN_REPLACEABLE_FILTER = BlockPredicateFilter.forPredicate(BlockPredicate.replaceable());

    public static final ResourceKey<ConfiguredFeature<?, ?>> ROSE_BUSH = createPatchConfiguredFeature("rose_bush", () -> Blocks.ROSE_BUSH, 5);
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_ROSE_BUSH = createPatchConfiguredFeature("blue_rose_bush", BWGBlocks.BLUE_ROSE_BUSH, 5);
    public static final ResourceKey<ConfiguredFeature<?, ?>> WINTER_ROSE = createFlowerConfiguredFeature("winter_rose", () -> BWGBlocks.WINTER_ROSE.getBlockSupplier().get());
    public static final ResourceKey<ConfiguredFeature<?, ?>> WINTER_SCILLA = createFlowerConfiguredFeature("winter_scilla", () -> BWGBlocks.WINTER_SCILLA.getBlockSupplier().get());
    public static final ResourceKey<ConfiguredFeature<?, ?>> WINTER_CYCLAMEN = createFlowerConfiguredFeature("winter_cyclamen", () -> BWGBlocks.WINTER_CYCLAMEN.getBlockSupplier().get());
    public static final ResourceKey<ConfiguredFeature<?, ?>> SNOWDROPS = createFlowerConfiguredFeature("snowdrops", () -> BWGBlocks.SNOWDROPS.getBlockSupplier().get());
    public static final ResourceKey<ConfiguredFeature<?, ?>> PROTEA_FLOWER = createFlowerConfiguredFeature("protea_flower", () -> BWGBlocks.PROTEA_FLOWER.getBlockSupplier().get());


    public static final ResourceKey<ConfiguredFeature<?, ?>> ROSE_FIELD_FLOWERS = ConfiguredFeaturesUtil.createConfiguredFeature("rose_field_flowers",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstapContext.lookup(Registries.CONFIGURED_FEATURE);

                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BWGBlocks.OSIRIA_ROSE.getFeature())), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BLUE_ROSE_BUSH)), 0.3F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BWGBlocks.ROSE.getFeature())), 0.25F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ROSE_BUSH)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> TULIPS = ConfiguredFeaturesUtil.createConfiguredFeature("tulips",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstapContext) -> ConfiguredFeaturesUtil.createRandomWeightedConfiguredFeature(configuredFeatureBootstapContext.lookup(Registries.CONFIGURED_FEATURE), BWGBlocks.MAGENTA_TULIP.getFeature(), BWGBlocks.PURPLE_TULIP.getFeature(), BWGBlocks.YELLOW_TULIP.getFeature(), BWGBlocks.GREEN_TULIP.getFeature(), BWGBlocks.CYAN_TULIP.getFeature())
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> JAPANESE_ORCHID = createFlowerConfiguredFeature("japanese_orchid", BWGBlocks.JAPANESE_ORCHID);

    public static final ResourceKey<ConfiguredFeature<?, ?>> CLOVER_PATCH = createPatchConfiguredFeature("clover_patch", BWGBlocks.CLOVER_PATCH, 5);
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_PATCH = createFlowerConfiguredFeature("flower_patch", BWGBlocks.FLOWER_PATCH);
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEAF_PILE = createPatchConfiguredFeature("leaf_pile", BWGBlocks.LEAF_PILE, 15);

    public static final ResourceKey<ConfiguredFeature<?, ?>> CLOVER_AND_FLOWERS = ConfiguredFeaturesUtil.createConfiguredFeature("clovers_and_flowers",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstapContext) -> ConfiguredFeaturesUtil.createRandomWeightedConfiguredFeature(configuredFeatureBootstapContext.lookup(Registries.CONFIGURED_FEATURE), FLOWER_PATCH, CLOVER_PATCH)
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_SAKURA_PETALS = ConfiguredFeaturesUtil.createConfiguredFeature("white_sakura_petals", Feature.FLOWER, () -> new RandomPatchConfiguration(96, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(createPetalBlockStateList(BWGBlocks.WHITE_SAKURA_PETALS))))));
    public static final ResourceKey<ConfiguredFeature<?, ?>> YELLOW_SAKURA_PETALS = ConfiguredFeaturesUtil.createConfiguredFeature("yellow_sakura_petals", Feature.FLOWER, () -> new RandomPatchConfiguration(96, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(createPetalBlockStateList(BWGBlocks.YELLOW_SAKURA_PETALS))))));

    public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_PETALS = ConfiguredFeaturesUtil.createConfiguredFeature("sakura_petals",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstapContext) -> ConfiguredFeaturesUtil.createRandomWeightedConfiguredFeature(configuredFeatureBootstapContext.lookup(Registries.CONFIGURED_FEATURE), WHITE_SAKURA_PETALS, YELLOW_SAKURA_PETALS)
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> ALLIUM = createFlowerConfiguredFeature("allium", () -> Blocks.ALLIUM);
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALLIUM_TALL_BUSH = createPatchConfiguredFeature("tall_allium_bush", BWGBlocks.TALL_ALLIUM, 15);
    public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_PINK_ALLIUM_BUSH = createPatchConfiguredFeature("tall_pink_allium_bush", BWGBlocks.TALL_PINK_ALLIUM, 15);
    public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_WHITE_ALLIUM_BUSH = createPatchConfiguredFeature("tall_white_allium_bush", BWGBlocks.TALL_WHITE_ALLIUM, 15);

    public static final ResourceKey<ConfiguredFeature<?, ?>> ALLIUM_SHRUBLAND_FLOWERS = ConfiguredFeaturesUtil.createConfiguredFeature("allium_shrubland_flowers",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstapContext.lookup(Registries.CONFIGURED_FEATURE);

                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ALLIUM_TALL_BUSH)), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(TALL_PINK_ALLIUM_BUSH)), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(TALL_WHITE_ALLIUM_BUSH)), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ALLIUM)), 0.1F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BWGBlocks.ALLIUM_FLOWER_BUSH.getFeature())), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BWGBlocks.PINK_ALLIUM.getFeature())), 0.1F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BWGBlocks.PINK_ALLIUM_FLOWER_BUSH.getFeature())), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BWGBlocks.WHITE_ALLIUM.getFeature())), 0.1F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BWGBlocks.WHITE_ALLIUM_FLOWER_BUSH.getFeature())));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> AMARANTH_GRASSLAND_FLOWERS = ConfiguredFeaturesUtil.createConfiguredFeature("amaranth_grassland_flowers",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstapContext) -> ConfiguredFeaturesUtil.createRandomWeightedConfiguredFeature(configuredFeatureBootstapContext.lookup(Registries.CONFIGURED_FEATURE), BWGBlocks.AMARANTH.getFeature(), BWGBlocks.MAGENTA_AMARANTH.getFeature(), BWGBlocks.ORANGE_AMARANTH.getFeature(), BWGBlocks.PURPLE_AMARANTH.getFeature(), BWGBlocks.CYAN_AMARANTH.getFeature())
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_BLUEBERRY = createPatchConfiguredFeatureState("blue_berry_bush", () -> BWGBlocks.BLUEBERRY_BUSH.get().defaultBlockState().setValue(BWGBerryBush.AGE, 3), 32);

    public static final ResourceKey<ConfiguredFeature<?, ?>> JACARANDA_BUSH = createPatchConfiguredFeature("jacaranda_bush", () -> BWGBlocks.JACARANDA_BUSH.getBlockSupplier().get(), 32);
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWERING_JACARANDA_BUSH = createPatchConfiguredFeature("flowering_jacaranda_bush", BWGBlocks.FLOWERING_JACARANDA_BUSH, 32);
    public static final ResourceKey<ConfiguredFeature<?, ?>> INDIGO_JACARANDA_BUSH = createPatchConfiguredFeature("indigo_jacaranda_bush", () -> BWGBlocks.INDIGO_JACARANDA_BUSH.getBlockSupplier().get(), 32);
    public static final ResourceKey<ConfiguredFeature<? ,?>> FLOWERING_INDIGO_JACARANDA_BUSH = createPatchConfiguredFeature("flowering_indigo_jacaranda_bush", BWGBlocks.FLOWERING_INDIGO_JACARANDA_BUSH, 32);

    public static final ResourceKey<ConfiguredFeature<?, ?>> JACARANDA_BUSHES = ConfiguredFeaturesUtil.createConfiguredFeature("jacaranda_bushes",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstapContext) -> ConfiguredFeaturesUtil.createRandomWeightedConfiguredFeature(
                    configuredFeatureBootstapContext.lookup(Registries.CONFIGURED_FEATURE), JACARANDA_BUSH, FLOWERING_JACARANDA_BUSH, INDIGO_JACARANDA_BUSH, FLOWERING_INDIGO_JACARANDA_BUSH)
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> HYDRANGEA_BUSH = createFlowerConfiguredFeature("hydrangea_bush", () -> BWGBlocks.HYDRANGEA_BUSH.getBlockSupplier().get());
    public static final ResourceKey<ConfiguredFeature<?, ?>> HYDRANGEA_HEDGE = createFlowerConfiguredFeature("hydrangea_hedge", BWGBlocks.HYDRANGEA_HEDGE);
    public static final ResourceKey<ConfiguredFeature<?, ?>> SHRUB = createPatchConfiguredFeature("shrub", () -> BWGBlocks.SHRUB.getBlockSupplier().get(), 4);

    public static final ResourceKey<ConfiguredFeature<?, ?>> HYDRANGEAS = ConfiguredFeaturesUtil.createConfiguredFeature("hydrangeas",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstapContext) -> ConfiguredFeaturesUtil.createRandomWeightedConfiguredFeature(configuredFeatureBootstapContext.lookup(Registries.CONFIGURED_FEATURE), HYDRANGEA_BUSH, HYDRANGEA_HEDGE)
    );

    public static final ResourceKey<ConfiguredFeature<? ,?>> GREEN_MUSHROOM = createFlowerConfiguredFeature("green_mushroom", BWGBlocks.GREEN_MUSHROOM);
    public static final ResourceKey<ConfiguredFeature<?, ?>> WEEPING_MILKCAP = createFlowerConfiguredFeature("weeping_milkcap", BWGBlocks.WEEPING_MILKCAP);
    public static final ResourceKey<ConfiguredFeature<?, ?>> WOOD_BLEWIT = createFlowerConfiguredFeature("wood_blewit", BWGBlocks.WOOD_BLEWIT);

    public static final ResourceKey<ConfiguredFeature<?, ?>> MUSHROOMS = ConfiguredFeaturesUtil.createConfiguredFeature("mushrooms",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstapContext) -> ConfiguredFeaturesUtil.createRandomWeightedConfiguredFeature(configuredFeatureBootstapContext.lookup(Registries.CONFIGURED_FEATURE), WOOD_BLEWIT, WEEPING_MILKCAP, GREEN_MUSHROOM)
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> BEACH_GRASS = ConfiguredFeaturesUtil.createPatchConfiguredFeatureWithBlock("beach_grass_patch", BWGBlocks.BEACH_GRASS, 32);
    public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_BEACH_GRASS_PATCH = ConfiguredFeaturesUtil.createPatchConfiguredFeatureWithBlock("tall_beach_grass_patch", BWGBlocks.TALL_BEACH_GRASS, 32);

    public static final ResourceKey<ConfiguredFeature<?, ?>> BEACH_GRASSES = ConfiguredFeaturesUtil.createConfiguredFeature("beach_grasses",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstapContext.lookup(Registries.CONFIGURED_FEATURE);

                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(TALL_BEACH_GRASS_PATCH), SAND_FILTER), 0.33F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BEACH_GRASS), SAND_FILTER)
                );
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SINGLE_PRAIRIE_GRASS = ConfiguredFeaturesUtil.createConfiguredFeature("single_prairie_grass", Feature.SIMPLE_BLOCK, () -> new SimpleBlockConfiguration(BlockStateProvider.simple(BWGBlocks.PRAIRIE_GRASS.get().defaultBlockState())));
    public static final ResourceKey<ConfiguredFeature<?, ?>> PRAIRIE_GRASS_PATCH = ConfiguredFeaturesUtil.createPatchConfiguredFeatureWithBlock("prairie_grass_patch", BWGBlocks.PRAIRIE_GRASS, 100);
    public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_PRAIRIE_GRASS_PATCH = ConfiguredFeaturesUtil.createPatchConfiguredFeatureWithBlock("tall_prairie_grass_patch", BWGBlocks.TALL_PRAIRIE_GRASS, 100);

    public static final ResourceKey<ConfiguredFeature<?, ?>> PRAIRIE_GRASS = ConfiguredFeaturesUtil.createConfiguredFeature("prairie_grass",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstapContext.lookup(Registries.CONFIGURED_FEATURE);

                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(PRAIRIE_GRASS_PATCH)), 0.75F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(TALL_PRAIRIE_GRASS_PATCH)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> FOXGLOVE = createFlowerConfiguredFeature("foxglove", BWGBlocks.FOXGLOVE);
    public static final ResourceKey<ConfiguredFeature<?, ?>> DELPHINIUM = createFlowerConfiguredFeature("delphinium", BWGBlocks.DELPHINIUM);
    public static final ResourceKey<ConfiguredFeature<?, ?>> CYAN_PITCHER_PLANT = createFlowerConfiguredFeature("cyan_pitcher_plant", BWGBlocks.CYAN_PITCHER_PLANT);
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAGENTA_PITCHER_PLANT = createFlowerConfiguredFeature("magenta_pitcher_plant", BWGBlocks.MAGENTA_PITCHER_PLANT);

    public static final ResourceKey<ConfiguredFeature<?, ?>> MINI_CACTI = ConfiguredFeaturesUtil.createPatchConfiguredFeatureWithBlock("mini_cacti", () -> BWGBlocks.MINI_CACTUS.getBlockSupplier().get(), 4);
    public static final ResourceKey<ConfiguredFeature<?, ?>> PRICKLY_PEAR_CACTI = ConfiguredFeaturesUtil.createPatchConfiguredFeatureWithBlock("prickly_pear_cacti", () -> BWGBlocks.PRICKLY_PEAR_CACTUS.getBlockSupplier().get(), 5);
    public static final ResourceKey<ConfiguredFeature<?, ?>> GOLDEN_SPINED_CACTI = ConfiguredFeaturesUtil.createPatchConfiguredFeatureWithBlock("golden_spined_cacti", () -> BWGBlocks.GOLDEN_SPINED_CACTUS.getBlockSupplier().get(), 5);
    public static final ResourceKey<ConfiguredFeature<?, ?>> BARREL_CACTI = ConfiguredFeaturesUtil.createPatchConfiguredFeatureWithBlock("barrel_cacti", BWGBlocks.BARREL_CACTUS, 4);
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWERING_BARREL_CACTI = ConfiguredFeaturesUtil.createPatchConfiguredFeatureWithBlock("flowering_barrel_cacti", BWGBlocks.FLOWERING_BARREL_CACTUS, 4);
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALOE_VERA = ConfiguredFeaturesUtil.createPatchConfiguredFeatureWithBlock("aloe_vera", BWGBlocks.ALOE_VERA, 6);

    public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_PUFFBALL = createFlowerConfiguredFeature("white_puffball", () -> BWGBlocks.WHITE_PUFFBALL.getBlockSupplier().get());
    public static final ResourceKey<ConfiguredFeature<?, ?>> FIRECRACKER_BUSH = createFlowerConfiguredFeature("firecracker_bush", () -> BWGBlocks.FIRECRACKER_FLOWER_BUSH.getBlockSupplier().get());


    public static final ResourceKey<ConfiguredFeature<?, ?>> WINDSWEPT_DESERT_VEGETATION = ConfiguredFeaturesUtil.createConfiguredFeature("windswept_desert_vegetation",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstapContext.lookup(Registries.CONFIGURED_FEATURE);

                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(MINI_CACTI), SAND_FILTER), 0.15F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(PRICKLY_PEAR_CACTI), SAND_FILTER), 0.15F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(VegetationFeatures.PATCH_CACTUS), SAND_FILTER), 0.15F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ALOE_VERA), SAND_FILTER), 0.3F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(GOLDEN_SPINED_CACTI), SAND_FILTER));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> MOJAVE_DESERT_VEGETATION = ConfiguredFeaturesUtil.createConfiguredFeature("mojave_desert_vegetation",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstapContext.lookup(Registries.CONFIGURED_FEATURE);

                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(MINI_CACTI)), 0.3F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(PRICKLY_PEAR_CACTI)), 0.3F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ALOE_VERA)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(FIRECRACKER_BUSH)), 0.1F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(GOLDEN_SPINED_CACTI)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> RUGGED_BADLANDS_VEGETATION = ConfiguredFeaturesUtil.createConfiguredFeature("rugged_badlands_vegetation",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstapContext.lookup(Registries.CONFIGURED_FEATURE);

                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(MINI_CACTI)), 0.15F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(PRICKLY_PEAR_CACTI)), 0.15F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ALOE_VERA)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BARREL_CACTI)), 0.15F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(GOLDEN_SPINED_CACTI)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> ATACAMA_OUTBACK_VEGETATION = ConfiguredFeaturesUtil.createConfiguredFeature("atacama_outback_vegetation",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstapContext.lookup(Registries.CONFIGURED_FEATURE);

                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(MINI_CACTI)), 0.15F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(PRICKLY_PEAR_CACTI)), 0.15F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ALOE_VERA)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(FLOWERING_BARREL_CACTI)), 0.15F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BARREL_CACTI)), 0.15F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(GOLDEN_SPINED_CACTI)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> ANEMONES = ConfiguredFeaturesUtil.createConfiguredFeature("anemones",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstapContext) -> ConfiguredFeaturesUtil.createRandomWeightedConfiguredFeature(configuredFeatureBootstapContext.lookup(Registries.CONFIGURED_FEATURE), BWGBlocks.PINK_ANEMONE.getFeature(), BWGBlocks.WHITE_ANEMONE.getFeature()
            ));

    public static final ResourceKey<ConfiguredFeature<?, ?>> SAGES = ConfiguredFeaturesUtil.createConfiguredFeature("sages",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstapContext) -> ConfiguredFeaturesUtil.createRandomWeightedConfiguredFeature(configuredFeatureBootstapContext.lookup(Registries.CONFIGURED_FEATURE), BWGBlocks.BLUE_SAGE.getFeature(), BWGBlocks.PURPLE_SAGE.getFeature(), BWGBlocks.WHITE_SAGE.getFeature()
            ));

    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_FLOWERS = ConfiguredFeaturesUtil.createConfiguredFeature("jungle_flowers",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstapContext) -> ConfiguredFeaturesUtil.createRandomWeightedConfiguredFeature(configuredFeatureBootstapContext.lookup(Registries.CONFIGURED_FEATURE),
                    BWGBlocks.BISTORT.getFeature(), BWGBlocks.GUZMANIA.getFeature(), BWGBlocks.BEGONIA.getFeature(), BWGBlocks.LAZARUS_BELLFLOWER.getFeature(), BWGBlocks.RICHEA.getFeature(), BWGBlocks.INCAN_LILY.getFeature()
            ));

    public static final ResourceKey<ConfiguredFeature<?, ?>> LEATHER_FLOWERS = ConfiguredFeaturesUtil.createConfiguredFeature("leather_flowers",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstapContext) -> ConfiguredFeaturesUtil.createRandomWeightedConfiguredFeature(configuredFeatureBootstapContext.lookup(Registries.CONFIGURED_FEATURE),
                    BWGBlocks.PEACH_LEATHER_FLOWER.getFeature(), BWGBlocks.VIOLET_LEATHER_FLOWER.getFeature()
            ));

    public static final ResourceKey<ConfiguredFeature<?, ?>> TINY_LILY_PAD = ConfiguredFeaturesUtil.createConfiguredFeature("tiny_lily_pad",
            Feature.RANDOM_PATCH,
            () -> new RandomPatchConfiguration(10, 7, 3,
                    PlacedFeaturesUtil.createPlacedFeatureDirect(ConfiguredFeaturesUtil.createConfiguredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BWGBlocks.TINY_LILY_PADS.get()))), ON_WATER_FILTER, IN_REPLACEABLE_FILTER)
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWERING_TINY_LILY_PAD = ConfiguredFeaturesUtil.createConfiguredFeature("flowering_tiny_lily_pad",
            Feature.RANDOM_PATCH,
            () -> new RandomPatchConfiguration(10, 7, 3,
                    PlacedFeaturesUtil.createPlacedFeatureDirect(ConfiguredFeaturesUtil.createConfiguredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BWGBlocks.FLOWERING_TINY_LILY_PADS.get()))), ON_WATER_FILTER, IN_REPLACEABLE_FILTER)
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> WATER_SILK = ConfiguredFeaturesUtil.createConfiguredFeature("water_silk",
            Feature.RANDOM_PATCH,
            () -> new RandomPatchConfiguration(10, 7, 3,
                    PlacedFeaturesUtil.createPlacedFeatureDirect(ConfiguredFeaturesUtil.createConfiguredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BWGBlocks.WATER_SILK.get()))), ON_WATER_FILTER, IN_REPLACEABLE_FILTER)
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SWAMP_WATER_VEGETATION = ConfiguredFeaturesUtil.createConfiguredFeature("swamp_water_vegetation",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstapContext.lookup(Registries.CONFIGURED_FEATURE);

                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(TINY_LILY_PAD), ON_WATER_FILTER), 0.5F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(WATER_SILK), ON_WATER_FILTER));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> MANGROVE_SWAMP_WATER_VEGETATION = ConfiguredFeaturesUtil.createConfiguredFeature("mangrove_swamp_water_vegetation",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstapContext.lookup(Registries.CONFIGURED_FEATURE);

                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(TINY_LILY_PAD), ON_WATER_FILTER), 0.5F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(FLOWERING_TINY_LILY_PAD), ON_WATER_FILTER));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> CATTAIL = createPatchConfiguredFeature("cattail", BWGBlocks.CATTAIL, 32);
    public static final ResourceKey<ConfiguredFeature<?, ?>> CATTAIL_SPROUT = createPatchConfiguredFeature("cattail_sprout", BWGBlocks.CATTAIL_SPROUT, 32);

    public static final ResourceKey<ConfiguredFeature<?, ?>> CATTAILS = ConfiguredFeaturesUtil.createConfiguredFeature("cattails",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstapContext) -> ConfiguredFeaturesUtil.createRandomWeightedConfiguredFeature(configuredFeatureBootstapContext.lookup(Registries.CONFIGURED_FEATURE), CATTAIL, CATTAIL_SPROUT)
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> WATER_VEGETATION = ConfiguredFeaturesUtil.createConfiguredFeature("crag_water_vegetation",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(TINY_LILY_PAD), ON_WATER_FILTER), 0.1875F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(CATTAILS), BlockPredicateFilter.forPredicate(BlockPredicate.noFluid(new BlockPos(0, -1, 0)))), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(VegetationFeatures.PATCH_WATERLILY), ON_WATER_FILTER), 0.1875F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(WATER_SILK), ON_WATER_FILTER), 0.1875F)
                ), PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(FLOWERING_TINY_LILY_PAD), ON_WATER_FILTER));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_GREEN_MUSHROOM1 = ConfiguredFeaturesUtil.createConfiguredFeature("huge_green_mushroom1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/mushrooms/green_mushroom/green_mushroom_trunk1"),
                    BiomesWeveGone.id("features/mushrooms/green_mushroom/green_mushroom_canopy1"),
                    BiasedToBottomInt.of(6, 8),
                    BlockStateProvider.simple(BWGBlocks.WHITE_MUSHROOM_STEM.get()),
                    BlockStateProvider.simple(BWGBlocks.GREEN_MUSHROOM_BLOCK.get()),
                    BWGBlocks.WHITE_MUSHROOM_STEM.get(),
                    BWGBlocks.GREEN_MUSHROOM_BLOCK.get(),
                    BlockTags.DIRT, 5, ImmutableList.of()
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_GREEN_MUSHROOM2 = ConfiguredFeaturesUtil.createConfiguredFeature("huge_green_mushroom1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/mushrooms/green_mushroom/green_mushroom_trunk2"),
                    BiomesWeveGone.id("features/mushrooms/green_mushroom/green_mushroom_canopy2"),
                    BiasedToBottomInt.of(6, 8),
                    BlockStateProvider.simple(BWGBlocks.WHITE_MUSHROOM_STEM.get()),
                    BlockStateProvider.simple(BWGBlocks.GREEN_MUSHROOM_BLOCK.get()),
                    BWGBlocks.WHITE_MUSHROOM_STEM.get(),
                    BWGBlocks.GREEN_MUSHROOM_BLOCK.get(),
                    BlockTags.DIRT, 5, ImmutableList.of()
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_WEEPING_MILKCAP1 = ConfiguredFeaturesUtil.createConfiguredFeature("huge_weeping_milkcap1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/mushrooms/weeping_milkcap/weeping_milkcap_trunk1"),
                    BiomesWeveGone.id("features/mushrooms/weeping_milkcap/weeping_milkcap_canopy1"),
                    BiasedToBottomInt.of(6, 8),
                    BlockStateProvider.simple(BWGBlocks.BROWN_MUSHROOM_STEM.get()),
                    BlockStateProvider.simple(BWGBlocks.WEEPING_MILKCAP_MUSHROOM_BLOCK.get()),
                    BWGBlocks.BROWN_MUSHROOM_STEM.get(),
                    BWGBlocks.WEEPING_MILKCAP_MUSHROOM_BLOCK.get(),
                    BlockTags.DIRT, 5, ImmutableList.of()
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_WOOD_BLEWIT1 = ConfiguredFeaturesUtil.createConfiguredFeature("huge_wood_blewit1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/mushrooms/wood_blewit/wood_blewit_trunk1"),
                    BiomesWeveGone.id("features/mushrooms/wood_blewit/wood_blewit_canopy1"),
                    BiasedToBottomInt.of(6, 8),
                    BlockStateProvider.simple(BWGBlocks.BROWN_MUSHROOM_STEM.get()),
                    BlockStateProvider.simple(BWGBlocks.WOOD_BLEWIT_MUSHROOM_BLOCK.get()),
                    BWGBlocks.BROWN_MUSHROOM_STEM.get(),
                    BWGBlocks.WOOD_BLEWIT_MUSHROOM_BLOCK.get(),
                    BlockTags.DIRT, 5, ImmutableList.of()
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_MUSHROOMS = ConfiguredFeaturesUtil.createConfiguredFeature("huge_mushrooms",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(HUGE_GREEN_MUSHROOM1)), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(HUGE_GREEN_MUSHROOM2)), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(HUGE_WEEPING_MILKCAP1)), 0.25F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(HUGE_WOOD_BLEWIT1)));
            }
    );


    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_PUMPKIN = ConfiguredFeaturesUtil.createConfiguredFeature("large_pumpkin",
            BWGFeatures.LARGE_PUMPKIN,
            NoneFeatureConfiguration::new
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> MEDIUM_PUMPKIN = ConfiguredFeaturesUtil.createConfiguredFeature("medium_pumpkin",
            BWGFeatures.MEDIUM_PUMPKIN,
            NoneFeatureConfiguration::new
    );



    private static SimpleWeightedRandomList.Builder<BlockState> createPetalBlockStateList(Supplier<? extends Block> block) {
        SimpleWeightedRandomList.Builder<BlockState> builder = SimpleWeightedRandomList.builder();
        for (int i = PinkPetalsBlock.MIN_FLOWERS; i <= PinkPetalsBlock.MAX_FLOWERS; i++)
            for (Direction direction : Direction.Plane.HORIZONTAL)
                builder.add(block.get().defaultBlockState().setValue(PinkPetalsBlock.AMOUNT, i).setValue(PinkPetalsBlock.FACING, direction), 1);
        return builder;
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> createFlowerConfiguredFeature(String id, Supplier<? extends Block> flowerBlock) {
        return ConfiguredFeaturesUtil.createConfiguredFeature(id, Feature.FLOWER, () -> VegetationFeatures.grassPatch(SimpleStateProvider.simple(flowerBlock.get().defaultBlockState()), 15));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> createPatchConfiguredFeature(String id, Supplier<? extends Block> block, int tries) {
        return createPatchConfiguredFeatureState(id, () -> block.get().defaultBlockState(), tries);
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> createPatchConfiguredFeatureState(String id, Supplier<? extends BlockState> state, int tries) {
        return ConfiguredFeaturesUtil.createConfiguredFeature(id, Feature.RANDOM_PATCH, () -> FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(state.get())), List.of(), tries));
    }


    public static void init() {
        BiomesWeveGone.LOGGER.info("Creating and Registering Overworld Vegetation Configured Features");
    }
}
