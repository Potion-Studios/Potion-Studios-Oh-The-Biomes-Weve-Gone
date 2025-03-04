package net.potionstudios.biomeswevegone.neoforge.datagen.generators;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.item.BWGItems;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.block.custom.BWGFarmLandBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.PottedBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.bush.*;
import net.potionstudios.biomeswevegone.world.level.block.plants.cactus.CarvedBarrelCactusBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.cactus.LiquidType;
import net.potionstudios.biomeswevegone.world.level.block.plants.tree.branch.TreeBranchBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.tree.fruit.BWGFruitBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.vegetation.AloeVeraBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.vegetation.BoneMealGrassBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.vegetation.GlowCaneBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.vegetation.cattail.CattailPlantBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.vegetation.FlatVegetationBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.vegetation.cattail.FluorescentCattailPlantBlock;
import net.potionstudios.biomeswevegone.world.level.block.sand.BWGSandSet;
import net.potionstudios.biomeswevegone.world.level.block.set.BWGBlockSet;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWood;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWoodSet;

/**
 * Used to generate models for blocks and items.
 * @see ItemModelProvider
 * @see BlockModelProvider
 * @author Joseph T. McQuigg
 */
public class ModelGenerators {

    public static void init(DataGenerator generator, boolean run, PackOutput output, ExistingFileHelper exFileHelper) {
        generator.addProvider(run, new BlockModelGenerators(output, exFileHelper));
        generator.addProvider(run, new ItemModelGenerators(output, exFileHelper));
    }

    /**
     * Used to generate models for items.
     * @see ItemModelProvider
     */
    private static class ItemModelGenerators extends ItemModelProvider {

        private ItemModelGenerators(PackOutput output, ExistingFileHelper exFileHelper) {
            super(output, BiomesWeveGone.MOD_ID, exFileHelper);
        }

        @Override
        protected void registerModels() {
            basicItem(BWGItems.BWG_LOGO.get());
            BWGItems.SIMPLE_ITEMS.forEach(supplier -> {
                if (supplier.get() instanceof SpawnEggItem) spawnEggItem(supplier.get());
                else basicItem(supplier.get());
            });
            BWGWoodSet.woodsets().forEach(set -> {
                simpleItem(set.door(), set.name() + "/door");
                simpleItem(set.signItem(), set.name() + "/sign");
                simpleItem(set.hangingSignItem(), set.name() + "/hanging_sign");
                simpleItem(set.boatItem().get(), set.name() + "/boat");
                simpleItem(set.chestBoatItem().get(), set.name() + "/chest_boat");
            });
            withExistingParent(key(BWGItems.MUSIC_DISC_PIXIE_CLUB.get()).getPath(), mcLoc("minecraft:item/template_music_disc")).texture("layer0", BiomesWeveGone.id(ModelProvider.ITEM_FOLDER + "/" + key(BWGItems.MUSIC_DISC_PIXIE_CLUB.get()).getPath()));
            simpleItemBlockTexture(BWGItems.TINY_LILY_PADS.get());
            singleTexture(key(BWGItems.FLOWERING_TINY_LILY_PADS.get()).getPath(), mcLoc("item/generated"), "layer0", BiomesWeveGone.id(ModelProvider.BLOCK_FOLDER + "/tiny_lily_pads")).texture("layer1", BiomesWeveGone.id(ModelProvider.BLOCK_FOLDER + "/tiny_lily_pads_flower_overlay"));
            simpleItemBlockTexture(BWGItems.WATER_SILK.get());
            simpleItemBlockTexture(BWGBlocks.POISON_IVY.get());
            basicItem(BWGBlocks.SKYRIS_VINE.get().asItem());
            basicItem(BWGBlocks.ALOE_VERA.get().asItem());
            simpleItem(BWGItems.CATTAIL_SPROUT.get(), "cattails");
            simpleItem(BWGItems.FLUORESCENT_CATTAIL_SPROUT.get(), "fluorescent_cattails");
            simpleItem(BWGBlocks.CYAN_PITCHER_PLANT.get().asItem(), "cyan_pitcher_plant");
            simpleItem(BWGBlocks.MAGENTA_PITCHER_PLANT.get().asItem(), "magenta_pitcher_plant");
            simpleItemBlockTexture(BWGBlocks.WITCH_HAZEL_BRANCH.get());
        }

        private void simpleItem(ItemLike item, String texture) {
            singleTexture(name(item), mcLoc("item/generated"), "layer0", BiomesWeveGone.id(ModelProvider.ITEM_FOLDER + "/" + texture));
        }

        private void simpleItemBlockTexture(ItemLike item) {
            singleTexture(name(item), mcLoc("item/generated"), "layer0", BiomesWeveGone.id(ModelProvider.BLOCK_FOLDER + "/" + name(item)));
        }

        private String name(ItemLike item) {
            return key(item.asItem()).getPath();
        }

        private ResourceLocation key(Item item) {
            return BuiltInRegistries.ITEM.getKey(item);
        }
    }

    /**
     * Used to generate models for blocks.
     * @see BlockStateProvider
     */
    private static class BlockModelGenerators extends BlockStateProvider {

        private BlockModelGenerators(PackOutput output, ExistingFileHelper exFileHelper) {
            super(output, BiomesWeveGone.MOD_ID, exFileHelper);
        }

