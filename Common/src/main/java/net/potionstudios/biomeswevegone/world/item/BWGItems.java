package net.potionstudios.biomeswevegone.world.item;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.PlatformHandler;
import net.potionstudios.biomeswevegone.world.entity.BWGEntities;
import net.potionstudios.biomeswevegone.world.item.custom.CampfireExplodingBlockItem;
import net.potionstudios.biomeswevegone.world.item.custom.PowderItem;
import net.potionstudios.biomeswevegone.world.item.jukebox.BWGJukeBoxSongs;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.block.plants.vegetation.cattail.ColorProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.Supplier;

/**
 * The items for Oh The Biomes We've Gone.
 * This class is used for registering items.
 * @see Registries#ITEM
 * @author Joseph T. McQuigg
 */
public class BWGItems {

    public static final ArrayList<Supplier<? extends Item>> ITEMS = new ArrayList<>();
    public static final ArrayList<Supplier<? extends Item>> NO_LANG_ITEMS = new ArrayList<>();
    public static final ArrayList<Supplier<? extends Item>> SIMPLE_ITEMS = new ArrayList<>();

    public static final Supplier<Item> BWG_LOGO = register("bwg_logo", () -> new Item(new Item.Properties()));

    public static final Supplier<SpawnEggItem> MAN_O_WAR_SPAWN_EGG = registerItem("man_o_war_spawn_egg", () -> new SpawnEggItem(BWGEntities.MAN_O_WAR.get(), new Item.Properties()));
    public static final Supplier<SpawnEggItem> PUMPKIN_WARDEN_SPAWN_EGG = registerItem("pumpkin_warden_spawn_egg", () -> new SpawnEggItem(BWGEntities.PUMPKIN_WARDEN.get(), new Item.Properties()));
    public static final Supplier<SpawnEggItem> ODDION_SPAWN_EGG = registerItem("oddion_spawn_egg", () -> new SpawnEggItem(BWGEntities.ODDION.get(), new Item.Properties()));

    public static final Supplier<MobBucketItem> MAN_O_WAR_BUCKET = registerMobBucket("man_o_war_bucket", BWGEntities.MAN_O_WAR::get, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH);

    public static final Supplier<Item> CATTAIL_SPROUT = registerItemNoLang("cattail_sprout", () -> new CampfireExplodingBlockItem(BWGBlocks.CATTAIL_SPROUT, new Item.Properties()));
    public static final Supplier<Item> FLUORESCENT_CATTAIL_SPROUT = registerItemNoLang("fluorescent_cattail_sprout", () -> new CampfireExplodingBlockItem(BWGBlocks.FLUORESCENT_CATTAIL_SPROUT, new Item.Properties()));

    public static final Supplier<Item> BLUE_GLOWCANE_SHOOT = registerSimpleItem("blue_glowcane_shoot", () -> new ItemNameBlockItem(BWGBlocks.BLUE_GLOWCANE.get(), new Item.Properties()));
    public static final Supplier<Item> GREEN_GLOWCANE_SHOOT = registerSimpleItem("green_glowcane_shoot", () -> new ItemNameBlockItem(BWGBlocks.GREEN_GLOWCANE.get(), new Item.Properties()));
    public static final Supplier<Item> RED_GLOWCANE_SHOOT = registerSimpleItem("red_glowcane_shoot", () -> new ItemNameBlockItem(BWGBlocks.RED_GLOWCANE.get(), new Item.Properties()));
    public static final Supplier<Item> YELLOW_GLOWCANE_SHOOT = registerSimpleItem("yellow_glowcane_shoot", () -> new ItemNameBlockItem(BWGBlocks.YELLOW_GLOWCANE.get(), new Item.Properties()));

    public static final Supplier<Item> BLUE_GLOWCANE_POWDER = registerSimpleItem("blue_glowcane_powder", () -> new PowderItem(ColorProperty.BLUE));
    public static final Supplier<Item> GREEN_GLOWCANE_POWDER = registerSimpleItem("green_glowcane_powder", () -> new PowderItem(ColorProperty.GREEN));
    public static final Supplier<Item> RED_GLOWCANE_POWDER = registerSimpleItem("red_glowcane_powder", () -> new PowderItem(ColorProperty.RED));
    public static final Supplier<Item> YELLOW_GLOWCANE_POWDER = registerSimpleItem("yellow_glowcane_powder", () -> new PowderItem(ColorProperty.YELLOW));

    public static final Supplier<Item> PALE_PUMPKIN_SEEDS = registerSimpleItem("pale_pumpkin_seeds", () -> new ItemNameBlockItem(BWGBlocks.PALE_PUMPKIN_STEM.get(), new Item.Properties()));

