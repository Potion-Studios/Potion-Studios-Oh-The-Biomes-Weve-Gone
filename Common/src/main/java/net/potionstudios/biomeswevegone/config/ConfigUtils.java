package net.potionstudios.biomeswevegone.config;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import corgitaco.corgilib.serialization.jankson.JanksonJsonOps;
import corgitaco.corgilib.shadow.blue.endless.jankson.Jankson;
import corgitaco.corgilib.shadow.blue.endless.jankson.JsonElement;
import corgitaco.corgilib.shadow.blue.endless.jankson.JsonGrammar;
import corgitaco.corgilib.shadow.blue.endless.jankson.JsonObject;
import corgitaco.corgilib.shadow.blue.endless.jankson.api.SyntaxError;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigUtils {
	public static <T> T loadConfig(Path path, Codec<T> codec, T defaultConfig) {
		if (!path.toFile().exists()) {
			createDefaultFile(path, codec, defaultConfig);
			return defaultConfig;
		}

		Jankson build = new Jankson.Builder().build();
		try {
			String configFile = Files.readString(path).strip();
			JsonObject load = build.load(configFile);
			Pair<T, JsonElement> configResult = codec.decode(JanksonJsonOps.INSTANCE, load).result().orElseThrow();
			T config = configResult.getFirst();
			createDefaultFile(path, codec, config);
			return config;
		} catch (IOException | SyntaxError e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> void createDefaultFile(Path path, Codec<T> codec, T config) {
		JsonElement jsonElement = codec.encodeStart(JanksonJsonOps.INSTANCE, config).result().orElseThrow();
		String json = jsonElement.toJson(JsonGrammar.JSON5);
		try {
			Files.createDirectories(path.getParent());
			Files.writeString(path, json);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
