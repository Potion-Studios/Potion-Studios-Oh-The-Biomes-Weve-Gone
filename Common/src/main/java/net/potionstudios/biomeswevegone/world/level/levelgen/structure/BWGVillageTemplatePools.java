package net.potionstudios.biomeswevegone.world.level.levelgen.structure;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.data.worldgen.placement.VillagePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.processor.BWGStructureProcessorLists;

import java.util.List;
import java.util.function.Function;

/**
 * BWG Village Template Pools
 * @see StructureTemplatePool
 * @author Joseph T. McQuigg
 */
@SuppressWarnings("unused")
public class BWGVillageTemplatePools {

    private static final ResourceKey<StructureTemplatePool> SKYRIS_TERMINATOR = createTerminatorPool("skyris");

    private static final ResourceKey<StructureTemplatePool> SKYRIS_DECOR = register("skyris/decor", templatePoolFactoryContext ->
            createTemplatePool(getEmptyPool(templatePoolFactoryContext), ImmutableList.of(
                singlePoolElement("skyris/skyris_lamp_1", 10),
                featureElement(templatePoolFactoryContext, VillagePlacements.PATCH_BERRY_BUSH_VILLAGE, 4),
                featureElement(templatePoolFactoryContext, VillagePlacements.PILE_HAY_VILLAGE, 4),
                emptyPoolElement(6)
            ), StructureTemplatePool.Projection.RIGID));

    public static final ResourceKey<StructureTemplatePool> SKYRIS_TOWN_CENTERS = register("skyris/town_centers", templatePoolFactoryContext ->
            createTemplatePool(getEmptyPool(templatePoolFactoryContext), ImmutableList.of(
                    singlePoolElement("skyris/town_centers/skyris_meeting_point_1", templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_STREETS),
                    singlePoolElement("skyris/town_centers/skyris_meeting_point_2", templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_TOWN_CENTERS),
                    singlePoolElement("skyris/town_centers/skyris_meeting_point_3", templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_TOWN_CENTERS),
                    singlePoolElement("skyris/town_centers/skyris_meeting_point_4", templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_TOWN_CENTERS)
            ), StructureTemplatePool.Projection.RIGID));

    public static final ResourceKey<StructureTemplatePool> SKYRIS_VILLAGERS = register("skyris/villagers", templatePoolFactoryContext ->
            createTemplatePool(getEmptyPool(templatePoolFactoryContext), ImmutableList.of(
                    singlePoolElement("skyris/villagers/baby", 2),
                    singlePoolElement("skyris/villagers/unemployed", 10)
            ), StructureTemplatePool.Projection.RIGID));

    public static final ResourceKey<StructureTemplatePool> SKYRIS_STREETS = register("skyris/streets", templatePoolFactoryContext ->
            createTemplatePool(getPool(templatePoolFactoryContext, SKYRIS_TERMINATOR), ImmutableList.of(
                    singlePoolElement("skyris/streets/skyris_corner_01", 3, templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_STREETS),
                    singlePoolElement("skyris/streets/skyris_corner_02", 3, templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_STREETS),
                    singlePoolElement("skyris/streets/skyris_straight_01", 4, templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_STREETS),
                    singlePoolElement("skyris/streets/skyris_straight_02", 4, templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_STREETS),
                    singlePoolElement("skyris/streets/skyris_straight_03", 3, templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_STREETS),
                    singlePoolElement("skyris/streets/skyris_crossroad_01", 3, templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_STREETS),
                    singlePoolElement("skyris/streets/skyris_crossroad_02", 3, templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_STREETS),
                    singlePoolElement("skyris/streets/skyris_crossroad_03", 3, templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_STREETS),
                    singlePoolElement("skyris/streets/skyris_square_01", 3, templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_STREETS),
                    singlePoolElement("skyris/streets/skyris_square_02", 3, templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_STREETS),
                    singlePoolElement("skyris/streets/skyris_turn_01", 3, templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_STREETS)
            ), StructureTemplatePool.Projection.TERRAIN_MATCHING));

