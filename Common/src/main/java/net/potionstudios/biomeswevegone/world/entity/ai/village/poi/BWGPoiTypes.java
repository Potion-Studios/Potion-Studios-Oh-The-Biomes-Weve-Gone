package net.potionstudios.biomeswevegone.world.entity.ai.village.poi;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.PlatformHandler;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;

import java.util.function.Supplier;

public class BWGPoiTypes {

    public static final ResourceKey<PoiType> FORAGER = register("forager", BWGBlocks.FORAGERS_TABLE, 1, 1);

    private static ResourceKey<PoiType> register(String id, Supplier<Block> block, int maxTickets, int validRange) {
        PlatformHandler.PLATFORM_HANDLER.registerPOIType(id, block, maxTickets, validRange);
        return BiomesWeveGone.key(Registries.POINT_OF_INTEREST_TYPE, id);
    }

    public static void poiTypes() {
        BiomesWeveGone.LOGGER.info("Registering Oh The Biomes We've Gone Poi Types");
    }
}
