package net.potionstudios.biomeswevegone.world.level.levelgen.biome;

import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.block.sand.BWGSandSet;
import net.potionstudios.biomeswevegone.world.level.levelgen.surfacerules.BWGRuleSources;
import net.potionstudios.biomeswevegone.world.level.levelgen.surfacerules.BandsRuleSource;
import net.potionstudios.biomeswevegone.world.level.levelgen.surfacerules.BetweenRepeatingNoiseRange;
import org.jetbrains.annotations.NotNull;

/**
 * Surface rules for the overworld biomes in Oh The Biomes We've Gone.
 * @see BWGBiomes
 * @see terrablender.worldgen.TBSurfaceRuleData
 * @author Joseph T. McQuigg
 */
public class BWGOverworldSurfaceRules {
    private static final SurfaceRules.ConditionSource WATER_CHECK = SurfaceRules.waterBlockCheck(-1, 0);

    private static final SurfaceRules.RuleSource STONE = makeifTrueRule(SurfaceRules.ON_FLOOR, makeStateRule(Blocks.STONE));
    private static final SurfaceRules.RuleSource GRAVEL = SurfaceRules.sequence(makeifTrueRule(SurfaceRules.ON_FLOOR, makeStateRule(Blocks.GRAVEL)), makeifTrueRule(SurfaceRules.UNDER_FLOOR, makeStateRule(Blocks.GRAVEL)));


    private static final SurfaceRules.RuleSource PURPLE_SAND = makeSandRule(BWGBlocks.PURPLE_SAND_SET);
    private static final SurfaceRules.RuleSource BLUE_SAND = makeSandRule(BWGBlocks.BLUE_SAND_SET);
    private static final SurfaceRules.RuleSource PINK_SAND = makeSandRule(BWGBlocks.PINK_SAND_SET);
    private static final SurfaceRules.RuleSource BLACK_SAND = makeSandRule(BWGBlocks.BLACK_SAND_SET);
    private static final SurfaceRules.RuleSource WHITE_SAND = makeSandRule(BWGBlocks.WHITE_SAND_SET);

    private static final SurfaceRules.RuleSource OVERGROWN_DACITE_DACITE_SURFACE = SurfaceRules.sequence(makeifTrueRule(WATER_CHECK, makeifTrueRule(SurfaceRules.ON_FLOOR, BWGBlocks.OVERGROWN_DACITE.get())), makeifTrueRule(SurfaceRules.UNDER_FLOOR, BWGBlocks.DACITE_SET.getBase()));
    private static final SurfaceRules.RuleSource OVERGROWN_PODZOL_DACITE_STONE_SURFACE = SurfaceRules.sequence(makeifTrueRule(WATER_CHECK, makeifTrueRule(SurfaceRules.ON_FLOOR, BWGBlocks.PODZOL_DACITE.get())), makeifTrueRule(SurfaceRules.UNDER_FLOOR, BWGBlocks.DACITE_SET.getBase()));
    private static final SurfaceRules.RuleSource LUSH_GRASS_LUSH_DIRT_LUSH_DIRT_SURFACE = SurfaceRules.sequence(makeifTrueRule(WATER_CHECK, makeifTrueRule(SurfaceRules.ON_FLOOR, BWGBlocks.LUSH_GRASS_BLOCK.get())), makeifTrueRule(SurfaceRules.UNDER_FLOOR, BWGBlocks.LUSH_DIRT.get()));
    private static final SurfaceRules.RuleSource COARSE_DIRT_DIRT_SURFACE = SurfaceRules.sequence(makeifTrueRule(SurfaceRules.ON_FLOOR, Blocks.COARSE_DIRT), makeifTrueRule(SurfaceRules.UNDER_FLOOR, Blocks.DIRT));

    private static final SurfaceRules.RuleSource SANDY_DIRT_SURFACE = SurfaceRules.sequence(makeifTrueRule(SurfaceRules.ON_FLOOR, BWGBlocks.SANDY_DIRT.get()), makeifTrueRule(SurfaceRules.UNDER_FLOOR, BWGBlocks.SANDY_DIRT.get()));


