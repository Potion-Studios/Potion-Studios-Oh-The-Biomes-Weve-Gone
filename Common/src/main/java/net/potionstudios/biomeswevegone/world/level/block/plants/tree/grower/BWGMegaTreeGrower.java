package net.potionstudios.biomeswevegone.world.level.block.plants.tree.grower;


import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BWGMegaTreeGrower extends AbstractMegaTreeGrower {

    private final SimpleWeightedRandomList<ResourceKey<ConfiguredFeature<?, ?>>> keys;
    private final SimpleWeightedRandomList<ResourceKey<ConfiguredFeature<?, ?>>> megaKeys;

    public BWGMegaTreeGrower(SimpleWeightedRandomList<ResourceKey<ConfiguredFeature<?, ?>>> keys, SimpleWeightedRandomList<ResourceKey<ConfiguredFeature<?, ?>>> megaKeys) {
        this.keys = keys;
        this.megaKeys = megaKeys;
    }

    public BWGMegaTreeGrower(SimpleWeightedRandomList<ResourceKey<ConfiguredFeature<?, ?>>> megaKeys) {
        this(SimpleWeightedRandomList.empty(), megaKeys);
    }

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(@NotNull RandomSource random) {
        return this.megaKeys.getRandomValue(random).orElse(null);
    }

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(@NotNull RandomSource random, boolean hasFlowers) {
        return this.keys.getRandomValue(random).orElse(null);
    }
}