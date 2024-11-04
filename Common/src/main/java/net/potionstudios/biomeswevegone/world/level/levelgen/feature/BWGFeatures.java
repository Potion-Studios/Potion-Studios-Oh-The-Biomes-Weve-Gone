package net.potionstudios.biomeswevegone.world.level.levelgen.feature;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.PlatformHandler;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.config.NoiseSphereConfig;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.config.RoundedRockConfig;

import java.util.function.Supplier;

public class BWGFeatures {

    public static final Supplier<SharpenedRockFeature> SHARPENED_ROCK = create("sharpened_rock", () -> new SharpenedRockFeature(NoneFeatureConfiguration.CODEC));
    public static final Supplier<RoundedRock> ROUNDED_ROCK = create("rounded_rock", () -> new RoundedRock(RoundedRockConfig.CODEC));
    public static final Supplier<PillarFeature> PILLAR = create("pillar", () -> new PillarFeature(PillarFeature.Config.CODEC));
    public static final Supplier<VineProcessorFeature> VINE_PROCESSOR = create("vine_processor", () -> new VineProcessorFeature(NoneFeatureConfiguration.CODEC));
    public static final Supplier<LushBlocksProcessorFeature> LUSH_BLOCKS_PROCESSOR = create("lush_blocks_processor", () -> new LushBlocksProcessorFeature(NoneFeatureConfiguration.CODEC));
    public static final Supplier<CragLakeFeature> CRAG_LAKE = create("crag_lake", () -> new CragLakeFeature(NoneFeatureConfiguration.CODEC));
    public static final Supplier<NoiseSphere> NOISE_SPHERE = create("noise_sphere", () -> new NoiseSphere(NoiseSphereConfig.CODEC));
    public static final Supplier<LargePumpkinFeature> LARGE_PUMPKIN = create("large_pumpkin", () -> new LargePumpkinFeature(NoneFeatureConfiguration.CODEC));
    public static final Supplier<MediumPumpkinFeature> MEDIUM_PUMPKIN = create("medium_pumpkin", () -> new MediumPumpkinFeature(NoneFeatureConfiguration.CODEC));
    public static final Supplier<ConfigurableFreezeTopLayer> CONFIGURABLE_FREEZE_TOP_LAYER = create("configurable_freeze_top_layer", () -> new ConfigurableFreezeTopLayer(ConfigurableFreezeTopLayer.Config.CODEC));


    public static <C extends FeatureConfiguration, F extends Feature<C>> Supplier<F> create(String id, Supplier<F> supplier) {
        return PlatformHandler.PLATFORM_HANDLER.register(BuiltInRegistries.FEATURE, id, supplier);
    }

    public static void features() {
        BiomesWeveGone.LOGGER.info("Registering Oh The Biomes We've Gone Custom Features");
    }
}
