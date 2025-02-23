package net.potionstudios.biomeswevegone.world.level.levelgen.feature.configured;

import com.mojang.datafixers.util.Pair;
import corgitaco.corgilib.math.blendingfunction.BlendingFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.levelgen.BWGWorldGenerationUtil;
import net.potionstudios.biomeswevegone.world.level.levelgen.CheckedBlockPlacement;
import net.potionstudios.biomeswevegone.world.level.levelgen.blockpredicates.RandomChancePredicate;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.BWGFeatures;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.ConfigurableFreezeTopLayer;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.PillarFeature;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.config.NoiseSphereConfig;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.config.RoundedRockConfig;

import java.util.List;

public class BWGOverworldConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> BOREALIS_ICE_SHARPENED_SPIKE = ConfiguredFeaturesUtil.createConfiguredFeature("borealis_ice_sharpened_spike",
            BWGFeatures.SHARPENED_ROCK,
            () -> FeatureConfiguration.NONE
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> WINDSWEPT_BOULDER = ConfiguredFeaturesUtil.createConfiguredFeature("windswept_boulder",
            BWGFeatures.NOISE_SPHERE,
            () -> new NoiseSphereConfig(
                    UniformInt.of(4, 16),
                    UniformFloat.of(0.01F, 0.1F),
                    new CheckedBlockPlacement(
                            List.of(
                                    Pair.of(BlockPredicate.alwaysTrue(), BlockStateProvider.simple(BWGBlocks.WINDSWEPT_SAND_SET.getSandstone())),
                                    Pair.of(BlockPredicate.allOf(
                                                    new RandomChancePredicate(ConstantFloat.of(0.2F)),
                                                    BlockPredicate.anyOf(BWGWorldGenerationUtil.blockMatchesInAllDirections(blockPos -> BlockPredicate.matchesBlocks(blockPos, Blocks.AIR, Blocks.CAVE_AIR)))
                                            ),
                                            BlockStateProvider.simple(BWGBlocks.WINDSWEPT_SAND_SET.getSand())
                                    )
                            )
                    )
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> STONE_BOULDER = ConfiguredFeaturesUtil.createConfiguredFeature("stone_boulder",
            BWGFeatures.NOISE_SPHERE,
            () -> new NoiseSphereConfig(
                    UniformInt.of(4, 16),
                    UniformFloat.of(0.01F, 0.1F),
                    new CheckedBlockPlacement(
                            List.of(
                                    Pair.of(BlockPredicate.alwaysTrue(), BlockStateProvider.simple(Blocks.STONE))
                            )
                    )
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> LUSH_ROUNDED_ROCK = ConfiguredFeaturesUtil.createConfiguredFeature("lush_rounded_rock",
            BWGFeatures.ROUNDED_ROCK,
            () -> new RoundedRockConfig(
                    UniformInt.of(4, 11),
                    UniformInt.of(33, 64),
                    UniformFloat.of(0.01F, 0.1F),
                    new CheckedBlockPlacement(
                            List.of(
                                    Pair.of(BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, BlockStateProvider.simple(Blocks.STONE)),
                                    Pair.of(BlockPredicate.allOf(
                                                    new RandomChancePredicate(ConstantFloat.of(0.4F)),
                                                    BlockPredicate.anyOf(BWGWorldGenerationUtil.blockMatchesInAllDirections(blockPos -> BlockPredicate.matchesBlocks(blockPos, Blocks.AIR, Blocks.CAVE_AIR)))
                                            ),
                                            new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.MOSS_BLOCK.defaultBlockState(), 1).add(Blocks.MOSSY_COBBLESTONE.defaultBlockState(), 2))
                                    )
                            )
                    ),
                    SimpleWeightedRandomList.single(BlendingFunction.EaseOutCubic.INSTANCE) // TODO: We need to configure the exponent by adding a config option in CorgiLib
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> DRIPSTONE_ROUNDED_ROCK = ConfiguredFeaturesUtil.createConfiguredFeature("dripstone_rounded_rock",
            BWGFeatures.ROUNDED_ROCK,
            () -> new RoundedRockConfig(
                    UniformInt.of(4, 12),
                    UniformInt.of(64, 75),
                    UniformFloat.of(0.2F, 0.6F),
                    new CheckedBlockPlacement(
                            List.of(
                                    Pair.of(BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, BlockStateProvider.simple(Blocks.STONE)),
                                    Pair.of(BlockPredicate.allOf(
                                                    new RandomChancePredicate(ConstantFloat.of(0.4F)),
                                                    BlockPredicate.anyOf(BWGWorldGenerationUtil.blockMatchesInAllDirections(blockPos -> BlockPredicate.matchesBlocks(blockPos, Blocks.AIR, Blocks.CAVE_AIR, Blocks.WATER)))
                                            ),
                                            new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.DRIPSTONE_BLOCK.defaultBlockState(), 1))
                                    )
                            )
                    ),
                    SimpleWeightedRandomList.<BlendingFunction>builder().add(new BlendingFunction.EaseInCirc(0.8), 4).build()
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> VINE_PROCESSOR = ConfiguredFeaturesUtil.createConfiguredFeature("vine_processor",
            BWGFeatures.VINE_PROCESSOR,
            () -> FeatureConfiguration.NONE
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> LUSH_BLOCKS_PROCESSOR = ConfiguredFeaturesUtil.createConfiguredFeature("lush_blocks_processor",
            BWGFeatures.LUSH_BLOCKS_PROCESSOR,
            () -> FeatureConfiguration.NONE
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> CRAG_LAKE = ConfiguredFeaturesUtil.createConfiguredFeature("crag_lake",
            BWGFeatures.CRAG_LAKE,
            () -> FeatureConfiguration.NONE
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_PILLAR = ConfiguredFeaturesUtil.createConfiguredFeature("jungle_pillar",
            BWGFeatures.PILLAR,
            () -> new PillarFeature.Config(
                    new CheckedBlockPlacement(List.of(
                            Pair.of(BlockPredicate.alwaysTrue(), BlockStateProvider.simple(Blocks.STONE)),
                            Pair.of(BlockPredicate.matchesBlocks(new BlockPos(0,1,0), Blocks.AIR, Blocks.CAVE_AIR), BlockStateProvider.simple(BWGBlocks.OVERGROWN_STONE.get())),
                            Pair.of(BlockPredicate.allOf(new RandomChancePredicate(ConstantFloat.of(0.4F)), BlockPredicate.not(BlockPredicate.matchesBlocks(BWGBlocks.OVERGROWN_STONE.get())), BlockPredicate.anyOf(BWGWorldGenerationUtil.blockMatchesInAllDirections(blockPos -> BlockPredicate.matchesBlocks(blockPos, Blocks.AIR, Blocks.CAVE_AIR)))), new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.MOSSY_COBBLESTONE.defaultBlockState(), 3).add(Blocks.MOSS_BLOCK.defaultBlockState(), 1)))
                    )),
                    UniformInt.of(15, 75),
                    UniformInt.of(7, 15),
                    UniformFloat.of(0.01F, 0.1F),
                    UniformFloat.of(0.1F, 0.5F),
                    new SimpleWeightedRandomList.Builder<PillarFeature.DistanceTestType>().add(PillarFeature.DistanceTestType.EUCLIDEAN, 20).add(PillarFeature.DistanceTestType.MANHATTAN, 2).add(PillarFeature.DistanceTestType.CHEBYSHEV, 1).build()
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> BOREALIS_ICE_FREEZE_TOP_LAYER = ConfiguredFeaturesUtil.createConfiguredFeature("borealis_ice_freeze_top_layer",
            BWGFeatures.CONFIGURABLE_FREEZE_TOP_LAYER,
            () -> new ConfigurableFreezeTopLayer.Config(BlockStateProvider.simple(BWGBlocks.BOREALIS_ICE.get()), BlockStateProvider.simple(Blocks.SNOW))
    );

    public static void init() {
        BiomesWeveGone.LOGGER.info("Registering Oh The Biomes You'll Go Overworld Configured Features");
    }
}
