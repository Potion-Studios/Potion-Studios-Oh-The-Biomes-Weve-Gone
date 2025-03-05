package net.potionstudios.biomeswevegone.neoforge.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.neoforge.datagen.generators.*;
import net.potionstudios.biomeswevegone.neoforge.datagen.generators.loot.GlobalLootModifiersGenerator;
import net.potionstudios.biomeswevegone.neoforge.datagen.generators.loot.LootGenerator;
import net.potionstudios.biomeswevegone.world.damagesource.BWGDamageTypes;
import net.potionstudios.biomeswevegone.world.item.jukebox.BWGJukeBoxSongs;
import net.potionstudios.biomeswevegone.world.level.levelgen.biome.BWGBiomes;
import net.potionstudios.biomeswevegone.world.level.levelgen.biome.modifiers.BWGBiomeModifiers;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.configured.ConfiguredFeaturesUtil;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.placed.PlacedFeaturesUtil;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.BWGStructureSets;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.BWGStructures;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.BWGTemplatePools;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.processor.BWGStructureProcessorLists;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * This class is used to register the data generators for the mod.
 * @see GatherDataEvent
 * @author Joseph T. McQuigg
 */
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = BiomesWeveGone.MOD_ID)
class DataGeneratorsRegister {

    @SubscribeEvent
    protected static void gatherData(final GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        DatapackBuiltinEntriesProvider datapackBuiltinEntriesProvider = new DatapackBuiltinEntriesProvider(output, lookupProvider, BUILDER, Set.of(BiomesWeveGone.MOD_ID));
        generator.addProvider(true, datapackBuiltinEntriesProvider);
        lookupProvider = datapackBuiltinEntriesProvider.getRegistryProvider();

        //ModelGenerators.init(generator, true, output);
        generator.addProvider(true, new BlockModelGenerator(output));
        generator.addProvider(true, new LangGenerator(output, "en_us"));
        generator.addProvider(true, new RecipeGenerator.RecipeGeneratorRunner(output, lookupProvider));
        generator.addProvider(true, new LootGenerator(output, lookupProvider));
        generator.addProvider(true, new GlobalLootModifiersGenerator(output, lookupProvider));
        BWGBiomeModifiers.init();
        TagsGenerator.init(generator, true, output, lookupProvider);
        generator.addProvider(true, new AdvancementGenerator(output, lookupProvider));
        generator.addProvider(true, new ParticleDescriptionGenerator(output));
        generator.addProvider(true, new SoundDefinitionsGenerator(output));
        generator.addProvider(true, new DatamapGenerator(output, lookupProvider));
    }

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, configuredFeatureHolderGetter -> ConfiguredFeaturesUtil.CONFIGURED_FEATURES_FACTORIES.forEach(((biomeResourceKey, biomeFactory) -> configuredFeatureHolderGetter.register(biomeResourceKey, biomeFactory.generate(configuredFeatureHolderGetter)))))
            .add(Registries.PLACED_FEATURE, pContext -> PlacedFeaturesUtil.PLACED_FEATURE_FACTORIES.forEach((resourceKey, factory) -> pContext.register(resourceKey, factory.generate(pContext.lookup(Registries.CONFIGURED_FEATURE)))))
            .add(Registries.BIOME, biomeBootstapContext ->  BWGBiomes.BIOME_FACTORIES.forEach(((biomeResourceKey, biomeFactory) -> biomeBootstapContext.register(biomeResourceKey, biomeFactory.generate(biomeBootstapContext.lookup(Registries.PLACED_FEATURE), biomeBootstapContext.lookup(Registries.CONFIGURED_CARVER))))))
            .add(Registries.TEMPLATE_POOL, context -> BWGTemplatePools.TEMPLATE_POOL_FACTORIES.forEach((templatePoolResourceKey, templatePoolFactory) -> context.register(templatePoolResourceKey, templatePoolFactory.generate(context))))
            .add(Registries.STRUCTURE, context -> BWGStructures.STRUCTURE_FACTORIES.forEach((structureResourceKey, structureFactory) -> context.register(structureResourceKey, structureFactory.generate(context))))
            .add(Registries.STRUCTURE_SET, context -> BWGStructureSets.STRUCTURE_SET_FACTORIES.forEach((structureSetResourceKey, structureSetFactory) -> context.register(structureSetResourceKey, structureSetFactory.generate(context.lookup(Registries.STRUCTURE)))))
            .add(Registries.PROCESSOR_LIST, pContext -> BWGStructureProcessorLists.STRUCTURE_PROCESSOR_LIST_FACTORIES.forEach((structureProcessorListResourceKey, processorListFactory) -> pContext.register(structureProcessorListResourceKey, processorListFactory.generate(pContext.lookup(Registries.PROCESSOR_LIST)))))
            .add(Registries.DAMAGE_TYPE, pContext -> BWGDamageTypes.DAMAGE_TYPE_FACTORIES.forEach(((damageTypeResourceKey, damageTypeFactory) -> pContext.register(damageTypeResourceKey, damageTypeFactory.generate(pContext)))))
            .add(Registries.JUKEBOX_SONG, pContext -> BWGJukeBoxSongs.JUKEBOX_SONG_FACTORIES.forEach((songResourceKey, songFactory) -> pContext.register(songResourceKey, songFactory.generate(pContext))))
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, pContext -> BWGBiomeModifiers.BIOME_MODIFIERS_FACTORIES.forEach((id, modifier) -> pContext.register(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, id), new BiomeModifiers.AddFeaturesBiomeModifier(
                    HolderSet.direct(Arrays.stream(modifier.biomes()).map(biome -> pContext.lookup(Registries.BIOME).getOrThrow(biome)).collect(Collectors.toList())),
                    HolderSet.direct(pContext.lookup(Registries.PLACED_FEATURE).getOrThrow(modifier.feature())),
                    modifier.step()
            ))));
}