        @Override
        protected void registerStatesAndModels() {
            BWGBlocks.cubeAllBlocks.forEach(block -> simpleBlockWithItem(block.get(), cubeAll(block.get())));
            BWGBlocks.BLOCKS.forEach(entry -> {
                Block block = entry.get();
                if (block instanceof StemBlock){
                  getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder().modelFile(models().withExistingParent(name(block) + "_stage" + state.getValue(StemBlock.AGE), name(Blocks.PUMPKIN_STEM) + "_stage" + state.getValue(StemBlock.AGE)).renderType("cutout")).build());
                } else if (block instanceof AttachedStemBlock) {
                    getVariantBuilder(block).forAllStates(state -> {
                        if (state.getValue(CarvedPumpkinBlock.FACING) == Direction.EAST)
                            return ConfiguredModel.builder().rotationY(180).modelFile(models().withExistingParent(name(block), "attached_pumpkin_stem").renderType("cutout")).build();
                        else if (state.getValue(CarvedPumpkinBlock.FACING) == Direction.WEST)
                            return ConfiguredModel.builder().modelFile(models().withExistingParent(name(block), "attached_pumpkin_stem").renderType("cutout")).build();
                        else if (state.getValue(CarvedPumpkinBlock.FACING) == Direction.SOUTH)
                            return ConfiguredModel.builder().rotationY(270).modelFile(models().withExistingParent(name(block), "attached_pumpkin_stem").renderType("cutout")).build();
                        else if (state.getValue(CarvedPumpkinBlock.FACING) == Direction.NORTH)
                            return ConfiguredModel.builder().rotationY(90).modelFile(models().withExistingParent(name(block), "attached_pumpkin_stem").renderType("cutout")).build();
                        return new ConfiguredModel[0];
                    });
                } else if (block instanceof GlowCaneBlock) {
                   simpleBlock(block, models().withExistingParent(name(block), mcLoc("block/tinted_cross")).texture("cross", blockBWGTexture(block)).renderType("cutout"));
                } else if (block instanceof FluorescentCattailPlantBlock fluorescentCattailBlock) {
                    getVariantBuilder(fluorescentCattailBlock).forAllStatesExcept(state -> {
                        if (state.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.LOWER)
                            return createRotatedModels(models().getExistingFile(blockBWGTexture(BWGBlocks.CATTAIL.get(), "bottom")));
                        else
                            return createRotatedModels(models().withExistingParent(name(block) + "_" + state.getValue(FluorescentCattailPlantBlock.COLOR).getSerializedName(), BiomesWeveGone.id("block/template_cattail_top"))
                                    .texture("sprout", blockBWGTexture(fluorescentCattailBlock.getSprout().getBlock(), state.getValue(FluorescentCattailPlantBlock.COLOR).getSerializedName())));
                            }, FluorescentCattailPlantBlock.WATERLOGGED);
                } else if (block instanceof CattailPlantBlock cattail) {
                    getVariantBuilder(block)
                            .partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
                            .addModels(createRotatedModels(models().getExistingFile(blockBWGTexture(BWGBlocks.CATTAIL.get(), "bottom"))))
                            .partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)
                            .addModels(createRotatedModels(models().withExistingParent(name(block), BiomesWeveGone.id("block/template_cattail_top"))
                                    .texture("sprout", blockBWGTexture(cattail.getSprout().getBlock()))));
                } else if (block instanceof DoublePlantBlock)
                    createDoubleBlock((DoublePlantBlock) block);
                else if (block instanceof FlowerBlock)
                    createFlowerBlock((FlowerBlock) block);
                else if (block instanceof RotatedPillarBlock)
                    simpleBlockWithItem(block, models().cubeColumn(name(block), blockBWGTexture(block), blockBWGTexture(block, "top")));
                else if (block instanceof WhitePuffballBlock)
                    getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder()
                            .modelFile(models().getExistingFile(blockBWGTexture(block, "stage" + state.getValue(WhitePuffballBlock.AGE))))
                            .build());
                else if (block instanceof OddionCrop)
                    getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder()
                            .modelFile(models().crop(name(block) + "_stage" + state.getValue(OddionCrop.AGE), blockBWGTexture(block, "stage" + state.getValue(OddionCrop.AGE))).renderType("cutout"))
                            .build());
                else if (block instanceof BWGFruitBlock || block instanceof SweetBerryBushBlock) {
                    getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder()
                            .modelFile(models().cross(name(block) + "_stage" + state.getValue(BWGFruitBlock.AGE), blockBWGTexture(block, "stage" + state.getValue(BWGFruitBlock.AGE))).renderType("cutout"))
                            .build());
                } else if (block instanceof BoneMealGrassBlock)
                    createCrossBlock(block, "cutout");
                else if (block instanceof HydrangeaHedgeBlock) {
                    defaultModelFile(block);
                    simpleBlockItemExistingModel(block);
                } else if (block instanceof BWGPlacementBushBlock || block instanceof MushroomBlock || block instanceof AloeVeraBlock || block instanceof ShrubBlock)
                    createCrossBlock(block, "cutout");
                else if (block instanceof WaterlilyBlock || block instanceof DirtPathBlock)
                    rotatableBlock(block);
                else if (block instanceof IronBarsBlock) {
                    paneBlockWithRenderType((IronBarsBlock) block, blockBWGTexture(name(block).replace("_pane", "")), blockBWGTexture(block, "top"), "translucent");
                    simpleItemBlockTexture(block, name(block).replace("_pane", ""));
                } else if (block instanceof FlowerPotBlock) {
                    if (((FlowerPotBlock) block).getPotted() == BWGBlocks.WHITE_PUFFBALL.getBlock())
                        simpleBlock(block, models().getExistingFile(blockBWGTexture(BWGBlocks.WHITE_PUFFBALL.getPottedBlock())));
                    else simpleBlock(block, models().withExistingParent(name(block), mcLoc("block/flower_pot_cross")).texture("plant", blockBWGTexture(((FlowerPotBlock) block).getPotted())).renderType("cutout"));
                } else if (block instanceof HugeMushroomBlock)
                    simpleBlockItem(block, models().cubeAll(name(block) + "_inventory", blockBWGTexture(block)));
                else if (block instanceof PinkPetalsBlock) {
                    for (int i = PinkPetalsBlock.MIN_FLOWERS; i <= PinkPetalsBlock.MAX_FLOWERS; i++)
                        models().withExistingParent(name(block) + "_" + i, mcLoc("block/flowerbed_" + i))
                                .texture("flowerbed", blockBWGTexture(block))
                                .texture("stem", mcLoc("block/pink_petals_stem"))
                                .renderType("cutout");
                    itemModels().basicItem(block.asItem());
                } else if (block instanceof BushBlock && !(block instanceof FlatVegetationBlock))
                    getVariantBuilder(block).partialState().setModels(new ConfiguredModel(models().getExistingFile(BiomesWeveGone.id("block/" + name(block)))));
                else if (block instanceof LanternBlock) {
                    getVariantBuilder(block).forAllStatesExcept(state -> {
                        if (state.getValue(LanternBlock.HANGING))
                            return ConfiguredModel.builder().modelFile(models().withExistingParent(name(block) + "_hanging", mcLoc("block/template_hanging_lantern")).texture("lantern", blockBWGTexture(block)).renderType("cutout")).build();
                        return ConfiguredModel.builder().modelFile(models().withExistingParent(name(block), mcLoc("block/template_lantern")).texture("lantern", blockBWGTexture(block)).renderType("cutout")).build();
                    }, LanternBlock.WATERLOGGED);
                    itemModels().basicItem(block.asItem());
                }
            });
            BWGWoodSet.woodsets().forEach(set -> {
                ResourceLocation planksTexture = woodBlockTexture(set.name(), "planks");
                simpleBlockWithItem(set.planks(), models().cubeAll(name(set.planks()), planksTexture));
                ResourceLocation logTexture = woodBlockTexture(set.name(), set.logStemEnum().getName());
                slabBlock(set.slab(), key(set.planks()), planksTexture);
                itemModels().slab(name(set.slab()), planksTexture, planksTexture, planksTexture);
                log(set);
                ResourceLocation strippedLogTexture = woodBlockTexture(set.name(), "stripped_" + set.logStemEnum().getName());
                log(set.strippedLogStem(), strippedLogTexture, woodBlockTexture(set.name(), "stripped_" + set.logStemEnum().getName() + "_top"));
                woodBlock(set.wood(), logTexture);
                woodBlock(set.strippedWood(), strippedLogTexture);
                registerStairs(set.stairs(), planksTexture);
                registerButton(set.button(), planksTexture);
                registerFenceAndGate(set.fence(), set.fenceGate(), planksTexture);
                signBlock(set.sign(), set.wallSign(), planksTexture);
                hangingSignBlock(set.hangingSign(), set.wallHangingSign(), models().sign(name(set.hangingSign()), strippedLogTexture));
                trapdoorBlockWithRenderType(set.trapdoor(), woodBlockTexture(set.name(), "trapdoor"), true, "cutout");
                itemModels().trapdoorBottom(name(set.trapdoor()), woodBlockTexture(set.name(), "trapdoor"));
	            doorBlockWithRenderType(set.door(), woodBlockTexture(set.name(), "door_bottom"), woodBlockTexture(set.name(), "door_top"), set != BWGWood.MAPLE ? "cutout" : "translucent");
                pressurePlateBlock(set.pressurePlate(), planksTexture);
                itemModels().pressurePlate(name(set.pressurePlate()), planksTexture);
                simpleBlockWithItem(set.bookshelf(), models().cubeColumn(name(set.bookshelf()), woodBlockTexture(set.name(), "bookshelf"), planksTexture));
                simpleBlockWithItem(set.craftingTable(), models().cube(name(set.craftingTable()), planksTexture, woodBlockTexture(set.name(), "crafting_table_top"), woodBlockTexture(set.name(), "crafting_table_front"), woodBlockTexture(set.name(), "crafting_table_side"), woodBlockTexture(set.name(), "crafting_table_side"), woodBlockTexture(set.name(), "crafting_table_front")).texture("particle", woodBlockTexture(set.name(), "crafting_table_front")));
                if (set.sapling() != null)
                    registerSapling(set.sapling(), woodBlockTexture(set.name(), "sapling"));
                if (set.leaves() != null)
                    simpleBlockWithItem(set.leaves(), models().leaves(name(set.leaves()), woodBlockTexture(set.name(), "leaves")).renderType("cutout_mipped"));
            });

            BWGWood.NONSET_WOOD.forEach(object -> {
                Block block = object.get();
                if (models().existingFileHelper.exists(blockBWGTexture(block), PackType.CLIENT_RESOURCES, ".json", "models")) {
                    defaultModelFile(block);
                    simpleBlockItemExistingModel(block);
                } else {
                    if (block instanceof SaplingBlock) registerSaplingBlock(object.get(), blockBWGTexture(block));
                    else if (block instanceof FlowerPotBlock) registerPottedSapling(object.get(), blockBWGTexture(((FlowerPotBlock) block).getPotted()));
                    else if (block instanceof LeavesBlock) simpleBlockWithItem(block, models().leaves(name(block), blockBWGTexture(block)).renderType("cutout_mipped"));
                }
            });

            ResourceLocation paloVerdeLog = woodBlockTexture("palo_verde", "log");
            ResourceLocation paloVerdeStrippedLog = woodBlockTexture("palo_verde", "stripped_log");
            log(BWGWood.PALO_VERDE_LOG.get(), paloVerdeLog, woodBlockTexture("palo_verde", "log_top"));
            log(BWGWood.STRIPPED_PALO_VERDE_LOG.get(), paloVerdeStrippedLog, woodBlockTexture("palo_verde", "stripped_log_top"));
            woodBlock(BWGWood.PALO_VERDE_WOOD.get(), paloVerdeLog);
            woodBlock(BWGWood.STRIPPED_PALO_VERDE_WOOD.get(), paloVerdeStrippedLog);
            simpleBlockWithItem(BWGWood.PALO_VERDE_LEAVES.get(), models().leaves(name(BWGWood.PALO_VERDE_LEAVES.get()), woodBlockTexture("palo_verde", "leaves")).renderType("cutout_mipped"));
            registerSapling(BWGWood.PALO_VERDE_SAPLING, woodBlockTexture("palo_verde", "sapling"));
            registerSapling(BWGWood.WHITE_SAKURA_SAPLING, woodBlockTexture("sakura", "white_sapling"));
            registerSapling(BWGWood.YELLOW_SAKURA_SAPLING, woodBlockTexture("sakura", "yellow_sapling"));
            simpleBlockWithItem(BWGWood.IMBUED_BLUE_ENCHANTED_WOOD.get(), models().cubeAll(name(BWGWood.IMBUED_BLUE_ENCHANTED_WOOD.get()), woodBlockTexture("blue_enchanted", "imbued_wood")));
            simpleBlockWithItem(BWGWood.IMBUED_GREEN_ENCHANTED_WOOD.get(), models().cubeAll(name(BWGWood.IMBUED_GREEN_ENCHANTED_WOOD.get()), woodBlockTexture("green_enchanted", "imbued_wood")));
            BWGSandSet.getSandSets().forEach(set -> {
                ResourceLocation sandStoneTopTexture = blockBWGTexture(set.getSandstone(), "top");
                var sandstoneModel = models().cubeBottomTop(name(set.getSandstone()), blockBWGTexture(set.getSandstone()), blockBWGTexture(set.getSandstone(), "bottom"), sandStoneTopTexture);
                simpleBlockWithItem(set.getSandstone(), sandstoneModel);
                slabBlock(set.getSandstoneSlab(), blockBWGTexture(set.getSandstone()), blockBWGTexture(set.getSandstone()), blockBWGTexture(set.getSandstone(), "bottom"), sandStoneTopTexture);
                itemModels().slab(name(set.getSandstoneSlab()), blockBWGTexture(set.getSandstone()), blockBWGTexture(set.getSandstone(), "bottom"), sandStoneTopTexture);
                stairsBlock(set.getSandstoneStairs(), name(set.getSandstoneStairs()), blockBWGTexture(set.getSandstone()), blockBWGTexture(set.getSandstone(), "bottom"), sandStoneTopTexture);
                itemModels().stairs(name(set.getSandstoneStairs()), blockBWGTexture(set.getSandstone()), blockBWGTexture(set.getSandstone(), "bottom"), sandStoneTopTexture);
                wallBlock(set.getSandstoneWall(), blockBWGTexture(set.getSandstone()));
                itemModels().wallInventory(name(set.getSandstoneWall()), blockBWGTexture(set.getSandstone()));
                simpleBlockWithItem(set.getChiseledSandstone(), models().cubeColumn(name(set.getChiseledSandstone()), blockBWGTexture(set.getChiseledSandstone()), sandStoneTopTexture));
                simpleBlockWithItem(set.getCutSandstone(), models().cubeColumn(name(set.getCutSandstone()), blockBWGTexture(set.getCutSandstone()), sandStoneTopTexture));
                slabBlock(set.getCutSandstoneSlab(), blockBWGTexture(set.getCutSandstone()), blockBWGTexture(set.getCutSandstone()), sandStoneTopTexture, sandStoneTopTexture);
                itemModels().slab(name(set.getCutSandstoneSlab()), blockBWGTexture(set.getCutSandstone()), blockBWGTexture(set.getCutSandstone()), sandStoneTopTexture);
                simpleBlockWithItem(set.getSmoothSandstone(), models().cubeAll(name(set.getSmoothSandstone()), sandStoneTopTexture));
                slabBlock(set.getSmoothSandstoneSlab(), blockBWGTexture(set.getSmoothSandstone()), sandStoneTopTexture, sandStoneTopTexture, sandStoneTopTexture);
                itemModels().slab(name(set.getSmoothSandstoneSlab()), sandStoneTopTexture, sandStoneTopTexture, sandStoneTopTexture);
                registerStairs(set.getSmoothSandstoneStairs(), sandStoneTopTexture);
            });
            simpleBlockWithItem(BWGBlocks.BLACK_ICE.get(), models().cubeAll(name(BWGBlocks.BLACK_ICE.get()), blockBWGTexture(BWGBlocks.BLACK_ICE.get())).renderType("translucent"));

            simpleBlockWithItem(BWGBlocks.BOREALIS_ICE.get(), models().leaves(name(BWGBlocks.BOREALIS_ICE.get()), blockBWGTexture(BWGBlocks.BOREALIS_ICE.get())).renderType("translucent"));
            simpleBlockWithItem(BWGBlocks.PACKED_BOREALIS_ICE.get(), models().leaves(name(BWGBlocks.PACKED_BOREALIS_ICE.get()), blockBWGTexture(BWGBlocks.PACKED_BOREALIS_ICE.get())));

            simpleBlockWithItem(BWGBlocks.FORAGERS_TABLE.get(),
                    models().cube(name(BWGBlocks.FORAGERS_TABLE.get()),
                            mcLoc("block/beehive_end"),
                            blockBWGTexture(BWGBlocks.FORAGERS_TABLE.get(), "top"),
                            blockBWGTexture(BWGBlocks.FORAGERS_TABLE.get(), "front"),
                            blockBWGTexture(BWGBlocks.FORAGERS_TABLE.get(), "front"),
                            blockBWGTexture(BWGBlocks.FORAGERS_TABLE.get(), "side"),
                            blockBWGTexture(BWGBlocks.FORAGERS_TABLE.get(), "side"))
                    .texture("particle", blockBWGTexture(BWGBlocks.FORAGERS_TABLE.get(), "top"))
            );

            BWGBlockSet.getBlockSets().forEach(set -> {
                registerSlab(set.getSlab(), set.getBase());
                registerStairs(set.getStairs(), set.getBase());
                registerWall(set.getWall(), set.getBase());
            });

            simpleBlockWithItem(BWGBlocks.PODZOL_DACITE.get(),
                    models().cubeBottomTop(name(BWGBlocks.PODZOL_DACITE.get()), blockBWGTexture(BWGBlocks.PODZOL_DACITE.get()), blockBWGTexture(BWGBlocks.DACITE_SET.getBase()), mcLoc("block/podzol_top")));

            simpleBlockWithItem(BWGBlocks.WHITE_PODZOL_DACITE.get(),
                    models().cubeBottomTop(name(BWGBlocks.WHITE_PODZOL_DACITE.get()), blockBWGTexture(BWGBlocks.WHITE_PODZOL_DACITE.get()), blockBWGTexture(BWGBlocks.WHITE_DACITE_SET.getBase()), mcLoc("block/podzol_top")));


            registerSlab(BWGBlocks.CATTAIL_THATCH_SLAB.get(), BWGBlocks.CATTAIL_THATCH.get());
            registerStairs(BWGBlocks.CATTAIL_THATCH_STAIRS.get(), BWGBlocks.CATTAIL_THATCH.get());

            simpleBlockWithItem(BWGBlocks.CATTAIL_THATCH_CARPET.get(), models().carpet(name(BWGBlocks.CATTAIL_THATCH_CARPET.get()), blockBWGTexture(BWGBlocks.CATTAIL_THATCH.get(), "top")));

            models().cubeBottomTop(name(BWGBlocks.OVERGROWN_DACITE.get()) + "_snowy",  blockBWGTexture(BWGBlocks.OVERGROWN_DACITE.get(), "snow_side"), blockBWGTexture(BWGBlocks.OVERGROWN_DACITE.get(), "bottom"), blockBWGTexture(BWGBlocks.OVERGROWN_DACITE.get(), "top")).texture("particle", blockBWGTexture(BWGBlocks.OVERGROWN_DACITE.get(), "bottom")).renderType("cutout_mipped");
            snowyRotatableBlock(BWGBlocks.OVERGROWN_DACITE.get());
            simpleBlockItemExistingModel(BWGBlocks.OVERGROWN_DACITE.get());

            models().cubeBottomTop(name(BWGBlocks.WHITE_OVERGROWN_DACITE.get()) + "_snowy",  blockBWGTexture(BWGBlocks.WHITE_OVERGROWN_DACITE.get(), "snow_side"), blockBWGTexture(BWGBlocks.WHITE_OVERGROWN_DACITE.get(), "bottom"), blockBWGTexture(BWGBlocks.WHITE_OVERGROWN_DACITE.get(), "top")).texture("particle", blockBWGTexture(BWGBlocks.WHITE_OVERGROWN_DACITE.get(), "bottom")).renderType("cutout_mipped");
            snowyRotatableBlock(BWGBlocks.WHITE_OVERGROWN_DACITE.get());
            simpleBlockItemExistingModel(BWGBlocks.WHITE_OVERGROWN_DACITE.get());

            models().cubeBottomTop(name(BWGBlocks.OVERGROWN_STONE.get()) + "_snowy",  blockBWGTexture(BWGBlocks.OVERGROWN_STONE.get(), "snow_side"), blockTexture(Blocks.STONE), blockBWGTexture(BWGBlocks.OVERGROWN_STONE.get(), "top")).texture("particle", blockTexture(Blocks.STONE)).renderType("cutout_mipped");
            snowyRotatableBlock(BWGBlocks.OVERGROWN_STONE.get());
            simpleBlockItemExistingModel(BWGBlocks.OVERGROWN_STONE.get());


            models().cubeBottomTop(name(BWGBlocks.LUSH_GRASS_BLOCK.get()) + "_snowy",  blockBWGTexture(BWGBlocks.LUSH_GRASS_BLOCK.get(), "snow_side"), blockBWGTexture(BWGBlocks.LUSH_DIRT.get()), blockBWGTexture(BWGBlocks.LUSH_GRASS_BLOCK.get(), "top")).texture("particle", blockBWGTexture(BWGBlocks.LUSH_DIRT.get())).renderType("cutout_mipped");
            snowyRotatableBlock(BWGBlocks.LUSH_GRASS_BLOCK.get());
            simpleBlockItemExistingModel(BWGBlocks.LUSH_GRASS_BLOCK.get());

            defaultModelFile(BWGBlocks.BARREL_CACTUS.get(), BWGBlocks.FLOWERING_BARREL_CACTUS.get());

            getVariantBuilder(BWGBlocks.CARVED_BARREL_CACTUS.get()).forAllStates(state -> {
                LiquidType liquid = state.getValue(CarvedBarrelCactusBlock.LIQUID);
               if (liquid.equals(LiquidType.EMPTY))
                   return ConfiguredModel.builder().modelFile(models().getExistingFile(blockBWGTexture(BWGBlocks.CARVED_BARREL_CACTUS.get()))).build();
               else if (liquid.equals(LiquidType.WATER))
                   return ConfiguredModel.builder().modelFile(models().getExistingFile(blockBWGTexture(BWGBlocks.CARVED_BARREL_CACTUS.get(), "water"))).build();
               else
                   return ConfiguredModel.builder().modelFile(models().getExistingFile(blockBWGTexture(BWGBlocks.CARVED_BARREL_CACTUS.get(), "honey"))).build();
            });

            simpleBlockItemExistingModel(BWGBlocks.CARVED_BARREL_CACTUS.get());

            getVariantBuilder(BWGBlocks.SANDY_FARMLAND.get()).forAllStates(state -> {
                if (state.getValue(BWGFarmLandBlock.MOISTURE) == BWGFarmLandBlock.MAX_MOISTURE) return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(name(BWGBlocks.SANDY_FARMLAND.get()) + "_moist", "template_farmland")
                                .texture("dirt", blockBWGTexture(BWGBlocks.SANDY_DIRT.get()))
                                .texture("top", blockBWGTexture(BWGBlocks.SANDY_FARMLAND.get(), "moist"))).build();
               else return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(name(BWGBlocks.SANDY_FARMLAND.get()), "template_farmland")
                                .texture("dirt", blockBWGTexture(BWGBlocks.SANDY_DIRT.get()))
                                .texture("top", blockBWGTexture(BWGBlocks.SANDY_FARMLAND.get()))).build();
            });
            simpleBlockItemExistingModel(BWGBlocks.SANDY_FARMLAND.get());

            getVariantBuilder(BWGBlocks.LUSH_FARMLAND.get()).forAllStates(state -> {
                if (state.getValue(BWGFarmLandBlock.MOISTURE) == BWGFarmLandBlock.MAX_MOISTURE) return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(name(BWGBlocks.LUSH_FARMLAND.get()) + "_moist", "template_farmland")
                                .texture("dirt", blockBWGTexture(BWGBlocks.LUSH_DIRT.get()))
                                .texture("top", blockBWGTexture(BWGBlocks.LUSH_FARMLAND.get(), "moist"))).build();
                else return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(name(BWGBlocks.LUSH_FARMLAND.get()), "template_farmland")
                                .texture("dirt", blockBWGTexture(BWGBlocks.LUSH_DIRT.get()))
                                .texture("top", blockBWGTexture(BWGBlocks.LUSH_FARMLAND.get()))).build();
            });
            simpleBlockItemExistingModel(BWGBlocks.LUSH_FARMLAND.get());

            rotatableBlock(BWGBlocks.CATTAIL_SPROUT.get());
            rotatableBlock(BWGBlocks.FLUORESCENT_CATTAIL_SPROUT.get());

            simpleItemBlockTexture(BWGBlocks.LEAF_PILE.get(), name(BWGBlocks.LEAF_PILE.get()));
            simpleItemBlockTexture(BWGBlocks.CLOVER_PATCH.get(), name(BWGBlocks.CLOVER_PATCH.get()));
            simpleItemBlockTexture(BWGBlocks.FLOWER_PATCH.get(), name(BWGBlocks.FLOWER_PATCH.get()));
            rotatableBlock(BWGBlocks.LEAF_PILE.get());
            simpleBlockWithItem(BWGBlocks.PALE_PUMPKIN.get(), models().getExistingFile(blockBWGTexture(BWGBlocks.PALE_PUMPKIN.get())));
            ModelFile carved = models().orientable(name(BWGBlocks.CARVED_PALE_PUMPKIN.get()), blockBWGTexture(BWGBlocks.PALE_PUMPKIN.get(), "side"), blockBWGTexture(BWGBlocks.CARVED_PALE_PUMPKIN.get()), blockBWGTexture(BWGBlocks.PALE_PUMPKIN.get(), "top"));
            simpleBlockItem(BWGBlocks.CARVED_PALE_PUMPKIN.get(), carved);
            getVariantBuilder(BWGBlocks.CARVED_PALE_PUMPKIN.get()).forAllStates(state -> {
               if (state.getValue(CarvedPumpkinBlock.FACING) == Direction.EAST)
                   return ConfiguredModel.builder().rotationY(90).modelFile(carved).build();
                else if (state.getValue(CarvedPumpkinBlock.FACING) == Direction.WEST)
                    return ConfiguredModel.builder().rotationY(270).modelFile(carved).build();
                else if (state.getValue(CarvedPumpkinBlock.FACING) == Direction.SOUTH)
                    return ConfiguredModel.builder().rotationY(180).modelFile(carved).build();
                else if (state.getValue(CarvedPumpkinBlock.FACING) == Direction.NORTH)
                    return ConfiguredModel.builder().modelFile(carved).build();
                return new ConfiguredModel[0];
            });
            ModelFile jack = models().orientable(name(BWGBlocks.PALE_JACK_O_LANTERN.get()), blockBWGTexture(BWGBlocks.PALE_PUMPKIN.get(), "side"), blockBWGTexture(BWGBlocks.PALE_JACK_O_LANTERN.get()), blockBWGTexture(BWGBlocks.PALE_PUMPKIN.get(), "top"));
            simpleBlockItem(BWGBlocks.PALE_JACK_O_LANTERN.get(), jack);
            getVariantBuilder(BWGBlocks.PALE_JACK_O_LANTERN.get()).forAllStates(state -> {
                if (state.getValue(CarvedPumpkinBlock.FACING) == Direction.EAST)
                    return ConfiguredModel.builder().rotationY(90).modelFile(jack).build();
                else if (state.getValue(CarvedPumpkinBlock.FACING) == Direction.WEST)
                    return ConfiguredModel.builder().rotationY(270).modelFile(jack).build();
                else if (state.getValue(CarvedPumpkinBlock.FACING) == Direction.SOUTH)
                    return ConfiguredModel.builder().rotationY(180).modelFile(jack).build();
                else if (state.getValue(CarvedPumpkinBlock.FACING) == Direction.NORTH)
                    return ConfiguredModel.builder().modelFile(jack).build();
                return new ConfiguredModel[0];
            });

            models().withExistingParent(name(BWGBlocks.WITCH_HAZEL_BRANCH.get()), mcLoc("block/coral_wall_fan")).texture("fan", blockBWGTexture(BWGBlocks.WITCH_HAZEL_BRANCH.get())).renderType("cutout_mipped");
            getVariantBuilder(BWGBlocks.WITCH_HAZEL_BRANCH.get()).forAllStatesExcept(state -> switch (state.getValue(TreeBranchBlock.FACING)) {
                case EAST ->
                        ConfiguredModel.builder().rotationY(90).modelFile(models().getExistingFile(blockBWGTexture(BWGBlocks.WITCH_HAZEL_BRANCH.get()))).build();
                case WEST ->
                        ConfiguredModel.builder().rotationY(270).modelFile(models().getExistingFile(blockBWGTexture(BWGBlocks.WITCH_HAZEL_BRANCH.get()))).build();
                case SOUTH ->
                        ConfiguredModel.builder().rotationY(180).modelFile(models().getExistingFile(blockBWGTexture(BWGBlocks.WITCH_HAZEL_BRANCH.get()))).build();
                default ->
                        ConfiguredModel.builder().modelFile(models().getExistingFile(blockBWGTexture(BWGBlocks.WITCH_HAZEL_BRANCH.get()))).build();
            }, TreeBranchBlock.WATERLOGGED);
            simpleBlockWithItem(BWGBlocks.WITCH_HAZEL_BLOSSOM.get(), models().getExistingFile(blockBWGTexture(BWGBlocks.WITCH_HAZEL_BLOSSOM.get())));
            simpleBlockWithItem(BWGWood.SPIRIT_ROOTS.get(), models().getExistingFile(blockBWGTexture(BWGWood.SPIRIT_ROOTS.get())));

            registerPatchBlockStates(BWGBlocks.CLOVER_PATCH.get(), new String[]{"clover_patch", "clover_patch2", "clover_patch3", "clover_patch4"});
            registerPatchBlockStates(BWGBlocks.FLOWER_PATCH.get(), new String[]{"flower_patch", "flower_patch2", "flower_patch3"});
        }

        private void registerPatchBlockStates(Block block, String[] models) {
            VariantBlockStateBuilder builder = getVariantBuilder(block);
            int[] rotations = {0, 90, 180, 270};

            for (String model : models) {
                for (int rotation : rotations) {
                    builder.partialState()
                            .addModels(new ConfiguredModel(models().getExistingFile(modLoc("block/" + model)), 0, rotation, false));
                }
            }
        }

        private void snowyRotatableBlock(Block block) {
            getVariantBuilder(block).partialState().with(BlockStateProperties.SNOWY, false)
                    .addModels(createRotatedModels(models().getExistingFile(blockBWGTexture(block))))
                    .partialState().with(BlockStateProperties.SNOWY, true)
                    .addModels(ConfiguredModel.builder().modelFile(models().getExistingFile(blockBWGTexture(block, "snowy"))).build());
        }

        private void rotatableBlock(Block block) {
            getVariantBuilder(block).partialState().addModels(createRotatedModels(models().getExistingFile(blockBWGTexture(block))));
        }

        private void rotatableBlockWithItem(Block block, ModelFile modelFile) {
            getVariantBuilder(block).partialState().addModels(createRotatedModels(modelFile));
            simpleBlockItem(block, modelFile);
        }

        private void rotatableBlock(Block block, ModelFile modelFile) {
            getVariantBuilder(block).partialState().addModels(createRotatedModels(modelFile));
        }

        private ConfiguredModel[] createRotatedModels(ModelFile model) {
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .nextModel().modelFile(model).rotationY(90)
                    .nextModel().modelFile(model).rotationY(180)
                    .nextModel().modelFile(model).rotationY(270)
                    .build();
        }

        private ResourceLocation woodBlockTexture(String type, String name) {
            return BiomesWeveGone.id(ModelProvider.BLOCK_FOLDER + "/" + type + "/" + name);
        }

        private ResourceLocation blockBWGTexture(Block block) {
            return BiomesWeveGone.id(ModelProvider.BLOCK_FOLDER + "/" + key(block).getPath());
        }

        private ResourceLocation blockBWGTexture(Block block, String end) {
            return BiomesWeveGone.id(ModelProvider.BLOCK_FOLDER + "/" + key(block).getPath() + "_" + end);
        }

        private ResourceLocation blockBWGTexture(String block) {
            return BiomesWeveGone.id(ModelProvider.BLOCK_FOLDER + "/" + block);
        }

        private void woodBlock(RotatedPillarBlock block, ResourceLocation texture) {
            getVariantBuilder(block).forAllStates(state -> {
                if (state.getValue(RotatedPillarBlock.AXIS).equals(Direction.Axis.X))
                    return ConfiguredModel.builder().rotationX(90).rotationY(90).modelFile(models().cubeColumn(name(block), texture, texture)).build();
                else if (state.getValue(RotatedPillarBlock.AXIS).equals(Direction.Axis.Y))
                    return ConfiguredModel.builder().modelFile(models().cubeColumn(name(block), texture, texture)).build();
                else
                    return ConfiguredModel.builder().rotationX(90).modelFile(models().cubeColumn(name(block), texture, texture)).build();
            });
            simpleBlockItem(block, models().cubeColumn(name(block), texture, texture));
        }

        private void createDoubleBlock(DoublePlantBlock doubleBlock) {
            getVariantBuilder(doubleBlock)
                    .partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER).addModels(new ConfiguredModel(models().cross(name(doubleBlock) + "_bottom", blockBWGTexture(doubleBlock, "bottom")).texture("particle", blockBWGTexture(doubleBlock, "bottom")).renderType("cutout")))
                    .partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER).addModels(new ConfiguredModel(models().cross(name(doubleBlock) + "_top", blockBWGTexture(doubleBlock, "top")).texture("particle", blockBWGTexture(doubleBlock, "top")).renderType("cutout")));
            simpleItemBlockTexture(doubleBlock, name(doubleBlock) + "_top");
        }

        private void createCrossBlock(Block block, String renderType) {
            simpleBlock(block, models().cross(name(block), blockBWGTexture(block)).renderType(renderType));
            simpleItemBlockTexture(block, name(block));
        }

        private void createFlowerBlock(FlowerBlock block) {
            simpleBlock(block, models().cross(name(block), blockBWGTexture(block)).renderType("cutout"));
            simpleItemBlockTexture(block, name(block));
        }

        private void simpleItemBlockTexture(Block block, String texture) {
            itemModels().singleTexture(key(block).getPath(), mcLoc("item/generated"), "layer0", BiomesWeveGone.id(ModelProvider.BLOCK_FOLDER + "/" + texture));
        }

        private void simpleItemBlockTexture(Block block, ResourceLocation texture) {
            itemModels().singleTexture(key(block).getPath(), mcLoc("item/generated"), "layer0", texture);
        }

        private void registerStairs(StairBlock stairs, Block texturedBlock) {
            registerStairs(stairs, ModelLocationUtils.getModelLocation(texturedBlock));
        }

        private void registerStairs(StairBlock stairs, ResourceLocation texture) {
            stairsBlock(stairs, texture);
            simpleBlockItem(stairs, itemModels().stairs("block/" + key(stairs).getPath(), texture, texture, texture));
        }

        private void registerSlab(SlabBlock slab, Block texturedBlock) {
            ResourceLocation texture = ModelLocationUtils.getModelLocation(texturedBlock);
            slabBlock(slab, texture, texture);
            simpleBlockItem(slab, itemModels().slab("block/" + key(slab).getPath(), texture, texture, texture));
        }

        private void registerWall(WallBlock wall, Block texturedBlock) {
            ResourceLocation texture = ModelLocationUtils.getModelLocation(texturedBlock);
            wallBlock(wall, texture);
            simpleBlockItem(wall, itemModels().wallInventory("block/" + key(wall).getPath(), texture));
        }

        private void registerButton(ButtonBlock button, ResourceLocation texture) {
            buttonBlock(button, texture);
            itemModels().buttonInventory(key(button).getPath(), texture);
        }

        private void registerFenceAndGate(FenceBlock fence, FenceGateBlock gate, ResourceLocation texture) {
            fenceBlock(fence, texture);
            itemModels().fenceInventory(key(fence).getPath(), texture);
            fenceGateBlock(gate, texture);
            itemModels().fenceGate(key(gate).getPath(), texture);
        }

        private void registerSaplingBlock(Block saplingBlock, ResourceLocation texture) {
            simpleBlock(saplingBlock, models().cross(name(saplingBlock), texture).renderType("cutout"));
            simpleItemBlockTexture(saplingBlock, texture);
        }

        private void registerPottedSapling(Block block, ResourceLocation texture) {
            simpleBlock(block, models().withExistingParent(name(block), mcLoc("block/flower_pot_cross")).texture("plant", texture).renderType("cutout"));
        }

        private void registerSapling(PottedBlock sapling, ResourceLocation texture) {
            registerSaplingBlock(sapling.getBlock(), texture);
            registerPottedSapling(sapling.getPottedBlock(), texture);
        }

        private void log(BWGWoodSet set) {
            log(set.logstem(), woodBlockTexture(set.name(), set.logStemEnum().getName()), woodBlockTexture(set.name(), set.logStemEnum().getName() + "_top"));
        }

        private void log(RotatedPillarBlock block, ResourceLocation side, ResourceLocation top) {
            axisBlock(block, side, top);
            itemModels().cubeColumn(name(block), side, top);
        }

        private void defaultModelFile(Block... block) {
            for (Block b : block)
                simpleBlock(b, models().getExistingFile(blockBWGTexture(b)));
        }

        private void simpleBlockItemExistingModel(Block block) {
            simpleBlockItem(block, models().getExistingFile(blockBWGTexture(block)));
        }

        private String name(Block block) {
            return key(block).getPath();
        }

        private ResourceLocation key(Block block) {
            return BuiltInRegistries.BLOCK.getKey(block);
        }
    }
}

