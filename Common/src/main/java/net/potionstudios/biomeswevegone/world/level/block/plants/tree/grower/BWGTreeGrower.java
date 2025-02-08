package net.potionstudios.biomeswevegone.world.level.block.plants.tree.grower;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class BWGTreeGrower extends TreeGrower {

    private final SimpleWeightedRandomList<ResourceKey<ConfiguredFeature<?, ?>>> keys;

    public BWGTreeGrower(String name, SimpleWeightedRandomList<ResourceKey<ConfiguredFeature<?, ?>>> keys) {
        super(name, 0, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        this.keys = keys;
    }

    @Nullable
    @Override
    public ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(@NotNull RandomSource random, boolean hasFlowers) {
        return this.keys.getRandomValue(random).orElse(null);
    }
}