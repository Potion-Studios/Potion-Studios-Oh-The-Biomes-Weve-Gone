package net.potionstudios.biomeswevegone.config.configs;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import corgitaco.corgilib.serialization.codec.CommentedCodec;
import net.potionstudios.biomeswevegone.PlatformHandler;
import net.potionstudios.biomeswevegone.config.ConfigUtils;
import org.jetbrains.annotations.NotNull;

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
        return ConfigUtils.loadConfig(PATH, CODEC, createDefault());
    }
}
