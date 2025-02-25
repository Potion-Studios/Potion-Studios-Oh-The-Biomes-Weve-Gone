package net.potionstudios.biomeswevegone.neoforge.datagen.generators;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.*;
import net.minecraft.tags.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.tags.BWGBiomeTags;
import net.potionstudios.biomeswevegone.tags.BWGBlockTags;
import net.potionstudios.biomeswevegone.tags.BWGItemTags;
import net.potionstudios.biomeswevegone.tags.BWGStructureTags;
import net.potionstudios.biomeswevegone.world.damagesource.BWGDamageTypes;
import net.potionstudios.biomeswevegone.world.entity.BWGEntities;
import net.potionstudios.biomeswevegone.world.entity.ai.village.poi.BWGPoiTypes;
import net.potionstudios.biomeswevegone.world.item.BWGItems;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.block.plants.vegetation.GlowCaneBlock;
import net.potionstudios.biomeswevegone.world.level.block.sand.BWGSandSet;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWood;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWoodSet;
import net.potionstudios.biomeswevegone.world.level.levelgen.biome.BWGBiomes;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.BWGStructures;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sereneseasons.init.ModTags;

import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * Used to generate tags for blocks and items.
 * @author Joseph T. McQuigg
 */
public class TagsGenerator {

    public static void init(DataGenerator generator, boolean run, PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper helper) {
        BlockTagGenerator BlockTags = generator.addProvider(run, new BlockTagGenerator(output, lookupProvider, helper));
        generator.addProvider(run, new ItemTagGenerator(output, lookupProvider, BlockTags, helper));
        generator.addProvider(run, new BiomeTagGenerator(output, lookupProvider, helper));
        generator.addProvider(run, new StructureTagGenerator(output, lookupProvider, helper));
        generator.addProvider(run, new EntityTypeTagGenerator(output, lookupProvider, helper));
        generator.addProvider(run, new PoiTagGenerator(output, lookupProvider, helper));
        generator.addProvider(run, new DamageTypeTagGenerator(output, lookupProvider, helper));
    }

    private static void sortTagsAlphabeticallyAndRemoveDuplicateTagEntries(Map<?, TagBuilder> tags) {
        for (TagBuilder value : tags.values()) {
            List<TagEntry> builderEntries = value.entries;

            Set<TagEntry> noDuplicates = new HashSet<>(builderEntries);
            builderEntries.clear();
            builderEntries.addAll(noDuplicates);
            builderEntries.sort(Comparator.comparing(TagEntry::toString));
        }
    }


