package net.potionstudios.biomeswevegone.forge.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.forge.loot.AddItemModifier;
import net.potionstudios.biomeswevegone.world.item.BWGItems;
import net.potionstudios.biomeswevegone.world.level.levelgen.biome.modifiers.BWGBiomeModifiers;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = BiomesWeveGone.MOD_ID)
class ForgeDatagen {

    @SubscribeEvent
    protected static void gatherData(final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        DatapackBuiltinEntriesProvider datapackBuiltinEntriesProvider = new DatapackBuiltinEntriesProvider(output, lookupProvider, BUILDER, Set.of(BiomesWeveGone.MOD_ID));
        generator.addProvider(event.includeServer(), datapackBuiltinEntriesProvider);

        generator.addProvider(event.includeServer(), new GlobalLootModifiersGenerator(output, lookupProvider));
    }

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, pContext -> BWGBiomeModifiers.BIOME_MODIFIERS_FACTORIES.forEach((id, modifier) -> pContext.register(ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, id), new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                    HolderSet.direct(Arrays.stream(modifier.biomes()).map(biome -> pContext.lookup(Registries.BIOME).getOrThrow(biome)).collect(Collectors.toList())),
                    HolderSet.direct(pContext.lookup(Registries.PLACED_FEATURE).getOrThrow(modifier.feature())),
                    modifier.step()
            ))));

    private static class GlobalLootModifiersGenerator extends GlobalLootModifierProvider {

        private GlobalLootModifiersGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, BiomesWeveGone.MOD_ID, registries);
        }

        @Override
        protected void start(HolderLookup.@NotNull Provider arg) {
            add("bwg_items_from_sniffer_dig", new AddItemModifier(new LootItemCondition[]{
                    new LootTableIdCondition.Builder(BuiltInLootTables.SNIFFER_DIGGING.location()).build()}, BWGItems.FLUORESCENT_CATTAIL_SPROUT.get(),
                    BWGItems.BLUE_GLOWCANE_SHOOT.get(), BWGItems.GREEN_GLOWCANE_SHOOT.get(), BWGItems.RED_GLOWCANE_SHOOT.get(), BWGItems.YELLOW_GLOWCANE_SHOOT.get(),
                    BWGItems.PALE_PUMPKIN_SEEDS.get()));
        }
    }

}
