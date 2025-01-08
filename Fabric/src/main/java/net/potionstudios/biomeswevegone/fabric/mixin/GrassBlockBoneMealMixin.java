package net.potionstudios.biomeswevegone.fabric.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.potionstudios.biomeswevegone.util.BoneMealHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GrassBlock.class)
public abstract class GrassBlockBoneMealMixin {
    /**
     * @reason Allows for Bonemealing of Prairie Grass to spawn Prairie Grass and other features.
     * @see GrassBlock#performBonemeal(ServerLevel, RandomSource, BlockPos, BlockState)
     * @author Joseph T. McQuigg
     */
    @Inject(method = "performBonemeal", at = @At("HEAD"), cancellable = true)
    private void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state, CallbackInfo ci) {
        if (BoneMealHandler.bwgBoneMealEventHandler(level, pos, state))
            ci.cancel();
    }
}
