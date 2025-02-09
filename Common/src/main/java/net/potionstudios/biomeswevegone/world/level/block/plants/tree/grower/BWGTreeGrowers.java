package net.potionstudios.biomeswevegone.world.level.block.plants.tree.grower;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.configured.BWGOverworldTreeConfiguredFeatures;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Contains all the tree grower suppliers for the BWG trees.
 * @see TreeGrower
 * @see BWGTreeGrower
 * @see BWGMegaTreeGrower
 * @author CorgiTaco, Joseph T. McQuigg
 */
public class BWGTreeGrowers {
    public static final Supplier<TreeGrower> ASPEN = () -> new BWGTreeGrower("aspen",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.ASPEN_SHRUB1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ASPEN_SHRUB2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ASPEN_TREE1, 2)
                    .add(BWGOverworldTreeConfiguredFeatures.ASPEN_TREE2, 2)
                    .add(BWGOverworldTreeConfiguredFeatures.ASPEN_TREE3, 2)
                    .add(BWGOverworldTreeConfiguredFeatures.ASPEN_TREE4, 2)
                    .add(BWGOverworldTreeConfiguredFeatures.ASPEN_TREE5, 2)
                    .build());

    public static final Supplier<TreeGrower> BAOBAB = () -> new BWGMegaTreeGrower("baobab",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.BAOBAB_TREE1)
                    .add(BWGOverworldTreeConfiguredFeatures.BAOBAB_TREE2)
                    .add(BWGOverworldTreeConfiguredFeatures.BAOBAB_TREE3)
                    .build());

    public static final Supplier<TreeGrower> BLUE_ENCHANTED = () -> new TreeGrower("blue_enchanted",
            0.5f,
            Optional.of(BWGOverworldTreeConfiguredFeatures.BLUE_ENCHANTED_SAPLING_TREE3),
            Optional.of(BWGOverworldTreeConfiguredFeatures.BLUE_ENCHANTED_SAPLING_TREE1),
            Optional.of(BWGOverworldTreeConfiguredFeatures.BLUE_ENCHANTED_SAPLING_TREE2),
            Optional.empty(),
            Optional.empty(),
            Optional.empty());

    public static final Supplier<TreeGrower> CIKA = () -> new TreeGrower("cika",
            0.5f,
            Optional.of(BWGOverworldTreeConfiguredFeatures.CIKA_TREE3),
            Optional.empty(),
            Optional.of(BWGOverworldTreeConfiguredFeatures.CIKA_TREE1),
            Optional.of(BWGOverworldTreeConfiguredFeatures.CIKA_TREE2),
            Optional.empty(),
            Optional.empty());

    public static final Supplier<TreeGrower> CYPRESS = () -> new BWGMegaTreeGrower("cypress",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.CYPRESS_TREE1)
                    .add(BWGOverworldTreeConfiguredFeatures.CYPRESS_TREE2)
                    .add(BWGOverworldTreeConfiguredFeatures.CYPRESS_TREE3)
                    .build());

    public static final Supplier<TreeGrower> EBONY = () -> new BWGMegaTreeGrower("ebony",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.EBONY_BUSH1)
                    .add(BWGOverworldTreeConfiguredFeatures.EBONY_TREE1)
                    .add(BWGOverworldTreeConfiguredFeatures.EBONY_TREE2)
                    .build());

    public static final Supplier<TreeGrower> FIR = () -> new BWGTreeGrower("fir",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.CONIFER_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.CONIFER_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.CONIFER_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.CONIFER_TREE4, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.CONIFER_TREE5, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.CONIFER_TREE6, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.CONIFER_TREE7, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.CONIFER_TREE8, 1)
                    .build());

    public static final Supplier<TreeGrower> GREEN_ENCHANTED = () -> new BWGTreeGrower("green_enchanted",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.GREEN_ENCHANTED_SAPLING_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.GREEN_ENCHANTED_SAPLING_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.GREEN_ENCHANTED_SAPLING_TREE3, 1)
                    .build());

    public static final Supplier<TreeGrower> HOLLY = () -> new BWGTreeGrower("holly",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.HOLLY_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.HOLLY_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.HOLLY_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.HOLLY_TREE4, 1)
                    .build());

    public static final Supplier<TreeGrower> IRONWOOD = () -> new BWGTreeGrower("ironwood",
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

    public static final Supplier<TreeGrower> JACARANDA = () -> new BWGTreeGrower("jacaranda",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.JACARANDA_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.JACARANDA_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.JACARANDA_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.JACARANDA_TREE4, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.INDIGO_JACARANDA_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.INDIGO_JACARANDA_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.INDIGO_JACARANDA_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.INDIGO_JACARANDA_TREE4, 1)
                    .build());

    public static final Supplier<TreeGrower> MAHOGANY = () -> new BWGTreeGrower("mahogany",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.MAHOGANY_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.MAHOGANY_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.MAHOGANY_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.MAHOGANY_TREE4, 1)
                    .build());

    public static final Supplier<TreeGrower> MAPLE = () -> new BWGTreeGrower("maple",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.MAPLE_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.MAPLE_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.MAPLE_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.MAPLE_TREE4, 1)
                    .build());

    public static final Supplier<TreeGrower> PALM = () -> new BWGTreeGrower("palm",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.PALM_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.PALM_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.PALM_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.PALM_TREE4, 1)
                    .build());

    public static final Supplier<TreeGrower> PINE = () -> new TreeGrower("pine",
            0.5f,
            Optional.of(BWGOverworldTreeConfiguredFeatures.PINE_LARGE_TREE1),
            Optional.of(BWGOverworldTreeConfiguredFeatures.PINE_LARGE_TREE2),
            Optional.of(BWGOverworldTreeConfiguredFeatures.PINE_TREE1),
            Optional.of(BWGOverworldTreeConfiguredFeatures.PINE_TREE2),
            Optional.empty(),
            Optional.empty());

    public static final Supplier<TreeGrower> RAINBOW_EUCALYPTUS = () -> new TreeGrower("rainbow_eucalyptus",
            Optional.of(BWGOverworldTreeConfiguredFeatures.RAINBOW_EUCALYPTUS_LARGE_TREE1),
            Optional.of(BWGOverworldTreeConfiguredFeatures.RAINBOW_EUCALYPTUS_TREE1),
                    Optional.empty());

    public static final Supplier<TreeGrower> REDWOOD = () -> new BWGTreeGrower("redwood",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.REDWOOD_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.REDWOOD_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.REDWOOD_TREE3, 1)
                    .build());

    public static final Supplier<TreeGrower> WHITE_SAKURA = () -> new BWGTreeGrower("white_sakura",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.SAKURA_WHITE_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SAKURA_WHITE_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SAKURA_WHITE_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SAKURA_WHITE_TREE4, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SAKURA_WHITE_TREE5, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SAKURA_WHITE_TREE6, 1)
                    .build());

    public static final Supplier<TreeGrower> YELLOW_SAKURA = () -> new BWGTreeGrower("yellow_sakura",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.SAKURA_YELLOW_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SAKURA_YELLOW_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SAKURA_YELLOW_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SAKURA_YELLOW_TREE4, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SAKURA_YELLOW_TREE5, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SAKURA_YELLOW_TREE6, 1)
                    .build());

    public static final Supplier<TreeGrower> SKYRIS = () -> new BWGTreeGrower("skyris",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.SKYRIS_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SKYRIS_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SKYRIS_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SKYRIS_TREE4, 1)
                    .build());

    public static final Supplier<TreeGrower> SPIRIT = () -> new BWGTreeGrower("spirit",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.BASE_SPIRIT_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BASE_SPIRIT_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BASE_SPIRIT_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BASE_SPIRIT_TREE4, 1)
                    .build());

    public static final Supplier<TreeGrower> WHITE_MANGROVE = () -> new BWGTreeGrower("white_mangrove",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.WHITE_MANGROVE_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.WHITE_MANGROVE_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.WHITE_MANGROVE_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.WHITE_MANGROVE_TREE4, 1)
                    .build());

    public static final Supplier<TreeGrower> WILLOW = () -> new BWGTreeGrower("willow",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.WILLOW_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.WILLOW_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.WILLOW_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.WILLOW_TREE4, 1)
                    .build());

    public static final Supplier<TreeGrower> WITCH_HAZEL = () -> new BWGTreeGrower("witch_hazel",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.WITCH_HAZEL1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.WITCH_HAZEL2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.WITCH_HAZEL3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.WITCH_HAZEL4, 1)
                    .build());

    public static final Supplier<TreeGrower> ZELKOVA = () -> new BWGTreeGrower("zelkova",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.ZELKOVA_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ZELKOVA_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ZELKOVA_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ZELKOVA_BROWN_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ZELKOVA_BROWN_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ZELKOVA_BROWN_TREE3, 1)
                    .build());

    public static final Supplier<TreeGrower> PALO_VERDE = () -> new TreeGrower("palo_verde",
            0.5f,
            Optional.empty(),
            Optional.empty(),
            Optional.of(BWGOverworldTreeConfiguredFeatures.PALO_VERDE_TREE1),
            Optional.of(BWGOverworldTreeConfiguredFeatures.PALO_VERDE_TREE2),
            Optional.empty(),
            Optional.empty());

    public static final Supplier<TreeGrower> ARAUCARIA = () -> new TreeGrower("araucaria",
            0.5f,
            Optional.empty(),
            Optional.empty(),
            Optional.of(BWGOverworldTreeConfiguredFeatures.ARAUCARIA_TREE1),
            Optional.of(BWGOverworldTreeConfiguredFeatures.ARAUCARIA_TREE2),
            Optional.empty(),
            Optional.empty());

    public static final Supplier<TreeGrower> BLUE_SPRUCE = () -> new BWGTreeGrower("blue_spruce",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.BLUE_SPRUCE_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BLUE_SPRUCE_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BLUE_SPRUCE_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BLUE_SPRUCE_TREE4, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BLUE_SPRUCE_TREE_MEDIUM1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BLUE_SPRUCE_TREE_MEDIUM2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BLUE_SPRUCE_TREE_MEDIUM3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BLUE_SPRUCE_TREE_MEDIUM4, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BLUE_SPRUCE_TREE_LARGE1, 1)
                    .build());

    public static final Supplier<TreeGrower> BROWN_BIRCH = () -> new BWGTreeGrower("brown_birch",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.BROWN_BIRCH_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BROWN_BIRCH_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BROWN_BIRCH_TREE3, 1)
                    .build());

    public static final Supplier<TreeGrower> BROWN_OAK = () -> new BWGTreeGrower("brown_oak",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.BROWN_OAK_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BROWN_OAK_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BROWN_OAK_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BROWN_OAK_TREE_LARGE1,1)
                    .add(BWGOverworldTreeConfiguredFeatures.BROWN_OAK_TREE_LARGE2,1)
                    .add(BWGOverworldTreeConfiguredFeatures.BROWN_OAK_TREE_LARGE3,1)
                    .build());

    public static final Supplier<TreeGrower> BROWN_ZELKOVA = () -> new BWGTreeGrower("brown_zelkova",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.ZELKOVA_BROWN_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ZELKOVA_BROWN_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ZELKOVA_BROWN_TREE3, 1)
                    .build());

    public static final Supplier<TreeGrower> INDIGO_JACARANDA = () -> new BWGTreeGrower("indigo_jacaranda",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.INDIGO_JACARANDA_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.INDIGO_JACARANDA_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.INDIGO_JACARANDA_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.INDIGO_JACARANDA_TREE4, 1)
                    .build());

    public static final Supplier<TreeGrower> ORANGE_BIRCH = () -> new BWGTreeGrower("orange_birch",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.BIRCH_ORANGE_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BIRCH_ORANGE_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BIRCH_ORANGE_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.BIRCH_ORANGE_TREE4,1)
                    .build());

    public static final Supplier<TreeGrower> ORANGE_OAK = () -> new BWGTreeGrower("orange_oak",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.ORANGE_OAK_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ORANGE_OAK_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ORANGE_OAK_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ORANGE_OAK_TREE_LARGE1,1)
                    .add(BWGOverworldTreeConfiguredFeatures.ORANGE_OAK_TREE_LARGE2,1)
                    .add(BWGOverworldTreeConfiguredFeatures.ORANGE_OAK_TREE_LARGE3,1)
                    .build());

    public static final Supplier<TreeGrower> ORANGE_SPRUCE = () -> new BWGTreeGrower("orange_spruce",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_ORANGE_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_ORANGE_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_ORANGE_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_ORANGE_TREE4, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_ORANGE_TREE_MEDIUM1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_ORANGE_TREE_MEDIUM2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_ORANGE_TREE_MEDIUM3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_ORANGE_TREE_MEDIUM4, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_ORANGE_TREE_LARGE1, 1)
                    .build());

    public static final Supplier<TreeGrower> ORCHARD = () -> new BWGTreeGrower("orchard",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.ORCHARD_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ORCHARD_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ORCHARD_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.ORCHARD_TREE4, 1)
                    .build());

    public static final Supplier<TreeGrower> RED_BIRCH = () -> new BWGTreeGrower("red_birch",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.RED_BIRCH_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_BIRCH_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_BIRCH_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_BIRCH_TREE4,1)
                    .build());

    public static final Supplier<TreeGrower> RED_MAPLE = () -> new BWGTreeGrower("red_maple",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.RED_MAPLE_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_MAPLE_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_MAPLE_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_MAPLE_TREE4, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_MAPLE_TREE5, 1)
                    .build());

    public static final Supplier<TreeGrower> RED_OAK = () -> new BWGTreeGrower("red_oak",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.RED_OAK_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_OAK_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_OAK_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_OAK_TREE_LARGE1,1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_OAK_TREE_LARGE2,1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_OAK_TREE_LARGE3,1)
                    .build());

    public static final Supplier<TreeGrower> RED_SPRUCE = () -> new BWGTreeGrower("red_spruce",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.RED_SPRUCE_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_SPRUCE_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_SPRUCE_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_SPRUCE_TREE4, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_SPRUCE_TREE_MEDIUM1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_SPRUCE_TREE_MEDIUM2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_SPRUCE_TREE_MEDIUM3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_SPRUCE_TREE_MEDIUM4, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.RED_SPRUCE_TREE_LARGE1, 1)
                    .build());

    public static final Supplier<TreeGrower> SILVER_MAPLE = () -> new BWGTreeGrower("silver_maple",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.SILVER_MAPLE_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SILVER_MAPLE_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SILVER_MAPLE_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SILVER_MAPLE_TREE4, 1)
                    .build());

    public static final Supplier<TreeGrower> YELLOW_BIRCH = () -> new BWGTreeGrower("yellow_birch",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.YELLOW_BIRCH_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.YELLOW_BIRCH_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.YELLOW_BIRCH_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.YELLOW_BIRCH_TREE4,1)
                    .build());

    public static final Supplier<TreeGrower> YELLOW_SPRUCE = () -> new BWGTreeGrower("yellow_spruce",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_YELLOW_TREE1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_YELLOW_TREE2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_YELLOW_TREE3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_YELLOW_TREE4, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_YELLOW_TREE_MEDIUM1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_YELLOW_TREE_MEDIUM2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_YELLOW_TREE_MEDIUM3, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_YELLOW_TREE_MEDIUM4, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.SPRUCE_YELLOW_TREE_LARGE1, 1)
                    .build());

    public static final Supplier<TreeGrower> YUCCA = () -> new TreeGrower("yucca",
            0.5f,
            Optional.empty(),
            Optional.empty(),
            Optional.of(BWGOverworldTreeConfiguredFeatures.YUCCA_TREE1),
            Optional.of(BWGOverworldTreeConfiguredFeatures.YUCCA_TREE2),
            Optional.empty(),
            Optional.empty());

    public static final Supplier<TreeGrower> FIRECRACKER = () -> new TreeGrower("firecracker",
            0.5f,
            Optional.empty(),
            Optional.empty(),
            Optional.of(BWGOverworldTreeConfiguredFeatures.FIRECRACKER_SHRUB),
            Optional.of(BWGOverworldTreeConfiguredFeatures.FIRECRACKER_SHRUB2),
            Optional.empty(),
            Optional.empty());

    public static final Supplier<TreeGrower> GIANT_ALLIUM = () -> new BWGTreeGrower("giant_allium",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.GIANT_ALLIUM_1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.GIANT_ALLIUM_2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.GIANT_ALLIUM_3, 1)
                    .build());

    public static final Supplier<TreeGrower> GIANT_PINK_ALLIUM = () -> new BWGTreeGrower("giant_pink_allium",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.GIANT_PINK_ALLIUM_1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.GIANT_PINK_ALLIUM_2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.GIANT_PINK_ALLIUM_3, 1)
                    .build());

    public static final Supplier<TreeGrower> GIANT_WHITE_ALLIUM = () -> new BWGTreeGrower("giant_white_allium",
            new SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>>()
                    .add(BWGOverworldTreeConfiguredFeatures.GIANT_WHITE_ALLIUM_1, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.GIANT_WHITE_ALLIUM_2, 1)
                    .add(BWGOverworldTreeConfiguredFeatures.GIANT_WHITE_ALLIUM_3, 1)
                    .build());
}
