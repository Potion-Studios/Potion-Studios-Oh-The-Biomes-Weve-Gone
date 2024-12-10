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
import net.potionstudios.biomeswevegone.PlatformHandler;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Supplier;

public record BWGTradesConfig(boolean enableTrades, boolean enableVanillaTradeAdditions) {

    private static final Path PATH = PlatformHandler.PLATFORM_HANDLER.configPath().resolve("trades.json5");

    @NotNull
    public static Supplier<BWGTradesConfig> INSTANCE = Suppliers.memoize(BWGTradesConfig::getOrCreateConfigFromDisk);

    private static final Codec<BWGTradesConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            CommentedCodec.of(Codec.BOOL, "enable_trades", "Whether to enable BWG Villager Trades").orElse(true).forGetter(config -> true),
            CommentedCodec.of(Codec.BOOL, "enable_vanilla_trade_additions", "Whether to add BWG Villager Trades to Vanilla Villagers").orElse(true).forGetter(config -> true)
    ).apply(instance, BWGTradesConfig::new));

    private static BWGTradesConfig createDefault() {
        return new BWGTradesConfig(true, true);
    }

    private static BWGTradesConfig getOrCreateConfigFromDisk() {
        BWGTradesConfig defaultConfig = createDefault();

        if (!PATH.toFile().exists()) {
            createDefaultFile(defaultConfig);
            return defaultConfig;
        }

        Jankson build = new Jankson.Builder().build();
        try {
            String configFile = Files.readString(PATH).stripTrailing().trim().strip().stripLeading();
            JsonObject load = build.load(configFile);
            Pair<BWGTradesConfig, JsonElement> configResult = CODEC.decode(JanksonJsonOps.INSTANCE, load).result().orElseThrow();
            BWGTradesConfig config = configResult.getFirst();

            BWGTradesConfig toCreate = new BWGTradesConfig(config.enableTrades, config.enableVanillaTradeAdditions);

            createDefaultFile(toCreate);
            return toCreate;

        } catch (IOException | SyntaxError e) {
            throw new RuntimeException(e);
        }
    }

    private static void createDefaultFile(BWGTradesConfig tradesConfig) {
        JsonElement jsonElement = CODEC.encodeStart(JanksonJsonOps.INSTANCE, tradesConfig).result().orElseThrow();
        String json = jsonElement.toJson(JsonGrammar.JSON5);
        try {
            Files.createDirectories(PATH.getParent());
            Files.writeString(PATH, json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
