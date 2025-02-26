package net.potionstudios.biomeswevegone.world.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
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

import java.util.ArrayList;
import java.util.function.Function;
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

    public static final Supplier<Item> BWG_LOGO = register("bwg_logo", Item::new, new Item.Properties());

    public static final Supplier<SpawnEggItem> MAN_O_WAR_SPAWN_EGG = registerItem("man_o_war_spawn_egg", properties -> new SpawnEggItem(BWGEntities.MAN_O_WAR.get(), properties), new Item.Properties());
    public static final Supplier<SpawnEggItem> PUMPKIN_WARDEN_SPAWN_EGG = registerItem("pumpkin_warden_spawn_egg", properties -> new SpawnEggItem(BWGEntities.PUMPKIN_WARDEN.get(), properties), new Item.Properties());
    public static final Supplier<SpawnEggItem> ODDION_SPAWN_EGG = registerItem("oddion_spawn_egg", properties -> new SpawnEggItem(BWGEntities.ODDION.get(), properties), new Item.Properties());

    public static final Supplier<MobBucketItem> MAN_O_WAR_BUCKET = registerMobBucket("man_o_war_bucket", BWGEntities.MAN_O_WAR::get, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH);

    public static final Supplier<Item> CATTAIL_SPROUT = registerItemNoLang("cattail_sprout", properties-> new CampfireExplodingBlockItem(BWGBlocks.CATTAIL_SPROUT, properties), new Item.Properties());
    public static final Supplier<Item> FLUORESCENT_CATTAIL_SPROUT = registerItemNoLang("fluorescent_cattail_sprout", properties -> new CampfireExplodingBlockItem(BWGBlocks.FLUORESCENT_CATTAIL_SPROUT, properties), new Item.Properties());

    public static final Supplier<Item> BLUE_GLOWCANE_SHOOT = registerSimpleItem("blue_glowcane_shoot", properties -> new BlockItem(BWGBlocks.BLUE_GLOWCANE.get(), properties), new Item.Properties().useItemDescriptionPrefix());
    public static final Supplier<Item> GREEN_GLOWCANE_SHOOT = registerSimpleItem("green_glowcane_shoot", properties -> new BlockItem(BWGBlocks.GREEN_GLOWCANE.get(), properties), new Item.Properties().useItemDescriptionPrefix());
    public static final Supplier<Item> RED_GLOWCANE_SHOOT = registerSimpleItem("red_glowcane_shoot", properties -> new BlockItem(BWGBlocks.RED_GLOWCANE.get(), properties), new Item.Properties().useItemDescriptionPrefix());
    public static final Supplier<Item> YELLOW_GLOWCANE_SHOOT = registerSimpleItem("yellow_glowcane_shoot", properties -> new BlockItem(BWGBlocks.YELLOW_GLOWCANE.get(), properties), new Item.Properties().useItemDescriptionPrefix());

    public static final Supplier<Item> BLUE_GLOWCANE_POWDER = registerSimpleItem("blue_glowcane_powder", properties -> new PowderItem(properties, ColorProperty.BLUE), new Item.Properties());
    public static final Supplier<Item> GREEN_GLOWCANE_POWDER = registerSimpleItem("green_glowcane_powder", properties -> new PowderItem(properties, ColorProperty.GREEN), new Item.Properties());
    public static final Supplier<Item> RED_GLOWCANE_POWDER = registerSimpleItem("red_glowcane_powder", properties -> new PowderItem(properties, ColorProperty.RED), new Item.Properties());
    public static final Supplier<Item> YELLOW_GLOWCANE_POWDER = registerSimpleItem("yellow_glowcane_powder", properties -> new PowderItem(properties, ColorProperty.YELLOW), new Item.Properties());

    public static final Supplier<Item> PALE_PUMPKIN_SEEDS = registerSimpleItem("pale_pumpkin_seeds", properties -> new BlockItem(BWGBlocks.PALE_PUMPKIN_STEM.get(), properties), new Item.Properties().useItemDescriptionPrefix());

    public static final Supplier<Item> BAOBAB_FRUIT = registerSimpleItem("baobab_fruit", Item::new, new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.35f).build()));
    public static final Supplier<Item> SOUL_FRUIT = registerSimpleItem("soul_fruit", Item::new, new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.35f).build()).component(DataComponents.CONSUMABLE, Consumable.builder().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40))).build()));
    public static final Supplier<Item> YUCCA_FRUIT = registerSimpleItem("yucca_fruit", Item::new, new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.35f).build()));
    public static final Supplier<Item> COOKED_YUCCA_FRUIT = registerSimpleItem("cooked_yucca_fruit", Item::new, new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationModifier(0.4f).build()));
    public static final Supplier<Item> GREEN_APPLE = registerSimpleItem("green_apple", Item::new, new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.35f).build()));
    public static final Supplier<Item> GREEN_APPLE_PIE = registerSimpleItem("green_apple_pie", Item::new, new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationModifier(0.4f).build()).component(DataComponents.CONSUMABLE, Consumable.builder().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 200, 0))).build()));

    public static final Supplier<Item> BLUEBERRIES = registerSimpleItem("blueberries", properties -> new BlockItem(BWGBlocks.BLUEBERRY_BUSH.get() , properties), new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).build()).useItemDescriptionPrefix());
    public static final Supplier<Item> BLUEBERRY_PIE = registerSimpleItem("blueberry_pie", Item::new, new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationModifier(0.3f).build()).component(DataComponents.CONSUMABLE, Consumable.builder().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 0))).build()));

    public static final Supplier<Item> ODDION_BULB = registerSimpleItem("oddion_bulb", properties -> new BlockItem(BWGBlocks.ODDION_CROP.get(), properties), new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.35f).build()).useItemDescriptionPrefix());
    public static final Supplier<Item> COOKED_ODDION_BULB = registerSimpleItem("cooked_oddion_bulb", Item::new, new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationModifier(0.5f).build()));
    public static final Supplier<Item> ALLIUM_ODDION_SOUP = registerSimpleItem("allium_oddion_soup", Item::new, new Item.Properties().stacksTo(1).usingConvertsTo(Items.BOWL).food((new FoodProperties.Builder()).nutrition(8).saturationModifier(1f).build()));
    public static final Supplier<Item> BLOOMING_ODDION = registerSimpleItem("blooming_oddion", Item::new, new Item.Properties().food(new FoodProperties.Builder().nutrition(9).saturationModifier(1.2f).build()));

    public static final Supplier<Item> WHITE_PUFFBALL_SPORES = registerSimpleItem("white_puffball_spores", properties -> new BlockItem(BWGBlocks.WHITE_PUFFBALL.getBlock(), properties), new Item.Properties().useItemDescriptionPrefix());
    public static final Supplier<Item> WHITE_PUFFBALL_CAP = registerSimpleItem("white_puffball_cap", Item::new, new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationModifier(0.2f).build()));
    public static final Supplier<Item> COOKED_WHITE_PUFFBALL_CAP = registerSimpleItem("cooked_white_puffball_cap", Item::new, new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.6f).build()));
    public static final Supplier<Item> WHITE_PUFFBALL_STEW = registerSimpleItem("white_puffball_stew", Item::new, new Item.Properties().stacksTo(1).usingConvertsTo(Items.BOWL).food((new FoodProperties.Builder()).nutrition(9).saturationModifier(1F).build()));

    public static final Supplier<Item> ALOE_VERA_JUICE = registerSimpleItem("aloe_vera_juice", Item::new, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).usingConvertsTo(Items.GLASS_BOTTLE).stacksTo(16).food(new FoodProperties.Builder().nutrition(3).saturationModifier(1.0F).build()));

    public static final Supplier<PlaceOnWaterBlockItem> TINY_LILY_PADS = registerItemNoLang("tiny_lily_pads", properties -> new PlaceOnWaterBlockItem(BWGBlocks.TINY_LILY_PADS.get(), properties), new Item.Properties());
    public static final Supplier<PlaceOnWaterBlockItem> FLOWERING_TINY_LILY_PADS = registerItemNoLang("flowering_tiny_lily_pads", properties -> new PlaceOnWaterBlockItem(BWGBlocks.FLOWERING_TINY_LILY_PADS.get(), properties), new Item.Properties());
    public static final Supplier<PlaceOnWaterBlockItem> WATER_SILK = registerItemNoLang("water_silk", properties -> new PlaceOnWaterBlockItem(BWGBlocks.WATER_SILK.get(), properties), new Item.Properties());

    public static final Supplier<Item> MUSIC_DISC_PIXIE_CLUB = registerItemNoLang("music_disc_pixie_club", Item::new, (new Item.Properties()).stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(BWGJukeBoxSongs.PIXIE_CLUB));


    private static Supplier<MobBucketItem> registerMobBucket(String id, Supplier<EntityType<? extends Mob>> entity, Supplier<Fluid> fluid, Supplier<SoundEvent> sound) {
        Supplier<MobBucketItem> supplier = PlatformHandler.PLATFORM_HANDLER.createMobBucket(entity, fluid, sound);
        supplier = PlatformHandler.PLATFORM_HANDLER.register(BuiltInRegistries.ITEM, id, supplier);
        SIMPLE_ITEMS.add(supplier);
        return supplier;
    }

    public static <I extends Item> Supplier<I> registerSimpleItem(String id, Function<Item.Properties, I> item, Item.Properties properties) {
        Supplier<I> supplier = registerItem(id, item, properties);
        SIMPLE_ITEMS.add(supplier);
        return supplier;
    }

    public static <I extends Item> Supplier<I> registerItem(String id, Function<Item.Properties, I> item, Item.Properties properties) {
        Supplier<I> supplier = register(id, item, properties);
        ITEMS.add(supplier);
        return supplier;
    }

    public static <I extends Item> Supplier<I> registerItemNoLang(String id, Function<Item.Properties, I> item, Item.Properties properties) {
        Supplier<I> supplier = register(id, item, properties);
        NO_LANG_ITEMS.add(supplier);
        return supplier;
    }

    public static <I extends Item> Supplier<I> register(String id, Function<Item.Properties, I> item, Item.Properties properties) {
        return PlatformHandler.PLATFORM_HANDLER.register(BuiltInRegistries.ITEM, id, () -> item.apply(properties.setId(key(id))));
    }

    private static ResourceKey<Item> key(String id) {
        return BiomesWeveGone.key(Registries.ITEM, id);
    }

    public static void items() {
        BiomesWeveGone.LOGGER.info("Registering Oh The Biomes We've Gone items");
    }
}
