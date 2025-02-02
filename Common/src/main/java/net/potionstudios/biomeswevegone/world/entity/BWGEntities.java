package net.potionstudios.biomeswevegone.world.entity;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.levelgen.Heightmap;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.PlatformHandler;
import net.potionstudios.biomeswevegone.world.entity.boats.BWGBoatEntity;
import net.potionstudios.biomeswevegone.world.entity.boats.BWGChestBoatEntity;
import net.potionstudios.biomeswevegone.world.entity.manowar.ManOWar;
import net.potionstudios.biomeswevegone.world.entity.oddion.Oddion;
import net.potionstudios.biomeswevegone.world.entity.pumpkinwarden.PumpkinWarden;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * This class is used to register entities.
 * @see EntityType
 * @author Joseph T. McQuigg
 */
public class BWGEntities {

	public static final Supplier<EntityType<ManOWar>> MAN_O_WAR = createEntity("man_o_war", ManOWar::new, MobCategory.WATER_CREATURE,0.6F, 1F);
	public static final Supplier<EntityType<PumpkinWarden>> PUMPKIN_WARDEN = createEntity("pumpkin_warden", PumpkinWarden::new, MobCategory.MISC,0.64F, 1.1F);
	public static final Supplier<EntityType<Oddion>> ODDION = createEntity("oddion", Oddion::new, MobCategory.CREATURE,0.5F, 0.75F);

	public static final Supplier<EntityType<BWGBoatEntity>> BWG_BOAT = createEntity("boat", BWGBoatEntity::new, MobCategory.MISC, EntityType.BOAT.getWidth(), EntityType.BOAT.getHeight(), 10);
	public static final Supplier<EntityType<BWGChestBoatEntity>> BWG_CHEST_BOAT = createEntity("chest_boat", BWGChestBoatEntity::new, MobCategory.MISC, EntityType.CHEST_BOAT.getWidth(), EntityType.CHEST_BOAT.getHeight(), 10);

	private static <E extends Entity> Supplier<EntityType<E>> createEntity(String id, EntityType.EntityFactory<E> factory, MobCategory category, float width, float height) {
		return PlatformHandler.PLATFORM_HANDLER.registerEntity(id, factory, category, width, height);
	}

	private static <E extends Entity> Supplier<EntityType<E>> createEntity(String id, EntityType.EntityFactory<E> factory, MobCategory category, float width, float height, int trackingRange) {
		return PlatformHandler.PLATFORM_HANDLER.registerEntity(id, factory, category, width, height, trackingRange);
	}

	@SuppressWarnings("unchecked")
	public static <T extends Mob> void registerSpawnPlacements(Consumer<SpawnPlacement<T>> consumer) {
		consumer.accept((SpawnPlacement<T>)new SpawnPlacement<>(MAN_O_WAR.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ManOWar::checkManOWarSpawnRules));
		consumer.accept((SpawnPlacement<T>)new SpawnPlacement<>(ODDION.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, Oddion::checkOddionSpawnRules));
	}

	public record SpawnPlacement<T extends Mob>(EntityType<T> entityType, SpawnPlacements.Type spawnPlacementType, Heightmap.Types heightmapType, SpawnPlacements.SpawnPredicate<T> predicate) {}

	/**
	 * Registers Entity Attributes
	 */
	public static void registerEntityAttributes(BiConsumer<EntityType<? extends LivingEntity>, AttributeSupplier> consumer) {
		consumer.accept(MAN_O_WAR.get(), ManOWar.createAttributes().build());
		consumer.accept(PUMPKIN_WARDEN.get(), PumpkinWarden.createAttributes().build());
		consumer.accept(ODDION.get(), Oddion.createAttributes().build());
	}

	public static void entities() {
		BiomesWeveGone.LOGGER.info("Registering Oh The Biomes We've Gone Entities");
	}
}
