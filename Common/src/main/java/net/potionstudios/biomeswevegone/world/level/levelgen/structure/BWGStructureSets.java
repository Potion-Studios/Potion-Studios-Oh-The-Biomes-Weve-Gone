package net.potionstudios.biomeswevegone.world.level.levelgen.structure;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.potionstudios.biomeswevegone.BiomesWeveGone;

import java.util.Map;

public class BWGStructureSets {

    public static final Map<ResourceKey<StructureSet>, StructureSetFactory> STRUCTURE_SET_FACTORIES = new Reference2ObjectOpenHashMap<>();


    public static final ResourceKey<StructureSet> SHARPENED_ROCKS = register("sharpened_rocks", structureHolderGetter ->
            new StructureSet(ImmutableList.of(StructureSet.entry(structureHolderGetter.getOrThrow(BWGStructures.SHARPENED_ROCK))), new RandomSpreadStructurePlacement(12, 3, RandomSpreadType.TRIANGULAR, 348457856))
    );

    public static final ResourceKey<StructureSet> IRONWOOD_GOUR_PLATEAU = register("ironwood_gour_plateau", structureHolderGetter ->
        new StructureSet(ImmutableList.of(StructureSet.entry(structureHolderGetter.getOrThrow(BWGStructures.IRONWOOD_GOUR_PLATEAU))), new RandomSpreadStructurePlacement(4, 1, RandomSpreadType.TRIANGULAR, 596586))
    );

    public static final ResourceKey<StructureSet> LARGE_COLD_LAKE = register("large_cold_lake", structureHolderGetter ->
        new StructureSet(ImmutableList.of(StructureSet.entry(structureHolderGetter.getOrThrow(BWGStructures.LARGE_LAKE))), new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.TRIANGULAR, 546451665))
    );

    public static final ResourceKey<StructureSet> LUSH_ARCHES = register("lush_arch", structureHolderGetter ->
        new StructureSet(ImmutableList.of(StructureSet.entry(structureHolderGetter.getOrThrow(BWGStructures.LUSH_ARCH))), new RandomSpreadStructurePlacement(8, 2, RandomSpreadType.TRIANGULAR, 54546651))
    );

    public static final ResourceKey<StructureSet> DRIPSTONE_ARCHES = register("dripstone_arches", structureHolderGetter ->
        new StructureSet(ImmutableList.of(StructureSet.entry(structureHolderGetter.getOrThrow(BWGStructures.DRIPSTONE_ARCH))), new RandomSpreadStructurePlacement(8, 2, RandomSpreadType.TRIANGULAR, 1151551458))
    );

    public static final ResourceKey<StructureSet> RED_ROCK_ARCHES = register("red_rock_arches", structureHolderGetter ->
        new StructureSet(ImmutableList.of(StructureSet.entry(structureHolderGetter.getOrThrow(BWGStructures.RED_ROCK_ARCH))), new RandomSpreadStructurePlacement(16, 4, RandomSpreadType.TRIANGULAR, 5454151))
    );

    private static final ResourceKey<StructureSet> PRAIRIE_HOUSES = register("prairie_houses", structureHolderGetter -> new StructureSet(
            ImmutableList.of(StructureSet.entry(structureHolderGetter.getOrThrow(BWGStructures.PRAIRIE_HOUSE), 5),
                    StructureSet.entry(structureHolderGetter.getOrThrow(BWGStructures.ABANDONED_PRAIRIE_HOUSE), 4))
            , new RandomSpreadStructurePlacement(25, 20, RandomSpreadType.LINEAR, 703905857)));

    private static final ResourceKey<StructureSet> RUGGED_FOSSILS = register("rugged_fossils", structureHolderGetter -> new StructureSet(
            ImmutableList.of(StructureSet.entry(structureHolderGetter.getOrThrow(BWGStructures.RUGGED_FOSSIL), 5))
            , new RandomSpreadStructurePlacement(8, 6, RandomSpreadType.LINEAR, 682594027)));

    private static final ResourceKey<StructureSet> ASPEN_MANORS = register("aspen_manors", structureHolderGetter -> new StructureSet(
            ImmutableList.of(StructureSet.entry(structureHolderGetter.getOrThrow(BWGStructures.ASPEN_MANOR_1), 1),
                    StructureSet.entry(structureHolderGetter.getOrThrow(BWGStructures.ASPEN_MANOR_2), 1))
            , new RandomSpreadStructurePlacement(50, 35, RandomSpreadType.LINEAR, 372893981)));

    private static final ResourceKey<StructureSet> VILLAGES = register("villages", structureHolderGetter -> new StructureSet(
            ImmutableList.of(
                    StructureSet.entry(structureHolderGetter.getOrThrow(BWGStructures.FORGOTTEN_VILLAGE)),
                    StructureSet.entry(structureHolderGetter.getOrThrow(BWGStructures.SKYRIS_VILLAGE)),
                    StructureSet.entry(structureHolderGetter.getOrThrow(BWGStructures.SALEM_VILLAGE)),
                    StructureSet.entry(structureHolderGetter.getOrThrow(BWGStructures.RED_ROCK_VILLAGE)),
                    StructureSet.entry(structureHolderGetter.getOrThrow(BWGStructures.PUMPKIN_PATCH_VILLAGE))
            ), new RandomSpreadStructurePlacement(34, 8, RandomSpreadType.LINEAR, 437845874)
    ));


    private static ResourceKey<StructureSet> register(String id, StructureSetFactory factory) {
        ResourceKey<StructureSet> structureSetResourceKey = ResourceKey.create(Registries.STRUCTURE_SET, BiomesWeveGone.id(id));
        STRUCTURE_SET_FACTORIES.put(structureSetResourceKey, factory);
        return structureSetResourceKey;
    }

    @FunctionalInterface
    public interface StructureSetFactory {
        StructureSet generate(HolderGetter<Structure> placedFeatureHolderGetter);
    }
}
