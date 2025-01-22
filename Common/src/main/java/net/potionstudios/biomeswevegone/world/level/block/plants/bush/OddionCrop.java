package net.potionstudios.biomeswevegone.world.level.block.plants.bush;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.potionstudios.biomeswevegone.world.entity.oddion.Oddion;
import net.potionstudios.biomeswevegone.world.item.BWGItems;
import org.jetbrains.annotations.NotNull;

public class OddionCrop extends BWGBerryBush {

    private static final IntegerProperty TIMER = IntegerProperty.create("timer", 0, 10);
    private static final BooleanProperty HATCHING = BooleanProperty.create("hatching");

    public OddionCrop() {
        super(() -> BWGItems.ODDION_BULB, false);
        this.registerDefaultState(this.stateDefinition.any().setValue(HATCHING, false).setValue(TIMER, 0));
    }

    @Override
    public @NotNull InteractionResult use(BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if (player.getItemInHand(hand).is(Items.BONE_MEAL))
            if (state.getValue(AGE) == MAX_AGE && !state.getValue(HATCHING)) {
                BlockState blockState = state.cycle(HATCHING);
                level.setBlockAndUpdate(pos, blockState);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, state));
                level.addParticle(ParticleTypes.HAPPY_VILLAGER, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, 0.0, 0.0, 0.0);
                level.playLocalSound(pos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.AMBIENT, 2 ,1, false);
                return InteractionResult.sidedSuccess(level.isClientSide());
            }
        return InteractionResult.PASS;
    }

    private boolean shouldHatch(Level level, BlockState state) {
        return level.random.nextInt(10 - state.getValue(TIMER)) == 0;
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState state) {
        return true;
    }

    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (state.getValue(HATCHING)) {
            if (shouldHatch(level, state)) {
                spawnOddion(level, pos);
            } else {
                level.setBlockAndUpdate(pos, state.setValue(TIMER, state.getValue(TIMER) + 1));
                if (level.isClientSide()) {
                    level.addParticle(ParticleTypes.HAPPY_VILLAGER, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, 0.0, 0.0, 0.0);
                    level.playSound(null, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1, 1);
                }
            }
        }
        super.randomTick(state, level, pos, random);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TIMER, HATCHING);
        super.createBlockStateDefinition(builder);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
        return state.getBlock() instanceof FarmBlock;
    }

    private void spawnOddion(Level level, BlockPos pos) {
        Oddion oddion = new Oddion(level);
        oddion.setPos(pos.getX(), pos.getY(), pos.getZ());
        level.addFreshEntity(oddion);
        level.destroyBlock(pos, true);
    }
}