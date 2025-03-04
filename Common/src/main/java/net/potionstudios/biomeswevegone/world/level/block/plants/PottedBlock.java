package net.potionstudios.biomeswevegone.world.level.block.plants;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.potionstudios.biomeswevegone.PlatformHandler;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class PottedBlock {
    private final Supplier<? extends Block> block;
    private final Supplier<? extends Block> pottedBlock;

    public PottedBlock(@NotNull Supplier<? extends Block> block, @NotNull Supplier<? extends Block> pottedBlock) {
        this.block = block;
        this.pottedBlock = pottedBlock;
    }

    public PottedBlock(String id, @NotNull Supplier<? extends Block> block) {
        this(block, BWGBlocks.registerBlock("potted_" + id, properties -> PlatformHandler.PLATFORM_HANDLER.createPottedBlock(block, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    }

    public Supplier<? extends Block> getBlockSupplier() {
        return block;
    }

    public Supplier<? extends Block> getPottedBlockSupplier() {
        return pottedBlock;
    }

    public Block getBlock() {
        return block.get();
    }

    public Item getItem() {
        return block.get().asItem();
    }

    public Block getPottedBlock() {
        return pottedBlock.get();
    }

    public BlockState getBlockState() {
        return block.get().defaultBlockState();
    }
}
