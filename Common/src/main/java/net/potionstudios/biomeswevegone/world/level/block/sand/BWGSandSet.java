package net.potionstudios.biomeswevegone.world.level.block.sand;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;

import java.util.ArrayList;
import java.util.function.Supplier;

/**
 * Sand and Sandstone blocks for Oh The Biomes We've Gone.
 * @author Joseph T. McQuigg
 **/
public class BWGSandSet {

    private static final ArrayList<BWGSandSet> sandSets = new ArrayList<>();
    private final String name;
    private final Supplier<ColoredFallingBlock> sand;
    private final Supplier<Block> sandstone;
    private final Supplier<SlabBlock> sandstoneSlab;
    private final Supplier<StairBlock> sandstoneStairs;
    private final Supplier<WallBlock> sandstoneWall;
    private final Supplier<Block> chiseledSandstone;
    private final Supplier<Block> smoothSandstone;
    private final Supplier<SlabBlock> smoothSandstoneSlab;
    private final Supplier<StairBlock> smoothSandstoneStairs;
    private final Supplier<Block> cutSandstone;
    private final Supplier<SlabBlock> cutSandstoneSlab;
    private final TagKey<Block> sandstoneBlocksTag;
    private final TagKey<Item> sandstoneBlocksItemTag;
    private final TagKey<Block> sandBlockTag;
    private final TagKey<Item> sandItemTag;
    private final TagKey<Block> sandstoneSlabsTag;
    private final TagKey<Item> sandstoneSlabsItemTag;
    private final TagKey<Block> sandstoneStairsTag;
    private final TagKey<Item> sandstoneStairsItemTag;


