package net.potionstudios.biomeswevegone.world.level.levelgen.biome.selector;

import corgitaco.corgilib.serialization.codec.Wrapped;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.potionstudios.biomeswevegone.world.level.levelgen.biome.BWGBiomes;
import terrablender.api.Region;

import java.util.List;

public class BWGBiomeSelectors {

    public static final Wrapped<List<List<ResourceKey<Biome>>>> OCEANS_BWG = BiomeSelectorsUtil.create("oceans/oceans_biomes_weve_gone", "", new ResourceKey[][]{
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, BWGBiomes.LUSH_STACKS, BWGBiomes.DEAD_SEA},
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, BWGBiomes.LUSH_STACKS, BWGBiomes.DEAD_SEA}
    });

    public static final Wrapped<List<List<ResourceKey<Biome>>>> OCEANS_2_BWG = BiomeSelectorsUtil.create("oceans/oceans_2_biomes_weve_gone", "", new ResourceKey[][]{
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, BWGBiomes.LUSH_STACKS, BWGBiomes.DEAD_SEA},
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, BWGBiomes.LUSH_STACKS, BWGBiomes.DEAD_SEA}
    });

    public static final Wrapped<List<List<ResourceKey<Biome>>>> MIDDLE_BIOMES_BWG = BiomeSelectorsUtil.create("middle_biomes/middle_biomes_biomes_weve_gone", "", new ResourceKey[][]{
            {BWGBiomes.SHATTERED_GLACIER, BWGBiomes.CRIMSON_TUNDRA, BWGBiomes.CRIMSON_TUNDRA, BWGBiomes.FROSTED_CONIFEROUS_FOREST, BWGBiomes.CONIFEROUS_FOREST},
            {BWGBiomes.PUMPKIN_VALLEY, BWGBiomes.PUMPKIN_VALLEY, BWGBiomes.SKYRIS_VALE, BWGBiomes.SKYRIS_VALE, BWGBiomes.CIKA_WOODS},
            {BWGBiomes.PRAIRIE, BWGBiomes.ALLIUM_SHRUBLAND, BWGBiomes.ALLIUM_SHRUBLAND, BWGBiomes.TEMPERATE_GROVE, BWGBiomes.TEMPERATE_GROVE},
            {BWGBiomes.ARAUCARIA_SAVANNA, BWGBiomes.IRONWOOD_GOUR, BWGBiomes.AMARANTH_GRASSLAND, BWGBiomes.EBONY_WOODS, BWGBiomes.JACARANDA_JUNGLE},
            {BWGBiomes.MOJAVE_DESERT, BWGBiomes.MOJAVE_DESERT, BWGBiomes.WINDSWEPT_DESERT, BWGBiomes.ATACAMA_OUTBACK, BWGBiomes.ATACAMA_OUTBACK}
    });

    public static final Wrapped<List<List<ResourceKey<Biome>>>> MIDDLE_BIOMES_2_BWG = BiomeSelectorsUtil.create("middle_biomes/middle_biomes_2_biomes_weve_gone", "", new ResourceKey[][]{
            {BWGBiomes.SHATTERED_GLACIER, BWGBiomes.CRIMSON_TUNDRA, BWGBiomes.FROSTED_CONIFEROUS_FOREST, BWGBiomes.ERODED_BOREALIS, BWGBiomes.CONIFEROUS_FOREST},
            {BWGBiomes.COCONINO_MEADOW, BWGBiomes.COCONINO_MEADOW, BWGBiomes.MAPLE_TAIGA, BWGBiomes.DACITE_RIDGES, BWGBiomes.DACITE_RIDGES},
            {BWGBiomes.PRAIRIE, BWGBiomes.ORCHARD, BWGBiomes.ORCHARD, BWGBiomes.ASPEN_BOREAL, BWGBiomes.WEEPING_WITCH_FOREST},
            {BWGBiomes.BAOBAB_SAVANNA, BWGBiomes.BAOBAB_SAVANNA, BWGBiomes.AMARANTH_GRASSLAND, BWGBiomes.CRAG_GARDENS, BWGBiomes.TROPICAL_RAINFOREST},
            {BWGBiomes.MOJAVE_DESERT, BWGBiomes.RUGGED_BADLANDS, BWGBiomes.WINDSWEPT_DESERT, BWGBiomes.ATACAMA_OUTBACK, BWGBiomes.ATACAMA_OUTBACK}
    });

    public static final Wrapped<List<List<ResourceKey<Biome>>>> MIDDLE_BIOMES_3_BWG = BiomeSelectorsUtil.create("middle_biomes/middle_biomes_3_biomes_weve_gone", "", new ResourceKey[][]{
            {BWGBiomes.SHATTERED_GLACIER, BWGBiomes.SHATTERED_GLACIER, BWGBiomes.FROSTED_CONIFEROUS_FOREST, BWGBiomes.FROSTED_CONIFEROUS_FOREST, BWGBiomes.CONIFEROUS_FOREST},
            {BWGBiomes.COCONINO_MEADOW, BWGBiomes.COCONINO_MEADOW, BWGBiomes.MAPLE_TAIGA, BWGBiomes.ZELKOVA_FOREST, BWGBiomes.ZELKOVA_FOREST},
            {BWGBiomes.PRAIRIE, BWGBiomes.PRAIRIE, BWGBiomes.ALLIUM_SHRUBLAND, BWGBiomes.ASPEN_BOREAL, BWGBiomes.WEEPING_WITCH_FOREST},
            {BWGBiomes.FIRECRACKER_CHAPARRAL, BWGBiomes.IRONWOOD_GOUR, BWGBiomes.AMARANTH_GRASSLAND, BWGBiomes.ENCHANTED_TANGLE, BWGBiomes.FRAGMENT_JUNGLE},
            {BWGBiomes.MOJAVE_DESERT, BWGBiomes.RUGGED_BADLANDS, BWGBiomes.WINDSWEPT_DESERT, BWGBiomes.ATACAMA_OUTBACK, BWGBiomes.ATACAMA_OUTBACK}
    });

    public static final Wrapped<List<List<ResourceKey<Biome>>>> MIDDLE_BIOMES_VARIANT_BWG = BiomeSelectorsUtil.create("middle_biomes_variant/middle_biomes_variant_biomes_weve_gone", "", new ResourceKey[][]{
            {Region.DEFERRED_PLACEHOLDER, Biomes.THE_VOID, Region.DEFERRED_PLACEHOLDER, Biomes.THE_VOID, Biomes.THE_VOID},
            {Biomes.THE_VOID, Biomes.THE_VOID, BWGBiomes.FORGOTTEN_FOREST, Biomes.THE_VOID, Region.DEFERRED_PLACEHOLDER},
            {Region.DEFERRED_PLACEHOLDER, Biomes.THE_VOID, BWGBiomes.FORGOTTEN_FOREST, Biomes.THE_VOID, Biomes.THE_VOID},
            {Biomes.THE_VOID, Biomes.THE_VOID, Region.DEFERRED_PLACEHOLDER, BWGBiomes.CRAG_GARDENS, BWGBiomes.CRAG_GARDENS},
            {Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID}
    });
    public static final Wrapped<List<List<ResourceKey<Biome>>>> PLATEAU_BIOMES_BWG = BiomeSelectorsUtil.create("plateau_biomes/plateau_biomes_biomes_weve_gone", "", new ResourceKey[][]{
            {BWGBiomes.SHATTERED_GLACIER, BWGBiomes.SHATTERED_GLACIER, BWGBiomes.FROSTED_CONIFEROUS_FOREST, BWGBiomes.FROSTED_TAIGA, BWGBiomes.CONIFEROUS_FOREST},
            {BWGBiomes.COCONINO_MEADOW, BWGBiomes.COCONINO_MEADOW, BWGBiomes.ASPEN_BOREAL, BWGBiomes.SKYRIS_VALE, BWGBiomes.SKYRIS_VALE},
            {BWGBiomes.TEMPERATE_GROVE, BWGBiomes.TEMPERATE_GROVE, BWGBiomes.TEMPERATE_GROVE, BWGBiomes.ROSE_FIELDS, BWGBiomes.DACITE_RIDGES},
            {BWGBiomes.BAOBAB_SAVANNA, BWGBiomes.BAOBAB_SAVANNA, BWGBiomes.REDWOOD_THICKET, BWGBiomes.JACARANDA_JUNGLE, BWGBiomes.CRAG_GARDENS},
            {BWGBiomes.RED_ROCK_VALLEY, BWGBiomes.RED_ROCK_VALLEY, BWGBiomes.RED_ROCK_VALLEY, BWGBiomes.SIERRA_BADLANDS, BWGBiomes.SIERRA_BADLANDS}
    });

    public static final Wrapped<List<List<ResourceKey<Biome>>>> PLATEAU_BIOMES_2_BWG = BiomeSelectorsUtil.create("plateau_biomes/plateau_biomes_2_biomes_weve_gone", "", new ResourceKey[][]{
            {BWGBiomes.SHATTERED_GLACIER, BWGBiomes.SHATTERED_GLACIER, BWGBiomes.FROSTED_CONIFEROUS_FOREST, BWGBiomes.FROSTED_TAIGA, BWGBiomes.CONIFEROUS_FOREST},
            {BWGBiomes.COCONINO_MEADOW, BWGBiomes.COCONINO_MEADOW, BWGBiomes.BLACK_FOREST, BWGBiomes.BLACK_FOREST, BWGBiomes.BLACK_FOREST},
            {BWGBiomes.SAKURA_GROVE, BWGBiomes.SAKURA_GROVE, BWGBiomes.SAKURA_GROVE, BWGBiomes.ROSE_FIELDS, BWGBiomes.WEEPING_WITCH_FOREST},
            {BWGBiomes.ARAUCARIA_SAVANNA, BWGBiomes.ARAUCARIA_SAVANNA, BWGBiomes.REDWOOD_THICKET, BWGBiomes.EBONY_WOODS, BWGBiomes.TROPICAL_RAINFOREST},
            {BWGBiomes.RED_ROCK_VALLEY, BWGBiomes.RED_ROCK_VALLEY, BWGBiomes.RED_ROCK_VALLEY, BWGBiomes.SIERRA_BADLANDS, BWGBiomes.SIERRA_BADLANDS}
    });

    public static final Wrapped<List<List<ResourceKey<Biome>>>> PLATEAU_BIOMES_3_BWG = BiomeSelectorsUtil.create("plateau_biomes/plateau_biomes_3_biomes_weve_gone", "", new ResourceKey[][]{
            {BWGBiomes.SHATTERED_GLACIER, BWGBiomes.SHATTERED_GLACIER, BWGBiomes.FROSTED_CONIFEROUS_FOREST, BWGBiomes.FROSTED_TAIGA, BWGBiomes.CONIFEROUS_FOREST},
            {BWGBiomes.COCONINO_MEADOW, BWGBiomes.COCONINO_MEADOW, BWGBiomes.PINE_BARRENS, BWGBiomes.CIKA_WOODS, BWGBiomes.CIKA_WOODS},
            {BWGBiomes.TEMPERATE_GROVE, BWGBiomes.TEMPERATE_GROVE, BWGBiomes.TEMPERATE_GROVE, BWGBiomes.ROSE_FIELDS, BWGBiomes.WEEPING_WITCH_FOREST},
            {BWGBiomes.FIRECRACKER_CHAPARRAL, BWGBiomes.FIRECRACKER_CHAPARRAL, BWGBiomes.REDWOOD_THICKET, BWGBiomes.EBONY_WOODS, BWGBiomes.ENCHANTED_TANGLE},
            {BWGBiomes.RED_ROCK_VALLEY, BWGBiomes.RED_ROCK_VALLEY, BWGBiomes.RED_ROCK_VALLEY, BWGBiomes.SIERRA_BADLANDS, BWGBiomes.SIERRA_BADLANDS}
    });

    public static final Wrapped<List<List<ResourceKey<Biome>>>> PLATEAU_BIOMES_VARIANT_BWG = BiomeSelectorsUtil.create("plateau_biomes_variant/plateau_biomes_variant_biomes_weve_gone", "", new ResourceKey[][]{
            {Region.DEFERRED_PLACEHOLDER, Biomes.THE_VOID, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Biomes.THE_VOID},
            {BWGBiomes.SAKURA_GROVE, BWGBiomes.OVERGROWTH_WOODLANDS, BWGBiomes.OVERGROWTH_WOODLANDS, Biomes.THE_VOID, Region.DEFERRED_PLACEHOLDER},
            {BWGBiomes.SAKURA_GROVE, BWGBiomes.SAKURA_GROVE, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
            {Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID, BWGBiomes.CRAG_GARDENS},
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID}
    });

    public static final Wrapped<List<List<ResourceKey<Biome>>>> PEAK_BIOMES_BWG = BiomeSelectorsUtil.create("peak_biomes/peak_biomes_biomes_weve_gone", "", new ResourceKey[][]{
            {Region.DEFERRED_PLACEHOLDER, BWGBiomes.HOWLING_PEAKS, BWGBiomes.ERODED_BOREALIS, BWGBiomes.ERODED_BOREALIS, BWGBiomes.ERODED_BOREALIS},
            {Region.DEFERRED_PLACEHOLDER, BWGBiomes.HOWLING_PEAKS, BWGBiomes.ERODED_BOREALIS, BWGBiomes.ERODED_BOREALIS, BWGBiomes.ERODED_BOREALIS},
            {Region.DEFERRED_PLACEHOLDER, BWGBiomes.HOWLING_PEAKS, BWGBiomes.HOWLING_PEAKS, BWGBiomes.HOWLING_PEAKS, BWGBiomes.HOWLING_PEAKS},
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, BWGBiomes.HOWLING_PEAKS, BWGBiomes.HOWLING_PEAKS, BWGBiomes.CRAG_GARDENS},
            {BWGBiomes.RED_ROCK_VALLEY, BWGBiomes.RED_ROCK_VALLEY, BWGBiomes.SIERRA_BADLANDS, BWGBiomes.SIERRA_BADLANDS, BWGBiomes.SIERRA_BADLANDS}
    });

    public static final Wrapped<List<List<ResourceKey<Biome>>>> SHATTERED_BIOMES_BWG = BiomeSelectorsUtil.create("shattered_biomes/shattered_biomes_biomes_weve_gone", "", new ResourceKey[][]{
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
            {Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID},
            {Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID}
    });

    public static final Wrapped<List<List<ResourceKey<Biome>>>> BEACH_BIOMES_BWG = BiomeSelectorsUtil.create("beach_biomes/beach_biomes_biomes_weve_gone", "", new ResourceKey[][]{
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
            {Biomes.BEACH, Biomes.BEACH, Biomes.BEACH, BWGBiomes.DACITE_SHORE, BWGBiomes.DACITE_SHORE},
            {BWGBiomes.BASALT_BARRERA, BWGBiomes.BASALT_BARRERA, BWGBiomes.BASALT_BARRERA, BWGBiomes.BASALT_BARRERA, BWGBiomes.BASALT_BARRERA},
            {BWGBiomes.RAINBOW_BEACH, BWGBiomes.RAINBOW_BEACH, BWGBiomes.RAINBOW_BEACH, BWGBiomes.RAINBOW_BEACH, BWGBiomes.RAINBOW_BEACH},
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER}
    });

    public static final Wrapped<List<List<ResourceKey<Biome>>>> PEAK_BIOMES_VARIANT_BWG = BiomeSelectorsUtil.create("peak_biomes_variant/peak_biomes_variant_biomes_weve_gone", "", new ResourceKey[][]{
            {Region.DEFERRED_PLACEHOLDER, BWGBiomes.HOWLING_PEAKS, BWGBiomes.ERODED_BOREALIS, BWGBiomes.ERODED_BOREALIS, BWGBiomes.HOWLING_PEAKS},
            {Region.DEFERRED_PLACEHOLDER, BWGBiomes.HOWLING_PEAKS, BWGBiomes.HOWLING_PEAKS, BWGBiomes.HOWLING_PEAKS, BWGBiomes.HOWLING_PEAKS},
            {Region.DEFERRED_PLACEHOLDER, BWGBiomes.HOWLING_PEAKS, BWGBiomes.HOWLING_PEAKS, BWGBiomes.HOWLING_PEAKS, BWGBiomes.HOWLING_PEAKS},
            {Region.DEFERRED_PLACEHOLDER, BWGBiomes.HOWLING_PEAKS, BWGBiomes.HOWLING_PEAKS, BWGBiomes.HOWLING_PEAKS, BWGBiomes.CRAG_GARDENS},
            {BWGBiomes.RED_ROCK_VALLEY, BWGBiomes.RED_ROCK_VALLEY, BWGBiomes.SIERRA_BADLANDS, BWGBiomes.SIERRA_BADLANDS, BWGBiomes.SIERRA_BADLANDS}
    });

    public static final Wrapped<List<List<ResourceKey<Biome>>>> SLOPE_BIOMES_BWG = BiomeSelectorsUtil.create("slope_biomes/slope_biomes_biomes_weve_gone", "", new ResourceKey[][]{
            {BWGBiomes.SHATTERED_GLACIER, BWGBiomes.ERODED_BOREALIS, BWGBiomes.ERODED_BOREALIS, BWGBiomes.FROSTED_TAIGA, BWGBiomes.FROSTED_TAIGA},
            {BWGBiomes.SHATTERED_GLACIER, BWGBiomes.ERODED_BOREALIS, BWGBiomes.CANADIAN_SHIELD, BWGBiomes.CONIFEROUS_FOREST, BWGBiomes.FROSTED_CONIFEROUS_FOREST},
            {BWGBiomes.SHATTERED_GLACIER, BWGBiomes.HOWLING_PEAKS, BWGBiomes.CANADIAN_SHIELD, BWGBiomes.CONIFEROUS_FOREST, BWGBiomes.FROSTED_CONIFEROUS_FOREST},
            {BWGBiomes.RED_ROCK_VALLEY, BWGBiomes.RED_ROCK_VALLEY, BWGBiomes.REDWOOD_THICKET, BWGBiomes.REDWOOD_THICKET, BWGBiomes.CRAG_GARDENS},
            {BWGBiomes.RED_ROCK_VALLEY, BWGBiomes.RED_ROCK_VALLEY, BWGBiomes.SIERRA_BADLANDS, BWGBiomes.SIERRA_BADLANDS, BWGBiomes.SIERRA_BADLANDS}
    });
}
