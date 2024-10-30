package net.potionstudios.biomeswevegone.world.level.levelgen.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.potionstudios.biomeswevegone.client.BWGSounds;
import net.potionstudios.biomeswevegone.client.particle.BWGParticles;
import net.potionstudios.biomeswevegone.world.entity.BWGEntities;
import net.potionstudios.biomeswevegone.world.level.levelgen.biome.features.BWGOverworldDefaultFeatures;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.placed.BWGOverworldTreePlacedFeatures;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.placed.BWGOverworldVegationPlacedFeatures;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.placed.BWGPlacedFeatures;


class BWGOverworldBiomes {

    private static void addDefaultOverworldGeneration(BiomeGenerationSettings.Builder generationSettings) {
        OverworldBiomes.globalOverworldGeneration(generationSettings);
        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(generationSettings);
    }

    private static BiomeGenerationSettings.Builder setupDefaultOverworldGeneration(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        addDefaultOverworldGeneration(generationSettings);
        return generationSettings;
    }

    private static BiomeGenerationSettings.Builder setupDefaultOverworldGenerationWithoutLava(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        generationSettings.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE);
        generationSettings.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE_EXTRA_UNDERGROUND);
        generationSettings.addCarver(GenerationStep.Carving.AIR, Carvers.CANYON);
        generationSettings.addFeature(GenerationStep.Decoration.LAKES, MiscOverworldPlacements.LAKE_LAVA_UNDERGROUND);
        BiomeDefaultFeatures.addDefaultCrystalFormations(generationSettings);
        BiomeDefaultFeatures.addDefaultMonsterRoom(generationSettings);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(generationSettings);
        BiomeDefaultFeatures.addDefaultSprings(generationSettings);
        BiomeDefaultFeatures.addSurfaceFreezing(generationSettings);
        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(generationSettings);
        return generationSettings;
    }

    private static void addSpawn(MobSpawnSettings.Builder builder, EntityType<?> entityType, int weight, int minGroupSize, int maxGroupSize) {
        builder.addSpawn(entityType.getCategory(), new MobSpawnSettings.SpawnerData(entityType, weight, minGroupSize, maxGroupSize));
    }

    protected static Biome alliumShrubland(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);

        BiomeDefaultFeatures.addSavannaExtraGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.ALLIUM_SHRUBLAND_FLOWERS);
        BWGOverworldDefaultFeatures.addCloverPatches(generationSettings);
        BWGOverworldDefaultFeatures.addOakBushes(generationSettings);
//        BYGDefaultBiomeFeatures.addBeeHive(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.GIANT_ALLIUMS);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.plainsSpawns(spawnSettings);
        addSpawn(spawnSettings, BWGEntities.ODDION.get(), 5, 2, 10);

        float temperature = 0.8F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.4F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome amaranthGrassland(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);

        BiomeDefaultFeatures.addSavannaExtraGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);

        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.AMARANTH_GRASSLAND_FLOWERS);
        BWGOverworldDefaultFeatures.addCloverPatches(generationSettings);
        BWGOverworldDefaultFeatures.addJacarandaBushes(generationSettings);
        BWGOverworldDefaultFeatures.addOakBushes(generationSettings);
        BWGOverworldDefaultFeatures.addSparseJacarandaTrees(generationSettings);
//        BYGDefaultBiomeFeatures.addBeeHive(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.plainsSpawns(spawnSettings);

        float temperature = 0.8F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.4F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome araucariaSavanna(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);
        BiomeDefaultFeatures.addSavannaTrees(generationSettings);
        BiomeDefaultFeatures.addDefaultGrass(generationSettings);
        BiomeDefaultFeatures.addWarmFlowers(generationSettings);
        BiomeDefaultFeatures.addSavannaExtraGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);
        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.SPARSE_ARAUCARIA_TREES);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.plainsSpawns(spawnSettings);
        addSpawn(spawnSettings, EntityType.LLAMA, 1, 3, 4);

        float temperature = 1.2F;
        return new Biome.BiomeBuilder().hasPrecipitation(false).temperature(temperature).downfall(0.0F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(10860373).foliageColorOverride(10860373).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome aspenBoreal(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGenerationWithoutLava(placedFeatureGetter, carverGetter);

        BiomeDefaultFeatures.addForestFlowers(generationSettings);
        BiomeDefaultFeatures.addDefaultGrass(generationSettings); //TODO: Remove One of the grasses
        BiomeDefaultFeatures.addForestGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);


        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.ASPEN_TREES);
        BWGOverworldDefaultFeatures.addSparseSpruceTrees(generationSettings);
        BWGOverworldDefaultFeatures.addLeafPile(generationSettings);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.ORANGE_DAISY);
