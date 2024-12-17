package net.potionstudios.biomeswevegone.world.entity.npc;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.npc.VillagerType;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.PlatformHandler;
import net.potionstudios.biomeswevegone.world.level.levelgen.biome.BWGBiomes;

import java.util.function.Supplier;

public class BWGVillagerType {

	public static final Supplier<VillagerType> SKYRIS = register("skyris");

	public static void setVillagerBWGBiomes() {
		VillagerType.BY_BIOME.put(BWGBiomes.MOJAVE_DESERT, VillagerType.DESERT);
		VillagerType.BY_BIOME.put(BWGBiomes.WINDSWEPT_DESERT, VillagerType.DESERT);
		VillagerType.BY_BIOME.put(BWGBiomes.TROPICAL_RAINFOREST, VillagerType.JUNGLE);
		VillagerType.BY_BIOME.put(BWGBiomes.WEEPING_WITCH_FOREST, VillagerType.TAIGA);
		VillagerType.BY_BIOME.put(BWGBiomes.SKYRIS_VALE, SKYRIS.get());
	}

	private static Supplier<VillagerType> register(String key) {
		return PlatformHandler.PLATFORM_HANDLER.register(BuiltInRegistries.VILLAGER_TYPE, key, () -> new VillagerType(key));
	}

	public static void villagerTypes() {
		BiomesWeveGone.LOGGER.info("Registering Oh The Biomes We've Gone villager types");
	}
}
