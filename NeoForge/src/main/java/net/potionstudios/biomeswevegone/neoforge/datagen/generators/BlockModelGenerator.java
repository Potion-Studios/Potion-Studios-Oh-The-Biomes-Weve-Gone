package net.potionstudios.biomeswevegone.neoforge.datagen.generators;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.util.ARGB;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.item.BWGItems;
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

        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(BWGBlocks.FORAGERS_TABLE.get(), ModelTemplates.CUBE.create(BWGBlocks.FORAGERS_TABLE.get(), new TextureMapping()
                .put(TextureSlot.DOWN, TextureMapping.getBlockTexture(Blocks.BEEHIVE, "_end"))
                .put(TextureSlot.UP, TextureMapping.getBlockTexture(BWGBlocks.FORAGERS_TABLE.get(), "_top"))
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

        itemModels.generateFlatItem(BWGItems.BWG_LOGO.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateSpawnEgg(BWGItems.ODDION_SPAWN_EGG.get(), ARGB.color(199, 165, 104), ARGB.color(210, 166, 246));
        itemModels.generateSpawnEgg(BWGItems.MAN_O_WAR_SPAWN_EGG.get(), ARGB.color(210, 166, 246), ARGB.color(199, 165, 104));
        itemModels.generateSpawnEgg(BWGItems.PUMPKIN_WARDEN_SPAWN_EGG.get(), ARGB.color(79, 57, 46), ARGB.color(192, 106, 5));

        itemModels.generateFlatItem(BWGItems.MAN_O_WAR_BUCKET.get(), ModelTemplates.FLAT_ITEM);

        itemModels.itemModelOutput.accept(BWGItems.CATTAIL_SPROUT.get(), ItemModelUtils.plainModel(ModelTemplates.FLAT_ITEM.create(BWGItems.CATTAIL_SPROUT.get(), TextureMapping.layer0(BiomesWeveGone.id("item/cattails")), itemModels.modelOutput)));
        itemModels.itemModelOutput.accept(BWGItems.FLUORESCENT_CATTAIL_SPROUT.get(), ItemModelUtils.plainModel(ModelTemplates.FLAT_ITEM.create(BWGItems.FLUORESCENT_CATTAIL_SPROUT.get(), TextureMapping.layer0(BiomesWeveGone.id("item/fluorescent_cattails")), itemModels.modelOutput)));

        itemModels.generateFlatItem(BWGItems.BLUE_GLOWCANE_SHOOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BWGItems.GREEN_GLOWCANE_SHOOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BWGItems.RED_GLOWCANE_SHOOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BWGItems.YELLOW_GLOWCANE_SHOOT.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(BWGItems.BLUE_GLOWCANE_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BWGItems.GREEN_GLOWCANE_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BWGItems.RED_GLOWCANE_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BWGItems.YELLOW_GLOWCANE_POWDER.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(BWGItems.BAOBAB_FRUIT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BWGItems.SOUL_FRUIT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BWGItems.YUCCA_FRUIT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BWGItems.COOKED_YUCCA_FRUIT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BWGItems.GREEN_APPLE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BWGItems.GREEN_APPLE_PIE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BWGItems.BLUEBERRIES.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BWGItems.BLUEBERRY_PIE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BWGItems.ODDION_BULB.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BWGItems.COOKED_ODDION_BULB.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BWGItems.ALLIUM_ODDION_SOUP.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BWGItems.BLOOMING_ODDION.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BWGItems.WHITE_PUFFBALL_SPORES.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BWGItems.WHITE_PUFFBALL_CAP.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BWGItems.COOKED_WHITE_PUFFBALL_CAP.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BWGItems.WHITE_PUFFBALL_STEW.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BWGItems.ALOE_VERA_JUICE.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(BWGItems.PALE_PUMPKIN_SEEDS.get(), ModelTemplates.FLAT_ITEM);

        blockModels.createFlatItemModelWithBlockTexture(BWGItems.TINY_LILY_PADS.get(), BWGBlocks.TINY_LILY_PADS.get());
        itemModels.declareCustomModelItem(BWGItems.TINY_LILY_PADS.get());
        itemModels.itemModelOutput.accept(BWGItems.FLOWERING_TINY_LILY_PADS.get(), ItemModelUtils.plainModel(ModelTemplates.TWO_LAYERED_ITEM.create(ModelLocationUtils.getModelLocation(BWGItems.FLOWERING_TINY_LILY_PADS.get()), TextureMapping.layered(TextureMapping.getBlockTexture(BWGBlocks.TINY_LILY_PADS.get()), TextureMapping.getBlockTexture(BWGBlocks.TINY_LILY_PADS.get(), "_flower_overlay")), blockModels.modelOutput)));
        //itemModels.declareCustomModelItem(BWGItems.FLOWERING_TINY_LILY_PADS.get());
        blockModels.createFlatItemModelWithBlockTexture(BWGItems.WATER_SILK.get(), BWGBlocks.WATER_SILK.get());
        itemModels.declareCustomModelItem(BWGItems.WATER_SILK.get());

        itemModels.generateFlatItem(BWGItems.MUSIC_DISC_PIXIE_CLUB.get(), ModelTemplates.MUSIC_DISC);
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