    public BWGSandSet(String name, int dustColor) {
        this.name = name;
        this.sand = BWGBlocks.registerCubeAllBlockItem(name + "_sand", () -> new ColoredFallingBlock(new ColorRGBA(dustColor), BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).setId(key(name + "_sand"))));
        this.sandstone = BWGBlocks.registerBlockItem(name + "_sandstone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE).setId(key(name + "_sandstone"))));
        this.sandstoneSlab = BWGBlocks.registerBlockItem(name + "_sandstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE_SLAB).setId(key(name + "_sandstone_slab"))));
        this.sandstoneStairs = BWGBlocks.registerBlockItem(name + "_sandstone_stairs", () -> new StairBlock(sandstone.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE_STAIRS).setId(key(name + "_sandstone_stairs"))));
        this.sandstoneWall = BWGBlocks.registerBlockItem(name + "_sandstone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE_WALL).setId(key(name + "_sandstone_wall"))));
        this.chiseledSandstone = BWGBlocks.registerBlockItem("chiseled_" + name + "_sandstone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_SANDSTONE).setId(key("chiseled_" + name + "_sandstone"))));
        this.smoothSandstone = BWGBlocks.registerBlockItem("smooth_" + name + "_sandstone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_SANDSTONE).setId(key("smooth_" + name + "_sandstone"))));
        this.smoothSandstoneSlab = BWGBlocks.registerBlockItem("smooth_" + name + "_sandstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_SANDSTONE_SLAB).setId(key("smooth_" + name + "_sandstone_slab"))));
        this.smoothSandstoneStairs = BWGBlocks.registerBlockItem("smooth_" + name + "_sandstone_stairs", () -> new StairBlock(smoothSandstone.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_SANDSTONE_STAIRS).setId(key("smooth_" + name + "_sandstone_stairs"))));
        this.cutSandstone = BWGBlocks.registerBlockItem("cut_" + name + "_sandstone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_SANDSTONE).setId(key("cut_" + name + "_sandstone"))));
        this.cutSandstoneSlab = BWGBlocks.registerBlockItem("cut_" + name + "_sandstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_SANDSTONE_SLAB).setId(key("cut_" + name + "_sandstone_slab"))));
        this.sandstoneBlocksTag = TagKey.create(Registries.BLOCK, BiomesWeveGone.id(name + "_sandstone_blocks"));
        this.sandstoneBlocksItemTag = TagKey.create(Registries.ITEM, BiomesWeveGone.id(name + "_sandstone_blocks"));
        this.sandBlockTag = TagKey.create(Registries.BLOCK, BiomesWeveGone.id("sand/" + name));
        this.sandItemTag = TagKey.create(Registries.ITEM, BiomesWeveGone.id("sand/" + name));
        this.sandstoneSlabsTag = TagKey.create(Registries.BLOCK, BiomesWeveGone.id(name + "_sandstone_slabs"));
        this.sandstoneSlabsItemTag = TagKey.create(Registries.ITEM, BiomesWeveGone.id(name + "_sandstone_slabs"));
        this.sandstoneStairsTag = TagKey.create(Registries.BLOCK, BiomesWeveGone.id(name + "_sandstone_stairs"));
        this.sandstoneStairsItemTag = TagKey.create(Registries.ITEM, BiomesWeveGone.id(name + "_sandstone_stairs"));
        sandSets.add(this);
    }

    public String getName() {
        return name;
    }

    public ColoredFallingBlock getSand() {
        return sand.get();
    }

    public Block getSandstone() {
        return sandstone.get();
    }

    public SlabBlock getSandstoneSlab() {
        return sandstoneSlab.get();
    }

    public StairBlock getSandstoneStairs() {
        return sandstoneStairs.get();
    }

    public WallBlock getSandstoneWall() {
        return sandstoneWall.get();
    }

    public Block getChiseledSandstone() {
        return chiseledSandstone.get();
    }

    public Block getSmoothSandstone() {
        return smoothSandstone.get();
    }

    public SlabBlock getSmoothSandstoneSlab() {
        return smoothSandstoneSlab.get();
    }

    public StairBlock getSmoothSandstoneStairs() {
        return smoothSandstoneStairs.get();
    }

    public Block getCutSandstone() {
        return cutSandstone.get();
    }

    public SlabBlock getCutSandstoneSlab() {
        return cutSandstoneSlab.get();
    }

    public TagKey<Block> getSandstoneBlocksTag() {
        return sandstoneBlocksTag;
    }

    public TagKey<Item> getSandstoneBlocksItemTag() {
        return sandstoneBlocksItemTag;
    }

    public TagKey<Block> getSandBlockTag() {
        return sandBlockTag;
    }

    public TagKey<Item> getSandItemTag() {
        return sandItemTag;
    }

    public TagKey<Block> getSandstoneSlabsTag() {
        return sandstoneSlabsTag;
    }

    public TagKey<Item> getSandstoneSlabsItemTag() {
        return sandstoneSlabsItemTag;
    }

    public TagKey<Block> getSandstoneStairsTag() {
        return sandstoneStairsTag;
    }

    public TagKey<Item> getSandstoneStairsItemTag() {
        return sandstoneStairsItemTag;
    }

    public BlockFamily getSandStoneFamily() {
        return BlockFamilies.familyBuilder(getSandstone())
                .wall(getSandstoneWall())
                .stairs(getSandstoneStairs())
                .slab(getSandstoneSlab())
                .chiseled(getChiseledSandstone())
                .cut(getCutSandstone())
                .dontGenerateRecipe()
                .getFamily();
    }

    public BlockFamily getSmoothSandStoneFamily() {
        return BlockFamilies.familyBuilder(getSmoothSandstone())
                .stairs(getSmoothSandstoneStairs())
                .slab(getSmoothSandstoneSlab())
                .getFamily();
    }

    public BlockFamily getCutSandStoneFamily() {
        return BlockFamilies.familyBuilder(getCutSandstone())
                .slab(getCutSandstoneSlab())
                .getFamily();
    }

    public static ArrayList<BWGSandSet> getSandSets() {
        return sandSets;
    }

    private static ResourceKey<Block> key(String name) {
        return ResourceKey.create(Registries.BLOCK, BiomesWeveGone.id(name));
    }
}
