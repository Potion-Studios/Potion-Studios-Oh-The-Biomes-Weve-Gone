package net.potionstudios.biomeswevegone.neoforge.datagen.generators;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.neoforge.common.Tags;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.tags.BWGItemTags;
import net.potionstudios.biomeswevegone.world.item.BWGItems;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.block.sand.BWGSandSet;
import net.potionstudios.biomeswevegone.world.level.block.set.BWGBlockSet;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWood;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWoodSet;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

/**
 * Generates recipes for the mod.
 * @see RecipeProvider
 * @author Joseph T. McQuigg
 */
public class RecipeGenerator extends RecipeProvider {
    public RecipeGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.CRAFTING_TABLE)
                .define('#', BWGItemTags.VANILLA_PLANKS)
                .pattern("##")
                .pattern("##")
                .group("planks")
                .unlockedBy("has_vanilla_planks", has(BWGItemTags.VANILLA_PLANKS))
                .save(recipeOutput);


        BWGWoodSet.woodsets().forEach(set -> {
            planksFromLog(recipeOutput, set.planks(), set.logItemTag(), 4);
            woodFromLogs(recipeOutput, set.wood(), set.logstem());
            woodFromLogs(recipeOutput, set.strippedWood(), set.strippedLogStem());
            set.makeFamily();

            generateRecipes(recipeOutput, set.family(), FeatureFlagSet.of(FeatureFlags.VANILLA));
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, set.bookshelf())
                    .define('#', set.planks())
                    .define('X', Items.BOOK)
                    .pattern("###")
                    .pattern("XXX")
                    .pattern("###")
                    .group("planks")
                    .unlockedBy(getHasName(set.planks()), has(set.planks()))
                    .save(recipeOutput);
            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, set.craftingTable())
                    .define('#', set.planks())
                    .pattern("##")
                    .pattern("##")
                    .group("planks")
                    .unlockedBy(getHasName(set.planks()), has(set.planks()))
                    .save(recipeOutput);
            hangingSign(recipeOutput, set.hangingSignItem(), set.strippedLogStem());
            woodenBoat(recipeOutput, set.boatItem().get(), set.planks());
            chestBoat(recipeOutput, set.chestBoatItem().get(), set.planks());
        });

        woodFromLogs(recipeOutput, BWGWood.PALO_VERDE_WOOD.get(), BWGWood.PALO_VERDE_LOG.get());
        woodFromLogs(recipeOutput, BWGWood.STRIPPED_PALO_VERDE_WOOD.get(), BWGWood.STRIPPED_PALO_VERDE_LOG.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Items.BIRCH_PLANKS, 4)
                .requires(BWGItemTags.PALO_VERDE_LOGS)
                .group("planks")
                .unlockedBy("has_logs", has(BWGItemTags.PALO_VERDE_LOGS))
                .save(recipeOutput, BiomesWeveGone.id("birch_planks_from_palo_verde_logs"));


        BWGSandSet.getSandSets().forEach(set -> {
            generateRecipes(recipeOutput, set.getSmoothSandStoneFamily(), FeatureFlagSet.of(FeatureFlags.VANILLA));
            generateRecipes(recipeOutput, set.getCutSandStoneFamily(), FeatureFlagSet.of(FeatureFlags.VANILLA));
        });

        BWGBlockSet.getBlockSets().forEach(set -> {
            generateRecipes(recipeOutput, set.getBlockFamily(), FeatureFlagSet.of());
            if (set.getBase().defaultBlockState().getSoundType() == SoundType.STONE || set.getBase().defaultBlockState().getSoundType() == SoundType.NETHERRACK || set.getBase().defaultBlockState().getSoundType() == SoundType.MUD_BRICKS)
                generateFamilyStoneCutterRecipes(recipeOutput, set);
        });

        BWGSandSet.getSandSets().forEach(set -> {
            twoByTwoPacker(recipeOutput, RecipeCategory.BUILDING_BLOCKS, set.getSandstone(), set.getSand());
            chiseled(recipeOutput, RecipeCategory.BUILDING_BLOCKS, set.getChiseledSandstone(), set.getSandstoneSlab());
            slabBuilder(RecipeCategory.BUILDING_BLOCKS, set.getSandstoneSlab(), Ingredient.of(set.getSandstone(), set.getChiseledSandstone()))
                    .unlockedBy(getHasName(set.getSandstone()), has(set.getSandstone()))
                    .unlockedBy(getHasName(set.getChiseledSandstone()), has(set.getChiseledSandstone()))
                    .save(recipeOutput);
            stairBuilder(set.getSandstoneStairs(), Ingredient.of(set.getSandstone(), set.getChiseledSandstone(), set.getCutSandstone()))
                    .unlockedBy(getHasName(set.getSandstone()), has(set.getSandstone()))
                    .unlockedBy(getHasName(set.getChiseledSandstone()), has(set.getChiseledSandstone()))
                    .unlockedBy(getHasName(set.getCutSandstone()), has(set.getCutSandstone()))
                    .save(recipeOutput);
            cut(recipeOutput, RecipeCategory.BUILDING_BLOCKS, set.getCutSandstone(), set.getSandstone());
            wall(recipeOutput, RecipeCategory.DECORATIONS, set.getSandstoneWall(), set.getSandstone());
            stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, set.getCutSandstone(), set.getSandstone());
            stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, set.getSandstoneSlab(), set.getSandstone(), 2);
            stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, set.getCutSandstoneSlab(), set.getSandstone(), 2);
            stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, set.getCutSandstoneSlab(), set.getCutSandstone(), 2);
            stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, set.getSandstoneStairs(), set.getSandstone());
            stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS, set.getSandstoneWall(), set.getSandstone());
            stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, set.getChiseledSandstone(), set.getSandstone());
            stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, set.getSmoothSandstoneSlab(), set.getSandstone(), 2);
            stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, set.getSmoothSandstoneStairs(), set.getSandstone());
            SimpleCookingRecipeBuilder.smelting(Ingredient.of(set.getSandstone()), RecipeCategory.BUILDING_BLOCKS, set.getSmoothSandstone().asItem(), 0.1F, 200)
                    .unlockedBy(getHasName(set.getSandstone()), has(set.getSandstone()))
                    .save(recipeOutput);
        });

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(BWGItems.YUCCA_FRUIT.get()), RecipeCategory.FOOD, BWGItems.COOKED_YUCCA_FRUIT.get(), 0.35F, 200)
                .unlockedBy(getHasName(BWGItems.YUCCA_FRUIT.get()), has(BWGItems.YUCCA_FRUIT.get()))
                .save(recipeOutput, BiomesWeveGone.id("cooked_yucca_fruit_from_smelting"));

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(BWGItems.YUCCA_FRUIT.get()), RecipeCategory.FOOD, BWGItems.COOKED_YUCCA_FRUIT.get(), 0.35F, 600)
                .unlockedBy(getHasName(BWGItems.YUCCA_FRUIT.get()), has(BWGItems.YUCCA_FRUIT.get()))
                .save(recipeOutput, BiomesWeveGone.id("cooked_yucca_fruit_from_camfire"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(BWGItems.YUCCA_FRUIT.get()), RecipeCategory.FOOD, BWGItems.COOKED_YUCCA_FRUIT.get(), 0.35F, 100)
                .unlockedBy(getHasName(BWGItems.YUCCA_FRUIT.get()), has(BWGItems.YUCCA_FRUIT.get()))
                .save(recipeOutput, BiomesWeveGone.id("cooked_yucca_fruit_from_smoker"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(BWGItems.ODDION_BULB.get()), RecipeCategory.FOOD, BWGItems.COOKED_ODDION_BULB.get(), 0.35F, 200)
                .unlockedBy(getHasName(BWGItems.ODDION_BULB.get()), has(BWGItems.ODDION_BULB.get()))
                .save(recipeOutput, BiomesWeveGone.id("cooked_oddion_bulb_from_smelting"));

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(BWGItems.ODDION_BULB.get()), RecipeCategory.FOOD, BWGItems.COOKED_ODDION_BULB.get(), 0.35F, 600)
                .unlockedBy(getHasName(BWGItems.ODDION_BULB.get()), has(BWGItems.ODDION_BULB.get()))
                .save(recipeOutput, BiomesWeveGone.id("cooked_oddion_bulb_from_campfire"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(BWGItems.ODDION_BULB.get()), RecipeCategory.FOOD, BWGItems.COOKED_ODDION_BULB.get(), 0.35F, 100)
                .unlockedBy(getHasName(BWGItems.ODDION_BULB.get()), has(BWGItems.ODDION_BULB.get()))
                .save(recipeOutput, BiomesWeveGone.id("cooked_oddion_bulb_from_smoker"));

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, BWGItems.ALLIUM_ODDION_SOUP.get())
                .define('#', BWGItems.COOKED_ODDION_BULB.get())
                .define('X', Items.BOWL)
                .pattern("##")
                .pattern("X ")
                .unlockedBy(getHasName(BWGItems.COOKED_ODDION_BULB.get()), has(BWGItems.COOKED_ODDION_BULB.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, BWGItems.BLOOMING_ODDION.get())
                .define('#', Items.EGG)
                .define('X', Items.WHEAT)
                .define('Y', BWGItems.COOKED_ODDION_BULB.get())
                .pattern("X#X")
                .pattern(" Y ")
                .unlockedBy(getHasName(BWGItems.COOKED_ODDION_BULB.get()), has(BWGItems.COOKED_ODDION_BULB.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BREWING, BWGItems.ALOE_VERA_JUICE.get(), 4)
                .requires(BWGBlocks.ALOE_VERA.get())
                .requires(Items.GLASS_BOTTLE, 4)
                .unlockedBy(getHasName(BWGBlocks.ALOE_VERA.get()), has(BWGBlocks.ALOE_VERA.get()))
                .save(recipeOutput);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(BWGItems.WHITE_PUFFBALL_CAP.get()), RecipeCategory.FOOD, BWGItems.COOKED_WHITE_PUFFBALL_CAP.get(), 0.35F, 200)
                .unlockedBy(getHasName(BWGItems.WHITE_PUFFBALL_CAP.get()), has(BWGItems.WHITE_PUFFBALL_CAP.get()))
                .save(recipeOutput);

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(BWGItems.WHITE_PUFFBALL_CAP.get()), RecipeCategory.FOOD, BWGItems.COOKED_WHITE_PUFFBALL_CAP.get(), 0.35F, 600)
                .unlockedBy(getHasName(BWGItems.WHITE_PUFFBALL_CAP.get()), has(BWGItems.WHITE_PUFFBALL_CAP.get()))
                .save(recipeOutput, BiomesWeveGone.id("cooked_white_puffball_cap_from_campfire"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(BWGItems.WHITE_PUFFBALL_CAP.get()), RecipeCategory.FOOD, BWGItems.COOKED_WHITE_PUFFBALL_CAP.get(), 0.35F, 100)
                .unlockedBy(getHasName(BWGItems.WHITE_PUFFBALL_CAP.get()), has(BWGItems.WHITE_PUFFBALL_CAP.get()))
                .save(recipeOutput, BiomesWeveGone.id("cooked_white_puffball_cap_from_smoker"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, BWGItems.WHITE_PUFFBALL_STEW.get())
                .requires(Items.BOWL)
                .requires(BWGItems.COOKED_WHITE_PUFFBALL_CAP.get(), 2)
                .unlockedBy(getHasName(BWGItems.COOKED_WHITE_PUFFBALL_CAP.get()), has(BWGItems.COOKED_WHITE_PUFFBALL_CAP.get()))
                .save(recipeOutput);

        threeByThreePacker(recipeOutput, RecipeCategory.BUILDING_BLOCKS, BWGBlocks.PACKED_BLACK_ICE.get(), BWGBlocks.BLACK_ICE.get());
        threeByThreePacker(recipeOutput, RecipeCategory.BUILDING_BLOCKS, BWGBlocks.PACKED_BOREALIS_ICE.get(), BWGBlocks.BOREALIS_ICE.get());

        dyeTagRecipe(recipeOutput, Items.BLACK_DYE, BWGItemTags.MAKES_BLACK_DYE);
        dyeTagRecipe(recipeOutput, Items.BLUE_DYE, BWGItemTags.MAKES_BLUE_DYE);
        //dyeTagRecipe(recipeOutput, Items.BROWN_DYE, BWGItemTags.MAKES_BROWN_DYE);
        dyeTagRecipe(recipeOutput, Items.CYAN_DYE, BWGItemTags.MAKES_CYAN_DYE);
        //dyeTagRecipe(recipeOutput, Items.GRAY_DYE, BWGItemTags.MAKES_GRAY_DYE);
        dyeTagRecipe(recipeOutput, Items.GREEN_DYE, BWGItemTags.MAKES_GREEN_DYE);
        //dyeTagRecipe(recipeOutput, Items.LIGHT_BLUE_DYE, BWGItemTags.MAKES_LIGHT_BLUE_DYE);
        //dyeTagRecipe(recipeOutput, Items.LIGHT_GRAY_DYE, BWGItemTags.MAKES_LIGHT_GRAY_DYE);
        //dyeTagRecipe(recipeOutput, Items.LIME_DYE, BWGItemTags.MAKES_LIME_DYE);
        dyeTagRecipe(recipeOutput, Items.MAGENTA_DYE, BWGItemTags.MAKES_MAGENTA_DYE);
        dyeTagRecipe(recipeOutput, Items.ORANGE_DYE, BWGItemTags.MAKES_ORANGE_DYE);
        dyeTagRecipe(recipeOutput, Items.PURPLE_DYE, BWGItemTags.MAKES_PURPLE_DYE);
        dyeTagRecipe(recipeOutput, Items.RED_DYE, BWGItemTags.MAKES_RED_DYE);
        dyeTagRecipe(recipeOutput, Items.WHITE_DYE, BWGItemTags.MAKES_WHITE_DYE);
        dyeTagRecipe(recipeOutput, Items.YELLOW_DYE, BWGItemTags.MAKES_YELLOW_DYE);
        dyeTagRecipe(recipeOutput, Items.PINK_DYE, BWGItemTags.MAKES_PINK_DYE);

        twoByTwoPackertoFourWithStoneCutting(recipeOutput, RecipeCategory.BUILDING_BLOCKS, BWGBlocks.RED_ROCK_BRICKS_SET.getBase(), BWGBlocks.RED_ROCK_SET.getBase());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BWGBlocks.MOSSY_RED_ROCK_BRICKS_SET.getBase())
                .requires(BWGBlocks.RED_ROCK_BRICKS_SET.getBase())
                .requires(Items.VINE)
                .group("mossy_red_rock_bricks")
                .unlockedBy(getHasName(Items.VINE), has(Items.VINE))
                .save(recipeOutput, getConversionRecipeName(BWGBlocks.MOSSY_RED_ROCK_BRICKS_SET.getBase(), Items.VINE));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BWGBlocks.MOSSY_RED_ROCK_BRICKS_SET.getBase())
                .requires(BWGBlocks.RED_ROCK_BRICKS_SET.getBase())
                .requires(Items.MOSS_BLOCK)
                .group("mossy_red_rock_bricks")
                .unlockedBy(getHasName(Items.MOSS_BLOCK), has(Items.MOSS_BLOCK))
                .save(recipeOutput, getConversionRecipeName(BWGBlocks.MOSSY_RED_ROCK_BRICKS_SET.getBase(), Items.MOSS_BLOCK));
        chiseledBuilder(RecipeCategory.BUILDING_BLOCKS, BWGBlocks.CHISELED_RED_ROCK_BRICKS_SET.getBase(), Ingredient.of(BWGBlocks.RED_ROCK_BRICKS_SET.getSlab()))
                .unlockedBy("has_tag", has(BWGItemTags.RED_ROCK_BRICKS))
                .save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BWGBlocks.ROCKY_STONE_SET.getBase(), 2)
                .requires(Items.COBBLESTONE)
                .requires(Items.STONE)
                .unlockedBy(getHasName(Items.COBBLESTONE), has(Items.COBBLESTONE))
                .unlockedBy(getHasName(Items.STONE), has(Items.STONE))
                .save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BWGBlocks.MOSSY_STONE_SET.getBase())
                .requires(Items.STONE)
                .requires(Items.VINE)
                .group("mossy_stone")
                .unlockedBy(getHasName(Items.VINE), has(Items.VINE))
                .save(recipeOutput, getConversionRecipeName(BWGBlocks.MOSSY_STONE_SET.getBase(), Items.VINE));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BWGBlocks.MOSSY_STONE_SET.getBase())
                .requires(Items.STONE)
                .requires(Items.MOSS_BLOCK)
                .group("mossy_stone")
                .unlockedBy(getHasName(Items.MOSS_BLOCK), has(Items.MOSS_BLOCK))
                .save(recipeOutput, getConversionRecipeName(BWGBlocks.MOSSY_STONE_SET.getBase(), Items.MOSS_BLOCK));


        smeltingResultFromBase(recipeOutput, BWGBlocks.DACITE_SET.getBase(), BWGBlocks.DACITE_COBBLESTONE_SET.getBase());
        twoByTwoPackertoFourWithStoneCutting(recipeOutput, RecipeCategory.BUILDING_BLOCKS, BWGBlocks.DACITE_BRICKS_SET.getBase(), BWGBlocks.DACITE_SET.getBase());
        twoByTwoPackertoFourWithStoneCutting(recipeOutput, RecipeCategory.BUILDING_BLOCKS, BWGBlocks.DACITE_TILE_SET.getBase(), BWGBlocks.DACITE_BRICKS_SET.getBase());
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BWGBlocks.DACITE_PILLAR.get())
                .define('#', BWGBlocks.DACITE_SET.getBase())
                .pattern("#")
                .pattern("#")
                .unlockedBy(getHasName(BWGBlocks.DACITE_SET.getBase()), has(BWGBlocks.DACITE_SET.getBase()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BWGBlocks.WINDSWEPT_SANDSTONE_PILLAR.get())
                .define('X', BWGBlocks.WINDSWEPT_SAND_SET.getSandstone())
                .pattern("X")
                .pattern("X")
                .unlockedBy(getHasName(BWGBlocks.WINDSWEPT_SAND_SET.getSandstone()), has(BWGBlocks.WINDSWEPT_SAND_SET.getSandstone()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Items.GOLDEN_APPLE)
                .define('#', Items.GOLD_INGOT)
                .define('X', BWGItems.GREEN_APPLE.get())
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .save(recipeOutput, BiomesWeveGone.id("golden_apple_from_green_apple"));

        sandToGlass(recipeOutput, BWGBlocks.BLACK_SAND_SET, Items.BLACK_STAINED_GLASS);
        sandToGlass(recipeOutput, BWGBlocks.BLUE_SAND_SET, Items.BLUE_STAINED_GLASS);
        sandToGlass(recipeOutput, BWGBlocks.PINK_SAND_SET, Items.PINK_STAINED_GLASS);
        sandToGlass(recipeOutput, BWGBlocks.PURPLE_SAND_SET, Items.PURPLE_STAINED_GLASS);
        sandToGlass(recipeOutput, BWGBlocks.WHITE_SAND_SET, Items.WHITE_STAINED_GLASS);
	    dyeAbleRecipe(recipeOutput, BWGBlocks.BLACK_SAND_SET.getSand(), Items.SAND, Tags.Items.DYES_BLACK);
	    dyeAbleRecipe(recipeOutput, BWGBlocks.BLUE_SAND_SET.getSand(), Items.SAND, Tags.Items.DYES_BLUE);
	    dyeAbleRecipe(recipeOutput, BWGBlocks.PINK_SAND_SET.getSand(), Items.SAND, Tags.Items.DYES_PINK);
	    dyeAbleRecipe(recipeOutput, BWGBlocks.PURPLE_SAND_SET.getSand(), Items.SAND, Tags.Items.DYES_PURPLE);
	    dyeAbleRecipe(recipeOutput, BWGBlocks.WHITE_SAND_SET.getSand(), Items.SAND, Tags.Items.DYES_WHITE);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, BWGItems.GREEN_APPLE_PIE.get())
                .requires(BWGItems.GREEN_APPLE.get())
                .requires(Items.SUGAR)
                .requires(Items.EGG)
                .unlockedBy(getHasName(BWGItems.GREEN_APPLE.get()), has(BWGItems.GREEN_APPLE.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, BWGItems.BLUEBERRY_PIE.get())
                .requires(BWGItems.BLUEBERRIES.get())
                .requires(Items.SUGAR)
                .requires(Items.EGG)
                .unlockedBy(getHasName(BWGItems.BLUEBERRIES.get()), has(BWGItems.BLUEBERRIES.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BWGBlocks.CATTAIL_THATCH.get(), 4)
                .define('#', BWGItems.CATTAIL_SPROUT.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(BWGItems.CATTAIL_SPROUT.get()), has(BWGItems.CATTAIL_SPROUT.get()))
                .save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, BWGBlocks.CATTAIL_THATCH_SLAB.get(), BWGBlocks.CATTAIL_THATCH.get());
        stairBuilder(BWGBlocks.CATTAIL_THATCH_STAIRS.get(), Ingredient.of(BWGBlocks.CATTAIL_THATCH.get()))
                .unlockedBy(getHasName(BWGBlocks.CATTAIL_THATCH.get()), has(BWGBlocks.CATTAIL_THATCH.get()))
                .save(recipeOutput);
        carpet(recipeOutput, BWGBlocks.CATTAIL_THATCH_CARPET.get(), BWGBlocks.CATTAIL_THATCH.get());

        nineBlockStorageRecipes(recipeOutput, RecipeCategory.DECORATIONS, Items.ALLIUM, RecipeCategory.DECORATIONS, BWGBlocks.ALLIUM_PETAL_BLOCK.get());
        nineBlockStorageRecipes(recipeOutput, RecipeCategory.DECORATIONS, BWGBlocks.PINK_ALLIUM.getBlock(), RecipeCategory.DECORATIONS, BWGBlocks.PINK_ALLIUM_PETAL_BLOCK.get());
        nineBlockStorageRecipes(recipeOutput, RecipeCategory.DECORATIONS, BWGBlocks.WHITE_ALLIUM.getBlock(), RecipeCategory.DECORATIONS, BWGBlocks.WHITE_ALLIUM_PETAL_BLOCK.get());
        nineBlockStorageRecipes(recipeOutput, RecipeCategory.DECORATIONS, BWGBlocks.ROSE.getBlock(), RecipeCategory.DECORATIONS, BWGBlocks.ROSE_PETAL_BLOCK.get());

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Items.PURPLE_WOOL)
                .requires(BWGBlocks.ALLIUM_PETAL_BLOCK.get(), 9)
                .unlockedBy(getHasName(BWGBlocks.ALLIUM_PETAL_BLOCK.get()), has(BWGBlocks.ALLIUM_PETAL_BLOCK.get()))
                .save(recipeOutput, BiomesWeveGone.id("purple_wool_from_allium_petal_block"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Items.WHITE_WOOL)
                .requires(BWGBlocks.WHITE_ALLIUM_PETAL_BLOCK.get(), 9)
                .unlockedBy(getHasName(BWGBlocks.WHITE_ALLIUM_PETAL_BLOCK.get()), has(BWGBlocks.WHITE_ALLIUM_PETAL_BLOCK.get()))
                .save(recipeOutput, BiomesWeveGone.id("white_wool_from_white_allium_petal_block"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Items.PINK_WOOL)
                .requires(BWGBlocks.PINK_ALLIUM_PETAL_BLOCK.get(), 9)
                .unlockedBy(getHasName(BWGBlocks.PINK_ALLIUM_PETAL_BLOCK.get()), has(BWGBlocks.PINK_ALLIUM_PETAL_BLOCK.get()))
                .save(recipeOutput, BiomesWeveGone.id("pink_wool_from_pink_allium_petal_block"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Items.RED_WOOL)
                .requires(BWGBlocks.ROSE_PETAL_BLOCK.get(), 9)
                .unlockedBy(getHasName(BWGBlocks.ROSE_PETAL_BLOCK.get()), has(BWGBlocks.ROSE_PETAL_BLOCK.get()))
                .save(recipeOutput, BiomesWeveGone.id("red_wool_from_rose_petal_block"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BWGBlocks.SANDY_DIRT.get(), 4)
                .requires(Items.SAND, 2)
                .requires(Items.DIRT, 2)
                .unlockedBy(getHasName(Items.SAND), has(Items.SAND))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BWGBlocks.FORAGERS_TABLE.get())
                .define('X', ItemTags.PLANKS)
                .define('#', Tags.Items.MUSHROOMS)
                .pattern("##")
                .pattern("XX")
                .unlockedBy("has_mushrooms", has(Tags.Items.MUSHROOMS))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BWGBlocks.PACKED_PALE_MUD.get())
                .requires(BWGBlocks.PALE_MUD.get())
                .requires(Items.WHEAT)
                .unlockedBy(getHasName(BWGBlocks.PALE_MUD.get()), has(BWGBlocks.PALE_MUD.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BWGBlocks.PALE_MUD_BRICKS_SET.getBase(), 4)
                .define('#', BWGBlocks.PACKED_PALE_MUD.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(BWGBlocks.PACKED_PALE_MUD.get()), has(BWGBlocks.PACKED_PALE_MUD.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BWGItems.BLUE_GLOWCANE_POWDER.get(), 3)
                .requires(BWGItems.BLUE_GLOWCANE_SHOOT.get())
                .unlockedBy(getHasName(BWGItems.BLUE_GLOWCANE_SHOOT.get()), has(BWGItems.BLUE_GLOWCANE_SHOOT.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BWGItems.GREEN_GLOWCANE_POWDER.get(), 3)
                .requires(BWGItems.GREEN_GLOWCANE_SHOOT.get())
                .unlockedBy(getHasName(BWGItems.GREEN_GLOWCANE_SHOOT.get()), has(BWGItems.GREEN_GLOWCANE_SHOOT.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BWGItems.RED_GLOWCANE_POWDER.get(), 3)
                .requires(BWGItems.RED_GLOWCANE_SHOOT.get())
                .unlockedBy(getHasName(BWGItems.RED_GLOWCANE_SHOOT.get()), has(BWGItems.RED_GLOWCANE_SHOOT.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BWGItems.YELLOW_GLOWCANE_POWDER.get(), 3)
                .requires(BWGItems.YELLOW_GLOWCANE_SHOOT.get())
                .unlockedBy(getHasName(BWGItems.YELLOW_GLOWCANE_SHOOT.get()), has(BWGItems.YELLOW_GLOWCANE_SHOOT.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BWGBlocks.BLUE_GLOW_BOTTLE.get())
                .requires(BWGItems.BLUE_GLOWCANE_POWDER.get(), 4)
                .requires(Items.GLASS_BOTTLE)
                .unlockedBy(getHasName(BWGItems.BLUE_GLOWCANE_POWDER.get()), has(BWGItems.BLUE_GLOWCANE_POWDER.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BWGBlocks.GREEN_GLOW_BOTTLE.get())
                .requires(BWGItems.GREEN_GLOWCANE_POWDER.get(), 4)
                .requires(Items.GLASS_BOTTLE)
                .unlockedBy(getHasName(BWGItems.GREEN_GLOWCANE_POWDER.get()), has(BWGItems.GREEN_GLOWCANE_POWDER.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BWGBlocks.RED_GLOW_BOTTLE.get())
                .requires(BWGItems.RED_GLOWCANE_POWDER.get(), 4)
                .requires(Items.GLASS_BOTTLE)
                .unlockedBy(getHasName(BWGItems.RED_GLOWCANE_POWDER.get()), has(BWGItems.RED_GLOWCANE_POWDER.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BWGBlocks.YELLOW_GLOW_BOTTLE.get())
                .requires(BWGItems.YELLOW_GLOWCANE_POWDER.get(), 4)
                .requires(Items.GLASS_BOTTLE)
                .unlockedBy(getHasName(BWGItems.YELLOW_GLOWCANE_POWDER.get()), has(BWGItems.YELLOW_GLOWCANE_POWDER.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.PUMPKIN_PIE)
                .requires(BWGBlocks.PALE_PUMPKIN.get())
                .requires(Items.SUGAR)
                .requires(Items.EGG)
                .unlockedBy(getHasName(BWGBlocks.CARVED_PALE_PUMPKIN.get()), has(BWGBlocks.CARVED_PALE_PUMPKIN.get()))
                .unlockedBy(getHasName(BWGBlocks.PALE_PUMPKIN.get()), has(BWGBlocks.PALE_PUMPKIN.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BWGBlocks.PALE_JACK_O_LANTERN.get())
                .define('A', BWGBlocks.CARVED_PALE_PUMPKIN.get())
                .define('B', Items.SOUL_TORCH)
                .pattern("A")
                .pattern("B")
                .unlockedBy(getHasName(BWGBlocks.CARVED_PALE_PUMPKIN.get()), has(BWGBlocks.CARVED_PALE_PUMPKIN.get()))
                .save(recipeOutput, BiomesWeveGone.id("pale_jack_o_lantern_from_soul_torch"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BWGBlocks.PALE_JACK_O_LANTERN.get())
                .define('A', BWGBlocks.CARVED_PALE_PUMPKIN.get())
                .define('B', BWGItems.SOUL_FRUIT.get())
                .pattern("A")
                .pattern("B")
                .unlockedBy(getHasName(BWGBlocks.CARVED_PALE_PUMPKIN.get()), has(BWGBlocks.CARVED_PALE_PUMPKIN.get()))
                .save(recipeOutput, BiomesWeveGone.id("pale_jack_o_lantern_from_soul_fruit"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BWGItems.PALE_PUMPKIN_SEEDS.get(), 4).requires(BWGBlocks.PALE_PUMPKIN.get()).unlockedBy(getHasName(BWGBlocks.PALE_PUMPKIN.get()), has(BWGBlocks.PALE_PUMPKIN.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.SOUL_TORCH, 4)
                .define('X', Ingredient.of(Items.COAL, Items.CHARCOAL))
                .define('#', Items.STICK)
                .define('S', BWGItems.SOUL_FRUIT.get())
                .pattern("X")
                .pattern("#")
                .pattern("S")
                .unlockedBy(getHasName(BWGItems.SOUL_FRUIT.get()), has(BWGItems.SOUL_FRUIT.get()))
                .save(recipeOutput, BiomesWeveGone.id("soul_torch_from_soul_fruit"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BWGBlocks.PALE_MUD.get(), 8)
                .define('X', BWGItems.SOUL_FRUIT.get())
                .define('#', Items.MUD)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .unlockedBy(getHasName(BWGItems.SOUL_FRUIT.get()), has(BWGItems.SOUL_FRUIT.get()))
                .save(recipeOutput);
    }

    private static void sandToGlass(RecipeOutput finishedRecipeConsumer, BWGSandSet set, Item glass) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(set.getSand()), RecipeCategory.BUILDING_BLOCKS, glass, 0.1F, 200)
                .unlockedBy(getHasName(set.getSand()), has(set.getSand()))
                .save(finishedRecipeConsumer, BiomesWeveGone.id(getHasName(glass).replace("has_", "") + "_from_" + getHasName(set.getSand()).replace("has_", "")));
    }

    private static void generateFamilyStoneCutterRecipes(RecipeOutput finishedRecipeConsumer, BWGBlockSet set) {
        stonecutterResultFromBase(finishedRecipeConsumer, RecipeCategory.BUILDING_BLOCKS, set.getSlab(), set.getBase(),2);
        stonecutterResultFromBase(finishedRecipeConsumer, RecipeCategory.BUILDING_BLOCKS, set.getStairs(), set.getBase());
        stonecutterResultFromBase(finishedRecipeConsumer, RecipeCategory.DECORATIONS, set.getWall(), set.getBase());
    }

    private static void twoByTwoPackertoFourWithStoneCutting(RecipeOutput finishedRecipeConsumer, RecipeCategory category, ItemLike packed, ItemLike unpacked) {
        ShapedRecipeBuilder.shaped(category, packed, 4)
                .define('#', unpacked)
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(unpacked), has(unpacked))
                .save(finishedRecipeConsumer);
        stonecutterResultFromBase(finishedRecipeConsumer, category, packed, unpacked);
    }

	private static void dyeAbleRecipe(RecipeOutput finishedRecipeConsumer, ItemLike item, ItemLike ingredient, TagKey<Item> dye) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, item, 8)
				.define('#', dye)
				.define('X', ingredient)
				.pattern("XXX")
				.pattern("X#X")
				.pattern("XXX")
				.unlockedBy(getHasName(ingredient), has(ingredient))
				.save(finishedRecipeConsumer);
	}

    private static void dyeTagRecipe(RecipeOutput finishedRecipeConsumer, Item dye, TagKey<Item> tag) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, dye)
                .requires(tag)
                .unlockedBy(tag.toString(), has(tag))
                .group(getHasName(dye).replace("has_", ""))
                .save(finishedRecipeConsumer, BiomesWeveGone.id(getHasName(dye).replace("has_", "")+ "_from_bwg_tag"));
    }
}