//        BYGDefaultBiomeFeatures.addBeeHive(generationSettings);
        BWGOverworldDefaultFeatures.addRose(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        BiomeDefaultFeatures.commonSpawns(spawnSettings);
        addSpawn(spawnSettings, EntityType.WOLF, 8, 4, 4);
        addSpawn(spawnSettings, EntityType.RABBIT, 4, 2, 3);
        addSpawn(spawnSettings, EntityType.FOX, 8, 2, 4);

        float temperature = 0.5F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.6F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(11909994).foliageColorOverride(14194987).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome pumpkinValley(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);
        BiomeDefaultFeatures.addDefaultGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);

        BWGOverworldDefaultFeatures.addMeadowShrubs(generationSettings);
        BWGOverworldDefaultFeatures.addCloverPatches(generationSettings);
        BWGOverworldDefaultFeatures.addOakBushes(generationSettings);

        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.LARGE_PUMPKIN);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.MEDIUM_PUMPKIN);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.SMALL_PUMPKIN);
        BWGOverworldDefaultFeatures.addAnemones(generationSettings);
        BWGOverworldDefaultFeatures.addCrocus(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        BiomeDefaultFeatures.commonSpawns(spawnSettings);

        float temperature = 0.35F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.8F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(11513689).foliageColorOverride(12435265).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome baobabSavanna(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);
        BiomeDefaultFeatures.addSavannaGrass(generationSettings);
        BiomeDefaultFeatures.addSavannaTrees(generationSettings);
        BiomeDefaultFeatures.addWarmFlowers(generationSettings);
        BiomeDefaultFeatures.addSavannaExtraGrass(generationSettings);

        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.BAOBAB_TREES);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        BiomeDefaultFeatures.commonSpawns(spawnSettings);
        addSpawn(spawnSettings, EntityType.HORSE, 1, 2, 6);
        addSpawn(spawnSettings, EntityType.DONKEY, 1, 1, 1);
        addSpawn(spawnSettings, EntityType.LLAMA, 1, 3, 4);

        float temperature = 1.2F;
        return new Biome.BiomeBuilder().hasPrecipitation(false).temperature(temperature).downfall(0.0F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(10860373).foliageColorOverride(10860373).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome basaltBarrera(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);

        BiomeDefaultFeatures.addDefaultGrass(generationSettings);

        addRawGeneration(generationSettings, BWGPlacedFeatures.LARGE_BASALT_COLUMN);
        addRawGeneration(generationSettings, BWGPlacedFeatures.SMALL_BASALT_COLUMN);
        addRawGeneration(generationSettings, BWGPlacedFeatures.BASALT_DELTA);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnSettings);

        float temperature = 0.85F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.75F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome bayou(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        OverworldBiomes.globalOverworldGeneration(generationSettings);
        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BWGOverworldDefaultFeatures.addSwampDelta(generationSettings);

        BiomeDefaultFeatures.addSwampClayDisk(generationSettings);
        BiomeDefaultFeatures.addJungleGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addSwampExtraVegetation(generationSettings);
        addVegetal(generationSettings, AquaticPlacements.SEAGRASS_SWAMP);

        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.BAYOU_TREES);
        BWGOverworldDefaultFeatures.addBWGSwampVegetation(generationSettings);
        BWGOverworldDefaultFeatures.addCattails(generationSettings);
        BWGOverworldDefaultFeatures.addLeatherFlowers(generationSettings);
        BWGOverworldDefaultFeatures.addMudDisks(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnSettings);
        addSpawn(spawnSettings, EntityType.SLIME, 1, 1, 1);
        addSpawn(spawnSettings, EntityType.FROG, 10, 2, 5);
        addSpawn(spawnSettings, EntityType.TROPICAL_FISH, 25, 8, 8);

        Music music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_SWAMP);
        float temperature = 0.8F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.9F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4815438).waterFogColor(6717479).grassColorOverride(7375928).foliageColorOverride(6337104).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome blackForest(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGenerationWithoutLava(placedFeatureGetter, carverGetter);

        BiomeDefaultFeatures.addMossyStoneBlock(generationSettings);
        BiomeDefaultFeatures.addFerns(generationSettings);

        BiomeDefaultFeatures.addDefaultFlowers(generationSettings);
        BiomeDefaultFeatures.addGiantTaigaVegetation(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);
        BiomeDefaultFeatures.addRareBerryBushes(generationSettings);

        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.BLACK_FOREST_TREES);
        BWGOverworldDefaultFeatures.addBlueBerryBush(generationSettings);
        BWGOverworldDefaultFeatures.addLushBlueBerryBush(generationSettings);
        BWGOverworldDefaultFeatures.addAnemones(generationSettings);
        BWGOverworldDefaultFeatures.addSages(generationSettings);
        BWGOverworldDefaultFeatures.addBlueRoseBush(generationSettings);
        BWGOverworldDefaultFeatures.addWinterSucculent(generationSettings);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.BLACK_ROSE);
        BWGOverworldDefaultFeatures.addMossyStoneBoulder(generationSettings);
        BWGOverworldDefaultFeatures.addRockyStoneBoulder(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        addSpawn(spawnSettings, EntityType.WOLF, 8, 4, 4);
        addSpawn(spawnSettings, EntityType.RABBIT, 4, 2, 3);
        addSpawn(spawnSettings, EntityType.FOX, 8, 2, 4);
        addSpawn(spawnSettings, EntityType.BAT, 10, 8, 8);
        BiomeDefaultFeatures.monsters(spawnSettings, 100, 25, 100, false);

        float temperature = 0.45F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.65F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(5011004).foliageColorOverride(2263842).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome canadianShield(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);

        BiomeDefaultFeatures.addMossyStoneBlock(generationSettings);
        BiomeDefaultFeatures.addFerns(generationSettings);


        BiomeDefaultFeatures.addDefaultFlowers(generationSettings);
        BiomeDefaultFeatures.addGiantTaigaVegetation(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);

        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.CANADIAN_SHIELD_TREES);

        BWGOverworldDefaultFeatures.addSparseSpruceTrees(generationSettings);
        BWGOverworldDefaultFeatures.addSparseAspenTreesShrubs(generationSettings);
        BWGOverworldDefaultFeatures.addBlueBerryBush(generationSettings);
        BWGOverworldDefaultFeatures.addLushBlueBerryBush(generationSettings);
        BWGOverworldDefaultFeatures.addAnemones(generationSettings);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.HYDRANGEAS);
        BWGOverworldDefaultFeatures.addBlueRoseBush(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        BiomeDefaultFeatures.monsters(spawnSettings, 95, 5, 100, false);
        addSpawn(spawnSettings, EntityType.WOLF, 8, 4, 4);
        addSpawn(spawnSettings, EntityType.RABBIT, 4, 2, 3);
        addSpawn(spawnSettings, EntityType.FOX, 8, 2, 4);
        addSpawn(spawnSettings, EntityType.BAT, 10, 8, 8);

        float temperature = 0.25F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.5F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome crimsonTundra(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);

        BiomeDefaultFeatures.addFerns(generationSettings);
        BiomeDefaultFeatures.addDefaultFlowers(generationSettings);
        BiomeDefaultFeatures.addGiantTaigaVegetation(generationSettings);
        BiomeDefaultFeatures.addSavannaExtraGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);

        BWGOverworldDefaultFeatures.addSparseRedOrangeSpruceTrees(generationSettings);
        BWGOverworldDefaultFeatures.addCloverPatches(generationSettings);
        BWGOverworldDefaultFeatures.addRose(generationSettings);


        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        BiomeDefaultFeatures.monsters(spawnSettings, 95, 5, 100, false);
        addSpawn(spawnSettings, EntityType.RABBIT, 10, 2, 3);
        addSpawn(spawnSettings, EntityType.FOX, 8, 2, 4);
        addSpawn(spawnSettings, EntityType.STRAY, 85, 2, 3);
        addSpawn(spawnSettings, EntityType.POLAR_BEAR, 1, 1, 2);

        float temperature = 0.75F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.1F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(6200521).waterFogColor(6200521).grassColorOverride(13388072).foliageColorOverride(13388072).fogColor(12638463).skyColor(12700876).ambientParticle(new AmbientParticleSettings(ParticleTypes.WHITE_ASH, 0.01428F)).ambientLoopSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome sakuraGrove(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGenerationWithoutLava(placedFeatureGetter, carverGetter);

        BiomeDefaultFeatures.addForestFlowers(generationSettings);
        BiomeDefaultFeatures.addCherryGroveVegetation(generationSettings);
        //BiomeDefaultFeatures.addDefaultFlowers(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);

        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.WHITE_SAKURA_TREES);
        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.YELLOW_SAKURA_TREES);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.JAPANESE_ORCHID);
        BWGOverworldDefaultFeatures.addLeafPile(generationSettings);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.SAKURA_PETALS);
        BWGOverworldDefaultFeatures.addFlowerPatches(generationSettings);
        BWGOverworldDefaultFeatures.addCloverPatches(generationSettings);
