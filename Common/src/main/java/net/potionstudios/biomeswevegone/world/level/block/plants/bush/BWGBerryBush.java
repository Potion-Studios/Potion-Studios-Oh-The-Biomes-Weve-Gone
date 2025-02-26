package net.potionstudios.biomeswevegone.world.level.block.plants.bush;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
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

    public BWGBerryBush(Supplier<Supplier<Item>> item, boolean hurtEntityInside, String id) {
        this(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH).setId(BiomesWeveGone.key(Registries.BLOCK, id)), item, hurtEntityInside);
    }

    @Override
    protected @NotNull ItemStack getCloneItemStack(@NotNull LevelReader level, @NotNull BlockPos pos, @NotNull BlockState state, boolean includeData) {
        return item.get().get().getDefaultInstance();
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull BlockHitResult hitResult) {
        int age = state.getValue(AGE);
        if (age > 1) {
            int numberOfItems = 1 + level.random.nextInt(2);
            popResource(level, pos, new ItemStack(item.get().get(), numberOfItems + ((age == MAX_AGE) ? 1 : 0)));
            level.playSound(player, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            BlockState blockState = state.setValue(AGE, 1);
            level.setBlock(pos, blockState, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockState));
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void entityInside(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Entity entity) {
        if (hurtEntityInside) super.entityInside(state, level, pos, entity);
    }
}
