package net.potionstudios.biomeswevegone.world.level.levelgen.structure;

import com.mojang.datafixers.util.Pair;
import corgitaco.corgilib.math.blendingfunction.BlendingFunction;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.tags.BWGBiomeTags;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.levelgen.BWGWorldGenerationUtil;
import net.potionstudios.biomeswevegone.world.level.levelgen.CheckedBlockPlacement;
import net.potionstudios.biomeswevegone.world.level.levelgen.blockpredicates.RandomChancePredicate;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.placed.BWGOverworldVegationPlacedFeatures;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.arch.ArchConfig;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.arch.ArchStructure;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.lake.LargeLakeConfig;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.lake.LargeLakeStructure;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.plateau.GourPlateauStructure;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.sharpenedrock.SharpenedRockConfig;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.sharpenedrock.SharpenedRockStructure;

import java.util.List;
import java.util.Map;

public class BWGStructures {
    public static final Map<ResourceKey<Structure>, StructureFactory> STRUCTURE_FACTORIES = new Reference2ObjectOpenHashMap<>();

    public static final ResourceKey<Structure> SHARPENED_ROCK = register("sharpened_rock", (structureFactoryBootstapContext) ->
            new SharpenedRockStructure(
                    structure(structureFactoryBootstapContext.lookup(Registries.BIOME).getOrThrow(BWGBiomeTags.SHARPENED_ROCKS), GenerationStep.Decoration.RAW_GENERATION, TerrainAdjustment.NONE),
                    new SharpenedRockConfig(UniformInt.of(20, 60), UniformFloat.of(40, 50), UniformFloat.of(0, 360F)))
    );

    public static final ResourceKey<Structure> IRONWOOD_GOUR_PLATEAU = register("ironwood_gour_plateau", (structureFactoryBootstapContext) ->
            new GourPlateauStructure(
                    structure(structureFactoryBootstapContext.lookup(Registries.BIOME).getOrThrow(BWGBiomeTags.IRONWOOD_GOUR_PLATEAU), GenerationStep.Decoration.RAW_GENERATION, TerrainAdjustment.NONE)
            )
    );

