package net.potionstudios.biomeswevegone.config.configs;

import net.potionstudios.biomeswevegone.config.ConfigLoader;

public class BWGTradesConfig {

	public static final BWGTradesConfig INSTANCE = ConfigLoader.loadConfig(BWGTradesConfig.class, "trades");

	public BWGVillagerTradesConfig villagerTrades = new BWGVillagerTradesConfig();

	public static class BWGVillagerTradesConfig {
		public boolean allowBWGForagerTrades = true;
		public boolean enableBWGVanillaProfessionTradeAdditions = true;
	}

	public BWGWanderingTraderTradesConfig wanderingTraderTrades = new BWGWanderingTraderTradesConfig();

	public static class BWGWanderingTraderTradesConfig {
		public boolean enableBWGItemsTrades = true;
	}
}