    /**
     * Used to generate tags for blocks.
     * @see BlockTagsProvider
     */
    private static class BlockTagGenerator extends BlockTagsProvider {
        private BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, BiomesWeveGone.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.@NotNull Provider provider) {
            BWGBlocks.BLOCKS.forEach(block -> easyBlockTags(block.get()));
            BWGWoodSet.woodsets().forEach(set -> {
                tag(BlockTags.PLANKS).add(set.planks());
                tag(BlockTags.WOODEN_SLABS).add(set.slab());
                tag(BlockTags.WOODEN_STAIRS).add(set.stairs());
                tag(BlockTags.WOODEN_BUTTONS).add(set.button());
                tag(BlockTags.WOODEN_PRESSURE_PLATES).add(set.pressurePlate());
                tag(BlockTags.WOODEN_TRAPDOORS).add(set.trapdoor());
                tag(BlockTags.WOODEN_DOORS).add(set.door());
                tag(BlockTags.WOODEN_FENCES).add(set.fence());
                tag(BlockTags.FENCE_GATES).add(set.fenceGate());
                tag(Tags.Blocks.FENCE_GATES_WOODEN).add(set.fenceGate());
                tag(BlockTags.STANDING_SIGNS).add(set.sign());
                tag(BlockTags.WALL_SIGNS).add(set.wallSign());
                tag(BlockTags.CEILING_HANGING_SIGNS).add(set.hangingSign());
                tag(BlockTags.WALL_HANGING_SIGNS).add(set.wallHangingSign());
                tag(Tags.Blocks.BOOKSHELVES).add(set.bookshelf());
                tag(BlockTags.ENCHANTMENT_POWER_PROVIDER).add(set.bookshelf());
                tag(set.logBlockTag()).add(set.logstem(), set.wood(), set.strippedLogStem(), set.strippedWood());
                tag(Tags.Blocks.STRIPPED_LOGS).add(set.strippedLogStem());
                tag(Tags.Blocks.STRIPPED_WOODS).add(set.strippedWood());
                tag(BlockTags.OVERWORLD_NATURAL_LOGS).add(set.logstem());
                tag(BlockTags.LOGS).addTag(set.logBlockTag());
                tag(BlockTags.LOGS_THAT_BURN).addTag(set.logBlockTag());
                tag(Tags.Blocks.PLAYER_WORKSTATIONS_CRAFTING_TABLES).add(set.craftingTable());
            });

            tag(BWGBlockTags.PALO_VERDE_LOGS).add(BWGWood.PALO_VERDE_LOG.get(), BWGWood.STRIPPED_PALO_VERDE_LOG.get(), BWGWood.PALO_VERDE_WOOD.get(), BWGWood.STRIPPED_PALO_VERDE_WOOD.get());
            tag(BlockTags.LOGS).addTag(BWGBlockTags.PALO_VERDE_LOGS);
            tag(BlockTags.LOGS_THAT_BURN).addTag(BWGBlockTags.PALO_VERDE_LOGS);

            BWGWood.WOOD.forEach(blocks -> {
                if (blocks.get() instanceof FlowerPotBlock)
                    tag(BlockTags.FLOWER_POTS).add(blocks.get());
                else if (blocks.get() instanceof SaplingBlock)
                    tag(BlockTags.SAPLINGS).add(blocks.get());
                else if (blocks.get() instanceof LeavesBlock) {
                    tag(BlockTags.MINEABLE_WITH_HOE).add(blocks.get());
                    tag(BlockTags.LEAVES).add(blocks.get());
                }
                else tag(BlockTags.MINEABLE_WITH_AXE).add(blocks.get());
            });
            BWGSandSet.getSandSets().forEach(set -> {
                tag(set.getSandstoneBlocksTag()).add(set.getSandstone(), set.getChiseledSandstone(), set.getCutSandstone(), set.getSmoothSandstone());
                tag(set.getSandstoneSlabsTag()).add(set.getSandstoneSlab(), set.getSmoothSandstoneSlab(), set.getCutSandstoneSlab());
                tag(set.getSandstoneStairsTag()).add(set.getSandstoneStairs(), set.getSmoothSandstoneStairs());
                tag(set.getSandBlockTag()).add(set.getSand());
                tag(Tags.Blocks.SANDSTONE_BLOCKS).addTag(set.getSandstoneBlocksTag());
                tag(Tags.Blocks.SANDS).addTag(set.getSandBlockTag());
                tag(Tags.Blocks.SANDSTONE_SLABS).addTag(set.getSandstoneSlabsTag());
                tag(Tags.Blocks.SANDSTONE_STAIRS).addTag(set.getSandstoneStairsTag());
            });
            tag(BWGBlockTags.TALL_ALLIUMS).add(BWGBlocks.TALL_ALLIUM.get(), BWGBlocks.TALL_PINK_ALLIUM.get(), BWGBlocks.TALL_WHITE_ALLIUM.get());
            tag(BWGBlockTags.SHORT_ALLIUMS).add(Blocks.ALLIUM, BWGBlocks.PINK_ALLIUM.getBlock(), BWGBlocks.WHITE_ALLIUM.getBlock());
            tag(BWGBlockTags.ALLIUM_FLOWER_BUSHES).add(BWGBlocks.ALLIUM_FLOWER_BUSH.getBlock(), BWGBlocks.PINK_ALLIUM_FLOWER_BUSH.getBlock(), BWGBlocks.WHITE_ALLIUM_FLOWER_BUSH.getBlock());
            tag(BWGBlockTags.ALLIUMS).addTag(BWGBlockTags.TALL_ALLIUMS).addTag(BWGBlockTags.SHORT_ALLIUMS).addTag(BWGBlockTags.ALLIUM_FLOWER_BUSHES);
            tag(BWGBlockTags.ROSES).add(BWGBlocks.ROSE.getBlock(), BWGBlocks.OSIRIA_ROSE.getBlock(), BWGBlocks.BLACK_ROSE.getBlock(), BWGBlocks.CYAN_ROSE.getBlock(), BWGBlocks.WINTER_ROSE.getBlock());
            tag(BWGBlockTags.TULIPS).add(Blocks.ORANGE_TULIP, Blocks.PINK_TULIP, Blocks.RED_TULIP, Blocks.WHITE_TULIP, BWGBlocks.CYAN_TULIP.getBlock(), BWGBlocks.GREEN_TULIP.getBlock(), BWGBlocks.MAGENTA_TULIP.getBlock(), BWGBlocks.PURPLE_TULIP.getBlock(), BWGBlocks.YELLOW_TULIP.getBlock());
            tag(BWGBlockTags.AMARANTH).add(BWGBlocks.AMARANTH.getBlock(), BWGBlocks.CYAN_AMARANTH.getBlock(), BWGBlocks.MAGENTA_AMARANTH.getBlock(), BWGBlocks.ORANGE_AMARANTH.getBlock(), BWGBlocks.PURPLE_AMARANTH.getBlock());
            tag(BWGBlockTags.SAGES).add(BWGBlocks.BLUE_SAGE.getBlock(), BWGBlocks.PURPLE_SAGE.getBlock(), BWGBlocks.WHITE_SAGE.getBlock());
            tag(BWGBlockTags.DAFFODILS).add(BWGBlocks.DAFFODIL.getBlock(), BWGBlocks.PINK_DAFFODIL.getBlock(), BWGBlocks.YELLOW_DAFFODIL.getBlock());
            tag(BlockTags.SMALL_FLOWERS).addTag(BWGBlockTags.SHORT_ALLIUMS).addTag(BWGBlockTags.ROSES).addTag(BWGBlockTags.TULIPS).addTag(BWGBlockTags.AMARANTH).addTag(BWGBlockTags.SAGES).addTag(BWGBlockTags.DAFFODILS)
                    .add(BWGBlocks.PINK_ANEMONE.getBlock(), BWGBlocks.WHITE_ANEMONE.getBlock(), BWGBlocks.ALPINE_BELLFLOWER.getBlock(), BWGBlocks.LAZARUS_BELLFLOWER.getBlock(), BWGBlocks.PEACH_LEATHER_FLOWER.getBlock(),
                            BWGBlocks.VIOLET_LEATHER_FLOWER.getBlock(), BWGBlocks.ANGELICA.getBlock(), BWGBlocks.BEGONIA.getBlock(), BWGBlocks.BISTORT.getBlock(), BWGBlocks.CALIFORNIA_POPPY.getBlock(), BWGBlocks.CROCUS.getBlock(),
                            BWGBlocks.FAIRY_SLIPPER.getBlock(), BWGBlocks.GUZMANIA.getBlock(), BWGBlocks.INCAN_LILY.getBlock(), BWGBlocks.IRIS.getBlock(), BWGBlocks.KOVAN_FLOWER.getBlock(), BWGBlocks.LOLLIPOP_FLOWER.getBlock(),
                            BWGBlocks.ORANGE_DAISY.getBlock(), BWGBlocks.PROTEA_FLOWER.getBlock(), BWGBlocks.PROTEA_FLOWER.getBlock(), BWGBlocks.SILVER_VASE_FLOWER.getBlock(), BWGBlocks.RICHEA.getBlock(), BWGBlocks.SNOWDROPS.getBlock(),
                            BWGBlocks.WINTER_CYCLAMEN.getBlock(), BWGBlocks.WINTER_SCILLA.getBlock());
            tag(BlockTags.FLOWERS).add(BWGBlocks.FLOWER_PATCH.get(), BWGBlocks.WHITE_SAKURA_PETALS.get(), BWGBlocks.YELLOW_SAKURA_PETALS.get(), BWGBlocks.FLOWERING_BARREL_CACTUS.get(), BWGBlocks.FLOWERING_TINY_LILY_PADS.get(),
                    BWGBlocks.FLOWERING_JACARANDA_BUSH.get(), BWGBlocks.FLOWERING_INDIGO_JACARANDA_BUSH.get(), BWGWood.FLOWERING_PALO_VERDE_LEAVES.get(), BWGWood.FLOWERING_SKYRIS_LEAVES.get(), BWGWood.FLOWERING_IRONWOOD_LEAVES.get(),
                    BWGWood.FLOWERING_INDIGO_JACARANDA_LEAVES.get(), BWGWood.FLOWERING_JACARANDA_LEAVES.get(), BWGWood.FLOWERING_YUCCA_LEAVES.get(), BWGWood.FLOWERING_ORCHARD_LEAVES.get(), BWGWood.FLOWERING_BAOBAB_LEAVES.get());

            tag(BWGBlockTags.RED_ROCK_BRICKS).add(BWGBlocks.RED_ROCK_BRICKS_SET.getBase(), BWGBlocks.MOSSY_RED_ROCK_BRICKS_SET.getBase(), BWGBlocks.CRACKED_RED_ROCK_BRICKS_SET.getBase(), BWGBlocks.CHISELED_RED_ROCK_BRICKS_SET.getBase());
            tag(BWGBlockTags.GLOW_BOTTLE).add(BWGBlocks.BLUE_GLOW_BOTTLE.get(), BWGBlocks.GREEN_GLOW_BOTTLE.get(), BWGBlocks.RED_GLOW_BOTTLE.get(), BWGBlocks.YELLOW_GLOW_BOTTLE.get());

            tag(BWGBlockTags.BLACK_ICE).add(BWGBlocks.BLACK_ICE.get(), BWGBlocks.PACKED_BLACK_ICE.get());
            tag(BWGBlockTags.BOREALIS_ICE).add(BWGBlocks.BOREALIS_ICE.get(), BWGBlocks.PACKED_BOREALIS_ICE.get());
            tag(BlockTags.SNOW_LAYER_CANNOT_SURVIVE_ON).addTag(BWGBlockTags.BLACK_ICE).addTag(BWGBlockTags.BOREALIS_ICE);
            tag(BlockTags.ICE).addTag(BWGBlockTags.BLACK_ICE).addTag(BWGBlockTags.BOREALIS_ICE);
            tag(BlockTags.WOOL_CARPETS).add(BWGBlocks.CATTAIL_THATCH_CARPET.get());
            tag(BlockTags.MUSHROOM_GROW_BLOCK).add(BWGBlocks.PODZOL_DACITE.get());
            tag(BWGBlockTags.SNOWY_PLANT_PLACEABLE).add(Blocks.SNOW_BLOCK, Blocks.POWDER_SNOW).addTag(BlockTags.DIRT);
            tag(BWGBlockTags.BWG_MUSHROOM_PLACEABLE).addTag(BlockTags.MUSHROOM_GROW_BLOCK).addTag(BlockTags.BASE_STONE_OVERWORLD).addTag(BlockTags.DIRT);
            tag(BWGBlockTags.HYDRANGEA_BUSH_PLACEABLE).addTag(BlockTags.DIRT);
            tag(BlockTags.DIRT).add(BWGBlocks.LUSH_GRASS_BLOCK.get(), BWGBlocks.SANDY_DIRT.get(), BWGBlocks.PALE_MUD.get(), BWGBlocks.LUSH_DIRT.get(), BWGBlocks.PEAT.get(), BWGBlocks.OVERGROWN_DACITE.get(), BWGBlocks.OVERGROWN_STONE.get(), BWGBlocks.PODZOL_DACITE.get());
            tag(BlockTags.CLIMBABLE).add(BWGBlocks.SKYRIS_VINE.get()).add(BWGBlocks.POISON_IVY.get());
            tag(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
                    .add(BWGBlocks.OVERGROWN_DACITE.get(), BWGBlocks.OVERGROWN_STONE.get(), BWGBlocks.PODZOL_DACITE.get(), BWGBlocks.MOSSY_STONE_SET.getBase(), BWGBlocks.LUSH_DIRT.get(), BWGBlocks.SANDY_DIRT.get(), BWGBlocks.PEAT.get(),
                            BWGBlocks.RED_ROCK_SET.getBase(), BWGBlocks.DACITE_SET.getBase(), BWGBlocks.ROCKY_STONE_SET.getBase(), BWGBlocks.PACKED_BLACK_ICE.get(), BWGBlocks.PACKED_BOREALIS_ICE.get(),
                            BWGBlocks.WINDSWEPT_SAND_SET.getSandstone(), BWGBlocks.BLACK_SAND_SET.getSandstone(), BWGBlocks.WHITE_SAND_SET.getSandstone(), BWGBlocks.BLUE_SAND_SET.getSandstone(), BWGBlocks.PURPLE_SAND_SET.getSandstone(), BWGBlocks.PINK_SAND_SET.getSandstone());
            tag(BlockTags.CROPS).add(BWGBlocks.ODDION_CROP.get(), BWGBlocks.PALE_PUMPKIN_STEM.get());
            tag(BlockTags.LOGS).add(BWGWood.SPIRIT_ROOTS.get());
            tag(BlockTags.LOGS_THAT_BURN).add(BWGWood.SPIRIT_ROOTS.get());
            tag(BlockTags.BEE_GROWABLES).add(BWGBlocks.BLUEBERRY_BUSH.get());
            tag(BlockTags.SNIFFER_DIGGABLE_BLOCK).add(BWGBlocks.LUSH_DIRT_PATH.get(), BWGBlocks.SANDY_DIRT.get(), BWGBlocks.SANDY_DIRT_PATH.get(), BWGBlocks.LUSH_DIRT.get(), BWGBlocks.LUSH_GRASS_BLOCK.get(), BWGBlocks.PEAT.get());
            tag(BlockTags.SNIFFER_EGG_HATCH_BOOST).add(BWGBlocks.LUSH_GRASS_BLOCK.get());
            tag(BlockTags.MAINTAINS_FARMLAND).add(BWGBlocks.ODDION_CROP.get(), BWGBlocks.ATTACHED_PALE_PUMPKIN_STEM.get(), BWGBlocks.PALE_PUMPKIN_STEM.get());
            tag(BlockTags.MINEABLE_WITH_HOE).add(BWGBlocks.ALLIUM_PETAL_BLOCK.get(), BWGBlocks.PINK_ALLIUM_PETAL_BLOCK.get(), BWGBlocks.WHITE_ALLIUM_PETAL_BLOCK.get(), BWGBlocks.ROSE_PETAL_BLOCK.get());
            tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(BWGBlockTags.BLACK_ICE).addTag(BWGBlockTags.BOREALIS_ICE);
            tag(BlockTags.REPLACEABLE).add(BWGBlocks.PRAIRIE_GRASS.get(), BWGBlocks.TALL_PRAIRIE_GRASS.get(), BWGBlocks.BEACH_GRASS.get(), BWGBlocks.TALL_BEACH_GRASS.get(), BWGBlocks.SKYRIS_VINE.get());
            tag(BlockTags.SAND).add(BWGBlocks.SANDY_DIRT.get(), BWGBlocks.CRACKED_SAND.get());
            tag(Tags.Blocks.BUDDING_BLOCKS).add(BWGWood.IMBUED_BLUE_ENCHANTED_WOOD.get(), BWGWood.IMBUED_GREEN_ENCHANTED_WOOD.get());
            tag(Tags.Blocks.VILLAGER_JOB_SITES).add(BWGBlocks.FORAGERS_TABLE.get());
            tag(Tags.Blocks.VILLAGER_FARMLANDS).add(BWGBlocks.LUSH_FARMLAND.get());
            tag(BlockTags.ENDERMAN_HOLDABLE).add(BWGBlocks.PALE_PUMPKIN.get(), BWGBlocks.CARVED_PALE_PUMPKIN.get(),
                    BWGBlocks.WINDSWEPT_SAND_SET.getSand(), BWGBlocks.BLACK_SAND_SET.getSand(), BWGBlocks.WHITE_SAND_SET.getSand(), BWGBlocks.BLUE_SAND_SET.getSand(), BWGBlocks.PURPLE_SAND_SET.getSand(), BWGBlocks.PINK_SAND_SET.getSand(),
                    BWGBlocks.GREEN_MUSHROOM.get(), BWGBlocks.WEEPING_MILKCAP.get());
            tag(BlockTags.SWORD_EFFICIENT).add(BWGBlocks.PALE_PUMPKIN.get(), BWGBlocks.CARVED_PALE_PUMPKIN.get(), BWGBlocks.ATTACHED_PALE_PUMPKIN_STEM.get(), BWGBlocks.PALE_JACK_O_LANTERN.get());
            tag(BlockTags.FALL_DAMAGE_RESETTING).add(BWGBlocks.BLUEBERRY_BUSH.get());


            tag(BWGBlockTags.STORAGE_BLOCKS_ALLIUM).add(BWGBlocks.ALLIUM_PETAL_BLOCK.get());
            tag(BWGBlockTags.STORAGE_BLOCKS_PINK_ALLIUM).add(BWGBlocks.PINK_ALLIUM_PETAL_BLOCK.get());
            tag(BWGBlockTags.STORAGE_BLOCKS_WHITE_ALLIUM).add(BWGBlocks.WHITE_ALLIUM_PETAL_BLOCK.get());
            tag(BWGBlockTags.STORAGE_BLOCKS_ROSE).add(BWGBlocks.ROSE_PETAL_BLOCK.get());
            tag(Tags.Blocks.STORAGE_BLOCKS).addTag(BWGBlockTags.STORAGE_BLOCKS_ALLIUM).addTag(BWGBlockTags.STORAGE_BLOCKS_PINK_ALLIUM).addTag(BWGBlockTags.STORAGE_BLOCKS_WHITE_ALLIUM).addTag(BWGBlockTags.STORAGE_BLOCKS_ROSE);
            // Entity Integration Tags
            tag(BlockTags.ANIMALS_SPAWNABLE_ON).add(BWGBlocks.LUSH_GRASS_BLOCK.get(), BWGBlocks.LUSH_DIRT.get(), BWGBlocks.PODZOL_DACITE.get(), BWGBlocks.OVERGROWN_STONE.get(), BWGBlocks.OVERGROWN_DACITE.get(), BWGBlocks.PEAT.get());
            tag(BlockTags.FROGS_SPAWNABLE_ON).add(BWGBlocks.LUSH_GRASS_BLOCK.get(), BWGBlocks.PALE_MUD.get(), BWGWood.SPIRIT_ROOTS.get());
            tag(BlockTags.PARROTS_SPAWNABLE_ON).add(BWGBlocks.LUSH_GRASS_BLOCK.get());
            tag(BlockTags.POLAR_BEARS_SPAWNABLE_ON_ALTERNATE).add(BWGBlocks.BLACK_ICE.get(), BWGBlocks.BOREALIS_ICE.get());
            tag(BlockTags.RABBITS_SPAWNABLE_ON).add(BWGBlocks.LUSH_GRASS_BLOCK.get(), BWGBlocks.RED_ROCK_SET.getBase());
            tag(BlockTags.FOXES_SPAWNABLE_ON).add(BWGBlocks.LUSH_GRASS_BLOCK.get(), BWGBlocks.PODZOL_DACITE.get());
            tag(BlockTags.WOLVES_SPAWNABLE_ON).add(BWGBlocks.LUSH_GRASS_BLOCK.get(), BWGBlocks.PODZOL_DACITE.get());

            tag(BlockTags.FROG_PREFER_JUMP_TO).add(BWGBlocks.FLOWERING_TINY_LILY_PADS.get(), BWGBlocks.TINY_LILY_PADS.get());
            tag(BlockTags.SNAPS_GOAT_HORN).add(BWGBlocks.PACKED_BLACK_ICE.get(), BWGBlocks.PACKED_BOREALIS_ICE.get());

            tag(BWGBlockTags.OAK_SAPLINGS).add(BWGWood.BROWN_OAK_SAPLING.getBlock(), BWGWood.ORANGE_OAK_SAPLING.getBlock(), BWGWood.RED_OAK_SAPLING.getBlock());
            tag(BWGBlockTags.SPRUCE_SAPLINGS).add(BWGWood.BLUE_SPRUCE_SAPLING.getBlock(), BWGWood.RED_SPRUCE_SAPLING.getBlock(), BWGWood.YELLOW_SPRUCE_SAPLING.getBlock(), BWGWood.ORANGE_SPRUCE_SAPLING.getBlock());
            tag(BWGBlockTags.BIRCH_SAPLINGS).add(BWGWood.YELLOW_BIRCH_SAPLING.getBlock(), BWGWood.ORANGE_BIRCH_SAPLING.getBlock(), BWGWood.RED_BIRCH_SAPLING.getBlock(), BWGWood.BROWN_BIRCH_SAPLING.getBlock());

            //Serene Seasons
            tag(ModTags.Blocks.YEAR_ROUND_CROPS).add(BWGBlocks.GREEN_MUSHROOM.get(), BWGBlocks.WEEPING_MILKCAP.get(), BWGBlocks.WOOD_BLEWIT.get(), BWGWood.ASPEN.sapling().getBlock(), BWGWood.RED_MAPLE_SAPLING.getBlock())
                    .addTag(BWGBlockTags.OAK_SAPLINGS);
            tag(ModTags.Blocks.AUTUMN_CROPS).add(BWGBlocks.PALE_PUMPKIN_STEM.get(), BWGWood.CYPRESS.sapling().getBlock(), BWGWood.EBONY.sapling().getBlock(), BWGWood.FIR.sapling().getBlock(), BWGWood.HOLLY.sapling().getBlock(), BWGWood.JACARANDA.sapling().getBlock(), BWGWood.INDIGO_JACARANDA_SAPLING.getBlock(), BWGWood.MAPLE.sapling().getBlock(), BWGWood.SILVER_MAPLE_SAPLING.getBlock(), BWGWood.PINE.sapling().getBlock(),
                            BWGWood.WILLOW.sapling().getBlock(), BWGWood.WITCH_HAZEL.sapling().getBlock(), BWGWood.ZELKOVA.sapling().getBlock(), BWGWood.BROWN_ZELKOVA_SAPLING.getBlock(), BWGWood.IRONWOOD.sapling().getBlock(), BWGWood.SKYRIS.sapling().getBlock(), BWGWood.CIKA.sapling().getBlock(), BWGWood.ORCHARD_SAPLING.getBlock(), BWGWood.YUCCA_SAPLING.getBlock())
                    .addTag(BWGBlockTags.SPRUCE_SAPLINGS).addTag(BWGBlockTags.BIRCH_SAPLINGS);
            tag(ModTags.Blocks.SPRING_CROPS).add(BWGBlocks.ODDION_CROP.get(), BWGBlocks.BLUEBERRY_BUSH.get(), BWGWood.YELLOW_SAKURA_SAPLING.getBlock(), BWGWood.WHITE_SAKURA_SAPLING.getBlock(), BWGWood.CYPRESS.sapling().getBlock(), BWGWood.FIR.sapling().getBlock(), BWGWood.EBONY.sapling().getBlock(), BWGWood.HOLLY.sapling().getBlock(),
                            BWGWood.JACARANDA.sapling().getBlock(), BWGWood.INDIGO_JACARANDA_SAPLING.getBlock(), BWGWood.MAHOGANY.sapling().getBlock(), BWGWood.MAPLE.sapling().getBlock(), BWGWood.SILVER_MAPLE_SAPLING.getBlock(), BWGWood.PALM.sapling().getBlock(), BWGWood.PINE.sapling().getBlock(), BWGWood.REDWOOD.sapling().getBlock(), BWGWood.WHITE_MANGROVE.sapling().getBlock(), BWGWood.WILLOW.sapling().getBlock(),
                            BWGWood.WITCH_HAZEL.sapling().getBlock(), BWGWood.ZELKOVA.sapling().getBlock(), BWGWood.BROWN_ZELKOVA_SAPLING.getBlock(), BWGWood.IRONWOOD.sapling().getBlock(), BWGWood.RAINBOW_EUCALYPTUS.sapling().getBlock(), BWGWood.ARAUCARIA_SAPLING.getBlock(), BWGWood.ORCHARD_SAPLING.getBlock(), BWGWood.YUCCA_SAPLING.getBlock(), BWGWood.SPIRIT.sapling().getBlock())
                    .addTag(BWGBlockTags.GLOWCANE).addTag(BWGBlockTags.SPRUCE_SAPLINGS).addTag(BWGBlockTags.BIRCH_SAPLINGS);
            tag(ModTags.Blocks.SUMMER_CROPS).add(BWGBlocks.ODDION_CROP.get(), BWGBlocks.BLUEBERRY_BUSH.get(), BWGWood.BAOBAB.sapling().getBlock(), BWGWood.JACARANDA.sapling().getBlock(), BWGWood.INDIGO_JACARANDA_SAPLING.getBlock(), BWGWood.MAHOGANY.sapling().getBlock(), BWGWood.PALM.sapling().getBlock(), BWGWood.REDWOOD.sapling().getBlock(), BWGWood.WHITE_MANGROVE.sapling().getBlock(),
                            BWGWood.PALO_VERDE_SAPLING.getBlock(), BWGWood.RAINBOW_EUCALYPTUS.sapling().getBlock(), BWGWood.GREEN_ENCHANTED.sapling().getBlock(), BWGWood.BLUE_ENCHANTED.sapling().getBlock(), BWGWood.ARAUCARIA_SAPLING.getBlock(), BWGWood.ORCHARD_SAPLING.getBlock(), BWGWood.YUCCA_SAPLING.getBlock(), BWGWood.SPIRIT.sapling().getBlock())
                    .addTag(BWGBlockTags.GLOWCANE);
            tag(ModTags.Blocks.WINTER_CROPS).add(BWGWood.SKYRIS.sapling().getBlock()).addTag(BWGBlockTags.SPRUCE_SAPLINGS);

            sortTagsAlphabeticallyAndRemoveDuplicateTagEntries(this.builders);
        }

        private void easyBlockTags(Block object) {
            if (object instanceof SlabBlock) tag(BlockTags.SLABS).add(object);
            else if (object instanceof StairBlock) tag(BlockTags.STAIRS).add(object);
            else if (object instanceof WallBlock) tag(BlockTags.WALLS).add(object);
            else if (object instanceof ColoredFallingBlock) tag(BlockTags.SAND).add(object);
            //else if (object instanceof FlowerBlock) tag(BlockTags.SMALL_FLOWERS).add(object);
            else if (object instanceof TallFlowerBlock) tag(BlockTags.TALL_FLOWERS).add(object);
            else if (object instanceof LeavesBlock) tag(BlockTags.LEAVES).add(object);
            else if (object instanceof CampfireBlock) tag(BlockTags.CAMPFIRES).add(object);
            else if (object instanceof FlowerPotBlock) tag(BlockTags.FLOWER_POTS).add(object);
            else if (object instanceof GlowCaneBlock) tag(BWGBlockTags.GLOWCANE).add(object);
            SoundType type = object.defaultBlockState().getSoundType();
            if (type == SoundType.STONE || type == SoundType.DEEPSLATE)
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(object);
            else if (type == SoundType.WOOD || type == SoundType.SWEET_BERRY_BUSH)
                tag(BlockTags.MINEABLE_WITH_AXE).add(object);
            else if (type == SoundType.GRAVEL || type == SoundType.SAND || type == SoundType.SNOW || type == SoundType.GRASS)
                if (object instanceof LeavesBlock) tag(BlockTags.MINEABLE_WITH_HOE).add(object);
                else tag(BlockTags.MINEABLE_WITH_SHOVEL).add(object);
        }
    }

