package net.potionstudios.biomeswevegone.config.configs;

import net.potionstudios.biomeswevegone.config.ConfigLoader;

public class BWGMobSpawnConfig {

    public static final BWGSpawnConfig INSTANCE = ConfigLoader.loadConfig(BWGMobSpawnConfig.class, "mob_spawn").spawn;

    public BWGSpawnConfig spawn = new BWGSpawnConfig();

    public static class BWGSpawnConfig {
        public boolean man_o_war = true;
        public boolean oddion = true;
    }
}
