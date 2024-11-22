package net.potionstudios.biomeswevegone.world.level.block;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.potionstudios.biomeswevegone.world.item.BWGItems;
import net.potionstudios.biomeswevegone.world.level.block.plants.bush.BWGPlacementBushBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.bush.DesertPlantBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.cactus.BWGCactusBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.vegetation.AloeVeraBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.vegetation.BWGDoublePlantBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.vegetation.FlatVegetationBlock;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWood;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWoodSet;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.function.BiConsumer;

/**
 * Block Features that need to be added separately from registration
 * @author Joseph T. McQuigg
 */
public class BlockFeatures {

    public static void registerCompostables(BiConsumer<ItemLike, Float> consumer) {
        BWGBlocks.BLOCKS.forEach(object -> {
            Block block = object.get();
            if (block instanceof TallGrassBlock || block instanceof BWGDoublePlantBlock)
                consumer.accept(block, 0.3F);
            else if (block instanceof VineBlock || block instanceof DesertPlantBlock || block instanceof BWGCactusBlock)
                consumer.accept(block, 0.5F);
            else if (block instanceof FlowerBlock || block instanceof TallFlowerBlock || block instanceof WaterlilyBlock || block instanceof MushroomBlock || block instanceof AloeVeraBlock || block instanceof FlatVegetationBlock || block instanceof BWGPlacementBushBlock || block instanceof PumpkinBlock || block instanceof CarvedPumpkinBlock)
                consumer.accept(block, 0.65F);
        });

        BWGWood.WOOD.forEach(entry -> {
            if (entry.get() instanceof LeavesBlock || entry.get() instanceof SaplingBlock)
                consumer.accept(entry.get(), 0.3F);
        });

        BWGItems.ITEMS.stream().filter(item -> item.get().components().has(DataComponents.FOOD)).forEach(item -> consumer.accept(item.get(), 0.85F));
        consumer.accept(BWGItems.PALE_PUMPKIN_SEEDS.get(), 0.3F);
    }

    /**
     * Register a block as flammable.
     **/
    public static void registerFlammable(TriConsumer<Block, Integer, Integer> consumer) {
        BWGWoodSet.woodsets().forEach(set -> {
            consumer.accept(set.planks(), 5, 20);
            consumer.accept(set.slab(), 5, 20);
            consumer.accept(set.stairs(), 5, 20);
            consumer.accept(set.fence(), 5, 20);
            consumer.accept(set.fenceGate(), 5, 20);
            consumer.accept(set.logstem(), 5, 5);
            consumer.accept(set.strippedLogStem(), 5, 5);
            consumer.accept(set.wood(), 5, 5);
            consumer.accept(set.strippedWood(), 5, 5);
            consumer.accept(set.bookshelf(), 30, 20);
        });
        BWGWood.WOOD.forEach(block -> {
            if (block.get() instanceof LeavesBlock)
                consumer.accept(block.get(), 30, 60);
        });
        consumer.accept(BWGWood.PALO_VERDE_LOG.get(), 5, 5);
        consumer.accept(BWGWood.STRIPPED_PALO_VERDE_LOG.get(), 5, 5);
        consumer.accept(BWGWood.PALO_VERDE_WOOD.get(), 5, 5);
        consumer.accept(BWGWood.STRIPPED_PALO_VERDE_WOOD.get(), 5, 5);
        consumer.accept(BWGWood.IMBUED_BLUE_ENCHANTED_WOOD.get(), 5, 5);
        consumer.accept(BWGWood.IMBUED_GREEN_ENCHANTED_WOOD.get(), 5, 5);
        BWGBlocks.BLOCKS.forEach(entry -> {
            Block block = entry.get();
            if (block instanceof LeavesBlock)
                consumer.accept(block, 30, 60);
            else if (block instanceof CarpetBlock)
                consumer.accept(block, 60, 20);
            else if (block instanceof SweetBerryBushBlock || block instanceof FlowerBlock || block instanceof TallFlowerBlock || block instanceof TallGrassBlock)
                consumer.accept(block, 60, 100);
        });
        consumer.accept(BWGBlocks.POISON_IVY.get(), 15, 100);
    }
}
