package net.potionstudios.biomeswevegone.world.level.levelgen.feature.configured;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import dev.corgitaco.ohthetreesyoullgrow.world.level.levelgen.feature.TYGFeatures;
import dev.corgitaco.ohthetreesyoullgrow.world.level.levelgen.feature.configurations.TreeFromStructureNBTConfig;
import dev.corgitaco.ohthetreesyoullgrow.world.level.levelgen.feature.configurations.treedecorators.AttachedToFruitLeavesDecorator;
import dev.corgitaco.ohthetreesyoullgrow.world.level.levelgen.feature.configurations.treedecorators.AttachedToLogsDecorator;
import dev.corgitaco.ohthetreesyoullgrow.world.level.levelgen.feature.configurations.treedecorators.TYGLeavesVineDecorator;
import dev.corgitaco.ohthetreesyoullgrow.world.level.levelgen.feature.configurations.treedecorators.TYGTrunkVineDecorator;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MangrovePropaguleBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.CocoaDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.block.plants.tree.fruit.BWGFruitBlock;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWood;
import net.potionstudios.biomeswevegone.world.level.block.wood.ImbuedBlock;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.placed.PlacedFeaturesUtil;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.treedecorators.GlowBerryDecorator;

import java.util.List;
import java.util.function.Supplier;


