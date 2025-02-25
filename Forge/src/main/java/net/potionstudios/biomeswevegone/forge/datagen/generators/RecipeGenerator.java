package net.potionstudios.biomeswevegone.forge.datagen.generators;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.tags.BWGItemTags;
import net.potionstudios.biomeswevegone.world.item.BWGItems;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.block.sand.BWGSandSet;
import net.potionstudios.biomeswevegone.world.level.block.set.BWGBlockSet;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWood;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWoodSet;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * Generates recipes for the mod.
 * @see RecipeProvider
 * @author Joseph T. McQuigg
 */
public class RecipeGenerator extends RecipeProvider {
    public RecipeGenerator(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> writer) {
        BWGWoodSet.woodsets().forEach(set -> {
            planksFromLog(writer, set.planks(), set.logItemTag(), 4);
            woodFromLogs(writer, set.wood(), set.logstem());
            woodFromLogs(writer, set.strippedWood(), set.strippedLogStem());
            set.makeFamily();
            generateRecipes(writer, set.family());
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, set.bookshelf())
                    .define('#', set.planks())
                    .define('X', Items.BOOK)
                    .pattern("###")
                    .pattern("XXX")
                    .pattern("###")
                    .group("planks")
                    .unlockedBy(getHasName(set.planks()), has(set.planks()))
                    .save(writer);
            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, set.craftingTable())
                    .define('#', set.planks())
                    .pattern("##")
                    .pattern("##")
                    .group("planks")
                    .unlockedBy(getHasName(set.planks()), has(set.planks()))
                    .save(writer);
            hangingSign(writer, set.hangingSignItem(), set.strippedLogStem());
            woodenBoat(writer, set.boatItem().get(), set.planks());
            chestBoat(writer, set.chestBoatItem().get(), set.boatItem().get());
        });

        woodFromLogs(writer, BWGWood.PALO_VERDE_WOOD.get(), BWGWood.PALO_VERDE_LOG.get());
        woodFromLogs(writer, BWGWood.STRIPPED_PALO_VERDE_WOOD.get(), BWGWood.STRIPPED_PALO_VERDE_LOG.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Items.BIRCH_PLANKS, 4)
                .requires(BWGItemTags.PALO_VERDE_LOGS)
                .group("planks")
                .unlockedBy("has_logs", has(BWGItemTags.PALO_VERDE_LOGS))
                .save(writer, BiomesWeveGone.id("birch_planks_from_palo_verde_logs"));


        BWGSandSet.getSandSets().forEach(set -> {
            generateRecipes(writer, set.getSmoothSandStoneFamily());
            generateRecipes(writer, set.getCutSandStoneFamily());
        });

        BWGBlockSet.getBlockSets().forEach(set -> {
            generateRecipes(writer, set.getBlockFamily());
            if (set.getBase().defaultBlockState().getSoundType() == SoundType.STONE || set.getBase().defaultBlockState().getSoundType() == SoundType.NETHERRACK)
                generateFamilyStoneCutterRecipes(writer, set);
        });

        BWGSandSet.getSandSets().forEach(set -> {
            twoByTwoPacker(writer, RecipeCategory.BUILDING_BLOCKS, set.getSandstone(), set.getSand());
            chiseled(writer, RecipeCategory.BUILDING_BLOCKS, set.getChiseledSandstone(), set.getSandstoneSlab());
            slabBuilder(RecipeCategory.BUILDING_BLOCKS, set.getSandstoneSlab(), Ingredient.of(set.getSandstone(), set.getChiseledSandstone()))
                    .unlockedBy(getHasName(set.getSandstone()), has(set.getSandstone()))
                    .unlockedBy(getHasName(set.getChiseledSandstone()), has(set.getChiseledSandstone()))
                    .save(writer);
            stairBuilder(set.getSandstoneStairs(), Ingredient.of(set.getSandstone(), set.getChiseledSandstone(), set.getCutSandstone()))
                    .unlockedBy(getHasName(set.getSandstone()), has(set.getSandstone()))
                    .unlockedBy(getHasName(set.getChiseledSandstone()), has(set.getChiseledSandstone()))
                    .unlockedBy(getHasName(set.getCutSandstone()), has(set.getCutSandstone()))
                    .save(writer);
            cut(writer, RecipeCategory.BUILDING_BLOCKS, set.getCutSandstone(), set.getSandstone());
            wall(writer, RecipeCategory.DECORATIONS, set.getSandstoneWall(), set.getSandstone());
            stonecutterResultFromBase(writer, RecipeCategory.BUILDING_BLOCKS, set.getCutSandstone(), set.getSandstone());
            stonecutterResultFromBase(writer, RecipeCategory.BUILDING_BLOCKS, set.getSandstoneSlab(), set.getSandstone(), 2);
            stonecutterResultFromBase(writer, RecipeCategory.BUILDING_BLOCKS, set.getCutSandstoneSlab(), set.getSandstone(), 2);
            stonecutterResultFromBase(writer, RecipeCategory.BUILDING_BLOCKS, set.getCutSandstoneSlab(), set.getCutSandstone(), 2);
            stonecutterResultFromBase(writer, RecipeCategory.BUILDING_BLOCKS, set.getSandstoneStairs(), set.getSandstone());
            stonecutterResultFromBase(writer, RecipeCategory.DECORATIONS, set.getSandstoneWall(), set.getSandstone());
            stonecutterResultFromBase(writer, RecipeCategory.BUILDING_BLOCKS, set.getChiseledSandstone(), set.getSandstone());
            stonecutterResultFromBase(writer, RecipeCategory.BUILDING_BLOCKS, set.getSmoothSandstoneSlab(), set.getSandstone(), 2);
            stonecutterResultFromBase(writer, RecipeCategory.BUILDING_BLOCKS, set.getSmoothSandstoneStairs(), set.getSandstone());
            SimpleCookingRecipeBuilder.smelting(Ingredient.of(set.getSandstone()), RecipeCategory.BUILDING_BLOCKS, set.getSmoothSandstone().asItem(), 0.1F, 200)
                    .unlockedBy(getHasName(set.getSandstone()), has(set.getSandstone()))
                    .save(writer);
        });

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(BWGItems.YUCCA_FRUIT.get()), RecipeCategory.FOOD, BWGItems.COOKED_YUCCA_FRUIT.get(), 0.35F, 200)
                        .unlockedBy(getHasName(BWGItems.YUCCA_FRUIT.get()), has(BWGItems.YUCCA_FRUIT.get()))
                        .save(writer, BiomesWeveGone.id("cooked_yucca_fruit_from_smelting"));

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(BWGItems.YUCCA_FRUIT.get()), RecipeCategory.FOOD, BWGItems.COOKED_YUCCA_FRUIT.get(), 0.35F, 600)
                        .unlockedBy(getHasName(BWGItems.YUCCA_FRUIT.get()), has(BWGItems.YUCCA_FRUIT.get()))
                        .save(writer, BiomesWeveGone.id("cooked_yucca_fruit_from_camfire"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(BWGItems.YUCCA_FRUIT.get()), RecipeCategory.FOOD, BWGItems.COOKED_YUCCA_FRUIT.get(), 0.35F, 100)
                        .unlockedBy(getHasName(BWGItems.YUCCA_FRUIT.get()), has(BWGItems.YUCCA_FRUIT.get()))
                        .save(writer, BiomesWeveGone.id("cooked_yucca_fruit_from_smoker"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(BWGItems.ODDION_BULB.get()), RecipeCategory.FOOD, BWGItems.COOKED_ODDION_BULB.get(), 0.35F, 200)
                        .unlockedBy(getHasName(BWGItems.ODDION_BULB.get()), has(BWGItems.ODDION_BULB.get()))
                        .save(writer, BiomesWeveGone.id("cooked_oddion_bulb_from_smelting"));

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(BWGItems.ODDION_BULB.get()), RecipeCategory.FOOD, BWGItems.COOKED_ODDION_BULB.get(), 0.35F, 600)
                        .unlockedBy(getHasName(BWGItems.ODDION_BULB.get()), has(BWGItems.ODDION_BULB.get()))
                        .save(writer, BiomesWeveGone.id("cooked_oddion_bulb_from_campfire"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(BWGItems.ODDION_BULB.get()), RecipeCategory.FOOD, BWGItems.COOKED_ODDION_BULB.get(), 0.35F, 100)
                        .unlockedBy(getHasName(BWGItems.ODDION_BULB.get()), has(BWGItems.ODDION_BULB.get()))
                        .save(writer, BiomesWeveGone.id("cooked_oddion_bulb_from_smoker"));

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, BWGItems.ALLIUM_ODDION_SOUP.get())
                .define('#', BWGItems.COOKED_ODDION_BULB.get())
                .define('X', Items.BOWL)
                .pattern("##")
                .pattern("X ")
                .unlockedBy(getHasName(BWGItems.COOKED_ODDION_BULB.get()), has(BWGItems.COOKED_ODDION_BULB.get()))
                .save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, BWGItems.BLOOMING_ODDION.get())
                        .define('#', Items.EGG)
                        .define('X', Items.WHEAT)
                        .define('Y', BWGItems.COOKED_ODDION_BULB.get())
                        .pattern("X#X")
                        .pattern(" Y ")
                        .unlockedBy(getHasName(BWGItems.COOKED_ODDION_BULB.get()), has(BWGItems.COOKED_ODDION_BULB.get()))
                        .save(writer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BREWING, BWGItems.ALOE_VERA_JUICE.get(), 4)
                        .requires(BWGBlocks.ALOE_VERA.get())
                        .requires(Items.GLASS_BOTTLE, 4)
                        .unlockedBy(getHasName(BWGBlocks.ALOE_VERA.get()), has(BWGBlocks.ALOE_VERA.get()))
                        .save(writer);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(BWGItems.WHITE_PUFFBALL_CAP.get()), RecipeCategory.FOOD, BWGItems.COOKED_WHITE_PUFFBALL_CAP.get(), 0.35F, 200)
                .unlockedBy(getHasName(BWGItems.WHITE_PUFFBALL_CAP.get()), has(BWGItems.WHITE_PUFFBALL_CAP.get()))
                .save(writer);

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(BWGItems.WHITE_PUFFBALL_CAP.get()), RecipeCategory.FOOD, BWGItems.COOKED_WHITE_PUFFBALL_CAP.get(), 0.35F, 600)
                .unlockedBy(getHasName(BWGItems.WHITE_PUFFBALL_CAP.get()), has(BWGItems.WHITE_PUFFBALL_CAP.get()))
                .save(writer, BiomesWeveGone.id("cooked_white_puffball_cap_from_campfire"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(BWGItems.WHITE_PUFFBALL_CAP.get()), RecipeCategory.FOOD, BWGItems.COOKED_WHITE_PUFFBALL_CAP.get(), 0.35F, 100)
                .unlockedBy(getHasName(BWGItems.WHITE_PUFFBALL_CAP.get()), has(BWGItems.WHITE_PUFFBALL_CAP.get()))
                .save(writer, BiomesWeveGone.id("cooked_white_puffball_cap_from_smoker"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, BWGItems.WHITE_PUFFBALL_STEW.get())
                .requires(Items.BOWL)
                .requires(BWGItems.COOKED_WHITE_PUFFBALL_CAP.get(), 2)
                .unlockedBy(getHasName(BWGItems.COOKED_WHITE_PUFFBALL_CAP.get()), has(BWGItems.COOKED_WHITE_PUFFBALL_CAP.get()))
                .save(writer);

        threeByThreePacker(writer, RecipeCategory.BUILDING_BLOCKS, BWGBlocks.PACKED_BLACK_ICE.get(), BWGBlocks.BLACK_ICE.get());
        threeByThreePacker(writer, RecipeCategory.BUILDING_BLOCKS, BWGBlocks.PACKED_BOREALIS_ICE.get(), BWGBlocks.BOREALIS_ICE.get());

        twoByTwoPackertoFourWithStoneCutting(writer, RecipeCategory.BUILDING_BLOCKS, BWGBlocks.RED_ROCK_BRICKS_SET.getBase(), BWGBlocks.RED_ROCK_SET.getBase());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BWGBlocks.MOSSY_RED_ROCK_BRICKS_SET.getBase())
                .requires(BWGBlocks.RED_ROCK_BRICKS_SET.getBase())
                .requires(Items.VINE)
                .group("mossy_red_rock_bricks")
                .unlockedBy(getHasName(Items.VINE), has(Items.VINE))
                .save(writer, getConversionRecipeName(BWGBlocks.MOSSY_RED_ROCK_BRICKS_SET.getBase(), Items.VINE));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BWGBlocks.MOSSY_RED_ROCK_BRICKS_SET.getBase())
                .requires(BWGBlocks.RED_ROCK_BRICKS_SET.getBase())
                .requires(Items.MOSS_BLOCK)
                .group("mossy_red_rock_bricks")
                .unlockedBy(getHasName(Items.MOSS_BLOCK), has(Items.MOSS_BLOCK))
                .save(writer, getConversionRecipeName(BWGBlocks.MOSSY_RED_ROCK_BRICKS_SET.getBase(), Items.MOSS_BLOCK));
        chiseledBuilder(RecipeCategory.BUILDING_BLOCKS, BWGBlocks.CHISELED_RED_ROCK_BRICKS_SET.getBase(), Ingredient.of(BWGBlocks.RED_ROCK_BRICKS_SET.getSlab()))
                .unlockedBy("has_tag", has(BWGItemTags.RED_ROCK_BRICKS))
                .save(writer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BWGBlocks.ROCKY_STONE_SET.getBase(), 2)
                .requires(Items.COBBLESTONE)
                .requires(Items.STONE)
                .unlockedBy(getHasName(Items.COBBLESTONE), has(Items.COBBLESTONE))
                .unlockedBy(getHasName(Items.STONE), has(Items.STONE))
                .save(writer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BWGBlocks.MOSSY_STONE_SET.getBase())
                .requires(Items.STONE)
                .requires(Items.VINE)
                .group("mossy_stone")
                .unlockedBy(getHasName(Items.VINE), has(Items.VINE))
                .save(writer, getConversionRecipeName(BWGBlocks.MOSSY_STONE_SET.getBase(), Items.VINE));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BWGBlocks.MOSSY_STONE_SET.getBase())
                .requires(Items.STONE)
                .requires(Items.MOSS_BLOCK)
                .group("mossy_stone")
                .unlockedBy(getHasName(Items.MOSS_BLOCK), has(Items.MOSS_BLOCK))
                .save(writer, getConversionRecipeName(BWGBlocks.MOSSY_STONE_SET.getBase(), Items.MOSS_BLOCK));


        smeltingResultFromBase(writer, BWGBlocks.DACITE_SET.getBase(), BWGBlocks.DACITE_COBBLESTONE_SET.getBase());
        twoByTwoPackertoFourWithStoneCutting(writer, RecipeCategory.BUILDING_BLOCKS, BWGBlocks.DACITE_BRICKS_SET.getBase(), BWGBlocks.DACITE_SET.getBase());
        twoByTwoPackertoFourWithStoneCutting(writer, RecipeCategory.BUILDING_BLOCKS, BWGBlocks.DACITE_TILE_SET.getBase(), BWGBlocks.DACITE_BRICKS_SET.getBase());
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BWGBlocks.DACITE_PILLAR.get())
                .define('#', BWGBlocks.DACITE_SET.getBase())
                .pattern("#")
                .pattern("#")
                .unlockedBy(getHasName(BWGBlocks.DACITE_SET.getBase()), has(BWGBlocks.DACITE_SET.getBase()))
                .save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BWGBlocks.WINDSWEPT_SANDSTONE_PILLAR.get())
                .define('X', BWGBlocks.WINDSWEPT_SAND_SET.getSandstone())
                .pattern("X")
                .pattern("X")
                .unlockedBy(getHasName(BWGBlocks.WINDSWEPT_SAND_SET.getSandstone()), has(BWGBlocks.WINDSWEPT_SAND_SET.getSandstone()))
                .save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Items.GOLDEN_APPLE)
                .define('#', Items.GOLD_INGOT)
                .define('X', BWGItems.GREEN_APPLE.get())
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .save(writer, BiomesWeveGone.id("golden_apple_from_green_apple"));

        sandToGlass(writer, BWGBlocks.BLACK_SAND_SET, Items.BLACK_STAINED_GLASS);
        sandToGlass(writer, BWGBlocks.BLUE_SAND_SET, Items.BLUE_STAINED_GLASS);
        sandToGlass(writer, BWGBlocks.PINK_SAND_SET, Items.PINK_STAINED_GLASS);
        sandToGlass(writer, BWGBlocks.PURPLE_SAND_SET, Items.PURPLE_STAINED_GLASS);
        sandToGlass(writer, BWGBlocks.WHITE_SAND_SET, Items.WHITE_STAINED_GLASS);
        dyeAbleRecipe(writer, BWGBlocks.BLACK_SAND_SET.getSand(), Items.SAND, Items.BLACK_DYE);
        dyeAbleRecipe(writer, BWGBlocks.BLUE_SAND_SET.getSand(), Items.SAND, Items.BLUE_DYE);
        dyeAbleRecipe(writer, BWGBlocks.PINK_SAND_SET.getSand(), Items.SAND, Items.PINK_DYE);
        dyeAbleRecipe(writer, BWGBlocks.PURPLE_SAND_SET.getSand(), Items.SAND, Items.PURPLE_DYE);
        dyeAbleRecipe(writer, BWGBlocks.WHITE_SAND_SET.getSand(), Items.SAND, Items.WHITE_DYE);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, BWGItems.GREEN_APPLE_PIE.get())
                .requires(BWGItems.GREEN_APPLE.get())
                .requires(Items.SUGAR)
                .requires(Items.EGG)
                .unlockedBy(getHasName(BWGItems.GREEN_APPLE.get()), has(BWGItems.GREEN_APPLE.get()))
                .save(writer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, BWGItems.BLUEBERRY_PIE.get())
                .requires(BWGItems.BLUEBERRIES.get())
                .requires(Items.SUGAR)
                .requires(Items.EGG)
                .unlockedBy(getHasName(BWGItems.BLUEBERRIES.get()), has(BWGItems.BLUEBERRIES.get()))
                .save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BWGBlocks.CATTAIL_THATCH.get(), 4)
                .define('#', BWGItems.CATTAIL_SPROUT.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(BWGItems.CATTAIL_SPROUT.get()), has(BWGItems.CATTAIL_SPROUT.get()))
                .save(writer);

        slab(writer, RecipeCategory.BUILDING_BLOCKS, BWGBlocks.CATTAIL_THATCH_SLAB.get(), BWGBlocks.CATTAIL_THATCH.get());
        stairBuilder(BWGBlocks.CATTAIL_THATCH_STAIRS.get(), Ingredient.of(BWGBlocks.CATTAIL_THATCH.get()))
                .unlockedBy(getHasName(BWGBlocks.CATTAIL_THATCH.get()), has(BWGBlocks.CATTAIL_THATCH.get()))
                .save(writer);
        carpet(writer, BWGBlocks.CATTAIL_THATCH_CARPET.get(), BWGBlocks.CATTAIL_THATCH.get());

        nineBlockStorageRecipes(writer, RecipeCategory.DECORATIONS, Items.ALLIUM, RecipeCategory.DECORATIONS, BWGBlocks.ALLIUM_PETAL_BLOCK.get());
        nineBlockStorageRecipes(writer, RecipeCategory.DECORATIONS, BWGBlocks.PINK_ALLIUM.getBlock(), RecipeCategory.DECORATIONS, BWGBlocks.PINK_ALLIUM_PETAL_BLOCK.get());
        nineBlockStorageRecipes(writer, RecipeCategory.DECORATIONS, BWGBlocks.WHITE_ALLIUM.getBlock(), RecipeCategory.DECORATIONS, BWGBlocks.WHITE_ALLIUM_PETAL_BLOCK.get());
        nineBlockStorageRecipes(writer, RecipeCategory.DECORATIONS, BWGBlocks.ROSE.getBlock(), RecipeCategory.DECORATIONS, BWGBlocks.ROSE_PETAL_BLOCK.get());

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Items.PURPLE_WOOL)
                .requires(BWGBlocks.ALLIUM_PETAL_BLOCK.get(), 9)
                .unlockedBy(getHasName(BWGBlocks.ALLIUM_PETAL_BLOCK.get()), has(BWGBlocks.ALLIUM_PETAL_BLOCK.get()))
                .save(writer, BiomesWeveGone.id("purple_wool_from_allium_petal_block"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Items.WHITE_WOOL)
                .requires(BWGBlocks.WHITE_ALLIUM_PETAL_BLOCK.get(), 9)
                .unlockedBy(getHasName(BWGBlocks.WHITE_ALLIUM_PETAL_BLOCK.get()), has(BWGBlocks.WHITE_ALLIUM_PETAL_BLOCK.get()))
                .save(writer, BiomesWeveGone.id("white_wool_from_white_allium_petal_block"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Items.PINK_WOOL)
                .requires(BWGBlocks.PINK_ALLIUM_PETAL_BLOCK.get(), 9)
                .unlockedBy(getHasName(BWGBlocks.PINK_ALLIUM_PETAL_BLOCK.get()), has(BWGBlocks.PINK_ALLIUM_PETAL_BLOCK.get()))
                .save(writer, BiomesWeveGone.id("pink_wool_from_pink_allium_petal_block"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Items.RED_WOOL)
                .requires(BWGBlocks.ROSE_PETAL_BLOCK.get(), 9)
                .unlockedBy(getHasName(BWGBlocks.ROSE_PETAL_BLOCK.get()), has(BWGBlocks.ROSE_PETAL_BLOCK.get()))
                .save(writer, BiomesWeveGone.id("red_wool_from_rose_petal_block"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BWGBlocks.SANDY_DIRT.get(), 4)
                .requires(Items.SAND, 2)
                .requires(Items.DIRT, 2)
                .unlockedBy(getHasName(Items.SAND), has(Items.SAND))
                .save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BWGBlocks.FORAGERS_TABLE.get())
                .define('X', ItemTags.PLANKS)
                .define('#', BWGItemTags.MUSHROOMS)
                .pattern("##")
                .pattern("XX")
                .unlockedBy("has_mushrooms", has(BWGItemTags.MUSHROOMS))
                .save(writer);

        BWGBlocks.BLOCKS.stream().filter(entry -> entry.get() instanceof FlowerBlock || entry.get() instanceof TallFlowerBlock).forEach(
                entry -> {
                    Block block = entry.get();
                    Item dye = DyeItem.byColor(DyeColor.byId(block.defaultMapColor().id));
                    oneToOneConversionRecipe(writer, dye, block, getItemName(dye), block instanceof TallFlowerBlock ? 2 : 1);
                }
        );
    }

    private static void sandToGlass(Consumer<FinishedRecipe> finishedRecipeConsumer, BWGSandSet set, Item glass) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(set.getSand()), RecipeCategory.BUILDING_BLOCKS, glass, 0.1F, 200)
                .unlockedBy(getHasName(set.getSand()), has(set.getSand()))
                .save(finishedRecipeConsumer, BiomesWeveGone.id(getHasName(glass).replace("has_", "") + "_from_" + getHasName(set.getSand()).replace("has_", "")));
    }

    private static void generateFamilyStoneCutterRecipes(Consumer<FinishedRecipe> finishedRecipeConsumer, BWGBlockSet set) {
        stonecutterResultFromBase(finishedRecipeConsumer, RecipeCategory.BUILDING_BLOCKS, set.getSlab(), set.getBase(),2);
        stonecutterResultFromBase(finishedRecipeConsumer, RecipeCategory.BUILDING_BLOCKS, set.getStairs(), set.getBase());
        stonecutterResultFromBase(finishedRecipeConsumer, RecipeCategory.DECORATIONS, set.getWall(), set.getBase());
    }

    private static void twoByTwoPackertoFourWithStoneCutting(Consumer<FinishedRecipe> finishedRecipeConsumer, RecipeCategory category, ItemLike packed, ItemLike unpacked) {
        ShapedRecipeBuilder.shaped(category, packed, 4)
                .define('#', unpacked)
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(unpacked), has(unpacked))
                .save(finishedRecipeConsumer);
        stonecutterResultFromBase(finishedRecipeConsumer, category, packed, unpacked);
    }

    private static void dyeAbleRecipe(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike item, ItemLike ingredient, Item dye) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, item, 8)
                .define('#', dye)
                .define('X', ingredient)
                .pattern("XXX")
                .pattern("X#X")
                .pattern("XXX")
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(finishedRecipeConsumer);
    }

}