    /**
     * Used to generate tags for items.
     * @see ItemTagsProvider
     */
    private static class ItemTagGenerator extends ItemTagsProvider {

        private ItemTagGenerator(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, BlockTagGenerator blockTagGenerator, @Nullable ExistingFileHelper existingFileHelper) {
            super(arg, completableFuture, blockTagGenerator.contentsGetter(), BiomesWeveGone.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.@NotNull Provider provider) {
            BWGWoodSet.woodsets().forEach(set -> {
                copy(set.logBlockTag(), set.logItemTag());
                tag(ItemTags.BOATS).add(set.boatItem().get());
                tag(ItemTags.CHEST_BOATS).add(set.chestBoatItem().get());
            });
            copy(BlockTags.SLABS, ItemTags.SLABS);
            copy(BlockTags.STAIRS, ItemTags.STAIRS);
            copy(BlockTags.WALLS, ItemTags.WALLS);
            //copy(BlockTags.BUTTONS, ItemTags.BUTTONS);
            copy(BlockTags.PLANKS, ItemTags.PLANKS);
            copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
            copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
            copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
            copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
            copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
            copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
            copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
            copy(BlockTags.FENCE_GATES, ItemTags.FENCE_GATES);
            copy(Tags.Blocks.FENCE_GATES_WOODEN, Tags.Items.FENCE_GATES_WOODEN);
            copy(BlockTags.STANDING_SIGNS, ItemTags.SIGNS);
            copy(BlockTags.CEILING_HANGING_SIGNS, ItemTags.HANGING_SIGNS);
            copy(Tags.Blocks.BOOKSHELVES, Tags.Items.BOOKSHELVES);
            copy(BlockTags.LOGS, ItemTags.LOGS);
            copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
            copy(Tags.Blocks.STRIPPED_LOGS, Tags.Items.STRIPPED_LOGS);
            copy(Tags.Blocks.STRIPPED_WOODS, Tags.Items.STRIPPED_WOODS);
            copy(Tags.Blocks.PLAYER_WORKSTATIONS_CRAFTING_TABLES, Tags.Items.PLAYER_WORKSTATIONS_CRAFTING_TABLES);
            copy(BlockTags.SAND, ItemTags.SAND);
            copy(BWGBlockTags.ALLIUM_FLOWER_BUSHES, BWGItemTags.ALLIUM_FLOWER_BUSHES);
            copy(BWGBlockTags.SHORT_ALLIUMS, BWGItemTags.SHORT_ALLIUMS);
            copy(BWGBlockTags.TALL_ALLIUMS, BWGItemTags.TALL_ALLIUMS);
            copy(BWGBlockTags.ALLIUMS, BWGItemTags.ALLIUMS);
            copy(BWGBlockTags.ROSES, BWGItemTags.ROSES);
            copy(BWGBlockTags.TULIPS, BWGItemTags.TULIPS);
            copy(BWGBlockTags.AMARANTH, BWGItemTags.AMARANTH);
            copy(BWGBlockTags.SAGES, BWGItemTags.SAGES);
            copy(BWGBlockTags.DAFFODILS, BWGItemTags.DAFFODILS);
            copy(BWGBlockTags.STORAGE_BLOCKS_ALLIUM, BWGItemTags.STORAGE_BLOCKS_ALLIUM);
            copy(BWGBlockTags.STORAGE_BLOCKS_PINK_ALLIUM, BWGItemTags.STORAGE_BLOCKS_PINK_ALLIUM);
            copy(BWGBlockTags.STORAGE_BLOCKS_WHITE_ALLIUM, BWGItemTags.STORAGE_BLOCKS_WHITE_ALLIUM);
            copy(BWGBlockTags.STORAGE_BLOCKS_ROSE, BWGItemTags.STORAGE_BLOCKS_ROSE);
            copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);
            copy(BlockTags.FLOWERS, ItemTags.FLOWERS);
            copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
            copy(BlockTags.TALL_FLOWERS, ItemTags.TALL_FLOWERS);
            copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
            copy(BlockTags.LEAVES, ItemTags.LEAVES);
            copy(BlockTags.WOOL_CARPETS, ItemTags.WOOL_CARPETS);
            copy(BWGBlockTags.RED_ROCK_BRICKS, BWGItemTags.RED_ROCK_BRICKS);
            copy(BWGBlockTags.GLOW_BOTTLE, BWGItemTags.GLOW_BOTTLE);

            BWGSandSet.getSandSets().forEach(set -> {
                copy(set.getSandstoneBlocksTag(), set.getSandstoneBlocksItemTag());
                copy(set.getSandBlockTag(), set.getSandItemTag());
                copy(set.getSandstoneSlabsTag(), set.getSandstoneSlabsItemTag());
                copy(set.getSandstoneStairsTag(), set.getSandstoneStairsItemTag());
            });
            copy(Tags.Blocks.SANDSTONE_BLOCKS, Tags.Items.SANDSTONE_BLOCKS);
            copy(Tags.Blocks.SANDS, Tags.Items.SANDS);
            copy(BWGBlockTags.BLACK_ICE, BWGItemTags.BLACK_ICE);
            copy(BWGBlockTags.BOREALIS_ICE, BWGItemTags.BOREALIS_ICE);
            copy(Tags.Blocks.BUDDING_BLOCKS, Tags.Items.BUDDING_BLOCKS);
            copy(Tags.Blocks.VILLAGER_JOB_SITES, Tags.Items.VILLAGER_JOB_SITES);

            copy(BWGBlockTags.OAK_SAPLINGS, BWGItemTags.OAK_SAPLINGS);
            copy(BWGBlockTags.SPRUCE_SAPLINGS, BWGItemTags.SPRUCE_SAPLINGS);
            copy(BWGBlockTags.BIRCH_SAPLINGS, BWGItemTags.BIRCH_SAPLINGS);

            tag(BWGItemTags.GLOWCANE_POWDER).add(BWGItems.BLUE_GLOWCANE_POWDER.get(), BWGItems.GREEN_GLOWCANE_POWDER.get(), BWGItems.RED_GLOWCANE_POWDER.get(), BWGItems.YELLOW_GLOWCANE_POWDER.get());
            tag(BWGItemTags.GLOWCANE_SHOOT).add(BWGItems.BLUE_GLOWCANE_SHOOT.get(), BWGItems.GREEN_GLOWCANE_SHOOT.get(), BWGItems.RED_GLOWCANE_SHOOT.get(), BWGItems.YELLOW_GLOWCANE_SHOOT.get());
            copy(BWGBlockTags.PALO_VERDE_LOGS, BWGItemTags.PALO_VERDE_LOGS);
            tag(BWGItemTags.SHEARS).addOptionalTag(Tags.Items.TOOLS_SHEAR);
            tag(Tags.Items.MUSIC_DISCS).add(BWGItems.MUSIC_DISC_PIXIE_CLUB.get());
            tag(ItemTags.STONE_CRAFTING_MATERIALS).add(BWGBlocks.ROCKY_STONE_SET.getBase().asItem());
            tag(ItemTags.VILLAGER_PLANTABLE_SEEDS).add(BWGItems.ODDION_BULB.get());
            tag(Tags.Items.MUSHROOMS).add(BWGBlocks.GREEN_MUSHROOM.get().asItem(), BWGBlocks.WEEPING_MILKCAP.get().asItem(), BWGBlocks.WOOD_BLEWIT.get().asItem());
            tag(Tags.Items.BUCKETS_ENTITY_WATER).add(BWGItems.MAN_O_WAR_BUCKET.get());
            tag(Tags.Items.CROPS).add(BWGItems.ODDION_BULB.get());
            tag(Tags.Items.FOODS_FRUIT).add(BWGItems.GREEN_APPLE.get(), BWGItems.BAOBAB_FRUIT.get(), BWGItems.YUCCA_FRUIT.get(), BWGItems.SOUL_FRUIT.get());
            tag(Tags.Items.FOODS_BERRY).add(BWGItems.BLUEBERRIES.get());
            tag(Tags.Items.FOODS_SOUP).add(BWGItems.ALLIUM_ODDION_SOUP.get(), BWGItems.WHITE_PUFFBALL_STEW.get());
            tag(Tags.Items.FOODS_PIE).add(BWGItems.GREEN_APPLE_PIE.get(), BWGItems.BLUEBERRY_PIE.get());
            tag(Tags.Items.FOODS).add(BWGItems.COOKED_ODDION_BULB.get(), BWGItems.COOKED_WHITE_PUFFBALL_CAP.get(), BWGItems.COOKED_YUCCA_FRUIT.get(), BWGItems.ALOE_VERA_JUICE.get());
            tag(Tags.Items.SEEDS_PUMPKIN).add(BWGItems.PALE_PUMPKIN_SEEDS.get());
            tag(Tags.Items.CROPS_PUMPKIN).add(BWGBlocks.PALE_PUMPKIN.get().asItem());
            tag(ItemTags.EQUIPPABLE_ENCHANTABLE).add(BWGBlocks.CARVED_PALE_PUMPKIN.get().asItem());
            tag(ItemTags.VANISHING_ENCHANTABLE).add(BWGBlocks.CARVED_PALE_PUMPKIN.get().asItem());
            tag(ItemTags.PIGLIN_REPELLENTS).add(BWGItems.SOUL_FRUIT.get());


            //Serene Seasons
            tag(ModTags.Items.YEAR_ROUND_CROPS).add(BWGBlocks.GREEN_MUSHROOM.get().asItem(), BWGBlocks.WEEPING_MILKCAP.get().asItem(), BWGBlocks.WOOD_BLEWIT.get().asItem(), BWGWood.ASPEN.sapling().getBlock().asItem(), BWGWood.RED_MAPLE_SAPLING.getBlock().asItem()).addTag(BWGItemTags.OAK_SAPLINGS);
            tag(ModTags.Items.AUTUMN_CROPS).add(BWGItems.PALE_PUMPKIN_SEEDS.get(), BWGWood.CYPRESS.sapling().getBlock().asItem(), BWGWood.EBONY.sapling().getBlock().asItem(), BWGWood.FIR.sapling().getBlock().asItem(), BWGWood.HOLLY.sapling().getBlock().asItem(), BWGWood.JACARANDA.sapling().getBlock().asItem(), BWGWood.INDIGO_JACARANDA_SAPLING.getBlock().asItem(), BWGWood.MAPLE.sapling().getBlock().asItem(), BWGWood.SILVER_MAPLE_SAPLING.getBlock().asItem(), BWGWood.PINE.sapling().getBlock().asItem(),
                    BWGWood.WILLOW.sapling().getBlock().asItem(), BWGWood.WITCH_HAZEL.sapling().getBlock().asItem(), BWGWood.ZELKOVA.sapling().getBlock().asItem(), BWGWood.BROWN_ZELKOVA_SAPLING.getBlock().asItem(), BWGWood.IRONWOOD.sapling().getBlock().asItem(), BWGWood.SKYRIS.sapling().getBlock().asItem(), BWGWood.CIKA.sapling().getBlock().asItem(), BWGWood.ORCHARD_SAPLING.getBlock().asItem(), BWGWood.YUCCA_SAPLING.getBlock().asItem()).addTag(BWGItemTags.BIRCH_SAPLINGS).addTag(BWGItemTags.SPRUCE_SAPLINGS);
            tag(ModTags.Items.SPRING_CROPS).add(BWGItems.ODDION_BULB.get(), BWGWood.YELLOW_SAKURA_SAPLING.getBlock().asItem(), BWGWood.WHITE_SAKURA_SAPLING.getBlock().asItem(), BWGWood.CYPRESS.sapling().getBlock().asItem(), BWGWood.FIR.sapling().getBlock().asItem(), BWGWood.EBONY.sapling().getBlock().asItem(), BWGWood.HOLLY.sapling().getBlock().asItem(),
                    BWGWood.JACARANDA.sapling().getBlock().asItem(), BWGWood.INDIGO_JACARANDA_SAPLING.getBlock().asItem(), BWGWood.MAHOGANY.sapling().getBlock().asItem(), BWGWood.MAPLE.sapling().getBlock().asItem(), BWGWood.SILVER_MAPLE_SAPLING.getBlock().asItem(), BWGWood.PALM.sapling().getBlock().asItem(), BWGWood.PINE.sapling().getBlock().asItem(), BWGWood.REDWOOD.sapling().getBlock().asItem(), BWGWood.WHITE_MANGROVE.sapling().getBlock().asItem(), BWGWood.WILLOW.sapling().getBlock().asItem(),
                    BWGWood.WITCH_HAZEL.sapling().getBlock().asItem(), BWGWood.ZELKOVA.sapling().getBlock().asItem(), BWGWood.BROWN_ZELKOVA_SAPLING.getBlock().asItem(), BWGWood.IRONWOOD.sapling().getBlock().asItem(), BWGWood.RAINBOW_EUCALYPTUS.sapling().getBlock().asItem(), BWGWood.ARAUCARIA_SAPLING.getBlock().asItem(), BWGWood.ORCHARD_SAPLING.getBlock().asItem(), BWGWood.YUCCA_SAPLING.getBlock().asItem(), BWGWood.SPIRIT.sapling().getBlock().asItem()).addTag(BWGItemTags.GLOWCANE_SHOOT).addTag(BWGItemTags.BIRCH_SAPLINGS).addTag(BWGItemTags.SPRUCE_SAPLINGS);
            tag(ModTags.Items.SUMMER_CROPS).add(BWGItems.ODDION_BULB.get(), BWGItems.BLUEBERRIES.get(), BWGWood.BAOBAB.sapling().getBlock().asItem(), BWGWood.JACARANDA.sapling().getBlock().asItem(), BWGWood.INDIGO_JACARANDA_SAPLING.getBlock().asItem(), BWGWood.MAHOGANY.sapling().getBlock().asItem(), BWGWood.PALM.sapling().getBlock().asItem(), BWGWood.REDWOOD.sapling().getBlock().asItem(), BWGWood.WHITE_MANGROVE.sapling().getBlock().asItem(),
                    BWGWood.PALO_VERDE_SAPLING.getBlock().asItem(), BWGWood.RAINBOW_EUCALYPTUS.sapling().getBlock().asItem(), BWGWood.GREEN_ENCHANTED.sapling().getBlock().asItem(), BWGWood.BLUE_ENCHANTED.sapling().getBlock().asItem(), BWGWood.ARAUCARIA_SAPLING.getBlock().asItem(), BWGWood.ORCHARD_SAPLING.getBlock().asItem(), BWGWood.YUCCA_SAPLING.getBlock().asItem(), BWGWood.SPIRIT.sapling().getBlock().asItem()).addTag(BWGItemTags.GLOWCANE_SHOOT);
            tag(ModTags.Items.WINTER_CROPS).add(BWGWood.SKYRIS.sapling().getBlock().asItem()).addTag(BWGItemTags.SPRUCE_SAPLINGS);

            sortTagsAlphabeticallyAndRemoveDuplicateTagEntries(this.builders);
        }
    }