    public static final Supplier<Item> BAOBAB_FRUIT = registerSimpleItem("baobab_fruit", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.35f).build())));
    public static final Supplier<Item> SOUL_FRUIT = registerSimpleItem("soul_fruit", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.35f).saturationModifier(new MobEffectInstance(MobEffects.BLINDNESS, 40), 1).build())));
    public static final Supplier<Item> YUCCA_FRUIT = registerSimpleItem("yucca_fruit", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.35f).build())));
    public static final Supplier<Item> COOKED_YUCCA_FRUIT = registerSimpleItem("cooked_yucca_fruit", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationModifier(0.4f).build())));
    public static final Supplier<Item> GREEN_APPLE = registerSimpleItem("green_apple", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.35f).build())));
    public static final Supplier<Item> GREEN_APPLE_PIE = registerSimpleItem("green_apple_pie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationModifier(0.4f).effect(new MobEffectInstance(MobEffects.DIG_SPEED, 200, 0), 1.0F).build())));

    public static final Supplier<Item> BLUEBERRIES = registerSimpleItem("blueberries", () -> new ItemNameBlockItem(BWGBlocks.BLUEBERRY_BUSH.get() , new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).build())));
    public static final Supplier<Item> BLUEBERRY_PIE = registerSimpleItem("blueberry_pie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationModifier(0.3f).effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 0), 1.0F).build())));

    public static final Supplier<Item> ODDION_BULB = registerSimpleItem("oddion_bulb", () -> new ItemNameBlockItem(BWGBlocks.ODDION_CROP.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.35f).build())));
    public static final Supplier<Item> COOKED_ODDION_BULB = registerSimpleItem("cooked_oddion_bulb", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationModifier(0.5f).build())));
    public static final Supplier<Item> ALLIUM_ODDION_SOUP = registerSimpleItem("allium_oddion_soup", () -> new Item((new Item.Properties()).stacksTo(1).usingConvertsTo(Items.BOWL).food((new FoodProperties.Builder()).nutrition(8).saturationModifier(1f).build())));
    public static final Supplier<Item> BLOOMING_ODDION = registerSimpleItem("blooming_oddion", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(9).saturationModifier(1.2f).build())));

    public static final Supplier<Item> WHITE_PUFFBALL_SPORES = registerSimpleItem("white_puffball_spores", () -> new ItemNameBlockItem(BWGBlocks.WHITE_PUFFBALL.getBlock(), new Item.Properties()));
    public static final Supplier<Item> WHITE_PUFFBALL_CAP = registerSimpleItem("white_puffball_cap", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationModifier(0.2f).build())));
    public static final Supplier<Item> COOKED_WHITE_PUFFBALL_CAP = registerSimpleItem("cooked_white_puffball_cap", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.6f).build())));
    public static final Supplier<Item> WHITE_PUFFBALL_STEW = registerSimpleItem("white_puffball_stew", () -> new Item((new Item.Properties()).stacksTo(1).usingConvertsTo(Items.BOWL).food((new FoodProperties.Builder()).nutrition(9).saturationModifier(1F).build())));

    public static final Supplier<Item> ALOE_VERA_JUICE = registerSimpleItem("aloe_vera_juice", () -> new HoneyBottleItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).food(new FoodProperties.Builder().nutrition(3).saturationModifier(1.0F).build())));

    public static final Supplier<PlaceOnWaterBlockItem> TINY_LILY_PADS = registerItemNoLang("tiny_lily_pads", () -> new PlaceOnWaterBlockItem(BWGBlocks.TINY_LILY_PADS.get(), new Item.Properties()));
    public static final Supplier<PlaceOnWaterBlockItem> FLOWERING_TINY_LILY_PADS = registerItemNoLang("flowering_tiny_lily_pads", () -> new PlaceOnWaterBlockItem(BWGBlocks.FLOWERING_TINY_LILY_PADS.get(), new Item.Properties()));
    public static final Supplier<PlaceOnWaterBlockItem> WATER_SILK = registerItemNoLang("water_silk", () -> new PlaceOnWaterBlockItem(BWGBlocks.WATER_SILK.get(), new Item.Properties()));

    public static final Supplier<Item> MUSIC_DISC_PIXIE_CLUB = registerItemNoLang("music_disc_pixie_club", () -> new Item((new Item.Properties()).stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(BWGJukeBoxSongs.PIXIE_CLUB)));

    private static Supplier<MobBucketItem> registerMobBucket(String id, Supplier<EntityType<? extends Mob>> entity, Supplier<Fluid> fluid, Supplier<SoundEvent> sound) {
        Supplier<MobBucketItem> supplier = PlatformHandler.PLATFORM_HANDLER.createMobBucket(entity, fluid, sound);
        supplier = registerItem(id, supplier);
        SIMPLE_ITEMS.add(supplier);
        return supplier;
    }

    public static <I extends Item> Supplier<I> registerSimpleItem(String id, Supplier<I> item) {
        Supplier<I> supplier = registerItem(id, item);
        SIMPLE_ITEMS.add(supplier);
        return supplier;
    }

    public static <I extends Item> Supplier<I> registerItem(String id, Supplier<I> item) {
        Supplier<I> supplier = register(id, item);
        ITEMS.add(supplier);
        return supplier;
    }

    public static <I extends Item> Supplier<I> registerItemNoLang(String id, Supplier<I> item) {
        Supplier<I> supplier = register(id, item);
        NO_LANG_ITEMS.add(supplier);
        return supplier;
    }

    public static <I extends Item> Supplier<I> register(String id, Supplier<I> item) {
        return PlatformHandler.PLATFORM_HANDLER.register(BuiltInRegistries.ITEM, id, item);
    }

    public static void items() {
        BiomesWeveGone.LOGGER.info("Registering Oh The Biomes We've Gone items");
    }
}
