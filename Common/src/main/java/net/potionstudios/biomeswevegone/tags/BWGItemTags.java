package net.potionstudios.biomeswevegone.tags;

import net.minecraft.core.registries.Registries;
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

    /** Saplings **/
    public static final TagKey<Item> OAK_SAPLINGS = create("saplings/oak");
    public static final TagKey<Item> SPRUCE_SAPLINGS = create("saplings/spruce");
    public static final TagKey<Item> BIRCH_SAPLINGS = create("saplings/birch");

    public static final TagKey<Item> MUSHROOMS = create("mushrooms");

    public static final TagKey<Item> CRAFTING_TABLES = create("crafting_tables");

    /** Dye Making **/
    public static final TagKey<Item> MAKES_BLACK_DYE = create("dye/makes_black");
    public static final TagKey<Item> MAKES_BLUE_DYE = create("dye/makes_blue");
    public static final TagKey<Item> MAKES_CYAN_DYE = create("dye/makes_cyan");
    public static final TagKey<Item> MAKES_GREEN_DYE = create("dye/makes_green");
    public static final TagKey<Item> MAKES_LIGHT_BLUE_DYE = create("dye/makes_light_blue");
    public static final TagKey<Item> MAKES_LIME_DYE = create("dye/makes_lime");
    public static final TagKey<Item> MAKES_MAGENTA_DYE = create("dye/makes_magenta");
    public static final TagKey<Item> MAKES_ORANGE_DYE = create("dye/makes_orange");
    public static final TagKey<Item> MAKES_PINK_DYE = create("dye/makes_pink");
    public static final TagKey<Item> MAKES_PURPLE_DYE = create("dye/makes_purple");
    public static final TagKey<Item> MAKES_RED_DYE = create("dye/makes_red");
    public static final TagKey<Item> MAKES_WHITE_DYE = create("dye/makes_white");
    public static final TagKey<Item> MAKES_YELLOW_DYE = create("dye/makes_yellow");

    public static final TagKey<Item> MAKES_2_BLUE_DYE = create("dye/makes_2_blue");
    public static final TagKey<Item> MAKES_2_CYAN_DYE = create("dye/makes_2_cyan");
    public static final TagKey<Item> MAKES_2_PINK_DYE = create("dye/makes_2_pink");
    public static final TagKey<Item> MAKES_2_PURPLE_DYE = create("dye/makes_2_purple");
    public static final TagKey<Item> MAKES_2_WHITE_DYE = create("dye/makes_2_white");

    private static TagKey<Item> create(String name) {
        return TagKey.create(Registries.ITEM, BiomesWeveGone.id(name));
    }
}
