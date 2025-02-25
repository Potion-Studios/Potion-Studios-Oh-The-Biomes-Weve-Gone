package net.potionstudios.biomeswevegone.world.level.levelgen.feature.configured;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;

import java.util.function.Supplier;

public class BWGConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> MOSSY_STONE_BOULDER = createForestRock("mossy_stone_boulder", BWGBlocks.MOSSY_STONE_SET::getBase);
    public static final ResourceKey<ConfiguredFeature<?, ?>> ROCKY_STONE_BOULDER = createForestRock("rocky_stone_boulder", BWGBlocks.ROCKY_STONE_SET::getBase);
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACKSTONE_BOULDER = createForestRock("blackstone_boulder", () -> Blocks.BLACKSTONE);
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_TERRACOTTA_BOULDER = createForestRock("orange_terracotta_boulder", () -> Blocks.ORANGE_TERRACOTTA);
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_ICE = ConfiguredFeaturesUtil.createConfiguredFeature("black_ice", Feature.SIMPLE_BLOCK, () -> new SimpleBlockConfiguration(BlockStateProvider.simple(BWGBlocks.BLACK_ICE.get().defaultBlockState())));
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_MUD = ConfiguredFeaturesUtil.createConfiguredFeature("disk_mud",
            Feature.DISK,
            () -> new DiskConfiguration(new RuleBasedBlockStateProvider(BlockStateProvider.simple(Blocks.MUD), ImmutableList.of(new RuleBasedBlockStateProvider.Rule(BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(), Blocks.AIR), BlockStateProvider.simple(Blocks.MUD)))), BlockPredicate.matchesBlocks(Blocks.DIRT, Blocks.CLAY), UniformInt.of(2, 6), 2)
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> BASALT_DELTA = ConfiguredFeaturesUtil.createConfiguredFeature("delta", Feature.DELTA_FEATURE, () -> new DeltaFeatureConfiguration(Blocks.WATER.defaultBlockState(), Blocks.WATER.defaultBlockState(), UniformInt.of(3, 4), UniformInt.of(0, 2)));
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_BASALT_COLUMN = ConfiguredFeaturesUtil.createConfiguredFeature("small_basalt_columns", Feature.BASALT_COLUMNS, () -> new ColumnFeatureConfiguration(UniformInt.of(0, 1), UniformInt.of(1, 1)));
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_BASALT_COLUMN = ConfiguredFeaturesUtil.createConfiguredFeature("large_basalt_columns", Feature.BASALT_COLUMNS, () -> new ColumnFeatureConfiguration(UniformInt.of(1, 2), UniformInt.of(1, 1)));

    public static final ResourceKey<ConfiguredFeature<?, ?>> SWAMP_GRASS_BLOCK_DELTA = ConfiguredFeaturesUtil.createConfiguredFeature("swamp_grass_block_delta", Feature.DELTA_FEATURE, () -> new DeltaFeatureConfiguration(Blocks.WATER.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState(), UniformInt.of(10, 15), UniformInt.of(1, 3)));

    private static ResourceKey<ConfiguredFeature<? ,?>> createForestRock(String name, Supplier<? extends Block> block) {
        return ConfiguredFeaturesUtil.createConfiguredFeature(name, Feature.FOREST_ROCK, () -> new BlockStateConfiguration(block.get().defaultBlockState()));
    }

    public static void configuredFeatures() {
        BiomesWeveGone.LOGGER.info("Registering Oh The Biomes We've Gone Configured Features");
        BWGOverworldVegetationConfiguredFeatures.init();
        BWGOverworldTreeConfiguredFeatures.init();
        BWGVanillaConfiguredFeatures.init();
        BWGOverworldConfiguredFeatures.init();
    }
}
