package net.potionstudios.biomeswevegone.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.potionstudios.biomeswevegone.BiomesWeveGone;

/**
 * Holds Custom Biome Tags for Biomes We've Gone
 * @see BiomeTags
 * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags
 * @see net.neoforged.neoforge.common.Tags.Biomes
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
    public static final TagKey<Biome> CANYON = create("canyon");
    public static final TagKey<Biome> HAS_TERRAIN_ADDITIONS = create("has_terrain_additions");

    /** Correlates to
     * @see BiomeTags#IS_OVERWORLD
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#IS_OVERWORLD,
     **/
    public static final TagKey<Biome> OVERWORLD = create("overworld");

    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#IS_HOT_OVERWORLD
     **/
    public static final TagKey<Biome> HOT = create("climate/hot");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#IS_TEMPERATE_OVERWORLD*
     **/
    public static final TagKey<Biome> TEMPERATE = create("climate/temperate");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#IS_COLD_OVERWORLD
     **/
    public static final TagKey<Biome> COLD = create("climate/cold");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#IS_WET_OVERWORLD
     */
    public static final TagKey<Biome> WET = create("climate/wet");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#IS_DRY_OVERWORLD
     */
    public static final TagKey<Biome> DRY = create("climate/dry");

    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#IS_VEGETATION_SPARSE_OVERWORLD
     **/
    public static final TagKey<Biome> SPARSE = create("density/sparse");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#IS_VEGETATION_DENSE_OVERWORLD
     */
    public static final TagKey<Biome> DENSE = create("density/dense");

    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#IS_PLAINS
     */
    public static final TagKey<Biome> PLAINS = create("plains");
    /** Correlates to
     * @see BiomeTags#IS_FOREST
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#IS_FOREST
     */
    public static final TagKey<Biome> FOREST = create("forest");
    /** Correlates to
     * @see BiomeTags#IS_TAIGA
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#IS_TAIGA
     */
    public static final TagKey<Biome> TAIGA = create("taiga");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#IS_DESERT
     */
    public static final TagKey<Biome> DESERT = create("desert");
    /** Correlates to
     * @see BiomeTags#IS_SAVANNA
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#IS_SAVANNA
     */
    public static final TagKey<Biome> SAVANNA = create("savanna");
    /** Correlates to
     * @see BiomeTags#IS_JUNGLE
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#IS_JUNGLE
     */
    public static final TagKey<Biome> JUNGLE = create("jungle");
    /** Correlates to
     * @see BiomeTags#IS_BEACH
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#IS_BEACH
     */
    public static final TagKey<Biome> BEACH = create("beach");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#IS_SWAMP
     */
    public static final TagKey<Biome> SWAMP = create("swamp");

    /**
     * Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#IS_MOUNTAIN_SLOPE
     */
    public static final TagKey<Biome> SLOPE = create("slope");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#IS_MOUNTAIN_PEAK
     */
    public static final TagKey<Biome> PEAK = create("peak");


    /** Correlates to
     * @see BiomeTags#IS_MOUNTAIN
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#IS_MOUNTAIN
     */
    public static final TagKey<Biome> MOUNTAIN = create("mountain");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#IS_SNOWY
     */
    public static final TagKey<Biome> SNOWY = create("snowy");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#IS_ICY
     */
    public static final TagKey<Biome> ICY = create("icy");
    /** Correlates to
     * @see BiomeTags#IS_BADLANDS
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#IS_BADLANDS
     */
    public static final TagKey<Biome> BADLANDS = create("badlands");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#IS_SANDY
     */
    public static final TagKey<Biome> SANDY = create("sandy");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#IS_FLORAL
     */
    public static final TagKey<Biome> FLORAL = create("floral");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#TREE_CONIFEROUS
     * @see net.minecraftforge.common.Tags.Biomes#IS_CONIFEROUS
     */
    public static final TagKey<Biome> CONIFEROUS = create("coniferous");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#DEAD
     * @see net.minecraftforge.common.Tags.Biomes#IS_DEAD
     */
    public static final TagKey<Biome> DEAD = create("dead");
    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#IS_WASTELAND
     * @see net.minecraftforge.common.Tags.Biomes#IS_WASTELAND
     */
    public static final TagKey<Biome> WASTELAND = create("wasteland");

    /** Correlates to
     * @see net.minecraftforge.common.Tags.Biomes#IS_MAGICAL
     */
    public static final TagKey<Biome> MAGICAL = create("magical");

    /** Correlates to
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#IS_WINDSWEPT
     */
    public static final TagKey<Biome> WINDSWEPT = create("windswept");

    /**
     * Correlates to
     * @see BiomeTags#IS_OCEAN
     * @see net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags#IS_OCEAN
     */
    public static final TagKey<Biome> OCEAN = create("ocean");

    public static class StructureHasTags {
        public static final TagKey<Biome> HAS_PRAIRIE_HOUSE = create("has_structure/prairie_house");
        public static final TagKey<Biome> HAS_RUGGED_FOSSIL = create("has_structure/rugged_fossil");
        public static final TagKey<Biome> HAS_ASPEN_MANOR = create("has_structure/aspen_manor");
        public static final TagKey<Biome> HAS_BOG_TRIAL = create("has_structure/bog_trial");
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
