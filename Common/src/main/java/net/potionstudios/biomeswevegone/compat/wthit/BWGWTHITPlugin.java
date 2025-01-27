package net.potionstudios.biomeswevegone.compat.wthit;

import mcp.mobius.waila.api.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.StemBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.bush.BWGBerryBush;
import net.potionstudios.biomeswevegone.world.level.block.plants.bush.OddionCrop;

/**
 * Waila plugin for BiomesWeveGone.
 * @see IWailaClientPlugin
 * @author Joseph T. McQuigg
 */
public class BWGWTHITPlugin implements IWailaClientPlugin, IWailaCommonPlugin {

	@Override
	public void register(IClientRegistrar registrar) {
		registrar.body(BWGPlantProvider.INSTANCE, BWGBerryBush.class);
		registrar.body(BWGPlantProvider.INSTANCE, StemBlock.class);
		registrar.body(BWGPlantProvider.INSTANCE, SaplingBlock.class);
		registrar.body(BWGOddionCropProvider.INSTANCE, OddionCrop.class);
	}

	@Override
	public void register(ICommonRegistrar registrar) {
		registrar.featureConfig(ResourceLocation.withDefaultNamespace("crop_progress"), true);
	}
}
