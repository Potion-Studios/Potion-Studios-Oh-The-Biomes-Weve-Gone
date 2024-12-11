package net.potionstudios.biomeswevegone.world.level.levelgen.structure.processor.processors;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.potionstudios.biomeswevegone.world.level.block.plants.tree.fruit.BWGFruitBlock;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.processor.BWGCustomStructureProcessors;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Places Fruit Blocks Below Supported Leaves Blocks, if the block below is air.
 * @see StructureProcessor
 * @author Joseph T. McQuigg
 */
public class FruitBlockProcessor extends StructureProcessor {

	public static final Codec<FruitBlockProcessor> CODEC = BuiltInRegistries.BLOCK.byNameCodec()
			.fieldOf("fruit_block")
			.xmap(FruitBlockProcessor::new, processor -> processor.fruitBlock)
			.codec();

	private final BWGFruitBlock fruitBlock;

	public FruitBlockProcessor(BWGFruitBlock fruitBlock) {
		this.fruitBlock = fruitBlock;
	}

	private FruitBlockProcessor(Block fruitBlock) {
		this((BWGFruitBlock) fruitBlock);
	}

	@Override
	public @NotNull List<StructureTemplate.StructureBlockInfo> finalizeProcessing(@NotNull ServerLevelAccessor serverLevel, @NotNull BlockPos offset, @NotNull BlockPos pos, @NotNull List<StructureTemplate.StructureBlockInfo> originalBlockInfos, @NotNull List<StructureTemplate.StructureBlockInfo> processedBlockInfos, @NotNull StructurePlaceSettings settings) {
		List<StructureTemplate.StructureBlockInfo> newInfo = new java.util.ArrayList<>(List.copyOf(processedBlockInfos));

		processedBlockInfos.stream().filter(blockInfo -> blockInfo.state().is(this.fruitBlock.getLeaves())).forEach(leavesBlockInfo -> {
			BlockPos fruitPos = leavesBlockInfo.pos().below();
			if (serverLevel.getBlockState(fruitPos).isAir() && serverLevel.getRandom().nextBoolean())
				newInfo.stream().filter(blockInfo -> blockInfo.pos().equals(fruitPos)).findFirst().ifPresent(fruitSpot ->
						newInfo.set(newInfo.indexOf(fruitSpot), new StructureTemplate.StructureBlockInfo(fruitSpot.pos(), this.fruitBlock.defaultBlockState().setValue(BWGFruitBlock.AGE, serverLevel.getRandom().nextInt(BWGFruitBlock.MAX_AGE)), fruitSpot.nbt())));
		});
		return newInfo;
	}

	@Override
	protected @NotNull StructureProcessorType<?> getType() {
		return BWGCustomStructureProcessors.FRUIT_BLOCK_PROCESSOR.get();
	}
}
