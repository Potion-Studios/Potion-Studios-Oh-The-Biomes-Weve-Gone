package net.potionstudios.biomeswevegone.world.level.levelgen.structure.processor;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.processor.processors.FruitBlockProcessor;

import java.util.Map;

public class BWGStructureProcessorLists {

    public static final Map<ResourceKey<StructureProcessorList>, StructureProcessorListFactory> STRUCTURE_PROCESSOR_LIST_FACTORIES = new Reference2ObjectOpenHashMap<>();

    public static final ResourceKey<StructureProcessorList> PRAIRIE_HOUSE = register("prairie_house", structureProcessorListHolderGetter -> new StructureProcessorList(
            ImmutableList.of(
                    BWGRuleProcessors.GRASS_RANDOM_DIRT_PODZOL_COARSE_DIRT,
                    BWGRuleProcessors.STONE_RANDOM_COBBLESTONE_MOSSY
            )
    ));

    public static final ResourceKey<StructureProcessorList> ABANDONED_PRAIRIE_HOUSE = register("abandoned_prairie_house", structureProcessorListHolderGetter -> new StructureProcessorList(
            ImmutableList.of(
                    BWGRuleProcessors.DIRT_RANDOM_GRASS_COARSE_DIRT_PODZOL,
                    BWGRuleProcessors.STONE_RANDOM_COBBLESTONE_MOSSY,
                    BWGRuleProcessors.MOSSIFY_70_PERCENT
            )
    ));

    public static final ResourceKey<StructureProcessorList> ASPEN_MANOR = register("aspen_manor", structureProcessorListHolderGetter -> new StructureProcessorList(
            ImmutableList.of(
                    BWGRuleProcessors.SWEETBERRY_BLUEBERRY_50_PERCENT_RANDOM_AGE
            )
    ));

    public static final ResourceKey<StructureProcessorList> FORGOTTEN_VILLAGE_BLOCKS = register("forgotten_village_blocks", structureProcessorListHolderGetter -> new StructureProcessorList(
            ImmutableList.of(
                    BWGRuleProcessors.FORGOTTEN_ROCKY_STONE,
                    BWGRuleProcessors.FORGOTTEN_GRAVEL,
                    BWGRuleProcessors.FORGOTTEN_LUSH_GRASS_BLOCK,
                    BWGRuleProcessors.FORGOTTEN_MOSSY_STONE_BRICKS
            )
    ));

    public static final ResourceKey<StructureProcessorList> SKYRIS_STREETS = register("skyris_streets", structureProcessorListHolderGetter -> new StructureProcessorList(
            ImmutableList.of(
                    BWGRuleProcessors.POLISHED_ANDESITE_RANDOM_ANDESITE_GRAVEL
            )
    ));

    public static final ResourceKey<StructureProcessorList> SKYRIS_TEMPLE = register("skyris_temple", structureProcessorListHolderGetter -> new StructureProcessorList(
            ImmutableList.of(
                    BWGRuleProcessors.POLISHED_ANDESITE_RANDOM_ANDESITE_GRAVEL,
                    BWGRuleProcessors.DACITE_10_PERCENT_COBBLED_DACITE,
                    BWGRuleProcessors.STONEBRICKS_VARIANTS
            )
    ));

    public static final ResourceKey<StructureProcessorList> SKYRIS_LIBRARY = register("skyris_library", structureProcessorListHolderGetter -> new StructureProcessorList(
            ImmutableList.of(
                    BWGRuleProcessors.POLISHED_ANDESITE_RANDOM_ANDESITE_GRAVEL,
                    BWGRuleProcessors.DACITE_VARIANTS
            )
    ));

    public static final ResourceKey<StructureProcessorList> SKYRIS_FLETCHER_HOUSE = register("skyris_fletcher_house", structureProcessorListHolderGetter -> new StructureProcessorList(
            ImmutableList.of(
                BWGRuleProcessors.PODZOL_25_PERCENT_COARSE_DIRT,
                BWGRuleProcessors.PODZOL_10_PERCENT_GRAVEL,
                BWGRuleProcessors.PODZOL_10_PERCENT_ANDESITE
            )
    ));