    private static final SurfaceRules.RuleSource NOISE_COARSE_DIRT = makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(1.75D), COARSE_DIRT_DIRT_SURFACE);
    private static final SurfaceRules.RuleSource PODZOL_DIRT_SURFACE = SurfaceRules.sequence(makeifTrueRule(WATER_CHECK, makeifTrueRule(SurfaceRules.ON_FLOOR, Blocks.PODZOL)), makeifTrueRule(SurfaceRules.UNDER_FLOOR, Blocks.DIRT));
    private static final SurfaceRules.RuleSource PEAT_SURFACE = SurfaceRules.sequence(makeifTrueRule(WATER_CHECK, makeifTrueRule(SurfaceRules.ON_FLOOR, BWGBlocks.PEAT.get())), makeifTrueRule(SurfaceRules.UNDER_FLOOR, BWGBlocks.PEAT.get()));
    private static final SurfaceRules.RuleSource OVERGROWN_STONE_STONE_SURFACE = SurfaceRules.sequence(makeifTrueRule(WATER_CHECK, makeifTrueRule(SurfaceRules.ON_FLOOR, BWGBlocks.OVERGROWN_STONE.get())), makeifTrueRule(SurfaceRules.UNDER_FLOOR, Blocks.STONE));
    private static final SurfaceRules.RuleSource CRACKED_RED_SAND_SURFACE = SurfaceRules.sequence(makeifTrueRule(SurfaceRules.ON_CEILING, Blocks.RED_SANDSTONE), makeifTrueRule(WATER_CHECK, makeifTrueRule(SurfaceRules.ON_FLOOR, BWGBlocks.CRACKED_RED_SAND.get())), makeifTrueRule(SurfaceRules.UNDER_FLOOR, Blocks.RED_SANDSTONE));
    private static final SurfaceRules.RuleSource CRACKED_SAND_SURFACE = SurfaceRules.sequence(makeifTrueRule(SurfaceRules.ON_CEILING, Blocks.SANDSTONE), makeifTrueRule(WATER_CHECK, makeifTrueRule(SurfaceRules.ON_FLOOR, BWGBlocks.CRACKED_SAND.get())), makeifTrueRule(SurfaceRules.UNDER_FLOOR, Blocks.SANDSTONE));
    private static final SurfaceRules.RuleSource QUICKSAND_SURFACE = makeifTrueRule(SurfaceRules.noiseCondition(Noises.POWDER_SNOW, 0.45, 0.5), makeifTrueRule(WATER_CHECK, SurfaceRules.sequence(makeifTrueRule(SurfaceRules.ON_FLOOR, makeStateRule(BWGBlocks.QUICKSAND.get())), makeifTrueRule(SurfaceRules.UNDER_FLOOR, makeStateRule(BWGBlocks.QUICKSAND.get())))));
    private static final SurfaceRules.RuleSource CRIMSON_ROCKY_STONE_SURFACE = makeifTrueRule(SurfaceRules.noiseCondition(Noises.POWDER_SNOW, 0.45, 0.5), makeifTrueRule(WATER_CHECK, SurfaceRules.sequence(makeifTrueRule(SurfaceRules.ON_FLOOR, makeStateRule(BWGBlocks.ROCKY_STONE_SET.getBase())), makeifTrueRule(SurfaceRules.UNDER_FLOOR, makeStateRule(BWGBlocks.ROCKY_STONE_SET.getBase())))));
    private static final SurfaceRules.RuleSource POWDER_SNOW_SURFACE = makeifTrueRule(SurfaceRules.noiseCondition(Noises.POWDER_SNOW, 0.45, 0.5), makeifTrueRule(WATER_CHECK, SurfaceRules.sequence(makeifTrueRule(SurfaceRules.ON_FLOOR, makeStateRule(Blocks.POWDER_SNOW)), makeifTrueRule(SurfaceRules.UNDER_FLOOR, makeStateRule(Blocks.POWDER_SNOW)))));
    private static final SurfaceRules.RuleSource RED_QUICKSAND_SURFACE = makeifTrueRule(SurfaceRules.noiseCondition(Noises.POWDER_SNOW, 0.45, 0.5), makeifTrueRule(WATER_CHECK, SurfaceRules.sequence(makeifTrueRule(SurfaceRules.ON_FLOOR, makeStateRule(BWGBlocks.RED_QUICKSAND.get())), makeifTrueRule(SurfaceRules.UNDER_FLOOR, makeStateRule(BWGBlocks.RED_QUICKSAND.get())))));
    private static final SurfaceRules.RuleSource ROOTED_DIRT_SURFACE = SurfaceRules.sequence(makeifTrueRule(WATER_CHECK, makeifTrueRule(SurfaceRules.ON_FLOOR, Blocks.ROOTED_DIRT)), makeifTrueRule(SurfaceRules.UNDER_FLOOR, Blocks.ROOTED_DIRT));
    private static final SurfaceRules.RuleSource GRASS_DIRT_DIRT_SURFACE = SurfaceRules.sequence(makeifTrueRule(WATER_CHECK, makeifTrueRule(SurfaceRules.ON_FLOOR, Blocks.GRASS_BLOCK)), makeifTrueRule(SurfaceRules.UNDER_FLOOR, Blocks.DIRT));
    private static final SurfaceRules.RuleSource MUD_SURFACE = SurfaceRules.sequence(makeifTrueRule(WATER_CHECK, makeifTrueRule(SurfaceRules.ON_FLOOR, Blocks.MUD)), makeifTrueRule(SurfaceRules.UNDER_FLOOR, Blocks.MUD));
    private static final SurfaceRules.RuleSource PACKED_MUD_SURFACE = SurfaceRules.sequence(makeifTrueRule(WATER_CHECK, makeifTrueRule(SurfaceRules.ON_FLOOR, Blocks.PACKED_MUD)), makeifTrueRule(SurfaceRules.UNDER_FLOOR, Blocks.PACKED_MUD));
    private static final SurfaceRules.RuleSource SAND_SURFACE = SurfaceRules.sequence(makeifTrueRule(SurfaceRules.ON_CEILING, Blocks.SANDSTONE), makeifTrueRule(WATER_CHECK, makeifTrueRule(SurfaceRules.ON_FLOOR, Blocks.SAND)), makeifTrueRule(SurfaceRules.UNDER_FLOOR, Blocks.SAND));
    private static final SurfaceRules.RuleSource SEA_LEVEL_WATER_NOISE = makeifTrueRule(SurfaceRules.ON_FLOOR,
            makeifTrueRule(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0),
                    makeifTrueRule(SurfaceRules.not(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0)),
                            makeifTrueRule(SurfaceRules.noiseCondition(Noises.SWAMP, 0.0D), Blocks.WATER)
                    )
            )
    );

    private static final SurfaceRules.RuleSource ASPEN_BOREAL = biomeAbovePreliminarySurface(BWGBiomes.ASPEN_BOREAL, SurfaceRules.sequence(
            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(1.75D),
                    SurfaceRules.sequence(
                            makeifTrueRule(SurfaceRules.ON_FLOOR, Blocks.GRASS_BLOCK),
                            makeifTrueRule(SurfaceRules.UNDER_FLOOR, Blocks.DIRT)
                    )),
            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(-0.95D), ROOTED_DIRT_SURFACE),
            PODZOL_DIRT_SURFACE
    ));

    private static final SurfaceRules.RuleSource ATACAMA_OUTBACK = biomeAbovePreliminarySurface(BWGBiomes.ATACAMA_OUTBACK, SurfaceRules.sequence(
        makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(1.75D),
                SurfaceRules.sequence(
                        makeifTrueRule(SurfaceRules.ON_FLOOR, Blocks.RED_SAND),
                        makeifTrueRule(SurfaceRules.UNDER_FLOOR, Blocks.RED_SANDSTONE)
                )),
            RED_QUICKSAND_SURFACE,
            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(-0.95D), CRACKED_RED_SAND_SURFACE),
            CRACKED_RED_SAND_SURFACE
    ));

    private static final SurfaceRules.RuleSource BAOBAB_SAVANNA = biomeAbovePreliminarySurface(BWGBiomes.BAOBAB_SAVANNA, SurfaceRules.sequence(
            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(1.75D),
                    makeifTrueRule(SurfaceRules.ON_FLOOR, Blocks.COARSE_DIRT))
    ));

    private static final SurfaceRules.RuleSource BASALT_BARRERA = biomeAbovePreliminarySurface(BWGBiomes.BASALT_BARRERA, SurfaceRules.sequence(
            SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(Blocks.BASALT.defaultBlockState())),
            SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.state(Blocks.BASALT.defaultBlockState())),
            SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(10, false, CaveSurface.FLOOR), SurfaceRules.state(Blocks.BASALT.defaultBlockState())),
            SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(10, false, CaveSurface.CEILING), SurfaceRules.state(Blocks.BASALT.defaultBlockState()))
    ));

    private static final SurfaceRules.RuleSource BAYOU = biomeAbovePreliminarySurface(BWGBiomes.BAYOU, SEA_LEVEL_WATER_NOISE);

    private static final SurfaceRules.RuleSource BLACK_FOREST = biomeAbovePreliminarySurface(BWGBiomes.BLACK_FOREST, SurfaceRules.sequence(
            NOISE_COARSE_DIRT,
            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(-0.95D), PEAT_SURFACE)
    ));

    private static final SurfaceRules.RuleSource CANADIAN_SHIELD = biomeAbovePreliminarySurface(BWGBiomes.CANADIAN_SHIELD, OVERGROWN_STONE_STONE_SURFACE);

