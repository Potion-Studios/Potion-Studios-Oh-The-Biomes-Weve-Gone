package net.potionstudios.biomeswevegone.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.potionstudios.biomeswevegone.BiomesWeveGone;

/**
 * Holds Custom Item Tags for Biomes We've Gone
 * @see net.minecraft.tags.ItemTags
 * @author Joseph T. McQuigg
 */
public final class BWGItemTags {
    public static final TagKey<Item> SHEARS = create("shears");

    public static final TagKey<Item> BLACK_ICE = create("black_ice");
    public static final TagKey<Item> BOREALIS_ICE = create("borealis_ice");
    public static final TagKey<Item> PALO_VERDE_LOGS = create("palo_verde_logs");

    public static final TagKey<Item> TALL_ALLIUMS = create("flowers/alliums/tall");
    public static final TagKey<Item> SHORT_ALLIUMS = create("flowers/alliums/short");
    public static final TagKey<Item> ALLIUM_FLOWER_BUSHES = create("flowers/alliums/flower_bushes");
    public static final TagKey<Item> ALLIUMS = create("flowers/alliums");
    public static final TagKey<Item> ROSES = create("flowers/roses");
    public static final TagKey<Item> TULIPS = create("flowers/tulips");
    public static final TagKey<Item> AMARANTH = create("flowers/amaranth");
    public static final TagKey<Item> SAGES = create("flowers/sages");
    public static final TagKey<Item> DAFFODILS = create("flowers/daffodils");

    public static final TagKey<Item> RED_ROCK_BRICKS = create("red_rock_bricks");

    public static final TagKey<Item> GLOWCANE_POWDER = create("glowcane_powder");
    public static final TagKey<Item> GLOWCANE_SHOOT = create("glowcane_shoot");
    public static final TagKey<Item> GLOW_BOTTLE = create("glow_bottle");

    /** Saplings **/
    public static final TagKey<Item> OAK_SAPLINGS = create("saplings/oak");
    public static final TagKey<Item> SPRUCE_SAPLINGS = create("saplings/spruce");
    public static final TagKey<Item> BIRCH_SAPLINGS = create("saplings/birch");

    public static final TagKey<Item> STORAGE_BLOCKS_ALLIUM = createCommon("storage_blocks/allium");
    public static final TagKey<Item> STORAGE_BLOCKS_PINK_ALLIUM = createCommon("storage_blocks/pink_allium");
    public static final TagKey<Item> STORAGE_BLOCKS_WHITE_ALLIUM = createCommon("storage_blocks/white_allium");
    public static final TagKey<Item> STORAGE_BLOCKS_ROSE = createCommon("storage_blocks/rose");

    private static TagKey<Item> create(String name) {
        return TagKey.create(Registries.ITEM, BiomesWeveGone.id(name));
    }

    private static TagKey<Item> createCommon(String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", name));
    }
}