//        BYGDefaultBiomeFeatures.addBeeHive(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        BiomeDefaultFeatures.monsters(spawnSettings, 95, 5, 100, false);
        addSpawn(spawnSettings, EntityType.WOLF, 5, 4, 4);
        addSpawn(spawnSettings, EntityType.BAT, 10, 8, 8);

        float temperature = 0.7F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.8F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(10999916).foliageColorOverride(10999916).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome cikaWoods(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGenerationWithoutLava(placedFeatureGetter, carverGetter);

        BiomeDefaultFeatures.addDefaultGrass(generationSettings);
        BiomeDefaultFeatures.addForestGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);
        BiomeDefaultFeatures.addRareBerryBushes(generationSettings);

        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.CIKA_TREES);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.LARGE_PUMPKIN);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.MEDIUM_PUMPKIN);
        BWGOverworldDefaultFeatures.addBlueBerryBush(generationSettings);
        BWGOverworldDefaultFeatures.addLushBlueBerryBush(generationSettings);
        BWGOverworldDefaultFeatures.addAnemones(generationSettings);
        BWGOverworldDefaultFeatures.addCrocus(generationSettings);
        BWGOverworldDefaultFeatures.addIris(generationSettings);
        BWGOverworldDefaultFeatures.addBlueRoseBush(generationSettings);
        BWGOverworldDefaultFeatures.addWinterSucculent(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        addSpawn(spawnSettings, EntityType.BAT, 10, 8, 8);
        BiomeDefaultFeatures.monsters(spawnSettings, 95, 5, 100, false);

        float temperature = 0.35F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.5F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(13414235).foliageColorOverride(13414235).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome coniferousForest(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter, boolean snowy) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGenerationWithoutLava(placedFeatureGetter, carverGetter);

        BiomeDefaultFeatures.addMossyStoneBlock(generationSettings);
        BiomeDefaultFeatures.addFerns(generationSettings);

        BiomeDefaultFeatures.addDefaultFlowers(generationSettings);
        BiomeDefaultFeatures.addGiantTaigaVegetation(generationSettings);
        BiomeDefaultFeatures.addTaigaGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);
        BiomeDefaultFeatures.addRareBerryBushes(generationSettings);

        BWGOverworldDefaultFeatures.addSparseSpruceTrees(generationSettings);
        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.CONIFER_TREES);

        BWGOverworldDefaultFeatures.addBlueBerryBush(generationSettings);
        BWGOverworldDefaultFeatures.addLushBlueBerryBush(generationSettings);
        BWGOverworldDefaultFeatures.addCrocus(generationSettings);
        BWGOverworldDefaultFeatures.addIris(generationSettings);
        BWGOverworldDefaultFeatures.addBlueRoseBush(generationSettings);
        BWGOverworldDefaultFeatures.addWinterSucculent(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        addSpawn(spawnSettings, EntityType.WOLF, 8, 4, 4);
        addSpawn(spawnSettings, EntityType.RABBIT, 4, 2, 3);
        addSpawn(spawnSettings, EntityType.FOX, 8, 2, 4);
        addSpawn(spawnSettings, EntityType.BAT, 10, 8, 8);
        addSpawn(spawnSettings, EntityType.SPIDER, 100, 4, 4);
        addSpawn(spawnSettings, snowy ? EntityType.HUSK : EntityType.ZOMBIE, 100, 4, 4);
        addSpawn(spawnSettings, EntityType.SKELETON, 100, 4, 4);
        addSpawn(spawnSettings, EntityType.ZOMBIE_VILLAGER, 25, 1, 1);
        addSpawn(spawnSettings, EntityType.CREEPER, 100, 4, 4);
        addSpawn(spawnSettings, EntityType.SLIME, 100, 4, 4);
        addSpawn(spawnSettings, EntityType.ENDERMAN, 10, 1, 4);
        addSpawn(spawnSettings, EntityType.WITCH, 5, 1, 1);

        float temperature = snowy ? -0.5F : 0.25F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.8F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(5011004).foliageColorOverride(2263842).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome cragGardens(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGenerationWithoutLava(placedFeatureGetter, carverGetter);
        addRawGeneration(generationSettings, BWGPlacedFeatures.CRAG_LAKE);
        addVegetal(generationSettings, BWGPlacedFeatures.VINE_PROCESSOR);
        addVegetal(generationSettings, BWGPlacedFeatures.LUSH_BLOCKS_PROCESSOR);

        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.CRAG_BAMBOO);
        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.CRAG_BUSHES);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.CRAG_LAKE_VEGETATION);

        BiomeDefaultFeatures.addJungleGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);

        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.GUIANA_SHIELD_TREES);
        BWGOverworldDefaultFeatures.addBWGTropicFlowers(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);
        addVegetal(generationSettings, AquaticPlacements.SEAGRASS_NORMAL);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.baseJungleSpawns(spawnSettings);
        addSpawn(spawnSettings, EntityType.PARROT, 40, 1, 2);
        addSpawn(spawnSettings, EntityType.BAT, 10, 8, 8);
        addSpawn(spawnSettings, EntityType.OCELOT, 2, 1, 1);

        float temperature = 0.95F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.9F).specialEffects((new BiomeSpecialEffects.Builder()).backgroundMusic(Musics.createGameMusic(BWGSounds.MUSIC_BIOME_CRAG_GARDENS.get())).waterColor(9230578).waterFogColor(2835532).grassColorOverride(10145074).foliageColorOverride(10145074).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome cypressSwamplands(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        OverworldBiomes.globalOverworldGeneration(generationSettings);
        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BWGOverworldDefaultFeatures.addSwampDelta(generationSettings);

        BiomeDefaultFeatures.addSwampClayDisk(generationSettings);
        BiomeDefaultFeatures.addSavannaExtraGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addSwampExtraVegetation(generationSettings);
        addVegetal(generationSettings, AquaticPlacements.SEAGRASS_SWAMP);

        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.CYPRESS_TREES);
        BWGOverworldDefaultFeatures.addBWGSwampVegetation(generationSettings);
        BWGOverworldDefaultFeatures.addCattails(generationSettings);
        BWGOverworldDefaultFeatures.addLeatherFlowers(generationSettings);
        BWGOverworldDefaultFeatures.addMudDisks(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnSettings);
        addSpawn(spawnSettings, EntityType.SLIME, 1, 1, 1);
        addSpawn(spawnSettings, EntityType.FROG, 10, 2, 5);
        addSpawn(spawnSettings, EntityType.TROPICAL_FISH, 25, 8, 8);

        Music music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_SWAMP);
        float temperature = 0.8F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.9F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(6337104).foliageColorOverride(6337104).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome lushStacks(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = OverworldBiomes.baseOceanGeneration(placedFeatureGetter, carverGetter);
        addRawGeneration(generationSettings, BWGPlacedFeatures.LUSH_ROUNDED_ROCK);
        addVegetal(generationSettings, AquaticPlacements.WARM_OCEAN_VEGETATION);
        addVegetal(generationSettings, AquaticPlacements.SEAGRASS_WARM);
        addVegetal(generationSettings, AquaticPlacements.SEA_PICKLE);
        BiomeDefaultFeatures.addLushCavesVegetationFeatures(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        addSpawn(spawnSettings, BWGEntities.MAN_O_WAR.get(), 200, 50, 50);
        BiomeDefaultFeatures.warmOceanSpawns(spawnSettings, 10, 4);
        float temperature = 1.0F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.8F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4445678).waterFogColor(270131).grassColorOverride(10275901).foliageColorOverride(10275901).waterFogColor(2835532).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome daciteRidges(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);

        BiomeDefaultFeatures.addDefaultFlowers(generationSettings);
        BiomeDefaultFeatures.addGiantTaigaVegetation(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);
        BiomeDefaultFeatures.addMossyStoneBlock(generationSettings);

        addRawGeneration(generationSettings, BWGPlacedFeatures.BOULDER);
        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.DACITE_RIDGE_TREES);
        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.HOLLY_TREES);
        BWGOverworldDefaultFeatures.addBlueBerryBush(generationSettings);
        BWGOverworldDefaultFeatures.addLushBlueBerryBush(generationSettings);
        BWGOverworldDefaultFeatures.addCrocus(generationSettings);
        BWGOverworldDefaultFeatures.addIris(generationSettings);
        BWGOverworldDefaultFeatures.addBlueRoseBush(generationSettings);
        BWGOverworldDefaultFeatures.addWinterSucculent(generationSettings);
        BWGOverworldDefaultFeatures.addMossyStoneBoulder(generationSettings);
        BWGOverworldDefaultFeatures.addRockyStoneBoulder(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.monsters(spawnSettings, 95, 5, 100, false);
        addSpawn(spawnSettings, EntityType.SHEEP, 12, 4, 4);
        addSpawn(spawnSettings, EntityType.WOLF, 8, 4, 4);
        addSpawn(spawnSettings, EntityType.RABBIT, 4, 2, 3);
        addSpawn(spawnSettings, EntityType.FOX, 8, 2, 4);
        addSpawn(spawnSettings, EntityType.BAT, 10, 8, 8);
        float temperature = 0.25F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.8F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(9230578).waterFogColor(2835532).grassColorOverride(5011004).foliageColorOverride(2263842).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome daciteShore(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);
        BiomeDefaultFeatures.addFerns(generationSettings);

        BiomeDefaultFeatures.addDefaultFlowers(generationSettings);
        BiomeDefaultFeatures.addJungleGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);

        BWGOverworldDefaultFeatures.addRockyStoneBoulder(generationSettings);

        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.monsters(spawnSettings, 95, 5, 100, false);
        addSpawn(spawnSettings, EntityType.SHEEP, 12, 4, 4);
        addSpawn(spawnSettings, EntityType.WOLF, 8, 4, 4);
        addSpawn(spawnSettings, EntityType.RABBIT, 4, 2, 3);
        addSpawn(spawnSettings, EntityType.FOX, 8, 2, 4);

        float temperature = 0.25F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.8F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(9230578).waterFogColor(2835532).grassColorOverride(5011004).foliageColorOverride(2263842).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome atacamaOutback(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        BiomeDefaultFeatures.addFossilDecoration(generationSettings);
        addDefaultOverworldGeneration(generationSettings);


        BiomeDefaultFeatures.addDesertVegetation(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDesertExtraVegetation(generationSettings);
        BiomeDefaultFeatures.addDesertExtraDecoration(generationSettings);

        BWGOverworldDefaultFeatures.addPaloVerdeTrees(generationSettings);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.ATACAMA_OUTBACK_VEGETATION);
        BWGOverworldDefaultFeatures.addShrub(generationSettings);
        BWGOverworldDefaultFeatures.addAnemones(generationSettings);
        BWGOverworldDefaultFeatures.addSages(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.desertSpawns(spawnSettings);

        return new Biome.BiomeBuilder().hasPrecipitation(false).temperature(2.0F).downfall(0.0F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(6200521).waterFogColor(6200521).fogColor(12815488).skyColor(12815488).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome windsweptDesert(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        BiomeDefaultFeatures.addFossilDecoration(generationSettings);
        addDefaultOverworldGeneration(generationSettings);
        BiomeDefaultFeatures.addDefaultFlowers(generationSettings);
        BiomeDefaultFeatures.addDesertVegetation(generationSettings);
        addRawGeneration(generationSettings, BWGPlacedFeatures.WINDSWEPT_BOULDER);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.WINDSWEPT_DESERT_VEGETATION);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.desertSpawns(spawnSettings);

        return new Biome.BiomeBuilder().hasPrecipitation(false).temperature(2.0F).downfall(0.0F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(6200521).waterFogColor(6200521).fogColor(12815488).skyColor(12815488).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome ebonyWoods(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGenerationWithoutLava(placedFeatureGetter, carverGetter);
        BWGOverworldDefaultFeatures.addHugeMushrooms(generationSettings);

        BiomeDefaultFeatures.addDefaultGrass(generationSettings);
        BiomeDefaultFeatures.addForestGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);

        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.EBONY_TREES);
        BWGOverworldDefaultFeatures.addLeafPile(generationSettings);
        BWGOverworldDefaultFeatures.addAnemones(generationSettings);
        BWGOverworldDefaultFeatures.addHorseWeed(generationSettings);
//        BYGDefaultBiomeFeatures.addBeeHive(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        addSpawn(spawnSettings, EntityType.WOLF, 8, 4, 4);
        addSpawn(spawnSettings, EntityType.RABBIT, 4, 2, 3);
        addSpawn(spawnSettings, EntityType.FOX, 8, 2, 4);
        addSpawn(spawnSettings, EntityType.BAT, 10, 8, 8);


        float temperature = 0.8F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.8F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(5406551).foliageColorOverride(6589494).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome enchantedTangle(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);
        BWGOverworldDefaultFeatures.addHugeMushrooms(generationSettings);

        BiomeDefaultFeatures.addLightBambooVegetation(generationSettings);
        BiomeDefaultFeatures.addSparseJungleTrees(generationSettings);
        BiomeDefaultFeatures.addJungleGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);
        BiomeDefaultFeatures.addSparseJungleMelons(generationSettings);

        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.ENCHANTED_TREES);
        BWGOverworldDefaultFeatures.addFlowerPatches(generationSettings);
        BWGOverworldDefaultFeatures.addBWGTropicFlowers(generationSettings);
        BWGOverworldDefaultFeatures.addFairySlipper(generationSettings);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.CYAN_ROSE);
        BWGOverworldDefaultFeatures.addWhitePuffball(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        addSpawn(spawnSettings, EntityType.SHEEP, 10, 4, 4);
        addSpawn(spawnSettings, EntityType.PIG, 15, 4, 4);
        addSpawn(spawnSettings, EntityType.CHICKEN, 12, 4, 4);
        addSpawn(spawnSettings, EntityType.COW, 10, 4, 4);
        addSpawn(spawnSettings, EntityType.BAT, 10, 8, 8);
        addSpawn(spawnSettings, EntityType.ENDERMAN, 10, 1, 4);
        addSpawn(spawnSettings, EntityType.OCELOT, 10, 1, 1);
        addSpawn(spawnSettings, EntityType.PARROT, 40, 1, 2);

        BiomeDefaultFeatures.baseJungleSpawns(spawnSettings);
        addSpawn(spawnSettings, EntityType.PARROT, 40, 1, 2);
        addSpawn(spawnSettings, EntityType.OCELOT, 2, 1, 3);
        addSpawn(spawnSettings, EntityType.PANDA, 1, 1, 2);

        float temperature = 0.8F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.8F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(10003745).foliageColorOverride(11898572).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientParticle(new AmbientParticleSettings(BWGParticles.BOREALIS_GLINT.get(), 0.00150F)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome frostedTaiga(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);

        BiomeDefaultFeatures.addFerns(generationSettings);
        BiomeDefaultFeatures.addDefaultFlowers(generationSettings);
        BiomeDefaultFeatures.addTaigaGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);
        BiomeDefaultFeatures.addRareBerryBushes(generationSettings);

        BWGOverworldDefaultFeatures.addSparseSpruceTrees(generationSettings);
        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.BLUE_SPRUCE_TREES);
        BWGOverworldDefaultFeatures.addCrocus(generationSettings);
        BWGOverworldDefaultFeatures.addIris(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        BiomeDefaultFeatures.monsters(spawnSettings, 95, 5, 100, false);
        addSpawn(spawnSettings, EntityType.WOLF, 8, 4, 4);
        addSpawn(spawnSettings, EntityType.RABBIT, 4, 2, 3);
        addSpawn(spawnSettings, EntityType.FOX, 8, 2, 4);
        addSpawn(spawnSettings, EntityType.BAT, 10, 8, 8);

        float temperature = -0.5F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.8F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome temperateGrove(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGenerationWithoutLava(placedFeatureGetter, carverGetter);

        BiomeDefaultFeatures.addPlainGrass(generationSettings);
        addVegetal(generationSettings, VegetationPlacements.PATCH_SUNFLOWER);
       // BiomeDefaultFeatures.addDefaultFlowers(generationSettings);
        BiomeDefaultFeatures.addDefaultGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);

        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.TEMPERATE_GROVE_TREES);
        BWGOverworldDefaultFeatures.addCloverPatches(generationSettings);
        BWGOverworldDefaultFeatures.addAnemones(generationSettings);
        BWGOverworldDefaultFeatures.addSages(generationSettings);
        BWGOverworldDefaultFeatures.addWhitePuffball(generationSettings);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.BISTORT);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.ANGELICA);