    public static final ResourceKey<StructureTemplatePool> SKYRIS_HOUSES = register("skyris/houses", templatePoolFactoryContext ->
            createTemplatePool(getPool(templatePoolFactoryContext, SKYRIS_TERMINATOR), ImmutableList.of(
                    singlePoolElement("skyris/houses/skyris_small_house_1", 2,templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_STREETS),
                    singlePoolElement("skyris/houses/skyris_small_house_2", 2, templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_STREETS),
                    singlePoolElement("skyris/houses/skyris_small_house_3", 2, templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_TOWN_CENTERS),
                    singlePoolElement("skyris/houses/skyris_small_house_4", 2, templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_STREETS),
                    singlePoolElement("skyris/houses/skyris_small_house_5", 2, templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_STREETS),
                    singlePoolElement("skyris/houses/skyris_small_house_6", templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_STREETS),
                    singlePoolElement("skyris/houses/skyris_medium_house_1", 2, templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_STREETS),
                    singlePoolElement("skyris/houses/skyris_tool_smith_1", 2, templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_STREETS),
                    singlePoolElement("skyris/houses/skyris_butcher_shop_1",2, templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_STREETS),
                    singlePoolElement("skyris/houses/skyris_fletcher_house_1", 2, templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_FLETCHER_HOUSE),
                    singlePoolElement("skyris/houses/skyris_forager_1", 2, templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_FORAGER_HOUSE),
                    singlePoolElement("skyris/houses/skyris_armorer_1"),
                    singlePoolElement("skyris/houses/skyris_fisher_1", 2),
                    singlePoolElement("skyris/houses/skyris_cartographer_house_1", 2, templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_STREETS),
                    singlePoolElement("skyris/houses/skyris_library_1", 2, templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_LIBRARY),
                    singlePoolElement("skyris/houses/skyris_mason_1", 2, templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_STREETS),
                    singlePoolElement("skyris/houses/skyris_weaponsmith_1", 2),
                    singlePoolElement("skyris/houses/skyris_temple_1", 2, templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_TEMPLE),
                    singlePoolElement("skyris/houses/skyris_animal_pen_1", 2, templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_STREETS),
                    singlePoolElement("skyris/houses/skyris_animal_pen_2", 2, templatePoolFactoryContext, BWGStructureProcessorLists.SKYRIS_STREETS),
                    singlePoolElement("skyris/houses/skyris_large_farm_1", 11, templatePoolFactoryContext, ProcessorLists.FARM_DESERT),
                    emptyPoolElement(7)
            ), StructureTemplatePool.Projection.RIGID));

    // Forgotten Village Template Pools
    private static final ResourceKey<StructureTemplatePool> FORGOTTEN_TERMINATOR = createTerminatorPool("forgotten", BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS);

