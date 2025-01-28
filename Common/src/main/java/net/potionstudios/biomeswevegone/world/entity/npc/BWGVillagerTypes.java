package net.potionstudios.biomeswevegone.world.entity.npc;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.biome.Biome;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.PlatformHandler;
import net.potionstudios.biomeswevegone.world.level.levelgen.biome.BWGBiomes;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class BWGVillagerTypes {

	public static final Supplier<VillagerType> SKYRIS = register("skyris");
	public static final Supplier<VillagerType> SALEM = register("salem");

	public static void setVillagerBiomes(BiConsumer<ResourceKey<Biome>, VillagerType> consumer) {
		consumer.accept(BWGBiomes.MOJAVE_DESERT, VillagerType.DESERT);
		consumer.accept(BWGBiomes.WINDSWEPT_DESERT, VillagerType.DESERT);
		consumer.accept(BWGBiomes.TROPICAL_RAINFOREST, VillagerType.JUNGLE);
		consumer.accept(BWGBiomes.SKYRIS_VALE, SKYRIS.get());
		consumer.accept(BWGBiomes.WEEPING_WITCH_FOREST, SALEM.get());
	}

	private static Supplier<VillagerType> register(String key) {
		return PlatformHandler.PLATFORM_HANDLER.register(BuiltInRegistries.VILLAGER_TYPE, key, () -> new VillagerType(key));
	}

	public static void villagerTypes() {
		BiomesWeveGone.LOGGER.info("Registering Oh The Biomes We've Gone villager types");
	}
}
