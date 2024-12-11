package net.potionstudios.biomeswevegone.config.configs;

import com.google.common.base.Suppliers;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import corgitaco.corgilib.serialization.codec.CommentedCodec;
import corgitaco.corgilib.serialization.jankson.JanksonJsonOps;
import corgitaco.corgilib.shadow.blue.endless.jankson.Jankson;
import corgitaco.corgilib.shadow.blue.endless.jankson.JsonElement;
import corgitaco.corgilib.shadow.blue.endless.jankson.JsonGrammar;
import corgitaco.corgilib.shadow.blue.endless.jankson.JsonObject;
import corgitaco.corgilib.shadow.blue.endless.jankson.api.SyntaxError;
import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.potionstudios.biomeswevegone.PlatformHandler;
import net.potionstudios.biomeswevegone.world.level.levelgen.biome.BWGBiomes;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.placed.BWGOverworldTreePlacedFeatures;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.placed.BWGVanillaPlacedFeatures;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.function.Supplier;

public record BWGWorldGenConfig(Map<ResourceKey<Biome>, Boolean> enabledBiomes, int regionWeight,
                                boolean vanillaAdditions, Map<ResourceKey<PlacedFeature>, Boolean> vanillaFeatures) {

    public static final Path PATH = PlatformHandler.PLATFORM_HANDLER.configPath().resolve("world_generation.json5");

    @NotNull
    public static Supplier<BWGWorldGenConfig> INSTANCE = Suppliers.memoize(BWGWorldGenConfig::getOrCreateConfigFromDisk);

    public static final Codec<BWGWorldGenConfig> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    CommentedCodec.of(Codec.unboundedMap(ResourceKey.codec(Registries.BIOME), Codec.BOOL), "enabled_biomes", "Which biomes are enabled, if disabled the biome will default to its vanilla counterpart for the given region").orElse(getDefaultBiomes()).forGetter(BWGWorldGenConfig::enabledBiomes),
                    CommentedCodec.of(Codec.INT, "region_weight", "How much each BWG region weighs. This weight applies to all 3 BWG Regions").orElse(8).forGetter(BWGWorldGenConfig::regionWeight),
                    CommentedCodec.of(Codec.BOOL, "vanilla_additions", "Whether to add bwg flowers and features to Vanilla Biomes (Config Option for Fabric Only)").orElse(true).forGetter(config -> true),
                    CommentedCodec.of(Codec.unboundedMap(ResourceKey.codec(Registries.PLACED_FEATURE), Codec.BOOL), "enabled_vanilla_additions", "BWG Features that we add to Vanilla Biomes").orElse(getVanillaPlacedFeatureAdditions()).forGetter(BWGWorldGenConfig::vanillaFeatures)
            ).apply(instance, BWGWorldGenConfig::new)
    );

    public static BWGWorldGenConfig createDefault() {
        return new BWGWorldGenConfig(getDefaultBiomes(), 8, true, getVanillaPlacedFeatureAdditions());
    }

    private static @NotNull Object2BooleanMap<ResourceKey<Biome>> getDefaultBiomes() {
        Object2BooleanMap<ResourceKey<Biome>> enabledBiomes = new Object2BooleanOpenHashMap<>();
        for (ResourceKey<Biome> biomeResourceKey : BWGBiomes.BIOME_FACTORIES.keySet()) {
            enabledBiomes.put(biomeResourceKey, true);
        }

        enabledBiomes.put(BWGBiomes.ERODED_BOREALIS, false);
        return enabledBiomes;
    }

    private static @NotNull Object2BooleanMap<ResourceKey<PlacedFeature>> getVanillaPlacedFeatureAdditions() {
        Object2BooleanMap<ResourceKey<PlacedFeature>> enabledFeatures = new Object2BooleanOpenHashMap<>();
        enabledFeatures.put(BWGVanillaPlacedFeatures.FLOWER_DEFAULT, true);
        enabledFeatures.put(BWGVanillaPlacedFeatures.FLOWER_PLAINS, true);
        enabledFeatures.put(BWGVanillaPlacedFeatures.FOREST_FLOWERS, true);
        enabledFeatures.put(BWGVanillaPlacedFeatures.FLOWER_WARM, true);
        enabledFeatures.put(BWGOverworldTreePlacedFeatures.PALM_TREES, true);

        return enabledFeatures;
    }

    public static BWGWorldGenConfig getOrCreateConfigFromDisk() {
        BWGWorldGenConfig defaultWorldGenConfig = createDefault();

        if (!PATH.toFile().exists()) {
            createDefaultFile(defaultWorldGenConfig);
            return defaultWorldGenConfig;
        } else {

            Jankson build = new Jankson.Builder().build();
            try {
                String configFile = Files.readString(PATH).stripTrailing().trim().strip().stripLeading();
                JsonObject load = build.load(configFile);
                Pair<BWGWorldGenConfig, JsonElement> configResult = CODEC.decode(JanksonJsonOps.INSTANCE, load).result().orElseThrow();
                BWGWorldGenConfig config = configResult.getFirst();


                Map<ResourceKey<Biome>, Boolean> temporary = new Reference2ObjectOpenHashMap<>();

                temporary.putAll(defaultWorldGenConfig.enabledBiomes);
                temporary.putAll(config.enabledBiomes);

                BWGWorldGenConfig toCreate = new BWGWorldGenConfig(temporary, config.regionWeight, config.vanillaAdditions, config.vanillaFeatures);

                createDefaultFile(toCreate);

                return toCreate;

            } catch (IOException | SyntaxError e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void createDefaultFile(BWGWorldGenConfig defaultWorldGenConfig) {
        JsonElement jsonElement = CODEC.encodeStart(JanksonJsonOps.INSTANCE, defaultWorldGenConfig).result().orElseThrow();
        String json = jsonElement.toJson(JsonGrammar.JSON5);
        try {
            Files.createDirectories(PATH.getParent());
            Files.writeString(PATH, json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
