package net.potionstudios.biomeswevegone.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.neoforge.loot.LootModifiersRegister;
import net.potionstudios.biomeswevegone.world.entity.BWGEntities;
import net.potionstudios.biomeswevegone.world.entity.npc.BWGVillagerTrades;
import net.potionstudios.biomeswevegone.world.level.levelgen.biome.BWGOverworldSurfaceRules;
import net.potionstudios.biomeswevegone.world.level.levelgen.biome.BWGTerraBlenderRegion;
import terrablender.api.SurfaceRuleManager;

@Mod(BiomesWeveGone.MOD_ID)
public class BiomesWeveGoneNeoForge {

	public BiomesWeveGoneNeoForge(final IEventBus eventBus) {
		IEventBus EVENT_BUS = NeoForge.EVENT_BUS;
		BiomesWeveGone.init();
		NeoForgePlatformHandler.register(eventBus);
		eventBus.addListener(this::onInitialize);
		eventBus.addListener(this::onPostInitialize);
		EVENT_BUS.addListener((ServerAboutToStartEvent event) -> BiomesWeveGone.serverStart(event.getServer()));
		eventBus.addListener((EntityAttributeCreationEvent event) -> BWGEntities.registerEntityAttributes(event::put));
		eventBus.addListener((RegisterSpawnPlacementsEvent event) -> BWGEntities.registerSpawnPlacements((consumer) -> event.register(consumer.entityType(), consumer.spawnPlacementType(), consumer.heightmapType(), consumer.predicate(), RegisterSpawnPlacementsEvent.Operation.OR)));
		VanillaCompatNeoForge.registerVanillaCompatEvents(EVENT_BUS);
		LootModifiersRegister.register(eventBus);
	}

	/**
	 * Should initialize everything where a specific event does not cover it.
	 * @see FMLCommonSetupEvent
	 */
	private void onInitialize(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			BiomesWeveGone.commonSetup();
			VanillaCompatNeoForge.init();
			BWGTerraBlenderRegion.registerTerrablenderRegions();
			NeoForgePlatformHandler.registerPottedPlants();
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
}
