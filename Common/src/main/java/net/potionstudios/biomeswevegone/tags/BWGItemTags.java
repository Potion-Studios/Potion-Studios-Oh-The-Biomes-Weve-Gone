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
public class BWGItemTags {
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
    public static final TagKey<Item> GLOWCANE_POWDER = create("glowcane_powder");
    public static final TagKey<Item> GLOWCANE_SHOOT = create("glowcane_shoot");

    public static final TagKey<Item> STORAGE_BLOCKS_ALLIUM = createCommon("storage_blocks/allium");
    public static final TagKey<Item> STORAGE_BLOCKS_PINK_ALLIUM = createCommon("storage_blocks/pink_allium");
    public static final TagKey<Item> STORAGE_BLOCKS_WHITE_ALLIUM = createCommon("storage_blocks/white_allium");
    public static final TagKey<Item> STORAGE_BLOCKS_ROSE = createCommon("storage_blocks/rose");
    
    public static final TagKey<Item> MAKES_BLACK_DYE = create("dye/makes_black_dye");
    public static final TagKey<Item> MAKES_BLUE_DYE = create("dye/makes_blue_dye");
    public static final TagKey<Item> MAKES_BROWN_DYE = create("dye/makes_brown_dye");
    public static final TagKey<Item> MAKES_CYAN_DYE = create("dye/makes_cyan_dye");
    public static final TagKey<Item> MAKES_GRAY_DYE = create("dye/makes_gray_dye");
    public static final TagKey<Item> MAKES_GREEN_DYE = create("dye/makes_green_dye");
    public static final TagKey<Item> MAKES_LIGHT_BLUE_DYE = create("dye/makes_light_blue_dye");
    public static final TagKey<Item> MAKES_LIGHT_GRAY_DYE = create("dye/makes_light_gray_dye");
    public static final TagKey<Item> MAKES_LIME_DYE = create("dye/makes_lime_dye");
    public static final TagKey<Item> MAKES_MAGENTA_DYE = create("dye/makes_magenta_dye");
    public static final TagKey<Item> MAKES_ORANGE_DYE = create("dye/makes_orange_dye");
    public static final TagKey<Item> MAKES_PINK_DYE = create("dye/makes_pink_dye");
    public static final TagKey<Item> MAKES_PURPLE_DYE = create("dye/makes_purple_dye");
    public static final TagKey<Item> MAKES_RED_DYE = create("dye/makes_red_dye");
    public static final TagKey<Item> MAKES_WHITE_DYE = create("dye/makes_white_dye");
    public static final TagKey<Item> MAKES_YELLOW_DYE = create("dye/makes_yellow_dye");


    private static TagKey<Item> create(String name) {
        return TagKey.create(Registries.ITEM, BiomesWeveGone.id(name));
    }

    private static TagKey<Item> createCommon(String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", name));
    }
}
