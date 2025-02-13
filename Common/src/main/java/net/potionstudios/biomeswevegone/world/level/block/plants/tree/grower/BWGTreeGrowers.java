package net.potionstudios.biomeswevegone.world.level.block.plants.tree.grower;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.configured.BWGOverworldTreeConfiguredFeatures;

import java.util.function.Supplier;

/**
 * Contains all the tree grower suppliers for the BWG trees.
 * @see BWGTreeGrower
 * @see BWGMegaTreeGrower
 * @author CorgiTaco, Joseph T. McQuigg
 */
public class BWGTreeGrowers {
    public static final Supplier<AbstractTreeGrower> ASPEN = () -> new BWGTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.ASPEN_SHRUB1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ASPEN_SHRUB2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ASPEN_TREE1, 2)
                    .add(BWGOverworldTreeConfiguredFeatures.ASPEN_TREE2, 2)
                    .add(BWGOverworldTreeConfiguredFeatures.ASPEN_TREE3, 2)
                    .add(BWGOverworldTreeConfiguredFeatures.ASPEN_TREE4, 2)
                    .add(BWGOverworldTreeConfiguredFeatures.ASPEN_TREE5, 2)
                    .build());

    public static final Supplier<AbstractTreeGrower> BAOBAB = () -> new BWGMegaTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.BAOBAB_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BAOBAB_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BAOBAB_TREE3, 1)
                    .build());

    public static final Supplier<AbstractTreeGrower> BLUE_ENCHANTED = () -> new BWGMegaTreeGrower(
            SimpleWeightedRandomList.single(BWGOverworldTreeConfiguredFeatures.BLUE_ENCHANTED_SAPLING_TREE2),
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.BLUE_ENCHANTED_SAPLING_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BLUE_ENCHANTED_SAPLING_TREE3, 1)
                    .build());

    public static final Supplier<AbstractTreeGrower> CIKA = () -> new BWGMegaTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.CIKA_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.CIKA_TREE2, 1)
                    .build(),
            SimpleWeightedRandomList.single(BWGOverworldTreeConfiguredFeatures.CIKA_TREE3));

    public static final Supplier<AbstractTreeGrower> CYPRESS = () -> new BWGMegaTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.CYPRESS_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.CYPRESS_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.CYPRESS_TREE3, 1)
                    .build());

    public static final Supplier<AbstractTreeGrower> EBONY = () -> new BWGMegaTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.EBONY_BUSH1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.EBONY_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.EBONY_TREE2, 1)
                    .build());

    public static final Supplier<AbstractTreeGrower> FIR = () -> new BWGMegaTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.CONIFER_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.CONIFER_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.CONIFER_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.CONIFER_TREE4, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.CONIFER_TREE5, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.CONIFER_TREE6, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.CONIFER_TREE7, 1)
                    .build(),
            SimpleWeightedRandomList.single(BWGOverworldTreeConfiguredFeatures.CONIFER_TREE8));

    public static final Supplier<AbstractTreeGrower> GREEN_ENCHANTED = () -> new BWGMegaTreeGrower(
            SimpleWeightedRandomList.single(BWGOverworldTreeConfiguredFeatures.GREEN_ENCHANTED_SAPLING_TREE2),
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.GREEN_ENCHANTED_SAPLING_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.GREEN_ENCHANTED_SAPLING_TREE3, 1)
                    .build());

    public static final Supplier<AbstractTreeGrower> HOLLY = () -> new BWGMegaTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.HOLLY_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.HOLLY_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.HOLLY_TREE4, 1)
                    .build(),
            SimpleWeightedRandomList.single(BWGOverworldTreeConfiguredFeatures.HOLLY_TREE3));

    public static final Supplier<AbstractTreeGrower> IRONWOOD = () -> new BWGTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.IRONWOOD_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.IRONWOOD_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.IRONWOOD_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.IRONWOOD_TREE4, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.FLOWERING_IRONWOOD_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.FLOWERING_IRONWOOD_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.FLOWERING_IRONWOOD_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.FLOWERING_IRONWOOD_TREE4, 1)
                    .build());

    public static final Supplier<AbstractTreeGrower> JACARANDA = () -> new BWGMegaTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.JACARANDA_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.JACARANDA_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.JACARANDA_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.INDIGO_JACARANDA_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.INDIGO_JACARANDA_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.INDIGO_JACARANDA_TREE3, 1)
                    .build(),
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.JACARANDA_TREE4, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.INDIGO_JACARANDA_TREE4, 1)
                    .build());

    public static final Supplier<AbstractTreeGrower> MAHOGANY = () -> new BWGMegaTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.MAHOGANY_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.MAHOGANY_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.MAHOGANY_TREE3, 1)
                    .build(),
            SimpleWeightedRandomList.single(BWGOverworldTreeConfiguredFeatures.MAHOGANY_TREE4));

    public static final Supplier<AbstractTreeGrower> MAPLE = () -> new BWGMegaTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.MAPLE_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.MAPLE_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.MAPLE_TREE4, 1)
                    .build(),
            SimpleWeightedRandomList.single(BWGOverworldTreeConfiguredFeatures.MAPLE_TREE3));

    public static final Supplier<AbstractTreeGrower> PALM = () -> new BWGMegaTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.PALM_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.PALM_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.PALM_TREE3, 1)
                    .build(),
            SimpleWeightedRandomList.single(BWGOverworldTreeConfiguredFeatures.PALM_TREE4));

    public static final Supplier<AbstractTreeGrower> PINE = () -> new BWGMegaTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.PINE_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.PINE_TREE2, 1).build(),
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.PINE_LARGE_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.PINE_LARGE_TREE2, 1)
                    .build());

    public static final Supplier<AbstractTreeGrower> RAINBOW_EUCALYPTUS = () -> new BWGMegaTreeGrower(
            SimpleWeightedRandomList.single(BWGOverworldTreeConfiguredFeatures.RAINBOW_EUCALYPTUS_TREE1),
            SimpleWeightedRandomList.single(BWGOverworldTreeConfiguredFeatures.RAINBOW_EUCALYPTUS_LARGE_TREE1));

    public static final Supplier<AbstractTreeGrower> REDWOOD = () -> new BWGMegaTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.REDWOOD_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.REDWOOD_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.REDWOOD_TREE3, 1)
                    .build());

    public static final Supplier<AbstractTreeGrower> WHITE_SAKURA = () -> new BWGMegaTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.SAKURA_WHITE_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SAKURA_WHITE_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SAKURA_WHITE_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SAKURA_WHITE_TREE6, 1)
                    .build(),
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.SAKURA_WHITE_TREE4, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SAKURA_WHITE_TREE5, 1)
                    .build());

    public static final Supplier<AbstractTreeGrower> YELLOW_SAKURA = () -> new BWGMegaTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.SAKURA_YELLOW_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SAKURA_YELLOW_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SAKURA_YELLOW_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SAKURA_YELLOW_TREE6, 1)
                    .build(),
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.SAKURA_YELLOW_TREE4, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SAKURA_YELLOW_TREE5, 1)
                    .build());

    public static final Supplier<AbstractTreeGrower> SKYRIS = () -> new BWGTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.SKYRIS_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SKYRIS_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SKYRIS_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SKYRIS_TREE4, 1)
                    .build());

    public static final Supplier<AbstractTreeGrower> WHITE_MANGROVE = () -> new BWGTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.WHITE_MANGROVE_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.WHITE_MANGROVE_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.WHITE_MANGROVE_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.WHITE_MANGROVE_TREE4, 1)
                    .build());

    public static final Supplier<AbstractTreeGrower> WILLOW = () -> new BWGTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.WILLOW_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.WILLOW_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.WILLOW_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.WILLOW_TREE4, 1)
                    .build());

    public static final Supplier<AbstractTreeGrower> WITCH_HAZEL = () -> new BWGMegaTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.WITCH_HAZEL3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.WITCH_HAZEL4, 1)
                    .build(),
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.WITCH_HAZEL1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.WITCH_HAZEL2, 1)
                    .build());

    public static final Supplier<AbstractTreeGrower> ZELKOVA = () -> new BWGTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.ZELKOVA_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ZELKOVA_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ZELKOVA_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ZELKOVA_BROWN_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ZELKOVA_BROWN_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ZELKOVA_BROWN_TREE3, 1)
                    .build());

    public static final Supplier<AbstractTreeGrower> PALO_VERDE = () -> new BWGTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.PALO_VERDE_TREE1,1)
                    .add(BWGOverworldTreeConfiguredFeatures.PALO_VERDE_TREE2,1)
                    .build());

    public static final Supplier<AbstractTreeGrower> ARAUCARIA = () -> new BWGTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.ARAUCARIA_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ARAUCARIA_TREE2, 1)
                    .build());

    public static final Supplier<AbstractTreeGrower> BLUE_SPRUCE = () -> new BWGMegaTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.BLUE_SPRUCE_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BLUE_SPRUCE_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BLUE_SPRUCE_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BLUE_SPRUCE_TREE4, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BLUE_SPRUCE_TREE_MEDIUM1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BLUE_SPRUCE_TREE_MEDIUM2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BLUE_SPRUCE_TREE_MEDIUM3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BLUE_SPRUCE_TREE_MEDIUM4, 1)
                    .build(),
            SimpleWeightedRandomList.single(BWGOverworldTreeConfiguredFeatures.BLUE_SPRUCE_TREES));

    public static final Supplier<AbstractTreeGrower> BROWN_BIRCH = () -> new BWGTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.BROWN_BIRCH_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BROWN_BIRCH_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BROWN_BIRCH_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BROWN_BIRCH_TREE4,1)
                    .build());

    public static final Supplier<AbstractTreeGrower> BROWN_OAK = () -> new BWGTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.BROWN_OAK_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BROWN_OAK_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BROWN_OAK_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BROWN_OAK_TREE_LARGE1,1)
                    .add(BWGOverworldTreeConfiguredFeatures.BROWN_OAK_TREE_LARGE2,1)
                    .add(BWGOverworldTreeConfiguredFeatures.BROWN_OAK_TREE_LARGE3,1)
                    .build());

    public static final Supplier<AbstractTreeGrower> BROWN_ZELKOVA = () -> new BWGTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.ZELKOVA_BROWN_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ZELKOVA_BROWN_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ZELKOVA_BROWN_TREE3, 1)
                    .build());

    public static final Supplier<AbstractTreeGrower> INDIGO_JACARANDA = () -> new BWGMegaTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.INDIGO_JACARANDA_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.INDIGO_JACARANDA_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.INDIGO_JACARANDA_TREE3, 1)
                    .build(),
            SimpleWeightedRandomList.single(BWGOverworldTreeConfiguredFeatures.INDIGO_JACARANDA_TREE4));

    public static final Supplier<AbstractTreeGrower> ORANGE_BIRCH = () -> new BWGTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.BIRCH_ORANGE_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BIRCH_ORANGE_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BIRCH_ORANGE_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BIRCH_ORANGE_TREE4,1)
                    .build());

    public static final Supplier<AbstractTreeGrower> ORANGE_OAK = () -> new BWGTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.ORANGE_OAK_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ORANGE_OAK_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ORANGE_OAK_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ORANGE_OAK_TREE_LARGE1,1)
                    .add(BWGOverworldTreeConfiguredFeatures.ORANGE_OAK_TREE_LARGE2,1)
                    .add(BWGOverworldTreeConfiguredFeatures.ORANGE_OAK_TREE_LARGE3,1)
                    .build());

    public static final Supplier<AbstractTreeGrower> ORANGE_SPRUCE = () -> new BWGMegaTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_ORANGE_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_ORANGE_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_ORANGE_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_ORANGE_TREE4, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_ORANGE_TREE_MEDIUM1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_ORANGE_TREE_MEDIUM2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_ORANGE_TREE_MEDIUM3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_ORANGE_TREE_MEDIUM4, 1)
                    .build(),
            SimpleWeightedRandomList.single(BWGOverworldTreeConfiguredFeatures.SPRUCE_ORANGE_TREE_LARGE1));

    public static final Supplier<AbstractTreeGrower> ORCHARD = () -> new BWGMegaTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.ORCHARD_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ORCHARD_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ORCHARD_TREE4, 1)
                    .build(),
            SimpleWeightedRandomList.single(BWGOverworldTreeConfiguredFeatures.ORCHARD_TREE2));

    public static final Supplier<AbstractTreeGrower> RED_BIRCH = () -> new BWGTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.RED_BIRCH_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_BIRCH_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_BIRCH_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_BIRCH_TREE4,1)
                    .build());

    public static final Supplier<AbstractTreeGrower> RED_MAPLE = () -> new BWGMegaTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.RED_MAPLE_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_MAPLE_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_MAPLE_TREE4, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_MAPLE_TREE5, 1)
                    .build(),
            SimpleWeightedRandomList.single(BWGOverworldTreeConfiguredFeatures.RED_MAPLE_TREE3));

    public static final Supplier<AbstractTreeGrower> RED_OAK = () -> new BWGTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.RED_OAK_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_OAK_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_OAK_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_OAK_TREE_LARGE1,1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_OAK_TREE_LARGE2,1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_OAK_TREE_LARGE3,1)
                    .build());

    public static final Supplier<AbstractTreeGrower> RED_SPRUCE = () -> new BWGMegaTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.RED_SPRUCE_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_SPRUCE_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_SPRUCE_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_SPRUCE_TREE4, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_SPRUCE_TREE_MEDIUM1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_SPRUCE_TREE_MEDIUM2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_SPRUCE_TREE_MEDIUM3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_SPRUCE_TREE_MEDIUM4, 1)
                    .build(),
            SimpleWeightedRandomList.single(BWGOverworldTreeConfiguredFeatures.RED_SPRUCE_TREE_LARGE1));

    public static final Supplier<AbstractTreeGrower> SILVER_MAPLE = () -> new BWGMegaTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.SILVER_MAPLE_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SILVER_MAPLE_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SILVER_MAPLE_TREE4, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SILVER_MAPLE_TREE5, 1)
                    .build(),
            SimpleWeightedRandomList.single(BWGOverworldTreeConfiguredFeatures.SILVER_MAPLE_TREE3));

    public static final Supplier<AbstractTreeGrower> YELLOW_BIRCH = () -> new BWGTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.YELLOW_BIRCH_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.YELLOW_BIRCH_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.YELLOW_BIRCH_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.YELLOW_BIRCH_TREE4,1)
                    .build());

    public static final Supplier<AbstractTreeGrower> YELLOW_SPRUCE = () -> new BWGMegaTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_YELLOW_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_YELLOW_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_YELLOW_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_YELLOW_TREE4, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_YELLOW_TREE_MEDIUM1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_YELLOW_TREE_MEDIUM2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_YELLOW_TREE_MEDIUM3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_YELLOW_TREE_MEDIUM4, 1)
                    .build(),
            SimpleWeightedRandomList.single(BWGOverworldTreeConfiguredFeatures.SPRUCE_YELLOW_TREE_LARGE1));

    public static final Supplier<AbstractTreeGrower> YUCCA = () -> new BWGTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.YUCCA_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.YUCCA_TREE2, 1)
                    .build());

    public static final Supplier<AbstractTreeGrower> FIRECRACKER = () -> new BWGTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.FIRECRACKER_SHRUB, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.FIRECRACKER_SHRUB2, 1)
                    .build());

    public static final Supplier<AbstractTreeGrower> GIANT_ALLIUM = () -> new BWGTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.GIANT_ALLIUM_1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.GIANT_ALLIUM_2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.GIANT_ALLIUM_3, 1)
                    .build());

    public static final Supplier<AbstractTreeGrower> GIANT_PINK_ALLIUM = () -> new BWGTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.GIANT_PINK_ALLIUM_1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.GIANT_PINK_ALLIUM_2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.GIANT_PINK_ALLIUM_3, 1)
                    .build());

    public static final Supplier<AbstractTreeGrower> GIANT_WHITE_ALLIUM = () -> new BWGTreeGrower(
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.GIANT_WHITE_ALLIUM_1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.GIANT_WHITE_ALLIUM_2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.GIANT_WHITE_ALLIUM_3, 1)
                    .build());
}