//    private static final SurfaceRules.RuleSource CANYON = makeifTrueRule(BWGBiomes.CANYON, SurfaceRules.sequence(
//            makeifTrueRule(SurfaceRules.ON_FLOOR, Blocks.TERRACOTTA),
//            makeifTrueRule(SurfaceRules.UNDER_FLOOR, Blocks.TERRACOTTA),
//            makeifTrueRule(SurfaceRules.stoneDepthCheck(10, false, CaveSurface.FLOOR), Blocks.TERRACOTTA)
//    ));

    private static final SurfaceRules.RuleSource CIKA_WOODS = biomeAbovePreliminarySurface(BWGBiomes.CIKA_WOODS, SurfaceRules.sequence(
            NOISE_COARSE_DIRT,
            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(-0.95D), PODZOL_DIRT_SURFACE)
    ));

    private static final SurfaceRules.RuleSource COCONINO_MEADOW = biomeAbovePreliminarySurface(BWGBiomes.COCONINO_MEADOW,
            LUSH_GRASS_LUSH_DIRT_LUSH_DIRT_SURFACE);

    private static final SurfaceRules.RuleSource CONIFEROUS_FOREST = biomeAbovePreliminarySurface(BWGBiomes.CONIFEROUS_FOREST, SurfaceRules.sequence(
            NOISE_COARSE_DIRT,
            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(-0.95D), PEAT_SURFACE)
    ));

    private static final SurfaceRules.RuleSource CRIMSON_TUNDRA = biomeAbovePreliminarySurface(BWGBiomes.CRIMSON_TUNDRA, SurfaceRules.sequence(
            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(1.75D),
                    SurfaceRules.sequence(
                            makeifTrueRule(WATER_CHECK, makeifTrueRule(SurfaceRules.ON_FLOOR, BWGBlocks.LUSH_GRASS_BLOCK.get())),
                            makeifTrueRule(SurfaceRules.UNDER_FLOOR, BWGBlocks.LUSH_DIRT.get())
                    )),
            CRIMSON_ROCKY_STONE_SURFACE,
            PEAT_SURFACE
    ));

    private static final SurfaceRules.RuleSource CYPRESS_MANGROVE = SurfaceRules.ifTrue(
            SurfaceRules.isBiome(BWGBiomes.CYPRESS_SWAMPLANDS, BWGBiomes.WHITE_MANGROVE_MARSHES), abovePreliminarySurface(
            SurfaceRules.sequence(SEA_LEVEL_WATER_NOISE,
                    makeifTrueRule(SurfaceRules.not(WATER_CHECK),
                            makeifTrueRule(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(
                                            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(1.75), Blocks.CLAY),
                                            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(-0.95), Blocks.MUD),
                                            SurfaceRules.state(BWGBlocks.WHITE_SAND_SET.getSand().defaultBlockState())
                                    )
                            )
                    ), SurfaceRules.sequence(
                            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(1.68), SurfaceRules.sequence(
                                    makeifTrueRule(SurfaceRules.ON_FLOOR, BWGBlocks.PEAT.get()),
                                    makeifTrueRule(SurfaceRules.UNDER_FLOOR, BWGBlocks.PEAT.get())
                            )),
                            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(-0.77),
                                    makeifTrueRule(SurfaceRules.ON_FLOOR, Blocks.MOSS_BLOCK)),
                            makeifTrueRule(SurfaceRules.ON_FLOOR, Blocks.GRASS_BLOCK)
                    )
            )
            ));

    private static final SurfaceRules.RuleSource DACITE_RIDGES = biomeAbovePreliminarySurface(BWGBiomes.DACITE_RIDGES, SurfaceRules.sequence(
            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(1.75D),
                    SurfaceRules.sequence(
                            makeifTrueRule(SurfaceRules.ON_FLOOR, Blocks.COARSE_DIRT),
                            makeifTrueRule(SurfaceRules.UNDER_FLOOR, BWGBlocks.DACITE_SET.getBase())
                    )),
            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(-0.95), OVERGROWN_PODZOL_DACITE_STONE_SURFACE),
            OVERGROWN_DACITE_DACITE_SURFACE,
            makeifTrueRule(SurfaceRules.stoneDepthCheck(10 , false, CaveSurface.FLOOR), BWGBlocks.DACITE_SET.getBase()),
            makeifTrueRule(SurfaceRules.stoneDepthCheck(10 , false, CaveSurface.CEILING), BWGBlocks.DACITE_SET.getBase())
    ));

    private static final SurfaceRules.RuleSource DACITE_SHORE = biomeAbovePreliminarySurface(BWGBiomes.DACITE_SHORE,
            SurfaceRules.sequence(
                    makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(1.85D), makeStateRule(BWGBlocks.DACITE_COBBLESTONE_SET.getBase())),
                    makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(-0.95D), SurfaceRules.sequence(makeifTrueRule(SurfaceRules.ON_FLOOR, BWGBlocks.WHITE_SAND_SET.getSand()), makeStateRule(BWGBlocks.DACITE_SET.getBase()))),
                    makeStateRule(BWGBlocks.DACITE_SET.getBase())
            ));

    private static final SurfaceRules.RuleSource DEAD_SEA = biomeAbovePreliminarySurface(BWGBiomes.DEAD_SEA,
            SurfaceRules.sequence(
                    new BetweenRepeatingNoiseRange(Noises.CALCITE, 0.02f, -2f, 2f, STONE, GRAVEL),
                    STONE
            ));

    private static final SurfaceRules.RuleSource EBONY_WOODS = biomeAbovePreliminarySurface(BWGBiomes.EBONY_WOODS,
            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(1.75D), COARSE_DIRT_DIRT_SURFACE));

    private static final SurfaceRules.RuleSource ENCHANTED_TANGLE = biomeAbovePreliminarySurface(BWGBiomes.ENCHANTED_TANGLE, BWGRuleSources.weightedRuleSource(
            SimpleWeightedRandomList.<SurfaceRules.RuleSource>builder()
                    .add(LUSH_GRASS_LUSH_DIRT_LUSH_DIRT_SURFACE, 5)
                    .add(PEAT_SURFACE, 3)
                    .add(COARSE_DIRT_DIRT_SURFACE, 1)
                    .add(MUD_SURFACE, 1)
                    .add(PODZOL_DIRT_SURFACE, 2)
                    .add(PACKED_MUD_SURFACE, 1)
                    .build()
    ));

    private static final SurfaceRules.RuleSource ERODED_BOREALIS = biomeAbovePreliminarySurface(BWGBiomes.ERODED_BOREALIS, SurfaceRules.sequence(
            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(1.75D),
                    SurfaceRules.sequence(
                            makeifTrueRule(SurfaceRules.ON_FLOOR, Blocks.GRASS_BLOCK),
                            makeifTrueRule(SurfaceRules.UNDER_FLOOR, Blocks.DIRT)
                    )),
            POWDER_SNOW_SURFACE,
            PEAT_SURFACE
    ));

    private static final SurfaceRules.RuleSource FORGOTTEN_FOREST = biomeAbovePreliminarySurface(BWGBiomes.FORGOTTEN_FOREST, SurfaceRules.sequence(
            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(1.75D),
                    SurfaceRules.sequence(
                            makeifTrueRule(SurfaceRules.ON_FLOOR, BWGBlocks.LUSH_GRASS_BLOCK.get()),
                            makeifTrueRule(SurfaceRules.UNDER_FLOOR, BWGBlocks.LUSH_DIRT.get())
                    )),
            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(-0.95D), NOISE_COARSE_DIRT),
            PEAT_SURFACE
    ));

    private static final SurfaceRules.RuleSource FRAGMENT_JUNGLE = biomeAbovePreliminarySurface(BWGBiomes.FRAGMENT_JUNGLE, SurfaceRules.sequence(
            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(1.75D),
                    SurfaceRules.sequence(
                            makeifTrueRule(SurfaceRules.ON_FLOOR, BWGBlocks.OVERGROWN_STONE.get()),
                            makeifTrueRule(SurfaceRules.UNDER_FLOOR, Blocks.STONE)
                    )),
            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(-0.95D), PEAT_SURFACE),
            PODZOL_DIRT_SURFACE
    ));

    private static final SurfaceRules.RuleSource FROSTED_CONIFEROUS_FOREST = biomeAbovePreliminarySurface(BWGBiomes.CONIFEROUS_FOREST, SurfaceRules.sequence(
            NOISE_COARSE_DIRT,
            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(-0.95D), PEAT_SURFACE)
    ));

    private static final SurfaceRules.RuleSource FROSTED_TIAGA = biomeAbovePreliminarySurface(BWGBiomes.FROSTED_TAIGA, SurfaceRules.sequence(
            NOISE_COARSE_DIRT,
            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(-0.95D), PEAT_SURFACE)
    ));

    private static final SurfaceRules.RuleSource HOWLING_PEAKS = biomeAbovePreliminarySurface(BWGBiomes.HOWLING_PEAKS,
            SurfaceRules.sequence(
                    makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(1.75D),
                            SurfaceRules.sequence(
                                    makeifTrueRule(SurfaceRules.ON_FLOOR, Blocks.GRASS_BLOCK),
                                    makeifTrueRule(SurfaceRules.UNDER_FLOOR, Blocks.DIRT)
                            )),
                    makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(-0.95D), PEAT_SURFACE),
                    PODZOL_DIRT_SURFACE
            )
    );

    private static final SurfaceRules.RuleSource IRONWOOD_GOUR = biomeAbovePreliminarySurface(BWGBiomes.IRONWOOD_GOUR, SurfaceRules.sequence(
            NOISE_COARSE_DIRT,
            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(-0.95D), PACKED_MUD_SURFACE)
    ));

    private static final SurfaceRules.RuleSource JACARANDA_JUNGLE = biomeAbovePreliminarySurface(BWGBiomes.JACARANDA_JUNGLE, BWGRuleSources.weightedRuleSource(
            SimpleWeightedRandomList.<SurfaceRules.RuleSource>builder()
                    .add(LUSH_GRASS_LUSH_DIRT_LUSH_DIRT_SURFACE, 5)
                    .add(PEAT_SURFACE, 3)
                    .add(COARSE_DIRT_DIRT_SURFACE, 1)
                    .add(MUD_SURFACE, 1)
                    .add(PODZOL_DIRT_SURFACE, 2)
                    .add(PACKED_MUD_SURFACE, 1)
                    .build()
    ));

    private static final SurfaceRules.RuleSource LUSH_STACKS = biomeAbovePreliminarySurface(BWGBiomes.LUSH_STACKS,
            SurfaceRules.sequence(
                    new BetweenRepeatingNoiseRange(Noises.CALCITE, 0.02f, -2f, 2f, STONE, GRAVEL),
                    STONE
            ));

    private static final SurfaceRules.RuleSource MOJAVE_DESERT = biomeAbovePreliminarySurface(BWGBiomes.MOJAVE_DESERT, BWGRuleSources.weightedRuleSource(
            SimpleWeightedRandomList.<SurfaceRules.RuleSource>builder()
                    .add(SAND_SURFACE, 3)
                    .add(SANDY_DIRT_SURFACE, 1)
                    .add(CRACKED_SAND_SURFACE, 1)
                    .build()
    ));

    private static final SurfaceRules.RuleSource OVERGROWTH_WOODLANDS = biomeAbovePreliminarySurface(BWGBiomes.OVERGROWTH_WOODLANDS, SurfaceRules.sequence(
            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(1.75D),
                    SurfaceRules.sequence(
                            makeifTrueRule(SurfaceRules.ON_FLOOR, Blocks.MOSS_BLOCK),
                            makeifTrueRule(SurfaceRules.UNDER_FLOOR, Blocks.MOSS_BLOCK)
                    )),
            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(-0.95D), PEAT_SURFACE),
            LUSH_GRASS_LUSH_DIRT_LUSH_DIRT_SURFACE
    ));

    private static final SurfaceRules.RuleSource PALE_BOG = biomeAbovePreliminarySurface(BWGBiomes.PALE_BOG, SurfaceRules.sequence(
            SEA_LEVEL_WATER_NOISE,
                    makeifTrueRule(SurfaceRules.not(WATER_CHECK),
                            makeifTrueRule(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(
                                            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(1.75), BWGBlocks.PACKED_PALE_MUD.get()),
                                            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(-0.95), BWGBlocks.BLACK_SAND_SET.getSand()),
                                            makeStateRule(BWGBlocks.PALE_MUD.get())
                                    )
                            )
                    ), SurfaceRules.sequence(
                            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(1.68), SurfaceRules.sequence(
                                    makeifTrueRule(SurfaceRules.ON_FLOOR, BWGBlocks.PEAT.get()),
                                    makeifTrueRule(SurfaceRules.UNDER_FLOOR, BWGBlocks.PALE_MUD.get())
                            )),
                            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(-0.77),
                                    makeifTrueRule(SurfaceRules.ON_FLOOR, BWGBlocks.LUSH_GRASS_BLOCK.get())),
                            makeifTrueRule(SurfaceRules.UNDER_FLOOR, BWGBlocks.PALE_MUD.get())
                    )
            )
    );

    private static final SurfaceRules.RuleSource PINE_BARRENS = biomeAbovePreliminarySurface(BWGBiomes.PINE_BARRENS, SurfaceRules.sequence(
            makeifTrueRule(SurfaceRules.ON_FLOOR, BWGBlocks.SANDY_DIRT.get()),
            makeifTrueRule(SurfaceRules.UNDER_FLOOR, Blocks.COARSE_DIRT)));
    private static final SurfaceRules.RuleSource PINE_BARRENS = biomeAbovePreliminarySurface(BWGBiomes.PINE_BARRENS, BWGRuleSources.weightedRuleSource(
            SimpleWeightedRandomList.<SurfaceRules.RuleSource>builder()
                    .add(COARSE_DIRT_DIRT_SURFACE, 3)
                    .add(PODZOL_DIRT_SURFACE, 3)
                    .add(MUD_SURFACE, 1)
                    .build()
    ));

    private static final SurfaceRules.RuleSource PUMPKIN_VALLEY = biomeAbovePreliminarySurface(BWGBiomes.PUMPKIN_VALLEY, LUSH_GRASS_LUSH_DIRT_LUSH_DIRT_SURFACE);

    private static final SurfaceRules.RuleSource RAINBOW_BEACH = biomeAbovePreliminarySurface(BWGBiomes.RAINBOW_BEACH,
        SurfaceRules.sequence(
                new BetweenRepeatingNoiseRange(Noises.CALCITE, 0.02f, -2f, 2f, PURPLE_SAND, WHITE_SAND, BLACK_SAND, PINK_SAND),
                BLUE_SAND
        ));


    private static final SurfaceRules.RuleSource RED_ROCK_VALLEY = biomeAbovePreliminarySurface(BWGBiomes.RED_ROCK_VALLEY, SurfaceRules.sequence(
       NOISE_COARSE_DIRT,
       makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(-0.95D), SurfaceRules.bandlands()),
       makeStateRule(BWGBlocks.RED_ROCK_SET.getBase())
    ));

    private static final SurfaceRules.RuleSource REDWOOD_THICKET = biomeAbovePreliminarySurface(BWGBiomes.REDWOOD_THICKET,
            SurfaceRules.sequence(
                    NOISE_COARSE_DIRT,
                    makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(-0.95D), PODZOL_DIRT_SURFACE)
            ));

    private static final SurfaceRules.RuleSource RUGGED_BADLANDS = biomeAbovePreliminarySurface(BWGBiomes.RUGGED_BADLANDS, SurfaceRules.sequence(
            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(1.75D),
                    SurfaceRules.sequence(
                            makeifTrueRule(SurfaceRules.ON_FLOOR, Blocks.SAND),
                            makeifTrueRule(SurfaceRules.UNDER_FLOOR, Blocks.SANDSTONE)
                    )),
            QUICKSAND_SURFACE,
            CRACKED_SAND_SURFACE
    ));

    private static final SurfaceRules.RuleSource SAKURA_GROVE = biomeAbovePreliminarySurface(BWGBiomes.SAKURA_GROVE, SurfaceRules.sequence(
            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(1.75D),
                    makeifTrueRule(SurfaceRules.ON_FLOOR, Blocks.PODZOL))
    ));

