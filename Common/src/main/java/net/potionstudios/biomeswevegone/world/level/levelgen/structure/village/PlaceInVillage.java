package net.potionstudios.biomeswevegone.world.level.levelgen.structure.village;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.processor.BWGStructureProcessorLists;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to add structures to villages.
 * We do this by adding the structures to the existing village structure pool.
 * @author Joseph T. McQuigg
 */
public class PlaceInVillage {

    /**
     * Adds a building to a village structure pool.
     *
     * @param server         The Server.
     * @param poolRL         The pool to add the building to.
     * @param processorList  The processor list to use.
     * @param nbtPieceRL     The nbt piece to add.
     * @param projection     The projection to use.
     * @param weight         The weight of the building.
     */
    private static void addBuildingToPool(@NotNull MinecraftServer server, ResourceLocation poolRL, ResourceKey<StructureProcessorList> processorList, ResourceLocation nbtPieceRL, StructureTemplatePool.Projection projection, int weight) {
        RegistryAccess.Frozen serverRegistry = server.registryAccess();
        Registry<StructureTemplatePool> templatePoolRegistry = serverRegistry.lookupOrThrow(Registries.TEMPLATE_POOL);
        Registry<StructureProcessorList> processorListRegistry = serverRegistry.lookupOrThrow(Registries.PROCESSOR_LIST);
        StructureTemplatePool pool = templatePoolRegistry.getValue(poolRL);
        Holder<StructureProcessorList> processorList1 = processorListRegistry.getOrThrow(processorList);
        if (pool == null) return;

        SinglePoolElement piece = SinglePoolElement.single(nbtPieceRL.toString(), processorList1).apply(projection);

        for (int i = 0; i < weight; i++)
            pool.templates.add(piece);

        List<Pair<StructurePoolElement, Integer>> listOfPieceEntries = new ArrayList<>(pool.rawTemplates);
        listOfPieceEntries.add(new Pair<>(piece, weight));
        pool.rawTemplates = listOfPieceEntries;
    }

    /**
     * Adds structures to villages.
     * @param server The server to add the structures to.
     */
    public static void addStructuresToVillages(@NotNull MinecraftServer server) {
        addBuildingToPool(server, getMcRL("plains/houses"), BWGStructureProcessorLists.MOSSIFY_10_PERCENT_WHITE_PUFFBALL, BiomesWeveGone.id("minecraft/village/plains/houses/plains_forager_1"), StructureTemplatePool.Projection.RIGID, 2);
        addBuildingToPool(server, getMcRL("taiga/houses"), BWGStructureProcessorLists.MOSSIFY_10_PERCENT_WHITE_PUFFBALL, BiomesWeveGone.id("minecraft/village/taiga/houses/taiga_forager_1"), StructureTemplatePool.Projection.RIGID, 2);
    }

    private static ResourceLocation getMcRL(String poolName) {
        return ResourceLocation.withDefaultNamespace("village/" + poolName);
    }
}