//        BYGDefaultBiomeFeatures.addBeeHive(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        addSpawn(spawnSettings, EntityType.BAT, 10, 8, 8);
        BiomeDefaultFeatures.monsters(spawnSettings, 95, 5, 100, false);
        float temperature = 0.75F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.8F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(11190111).foliageColorOverride(11190111).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome jacarandaJungle(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);
        BWGOverworldDefaultFeatures.addHugeMushrooms(generationSettings);

        BiomeDefaultFeatures.addForestFlowers(generationSettings);
        BiomeDefaultFeatures.addLightBambooVegetation(generationSettings);
        BiomeDefaultFeatures.addDefaultFlowers(generationSettings);
        BiomeDefaultFeatures.addSparseJungleTrees(generationSettings);
        BiomeDefaultFeatures.addSavannaExtraGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);

        BWGOverworldDefaultFeatures.addLeafPile(generationSettings);
        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.JACARANDA_TREES);
        BWGOverworldDefaultFeatures.addJacarandaBushes(generationSettings);
        BWGOverworldDefaultFeatures.addBWGTropicFlowers(generationSettings);
        BWGOverworldDefaultFeatures.addWhitePuffball(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        addSpawn(spawnSettings, EntityType.WOLF, 5, 4, 4);
        addSpawn(spawnSettings, EntityType.BAT, 10, 8, 8);
        BiomeDefaultFeatures.monsters(spawnSettings, 95, 5, 100, false);

        float temperature = 0.95F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.5F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(10145074).foliageColorOverride(14180771).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome whiteMangroveMarshes(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        OverworldBiomes.globalOverworldGeneration(generationSettings);
        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BWGOverworldDefaultFeatures.addSwampDelta(generationSettings);

        BiomeDefaultFeatures.addSwampClayDisk(generationSettings);
        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.OAK_TREES_SWAMP);
        addVegetal(generationSettings, VegetationPlacements.FLOWER_SWAMP);
        addVegetal(generationSettings, VegetationPlacements.PATCH_GRASS_NORMAL);
        addVegetal(generationSettings, VegetationPlacements.PATCH_DEAD_BUSH);
        addVegetal(generationSettings, VegetationPlacements.PATCH_WATERLILY);
        addVegetal(generationSettings, VegetationPlacements.BROWN_MUSHROOM_SWAMP);
        addVegetal(generationSettings, VegetationPlacements.RED_MUSHROOM_SWAMP);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addSwampExtraVegetation(generationSettings);
        addVegetal(generationSettings, AquaticPlacements.SEAGRASS_SWAMP);

        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.MANGROVE_TREES);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.MANGROVE_SWAMP_WATER_VEGETATION);
        BWGOverworldDefaultFeatures.addCattails(generationSettings);
        BWGOverworldDefaultFeatures.addLeatherFlowers(generationSettings);
        BWGOverworldDefaultFeatures.addMudDisks(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnSettings);
        addSpawn(spawnSettings, EntityType.SLIME, 1, 1, 1);
        addSpawn(spawnSettings, EntityType.FROG, 10, 2, 5);
        addSpawn(spawnSettings, EntityType.TROPICAL_FISH, 25, 8, 8);

        Music music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_SWAMP);
        float temperature = 0.8F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.9F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(5878398).waterFogColor(2047788).grassColorOverride(5737549).foliageColorOverride(5737549).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome mapleTaiga(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);

        BiomeDefaultFeatures.addDefaultFlowers(generationSettings);
        BiomeDefaultFeatures.addTaigaGrass(generationSettings);
        BiomeDefaultFeatures.addSavannaExtraGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);

        BWGOverworldDefaultFeatures.addSparseSpruceTrees(generationSettings);
        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.MAPLE_TAIGA_TREES);
        BWGOverworldDefaultFeatures.addOakBushes(generationSettings);

        BWGOverworldDefaultFeatures.addSparseAspenTreesShrubs(generationSettings);
        BWGOverworldDefaultFeatures.addBlueBerryBush(generationSettings);
        BWGOverworldDefaultFeatures.addLushBlueBerryBush(generationSettings);
        BWGOverworldDefaultFeatures.addAnemones(generationSettings);
        BWGOverworldDefaultFeatures.addSages(generationSettings);
        BWGOverworldDefaultFeatures.addRose(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        BiomeDefaultFeatures.commonSpawns(spawnSettings);
        addSpawn(spawnSettings, EntityType.WOLF, 8, 4, 4);
        addSpawn(spawnSettings, EntityType.RABBIT, 4, 2, 3);
        addSpawn(spawnSettings, EntityType.FOX, 8, 2, 4);

        float temperature = 0.25F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.8F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(6586199).foliageColorOverride(14206262).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome coconinoMeadow(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);

        BiomeDefaultFeatures.addForestFlowers(generationSettings);
        BiomeDefaultFeatures.addPlainGrass(generationSettings);
        addVegetal(generationSettings, VegetationPlacements.PATCH_GRASS_NORMAL);
        addVegetal(generationSettings, VegetationPlacements.PATCH_SUNFLOWER);

        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);

        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.SPARSE_MEADOW_TREES);
        BWGOverworldDefaultFeatures.addMeadowShrubs(generationSettings);
        BWGOverworldDefaultFeatures.addCloverFlowers(generationSettings);
        BWGOverworldDefaultFeatures.addCloverPatches(generationSettings);
        BWGOverworldDefaultFeatures.addOakBushes(generationSettings);

        BWGOverworldDefaultFeatures.addBlueBerryBush(generationSettings);
        BWGOverworldDefaultFeatures.addLushBlueBerryBush(generationSettings);
        BWGOverworldDefaultFeatures.addAnemones(generationSettings);
        BWGOverworldDefaultFeatures.addTulips(generationSettings);
        BWGOverworldDefaultFeatures.addWhitePuffball(generationSettings);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.ALPINE_BELLFLOWER);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.ANGELICA);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.PINK_DAFFODIL);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        BiomeDefaultFeatures.commonSpawns(spawnSettings);
        addSpawn(spawnSettings, EntityType.WOLF, 8, 4, 4);
        addSpawn(spawnSettings, EntityType.RABBIT, 4, 2, 3);
        addSpawn(spawnSettings, EntityType.FOX, 8, 2, 4);

        float temperature = 0.8F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.8F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(6530407).foliageColorOverride(5999709).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome deadSea(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);

        addRawGeneration(generationSettings, BWGPlacedFeatures.DRIPSTONE_ROUNDED_ROCK);

        addVegetal(generationSettings, AquaticPlacements.SEAGRASS_NORMAL);
        BiomeDefaultFeatures.addDripstone(generationSettings);


        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        addSpawn(spawnSettings, EntityType.BAT, 10, 8, 8);
        BiomeDefaultFeatures.monsters(spawnSettings, 95, 5, 100, true);

        float temperature = 2.0F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.0F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(9230578).waterFogColor(2835532).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome mojaveDesert(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        BiomeDefaultFeatures.addFossilDecoration(generationSettings);
        addDefaultOverworldGeneration(generationSettings);

        BiomeDefaultFeatures.addDefaultFlowers(generationSettings);
        BiomeDefaultFeatures.addDefaultGrass(generationSettings);
        BiomeDefaultFeatures.addDesertVegetation(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDesertExtraVegetation(generationSettings);
        BiomeDefaultFeatures.addDesertExtraDecoration(generationSettings);
        BWGOverworldDefaultFeatures.addBeachGrass(generationSettings);

        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.YUCCA_TREES);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.MOJAVE_DESERT_VEGETATION);
        BWGOverworldDefaultFeatures.addFirecrackerBush(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.monsters(spawnSettings, 109, 1, 100, false);
        addSpawn(spawnSettings, EntityType.HUSK, 80, 4, 4);

        float temperature = 2.0F;
        return new Biome.BiomeBuilder().hasPrecipitation(false).temperature(temperature).downfall(0.0F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome orchard(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGenerationWithoutLava(placedFeatureGetter, carverGetter);

        BiomeDefaultFeatures.addDefaultFlowers(generationSettings);
        BiomeDefaultFeatures.addSavannaExtraGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);

        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.ORCHARD_TREES);
        BWGOverworldDefaultFeatures.addCloverFlowers(generationSettings);
        BWGOverworldDefaultFeatures.addCloverPatches(generationSettings);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.LOLLIPOP_FLOWERS);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.YELLOW_DAFFODIL);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.WHITE_ALLIUMS);
