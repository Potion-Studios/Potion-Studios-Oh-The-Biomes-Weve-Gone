package net.potionstudios.biomeswevegone.forge.datagen.generators;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.tags.BWGBiomeTags;
import net.potionstudios.biomeswevegone.tags.BWGBlockTags;
import net.potionstudios.biomeswevegone.tags.BWGItemTags;
import net.potionstudios.biomeswevegone.tags.BWGStructureTags;
import net.potionstudios.biomeswevegone.world.damagesource.BWGDamageTypes;
import net.potionstudios.biomeswevegone.world.entity.ai.village.poi.BWGPoiTypes;
import net.potionstudios.biomeswevegone.world.item.BWGItems;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
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
                tag(BlockTags.OVERWORLD_NATURAL_LOGS).add(set.logstem());
                tag(BlockTags.LOGS).addTag(set.logBlockTag());
                tag(BlockTags.LOGS_THAT_BURN).addTag(set.logBlockTag());
            });

            tag(BWGBlockTags.PALO_VERDE_LOGS).add(BWGWood.PALO_VERDE_LOG.get(), BWGWood.STRIPPED_PALO_VERDE_LOG.get(), BWGWood.PALO_VERDE_WOOD.get(), BWGWood.STRIPPED_PALO_VERDE_WOOD.get());
            tag(BlockTags.LOGS).addTag(BWGBlockTags.PALO_VERDE_LOGS);

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
                tag(Tags.Blocks.SANDSTONE).addTag(set.getSandstoneBlocksTag());
                tag(Tags.Blocks.SAND).addTag(set.getSandBlockTag());

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

            tag(BWGBlockTags.BLACK_ICE).add(BWGBlocks.BLACK_ICE.get(), BWGBlocks.PACKED_BLACK_ICE.get());
            tag(BWGBlockTags.BOREALIS_ICE).add(BWGBlocks.BOREALIS_ICE.get(), BWGBlocks.PACKED_BOREALIS_ICE.get());
            tag(BlockTags.SNOW_LAYER_CANNOT_SURVIVE_ON).addTag(BWGBlockTags.BLACK_ICE).addTag(BWGBlockTags.BOREALIS_ICE);
            tag(BlockTags.ICE).addTag(BWGBlockTags.BLACK_ICE).addTag(BWGBlockTags.BOREALIS_ICE);
            tag(BlockTags.FROG_PREFER_JUMP_TO).add(BWGBlocks.FLOWERING_TINY_LILY_PADS.get(), BWGBlocks.TINY_LILY_PADS.get());
            tag(BlockTags.WOOL_CARPETS).add(BWGBlocks.CATTAIL_THATCH_CARPET.get());
            tag(BlockTags.MUSHROOM_GROW_BLOCK).add(BWGBlocks.PODZOL_DACITE.get());
            tag(BWGBlockTags.SNOWY_PLANT_PLACEABLE).add(Blocks.SNOW_BLOCK, Blocks.POWDER_SNOW).addTag(BlockTags.DIRT);
            tag(BWGBlockTags.BWG_MUSHROOM_PLACEABLE).addTag(BlockTags.MUSHROOM_GROW_BLOCK).addTag(BlockTags.BASE_STONE_OVERWORLD).addTag(BlockTags.DIRT);
            tag(BWGBlockTags.HYDRANGEA_BUSH_PLACEABLE).addTag(BlockTags.DIRT);
            tag(BlockTags.DIRT).add(BWGBlocks.LUSH_GRASS_BLOCK.get(), BWGBlocks.SANDY_DIRT.get(), BWGBlocks.LUSH_DIRT.get(), BWGBlocks.PEAT.get(), BWGBlocks.OVERGROWN_DACITE.get(), BWGBlocks.OVERGROWN_STONE.get(), BWGBlocks.PODZOL_DACITE.get());
            tag(BlockTags.CLIMBABLE).add(BWGBlocks.SKYRIS_VINE.get()).add(BWGBlocks.POISON_IVY.get());
            tag(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
                    .add(BWGBlocks.OVERGROWN_DACITE.get(), BWGBlocks.OVERGROWN_STONE.get(), BWGBlocks.PODZOL_DACITE.get(),
                            BWGBlocks.ROCKY_STONE_SET.getBase(), BWGBlocks.MOSSY_STONE_SET.getBase(), BWGBlocks.LUSH_DIRT.get(), BWGBlocks.SANDY_DIRT.get(), BWGBlocks.PEAT.get());
            tag(BlockTags.CROPS).add(BWGBlocks.ODDION_CROP.get());
            tag(BlockTags.BEE_GROWABLES).add(BWGBlocks.BLUEBERRY_BUSH.get());
            tag(BlockTags.SNIFFER_DIGGABLE_BLOCK).add(BWGBlocks.LUSH_DIRT_PATH.get(), BWGBlocks.SANDY_DIRT.get(), BWGBlocks.SANDY_DIRT_PATH.get(), BWGBlocks.LUSH_DIRT.get(), BWGBlocks.LUSH_GRASS_BLOCK.get(), BWGBlocks.PEAT.get());
            tag(BlockTags.SNIFFER_EGG_HATCH_BOOST).add(BWGBlocks.LUSH_GRASS_BLOCK.get());
            tag(BlockTags.MAINTAINS_FARMLAND).add(BWGBlocks.ODDION_CROP.get());
            tag(BlockTags.MINEABLE_WITH_HOE).add(BWGBlocks.ALLIUM_PETAL_BLOCK.get(), BWGBlocks.PINK_ALLIUM_PETAL_BLOCK.get(), BWGBlocks.WHITE_ALLIUM_PETAL_BLOCK.get(), BWGBlocks.ROSE_PETAL_BLOCK.get());
            tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(BWGBlockTags.BLACK_ICE).addTag(BWGBlockTags.BOREALIS_ICE);
            tag(BlockTags.REPLACEABLE).add(BWGBlocks.PRAIRIE_GRASS.get(), BWGBlocks.TALL_PRAIRIE_GRASS.get(), BWGBlocks.BEACH_GRASS.get(), BWGBlocks.TALL_BEACH_GRASS.get(), BWGBlocks.SKYRIS_VINE.get());
            tag(BlockTags.SAND).add(BWGBlocks.SANDY_DIRT.get(), BWGBlocks.CRACKED_SAND.get());

            // Entity Integration Tags
            tag(BlockTags.ANIMALS_SPAWNABLE_ON).add(BWGBlocks.LUSH_GRASS_BLOCK.get(), BWGBlocks.LUSH_DIRT.get(), BWGBlocks.PODZOL_DACITE.get(), BWGBlocks.OVERGROWN_STONE.get(), BWGBlocks.OVERGROWN_DACITE.get(), BWGBlocks.PEAT.get());
            tag(BlockTags.FROGS_SPAWNABLE_ON).add(BWGBlocks.LUSH_GRASS_BLOCK.get());
            tag(BlockTags.PARROTS_SPAWNABLE_ON).add(BWGBlocks.LUSH_GRASS_BLOCK.get());
            tag(BlockTags.POLAR_BEARS_SPAWNABLE_ON_ALTERNATE).add(BWGBlocks.BLACK_ICE.get(), BWGBlocks.BOREALIS_ICE.get());
            tag(BlockTags.RABBITS_SPAWNABLE_ON).add(BWGBlocks.LUSH_GRASS_BLOCK.get(), BWGBlocks.RED_ROCK_SET.getBase());
            tag(BlockTags.FOXES_SPAWNABLE_ON).add(BWGBlocks.LUSH_GRASS_BLOCK.get(), BWGBlocks.PODZOL_DACITE.get());
            tag(BlockTags.WOLVES_SPAWNABLE_ON).add(BWGBlocks.LUSH_GRASS_BLOCK.get(), BWGBlocks.PODZOL_DACITE.get());

            tag(BlockTags.FROG_PREFER_JUMP_TO).add(BWGBlocks.FLOWERING_TINY_LILY_PADS.get(), BWGBlocks.TINY_LILY_PADS.get());
            tag(BlockTags.SNAPS_GOAT_HORN).add(BWGBlocks.PACKED_BLACK_ICE.get(), BWGBlocks.PACKED_BOREALIS_ICE.get());

            sortTagsAlphabeticallyAndRemoveDuplicateTagEntries(this.builders);
        }

        private void easyBlockTags(Block object) {
            if (object instanceof SlabBlock) tag(BlockTags.SLABS).add(object);
            else if (object instanceof StairBlock) tag(BlockTags.STAIRS).add(object);
            else if (object instanceof WallBlock) tag(BlockTags.WALLS).add(object);
            else if (object instanceof SandBlock) tag(BlockTags.SAND).add(object);
            //else if (object instanceof FlowerBlock) tag(BlockTags.SMALL_FLOWERS).add(object);
            else if (object instanceof TallFlowerBlock) tag(BlockTags.TALL_FLOWERS).add(object);
            else if (object instanceof LeavesBlock) tag(BlockTags.LEAVES).add(object);
            else if (object instanceof CampfireBlock) tag(BlockTags.CAMPFIRES).add(object);
            else if (object instanceof FlowerPotBlock) tag(BlockTags.FLOWER_POTS).add(object);
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
            copy(BlockTags.FLOWERS, ItemTags.FLOWERS);
            copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
            copy(BlockTags.TALL_FLOWERS, ItemTags.TALL_FLOWERS);
            copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
            copy(BlockTags.LEAVES, ItemTags.LEAVES);
            copy(BlockTags.WOOL_CARPETS, ItemTags.WOOL_CARPETS);
            BWGSandSet.getSandSets().forEach(set -> {
                copy(set.getSandstoneBlocksTag(), set.getSandstoneBlocksItemTag());
                copy(set.getSandBlockTag(), set.getSandItemTag());
                copy(set.getSandstoneSlabsTag(), set.getSandstoneSlabsItemTag());
                copy(set.getSandstoneStairsTag(), set.getSandstoneStairsItemTag());
            });
            copy(Tags.Blocks.SANDSTONE, Tags.Items.SANDSTONE);
            copy(Tags.Blocks.SAND, Tags.Items.SAND);
            copy(BWGBlockTags.BLACK_ICE, BWGItemTags.BLACK_ICE);
            copy(BWGBlockTags.BOREALIS_ICE, BWGItemTags.BOREALIS_ICE);

            BWGBlocks.BLOCKS.stream().filter(entry -> entry.get() instanceof FlowerBlock || entry.get() instanceof TallFlowerBlock).forEach(
                    entry -> {
                        Block block = entry.get();
                        if (block.defaultMapColor() == MapColor.COLOR_BLACK)
                            tag(BWGItemTags.MAKES_BLACK_DYE).add(block.asItem());
                        else if (block.defaultMapColor() == MapColor.COLOR_BLUE)
                            tag(BWGItemTags.MAKES_BLUE_DYE).add(block.asItem());
                        else if (block.defaultMapColor() == MapColor.COLOR_BROWN)
                            tag(BWGItemTags.MAKES_BROWN_DYE).add(block.asItem());
                        else if (block.defaultMapColor() == MapColor.COLOR_CYAN)
                            tag(BWGItemTags.MAKES_CYAN_DYE).add(block.asItem());
                        else if (block.defaultMapColor() == MapColor.COLOR_GRAY)
                            tag(BWGItemTags.MAKES_GRAY_DYE).add(block.asItem());
                        else if (block.defaultMapColor() == MapColor.COLOR_GREEN)
                            tag(BWGItemTags.MAKES_GREEN_DYE).add(block.asItem());
                        else if (block.defaultMapColor() == MapColor.COLOR_LIGHT_BLUE)
                            tag(BWGItemTags.MAKES_LIGHT_BLUE_DYE).add(block.asItem());
                        else if (block.defaultMapColor() == MapColor.COLOR_LIGHT_GRAY)
                            tag(BWGItemTags.MAKES_LIGHT_GRAY_DYE).add(block.asItem());
                        else if (block.defaultMapColor() == MapColor.COLOR_MAGENTA)
                            tag(BWGItemTags.MAKES_MAGENTA_DYE).add(block.asItem());
                        else if (block.defaultMapColor() == MapColor.COLOR_ORANGE)
                            tag(BWGItemTags.MAKES_ORANGE_DYE).add(block.asItem());
                        else if (block.defaultMapColor() == MapColor.COLOR_PINK)
                            tag(BWGItemTags.MAKES_PINK_DYE).add(block.asItem());
                        else if (block.defaultMapColor() == MapColor.COLOR_PURPLE)
                            tag(BWGItemTags.MAKES_PURPLE_DYE).add(block.asItem());
                        else if (block.defaultMapColor() == MapColor.COLOR_RED)
                            tag(BWGItemTags.MAKES_RED_DYE).add(block.asItem());
                        else if (block.defaultMapColor() == MapColor.TERRACOTTA_WHITE)
                            tag(BWGItemTags.MAKES_WHITE_DYE).add(block.asItem());
                        else if (block.defaultMapColor() == MapColor.COLOR_YELLOW)
                            tag(BWGItemTags.MAKES_YELLOW_DYE).add(block.asItem());
                    }
            );
            copy(BWGBlockTags.PALO_VERDE_LOGS, BWGItemTags.PALO_VERDE_LOGS);
            tag(BWGItemTags.SHEARS).addOptionalTag(Tags.Items.SHEARS).addOptionalTag(new ResourceLocation("c", "shears"));
            tag(ItemTags.MUSIC_DISCS).add(BWGItems.MUSIC_DISC_PIXIE_CLUB.get());
            tag(ItemTags.STONE_CRAFTING_MATERIALS).add(BWGBlocks.ROCKY_STONE_SET.getBase().asItem());
            tag(ItemTags.VILLAGER_PLANTABLE_SEEDS).add(BWGItems.ODDION_BULB.get());
            tag(Tags.Items.MUSHROOMS).add(BWGBlocks.GREEN_MUSHROOM.get().asItem(), BWGBlocks.WEEPING_MILKCAP.get().asItem(), BWGBlocks.WOOD_BLEWIT.get().asItem());
            tag(Tags.Items.CROPS).add(BWGItems.ODDION_BULB.get());
            tag(BWGItemTags.MUSHROOMS).add(Items.BROWN_MUSHROOM, Items.RED_MUSHROOM, BWGBlocks.GREEN_MUSHROOM.get().asItem(), BWGBlocks.WEEPING_MILKCAP.get().asItem(), BWGBlocks.WOOD_BLEWIT.get().asItem()).addOptionalTag(Tags.Items.MUSHROOMS);
            tag(BWGItemTags.VANILLA_PLANKS).add(Items.ACACIA_PLANKS, Items.BIRCH_PLANKS, Items.CRIMSON_PLANKS, Items.DARK_OAK_PLANKS, Items.JUNGLE_PLANKS, Items.OAK_PLANKS, Items.SPRUCE_PLANKS, Items.WARPED_PLANKS, Items.CHERRY_PLANKS, Items.MANGROVE_PLANKS, Items.BAMBOO_PLANKS, Items.CRIMSON_PLANKS);
            sortTagsAlphabeticallyAndRemoveDuplicateTagEntries(this.builders);
        }
    }


    private static class BiomeTagGenerator extends BiomeTagsProvider {
        private BiomeTagGenerator(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
            super(arg, completableFuture, BiomesWeveGone.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.@NotNull Provider provider) {
            BWGBiomes.BIOME_FACTORIES.keySet().stream().sorted().toList().forEach(biome -> tag(BWGBiomeTags.OVERWORLD).add(biome));
            BWGBiomes.BIOMES_BY_TAG.forEach((tag, biome) -> tag(tag).add(biome));

            tag(BiomeTags.IS_OVERWORLD).addTag(BWGBiomeTags.OVERWORLD);

            tag(Tags.Biomes.IS_HOT_OVERWORLD).addTag(BWGBiomeTags.HOT);
            tag(Tags.Biomes.IS_COLD_OVERWORLD).addTag(BWGBiomeTags.COLD);
            tag(Tags.Biomes.IS_WET_OVERWORLD).addTag(BWGBiomeTags.WET);
            tag(Tags.Biomes.IS_DRY_OVERWORLD).addTag(BWGBiomeTags.DRY);
            tag(Tags.Biomes.IS_SPARSE_OVERWORLD).addTag(BWGBiomeTags.SPARSE);
            tag(Tags.Biomes.IS_DENSE_OVERWORLD).addTag(BWGBiomeTags.DENSE);

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
            tag(Tags.Biomes.IS_SLOPE).addTag(BWGBiomeTags.SLOPE);
            tag(Tags.Biomes.IS_PEAK).addTag(BWGBiomeTags.PEAK);
            tag(Tags.Biomes.IS_SWAMP).addTag(BWGBiomeTags.SWAMP);
            tag(Tags.Biomes.IS_SANDY).addTag(BWGBiomeTags.SANDY);
            tag(Tags.Biomes.IS_SNOWY).addTag(BWGBiomeTags.SNOWY);
            tag(BiomeTags.IS_OCEAN).addTag(BWGBiomeTags.OCEAN);
            tag(Tags.Biomes.IS_CONIFEROUS).addTag(BWGBiomeTags.CONIFEROUS);
            tag(Tags.Biomes.IS_WASTELAND).addTag(BWGBiomeTags.WASTELAND);
            tag(Tags.Biomes.IS_MAGICAL).addTag(BWGBiomeTags.MAGICAL);

            tag(BWGBiomeTags.WET).addTag(BWGBiomeTags.SWAMP).addTag(BWGBiomeTags.BEACH);
            tag(BWGBiomeTags.DENSE).addTag(BWGBiomeTags.FOREST).addTag(BWGBiomeTags.JUNGLE).addTag(BWGBiomeTags.SWAMP);
            tag(BWGBiomeTags.DRY).addTag(BWGBiomeTags.DESERT);

            tag(BWGBiomeTags.SHARPENED_ROCKS).add(BWGBiomes.RUGGED_BADLANDS);
            tag(BWGBiomeTags.IRONWOOD_GOUR_PLATEAU).add(BWGBiomes.IRONWOOD_GOUR);
            tag(BWGBiomeTags.LARGE_COLD_LAKE).add(BWGBiomes.MAPLE_TAIGA).add(BWGBiomes.CRIMSON_TUNDRA).add(BWGBiomes.OVERGROWTH_WOODLANDS);

            /**
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
            sortTagsAlphabeticallyAndRemoveDuplicateTagEntries(this.builders);
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
            tag(DamageTypeTags.IS_EXPLOSION).add(BWGDamageTypes.CATTAIL_EXPLOSION);
        }
    }
}
