package net.potionstudios.biomeswevegone.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.potionstudios.biomeswevegone.BiomesWeveGone;

/**
 * Holds Custom Biome Tags for Biomes We've Gone
 * @see BiomeTags
 * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags
 * @see net.minecraftforge.common.Tags.Biomes
 * @author Joseph T. McQuigg
 */
@SuppressWarnings("JavadocReference")
public class BWGBiomeTags {

    public static final TagKey<Biome> SHARPENED_ROCKS = create("sharpened_rocks");
    public static final TagKey<Biome> IRONWOOD_GOUR_PLATEAU = create("ironwood_gour_plateau");
    public static final TagKey<Biome> LARGE_COLD_LAKE = create("large_cold_lake");
    public static final TagKey<Biome> LUSH_ARCH = create("lush_arch");
    public static final TagKey<Biome> RED_ROCK_ARCH = create("red_rock_arch");
    public static final TagKey<Biome> DRIPSTONE_ARCH = create("dripstone_arch");
    public static final TagKey<Biome> HAS_TERRAIN_ADDITIONS = create("has_terrain_additions");

    /** Correlates to
     * @see BiomeTags#IS_OVERWORLD
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#IN_OVERWORLD,
     **/
    public static final TagKey<Biome> OVERWORLD = create("overworld");

    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#CLIMATE_HOT
     * @see net.minecraftforge.common.Tags.Biomes#IS_HOT_OVERWORLD
     **/
    public static final TagKey<Biome> HOT = create("climate/hot");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#CLIMATE_TEMPERATE*/
    public static final TagKey<Biome> TEMPERATE = create("climate/temperate");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#CLIMATE_COLD
     * @see net.minecraftforge.common.Tags.Biomes#IS_COLD_OVERWORLD
     **/
    public static final TagKey<Biome> COLD = create("climate/cold");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#CLIMATE_WET
     * @see net.minecraftforge.common.Tags.Biomes#IS_WET_OVERWORLD
     */
    public static final TagKey<Biome> WET = create("climate/wet");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#CLIMATE_DRY
     * @see net.minecraftforge.common.Tags.Biomes#IS_DRY_OVERWORLD
     */
    public static final TagKey<Biome> DRY = create("climate/dry");

    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#VEGETATION_SPARSE
     * @see net.minecraftforge.common.Tags.Biomes#IS_SPARSE_OVERWORLD
     **/
    public static final TagKey<Biome> SPARSE = create("density/sparse");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#VEGETATION_DENSE
     * @see net.minecraftforge.common.Tags.Biomes#IS_DENSE_OVERWORLD
     */
    public static final TagKey<Biome> DENSE = create("density/dense");

    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#PLAINS
     * @see net.minecraftforge.common.Tags.Biomes#IS_PLAINS
     */
    public static final TagKey<Biome> PLAINS = create("plains");
    /** Correlates to
     * @see BiomeTags#IS_FOREST
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#FOREST
     */
    public static final TagKey<Biome> FOREST = create("forest");
    /** Correlates to
     * @see BiomeTags#IS_TAIGA
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#TAIGA
     */
    public static final TagKey<Biome> TAIGA = create("taiga");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#DESERT
     * @see net.minecraftforge.common.Tags.Biomes#IS_DESERT
     */
    public static final TagKey<Biome> DESERT = create("desert");
    /** Correlates to
     * @see BiomeTags#IS_SAVANNA
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#SAVANNA
     */
    public static final TagKey<Biome> SAVANNA = create("savanna");
    /** Correlates to
     * @see BiomeTags#IS_JUNGLE
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#JUNGLE
     */
    public static final TagKey<Biome> JUNGLE = create("jungle");
    /** Correlates to
     * @see BiomeTags#IS_BEACH
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#BEACH
     */
    public static final TagKey<Biome> BEACH = create("beach");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#SWAMP
     * @see net.minecraftforge.common.Tags.Biomes#IS_SWAMP
     */
    public static final TagKey<Biome> SWAMP = create("swamp");

    /**
     * Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#MOUNTAIN_SLOPE
     * @see net.minecraftforge.common.Tags.Biomes#IS_SLOPE
     */
    public static final TagKey<Biome> SLOPE = create("slope");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#MOUNTAIN_PEAK
     * @see net.minecraftforge.common.Tags.Biomes#IS_PEAK
     */
    public static final TagKey<Biome> PEAK = create("peak");


    /** Correlates to
     * @see BiomeTags#IS_MOUNTAIN
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#MOUNTAIN
     * @see net.minecraftforge.common.Tags.Biomes#IS_MOUNTAIN
     */
    public static final TagKey<Biome> MOUNTAIN = create("mountain");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#SNOWY
     * @see net.minecraftforge.common.Tags.Biomes#IS_SNOWY
     */
    public static final TagKey<Biome> SNOWY = create("snowy");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#ICY
     */
    public static final TagKey<Biome> ICY = create("icy");
    /** Correlates to
     * @see BiomeTags#IS_BADLANDS
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#BADLANDS
     */
    public static final TagKey<Biome> BADLANDS = create("badlands");
    /** Correlates to
     * @see net.minecraftforge.common.Tags.Biomes#IS_SANDY
     */
    public static final TagKey<Biome> SANDY = create("sandy");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#FLORAL
     */
    public static final TagKey<Biome> FLORAL = create("floral");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#TREE_CONIFEROUS
     * @see net.minecraftforge.common.Tags.Biomes#IS_CONIFEROUS
     */
    public static final TagKey<Biome> CONIFEROUS = create("coniferous");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#DEAD
     * @see net.minecraftforge.common.Tags.Biomes#IS_DEAD
     */
    public static final TagKey<Biome> DEAD = create("dead");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#WASTELAND
     * @see net.minecraftforge.common.Tags.Biomes#IS_WASTELAND
     */
    public static final TagKey<Biome> WASTELAND = create("wasteland");

    /** Correlates to
     * @see net.minecraftforge.common.Tags.Biomes#IS_MAGICAL
     */
    public static final TagKey<Biome> MAGICAL = create("magical");

    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#WINDSWEPT
     */
    public static final TagKey<Biome> WINDSWEPT = create("windswept");

    /**
     * Correlates to
     * @see BiomeTags#IS_OCEAN
     * @see net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags#OCEAN
     */
    public static final TagKey<Biome> OCEAN = create("ocean");

    public static class StructureHasTags {
        public static final TagKey<Biome> HAS_PRAIRIE_HOUSE = create("has_structure/prairie_house");
        public static final TagKey<Biome> HAS_RUGGED_FOSSIL = create("has_structure/rugged_fossil");
        public static final TagKey<Biome> HAS_ASPEN_MANOR = create("has_structure/aspen_manor");
        public static final TagKey<Biome> HAS_VILLAGE_FORGOTTEN = create("has_structure/village_forgotten");
        public static final TagKey<Biome> HAS_VILLAGE_SKYRIS = create("has_structure/village_skyris");
        public static final TagKey<Biome> HAS_VILLAGE_SALEM = create("has_structure/village_salem");
        public static final TagKey<Biome> HAS_VILLAGE_RED_ROCK = create("has_structure/village_red_rock");
        public static final TagKey<Biome> HAS_VILLAGE_PUMPKIN_PATCH = create("has_structure/village_pumpkin_patch");
    }

    private static TagKey<Biome> create(String name) {
        return TagKey.create(Registries.BIOME, BiomesWeveGone.id(name));
    }
}