    private static class BiomeTagGenerator extends BiomeTagsProvider {
        private BiomeTagGenerator(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
            super(arg, completableFuture, BiomesWeveGone.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.@NotNull Provider provider) {
            BWGBiomes.BIOME_FACTORIES.keySet().stream().sorted().toList().forEach(biome -> {
                tag(BWGBiomeTags.OVERWORLD).add(biome);
                float temperature = provider.asGetterLookup().lookupOrThrow(Registries.BIOME).getOrThrow(biome).value().getBaseTemperature();
                if (temperature > 0.8F) tag(BWGBiomeTags.HOT).add(biome);
                else if (temperature < 0.5F) tag(BWGBiomeTags.COLD).add(biome);
                else tag(BWGBiomeTags.TEMPERATE).add(biome);
            });
            BWGBiomes.BIOMES_BY_TAG.forEach((tag, biome) -> tag(tag).add(biome));

            tag(BiomeTags.IS_OVERWORLD).addTag(BWGBiomeTags.OVERWORLD);

            tag(Tags.Biomes.IS_HOT_OVERWORLD).addTag(BWGBiomeTags.HOT);
            tag(Tags.Biomes.IS_COLD_OVERWORLD).addTag(BWGBiomeTags.COLD);
            tag(Tags.Biomes.IS_TEMPERATE_OVERWORLD).addTag(BWGBiomeTags.TEMPERATE);
            tag(Tags.Biomes.IS_WET_OVERWORLD).addTag(BWGBiomeTags.WET);
            tag(Tags.Biomes.IS_DRY_OVERWORLD).addTag(BWGBiomeTags.DRY);
            tag(Tags.Biomes.IS_SPARSE_VEGETATION_OVERWORLD).addTag(BWGBiomeTags.SPARSE);
            tag(Tags.Biomes.IS_DENSE_VEGETATION_OVERWORLD).addTag(BWGBiomeTags.DENSE);

            tag(Tags.Biomes.IS_PLAINS).addTag(BWGBiomeTags.PLAINS);
            tag(BiomeTags.IS_FOREST).addTag(BWGBiomeTags.FOREST);
            tag(BiomeTags.IS_TAIGA).addTag(BWGBiomeTags.TAIGA);
            tag(Tags.Biomes.IS_DESERT).addTag(BWGBiomeTags.DESERT);
            tag(BiomeTags.IS_SAVANNA).addTag(BWGBiomeTags.SAVANNA);
            tag(BiomeTags.IS_JUNGLE).addTag(BWGBiomeTags.JUNGLE);
            tag(BiomeTags.IS_MOUNTAIN).addTag(BWGBiomeTags.MOUNTAIN);
            tag(Tags.Biomes.IS_MOUNTAIN).addTag(BWGBiomeTags.MOUNTAIN);
            tag(BiomeTags.IS_BEACH).addTag(BWGBiomeTags.BEACH);
            tag(BiomeTags.IS_BADLANDS).addTag(BWGBiomeTags.BADLANDS);
            tag(Tags.Biomes.IS_MOUNTAIN_SLOPE).addTag(BWGBiomeTags.SLOPE);
            tag(Tags.Biomes.IS_MOUNTAIN_PEAK).addTag(BWGBiomeTags.PEAK);
            tag(Tags.Biomes.IS_SWAMP).addTag(BWGBiomeTags.SWAMP);
            tag(Tags.Biomes.IS_SANDY).addTag(BWGBiomeTags.SANDY);
            tag(Tags.Biomes.IS_WINDSWEPT).addTag(BWGBiomeTags.WINDSWEPT);
            tag(Tags.Biomes.IS_SNOWY).addTag(BWGBiomeTags.SNOWY);
            tag(Tags.Biomes.IS_ICY).addTag(BWGBiomeTags.ICY);
            tag(Tags.Biomes.IS_FLORAL).addTag(BWGBiomeTags.FLORAL);
            tag(Tags.Biomes.IS_DEAD).addTag(BWGBiomeTags.DEAD);
            tag(BiomeTags.IS_OCEAN).addTag(BWGBiomeTags.OCEAN);
            tag(Tags.Biomes.IS_CONIFEROUS_TREE).addTag(BWGBiomeTags.CONIFEROUS);
            tag(Tags.Biomes.IS_WASTELAND).addTag(BWGBiomeTags.WASTELAND);
            tag(Tags.Biomes.IS_MAGICAL).addTag(BWGBiomeTags.MAGICAL);

            tag(BWGBiomeTags.WET).addTag(BWGBiomeTags.SWAMP).addTag(BWGBiomeTags.BEACH);
            tag(BWGBiomeTags.DENSE).addTag(BWGBiomeTags.FOREST).addTag(BWGBiomeTags.JUNGLE).addTag(BWGBiomeTags.SWAMP);
            tag(BWGBiomeTags.DRY).addTag(BWGBiomeTags.DESERT);

            tag(BWGBiomeTags.SHARPENED_ROCKS).add(BWGBiomes.RUGGED_BADLANDS);
            tag(BWGBiomeTags.IRONWOOD_GOUR_PLATEAU).add(BWGBiomes.IRONWOOD_GOUR);
            tag(BWGBiomeTags.LARGE_COLD_LAKE).add(BWGBiomes.MAPLE_TAIGA, BWGBiomes.CRIMSON_TUNDRA, BWGBiomes.OVERGROWTH_WOODLANDS);

            /*
             * Structure Location Biome Tags
             */
            tag(BiomeTags.HAS_SWAMP_HUT).addTag(BWGBiomeTags.SWAMP);
            tag(BiomeTags.HAS_JUNGLE_TEMPLE).addTag(BWGBiomeTags.JUNGLE);
            tag(BiomeTags.HAS_RUINED_PORTAL_OCEAN).addTag(BWGBiomeTags.OCEAN);
            tag(BiomeTags.HAS_SHIPWRECK).addTag(BWGBiomeTags.OCEAN);
            tag(BiomeTags.HAS_OCEAN_RUIN_WARM).addTag(BWGBiomeTags.OCEAN);
            tag(BiomeTags.HAS_VILLAGE_SAVANNA).addTag(BWGBiomeTags.SAVANNA);
            tag(BiomeTags.HAS_PILLAGER_OUTPOST).addTag(BWGBiomeTags.SAVANNA);

            // Serene Seasons Support
            tag(ModTags.Biomes.TROPICAL_BIOMES)
                    .addTag(BWGBiomeTags.DESERT).addTag(BWGBiomeTags.JUNGLE);
            tag(ModTags.Biomes.INFERTILE_BIOMES)
                    .add(BWGBiomes.DEAD_SEA);
            tag(ModTags.Biomes.BLACKLISTED_BIOMES)
                    .addTag(BWGBiomeTags.OCEAN);
            sortTagsAlphabeticallyAndRemoveDuplicateTagEntries(this.builders);
        }
    }

