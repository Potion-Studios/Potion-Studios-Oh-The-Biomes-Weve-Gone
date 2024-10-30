package net.potionstudios.biomeswevegone.world.level.levelgen.biome.features;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.placed.BWGOverworldTreePlacedFeatures;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.placed.BWGOverworldVegationPlacedFeatures;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.placed.BWGPlacedFeatures;
import org.jetbrains.annotations.NotNull;

public class BWGOverworldDefaultFeatures {

    public static void addCloverPatches(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen,  BWGOverworldVegationPlacedFeatures.CLOVER_PATCH);
    }

    public static void addLeafPile(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen,  BWGOverworldVegationPlacedFeatures.LEAF_PILE);
    }

    public static void addBWGMushrooms(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen,  BWGOverworldVegationPlacedFeatures.MUSHROOMS);
    }

    public static void addSparseRedOakTrees(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldTreePlacedFeatures.SPARSE_RED_OAK_TREES);
    }

    public static void addSparseJacarandaTrees(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldTreePlacedFeatures.SPARSE_JACARANDA_TREES);
    }

    public static void addSparseRedOrangeSpruceTrees(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldTreePlacedFeatures.SPARSE_RED_SPRUCE_TREES);
        addVegetal(gen, BWGOverworldTreePlacedFeatures.SPARSE_ORANGE_SPRUCE_TREES);
    }

    public static void addSparseSpruceTrees(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldTreePlacedFeatures.SPARSE_SPRUCE_TREES);
    }

    public static void addRoseFieldSpruceTrees(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldTreePlacedFeatures.ROSE_FIELD_SPRUCE_TREES);
    }

    public static void addOakBushes(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldTreePlacedFeatures.OAK_BUSHES);
    }

    public static void addMeadowShrubs(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldTreePlacedFeatures.MEADOW_SHRUBS);
    }

    public static void addBeachGrass(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.PATCH_BEACH_GRASS);
    }

    public static void addCrocus(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.CROCUS);
    }

    public static void addFairySlipper(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.FAIRY_SLIPPER);
    }

    public static void addHugeMushrooms(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.HUGE_MUSHROOMS);
    }

    public static void addMossyStoneBoulder(BiomeGenerationSettings.Builder gen) {
        gen.addFeature(GenerationStep.Decoration.RAW_GENERATION, BWGPlacedFeatures.MOSSY_STONE_BOULDER);
    }

    public static void addRockyStoneBoulder(BiomeGenerationSettings.Builder gen) {
        gen.addFeature(GenerationStep.Decoration.RAW_GENERATION, BWGPlacedFeatures.ROCKY_STONE_BOULDER);
    }

    public static void addPatchBeachGrassNoise(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.PATCH_BEACH_GRASS_NOISE);
    }

    public static void addCloverFlowers(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.CLOVER_FLOWERS);
    }

    public static void addRose(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.ROSE);
    }

    public static void addBlueBerryBush(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.BLUE_BERRY_BUSH);
    }

    public static void addLushBlueBerryBush(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.BLUE_BERRY_BUSH_LUSH);
    }

    public static void addAnemones(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.ANEMONES);
    }

    public static void addTulips(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.TULIPS);
    }

    public static void addIris(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.IRIS);
    }

    public static void addWinterScilla(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.WINTER_SCILLA);
    }

    public static void addWinterCyclamen(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.WINTER_CYCLAMEN);
    }

    public static void addWinterSucculent(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.WINTER_SUCCULENT);
    }

    public static void addBlueRoseBush(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.BLUE_ROSE_BUSH);
    }

    public static void addSages(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.SAGES);
    }

    public static void addSparseAspenTreesShrubs(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldTreePlacedFeatures.ASPEN_TREES_SPARSE);
        addVegetal(gen, BWGOverworldTreePlacedFeatures.ASPEN_SHRUBS);
    }

    public static void addBWGTropicFlowers(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.JUNGLE_FLOWERS);
    }

    public static void addSparseOakTrees(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldTreePlacedFeatures.SPARSE_OAK_TREES);
    }

    public static void addPaloVerdeTrees(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldTreePlacedFeatures.PALO_VERDE_TREES);
    }

    public static void addHorseWeed(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.HORSEWEED);
    }

    public static void addCaliforniaPoppy(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.CALIFORNIA_POPPY);
    }

    public static void addJacarandaBushes(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.JACARANDA_BUSHES);
    }

    public static void addFlowerPatches(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.FLOWER_PATCHES);
    }

    public static void addLeatherFlowers(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.LEATHER_FLOWERS);
    }

    public static void addMudDisks(BiomeGenerationSettings.Builder biomeIn) {
        biomeIn.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BWGPlacedFeatures.DISK_MUD);
    }

    public static void addPaleBogFlowers(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.LEATHER_FLOWERS);
    }

    public static void addBWGSwampVegetation(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.SWAMP_WATER_VEGETATION);
    }

    public static void addWhitePuffball(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.WHITE_PUFFBALL);
    }

    public static void addFirecrackerBush(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.FIRECRACKER_BUSH);
    }

    public static void addShrub(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.SHRUB);
    }

    public static void addOrangeTerracottaBoulder(BiomeGenerationSettings.Builder gen) {
        gen.addFeature(GenerationStep.Decoration.RAW_GENERATION, BWGPlacedFeatures.ORANGE_TERRACOTTA_BOULDER);
    }

    public static void addSwampDelta(BiomeGenerationSettings.Builder gen) {
        gen.addFeature(GenerationStep.Decoration.RAW_GENERATION, BWGPlacedFeatures.SWAMP_GRASS_BLOCK_DELTA);
    }

    public static void addCattails(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.CATTAILS);
    }

    public static void addFluorscentCattails(BiomeGenerationSettings.Builder gen) {
        addVegetal(gen, BWGOverworldVegationPlacedFeatures.FLUORESCENT_CATTAILS);
    }

    private static void addVegetal(BiomeGenerationSettings.@NotNull Builder builder, ResourceKey<PlacedFeature> feature) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,  feature);
    }
}
