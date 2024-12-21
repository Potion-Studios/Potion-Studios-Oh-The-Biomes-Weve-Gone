package net.potionstudios.biomeswevegone.config.configs;

import net.potionstudios.biomeswevegone.config.ConfigLoader;
import net.potionstudios.biomeswevegone.config.ConfigUtils;

public class BWGTradesConfig {

	public static final BWGTradesConfig INSTANCE = ConfigLoader.loadConfig(BWGTradesConfig.class, "trades");

	public BWGVillagerTradesConfig villagerTrades = new BWGVillagerTradesConfig();

	public static class BWGVillagerTradesConfig {
		public ConfigUtils.CommentValue<Boolean> allowBWGForagerTrades = ConfigUtils.CommentValue.of("Allow BWG Forager Profession Trades", true);
		public ConfigUtils.CommentValue<Boolean> enableBWGVanillaProfessionTradeAdditions = ConfigUtils.CommentValue.of("Allows BWG Items to be added to Vanilla Profession Trades", true);
	}

	public BWGWanderingTraderTradesConfig wanderingTraderTrades = new BWGWanderingTraderTradesConfig();

	public static class BWGWanderingTraderTradesConfig {
		public ConfigUtils.CommentValue<Boolean> enableBWGItemsTrades = ConfigUtils.CommentValue.of("Allows BWG Items to be added to Wandering Trader Offerings", true);
	}
}
