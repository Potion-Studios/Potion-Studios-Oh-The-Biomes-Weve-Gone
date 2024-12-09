package net.potionstudios.biomeswevegone;

import com.mojang.logging.LogUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.potionstudios.biomeswevegone.sounds.BWGSounds;
import net.potionstudios.biomeswevegone.compat.vanilla.dispenser.BWGDispenseItemBehavior;
import net.potionstudios.biomeswevegone.world.entity.BWGEntities;
import net.potionstudios.biomeswevegone.world.entity.ai.village.poi.BWGPoiTypes;
import net.potionstudios.biomeswevegone.world.entity.npc.BWGVillagerProfessions;
import net.potionstudios.biomeswevegone.world.entity.npc.BWGVillagerType;
import net.potionstudios.biomeswevegone.world.item.BWGCreativeTabs;
import net.potionstudios.biomeswevegone.world.item.BWGItems;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.block.entities.BWGBlockEntities;
import net.potionstudios.biomeswevegone.world.level.levelgen.blockpredicates.BWGBlockPredicateTypes;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.BWGFeatures;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.configured.BWGConfiguredFeatures;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.placed.BWGPlacedFeatures;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.stateproviders.BWGStateProviders;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.treedecorators.BWGTreeDecorators;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.BWGStructurePieceTypes;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.BWGStructureTypes;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.BWGTemplatePools;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.processor.BWGCustomStructureProcessors;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.village.PlaceInVillage;
import net.potionstudios.biomeswevegone.world.level.levelgen.surfacerules.BWGRuleSources;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

/**
 * The main class for Oh The Biomes We've Gone.
 * @author Joseph T. McQuigg
 */
public class BiomesWeveGone {

    /** The mod id for Oh The Biomes We've Gone. */
    public static final String MOD_ID = "biomeswevegone";

    /** The logger for Oh The Biomes We've Gone. */
    public static final Logger LOGGER = LogUtils.getLogger();

    /**
     * Initializes the mod.
     */
    public static void init() {
        BWGItems.items();
        BWGBlocks.blocks();
        BWGBlockEntities.blockEntities();
        BWGEntities.entities();
        BWGCreativeTabs.tabs();
        BWGSounds.sounds();
        BWGBlockPredicateTypes.blockPredicateTypes();
        BWGStateProviders.stateProviders();
        BWGTreeDecorators.treeDecorators();
        BWGFeatures.features();
        BWGStructurePieceTypes.structurePieceTypes();
        BWGStructureTypes.structureTypes();
        BWGConfiguredFeatures.configuredFeatures();
        BWGPlacedFeatures.placedFeatures();
        BWGRuleSources.ruleSources();
        BWGTemplatePools.templatePools();
        BWGPoiTypes.poiTypes();
        BWGVillagerProfessions.professions();
        BWGCustomStructureProcessors.processors();
        GeckoLib.initialize();
    }

    /**
     * Ran later in the initialization process to setup common things.
     */
    public static void commonSetup() {
        BWGEntities.registerSpawnPlacements();
        BWGVillagerType.setVillagerBWGBiomes();
    }

    /**
     * Ran after the mod is fully loaded to finish setup.
     */
    public static void postInit() {
        BWGDispenseItemBehavior.registerDispenseItemBehavior();
    }

    /**
     * Runs on Server Start.
     * @param server the server to run on
     */
    public static void serverStart(MinecraftServer server) {
        PlaceInVillage.addStructuresToVillages(server);
    }

    /**
     * Creates a new resource location for Oh The Biomes We've Gone.
     * @param name the name of the resource
     * @return the new resource location with the Biomes We've Gone location
     */
    public static ResourceLocation id(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    /**
     * Creates a new resource key for Oh The Biomes We've Gone.
     * @param registryKey the registry key for the resource
     * @param name the name of the resource
     * @return the new resource key with the Biomes We've Gone location
     */
    public static <T> ResourceKey<T> key(ResourceKey<? extends Registry<T>> registryKey, String name) {
        return ResourceKey.create(registryKey, id(name));
    }
}
