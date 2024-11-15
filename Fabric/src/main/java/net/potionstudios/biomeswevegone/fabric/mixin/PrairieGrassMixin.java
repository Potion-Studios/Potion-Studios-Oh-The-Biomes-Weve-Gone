package net.potionstudios.biomeswevegone.fabric.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.potionstudios.biomeswevegone.util.BoneMealHandler;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.levelgen.biome.BWGBiomes;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.placed.BWGOverworldVegationPlacedFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GrassBlock.class)
public abstract class PrairieGrassMixin extends SpreadingSnowyDirtBlock implements BonemealableBlock {

    protected PrairieGrassMixin(Properties properties) {
        super(properties);
    }

    /**
     * @reason Allows for Bonemealing of Prairie Grass to spawn Prairie Grass and other features.
     * @see GrassBlock#performBonemeal(ServerLevel, RandomSource, BlockPos, BlockState)
     * @author Joseph T. McQuigg
     */
    @Inject(method = "performBonemeal", at = @At("HEAD"), cancellable = true)
    private void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state, CallbackInfo ci) {
        boolean cancel = false;
        if (state.is(Blocks.GRASS_BLOCK))
            if (level.getBiome(pos).is(BWGBiomes.PRAIRIE))
                cancel = BoneMealHandler.grassBoneMealHandler(level, pos.above(), BWGBlocks.PRAIRIE_GRASS.get(), BWGOverworldVegationPlacedFeatures.PRAIRIE_GRASS_BONEMEAL, false);
            else if (level.getBiome(pos).is(BWGBiomes.ALLIUM_SHRUBLAND))
                cancel = BoneMealHandler.grassBoneMealHandler(level, pos.above(), Blocks.SHORT_GRASS, VegetationPlacements.GRASS_BONEMEAL, true);
        if (cancel)
            ci.cancel();
    }
}
