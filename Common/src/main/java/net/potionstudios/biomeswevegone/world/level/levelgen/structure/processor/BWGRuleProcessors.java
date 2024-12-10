package net.potionstudios.biomeswevegone.world.level.levelgen.structure.processor;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.levelgen.structure.templatesystem.AlwaysTrueTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.ProcessorRule;
import net.minecraft.world.level.levelgen.structure.templatesystem.RandomBlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleProcessor;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.block.plants.bush.WhitePuffballBlock;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWood;

class BWGRuleProcessors {

	protected static final RuleProcessor FORGOTTEN_ROCKY_STONE = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(BWGBlocks.ROCKY_STONE_SET.getBase(), 0.25F, BWGBlocks.MOSSY_STONE_SET.getBase()),
			createAlwaysTrueRandomBlockMatchTest(BWGBlocks.ROCKY_STONE_SET.getBase(), 0.25F, Blocks.GRAVEL),
			createAlwaysTrueRandomBlockMatchTest(BWGBlocks.ROCKY_STONE_SET.getBase(), 0.25F, Blocks.STONE)
	);

	protected static final RuleProcessor FORGOTTEN_GRAVEL = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(Blocks.GRAVEL, 0.35F, Blocks.SUSPICIOUS_GRAVEL)
	);

	protected static final RuleProcessor FORGOTTEN_LUSH_GRASS_BLOCK = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(BWGBlocks.LUSH_GRASS_BLOCK.get(), 0.45F, Blocks.MOSS_BLOCK)
	);

	protected static final RuleProcessor FORGOTTEN_MOSSY_STONE_BRICKS = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(Blocks.MOSSY_STONE_BRICKS, 0.1F, Blocks.STONE_BRICKS),
			createAlwaysTrueRandomBlockMatchTest(Blocks.MOSSY_STONE_BRICKS, 0.2F, Blocks.CRACKED_STONE_BRICKS),
			createAlwaysTrueRandomBlockMatchTest(Blocks.MOSSY_STONE_BRICKS, 0.1F, Blocks.TUFF)
	);

	protected static final RuleProcessor DIRT_RANDOM_GRASS_COARSE_DIRT = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(Blocks.DIRT, 0.33F, Blocks.GRASS_BLOCK),
			createAlwaysTrueRandomBlockMatchTest(Blocks.DIRT, 0.33F, Blocks.COARSE_DIRT)
	);

	protected static final RuleProcessor DIRT_RANDOM_GRASS_COARSE_DIRT_PODZOL = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(Blocks.DIRT, 0.25F, Blocks.GRASS_BLOCK),
			createAlwaysTrueRandomBlockMatchTest(Blocks.DIRT, 0.25F, Blocks.COARSE_DIRT),
			createAlwaysTrueRandomBlockMatchTest(Blocks.DIRT, 0.25F, Blocks.PODZOL)
	);

	protected static final RuleProcessor GRASS_RANDOM_DIRT_PODZOL_COARSE_DIRT = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(Blocks.GRASS_BLOCK, 0.25F, Blocks.DIRT),
			createAlwaysTrueRandomBlockMatchTest(Blocks.GRASS_BLOCK, 0.25F, Blocks.PODZOL),
			createAlwaysTrueRandomBlockMatchTest(Blocks.GRASS_BLOCK, 0.25F, Blocks.COARSE_DIRT)
	);

	protected static final RuleProcessor STONE_RANDOM_COBBLESTONE_MOSSY = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(Blocks.STONE, 0.15F, Blocks.COBBLESTONE),
			createAlwaysTrueRandomBlockMatchTest(Blocks.STONE, 0.15F, Blocks.MOSSY_COBBLESTONE),
			createAlwaysTrueRandomBlockMatchTest(Blocks.STONE, 0.15F, BWGBlocks.MOSSY_STONE_SET.getBase())
	);

	protected static final RuleProcessor MOSSIFY_70_PERCENT = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(Blocks.STONE, 0.7F, BWGBlocks.MOSSY_STONE_SET.getBase()),
			createAlwaysTrueRandomBlockMatchTest(Blocks.COBBLESTONE, 0.7F, Blocks.MOSSY_COBBLESTONE),
			createAlwaysTrueRandomBlockMatchTest(Blocks.STONE_BRICKS, 0.7F, Blocks.MOSSY_STONE_BRICKS),
			createAlwaysTrueRandomBlockMatchTest(Blocks.STONE_STAIRS, 0.7F, BWGBlocks.MOSSY_STONE_SET.getStairs()),
			createAlwaysTrueRandomBlockMatchTest(Blocks.COBBLESTONE_STAIRS, 0.7F, Blocks.MOSSY_COBBLESTONE_STAIRS)
	);

	protected static final RuleProcessor SWEETBERRY_BLUEBERRY_50_PERCENT_RANDOM_AGE = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(Blocks.SWEET_BERRY_BUSH, 0.125F, BWGBlocks.BLUEBERRY_BUSH.get().defaultBlockState().setValue(SweetBerryBushBlock.AGE, 0)),
			createAlwaysTrueRandomBlockMatchTest(Blocks.SWEET_BERRY_BUSH, 0.125F, BWGBlocks.BLUEBERRY_BUSH.get().defaultBlockState().setValue(SweetBerryBushBlock.AGE, 1)),
			createAlwaysTrueRandomBlockMatchTest(Blocks.SWEET_BERRY_BUSH, 0.125F, BWGBlocks.BLUEBERRY_BUSH.get().defaultBlockState().setValue(SweetBerryBushBlock.AGE, 2)),
			createAlwaysTrueRandomBlockMatchTest(Blocks.SWEET_BERRY_BUSH, 0.125F, BWGBlocks.BLUEBERRY_BUSH.get().defaultBlockState().setValue(SweetBerryBushBlock.AGE, 3)),
			createAlwaysTrueRandomBlockMatchTest(BWGBlocks.BLUEBERRY_BUSH.get(), 0.125F, Blocks.SWEET_BERRY_BUSH.defaultBlockState().setValue(SweetBerryBushBlock.AGE, 0)),
			createAlwaysTrueRandomBlockMatchTest(BWGBlocks.BLUEBERRY_BUSH.get(), 0.125F, Blocks.SWEET_BERRY_BUSH.defaultBlockState().setValue(SweetBerryBushBlock.AGE, 1)),
			createAlwaysTrueRandomBlockMatchTest(BWGBlocks.BLUEBERRY_BUSH.get(), 0.125F, Blocks.SWEET_BERRY_BUSH.defaultBlockState().setValue(SweetBerryBushBlock.AGE, 2)),
			createAlwaysTrueRandomBlockMatchTest(BWGBlocks.BLUEBERRY_BUSH.get(), 0.125F, Blocks.SWEET_BERRY_BUSH.defaultBlockState().setValue(SweetBerryBushBlock.AGE, 3)
	));

	protected static final RuleProcessor MOSSIFY_10_PERCENT = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(Blocks.COBBLESTONE, 0.1F, Blocks.MOSSY_COBBLESTONE)
	);

	protected static final RuleProcessor MOSSIFY_50_PERCENT = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(Blocks.STONE, 0.5F, BWGBlocks.MOSSY_STONE_SET.getBase()),
			createAlwaysTrueRandomBlockMatchTest(Blocks.COBBLESTONE, 0.5F, Blocks.MOSSY_COBBLESTONE),
			createAlwaysTrueRandomBlockMatchTest(Blocks.STONE_BRICKS, 0.5F, Blocks.MOSSY_STONE_BRICKS)
	);

	protected static final RuleProcessor WHITE_PUFFBALL_RANDOM_AGE = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(BWGBlocks.WHITE_PUFFBALL.getBlock(), 0.33f, BWGBlocks.WHITE_PUFFBALL.getBlockState().setValue(WhitePuffballBlock.AGE, 0)),
			createAlwaysTrueRandomBlockMatchTest(BWGBlocks.WHITE_PUFFBALL.getBlock(), 0.33f, BWGBlocks.WHITE_PUFFBALL.getBlockState().setValue(WhitePuffballBlock.AGE, 1)),
			createAlwaysTrueRandomBlockMatchTest(BWGBlocks.WHITE_PUFFBALL.getBlock(), 0.33f, BWGBlocks.WHITE_PUFFBALL.getBlockState().setValue(WhitePuffballBlock.AGE, 2))
	);

	protected static RuleProcessor packedMudToBlock(float chance, Block newBlock) {
		return createRuleProcessor(createAlwaysTrueRandomBlockMatchTest(Blocks.PACKED_MUD, chance, newBlock));
	}

	protected static final RuleProcessor STRIPPED_OAK_LOG_60_PERCENT_OAK_PLANKS = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(Blocks.STRIPPED_OAK_LOG, 0.6F, Blocks.OAK_PLANKS)
	);

	protected static RuleProcessor lushDirtToCoarseDirt(float chance) {
		return createRuleProcessor(createAlwaysTrueRandomBlockMatchTest(BWGBlocks.LUSH_DIRT_PATH.get(), chance, Blocks.COARSE_DIRT));
	}

	protected static final RuleProcessor POLISHED_ANDESITE_RANDOM_ANDESITE_GRAVEL = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(Blocks.POLISHED_ANDESITE, 0.3F, Blocks.ANDESITE),
			createAlwaysTrueRandomBlockMatchTest(Blocks.POLISHED_ANDESITE, 0.3F, Blocks.GRAVEL)
	);

	protected static final RuleProcessor STONE_SLAB_RANDOM_ANDESITE_ROCKY_SLAB = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(Blocks.STONE_SLAB, 0.33F, Blocks.ANDESITE_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM)),
			createAlwaysTrueRandomBlockMatchTest(Blocks.STONE_SLAB, 0.33F, BWGBlocks.ROCKY_STONE_SET.getSlab().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM))
	);

	protected static final RuleProcessor STONE_RANDOM_ROCKY_ANDESITE = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(Blocks.STONE, 0.33F, Blocks.ANDESITE),
			createAlwaysTrueRandomBlockMatchTest(Blocks.STONE, 0.33F, BWGBlocks.ROCKY_STONE_SET.getBase())
	);

	protected static final RuleProcessor ORANGE_TERRACOTTA_TO_RED_ROCK_CRACKED_RED_ROCK_BRICKS = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(Blocks.ORANGE_TERRACOTTA, 0.33F, BWGBlocks.RED_ROCK_SET.getBase()),
			createAlwaysTrueRandomBlockMatchTest(Blocks.ORANGE_TERRACOTTA, 0.33F, BWGBlocks.CRACKED_RED_ROCK_BRICKS_SET.getBase()),
			createAlwaysTrueRandomBlockMatchTest(Blocks.ORANGE_TERRACOTTA, 0.1F, BWGBlocks.RED_ROCK_BRICKS_SET.getBase())
	);

	protected static final RuleProcessor RED_ROCK_BRICKS_TO_ORANGE_TERRACOTTA = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(BWGBlocks.RED_ROCK_BRICKS_SET.getBase(), 0.33F, Blocks.ORANGE_TERRACOTTA)
	);

	protected static final RuleProcessor RED_ROCK_CRACKED_BRICKS_50_PERCENT_MOSSY_RED_ROCK_BRICKS = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(BWGBlocks.CRACKED_RED_ROCK_BRICKS_SET.getBase(), 0.5F, BWGBlocks.MOSSY_RED_ROCK_BRICKS_SET.getBase())
	);

	protected static final RuleProcessor STRIPPED_BAOBAB_WOOD_50_PERCENT_BAOBAB_PLANKS = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(BWGWood.BAOBAB.strippedWood(), 0.5F, BWGWood.BAOBAB.planks())
	);

	protected static final RuleProcessor RED_ROCK_25_PERCENT_RED_ROCK_BRICKS = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(BWGBlocks.RED_ROCK_SET.getBase(), 0.25F, BWGBlocks.RED_ROCK_BRICKS_SET.getBase())
	);

	protected static final RuleProcessor RED_ROCK_25_PERCENT_CRACKED_RED_ROCK_BRICKS = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(BWGBlocks.RED_ROCK_SET.getBase(), 0.25F, BWGBlocks.CRACKED_RED_ROCK_BRICKS_SET.getBase())
	);

	protected static final RuleProcessor RED_ROCK_BRICKS_35_PERCENT_CRACKED_RED_ROCK_BRICKS = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(BWGBlocks.RED_ROCK_BRICKS_SET.getBase(), 0.35F, BWGBlocks.CRACKED_RED_ROCK_BRICKS_SET.getBase())
	);

	protected static final RuleProcessor RANDOM_DESERT_POTTED_PLANT = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(Blocks.POTTED_CACTUS, 0.1f, Blocks.POTTED_DEAD_BUSH),
			createAlwaysTrueRandomBlockMatchTest(Blocks.POTTED_CACTUS, 0.2f, BWGBlocks.PRICKLY_PEAR_CACTUS.getPottedBlock()),
			createAlwaysTrueRandomBlockMatchTest(Blocks.POTTED_CACTUS, 0.2f, BWGBlocks.MINI_CACTUS.getPottedBlock()),
			createAlwaysTrueRandomBlockMatchTest(Blocks.POTTED_CACTUS, 0.2f, BWGBlocks.GOLDEN_SPINED_CACTUS.getPottedBlock())
	);

	protected static final RuleProcessor DACITE_10_PERCENT_COBBLED_DACITE = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(BWGBlocks.DACITE_SET.getBase(), 0.1f, BWGBlocks.DACITE_COBBLESTONE_SET.getBase())
	);

	protected static final RuleProcessor DACITE_VARIANTS = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(BWGBlocks.DACITE_SET.getBase(), 0.4f, BWGBlocks.DACITE_COBBLESTONE_SET.getBase()),
			createAlwaysTrueRandomBlockMatchTest(BWGBlocks.DACITE_SET.getBase(), 0.4f, BWGBlocks.DACITE_BRICKS_SET.getBase())
	);

	protected static final RuleProcessor STONEBRICKS_VARIANTS = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(Blocks.STONE_BRICKS, 0.3f, Blocks.MOSSY_STONE_BRICKS),
			createAlwaysTrueRandomBlockMatchTest(Blocks.STONE_BRICKS, 0.3f, Blocks.CRACKED_STONE_BRICKS),
			createAlwaysTrueRandomBlockMatchTest(Blocks.STONE_BRICKS, 0.3f, BWGBlocks.MOSSY_STONE_SET.getBase())
	);

	protected static final RuleProcessor PODZOL_25_PERCENT_COARSE_DIRT = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(Blocks.PODZOL, 0.25f, Blocks.COARSE_DIRT)
	);

	protected static final RuleProcessor PODZOL_10_PERCENT_GRAVEL = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(Blocks.PODZOL, 0.1f, Blocks.GRAVEL)
	);

	protected static final RuleProcessor PODZOL_10_PERCENT_ANDESITE = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(Blocks.PODZOL, 0.1f, Blocks.ANDESITE),
			createAlwaysTrueRandomBlockMatchTest(Blocks.PODZOL, 0.1f, Blocks.POLISHED_ANDESITE)
	);

	protected static final RuleProcessor PODZOL_50_PERCENT_LUSH_GRASS = createRuleProcessor(
			createAlwaysTrueRandomBlockMatchTest(Blocks.PODZOL, 0.5f, BWGBlocks.LUSH_GRASS_BLOCK.get())
	);

	protected static RuleProcessor skyrisLeavesToGreenAppleLeaves(float chance) {
		return createRuleProcessor(createAlwaysTrueRandomBlockMatchTest(BWGWood.SKYRIS.leaves(), chance, BWGWood.SKYRIS_LEAVES_GREEN_APPLE.get()));
	}

	protected static RuleProcessor skyrisLeavesToFlowering(float chance) {
		return createRuleProcessor(createAlwaysTrueRandomBlockMatchTest(BWGWood.SKYRIS.leaves(), chance, BWGWood.FLOWERING_SKYRIS_LEAVES.get()));
	}

	private static ProcessorRule createAlwaysTrueRandomBlockMatchTest(Block start, float chance, Block newBlock) {
		return createProcessorRule(createRandomBlockMatchTest(start, chance), AlwaysTrueTest.INSTANCE, newBlock.defaultBlockState());
	}

	private static ProcessorRule createAlwaysTrueRandomBlockMatchTest(Block start, float chance, BlockState newBlock) {
		return createProcessorRule(createRandomBlockMatchTest(start, chance), AlwaysTrueTest.INSTANCE, newBlock);
	}

	private static RandomBlockMatchTest createRandomBlockMatchTest(Block block, float chance) {
		return new RandomBlockMatchTest(block, chance);
	}

	private static ProcessorRule createProcessorRule(RandomBlockMatchTest test, AlwaysTrueTest alwaysTrueTest, net.minecraft.world.level.block.state.BlockState blockState) {
		return new ProcessorRule(test, alwaysTrueTest, blockState);
	}

	private static RuleProcessor createRuleProcessor(ProcessorRule... rules) {
		return new RuleProcessor(ImmutableList.copyOf(rules));
	}
}
