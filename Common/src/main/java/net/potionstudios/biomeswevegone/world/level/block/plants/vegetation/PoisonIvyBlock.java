package net.potionstudios.biomeswevegone.world.level.block.plants.vegetation;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class PoisonIvyBlock extends VineBlock {

    public PoisonIvyBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.VINE));
    }

    @Override
    public void entityInside(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Entity entity) {
        if (level.getDifficulty() != Difficulty.PEACEFUL)
            if (entity instanceof LivingEntity livingentity)
                if (!livingentity.isInvulnerableTo(entity.damageSources().magic()) && !livingentity.hasEffect(MobEffects.POISON))
                    livingentity.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 0));
    }
}