    public static final ResourceKey<StructureTemplatePool> FORGOTTEN_TOWN_CENTERS = register("forgotten/town_centers", templatePoolFactoryContext ->
            createTemplatePool(getEmptyPool(templatePoolFactoryContext), ImmutableList.of(
                    singlePoolElement("forgotten/town_centers/forgotten_meeting_point_1", templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    singlePoolElement("forgotten/town_centers/forgotten_meeting_point_2", templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    singlePoolElement("forgotten/town_centers/forgotten_meeting_point_3", templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS)
            ), StructureTemplatePool.Projection.RIGID));

    private static final ResourceKey<StructureTemplatePool> FORGOTTEN_DECOR = register("forgotten/decor", templatePoolFactoryContext ->
            createTemplatePool(getEmptyPool(templatePoolFactoryContext), ImmutableList.of(
                    singlePoolElement("forgotten/forgotten_lamp_1", 6),
                    featureElement(templatePoolFactoryContext, VillagePlacements.PILE_MELON_VILLAGE, 2),
                    featureElement(templatePoolFactoryContext, VillagePlacements.PILE_HAY_VILLAGE, 1),
                    emptyPoolElement(6)
            ), StructureTemplatePool.Projection.RIGID));

    public static final ResourceKey<StructureTemplatePool> FORGOTTEN_STREETS = register("forgotten/streets", templatePoolFactoryContext ->
            createTemplatePool(getPool(templatePoolFactoryContext, FORGOTTEN_TERMINATOR), ImmutableList.of(
                    singlePoolElement("forgotten/streets/forgotten_corner_01", 3, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    singlePoolElement("forgotten/streets/forgotten_corner_02", 3, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    singlePoolElement("forgotten/streets/forgotten_corner_03", 3, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    singlePoolElement("forgotten/streets/forgotten_straight_01", 4, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    singlePoolElement("forgotten/streets/forgotten_straight_02", 4, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    singlePoolElement("forgotten/streets/forgotten_straight_03", 3, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    singlePoolElement("forgotten/streets/forgotten_straight_04", 3, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    singlePoolElement("forgotten/streets/forgotten_straight_05", 3, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    singlePoolElement("forgotten/streets/forgotten_straight_06", 3, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    singlePoolElement("forgotten/streets/forgotten_crossroad_01", 3, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    singlePoolElement("forgotten/streets/forgotten_crossroad_02", 3, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    singlePoolElement("forgotten/streets/forgotten_crossroad_03", 3, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    singlePoolElement("forgotten/streets/forgotten_crossroad_04", 3, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    singlePoolElement("forgotten/streets/forgotten_crossroad_05", 3, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    singlePoolElement("forgotten/streets/forgotten_crossroad_06", 3, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    singlePoolElement("forgotten/streets/forgotten_turn_01", 3, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS)
            ), StructureTemplatePool.Projection.TERRAIN_MATCHING));

//    public static final ResourceKey<StructureTemplatePool> FORGOTTEN_VILLAGERS = register("forgotten/villagers", templatePoolFactoryContext ->
//            createTemplatePool(getEmptyPool(templatePoolFactoryContext), ImmutableList.of(
//                    singlePoolElement("forgotten/villagers/baby", 2),
//                    singlePoolElement("forgotten/villagers/unemployed", 10)
//            ), StructureTemplatePool.Projection.RIGID));

    public static final ResourceKey<StructureTemplatePool> FORGOTTEN_HOUSES = register("forgotten/houses", templatePoolFactoryContext ->
            createTemplatePool(getPool(templatePoolFactoryContext, FORGOTTEN_TERMINATOR), ImmutableList.of(
                    singlePoolElement("forgotten/houses/forgotten_small_house_1", 3, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    legacyPoolElement("forgotten/houses/forgotten_small_house_2", 3, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    legacyPoolElement("forgotten/houses/forgotten_small_house_3", 3, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    legacyPoolElement("forgotten/houses/forgotten_small_house_4", 3, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    legacyPoolElement("forgotten/houses/forgotten_small_house_5", 3, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    legacyPoolElement("forgotten/houses/forgotten_small_house_6", 3, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    legacyPoolElement("forgotten/houses/forgotten_small_house_7", 3, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    legacyPoolElement("forgotten/houses/forgotten_small_house_8", 3, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    legacyPoolElement("forgotten/houses/forgotten_armorer_house_1", 3, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    legacyPoolElement("forgotten/houses/forgotten_butcher_shop_1", 2, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    legacyPoolElement("forgotten/houses/forgotten_butcher_shop_2", 3, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    legacyPoolElement("forgotten/houses/forgotten_cartographer_1", 1, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    legacyPoolElement("forgotten/houses/forgotten_cartographer_2", 2, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    legacyPoolElement("forgotten/houses/forgotten_temple_1", 1, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    legacyPoolElement("forgotten/houses/forgotten_temple_2", 2, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    legacyPoolElement("forgotten/houses/forgotten_tool_smith_1", 2, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    legacyPoolElement("forgotten/houses/forgotten_animal_pen_1", 2, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    legacyPoolElement("forgotten/houses/forgotten_animal_pen_2", 2, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    legacyPoolElement("forgotten/houses/forgotten_animal_pen_3", 2, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    legacyPoolElement("forgotten/houses/forgotten_accessory_1", 1, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    legacyPoolElement("forgotten/houses/forgotten_accessory_2", 1, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    legacyPoolElement("forgotten/houses/forgotten_small_farm_1", 2, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    legacyPoolElement("forgotten/houses/forgotten_large_farm_1", 3, templatePoolFactoryContext, BWGStructureProcessorLists.FORGOTTEN_VILLAGE_BLOCKS),
                    emptyPoolElement(6)
            ), StructureTemplatePool.Projection.RIGID));
    
    
    // Salem Village Template Pools
    private static final ResourceKey<StructureTemplatePool> SALEM_TERMINATOR = createTerminatorPool("salem", BWGStructureProcessorLists.SALEM_STREETS);

    public static final ResourceKey<StructureTemplatePool> SALEM_TOWN_CENTERS = register("salem/town_centers", templatePoolFactoryContext ->
            createTemplatePool(getEmptyPool(templatePoolFactoryContext), ImmutableList.of(
                    singlePoolElement("salem/town_centers/salem_meeting_point_1", templatePoolFactoryContext, BWGStructureProcessorLists.SALEM_TOWN_CENTER),
                    singlePoolElement("salem/town_centers/salem_meeting_point_2", templatePoolFactoryContext, BWGStructureProcessorLists.SALEM_TOWN_CENTER)
            ), StructureTemplatePool.Projection.RIGID));

    private static final ResourceKey<StructureTemplatePool> SALEM_DECOR = register("salem/decor", templatePoolFactoryContext ->
            createTemplatePool(getEmptyPool(templatePoolFactoryContext), ImmutableList.of(
                    singlePoolElement("salem/salem_lamp_1", 10),
                    featureElement(templatePoolFactoryContext, VillagePlacements.PILE_HAY_VILLAGE, 2),
                    emptyPoolElement(6)
            ), StructureTemplatePool.Projection.RIGID));

    public static final ResourceKey<StructureTemplatePool> SALEM_STREETS = register("salem/streets", templatePoolFactoryContext ->
            createTemplatePool(getPool(templatePoolFactoryContext, SALEM_TERMINATOR), ImmutableList.of(
                    singlePoolElement("salem/streets/salem_corner_01", 3, templatePoolFactoryContext, BWGStructureProcessorLists.SALEM_STREETS),
                    singlePoolElement("salem/streets/salem_corner_02", 3, templatePoolFactoryContext, BWGStructureProcessorLists.SALEM_STREETS),
                    singlePoolElement("salem/streets/salem_straight_01", 4, templatePoolFactoryContext, BWGStructureProcessorLists.SALEM_STREETS),
                    singlePoolElement("salem/streets/salem_straight_02", 4, templatePoolFactoryContext, BWGStructureProcessorLists.SALEM_STREETS),
                    singlePoolElement("salem/streets/salem_straight_03", 3, templatePoolFactoryContext, BWGStructureProcessorLists.SALEM_STREETS),
                    singlePoolElement("salem/streets/salem_crossroad_01", 3, templatePoolFactoryContext, BWGStructureProcessorLists.SALEM_STREETS),
                    singlePoolElement("salem/streets/salem_crossroad_02", 3, templatePoolFactoryContext, BWGStructureProcessorLists.SALEM_STREETS),
                    singlePoolElement("salem/streets/salem_crossroad_03", 3, templatePoolFactoryContext, BWGStructureProcessorLists.SALEM_STREETS),
                    singlePoolElement("salem/streets/salem_square_01", 3, templatePoolFactoryContext, BWGStructureProcessorLists.SALEM_STREETS),
                    singlePoolElement("salem/streets/salem_square_02", 3, templatePoolFactoryContext, BWGStructureProcessorLists.SALEM_STREETS),
                    singlePoolElement("salem/streets/salem_turn_01", 3, templatePoolFactoryContext, BWGStructureProcessorLists.SALEM_STREETS)
            ), StructureTemplatePool.Projection.TERRAIN_MATCHING));

    public static final ResourceKey<StructureTemplatePool> SALEM_VILLAGERS = register("salem/villagers", templatePoolFactoryContext ->
            createTemplatePool(getEmptyPool(templatePoolFactoryContext), ImmutableList.of(
                    singlePoolElement("salem/villagers/baby", 2),
                    singlePoolElement("salem/villagers/unemployed", 10)
            ), StructureTemplatePool.Projection.RIGID));

    public static final ResourceKey<StructureTemplatePool> SALEM_HOUSES = register("salem/houses", templatePoolFactoryContext ->
            createTemplatePool(getPool(templatePoolFactoryContext, SALEM_TERMINATOR), ImmutableList.of(
                    singlePoolElement("salem/houses/salem_small_house_1", 3, templatePoolFactoryContext, BWGStructureProcessorLists.SALEM_HOUSES),
                    legacyPoolElement("salem/houses/salem_small_house_2", 3),
                    legacyPoolElement("salem/houses/salem_small_house_3", 3),
                    legacyPoolElement("salem/houses/salem_medium_house_1", 3),
                    legacyPoolElement("salem/houses/salem_medium_house_2", 3),
                    legacyPoolElement("salem/houses/salem_medium_house_3", 3),
                    legacyPoolElement("salem/houses/salem_medium_house_4", 3),
                    //legacyPoolElement("salem/houses/salem_large_house_1", 2), TODO: Add large house NBT Currently broken
                    legacyPoolElement("salem/houses/salem_shepherd_house_1", 3),
                    legacyPoolElement("salem/houses/salem_mason_1", 2),
                    legacyPoolElement("salem/houses/salem_temple", 3),
                    legacyPoolElement("salem/houses/salem_weaponsmith_1", 2),
                    legacyPoolElement("salem/houses/salem_toolsmith_1", 2),
                    legacyPoolElement("salem/houses/salem_tannery_1", 2),
                    legacyPoolElement("salem/houses/salem_fisher_1", 2),
                    legacyPoolElement("salem/houses/salem_fletcher_house_1", 2),
                    legacyPoolElement("salem/houses/salem_forager_1", 2),
                    legacyPoolElement("salem/houses/salem_cartographer_house_1", 2),
                    legacyPoolElement("salem/houses/salem_butcher_shop_1", 2),
                    legacyPoolElement("salem/houses/salem_armorsmith_1", 2),
                    legacyPoolElement("salem/houses/salem_animal_pen_1", 2),
                    singlePoolElement("salem/houses/salem_animal_pen_2", 2, templatePoolFactoryContext, BWGStructureProcessorLists.SALEM_HOUSES),
                    legacyPoolElement("salem/houses/salem_small_farm_1", 2, templatePoolFactoryContext, ProcessorLists.FARM_TAIGA),
                    legacyPoolElement("salem/houses/salem_small_farm_2", 2, templatePoolFactoryContext, ProcessorLists.FARM_TAIGA),
                    legacyPoolElement("salem/houses/salem_large_farm_1", 3, templatePoolFactoryContext, ProcessorLists.FARM_TAIGA),
                    emptyPoolElement(10)
            ), StructureTemplatePool.Projection.RIGID));

    //Red Rock Village Template Pools
    private static final ResourceKey<StructureTemplatePool> RED_ROCK_TERMINATOR = createTerminatorPool("red_rock");

    public static final ResourceKey<StructureTemplatePool> RED_ROCK_TOWN_CENTERS = register("red_rock/town_centers", templatePoolFactoryContext ->
            createTemplatePool(getEmptyPool(templatePoolFactoryContext), ImmutableList.of(
                    singlePoolElement("red_rock/town_centers/red_rock_meeting_point_1", templatePoolFactoryContext, BWGStructureProcessorLists.RED_ROCK_MEETING_POINT_1),
                    singlePoolElement("red_rock/town_centers/red_rock_meeting_point_2", templatePoolFactoryContext, BWGStructureProcessorLists.RED_ROCK_CRACKED_BRICKS_50_PERCENT_MOSSY_RED_ROCK_BRICKS),
                    singlePoolElement("red_rock/town_centers/red_rock_meeting_point_3", templatePoolFactoryContext, BWGStructureProcessorLists.RED_ROCK_MEETING_POINT_3)
            ), StructureTemplatePool.Projection.RIGID));

    private static final ResourceKey<StructureTemplatePool> RED_ROCK_DECOR = register("red_rock/decor", templatePoolFactoryContext ->
            createTemplatePool(getEmptyPool(templatePoolFactoryContext), ImmutableList.of(
                    singlePoolElement("red_rock/red_rock_lamp_1", 10),
                    featureElement(templatePoolFactoryContext, VillagePlacements.PILE_HAY_VILLAGE, 2),
                    emptyPoolElement(6)
            ), StructureTemplatePool.Projection.RIGID));

    public static final ResourceKey<StructureTemplatePool> RED_ROCK_STREETS = register("red_rock/streets", templatePoolFactoryContext ->
            createTemplatePool(getPool(templatePoolFactoryContext, RED_ROCK_TERMINATOR), ImmutableList.of(
                    singlePoolElement("red_rock/streets/red_rock_corner_01", 3, templatePoolFactoryContext, BWGStructureProcessorLists.RED_ROCK_STREETS),
                    singlePoolElement("red_rock/streets/red_rock_corner_02", 3, templatePoolFactoryContext, BWGStructureProcessorLists.RED_ROCK_STREETS),
                    singlePoolElement("red_rock/streets/red_rock_straight_01", 4, templatePoolFactoryContext, BWGStructureProcessorLists.RED_ROCK_STREETS),
                    singlePoolElement("red_rock/streets/red_rock_straight_02", 4, templatePoolFactoryContext, BWGStructureProcessorLists.RED_ROCK_STREETS),
                    singlePoolElement("red_rock/streets/red_rock_straight_03", 3, templatePoolFactoryContext, BWGStructureProcessorLists.RED_ROCK_STREETS),
                    singlePoolElement("red_rock/streets/red_rock_crossroad_01", 3, templatePoolFactoryContext, BWGStructureProcessorLists.RED_ROCK_STREETS),
                    singlePoolElement("red_rock/streets/red_rock_crossroad_02", 3, templatePoolFactoryContext, BWGStructureProcessorLists.RED_ROCK_STREETS),
                    singlePoolElement("red_rock/streets/red_rock_crossroad_03", 3, templatePoolFactoryContext, BWGStructureProcessorLists.RED_ROCK_STREETS),
                    singlePoolElement("red_rock/streets/red_rock_square_01", 3, templatePoolFactoryContext, BWGStructureProcessorLists.RED_ROCK_STREETS),
                    singlePoolElement("red_rock/streets/red_rock_square_02", 3, templatePoolFactoryContext, BWGStructureProcessorLists.RED_ROCK_STREETS),
                    singlePoolElement("red_rock/streets/red_rock_turn_01", 3, templatePoolFactoryContext, BWGStructureProcessorLists.RED_ROCK_STREETS)
            ), StructureTemplatePool.Projection.TERRAIN_MATCHING));

    public static final ResourceKey<StructureTemplatePool> RED_ROCK_VILLAGERS = register("red_rock/villagers", templatePoolFactoryContext ->
            createTemplatePool(getEmptyPool(templatePoolFactoryContext), ImmutableList.of(
                    singlePoolElement("red_rock/villagers/baby", 2),
                    singlePoolElement("red_rock/villagers/unemployed", 10)
            ), StructureTemplatePool.Projection.RIGID));

    public static final ResourceKey<StructureTemplatePool> RED_ROCK_HOUSES = register("red_rock/houses", templatePoolFactoryContext ->
            createTemplatePool(getPool(templatePoolFactoryContext, RED_ROCK_TERMINATOR), ImmutableList.of(
                    singlePoolElement("red_rock/houses/red_rock_small_house_1", 3, templatePoolFactoryContext, BWGStructureProcessorLists.RED_ROCK_TO_BRICKS),
                    singlePoolElement("red_rock/houses/red_rock_small_house_2", 3),
                    singlePoolElement("red_rock/houses/red_rock_small_house_3", 3),
                    singlePoolElement("red_rock/houses/red_rock_small_house_4", 3),
                    singlePoolElement("red_rock/houses/red_rock_small_house_5", 3),
                    singlePoolElement("red_rock/houses/red_rock_medium_house_1", 3, templatePoolFactoryContext, BWGStructureProcessorLists.RED_ROCK_HOUSE),
                    singlePoolElement("red_rock/houses/red_rock_medium_house_2", 3, templatePoolFactoryContext, BWGStructureProcessorLists.RED_ROCK_HOUSE),
                    singlePoolElement("red_rock/houses/red_rock_large_house_1", 2, templatePoolFactoryContext, BWGStructureProcessorLists.RED_ROCK_HOUSE),
                    singlePoolElement("red_rock/houses/red_rock_shepherd_house_1", 3),
                    singlePoolElement("red_rock/houses/red_rock_mason_1", 2, templatePoolFactoryContext, BWGStructureProcessorLists.RED_ROCK_HOUSE),
                    singlePoolElement("red_rock/houses/red_rock_cleric", 2, templatePoolFactoryContext, BWGStructureProcessorLists.RED_ROCK_HOUSE),
                    singlePoolElement("red_rock/houses/red_rock_farm_small_1", 2, templatePoolFactoryContext, ProcessorLists.FARM_DESERT),
                    singlePoolElement("red_rock/houses/red_rock_farm_small_2", 2, templatePoolFactoryContext, ProcessorLists.FARM_DESERT),
                    singlePoolElement("red_rock/houses/red_rock_farm_large_1", 3, templatePoolFactoryContext, ProcessorLists.FARM_DESERT)
            ), StructureTemplatePool.Projection.RIGID));

    //Pumpkin Patch Village Template Pools
    private static final ResourceKey<StructureTemplatePool> PUMPKIN_PATCH_TERMINATOR = createTerminatorPool("pumpkin_patch", BWGStructureProcessorLists.PUMPKIN_PATCH_STREETS);

    public static final ResourceKey<StructureTemplatePool> PUMPKIN_PATCH_TOWN_CENTERS = register("pumpkin_patch/town_centers", templatePoolFactoryContext ->
            createTemplatePool(getEmptyPool(templatePoolFactoryContext), ImmutableList.of(
                    legacyPoolElement("pumpkin_patch/town_centers/pumpkin_patch_meeting_point_1"),
                    legacyPoolElement("pumpkin_patch/town_centers/pumpkin_patch_meeting_point_2"),
                    legacyPoolElement("pumpkin_patch/town_centers/pumpkin_patch_meeting_point_3")
            ), StructureTemplatePool.Projection.RIGID));

    private static final ResourceKey<StructureTemplatePool> PUMPKIN_PATCH_DECOR = register("pumpkin_patch/decor", templatePoolFactoryContext ->
            createTemplatePool(getEmptyPool(templatePoolFactoryContext), ImmutableList.of(
                    singlePoolElement("pumpkin_patch/pumpkin_patch_lamp_1", 10),
                    featureElement(templatePoolFactoryContext, VillagePlacements.PILE_PUMPKIN_VILLAGE, 2),
                    featureElement(templatePoolFactoryContext, VillagePlacements.PILE_HAY_VILLAGE, 1),
                    emptyPoolElement(5)
            ), StructureTemplatePool.Projection.RIGID));

    public static final ResourceKey<StructureTemplatePool> PUMPKIN_PATCH_STREETS = register("pumpkin_patch/streets", templatePoolFactoryContext ->
            createTemplatePool(getPool(templatePoolFactoryContext, PUMPKIN_PATCH_TERMINATOR), ImmutableList.of(
                    singlePoolElement("pumpkin_patch/streets/pumpkin_patch_corner_01", 3, templatePoolFactoryContext, BWGStructureProcessorLists.PUMPKIN_PATCH_STREETS),
                    singlePoolElement("pumpkin_patch/streets/pumpkin_patch_corner_02", 3, templatePoolFactoryContext, BWGStructureProcessorLists.PUMPKIN_PATCH_STREETS),
                    singlePoolElement("pumpkin_patch/streets/pumpkin_patch_straight_01", 4, templatePoolFactoryContext, BWGStructureProcessorLists.PUMPKIN_PATCH_STREETS),
                    singlePoolElement("pumpkin_patch/streets/pumpkin_patch_straight_02", 4, templatePoolFactoryContext, BWGStructureProcessorLists.PUMPKIN_PATCH_STREETS),
                    singlePoolElement("pumpkin_patch/streets/pumpkin_patch_straight_03", 3, templatePoolFactoryContext, BWGStructureProcessorLists.PUMPKIN_PATCH_STREETS),
                    singlePoolElement("pumpkin_patch/streets/pumpkin_patch_crossroad_01", 3, templatePoolFactoryContext, BWGStructureProcessorLists.PUMPKIN_PATCH_STREETS),
                    singlePoolElement("pumpkin_patch/streets/pumpkin_patch_crossroad_02", 3, templatePoolFactoryContext, BWGStructureProcessorLists.PUMPKIN_PATCH_STREETS),
                    singlePoolElement("pumpkin_patch/streets/pumpkin_patch_crossroad_02", 3),
                    singlePoolElement("pumpkin_patch/streets/pumpkin_patch_square_01", 3, templatePoolFactoryContext, BWGStructureProcessorLists.PUMPKIN_PATCH_STREETS),
                    singlePoolElement("pumpkin_patch/streets/pumpkin_patch_square_02", 3, templatePoolFactoryContext, BWGStructureProcessorLists.PUMPKIN_PATCH_STREETS),
                    singlePoolElement("pumpkin_patch/streets/pumpkin_patch_turn_01", 3, templatePoolFactoryContext, BWGStructureProcessorLists.PUMPKIN_PATCH_STREETS)
            ), StructureTemplatePool.Projection.TERRAIN_MATCHING));

    public static final ResourceKey<StructureTemplatePool> PUMPKIN_PATCH_HOUSES = register("pumpkin_patch/houses", templatePoolFactoryContext ->
            createTemplatePool(getPool(templatePoolFactoryContext, PUMPKIN_PATCH_TERMINATOR), ImmutableList.of(
                    singlePoolElement("pumpkin_patch/houses/pumpkin_patch_small_house_1", 3, templatePoolFactoryContext, BWGStructureProcessorLists.PUMPKIN_PATCH_HOUSE),
                    singlePoolElement("pumpkin_patch/houses/pumpkin_patch_small_house_2", 3, templatePoolFactoryContext, BWGStructureProcessorLists.PUMPKIN_PATCH_HOUSE),
                    legacyPoolElement("pumpkin_patch/houses/pumpkin_patch_small_house_3", 3),
                    legacyPoolElement("pumpkin_patch/houses/pumpkin_patch_small_house_4", 3),
                    legacyPoolElement("pumpkin_patch/houses/pumpkin_patch_medium_house_1", 3),
                    legacyPoolElement("pumpkin_patch/houses/pumpkin_patch_medium_house_2", 2),
                    legacyPoolElement("pumpkin_patch/houses/pumpkin_patch_medium_house_3", 3),
                    legacyPoolElement("pumpkin_patch/houses/pumpkin_patch_small_farm_1", 2, templatePoolFactoryContext, ProcessorLists.FARM_TAIGA),
                    legacyPoolElement("pumpkin_patch/houses/pumpkin_patch_small_farm_2", 3, templatePoolFactoryContext, ProcessorLists.FARM_TAIGA),
                    legacyPoolElement("pumpkin_patch/houses/pumpkin_patch_small_farm_3", 2, templatePoolFactoryContext, ProcessorLists.FARM_TAIGA),
                    legacyPoolElement("pumpkin_patch/houses/pumpkin_patch_small_farm_4", 3, templatePoolFactoryContext, ProcessorLists.FARM_TAIGA),
                    legacyPoolElement("pumpkin_patch/houses/pumpkin_patch_small_farm_5", 2, templatePoolFactoryContext, ProcessorLists.FARM_TAIGA),
                    emptyPoolElement(10)
            ), StructureTemplatePool.Projection.RIGID));


    private static final ResourceKey<StructureTemplatePool> PUMPKIN_PATCH_WARDS = register("pumpkin_patch/wards", templatePoolFactoryContext ->
            createTemplatePool(getEmptyPool(templatePoolFactoryContext), ImmutableList.of(
                    singlePoolElement("pumpkin_patch/wards/pumpkin_ward")
            ), StructureTemplatePool.Projection.RIGID));

    private static Pair<Function<StructureTemplatePool.Projection, ? extends StructurePoolElement>, Integer> emptyPoolElement(int weight) {
        return Pair.of(StructurePoolElement.empty(), weight);
    }

    private static Pair<Function<StructureTemplatePool.Projection, ? extends StructurePoolElement>, Integer> featureElement(BootstapContext<StructureTemplatePool> context, ResourceKey<PlacedFeature> placedFeature, int weight) {
        return Pair.of(StructurePoolElement.feature(context.lookup(Registries.PLACED_FEATURE).getOrThrow(placedFeature)), weight);
    }

    private static Pair<Function<StructureTemplatePool.Projection, ? extends StructurePoolElement>, Integer> legacyPoolElement(String id) {
        return legacyPoolElement(id, 1);
    }

    private static Pair<Function<StructureTemplatePool.Projection, ? extends StructurePoolElement>, Integer> legacyPoolElement(String id, int weight) {
        return Pair.of(StructurePoolElement.legacy(BiomesWeveGone.id("village/" + id).toString()), weight);
    }

    private static Pair<Function<StructureTemplatePool.Projection, ? extends StructurePoolElement>, Integer> legacyPoolElement(String id, BootstapContext<StructureTemplatePool> context, ResourceKey<StructureProcessorList> processorList) {
        return legacyPoolElement(id, 1, context, processorList);
    }

    private static Pair<Function<StructureTemplatePool.Projection, ? extends StructurePoolElement>, Integer> legacyPoolElement(String id, int weight, BootstapContext<StructureTemplatePool> context, ResourceKey<StructureProcessorList> processorList) {
        return Pair.of(StructurePoolElement.legacy(BiomesWeveGone.id("village/" + id).toString(), getProcessor(context, processorList)), weight);
    }

    private static Pair<Function<StructureTemplatePool.Projection, ? extends StructurePoolElement>, Integer> singlePoolElement(String id) {
        return singlePoolElement(id, 1);
    }

    private static Pair<Function<StructureTemplatePool.Projection, ? extends StructurePoolElement>, Integer> singlePoolElement(String id, int weight) {
        return Pair.of(StructurePoolElement.single(BiomesWeveGone.id("village/" + id).toString()), weight);
    }

    private static Pair<Function<StructureTemplatePool.Projection, ? extends StructurePoolElement>, Integer> singlePoolElement(String id, BootstapContext<StructureTemplatePool> context, ResourceKey<StructureProcessorList> processorList) {
        return singlePoolElement(id, 1, context, processorList);
    }

    private static Pair<Function<StructureTemplatePool.Projection, ? extends StructurePoolElement>, Integer> singlePoolElement(String id, int weight, BootstapContext<StructureTemplatePool> context, ResourceKey<StructureProcessorList> processorList) {
        return Pair.of(StructurePoolElement.single(BiomesWeveGone.id("village/" + id).toString(), getProcessor(context, processorList)), weight);
    }

    private static StructureTemplatePool createTemplatePool(Holder<StructureTemplatePool> fallback, List<Pair<Function<StructureTemplatePool.Projection, ? extends StructurePoolElement>, Integer>> rawTemplateFactories, StructureTemplatePool.Projection projection) {
        return new StructureTemplatePool(fallback, rawTemplateFactories, projection);
    }

    private static Holder.Reference<StructureTemplatePool> getEmptyPool(BootstapContext<StructureTemplatePool> context) {
        return getPool(context, Pools.EMPTY);
    }

    private static Holder.Reference<StructureTemplatePool> getPool(BootstapContext<StructureTemplatePool> context, ResourceKey<StructureTemplatePool> poolResourceKey) {
        return context.lookup(Registries.TEMPLATE_POOL).getOrThrow(poolResourceKey);
    }

    private static Holder.Reference<StructureProcessorList> getProcessor(BootstapContext<StructureTemplatePool> context, ResourceKey<StructureProcessorList> processorList) {
        return context.lookup(Registries.PROCESSOR_LIST).getOrThrow(processorList);
    }

    private static ResourceKey<StructureTemplatePool> createTerminatorPool(String id) {
        return register(id + "/terminators", templatePoolFactoryContext ->
                createTemplatePool(getEmptyPool(templatePoolFactoryContext), ImmutableList.of(
                        legacyPoolElement(id + "/terminators/" + id + "_terminator_01"),
                        legacyPoolElement(id + "/terminators/" + id + "_terminator_02")
                ), StructureTemplatePool.Projection.TERRAIN_MATCHING));
    }

    private static ResourceKey<StructureTemplatePool> createTerminatorPool(String id, ResourceKey<StructureProcessorList> processorList) {
        return register(id + "/terminators", templatePoolFactoryContext ->
                createTemplatePool(getEmptyPool(templatePoolFactoryContext), ImmutableList.of(
                        legacyPoolElement(id + "/terminators/" + id + "_terminator_01", templatePoolFactoryContext, processorList),
                        legacyPoolElement(id + "/terminators/" + id + "_terminator_02", templatePoolFactoryContext, processorList)
                ), StructureTemplatePool.Projection.TERRAIN_MATCHING));
    }

    private static ResourceKey<StructureTemplatePool> register(String id, BWGTemplatePools.TemplatePoolFactory factory) {
        ResourceKey<StructureTemplatePool> templatePoolResourceKey = BiomesWeveGone.key(Registries.TEMPLATE_POOL, "village/" + id);
        BWGTemplatePools.TEMPLATE_POOL_FACTORIES.put(templatePoolResourceKey, factory);
        return templatePoolResourceKey;
    }

    public static void villageTemplatePools() {
        BiomesWeveGone.LOGGER.info("Registering Oh The Biome's We've Gone Village Template Pools");
    }
}
