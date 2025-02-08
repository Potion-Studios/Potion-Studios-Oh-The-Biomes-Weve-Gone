package net.potionstudios.biomeswevegone.config.configs;

import net.potionstudios.biomeswevegone.config.ConfigLoader;

/**
 * Config to Enable/Disable the spawning of mobs added by Oh The Biomes We've Gone.
 * @see ConfigLoader
 * @author Joseph T. McQuigg
 */
public class BWGMobSpawnConfig {

    public static BWGSpawnConfig INSTANCE = ConfigLoader.loadConfig(BWGMobSpawnConfig.class, "mob_spawn").spawn;

    public BWGSpawnConfig spawn = new BWGSpawnConfig();

    public static class BWGSpawnConfig {
        public boolean man_o_war = true;
        public boolean oddion = true;
    }
}
