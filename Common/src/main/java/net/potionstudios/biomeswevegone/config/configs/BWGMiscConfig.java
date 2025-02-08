package net.potionstudios.biomeswevegone.config.configs;

import net.potionstudios.biomeswevegone.config.ConfigLoader;
import net.potionstudios.biomeswevegone.config.ConfigUtils;

public class BWGMiscConfig {

    public static final BWGMiscConfig INSTANCE = ConfigLoader.loadConfig(BWGMiscConfig.class, "misc");

    public MISC misc = new MISC();

    public static class MISC {

    }

    public SOUL_FRUIT soulFruit = new SOUL_FRUIT();

    public static class SOUL_FRUIT {
        public ConfigUtils.CommentValue<Boolean> ALLOW_SOUL_FRUIT_BLINDNESS = ConfigUtils.CommentValue.of("Allow Soul Fruit to give the player blindness", true);
        public ConfigUtils.CommentValue<Integer> SOUL_FRUIT_BLINDNESS = ConfigUtils.CommentValue.of("The duration of the blindness effect from Soul Fruit in Seconds, Value must be > 0", 60);
        public ConfigUtils.CommentValue<Integer> SOUL_FRUIT_BLINDNESS_RANGE = ConfigUtils.CommentValue.of("The range of the blindness effect from Soul Fruit in Blocks, Value must be > 0", 25);
    }

}
