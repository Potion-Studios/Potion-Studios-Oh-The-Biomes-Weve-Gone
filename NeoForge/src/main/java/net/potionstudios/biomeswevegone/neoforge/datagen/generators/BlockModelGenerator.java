package net.potionstudios.biomeswevegone.neoforge.datagen.generators;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.item.BWGItems;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.block.set.BWGBlockSet;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWoodSet;
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

        BWGWoodSet.woodsets().forEach(woodSet -> {
            ResourceLocation Planks = BiomesWeveGone.id("block/" + woodSet.name() + "/planks");
            blockModels.createTrivialBlock(woodSet.planks(), TexturedModel.CUBE.updateTexture(textureMapping -> textureMapping.put(TextureSlot.ALL, Planks)));
            blockItemModel(blockModels, woodSet.planks());

            ResourceLocation Log = BiomesWeveGone.id("block/" + woodSet.name() + "/" + woodSet.logStemEnum().getName());
            ResourceLocation LogTop = BiomesWeveGone.id("block/" + woodSet.name() + "/" + woodSet.logStemEnum().getName() + "_top");

            blockModels.blockStateOutput.accept(BlockModelGenerators.createRotatedPillarWithHorizontalVariant(woodSet.logstem(),
                    ModelTemplates.CUBE_COLUMN.create(woodSet.logstem(), new TextureMapping().put(TextureSlot.END, LogTop).put(TextureSlot.SIDE, Log), blockModels.modelOutput),
                    ModelTemplates.CUBE_COLUMN_HORIZONTAL.create(woodSet.logstem(), new TextureMapping().put(TextureSlot.END, LogTop).put(TextureSlot.SIDE, Log), blockModels.modelOutput)));
            blockItemModel(blockModels, woodSet.logstem());

            blockModels.blockStateOutput.accept(BlockModelGenerators.createRotatedPillarWithHorizontalVariant(woodSet.wood(),
                    ModelTemplates.CUBE_COLUMN.create(woodSet.wood(), new TextureMapping().put(TextureSlot.END, Log).put(TextureSlot.SIDE, Log), blockModels.modelOutput),
                    ModelTemplates.CUBE_COLUMN_HORIZONTAL.create(woodSet.wood(), new TextureMapping().put(TextureSlot.END, Log).put(TextureSlot.SIDE, Log), blockModels.modelOutput)));
            blockItemModel(blockModels, woodSet.wood());

            ResourceLocation StrippedLog = BiomesWeveGone.id("block/" + woodSet.name() + "/stripped_" + woodSet.logStemEnum().getName());
            ResourceLocation StrippedLogTop = BiomesWeveGone.id("block/" + woodSet.name() + "/stripped_" + woodSet.logStemEnum().getName() + "_top");
            blockModels.blockStateOutput.accept(BlockModelGenerators.createRotatedPillarWithHorizontalVariant(woodSet.strippedLogStem(),
                    ModelTemplates.CUBE_COLUMN.create(woodSet.strippedLogStem(), new TextureMapping().put(TextureSlot.END, StrippedLogTop).put(TextureSlot.SIDE, StrippedLog), blockModels.modelOutput),
                    ModelTemplates.CUBE_COLUMN_HORIZONTAL.create(woodSet.strippedLogStem(), new TextureMapping().put(TextureSlot.END, StrippedLogTop).put(TextureSlot.SIDE, StrippedLog), blockModels.modelOutput)));
            blockItemModel(blockModels, woodSet.strippedLogStem());

            blockModels.blockStateOutput.accept(BlockModelGenerators.createRotatedPillarWithHorizontalVariant(woodSet.strippedWood(),
                    ModelTemplates.CUBE_COLUMN.create(woodSet.strippedWood(), new TextureMapping().put(TextureSlot.END, StrippedLog).put(TextureSlot.SIDE, StrippedLog), blockModels.modelOutput),
                    ModelTemplates.CUBE_COLUMN_HORIZONTAL.create(woodSet.strippedWood(), new TextureMapping().put(TextureSlot.END, StrippedLog).put(TextureSlot.SIDE, StrippedLog), blockModels.modelOutput)));
            blockItemModel(blockModels, woodSet.strippedWood());

            itemModels.itemModelOutput.accept(woodSet.boatItem().get(), ItemModelUtils.plainModel(ModelTemplates.FLAT_ITEM.create(woodSet.boatItem().get(), TextureMapping.layer0(BiomesWeveGone.id("item/" + woodSet.name() + "/boat")), itemModels.modelOutput)));
            itemModels.itemModelOutput.accept(woodSet.chestBoatItem().get(), ItemModelUtils.plainModel(ModelTemplates.FLAT_ITEM.create(woodSet.chestBoatItem().get(), TextureMapping.layer0(BiomesWeveGone.id("item/" + woodSet.name() + "/chest_boat")), itemModels.modelOutput)));
        });

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

        basicItem(itemModels, BWGItems.BWG_LOGO.get());

        itemModels.generateSpawnEgg(BWGItems.ODDION_SPAWN_EGG.get(), ARGB.color(199, 165, 104), ARGB.color(210, 166, 246));
        itemModels.generateSpawnEgg(BWGItems.MAN_O_WAR_SPAWN_EGG.get(), ARGB.color(210, 166, 246), ARGB.color(199, 165, 104));
        itemModels.generateSpawnEgg(BWGItems.PUMPKIN_WARDEN_SPAWN_EGG.get(), ARGB.color(79, 57, 46), ARGB.color(192, 106, 5));

        basicItem(itemModels, BWGItems.MAN_O_WAR_BUCKET.get());

        itemModels.itemModelOutput.accept(BWGItems.CATTAIL_SPROUT.get(), ItemModelUtils.plainModel(ModelTemplates.FLAT_ITEM.create(BWGItems.CATTAIL_SPROUT.get(), TextureMapping.layer0(BiomesWeveGone.id("item/cattails")), itemModels.modelOutput)));
        itemModels.itemModelOutput.accept(BWGItems.FLUORESCENT_CATTAIL_SPROUT.get(), ItemModelUtils.plainModel(ModelTemplates.FLAT_ITEM.create(BWGItems.FLUORESCENT_CATTAIL_SPROUT.get(), TextureMapping.layer0(BiomesWeveGone.id("item/fluorescent_cattails")), itemModels.modelOutput)));

        basicItem(itemModels, BWGItems.BLUE_GLOWCANE_SHOOT.get());
        basicItem(itemModels, BWGItems.GREEN_GLOWCANE_SHOOT.get());
        basicItem(itemModels, BWGItems.RED_GLOWCANE_SHOOT.get());
        basicItem(itemModels, BWGItems.YELLOW_GLOWCANE_SHOOT.get());

        basicItem(itemModels, BWGItems.BLUE_GLOWCANE_POWDER.get());
        basicItem(itemModels, BWGItems.GREEN_GLOWCANE_POWDER.get());
        basicItem(itemModels, BWGItems.RED_GLOWCANE_POWDER.get());
        basicItem(itemModels, BWGItems.YELLOW_GLOWCANE_POWDER.get());

        basicItem(itemModels, BWGItems.BAOBAB_FRUIT.get());
        basicItem(itemModels, BWGItems.SOUL_FRUIT.get());
        basicItem(itemModels, BWGItems.YUCCA_FRUIT.get());
        basicItem(itemModels, BWGItems.COOKED_YUCCA_FRUIT.get());
        basicItem(itemModels, BWGItems.GREEN_APPLE.get());
        basicItem(itemModels, BWGItems.GREEN_APPLE_PIE.get());
        basicItem(itemModels, BWGItems.BLUEBERRIES.get());
        basicItem(itemModels, BWGItems.BLUEBERRY_PIE.get());
        basicItem(itemModels, BWGItems.ODDION_BULB.get());
        basicItem(itemModels, BWGItems.COOKED_ODDION_BULB.get());
        basicItem(itemModels, BWGItems.ALLIUM_ODDION_SOUP.get());
        basicItem(itemModels, BWGItems.BLOOMING_ODDION.get());
        basicItem(itemModels, BWGItems.WHITE_PUFFBALL_SPORES.get());
        basicItem(itemModels, BWGItems.WHITE_PUFFBALL_CAP.get());
        basicItem(itemModels, BWGItems.COOKED_WHITE_PUFFBALL_CAP.get());
        basicItem(itemModels, BWGItems.WHITE_PUFFBALL_STEW.get());
        basicItem(itemModels, BWGItems.ALOE_VERA_JUICE.get());

        basicItem(itemModels, BWGItems.PALE_PUMPKIN_SEEDS.get());

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

    private void basicItem(ItemModelGenerators itemModels, Item item) {
        itemModels.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
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
