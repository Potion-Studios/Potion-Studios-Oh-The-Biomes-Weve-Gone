package net.potionstudios.biomeswevegone.world.level.block.plants.bush;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
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

    public static final IntegerProperty TIMER = IntegerProperty.create("timer", 0, 10);
    public static final BooleanProperty HATCHING = BooleanProperty.create("hatching");

    public OddionCrop(BlockBehaviour.Properties properties) {
        super(properties, () -> BWGItems.ODDION_BULB, false);
        this.registerDefaultState(this.stateDefinition.any().setValue(HATCHING, false).setValue(TIMER, 0));
    }

    @Override
    protected @NotNull InteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if (stack.is(ItemTags.HOES) && state.getValue(AGE) == MAX_AGE) {
            maxAgeHarvest(state, level, pos, player);
            return InteractionResult.SUCCESS;
        } else if (stack.is(Items.BONE_MEAL))
            return InteractionResult.PASS;
        return InteractionResult.PASS;
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull BlockHitResult hitResult) {
        if (state.getValue(AGE) == MAX_AGE) {
            maxAgeHarvest(state, level, pos, player);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    private void maxAgeHarvest(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player) {
        popResource(level, pos, new ItemStack(BWGItems.ODDION_BULB.get(), 2));
        level.playSound(player, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
        BlockState blockState = state.setValue(AGE, 1).setValue(TIMER, 0).setValue(HATCHING, false);
        level.setBlock(pos, blockState, 2);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockState));
    }

    private boolean shouldHatch(@NotNull ServerLevel level, @NotNull BlockState state) {
        return level.getRandom().nextInt(10 - state.getValue(TIMER)) == 0;
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState state) {
        return state.getValue(AGE) < MAX_AGE || state.getValue(HATCHING);
    }

    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (state.getValue(HATCHING)) {
            if (shouldHatch(level, state))
                spawnOddion(level, pos);
            else {
                level.setBlockAndUpdate(pos, state.setValue(TIMER, state.getValue(TIMER) + 1));
                level.playSound(null, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1, 1);
            }
            return;
        }
        super.randomTick(state, level, pos, random);
    }

    @Override
    public void animateTick(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (state.getValue(HATCHING) && random.nextInt(6) == 0)
            level.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX() + 0.5, pos.getY() + 0.6, pos.getZ() + 0.5, 0.0, 0.0, 0.0);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(TIMER, HATCHING));
    }

    @Override
    protected boolean mayPlaceOn(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
        return state.getBlock() instanceof FarmBlock;
    }

    private void spawnOddion(@NotNull ServerLevel level, @NotNull BlockPos pos) {
        Oddion oddion = new Oddion(level);
        oddion.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        level.addFreshEntity(oddion);
        level.destroyBlock(pos, true);
    }

    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader level, @NotNull BlockPos pos, @NotNull BlockState state) {
        return !state.getValue(HATCHING);
    }

    @Override
    public void performBonemeal(@NotNull ServerLevel level, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState state) {
        if (state.getValue(AGE) == MAX_AGE)
            level.setBlockAndUpdate(pos, state.setValue(HATCHING, true));

        else super.performBonemeal(level, random, pos, state);
    }
}