//        BYGDefaultBiomeFeatures.addBeeHive(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.plainsSpawns(spawnSettings);

        float temperature = 0.8F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.8F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(10210365).foliageColorOverride(10210365).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome paleBog(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        OverworldBiomes.globalOverworldGeneration(generationSettings);
        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BWGOverworldDefaultFeatures.addSwampDelta(generationSettings);

        BiomeDefaultFeatures.addSwampClayDisk(generationSettings);
        BiomeDefaultFeatures.addJungleGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addSwampExtraVegetation(generationSettings);
        addVegetal(generationSettings, AquaticPlacements.SEAGRASS_SWAMP);

        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.SPIRIT_TREES);
        BWGOverworldDefaultFeatures.addBWGSwampVegetation(generationSettings);
        BWGOverworldDefaultFeatures.addFluorscentCattails(generationSettings);
        BWGOverworldDefaultFeatures.addCattails(generationSettings);
        BWGOverworldDefaultFeatures.addSages(generationSettings);
        BWGOverworldDefaultFeatures.addMudDisks(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        addSpawn(spawnSettings, EntityType.DROWNED, 85, 2, 4);
        addSpawn(spawnSettings, EntityType.SPIDER, 85, 3, 4);
        addSpawn(spawnSettings, EntityType.BOGGED, 85, 2, 4);
        addSpawn(spawnSettings, EntityType.SLIME, 1, 1, 1);
        addSpawn(spawnSettings, EntityType.FROG, 10, 2, 5);

        float temperature = 0.8F;
        return new Biome.BiomeBuilder().hasPrecipitation(true)
                .temperature(temperature)
                .downfall(0.9F)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .backgroundMusic(Musics.createGameMusic(BWGSounds.MUSIC_BIOME_PALE_BOG.get()))
                        .ambientParticle(new AmbientParticleSettings(BWGParticles.SPIRIT.get(), 0.00005F))
                        .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0))
                        .ambientAdditionsSound(new AmbientAdditionsSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111))
                        .waterColor(9230578).waterFogColor(2835532).grassColorOverride(11056293).foliageColorOverride(11056293).fogColor(11056293).skyColor(11056293)
                        .build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome prairie(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);

        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);

        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.PRAIRIE_SHRUBS);
        BWGOverworldDefaultFeatures.addSparseOakTrees(generationSettings);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.PRAIRIE_GRASS);

        BWGOverworldDefaultFeatures.addCaliforniaPoppy(generationSettings);
        BWGOverworldDefaultFeatures.addWhitePuffball(generationSettings);
