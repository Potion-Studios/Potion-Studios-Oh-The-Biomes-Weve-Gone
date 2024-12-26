package net.potionstudios.biomeswevegone.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.forge.loot.LootModifiersRegister;
import net.potionstudios.biomeswevegone.forge.client.BiomesWeveGoneClientForge;
import net.potionstudios.biomeswevegone.world.entity.BWGEntities;
import net.potionstudios.biomeswevegone.world.entity.npc.BWGVillagerTrades;
import net.potionstudios.biomeswevegone.world.level.levelgen.biome.BWGOverworldSurfaceRules;
import net.potionstudios.biomeswevegone.world.level.levelgen.biome.BWGTerraBlenderRegion;
import terrablender.api.SurfaceRuleManager;

/**
 * Main class for the mod on the Forge platform.
 * @see Mod
 * @see BiomesWeveGone
 * @author Joseph T. McQuigg
 */
@Mod(BiomesWeveGone.MOD_ID)
public class BiomesWeveGoneForge {
    public BiomesWeveGoneForge(final FMLJavaModLoadingContext context) {
        IEventBus MOD_BUS = context.getModEventBus();
        IEventBus EVENT_BUS = MinecraftForge.EVENT_BUS;
        BiomesWeveGone.init();
        ForgePlatformHandler.register(MOD_BUS);
        MOD_BUS.addListener(this::onInitialize);
        MOD_BUS.addListener(this::onPostInitialize);
        EVENT_BUS.addListener(this::onServerStarting);
        MOD_BUS.addListener((EntityAttributeCreationEvent event) -> BWGEntities.registerEntityAttributes(event::put));
        MOD_BUS.addListener((SpawnPlacementRegisterEvent event) -> BWGEntities.registerSpawnPlacements((consumer) -> event.register(consumer.entityType(), consumer.spawnPlacementType(), consumer.heightmapType(), consumer.predicate(), SpawnPlacementRegisterEvent.Operation.OR)));
        VanillaCompatForge.registerVanillaCompatEvents(EVENT_BUS);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> BiomesWeveGoneClientForge.init(MOD_BUS));
        LootModifiersRegister.register(MOD_BUS);
    }

    /**
     * Should initialize everything where a specific event does not cover it.
     * @see FMLCommonSetupEvent
     */
    private void onInitialize(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            BiomesWeveGone.commonSetup();
            VanillaCompatForge.init();
            BWGTerraBlenderRegion.registerTerrablenderRegions();
            ForgePlatformHandler.registerPottedPlants();
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, BiomesWeveGone.MOD_ID, BWGOverworldSurfaceRules.makeRules());
        });
    }

    /**
     * Initializes things that should be done after the mod is fully loaded.
     * @see FMLLoadCompleteEvent
     */
    private void onPostInitialize(final FMLLoadCompleteEvent event) {
        event.enqueueWork(BiomesWeveGone::postInit);
        BWGVillagerTrades.makeTrades();
        BWGVillagerTrades.makeWanderingTrades();
    }

    /**
     * Initializes server-side things.
     * @see ServerAboutToStartEvent
     */
    private void onServerStarting(final ServerAboutToStartEvent event) {
        BiomesWeveGone.serverStart(event.getServer());
    }
}
