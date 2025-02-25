package net.potionstudios.biomeswevegone.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.EatBlockGoal;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(EatBlockGoal.class)
public abstract class EatBlockGoalMixin {

    @Shadow @Final private Mob mob;

    @Shadow @Final private static Predicate<BlockState> IS_TALL_GRASS;

    @Shadow @Final private Level level;

    @Inject(method = "canUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Mob;blockPosition()Lnet/minecraft/core/BlockPos;"), cancellable = true)
    private void canUse(CallbackInfoReturnable<Boolean> cir) {
        BlockPos blockPos = mob.blockPosition();
        if (IS_TALL_GRASS.test(level.getBlockState(blockPos)))
            cir.setReturnValue(true);
        else if (level.getBlockState(blockPos.below()).is(BWGBlocks.LUSH_GRASS_BLOCK.get()))
            cir.setReturnValue(true);
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;below()Lnet/minecraft/core/BlockPos;"))
    private void tick(CallbackInfo ci, @Local BlockPos blockPos) {
        if (level instanceof ServerLevel serverLevel) {
            BlockPos below = blockPos.below();
            if (serverLevel.getBlockState(below).is(BWGBlocks.LUSH_GRASS_BLOCK.get()))
                if (serverLevel.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                    serverLevel.levelEvent(2001, below, Block.getId(BWGBlocks.LUSH_GRASS_BLOCK.get().defaultBlockState()));
                    serverLevel.setBlock(below, BWGBlocks.LUSH_DIRT.get().defaultBlockState(), 2);
                    mob.ate();
                }
        }
    }

}
