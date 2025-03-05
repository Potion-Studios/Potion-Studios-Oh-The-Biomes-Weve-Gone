package net.potionstudios.biomeswevegone.neoforge.datagen.generators;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.block.set.BWGBlockSet;
import org.jetbrains.annotations.NotNull;

import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class BlockModelGenerator extends ModelProvider {
    public BlockModelGenerator(PackOutput arg) {
        super(arg, BiomesWeveGone.MOD_ID);
    }

    @Override
    protected void registerModels(@NotNull BlockModelGenerators blockModels, @NotNull ItemModelGenerators itemModels) {
        BWGBlocks.cubeAllBlocks.forEach(block -> {
            blockModels.createTrivialCube(block.get());
            blockItemModel(blockModels, block.get());
        });

        BWGBlockSet.getBlockSets().stream().filter(set -> set.getBlockFamily().shouldGenerateModel()).forEach(set -> blockModels.family(set.getBase()).generateFor(set.getBlockFamily()));

        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(BWGBlocks.FORAGERS_TABLE.get(), ModelTemplates.CUBE.create(BWGBlocks.FORAGERS_TABLE.get(), new TextureMapping().put(TextureSlot.DOWN, TextureMapping.getBlockTexture(Blocks.BEEHIVE))
                .put(TextureSlot.UP, TextureMapping.getBlockTexture(BWGBlocks.FORAGERS_TABLE.get(), "_top"))
                .put(TextureSlot.DOWN, TextureMapping.getBlockTexture(Blocks.BEEHIVE))  //TODO: Add _end for some reason it's not working rn
                .put(TextureSlot.EAST, TextureMapping.getBlockTexture(BWGBlocks.FORAGERS_TABLE.get(), "_side"))
                .put(TextureSlot.WEST, TextureMapping.getBlockTexture(BWGBlocks.FORAGERS_TABLE.get(), "_side"))
                .put(TextureSlot.NORTH, TextureMapping.getBlockTexture(BWGBlocks.FORAGERS_TABLE.get(), "_front"))
                .put(TextureSlot.SOUTH, TextureMapping.getBlockTexture(BWGBlocks.FORAGERS_TABLE.get(), "_front"))
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(BWGBlocks.FORAGERS_TABLE.get(), "_top")), blockModels.modelOutput)));
        blockItemModel(blockModels, BWGBlocks.FORAGERS_TABLE.get());

        blockModels.createTrivialBlock(BWGBlocks.BLACK_ICE.get(), TexturedModel.CUBE.updateTemplate(template -> template.extend().renderType(mcLocation("translucent")).build()));
        blockItemModel(blockModels, BWGBlocks.BLACK_ICE.get());
        //blockModels.createTrivialBlock(BWGBlocks.BOREALIS_ICE.get(), TexturedModel.CUBE.updateTemplate(template -> template.extend().renderType(mcLocation("translucent")).build()));
        //blockItemModel(blockModels, BWGBlocks.BOREALIS_ICE.get());

    }

    private void blockItemModel(BlockModelGenerators blockModels, Block block) {
        blockModels.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block));
    }

    @Override
    protected @NotNull Stream<? extends Holder<Block>> getKnownBlocks() {
        return Stream.empty();
    }

    @Override
    protected @NotNull Stream<? extends Holder<Item>> getKnownItems() {
        return Stream.empty();
    }
}