public class BWGOverworldTreeConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ARAUCARIA_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("araucaria_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/araucaria/araucaria_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/araucaria/araucaria_tree_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.PINE.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ARAUCARIA_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> ARAUCARIA_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("araucaria_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/araucaria/araucaria_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/araucaria/araucaria_tree_canopy2"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.PINE.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ARAUCARIA_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final Supplier<GlowBerryDecorator> GLOW_BERRY_DECORATOR = Suppliers.memoize(() -> new GlowBerryDecorator(ConstantFloat.of(0.1F), UniformInt.of(2, 6), UniformFloat.of(0.25F, 0.5F)));
    public static final Supplier<AttachedToLogsDecorator> MOSS_CARPET = () -> new AttachedToLogsDecorator(0.9F, 0, 0, SimpleStateProvider.simple(Blocks.MOSS_CARPET.defaultBlockState()), 1, List.of(Direction.UP));
    public static final Supplier<AttachedToLogsDecorator> SHELF_FUNGI = () -> new AttachedToLogsDecorator(0.3F, 0, 1, SimpleStateProvider.simple(BWGBlocks.SHELF_FUNGI.get().defaultBlockState()), 2, List.of(Direction.WEST, Direction.NORTH, Direction.SOUTH, Direction.EAST));


    public static final ResourceKey<ConfiguredFeature<?, ?>> ANCIENT_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("ancient_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/ancient/ancient_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/ancient/ancient_tree_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.DARK_OAK_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.DARK_OAK_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.DARK_OAK_LOG,
                    Blocks.DARK_OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(GLOW_BERRY_DECORATOR.get(), new TrunkVineDecorator(), MOSS_CARPET.get(), SHELF_FUNGI.get())
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> ANCIENT_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("ancient_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/ancient/ancient_tree_trunk2"),
                    BiomesWeveGone.id("features/trees/ancient/ancient_tree_canopy2"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.EBONY.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.EBONY.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(GLOW_BERRY_DECORATOR.get(), new TrunkVineDecorator(), MOSS_CARPET.get(), SHELF_FUNGI.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> ANCIENT_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("ancient_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/ancient/ancient_tree_trunk3"),
                    BiomesWeveGone.id("features/trees/ancient/ancient_tree_canopy3"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.MAPLE.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.SILVER_MAPLE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(GLOW_BERRY_DECORATOR.get(), new TrunkVineDecorator(), MOSS_CARPET.get(), SHELF_FUNGI.get())
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> ASPEN_SHRUB1 = ConfiguredFeaturesUtil.createConfiguredFeature("aspen_shrub1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/aspen-shrub/aspen_shrub_trunk1"),
                    BiomesWeveGone.id("features/trees/aspen-shrub/aspen_shrub_canopy1"),
                    ConstantInt.of(0),
                    BlockStateProvider.simple(BWGWood.ASPEN.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ASPEN.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 1
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> ASPEN_SHRUB2 = ConfiguredFeaturesUtil.createConfiguredFeature("aspen_shrub2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/aspen-shrub/aspen_shrub_trunk1"),
                    BiomesWeveGone.id("features/trees/aspen-shrub/aspen_shrub_canopy2"),
                    ConstantInt.of(0),
                    BlockStateProvider.simple(BWGWood.ASPEN.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ASPEN.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 1
            )
    );


    public static final ResourceKey<ConfiguredFeature<?, ?>> ASPEN_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("aspen_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/aspen/aspen_trunk1"),
                    BiomesWeveGone.id("features/trees/aspen/aspen_canopy1"),
                    BiasedToBottomInt.of(7, 12),
                    BlockStateProvider.simple(BWGWood.ASPEN.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ASPEN.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 4, ImmutableList.of(SHELF_FUNGI.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> ASPEN_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("aspen_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/aspen/aspen_trunk2"),
                    BiomesWeveGone.id("features/trees/aspen/aspen_canopy2"),
                    BiasedToBottomInt.of(7, 12),
                    BlockStateProvider.simple(BWGWood.ASPEN.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ASPEN.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 8, ImmutableList.of(SHELF_FUNGI.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> ASPEN_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("aspen_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/aspen/aspen_trunk3"),
                    BiomesWeveGone.id("features/trees/aspen/aspen_canopy3"),
                    BiasedToBottomInt.of(6, 10),
                    BlockStateProvider.simple(BWGWood.ASPEN.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ASPEN.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 4, ImmutableList.of(SHELF_FUNGI.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> ASPEN_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("aspen_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/aspen/aspen_trunk4"),
                    BiomesWeveGone.id("features/trees/aspen/aspen_canopy4"),
                    BiasedToBottomInt.of(6, 10),
                    BlockStateProvider.simple(BWGWood.ASPEN.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ASPEN.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 4, ImmutableList.of(SHELF_FUNGI.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> ASPEN_TREE5 = ConfiguredFeaturesUtil.createConfiguredFeature("aspen_tree5",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/aspen/aspen_trunk5"),
                    BiomesWeveGone.id("features/trees/aspen/aspen_canopy5"),
                    BiasedToBottomInt.of(6, 12),
                    BlockStateProvider.simple(BWGWood.ASPEN.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ASPEN.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 4, ImmutableList.of(SHELF_FUNGI.get())
            )
    );

    private static final Supplier<AttachedToLeavesDecorator> BAOBAB_FRUIT = () -> new AttachedToFruitLeavesDecorator(0.2F, 2, 0, BWGWood.RIPE_BAOBAB_LEAVES.get(), new RandomizedIntStateProvider(BlockStateProvider.simple(BWGBlocks.BAOBAB_FRUIT_BLOCK.get().defaultBlockState()), BWGFruitBlock.AGE, UniformInt.of(0, 3)), 2, List.of(Direction.DOWN));

    public static final ResourceKey<ConfiguredFeature<?, ?>> BAOBAB_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("baobab_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/baobab/baobab_trunk1"),
                    BiomesWeveGone.id("features/trees/baobab/baobab_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.BAOBAB.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.BAOBAB.leaves().defaultBlockState(), 10)
                            .add(BWGWood.FLOWERING_BAOBAB_LEAVES.get().defaultBlockState(), 2)
                            .add(BWGWood.RIPE_BAOBAB_LEAVES.get().defaultBlockState(), 1).build()
                    ),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(BAOBAB_FRUIT.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> BAOBAB_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("baobab_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/baobab/baobab_trunk2"),
                    BiomesWeveGone.id("features/trees/baobab/baobab_canopy2"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.BAOBAB.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.BAOBAB.leaves().defaultBlockState(), 10)
                            .add(BWGWood.FLOWERING_BAOBAB_LEAVES.get().defaultBlockState(), 2)
                            .add(BWGWood.RIPE_BAOBAB_LEAVES.get().defaultBlockState(), 1).build()
                    ),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(BAOBAB_FRUIT.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> BAOBAB_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("baobab_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/baobab/baobab_trunk3"),
                    BiomesWeveGone.id("features/trees/baobab/baobab_canopy3"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.BAOBAB.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.BAOBAB.leaves().defaultBlockState(), 10)
                            .add(BWGWood.FLOWERING_BAOBAB_LEAVES.get().defaultBlockState(), 2)
                            .add(BWGWood.RIPE_BAOBAB_LEAVES.get().defaultBlockState(), 1).build()
                    ),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(BAOBAB_FRUIT.get())
            )
    );


    public static final ResourceKey<ConfiguredFeature<?, ?>> BIRCH_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("birch_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/birch/birch_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/birch/birch_tree_canopy1"),
                    BiasedToBottomInt.of(3, 10),
                    BlockStateProvider.simple(Blocks.BIRCH_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.BIRCH_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.BIRCH_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(SHELF_FUNGI.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIRCH_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("birch_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/birch/birch_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/birch/birch_tree_canopy2"),
                    BiasedToBottomInt.of(3, 10),
                    BlockStateProvider.simple(Blocks.BIRCH_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.BIRCH_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.BIRCH_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(SHELF_FUNGI.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIRCH_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("birch_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/birch/birch_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/birch/birch_tree_canopy3"),
                    BiasedToBottomInt.of(3, 10),
                    BlockStateProvider.simple(Blocks.BIRCH_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.BIRCH_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.BIRCH_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(SHELF_FUNGI.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIRCH_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("birch_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/birch/birch_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/birch/birch_tree_canopy4"),
                    BiasedToBottomInt.of(3, 10),
                    BlockStateProvider.simple(Blocks.BIRCH_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.BIRCH_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.BIRCH_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(SHELF_FUNGI.get())
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> BROWN_BIRCH_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("brown_birch_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/birch/birch_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/birch/birch_tree_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.BIRCH_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.BROWN_BIRCH_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.BIRCH_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> BROWN_BIRCH_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("brown_birch_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/birch/birch_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/birch/birch_tree_canopy2"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.BIRCH_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.BROWN_BIRCH_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.BIRCH_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> BROWN_BIRCH_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("brown_birch_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/birch/birch_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/birch/birch_tree_canopy3"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.BIRCH_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.BROWN_BIRCH_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.BIRCH_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> BROWN_BIRCH_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("brown_birch_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/birch/birch_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/birch/birch_tree_canopy4"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.BIRCH_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.BROWN_BIRCH_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.BIRCH_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );


    public static final ResourceKey<ConfiguredFeature<?, ?>> BIRCH_ORANGE_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("orange_birch_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/birch/birch_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/birch/birch_tree_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.BIRCH_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ORANGE_BIRCH_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.BIRCH_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIRCH_ORANGE_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("orange_birch_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/birch/birch_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/birch/birch_tree_canopy2"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.BIRCH_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ORANGE_BIRCH_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.BIRCH_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIRCH_ORANGE_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("orange_birch_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/birch/birch_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/birch/birch_tree_canopy3"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.BIRCH_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ORANGE_BIRCH_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.BIRCH_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIRCH_ORANGE_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("orange_birch_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/birch/birch_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/birch/birch_tree_canopy4"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.BIRCH_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ORANGE_BIRCH_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.BIRCH_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );


    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_BIRCH_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("red_birch_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/birch/birch_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/birch/birch_tree_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.BIRCH_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.RED_BIRCH_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.BIRCH_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_BIRCH_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("red_birch_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/birch/birch_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/birch/birch_tree_canopy2"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.BIRCH_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.RED_BIRCH_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.BIRCH_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_BIRCH_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("red_birch_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/birch/birch_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/birch/birch_tree_canopy3"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.BIRCH_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.RED_BIRCH_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.BIRCH_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_BIRCH_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("red_birch_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/birch/birch_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/birch/birch_tree_canopy4"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.BIRCH_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.RED_BIRCH_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.BIRCH_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );


    public static final ResourceKey<ConfiguredFeature<?, ?>> YELLOW_BIRCH_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("yellow_birch_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/birch/birch_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/birch/birch_tree_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.BIRCH_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.YELLOW_BIRCH_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.BIRCH_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> YELLOW_BIRCH_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("yellow_birch_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/birch/birch_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/birch/birch_tree_canopy2"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.BIRCH_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.YELLOW_BIRCH_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.BIRCH_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> YELLOW_BIRCH_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("yellow_birch_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/birch/birch_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/birch/birch_tree_canopy3"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.BIRCH_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.YELLOW_BIRCH_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.BIRCH_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> YELLOW_BIRCH_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("yellow_birch_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/birch/birch_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/birch/birch_tree_canopy4"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.BIRCH_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.YELLOW_BIRCH_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.BIRCH_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUFF_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("bluff_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/bluff/bluff_trunk2"),
                    BiomesWeveGone.id("features/trees/bluff/bluff_canopy1"),
                    BiasedToBottomInt.of(1, 2),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.SPRUCE_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUFF_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("bluff_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/bluff/bluff_trunk2"),
                    BiomesWeveGone.id("features/trees/bluff/bluff_canopy2"),
                    BiasedToBottomInt.of(1, 2),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.SPRUCE_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> BIRCH_BOREAL_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("birch_boreal_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/birch/birch_yellow_boreal_trunk"),
                    BiomesWeveGone.id("features/trees/birch/birch_yellow_boreal_canopy"),
                    BiasedToBottomInt.of(3, 5),
                    BlockStateProvider.simple(Blocks.BIRCH_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.BIRCH_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.BIRCH_LOG,
                    Blocks.BIRCH_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> BIRCH_YELLOW_BOREAL_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("birch_yellow_boreal_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/birch/birch_boreal_trunk1"),
                    BiomesWeveGone.id("features/trees/birch/birch_boreal_canopy1"),
                    BiasedToBottomInt.of(3, 5),
                    BlockStateProvider.simple(Blocks.BIRCH_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.YELLOW_BIRCH_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.BIRCH_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_BUSH1 = ConfiguredFeaturesUtil.createConfiguredFeature("oak_bush1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/bush/trunk_bush1"),
                    BiomesWeveGone.id("features/trees/bush/canopy_bush1"),
                    BiasedToBottomInt.of(1, 2),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    BlockStateProvider.simple(Blocks.OAK_LEAVES),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_BUSH1 = ConfiguredFeaturesUtil.createConfiguredFeature("jungle_bush1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/bush/trunk_bush1"),
                    BiomesWeveGone.id("features/trees/bush/canopy_bush1"),
                    BiasedToBottomInt.of(1, 2),
                    BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                    BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_WHITE_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("white_sakura_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/sakura/sakura_trunk1"),
                    BiomesWeveGone.id("features/trees/sakura/sakura_canopy1"),
                    BiasedToBottomInt.of(1, 3),
                    BlockStateProvider.simple(BWGWood.SAKURA.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.WHITE_SAKURA_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_WHITE_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("white_sakura_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/sakura/sakura_trunk2"),
                    BiomesWeveGone.id("features/trees/sakura/sakura_canopy2"),
                    BiasedToBottomInt.of(1, 4),
                    BlockStateProvider.simple(BWGWood.SAKURA.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.WHITE_SAKURA_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_WHITE_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("white_sakura_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/sakura/sakura_trunk3"),
                    BiomesWeveGone.id("features/trees/sakura/sakura_canopy3"),
                    BiasedToBottomInt.of(4, 9),
                    BlockStateProvider.simple(BWGWood.SAKURA.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.WHITE_SAKURA_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_WHITE_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("white_sakura_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/sakura/sakura_trunk4"),
                    BiomesWeveGone.id("features/trees/sakura/sakura_canopy4"),
                    BiasedToBottomInt.of(6, 16),
                    BlockStateProvider.simple(BWGWood.SAKURA.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.WHITE_SAKURA_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_WHITE_TREE5 = ConfiguredFeaturesUtil.createConfiguredFeature("white_sakura_tree5",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/sakura/sakura_trunk5"),
                    BiomesWeveGone.id("features/trees/sakura/sakura_canopy5"),
                    BiasedToBottomInt.of(7, 16),
                    BlockStateProvider.simple(BWGWood.SAKURA.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.WHITE_SAKURA_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_WHITE_TREE6 = ConfiguredFeaturesUtil.createConfiguredFeature("white_sakura_tree6",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/sakura/sakura_trunk6"),
                    BiomesWeveGone.id("features/trees/sakura/sakura_canopy6"),
                    BiasedToBottomInt.of(7, 16),
                    BlockStateProvider.simple(BWGWood.SAKURA.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.WHITE_SAKURA_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_WHITE_TREE7 = ConfiguredFeaturesUtil.createConfiguredFeature("white_sakura_tree7",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/sakura/sakura_trunk7"),
                    BiomesWeveGone.id("features/trees/sakura/sakura_canopy7"),
                    BiasedToBottomInt.of(5, 14),
                    BlockStateProvider.simple(BWGWood.SAKURA.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.WHITE_SAKURA_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_YELLOW_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("yellow_sakura_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/sakura/sakura_trunk1"),
                    BiomesWeveGone.id("features/trees/sakura/sakura_canopy1"),
                    BiasedToBottomInt.of(1, 3),
                    BlockStateProvider.simple(BWGWood.SAKURA.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.YELLOW_SAKURA_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_YELLOW_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("yellow_sakura_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/sakura/sakura_trunk2"),
                    BiomesWeveGone.id("features/trees/sakura/sakura_canopy2"),
                    BiasedToBottomInt.of(1, 4),
                    BlockStateProvider.simple(BWGWood.SAKURA.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.YELLOW_SAKURA_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_YELLOW_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("yellow_sakura_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/sakura/sakura_trunk3"),
                    BiomesWeveGone.id("features/trees/sakura/sakura_canopy3"),
                    BiasedToBottomInt.of(4, 9),
                    BlockStateProvider.simple(BWGWood.SAKURA.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.YELLOW_SAKURA_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_YELLOW_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("yellow_sakura_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/sakura/sakura_trunk4"),
                    BiomesWeveGone.id("features/trees/sakura/sakura_canopy4"),
                    BiasedToBottomInt.of(7, 16),
                    BlockStateProvider.simple(BWGWood.SAKURA.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.YELLOW_SAKURA_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_YELLOW_TREE5 = ConfiguredFeaturesUtil.createConfiguredFeature("yellow_sakura_tree5",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/sakura/sakura_trunk5"),
                    BiomesWeveGone.id("features/trees/sakura/sakura_canopy5"),
                    BiasedToBottomInt.of(7, 16),
                    BlockStateProvider.simple(BWGWood.SAKURA.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.YELLOW_SAKURA_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_YELLOW_TREE6 = ConfiguredFeaturesUtil.createConfiguredFeature("yellow_sakura_tree6",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/sakura/sakura_trunk6"),
                    BiomesWeveGone.id("features/trees/sakura/sakura_canopy6"),
                    BiasedToBottomInt.of(6, 16),
                    BlockStateProvider.simple(BWGWood.SAKURA.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.YELLOW_SAKURA_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_YELLOW_TREE7 = ConfiguredFeaturesUtil.createConfiguredFeature("yellow_sakura_tree7",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/sakura/sakura_trunk7"),
                    BiomesWeveGone.id("features/trees/sakura/sakura_canopy7"),
                    BiasedToBottomInt.of(6, 13),
                    BlockStateProvider.simple(BWGWood.SAKURA.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.YELLOW_SAKURA_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> CIKA_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("cika_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/cika/cika_trunk1"),
                    BiomesWeveGone.id("features/trees/cika/cika_canopy1"),
                    BiasedToBottomInt.of(14, 21),
                    BlockStateProvider.simple(BWGWood.CIKA.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.CIKA.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 14
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> CIKA_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("cika_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/cika/cika_trunk2"),
                    BiomesWeveGone.id("features/trees/cika/cika_canopy2"),
                    BiasedToBottomInt.of(5, 12),
                    BlockStateProvider.simple(BWGWood.CIKA.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.CIKA.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 12
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> CIKA_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("cika_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/cika/cika_trunk3"),
                    BiomesWeveGone.id("features/trees/cika/cika_canopy3"),
                    BiasedToBottomInt.of(7, 18),
                    BlockStateProvider.simple(BWGWood.CIKA.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.CIKA.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 14
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> CONIFER_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("conifer_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/conifer/conifer_trunk1"),
                    BiomesWeveGone.id("features/trees/conifer/conifer_canopy1"),
                    BiasedToBottomInt.of(19, 25),
                    BlockStateProvider.simple(BWGWood.FIR.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.FIR.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 14
            )

    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONIFER_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("conifer_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/conifer/conifer_trunk1"),
                    BiomesWeveGone.id("features/trees/conifer/conifer_canopy2"),
                    BiasedToBottomInt.of(23, 30),
                    BlockStateProvider.simple(BWGWood.FIR.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.FIR.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 14
            )

    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONIFER_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("conifer_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/conifer/conifer_trunk1"),
                    BiomesWeveGone.id("features/trees/conifer/conifer_canopy3"),
                    BiasedToBottomInt.of(26, 35),
                    BlockStateProvider.simple(BWGWood.FIR.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.FIR.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 14
            )

    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONIFER_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("conifer_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/conifer/conifer_trunk1"),
                    BiomesWeveGone.id("features/trees/conifer/conifer_canopy4"),
                    BiasedToBottomInt.of(25, 35),
                    BlockStateProvider.simple(BWGWood.FIR.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.FIR.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 14
            )

    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONIFER_TREE5 = ConfiguredFeaturesUtil.createConfiguredFeature("conifer_tree5",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/conifer/conifer_trunk1"),
                    BiomesWeveGone.id("features/trees/conifer/conifer_canopy5"),
                    BiasedToBottomInt.of(7, 11),
                    BlockStateProvider.simple(BWGWood.FIR.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.FIR.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 14
            )

    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONIFER_TREE6 = ConfiguredFeaturesUtil.createConfiguredFeature("conifer_tree6",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/conifer/conifer_trunk1"),
                    BiomesWeveGone.id("features/trees/conifer/conifer_canopy6"),
                    BiasedToBottomInt.of(10, 15),
                    BlockStateProvider.simple(BWGWood.FIR.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.FIR.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 14
            )

    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONIFER_TREE7 = ConfiguredFeaturesUtil.createConfiguredFeature("conifer_tree7",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/conifer/conifer_trunk1"),
                    BiomesWeveGone.id("features/trees/conifer/conifer_canopy7"),
                    BiasedToBottomInt.of(11, 18),
                    BlockStateProvider.simple(BWGWood.FIR.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.FIR.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 14
            )

    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONIFER_TREE8 = ConfiguredFeaturesUtil.createConfiguredFeature("conifer_tree8",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/conifer/conifer_trunk8"),
                    BiomesWeveGone.id("features/trees/conifer/conifer_canopy8"),
                    BiasedToBottomInt.of(12, 18),
                    BlockStateProvider.simple(BWGWood.FIR.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.FIR.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 14
            )
    );

    public static final Supplier<AttachedToLogsDecorator> WITCH_HAZEL_BLOSSOM = () -> new AttachedToLogsDecorator(0.1F, 15, 15, SimpleStateProvider.simple(BWGBlocks.WITCH_HAZEL_BLOSSOM.get().defaultBlockState()), 3, List.of(Direction.DOWN));
    public static final Supplier<AttachedToLogsDecorator> WITCH_HAZEL_BRANCH = () -> new AttachedToLogsDecorator(0.65F, 0, 1, SimpleStateProvider.simple(BWGBlocks.WITCH_HAZEL_BRANCH.get().defaultBlockState()), 2, List.of(Direction.WEST, Direction.NORTH, Direction.SOUTH, Direction.EAST));
    public static final Supplier<AttachedToLogsDecorator> GLOW_LICHEN = () -> new AttachedToLogsDecorator(0.9F, 0, 0, SimpleStateProvider.simple(Blocks.GLOW_LICHEN.defaultBlockState()), 1, List.of(Direction.WEST, Direction.NORTH, Direction.SOUTH, Direction.EAST));

    public static final ResourceKey<ConfiguredFeature<?, ?>> CYPRESS_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("cypress_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/cypress/cypress_trunk1"),
                    BiomesWeveGone.id("features/trees/cypress/cypress_canopy1"),
                    BiasedToBottomInt.of(14, 16),
                    BlockStateProvider.simple(BWGWood.CYPRESS.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.CYPRESS.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 14, ImmutableList.of(new LeaveVineDecorator(1), new TrunkVineDecorator(), MOSS_CARPET.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> CYPRESS_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("cypress_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/cypress/cypress_trunk2"),
                    BiomesWeveGone.id("features/trees/cypress/cypress_canopy2"),
                    BiasedToBottomInt.of(10, 13),
                    BlockStateProvider.simple(BWGWood.CYPRESS.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.CYPRESS.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 14, ImmutableList.of(new LeaveVineDecorator(1), new TrunkVineDecorator(), MOSS_CARPET.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> CYPRESS_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("cypress_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/cypress/cypress_trunk3"),
                    BiomesWeveGone.id("features/trees/cypress/cypress_canopy3"),
                    BiasedToBottomInt.of(10, 13),
                    BlockStateProvider.simple(BWGWood.CYPRESS.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.CYPRESS.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 14, ImmutableList.of(new LeaveVineDecorator(1), new TrunkVineDecorator(), MOSS_CARPET.get())
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> WITCH_HAZEL1 = ConfiguredFeaturesUtil.createConfiguredFeature("witch_hazel1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/witch-hazel/witch-hazel_trunk_1"),
                    BiomesWeveGone.id("features/trees/witch-hazel/witch-hazel_canopy_1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.WITCH_HAZEL.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.BLOOMING_WITCH_HAZEL_LEAVES.get().defaultBlockState(), 1).add(BWGWood.WITCH_HAZEL.leaves().defaultBlockState(), 4).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(WITCH_HAZEL_BLOSSOM.get(), WITCH_HAZEL_BRANCH.get())
            )
    );


    public static final ResourceKey<ConfiguredFeature<?, ?>> WITCH_HAZEL2 = ConfiguredFeaturesUtil.createConfiguredFeature("witch_hazel2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/witch-hazel/witch-hazel_trunk_2"),
                    BiomesWeveGone.id("features/trees/witch-hazel/witch-hazel_canopy_2"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.WITCH_HAZEL.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.BLOOMING_WITCH_HAZEL_LEAVES.get().defaultBlockState(), 1).add(BWGWood.WITCH_HAZEL.leaves().defaultBlockState(), 4).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(WITCH_HAZEL_BLOSSOM.get(), WITCH_HAZEL_BRANCH.get())
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> WITCH_HAZEL3 = ConfiguredFeaturesUtil.createConfiguredFeature("witch_hazel3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/witch-hazel/witch-hazel_trunk_3"),
                    BiomesWeveGone.id("features/trees/witch-hazel/witch-hazel_canopy_3"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.WITCH_HAZEL.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.BLOOMING_WITCH_HAZEL_LEAVES.get().defaultBlockState(), 1).add(BWGWood.WITCH_HAZEL.leaves().defaultBlockState(), 4).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(WITCH_HAZEL_BLOSSOM.get(), WITCH_HAZEL_BRANCH.get())
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> WITCH_HAZEL4 = ConfiguredFeaturesUtil.createConfiguredFeature("witch_hazel4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/witch-hazel/witch-hazel_trunk_4"),
                    BiomesWeveGone.id("features/trees/witch-hazel/witch-hazel_canopy_4"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.WITCH_HAZEL.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.BLOOMING_WITCH_HAZEL_LEAVES.get().defaultBlockState(), 1).add(BWGWood.WITCH_HAZEL.leaves().defaultBlockState(), 4).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(WITCH_HAZEL_BLOSSOM.get(), WITCH_HAZEL_BRANCH.get())
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> WITCH_HAZEL5 = ConfiguredFeaturesUtil.createConfiguredFeature("witch_hazel5",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/witch-hazel/witch-hazel_trunk_5"),
                    BiomesWeveGone.id("features/trees/witch-hazel/witch-hazel_canopy_5"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.WITCH_HAZEL.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.BLOOMING_WITCH_HAZEL_LEAVES.get().defaultBlockState(), 1).add(BWGWood.WITCH_HAZEL.leaves().defaultBlockState(), 4).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(WITCH_HAZEL_BLOSSOM.get(), WITCH_HAZEL_BRANCH.get())
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> FIRECRACKER_SHRUB = ConfiguredFeaturesUtil.createConfiguredFeature("firecracker_shrub",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/meadow/meadow_shrub_canopy1"),
                    ConstantInt.of(0),
                    BlockStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
                    BlockStateProvider.simple(BWGWood.FIRECRACKER_LEAVES.get().defaultBlockState()),
                    Blocks.DARK_OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> FIRECRACKER_SHRUB2 = ConfiguredFeaturesUtil.createConfiguredFeature("firecracker_shrub2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/meadow/meadow_shrub_canopy1"),
                    ConstantInt.of(0),
                    BlockStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
                    BlockStateProvider.simple(BWGWood.FIRECRACKER_LEAVES.get().defaultBlockState()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT,  5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> FIRECRACKER_BUSH1 = ConfiguredFeaturesUtil.createConfiguredFeature("firecracker_bush1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/bush/trunk_bush1"),
                    BiomesWeveGone.id("features/trees/bush/canopy_bush1"),
                    BiasedToBottomInt.of(1, 2),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    BlockStateProvider.simple(BWGWood.FIRECRACKER_LEAVES.get().defaultBlockState()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

//    public static final ResourceKey<ConfiguredFeature<?, ?>> DECIDUOUS_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("deciduous_tree1",
//            TYGFeatures.TREE_FROM_NBT_V1,
//            () -> new TreeFromStructureNBTConfig(
//                    BiomesWeveGone.id("features/trees/deciduous/deciduous_trunk1"),
//                    BiomesWeveGone.id("features/trees/deciduous/deciduous_canopy1"),
//                    BiasedToBottomInt.of(4, 7),
//                    BlockStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
//                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.OAK_LEAVES.defaultBlockState(), 1).build()),
//                    Blocks.OAK_LOG,
//                    Blocks.OAK_LEAVES,
//                    BlockTags.DIRT, 1, ImmutableList.of()
//            )
//    );
//
//    public static final ResourceKey<ConfiguredFeature<?, ?>> DECIDUOUS_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("deciduous_tree2",
//            TYGFeatures.TREE_FROM_NBT_V1,
//            () -> new TreeFromStructureNBTConfig(
//                    BiomesWeveGone.id("features/trees/generic_trunk"),
//                    BiomesWeveGone.id("features/trees/deciduous/deciduous_canopy2"),
//                    BiasedToBottomInt.of(6, 8),
//                    BlockStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
//                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.OAK_LEAVES.defaultBlockState(), 1).build()),
//                    Blocks.OAK_LOG,
//                    Blocks.OAK_LEAVES,
//                    BlockTags.DIRT, 1, ImmutableList.of()
//            )
//    );
//
//    public static final ResourceKey<ConfiguredFeature<?, ?>> DECIDUOUS_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("deciduous_tree3",
//            TYGFeatures.TREE_FROM_NBT_V1,
//            () -> new TreeFromStructureNBTConfig(
//                    BiomesWeveGone.id("features/trees/generic_trunk"),
//                    BiomesWeveGone.id("features/trees/deciduous/deciduous_canopy3"),
//                    BiasedToBottomInt.of(6, 8),
//                    BlockStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
//                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.OAK_LEAVES.defaultBlockState(), 1).build()),
//                    Blocks.OAK_LOG,
//                    Blocks.OAK_LEAVES,
//                    BlockTags.DIRT, 1, ImmutableList.of()
//            )
//    );
//
//    public static final ResourceKey<ConfiguredFeature<?, ?>> DECIDUOUS_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("deciduous_tree4",
//            TYGFeatures.TREE_FROM_NBT_V1,
//            () -> new TreeFromStructureNBTConfig(
//                    BiomesWeveGone.id("features/trees/generic_trunk"),
//                    BiomesWeveGone.id("features/trees/deciduous/deciduous_canopy3"),
//                    BiasedToBottomInt.of(5, 8),
//                    BlockStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
//                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.OAK_LEAVES.defaultBlockState(), 1).build()),
//                    Blocks.OAK_LOG,
//                    Blocks.OAK_LEAVES,
//                    BlockTags.DIRT, 8, ImmutableList.of()
//            )
//    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> EBONY_BUSH1 = ConfiguredFeaturesUtil.createConfiguredFeature("ebony_bush1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/ebony/ebony_bush_trunk"),
                    BiomesWeveGone.id("features/trees/ebony/ebony_canopy2"),
                    ConstantInt.of(0)
                    ,
                    BlockStateProvider.simple(BWGWood.EBONY.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.EBONY.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 6
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> EBONY_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("ebony_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/ebony/ebony_trunk1"),
                    BiomesWeveGone.id("features/trees/ebony/ebony_canopy1"),
                    BiasedToBottomInt.of(4, 6),
                    BlockStateProvider.simple(BWGWood.EBONY.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.EBONY.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 6
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> EBONY_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("ebony_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/ebony/ebony_trunk2"),
                    BiomesWeveGone.id("features/trees/ebony/ebony_canopy2"),
                    BiasedToBottomInt.of(4, 6),
                    BlockStateProvider.simple(BWGWood.EBONY.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.EBONY.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 6
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> HOLLY_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("holly_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/holly/holly_canopy1"),
                    BiasedToBottomInt.of(10, 16),
                    BlockStateProvider.simple(BWGWood.HOLLY.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.HOLLY.leaves().defaultBlockState(), 7).add(BWGWood.HOLLY_BERRY_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 6
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> HOLLY_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("holly_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/holly/holly_canopy2"),
                    BiasedToBottomInt.of(8, 14),
                    BlockStateProvider.simple(BWGWood.HOLLY.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.HOLLY.leaves().defaultBlockState(), 7).add(BWGWood.HOLLY_BERRY_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 6
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> HOLLY_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("holly_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/holly/holly_trunk3"),
                    BiomesWeveGone.id("features/trees/holly/holly_canopy3"),
                    BiasedToBottomInt.of(2, 12),
                    BlockStateProvider.simple(BWGWood.HOLLY.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.HOLLY.leaves().defaultBlockState(), 7).add(BWGWood.HOLLY_BERRY_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 6
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> HOLLY_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("holly_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/holly/holly_canopy4"),
                    BiasedToBottomInt.of(10, 16),
                    BlockStateProvider.simple(BWGWood.HOLLY.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.HOLLY.leaves().defaultBlockState(), 7).add(BWGWood.HOLLY_BERRY_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 6
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_ENCHANTED_SAPLING_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("blue_enchanted_sapling_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_trunk_1"),
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_canopy_1"),
                    BiasedToBottomInt.of(2, 5),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.BLUE_ENCHANTED.logstem().defaultBlockState(), 45).build()),
                    BlockStateProvider.simple(BWGWood.BLUE_ENCHANTED.leaves().defaultBlockState()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_ENCHANTED_SAPLING_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("blue_enchanted_sapling_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_trunk_2"),
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_canopy_2"),
                    BiasedToBottomInt.of(2, 5),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.BLUE_ENCHANTED.logstem().defaultBlockState(), 45).build()),
                    BlockStateProvider.simple(BWGWood.BLUE_ENCHANTED.leaves().defaultBlockState()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_ENCHANTED_SAPLING_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("blue_enchanted_sapling_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_trunk_3"),
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_canopy_3"),
                    BiasedToBottomInt.of(2, 5),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.BLUE_ENCHANTED.logstem().defaultBlockState(), 45).build()),
                    BlockStateProvider.simple(BWGWood.BLUE_ENCHANTED.leaves().defaultBlockState()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_ENCHANTED_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("blue_enchanted_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_trunk_1"),
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_canopy_1"),
                    BiasedToBottomInt.of(1, 5),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.IMBUED_BLUE_ENCHANTED_WOOD.get().defaultBlockState().setValue(ImbuedBlock.PERSISTENT, false), 1).add(BWGWood.BLUE_ENCHANTED.logstem().defaultBlockState(), 10).build()),
                    BlockStateProvider.simple(BWGWood.BLUE_ENCHANTED.leaves().defaultBlockState()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_ENCHANTED_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("blue_enchanted_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_trunk_2"),
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_canopy_2"),
                    BiasedToBottomInt.of(1, 5),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.IMBUED_BLUE_ENCHANTED_WOOD.get().defaultBlockState().setValue(ImbuedBlock.PERSISTENT, false), 1).add(BWGWood.BLUE_ENCHANTED.logstem().defaultBlockState(), 10).build()),
                    BlockStateProvider.simple(BWGWood.BLUE_ENCHANTED.leaves().defaultBlockState()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_ENCHANTED_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("blue_enchanted_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_trunk_3"),
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_canopy_3"),
                    BiasedToBottomInt.of(1, 5),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.IMBUED_BLUE_ENCHANTED_WOOD.get().defaultBlockState().setValue(ImbuedBlock.PERSISTENT, false), 1).add(BWGWood.BLUE_ENCHANTED.logstem().defaultBlockState(), 10).build()),
                    BlockStateProvider.simple(BWGWood.BLUE_ENCHANTED.leaves().defaultBlockState()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_ENCHANTED_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("blue_enchanted_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_trunk_4"),
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_canopy_4"),
                    ConstantInt.of(0)
                    ,
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.IMBUED_BLUE_ENCHANTED_WOOD.get().defaultBlockState().setValue(ImbuedBlock.PERSISTENT, false), 1).add(BWGWood.BLUE_ENCHANTED.logstem().defaultBlockState(), 10).build()),
                    BlockStateProvider.simple(BWGWood.BLUE_ENCHANTED.leaves().defaultBlockState()),
                    BWGWood.BLUE_ENCHANTED.wood(),
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );


    public static final ResourceKey<ConfiguredFeature<?, ?>> GREEN_ENCHANTED_SAPLING_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("green_enchanted_sapling_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_trunk_1"),
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_canopy_1"),
                    BiasedToBottomInt.of(2, 5),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.GREEN_ENCHANTED.logstem().defaultBlockState(), 45).build()),
                    BlockStateProvider.simple(BWGWood.GREEN_ENCHANTED.leaves().defaultBlockState()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> GREEN_ENCHANTED_SAPLING_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("green_enchanted_sapling_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_trunk_2"),
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_canopy_2"),
                    BiasedToBottomInt.of(2, 5),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.GREEN_ENCHANTED.logstem().defaultBlockState(), 45).build()),
                    BlockStateProvider.simple(BWGWood.GREEN_ENCHANTED.leaves().defaultBlockState()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> GREEN_ENCHANTED_SAPLING_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("green_enchanted_sapling_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_trunk_3"),
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_canopy_3"),
                    BiasedToBottomInt.of(2, 5),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.GREEN_ENCHANTED.logstem().defaultBlockState(), 45).build()),
                    BlockStateProvider.simple(BWGWood.GREEN_ENCHANTED.leaves().defaultBlockState()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> GREEN_ENCHANTED_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("green_enchanted_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_trunk_1"),
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_canopy_1"),
                    BiasedToBottomInt.of(2, 5),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.IMBUED_GREEN_ENCHANTED_WOOD.get().defaultBlockState().setValue(ImbuedBlock.PERSISTENT, false), 1).add(BWGWood.GREEN_ENCHANTED.logstem().defaultBlockState(), 45).build()),
                    BlockStateProvider.simple(BWGWood.GREEN_ENCHANTED.leaves().defaultBlockState()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(GLOW_BERRY_DECORATOR.get())
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> GREEN_ENCHANTED_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("green_enchanted_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_trunk_2"),
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_canopy_2"),
                    BiasedToBottomInt.of(2, 5),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.IMBUED_GREEN_ENCHANTED_WOOD.get().defaultBlockState().setValue(ImbuedBlock.PERSISTENT, false), 1).add(BWGWood.GREEN_ENCHANTED.logstem().defaultBlockState(), 45).build()),
                    BlockStateProvider.simple(BWGWood.GREEN_ENCHANTED.leaves().defaultBlockState()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(GLOW_BERRY_DECORATOR.get())
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> GREEN_ENCHANTED_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("green_enchanted_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_trunk_3"),
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_canopy_3"),
                    BiasedToBottomInt.of(2, 5),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.IMBUED_GREEN_ENCHANTED_WOOD.get().defaultBlockState().setValue(ImbuedBlock.PERSISTENT, false), 1).add(BWGWood.GREEN_ENCHANTED.logstem().defaultBlockState(), 45).build()),
                    BlockStateProvider.simple(BWGWood.GREEN_ENCHANTED.leaves().defaultBlockState()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(GLOW_BERRY_DECORATOR.get())
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> GREEN_ENCHANTED_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("green_enchanted_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_trunk_4"),
                    BiomesWeveGone.id("features/trees/enchanted/enchanted_canopy_4"),
                    BiasedToBottomInt.of(2, 5),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.IMBUED_GREEN_ENCHANTED_WOOD.get().defaultBlockState().setValue(ImbuedBlock.PERSISTENT, false), 1).add(BWGWood.GREEN_ENCHANTED.logstem().defaultBlockState(), 45).build()),
                    BlockStateProvider.simple(BWGWood.GREEN_ENCHANTED.leaves().defaultBlockState()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> JACARANDA_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("jacaranda_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/jacaranda/jacaranda_trunk_1"),
                    BiomesWeveGone.id("features/trees/jacaranda/jacaranda_canopy_1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.JACARANDA.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.FLOWERING_JACARANDA_LEAVES.get().defaultBlockState(), 1).add(BWGWood.JACARANDA.leaves().defaultBlockState(), 4).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> JACARANDA_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("jacaranda_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/jacaranda/jacaranda_trunk_2"),
                    BiomesWeveGone.id("features/trees/jacaranda/jacaranda_canopy_2"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.JACARANDA.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.FLOWERING_JACARANDA_LEAVES.get().defaultBlockState(), 1).add(BWGWood.JACARANDA.leaves().defaultBlockState(), 4).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> JACARANDA_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("jacaranda_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/jacaranda/jacaranda_trunk_3"),
                    BiomesWeveGone.id("features/trees/jacaranda/jacaranda_canopy_3"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.JACARANDA.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.FLOWERING_JACARANDA_LEAVES.get().defaultBlockState(), 1).add(BWGWood.JACARANDA.leaves().defaultBlockState(), 4).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> JACARANDA_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("jacaranda_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/jacaranda/jacaranda_trunk_4"),
                    BiomesWeveGone.id("features/trees/jacaranda/jacaranda_canopy_4"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.JACARANDA.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.FLOWERING_JACARANDA_LEAVES.get().defaultBlockState(), 1).add(BWGWood.JACARANDA.leaves().defaultBlockState(), 4).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> INDIGO_JACARANDA_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("indigo_jacaranda_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/jacaranda/jacaranda_trunk_1"),
                    BiomesWeveGone.id("features/trees/jacaranda/jacaranda_canopy_1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.JACARANDA.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.FLOWERING_INDIGO_JACARANDA_LEAVES.get().defaultBlockState(), 1).add(BWGWood.INDIGO_JACARANDA_LEAVES.get().defaultBlockState(), 4).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> INDIGO_JACARANDA_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("indigo_jacaranda_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/jacaranda/jacaranda_trunk_2"),
                    BiomesWeveGone.id("features/trees/jacaranda/jacaranda_canopy_2"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.JACARANDA.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.FLOWERING_INDIGO_JACARANDA_LEAVES.get().defaultBlockState(), 1).add(BWGWood.INDIGO_JACARANDA_LEAVES.get().defaultBlockState(), 4).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> INDIGO_JACARANDA_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("indigo_jacaranda_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/jacaranda/jacaranda_trunk_3"),
                    BiomesWeveGone.id("features/trees/jacaranda/jacaranda_canopy_3"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.JACARANDA.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.FLOWERING_INDIGO_JACARANDA_LEAVES.get().defaultBlockState(), 1).add(BWGWood.INDIGO_JACARANDA_LEAVES.get().defaultBlockState(), 4).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> INDIGO_JACARANDA_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("indigo_jacaranda_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/jacaranda/jacaranda_trunk_4"),
                    BiomesWeveGone.id("features/trees/jacaranda/jacaranda_canopy_4"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.JACARANDA.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.FLOWERING_INDIGO_JACARANDA_LEAVES.get().defaultBlockState(), 1).add(BWGWood.INDIGO_JACARANDA_LEAVES.get().defaultBlockState(), 4).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    private static final Supplier<AttachedToLeavesDecorator> YUCCA_FRUIT = () -> new AttachedToFruitLeavesDecorator(0.1F, 2, 0, BWGWood.RIPE_YUCCA_LEAVES.get(), new RandomizedIntStateProvider(BlockStateProvider.simple(BWGBlocks.YUCCA_FRUIT_BLOCK.get().defaultBlockState()), BWGFruitBlock.AGE, UniformInt.of(0, 3)), 2, List.of(Direction.DOWN));

    public static final ResourceKey<ConfiguredFeature<?, ?>> YUCCA_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("yucca_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/yucca/yucca_trunk1"),
                    BiomesWeveGone.id("features/trees/yucca/yucca_canopy1"),
                    BiasedToBottomInt.of(1, 2),
                    BlockStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.YUCCA_LEAVES.get().defaultBlockState(), 10)
                            .add(BWGWood.FLOWERING_YUCCA_LEAVES.get().defaultBlockState(), 2)
                            .add(BWGWood.RIPE_YUCCA_LEAVES.get().defaultBlockState(), 1).build()
                    ),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.SAND, 4, ImmutableList.of(YUCCA_FRUIT.get())
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> YUCCA_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("yucca_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/yucca/yucca_trunk2"),
                    BiomesWeveGone.id("features/trees/yucca/yucca_canopy2"),
                    BiasedToBottomInt.of(5, 8),
                    BlockStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.YUCCA_LEAVES.get().defaultBlockState(), 10)
                            .add(BWGWood.FLOWERING_YUCCA_LEAVES.get().defaultBlockState(), 2)
                            .add(BWGWood.RIPE_YUCCA_LEAVES.get().defaultBlockState(), 1).build()
                    ),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.SAND, 6, ImmutableList.of(YUCCA_FRUIT.get())
            )
    );

    // TODO: BYG Propagule
    public static final Supplier<AttachedToLeavesDecorator> PROPAGULE_DECORATOR = () -> new AttachedToLeavesDecorator(0.14F, 1, 0, new RandomizedIntStateProvider(BlockStateProvider.simple(Blocks.MANGROVE_PROPAGULE.defaultBlockState().setValue(MangrovePropaguleBlock.HANGING, Boolean.TRUE)), MangrovePropaguleBlock.AGE, UniformInt.of(0, 4)), 2, List.of(Direction.DOWN));

    public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_MANGROVE_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("white_mangrove_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/white_mangrove/white_mangrove_base1"),
                    BiomesWeveGone.id("features/trees/white_mangrove/white_mangrove_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.WHITE_MANGROVE.logstem()),
                    BlockStateProvider.simple(BWGWood.WHITE_MANGROVE.leaves()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(new LeaveVineDecorator(1), new TrunkVineDecorator(), MOSS_CARPET.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_MANGROVE_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("white_mangrove_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/white_mangrove/white_mangrove_base2"),
                    BiomesWeveGone.id("features/trees/white_mangrove/white_mangrove_canopy2"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.WHITE_MANGROVE.logstem()),
                    BlockStateProvider.simple(BWGWood.WHITE_MANGROVE.leaves()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(new LeaveVineDecorator(1), new TrunkVineDecorator(), MOSS_CARPET.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_MANGROVE_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("white_mangrove_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/white_mangrove/white_mangrove_base3"),
                    BiomesWeveGone.id("features/trees/white_mangrove/white_mangrove_canopy3"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.WHITE_MANGROVE.logstem()),
                    BlockStateProvider.simple(BWGWood.WHITE_MANGROVE.leaves()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(new LeaveVineDecorator(1), new TrunkVineDecorator(), MOSS_CARPET.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_MANGROVE_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("white_mangrove_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/white_mangrove/white_mangrove_base4"),
                    BiomesWeveGone.id("features/trees/white_mangrove/white_mangrove_canopy4"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.WHITE_MANGROVE.logstem()),
                    BlockStateProvider.simple(BWGWood.WHITE_MANGROVE.leaves()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(new LeaveVineDecorator(1), new TrunkVineDecorator(), MOSS_CARPET.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_MANGROVE_TREE5 = ConfiguredFeaturesUtil.createConfiguredFeature("white_mangrove_tree5",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/white_mangrove/white_mangrove_base5"),
                    BiomesWeveGone.id("features/trees/white_mangrove/white_mangrove_canopy5"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.WHITE_MANGROVE.logstem()),
                    BlockStateProvider.simple(BWGWood.WHITE_MANGROVE.leaves()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(new LeaveVineDecorator(1), new TrunkVineDecorator(), MOSS_CARPET.get())
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> IRONWOOD_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("ironwood_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/ironwood/ironwood_trunk1"),
                    BiomesWeveGone.id("features/trees/ironwood/ironwood_canopy1"),
                    BiasedToBottomInt.of(5, 10),
                    BlockStateProvider.simple(BWGWood.IRONWOOD.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.IRONWOOD.leaves().defaultBlockState(), 4)
                            .build()),
                    Blocks.BIRCH_LOG,
                    Blocks.AZALEA_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> IRONWOOD_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("ironwood_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/ironwood/ironwood_trunk2"),
                    BiomesWeveGone.id("features/trees/ironwood/ironwood_canopy2"),
                    BiasedToBottomInt.of(5, 10),
                    BlockStateProvider.simple(BWGWood.IRONWOOD.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.IRONWOOD.leaves().defaultBlockState(), 4)
                            .build()),
                    Blocks.BIRCH_LOG,
                    Blocks.AZALEA_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> IRONWOOD_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("ironwood_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/ironwood/ironwood_trunk3"),
                    BiomesWeveGone.id("features/trees/ironwood/ironwood_canopy3"),
                    BiasedToBottomInt.of(5, 10),
                    BlockStateProvider.simple(BWGWood.IRONWOOD.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.IRONWOOD.leaves().defaultBlockState(), 4)
                            .build()),
                    Blocks.BIRCH_LOG,
                    Blocks.AZALEA_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> IRONWOOD_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("ironwood_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/ironwood/ironwood_trunk4"),
                    BiomesWeveGone.id("features/trees/ironwood/ironwood_canopy4"),
                    BiasedToBottomInt.of(5, 10),
                    BlockStateProvider.simple(BWGWood.IRONWOOD.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.IRONWOOD.leaves().defaultBlockState(), 4)
                            .build()),
                    Blocks.BIRCH_LOG,
                    Blocks.AZALEA_LEAVES,
                    BlockTags.DIRT, 5
            )
    );


    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWERING_IRONWOOD_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("flowering_ironwood_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/ironwood/ironwood_trunk1"),
                    BiomesWeveGone.id("features/trees/ironwood/ironwood_canopy1"),
                    BiasedToBottomInt.of(5, 10),
                    BlockStateProvider.simple(BWGWood.IRONWOOD.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.IRONWOOD.leaves().defaultBlockState(), 2).add(BWGWood.FLOWERING_IRONWOOD_LEAVES.get().defaultBlockState(), 3).build()),
                    Blocks.BIRCH_LOG,
                    Blocks.AZALEA_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWERING_IRONWOOD_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("flowering_ironwood_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/ironwood/ironwood_trunk2"),
                    BiomesWeveGone.id("features/trees/ironwood/ironwood_canopy2"),
                    BiasedToBottomInt.of(5, 10),
                    BlockStateProvider.simple(BWGWood.IRONWOOD.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.IRONWOOD.leaves().defaultBlockState(), 2).add(BWGWood.FLOWERING_IRONWOOD_LEAVES.get().defaultBlockState(), 3).build()),
                    Blocks.BIRCH_LOG,
                    Blocks.AZALEA_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWERING_IRONWOOD_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("flowering_ironwood_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/ironwood/ironwood_trunk3"),
                    BiomesWeveGone.id("features/trees/ironwood/ironwood_canopy3"),
                    BiasedToBottomInt.of(5, 10),
                    BlockStateProvider.simple(BWGWood.IRONWOOD.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.IRONWOOD.leaves().defaultBlockState(), 2).add(BWGWood.FLOWERING_IRONWOOD_LEAVES.get().defaultBlockState(), 3).build()),
                    Blocks.BIRCH_LOG,
                    Blocks.AZALEA_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWERING_IRONWOOD_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("flowering_ironwood_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/ironwood/ironwood_trunk4"),
                    BiomesWeveGone.id("features/trees/ironwood/ironwood_canopy4"),
                    BiasedToBottomInt.of(5, 10),
                    BlockStateProvider.simple(BWGWood.IRONWOOD.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.IRONWOOD.leaves().defaultBlockState(), 2).add(BWGWood.FLOWERING_IRONWOOD_LEAVES.get().defaultBlockState(), 3).build()),
                    Blocks.BIRCH_LOG,
                    Blocks.AZALEA_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> MAPLE_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("maple_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/maple/maple_trunk1"),
                    BiomesWeveGone.id("features/trees/maple/maple_canopy1"),
                    BiasedToBottomInt.of(4, 6),
                    BlockStateProvider.simple(BWGWood.MAPLE.logstem()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.MAPLE.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> MAPLE_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("maple_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/maple/maple_trunk2"),
                    BiomesWeveGone.id("features/trees/maple/maple_canopy2"),
                    BiasedToBottomInt.of(4, 6),
                    BlockStateProvider.simple(BWGWood.MAPLE.logstem()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.MAPLE.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> MAPLE_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("maple_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/maple/maple_trunk3"),
                    BiomesWeveGone.id("features/trees/maple/maple_canopy3"),
                    BiasedToBottomInt.of(4, 6),
                    BlockStateProvider.simple(BWGWood.MAPLE.logstem()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.MAPLE.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> MAPLE_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("maple_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/maple/maple_trunk4"),
                    BiomesWeveGone.id("features/trees/maple/maple_canopy4"),
                    BiasedToBottomInt.of(4, 6),
                    BlockStateProvider.simple(BWGWood.MAPLE.logstem()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.MAPLE.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> MAPLE_TREE5 = ConfiguredFeaturesUtil.createConfiguredFeature("maple_tree5",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/maple/maple_trunk4"),
                    BiomesWeveGone.id("features/trees/maple/maple_canopy5"),
                    BiasedToBottomInt.of(4, 6),
                    BlockStateProvider.simple(BWGWood.MAPLE.logstem()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.MAPLE.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_MAPLE_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("red_maple_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/maple/maple_trunk1"),
                    BiomesWeveGone.id("features/trees/maple/maple_canopy1"),
                    BiasedToBottomInt.of(4, 6),
                    BlockStateProvider.simple(BWGWood.MAPLE.logstem()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.RED_MAPLE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_MAPLE_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("red_maple_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/maple/maple_trunk2"),
                    BiomesWeveGone.id("features/trees/maple/maple_canopy2"),
                    BiasedToBottomInt.of(4, 6),
                    BlockStateProvider.simple(BWGWood.MAPLE.logstem()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.RED_MAPLE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_MAPLE_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("red_maple_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/maple/maple_trunk3"),
                    BiomesWeveGone.id("features/trees/maple/maple_canopy3"),
                    BiasedToBottomInt.of(4, 6),
                    BlockStateProvider.simple(BWGWood.MAPLE.logstem()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.RED_MAPLE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_MAPLE_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("red_maple_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/maple/maple_trunk4"),
                    BiomesWeveGone.id("features/trees/maple/maple_canopy4"),
                    BiasedToBottomInt.of(4, 6),
                    BlockStateProvider.simple(BWGWood.MAPLE.logstem()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.RED_MAPLE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_MAPLE_TREE5 = ConfiguredFeaturesUtil.createConfiguredFeature("red_maple_tree5",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/maple/maple_trunk4"),
                    BiomesWeveGone.id("features/trees/maple/maple_canopy5"),
                    BiasedToBottomInt.of(4, 6),
                    BlockStateProvider.simple(BWGWood.MAPLE.logstem()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.RED_MAPLE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_MAPLE_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("silver_maple_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/maple/maple_trunk1"),
                    BiomesWeveGone.id("features/trees/maple/maple_canopy1"),
                    BiasedToBottomInt.of(4, 6),
                    BlockStateProvider.simple(BWGWood.MAPLE.logstem()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.SILVER_MAPLE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_MAPLE_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("silver_maple_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/maple/maple_trunk2"),
                    BiomesWeveGone.id("features/trees/maple/maple_canopy2"),
                    BiasedToBottomInt.of(4, 6),
                    BlockStateProvider.simple(BWGWood.MAPLE.logstem()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.SILVER_MAPLE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_MAPLE_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("silver_maple_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/maple/maple_trunk3"),
                    BiomesWeveGone.id("features/trees/maple/maple_canopy3"),
                    BiasedToBottomInt.of(4, 6),
                    BlockStateProvider.simple(BWGWood.MAPLE.logstem()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.SILVER_MAPLE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_MAPLE_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("silver_maple_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/maple/maple_trunk4"),
                    BiomesWeveGone.id("features/trees/maple/maple_canopy4"),
                    BiasedToBottomInt.of(4, 6),
                    BlockStateProvider.simple(BWGWood.MAPLE.logstem()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.SILVER_MAPLE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_MAPLE_TREE5 = ConfiguredFeaturesUtil.createConfiguredFeature("silver_maple_tree5",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/maple/maple_trunk4"),
                    BiomesWeveGone.id("features/trees/maple/maple_canopy5"),
                    BiasedToBottomInt.of(4, 6),
                    BlockStateProvider.simple(BWGWood.MAPLE.logstem()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.SILVER_MAPLE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> MEADOW_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("meadow_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/meadow/meadow_canopy1"),
                    BiasedToBottomInt.of(6, 8),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.OAK_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEADOW_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("meadow_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/meadow/meadow_canopy2"),
                    BiasedToBottomInt.of(6, 8),
                    BlockStateProvider.simple(Blocks.DARK_OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.DARK_OAK_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.DARK_OAK_LOG,
                    Blocks.DARK_OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEADOW_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("meadow_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/meadow/meadow_canopy3"),
                    BiasedToBottomInt.of(6, 8),
                    BlockStateProvider.simple(Blocks.DARK_OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.DARK_OAK_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.DARK_OAK_LOG,
                    Blocks.DARK_OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("oak_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/oak/oak_trunk1"),
                    BiomesWeveGone.id("features/trees/oak/oak_canopy1"),
                    BiasedToBottomInt.of(1, 2),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.OAK_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(MOSS_CARPET.get(), SHELF_FUNGI.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("oak_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/oak/oak_trunk2"),
                    BiomesWeveGone.id("features/trees/oak/oak_canopy2"),
                    BiasedToBottomInt.of(1, 2),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.OAK_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(MOSS_CARPET.get(), SHELF_FUNGI.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("oak_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/oak/oak_canopy3"),
                    BiasedToBottomInt.of(5, 6),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.OAK_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(MOSS_CARPET.get(), SHELF_FUNGI.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_TREE_LARGE1 = ConfiguredFeaturesUtil.createConfiguredFeature("large_oak_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/oak/large_oak_trunk1"),
                    BiomesWeveGone.id("features/trees/oak/large_oak_canopy1"),
                    BiasedToBottomInt.of(2, 4),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.OAK_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(MOSS_CARPET.get(), SHELF_FUNGI.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_TREE_LARGE2 = ConfiguredFeaturesUtil.createConfiguredFeature("large_oak_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/oak/large_oak_trunk2"),
                    BiomesWeveGone.id("features/trees/oak/large_oak_canopy2"),
                    BiasedToBottomInt.of(1, 3),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.OAK_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(MOSS_CARPET.get(), SHELF_FUNGI.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_TREE_LARGE3 = ConfiguredFeaturesUtil.createConfiguredFeature("large_oak_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/oak/large_oak_trunk3"),
                    BiomesWeveGone.id("features/trees/oak/large_oak_canopy3"),
                    BiasedToBottomInt.of(1, 3),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.OAK_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(MOSS_CARPET.get(), SHELF_FUNGI.get())
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_OAK_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("red_oak_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/oak/oak_trunk1"),
                    BiomesWeveGone.id("features/trees/oak/red_oak_canopy1"),
                    BiasedToBottomInt.of(1, 2),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.RED_OAK_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_OAK_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("red_oak_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/oak/oak_trunk2"),
                    BiomesWeveGone.id("features/trees/oak/red_oak_canopy2"),
                    BiasedToBottomInt.of(1, 2),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.RED_OAK_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_OAK_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("red_oak_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/oak/red_oak_canopy3"),
                    BiasedToBottomInt.of(5, 6),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.RED_OAK_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_OAK_TREE_LARGE1 = ConfiguredFeaturesUtil.createConfiguredFeature("large_red_oak_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/oak/large_oak_trunk1"),
                    BiomesWeveGone.id("features/trees/oak/large_red_oak_canopy1"),
                    BiasedToBottomInt.of(2, 4),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.RED_OAK_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_OAK_TREE_LARGE2 = ConfiguredFeaturesUtil.createConfiguredFeature("large_red_oak_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/oak/large_oak_trunk2"),
                    BiomesWeveGone.id("features/trees/oak/large_red_oak_canopy2"),
                    BiasedToBottomInt.of(1, 3),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.RED_OAK_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_OAK_TREE_LARGE3 = ConfiguredFeaturesUtil.createConfiguredFeature("large_red_oak_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/oak/large_oak_trunk3"),
                    BiomesWeveGone.id("features/trees/oak/large_red_oak_canopy3"),
                    BiasedToBottomInt.of(1, 3),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.RED_OAK_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> BROWN_OAK_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("brown_oak_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/oak/oak_trunk1"),
                    BiomesWeveGone.id("features/trees/oak/brown_oak_canopy1"),
                    BiasedToBottomInt.of(1, 2),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.BROWN_OAK_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> BROWN_OAK_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("brown_oak_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/oak/oak_trunk1"),
                    BiomesWeveGone.id("features/trees/oak/brown_oak_canopy2"),
                    BiasedToBottomInt.of(1, 2),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.BROWN_OAK_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> BROWN_OAK_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("brown_oak_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/oak/brown_oak_canopy3"),
                    BiasedToBottomInt.of(5, 6),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.BROWN_OAK_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> BROWN_OAK_TREE_LARGE1 = ConfiguredFeaturesUtil.createConfiguredFeature("large_brown_oak_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/oak/large_oak_trunk1"),
                    BiomesWeveGone.id("features/trees/oak/large_brown_oak_canopy1"),
                    BiasedToBottomInt.of(2, 4),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.BROWN_OAK_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> BROWN_OAK_TREE_LARGE2 = ConfiguredFeaturesUtil.createConfiguredFeature("large_brown_oak_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/oak/large_oak_trunk2"),
                    BiomesWeveGone.id("features/trees/oak/large_brown_oak_canopy2"),
                    BiasedToBottomInt.of(1, 3),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.BROWN_OAK_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> BROWN_OAK_TREE_LARGE3 = ConfiguredFeaturesUtil.createConfiguredFeature("large_brown_oak_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/oak/large_oak_trunk3"),
                    BiomesWeveGone.id("features/trees/oak/large_brown_oak_canopy3"),
                    BiasedToBottomInt.of(1, 3),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.BROWN_OAK_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_OAK_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("orange_oak_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/oak/oak_trunk1"),
                    BiomesWeveGone.id("features/trees/oak/orange_oak_canopy1"),
                    BiasedToBottomInt.of(1, 2),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ORANGE_OAK_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_OAK_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("orange_oak_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/oak/oak_trunk2"),
                    BiomesWeveGone.id("features/trees/oak/orange_oak_canopy2"),
                    BiasedToBottomInt.of(1, 2),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ORANGE_OAK_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_OAK_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("orange_oak_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/oak/orange_oak_canopy3"),
                    BiasedToBottomInt.of(5, 6),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ORANGE_OAK_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_OAK_TREE_LARGE1 = ConfiguredFeaturesUtil.createConfiguredFeature("large_orange_oak_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/oak/large_oak_trunk1"),
                    BiomesWeveGone.id("features/trees/oak/large_orange_oak_canopy1"),
                    BiasedToBottomInt.of(2, 4),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ORANGE_OAK_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_OAK_TREE_LARGE2 = ConfiguredFeaturesUtil.createConfiguredFeature("large_orange_oak_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/oak/large_oak_trunk2"),
                    BiomesWeveGone.id("features/trees/oak/large_orange_oak_canopy2"),
                    BiasedToBottomInt.of(1, 3),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ORANGE_OAK_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_OAK_TREE_LARGE3 = ConfiguredFeaturesUtil.createConfiguredFeature("large_orange_oak_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/oak/large_oak_trunk2"),
                    BiomesWeveGone.id("features/trees/oak/large_orange_oak_canopy2"),
                    BiasedToBottomInt.of(1, 3),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ORANGE_OAK_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    private static final Supplier<AttachedToLeavesDecorator> APPLE_FRUIT = () -> new AttachedToFruitLeavesDecorator(0.1F, 2, 0, BWGWood.RIPE_ORCHARD_LEAVES.get(), new RandomizedIntStateProvider(BlockStateProvider.simple(BWGBlocks.APPLE_FRUIT_BLOCK.get().defaultBlockState()), BWGFruitBlock.AGE, UniformInt.of(0, 3)), 2, List.of(Direction.DOWN));

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORCHARD_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("orchard_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/orchard/orchard_trunk1"),
                    BiomesWeveGone.id("features/trees/orchard/orchard_canopy1"),
                    BiasedToBottomInt.of(6, 10),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.ORCHARD_LEAVES.get().defaultBlockState(), 10)
                            .add(BWGWood.FLOWERING_ORCHARD_LEAVES.get().defaultBlockState(), 1)
                            .add(BWGWood.RIPE_ORCHARD_LEAVES.get().defaultBlockState(), 1).build()
                    ),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(APPLE_FRUIT.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORCHARD_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("orchard_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/orchard/orchard_trunk2"),
                    BiomesWeveGone.id("features/trees/orchard/orchard_canopy2"),
                    BiasedToBottomInt.of(6, 11),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.ORCHARD_LEAVES.get().defaultBlockState(), 10)
                            .add(BWGWood.FLOWERING_ORCHARD_LEAVES.get().defaultBlockState(), 1)
                            .add(BWGWood.RIPE_ORCHARD_LEAVES.get().defaultBlockState(), 1).build()
                    ),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(APPLE_FRUIT.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORCHARD_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("orchard_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/orchard/orchard_canopy3"),
                    UniformInt.of(8, 11),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.ORCHARD_LEAVES.get().defaultBlockState(), 10)
                            .add(BWGWood.FLOWERING_ORCHARD_LEAVES.get().defaultBlockState(), 1)
                            .add(BWGWood.RIPE_ORCHARD_LEAVES.get().defaultBlockState(), 1).build()
                    ),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(APPLE_FRUIT.get())
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORCHARD_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("orchard_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/oak/large_oak_trunk1"),
                    BiomesWeveGone.id("features/trees/oak/large_oak_canopy1"),
                    BiasedToBottomInt.of(2, 4),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.ORCHARD_LEAVES.get().defaultBlockState(), 10)
                            .add(BWGWood.FLOWERING_ORCHARD_LEAVES.get().defaultBlockState(), 1)
                            .add(BWGWood.RIPE_ORCHARD_LEAVES.get().defaultBlockState(), 1).build()
                    ),                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(APPLE_FRUIT.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORCHARD_TREE5 = ConfiguredFeaturesUtil.createConfiguredFeature("orchard_tree5",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/oak/large_oak_trunk2"),
                    BiomesWeveGone.id("features/trees/oak/large_oak_canopy2"),
                    BiasedToBottomInt.of(1, 3),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.ORCHARD_LEAVES.get().defaultBlockState(), 10)
                            .add(BWGWood.FLOWERING_ORCHARD_LEAVES.get().defaultBlockState(), 1)
                            .add(BWGWood.RIPE_ORCHARD_LEAVES.get().defaultBlockState(), 1).build()
                    ),                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(APPLE_FRUIT.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> PALM_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("palm_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/palm/palm_trunk"),
                    BiomesWeveGone.id("features/trees/palm/palm_canopy1"),
                    BiasedToBottomInt.of(1, 1),
                    BlockStateProvider.simple(BWGWood.PALM.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.PALM.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.SAND, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> PALM_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("palm_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/palm/palm_trunk"),
                    BiomesWeveGone.id("features/trees/palm/palm_canopy2"),
                    BiasedToBottomInt.of(3, 4),
                    BlockStateProvider.simple(BWGWood.PALM.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.PALM.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.SAND, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> PALM_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("palm_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/palm/palm_trunk"),
                    BiomesWeveGone.id("features/trees/palm/palm_canopy3"),
                    BiasedToBottomInt.of(1, 2),
                    BlockStateProvider.simple(BWGWood.PALM.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.PALM.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.SAND, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> PALM_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("palm_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/palm/palm_trunk4"),
                    BiomesWeveGone.id("features/trees/palm/palm_canopy4"),
                    BiasedToBottomInt.of(3, 4),
                    BlockStateProvider.simple(BWGWood.PALM.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.PALM.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.SAND, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> PINE_LARGE_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("large_pine_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/pine/large_pine_trunk1"),
                    BiomesWeveGone.id("features/trees/pine/large_pine_canopy2"),
                    BiasedToBottomInt.of(7, 9),
                    BlockStateProvider.simple(BWGWood.PINE.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.PINE.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> PINE_LARGE_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("large_pine_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/pine/large_pine_canopy1"),
                    BiasedToBottomInt.of(7, 9),
                    BlockStateProvider.simple(BWGWood.PINE.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.PINE.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> PINE_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("pine_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/pine/pine_canopy1"),
                    BiasedToBottomInt.of(9, 12),
                    BlockStateProvider.simple(BWGWood.PINE.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.PINE.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> PINE_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("pine_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/pine/pine_canopy2"),
                    BiasedToBottomInt.of(9, 12),
                    BlockStateProvider.simple(BWGWood.PINE.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.PINE.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> PALO_VERDE_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("palo_verde_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/palo-verde/palo_verde_canopy1"),
                    BiasedToBottomInt.of(1, 4),
                    BlockStateProvider.simple(BWGWood.PALO_VERDE_LOG.get()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.PALO_VERDE_LEAVES.get().defaultBlockState(), 10)
                            .add(BWGWood.FLOWERING_PALO_VERDE_LEAVES.get().defaultBlockState(), 1)
                            .build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.SAND, 2
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> PALO_VERDE_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("palo_verde_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/palo-verde/palo_verde_canopy2"),
                    BiasedToBottomInt.of(1, 4),
                    BlockStateProvider.simple(BWGWood.PALO_VERDE_LOG.get()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.PALO_VERDE_LEAVES.get().defaultBlockState(), 1)
                            .add(BWGWood.FLOWERING_PALO_VERDE_LEAVES.get().defaultBlockState(), 1)
                            .build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.SAND, 2
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> RAINBOW_EUCALYPTUS_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("rainbow_eucalyptus_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/rainbow-eucalyptus/rainbow_eucalyptus_tree_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.RAINBOW_EUCALYPTUS.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.RAINBOW_EUCALYPTUS.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(new TYGLeavesVineDecorator((VineBlock) Blocks.VINE, 0.05F), new TYGTrunkVineDecorator((VineBlock) Blocks.VINE, 0.05F))
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> RAINBOW_EUCALYPTUS_LARGE_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("rainbow_eucalyptus_large_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/rainbow-eucalyptus/rainbow_eucalyptus_tree_large_trunk1"),
                    BiomesWeveGone.id("features/trees/rainbow-eucalyptus/rainbow_eucalyptus_tree_large_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.RAINBOW_EUCALYPTUS.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.RAINBOW_EUCALYPTUS.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(new TYGTrunkVineDecorator((VineBlock) Blocks.VINE, 0.05F), new TYGLeavesVineDecorator((VineBlock) Blocks.VINE, 0.05F))
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> REDWOOD_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("redwood_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/redwood/redwood_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/redwood/redwood_tree_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.REDWOOD.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.REDWOOD.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> REDWOOD_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("redwood_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/redwood/redwood_tree_trunk2"),
                    BiomesWeveGone.id("features/trees/redwood/redwood_tree_canopy2"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.REDWOOD.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.REDWOOD.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> REDWOOD_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("redwood_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/redwood/redwood_tree_trunk3"),
                    BiomesWeveGone.id("features/trees/redwood/redwood_tree_canopy3"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.REDWOOD.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.REDWOOD.leaves().defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SHRUB_MEADOW = ConfiguredFeaturesUtil.createConfiguredFeature("meadow_shrub1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/meadow/meadow_shrub_canopy1"),
                    ConstantInt.of(0),
                    BlockStateProvider.simple(Blocks.DARK_OAK_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.OAK_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.DARK_OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SHRUB_MEADOW2 = ConfiguredFeaturesUtil.createConfiguredFeature("meadow_shrub2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/meadow/meadow_shrub_canopy2"),
                    ConstantInt.of(0),
                    BlockStateProvider.simple(Blocks.DARK_OAK_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.OAK_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.DARK_OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SHRUB_PRAIRIE1 = ConfiguredFeaturesUtil.createConfiguredFeature("prairie_shrub1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/prairie/prairie_shrub_canopy1"),
                    ConstantInt.of(0),
                    BlockStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.OAK_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 0
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SHRUB_PRAIRIE2 = ConfiguredFeaturesUtil.createConfiguredFeature("prairie_shrub2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/prairie/prairie_shrub_canopy2"),
                    ConstantInt.of(0),
                    BlockStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.OAK_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 0
            )
    );

    private static final Supplier<AttachedToLeavesDecorator> GREEN_APPLE_FRUIT = () -> new AttachedToFruitLeavesDecorator(0.1F, 2, 0, BWGWood.SKYRIS_LEAVES_GREEN_APPLE.get(), new RandomizedIntStateProvider(BlockStateProvider.simple(BWGBlocks.GREEN_APPLE_FRUIT_BLOCK.get().defaultBlockState()), BWGFruitBlock.AGE, UniformInt.of(0, 3)), 2, List.of(Direction.DOWN));

    public static final ResourceKey<ConfiguredFeature<?, ?>> SKYRIS_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("skyris_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/skyris/skyris_trunk_1"),
                    BiomesWeveGone.id("features/trees/skyris/skyris_canopy_1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.SKYRIS.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.SKYRIS.leaves().defaultBlockState(), 10)
                            .add(BWGWood.FLOWERING_SKYRIS_LEAVES.get().defaultBlockState(), 2)
                            .add(BWGWood.SKYRIS_LEAVES_GREEN_APPLE.get().defaultBlockState(), 1).build()
                    ),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(new TYGLeavesVineDecorator(BWGBlocks.SKYRIS_VINE.get(), 0.1F), new TYGTrunkVineDecorator(BWGBlocks.SKYRIS_VINE.get(), 0.3F), GREEN_APPLE_FRUIT.get())
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SKYRIS_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("skyris_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/skyris/skyris_trunk_2"),
                    BiomesWeveGone.id("features/trees/skyris/skyris_canopy_2"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.SKYRIS.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.SKYRIS.leaves().defaultBlockState(), 10)
                            .add(BWGWood.FLOWERING_SKYRIS_LEAVES.get().defaultBlockState(), 2)
                            .add(BWGWood.SKYRIS_LEAVES_GREEN_APPLE.get().defaultBlockState(), 1).build()
                    ),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(new TYGLeavesVineDecorator(BWGBlocks.SKYRIS_VINE.get(), 0.1F), new TYGTrunkVineDecorator(BWGBlocks.SKYRIS_VINE.get(), 0.3F), GREEN_APPLE_FRUIT.get())
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SKYRIS_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("skyris_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/skyris/skyris_trunk_3"),
                    BiomesWeveGone.id("features/trees/skyris/skyris_canopy_3"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.SKYRIS.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.SKYRIS.leaves().defaultBlockState(), 10)
                            .add(BWGWood.FLOWERING_SKYRIS_LEAVES.get().defaultBlockState(), 2)
                            .add(BWGWood.SKYRIS_LEAVES_GREEN_APPLE.get().defaultBlockState(), 1).build()
                    ),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(new TYGLeavesVineDecorator(BWGBlocks.SKYRIS_VINE.get(), 0.1F), new TYGTrunkVineDecorator(BWGBlocks.SKYRIS_VINE.get(), 0.3F), GREEN_APPLE_FRUIT.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SKYRIS_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("skyris_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/skyris/skyris_trunk_4"),
                    BiomesWeveGone.id("features/trees/skyris/skyris_canopy_4"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.SKYRIS.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.SKYRIS.leaves().defaultBlockState(), 10)
                            .add(BWGWood.FLOWERING_SKYRIS_LEAVES.get().defaultBlockState(), 2)
                            .add(BWGWood.SKYRIS_LEAVES_GREEN_APPLE.get().defaultBlockState(), 1).build()
                    ),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(new TYGLeavesVineDecorator(BWGBlocks.SKYRIS_VINE.get(), 0.1F), new TYGTrunkVineDecorator(BWGBlocks.SKYRIS_VINE.get(), 0.3F), GREEN_APPLE_FRUIT.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SKYRIS_TREE5 = ConfiguredFeaturesUtil.createConfiguredFeature("skyris_tree5",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/skyris/skyris_trunk_5"),
                    BiomesWeveGone.id("features/trees/skyris/skyris_canopy_5"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.SKYRIS.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.SKYRIS.leaves().defaultBlockState(), 10)
                            .add(BWGWood.FLOWERING_SKYRIS_LEAVES.get().defaultBlockState(), 2)
                            .add(BWGWood.SKYRIS_LEAVES_GREEN_APPLE.get().defaultBlockState(), 1).build()
                    ),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(new TYGLeavesVineDecorator(BWGBlocks.SKYRIS_VINE.get(), 0.1F), new TYGTrunkVineDecorator(BWGBlocks.SKYRIS_VINE.get(), 0.3F), GREEN_APPLE_FRUIT.get())
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SKYRIS_TREE6 = ConfiguredFeaturesUtil.createConfiguredFeature("skyris_tree6",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/skyris/skyris_trunk_6"),
                    BiomesWeveGone.id("features/trees/skyris/skyris_canopy_6"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.SKYRIS.logstem().defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.SKYRIS.leaves().defaultBlockState(), 10)
                            .add(BWGWood.FLOWERING_SKYRIS_LEAVES.get().defaultBlockState(), 2)
                            .add(BWGWood.SKYRIS_LEAVES_GREEN_APPLE.get().defaultBlockState(), 1).build()
                    ),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(new TYGLeavesVineDecorator(BWGBlocks.SKYRIS_VINE.get(), 0.1F), new TYGTrunkVineDecorator(BWGBlocks.SKYRIS_VINE.get(), 0.3F), GREEN_APPLE_FRUIT.get())
            )
    );


    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.SPRUCE_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_canopy2"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.SPRUCE_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_canopy3"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.SPRUCE_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_canopy4"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.SPRUCE_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_TREE_MEDIUM1 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_tree_medium1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.SPRUCE_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_TREE_MEDIUM2 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_tree_medium2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_trunk2"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_canopy2"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.SPRUCE_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_TREE_MEDIUM3 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_tree_medium3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_trunk2"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_canopy3"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.SPRUCE_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_TREE_MEDIUM4 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_tree_medium4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_trunk2"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_canopy4"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.SPRUCE_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_TREE_LARGE1 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_tree_large1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_large_trunk1"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_large_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.SPRUCE_LEAVES.defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    //Yellow Spruce
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_YELLOW_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_yellow_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.YELLOW_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_YELLOW_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_yellow_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_canopy2"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.YELLOW_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_YELLOW_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_yellow_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_canopy3"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.YELLOW_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_YELLOW_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_yellow_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_canopy4"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.YELLOW_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_YELLOW_TREE_MEDIUM1 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_yellow_tree_medium1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.YELLOW_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_YELLOW_TREE_MEDIUM2 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_yellow_tree_medium2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_trunk2"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_canopy2"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.YELLOW_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_YELLOW_TREE_MEDIUM3 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_yellow_tree_medium3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_trunk2"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_canopy3"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.YELLOW_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_YELLOW_TREE_MEDIUM4 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_yellow_tree_medium4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_trunk2"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_canopy4"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.YELLOW_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_YELLOW_TREE_LARGE1 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_yellow_tree_large1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_large_trunk1"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_large_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.YELLOW_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    //Orange Spruce
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_ORANGE_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_orange_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ORANGE_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_ORANGE_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_orange_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_canopy2"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ORANGE_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_ORANGE_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_orange_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_canopy3"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ORANGE_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_ORANGE_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_orange_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_canopy4"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ORANGE_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_ORANGE_TREE_MEDIUM1 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_orange_tree_medium1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ORANGE_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_ORANGE_TREE_MEDIUM2 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_orange_tree_medium2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_trunk2"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_canopy2"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ORANGE_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_ORANGE_TREE_MEDIUM3 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_orange_tree_medium3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_trunk2"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_canopy3"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ORANGE_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_ORANGE_TREE_MEDIUM4 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_orange_tree_medium4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_trunk2"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_canopy4"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ORANGE_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_ORANGE_TREE_LARGE1 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_orange_tree_large1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_large_trunk1"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_large_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.ORANGE_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    //Red Spruce
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_SPRUCE_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_red_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.RED_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_SPRUCE_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_red_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_canopy2"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.RED_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_SPRUCE_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_red_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_canopy3"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.RED_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_SPRUCE_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_red_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_canopy4"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.RED_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_SPRUCE_TREE_MEDIUM1 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_red_tree_medium1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.RED_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_SPRUCE_TREE_MEDIUM2 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_red_tree_medium2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_trunk2"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_canopy2"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.RED_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_SPRUCE_TREE_MEDIUM3 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_red_tree_medium3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_trunk2"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_canopy3"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.RED_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_SPRUCE_TREE_MEDIUM4 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_red_tree_medium4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_trunk2"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_canopy4"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.RED_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_SPRUCE_TREE_LARGE1 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_red_tree_large1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_large_trunk1"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_large_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.RED_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_SPRUCE_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_blue_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.BLUE_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_SPRUCE_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_blue_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_canopy2"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.BLUE_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_SPRUCE_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_blue_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_canopy3"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.BLUE_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_SPRUCE_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_blue_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_canopy4"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.BLUE_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_SPRUCE_TREE_MEDIUM1 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_blue_tree_medium1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.BLUE_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_SPRUCE_TREE_MEDIUM2 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_blue_tree_medium2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_trunk2"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_canopy2"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.BLUE_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_SPRUCE_TREE_MEDIUM3 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_blue_tree_medium3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_trunk2"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_canopy3"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.BLUE_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_SPRUCE_TREE_MEDIUM4 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_blue_tree_medium4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_trunk2"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_medium_canopy4"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.BLUE_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_SPRUCE_TREE_LARGE1 = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_blue_tree_large1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_large_trunk1"),
                    BiomesWeveGone.id("features/trees/spruce/spruce_tree_large_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.BLUE_SPRUCE_LEAVES.get().defaultBlockState(), 1).build()),
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> TROPICAL_SHRUB1 = ConfiguredFeaturesUtil.createConfiguredFeature("tropical_shrub1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/generic_trunk"),
                    BiomesWeveGone.id("features/trees/mahogany/tropical_shrub_canopy1"),
                    ConstantInt.of(0)
                    ,
                    BlockStateProvider.simple(BWGWood.MAHOGANY.logstem()),
                    BlockStateProvider.simple(BWGWood.MAHOGANY.leaves()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 0, ImmutableList.of(new TYGLeavesVineDecorator((VineBlock) Blocks.VINE, 0.5F), new TYGTrunkVineDecorator((VineBlock) Blocks.VINE, 1F))
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> MAHOGANY_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("mahogany_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/mahogany/mahogany_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/mahogany/mahogany_tree_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.MAHOGANY.logstem()),
                    BlockStateProvider.simple(BWGWood.MAHOGANY.leaves()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(new TYGLeavesVineDecorator((VineBlock) Blocks.VINE, 0.5F), new TYGTrunkVineDecorator((VineBlock) Blocks.VINE, 1F), new CocoaDecorator(0.2F))
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAHOGANY_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("mahogany_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/mahogany/mahogany_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/mahogany/mahogany_tree_canopy2"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.MAHOGANY.logstem()),
                    BlockStateProvider.simple(BWGWood.MAHOGANY.leaves()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(new TYGLeavesVineDecorator((VineBlock) Blocks.VINE, 0.4F), new TYGTrunkVineDecorator((VineBlock) Blocks.VINE, 1F), new CocoaDecorator(0.2F))
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAHOGANY_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("mahogany_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/mahogany/mahogany_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/mahogany/mahogany_tree_canopy3"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.MAHOGANY.logstem()),
                    BlockStateProvider.simple(BWGWood.MAHOGANY.leaves()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(new TYGLeavesVineDecorator((VineBlock) Blocks.VINE, 0.4F), new TYGTrunkVineDecorator((VineBlock) Blocks.VINE, 1F), new CocoaDecorator(0.2F))
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAHOGANY_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("mahogany_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/mahogany/mahogany_tree_trunk4"),
                    BiomesWeveGone.id("features/trees/mahogany/mahogany_tree_canopy4"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.MAHOGANY.logstem()),
                    BlockStateProvider.simple(BWGWood.MAHOGANY.leaves()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(new TYGLeavesVineDecorator((VineBlock) Blocks.VINE, 0.4F), new TYGTrunkVineDecorator((VineBlock) Blocks.VINE, 1F), new CocoaDecorator(0.2F))
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> FORGOTTEN_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("forgotten_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/forgotten/forgotten_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/forgotten/forgotten_tree_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.DARK_OAK_LOG),
                    BlockStateProvider.simple(Blocks.DARK_OAK_LEAVES),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(new TYGLeavesVineDecorator((VineBlock) Blocks.VINE, 0.2F), GLOW_BERRY_DECORATOR.get(), new TrunkVineDecorator(), MOSS_CARPET.get(), SHELF_FUNGI.get())
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> WOODLANDS_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("woodlands_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/woodlands/woodlands_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/woodlands/woodlands_tree_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    BlockStateProvider.simple(Blocks.OAK_LEAVES),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(GLOW_BERRY_DECORATOR.get(), MOSS_CARPET.get(), SHELF_FUNGI.get())
            )
    );



    public static final ResourceKey<ConfiguredFeature<?, ?>> WOODLANDS_TREE_LARGE1 = ConfiguredFeaturesUtil.createConfiguredFeature("woodlands_tree_large1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/woodlands/woodlands_tree_large_trunk1"),
                    BiomesWeveGone.id("features/trees/woodlands/woodlands_tree_large_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.BIRCH_LOG),
                    BlockStateProvider.simple(Blocks.BIRCH_LEAVES),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(GLOW_BERRY_DECORATOR.get(), MOSS_CARPET.get(), SHELF_FUNGI.get())
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> WOODLANDS_TREE_LARGE2 = ConfiguredFeaturesUtil.createConfiguredFeature("woodlands_tree_large2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/woodlands/woodlands_tree_large_trunk1"),
                    BiomesWeveGone.id("features/trees/woodlands/woodlands_tree_large_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(Blocks.BIRCH_LOG),
                    BlockStateProvider.simple(BWGWood.YELLOW_BIRCH_LEAVES.get()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(GLOW_BERRY_DECORATOR.get(), MOSS_CARPET.get(), SHELF_FUNGI.get())
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> WOODLANDS_STUMP1 = ConfiguredFeaturesUtil.createConfiguredFeature("woodlands_stump1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/woodlands/woodlands_stump_trunk1"),
                    BiomesWeveGone.id("features/trees/woodlands/woodlands_stump_canopy1"),
                    BiasedToBottomInt.of(2, 5),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.OAK_LOG.defaultBlockState(), 35).add(Blocks.MOSS_BLOCK.defaultBlockState(), 8).build()),
                    BlockStateProvider.simple(Blocks.OAK_LEAVES),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5, ImmutableList.of(GLOW_BERRY_DECORATOR.get(), new TYGTrunkVineDecorator((VineBlock) Blocks.VINE, 1F), MOSS_CARPET.get(), SHELF_FUNGI.get())
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> WILLOW_DEAD_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("dead_willow_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/willow/dead_willow_trunk"),
                    BiomesWeveGone.id("features/trees/willow/dead_willow_canopy"),
                    BiasedToBottomInt.of(5, 7),
                    BlockStateProvider.simple(BWGWood.WILLOW.logstem()),
                    BlockStateProvider.simple(BWGWood.WILLOW.leaves()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 10
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> WILLOW_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("willow_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/willow/willow_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/willow/willow_tree_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.WILLOW.logstem()),
                    BlockStateProvider.simple(BWGWood.WILLOW.leaves()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 10, ImmutableList.of(new LeaveVineDecorator(1), new TrunkVineDecorator(), MOSS_CARPET.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> WILLOW_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("willow_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/willow/willow_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/willow/willow_tree_canopy2"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.WILLOW.logstem()),
                    BlockStateProvider.simple(BWGWood.WILLOW.leaves()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 10, ImmutableList.of(new LeaveVineDecorator(1), new TrunkVineDecorator(), MOSS_CARPET.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> WILLOW_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("willow_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/willow/willow_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/willow/willow_tree_canopy3"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.WILLOW.logstem()),
                    BlockStateProvider.simple(BWGWood.WILLOW.leaves()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 10, ImmutableList.of(new LeaveVineDecorator(1), new TrunkVineDecorator(), MOSS_CARPET.get())
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> WILLOW_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("willow_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/willow/willow_tree_trunk2"),
                    BiomesWeveGone.id("features/trees/willow/willow_tree_canopy4"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.WILLOW.logstem()),
                    BlockStateProvider.simple(BWGWood.WILLOW.leaves()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 10, ImmutableList.of(new LeaveVineDecorator(1), new TrunkVineDecorator(), MOSS_CARPET.get())
            )
    );

    private static final Supplier<AttachedToLeavesDecorator> SOUL_FRUIT = () -> new AttachedToFruitLeavesDecorator(0.06F, 8, 8, BWGWood.FLOWERING_SPIRIT_LEAVES.get(), new RandomizedIntStateProvider(BlockStateProvider.simple(BWGBlocks.SOUL_FRUIT_BLOCK.get().defaultBlockState()), BWGFruitBlock.AGE, UniformInt.of(0, 3)), 2, List.of(Direction.DOWN));

    public static final ResourceKey<ConfiguredFeature<?, ?>> SPIRIT_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("spirit_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spirit/spirit_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/spirit/spirit_tree_canopy1"),
                    BiasedToBottomInt.of(8, 15),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.SPIRIT_ROOTS.get().defaultBlockState(), 5).add(BWGWood.SPIRIT.logstem().defaultBlockState(), 10).build()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.SPIRIT_LEAVES.get().defaultBlockState(), 10)
                            .add(BWGWood.FLOWERING_SPIRIT_LEAVES.get().defaultBlockState(), 3).build()
                    ),
                    BWGWood.SPIRIT.logstem(),
                    BWGWood.SPIRIT_LEAVES.get(),
                    BlockTags.DIRT, 10, ImmutableList.of(new LeaveVineDecorator(1), new TrunkVineDecorator(), SOUL_FRUIT.get())
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SPIRIT_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("spirit_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spirit/spirit_tree_trunk2"),
                    BiomesWeveGone.id("features/trees/spirit/spirit_tree_canopy2"),
                    BiasedToBottomInt.of(8, 15),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.SPIRIT_ROOTS.get().defaultBlockState(), 5).add(BWGWood.SPIRIT.logstem().defaultBlockState(), 10).build()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.SPIRIT_LEAVES.get().defaultBlockState(), 10)
                            .add(BWGWood.FLOWERING_SPIRIT_LEAVES.get().defaultBlockState(), 3).build()
                    ),
                    BWGWood.SPIRIT.logstem(),
                    BWGWood.SPIRIT_LEAVES.get(),
                    BlockTags.DIRT, 10, ImmutableList.of(new LeaveVineDecorator(1), new TrunkVineDecorator(), SOUL_FRUIT.get())
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SPIRIT_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("spirit_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spirit/spirit_tree_trunk3"),
                    BiomesWeveGone.id("features/trees/spirit/spirit_tree_canopy3"),
                    BiasedToBottomInt.of(8, 15),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.SPIRIT_ROOTS.get().defaultBlockState(), 5).add(BWGWood.SPIRIT.logstem().defaultBlockState(), 10).build()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.SPIRIT_LEAVES.get().defaultBlockState(), 10)
                            .add(BWGWood.FLOWERING_SPIRIT_LEAVES.get().defaultBlockState(), 3).build()
                    ),
                    BWGWood.SPIRIT.logstem(),
                    BWGWood.SPIRIT_LEAVES.get(),
                    BlockTags.DIRT, 10, ImmutableList.of(new LeaveVineDecorator(1), new TrunkVineDecorator(), SOUL_FRUIT.get())
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SPIRIT_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("spirit_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spirit/spirit_tree_trunk4"),
                    BiomesWeveGone.id("features/trees/spirit/spirit_tree_canopy4"),
                    BiasedToBottomInt.of(8, 15),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.SPIRIT_ROOTS.get().defaultBlockState(), 5).add(BWGWood.SPIRIT.logstem().defaultBlockState(), 10).build()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.SPIRIT_LEAVES.get().defaultBlockState(), 10)
                            .add(BWGWood.FLOWERING_SPIRIT_LEAVES.get().defaultBlockState(), 3).build()
                    ),
                    BWGWood.SPIRIT.logstem(),
                    BWGWood.SPIRIT_LEAVES.get(),
                    BlockTags.DIRT, 10, ImmutableList.of(new LeaveVineDecorator(1), new TrunkVineDecorator(), SOUL_FRUIT.get())
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> BASE_SPIRIT_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("base_spirit_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spirit/spirit_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/spirit/spirit_tree_canopy1"),
                    BiasedToBottomInt.of(8, 15),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.SPIRIT_ROOTS.get().defaultBlockState(), 5).add(BWGWood.SPIRIT.logstem().defaultBlockState(), 10).build()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.SPIRIT_LEAVES.get().defaultBlockState(), 10)
                            .add(BWGWood.FLOWERING_SPIRIT_LEAVES.get().defaultBlockState(), 3).build()
                    ),
                    BWGWood.SPIRIT.logstem(),
                    BWGWood.SPIRIT_LEAVES.get(),
                    BlockTags.DIRT, 10
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> BASE_SPIRIT_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("base_spirit_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spirit/spirit_tree_trunk2"),
                    BiomesWeveGone.id("features/trees/spirit/spirit_tree_canopy2"),
                    BiasedToBottomInt.of(8, 15),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.SPIRIT_ROOTS.get().defaultBlockState(), 5).add(BWGWood.SPIRIT.logstem().defaultBlockState(), 10).build()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.SPIRIT_LEAVES.get().defaultBlockState(), 10)
                            .add(BWGWood.FLOWERING_SPIRIT_LEAVES.get().defaultBlockState(), 3).build()
                    ),
                    BWGWood.SPIRIT.logstem(),
                    BWGWood.SPIRIT_LEAVES.get(),
                    BlockTags.DIRT, 10
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> BASE_SPIRIT_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("base_spirit_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spirit/spirit_tree_trunk3"),
                    BiomesWeveGone.id("features/trees/spirit/spirit_tree_canopy3"),
                    BiasedToBottomInt.of(8, 15),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.SPIRIT_ROOTS.get().defaultBlockState(), 5).add(BWGWood.SPIRIT.logstem().defaultBlockState(), 10).build()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.SPIRIT_LEAVES.get().defaultBlockState(), 10)
                            .add(BWGWood.FLOWERING_SPIRIT_LEAVES.get().defaultBlockState(), 3).build()
                    ),
                    BWGWood.SPIRIT.logstem(),
                    BWGWood.SPIRIT_LEAVES.get(),
                    BlockTags.DIRT, 10
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> BASE_SPIRIT_TREE4 = ConfiguredFeaturesUtil.createConfiguredFeature("base_spirit_tree4",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/spirit/spirit_tree_trunk4"),
                    BiomesWeveGone.id("features/trees/spirit/spirit_tree_canopy4"),
                    BiasedToBottomInt.of(8, 15),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BWGWood.SPIRIT_ROOTS.get().defaultBlockState(), 5).add(BWGWood.SPIRIT.logstem().defaultBlockState(), 10).build()),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(BWGWood.SPIRIT_LEAVES.get().defaultBlockState(), 10)
                            .add(BWGWood.FLOWERING_SPIRIT_LEAVES.get().defaultBlockState(), 3).build()
                    ),
                    BWGWood.SPIRIT.logstem(),
                    BWGWood.SPIRIT_LEAVES.get(),
                    BlockTags.DIRT, 10
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> ZELKOVA_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("zelkova_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/zelkova/zelkova_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/zelkova/zelkova_tree_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.ZELKOVA.logstem()),
                    BlockStateProvider.simple(BWGWood.ZELKOVA.leaves()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> ZELKOVA_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("zelkova_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/zelkova/zelkova_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/zelkova/zelkova_tree_canopy2"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.ZELKOVA.logstem()),
                    BlockStateProvider.simple(BWGWood.ZELKOVA.leaves()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> ZELKOVA_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("zelkova_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/zelkova/zelkova_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/zelkova/zelkova_tree_canopy3"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.ZELKOVA.logstem()),
                    BlockStateProvider.simple(BWGWood.ZELKOVA.leaves()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> ZELKOVA_BROWN_TREE1 = ConfiguredFeaturesUtil.createConfiguredFeature("brown_zelkova_tree1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/zelkova/zelkova_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/zelkova/zelkova_tree_canopy1"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.ZELKOVA.logstem()),
                    BlockStateProvider.simple(BWGWood.BROWN_ZELKOVA_LEAVES.get()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> ZELKOVA_BROWN_TREE2 = ConfiguredFeaturesUtil.createConfiguredFeature("brown_zelkova_tree2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/zelkova/zelkova_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/zelkova/zelkova_tree_canopy2"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.ZELKOVA.logstem()),
                    BlockStateProvider.simple(BWGWood.BROWN_ZELKOVA_LEAVES.get()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> ZELKOVA_BROWN_TREE3 = ConfiguredFeaturesUtil.createConfiguredFeature("brown_zelkova_tree3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/zelkova/zelkova_tree_trunk1"),
                    BiomesWeveGone.id("features/trees/zelkova/zelkova_tree_canopy3"),
                    BiasedToBottomInt.of(5, 15),
                    BlockStateProvider.simple(BWGWood.ZELKOVA.logstem()),
                    BlockStateProvider.simple(BWGWood.BROWN_ZELKOVA_LEAVES.get()),
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> GIANT_ALLIUM_1 = ConfiguredFeaturesUtil.createConfiguredFeature("giant_allium_1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/giant_allium/giant_allium_trunk1"),
                    BiomesWeveGone.id("features/trees/giant_allium/giant_allium_canopy1"),
                    BiasedToBottomInt.of(3, 7),
                    BlockStateProvider.simple(BWGWood.FLORUS.logstem()),
                    BlockStateProvider.simple(BWGBlocks.ALLIUM_PETAL_BLOCK.get()),
                    Blocks.OAK_LOG,
                    Blocks.STONE,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> GIANT_ALLIUM_2 = ConfiguredFeaturesUtil.createConfiguredFeature("giant_allium_2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/giant_allium/giant_allium_trunk2"),
                    BiomesWeveGone.id("features/trees/giant_allium/giant_allium_canopy2"),
                    BiasedToBottomInt.of(3, 7),
                    BlockStateProvider.simple(BWGWood.FLORUS.logstem()),
                    BlockStateProvider.simple(BWGBlocks.ALLIUM_PETAL_BLOCK.get()),
                    Blocks.OAK_LOG,
                    Blocks.STONE,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> GIANT_ALLIUM_3 = ConfiguredFeaturesUtil.createConfiguredFeature("giant_allium_3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/giant_allium/giant_allium_trunk3"),
                    BiomesWeveGone.id("features/trees/giant_allium/giant_allium_canopy3"),
                    BiasedToBottomInt.of(3, 7),
                    BlockStateProvider.simple(BWGWood.FLORUS.logstem()),
                    BlockStateProvider.simple(BWGBlocks.ALLIUM_PETAL_BLOCK.get()),
                    Blocks.OAK_LOG,
                    Blocks.STONE,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> GIANT_PINK_ALLIUM_1 = ConfiguredFeaturesUtil.createConfiguredFeature("giant_pink_allium_1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/giant_allium/giant_allium_trunk1"),
                    BiomesWeveGone.id("features/trees/giant_allium/giant_allium_canopy1"),
                    BiasedToBottomInt.of(3, 7),
                    BlockStateProvider.simple(BWGWood.FLORUS.logstem()),
                    BlockStateProvider.simple(BWGBlocks.PINK_ALLIUM_PETAL_BLOCK.get()),
                    Blocks.OAK_LOG,
                    Blocks.STONE,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> GIANT_PINK_ALLIUM_2 = ConfiguredFeaturesUtil.createConfiguredFeature("giant_pink_allium_2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/giant_allium/giant_allium_trunk2"),
                    BiomesWeveGone.id("features/trees/giant_allium/giant_allium_canopy2"),
                    BiasedToBottomInt.of(3, 7),
                    BlockStateProvider.simple(BWGWood.FLORUS.logstem()),
                    BlockStateProvider.simple(BWGBlocks.PINK_ALLIUM_PETAL_BLOCK.get()),
                    Blocks.OAK_LOG,
                    Blocks.STONE,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> GIANT_PINK_ALLIUM_3 = ConfiguredFeaturesUtil.createConfiguredFeature("giant_pink_allium_3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/giant_allium/giant_allium_trunk3"),
                    BiomesWeveGone.id("features/trees/giant_allium/giant_allium_canopy3"),
                    BiasedToBottomInt.of(3, 7),
                    BlockStateProvider.simple(BWGWood.FLORUS.logstem()),
                    BlockStateProvider.simple(BWGBlocks.PINK_ALLIUM_PETAL_BLOCK.get()),
                    Blocks.OAK_LOG,
                    Blocks.STONE,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> GIANT_WHITE_ALLIUM_1 = ConfiguredFeaturesUtil.createConfiguredFeature("giant_white_allium_1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/giant_allium/giant_allium_trunk1"),
                    BiomesWeveGone.id("features/trees/giant_allium/giant_allium_canopy1"),
                    BiasedToBottomInt.of(3, 7),
                    BlockStateProvider.simple(BWGWood.FLORUS.logstem()),
                    BlockStateProvider.simple(BWGBlocks.WHITE_ALLIUM_PETAL_BLOCK.get()),
                    Blocks.OAK_LOG,
                    Blocks.STONE,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> GIANT_WHITE_ALLIUM_2 = ConfiguredFeaturesUtil.createConfiguredFeature("giant_white_allium_2",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/giant_allium/giant_allium_trunk2"),
                    BiomesWeveGone.id("features/trees/giant_allium/giant_allium_canopy2"),
                    BiasedToBottomInt.of(3, 7),
                    BlockStateProvider.simple(BWGWood.FLORUS.logstem()),
                    BlockStateProvider.simple(BWGBlocks.WHITE_ALLIUM_PETAL_BLOCK.get()),
                    Blocks.OAK_LOG,
                    Blocks.STONE,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> GIANT_WHITE_ALLIUM_3 = ConfiguredFeaturesUtil.createConfiguredFeature("giant_white_allium_3",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/giant_allium/giant_allium_trunk3"),
                    BiomesWeveGone.id("features/trees/giant_allium/giant_allium_canopy3"),
                    BiasedToBottomInt.of(3, 7),
                    BlockStateProvider.simple(BWGWood.FLORUS.logstem()),
                    BlockStateProvider.simple(BWGBlocks.WHITE_ALLIUM_PETAL_BLOCK.get()),
                    Blocks.OAK_LOG,
                    Blocks.STONE,
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> FLORUS_ROSE_1 = ConfiguredFeaturesUtil.createConfiguredFeature("florus_rose_1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/florus/florus_rose_trunk1"),
                    BiomesWeveGone.id("features/trees/florus/florus_rose_canopy1"),
                    BiasedToBottomInt.of(2, 5),
                    BlockStateProvider.simple(BWGWood.FLORUS.logstem()),
                    BlockStateProvider.simple(BWGBlocks.ROSE_PETAL_BLOCK.get()),
                    BWGWood.FLORUS.logstem(),
                    BWGBlocks.WHITE_ALLIUM_PETAL_BLOCK.get(),
                    BlockTags.DIRT, 5
            )
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLORUS_THORN_1 = ConfiguredFeaturesUtil.createConfiguredFeature("florus_thorn_1",
            TYGFeatures.TREE_FROM_NBT_V1,
            () -> new TreeFromStructureNBTConfig(
                    BiomesWeveGone.id("features/trees/florus/florus_thorn_trunk1"),
                    BiomesWeveGone.id("features/trees/florus/florus_thorn_canopy1"),
                    BiasedToBottomInt.of(2, 5),
                    BlockStateProvider.simple(BWGWood.FLORUS.logstem()),
                    BlockStateProvider.simple(BWGBlocks.ROSE_PETAL_BLOCK.get()),
                    BWGWood.FLORUS.logstem(),
                    BWGBlocks.WHITE_ALLIUM_PETAL_BLOCK.get(),
                    BlockTags.DIRT, 5
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> GIANT_ALLIUMS = ConfiguredFeaturesUtil.createConfiguredFeature("giant_alliums",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> ConfiguredFeaturesUtil.createRandomWeightedConfiguredFeature(configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE), GIANT_ALLIUM_1, GIANT_ALLIUM_2, GIANT_ALLIUM_3, GIANT_PINK_ALLIUM_1, GIANT_PINK_ALLIUM_2, GIANT_PINK_ALLIUM_3, GIANT_WHITE_ALLIUM_1, GIANT_WHITE_ALLIUM_2, GIANT_WHITE_ALLIUM_3)
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> FIRECRACKER_SHRUBS = ConfiguredFeaturesUtil.createConfiguredFeature("firecracker_shrubs",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(FIRECRACKER_BUSH1)), 0.5F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(FIRECRACKER_SHRUB)), 0.25F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(FIRECRACKER_SHRUB2)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> BAYOU_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("bayou_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(WILLOW_DEAD_TREE1)), 0.01F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(WILLOW_TREE1)), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(WILLOW_TREE2)), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(WILLOW_TREE3)), 0.25F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(WILLOW_TREE4)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SPIRIT_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("spirit_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPIRIT_TREE1)), 0.01F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPIRIT_TREE4)), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPIRIT_TREE3)), 0.25F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPIRIT_TREE2)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> CANADIAN_SHIELD_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("canadian_shield_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(PINE_TREE1)), 0.05F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(PINE_TREE2)), 0.05F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BLUFF_TREE1)), 0.3F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BLUFF_TREE2)), 0.3F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(CONIFER_TREE6)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> FLORUS_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("florus_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(FLORUS_ROSE_1)), 0.65F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(FLORUS_ROSE_1)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_FOREST_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("black_forest_trees",
             Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ASPEN_TREE3)), 0.05F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ASPEN_TREE2)), 0.05F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(CONIFER_TREE6)), 0.05F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(CONIFER_TREE7)), 0.05F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(PINE_LARGE_TREE1)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(PINE_LARGE_TREE2)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(PINE_TREE1)), 0.2F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(PINE_TREE2)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> CIKA_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("cika_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(CIKA_TREE2)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(CIKA_TREE3)), 0.1F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(CIKA_TREE1)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> CONIFER_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("conifer_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(CONIFER_TREE1)), 0.06F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(CONIFER_TREE2)), 0.5F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(CONIFER_TREE3)), 0.04F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(CONIFER_TREE4)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(CONIFER_TREE5)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(CONIFER_TREE6)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(CONIFER_TREE7)), 0.1F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(CONIFER_TREE8)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> CYPRESS_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("cypress_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(CYPRESS_TREE1)), 0.33F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(CYPRESS_TREE2)), 0.33F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(CYPRESS_TREE3)));
            }
    );

    //TODO: Add back deciduous trees
    public static final ResourceKey<ConfiguredFeature<?, ?>> DECIDUOUS_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("deciduous_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(OAK_TREE1)), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(OAK_TREE2)), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(OAK_TREE3)), 0.25F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(OAK_TREE_LARGE1)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> DACITE_RIDGE_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("dacite_ridge_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_TREE_MEDIUM2)), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_TREE_MEDIUM1)), 0.25F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_TREE_LARGE1)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> JACARANDA_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("jacaranda_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(JACARANDA_TREE1)), 0.125F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(JACARANDA_TREE2)), 0.125F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(JACARANDA_TREE3)), 0.125F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(JACARANDA_TREE4)), 0.125F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(INDIGO_JACARANDA_TREE1)), 0.125F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(INDIGO_JACARANDA_TREE2)), 0.125F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(INDIGO_JACARANDA_TREE3)), 0.125F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(INDIGO_JACARANDA_TREE4)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> MAPLE_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("maple_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(MAPLE_TREE5)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(MAPLE_TREE4)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(MAPLE_TREE3)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(MAPLE_TREE2)), 0.2F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(MAPLE_TREE1)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_MAPLE_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("red_maple_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RED_MAPLE_TREE5)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RED_MAPLE_TREE4)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RED_MAPLE_TREE3)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RED_MAPLE_TREE2)), 0.2F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RED_MAPLE_TREE1)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_MAPLE_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("silver_maple_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SILVER_MAPLE_TREE5)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SILVER_MAPLE_TREE4)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SILVER_MAPLE_TREE3)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SILVER_MAPLE_TREE2)), 0.2F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SILVER_MAPLE_TREE1)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> MAPLE_TAIGA_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("maple_taiga_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SILVER_MAPLE_TREES)), 0.1F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RED_MAPLE_TREES)), 0.35F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(MAPLE_TREES)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> NORTHERN_FOREST_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("northern_forest_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(PINE_TREE1)), 0.5F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(PINE_TREE2)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> REDWOOD_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("redwood_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(REDWOOD_TREE1)), 0.55F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(REDWOOD_TREE2)), 0.1F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(REDWOOD_TREE3)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("spruce_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_TREE1)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_TREE2)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_TREE3)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_TREE4)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_TREE_MEDIUM1)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_TREE_MEDIUM2)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_TREE_MEDIUM3)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_TREE_MEDIUM4)), 0.111F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_TREE1)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_SPRUCE_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("orange_spruce_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_ORANGE_TREE1)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_ORANGE_TREE2)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_ORANGE_TREE3)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_ORANGE_TREE4)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_ORANGE_TREE_MEDIUM1)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_ORANGE_TREE_MEDIUM2)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_ORANGE_TREE_MEDIUM3)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_ORANGE_TREE_MEDIUM4)), 0.111F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_ORANGE_TREE_LARGE1)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> YELLOW_SPRUCE_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("yellow_spruce_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_YELLOW_TREE1)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_YELLOW_TREE2)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_YELLOW_TREE3)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_YELLOW_TREE4)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_YELLOW_TREE_MEDIUM1)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_YELLOW_TREE_MEDIUM2)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_YELLOW_TREE_MEDIUM3)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_YELLOW_TREE_MEDIUM4)), 0.111F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_YELLOW_TREE_LARGE1)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_SPRUCE_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("red_spruce_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RED_SPRUCE_TREE1)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RED_SPRUCE_TREE2)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RED_SPRUCE_TREE3)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RED_SPRUCE_TREE4)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RED_SPRUCE_TREE_MEDIUM1)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RED_SPRUCE_TREE_MEDIUM2)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RED_SPRUCE_TREE_MEDIUM3)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RED_SPRUCE_TREE_MEDIUM4)), 0.111F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RED_SPRUCE_TREE1)));
            }
    );


    public static final ResourceKey<ConfiguredFeature<?, ?>> AUTUMNAL_SPRUCE_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("autumnal_spruce_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RED_SPRUCE_TREES)), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ORANGE_SPRUCE_TREES)), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(YELLOW_SPRUCE_TREES)), 0.25F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SPRUCE_TREES)));
            }
    );


    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_SPRUCE_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("blue_spruce_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BLUE_SPRUCE_TREE1)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BLUE_SPRUCE_TREE2)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BLUE_SPRUCE_TREE3)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BLUE_SPRUCE_TREE4)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BLUE_SPRUCE_TREE_MEDIUM1)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BLUE_SPRUCE_TREE_MEDIUM2)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BLUE_SPRUCE_TREE_MEDIUM3)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BLUE_SPRUCE_TREE_MEDIUM4)), 0.111F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BLUE_SPRUCE_TREE1)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> ROSE_FIELD_SPRUCE_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("rose_field_spruce_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BLUE_SPRUCE_TREE1)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BLUE_SPRUCE_TREE2)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BLUE_SPRUCE_TREE3)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BLUE_SPRUCE_TREE4)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RED_SPRUCE_TREE1)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RED_SPRUCE_TREE2)), 0.111F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RED_SPRUCE_TREE3)), 0.111F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RED_SPRUCE_TREE4)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> MAHOGANY_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("mahogany_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(MAHOGANY_TREE1)), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(MAHOGANY_TREE2)), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(MAHOGANY_TREE3)), 0.25F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(MAHOGANY_TREE4)));
            }
    );


    public static final ResourceKey<ConfiguredFeature<?, ?>> RAINFOREST_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("rainforest_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(TROPICAL_SHRUB1)), 0.35F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(MAHOGANY_TREES)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> GUIANA_SHIELD_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("guiana_shield_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RAINBOW_EUCALYPTUS_LARGE_TREE1)), 0.1F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RAINBOW_EUCALYPTUS_TREE1)), 0.1F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(TROPICAL_SHRUB1)), 0.1F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(MAHOGANY_TREES)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> RAINBOW_EUCALYPTUS_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("rainbow_eucalyptus_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RAINBOW_EUCALYPTUS_LARGE_TREE1)), 0.333F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RAINBOW_EUCALYPTUS_TREE1)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SKYRIS_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("skyris_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SKYRIS_TREE1)), 0.1667F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SKYRIS_TREE2)), 0.1667F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SKYRIS_TREE3)), 0.1667F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SKYRIS_TREE4)), 0.1667F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SKYRIS_TREE5)), 0.1667F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SKYRIS_TREE6)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> BAOBAB_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("baobab_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BAOBAB_TREE1)), 0.35F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BAOBAB_TREE2)), 0.35F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BAOBAB_TREE3)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> ARAUCARIA_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("araucaria_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ARAUCARIA_TREE1)), 0.5F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ARAUCARIA_TREE2)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> IRONWOOD_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("ironwood_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(IRONWOOD_TREE1)), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(IRONWOOD_TREE2)), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(IRONWOOD_TREE3)), 0.25F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(IRONWOOD_TREE4)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWERING_IRONWOOD_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("flowering_ironwood_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(FLOWERING_IRONWOOD_TREE1)), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(FLOWERING_IRONWOOD_TREE2)), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(FLOWERING_IRONWOOD_TREE3)), 0.25F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(FLOWERING_IRONWOOD_TREE4)));
            }
    );


    public static final ResourceKey<ConfiguredFeature<?, ?>> BIRCH_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("birch_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BIRCH_TREE1)), 0.33F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BIRCH_TREE2)), 0.33F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BIRCH_TREE3)), 0.33F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BIRCH_TREE4)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_BIRCH_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("orange_birch_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BIRCH_ORANGE_TREE1)), 0.33F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BIRCH_ORANGE_TREE2)), 0.33F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BIRCH_ORANGE_TREE3)), 0.33F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BIRCH_ORANGE_TREE4)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> YELLOW_BIRCH_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("yellow_birch_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(YELLOW_BIRCH_TREE1)), 0.33F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(YELLOW_BIRCH_TREE2)), 0.33F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(YELLOW_BIRCH_TREE3)), 0.33F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(YELLOW_BIRCH_TREE4)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> BROWN_ZELKOVA_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("brown_zelkova_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ZELKOVA_BROWN_TREE1)), 0.33F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ZELKOVA_BROWN_TREE2)), 0.33F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ZELKOVA_BROWN_TREE3)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_BUSHES = ConfiguredFeaturesUtil.createConfiguredFeature("oak_bushes",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(OAK_BUSH1)), 0.5F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(OAK_BUSH1)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> MEADOW_SHRUBS = ConfiguredFeaturesUtil.createConfiguredFeature("meadow_shrubs",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SHRUB_MEADOW)), 0.5F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SHRUB_MEADOW2)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> MEADOW_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("meadow_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(MEADOW_TREE1)), 0.3F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(MEADOW_TREE2)), 0.3F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(MEADOW_TREE3)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> TEMPERATE_GROVE_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("temperate_grove_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BIRCH_YELLOW_BOREAL_TREE1)), 0.5F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BIRCH_BOREAL_TREE1)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> ENCHANTED_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("enchanted_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
//                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BLUE_ENCHANTED_TREE4)), 0.05F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BLUE_ENCHANTED_TREE3)), 0.1F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BLUE_ENCHANTED_TREE2)), 0.2F),
//                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(GREEN_ENCHANTED_TREE4)), 0.05F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(GREEN_ENCHANTED_TREE3)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(GREEN_ENCHANTED_TREE2)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(GREEN_ENCHANTED_TREE1)), 0.1F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BLUE_ENCHANTED_TREE1)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> ASPEN_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("aspen_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ASPEN_TREE5)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ASPEN_TREE4)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ASPEN_TREE3)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ASPEN_TREE2)), 0.2F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ASPEN_TREE1)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> ASPEN_SHRUBS = ConfiguredFeaturesUtil.createConfiguredFeature("aspen_shrubs",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ASPEN_SHRUB1)), 0.5F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ASPEN_SHRUB2)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> ZELKOVA_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("zelkova_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ZELKOVA_TREE1)), 0.33F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ZELKOVA_TREE2)), 0.33F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ZELKOVA_TREE3)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> PALO_VERDE_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("palo_verde_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(PALO_VERDE_TREE1)), 0.5F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(PALO_VERDE_TREE2)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> YUCCA_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("yucca_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(YUCCA_TREE1)), 0.5F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(YUCCA_TREE2)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> PRAIRIE_SHRUBS = ConfiguredFeaturesUtil.createConfiguredFeature("prairie_shrubs", Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SHRUB_PRAIRIE1)), 0.5F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SHRUB_PRAIRIE2)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> PALM_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("palm_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(PALM_TREE1)), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(PALM_TREE2)), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(PALM_TREE3)), 0.25F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(PALM_TREE4)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_SAKURA_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("white_sakura_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SAKURA_WHITE_TREE1)), 0.1F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SAKURA_WHITE_TREE2)), 0.1F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SAKURA_WHITE_TREE3)), 0.1F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SAKURA_WHITE_TREE4)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SAKURA_WHITE_TREE5)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SAKURA_WHITE_TREE6)), 0.2F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SAKURA_WHITE_TREE7)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> YELLOW_SAKURA_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("yellow_sakura_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SAKURA_YELLOW_TREE1)), 0.1F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SAKURA_YELLOW_TREE2)), 0.1F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SAKURA_YELLOW_TREE3)), 0.1F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SAKURA_YELLOW_TREE4)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SAKURA_YELLOW_TREE5)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SAKURA_YELLOW_TREE6)), 0.2F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(SAKURA_YELLOW_TREE7)));
            }
    );


    public static final ResourceKey<ConfiguredFeature<?, ?>> WITCH_HAZEL_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("witch_hazel_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(WITCH_HAZEL1)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(WITCH_HAZEL2)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(WITCH_HAZEL3)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(WITCH_HAZEL4)), 0.2F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(WITCH_HAZEL5)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> EBONY_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("ebony_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(EBONY_TREE1)), 0.45F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(EBONY_TREE2)), 0.45F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(EBONY_BUSH1)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> FRAGMENT_JUNGLE_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("fragment_jungle_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RAINBOW_EUCALYPTUS_TREE1)), 0.3F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RAINBOW_EUCALYPTUS_LARGE_TREE1)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(EBONY_TREES)), 0.3F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(DECIDUOUS_TREES)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> HOLLY_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("holly_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(HOLLY_TREE1)), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(HOLLY_TREE2)), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(HOLLY_TREE3)), 0.25F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(HOLLY_TREE4)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> MANGROVE_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("white_mangrove_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(WHITE_MANGROVE_TREE1)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(WHITE_MANGROVE_TREE2)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(WHITE_MANGROVE_TREE3)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(WHITE_MANGROVE_TREE4)), 0.2F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(WHITE_MANGROVE_TREE5)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORCHARD_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("orchard_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ORCHARD_TREE1)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ORCHARD_TREE2)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ORCHARD_TREE3)), 0.2F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ORCHARD_TREE4)), 0.2F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ORCHARD_TREE5)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> TEMPERATE_RAINFOREST_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("temperate_rainforest_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(WOODLANDS_TREE1)), 0.5F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(WOODLANDS_TREE1)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> FORGOTTEN_FOREST_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("forgotten_forest_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
//                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ANCIENT_TREE1)), 0.15F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ANCIENT_TREE2)), 0.15F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ANCIENT_TREE3)), 0.15F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(WOODLANDS_TREE1)), 0.25F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(FORGOTTEN_TREE1)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERGROWTH_WOODLANDS_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("overgrowth_woodlans_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(WOODLANDS_TREE1)), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(WOODLANDS_STUMP1)), 0.1F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(WOODLANDS_TREE_LARGE1)), 0.35F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(WOODLANDS_TREE_LARGE2)));
            }
    );


    public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("oak_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(OAK_TREE1)), 0.16F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(OAK_TREE2)), 0.16F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(OAK_TREE3)), 0.16F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(OAK_TREE_LARGE1)), 0.16F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(OAK_TREE_LARGE2)), 0.16F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(OAK_TREE_LARGE3)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_TREES_SWAMP = ConfiguredFeaturesUtil.createConfiguredFeature("oak_trees_swamp",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(OAK_TREE1)), 0.16F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(OAK_TREE2)), 0.16F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(OAK_TREE3)), 0.16F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(OAK_TREE_LARGE1)), 0.16F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(OAK_TREE_LARGE2)), 0.16F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(OAK_TREE_LARGE3)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_OAK_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("orange_oak_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ORANGE_OAK_TREE1)), 0.16F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ORANGE_OAK_TREE2)), 0.16F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ORANGE_OAK_TREE3)), 0.16F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ORANGE_OAK_TREE_LARGE1)), 0.16F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ORANGE_OAK_TREE_LARGE2)), 0.16F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ORANGE_OAK_TREE_LARGE3)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> BROWN_OAK_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("brown_oak_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BROWN_OAK_TREE1)), 0.16F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BROWN_OAK_TREE2)), 0.16F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BROWN_OAK_TREE3)), 0.16F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BROWN_OAK_TREE_LARGE1)), 0.16F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BROWN_OAK_TREE_LARGE2)), 0.16F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BROWN_OAK_TREE_LARGE3)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_OAK_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("red_oak_trees",
            Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);

                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RED_OAK_TREE1)), 0.16F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RED_OAK_TREE2)), 0.16F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RED_OAK_TREE3)), 0.16F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RED_OAK_TREE_LARGE1)), 0.16F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RED_OAK_TREE_LARGE2)), 0.16F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RED_OAK_TREE_LARGE3)));
            }
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> AUTUMNAL_OAK_TREES = ConfiguredFeaturesUtil.createConfiguredFeature("autumnal_oak_trees", Feature.RANDOM_SELECTOR,
            (configuredFeatureBootstrapContext) -> {
                HolderGetter<ConfiguredFeature<?, ?>> lookup = configuredFeatureBootstrapContext.lookup(Registries.CONFIGURED_FEATURE);

                return new RandomFeatureConfiguration(ImmutableList.of(
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(RED_OAK_TREES)), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(BROWN_OAK_TREES)), 0.25F),
                        new WeightedPlacedFeature(PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(ORANGE_OAK_TREES)), 0.25F)),
                        PlacedFeaturesUtil.createPlacedFeatureDirect(lookup.getOrThrow(OAK_TREES)));
            }
    );

    public static void init() {
        BiomesWeveGone.LOGGER.info("Registering Oh The Biomes We've Gone Tree Configured Features");
    }
}