    public static final ResourceKey<Structure> LARGE_LAKE = register("large_cold_lake", (structureFactoryBootstapContext) ->
            {
                HolderGetter<PlacedFeature> placedFeatureHolderGetter = structureFactoryBootstapContext.lookup(Registries.PLACED_FEATURE);
                return new LargeLakeStructure(
                        new Structure.StructureSettings(structureFactoryBootstapContext.lookup(Registries.BIOME).getOrThrow(BWGBiomeTags.LARGE_COLD_LAKE),
                                Map.of(
                                        MobCategory.WATER_AMBIENT,
                                        new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, SimpleWeightedRandomList.create(new MobSpawnSettings.SpawnerData(EntityType.SALMON, 5, 4, 10)))
                                ),
                                GenerationStep.Decoration.RAW_GENERATION, TerrainAdjustment.NONE),
                        new LargeLakeConfig(
                                HolderSet.direct(placedFeatureHolderGetter.getOrThrow(BWGOverworldVegationPlacedFeatures.COLD_LAKE_VEGETATION)),
                                UniformInt.of(96, 128),
                                UniformInt.of(20, 30)
                        )
                );
            }
    );

    public static final ResourceKey<Structure> LUSH_ARCH = register("lush_arch", (structureFactoryBootstapContext) ->
            new ArchStructure(
                    structure(structureFactoryBootstapContext.lookup(Registries.BIOME).getOrThrow(BWGBiomeTags.LUSH_ARCH), GenerationStep.Decoration.RAW_GENERATION, TerrainAdjustment.NONE),
                    new ArchConfig(UniformInt.of(64, 200), UniformInt.of(50, 150),
                            new CheckedBlockPlacement(List.of(
                                    Pair.of(BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, BlockStateProvider.simple(Blocks.STONE)),
                                    Pair.of(BlockPredicate.allOf(
                                                    new RandomChancePredicate(ConstantFloat.of(0.4F)),
                                                    BlockPredicate.anyOf(BWGWorldGenerationUtil.blockMatchesInAllDirections(blockPos -> BlockPredicate.matchesBlocks(blockPos, Blocks.AIR, Blocks.CAVE_AIR)))
                                            ),
                                            new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.MOSS_BLOCK.defaultBlockState(), 1).add(Blocks.MOSSY_COBBLESTONE.defaultBlockState(), 2))
                                    )
                            )),
                            new ArchConfig.ArchGeneratorConfig(8, 8,
                                    SimpleWeightedRandomList.single(new ArchConfig.GenerationConfig(UniformInt.of(10, 30), UniformFloat.of(0.05F, 0.1F))),
                                    SimpleWeightedRandomList.<BlendingFunction>builder()
                                            .add(BlendingFunction.EaseOutCubic.INSTANCE, 1)
                                            .add(BlendingFunction.EaseInCirc.INSTANCE, 1)
                                            .add(BlendingFunction.EaseOutQuint.INSTANCE, 1)
                                            .add(BlendingFunction.EaseOutElastic.INSTANCE, 1)
                                            .add(BlendingFunction.EaseOutBounce.INSTANCE, 1)
                                            .build()
                            ))
            )
    );

    public static final ResourceKey<Structure> DRIPSTONE_ARCH = register("dripstone_arch", (structureFactoryBootstapContext) ->
            new ArchStructure(
                    structure(structureFactoryBootstapContext.lookup(Registries.BIOME).getOrThrow(BWGBiomeTags.DRIPSTONE_ARCH), GenerationStep.Decoration.RAW_GENERATION, TerrainAdjustment.NONE),
                    new ArchConfig(UniformInt.of(64, 200), UniformInt.of(50, 150),
                            new CheckedBlockPlacement(
                                    List.of(
                                            Pair.of(BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, BlockStateProvider.simple(Blocks.STONE)),
                                            Pair.of(BlockPredicate.allOf(
                                                            new RandomChancePredicate(ConstantFloat.of(0.4F)),
                                                            BlockPredicate.anyOf(BWGWorldGenerationUtil.blockMatchesInAllDirections(blockPos -> BlockPredicate.matchesBlocks(blockPos, Blocks.AIR, Blocks.CAVE_AIR, Blocks.WATER)))
                                                    ),
                                                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.DRIPSTONE_BLOCK.defaultBlockState(), 1))
                                            )
                                    )
                            ),
                            new ArchConfig.ArchGeneratorConfig(8, 8,
                                    SimpleWeightedRandomList.single(new ArchConfig.GenerationConfig(UniformInt.of(10, 30), UniformFloat.of(0.09F, 0.2F))),
                                    SimpleWeightedRandomList.<BlendingFunction>builder()
                                            .add(BlendingFunction.EaseOutCubic.INSTANCE, 1)
                                            .add(BlendingFunction.EaseInCirc.INSTANCE, 1)
                                            .add(BlendingFunction.EaseOutQuint.INSTANCE, 1)
                                            .add(BlendingFunction.EaseOutBounce.INSTANCE, 1)
                                            .build()
                            ))
            )
    );


    public static final ResourceKey<Structure> RED_ROCK_ARCH = register("red_rock_arch", (structureFactoryBootstapContext) ->
            new ArchStructure(
                    structure(structureFactoryBootstapContext.lookup(Registries.BIOME).getOrThrow(BWGBiomeTags.RED_ROCK_ARCH), GenerationStep.Decoration.RAW_GENERATION, TerrainAdjustment.NONE),
                    new ArchConfig(UniformInt.of(64, 200), UniformInt.of(50, 150),
                            new CheckedBlockPlacement(List.of(
                                    Pair.of(BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, BlockStateProvider.simple(BWGBlocks.RED_ROCK_SET.getBase())),
                                    Pair.of(BlockPredicate.allOf(
                                                    new RandomChancePredicate(ConstantFloat.of(0.4F)),
                                                    BlockPredicate.anyOf(BWGWorldGenerationUtil.blockMatchesInAllDirections(blockPos -> BlockPredicate.matchesBlocks(blockPos, Blocks.AIR, Blocks.CAVE_AIR, Blocks.WATER)))
                                            ),
                                            new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.ORANGE_TERRACOTTA.defaultBlockState(), 1))
                                    )
                            )),

                            new ArchConfig.ArchGeneratorConfig(4, 4,
                                    SimpleWeightedRandomList.single(new ArchConfig.GenerationConfig(UniformInt.of(20, 25), UniformFloat.of(0.05F, 0.1F))),
                                    SimpleWeightedRandomList.<BlendingFunction>builder()
                                            .add(BlendingFunction.EaseOutCubic.INSTANCE, 1)
                                            .add(BlendingFunction.EaseInCirc.INSTANCE, 1)
                                            .add(BlendingFunction.EaseOutQuint.INSTANCE, 1)
                                            .add(BlendingFunction.EaseOutBounce.INSTANCE, 1)
                                            .build()
                            ))
            )
    );

    public static final ResourceKey<Structure> PRAIRIE_HOUSE = register("prairie_house", (structureFactoryBootstapContext) ->
            createJigsaw(
                    structure(structureFactoryBootstapContext.lookup(Registries.BIOME).getOrThrow(BWGBiomeTags.StructureHasTags.HAS_PRAIRIE_HOUSE), TerrainAdjustment.BEARD_THIN),
                    structureFactoryBootstapContext.lookup(Registries.TEMPLATE_POOL).getOrThrow(BWGTemplatePools.PRAIRIE_HOUSE),
                    1,
                    ConstantHeight.of(VerticalAnchor.absolute(1)),
                    Heightmap.Types.WORLD_SURFACE_WG
            )
    );

    public static final ResourceKey<Structure> ABANDONED_PRAIRIE_HOUSE = register("abandoned_prairie_house", (structureFactoryBootstapContext) ->
            createJigsaw(
                    structure(structureFactoryBootstapContext.lookup(Registries.BIOME).getOrThrow(BWGBiomeTags.StructureHasTags.HAS_PRAIRIE_HOUSE), TerrainAdjustment.BEARD_THIN),
                    structureFactoryBootstapContext.lookup(Registries.TEMPLATE_POOL).getOrThrow(BWGTemplatePools.ABANDONED_PRAIRIE_HOUSE),
                    1,
                    ConstantHeight.of(VerticalAnchor.absolute(1)),
                    Heightmap.Types.WORLD_SURFACE_WG
            )
    );

    public static final ResourceKey<Structure> RUGGED_FOSSIL = register("rugged_fossil", (structureFactoryBootstapContext) ->
            createJigsaw(
                    structure(structureFactoryBootstapContext.lookup(Registries.BIOME).getOrThrow(BWGBiomeTags.StructureHasTags.HAS_RUGGED_FOSSIL), TerrainAdjustment.NONE),
                    structureFactoryBootstapContext.lookup(Registries.TEMPLATE_POOL).getOrThrow(BWGTemplatePools.RUGGED_FOSSIL),
                    1,
                    ConstantHeight.of(VerticalAnchor.absolute(1)),
                    Heightmap.Types.WORLD_SURFACE_WG
            )
    );

    public static final ResourceKey<Structure> ASPEN_MANOR_1 = register("aspen_manor_1", (structureFactoryBootstapContext) ->
            createJigsaw(
                    structure(structureFactoryBootstapContext.lookup(Registries.BIOME).getOrThrow(BWGBiomeTags.StructureHasTags.HAS_ASPEN_MANOR),
                            Map.of(
                                    MobCategory.MONSTER,
                                    new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.STRUCTURE, SimpleWeightedRandomList.create(new MobSpawnSettings.SpawnerData(EntityType.VINDICATOR, 25, 2, 4)))
                            ),
                            TerrainAdjustment.BEARD_THIN),
                    structureFactoryBootstapContext.lookup(Registries.TEMPLATE_POOL).getOrThrow(BWGTemplatePools.ASPEN_MANOR_1),
                    1,
                    ConstantHeight.of(VerticalAnchor.absolute(1)),
                    Heightmap.Types.WORLD_SURFACE_WG
            )
    );

    public static final ResourceKey<Structure> ASPEN_MANOR_2 = register("aspen_manor_2", (structureFactoryBootstapContext) ->
            createJigsaw(
                    structure(structureFactoryBootstapContext.lookup(Registries.BIOME).getOrThrow(BWGBiomeTags.StructureHasTags.HAS_ASPEN_MANOR),
                            Map.of(
                                    MobCategory.MONSTER,
                                    new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.STRUCTURE, SimpleWeightedRandomList.create(new MobSpawnSettings.SpawnerData(EntityType.VINDICATOR, 25, 2, 4)))
                            ),
                            TerrainAdjustment.BEARD_THIN),
                    structureFactoryBootstapContext.lookup(Registries.TEMPLATE_POOL).getOrThrow(BWGTemplatePools.ASPEN_MANOR_2),
                    1,
                    ConstantHeight.of(VerticalAnchor.absolute(1)),
                    Heightmap.Types.WORLD_SURFACE_WG
            )
    );

    public static final ResourceKey<Structure> BOG_TRIAL = register("bog_trial", (structureFactoryBootstapContext) ->
            createJigsaw(
                    structure(structureFactoryBootstapContext.lookup(Registries.BIOME).getOrThrow(BWGBiomeTags.StructureHasTags.HAS_BOG_TRIAL), TerrainAdjustment.BEARD_THIN),
                    structureFactoryBootstapContext.lookup(Registries.TEMPLATE_POOL).getOrThrow(BWGTemplatePools.BOG_TRIAL),
                    1,
                    ConstantHeight.of(VerticalAnchor.absolute(1)),
                    Heightmap.Types.WORLD_SURFACE_WG
            )
    );

    public static final ResourceKey<Structure> FORGOTTEN_VILLAGE = register("village/forgotten", (structureFactoryBootstapContext ->
            createJigsawWithExpansion(
                    structure(structureFactoryBootstapContext.lookup(Registries.BIOME).getOrThrow(BWGBiomeTags.StructureHasTags.HAS_VILLAGE_FORGOTTEN),
                            TerrainAdjustment.BEARD_THIN),
                    structureFactoryBootstapContext.lookup(Registries.TEMPLATE_POOL).getOrThrow(BWGVillageTemplatePools.FORGOTTEN_TOWN_CENTERS),
                    6,
                    ConstantHeight.of(VerticalAnchor.absolute(1)),
                    Heightmap.Types.WORLD_SURFACE_WG
            )));

    public static final ResourceKey<Structure> SKYRIS_VILLAGE = register("village/skyris", (structureFactoryBootstapContext ->
            createJigsawWithExpansion(
                    structure(structureFactoryBootstapContext.lookup(Registries.BIOME).getOrThrow(BWGBiomeTags.StructureHasTags.HAS_VILLAGE_SKYRIS),
                            TerrainAdjustment.BEARD_THIN),
                    structureFactoryBootstapContext.lookup(Registries.TEMPLATE_POOL).getOrThrow(BWGVillageTemplatePools.SKYRIS_TOWN_CENTERS),
                    6,
                    ConstantHeight.of(VerticalAnchor.absolute(1)),
                    Heightmap.Types.WORLD_SURFACE_WG
            )));

    public static final ResourceKey<Structure> SALEM_VILLAGE = register("village/salem", (structureFactoryBootstapContext ->
            createJigsawWithExpansion(
                    structure(structureFactoryBootstapContext.lookup(Registries.BIOME).getOrThrow(BWGBiomeTags.StructureHasTags.HAS_VILLAGE_SALEM),
                            TerrainAdjustment.BEARD_THIN),
                    structureFactoryBootstapContext.lookup(Registries.TEMPLATE_POOL).getOrThrow(BWGVillageTemplatePools.SALEM_TOWN_CENTERS),
                    6,
                    ConstantHeight.of(VerticalAnchor.absolute(1)),
                    Heightmap.Types.WORLD_SURFACE_WG
            )));

    public static final ResourceKey<Structure> RED_ROCK_VILLAGE = register("village/red_rock", (structureFactoryBootstapContext ->
            createJigsawWithExpansion(
                    structure(structureFactoryBootstapContext.lookup(Registries.BIOME).getOrThrow(BWGBiomeTags.StructureHasTags.HAS_VILLAGE_RED_ROCK),
                            TerrainAdjustment.BEARD_THIN),
                    structureFactoryBootstapContext.lookup(Registries.TEMPLATE_POOL).getOrThrow(BWGVillageTemplatePools.RED_ROCK_TOWN_CENTERS),
                    6,
                    ConstantHeight.of(VerticalAnchor.absolute(1)),
                    Heightmap.Types.WORLD_SURFACE_WG
            )));

    public static final ResourceKey<Structure> PUMPKIN_PATCH_VILLAGE = register("village/pumpkin_patch", (structureFactoryBootstapContext ->
            createJigsawWithExpansion(
                    structure(structureFactoryBootstapContext.lookup(Registries.BIOME).getOrThrow(BWGBiomeTags.StructureHasTags.HAS_VILLAGE_PUMPKIN_PATCH),
                            TerrainAdjustment.BEARD_THIN),
                    structureFactoryBootstapContext.lookup(Registries.TEMPLATE_POOL).getOrThrow(BWGVillageTemplatePools.PUMPKIN_PATCH_TOWN_CENTERS),
                    6,
                    ConstantHeight.of(VerticalAnchor.absolute(1)),
                    Heightmap.Types.WORLD_SURFACE_WG
            )));

