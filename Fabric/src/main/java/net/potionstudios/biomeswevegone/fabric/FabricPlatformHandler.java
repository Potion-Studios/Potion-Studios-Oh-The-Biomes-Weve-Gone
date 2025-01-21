package net.potionstudios.biomeswevegone.fabric;

import com.google.auto.service.AutoService;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.PlatformHandler;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Supplier;

@AutoService(PlatformHandler.class)
public final class FabricPlatformHandler implements PlatformHandler {
	@Override
	public Platform getPlatform() {
		return Platform.FABRIC;
	}

	@Override
	public Path configPath() {
		return FabricLoader.getInstance().getConfigDir().resolve(BiomesWeveGone.MOD_ID);
	}

	@Override
	public <E extends Entity> Supplier<EntityType<E>> registerEntity(String id, EntityType.EntityFactory<E> factory, MobCategory category, float width, float height) {
		EntityType<E> entity = FabricEntityTypeBuilder.create(category, factory).dimensions(EntityDimensions.scalable(width, height)).build();
		Registry.register(BuiltInRegistries.ENTITY_TYPE, BiomesWeveGone.id(id), entity);
		return () -> entity;
	}

	@Override
	public <E extends Entity> Supplier<EntityType<E>> registerEntity(String id, EntityType.EntityFactory<E> factory, MobCategory category, float width, float height, int trackingRange) {
		EntityType<E> entity = FabricEntityTypeBuilder.create(category, factory).dimensions(EntityDimensions.scalable(width, height)).trackRangeChunks(trackingRange).build();
		Registry.register(BuiltInRegistries.ENTITY_TYPE, BiomesWeveGone.id(id), entity);
		return () -> entity;
	}

	@Override
	public <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntity(String key, Supplier<BlockEntityType.Builder<T>> builder) {
		ResourceLocation resourceLocation = BiomesWeveGone.id(key);
		BlockEntityType<T> blockEntity = builder.get().build(Util.fetchChoiceType(References.BLOCK_ENTITY, resourceLocation.toString()));
		Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, resourceLocation, blockEntity);
		return () -> blockEntity;
	}

	@Override
	public Supplier<PoiType> registerPOIType(String id, Supplier<Block> block, int maxTickets, int validRange) {
		PoiType poi = PointOfInterestHelper.register(BiomesWeveGone.id(id), maxTickets, validRange, block.get());
		return () -> poi;
	}

	@Override
	public WoodType createWoodType(String id, @NotNull BlockSetType setType) {
		return new WoodTypeBuilder().register(BiomesWeveGone.id(id), setType);
	}

	@Override
	public Supplier<SimpleParticleType> registerCreateParticle(String name) {
		SimpleParticleType particleMaker = Registry.register(BuiltInRegistries.PARTICLE_TYPE, BiomesWeveGone.id(name), FabricParticleTypes.simple());
		return () -> particleMaker;
	}

	@SafeVarargs
	@Override
	public final Supplier<CreativeModeTab> createCreativeTab(String name, Supplier<ItemStack> icon, ArrayList<Supplier<? extends Item>>... items) {
		CreativeModeTab tab = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, BiomesWeveGone.id(name), FabricItemGroup.builder()
				.title(Component.translatable("itemGroup." + BiomesWeveGone.MOD_ID + "." + name))
				.icon(icon)
				.displayItems((entry, context) -> {
					for (ArrayList<Supplier<? extends Item>> item : items)
						item.forEach((item1) -> context.accept(item1.get()));
				})
				.build());
		return () -> tab;
	}

	@Override
	public <T> Supplier<T> register(Registry<? super T> registry, String name, Supplier<T> value) {
		T value1 = Registry.register(registry, BiomesWeveGone.id(name), value.get());
		return () -> value1;
	}

	@Override
	public <T> Supplier<Holder.Reference<T>> registerForHolder(Registry<T> registry, String name, Supplier<T> value) {
		Holder.Reference<T> reference = Registry.registerForHolder(registry, BiomesWeveGone.id(name), value.get());
		return () -> reference;
	}
}