    private static class StructureTagGenerator extends StructureTagsProvider {

        private StructureTagGenerator(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
            super(arg, completableFuture, BiomesWeveGone.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.@NotNull Provider provider) {
            tag(BWGStructureTags.PRAIRIE_HOUSES).add(BWGStructures.PRAIRIE_HOUSE).add(BWGStructures.ABANDONED_PRAIRIE_HOUSE);
            tag(BWGStructureTags.ASPEN_MANORS).add(BWGStructures.ASPEN_MANOR_1).add(BWGStructures.ASPEN_MANOR_2);
            tag(BWGStructureTags.VILLAGE)
                    .add(BWGStructures.FORGOTTEN_VILLAGE)
                    .add(BWGStructures.SKYRIS_VILLAGE)
                    .add(BWGStructures.SALEM_VILLAGE)
                    .add(BWGStructures.RED_ROCK_VILLAGE)
                    .add(BWGStructures.PUMPKIN_PATCH_VILLAGE);
            tag(StructureTags.VILLAGE).addTag(BWGStructureTags.VILLAGE);
        }
    }

    private static class EntityTypeTagGenerator extends EntityTypeTagsProvider {

        private EntityTypeTagGenerator(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
            super(arg, completableFuture, BiomesWeveGone.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.@NotNull Provider provider) {
            tag(EntityTypeTags.CAN_BREATHE_UNDER_WATER).add(BWGEntities.MAN_O_WAR.get());
            tag(EntityTypeTags.AQUATIC).add(BWGEntities.MAN_O_WAR.get());
            tag(Tags.EntityTypes.BOATS).add(BWGEntities.BWG_BOAT.get(), BWGEntities.BWG_CHEST_BOAT.get());
        }
    }

    private static class PoiTagGenerator extends PoiTypeTagsProvider {

        private PoiTagGenerator(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
            super(arg, completableFuture, BiomesWeveGone.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.@NotNull Provider provider) {
            tag(PoiTypeTags.ACQUIRABLE_JOB_SITE).add(BWGPoiTypes.FORAGER);
        }
    }

    private static class DamageTypeTagGenerator extends DamageTypeTagsProvider {

        private DamageTypeTagGenerator(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
            super(arg, completableFuture, BiomesWeveGone.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.@NotNull Provider provider) {
            tag(DamageTypeTags.BYPASSES_ARMOR).add(BWGDamageTypes.IN_QUICKSAND);
            tag(Tags.DamageTypes.IS_ENVIRONMENT).add(BWGDamageTypes.IN_QUICKSAND);
            tag(DamageTypeTags.IS_EXPLOSION).add(BWGDamageTypes.CATTAIL_EXPLOSION);
        }
    }
}