//    public static final ResourceKey<Structure> CANYON = register("canyon", (structureFactoryBootstapContext) ->
//            new CanyonStructure(
//                    structure(structureFactoryBootstapContext.lookup(Registries.BIOME).getOrThrow(BWGBiomeTags.CANYON), GenerationStep.Decoration.RAW_GENERATION, TerrainAdjustment.NONE)
//            )
//    );

    private static ResourceKey<Structure> register(String id, StructureFactory factory) {
        ResourceKey<Structure> structureSetResourceKey = BiomesWeveGone.key(Registries.STRUCTURE, id);
        STRUCTURE_FACTORIES.put(structureSetResourceKey, factory);
        return structureSetResourceKey;
    }

    private static Structure.StructureSettings structure(HolderSet<Biome> tag, TerrainAdjustment adj) {
        return new Structure.StructureSettings(tag, Map.of(), GenerationStep.Decoration.SURFACE_STRUCTURES, adj);
    }

    private static Structure.StructureSettings structure(HolderSet<Biome> tag, Map<MobCategory, StructureSpawnOverride> spawnOverrides, TerrainAdjustment adj) {
        return new Structure.StructureSettings(tag, spawnOverrides, GenerationStep.Decoration.SURFACE_STRUCTURES, adj);
    }

    private static Structure.StructureSettings structure(HolderSet<Biome> tag, GenerationStep.Decoration decoration, TerrainAdjustment adj) {
        return new Structure.StructureSettings(tag, Map.of(), decoration, adj);
    }

    private static JigsawStructure createJigsaw(Structure.StructureSettings settings, Holder<StructureTemplatePool> startPool, int maxDepth,
                                                HeightProvider startHeight, Heightmap.Types projectStartToHeightmap) {
        return new JigsawStructure(settings, startPool, maxDepth, startHeight, false, projectStartToHeightmap);
    }

    private static JigsawStructure createJigsawWithExpansion(Structure.StructureSettings settings, Holder<StructureTemplatePool> startPool, int maxDepth,
                                                             HeightProvider startHeight, Heightmap.Types projectStartToHeightmap) {
        return new JigsawStructure(settings, startPool, maxDepth, startHeight, true, projectStartToHeightmap);
    }


    @FunctionalInterface
    public interface StructureFactory {
        Structure generate(BootstrapContext<Structure> structureFactoryBootstapContext);
    }

}
