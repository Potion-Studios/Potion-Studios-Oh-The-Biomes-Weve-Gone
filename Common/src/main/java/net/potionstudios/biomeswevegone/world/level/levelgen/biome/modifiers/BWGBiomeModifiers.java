package net.potionstudios.biomeswevegone.world.level.levelgen.biome.modifiers;

import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.config.configs.BWGWorldGenConfig;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.placed.BWGOverworldTreePlacedFeatures;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.placed.BWGVanillaPlacedFeatures;

import java.util.Map;

/**
 * Used to register BWG Biome Modifiers for Vanilla Biomes
 * @author Joseph T. McQuigg
 */
public class BWGBiomeModifiers {
	public static final Map<ResourceLocation, BWGBiomeModifier> BIOME_MODIFIERS_FACTORIES = new Reference2ObjectOpenHashMap<>();

	@SafeVarargs
	private static void registerModifierVegetalDecoration(String id, ResourceKey<PlacedFeature> feature, Map<ResourceKey<PlacedFeature>, Boolean> map, ResourceKey<Biome>... biomes) {
		BIOME_MODIFIERS_FACTORIES.put(BiomesWeveGone.id(id), new BWGBiomeModifier(feature, GenerationStep.Decoration.VEGETAL_DECORATION, map.get(feature), biomes));
	}

	/**
	 * Represents a BWG Biome Modifier
	 * @param feature The feature to add
	 * @param step The generation step to add the feature to
	 * @param biomes The biomes to add the feature to
	 */
	public record BWGBiomeModifier(ResourceKey<PlacedFeature> feature, GenerationStep.Decoration step, Boolean enabled, ResourceKey<Biome>... biomes) {
		@SafeVarargs
        public BWGBiomeModifier {}
	}

	public static void init() {
		BiomesWeveGone.LOGGER.info("Creating and Registering BWG Biome Modifiers for Vanilla Biomes");
		Map<ResourceKey<PlacedFeature>, Boolean> map = BWGWorldGenConfig.INSTANCE.get().vanillaFeatures();
		registerModifierVegetalDecoration("vanilla/flower_default", BWGVanillaPlacedFeatures.FLOWER_DEFAULT, map, Biomes.OLD_GROWTH_PINE_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA, Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_FOREST, Biomes.DESERT, Biomes.SNOWY_PLAINS, Biomes.ICE_SPIKES,
				Biomes.WINDSWEPT_SAVANNA, Biomes.FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST, Biomes.BIRCH_FOREST, Biomes.TAIGA, Biomes.SNOWY_TAIGA, Biomes.DARK_FOREST);
		registerModifierVegetalDecoration("vanilla/flower_warm", BWGVanillaPlacedFeatures.FLOWER_WARM, map, Biomes.SPARSE_JUNGLE, Biomes.JUNGLE, Biomes.BAMBOO_JUNGLE, Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU);
		registerModifierVegetalDecoration("vanilla/flower_plains", BWGVanillaPlacedFeatures.FLOWER_PLAINS, map, Biomes.PLAINS, Biomes.SUNFLOWER_PLAINS);
		registerModifierVegetalDecoration("vanilla/forest_flowers", BWGVanillaPlacedFeatures.FOREST_FLOWERS, map, Biomes.FOREST, Biomes.DARK_FOREST);
		registerModifierVegetalDecoration("vanilla/beach/palm_trees", BWGOverworldTreePlacedFeatures.PALM_TREES, map,Biomes.BEACH);
	}
}


