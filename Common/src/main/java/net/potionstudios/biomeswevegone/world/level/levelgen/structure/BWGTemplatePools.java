package net.potionstudios.biomeswevegone.world.level.levelgen.structure;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.processor.BWGStructureProcessorLists;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class BWGTemplatePools {

	public static final Map<ResourceKey<StructureTemplatePool>, TemplatePoolFactory> TEMPLATE_POOL_FACTORIES = new Reference2ObjectOpenHashMap<>();

	public static final ResourceKey<StructureTemplatePool> PRAIRIE_HOUSE = register("prairie_house", templatePoolFactoryContext ->
			createTemplatePool(getEmptyPool(templatePoolFactoryContext), ImmutableList.of(Pair.of(StructurePoolElement.single(BWGStructures.PRAIRIE_HOUSE.location().toString(), getProcessor(templatePoolFactoryContext, BWGStructureProcessorLists.PRAIRIE_HOUSE)), 1)), StructureTemplatePool.Projection.RIGID));

	public static final ResourceKey<StructureTemplatePool> ABANDONED_PRAIRIE_HOUSE = register("abandoned_prairie_house", templatePoolFactoryContext ->
			createTemplatePool(getEmptyPool(templatePoolFactoryContext), ImmutableList.of(Pair.of(StructurePoolElement.single(BWGStructures.ABANDONED_PRAIRIE_HOUSE.location().toString(), getProcessor(templatePoolFactoryContext, BWGStructureProcessorLists.ABANDONED_PRAIRIE_HOUSE)), 1)), StructureTemplatePool.Projection.RIGID));

	public static final ResourceKey<StructureTemplatePool> RUGGED_FOSSIL = register("rugged_fossil", templatePoolFactoryContext ->
			createTemplatePool(getEmptyPool(templatePoolFactoryContext),
					ImmutableList.of(
							Pair.of(StructurePoolElement.single(BiomesWeveGone.id("fossils/rugged_1").toString(), getEmptyProcessor(templatePoolFactoryContext)), 1),
							Pair.of(StructurePoolElement.single(BiomesWeveGone.id("fossils/rugged_2").toString(), getEmptyProcessor(templatePoolFactoryContext)), 1),
							Pair.of(StructurePoolElement.single(BiomesWeveGone.id("fossils/rugged_3").toString(), getEmptyProcessor(templatePoolFactoryContext)), 1),
							Pair.of(StructurePoolElement.single(BiomesWeveGone.id("fossils/rugged_4").toString(), getEmptyProcessor(templatePoolFactoryContext)), 1),
							Pair.of(StructurePoolElement.single(BiomesWeveGone.id("fossils/rugged_5").toString(), getEmptyProcessor(templatePoolFactoryContext)), 1),
							Pair.of(StructurePoolElement.single(BiomesWeveGone.id("fossils/rugged_6").toString(), getEmptyProcessor(templatePoolFactoryContext)), 1))
					, StructureTemplatePool.Projection.TERRAIN_MATCHING));

	public static final ResourceKey<StructureTemplatePool> ASPEN_MANOR_1 = register("aspen_manor_1", templatePoolFactoryContext ->
			createTemplatePool(getEmptyPool(templatePoolFactoryContext), ImmutableList.of(Pair.of(StructurePoolElement.single(BWGStructures.ASPEN_MANOR_1.location().toString(), getProcessor(templatePoolFactoryContext, BWGStructureProcessorLists.ASPEN_MANOR)), 1)), StructureTemplatePool.Projection.RIGID));

	public static final ResourceKey<StructureTemplatePool> ASPEN_MANOR_2 = register("aspen_manor_2", templatePoolFactoryContext ->
			createTemplatePool(getEmptyPool(templatePoolFactoryContext), ImmutableList.of(Pair.of(StructurePoolElement.single(BWGStructures.ASPEN_MANOR_2.location().toString(), getProcessor(templatePoolFactoryContext, BWGStructureProcessorLists.ASPEN_MANOR)), 1)), StructureTemplatePool.Projection.RIGID));

	public static final ResourceKey<StructureTemplatePool> BOG_TRIAL = register("bog_trial", templatePoolFactoryContext ->
			createTemplatePool(getEmptyPool(templatePoolFactoryContext), ImmutableList.of(Pair.of(StructurePoolElement.single(BWGStructures.BOG_TRIAL.location().toString(), getProcessor(templatePoolFactoryContext, BWGStructureProcessorLists.BOG_TRIAL)), 1)), StructureTemplatePool.Projection.RIGID));

	private static final ResourceKey<StructureTemplatePool> BABY_VILLAGER = register("baby_villager", templatePoolFactoryContext ->
			createTemplatePool(getEmptyPool(templatePoolFactoryContext), ImmutableList.of(Pair.of(StructurePoolElement.single(ResourceLocation.withDefaultNamespace("village/plains/villagers/baby").toString()), 1)), StructureTemplatePool.Projection.RIGID));

	private static StructureTemplatePool createTemplatePool(Holder<StructureTemplatePool> fallback, List<Pair<Function<StructureTemplatePool.Projection, ? extends StructurePoolElement>, Integer>> rawTemplateFactories, StructureTemplatePool.Projection projection) {
		return new StructureTemplatePool(fallback, rawTemplateFactories, projection);
	}

	private static Holder.Reference<StructureTemplatePool> getEmptyPool(BootstrapContext<StructureTemplatePool> context) {
		return getPool(context, Pools.EMPTY);
	}

	private static Holder.Reference<StructureTemplatePool> getPool(BootstrapContext<StructureTemplatePool> context, ResourceKey<StructureTemplatePool> poolResourceKey) {
		return context.lookup(Registries.TEMPLATE_POOL).getOrThrow(poolResourceKey);
	}

	private static Holder.Reference<StructureProcessorList> getEmptyProcessor(BootstrapContext<StructureTemplatePool> context) {
		return getProcessor(context, ProcessorLists.EMPTY);
	}

	private static Holder.Reference<StructureProcessorList> getProcessor(BootstrapContext<StructureTemplatePool> context, ResourceKey<StructureProcessorList> processorList) {
		return context.lookup(Registries.PROCESSOR_LIST).getOrThrow(processorList);
	}

	private static ResourceKey<StructureTemplatePool> register(String id, TemplatePoolFactory factory) {
		ResourceKey<StructureTemplatePool> templatePoolResourceKey = ResourceKey.create(Registries.TEMPLATE_POOL, BiomesWeveGone.id(id));
		TEMPLATE_POOL_FACTORIES.put(templatePoolResourceKey, factory);
		return templatePoolResourceKey;
	}

	public static void templatePools() {
		BiomesWeveGone.LOGGER.info("Registering Oh The Biomes We've Gone Template Pools");
		BWGVillageTemplatePools.villageTemplatePools();
	}

	@FunctionalInterface
	public interface TemplatePoolFactory {
		StructureTemplatePool generate(BootstrapContext<StructureTemplatePool> templatePoolFactoryContext);
	}
}
