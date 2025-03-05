package net.potionstudios.biomeswevegone.world.level.block.set;

import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;

import java.util.ArrayList;
import java.util.function.Supplier;

public class BWGBlockSet {

    private static final ArrayList<BWGBlockSet> blockSets = new ArrayList<>();

    private final Supplier<Block> base;
    private final Supplier<SlabBlock> slab;
    private final Supplier<StairBlock> stairs;
    private final Supplier<WallBlock> wall;

    public BWGBlockSet(String name, BlockBehaviour.Properties properties) {
        this.base = BWGBlocks.registerBlockItem(name, Block::new, properties);
        this.slab = BWGBlocks.registerBlockItem(name + "_slab", SlabBlock::new, properties);
        this.stairs = BWGBlocks.registerBlockItem(name + "_stairs", properties1 -> new StairBlock(base.get().defaultBlockState(), properties1), properties);
        this.wall = BWGBlocks.registerBlockItem(name + "_wall", WallBlock::new, properties);
        blockSets.add(this);
    }

    public BWGBlockSet(String name, String alt, BlockBehaviour.Properties properties) {
        this.base = BWGBlocks.registerBlockItem(name, Block::new, properties);
        this.slab = BWGBlocks.registerBlockItem(alt + "_slab", SlabBlock::new, properties);
        this.stairs = BWGBlocks.registerBlockItem(alt + "_stairs", properties1 -> new StairBlock(base.get().defaultBlockState(), properties1), properties);
        this.wall = BWGBlocks.registerBlockItem(alt + "_wall", WallBlock::new, properties);
        blockSets.add(this);
    }

    public BWGBlockSet(Supplier<Block> base, Supplier<SlabBlock> slab, Supplier<StairBlock> stairs, Supplier<WallBlock> wall) {
        this.base = base;
        this.slab = slab;
        this.stairs = stairs;
        this.wall = wall;
        blockSets.add(this);
    }

    public BWGBlockSet(String name, MapColor color) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).mapColor(color);
        this.base = BWGBlocks.registerBlockItem(name, Block::new, properties);
        this.slab = BWGBlocks.registerBlockItem(name + "_slab", SlabBlock::new, properties);
        this.stairs = BWGBlocks.registerBlockItem(name + "_stairs", properties1 -> new StairBlock(base.get().defaultBlockState(), properties1), properties);
        this.wall = BWGBlocks.registerBlockItem(name + "_wall", WallBlock::new, properties);
        blockSets.add(this);
    }

    public BWGBlockSet(String name, String alt, MapColor color) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).mapColor(color);
        this.base = BWGBlocks.registerBasicBlockWithItem(name, properties);
        this.slab = BWGBlocks.registerBlockItem(alt + "_slab", SlabBlock::new, properties);
        this.stairs = BWGBlocks.registerBlockItem(alt + "_stairs", properties1 -> new StairBlock(base.get().defaultBlockState(), properties1), properties);
        this.wall = BWGBlocks.registerBlockItem(alt + "_wall", WallBlock::new, properties);
        blockSets.add(this);
    }

    public Block getBase() {
        return base.get();
    }

    public SlabBlock getSlab() {
        return slab.get();
    }

    public StairBlock getStairs() {
        return stairs.get();
    }

    public WallBlock getWall() {
        return wall.get();
    }

    private BlockFamily family = null;

    public BlockFamily getBlockFamily() {
        if (family == null)
            family = BlockFamilies.familyBuilder(getBase()).slab(getSlab()).stairs(getStairs()).wall(getWall()).getFamily();
        return family;
    }

    public static ArrayList<BWGBlockSet> getBlockSets() {
        return blockSets;
    }
}
