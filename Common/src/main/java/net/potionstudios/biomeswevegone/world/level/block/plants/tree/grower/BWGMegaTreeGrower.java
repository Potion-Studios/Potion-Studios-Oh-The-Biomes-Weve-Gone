package net.potionstudios.biomeswevegone.world.level.block.plants.tree.grower;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BWGMegaTreeGrower extends BWGTreeGrower {

    private final SimpleWeightedRandomList<ResourceKey<ConfiguredFeature<?, ?>>> megaKeys;

    public BWGMegaTreeGrower(String name, SimpleWeightedRandomList<ResourceKey<ConfiguredFeature<?, ?>>> keys, SimpleWeightedRandomList<ResourceKey<ConfiguredFeature<?, ?>>> megaKeys) {
        super(name, keys);
        this.megaKeys = megaKeys;
    }

    @Nullable
    @Override
    public ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(@NotNull RandomSource random) {
        return this.megaKeys.getRandomValue(random).orElse(null);
    }
}