/*
    private static final SurfaceRules.RuleSource SHATTERED_GLACIER = makeifTrueRule(BWGBiomes.SHATTERED_GLACIER, makeifTrueRule(SurfaceRules.abovePreliminarySurface(), SurfaceRules.sequence(

    ))); */

    private static final SurfaceRules.RuleSource SIERRA_BADLANDS = biomeAbovePreliminarySurface(BWGBiomes.SIERRA_BADLANDS, SurfaceRules.sequence(
            NOISE_COARSE_DIRT,
            makeifTrueRule(SurfaceRules.not(SurfaceRuleData.surfaceNoiseAbove(-0.95D)), GRASS_DIRT_DIRT_SURFACE), SurfaceRules.bandlands()
    ));

    private static final SurfaceRules.RuleSource SKYRIS_VALE = biomeAbovePreliminarySurface(BWGBiomes.SKYRIS_VALE, SurfaceRules.sequence(
            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(1.75D),
                    SurfaceRules.sequence(
                            makeifTrueRule(SurfaceRules.ON_FLOOR, BWGBlocks.LUSH_GRASS_BLOCK.get()),
                            makeifTrueRule(SurfaceRules.UNDER_FLOOR, BWGBlocks.LUSH_DIRT.get())
                    )),
            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(-0.95D), OVERGROWN_PODZOL_DACITE_STONE_SURFACE),
            OVERGROWN_DACITE_DACITE_SURFACE,
            makeifTrueRule(SurfaceRules.stoneDepthCheck(10, false, CaveSurface.FLOOR), BWGBlocks.DACITE_SET.getBase()),
            makeifTrueRule(SurfaceRules.stoneDepthCheck(10, false, CaveSurface.CEILING), BWGBlocks.DACITE_SET.getBase())
    ));

    private static final SurfaceRules.RuleSource WEEPING_WITCH_FOREST = biomeAbovePreliminarySurface(BWGBiomes.WEEPING_WITCH_FOREST, SurfaceRules.sequence(
            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(1.75D),
                    SurfaceRules.sequence(
                            makeifTrueRule(SurfaceRules.ON_FLOOR, BWGBlocks.LUSH_GRASS_BLOCK.get()),
                            makeifTrueRule(SurfaceRules.UNDER_FLOOR, BWGBlocks.LUSH_DIRT.get())
                    )),
            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(-0.95D), NOISE_COARSE_DIRT),
            PEAT_SURFACE
    ));

