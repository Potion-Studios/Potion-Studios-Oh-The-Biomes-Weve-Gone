package net.potionstudios.biomeswevegone.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.potionstudios.biomeswevegone.BiomesWeveGone;

/**
 * Holds Custom Block Tags for Biomes We've Gone
 * @see net.minecraft.tags.BlockTags
 * @author Joseph T. McQuigg
 */
public final class BWGBlockTags {
    public static final TagKey<Block> PALO_VERDE_LOGS = create("palo_verde_logs");
    public static final TagKey<Block> BLACK_ICE = create("black_ice");
    public static final TagKey<Block> BOREALIS_ICE = create("borealis_ice");
    public static final TagKey<Block> SNOWY_PLANT_PLACEABLE = create("snowy_plant_placeable");
    public static final TagKey<Block> BWG_MUSHROOM_PLACEABLE = create("bwg_mushroom_placeable");
    public static final TagKey<Block> HYDRANGEA_BUSH_PLACEABLE = create("hydrangea_bush_placeable");
    public static final TagKey<Block> TALL_ALLIUMS = create("flowers/alliums/tall");
    public static final TagKey<Block> SHORT_ALLIUMS = create("flowers/alliums/short");
    public static final TagKey<Block> ALLIUM_FLOWER_BUSHES = create("flowers/alliums/flower_bushes");
    public static final TagKey<Block> ALLIUMS = create("flowers/alliums");
    public static final TagKey<Block> ROSES = create("flowers/roses");
    public static final TagKey<Block> TULIPS = create("flowers/tulips");
    public static final TagKey<Block> AMARANTH = create("flowers/amaranth");
    public static final TagKey<Block> SAGES = create("flowers/sages");
    public static final TagKey<Block> DAFFODILS = create("flowers/daffodils");

    public static final TagKey<Block> RED_ROCK_BRICKS = create("red_rock_bricks");

    public static final TagKey<Block> GLOWCANE = create("glowcane");
    public static final TagKey<Block> GLOW_BOTTLE = create("glow_bottle");

    /** Saplings **/
    public static final TagKey<Block> OAK_SAPLINGS = create("saplings/oak");
    public static final TagKey<Block> SPRUCE_SAPLINGS = create("saplings/spruce");
    public static final TagKey<Block> BIRCH_SAPLINGS = create("saplings/birch");

    /** Storage Blocks **/
    public static final TagKey<Block> STORAGE_BLOCKS_ALLIUM = createCommon("storage_blocks/allium");
    public static final TagKey<Block> STORAGE_BLOCKS_PINK_ALLIUM = createCommon("storage_blocks/pink_allium");
    public static final TagKey<Block> STORAGE_BLOCKS_WHITE_ALLIUM = createCommon("storage_blocks/white_allium");
    public static final TagKey<Block> STORAGE_BLOCKS_ROSE = createCommon("storage_blocks/rose");

    private static TagKey<Block> create(String name) {
        return TagKey.create(Registries.BLOCK, BiomesWeveGone.id(name));
    }

    private static TagKey<Block> createCommon(String name) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", name));
    }
}
