package net.potionstudios.biomeswevegone.world.level.block.wood;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.PlatformHandler;
import net.potionstudios.biomeswevegone.client.particle.BWGParticles;
import net.potionstudios.biomeswevegone.world.item.BWGItems;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.block.plants.PottedBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.tree.fruit.BWGFruitBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.tree.grower.BWGTreeGrowers;
import net.potionstudios.biomeswevegone.world.level.block.plants.tree.leaves.BWGChangingLeavesBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.tree.leaves.BWGFireCrackerLeaves;
import net.potionstudios.biomeswevegone.world.level.block.plants.tree.leaves.BWGFruitLeavesBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.tree.leaves.BWGLeavesBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.tree.sapling.BWGSaplingBlock;

import java.util.ArrayList;
import java.util.function.Supplier;

/**
 * Holds all the wood sets
 *
 * @author Joseph T. McQuigg
 * @see BWGWoodSet
 */
public class BWGWood {

    public static final ArrayList<Supplier<? extends Block>> WOOD = new ArrayList<>();
    public static final ArrayList<Supplier<? extends Item>> WOOD_BLOCK_ITEMS = new ArrayList<>();
    public static final ArrayList<Supplier<? extends Block>> NONSET_WOOD = new ArrayList<>();

    public static final BWGWoodSet ASPEN = new BWGWoodSet("aspen", MapColor.QUARTZ, BWGTreeGrowers.ASPEN);
    public static final BWGWoodSet BAOBAB = new BWGWoodSet("baobab", MapColor.TERRACOTTA_WHITE, BWGTreeGrowers.BAOBAB);
    public static final BWGWoodSet BLUE_ENCHANTED = new BWGWoodSet("blue_enchanted", MapColor.COLOR_BLUE, BWGTreeGrowers.BLUE_ENCHANTED, true);
    public static final Supplier<ImbuedBlock> IMBUED_BLUE_ENCHANTED_WOOD = registerBlockItem("imbued_blue_enchanted_wood", () -> new ImbuedBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).setId(key("imbued_blue_enchanted_wood"))));
    public static final BWGWoodSet CIKA = new BWGWoodSet("cika", MapColor.TERRACOTTA_WHITE, BWGTreeGrowers.CIKA);
    public static final BWGWoodSet CYPRESS = new BWGWoodSet("cypress", MapColor.TERRACOTTA_WHITE, BWGTreeGrowers.CYPRESS);
    public static final BWGWoodSet EBONY = new BWGWoodSet("ebony", MapColor.COLOR_BLACK, BWGTreeGrowers.EBONY);
    public static final BWGWoodSet FIR = new BWGWoodSet("fir", MapColor.TERRACOTTA_WHITE,BWGTreeGrowers.FIR);
    public static final BWGWoodSet FLORUS = new BWGWoodSet(new BlockSetType("florus"), MapColor.COLOR_GREEN, BWGWoodSet.LogStem.STEM, null, false, false, null);
    public static final BWGWoodSet GREEN_ENCHANTED = new BWGWoodSet("green_enchanted", MapColor.COLOR_LIGHT_GREEN, BWGTreeGrowers.GREEN_ENCHANTED, true);
    public static final Supplier<ImbuedBlock> IMBUED_GREEN_ENCHANTED_WOOD = registerBlockItem("imbued_green_enchanted_wood", () -> new ImbuedBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).setId(key("imbued_green_enchanted_wood"))));
    public static final BWGWoodSet HOLLY = new BWGWoodSet("holly", MapColor.TERRACOTTA_WHITE, BWGTreeGrowers.HOLLY);
    public static final BWGWoodSet IRONWOOD = new BWGWoodSet("ironwood", MapColor.COLOR_GRAY, BWGTreeGrowers.IRONWOOD);
    public static final BWGWoodSet JACARANDA = new BWGWoodSet("jacaranda", MapColor.COLOR_PINK, BWGTreeGrowers.JACARANDA);
    public static final BWGWoodSet MAHOGANY = new BWGWoodSet("mahogany", MapColor.COLOR_PINK, BWGTreeGrowers.MAHOGANY);
    public static final BWGWoodSet MAPLE = new BWGWoodSet("maple", MapColor.TERRACOTTA_WHITE, BWGTreeGrowers.MAPLE);
    public static final BWGWoodSet PALM = new BWGWoodSet("palm", MapColor.TERRACOTTA_WHITE, BWGTreeGrowers.PALM, BlockTags.SAND);
    public static final BWGWoodSet PINE = new BWGWoodSet("pine", MapColor.TERRACOTTA_WHITE, BWGTreeGrowers.PINE);
    public static final BWGWoodSet RAINBOW_EUCALYPTUS = new BWGWoodSet("rainbow_eucalyptus", MapColor.TERRACOTTA_WHITE, BWGTreeGrowers.RAINBOW_EUCALYPTUS);
    public static final BWGWoodSet REDWOOD = new BWGWoodSet("redwood", MapColor.COLOR_RED, BWGTreeGrowers.REDWOOD);
    public static final BWGWoodSet SAKURA = new BWGWoodSet(BlockSetType.register(new BlockSetType("sakura", true, true, true, BlockSetType.PressurePlateSensitivity.EVERYTHING, SoundType.CHERRY_WOOD, SoundEvents.CHERRY_WOOD_DOOR_CLOSE, SoundEvents.CHERRY_WOOD_DOOR_OPEN, SoundEvents.CHERRY_WOOD_TRAPDOOR_CLOSE, SoundEvents.CHERRY_WOOD_TRAPDOOR_OPEN, SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_ON, SoundEvents.CHERRY_WOOD_BUTTON_CLICK_OFF, SoundEvents.CHERRY_WOOD_BUTTON_CLICK_ON)), MapColor.COLOR_RED, null, false);
    public static final PottedBlock WHITE_SAKURA_SAPLING = createSapling("white_sakura", BWGTreeGrowers.WHITE_SAKURA, BlockTags.DIRT);
    public static final PottedBlock YELLOW_SAKURA_SAPLING = createSapling("yellow_sakura", BWGTreeGrowers.YELLOW_SAKURA, BlockTags.DIRT);
    public static final BWGWoodSet SKYRIS = new BWGWoodSet("skyris", MapColor.COLOR_LIGHT_BLUE, BWGTreeGrowers.SKYRIS);
    public static final BWGWoodSet SPIRIT = new BWGWoodSet(BlockSetType.register(new BlockSetType("spirit", true, true, true, BlockSetType.PressurePlateSensitivity.EVERYTHING, SoundType.NETHER_WOOD, SoundEvents.NETHER_WOOD_DOOR_CLOSE, SoundEvents.NETHER_WOOD_DOOR_OPEN, SoundEvents.NETHER_WOOD_TRAPDOOR_CLOSE, SoundEvents.NETHER_WOOD_TRAPDOOR_OPEN, SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_ON, SoundEvents.NETHER_WOOD_BUTTON_CLICK_OFF, SoundEvents.NETHER_WOOD_BUTTON_CLICK_ON)), MapColor.COLOR_LIGHT_GRAY, BWGTreeGrowers.SPIRIT, false);
    public static final Supplier<MangroveRootsBlock> SPIRIT_ROOTS = registerBlockItem("spirit_roots", () -> new MangroveRootsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_ROOTS).sound(SoundType.NETHER_WOOD).setId(key("spirit_roots"))));
    public static final BWGWoodSet WHITE_MANGROVE = new BWGWoodSet("white_mangrove", MapColor.TERRACOTTA_WHITE, BWGTreeGrowers.WHITE_MANGROVE);
    public static final BWGWoodSet WILLOW = new BWGWoodSet("willow", MapColor.COLOR_GREEN, BWGTreeGrowers.WILLOW);
    public static final BWGWoodSet WITCH_HAZEL = new BWGWoodSet("witch_hazel", MapColor.COLOR_GREEN, BWGTreeGrowers.WITCH_HAZEL);
    public static final BWGWoodSet ZELKOVA = new BWGWoodSet("zelkova", MapColor.COLOR_ORANGE, BWGTreeGrowers.ZELKOVA);

    public static final Supplier<RotatedPillarBlock> PALO_VERDE_LOG = registerBlockItem("palo_verde_log", () -> new RotatedPillarBlock(Blocks.logProperties(MapColor.COLOR_GREEN, MapColor.COLOR_GREEN, SoundType.WOOD).setId(key("palo_verde_log"))));
    public static final Supplier<RotatedPillarBlock> PALO_VERDE_WOOD = registerBlockItem("palo_verde_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava().setId(key("palo_verde_wood"))));
    public static final Supplier<RotatedPillarBlock> STRIPPED_PALO_VERDE_LOG = registerBlockItem("stripped_palo_verde_log", () -> new RotatedPillarBlock(Blocks.logProperties(MapColor.COLOR_GREEN, MapColor.COLOR_GREEN, SoundType.WOOD).setId(key("stripped_palo_verde_log"))));
    public static final Supplier<RotatedPillarBlock> STRIPPED_PALO_VERDE_WOOD = registerBlockItem("stripped_palo_verde_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava().setId(key("stripped_palo_verde_wood"))));

    public static final Supplier<LeavesBlock> PALO_VERDE_LEAVES = registerBlockItem("palo_verde_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).setId(key("palo_verde_leaves"))));

    public static final PottedBlock PALO_VERDE_SAPLING = createSapling("palo_verde", BWGTreeGrowers.PALO_VERDE, BlockTags.SAND);

    // Other Saplings
    public static final PottedBlock ARAUCARIA_SAPLING = createNonSetSapling("araucaria", BWGTreeGrowers.ARAUCARIA, BlockTags.DIRT);
    public static final PottedBlock BLUE_SPRUCE_SAPLING = createNonSetSapling("blue_spruce", BWGTreeGrowers.BLUE_SPRUCE, BlockTags.DIRT);
    public static final PottedBlock BROWN_BIRCH_SAPLING = createNonSetSapling("brown_birch", BWGTreeGrowers.BROWN_BIRCH, BlockTags.DIRT);
    public static final PottedBlock BROWN_OAK_SAPLING = createNonSetSapling("brown_oak", BWGTreeGrowers.BROWN_OAK, BlockTags.DIRT);
    public static final PottedBlock BROWN_ZELKOVA_SAPLING = createNonSetSapling("brown_zelkova", BWGTreeGrowers.BROWN_ZELKOVA, BlockTags.DIRT);
    public static final PottedBlock INDIGO_JACARANDA_SAPLING = createNonSetSapling("indigo_jacaranda", BWGTreeGrowers.INDIGO_JACARANDA, BlockTags.DIRT);
    public static final PottedBlock ORANGE_BIRCH_SAPLING = createNonSetSapling("orange_birch", BWGTreeGrowers.ORANGE_BIRCH, BlockTags.DIRT);
    public static final PottedBlock ORANGE_OAK_SAPLING = createNonSetSapling("orange_oak", BWGTreeGrowers.ORANGE_OAK, BlockTags.DIRT);
    public static final PottedBlock ORANGE_SPRUCE_SAPLING = createNonSetSapling("orange_spruce", BWGTreeGrowers.ORANGE_SPRUCE, BlockTags.DIRT);
    public static final PottedBlock ORCHARD_SAPLING = createNonSetSapling("orchard", BWGTreeGrowers.ORCHARD, BlockTags.DIRT);
    public static final PottedBlock RED_BIRCH_SAPLING = createNonSetSapling("red_birch", BWGTreeGrowers.RED_BIRCH, BlockTags.DIRT);
    public static final PottedBlock RED_MAPLE_SAPLING = createNonSetSapling("red_maple", BWGTreeGrowers.RED_MAPLE, BlockTags.DIRT);
    public static final PottedBlock RED_OAK_SAPLING = createNonSetSapling("red_oak", BWGTreeGrowers.RED_OAK, BlockTags.DIRT);
    public static final PottedBlock RED_SPRUCE_SAPLING = createNonSetSapling("red_spruce", BWGTreeGrowers.RED_SPRUCE, BlockTags.DIRT);
    public static final PottedBlock SILVER_MAPLE_SAPLING = createNonSetSapling("silver_maple", BWGTreeGrowers.SILVER_MAPLE, BlockTags.DIRT);
    public static final PottedBlock YELLOW_BIRCH_SAPLING = createNonSetSapling("yellow_birch", BWGTreeGrowers.YELLOW_BIRCH, BlockTags.DIRT);
    public static final PottedBlock YELLOW_SPRUCE_SAPLING = createNonSetSapling("yellow_spruce", BWGTreeGrowers.YELLOW_SPRUCE, BlockTags.DIRT);
    public static final PottedBlock YUCCA_SAPLING = createNonSetSapling("yucca", BWGTreeGrowers.YUCCA, BlockTags.SAND);

    public static final Supplier<LeavesBlock> ARAUCARIA_LEAVES = registerLeaves("araucaria", MapColor.COLOR_GREEN);
    public static final Supplier<LeavesBlock> RIPE_BAOBAB_LEAVES = registerLeaves("ripe_baobab", BWGBlocks.BAOBAB_FRUIT_BLOCK, MapColor.COLOR_GREEN, 0.04F);
    public static final Supplier<LeavesBlock> FLOWERING_BAOBAB_LEAVES = registerLeaves("flowering_baobab", MapColor.COLOR_GREEN, BWGWood.RIPE_BAOBAB_LEAVES, 0.02F);
    public static final Supplier<LeavesBlock> BLUE_SPRUCE_LEAVES = registerLeaves("blue_spruce", MapColor.COLOR_LIGHT_BLUE);
    public static final Supplier<LeavesBlock> BLOOMING_WITCH_HAZEL_LEAVES = registerGlowingLeaves("blooming_witch_hazel", MapColor.COLOR_ORANGE);
    public static final Supplier<LeavesBlock> BROWN_BIRCH_LEAVES = registerLeaves("brown_birch", MapColor.COLOR_BROWN);
    public static final Supplier<LeavesBlock> BROWN_OAK_LEAVES = registerLeaves("brown_oak", MapColor.COLOR_BROWN);
    public static final Supplier<LeavesBlock> BROWN_ZELKOVA_LEAVES = registerLeaves("brown_zelkova", MapColor.COLOR_BROWN);
    public static final Supplier<LeavesBlock> RIPE_ORCHARD_LEAVES = registerLeaves("ripe_orchard", BWGBlocks.APPLE_FRUIT_BLOCK, MapColor.COLOR_GREEN, 0.04F);
    public static final Supplier<LeavesBlock> FLOWERING_ORCHARD_LEAVES = registerLeaves("flowering_orchard", MapColor.COLOR_GREEN, BWGWood.RIPE_ORCHARD_LEAVES, 0.02F);
    public static final Supplier<LeavesBlock> FLOWERING_PALO_VERDE_LEAVES = registerLeaves("flowering_palo_verde", () -> ParticleTypes.SPORE_BLOSSOM_AIR, MapColor.COLOR_YELLOW);
    public static final Supplier<LeavesBlock> HOLLY_BERRY_LEAVES = registerLeaves("holly_berry", MapColor.TERRACOTTA_GREEN);
    public static final Supplier<LeavesBlock> INDIGO_JACARANDA_LEAVES = registerLeaves("indigo_jacaranda", MapColor.TERRACOTTA_BLUE);
    public static final Supplier<LeavesBlock> FLOWERING_JACARANDA_LEAVES = registerLeaves("flowering_jacaranda", MapColor.TERRACOTTA_PURPLE);
    public static final Supplier<LeavesBlock> FLOWERING_INDIGO_JACARANDA_LEAVES = registerLeaves("flowering_indigo_jacaranda", MapColor.TERRACOTTA_BLUE);
    public static final Supplier<LeavesBlock> YUCCA_LEAVES = registerLeaves("yucca", MapColor.COLOR_GREEN);
    public static final Supplier<LeavesBlock> RIPE_YUCCA_LEAVES = registerLeaves("ripe_yucca", BWGBlocks.YUCCA_FRUIT_BLOCK, MapColor.COLOR_GREEN, 0.04F);
    public static final Supplier<LeavesBlock> FLOWERING_YUCCA_LEAVES = registerLeaves("flowering_yucca", MapColor.COLOR_GREEN, BWGWood.RIPE_YUCCA_LEAVES, 0.02F);
    public static final Supplier<LeavesBlock> ORANGE_BIRCH_LEAVES = registerLeaves("orange_birch", MapColor.COLOR_ORANGE);
    public static final Supplier<LeavesBlock> ORANGE_OAK_LEAVES = registerLeaves("orange_oak", MapColor.COLOR_ORANGE);
    public static final Supplier<LeavesBlock> ORANGE_SPRUCE_LEAVES = registerLeaves("orange_spruce", MapColor.COLOR_ORANGE);
    public static final Supplier<LeavesBlock> ORCHARD_LEAVES = registerLeaves("orchard", MapColor.COLOR_GREEN);
    public static final Supplier<LeavesBlock> RED_BIRCH_LEAVES = registerLeaves("red_birch", MapColor.COLOR_RED);
    public static final Supplier<LeavesBlock> RED_MAPLE_LEAVES = registerLeaves("red_maple", BWGParticles.RED_MAPLE_LEAVES, MapColor.COLOR_RED);
    public static final Supplier<LeavesBlock> RED_OAK_LEAVES = registerLeaves("red_oak", MapColor.COLOR_RED);
    public static final Supplier<LeavesBlock> RED_SPRUCE_LEAVES = registerLeaves("red_spruce", MapColor.COLOR_RED);
    public static final Supplier<LeavesBlock> SILVER_MAPLE_LEAVES = registerLeaves("silver_maple", BWGParticles.SILVER_MAPLE_LEAVES, MapColor.COLOR_LIGHT_GRAY);
    public static final Supplier<LeavesBlock> SPIRIT_LEAVES = registerNonSetBlockItem("spirit_leaves", () -> new BWGLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_LEAVES).mapColor(MapColor.COLOR_LIGHT_GRAY).setId(key("spirit_leaves")), BWGParticles.SPIRIT_LEAVES));
    public static final Supplier<LeavesBlock> FLOWERING_SPIRIT_LEAVES = registerNonSetBlockItem("flowering_spirit_leaves", () -> new BWGLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_LEAVES).lightLevel(light -> 6).mapColor(MapColor.COLOR_LIGHT_GRAY).setId(key("flowering_spirit_leaves")), BWGParticles.SPIRIT_LEAVES));
    public static final Supplier<LeavesBlock> SKYRIS_LEAVES_GREEN_APPLE = registerLeaves("green_apple_skyris", BWGBlocks.GREEN_APPLE_FRUIT_BLOCK, MapColor.COLOR_PINK, 0.04F);
    public static final Supplier<LeavesBlock> FLOWERING_SKYRIS_LEAVES = registerLeaves("flowering_skyris", MapColor.COLOR_PINK, BWGWood.SKYRIS_LEAVES_GREEN_APPLE, 0.02F);
    public static final Supplier<LeavesBlock> FLOWERING_IRONWOOD_LEAVES = registerLeaves("flowering_ironwood", BWGParticles.IRONWOOD_LEAVES, MapColor.COLOR_LIGHT_GREEN);
    public static final Supplier<LeavesBlock> WHITE_SAKURA_LEAVES = registerSakuraLeaves("white_sakura", BWGParticles.WHITE_SAKURA_LEAVES, MapColor.COLOR_LIGHT_GRAY);
    public static final Supplier<LeavesBlock> YELLOW_SAKURA_LEAVES = registerSakuraLeaves("yellow_sakura", BWGParticles.YELLOW_SAKURA_LEAVES, MapColor.COLOR_YELLOW);
    public static final Supplier<LeavesBlock> YELLOW_BIRCH_LEAVES = registerLeaves("yellow_birch", MapColor.COLOR_YELLOW);
    public static final Supplier<LeavesBlock> YELLOW_SPRUCE_LEAVES = registerLeaves("yellow_spruce", MapColor.COLOR_YELLOW);
    public static final Supplier<LeavesBlock> FIRECRACKER_LEAVES = registerNonSetBlockItem("firecracker_leaves", () -> new BWGFireCrackerLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).mapColor(MapColor.COLOR_GREEN).setId(key("firecracker_leaves"))));

    private static Supplier<LeavesBlock> registerGlowingLeaves(String key, MapColor mapColor) {
        return registerNonSetBlockItem(key + "_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).mapColor(mapColor).lightLevel(state -> 8).setId(key(key + "_leaves"))));
    }

    private static Supplier<LeavesBlock> registerLeaves(String key, MapColor mapColor) {
        return registerNonSetBlockItem(key + "_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).mapColor(mapColor).setId(key(key + "_leaves"))));
    }

    private static Supplier<LeavesBlock> registerLeaves(String key, Supplier<SimpleParticleType> particleType, MapColor mapColor) {
        return registerNonSetBlockItem(key + "_leaves", () -> new BWGLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).mapColor(mapColor).setId(key(key + "_leaves")), particleType));
    }

    private static Supplier<LeavesBlock> registerSakuraLeaves(String key, Supplier<SimpleParticleType> particleType, MapColor mapColor) {
        return registerNonSetBlockItem(key + "_leaves", () -> new BWGLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_LEAVES).mapColor(mapColor).setId(key(key + "_leaves")), particleType));
    }

    private static Supplier<LeavesBlock> registerLeaves(String key, MapColor mapColor, Supplier<LeavesBlock> ripeLeaves, float chance) {
        return registerNonSetBlockItem(key + "_leaves", () -> new BWGChangingLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).mapColor(mapColor).setId(key(key + "_leaves")), ripeLeaves, chance));
    }

    private static Supplier<LeavesBlock> registerLeaves(String key, Supplier<BWGFruitBlock> bwgFruitBlockSupplier, MapColor mapColor, float chance) {
        return registerNonSetBlockItem(key + "_leaves", () -> new BWGFruitLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).mapColor(mapColor).setId(key(key + "_leaves")), bwgFruitBlockSupplier, chance));
    }

    protected static PottedBlock createNonSetSapling(String key, Supplier<TreeGrower> grower, TagKey<Block> ground) {
        PottedBlock sapling = createSapling(key, grower, ground);
        NONSET_WOOD.add(sapling.getBlockSupplier());
        NONSET_WOOD.add(sapling.getPottedBlockSupplier());
        return sapling;
    }
    
    protected static PottedBlock createSapling(String key, Supplier<TreeGrower> grower, TagKey<Block> ground) {
        Supplier<SaplingBlock> sapling = registerBlockItem(key + "_sapling", () -> new BWGSaplingBlock(ground, grower.get(), key + "_sapling"));
        return new PottedBlock(sapling, register("potted_" + key + "_sapling", PlatformHandler.PLATFORM_HANDLER.createPottedBlock(sapling, "potted_" + key + "_sapling")));
    }

    private static <B extends Block> Supplier<B> registerNonSetBlockItem(String key, Supplier<B> blockSupplier) {
        Supplier<B> block = register(key, blockSupplier);
        NONSET_WOOD.add(block);
        registerItem(key, () -> new BlockItem(block.get(), new Item.Properties().setId(keyItem(key))));
        return block;
    } 
    
    protected static <B extends Block> Supplier<B> registerBlockItem(String key, Supplier<B> blockSupplier) {
        Supplier<B> block = register(key, blockSupplier);
        registerItem(key, () -> new BlockItem(block.get(), new Item.Properties().setId(keyItem(key))));
        return block;
    }

    protected static <B extends Block> Supplier<B> register(String id, Supplier<B> blockSupplier) {
        Supplier<B> block = BWGBlocks.register(id, blockSupplier);
        WOOD.add(block);
        return block;
    }

    protected static <I extends Item> Supplier<I> registerItem(String id, Supplier<I> item) {
        Supplier<I> supplier = BWGItems.register(id, item);
        WOOD_BLOCK_ITEMS.add(supplier);
        return supplier;
    }

    private static ResourceKey<Block> key(String name) {
        return BiomesWeveGone.key(Registries.BLOCK, name);
    }

    private static ResourceKey<Item> keyItem(String name) {
        return BiomesWeveGone.key(Registries.ITEM, name);
    }

    public static void wood() {
        BiomesWeveGone.LOGGER.info("Registering Oh The Biomes We've Gone Wood");
    }
}
