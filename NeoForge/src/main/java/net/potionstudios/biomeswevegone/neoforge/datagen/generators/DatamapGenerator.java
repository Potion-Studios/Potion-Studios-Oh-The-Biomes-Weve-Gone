package net.potionstudios.biomeswevegone.neoforge.datagen.generators;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.block.BlockFeatures;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class DatamapGenerator extends DataMapProvider {
    /**
     * Create a new provider.
     *
     * @param packOutput     the output location
     * @param lookupProvider a {@linkplain CompletableFuture} supplying the registries
     */
    public DatamapGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.@NotNull Provider arg) {
        builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(id(BWGBlocks.PEAT.get().asItem()), new FurnaceFuel(1200), false)
                .conditions(new ModLoadedCondition(BiomesWeveGone.MOD_ID));

        var builder = builder(NeoForgeDataMaps.COMPOSTABLES);
        BlockFeatures.registerCompostables((item, chance) -> builder.add(id(item.asItem()), new Compostable(chance, true), false));
        builder.conditions(new ModLoadedCondition(BiomesWeveGone.MOD_ID));
    }

    private ResourceLocation id(Item item) {
        return BuiltInRegistries.ITEM.getKey(item);
    }
}