    public static final ResourceKey<StructureProcessorList> SKYRIS_FORAGER_HOUSE = register("skyris_forager_house", structureProcessorListHolderGetter -> new StructureProcessorList(
            ImmutableList.of(
                BWGRuleProcessors.POLISHED_ANDESITE_RANDOM_ANDESITE_GRAVEL,
                BWGRuleProcessors.PODZOL_25_PERCENT_COARSE_DIRT,
                BWGRuleProcessors.PODZOL_50_PERCENT_LUSH_GRASS,
                BWGRuleProcessors.skyrisLeavesToGreenAppleLeaves(0.15f),
                BWGRuleProcessors.skyrisLeavesToFlowering(0.2f),
                new FruitBlockProcessor(BWGBlocks.GREEN_APPLE_FRUIT_BLOCK.get())
            )
    ));

    public static final ResourceKey<StructureProcessorList> SKYRIS_TOWN_CENTERS = register("skyris_town_centers", structureProcessorListHolderGetter -> new StructureProcessorList(
            ImmutableList.of(
                    BWGRuleProcessors.POLISHED_ANDESITE_RANDOM_ANDESITE_GRAVEL,
                    BWGRuleProcessors.skyrisLeavesToGreenAppleLeaves(0.15f),
                    BWGRuleProcessors.skyrisLeavesToFlowering(0.2f),
                    new FruitBlockProcessor(BWGBlocks.GREEN_APPLE_FRUIT_BLOCK.get())
            )
    ));

    public static final ResourceKey<StructureProcessorList> SALEM_TOWN_CENTER = register("salem_town_center", structureProcessorListHolderGetter -> new StructureProcessorList(
            ImmutableList.of(
                    BWGRuleProcessors.lushDirtToCoarseDirt(.4f),
                    BWGRuleProcessors.STONE_SLAB_RANDOM_ANDESITE_ROCKY_SLAB,
                    BWGRuleProcessors.STONE_RANDOM_ROCKY_ANDESITE
            )
    ));

    public static final ResourceKey<StructureProcessorList> SALEM_STREETS = register("salem_streets", structureProcessorListHolderGetter -> new StructureProcessorList(
            ImmutableList.of(
                    BWGRuleProcessors.lushDirtToCoarseDirt(.3f)
            )
    ));

    public static final ResourceKey<StructureProcessorList> SALEM_HOUSES = register("salem_houses", structureProcessorListHolderGetter -> new StructureProcessorList(
            ImmutableList.of(
                    BWGRuleProcessors.STRIPPED_OAK_LOG_60_PERCENT_OAK_PLANKS,
                    BWGRuleProcessors.STONE_RANDOM_COBBLESTONE_MOSSY
            )
    ));

    public static final ResourceKey<StructureProcessorList> RED_ROCK_STREETS = register("red_rock_streets", structureProcessorListHolderGetter -> new StructureProcessorList(
            ImmutableList.of(
                    BWGRuleProcessors.ORANGE_TERRACOTTA_TO_RED_ROCK_CRACKED_RED_ROCK_BRICKS
            )
    ));

    public static final ResourceKey<StructureProcessorList> RED_ROCK_CRACKED_BRICKS_50_PERCENT_MOSSY_RED_ROCK_BRICKS = register("red_rock_cracked_bricks_50_percent_mossy_red_rock_bricks", structureProcessorListHolderGetter -> new StructureProcessorList(
            ImmutableList.of(
                    BWGRuleProcessors.RED_ROCK_CRACKED_BRICKS_50_PERCENT_MOSSY_RED_ROCK_BRICKS
            )
    ));

    public static final ResourceKey<StructureProcessorList> RED_ROCK_MEETING_POINT_1 = register("red_rock_meeting_point_1", structureProcessorListHolderGetter -> new StructureProcessorList(
            ImmutableList.of(
                    BWGRuleProcessors.RED_ROCK_BRICKS_TO_ORANGE_TERRACOTTA,
                    BWGRuleProcessors.RED_ROCK_CRACKED_BRICKS_50_PERCENT_MOSSY_RED_ROCK_BRICKS
            )
    ));

    public static final ResourceKey<StructureProcessorList> RED_ROCK_MEETING_POINT_3 = register("red_rock_meeting_point_3", structureProcessorListHolderGetter -> new StructureProcessorList(
            ImmutableList.of(
                    BWGRuleProcessors.STRIPPED_BAOBAB_WOOD_50_PERCENT_BAOBAB_PLANKS
            )
    ));

    public static final ResourceKey<StructureProcessorList> RED_ROCK_TO_BRICKS = register("red_rock_to_bricks", structureProcessorListHolderGetter -> new StructureProcessorList(
            ImmutableList.of(
                    BWGRuleProcessors.RED_ROCK_25_PERCENT_CRACKED_RED_ROCK_BRICKS,
                    BWGRuleProcessors.RED_ROCK_25_PERCENT_RED_ROCK_BRICKS
            )
    ));

