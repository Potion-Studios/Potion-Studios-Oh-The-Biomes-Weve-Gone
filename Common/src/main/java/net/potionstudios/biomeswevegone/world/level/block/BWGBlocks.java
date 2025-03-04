package net.potionstudios.biomeswevegone.world.level.block;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.PlatformHandler;
import net.potionstudios.biomeswevegone.tags.BWGBlockTags;
import net.potionstudios.biomeswevegone.world.item.BWGItems;
import net.potionstudios.biomeswevegone.world.level.block.custom.*;
import net.potionstudios.biomeswevegone.world.level.block.plants.PottedBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.bush.*;
import net.potionstudios.biomeswevegone.world.level.block.plants.cactus.BarrelCactusBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.cactus.CarvedBarrelCactusBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.flower.*;
import net.potionstudios.biomeswevegone.world.level.block.plants.tree.branch.TreeBranchBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.tree.fruit.BWGFruitBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.tree.fruit.SoulFruitBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.tree.grower.BWGTreeGrowers;
import net.potionstudios.biomeswevegone.world.level.block.plants.vegetation.*;
import net.potionstudios.biomeswevegone.world.level.block.plants.vegetation.cattail.CattailPlantBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.vegetation.cattail.CattailSproutBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.vegetation.cattail.FluorescentCattailPlantBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.vegetation.pumpkin.PaleCarvedPumpkinBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.vegetation.pumpkin.PalePumpkinBlock;
import net.potionstudios.biomeswevegone.world.level.block.sand.BWGQuickSand;
import net.potionstudios.biomeswevegone.world.level.block.sand.BWGSandSet;
import net.potionstudios.biomeswevegone.world.level.block.set.BWGBlockSet;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWood;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.configured.BWGOverworldVegetationConfiguredFeatures;

import java.util.ArrayList;
import java.util.function.Supplier;

/**
 * The blocks for Oh The Biomes We've Gone.
 * This class is used for registering blocks and blockitems.
 * @see Registries#BLOCK
 * @author Joseph T. McQuigg
 */
@SuppressWarnings("unused")
public class BWGBlocks {

    public static final ArrayList<Supplier<? extends Block>> cubeAllBlocks = new ArrayList<>();

    public static final ArrayList<Supplier<? extends Block>> BLOCKS = new ArrayList<>();
    public static final ArrayList<Supplier<? extends Item>> BLOCK_ITEMS = new ArrayList<>();

