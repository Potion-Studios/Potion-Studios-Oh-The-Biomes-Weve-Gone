package net.potionstudios.biomeswevegone.compat.wthit;

import mcp.mobius.waila.api.*;
import mcp.mobius.waila.api.component.PairComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.level.block.plants.bush.BWGBerryBush;

/**
 * Allows seeing bwg plant information in Waila.
 * @see IBlockComponentProvider
 * @author Joseph T. McQuigg
 */
enum BWGPlantProvider implements IBlockComponentProvider {

	INSTANCE;

	private static void addMaturityTooltip(ITooltip tooltip, float growthValue) {
		growthValue *= 100.0F;
		if (growthValue < 100.0F)
			tooltip.addLine(new PairComponent(
					Component.translatable("tooltip.waila.crop_growth"), Component.literal(String.format("%.0f%%", growthValue))));
		else
			tooltip.addLine(new PairComponent(
					Component.translatable("tooltip.waila.crop_growth"), Component.translatable("tooltip.waila.crop_mature")
			));
	}

	private static void addGrowableTooltip(ITooltip tooltip, ResourceLocation tag, String translationKey, boolean growable) {
		tooltip.setLine(tag, new PairComponent(Component.translatable(translationKey),
				growable ? Component.translatable("tooltip.waila.true") : Component.translatable("tooltip.waila.false")));
	}

	private static void addCropGrowableTooltip(ITooltip tooltip, IBlockAccessor accessor) {
		int lightLevel = accessor.getWorld().getRawBrightness(accessor.getPosition(), 0);
		addGrowableTooltip(tooltip, new ResourceLocation("plant.crop_growable"), "tooltip.waila.crop_growable", lightLevel >= 9);
	}

	private static void addTreeGrowableTooltip(ITooltip tooltip, IBlockAccessor accessor) {
		int lightLevel = accessor.getWorld().getRawBrightness(accessor.getPosition(), 0);
		addGrowableTooltip(tooltip, new ResourceLocation("plant.tree_growable"), "tooltip.waila.tree_growable", lightLevel >= 9);
	}

	@Override
	public void appendBody(ITooltip tooltip, IBlockAccessor accessor, IPluginConfig config) {
		Block block = accessor.getBlock();
		if (!IModInfo.get(block).getId().equals(BiomesWeveGone.MOD_ID)) return;

		BlockState state = accessor.getBlockState();

		if (config.getBoolean(new ResourceLocation("plant.crop_progress")))
			if (block instanceof BWGBerryBush)
				addMaturityTooltip(tooltip, (float) state.getValue(BWGBerryBush.AGE) / BWGBerryBush.MAX_AGE);
			else if (block instanceof StemBlock)
				addMaturityTooltip(tooltip, (float) state.getValue(StemBlock.AGE) / StemBlock.MAX_AGE);

		if (config.getBoolean(new ResourceLocation("plant.crop_growable")))
			if (block instanceof CropBlock || block instanceof StemBlock)
				addCropGrowableTooltip(tooltip, accessor);

		if (config.getBoolean(new ResourceLocation("plant.tree_growable")))
			if (block instanceof SaplingBlock)
				addTreeGrowableTooltip(tooltip, accessor);
	}
}