//    private static final SurfaceRules.RuleSource TROPICAL_ISLAND = biomeAbovePreliminarySurface(BWGBiomes.TROPICAL_ISLAND, SurfaceRules.sequence(
//            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(1.75D),
//                    SurfaceRules.sequence(
//                            makeifTrueRule(SurfaceRules.ON_FLOOR, Blocks.MOSS_BLOCK),
//                            makeifTrueRule(SurfaceRules.UNDER_FLOOR, Blocks.MOSS_BLOCK)
//                    )),
//            makeifTrueRule(SurfaceRuleData.surfaceNoiseAbove(-0.95D), PODZOL_DIRT_SURFACE),
//            LUSH_GRASS_LUSH_DIRT_LUSH_DIRT_SURFACE
//    ));

    private static final SurfaceRules.RuleSource BLACK_ICE_BANDS = biomeAbovePreliminarySurface(BWGBiomes.SHATTERED_GLACIER, new BandsRuleSource(SimpleWeightedRandomList.<BlockState>builder().add(BWGBlocks.PACKED_BLACK_ICE.get().defaultBlockState(), 3).add(BWGBlocks.BLACK_ICE.get().defaultBlockState(), 1).build(), UniformInt.of(1, 5), UniformInt.of(20, 40), 1, 10));