    public static final Supplier<Block> FORAGERS_TABLE = registerBlockItem("foragers_table", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.FLETCHING_TABLE).setId(key("foragers_table"))));

    public static final Supplier<Block> PEAT = registerBasicBlockWithItem("peat", BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT));
    public static final Supplier<Block> SANDY_DIRT = registerBasicBlockWithItem("sandy_dirt", BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT));
    public static final Supplier<DirtPathBlock> SANDY_DIRT_PATH = registerBlockItem("sandy_dirt_path", () -> new BWGDirtPathBlock(SANDY_DIRT, "sandy_dirt_path"));
    public static final Supplier<BWGFarmLandBlock> SANDY_FARMLAND = registerBlockItem("sandy_farmland", PlatformHandler.PLATFORM_HANDLER.bwgFarmLandBlock(SANDY_DIRT, "sandy_farmland"));

    public static final Supplier<Block> LUSH_DIRT = registerBasicBlockWithItem("lush_dirt", BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT));
    public static final Supplier<BWGSpreadableBlock> LUSH_GRASS_BLOCK = registerBlockItem("lush_grass_block", () -> new BWGSpreadableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK).setId(key("lush_grass_block")), LUSH_DIRT));
    public static final Supplier<DirtPathBlock> LUSH_DIRT_PATH = registerBlockItem("lush_dirt_path", () -> new BWGDirtPathBlock(LUSH_DIRT, "lush_dirt_path"));
    public static final Supplier<BWGFarmLandBlock> LUSH_FARMLAND = registerBlockItem("lush_farmland", PlatformHandler.PLATFORM_HANDLER.bwgFarmLandBlock(LUSH_DIRT, "lush_farmland"));

    public static final BWGSandSet BLACK_SAND_SET = new BWGSandSet("black", 5197647);
    public static final BWGSandSet WHITE_SAND_SET = new BWGSandSet("white", 15395562);
    public static final BWGSandSet BLUE_SAND_SET = new BWGSandSet("blue", 13559021);
    public static final BWGSandSet PURPLE_SAND_SET = new BWGSandSet("purple", 12887002);
    public static final BWGSandSet PINK_SAND_SET = new BWGSandSet("pink", 15585004);
    public static final BWGSandSet WINDSWEPT_SAND_SET = new BWGSandSet("windswept", 15585004);
    public static final Supplier<RotatedPillarBlock> WINDSWEPT_SANDSTONE_PILLAR = registerBlockItem("windswept_sandstone_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE).mapColor(MapColor.COLOR_YELLOW).setId(key("windswept_sandstone_pillar"))));
    public static final Supplier<ColoredFallingBlock> CRACKED_SAND = registerCubeAllBlockItem("cracked_sand", () -> new ColoredFallingBlock(new ColorRGBA(14406560), BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).setId(key("cracked_sand"))));
    public static final Supplier<ColoredFallingBlock> CRACKED_RED_SAND = registerCubeAllBlockItem("cracked_red_sand", () -> new ColoredFallingBlock(new ColorRGBA(11098145), BlockBehaviour.Properties.ofFullCopy(Blocks.RED_SAND).setId(key("cracked_red_sand"))));

    public static final Supplier<BWGQuickSand> QUICKSAND = registerCubeAllBlockItem("quicksand", () -> new BWGQuickSand(16777215, "quicksand"));
    public static final Supplier<BWGQuickSand> RED_QUICKSAND = registerCubeAllBlockItem("red_quicksand", () -> new BWGQuickSand(11098145, "red_quicksand"));

    public static final Supplier<IceBlock> BLACK_ICE = registerBlockItem("black_ice", () -> new IceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ICE).setId(key("black_ice"))));
    public static final Supplier<IceBlock> PACKED_BLACK_ICE = registerCubeAllBlockItem("packed_black_ice", () -> new IceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_ICE).setId(key("packed_black_ice"))));

    public static final Supplier<IceBlock> BOREALIS_ICE = registerBlockItem("borealis_ice", () -> new IceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ICE).lightLevel(state -> 10).setId(key("borealis_ice"))));
    public static final Supplier<IceBlock> PACKED_BOREALIS_ICE = registerBlockItem("packed_borealis_ice", () -> new IceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_ICE).lightLevel(state -> 10).setId(key("packed_borealis_ice"))));

    public static final Supplier<BWGBerryBush> BLUEBERRY_BUSH = registerBlock("blueberry_bush", () -> new BWGBerryBush(() -> BWGItems.BLUEBERRIES, true, "blueberry_bush"));
    public static final Supplier<FloweringBushBlock> FLOWERING_JACARANDA_BUSH = registerBlockItem("flowering_jacaranda_bush", () -> new FloweringBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA).setId(key("flowering_jacaranda_bush"))));
    public static final PottedBlock JACARANDA_BUSH = createPottedVariant("jacaranda_bush",  () -> new FlowerableBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA).setId(key("jacaranda_bush")), FLOWERING_JACARANDA_BUSH));
    public static final Supplier<FloweringBushBlock> FLOWERING_INDIGO_JACARANDA_BUSH = registerBlockItem("flowering_indigo_jacaranda_bush", () -> new FloweringBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA).setId(key("flowering_indigo_jacaranda_bush"))));
    public static final PottedBlock INDIGO_JACARANDA_BUSH = createPottedVariant("indigo_jacaranda_bush", () -> new FlowerableBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA).setId(key("indigo_jacaranda_bush")), FLOWERING_INDIGO_JACARANDA_BUSH));
    public static final PottedBlock HYDRANGEA_BUSH = createPottedVariant("hydrangea_bush", HydrangeaBushBlock::new);
    public static final Supplier<BushBlock> HYDRANGEA_HEDGE = registerBlockItem("hydrangea_hedge", HydrangeaHedgeBlock::new);
    public static final PottedBlock SHRUB = createPottedVariant("shrub", () -> new ShrubBlock(null, "shrub"));
    public static final PottedBlock FIRECRACKER_FLOWER_BUSH = createPottedVariant("firecracker_flower_bush", () -> new ShrubBlock(BWGTreeGrowers.FIRECRACKER, "firecracker_flower_bush"));
    public static final Supplier<OddionCrop> ODDION_CROP = registerBlock("oddion_crop", OddionCrop::new);

    public static final Supplier<HugeMushroomBlock> GREEN_MUSHROOM_BLOCK = registerBlockItem("green_mushroom_block", () -> new HugeMushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).sound(SoundType.STEM).strength(0.2F).setId(key("green_mushroom_block"))));
    public static final Supplier<HugeMushroomBlock> WEEPING_MILKCAP_MUSHROOM_BLOCK = registerBlockItem("weeping_milkcap_mushroom_block", () -> new HugeMushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).sound(SoundType.STEM).strength(0.2F).setId(key("weeping_milkcap_mushroom_block"))));
    public static final Supplier<HugeMushroomBlock> WOOD_BLEWIT_MUSHROOM_BLOCK = registerBlockItem("wood_blewit_mushroom_block", () -> new HugeMushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).sound(SoundType.STEM).strength(0.2F).setId(key("wood_blewit_mushroom_block"))));
    public static final Supplier<HugeMushroomBlock> WHITE_MUSHROOM_STEM = registerBlockItem("white_mushroom_stem", () -> new HugeMushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).sound(SoundType.STEM).strength(0.2F).setId(key("white_mushroom_stem"))));
    public static final Supplier<HugeMushroomBlock> BROWN_MUSHROOM_STEM = registerBlockItem("brown_mushroom_stem", () -> new HugeMushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).sound(SoundType.STEM).strength(0.2F).setId(key("brown_mushroom_stem"))));

    public static final Supplier<MushroomBlock> GREEN_MUSHROOM = registerBlockItem("green_mushroom", () -> new BWGMushroomBlock(BlockBehaviour.Properties.of().noCollission().mapColor(MapColor.DIRT).sound(SoundType.STEM).strength(0.2F).setId(key("green_mushroom")), BWGBlockTags.BWG_MUSHROOM_PLACEABLE, BWGOverworldVegetationConfiguredFeatures.HUGE_GREEN_MUSHROOM1, Block.box(1.0, 0.0, 1.0, 15.0, 13.0, 15.0)));
    public static final Supplier<MushroomBlock> WEEPING_MILKCAP = registerBlockItem("weeping_milkcap", () -> new BWGMushroomBlock(BlockBehaviour.Properties.of().noCollission().mapColor(MapColor.DIRT).sound(SoundType.STEM).strength(0.2F).setId(key("weeping_milkcap")), BWGBlockTags.BWG_MUSHROOM_PLACEABLE, BWGOverworldVegetationConfiguredFeatures.HUGE_WEEPING_MILKCAP1, Block.box(3.0, 0.0, 3.0, 14.0, 6.0, 14.0)));
    public static final Supplier<MushroomBlock> WOOD_BLEWIT = registerBlockItem("wood_blewit", () -> new BWGMushroomBlock(BlockBehaviour.Properties.of().noCollission().mapColor(MapColor.DIRT).sound(SoundType.STEM).strength(0.2F).setId(key("wood_blewit")), BWGBlockTags.BWG_MUSHROOM_PLACEABLE, BWGOverworldVegetationConfiguredFeatures.HUGE_WOOD_BLEWIT1, Block.box(5.0, 0.0, 5.0, 11.0, 7.0, 11.0)));

    /** Alliums */
    public static final Supplier<TallFlowerBlock> TALL_ALLIUM = registerTallFlower("tall_allium", BWGTreeGrowers.GIANT_ALLIUM);
    public static final FlowerBlockFeature ALLIUM_FLOWER_BUSH = registerFlower("allium_flower_bush", Block.box(2.0, 0.0, 2.0, 14.0, 13.0, 14.0), TALL_ALLIUM);
    public static final Supplier<Block> ALLIUM_PETAL_BLOCK = registerBasicBlockWithItem("allium_petal_block", BlockBehaviour.Properties.ofFullCopy(Blocks.HAY_BLOCK).mapColor(MapColor.COLOR_MAGENTA).sound(SoundType.AZALEA));
    public static final FlowerBlockFeature PINK_ALLIUM = registerFlower("pink_allium", Block.box(5.0, 0.0, 5.0, 11.0, 14.0, 11.0));
    public static final Supplier<TallFlowerBlock> TALL_PINK_ALLIUM = registerTallFlower("tall_pink_allium", BWGTreeGrowers.GIANT_PINK_ALLIUM);
    public static final FlowerBlockFeature PINK_ALLIUM_FLOWER_BUSH = registerFlower("pink_allium_flower_bush", Block.box(2.0, 0.0, 2.0, 14.0, 13.0, 14.0), TALL_PINK_ALLIUM);
    public static final Supplier<Block> PINK_ALLIUM_PETAL_BLOCK = registerBasicBlockWithItem("pink_allium_petal_block", BlockBehaviour.Properties.ofFullCopy(Blocks.HAY_BLOCK).mapColor(MapColor.COLOR_PINK).sound(SoundType.AZALEA));
    public static final FlowerBlockFeature WHITE_ALLIUM = registerFlower("white_allium", Block.box(5.0, 0.0, 5.0, 11.0, 14.0, 11.0));
    public static final Supplier<TallFlowerBlock> TALL_WHITE_ALLIUM = registerTallFlower("tall_white_allium", BWGTreeGrowers.GIANT_WHITE_ALLIUM);
    public static final FlowerBlockFeature WHITE_ALLIUM_FLOWER_BUSH = registerFlower("white_allium_flower_bush", Block.box(2.0, 0.0, 2.0, 14.0, 13.0, 14.0), TALL_WHITE_ALLIUM);
    public static final Supplier<Block> WHITE_ALLIUM_PETAL_BLOCK = registerBasicBlockWithItem("white_allium_petal_block", BlockBehaviour.Properties.ofFullCopy(Blocks.HAY_BLOCK).mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.AZALEA));

    /** Glowing Pitcher Plants */
    public static final Supplier<TallFlowerBlock> CYAN_PITCHER_PLANT = registerTallGlowingFlower("cyan_pitcher_plant");
    public static final Supplier<TallFlowerBlock> MAGENTA_PITCHER_PLANT = registerTallGlowingFlower("magenta_pitcher_plant");

    /** Roses */
    public static final FlowerBlockFeature ROSE = registerFlower("rose", Block.box(5.0, 0.0, 5.0, 11.0, 12.0, 11.0));
    public static final FlowerBlockFeature OSIRIA_ROSE = registerFlower("osiria_rose", Block.box(5.0, 0.0, 5.0, 11.0, 12.0, 11.0));
    public static final FlowerBlockFeature BLACK_ROSE = registerFlower("black_rose", Block.box(5.0, 0.0, 5.0, 11.0, 12.0, 11.0));
    public static final FlowerBlockFeature CYAN_ROSE = registerFlower("cyan_rose", Block.box(5.0, 0.0, 5.0, 11.0, 12.0, 11.0));
    public static final Supplier<TallFlowerBlock> BLUE_ROSE_BUSH = registerTallFlower("blue_rose_bush");
    public static final Supplier<Block> ROSE_PETAL_BLOCK = registerBasicBlockWithItem("rose_petal_block", BlockBehaviour.Properties.ofFullCopy(Blocks.HAY_BLOCK).mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.AZALEA));

    /** Tulips */
    public static final FlowerBlockFeature CYAN_TULIP = registerFlower("cyan_tulip", Block.box(5.0, 0.0, 5.0, 11.0, 13.0, 11.0));
    public static final FlowerBlockFeature GREEN_TULIP = registerFlower("green_tulip", Block.box(5.0, 0.0, 5.0, 11.0, 13.0, 11.0));
    public static final FlowerBlockFeature MAGENTA_TULIP = registerFlower("magenta_tulip", Block.box(5.0, 0.0, 5.0, 11.0, 13.0, 11.0));
    public static final FlowerBlockFeature PURPLE_TULIP = registerFlower("purple_tulip", Block.box(5.0, 0.0, 5.0, 11.0, 13.0, 11.0));
    public static final FlowerBlockFeature YELLOW_TULIP = registerFlower("yellow_tulip", Block.box(5.0, 0.0, 5.0, 11.0, 13.0, 11.0));

    /** Amaranth */
    public static final FlowerBlockFeature AMARANTH = registerFlower("amaranth", Block.box(2.0, 0.0, 2.0, 14.0, 15.0, 14.0));
    public static final FlowerBlockFeature CYAN_AMARANTH = registerFlower("cyan_amaranth", Block.box(2.0, 0.0, 2.0, 14.0, 15.0, 14.0));
    public static final FlowerBlockFeature MAGENTA_AMARANTH = registerFlower("magenta_amaranth", Block.box(2.0, 0.0, 2.0, 14.0, 15.0, 14.0));
    public static final FlowerBlockFeature ORANGE_AMARANTH = registerFlower("orange_amaranth", Block.box(2.0, 0.0, 2.0, 14.0, 15.0, 14.0));
    public static final FlowerBlockFeature PURPLE_AMARANTH = registerFlower("purple_amaranth", Block.box(2.0, 0.0, 2.0, 14.0, 15.0, 14.0));

    /** Sages */
    public static final FlowerBlockFeature BLUE_SAGE = registerFlower("blue_sage", Block.box(4.0, 0.0, 4.0, 12.0, 13.0, 12.0));
    public static final FlowerBlockFeature PURPLE_SAGE = registerFlower("purple_sage", Block.box(4.0, 0.0, 4.0, 12.0, 13.0, 12.0));
    public static final FlowerBlockFeature WHITE_SAGE = registerFlower("white_sage", Block.box(4.0, 0.0, 4.0, 12.0, 13.0, 12.0));

    /** Daffodils */
    public static final FlowerBlockFeature DAFFODIL = registerFlower("daffodil", Block.box(5.0, 0.0, 5.0, 11.0, 12.0, 11.0));
    public static final FlowerBlockFeature PINK_DAFFODIL = registerFlower("pink_daffodil", Block.box(5.0, 0.0, 5.0, 11.0, 12.0, 11.0));
    public static final FlowerBlockFeature YELLOW_DAFFODIL = registerFlower("yellow_daffodil", Block.box(5.0, 0.0, 5.0, 11.0, 12.0, 11.0));

    /** Anemones */
    public static final FlowerBlockFeature PINK_ANEMONE = registerFlower("pink_anemone", Block.box(1.0, 0.0, 1.0, 15.0, 8.0, 15.0));
    public static final FlowerBlockFeature WHITE_ANEMONE = registerFlower("white_anemone", Block.box(1.0, 0.0, 1.0, 15.0, 8.0, 15.0));

   /** Bellflowers */
    public static final FlowerBlockFeature ALPINE_BELLFLOWER = registerFlower("alpine_bellflower", Block.box(2.0, 0.0, 2.0, 14.0, 12.0, 14.0));
    public static final FlowerBlockFeature LAZARUS_BELLFLOWER = registerFlower("lazarus_bellflower", Block.box(2.0, 0.0, 2.0, 14.0, 14.0, 14.0));

    /** Leather Flowers */
    public static final FlowerBlockFeature PEACH_LEATHER_FLOWER = registerFlower("peach_leather_flower", Block.box(5.0, 0.0, 5.0, 11.0, 16.0, 11.0));
    public static final FlowerBlockFeature VIOLET_LEATHER_FLOWER = registerFlower("violet_leather_flower", Block.box(5.0, 0.0, 5.0, 11.0, 16.0, 11.0));

    /** Other Flowers */
    public static final FlowerBlockFeature ANGELICA = registerFlower("angelica", Block.box(4.0, 0.0, 4.0, 12.0, 15.0, 12.0));
    public static final FlowerBlockFeature BEGONIA = registerFlower("begonia", Block.box(4.0, 0.0, 4.0, 12.0, 13.0, 12.0));
    public static final FlowerBlockFeature BISTORT = registerFlower("bistort", Block.box(4.0, 0.0, 4.0, 12.0, 16.0, 12.0));
    public static final FlowerBlockFeature CALIFORNIA_POPPY = registerFlower("california_poppy");
    public static final FlowerBlockFeature CROCUS = registerFlower("crocus", Block.box(0.0, 0.0, 0.0, 16.0, 12.0, 16.0));
    public static final Supplier<TallFlowerBlock> DELPHINIUM = registerTallFlower("delphinium");
    public static final FlowerBlockFeature FAIRY_SLIPPER = registerFlower("fairy_slipper", BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_TULIP).noOcclusion().mapColor(MapColor.COLOR_PINK).lightLevel((level) -> 8), Block.box(1.0, 0.0, 1.0, 15.0, 14.0, 15.0));
    public static final Supplier<TallFlowerBlock> FOXGLOVE = registerTallFlower("foxglove");
    public static final FlowerBlockFeature GUZMANIA = registerFlower("guzmania", Block.box(4.0, 0.0, 4.0, 12.0, 15.0, 12.0));
    public static final FlowerBlockFeature INCAN_LILY = registerFlower("incan_lily");
    public static final FlowerBlockFeature IRIS = registerFlower("iris", Block.box(5.0, 0.0, 5.0, 11.0, 13.0, 11.0));
    public static final Supplier<TallFlowerBlock> JAPANESE_ORCHID = registerTallFlower("japanese_orchid");
    public static final FlowerBlockFeature KOVAN_FLOWER = registerFlower("kovan_flower");
    public static final FlowerBlockFeature LOLLIPOP_FLOWER = registerFlower("lollipop_flower", Block.box(2.0, 0.0, 2.0, 14.0, 13.0, 14.0));
    public static final FlowerBlockFeature ORANGE_DAISY = registerFlower("orange_daisy", Block.box(5.0, 0.0, 5.0, 11.0, 14.0, 11.0));
    public static final FlowerBlockFeature PROTEA_FLOWER = registerFlower("protea_flower", Block.box(5.0, 0.0, 5.0, 11.0, 13.0, 11.0));
    public static final FlowerBlockFeature RICHEA = registerFlower("richea", Block.box(5.0, 0.0, 5.0, 11.0, 16.0, 11.0));
    public static final FlowerBlockFeature SILVER_VASE_FLOWER = registerFlower("silver_vase_flower");
    public static final FlowerBlockFeature HORSEWEED = registerFlower("horseweed", Block.box(4.0, 0.0, 4.0, 12.0, 15.0, 12.0));
    public static final FlowerBlockFeature WINTER_SUCCULENT = registerFlower("winter_succulent", Block.box(4.0, 0.0, 4.0, 12.0, 15.0, 12.0));

    /** Snowy Plants */
    public static final PottedBlock SNOWDROPS = createPottedVariant("snowdrops", () -> new BWGPlacementBushBlock(BlockBehaviour.Properties.of().sound(SoundType.GRASS).strength(0.0f).noCollission().noOcclusion().setId(key("snowdrops")), Block.box(2.0D, 0.0D, 2.0D, 14.0D, 14.0D, 14.0D), BWGBlockTags.SNOWY_PLANT_PLACEABLE));
    public static final PottedBlock WINTER_CYCLAMEN = createPottedVariant("winter_cyclamen", () -> new BWGPlacementBushBlock(BlockBehaviour.Properties.of().sound(SoundType.GRASS).strength(0.0f).noCollission().noOcclusion(), Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D), BWGBlockTags.SNOWY_PLANT_PLACEABLE));
    public static final PottedBlock WINTER_ROSE = createPottedVariant("winter_rose", () -> new BWGPlacementBushBlock(BlockBehaviour.Properties.of().sound(SoundType.GRASS).strength(0.0f).noCollission().noOcclusion().setId(key("winter_rose")), Block.box(5.0D, 0.0D, 5.0D, 11.0D, 12.0D, 11.0D), BWGBlockTags.SNOWY_PLANT_PLACEABLE));
    public static final PottedBlock WINTER_SCILLA = createPottedVariant("winter_scilla", () -> new BWGPlacementBushBlock(BlockBehaviour.Properties.of().sound(SoundType.GRASS).strength(0.0f).noCollission().noOcclusion().setId(key("winter_scilla")), Block.box(3.0D, 0.0D, 3.0D, 13.0D, 11.0D, 13.0D), BWGBlockTags.SNOWY_PLANT_PLACEABLE));

    /** Cattails */
    public static final Supplier<CattailPlantBlock> CATTAIL = registerBlock("cattail", () -> new CattailPlantBlock(() -> BWGItems.CATTAIL_SPROUT));
    public static final Supplier<CattailSproutBlock> CATTAIL_SPROUT = registerBlock("cattail_sprout", () -> new CattailSproutBlock(CATTAIL, "cattail_sprout"));

    public static final Supplier<FluorescentCattailPlantBlock> FLUORESCENT_CATTAIL = registerBlock("fluorescent_cattail", () -> new FluorescentCattailPlantBlock(() -> BWGItems.FLUORESCENT_CATTAIL_SPROUT));
    public static final Supplier<CattailSproutBlock> FLUORESCENT_CATTAIL_SPROUT = registerBlock("fluorescent_cattail_sprout", () -> new CattailSproutBlock(FLUORESCENT_CATTAIL, "fluorescent_cattail_sprout"));

    /** GlowCane and Bottles */
    public static final Supplier<GlowCaneBlock> BLUE_GLOWCANE = registerBlock("blue_glowcane", () -> new GlowCaneBlock(() -> BWGItems.BLUE_GLOWCANE_SHOOT, "blue_glowcane"));
    public static final Supplier<GlowCaneBlock> GREEN_GLOWCANE = registerBlock("green_glowcane", () -> new GlowCaneBlock(() -> BWGItems.GREEN_GLOWCANE_SHOOT, "green_glowcane"));
    public static final Supplier<GlowCaneBlock> RED_GLOWCANE = registerBlock("red_glowcane", () -> new GlowCaneBlock(() -> BWGItems.RED_GLOWCANE_SHOOT, "red_glowcane"));
    public static final Supplier<GlowCaneBlock> YELLOW_GLOWCANE = registerBlock("yellow_glowcane", () -> new GlowCaneBlock(() -> BWGItems.YELLOW_GLOWCANE_SHOOT, "yellow_glowcane"));

    public static final Supplier<LanternBlock> BLUE_GLOW_BOTTLE = registerBlockItem("blue_glow_bottle", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).lightLevel(light -> 14).setId(key("blue_glow_bottle"))));
    public static final Supplier<LanternBlock> GREEN_GLOW_BOTTLE = registerBlockItem("green_glow_bottle", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).lightLevel(light -> 14).setId(key("green_glow_bottle"))));
    public static final Supplier<LanternBlock> RED_GLOW_BOTTLE = registerBlockItem("red_glow_bottle", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).lightLevel(light -> 14).setId(key("red_glow_bottle"))));
    public static final Supplier<LanternBlock> YELLOW_GLOW_BOTTLE = registerBlockItem("yellow_glow_bottle", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).lightLevel(light -> 14).setId(key("yellow_glow_bottle"))));

    public static final PottedBlock WHITE_PUFFBALL = createPottedVariantWithoutItem("white_puffball", WhitePuffballBlock::new);

    /** Grasses */
    public static final Supplier<DoublePlantBlock> TALL_PRAIRIE_GRASS = registerBlockItem("tall_prairie_grass", () -> new BWGDoublePlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS).mapColor(MapColor.COLOR_GREEN).setId(key("tall_prairie_grass")), BlockTags.DIRT));
    public static final Supplier<BoneMealGrassBlock> PRAIRIE_GRASS = registerBlockItem("prairie_grass", () -> new BoneMealGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).setId(key("prairie_grass")), TALL_PRAIRIE_GRASS, BlockTags.DIRT));
    public static final Supplier<DoublePlantBlock> TALL_BEACH_GRASS = registerBlockItem("tall_beach_grass", () -> new BWGDoublePlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS).mapColor(MapColor.COLOR_GREEN).setId(key("tall_beach_grass")), BlockTags.SAND));
    public static final Supplier<BoneMealGrassBlock> BEACH_GRASS = registerBlockItem("beach_grass", () -> new BoneMealGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).setId(key("beach_grass")), TALL_BEACH_GRASS, BlockTags.SAND));

    /** Flat Blocks */
    public static final Supplier<FlatVegetationBlock> LEAF_PILE = registerBlockItem("leaf_pile", () -> new FlatVegetationBlock("leaf_pile"));
    public static final Supplier<FlatVegetationBlock> CLOVER_PATCH = registerBlockItem("clover_patch", () -> new FlatVegetationBlock("clover_patch"));
    public static final Supplier<FlatVegetationBlock> FLOWER_PATCH = registerBlockItem("flower_patch", () -> new FlatVegetationBlock("flower_patch"));
    public static final Supplier<PinkPetalsBlock> WHITE_SAKURA_PETALS = registerBlockItem("white_sakura_petals", () -> new PinkPetalsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_PETALS).mapColor(MapColor.TERRACOTTA_WHITE).setId(key("white_sakura_petals"))));
    public static final Supplier<PinkPetalsBlock> YELLOW_SAKURA_PETALS = registerBlockItem("yellow_sakura_petals", () -> new PinkPetalsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_PETALS).mapColor(MapColor.COLOR_YELLOW).setId(key("yellow_sakura_petals"))));

    public static final Supplier<PoisonIvyBlock> POISON_IVY = registerBlockItem("poison_ivy", PoisonIvyBlock::new);
    public static final Supplier<VineBlock> SKYRIS_VINE = registerBlockItem("skyris_vine", () -> new VineBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.VINE).setId(key("skyris_vine"))));

    public static final Supplier<TreeBranchBlock> WITCH_HAZEL_BRANCH = registerBlockItem("witch_hazel_branch", () -> new TreeBranchBlock("witch_hazel_branch"));
    public static final Supplier<WitchHazelBlossomBlock> WITCH_HAZEL_BLOSSOM = registerBlockItem("witch_hazel_blossom", WitchHazelBlossomBlock::new);

    public static final Supplier<TreeBranchBlock> SHELF_FUNGI = registerBlockItem("shelf_fungi", () -> new TreeBranchBlock("shelf_fungi"));

    /** Desert Plants */
    public static final PottedBlock MINI_CACTUS = createPottedVariant("mini_cactus", () -> new DesertPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CACTUS).noOcclusion().noCollission().setId(key("mini_cactus")), Block.box(5.0D, 0.0D, 5.0D, 11.0D, 7.0D, 11.0D), BlockTags.SAND));
    public static final PottedBlock PRICKLY_PEAR_CACTUS = createPottedVariant("prickly_pear_cactus", () -> new DesertPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CACTUS).noOcclusion().noCollission().setId(key("prickly_pear_cactus")), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D), BlockTags.SAND));
    public static final PottedBlock GOLDEN_SPINED_CACTUS = createPottedVariant("golden_spined_cactus", () -> new DesertPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CACTUS).noOcclusion().noCollission().setId(key("golden_spined_cactus")), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D), BlockTags.SAND));
    public static final Supplier<BarrelCactusBlock> BARREL_CACTUS = registerBlockItem("barrel_cactus", () -> new BarrelCactusBlock("barrel_cactus"));
    public static final Supplier<BarrelCactusBlock> FLOWERING_BARREL_CACTUS = registerBlockItem("flowering_barrel_cactus", () -> new BarrelCactusBlock("flowering_barrel_cactus"));
    public static final Supplier<CarvedBarrelCactusBlock> CARVED_BARREL_CACTUS = registerBlockItem("carved_barrel_cactus", CarvedBarrelCactusBlock::new);
    public static final Supplier<AloeVeraBlock> ALOE_VERA = registerBlockItem("aloe_vera", AloeVeraBlock::new);
    public static final Supplier<BloomingAloeVeraBlock> BLOOMING_ALOE_VERA = registerBlock("blooming_aloe_vera", BloomingAloeVeraBlock::new);

    /** Lily Pads */
    public static final Supplier<WaterlilyBlock> TINY_LILY_PADS = registerBlock("tiny_lily_pads", () -> new WaterlilyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LILY_PAD).setId(key("tiny_lily_pads"))));
    public static final Supplier<WaterlilyBlock> FLOWERING_TINY_LILY_PADS = registerBlock("flowering_tiny_lily_pads", () -> new WaterlilyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LILY_PAD).setId(key("flowering_tiny_lily_pads"))));

    public static final Supplier<BWGFruitBlock> APPLE_FRUIT_BLOCK = registerBlock("apple_fruit", () -> new BWGFruitBlock(() -> () -> Items.APPLE, "ripe_orchard_leaves", "apple_fruit"));
    public static final Supplier<BWGFruitBlock> BAOBAB_FRUIT_BLOCK = registerBlock("baobab_fruit", () -> new BWGFruitBlock(() ->BWGItems.BAOBAB_FRUIT,"ripe_baobab_leaves", "baobab_fruit"));
    public static final Supplier<BWGFruitBlock> GREEN_APPLE_FRUIT_BLOCK = registerBlock("green_apple_fruit", () -> new BWGFruitBlock(() ->BWGItems.GREEN_APPLE, "green_apple_skyris_leaves", "green_apple_fruit"));
    public static final Supplier<BWGFruitBlock> YUCCA_FRUIT_BLOCK = registerBlock("yucca_fruit", () -> new BWGFruitBlock(() ->BWGItems.YUCCA_FRUIT, "ripe_yucca_leaves", "yucca_fruit"));
    public static final Supplier<SoulFruitBlock> SOUL_FRUIT_BLOCK = registerBlock("soul_fruit", SoulFruitBlock::new);

    public static final BWGBlockSet DACITE_SET = new BWGBlockSet("dacite", MapColor.TERRACOTTA_WHITE);
    public static final BWGBlockSet DACITE_BRICKS_SET = new BWGBlockSet("dacite_bricks", "dacite_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.TERRACOTTA_WHITE));
    public static final BWGBlockSet DACITE_COBBLESTONE_SET = new BWGBlockSet("dacite_cobblestone", BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE).mapColor(MapColor.TERRACOTTA_WHITE));
    public static final Supplier<RotatedPillarBlock> DACITE_PILLAR = registerBlockItem("dacite_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).mapColor(MapColor.TERRACOTTA_WHITE).setId(key("dacite_pillar"))));
    public static final BWGBlockSet DACITE_TILE_SET = new BWGBlockSet("dacite_tile", MapColor.TERRACOTTA_WHITE);
    public static final Supplier<SnowyDirtBlock> PODZOL_DACITE = registerBlockItem("podzol_dacite", () -> new SnowyDirtBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).mapColor(MapColor.TERRACOTTA_WHITE).setId(key("podzol_dacite"))));
    public static final Supplier<BWGSpreadableBlock> OVERGROWN_DACITE = registerBlockItem("overgrown_dacite", () -> new BWGSpreadableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).randomTicks().setId(key("overgrown_dacite")), DACITE_SET::getBase));
    public static final Supplier<BWGSpreadableBlock> OVERGROWN_STONE = registerBlockItem("overgrown_stone", () -> new BWGSpreadableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).randomTicks().setId(key("overgrown_stone")), () -> Blocks.STONE));

    public static final BWGBlockSet RED_ROCK_SET = new BWGBlockSet("red_rock", MapColor.COLOR_RED);
    public static final BWGBlockSet RED_ROCK_BRICKS_SET = new BWGBlockSet("red_rock_bricks", "red_rock_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.COLOR_RED));
    public static final BWGBlockSet CRACKED_RED_ROCK_BRICKS_SET = new BWGBlockSet("cracked_red_rock_bricks", "cracked_red_rock_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.CRACKED_STONE_BRICKS).mapColor(MapColor.COLOR_RED));
    public static final BWGBlockSet CHISELED_RED_ROCK_BRICKS_SET = new BWGBlockSet("chiseled_red_rock_bricks", "chiseled_red_rock_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).mapColor(MapColor.COLOR_RED));
    public static final BWGBlockSet MOSSY_RED_ROCK_BRICKS_SET = new BWGBlockSet("mossy_red_rock_bricks", "mossy_red_rock_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS).mapColor(MapColor.COLOR_RED));

    public static final BWGBlockSet MOSSY_STONE_SET = new BWGBlockSet("mossy_stone", BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE));
    public static final BWGBlockSet ROCKY_STONE_SET = new BWGBlockSet("rocky_stone", BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE));

    public static final Supplier<MudBlock> PALE_MUD = registerCubeAllBlockItem("pale_mud", () -> new MudBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MUD).setId(key("pale_mud"))));
    public static final Supplier<Block> PACKED_PALE_MUD = registerCubeAllBlockItem("packed_pale_mud", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD).setId(key("packed_pale_mud"))));
    public static final BWGBlockSet PALE_MUD_BRICKS_SET = new BWGBlockSet("pale_mud_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.MUD_BRICKS));

    public static final Supplier<Block> WATER_SILK = registerBlock("water_silk", () -> new WaterlilyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LILY_PAD).noCollission().mapColor(MapColor.COLOR_GREEN).setId(key("water_silk"))));

    public static final Supplier<HayBlock> CATTAIL_THATCH = registerBlockItem("cattail_thatch", () -> new HayBlock(BlockBehaviour.Properties.of().sound(SoundType.GRASS).strength(0.5f).setId(key("cattail_thatch"))));
    public static final Supplier<SlabBlock> CATTAIL_THATCH_SLAB = registerBlockItem("cattail_thatch_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(CATTAIL_THATCH.get()).setId(key("cattail_thatch_slab"))));
    public static final Supplier<StairBlock> CATTAIL_THATCH_STAIRS = registerBlockItem("cattail_thatch_stairs", () -> new StairBlock(CATTAIL_THATCH.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(CATTAIL_THATCH.get()).setId(key("cattail_thatch_stairs"))));
    public static final Supplier<WoolCarpetBlock> CATTAIL_THATCH_CARPET = registerBlockItem("cattail_thatch_carpet", () -> new WoolCarpetBlock(DyeColor.BROWN, BlockBehaviour.Properties.of().sound(SoundType.GRASS).strength(0.5f).setId(key("cattail_thatch_carpet"))));

    public static final Supplier<AttachedStemBlock> ATTACHED_PALE_PUMPKIN_STEM = registerBlock("attached_pale_pumpkin_stem", () -> new AttachedStemBlock(BiomesWeveGone.key(Registries.BLOCK, "pale_pumpkin_stem"), BiomesWeveGone.key(Registries.BLOCK, "pale_pumpkin"), BiomesWeveGone.key(Registries.ITEM, "pale_pumpkin_seeds"), BlockBehaviour.Properties.ofFullCopy(Blocks.ATTACHED_PUMPKIN_STEM).setId(key("attached_pale_pumpkin_stem"))));
    public static final Supplier<StemBlock> PALE_PUMPKIN_STEM = registerBlock("pale_pumpkin_stem", () -> new StemBlock(BiomesWeveGone.key(Registries.BLOCK, "pale_pumpkin"), BiomesWeveGone.key(Registries.BLOCK,"attached_pale_pumpkin_stem"), BiomesWeveGone.key(Registries.ITEM, "pale_pumpkin_seeds"), BlockBehaviour.Properties.ofFullCopy(Blocks.PUMPKIN_STEM).setId(key("pale_pumpkin_stem"))));
    public static final Supplier<PalePumpkinBlock> PALE_PUMPKIN = registerBlockItem("pale_pumpkin", PalePumpkinBlock::new);
    public static final Supplier<PaleCarvedPumpkinBlock> CARVED_PALE_PUMPKIN = registerBlockItem("carved_pale_pumpkin", () -> new PaleCarvedPumpkinBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARVED_PUMPKIN).mapColor(MapColor.TERRACOTTA_WHITE), "carved_pale_pumpkin"));
    public static final Supplier<PaleCarvedPumpkinBlock> PALE_JACK_O_LANTERN = registerBlockItem("pale_jack_o_lantern", () -> new PaleCarvedPumpkinBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JACK_O_LANTERN).mapColor(MapColor.TERRACOTTA_WHITE), "pale_jack_o_lantern"));

    private static FlowerBlockFeature registerFlower(String key) {
        Supplier<? extends Block> flower = registerBlockItem(key, () -> new BWGFlowerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_TULIP).noOcclusion().setId(key(key))));
        return new FlowerBlockFeature(key, flower);
    }

    private static FlowerBlockFeature registerFlower(String key, VoxelShape shape) {
        Supplier<? extends Block> flower = registerBlockItem(key, () -> new BWGFlowerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_TULIP).noOcclusion().setId(key(key)), BlockTags.DIRT, shape));
        return new FlowerBlockFeature(key, flower);
    }

    private static FlowerBlockFeature registerFlower(String key, VoxelShape shape, Supplier<? extends Block> growAble) {
        Supplier<? extends Block> flower = registerBlockItem(key, () -> new BWGBonemealableFlowerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_TULIP).noOcclusion().setId(key(key)), BlockTags.DIRT, shape, growAble));
        return new FlowerBlockFeature(key, flower);
    }

    private static FlowerBlockFeature registerFlower(String key, BlockBehaviour.Properties properties, VoxelShape shape) {
        Supplier<? extends Block> flower = registerBlockItem(key, () -> new BWGFlowerBlock(properties.setId(key(key)), BlockTags.DIRT, shape));
        return new FlowerBlockFeature(key, flower);
    }

    private static Supplier<TallFlowerBlock> registerTallFlower(String key) {
        return registerBlockItem(key, () -> new BWGTallFlowerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SUNFLOWER).noOcclusion().setId(key(key))));
    }

    private static Supplier<TallFlowerBlock> registerTallFlower(String key, Supplier<TreeGrower> treeGrower) {
        return registerBlockItem(key, () -> new BWGTallFlowerBlockTreeGrower(BlockBehaviour.Properties.ofFullCopy(Blocks.SUNFLOWER).noOcclusion().setId(key(key)), treeGrower));
    }

    private static Supplier<TallFlowerBlock> registerTallGlowingFlower(String key) {
        return registerBlockItem(key, () -> new BWGTallFlowerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SUNFLOWER).lightLevel(state -> 10).noOcclusion().setId(key(key))));
    }

    public static Supplier<Block> registerBasicBlockWithItem(String key, BlockBehaviour.Properties properties) {
        return registerCubeAllBlockItem(key, () -> new Block(properties.setId(key(key))));
    }

    public static <B extends Block> Supplier<B> registerBlockItem(String key, Supplier<B> blockSupplier) {
        Supplier<B> block = registerBlock(key, blockSupplier);
        Supplier<Item> item = BWGItems.register(key, properties -> new BlockItem(block.get(), properties), new Item.Properties());
        BLOCK_ITEMS.add(item);
        return block;
    }

    public static <B extends Block> Supplier<B> registerBlockItemNoTab(String key, Supplier<B> blockSupplier) {
        Supplier<B> block = registerBlock(key, blockSupplier);
        BWGItems.register(key, properties -> new BlockItem(block.get(), properties), new Item.Properties());
        return block;
    }

    public static <B extends Block> Supplier<B> registerCubeAllBlockItem(String key, Supplier<B> blockSupplier) {
        Supplier<B> block = registerBlockItem(key, blockSupplier);
        cubeAllBlocks.add(block);
        return block;
    }

    public static <B extends Block> PottedBlock createPottedVariant(String key, Supplier<B> blockSupplier) {
        Supplier<B> block = registerBlockItem(key, blockSupplier);
        return new PottedBlock(key, block);
    }

    public static <B extends Block> PottedBlock createPottedVariantWithoutItem(String key, Supplier<B> blockSupplier) {
        Supplier<B> block = registerBlock(key, blockSupplier);
        return new PottedBlock(key, block);
    }

    public static <B extends Block> Supplier<B> registerBlock(String id, Supplier<B> block) {
        Supplier<B> blockSupplier = register(id, block);
        BLOCKS.add(blockSupplier);
        return blockSupplier;
    }

    public static <B extends Block> Supplier<B> register(String id, Supplier<B> block) {
        return PlatformHandler.PLATFORM_HANDLER.register(BuiltInRegistries.BLOCK, id, block);
    }

    private static ResourceKey<Block> key(String id) {
        return BiomesWeveGone.key(Registries.BLOCK, id);
    }

    public static void blocks() {
        BiomesWeveGone.LOGGER.info("Registering Oh The Biomes We've Gone Blocks");
        BWGWood.wood();
    }
}
