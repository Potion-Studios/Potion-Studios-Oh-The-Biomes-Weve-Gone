package net.potionstudios.biomeswevegone.world.level.block.entities;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.PlatformHandler;
import net.potionstudios.biomeswevegone.world.level.block.entities.sign.BWGHangingSignBlockEntity;
import net.potionstudios.biomeswevegone.world.level.block.entities.sign.BWGSignBlockEntity;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWoodSet;

import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BWGBlockEntities {
    public static final Supplier<BlockEntityType<BWGSignBlockEntity>> SIGNS = register("sign", () -> new BlockEntityType<>(
            BWGSignBlockEntity::new,
            Stream.concat(
                    BWGWoodSet.woodsets().stream().map(BWGWoodSet::sign),
                    BWGWoodSet.woodsets().stream().map(BWGWoodSet::wallSign)
            ).collect(Collectors.toSet())));

    public static final Supplier<BlockEntityType<BWGHangingSignBlockEntity>> HANGING_SIGNS = register("hanging_sign", () -> new BlockEntityType<>(
       BWGHangingSignBlockEntity::new,
       Stream.concat(
               BWGWoodSet.woodsets().stream().map(BWGWoodSet::hangingSign),
               BWGWoodSet.woodsets().stream().map(BWGWoodSet::wallHangingSign)
       ).collect(Collectors.toSet())));

    private static <T extends BlockEntity> Supplier<BlockEntityType<T>> register(String key, Supplier<BlockEntityType<T>> blockEntity) {
        return PlatformHandler.PLATFORM_HANDLER.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, key, blockEntity);
    }

    public static void blockEntities() {
        BiomesWeveGone.LOGGER.info("Registering Oh The Biomes We've Gone Block Entities");
    }
}
