package net.potionstudios.biomeswevegone.world.level.levelgen.biome;

import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.tags.BWGBiomeTags;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class BWGBiomes {

    public static final Map<ResourceKey<Biome>, BiomeFactory> BIOME_FACTORIES = new Reference2ObjectOpenHashMap<>();
    public static final Multimap<TagKey<Biome>, ResourceKey<Biome>> BIOMES_BY_TAG = Multimaps.newSetMultimap(new HashMap<>(), HashSet::new);


    /************Overworld Biomes************/
    public static final ResourceKey<Biome> ALLIUM_SHRUBLAND = createBiome("allium_shrubland", BWGOverworldBiomes::alliumShrubland, BWGBiomeTags.PLAINS, BWGBiomeTags.FLORAL, BWGBiomeTags.GRASS_BLOCK_FLOWER_BONEMEAL);
    public static final ResourceKey<Biome> AMARANTH_GRASSLAND = createBiome("amaranth_grassland", BWGOverworldBiomes::amaranthGrassland, BWGBiomeTags.PLAINS, BWGBiomeTags.FLORAL, BWGBiomeTags.SPARSE, BWGBiomeTags.GRASS_BLOCK_FLOWER_BONEMEAL);
    public static final ResourceKey<Biome> ARAUCARIA_SAVANNA = createBiome("araucaria_savanna", BWGOverworldBiomes::araucariaSavanna, BWGBiomeTags.SAVANNA, BWGBiomeTags.SPARSE, BiomeTags.HAS_TRAIL_RUINS);
    public static final ResourceKey<Biome> ASPEN_BOREAL = createBiome("aspen_boreal", BWGOverworldBiomes::aspenBoreal, BWGBiomeTags.FOREST, BWGBiomeTags.CONIFEROUS, BiomeTags.HAS_VILLAGE_TAIGA, BiomeTags.HAS_PILLAGER_OUTPOST, BWGBiomeTags.StructureHasTags.HAS_ASPEN_MANOR);
    public static final ResourceKey<Biome> ATACAMA_OUTBACK = createBiome("atacama_outback", BWGOverworldBiomes::atacamaOutback, BWGBiomeTags.DESERT, BWGBiomeTags.SANDY, BWGBiomeTags.SPARSE, BWGBiomeTags.HOT);
    public static final ResourceKey<Biome> BAOBAB_SAVANNA = createBiome("baobab_savanna", BWGOverworldBiomes::baobabSavanna, BWGBiomeTags.SAVANNA, BWGBiomeTags.SPARSE);
    public static final ResourceKey<Biome> BASALT_BARRERA = createBiome("basalt_barrera", BWGOverworldBiomes::basaltBarrera, BWGBiomeTags.BEACH, BWGBiomeTags.WASTELAND);
    public static final ResourceKey<Biome> BAYOU = createBiome("bayou", BWGOverworldBiomes::bayou, BWGBiomeTags.SWAMP);
    public static final ResourceKey<Biome> BLACK_FOREST = createBiome("black_forest", BWGOverworldBiomes::blackForest, BWGBiomeTags.FOREST, BWGBiomeTags.CONIFEROUS, BiomeTags.HAS_VILLAGE_TAIGA, BiomeTags.HAS_PILLAGER_OUTPOST);
    public static final ResourceKey<Biome> CANADIAN_SHIELD = createBiome("canadian_shield", BWGOverworldBiomes::canadianShield, BWGBiomeTags.FOREST, BWGBiomeTags.CONIFEROUS, BWGBiomeTags.SLOPE);
//    public static final ResourceKey<Biome> CANYON = createBiome("canyon", BWGOverworldBiomes::canyon, BWGBiomeTags.CANYON);
    public static final ResourceKey<Biome> CIKA_WOODS = createBiome("cika_woods", BWGOverworldBiomes::cikaWoods, BWGBiomeTags.FOREST, BWGBiomeTags.CONIFEROUS, BWGBiomeTags.StructureHasTags.HAS_VILLAGE_PUMPKIN_PATCH);
    public static final ResourceKey<Biome> COCONINO_MEADOW = createBiome("coconino_meadow", BWGOverworldBiomes::coconinoMeadow, BWGBiomeTags.PLAINS, BWGBiomeTags.FLORAL, BWGBiomeTags.GRASS_BLOCK_FLOWER_BONEMEAL);
    public static final ResourceKey<Biome> CONIFEROUS_FOREST = createBiome("coniferous_forest", (placedFeatureHolderGetter, carverHolderGetter) -> BWGOverworldBiomes.coniferousForest(placedFeatureHolderGetter, carverHolderGetter, false), BWGBiomeTags.FOREST, BWGBiomeTags.CONIFEROUS, BiomeTags.HAS_VILLAGE_TAIGA, BiomeTags.HAS_PILLAGER_OUTPOST);
    public static final ResourceKey<Biome> CRAG_GARDENS = createBiome("crag_gardens", BWGOverworldBiomes::cragGardens, BWGBiomeTags.JUNGLE, BWGBiomeTags.MOUNTAIN, BWGBiomeTags.DENSE);
    public static final ResourceKey<Biome> CRIMSON_TUNDRA = createBiome("crimson_tundra", BWGOverworldBiomes::crimsonTundra, BWGBiomeTags.PLAINS, BWGBiomeTags.COLD, BWGBiomeTags.GRASS_BLOCK_FLOWER_BONEMEAL);
    public static final ResourceKey<Biome> CYPRESS_SWAMPLANDS = createBiome("cypress_swamplands", BWGOverworldBiomes::cypressSwamplands, BWGBiomeTags.SWAMP);
    public static final ResourceKey<Biome> DACITE_RIDGES = createBiome("dacite_ridges", BWGOverworldBiomes::daciteRidges, BWGBiomeTags.FOREST, BWGBiomeTags.CONIFEROUS);
    public static final ResourceKey<Biome> DACITE_SHORE = createBiome("dacite_shore", BWGOverworldBiomes::daciteShore, BWGBiomeTags.BEACH, BWGBiomeTags.WASTELAND, BiomeTags.HAS_BURIED_TREASURE);
    public static final ResourceKey<Biome> DEAD_SEA = createBiome("dead_sea", BWGOverworldBiomes::deadSea, BWGBiomeTags.OCEAN, BWGBiomeTags.DEAD, BWGBiomeTags.WASTELAND, BWGBiomeTags.DRIPSTONE_ARCH);
    public static final ResourceKey<Biome> EBONY_WOODS = createBiome("ebony_woods", BWGOverworldBiomes::ebonyWoods, BWGBiomeTags.FOREST, BWGBiomeTags.DENSE);
    public static final ResourceKey<Biome> ENCHANTED_TANGLE = createBiome("enchanted_tangle", BWGOverworldBiomes::enchantedTangle, BWGBiomeTags.FOREST, BWGBiomeTags.MAGICAL, BWGBiomeTags.FLORAL, BWGBiomeTags.GRASS_BLOCK_FLOWER_BONEMEAL);

    public static final ResourceKey<Biome> ERODED_BOREALIS = createBiome("eroded_borealis", BWGOverworldBiomes::erodedBorealis, BWGBiomeTags.COLD, BWGBiomeTags.SNOWY,  BWGBiomeTags.FOREST, BiomeTags.HAS_IGLOO, BiomeTags.HAS_VILLAGE_SNOWY, BiomeTags.HAS_PILLAGER_OUTPOST); //TODO: Tags
    public static final ResourceKey<Biome> FIRECRACKER_CHAPARRAL = createBiome("firecracker_chaparral", BWGOverworldBiomes::firecrackerChaparral, BWGBiomeTags.PLAINS, BWGBiomeTags.SPARSE);
    public static final ResourceKey<Biome> FORGOTTEN_FOREST = createBiome("forgotten_forest", BWGOverworldBiomes::forgottenForest, BWGBiomeTags.FOREST, BWGBiomeTags.MAGICAL, BiomeTags.HAS_WOODLAND_MANSION, BWGBiomeTags.StructureHasTags.HAS_VILLAGE_FORGOTTEN);
    public static final ResourceKey<Biome> FRAGMENT_JUNGLE = createBiome("fragment_jungle", BWGOverworldBiomes::fragmentJungle, BWGBiomeTags.JUNGLE);
    public static final ResourceKey<Biome> FROSTED_CONIFEROUS_FOREST = createBiome("frosted_coniferous_forest", (placedFeatureHolderGetter, carverHolderGetter) -> BWGOverworldBiomes.coniferousForest(placedFeatureHolderGetter, carverHolderGetter, true), BWGBiomeTags.FOREST, BWGBiomeTags.CONIFEROUS, BWGBiomeTags.COLD);
    public static final ResourceKey<Biome> FROSTED_TAIGA = createBiome("frosted_taiga", BWGOverworldBiomes::frostedTaiga, BWGBiomeTags.TAIGA, BWGBiomeTags.CONIFEROUS, BiomeTags.HAS_IGLOO, BiomeTags.HAS_VILLAGE_SNOWY, BiomeTags.HAS_PILLAGER_OUTPOST);
    public static final ResourceKey<Biome> HOWLING_PEAKS = createBiome("howling_peaks", BWGOverworldBiomes::howlingPeaks, BWGBiomeTags.FOREST, BWGBiomeTags.MOUNTAIN, BWGBiomeTags.SNOWY, BWGBiomeTags.CONIFEROUS, BWGBiomeTags.PEAK);
    public static final ResourceKey<Biome> IRONWOOD_GOUR = createBiome("ironwood_gour", BWGOverworldBiomes::ironwoodGour, BWGBiomeTags.IRONWOOD_GOUR_PLATEAU, BWGBiomeTags.SAVANNA, BWGBiomeTags.SPARSE);
    public static final ResourceKey<Biome> JACARANDA_JUNGLE = createBiome("jacaranda_jungle", BWGOverworldBiomes::jacarandaJungle, BWGBiomeTags.JUNGLE);
    public static final ResourceKey<Biome> LUSH_STACKS = createBiome("lush_stacks", BWGOverworldBiomes::lushStacks, BWGBiomeTags.LUSH_ARCH, BWGBiomeTags.OCEAN, BWGBiomeTags.FLORAL);
    public static final ResourceKey<Biome> MAPLE_TAIGA = createBiome("maple_taiga", BWGOverworldBiomes::mapleTaiga, BWGBiomeTags.TAIGA, BWGBiomeTags.CONIFEROUS, BiomeTags.HAS_VILLAGE_TAIGA, BiomeTags.HAS_PILLAGER_OUTPOST);
    public static final ResourceKey<Biome> MOJAVE_DESERT = createBiome("mojave_desert", BWGOverworldBiomes::mojaveDesert, BWGBiomeTags.DESERT, BWGBiomeTags.SANDY, BiomeTags.HAS_DESERT_PYRAMID, BiomeTags.HAS_VILLAGE_DESERT, BWGBiomeTags.HOT);
    public static final ResourceKey<Biome> ORCHARD = createBiome("orchard", BWGOverworldBiomes::orchard, BWGBiomeTags.FOREST, BWGBiomeTags.FLORAL, BWGBiomeTags.DENSE, BiomeTags.HAS_VILLAGE_PLAINS, BiomeTags.HAS_PILLAGER_OUTPOST, BWGBiomeTags.GRASS_BLOCK_FLOWER_BONEMEAL);
    public static final ResourceKey<Biome> OVERGROWTH_WOODLANDS = createBiome("overgrowth_woodlands", BWGOverworldBiomes::overgrowthWoodlands, BWGBiomeTags.FOREST, BWGBiomeTags.DENSE, BiomeTags.HAS_VILLAGE_PLAINS, BiomeTags.HAS_PILLAGER_OUTPOST);

    public static final ResourceKey<Biome> PALE_BOG = createBiome("pale_bog", BWGOverworldBiomes::paleBog, BWGBiomeTags.SWAMP, BWGBiomeTags.StructureHasTags.HAS_BOG_TRIAL);
    public static final ResourceKey<Biome> PRAIRIE = createBiome("prairie", BWGOverworldBiomes::prairie, BWGBiomeTags.PLAINS, BWGBiomeTags.DENSE, BWGBiomeTags.StructureHasTags.HAS_PRAIRIE_HOUSE, BWGBiomeTags.TEMPERATE);
    public static final ResourceKey<Biome> PUMPKIN_VALLEY = createBiome("pumpkin_valley", BWGOverworldBiomes::pumpkinValley, BWGBiomeTags.PLAINS, BWGBiomeTags.DENSE, BWGBiomeTags.StructureHasTags.HAS_VILLAGE_PUMPKIN_PATCH, BWGBiomeTags.GRASS_BLOCK_FLOWER_BONEMEAL);
    public static final ResourceKey<Biome> RAINBOW_BEACH = createBiome("rainbow_beach", BWGOverworldBiomes::rainbowBeach, BWGBiomeTags.BEACH, BWGBiomeTags.SANDY, BiomeTags.HAS_BURIED_TREASURE);
    public static final ResourceKey<Biome> RED_ROCK_VALLEY = createBiome("red_rock_valley", BWGOverworldBiomes::redRockValley, BWGBiomeTags.BADLANDS, BWGBiomeTags.RED_ROCK_ARCH, BWGBiomeTags.StructureHasTags.HAS_VILLAGE_RED_ROCK, BWGBiomeTags.DRY, BWGBiomeTags.HOT);
    public static final ResourceKey<Biome> REDWOOD_THICKET = createBiome("redwood_thicket", BWGOverworldBiomes::redwoodThicket, BWGBiomeTags.FOREST, BWGBiomeTags.DENSE);
    public static final ResourceKey<Biome> ROSE_FIELDS = createBiome("rose_fields", BWGOverworldBiomes::roseFields, BWGBiomeTags.PLAINS, BWGBiomeTags.DENSE, BiomeTags.HAS_VILLAGE_TAIGA, BiomeTags.HAS_PILLAGER_OUTPOST);
    public static final ResourceKey<Biome> RUGGED_BADLANDS = createBiome("rugged_badlands", BWGOverworldBiomes::ruggedBadlands, BWGBiomeTags.BADLANDS, BWGBiomeTags.SANDY, BWGBiomeTags.SPARSE, BWGBiomeTags.SHARPENED_ROCKS, BiomeTags.HAS_DESERT_PYRAMID, BiomeTags.HAS_VILLAGE_DESERT, BWGBiomeTags.StructureHasTags.HAS_RUGGED_FOSSIL, BWGBiomeTags.DRY, BWGBiomeTags.HOT);
    public static final ResourceKey<Biome> SAKURA_GROVE = createBiome("sakura_grove", BWGOverworldBiomes::sakuraGrove, BWGBiomeTags.FOREST, BWGBiomeTags.FLORAL, BWGBiomeTags.DENSE, BWGBiomeTags.GRASS_BLOCK_FLOWER_BONEMEAL);
    public static final ResourceKey<Biome> SHATTERED_GLACIER = createBiome("shattered_glacier", BWGOverworldBiomes::shatteredGlacier, BWGBiomeTags.COLD, BWGBiomeTags.SLOPE, BWGBiomeTags.ICY);
    public static final ResourceKey<Biome> SIERRA_BADLANDS = createBiome("sierra_badlands", BWGOverworldBiomes::sierraBadlands, BWGBiomeTags.BADLANDS, BWGBiomeTags.HOT);
    public static final ResourceKey<Biome> SKYRIS_VALE = createBiome("skyrise_vale", BWGOverworldBiomes::skyrisVale, BWGBiomeTags.FOREST, BWGBiomeTags.MAGICAL, BWGBiomeTags.DENSE, BWGBiomeTags.StructureHasTags.HAS_VILLAGE_SKYRIS);
    public static final ResourceKey<Biome> TROPICAL_RAINFOREST = createBiome("tropical_rainforest", BWGOverworldBiomes::tropicalRainforest, BWGBiomeTags.JUNGLE, BiomeTags.HAS_BURIED_TREASURE);
//    public static final ResourceKey<Biome> TROPICAL_ISLAND = createBiome("tropical_island", BWGOverworldBiomes::tropicalIsland, BWGBiomeTags.JUNGLE);
    public static final ResourceKey<Biome> TEMPERATE_GROVE = createBiome("temperate_grove", BWGOverworldBiomes::temperateGrove, BWGBiomeTags.PLAINS, BWGBiomeTags.SPARSE, BiomeTags.HAS_VILLAGE_PLAINS, BiomeTags.HAS_PILLAGER_OUTPOST, BWGBiomeTags.TEMPERATE, BWGBiomeTags.GRASS_BLOCK_FLOWER_BONEMEAL);
    public static final ResourceKey<Biome> WEEPING_WITCH_FOREST = createBiome("weeping_witch_forest", BWGOverworldBiomes::weepingWitchForest, BWGBiomeTags.FOREST, BWGBiomeTags.MAGICAL, BWGBiomeTags.DENSE, BiomeTags.HAS_WOODLAND_MANSION, BWGBiomeTags.StructureHasTags.HAS_VILLAGE_SALEM);
    public static final ResourceKey<Biome> WHITE_MANGROVE_MARSHES = createBiome("white_mangrove_marshes", BWGOverworldBiomes::whiteMangroveMarshes, BWGBiomeTags.SWAMP);
    public static final ResourceKey<Biome> WINDSWEPT_DESERT = createBiome("windswept_desert", BWGOverworldBiomes::windsweptDesert, BWGBiomeTags.DESERT, BWGBiomeTags.SANDY, BWGBiomeTags.WINDSWEPT, BWGBiomeTags.HOT);
    public static final ResourceKey<Biome> ZELKOVA_FOREST = createBiome("zelkova_forest", BWGOverworldBiomes::zelkovaForest, BWGBiomeTags.FOREST, BWGBiomeTags.DENSE);
    
    @SafeVarargs
    private static ResourceKey<Biome> createBiome(String id, BiomeFactory biomeFactory, TagKey<Biome>... tags) {
        ResourceKey<Biome> biomeResourceKey = BiomesWeveGone.key(Registries.BIOME, id);
        BIOME_FACTORIES.put(biomeResourceKey, biomeFactory);

            for (TagKey<Biome> tag : tags)
                BIOMES_BY_TAG.put(tag, biomeResourceKey);

        return biomeResourceKey;
    }


    @FunctionalInterface
    public interface BiomeFactory {
        Biome generate(HolderGetter<PlacedFeature> placedFeatureHolderGetter, HolderGetter<ConfiguredWorldCarver<?>> worldCarverHolderGetter);
    }
}