//        BYGDefaultBiomeFeatures.addBeeHive(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.plainsSpawns(spawnSettings);
        BiomeDefaultFeatures.commonSpawns(spawnSettings);

        float temperature = 0.8F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.2F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(10003745).foliageColorOverride(10003745).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome rainbowBeach(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);

        BWGOverworldDefaultFeatures.addBeachGrass(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnSettings);

        float temperature = 0.85F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.75F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4566514).waterFogColor(267827).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome overgrowthWoodlands(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGenerationWithoutLava(placedFeatureGetter, carverGetter);

        BiomeDefaultFeatures.addForestFlowers(generationSettings);
        BiomeDefaultFeatures.addFerns(generationSettings);
        BiomeDefaultFeatures.addDefaultFlowers(generationSettings);
        BiomeDefaultFeatures.addSavannaExtraGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);

        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.OVERGROWTH_WOODLANDS_TREES);
        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.BWG_OAK_TREES);
        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.BWG_BIRCH_TREES);

        BWGOverworldDefaultFeatures.addOakBushes(generationSettings);
        BWGOverworldDefaultFeatures.addAnemones(generationSettings);
        BWGOverworldDefaultFeatures.addRose(generationSettings);
        BWGOverworldDefaultFeatures.addBlueRoseBush(generationSettings);
        BWGOverworldDefaultFeatures.addFairySlipper(generationSettings);
        BWGOverworldDefaultFeatures.addWhitePuffball(generationSettings);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.ANGELICA);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.PINK_DAFFODIL);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.HORSEWEED);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        BiomeDefaultFeatures.commonSpawns(spawnSettings);
        addSpawn(spawnSettings, EntityType.WOLF, 8, 4, 4);
        addSpawn(spawnSettings, EntityType.RABBIT, 4, 2, 3);
        addSpawn(spawnSettings, EntityType.FOX, 8, 2, 4);

        float temperature = 0.8F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.9F).specialEffects((new BiomeSpecialEffects.Builder()).foliageColorOverride(7110705).grassColorOverride(7110705).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(9358480).ambientParticle(new AmbientParticleSettings(ParticleTypes.SPORE_BLOSSOM_AIR, 0.00050F)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    // Come back later and do decent arches
    protected static Biome redRockValley(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        BiomeDefaultFeatures.addFossilDecoration(generationSettings);
        addDefaultOverworldGeneration(generationSettings);

        BiomeDefaultFeatures.addDefaultFlowers(generationSettings);
        BiomeDefaultFeatures.addForestGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);

        BWGOverworldDefaultFeatures.addOrangeTerracottaBoulder(generationSettings);
        BWGOverworldDefaultFeatures.addPaloVerdeTrees(generationSettings);
        BWGOverworldDefaultFeatures.addFirecrackerBush(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        addSpawn(spawnSettings, EntityType.RABBIT, 4, 2, 3);
        addSpawn(spawnSettings, EntityType.BAT, 10, 8, 8);
        addSpawn(spawnSettings, EntityType.SPIDER, 100, 4, 4);
        addSpawn(spawnSettings, EntityType.HUSK, 95, 4, 4);
        addSpawn(spawnSettings, EntityType.ZOMBIE_VILLAGER, 5, 1, 1);
        addSpawn(spawnSettings, EntityType.SKELETON, 100, 4, 4);
        addSpawn(spawnSettings, EntityType.CREEPER, 100, 4, 4);
        addSpawn(spawnSettings, EntityType.SLIME, 100, 4, 4);
        addSpawn(spawnSettings, EntityType.ENDERMAN, 10, 1, 4);
        addSpawn(spawnSettings, EntityType.WITCH, 5, 1, 1);

        float temperature = 1.2F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.1F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(10855786).foliageColorOverride(10855786).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome redwoodThicket(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGenerationWithoutLava(placedFeatureGetter, carverGetter);

        addRawGeneration(generationSettings, BWGPlacedFeatures.BOULDER);
        BiomeDefaultFeatures.addForestFlowers(generationSettings);
        BiomeDefaultFeatures.addFerns(generationSettings);

        BiomeDefaultFeatures.addDefaultFlowers(generationSettings);
        BiomeDefaultFeatures.addForestGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);

        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.SPRUCE_TREES);
        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.REDWOOD_TREES);
        BWGOverworldDefaultFeatures.addLeafPile(generationSettings);
        BWGOverworldDefaultFeatures.addMossyStoneBoulder(generationSettings);
        BWGOverworldDefaultFeatures.addRockyStoneBoulder(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        BiomeDefaultFeatures.commonSpawns(spawnSettings);
        addSpawn(spawnSettings, EntityType.WOLF, 8, 4, 4);
        addSpawn(spawnSettings, EntityType.RABBIT, 4, 2, 3);
        addSpawn(spawnSettings, EntityType.FOX, 8, 2, 4);

        float temperature = 0.9F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.9F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(8896351).foliageColorOverride(8896351).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome roseFields(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);
        BiomeDefaultFeatures.addSavannaExtraGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);

        BWGOverworldDefaultFeatures.addRoseFieldSpruceTrees(generationSettings);

        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.ROSE_FIELD_FLOWERS);
        BWGOverworldDefaultFeatures.addBlueBerryBush(generationSettings);
        BWGOverworldDefaultFeatures.addLushBlueBerryBush(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.plainsSpawns(spawnSettings);
        addSpawn(spawnSettings, EntityType.FOX, 8, 2, 4);
        float temperature = 0.8F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.4F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(8231780).foliageColorOverride(8231780).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome shatteredGlacier(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);

        BiomeDefaultFeatures.addDefaultCarversAndLakes(generationSettings);
        BiomeDefaultFeatures.addDefaultCrystalFormations(generationSettings);
        BiomeDefaultFeatures.addDefaultMonsterRoom(generationSettings);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(generationSettings);
        BiomeDefaultFeatures.addDefaultSprings(generationSettings);
        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(generationSettings);
//        generationSettings.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, BYGPlacedFeatures.BLACK_ICE_SNOW);
        BiomeDefaultFeatures.addSurfaceFreezing(generationSettings);
        BiomeDefaultFeatures.addFerns(generationSettings);
        BiomeDefaultFeatures.addDefaultFlowers(generationSettings);
        BiomeDefaultFeatures.addTaigaGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);

        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.WINTER_ROSE);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnSettings);
        addSpawn(spawnSettings, EntityType.STRAY, 80, 4, 4);

        float temperature = -0.5F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(1.0F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(6200521).waterFogColor(6200521).grassColorOverride(15834405).foliageColorOverride(15834405).fogColor(12638463).skyColor(12700876).ambientParticle(new AmbientParticleSettings(ParticleTypes.WHITE_ASH, 0.01428F)).ambientLoopSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome firecrackerChaparral(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);

        BiomeDefaultFeatures.addSavannaGrass(generationSettings);

        BiomeDefaultFeatures.addDesertVegetation(generationSettings);
        BiomeDefaultFeatures.addSavannaExtraGrass(generationSettings);

        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);

        BWGOverworldDefaultFeatures.addSparseOakTrees(generationSettings);
        BWGOverworldDefaultFeatures.addShrub(generationSettings);
        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.FIRECRACKER_SHRUBS);
        BWGOverworldDefaultFeatures.addFirecrackerBush(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        addSpawn(spawnSettings, EntityType.HORSE, 5, 2, 6);
        addSpawn(spawnSettings, EntityType.DONKEY, 1, 1, 3);
        addSpawn(spawnSettings, EntityType.BAT, 10, 8, 8);
        BiomeDefaultFeatures.monsters(spawnSettings, 95, 5, 100, false);

        float temperature = 2.0F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.4F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(9874031).foliageColorOverride(7048739).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome sierraBadlands(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        OverworldBiomes.globalOverworldGeneration(generationSettings);

        addRawGeneration(generationSettings, BWGPlacedFeatures.BOULDER);
        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BiomeDefaultFeatures.addExtraGold(generationSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(generationSettings);
        BiomeDefaultFeatures.addExtraEmeralds(generationSettings);
        BiomeDefaultFeatures.addBadlandGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addBadlandExtraVegetation(generationSettings);

        BWGOverworldDefaultFeatures.addOakBushes(generationSettings);
        BWGOverworldDefaultFeatures.addPaloVerdeTrees(generationSettings);
        BWGOverworldDefaultFeatures.addOrangeTerracottaBoulder(generationSettings);
        BWGOverworldDefaultFeatures.addFirecrackerBush(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        addSpawn(spawnSettings, EntityType.RABBIT, 4, 2, 3);
        addSpawn(spawnSettings, EntityType.BAT, 10, 8, 8);
        addSpawn(spawnSettings, EntityType.SPIDER, 100, 4, 4);
        addSpawn(spawnSettings, EntityType.HUSK, 95, 4, 4);
        addSpawn(spawnSettings, EntityType.ZOMBIE_VILLAGER, 5, 1, 1);
        addSpawn(spawnSettings, EntityType.SKELETON, 100, 4, 4);
        addSpawn(spawnSettings, EntityType.CREEPER, 100, 4, 4);
        addSpawn(spawnSettings, EntityType.SLIME, 100, 4, 4);
        addSpawn(spawnSettings, EntityType.ENDERMAN, 10, 1, 4);
        addSpawn(spawnSettings, EntityType.WITCH, 5, 1, 1);

        float temperature = 1.2F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.1F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(10855786).foliageColorOverride(10855786).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome ruggedBadlands(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);

        BiomeDefaultFeatures.addDesertVegetation(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDesertExtraVegetation(generationSettings);
        BiomeDefaultFeatures.addDesertExtraDecoration(generationSettings);
        BWGOverworldDefaultFeatures.addPatchBeachGrassNoise(generationSettings);

        BWGOverworldDefaultFeatures.addPaloVerdeTrees(generationSettings);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.RUGGED_BADLANDS_VEGETATION);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        addSpawn(spawnSettings, EntityType.RABBIT, 4, 2, 3);
        addSpawn(spawnSettings, EntityType.BAT, 10, 8, 8);
        addSpawn(spawnSettings, EntityType.SPIDER, 100, 4, 4);
        addSpawn(spawnSettings, EntityType.HUSK, 95, 4, 4);
        addSpawn(spawnSettings, EntityType.ZOMBIE_VILLAGER, 5, 1, 1);
        addSpawn(spawnSettings, EntityType.SKELETON, 100, 4, 4);
        addSpawn(spawnSettings, EntityType.CREEPER, 100, 4, 4);
        addSpawn(spawnSettings, EntityType.SLIME, 100, 4, 4);
        addSpawn(spawnSettings, EntityType.ENDERMAN, 10, 1, 4);
        addSpawn(spawnSettings, EntityType.WITCH, 5, 1, 1);
        addSpawn(spawnSettings, EntityType.RAVAGER, 1, 1, 2);

        float temperature = 1.2F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.1F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(10855786).foliageColorOverride(10855786).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome canyon(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);
//        addRawGeneration(generationSettings, BWGPlacedFeatures.CANYON);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        addSpawn(spawnSettings, EntityType.RABBIT, 4, 2, 3);
        addSpawn(spawnSettings, EntityType.BAT, 10, 8, 8);
        addSpawn(spawnSettings, EntityType.SPIDER, 100, 4, 4);
        addSpawn(spawnSettings, EntityType.HUSK, 95, 4, 4);
        addSpawn(spawnSettings, EntityType.ZOMBIE_VILLAGER, 5, 1, 1);
        addSpawn(spawnSettings, EntityType.SKELETON, 100, 4, 4);
        addSpawn(spawnSettings, EntityType.CREEPER, 100, 4, 4);
        addSpawn(spawnSettings, EntityType.SLIME, 100, 4, 4);
        addSpawn(spawnSettings, EntityType.ENDERMAN, 10, 1, 4);
        addSpawn(spawnSettings, EntityType.WITCH, 5, 1, 1);

        float temperature = 1.2F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.1F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(10855786).foliageColorOverride(10855786).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    //TODO: Configure properties
    protected static Biome ironwoodGour(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);

        BiomeDefaultFeatures.addSavannaGrass(generationSettings);
        BiomeDefaultFeatures.addSavannaTrees(generationSettings);
        BiomeDefaultFeatures.addWarmFlowers(generationSettings);
        BiomeDefaultFeatures.addSavannaExtraGrass(generationSettings);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.IRONWOOD_PLATEAU_PATCH_GRASS_WORLD_SURFACE);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);
        BWGOverworldDefaultFeatures.addSages(generationSettings);
        BWGOverworldDefaultFeatures.addCaliforniaPoppy(generationSettings);
        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.IRONWOOD_PLATEAU_TREES);
        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.IRONWOOD_GROUND_TREES);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.IRONWOOD_PLATEAU_GLOW_LICHEN);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        addSpawn(spawnSettings, EntityType.RABBIT, 4, 2, 3);
        addSpawn(spawnSettings, EntityType.BAT, 10, 8, 8);
        addSpawn(spawnSettings, EntityType.SPIDER, 100, 4, 4);
        addSpawn(spawnSettings, EntityType.HUSK, 95, 4, 4);
        addSpawn(spawnSettings, EntityType.ZOMBIE_VILLAGER, 5, 1, 1);
        addSpawn(spawnSettings, EntityType.SKELETON, 100, 4, 4);
        addSpawn(spawnSettings, EntityType.CREEPER, 100, 4, 4);
        addSpawn(spawnSettings, EntityType.SLIME, 100, 4, 4);
        addSpawn(spawnSettings, EntityType.ENDERMAN, 10, 1, 4);
        addSpawn(spawnSettings, EntityType.WITCH, 5, 1, 1);

        float temperature = 1.2F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.1F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(10855786).foliageColorOverride(10855786).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome erodedBorealis(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGenerationWithoutLava(placedFeatureGetter, carverGetter);

        addRawGeneration(generationSettings, BWGPlacedFeatures.BOREALIS_ICE_SHARPENED_SPIKE);
        BiomeDefaultFeatures.addFerns(generationSettings);
        BiomeDefaultFeatures.addDefaultFlowers(generationSettings);
        BiomeDefaultFeatures.addTaigaGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);
        BiomeDefaultFeatures.addRareBerryBushes(generationSettings);

        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.BLUE_SPRUCE_TREES);
        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.HOLLY_TREES);
        BWGOverworldDefaultFeatures.addBlueBerryBush(generationSettings);
        BWGOverworldDefaultFeatures.addLushBlueBerryBush(generationSettings);
        BWGOverworldDefaultFeatures.addWinterSucculent(generationSettings);
        BWGOverworldDefaultFeatures.addWinterCyclamen(generationSettings);
        BWGOverworldDefaultFeatures.addWinterScilla(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnSettings);
        addSpawn(spawnSettings, EntityType.ALLAY, 1, 1, 1);
        addSpawn(spawnSettings, EntityType.RABBIT, 10, 2, 3);
        addSpawn(spawnSettings, EntityType.FOX, 8, 2, 4);
        addSpawn(spawnSettings, EntityType.WOLF, 8, 3, 4);
        addSpawn(spawnSettings, EntityType.STRAY, 85, 2, 3);
        addSpawn(spawnSettings, EntityType.POLAR_BEAR, 1, 1, 2);

        float temperature = -0.5F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.3F).specialEffects((new BiomeSpecialEffects.Builder()).backgroundMusic(Musics.createGameMusic(BWGSounds.MUSIC_BIOME_ERODED_BOREALIS.get())).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientParticle(new AmbientParticleSettings(BWGParticles.BOREALIS_GLINT.get(), 0.00200F)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome skyrisVale(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);

        BiomeDefaultFeatures.addExtraEmeralds(generationSettings);
        BiomeDefaultFeatures.addSavannaExtraGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);

        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.SKYRIS_TREES);
        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.SPARSE_BLUE_SPRUCE_TREES);
        BWGOverworldDefaultFeatures.addMossyStoneBoulder(generationSettings);
        BWGOverworldDefaultFeatures.addRockyStoneBoulder(generationSettings);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.FOXGLOVES);
        BWGOverworldDefaultFeatures.addCrocus(generationSettings);
        BWGOverworldDefaultFeatures.addFairySlipper(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        addSpawn(spawnSettings, EntityType.RABBIT, 4, 2, 3);
        addSpawn(spawnSettings, EntityType.FOX, 8, 2, 4);
        addSpawn(spawnSettings, EntityType.BAT, 10, 8, 8);
        addSpawn(spawnSettings, EntityType.SPIDER, 100, 4, 4);
        addSpawn(spawnSettings, EntityType.SKELETON, 100, 4, 4);
        addSpawn(spawnSettings, EntityType.SLIME, 100, 4, 4);
        addSpawn(spawnSettings, EntityType.ENDERMAN, 10, 1, 4);
        addSpawn(spawnSettings, EntityType.WITCH, 5, 1, 1);

        float temperature = 0.25F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.8F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(6409356).foliageColorOverride(7135854).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome howlingPeaks(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);

        BiomeDefaultFeatures.addExtraEmeralds(generationSettings);
        BiomeDefaultFeatures.addGiantTaigaVegetation(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);
        addRawGeneration(generationSettings, BWGPlacedFeatures.BOULDER);

        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.ORANGE_BIRCH_TREES);
        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.YELLOW_BIRCH_TREES);
        BWGOverworldDefaultFeatures.addSparseSpruceTrees(generationSettings);
        BWGOverworldDefaultFeatures.addBlueBerryBush(generationSettings);
        BWGOverworldDefaultFeatures.addLushBlueBerryBush(generationSettings);
        BWGOverworldDefaultFeatures.addAnemones(generationSettings);
        BWGOverworldDefaultFeatures.addCrocus(generationSettings);
        BWGOverworldDefaultFeatures.addRockyStoneBoulder(generationSettings);
        BWGOverworldDefaultFeatures.addWhitePuffball(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        BiomeDefaultFeatures.commonSpawns(spawnSettings);
        addSpawn(spawnSettings, EntityType.WOLF, 8, 4, 4);
        addSpawn(spawnSettings, EntityType.RABBIT, 10, 2, 3);
        addSpawn(spawnSettings, EntityType.FOX, 8, 2, 4);
        addSpawn(spawnSettings, EntityType.STRAY, 80, 4, 4);

        float temperature = -0.5F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(1.0F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(6200521).waterFogColor(6200521).grassColorOverride(15834405).foliageColorOverride(15834405).fogColor(12638463).skyColor(12700876).ambientParticle(new AmbientParticleSettings(ParticleTypes.WHITE_ASH, 0.01428F)).ambientLoopSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome fragmentJungle(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);


        BiomeDefaultFeatures.addExtraEmeralds(generationSettings);
        BiomeDefaultFeatures.addLightBambooVegetation(generationSettings);
        BiomeDefaultFeatures.addSparseJungleTrees(generationSettings);
        BiomeDefaultFeatures.addJungleGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);
        BiomeDefaultFeatures.addSparseJungleMelons(generationSettings);

        addRawGeneration(generationSettings, BWGPlacedFeatures.STONE_PILLAR);
        addRawGeneration(generationSettings, BWGOverworldVegationPlacedFeatures.PATCH_GRASS_JUNGLE_WORLD_SURFACE);

        BWGOverworldDefaultFeatures.addOakBushes(generationSettings);
        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.FRAGMENT_JUNGLE_TREES);
        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.RAINFOREST_TREES);
        BWGOverworldDefaultFeatures.addBWGTropicFlowers(generationSettings);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.JUNGLE_FLOWERS_PILLAR);

        BWGOverworldDefaultFeatures.addMossyStoneBoulder(generationSettings);
        BWGOverworldDefaultFeatures.addRockyStoneBoulder(generationSettings);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.DELPHINIUM);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.DELPHINIUM_PILLAR);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        BiomeDefaultFeatures.monsters(spawnSettings, 95, 5, 100, false);
        addSpawn(spawnSettings, EntityType.PARROT, 40, 1, 2);
        addSpawn(spawnSettings, EntityType.CHICKEN, 10, 4, 4);
        addSpawn(spawnSettings, EntityType.BAT, 10, 8, 8);
        addSpawn(spawnSettings, EntityType.OCELOT, 2, 1, 1);

        float temperature = 0.25F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.8F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(10145074).foliageColorOverride(10145074).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome tropicalRainforest(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);
        BWGOverworldDefaultFeatures.addHugeMushrooms(generationSettings);

        BiomeDefaultFeatures.addLightBambooVegetation(generationSettings);
        BiomeDefaultFeatures.addSparseJungleTrees(generationSettings);
        BiomeDefaultFeatures.addWarmFlowers(generationSettings);
        BiomeDefaultFeatures.addJungleGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);
        BiomeDefaultFeatures.addSparseJungleMelons(generationSettings);

        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.RAINFOREST_TREES);
        BWGOverworldDefaultFeatures.addBWGTropicFlowers(generationSettings);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.DELPHINIUM);