//    public static final SurfaceRules.RuleSource BOREALIS_ICE_BANDS = biomeAbovePreliminarySurface(BWGBiomes.ERODED_BOREALIS, new BandsRuleSource(SimpleWeightedRandomList.<BlockState>builder().add(BWGBlocks.PACKED_BOREALIS_ICE.get().defaultBlockState(), 3).add(BWGBlocks.BOREALIS_ICE.get().defaultBlockState(), 1).build(), UniformInt.of(1, 5), UniformInt.of(20, 40), 1, 10));

    private static final SurfaceRules.RuleSource WINDSWEPT_DESERT = biomeAbovePreliminarySurface(BWGBiomes.WINDSWEPT_DESERT, makeSandRule(BWGBlocks.WINDSWEPT_SAND_SET));

    /**
     * Makes the surface rules for the overworld biomes in Oh The Biomes We've Gone.
     * @return The surface rules for the overworld biomes in Oh The Biomes We've Gone.
     */
    public static SurfaceRules.RuleSource makeRules() {
        return SurfaceRules.sequence(
                ASPEN_BOREAL,
                ATACAMA_OUTBACK,
                BAOBAB_SAVANNA,
                BASALT_BARRERA,
                BAYOU,
                BLACK_FOREST,
                BLACK_ICE_BANDS,
                CANADIAN_SHIELD,
//                CANYON,
                CIKA_WOODS,
                COCONINO_MEADOW,
                CONIFEROUS_FOREST,
                CYPRESS_MANGROVE,
                CRIMSON_TUNDRA,
                DACITE_RIDGES,
                DACITE_SHORE,
                DEAD_SEA,
                EBONY_WOODS,
                ENCHANTED_TANGLE,
                ERODED_BOREALIS,
                FORGOTTEN_FOREST,
                FRAGMENT_JUNGLE,
                FROSTED_CONIFEROUS_FOREST,
                FROSTED_TIAGA,
                HOWLING_PEAKS,
                IRONWOOD_GOUR,
                JACARANDA_JUNGLE,
                LUSH_STACKS,
                MOJAVE_DESERT,
                OVERGROWTH_WOODLANDS,
                PALE_BOG,
                PINE_BARRENS,
                PUMPKIN_VALLEY,
                RAINBOW_BEACH,
                RED_ROCK_VALLEY,
                REDWOOD_THICKET,
                RUGGED_BADLANDS,
                SAKURA_GROVE,
                //SHATTERED_GLACIER,
                SIERRA_BADLANDS,
                SKYRIS_VALE,
                WEEPING_WITCH_FOREST,
                WINDSWEPT_DESERT
//                TROPICAL_ISLAND,
        );
    }

    // Helper methods
    private static SurfaceRules.RuleSource makeSandRule(@NotNull BWGSandSet sandSet) {
        return SurfaceRules.sequence(
                makeifTrueRule(SurfaceRules.ON_FLOOR, sandSet.getSand()),
                makeifTrueRule(SurfaceRules.UNDER_FLOOR, sandSet.getSand()),
                makeifTrueRule(SurfaceRules.stoneDepthCheck(10, false, CaveSurface.FLOOR), sandSet.getSandstone()),
                makeifTrueRule(SurfaceRules.stoneDepthCheck(10, false, CaveSurface.CEILING), sandSet.getSandstone())
        );
    }


    /**
     * Makes a State Rule for a block.
     * @param block The block to make the rule for.
     * @return The State rule
     */
    private static <B extends Block> SurfaceRules.RuleSource makeStateRule(B block) {
        return SurfaceRules.state(block.defaultBlockState());
    }

    /**
     * Makes a rule that checks if a biome is true.
     * @param biome The biome to check for.
     * @param rule(s) The rule(s) to apply if the biome is true.
     * @return the surface rule
     */
    private static SurfaceRules.RuleSource makeifTrueRule(ResourceKey<Biome> biome, SurfaceRules.RuleSource rule) {
        return makeifTrueRule(SurfaceRules.isBiome(biome), rule);
    }

    /**
     * Makes a rule that checks if a condition is true.
     * @param conditionSource The condition to check for.
     * @param block The block to apply if the condition is true.
     * @return the surface rule
     */
    private static <B extends Block> SurfaceRules.RuleSource makeifTrueRule(SurfaceRules.ConditionSource conditionSource, B block) {
        return makeifTrueRule(conditionSource, makeStateRule(block));
    }

    private static SurfaceRules.RuleSource makeifTrueRule(SurfaceRules.ConditionSource ifTrue, SurfaceRules.RuleSource thenRun) {
        return SurfaceRules.ifTrue(ifTrue, thenRun);
    }

    /**
     * Makes an if true rule for above preliminary surface.
     * @param rule The rule to apply if the condition is true.
     * @return the surface rule
     */
    private static SurfaceRules.RuleSource abovePreliminarySurface(SurfaceRules.RuleSource rule) {
        return makeifTrueRule(SurfaceRules.abovePreliminarySurface(), rule);
    }


    private static SurfaceRules.RuleSource biomeAbovePreliminarySurface(ResourceKey<Biome> biome, SurfaceRules.RuleSource rule) {
        return makeifTrueRule(biome, abovePreliminarySurface(rule));
    }
}
