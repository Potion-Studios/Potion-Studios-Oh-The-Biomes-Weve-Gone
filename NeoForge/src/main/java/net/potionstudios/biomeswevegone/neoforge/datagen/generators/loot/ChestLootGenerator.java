package net.potionstudios.biomeswevegone.neoforge.datagen.generators.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.saveddata.maps.MapDecorationTypes;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.functions.ExplorationMapFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.item.BWGItems;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWood;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

public class ChestLootGenerator implements LootTableSubProvider {

    private final HolderLookup.Provider lookupProvider;

    public ChestLootGenerator(HolderLookup.Provider lookupProvider) {
        this.lookupProvider = lookupProvider;
    }

    @Override
    public void generate(@NotNull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        createTable(output,"village/forgotten/house", 3.0f, 8.0f,
                lootItemWithCount(BWGBlocks.SHELF_FUNGI.get(), 1, 4).setWeight(9),
                lootItemWithCount(Blocks.MOSS_BLOCK, 1, 7),
                lootItemWithCount(BWGItems.ALLIUM_ODDION_SOUP.get(), 1, 2).setWeight(1),
                lootItemWithCount(BWGItems.ODDION_BULB.get(), 2, 8).setWeight(10),
                lootItemWithCount(Items.AMETHYST_SHARD, 2, 4).setWeight(2),
                lootItemWithCount(Items.EMERALD, 1, 4).setWeight(1),
                lootItemWithCount(Items.MELON_SLICE, 1, 9).setWeight(8),
                lootItemWithCount(BWGItems.MUSIC_DISC_PIXIE_CLUB.get(), 1, 1).setWeight(1));

        createTable(output,"village/forgotten/armorer", 3.0f, 8.0f,
                lootItemWithCount(Items.RAW_IRON, 2, 7).setWeight(2),
                lootItemWithCount(Items.IRON_INGOT, 2, 4).setWeight(1),
                lootItemWithCount(Items.RAW_COPPER, 6, 12).setWeight(8),
                lootItemWithCount(Items.STONE_PICKAXE, 1, 1).setWeight(3),
                lootItemWithCount(Items.CHARCOAL, 3, 9).setWeight(6),
                lootItemWithCount(Items.EXPLORER_POTTERY_SHERD, 1, 2).setWeight(3),
                lootItemWithCount(Items.WILD_ARMOR_TRIM_SMITHING_TEMPLATE, 1, 2).setWeight(2));

        createTable(output,"village/forgotten/cartographer", 3.0f, 8.0f,
                lootItemWithCount(Items.SNIFFER_EGG, 1, 1).setWeight(1),
                lootItemWithCount(Items.TORCHFLOWER_SEEDS, 1, 1).setWeight(1),
                lootItemWithCount(Items.BONE, 6, 12).setWeight(9),
                lootItemWithCount(Items.PITCHER_POD, 1, 1).setWeight(1),
                lootItemWithCount(Items.STRING, 3, 9).setWeight(9),
                lootItemWithCount(Items.EMERALD, 1, 4).setWeight(9),
                lootItemWithCount(Items.SKULL_POTTERY_SHERD, 1, 2).setWeight(3),
                lootItemWithCount(Items.DANGER_POTTERY_SHERD, 1, 2).setWeight(2),
                lootItemWithCount(BWGItems.MUSIC_DISC_PIXIE_CLUB.get(), 1, 1).setWeight(1));

        createTable(output,"village/forgotten/temple", 3.0f, 8.0f,
                lootItemWithCount(Blocks.SCULK, 1, 4).setWeight(1),
                lootItemWithCount(Items.BONE, 6, 12).setWeight(9),
                lootItemWithCount(Items.STRING, 3, 9).setWeight(9),
                lootItemWithCount(Items.EMERALD, 1, 4).setWeight(9),
                lootItemWithCount(Items.EXPERIENCE_BOTTLE, 3, 7).setWeight(3),
                lootItemWithCount(Items.WILD_ARMOR_TRIM_SMITHING_TEMPLATE, 1, 2).setWeight(2),
                lootItemWithCount(BWGItems.MUSIC_DISC_PIXIE_CLUB.get(), 1, 1).setWeight(1));

        createTable(output,"village/skyris/house", 3.0f, 8.0f,
                lootItemWithCount(Items.BEETROOT_SOUP, 1, 3),
                lootItem(Items.LEAD).setWeight(2),
                lootItem(Items.LEATHER),
                lootItemWithCount(BWGBlocks.SKYRIS_VINE.get(), 1, 7),
                lootItemWithCount(BWGBlocks.FAIRY_SLIPPER.getBlock(), 1, 4).setWeight(10),
                lootItemWithCount(Items.WRITABLE_BOOK, 1, 5).setWeight(10),
                lootItem(Items.BOOK),
                lootItem(BWGItems.GREEN_APPLE.get()),
                lootItemWithCount(Items.EMERALD, 1, 4).setWeight(2),
                lootItemWithCount(BWGWood.SKYRIS.sapling().getBlock(), 1, 2).setWeight(5));

        createTable(output, "village/skyris/library", 3.0f, 8.0f,
                lootItemWithCount(Items.INK_SAC, 1, 3),
                lootItem(Items.MAP).setWeight(2),
                lootItem(Items.FEATHER),
                lootItemWithCount(Items.EXPERIENCE_BOTTLE, 1, 7).setWeight(10),
                lootItemWithCount(Items.PAPER, 1, 4).setWeight(10),
                lootItemWithCount(Items.WRITABLE_BOOK, 1, 5).setWeight(10),
                lootItem(Items.BOOK),
                lootItem(BWGWood.SKYRIS.bookshelf()),
                lootItemWithCount(Items.EMERALD, 1, 4).setWeight(2),
                lootItem(Items.BOOK).apply(EnchantWithLevelsFunction.enchantWithLevels(lookupProvider, ConstantValue.exactly(30))),
                lootItem(Items.MAP).apply(ExplorationMapFunction.makeExplorationMap().setZoom((byte)1).setMapDecoration(MapDecorationTypes.RED_X).setSkipKnownStructures(false)));

        createTable(output, "village/salem/house", 3.0f, 8.0f,
                lootItemWithCount(BWGItems.WHITE_PUFFBALL_STEW.get(), 1, 3).setWeight(6),
                lootItem(Items.EMERALD).setWeight(2),
                lootItem(Items.BREAD),
                lootItemWithCount(BWGWood.WITCH_HAZEL.sapling().getBlock().asItem(), 2, 5).setWeight(8),
                lootItemWithCount(BWGBlocks.WITCH_HAZEL_BLOSSOM.get(), 1, 3).setWeight(6),
                lootItemWithCount(BWGBlocks.GREEN_MUSHROOM.get(), 1, 5).setWeight(4),
                lootItem(Items.BOOK),
                lootItemWithCount(Items.PUMPKIN, 1, 4).setWeight(5),
                lootItemWithCount(Items.CANDLE, 2, 4).setWeight(5));

        createTable(output, "village/salem/temple", 3.0f, 5.0f,
                lootItemWithCount(Items.EMERALD, 3, 15).setWeight(2),
                lootItem(Items.RABBIT_FOOT).setWeight(1),
                lootItemWithCount(Items.EXPERIENCE_BOTTLE, 4, 9).setWeight(4),
                lootItemWithCount(Items.BOOK, 9, 12).setWeight(8),
                lootItemWithCount(Items.WRITABLE_BOOK, 1, 2).setWeight(7),
                lootItemWithCount(Items.GLISTERING_MELON_SLICE, 2, 4).setWeight(5));

        createTable(output, "village/salem/animal_pen", 3.0f, 8.0f,
                lootItemWithCount(Items.WHEAT, 12, 24),
                lootItem(Items.LEAD).setWeight(2),
                lootItem(Items.LEATHER),
                lootItem(Items.APPLE),
                lootItemWithCount(Items.CARROT, 1, 7).setWeight(10));

        createTable(output, "village/salem/forager", 3.0f, 8.0f,
                lootItemWithCount(BWGBlocks.POISON_IVY.get(), 3, 6).setWeight(2),
                lootItem(BWGBlocks.GREEN_MUSHROOM.get()).setWeight(8),
                lootItem(Items.PODZOL).setWeight(7),
                lootItemWithCount(BWGItems.WHITE_PUFFBALL_SPORES.get(), 3, 7).setWeight(10),
                lootItemWithCount(Items.STICK, 10, 15).setWeight(10),
                lootItem(BWGBlocks.WOOD_BLEWIT.get()),
                lootItem(BWGBlocks.WEEPING_MILKCAP.get()),
                lootItemWithCount(Items.BROWN_MUSHROOM, 1, 4).setWeight(2),
                lootItemWithCount(Items.RED_MUSHROOM, 1, 2).setWeight(5));

        createTable(output, "village/pumpkin_patch/house", 3.0f, 8.0f,
                lootItemWithCount(Items.PUMPKIN_PIE, 1, 3).setWeight(6),
                lootItem(Items.EMERALD).setWeight(2),
                lootItem(Items.BREAD),
                lootItemWithCount(Items.CARVED_PUMPKIN, 2, 5).setWeight(8),
                lootItemWithCount(Items.PUMPKIN, 1, 4).setWeight(6),
                lootItemWithCount(BWGBlocks.GREEN_MUSHROOM.get(), 1, 5).setWeight(4),
                lootItem(Items.PUMPKIN_SEEDS),
                lootItemWithCount(Items.CANDLE, 2, 4).setWeight(5));

        createTable(output, "village/red_rock/house", 3.0f, 8.0f,
                lootItemWithCount(BWGBlocks.CATTAIL_THATCH.get(), 1, 3),
                lootItem(Items.STONE_PICKAXE).setWeight(2),
                lootItem(Items.GOLDEN_PICKAXE),
                lootItemWithCount(BWGBlocks.FIRECRACKER_FLOWER_BUSH.getBlock(), 1, 7).setWeight(10),
                lootItemWithCount(BWGBlocks.ALOE_VERA.get(), 1, 4).setWeight(10),
                lootItemWithCount(Items.COPPER_INGOT, 1, 5).setWeight(10),
                lootItem(BWGBlocks.RED_ROCK_SET.getBase()),
                lootItem(BWGItems.CATTAIL_SPROUT.get()),
                lootItemWithCount(Items.EMERALD, 1, 4).setWeight(2),
                lootItemWithCount(BWGItems.ALOE_VERA_JUICE.get(), 1, 2).setWeight(5));

        createTable(output, "village/red_rock/cleric", 3.0f, 8.0f,
                lootItemWithCount(Items.PURPLE_CANDLE, 1, 3),
                lootItem(Items.REDSTONE),
                lootItemWithCount(Items.LAPIS_LAZULI, 1, 7).setWeight(10),
                lootItemWithCount(BWGBlocks.ALOE_VERA.get(), 1, 4).setWeight(10),
                lootItemWithCount(Items.PAPER, 1, 5).setWeight(10),
                lootItem(Items.BLACK_CANDLE),
                lootItem(Items.GLASS_BOTTLE),
                lootItemWithCount(Items.EMERALD, 1, 4).setWeight(2),
                lootItemWithCount(BWGItems.ALOE_VERA_JUICE.get(), 1, 2).setWeight(5));

        createTable(output, "village/red_rock/mason", 1.0f, 5.0f,
                lootItemWithCount(Items.CLAY_BALL, 1, 3),
                lootItem(Items.FLOWER_POT),
                lootItem(BWGBlocks.RED_ROCK_SET.getBase()).setWeight(2),
                lootItem(BWGBlocks.RED_ROCK_BRICKS_SET.getBase()).setWeight(2),
                lootItemWithCount(Items.IRON_PICKAXE, 1, 4).setWeight(4),
                lootItem(Items.YELLOW_DYE),
                lootItem(BWGBlocks.CHISELED_RED_ROCK_BRICKS_SET.getBase()),
                lootItem(Items.EMERALD));

        createTable(output, "village/red_rock/market", 3.0f, 8.0f,
                lootItemWithCount(BWGBlocks.CATTAIL_THATCH.get(), 1, 3),
                lootItem(Items.PAPER).setWeight(2),
                lootItem(Items.BOOK),
                lootItemWithCount(Items.MELON, 1, 7).setWeight(10),
                lootItemWithCount(Items.PUMPKIN, 1, 4).setWeight(10),
                lootItemWithCount(Items.COPPER_INGOT, 1, 5).setWeight(7),
                lootItem(Items.ARROW),
                lootItem(BWGItems.CATTAIL_SPROUT.get()),
                lootItemWithCount(Items.EMERALD, 1, 4).setWeight(2),
                lootItemWithCount(BWGItems.ALOE_VERA_JUICE.get(), 1, 2).setWeight(5));
    }

    private LootPoolSingletonContainer.Builder<?> lootItem(ItemLike item) {
        return LootItem.lootTableItem(item);
    }

    private LootPoolSingletonContainer.Builder<?> lootItemWithCount(ItemLike item, int min, int max) {
        return LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)));
    }

    private void createTable(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output, String id, float minRolls, float maxRolls, LootPoolEntryContainer.Builder<?>... entriesBuilder) {
        LootPool.Builder pool = LootPool.lootPool().setRolls(UniformGenerator.between(minRolls, maxRolls));
        for (LootPoolEntryContainer.Builder<?> entry : entriesBuilder) pool.add(entry);
        output.accept(ResourceKey.create(Registries.LOOT_TABLE, BiomesWeveGone.id("chests/" + id)), LootTable.lootTable().withPool(pool));
    }
}