//        BYGDefaultBiomeFeatures.addBeeHive(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.baseJungleSpawns(spawnSettings);
        addSpawn(spawnSettings, EntityType.PARROT, 40, 1, 2);
        addSpawn(spawnSettings, EntityType.BAT, 10, 8, 8);
        addSpawn(spawnSettings, EntityType.OCELOT, 2, 1, 1);

        float temperature = 0.95F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.9F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(10145074).foliageColorOverride(10145074).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome tropicalIsland(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGeneration(placedFeatureGetter, carverGetter);

        BiomeDefaultFeatures.addLightBambooVegetation(generationSettings);
        BiomeDefaultFeatures.addSparseJungleTrees(generationSettings);
        BiomeDefaultFeatures.addJungleGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);
        BiomeDefaultFeatures.addSparseJungleMelons(generationSettings);

        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.SPARSE_ENCHANTED_TREES);
        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.RAINFOREST_TREES);
        BWGOverworldDefaultFeatures.addBWGTropicFlowers(generationSettings);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.DELPHINIUM);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.CYAN_PITCHER_PLANT);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.MAGENTA_PITCHER_PLANT);
//        BYGDefaultBiomeFeatures.addBeeHive(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.baseJungleSpawns(spawnSettings);
        addSpawn(spawnSettings, EntityType.PARROT, 40, 1, 2);
        addSpawn(spawnSettings, EntityType.BAT, 10, 8, 8);
        addSpawn(spawnSettings, EntityType.OCELOT, 2, 1, 1);

        float temperature = 0.95F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.9F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(10145074).foliageColorOverride(10145074).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome weepingWitchForest(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGenerationWithoutLava(placedFeatureGetter, carverGetter);
        BWGOverworldDefaultFeatures.addHugeMushrooms(generationSettings);

        BiomeDefaultFeatures.addForestGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);

        BWGOverworldDefaultFeatures.addSparseSpruceTrees(generationSettings);
        BWGOverworldDefaultFeatures.addSparseAspenTreesShrubs(generationSettings);
        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.HAZEL_TREES);
        BWGOverworldDefaultFeatures.addBlueBerryBush(generationSettings);
        BWGOverworldDefaultFeatures.addLushBlueBerryBush(generationSettings);
        BWGOverworldDefaultFeatures.addAnemones(generationSettings);
        BWGOverworldDefaultFeatures.addCrocus(generationSettings);
        BWGOverworldDefaultFeatures.addWhitePuffball(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        BiomeDefaultFeatures.commonSpawns(spawnSettings);
        addSpawn(spawnSettings, EntityType.WOLF, 8, 4, 4);
        addSpawn(spawnSettings, EntityType.RABBIT, 4, 2, 3);
        addSpawn(spawnSettings, EntityType.FOX, 8, 2, 4);
        addSpawn(spawnSettings, EntityType.ALLAY, 1, 1, 2);
        addSpawn(spawnSettings, EntityType.WITCH, 6, 1, 1);
        addSpawn(spawnSettings, EntityType.CAVE_SPIDER, 50, 2, 4);

        float temperature = 0.25F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.8F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(12435265).foliageColorOverride(12435265).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome forgottenForest(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGenerationWithoutLava(placedFeatureGetter, carverGetter);
        BWGOverworldDefaultFeatures.addHugeMushrooms(generationSettings);

        BiomeDefaultFeatures.addJungleGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);

        BWGOverworldDefaultFeatures.addOakBushes(generationSettings);
        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.FORGOTTEN_FOREST_TREES);
        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.FLORUS_TREES);
        BWGOverworldDefaultFeatures.addAnemones(generationSettings);
        BWGOverworldDefaultFeatures.addRose(generationSettings);
        BWGOverworldDefaultFeatures.addBlueRoseBush(generationSettings);
        BWGOverworldDefaultFeatures.addFairySlipper(generationSettings);
        BWGOverworldDefaultFeatures.addWhitePuffball(generationSettings);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.ANGELICA);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.PINK_DAFFODIL);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.HORSEWEED);
        //BYGDefaultBiomeFeatures.addBeeHive(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        addSpawn(spawnSettings, EntityType.ALLAY, 1, 1, 1);
        addSpawn(spawnSettings, EntityType.RABBIT, 4, 2, 3);
        addSpawn(spawnSettings, EntityType.FOX, 8, 2, 4);
        addSpawn(spawnSettings, EntityType.CAVE_SPIDER, 90, 2, 3);

        float temperature = 0.8F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.8F).specialEffects((new BiomeSpecialEffects.Builder()).backgroundMusic(Musics.createGameMusic(BWGSounds.MUSIC_BIOME_FORGOTTEN_FOREST.get())).waterColor(4159204).waterFogColor(329011).grassColorOverride(8034667).foliageColorOverride(6530362).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientParticle(new AmbientParticleSettings(BWGParticles.FIREFLY.get(), 0.00190F)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static Biome zelkovaForest(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder generationSettings = setupDefaultOverworldGenerationWithoutLava(placedFeatureGetter, carverGetter);

        BiomeDefaultFeatures.addFerns(generationSettings);

        BiomeDefaultFeatures.addDefaultFlowers(generationSettings);
        BiomeDefaultFeatures.addTaigaGrass(generationSettings);
        BiomeDefaultFeatures.addSavannaExtraGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);

        addVegetal(generationSettings, BWGOverworldTreePlacedFeatures.ZELKOVA_TREES);
        BWGOverworldDefaultFeatures.addBlueBerryBush(generationSettings);
        BWGOverworldDefaultFeatures.addLushBlueBerryBush(generationSettings);
        addVegetal(generationSettings, BWGOverworldVegationPlacedFeatures.KOVAN_FLOWER);
        BWGOverworldDefaultFeatures.addAnemones(generationSettings);
        BWGOverworldDefaultFeatures.addCrocus(generationSettings);
        BWGOverworldDefaultFeatures.addBWGMushrooms(generationSettings);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        BiomeDefaultFeatures.monsters(spawnSettings, 95, 5, 100, false);
        addSpawn(spawnSettings, EntityType.WOLF, 5, 4, 4);
        addSpawn(spawnSettings, EntityType.BAT, 10, 8, 8);

        float temperature = 0.25F;
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.8F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).grassColorOverride(5416309).foliageColorOverride(5416309).fogColor(12638463).skyColor(OverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    private static void addVegetal(BiomeGenerationSettings.Builder builder, ResourceKey<PlacedFeature> feature) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, feature);
    }

    private static void addRawGeneration(BiomeGenerationSettings.Builder builder, ResourceKey<PlacedFeature> feature) {
        builder.addFeature(GenerationStep.Decoration.RAW_GENERATION, feature);
    }
}
