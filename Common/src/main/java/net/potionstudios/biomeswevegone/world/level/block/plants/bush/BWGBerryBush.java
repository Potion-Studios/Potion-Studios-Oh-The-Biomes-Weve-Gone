package net.potionstudios.biomeswevegone.world.level.block.plants.bush;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class BWGBerryBush extends SweetBerryBushBlock {

    protected final Supplier<Supplier<Item>> item;
    private final boolean hurtEntityInside;

    public BWGBerryBush(Properties properties, Supplier<Supplier<Item>> item, boolean hurtEntityInside) {
        super(properties);
        this.item = item;
        this.hurtEntityInside = hurtEntityInside;
    }

    public BWGBerryBush(Supplier<Supplier<Item>> item, boolean hurtEntityInside) {
        this(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH), item, hurtEntityInside);
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(@NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull BlockState state) {
        return item.get().get().getDefaultInstance();
    }

    @Override
    public @NotNull InteractionResult use(BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        int age = state.getValue(AGE);
        boolean isMaxAge = age == MAX_AGE;
        if (!isMaxAge && player.getItemInHand(hand).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else if (age > 1) {
            int numberOfItems = 1 + level.random.nextInt(2);
            popResource(level, pos, new ItemStack(item.get().get(), numberOfItems + (isMaxAge ? 1 : 0)));
            level.playSound(player, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            BlockState blockState = state.setValue(AGE, 1);
            level.setBlock(pos, blockState, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockState));
            return InteractionResult.sidedSuccess(level.isClientSide());
        } else {
            return super.use(state, level, pos, player, hand, hit);
        }
    }

    @Override
    public void entityInside(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Entity entity) {
        if (hurtEntityInside) super.entityInside(state, level, pos, entity);
    }
}