    public static final ResourceKey<StructureProcessorList> RED_ROCK_HOUSE = register("red_rock_house", structureProcessorListHolderGetter -> new StructureProcessorList(
            ImmutableList.of(
                    BWGRuleProcessors.RED_ROCK_BRICKS_35_PERCENT_CRACKED_RED_ROCK_BRICKS,
                    BWGRuleProcessors.RANDOM_DESERT_POTTED_PLANT
            )
    ));

    public static final ResourceKey<StructureProcessorList> PUMPKIN_PATCH_STREETS = register("pumpkin_patch_streets", structureProcessorListHolderGetter -> new StructureProcessorList(
            ImmutableList.of(
                    BWGRuleProcessors.packedMudToBlock(0.15f, BWGBlocks.LUSH_DIRT_PATH.get()),
                    BWGRuleProcessors.packedMudToBlock(0.45f, BWGBlocks.PEAT.get()),
                    BWGRuleProcessors.packedMudToBlock(0.4f, Blocks.COARSE_DIRT)
            )
    ));

    public static final ResourceKey<StructureProcessorList> PUMPKIN_PATCH_HOUSE = register("pumpkin_patch_house", structureProcessorListHolderGetter -> new StructureProcessorList(
            ImmutableList.of(
                    BWGRuleProcessors.packedMudToBlock(0.5f, BWGBlocks.LUSH_GRASS_BLOCK.get()),
                    BWGRuleProcessors.packedMudToBlock(0.35f, BWGBlocks.PEAT.get()),
                    BWGRuleProcessors.packedMudToBlock(0.1f, Blocks.COARSE_DIRT),
                    BWGRuleProcessors.STONEBRICKS_VARIANTS,
                    BWGRuleProcessors.STONEBRICKS_MORE_VARIANTS
            )
    ));

    public static final ResourceKey<StructureProcessorList> PUMPKIN_PATCH_MEETING_POINT_2 = register("pumpkin_patch_meeting_point_2", structureProcessorListHolderGetter -> new StructureProcessorList(
            ImmutableList.of(
                    BWGRuleProcessors.COBBLESTONE_VARIANTS
            )
    ));

    public static final ResourceKey<StructureProcessorList> PUMPKIN_PATCH_MEETING_POINT_1 = register("pumpkin_patch_meeting_point_1", structureProcessorListHolderGetter -> new StructureProcessorList(
            ImmutableList.of(
                    BWGRuleProcessors.COBBLESTONE_50_PERCENT_DIRT_PATH,
                    BWGRuleProcessors.COBBLESTONE_20_PERCENT_GRASS,
                    BWGRuleProcessors.STONEBRICKS_VARIANTS,
                    BWGRuleProcessors.COBBLESTONE_VARIANTS
            )
    ));

    public static final ResourceKey<StructureProcessorList> MOSSIFY_10_PERCENT_WHITE_PUFFBALL = register("mossify_10_percent_white_puffball", structureProcessorListHolderGetter -> new StructureProcessorList(
            ImmutableList.of(
                    BWGRuleProcessors.MOSSIFY_10_PERCENT,
                    BWGRuleProcessors.WHITE_PUFFBALL_RANDOM_AGE
            )
    ));

    public static final ResourceKey<StructureProcessorList> BOG_TRIAL = register("bog_trial", structureProcessorListHolderGetter -> new StructureProcessorList(
            ImmutableList.of(
                    BWGRuleProcessors.spiritLeavesToFlowering(0.5f),
                    BWGRuleProcessors.BOG_TRIAL_FLOOR,
                    BWGRuleProcessors.WHITE_PUFFBALL_RANDOM_AGE
            )
    ));

    private static ResourceKey<StructureProcessorList> register(String id, StructureProcessorListFactory factory) {
        ResourceKey<StructureProcessorList> structureProcessorListResourceKey = BiomesWeveGone.key(Registries.PROCESSOR_LIST, id);
        STRUCTURE_PROCESSOR_LIST_FACTORIES.put(structureProcessorListResourceKey, factory);
        return structureProcessorListResourceKey;
    }

    @FunctionalInterface
    public interface StructureProcessorListFactory  {
        StructureProcessorList generate(HolderGetter<StructureProcessorList> structureProcessorListHolderGetter);
    }
}
