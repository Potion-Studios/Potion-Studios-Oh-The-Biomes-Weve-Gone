package net.potionstudios.biomeswevegone.world.level.block.plants.vegetation.pumpkin;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class PaleCarvedPumpkinBlock extends CarvedPumpkinBlock {

    @Nullable
    private BlockPattern snowGolemFull;
    @Nullable
    private BlockPattern ironGolemFull;
    private static final Predicate<BlockState> PALE_PUMPKINS_PREDICATE = blockState -> blockState != null
            && (blockState.is(BWGBlocks.CARVED_PALE_PUMPKIN.get()) || blockState.is(BWGBlocks.PALE_JACK_O_LANTERN.get()));

    public PaleCarvedPumpkinBlock(Properties properties) {
        super(properties);
    }

    public PaleCarvedPumpkinBlock() {
        this(BlockBehaviour.Properties.ofFullCopy(Blocks.JACK_O_LANTERN).lightLevel(light -> 14));
    }

    @Override
    protected void onPlace(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState oldState, boolean movedByPiston) {
        if (!oldState.is(state.getBlock()))
            trySpawnGolem(level, pos);
    }

    private void trySpawnGolem(Level level, BlockPos pos) {
        BlockPattern.BlockPatternMatch blockPatternMatch = this.getOrCreateSnowGolemFull().find(level, pos);
        if (blockPatternMatch != null) {
            SnowGolem snowGolem = EntityType.SNOW_GOLEM.create(level, EntitySpawnReason.TRIGGERED);
            if (snowGolem != null) {
                spawnGolemInWorld(level, blockPatternMatch, snowGolem, blockPatternMatch.getBlock(0, 2, 0).getPos());
            }
        } else {
            BlockPattern.BlockPatternMatch blockPatternMatch2 = this.getOrCreateIronGolemFull().find(level, pos);
            if (blockPatternMatch2 != null) {
                IronGolem ironGolem = EntityType.IRON_GOLEM.create(level, EntitySpawnReason.TRIGGERED);
                if (ironGolem != null) {
                    ironGolem.setPlayerCreated(true);
                    spawnGolemInWorld(level, blockPatternMatch2, ironGolem, blockPatternMatch2.getBlock(1, 2, 0).getPos());
                }
            }
        }
    }

    private static void spawnGolemInWorld(Level level, BlockPattern.BlockPatternMatch patternMatch, Entity golem, BlockPos pos) {
        clearPatternBlocks(level, patternMatch);
        golem.moveTo((double)pos.getX() + 0.5, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.5, 0.0F, 0.0F);
        level.addFreshEntity(golem);

        for (ServerPlayer serverPlayer : level.getEntitiesOfClass(ServerPlayer.class, golem.getBoundingBox().inflate(5.0))) {
            CriteriaTriggers.SUMMONED_ENTITY.trigger(serverPlayer, golem);
        }

        updatePatternBlocks(level, patternMatch);
    }

    private BlockPattern getOrCreateSnowGolemFull() {
        if (this.snowGolemFull == null) {
            this.snowGolemFull = BlockPatternBuilder.start()
                    .aisle("^", "#", "#")
                    .where('^', BlockInWorld.hasState(PALE_PUMPKINS_PREDICATE))
                    .where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.SNOW_BLOCK)))
                    .build();
        }

        return this.snowGolemFull;
    }

    private BlockPattern getOrCreateIronGolemFull() {
        if (this.ironGolemFull == null) {
            this.ironGolemFull = BlockPatternBuilder.start()
                    .aisle("~^~", "###", "~#~")
                    .where('^', BlockInWorld.hasState(PALE_PUMPKINS_PREDICATE))
                    .where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.IRON_BLOCK)))
                    .where('~', blockInWorld -> blockInWorld.getState().isAir())
                    .build();
        }

        return this.ironGolemFull;
    }
}
